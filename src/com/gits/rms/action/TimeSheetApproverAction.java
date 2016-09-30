
package com.gits.rms.action;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetApproverAction extends ActionSupport {
    private static final long serialVersionUID = -8550150255424211681L;
    static Logger log = Logger.getLogger(TimeSheetApproverAction.class.getName());// for store log details
    private TimeSheetApproverService timeSheetAppproverService = new TimeSheetApproverDaoService();
    private TimeSheetApproverVO timeSheetApprover;
    private List<TimeSheetApproverVO> timeSheetApproverList;
    private List<TimeSheetApproverVO> timeSheetApproverListForMail;
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetCategoryAssignVO timeSheetCategoryAssignVO;
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;
    private TimeSheetProjectAssignVO timeSheetProjectAssignVO = new TimeSheetProjectAssignVO();
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private Integer sameInsertTimeSheetApproverOpr;
    private SignatureVO sigObj;
    private String sSignature;
    private List dateList = new ArrayList();
    private Integer employeeId = 0;
    private String monthList = "";
    private String yearList = "";
    private String monthList1 = "";
    private String yearList1 = "";
    private Boolean isCurrentYear;
    private Boolean isCurrentMonth;
    private Boolean isEntered;
    private Boolean isRejected;
    private Boolean isApproved;
    private Boolean isReworked;
    private TreeMap<String, String> activityMap = new TreeMap<String, String>();
    private HashMap<String, String> dateDetail = new HashMap<String, String>();
    private ProjectAssignEmpVO tsProjAssigned = new ProjectAssignEmpVO();
    private EmployeesService employeesService = new EmployeesDaoService();

    private String viewTimesheet;


    private Set pendingEmpList;
    private List<EmployeesVO> subEmpList = new ArrayList<EmployeesVO>();
    private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();
    private List<EmployeesVO> pendingList = new ArrayList<EmployeesVO>();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();

    // Get List of TimeSheetApprover
    @SkipValidation
    public String getAllTimeSheetApprover() {
        getAllTsSubEmployees();
        timeSheetApproverList = timeSheetAppproverService.getAllTimeSheetApprover();
        return SUCCESS;
    }

    // To View the TimeSheet Search Form
    @SkipValidation
    public String timeAppSearchForm() {
        return SUCCESS;
    }

    // To Search Timesheet Approver
    @SkipValidation
    public String timeAppSearchResult() {
        timeSheetApproverList = timeSheetAppproverService.timeAppSearchResult(timeSheetApprover);
        return SUCCESS;
    }

    @SkipValidation
    public List getAllTsSubEmployees() {
        timeSheetApproverList = timeSheetAppproverService.getAllTsSubEmployees();
        ArrayList<Integer> alTSApproverList = new ArrayList<Integer>();
        for (int i = 0; i < timeSheetApproverList.size(); i++) {
            alTSApproverList.add(timeSheetApproverList.get(i).getHcmoEmployeeId().getEmployeeId());
        }
        return alTSApproverList;
    }

    // Based on EmployeeId get All TimeSheetApprover of that Employee
    @SkipValidation
    public String getEmployeeAllTimeSheetApprover() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        Integer employeeId1 = (Integer) session.get("EMPLOYEE_ID");

        // Integer employeeId = new
        // Integer(Integer.valueOf(ServletActionContext.getRequest().getParameter("timeSheetApprover.hcmoEmployeeId.employeeId")));
        Integer employeeId = Integer.valueOf(employeeId1);
        if (employeeId == null) {
            employeeId = timeSheetApprover.getHcmoEmployeeId().getEmployeeId();
        }
        timeSheetApproverList = timeSheetAppproverService.getEmployeeAllTimeSheetApprover(employeeId);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // TimeSheetApprover it shows blank Form to enter New Data
    @SkipValidation
    public String setUpInsertOrUpdateTimeSheetApprover() {
        if ((timeSheetApprover != null) && (timeSheetApprover.getHcmoApproverId() != null)) {
            timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        }
        return SUCCESS;
    }

    // To get Particular TimeSheet Approver Data
    @SkipValidation
    public String timesheetApproverView() {
        if ((timeSheetApprover != null) && (timeSheetApprover.getHcmoApproverId() != null)) {
            timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new TimeSheetApprover for that Employee or
    // Update data of the TimeSheetApprover
    @SkipValidation
    public String setUpEmpInsertOrUpdateTimeSheetApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("timeSheetApprover.hcmoEmployeeId.employeeId"));
        if ((timeSheetApprover != null) && (timeSheetApprover.getHcmoApproverId() != null)
            && (employeeId != null)) {
            timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new TimeSheetApprover for that Employee or
    // Update data of the TimeSheetApprover
    @SkipValidation
    public String setUpEmpInsertOrUpdateTimeSheetApproverSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("timeSheetApprover.hcmoEmployeeId.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("timeSheetApprover.hcmoApproverId"));
        if ((timeSheetApprover != null) && (timeSheetApprover.getHcmoApproverId() != null)
            && (employeeId != null)) {
            timeSheetApprover = timeSheetAppproverService.getEmpTimeSheetApprover(timeSheetApprover);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new TimeSheetApprover
    // or update particular TimeSheetApprover Data
    public String insertOrUpdateTimeSheetApprover() {
        /*if (!validationEmp()) {
            return INPUT;
        } else {
        */    try {
        	    log.debug("control enters into insertion of timesheet approver");
                if (timeSheetApprover.getHcmoApproverId() == null) {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    Integer employeeId = (Integer) session.get("EMPLOYEE_ID");
                    timeSheetApprover.setCreated(DateUtils.getCurrentDateTime());
                    timeSheetApprover.setCreatedBy(oEmp);
                    timeSheetApprover.setUpdatedBy(oEmp);
                    timeSheetApprover.setIsActive(1);
                    /*timeSheetApproverListForMail = timeSheetAppproverService.getEmployeeAllTimeSheetApprover(timeSheetApprover.getHcmoEmployeeId().getEmployeeId());

                    if (timeSheetApproverListForMail.size() > 0) {
                        addActionError(getText("error.timesheet.approver.exists"));
                        return ERROR;
                    }

                    // To prevent the Duplicate Operation again.
                    sameInsertTimeSheetApproverOpr = timeSheetAppproverService.getTimeSheetApproverCount(timeSheetApprover);
                    if (sameInsertTimeSheetApproverOpr > 0) {
                        addActionError(getText("label.title.timeSheetApprover.sameOperationAgain"));
                        return INPUT;

                    }
*/
                    timeSheetAppproverService.insertTimeSheetApprover(timeSheetApprover);
                    /*String sSubject = getText("message.subject.timesheetApprover.add");
                    timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());

                    if ((timeSheetApproverListForMail.size() != 0)
                        || (timeSheetApproverListForMail.isEmpty() == false)) {
                        TimeSheetApproverVO newTimesheetApprover;
                        for (Iterator<TimeSheetApproverVO> it = timeSheetApproverListForMail.iterator(); it.hasNext();) {
                            newTimesheetApprover = it.next();
                            mailForApprovers(newTimesheetApprover.getHcmoApprovingEmpId().getEmployeeId(), newTimesheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.allOtherApprovers.add.Status"), newTimesheetApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                    mail(timeSheetApprover.getHcmoEmployeeId().getEmployeeId(), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), getText("timesheetApprover.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    if (!employeeId.equals(timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId())) {
                        mailForApprovers(timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId(), timeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.allOtherApprovers.add.Status"), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetApprover.loggedIn.add.addedBy"), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);

                    // To Set Menu for Approving Employee
                    if (timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId().equals(oEmp.getEmployeeId())) {
                        if (session.get("TIMESHEET_APPROVER") != "BOTH") {
                            session.put("TIMESHEET_APPROVER", "BOTH");
                        }
                    }*/
                    addActionMessage(getText("Added Successfully"));
                    log.info("Approver details successfully inserted");
                } else {
                	log.debug("control enters into timesheet approver updation");
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    timeSheetApprover.setUpdatedBy(oEmp);
                    /*TimeSheetApproverVO editTimeApp = new TimeSheetApproverVO();
                    editTimeApp = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
                    editTimeApp.getHcmoApprovingEmpId().getEmployeeId();
                    timeSheetApproverListForMail = timeSheetAppproverService.getEmployeeAllTimeSheetApprover(timeSheetApprover.getHcmoEmployeeId().getEmployeeId());

                    // To prevent the Duplicate Operation again.
                    sameInsertTimeSheetApproverOpr = timeSheetAppproverService.getTimeSheetApproverCount(timeSheetApprover);
                    if (sameInsertTimeSheetApproverOpr > 0) {
                        addActionError(getText("label.title.timeSheetApprover.sameOperationAgain"));
                        return INPUT;
                    }*/

                    timeSheetAppproverService.updateTimeSheetApprover(timeSheetApprover);
                    /*String sSubject = getText("message.subject.timesheetApprover.edit");

                    if ((timeSheetApproverListForMail.size() != 0)
                        || (timeSheetApproverListForMail.isEmpty() == false)) {
                        TimeSheetApproverVO newTimeSheetApprover;
                        TimeSheetApproverVO newTimeSheetApproverInnerLoop;
                        for (Iterator<TimeSheetApproverVO> it = timeSheetApproverListForMail.iterator(); it.hasNext();) {
                            newTimeSheetApprover = it.next();
                            int outerApproverId = newTimeSheetApprover.getHcmoApproverId();
                            int outerApproverEmpId = newTimeSheetApprover.getHcmoApprovingEmpId().getEmployeeId();

                            timeSheetApproverList = timeSheetAppproverService.getEmployeeAllTimeSheetApprover(timeSheetApprover.getHcmoEmployeeId().getEmployeeId());
                            for (Iterator<TimeSheetApproverVO> ite = timeSheetApproverList.iterator(); ite.hasNext();) {
                                newTimeSheetApproverInnerLoop = ite.next();
                                int innerApproverId = newTimeSheetApproverInnerLoop.getHcmoApproverId();
                                int innerEmployeeId = newTimeSheetApproverInnerLoop.getHcmoApprovingEmpId().getEmployeeId();

                                if (outerApproverId == innerApproverId) {
                                    if (outerApproverEmpId != innerEmployeeId) {
                                        mailForOldApprover(newTimeSheetApprover.getHcmoApprovingEmpId().getEmployeeId(), newTimeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.oldApprover.edit.changeStatus"), newTimeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                        mailForApprovers(newTimeSheetApproverInnerLoop.getHcmoApprovingEmpId().getEmployeeId(), newTimeSheetApproverInnerLoop.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.allOtherApprovers.edit.Status"), newTimeSheetApproverInnerLoop.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    } else if (outerApproverEmpId == innerEmployeeId) {
                                        mailForApprovers(newTimeSheetApprover.getHcmoApprovingEmpId().getEmployeeId(), newTimeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.allOtherApprovers.edit.Status"), newTimeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                }
                            }

                        }
                    }
                    timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
                    mail(timeSheetApprover.getHcmoEmployeeId().getEmployeeId(), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), getText("timesheetApprover.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetApprover.loggedIn.edit.updatedBy"), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);

                    // To Set Menu for Approving Employee
                    Integer TimesheetApproverCount = timeSheetAppproverService.checkTimesheetApprover(oEmp.getEmployeeId());
                    if (TimesheetApproverCount > 0) {
                        session.put("TIMESHEET_APPROVER", "BOTH");
                    } else {
                        session.put("TIMESHEET_APPROVER", "NON-APPROVER");
                    }*/
                    addActionMessage(getText("Updated Successfully"));
                    log.info("approver details updated successfully");
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Exception occurred during the insertion/updation of timesheet approver" + e);
            }
        //}
        return SUCCESS;
    }

    // To delete particular TimesheetApprover detail
    @SkipValidation
    public String deleteTimeSheetApprover() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        timeSheetApprover.setUpdatedBy(oEmp);
        timeSheetAppproverService.deleteTimeSheetApprover(timeSheetApprover);
        String sSubject = getText("message.subject.timesheetApprover.delete");

        TimeSheetApproverVO newTimesheetApprover;
        timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        timeSheetApproverList = timeSheetAppproverService.getEmployeeAllTimeSheetApprover(timeSheetApprover.getHcmoEmployeeId().getEmployeeId());
        for (Iterator<TimeSheetApproverVO> it = timeSheetApproverList.iterator(); it.hasNext();) {
            newTimesheetApprover = it.next();
            mailForApprovers(newTimesheetApprover.getHcmoApprovingEmpId().getEmployeeId(), newTimesheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.allOtherApprovers.delete.Status"), newTimesheetApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
        }
        timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        mail(timeSheetApprover.getHcmoEmployeeId().getEmployeeId(), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), getText("timesheetApprover.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        mail(timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId(), timeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("timesheetApprover.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetApprover.loggedIn.delete.deletedBy"), timeSheetApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);

        // To Set Menu for Approving Employee
        Integer TimesheetApproverCount = timeSheetAppproverService.checkTimesheetApprover(oEmp.getEmployeeId());
        if (TimesheetApproverCount > 0) {
            session.put("TIMESHEET_APPROVER", "BOTH");
        } else {
            session.put("TIMESHEET_APPROVER", "NON-APPROVER");
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    private boolean validationEmp() {
        if (timeSheetApprover.getHcmoEmployeeId().getEmployeeId().equals(timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId())) {
            addActionError(getText("errors.timesheet.selfApprover"));
            return false;
        } else {
            return true;
        }
    }

    @SkipValidation
    public String validateAssignedEmployeeProject() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer employeeId = oEmp.getEmployeeId();
        timeSheetProjectAssignList = timeSheetProjectAssignService.getEmployeeAssignedProjectList(employeeId);
        if (timeSheetProjectAssignList.isEmpty()) {
            addActionError("You have not yet appointed in any of the project to enter Timesheet, please contact your admin for further information");
            return INPUT;
        } else {
            return SUCCESS;
        }

    }

    @SkipValidation
    public String getEmployeeData() {
        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();
        session.put("EmployeeId", EmployeeId);
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();

            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();

            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String sample() {
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeDataLink() {

    	if(tsProjAssigned.getEmployeeName().getEmployeeId()== null) {
    		pendingEmpList = new TreeSet();
            subEmpList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
            for (int i = 0; i <= (subEmpList.size() - 1); i++) {
                pendingList = timeSheetProjectAssignService.getEmpPendingApprovalStatus(subEmpList.get(i).getEmployeeId());
                if (pendingList.size() > 0) {
                    pendingEmpList.add(subEmpList.get(i).getEmpFirstName());
                }
            }
            loadValues.getAllSubEmployeeForTimeSheet();

    		addActionError("Please Select A Employee");
    		return INPUT;
    	}


        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        viewTimesheet = "View";
        EmployeesVO empname = new EmployeesVO();
        empname = employeesService.getEmployeeById(tsProjAssigned.getEmployeeName().getEmployeeId());
        if (tsProjAssigned.getEmployeeName().getEmployeeId() == null) {
            return ERROR;
        }
        Integer EmployeeId = tsProjAssigned.getEmployeeName().getEmployeeId();
        session.put("EmployeeId", tsProjAssigned.getEmployeeName().getEmployeeId());
        session.put("EmployeeName", empname.getEmpFirstName().toUpperCase() + " "
            + empname.getEmpLastName().toUpperCase());
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();

            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                            activityMap.put(sMonth.format(enterDate) + "-"
                                + sDate.format(enterDate) + "-" + sYear.format(enterDate), "Pending For Approval");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                        activityMap.put(sMonth.format(enterDate) + "-" + sDate.format(enterDate)
                            + "-" + sYear.format(enterDate), "Pending For Approval");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();

            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                            activityMap.put(sMonth.format(enterDate) + "-"
                                + sDate.format(enterDate) + "-" + sYear.format(enterDate), "Pending For Approval");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                        activityMap.put(sMonth.format(enterDate) + "-" + sDate.format(enterDate)
                            + "-" + sYear.format(enterDate), "Pending For Approval");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeDataCalendarDec() {
        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        if ((monthList.length() == 0) || (monthList == null)) {
        } else {
            calender.set(Calendar.MONTH, Integer.parseInt(monthList));

        }
        viewTimesheet = "View";
        calender.add(Calendar.MONTH, -1);
        if (Integer.parseInt(monthList) == 0) {
            calender.set(Calendar.YEAR, Integer.parseInt(yearList) - 1);
            yearList = String.valueOf(calender.get(Calendar.YEAR));
        }
        if ((calender.get(Calendar.YEAR) > Integer.parseInt(yearList))
            || (calender.get(Calendar.YEAR) < Integer.parseInt(yearList))) {
            calender.set(Calendar.YEAR, Integer.parseInt(yearList));
            yearList = String.valueOf(calender.get(Calendar.YEAR));
        }

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.setTime(calender.getTimeInMillis());
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        if (timeSheetCategoryAssignList.size() == 0) {
            monthList = String.valueOf(calender.get(Calendar.MONTH) + 1);
        }
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();

            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                            activityMap.put(sMonth.format(enterDate) + "-"
                                + sDate.format(enterDate) + "-" + sYear.format(enterDate), "Pending For Approval");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                        activityMap.put(sMonth.format(enterDate) + "-" + sDate.format(enterDate)
                            + "-" + sYear.format(enterDate), "Pending For Approval");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);

            }
            if ((yearList.length() == 0) || (yearList == null)) {
                yearList = String.valueOf(calender.get(Calendar.YEAR));
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();
            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeDataCalendarInc() {
        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        if ((monthList.length() == 0) || (monthList == null)) {
        } else {
            calender.set(Calendar.MONTH, Integer.parseInt(monthList));
        }
        viewTimesheet = "View";
        calender.add(Calendar.MONTH, 1);
        if (Integer.parseInt(monthList) == 11) {
            calender.set(Calendar.YEAR, Integer.parseInt(yearList) + 1);
            yearList = String.valueOf(calender.get(Calendar.YEAR));
        }
        if (calender.get(Calendar.YEAR) < Integer.parseInt(yearList)) {
            calender.set(Calendar.YEAR, Integer.parseInt(yearList));
            yearList = String.valueOf(calender.get(Calendar.YEAR));
        }

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.setTime(calender.getTimeInMillis());
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        if (timeSheetCategoryAssignList.size() == 0) {
            monthList = String.valueOf(calender.get(Calendar.MONTH) + 1);
        }
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();
            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if ((yearList.length() == 0) || (yearList == null)) {
                yearList = String.valueOf(calender.get(Calendar.YEAR));
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();

            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeDataCalendarYearDec() {
        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        if ((monthList.length() == 0) || (monthList == null)) {
        } else {
            calender.set(Calendar.MONTH, Integer.parseInt(monthList));

        }
        viewTimesheet = "View";
        calender.set(Calendar.YEAR, Integer.parseInt(yearList) - 1);
        yearList = String.valueOf(calender.get(Calendar.YEAR));

        Map session = ActionContext.getContext().getSession();
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.setTime(calender.getTimeInMillis());
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        if (timeSheetCategoryAssignList.size() == 0) {
            monthList = String.valueOf(calender.get(Calendar.MONTH) + 1);
        }
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();

            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();

                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);

            }
            if ((yearList.length() == 0) || (yearList == null)) {
                yearList = String.valueOf(calender.get(Calendar.YEAR));
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();

            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeDataCalendarYearInc() {
        Date d = new Date();
        Calendar calender = Calendar.getInstance();
        if ((monthList.length() == 0) || (monthList == null)) {
        } else {
            calender.set(Calendar.MONTH, Integer.parseInt(monthList));

        }
        viewTimesheet = "View";
        calender.set(Calendar.YEAR, Integer.parseInt(yearList) + 1);
        yearList = String.valueOf(calender.get(Calendar.YEAR));
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), 1);
        d.setTime(calender.getTimeInMillis());
        Date date = new Date();
        calender.set(calender.get(calender.YEAR), calender.get(calender.MONTH), calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        date.setTime(calender.getTimeInMillis());
        timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearchResult(EmployeeId, new Date(d.getTime()), new Date(date.getTime()));
        if (timeSheetCategoryAssignList.size() == 0) {
            monthList = String.valueOf(calender.get(Calendar.MONTH) + 1);
        }
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        try {
            Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();

            while (it.hasNext()) {
                timeSheetCategoryAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                if (timeSheetCategoryAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);

            }
            if ((yearList.length() == 0) || (yearList == null)) {
                yearList = String.valueOf(calender.get(Calendar.YEAR));
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();

            while (it.hasNext()) {
                timeSheetProjectAssignVO = it.next();
                Calendar.getInstance();
                Date enterDate = timeSheetProjectAssignVO.getEnterDate();
                if (timeSheetProjectAssignVO.getRejected() == 1) {
                    dateDetail.put(sDate.format(enterDate), "Rejected");
                } else if (timeSheetProjectAssignVO.getRework() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Rework");
                    }
                } else if (timeSheetProjectAssignVO.getApprove() == 1) {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Approve");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Approve");
                    }
                } else {
                    if (dateDetail.containsKey(sDate.format(enterDate))) {
                        if (dateDetail.get(sDate.format(enterDate)).equals("Rejected")) {
                            dateDetail.put(sDate.format(enterDate), "Rejected");
                        } else if (dateDetail.get(sDate.format(enterDate)).equals("Rework")) {
                            dateDetail.put(sDate.format(enterDate), "Rework");
                        } else {
                            dateDetail.put(sDate.format(enterDate), "Entered");
                        }
                    } else {
                        dateDetail.put(sDate.format(enterDate), "Entered");
                    }
                }
                dateList.add(sDate.format(enterDate));
                monthList = sMonth.format(enterDate);
                yearList = sYear.format(enterDate);
            }
            if (yearList.equals(sYear.format(date)) && monthList.equals(sMonth.format(date))) {
                isCurrentYear = true;
            } else {
                isCurrentYear = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        Format formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sDummy), sMessage.lastIndexOf(sDummy)
            + sDummy.length(), From);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("message.header.timesheetApprover.details")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + timeSheetApprover.getHcmoEmployeeId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.timesheet.timesheetApprovingEmployee")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON
            + timeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + formatter.format(timeSheetApprover.getUpdated())
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    public void mailForApprovers(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        Format formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        timeSheetApprover = timeSheetAppproverService.getTimeSheetApprover(timeSheetApprover.getHcmoApproverId());
        HCMOneMailer mailer = new HCMOneMailer();
        String sDummy = Constants.PERSON;
        String sFirstPerson = Constants.EMPLOYEE_PERSON;
        String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));

        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);

        sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
            + sFirstPerson.length(), From);
        sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
            + sLoggedInPerson.length(), LoggedIn);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("message.header.timesheetApprover.details")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + timeSheetApprover.getHcmoEmployeeId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.timesheet.timesheetApprovingEmployee")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON
            + timeSheetApprover.getHcmoApprovingEmpId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + formatter.format(timeSheetApprover.getUpdated())
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    public void mailForOldApprover(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        HCMOneMailer mailer = new HCMOneMailer();
        String sDummy = Constants.PERSON;
        String sFirstPerson = Constants.EMPLOYEE_PERSON;
        String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
            + sFirstPerson.length(), From);
        sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
            + sLoggedInPerson.length(), LoggedIn);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    public TimeSheetApproverVO getTimeSheetApprover() {
        return timeSheetApprover;
    }

    public void setTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        this.timeSheetApprover = timeSheetApprover;
    }

    public List<TimeSheetApproverVO> getTimeSheetApproverList() {
        return timeSheetApproverList;
    }

    public void setTimeSheetApproverList(List<TimeSheetApproverVO> timeSheetApproverList) {
        this.timeSheetApproverList = timeSheetApproverList;
    }

    public TimeSheetApproverService getTimeSheetAppproverService() {
        return timeSheetAppproverService;
    }

    public void setTimeSheetAppproverService(TimeSheetApproverService timeSheetAppproverService) {
        this.timeSheetAppproverService = timeSheetAppproverService;
    }

    public TimeSheetCategoryAssignService getTimeSheetCategoryAssignService() {
        return timeSheetCategoryAssignService;
    }

    public void setTimeSheetCategoryAssignService(TimeSheetCategoryAssignService timeSheetCategoryAssignService) {
        this.timeSheetCategoryAssignService = timeSheetCategoryAssignService;
    }

    public TimeSheetCategoryAssignVO getTimeSheetCategoryAssignVO() {
        return timeSheetCategoryAssignVO;
    }

    public void setTimeSheetCategoryAssignVO(TimeSheetCategoryAssignVO timeSheetCategoryAssignVO) {
        this.timeSheetCategoryAssignVO = timeSheetCategoryAssignVO;
    }

    public List<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
        return timeSheetCategoryAssignList;
    }

    public void setTimeSheetCategoryAssignList(List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
        this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
    }

    public List getDateList() {
        return dateList;
    }

    public void setDateList(List dateList) {
        this.dateList = dateList;
    }

    public String getMonthList() {
        return monthList;
    }

    public void setMonthList(String monthList) {
        this.monthList = monthList;
    }

    public String getYearList() {
        return yearList.trim();
    }

    public void setYearList(String yearList) {
        this.yearList = yearList;
    }

    public String getMonthList1() {
        return monthList1;
    }

    public void setMonthList1(String monthList1) {
        this.monthList1 = monthList1;
    }

    public String getYearList1() {
        return yearList1;
    }

    public void setYearList1(String yearList1) {
        this.yearList1 = yearList1;
    }

    public Boolean getIsCurrentYear() {
        return isCurrentYear;
    }

    public void setIsCurrentYear(Boolean isCurrentYear) {
        this.isCurrentYear = isCurrentYear;
    }

    public Boolean getIsCurrentMonth() {
        return isCurrentMonth;
    }

    public void setIsCurrentMonth(Boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
    }

    public Boolean getIsEntered() {
        return isEntered;
    }

    public void setIsEntered(Boolean isEntered) {
        this.isEntered = isEntered;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsReworked() {
        return isReworked;
    }

    public void setIsReworked(Boolean isReworked) {
        this.isReworked = isReworked;
    }

    public HashMap<String, String> getDateDetail() {
        return dateDetail;
    }

    public void setDateDetail(HashMap<String, String> dateDetail) {
        this.dateDetail = dateDetail;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<TimeSheetProjectAssignVO> getTimeSheetProjectAssignList() {
        return timeSheetProjectAssignList;
    }

    public void setTimeSheetProjectAssignList(List<TimeSheetProjectAssignVO> timeSheetProjectAssignList) {
        this.timeSheetProjectAssignList = timeSheetProjectAssignList;
    }

    public TreeMap<String, String> getActivityMap() {
        return activityMap;
    }

    public void setActivityMap(TreeMap<String, String> activityMap) {
        this.activityMap = activityMap;
    }

    public ProjectAssignEmpVO getTsProjAssigned() {
        return tsProjAssigned;
    }

    public void setTsProjAssigned(ProjectAssignEmpVO tsProjAssigned) {
        this.tsProjAssigned = tsProjAssigned;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public String getViewTimesheet() {
        return viewTimesheet;
    }

    public void setViewTimesheet(String viewTimesheet) {
        this.viewTimesheet = viewTimesheet;
    }

    public void setSameInsertTimeSheetApproverOpr(Integer sameInsertTimeSheetApproverOpr) {
        this.sameInsertTimeSheetApproverOpr = sameInsertTimeSheetApproverOpr;
    }

    public Integer getSameInsertTimeSheetApproverOpr() {
        return sameInsertTimeSheetApproverOpr;
    }

	public Set getPendingEmpList() {
		return pendingEmpList;
	}

	public void setPendingEmpList(Set pendingEmpList) {
		this.pendingEmpList = pendingEmpList;
	}

}