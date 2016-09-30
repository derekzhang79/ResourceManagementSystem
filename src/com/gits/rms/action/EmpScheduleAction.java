
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.TimeSheetApproverVO;

public class EmpScheduleAction extends ActionSupport {
	
    private static final long serialVersionUID = -3812684747226899526L;
	List scheduleTypeList = new ArrayList();
	private String startDate;
    private String endDate;
    private int employeeId;
    private String scheduleType;
    private String activityName;
    private String projectName;
    private String employeeName;
    private String description;
    private String clientName;

    List projectList = new ArrayList();
   
    List projectActivityList = new ArrayList();

   

	/* for timsheet details */
    private Double enteredVal;
    private String timesheetStatus;
    private String categoryName;

    /* Leave details */
    private String leaveStart;
    private String leaveStatus;
    private int noOfDays;
   
    private int hours;
    private int minutes;
    private String approveNotes;
    private String dateMonthValue;

    public SimpleDateFormat dfDateField = new SimpleDateFormat("M/d/yy");
    public SimpleDateFormat monthName = new SimpleDateFormat("MMMM"); // display
                                                                      // month
                                                                      // for
                                                                      // title
                                                                      // and
                                                                      // next/last
                                                                      // month
                                                                      // navigation
    public SimpleDateFormat year = new SimpleDateFormat("yyyy"); // display year
    public SimpleDateFormat month = new SimpleDateFormat("M");
    public SimpleDateFormat monthformat = new SimpleDateFormat("MM");
    public SimpleDateFormat day = new SimpleDateFormat("d");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/d/yy");
    public SimpleDateFormat dfMySQLDate = new SimpleDateFormat("yyyy-MM-dd"); // matches
                                                                              // format
                                                                              // used
                                                                              // by
                                                                              // MySQL
                                                                              // database
    public SimpleDateFormat dfDayOfMonth = new SimpleDateFormat("d");
    public SimpleDateFormat sdfNewEvent = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public SimpleDateFormat sdfNewEventFormat = new SimpleDateFormat("MM/dd/yyyy");
    public SimpleDateFormat dateformat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    Calendar thisMonth = Calendar.getInstance();
    private String yearList = "";
   
    private String monthValue = "";
    private String monthInput = "";
    private String errText;

    private ProjectAssignEmpService projectAssignEmpService = new ProjectAssignEmpDaoService();
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private LeaveHistoryService leaveHistoryService = new LeaveHistoryDaoService();
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private ProjectService proService = new ProjectDaoService();
    private TimeSheetApproverService timeSheetAppproverService = new TimeSheetApproverDaoService();
    List searchResult = new ArrayList();
    List emptimesheetList = new ArrayList();
    List empCategorytimesheetList = new ArrayList();
    List empleavedetailsList = new ArrayList();
    List<EmployeesVO> subEmpList = new ArrayList<EmployeesVO>();
    List emptylist = new ArrayList();

    @SkipValidation
    public String empScheduleForm() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

      
        proService.isProjectOwner(oEmp.getEmployeeId());

        
        scheduleTypeList.add("Leave");
        scheduleTypeList.add("Timesheet");
        scheduleTypeList.add("Project Details");

        return SUCCESS;
    }

    public String empSchedule() {
        Map session = ActionContext.getContext().getSession();
       
        session.get("EMPLOYEE_OBJECT");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        if (session.get("ROLE").equals("ADMIN") || session.get("ROLE").equals("Admin")
            || session.get("LEAVE_APPROVER").equals("BOTH")
            || session.get("TIMESHEET_APPROVER").equals("BOTH")) {

            if (employeeId == 0) {
                errText = getText("Please select the Employee");
                return ERROR;

            }

        }
       
        if ((scheduleType == null) || scheduleType.equals("")) {
            errText = getText("Please select the Schedule Type");
            return ERROR;

        }
     
        if ((startDate == null) || startDate.equals("")) {
            errText = getText("Please select the Start Date");
            return ERROR;

        }
       
        if ((endDate == null) || endDate.equals("")) {
            errText = getText("Please select the End Date");
            return ERROR;

        }

        try {
            Date start = dateFormat.parse(startDate.toString());
            Date end = dateFormat.parse(endDate.toString());

            if (end.before(start)) {
                errText = getText("The Start Date is Invalid");
                return ERROR;
            }

        } catch (Exception e) {

        }

        if (scheduleType.equals("Project Details")) {

            return "Project";

        }

        if (scheduleType.equals("Timesheet")) {

            return "Timesheet";

        }

        if (scheduleType.equals("Leave")) {

            return "Leave";

        }

        return SUCCESS;
    }

    public String getSchedSubEmployees() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        if (scheduleType.equals("Leave")) {
            List<LeaveApproverVO> subEmplist = leaveAppproverService.getCurrentLeaveApprover(oEmp.getEmployeeId());
            if (subEmplist.size() > 0) {
               
                for (int i = 0; i <= (subEmplist.size() - 1); i++) {
                    EmployeesVO empvo = subEmplist.get(i).getHcmoEmployeeId();
                    subEmpList.add(empvo);
                }

            } else {
                subEmpList.add(oEmp);
            }

        }

        if (scheduleType.equals("Timesheet") || scheduleType.equals("Project Details")) {
            List<TimeSheetApproverVO> tsSubEmpList = timeSheetAppproverService.getAllTimeSheeetSubEmployee(oEmp.getEmployeeId());
            if (tsSubEmpList.size() > 0) {
               
                for (int i = 0; i <= (tsSubEmpList.size() - 1); i++) {
                    EmployeesVO empvo = tsSubEmpList.get(i).getHcmoEmployeeId();
                    subEmpList.add(empvo);
                }

            } else {
                subEmpList.add(oEmp);
            }
        }

        return SUCCESS;
    }

    public String getProjectDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        try {

            if (employeeId == 0) {
                employeeId = oEmp.getEmployeeId();
            }

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date startdate = dateFormat.parse(startDate);
            Date enddate = dateFormat.parse(endDate);

            // Date today = new Date();
            int date = Integer.parseInt(year.format(startdate));
            int month = Integer.parseInt(monthformat.format(startdate));
            yearList = "" + date;
           
            monthValue = "" + month;

            searchResult = projectAssignEmpService.empScheduleProjSearchResult(employeeId, startdate, enddate);
            empleavedetailsList = leaveHistoryService.getLeaveHistoryDetails(employeeId);

        } catch (Exception e) {

        }

        return SUCCESS;
    }

    public String getTimesheetDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        try {
            if (employeeId == 0) {
                employeeId = oEmp.getEmployeeId();
            }
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date startdate = dateFormat.parse(startDate);
            Date enddate=dateFormat.parse(endDate);

            int date = Integer.parseInt(year.format(startdate));
            int month = Integer.parseInt(monthformat.format(startdate));
            yearList=""+date;
            monthValue=""+month;

            emptimesheetList = timeSheetProjectAssignService.getEmpTimesheetDetails(employeeId);
            empCategorytimesheetList = timeSheetCategoryAssignService.timeSheetCategoryEmpSearchResult(employeeId);
        } catch (Exception e) {

        }
        return SUCCESS;

    }

    @SkipValidation
    public String getLeaveScheduleDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        try {
            if (employeeId == 0) {
                employeeId = oEmp.getEmployeeId();
            }
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date startdate = dateFormat.parse(startDate);
            dateFormat.parse(endDate);

            int date = Integer.parseInt(year.format(startdate));
            int month = Integer.parseInt(monthformat.format(startdate));
            yearList = "" + date;
            monthValue = "" + month;

            empleavedetailsList = leaveHistoryService.getLeaveHistoryDetails(employeeId);
        } catch (Exception e) {

        }

        return SUCCESS;

    }

    @SkipValidation
    public String displayProjectDetails() {

        try {

            
            new SimpleDateFormat("MM/dd/yyyy");

        } catch (Exception e) {

        }

        return SUCCESS;
    }

    @SkipValidation
    public String displayTimesheetDetails() {

        try {

           
            new SimpleDateFormat("MM/dd/yyyy");

        } catch (Exception e) {

        }

        return SUCCESS;
    }

    @SkipValidation
    public String displayLeaveDetails() {

        try {

           
            new SimpleDateFormat("MM/dd/yyyy");
        } catch (Exception e) {

        }

        return SUCCESS;
    }

    @SkipValidation
    public String displayMoreProjects() {

        try {

        } catch (Exception e) {

        }

        return SUCCESS;
    }

    public String incrementYear() {

        try {
            Map session = ActionContext.getContext().getSession();
            ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            getAllDetails();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateMonth = (Date) formatter.parse(dateMonthValue);
            startDate = session.get("startDate").toString();
            endDate = session.get("endDate").toString();

            Calendar now = Calendar.getInstance();
            now.setTime(dateMonth);
            now.add(Calendar.YEAR, +1);
            yearList = "" + now.get(Calendar.YEAR);
           
            monthValue = "" + now.get(Calendar.MONTH);
        } catch (Exception e) {

        }
        return SUCCESS;
    }

    public String decrementYear() {

        try {
            Map session = ActionContext.getContext().getSession();
           
            ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            getAllDetails();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             Date dateMonth = (Date) formatter.parse(dateMonthValue);
            startDate = session.get("startDate").toString();
            endDate = session.get("endDate").toString();

            Calendar now = Calendar.getInstance();
            now.setTime(dateMonth);
            now.add(Calendar.YEAR, -1);

            yearList = "" + now.get(Calendar.YEAR);
            monthValue = "" + now.get(Calendar.MONTH);

        } catch (Exception e) {

        }
        return SUCCESS;
    }

    public String incrementMonth() {

        try {
            Map session = ActionContext.getContext().getSession();
           
            ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            getAllDetails();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateMonth = (Date) formatter.parse(dateMonthValue);
            startDate = session.get("startDate").toString();
            endDate = session.get("endDate").toString();

            Calendar now = Calendar.getInstance();
            now.setTime(dateMonth);
            now.add(Calendar.MONTH, +1);

            yearList = "" + now.get(Calendar.YEAR);
           
            monthValue = "" + now.get(Calendar.MONTH);

        } catch (Exception e) {

        }
        return SUCCESS;
    }

    public String decrementMonth() {

        try {
            Map session = ActionContext.getContext().getSession();
          
            ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            getAllDetails();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateMonth = (Date) formatter.parse(dateMonthValue);
            startDate = session.get("startDate").toString();
            endDate = session.get("endDate").toString();

            Calendar now = Calendar.getInstance();
            now.setTime(dateMonth);
            now.add(Calendar.MONTH, -1);

            yearList = "" + now.get(Calendar.YEAR);
            
            monthValue = "" + now.get(Calendar.MONTH);
        } catch (Exception e) {

        }
        return SUCCESS;
    }

    public void getAllDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        try {

            startDate = session.get("startDate").toString();
            endDate = session.get("endDate").toString();
            employeeId = Integer.parseInt(session.get("employeeId").toString());

            if (employeeId == 0) {
                employeeId = oEmp.getEmployeeId();
            }

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            Date startdate = dateFormat.parse(startDate);
            Date enddate = dateFormat.parse(endDate);

            int date = Integer.parseInt(year.format(startdate));
            int month = Integer.parseInt(monthformat.format(startdate));
            yearList = "" + date;
           
            monthValue = "" + month;

            // Project Details
            searchResult = projectAssignEmpService.empScheduleProjSearchResult(employeeId, startdate, enddate);
            // Leave Details
            empleavedetailsList = leaveHistoryService.getLeaveHistoryDetails(employeeId);
            // Timesheet Details
            emptimesheetList = timeSheetProjectAssignService.getEmpTimesheetDetails(employeeId);
            empCategorytimesheetList = timeSheetCategoryAssignService.timeSheetCategoryEmpSearchResult(employeeId);
        } catch (Exception e) {
        }

    }

    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getYearList() {
        return yearList;
    }

    public void setYearList(String yearList) {
        this.yearList = yearList;
    }

   
    public List getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List searchResult) {
        this.searchResult = searchResult;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List getEmptimesheetList() {
        return emptimesheetList;
    }

    public void setEmptimesheetList(List emptimesheetList) {
        this.emptimesheetList = emptimesheetList;
    }

    public Double getEnteredVal() {
        return enteredVal;
    }

    public void setEnteredVal(Double enteredVal) {
        this.enteredVal = enteredVal;
    }

    public String getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(String timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }

    public List getEmpCategorytimesheetList() {
        return empCategorytimesheetList;
    }

    public void setEmpCategorytimesheetList(List empCategorytimesheetList) {
        this.empCategorytimesheetList = empCategorytimesheetList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List getEmpleavedetailsList() {
        return empleavedetailsList;
    }

    public void setEmpleavedetailsList(List empleavedetailsList) {
        this.empleavedetailsList = empleavedetailsList;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(String leaveStart) {
        this.leaveStart = leaveStart;
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeesVO> getSubEmpList() {
        return subEmpList;
    }

    public void setSubEmpList(List<EmployeesVO> subEmpList) {
        this.subEmpList = subEmpList;
    }

    public List getEmptylist() {
        return emptylist;
    }

    public void setEmptylist(List emptylist) {
        this.emptylist = emptylist;
    }

    public void setErrText(String errText) {
        this.errText = errText;
    }

    public String getErrText() {
        return errText;
    }

    public String getMonthInput() {
        return monthInput;
    }

    public void setMonthInput(String monthInput) {
        this.monthInput = monthInput;
    }

   
    public List getProjectList() {
        return projectList;
    }

    public void setProjectList(List projectList) {
        this.projectList = projectList;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public List getProjectActivityList() {
		return projectActivityList;
	}

	public void setProjectActivityList(List projectActivityList) {
		this.projectActivityList = projectActivityList;
	}
   
	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getHours() {
		return hours;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setDateMonthValue(String dateMonthValue) {
		this.dateMonthValue = dateMonthValue;
	}

	public String getDateMonthValue() {
		return dateMonthValue;
	}

	public void setApproveNotes(String approveNotes) {
		this.approveNotes = approveNotes;
	}

	public String getApproveNotes() {
		return approveNotes;
	}

	public void setMonthValue(String monthValue) {
		this.monthValue = monthValue;
	}

	public String getMonthValue() {
		return monthValue;
	}
	public List getScheduleTypeList() {
		return scheduleTypeList;
	}

	public void setScheduleTypeList(List scheduleTypeList) {
		this.scheduleTypeList = scheduleTypeList;
	}
}
