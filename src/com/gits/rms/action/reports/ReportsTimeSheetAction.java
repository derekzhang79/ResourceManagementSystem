
package com.gits.rms.action.reports;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.ReportsAction;
import com.gits.rms.service.ClientDaoService;
import com.gits.rms.service.ClientService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpenseStatusTrackerDaoService;
import com.gits.rms.service.ExpenseStatusTrackerService;
import com.gits.rms.service.ExpensesTypeDaoService;
import com.gits.rms.service.ExpensesTypeService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesTypeVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimesheetReportVO;

public class ReportsTimeSheetAction extends ActionSupport {

    private ReportsVO report;
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;
    private TimeSheetCategoryAssignVO timeSheetCategoryAssign = new TimeSheetCategoryAssignVO();
    private ArrayList timeSheetReportList = new ArrayList();
    private ArrayList displayList = new ArrayList();
    private TimeSheetCategoryAssignService timesheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetProjeectAssignedService timeProService=new TimeSheetProjeectAssignedDaoService();
    EmployeesService employeeServive = new EmployeesDaoService();
    ClientService clientService = new ClientDaoService();
    TimeSheetApproverService timeSheetApproverService = new TimeSheetApproverDaoService();
    private List<EmployeesVO> employeeList;
    private EmployeesService employeeService = new EmployeesDaoService();

    List<ClientVO> clientDetails = new ArrayList<ClientVO>();

    private Map reportParameters = new HashMap();
    Set timesheetProjectList = new HashSet();
    Set timesheetCategoryList = new HashSet();
    private List<LeaveHistoryVO> leaveList;
    private LeaveHistoryService leaveService = new LeaveHistoryDaoService();
    private List<ExpenseStatusTrackerVO> expensesList;
    private ExpenseStatusTrackerService expensesService = new ExpenseStatusTrackerDaoService();
    private ExpensesTypeService expTypeService = new ExpensesTypeDaoService();
    private InputStream inStream;
    protected PdfPTable footer;
    private ReportsAction repoAction;
    private List<EmployeesVO> subEmpIdListObj;
    
    List<EmployeesVO> employeelist;
    @Override
    public String execute() throws Exception {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Map mSession = ActionContext.getContext().getSession();
//          CLIENT ID ADDED BY BALA
            Integer clientId = (Integer) mSession.get("CLIENT_INFO"); 
            String timesheetRole = String.valueOf(mSession.get("TIMESHEET_APPROVER"));
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            subEmpIdListObj = employeeService.getCurrentTimeSheetSubEmployee();
            subEmpIdListObj.add(oEmp);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("timeSheetSubEmpListReport", subEmpIdListObj);
            
            if (report.getEmpIdObjList().size() == 0) {
            	
                employeeList = employeeService.getAllEmployees(clientId);
                report.setEmpIdObjList(employeeList);
            }
            
            if (report.getEmpIdObjList().size() == 0) {

                if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                        || (oEmp.getRoleObj().getRoleName().equals("admin"))
                        || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {

                    employeeList = employeeService.getAllEmployees(clientId);
                    report.setEmpIdObjList(employeeList);
                }else if(timesheetRole.equals("BOTH")){
                    employeeList = timeProService.getCurrentSubEmployeeForTimeSheet();
                    report.setEmpIdObjList(employeeList);
                }else {
                    List<EmployeesVO> empList = new LinkedList<EmployeesVO>();
                    EmployeesVO empObj = new EmployeesVO();

                    empObj = employeeService.getEmployeeById(oEmp.getEmployeeId());
                    empList.add(empObj);
                    report.setEmpIdObjList(empList);
                }
           }
            timeSheetCategoryAssignList = timesheetCategoryAssignService.timeSheetCategoryAssignReport(report);
            timeSheetProjectAssignList = timesheetCategoryAssignService.timeSheetProjectAssignReport(report);
            report.setLeaveStatus("");
            leaveList = leaveService.getLeaveHistReports(report);
            expensesList = expensesService.getExpensesHistReports(report);

            for (TimeSheetProjectAssignVO projectlist : timeSheetProjectAssignList) {
                timesheetProjectList.add(projectlist.getProjectName().getProjectName() + "("
                    + projectlist.getProjectActivity().getActivityName() + ")");

            }

            clientDetails = clientService.getAllClient();

            ArrayList<Date> fulllist = new ArrayList<Date>();
            ArrayList<Date> dates = new ArrayList<Date>();
            ArrayList<Date> dates1 = new ArrayList<Date>();
            ArrayList<Date> dates2 = new ArrayList<Date>();
            ArrayList<ArrayList> sublist = new ArrayList<ArrayList>();

            String start_date = formatter.format(report.getFromDate());
            String end_date1 = formatter.format(report.getToDate());
            String str_date = start_date;
            String end_date = end_date1;
            // ReportsVO reports = new ReportsVO();

            Date startDate = formatter.parse(str_date);
            Date endDate = formatter.parse(end_date);
            long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
            long endTime = endDate.getTime(); // create your endtime here,
                                              // possibly using Calendar or Date
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                fulllist.add(new Date(curTime));
                dates.add(new Date(curTime));
                dates1.add(new Date(curTime));
                curTime += interval;
                while (dates.size() == 7) {
                    dates = new ArrayList<Date>();
                    sublist.add(dates);
                    dates.removeAll(dates);
                }

            }
            if (dates1.size() >6) {
                for (int i = 0; i <= 6; i++) {
                    dates2.add(dates1.get(i));
                }
            } else {

                for (int i = 0; i <= (dates1.size() - 1); i++) {
                    dates2.add(dates1.get(i));
                }
            }

            sublist.add(0, dates2);

            StringBuffer str = new StringBuffer();

            List<EmployeesVO> employeelist = report.getEmpIdObjList();
//            employeelist.addAll(report.getEmpIdObjList())

            str.append("<html><head>");

            str.append("</head><body style=\"font-family:Times;font-size:7;\"><form>");

            if (clientDetails.size() > 0) {
                str.append("<div align=\"left\" style=\"font-family:Times;font-size:17;\">");
                str.append("<b>" + clientDetails.get(0).getCompanyName() + "</b>");
                str.append("</div>");
                str.append("<br>");
            }

            str.append("<div align=\"left\" style=\"font-family:Times;font-size:11;\">");
            str.append("<b>1. Timesheet Report for " + formatter.format(report.getFromDate())
                + " - " + formatter.format(report.getToDate()) + "</b>");
            str.append("</div>");
            str.append("<br>");
            str.append("<table bgcolor=black><tr><td style=\"font-family:Times;font-size:10;\"><font color=white>TimeSheet Report</font></td><td style=\"font-family:Times;font-size:10;\" align=right><font color=white>"
                + new Date() + "</font></td></tr></table>");

            str.append("<br>");
            for (EmployeesVO employees : employeelist) {
                EmployeesVO emp = employeeServive.getEmployeeById(employees.getEmployeeId());
                TimeSheetApproverVO tsApprover = timeSheetApproverService.getEmployeeTimeSheetApproverDetails(emp.getEmployeeId());

                TimesheetReportVO timesheetReportDisplay = new TimesheetReportVO();
                String approverName = "--";
                if (tsApprover != null) {
                    approverName = tsApprover.getHcmoApprovingEmpId().getEmpFirstName();
                }

                str.append("<table border=\"1\" width=\"50%\">");

                str.append("<tr><td align=\"left\"><b>Status </b></td>");
                str.append("<td>Reviewed</td></tr>");

                str.append("<tr><td align=\"left\"><b>Employee Name </b></td>");
                str.append("<td>" + emp.getEmpFirstName() + "</td></tr>");

                str.append("<tr><td align=\"left\"><b>Approver Name </b></td>");
                str.append("<td>" + approverName + "</td></tr>");

                str.append("<tr><td align=\"left\"><b>Start Date </b></td>");
                str.append("<td>" + formatter.format(report.getFromDate()) + "</td></tr>");

                str.append("<tr><td align=\"left\"><b>End Date </b></td>");
                str.append("<td>" + formatter.format(report.getToDate()) + "</td></tr>");
                str.append("</table>");

                timesheetReportDisplay.setEmployeeName(emp.getEmpFirstName());
                timesheetReportDisplay.setApproverName(approverName);
                timesheetReportDisplay.setStartDate(report.getFromDate());
                timesheetReportDisplay.setEndDate(report.getToDate());
                displayList.add(timesheetReportDisplay);

                str.append("<br>");
                if ((timeSheetProjectAssignList.size() > 0)
                    || (timeSheetCategoryAssignList.size() > 0)) {
                    str.append("<table border=\"1\">");
                    for (int j = 0; j <= (sublist.size() - 1); j++) {

                        List display = new ArrayList();
                        display = sublist.get(j);

                        str.append("<tr bgcolor=\"black\">");
                        str.append("<th align=\"center\" style=\"text-align:left; font-weight:bold; font-size:7px; padding-right:10px; background-color: #000; color: white;\"><b>Type (Project/Category)</b></th>");
                        str.append("<th align=\"center\" style=\"text-align:left; font-weight:bold; font-size:7px; padding-right:10px; background-color: #000; color: white;\"><b>Name</b></th>");

                        while (display.size() < 7) {
                            display.add("");

                        }

                        for (int k = 0; k <= (display.size() - 1); k++) {
                            try {
                                str.append("<th align=\"center\" style=\"text-align:left; font-weight:bold; font-size:7px; padding-right:10px; background-color: #000; color: white;\"><b>"
                                    + formatter.format(display.get(k)) + "</b></th>");
                            } catch (Exception e) {
                                str.append("<th align=\"center\" style=\"text-align:left; font-weight:bold; font-size:7px; padding-right:10px; background-color: #000; color: black;\"><b>&nbsp;</b></th>");
                            }
                        }
                        str.append("</tr>");

                        Iterator it = timesheetProjectList.iterator();
                        while (it.hasNext()) {
                            String projectname = it.next().toString();

                            str.append("<tr>");
                            str.append("<td align=\"center\">Project</td>");
                            str.append("<td>" + projectname + "</td>");
                            for (int k = 0; k <= (display.size() - 1); k++) {

                                str.append("<td align=\"center\">");

                                for (TimeSheetProjectAssignVO projectlist : timeSheetProjectAssignList) {

                                    String projAndActivity = projectlist.getProjectName().getProjectName()
                                        + "("
                                        + projectlist.getProjectActivity().getActivityName()
                                        + ")";
                                    if (projectlist.getEnterDate().equals(display.get(k))
                                        && projAndActivity.equals(projectname)
                                        && projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())) {

                                        str.append("" + projectlist.getEnterTime());
                                        str.append("<br><div align=\"right\">");
                                        if (projectlist.getApprove() != 0) {
                                            str.append("App");
                                        } else if (projectlist.getRejected() != 0) {
                                            str.append("Rej");
                                        } else if (projectlist.getRework() != 0) {
                                            str.append("Rew");
                                        } else {
                                            str.append("For App");
                                        }
                                        str.append("</div>");
                                    }
                                }

                                str.append("</td>");

                            }
                            str.append("</tr>");

                            // str.append("<tr>");
                            //
                            // str.append("<td colspan=\"2\" align=\"center\">Approval Status</td>");
                            //
                            // for(int k=0;k<=display.size()-1;k++) {
                            //
                            // str.append("<td align=\"center\">");
                            //
                            // for(TimeSheetProjectAssignVO
                            // projectlist:timeSheetProjectAssignList){
                            //
                            // String
                            // projAndActivity=projectlist.getProjectName().getProjectName()+"("+projectlist.getProjectActivity().getActivityName()+")";
                            // if(projectlist.getEnter_date().equals(display.get(k))
                            // && projAndActivity.equals(projectname) &&
                            // projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
                            //
                            // if(projectlist.getApprove()!=0){
                            // str.append("App");
                            // }else if(projectlist.getRejected()!=0){
                            // str.append("Rej");
                            // }else if(projectlist.getRework()!=0){
                            // str.append("Rew");
                            // }else{
                            // str.append("For App");
                            // }
                            // }
                            // }
                            //
                            //
                            // str.append("</td>");
                            //
                            // }
                            //
                            // str.append("</tr>");

                        }

                        if (timeSheetCategoryAssignList.size() > 0) {

                            for (TimeSheetCategoryAssignVO categorylist : timeSheetCategoryAssignList) {

                                if (categorylist.getTimesheetCategoryName() != null) {

                                    timesheetCategoryList.add(categorylist.getTimesheetCategoryName().getName());
                                }

                            }
                        }

                        Iterator itr = timesheetCategoryList.iterator();
                        while (itr.hasNext()) {
                            String categoryname = itr.next().toString();

                            str.append("<tr>");
                            str.append("<td align=\"center\">Category</td>");
                            str.append("<td align=\"center\">" + categoryname + "</td>");
                            for (int k = 0; k <= (display.size() - 1); k++) {

                                str.append("<td align=\"center\">");

                                for (TimeSheetCategoryAssignVO categorylist : timeSheetCategoryAssignList) {
                                    if (categorylist.getTimesheetCategoryName() != null) {

                                        if (categorylist.getEnterDate().equals(display.get(k))
                                            && categorylist.getTimesheetCategoryName().getName().equals(categoryname)
                                            && categorylist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())) {
                                            str.append("" + categorylist.getEnterTime());
                                            str.append("<br><div align=\"right\">");
                                            if (categorylist.getApprove() != 0) {
                                                str.append("App");
                                            } else if (categorylist.getRejected() != 0) {
                                                str.append("Rej");
                                            } else if (categorylist.getRework() != 0) {
                                                str.append("Rew");
                                            } else {
                                                str.append("For App");
                                            }
                                            str.append("<br></div>");
                                        }
                                    }
                                }

                                str.append("</td>");

                            }
                            str.append("</tr>");

                        }

                    }
                    str.append("</table>");

                } else {
                    str.append("<br>");
                    str.append("<table bgcolor=\"black\"><tr><td><font color=\"white\"><b>No Records exist.</b></font></td></tr></table>");
                }

                str.append("<br>");

                if ((timeSheetProjectAssignList.size() > 0)
                    || (timeSheetCategoryAssignList.size() > 0)) {

                    str.append("<table border=\"1\">");
                    str.append("<tr bgcolor=\"black\">");
                    str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Project Details</font></th>");
                    str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Total</font></th>");
                    str.append("</tr>");
               		str.append("</tr>");
                    Iterator it = timeSheetProjectAssignList.iterator();
                    LinkedList<String> lList =  new LinkedList<String>();
                    while (it.hasNext()) {
                        TimeSheetProjectAssignVO proName = (TimeSheetProjectAssignVO) it.next();
                        String projectname = proName.getProjectName().getProjectName() + "("
                            + proName.getProjectActivity().getActivityName() + ")";
                            if(lList.contains(projectname)){
                            }else{
                            String ProjAndActivity = proName.getProjectName().getProjectName();
                            str.append("<tr>");
                            str.append("<td align=\"center\">" + projectname + "</td>");
                            str.append("<td align=\"center\">");

                            double projTotal = 0.0;
                            for (TimeSheetProjectAssignVO projectlist : timeSheetProjectAssignList) {
                                String projAndActivity = projectlist.getProjectName().getProjectName()
                                    + "(" + projectlist.getProjectActivity().getActivityName() + ")";
                                if (projAndActivity.equals(projectname)
                                    && projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())) {
                                    projTotal = projTotal + projectlist.getEnterTime();
                                }
                            }
                            str.append("" + projTotal);

                            str.append("</td>");

                            str.append("</tr>");
                            lList.add(projectname);
                            }
                        }


                    Iterator itr = timesheetCategoryList.iterator();

                    while (itr.hasNext()) {
                        String categoryname = itr.next().toString();

                        str.append("<tr>");

                        str.append("<td align=\"center\">" + categoryname + "</td>");
                        str.append("<td align=\"center\">");

                        double catTotal = 0.0;
                        for (TimeSheetCategoryAssignVO categorylist : timeSheetCategoryAssignList) {

                            if (categorylist.getTimesheetCategoryName() != null) {
                                if (categorylist.getTimesheetCategoryName().getName().equals(categoryname)
                                    && categorylist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())) {

                                    catTotal = catTotal + categorylist.getEnterTime();
                                }
                            }
                        }
                        str.append("" + catTotal);

                        str.append("</td>");

                        str.append("</tr>");

                    }

                    str.append("</table>");

                } else {
                    str.append("<br>");
                    str.append("<table bgcolor=\"black\"><tr><td><font color=\"white\"><b>No Records exist.</b></font></td></tr></table>");
                }

                /* Leave details */

                str.append("<br>");
                str.append("<table border=\"1\">");
                str.append("<tr bgcolor=\"black\">");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Leave Type</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Date Applied</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Status</font></th>");
                str.append("</tr>");

                for (LeaveHistoryVO leaveHistoryList : leaveList) {

                    if (leaveHistoryList.getEmpIdObj().getEmployeeId().equals(emp.getEmployeeId())) {

                        str.append("<tr><td align=\"center\">"
                            + leaveHistoryList.getLeaveTypeIdObj().getLeaveTypeName()
                            + "</td><td align=\"center\">" + leaveHistoryList.getLeaveDate()
                            + "</td><td align=\"center\">" + leaveHistoryList.getLeaveStatus()
                            + "</td></tr>");

                    }
                }

                str.append("<tr></tr></table>");

                /* Expense details */

                str.append("<br>");
                str.append("<table border=\"1\">");
                str.append("<tr bgcolor=\"black\">");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Expense Type</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Date Applied</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Amount</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Status</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Attachment</font></th>");
                str.append("<th align=\"center\" bgcolor=\"black\"><font color=white>Remarks</font></th>");
                str.append("</tr>");

                for (ExpenseStatusTrackerVO expenseHistoryList : expensesList) {

                    if (expenseHistoryList.getHcmoExpensesId().getHcmoEmployeeId().getEmployeeId().equals(emp.getEmployeeId())) {

                        String attachment = "--";
                        if (expenseHistoryList.getHcmoExpensesId().getExpAttachFileName() != null) {
                            attachment = expenseHistoryList.getHcmoExpensesId().getExpAttachFileName();
                        }
                        ExpensesTypeVO expType = new ExpensesTypeVO();
                        expType = expTypeService.getExpensesType(expenseHistoryList.getHcmoExpensesId().getHcmoExpensesId());
                        String expenseName = "";
                        if (expType != null) {
                            expenseName = expType.getName();
                        }

                        str.append("<tr><td align=\"center\">" + expenseName
                            + "</td><td align=\"center\">" + expenseHistoryList.getCreated()
                            + "</td><td align=\"center\">"
                            + expenseHistoryList.getHcmoExpensesId().getTotalAmount()
                            + "</td><td align=\"center\">" + expenseHistoryList.getApprovalStatus()
                            + "</td><td align=\"center\">" + attachment
                            + "</td><td align=\"center\"></td></tr>");

                    }
                }

                str.append("<tr></tr></table>");
                str.append("<br><br>");

            }

            str.append("<table><tr>");
            str.append("<th><b>Approval Status Key </b></th>");
            str.append("</tr>");
            str.append("<tr><td>App      - Approved</td></tr>");
            str.append("<tr><td>For App  - For Approval</td></tr>");
            str.append("<tr><td>Rew      - Rework</td></tr>");
            str.append("<tr><td>Rej      - Rejected</td></tr>");
            str.append("</table>");

            str.append("</form></body></html>");

            htmlToPdfFile(str.toString(), getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath());

            inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + "/"
                + "TimeSheetReport/Time Sheet Report.pdf"));

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public static void htmlToPdfFile(String str, String path) throws Exception {
        try {
            BaseFont bf_symbol = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);

            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, new FileOutputStream(path
                + "/TimeSheetReport/Time Sheet Report.pdf"));

            PdfPTable footer1 = new PdfPTable(1);
            footer1.getTotalHeight();
            Font font = new Font(bf_symbol);
            font.setColor(Color.WHITE);
            font.setSize(8);
            HeaderFooter footer = new HeaderFooter(new Phrase("Roosterhr.com All Rights Reserved  ", font), true);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Element.ALIGN_LEFT);
            footer.setBackgroundColor(Color.BLACK);

            document.setFooter(footer);
            document.open();

            HTMLWorker htmlWorker = new HTMLWorker(document);

            htmlWorker.parse(new StringReader(str));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public List<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
        return timeSheetCategoryAssignList;
    }

    public void setTimeSheetCategoryAssignList(List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
        this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
    }

    public List<TimeSheetProjectAssignVO> getTimeSheetProjectAssignList() {
        return timeSheetProjectAssignList;
    }

    public void setTimeSheetProjectAssignList(List<TimeSheetProjectAssignVO> timeSheetProjectAssignList) {
        this.timeSheetProjectAssignList = timeSheetProjectAssignList;
    }

    public TimeSheetCategoryAssignVO getTimeSheetCategoryAssign() {
        return timeSheetCategoryAssign;
    }

    public void setTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign) {
        this.timeSheetCategoryAssign = timeSheetCategoryAssign;
    }

    public ArrayList getTimeSheetReportList() {
        return timeSheetReportList;
    }

    public void setTimeSheetReportList(ArrayList timeSheetReportList) {
        this.timeSheetReportList = timeSheetReportList;
    }

    public TimeSheetCategoryAssignService getTimesheetCategoryAssignService() {
        return timesheetCategoryAssignService;
    }

    public void setTimesheetCategoryAssignService(TimeSheetCategoryAssignService timesheetCategoryAssignService) {
        this.timesheetCategoryAssignService = timesheetCategoryAssignService;
    }

    public TimeSheetCategoryAssignService getTimeSheetCategoryAssignService() {
        return timeSheetCategoryAssignService;
    }

    public void setTimeSheetCategoryAssignService(TimeSheetCategoryAssignService timeSheetCategoryAssignService) {
        this.timeSheetCategoryAssignService = timeSheetCategoryAssignService;
    }

    public ArrayList getDisplayList() {
        return displayList;
    }

    public void setDisplayList(ArrayList displayList) {
        this.displayList = displayList;
    }

    public Map getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(Map reportParameters) {
        this.reportParameters = reportParameters;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

	public List<EmployeesVO> getSubEmpIdListObj() {
		return subEmpIdListObj;
	}

	public void setSubEmpIdListObj(List<EmployeesVO> subEmpIdListObj) {
		this.subEmpIdListObj = subEmpIdListObj;
	}
}
