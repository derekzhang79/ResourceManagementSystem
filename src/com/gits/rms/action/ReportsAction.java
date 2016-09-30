
package com.gits.rms.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.TimeSheetProjectAssignHibernateDao;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpenseStatusTrackerDaoService;
import com.gits.rms.service.ExpenseStatusTrackerService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.DirectDebitVO;
import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportTimeSheetDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TargetsVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimesheetAchivedTargetReportDisplayVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class ReportsAction extends ActionSupport {
    private static final long serialVersionUID = -2910613743999510046L;
    private ReportsVO report = new ReportsVO();
    private List<ReportsVO> reportsList;
    private List<EmployeesVO> subEmpIdListObj;

    private List<MessageVO> msgBroadcastList;
    private MessageService msgBroadcastService = new MessageDaoService();

    private List<EmployeesVO> employeeList;
    private EmployeesService employeeService = new EmployeesDaoService();

    private List<ProjectVO> projectList;
    private ProjectService projectService = new ProjectDaoService();

    private List<LeaveHistoryVO> leaveList;
    private LeaveHistoryService leaveService = new LeaveHistoryDaoService();

    private List<ExpenseStatusTrackerVO> expensesList;
    private String currencyTypeValue;
    private String curTypeValueForTotalAmtField;
    private String curTypeValueForReimbAmtField;
    private String totalAmtFieldStringValue;
    private String reimbAmtFieldStringValue;
    private String reimbAmtFieldStringEmptyValue;
    private ExpenseStatusTrackerService expensesService = new ExpenseStatusTrackerDaoService();
    private CurrencyService currencyService = new CurrencyDaoService();
    private TimesheetProjectDaoService timesheetProjService = new TimesheetProjectDaoService();
    
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;
    Set timesheetProjectList = new HashSet();
    Set timesheetCategoryList = new HashSet();
    private String startDate;
    private String endDate;

    private TimeSheetCategoryAssignVO timeSheetCategoryAssign = new TimeSheetCategoryAssignVO();
    private ArrayList timeSheetReportList = new ArrayList();
    private TimeSheetCategoryAssignService timesheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();
    private List<ReportTimeEnteredDislayVO> enteredTimeDisplayList;
    private ReportTimeEnteredDislayVO enteredTimeDisplayObj;
    private TargetsService targetService = new TargetsDaoService();
    private List<TimesheetAchivedTargetReportDisplayVO> targetAndGoalList;

    @SkipValidation
    public String getBroadcastReportPreview() {
        msgBroadcastList = msgBroadcastService.getBroadcastMessageReports(report);
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeReportsPreview() {
        employeeList = employeeService.getEmployeeReports(report);
        return SUCCESS;
    }

    @SkipValidation
    public String getProjectReportsPreview() {
        projectList = projectService.getProjectsReports(report);
        return SUCCESS;
    }

    @SkipValidation
    public String getLeaveHistoryReportsPreview() {
        leaveList = leaveService.getLeaveHistReports(report);
        return SUCCESS;
    }

    @SkipValidation
    public String getExpensesStatusTrackerReportsPreview() {
        ActionContext.getContext().getSession();
        expensesList = expensesService.getExpensesHistReports(report);

        // Add currency type value with Total Amount values like ($) 6000.00
        for (int i = 0; i < expensesList.size(); i++) {
            totalAmtFieldStringValue = expensesList.get(i).getHcmoExpensesId().getTotalAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForTotalAmtField = currencyTypeValue + "   " + totalAmtFieldStringValue;
                expensesList.get(i).getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForTotalAmtField = currencyTypeValue + "   " + totalAmtFieldStringValue;
                expensesList.get(i).getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
            }
        }

        // Add currency type value with Reimbursement Amount values like ($)
        // 6000.00
        for (int i = 0; i < expensesList.size(); i++) {
            ExpenseStatusTrackerVO expStatus = expensesList.get(i);

            if (expStatus.getApprovalStatus().equals("ForApproval")) {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
            if ((expStatus.getApprovalStatus().equals("Approved"))) {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
            if (expStatus.getApprovalStatus().equals("Reimbursed")) {
                reimbAmtFieldStringValue = expStatus.getHcmoExpensesId().getReimbursementAmount().toString();
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue + "0";
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue + "0";
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            } else {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
        }

        return SUCCESS;
    }

    @SkipValidation
    public String getBirthdayReportsPreview() {
        employeeList = employeeService.getEmployeeBirthdayReports(report);
        return SUCCESS;
    }

    @SkipValidation
    public String getTimeSheetReportsPreview() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Map mSession = ActionContext.getContext().getSession();
//        CLIENT ID ADDED BY BALA
        Integer clientId = (Integer) mSession.get("CLIENT_INFO");
        String timesheetRole = String.valueOf(mSession.get("TIMESHEET_APPROVER"));
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        
        getReportTimeSheetSubEmpList();
        if (report.getFromDate() == null) {
            addActionError("Please select the From Date");
            return INPUT;
        }
        
        if (report.getToDate() == null) {
            addActionError("Please select the To Date");
            return INPUT;
        }
        
        if (report.getToDate().before(report.getFromDate())) {
            addActionError("The To Date must be after the From date");
            return INPUT;
        }
        
        if (report.getEmpIdObjList().size() == 0) {

        	 if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                     || (oEmp.getRoleObj().getRoleName().equals("admin"))
                     || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
//        		 CLIENT ID ADDED BY BALA
        		 employeeList = employeeService.getAllEmployees(clientId);
                 report.setEmpIdObjList(employeeList);
             }else if(timesheetRole.equals("BOTH")){
            	 employeeList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
             }else {
            	 List<EmployeesVO> empList = new LinkedList<EmployeesVO>();
            	 EmployeesVO empObj = new EmployeesVO();

            	 empObj = employeeService.getEmployeeById(oEmp.getEmployeeId());
            	 empList.add(empObj);
                 report.setEmpIdObjList(empList);
             }
        }

        TimeSheetProjectAssignVO timeSheetProjectAssign = new TimeSheetProjectAssignVO();
        timeSheetCategoryAssignList = timesheetCategoryAssignService.timeSheetCategoryAssignReport(report);
        timeSheetProjectAssignList = timesheetCategoryAssignService.timeSheetProjectAssignReport(report);

        startDate = formatter.format(report.getFromDate());
        endDate = formatter.format(report.getToDate());

        for (TimeSheetProjectAssignVO projectlist : timeSheetProjectAssignList) {
            timesheetProjectList.add(projectlist.getProjectName().getProjectName() + "("
                + projectlist.getProjectActivity().getActivityName() + ")");

        }
        if (timeSheetCategoryAssignList.size() > 0) {
            for (TimeSheetCategoryAssignVO categorylist : timeSheetCategoryAssignList) {

                if (categorylist.getTimesheetCategoryName() != null) {
                    timesheetCategoryList.add(categorylist.getTimesheetCategoryName().getName());
                }

            }
        }

        report.setLeaveStatus("");
        leaveList = leaveService.getLeaveHistReports(report);
        getExpenseDetails();

        if (!(timeSheetCategoryAssignList.isEmpty())) {
            for (Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator(); it.hasNext();) {
                ReportTimeSheetDislayVO reportTimesheetDisplay = new ReportTimeSheetDislayVO();
                timeSheetCategoryAssign = it.next();

                if (timeSheetCategoryAssign.getTimesheetCategoryName() == null) {
                    reportTimesheetDisplay.setCategoryName("");
                } else {
                    reportTimesheetDisplay.setCategoryName(timeSheetCategoryAssign.getTimesheetCategoryName().getName());
                }

                reportTimesheetDisplay.setEnteredDate(timeSheetCategoryAssign.getEnterDate());
                reportTimesheetDisplay.setEnteredTime(timeSheetCategoryAssign.getEnterTime());
                reportTimesheetDisplay.setEmpName(timeSheetCategoryAssign.getEmployeeName().getEmpFullName());

                if (timeSheetCategoryAssign.getApprove() == 0) {
                    reportTimesheetDisplay.setApproved("");
                } else {
                    reportTimesheetDisplay.setApproved("Approved");
                }

                if (timeSheetCategoryAssign.getRejected() == 0) {
                    reportTimesheetDisplay.setRejected("");
                } else {
                    reportTimesheetDisplay.setRejected("Rejected");
                }

                if (timeSheetCategoryAssign.getRework() == 0) {
                    reportTimesheetDisplay.setRework("");
                } else {
                    reportTimesheetDisplay.setRework("Rework");
                }

                timeSheetReportList.add(reportTimesheetDisplay);
            }
        }

        if (!(timeSheetProjectAssignList.isEmpty())) {
            for (Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator(); it.hasNext();) {
                ReportTimeSheetDislayVO reportTimesheetDisplay = new ReportTimeSheetDislayVO();
                timeSheetProjectAssign = it.next();
                reportTimesheetDisplay.setProjectName(timeSheetProjectAssign.getProjectName().getProjectName());
                reportTimesheetDisplay.setEnteredDate(timeSheetProjectAssign.getEnterDate());
                reportTimesheetDisplay.setEnteredTime(timeSheetProjectAssign.getEnterTime());
                reportTimesheetDisplay.setEmpName(timeSheetProjectAssign.getEmployeeName().getEmpFullName());

                if (timeSheetProjectAssign.getApprove() == 0) {
                    reportTimesheetDisplay.setApproved("");
                } else {
                    reportTimesheetDisplay.setApproved("Approved");
                }

                if (timeSheetProjectAssign.getRejected() == 0) {
                    reportTimesheetDisplay.setRejected("");
                } else {
                    reportTimesheetDisplay.setRejected("Rejected");
                }

                if (timeSheetProjectAssign.getRework() == 0) {
                    reportTimesheetDisplay.setRework("");
                } else {
                    reportTimesheetDisplay.setRework("Rework");
                }

                timeSheetReportList.add(reportTimesheetDisplay);
            }
        }

        return SUCCESS;
    }
    
    public String getTimeEstimationReportsPreview() {
    	getReportTimeSheetSubEmpList();
    	
    	if (report.getFromDate() == null) {
            addActionError(getText("errors.report.timeEstimation.fromDate"));
            return INPUT;
        }
    	
        if (report.getToDate() == null) {
        	addActionError(getText("errors.report.timeEstimation.toDate"));
            return INPUT;
        }
        
        if (report.getToDate().before(report.getFromDate())) {
            addActionError("To Date must be after the From date");
            return INPUT;
        }
        
        enteredTimeDisplayList = timesheetProjService.getTimeEstimationReport(report);
        
        for (Iterator<ReportTimeEnteredDislayVO> it = enteredTimeDisplayList.iterator(); it.hasNext();) {
        	enteredTimeDisplayObj = (ReportTimeEnteredDislayVO)it.next();
        	
        	if( enteredTimeDisplayObj.getEstimatedHours() !=null ){
        		if(enteredTimeDisplayObj.getTotalEnteredTime() !=null){
        			Double estimatedHour = Double.valueOf(enteredTimeDisplayObj.getEstimatedHours());
        			Double enteredHour = enteredTimeDisplayObj.getTotalEnteredTime();
        			
        			if(estimatedHour > enteredHour){
        				enteredTimeDisplayObj.setOverTimeHours(0d);
        				enteredTimeDisplayObj.setTimeRemaining(estimatedHour-enteredHour);
        				enteredTimeDisplayObj.setStatus("Normal");
        			}else if(estimatedHour < enteredHour){
        				enteredTimeDisplayObj.setOverTimeHours(enteredHour-estimatedHour);
        				enteredTimeDisplayObj.setTimeRemaining(0d);
        				enteredTimeDisplayObj.setStatus("Over time");
        			}
        			
        		}
        		
        	}
        }
        
        return SUCCESS;
    }
    
    public String getTargetAndGoalReportsPreview() {
    	
    	getReportTimeSheetSubEmpList();
    	if (report.getFromDate() == null) {
            addActionError(getText("errors.report.timeEstimation.fromDate"));
            return INPUT;
        }
    	
        if (report.getToDate() == null) {
        	addActionError(getText("errors.report.timeEstimation.toDate"));
            return INPUT;
        }
        
        if (report.getToDate().before(report.getFromDate())) {
            addActionError("To Date must be after the From date");
            return INPUT;
        }
        
        targetAndGoalList = targetService.getTargetAndGoalReport(report);
        
        return SUCCESS;
    }
    
    public String getReportLeaveSubEmpList() {
        getSubEmployeeList();
        return SUCCESS;
    }

    private void getSubEmployeeList() {
        subEmpIdListObj = employeeService.getCurrentSubEmployee();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        subEmpIdListObj.add(empSelf);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("leaveSubEmpListReport", subEmpIdListObj);
    }

    public String getReportExpensesSubEmpList() {
        getExpensesSubEmployeeList();
        return SUCCESS;
    }

    private void getExpensesSubEmployeeList() {
        subEmpIdListObj = employeeService.getCurrentExpensesSubEmployee();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        subEmpIdListObj.add(empSelf);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("expensesSubEmpListReport", subEmpIdListObj);
    }

    // Used in Both Employee and Birthday tab to display the sub-emp list
    public String getReportEmpReportingToSubEmpList() {
        getReportingToSubEmpList();
        return SUCCESS;
    }

    private void getReportingToSubEmpList() {
        subEmpIdListObj = employeeService.getReportingToSubEmpList();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        subEmpIdListObj.add(empSelf);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("subEmpReportingToList", subEmpIdListObj);
    }

    public String getReportTimeSheetSubEmpList() {
        getSubEmployeeTimeSheetList();
        return SUCCESS;
    }

    private void getSubEmployeeTimeSheetList() {
        subEmpIdListObj = employeeService.getCurrentTimeSheetSubEmployee();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        subEmpIdListObj.add(empSelf);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("timeSheetSubEmpListReport", subEmpIdListObj);
    }

    public void getExpenseDetails() {
        ActionContext.getContext().getSession();
        expensesList = expensesService.getExpensesHistReports(report);

        // Add currency type value with Total Amount values like ($) 6000.00
        for (int i = 0; i < expensesList.size(); i++) {
            totalAmtFieldStringValue = expensesList.get(i).getHcmoExpensesId().getTotalAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForTotalAmtField = currencyTypeValue + "   " + totalAmtFieldStringValue;
                expensesList.get(i).getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForTotalAmtField = currencyTypeValue + "   " + totalAmtFieldStringValue;
                expensesList.get(i).getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
            }
        }

        // Add currency type value with Reimbursement Amount values like ($)
        // 6000.00
        for (int i = 0; i < expensesList.size(); i++) {
            ExpenseStatusTrackerVO expStatus = expensesList.get(i);

            if (expStatus.getApprovalStatus().equals("ForApproval")) {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
            if ((expStatus.getApprovalStatus().equals("Approved"))) {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
            if (expStatus.getApprovalStatus().equals("Reimbursed")) {
                reimbAmtFieldStringValue = expStatus.getHcmoExpensesId().getReimbursementAmount().toString();
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue + "0";
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue + "0";
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            } else {
                reimbAmtFieldStringValue = "0.00";
                currencyTypeValue = currencyService.getCurrencyType();
                if (currencyTypeValue == null) {
                    currencyTypeValue = "";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                } else {
                    currencyTypeValue = "(" + currencyTypeValue + ")";
                    curTypeValueForReimbAmtField = currencyTypeValue + "   "
                        + reimbAmtFieldStringValue;
                    expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                }
            }
        }

    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public List<ReportsVO> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<ReportsVO> reportsList) {
        this.reportsList = reportsList;
    }

    public List<MessageVO> getMsgBroadcastList() {
        return msgBroadcastList;
    }

    public void setMsgBroadcastList(List<MessageVO> msgBroadcastList) {
        this.msgBroadcastList = msgBroadcastList;
    }

    public MessageService getMsgBroadcastService() {
        return msgBroadcastService;
    }

    public void setMsgBroadcastService(MessageService msgBroadcastService) {
        this.msgBroadcastService = msgBroadcastService;
    }

    public List<EmployeesVO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeesVO> employeeList) {
        this.employeeList = employeeList;
    }

    public EmployeesService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeesService employeeService) {
        this.employeeService = employeeService;
    }

    public List<ProjectVO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectVO> projectList) {
        this.projectList = projectList;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<LeaveHistoryVO> getLeaveList() {
        return leaveList;
    }

    public LeaveHistoryService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveHistoryService leaveService) {
        this.leaveService = leaveService;
    }

    public List<ExpenseStatusTrackerVO> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<ExpenseStatusTrackerVO> expensesList) {
        this.expensesList = expensesList;
    }

    public ExpenseStatusTrackerService getExpensesService() {
        return expensesService;
    }

    public void setExpensesService(ExpenseStatusTrackerService expensesService) {
        this.expensesService = expensesService;
    }

    public void setLeaveList(List<LeaveHistoryVO> leaveList) {
        this.leaveList = leaveList;
    }

    public List<EmployeesVO> getSubEmpIdListObj() {
        return subEmpIdListObj;
    }

    public void setSubEmpIdListObj(List<EmployeesVO> subEmpIdListObj) {
        this.subEmpIdListObj = subEmpIdListObj;
    }

    public List<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
        return timeSheetCategoryAssignList;
    }

    public void setTimeSheetCategoryAssignList(List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
        this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
    }

    public TimeSheetCategoryAssignService getTimesheetCategoryAssignService() {
        return timesheetCategoryAssignService;
    }

    public void setTimesheetCategoryAssignService(TimeSheetCategoryAssignService timesheetCategoryAssignService) {
        this.timesheetCategoryAssignService = timesheetCategoryAssignService;
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

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurTypeValueForTotalAmtField(String curTypeValueForTotalAmtField) {
        this.curTypeValueForTotalAmtField = curTypeValueForTotalAmtField;
    }

    public String getCurTypeValueForTotalAmtField() {
        return curTypeValueForTotalAmtField;
    }

    public void setCurTypeValueForReimbAmtField(String curTypeValueForReimbAmtField) {
        this.curTypeValueForReimbAmtField = curTypeValueForReimbAmtField;
    }

    public String getCurTypeValueForReimbAmtField() {
        return curTypeValueForReimbAmtField;
    }

    public void setTotalAmtFieldStringValue(String totalAmtFieldStringValue) {
        this.totalAmtFieldStringValue = totalAmtFieldStringValue;
    }

    public String getTotalAmtFieldStringValue() {
        return totalAmtFieldStringValue;
    }

    public void setReimbAmtFieldStringValue(String reimbAmtFieldStringValue) {
        this.reimbAmtFieldStringValue = reimbAmtFieldStringValue;
    }

    public String getReimbAmtFieldStringValue() {
        return reimbAmtFieldStringValue;
    }

    public void setReimbAmtFieldStringEmptyValue(String reimbAmtFieldStringEmptyValue) {
        this.reimbAmtFieldStringEmptyValue = reimbAmtFieldStringEmptyValue;
    }

    public String getReimbAmtFieldStringEmptyValue() {
        return reimbAmtFieldStringEmptyValue;
    }

    public Set getTimesheetProjectList() {
        return timesheetProjectList;
    }

    public void setTimesheetProjectList(Set timesheetProjectList) {
        this.timesheetProjectList = timesheetProjectList;
    }

    public Set getTimesheetCategoryList() {
        return timesheetCategoryList;
    }

    public void setTimesheetCategoryList(Set timesheetCategoryList) {
        this.timesheetCategoryList = timesheetCategoryList;
    }

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public List<ReportTimeEnteredDislayVO> getEnteredTimeDisplayList() {
		return enteredTimeDisplayList;
	}

	public void setEnteredTimeDisplayList(
			List<ReportTimeEnteredDislayVO> enteredTimeDisplayList) {
		this.enteredTimeDisplayList = enteredTimeDisplayList;
	}

	public List<TimesheetAchivedTargetReportDisplayVO> getTargetAndGoalList() {
		return targetAndGoalList;
	}

	public void setTargetAndGoalList(
			List<TimesheetAchivedTargetReportDisplayVO> targetAndGoalList) {
		this.targetAndGoalList = targetAndGoalList;
	}
}
