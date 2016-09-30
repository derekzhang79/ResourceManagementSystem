
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.TimesheetCategoryEmpDaoService;
import com.gits.rms.service.TimesheetCategoryEmpService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimesheetCategoryEmpVO;

public class TimeSheetCategoryEmpAction extends ActionSupport {
    private static final long serialVersionUID = -8196652957610198479L;
    private TimesheetCategoryEmpService timesheetCategoryEmpService = new TimesheetCategoryEmpDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<TimesheetCategoryEmpVO> timesheetCategoryEmplist;
    private List<TimeSheetCategoryAssignVO> timesheetCategoryAssignObj;
    private TimesheetCategoryEmpVO timesheetCategoryEmp;
    private TimesheetCategoryEmpVO timesheetCategoryEmpCount;

    // To get List of TimeSheetCategory for an Employee
    @SkipValidation
    public String getAllTimesheetCategoryEmp() {
        timesheetCategoryEmplist = timesheetCategoryEmpService.getAllTimeSheetCategoryEmp();
        return SUCCESS;
    }

    // //To View the TimeSheetCategory Employee Form
    @SkipValidation
    public String timesheetCategoryEmpSearchForm() {
        timesheetCategoryEmplist = timesheetCategoryEmpService.getAllTimeSheetCategoryEmp();
        return SUCCESS;
    }

    // To Search TimeSheetCategory Employee
    @SkipValidation
    public String timesheetCategoryEmpSearchResult() {
        timesheetCategoryEmplist = timesheetCategoryEmpService.timeSheetCategoryEmpSearchResult(timesheetCategoryEmp);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // TimeSheetCategory Employee it shows blank Form to enter New Data
    @SkipValidation
    public String deleteTimesheetCategoryEmp() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        timesheetCategoryEmp.setUpdatedBy(oEmp);

        // To check that the TimesheetCategoryEmp is being used in the Timesheet
        // Entered Page,If yes then we are not allowing the TimesheetCategoryEmp
        // to get delete.
        timesheetCategoryEmpCount = timesheetCategoryEmpService.getTimeSheetCategoryEmpCount(timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId());
        if (timesheetCategoryEmpCount != null) {
            timesheetCategoryAssignObj = timesheetCategoryEmpService.checkTimeSheetCategoryInTimeSheetCategoryAssign(timesheetCategoryEmpCount);

            if (!(timesheetCategoryAssignObj.isEmpty())) {
                addActionError(getText("label.header.timeSheet.msg.deleteTimesheetCategory"));
                return SUCCESS;
            }
        }
        timesheetCategoryEmpService.deleteTimeSheetCategoryEmp(timesheetCategoryEmp);
        addActionMessage(getText("Deleted Successfully"));
        // For Drop down List
        loadValues.getAllTimesheetCategoryEmpName();
        return SUCCESS;
    }

    @SkipValidation
    public String setUpTimesheetCategoryEmp() {
        if ((timesheetCategoryEmp != null)
            && (timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId() != null)) {
            timesheetCategoryEmp = timesheetCategoryEmpService.getTimeSheetCategoryEmp(timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId());
        }
        return SUCCESS;
    }

    // To get Particular TimeSheetCategory Data of an Employee
    @SkipValidation
    public String timesheetCategoryEmpView() {

        if ((timesheetCategoryEmp != null)
            && (timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId() != null)) {
            timesheetCategoryEmp = timesheetCategoryEmpService.getTimeSheetCategoryEmp(timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId());
        }
        return SUCCESS;
    }

    // To insert new TimeSheetCategory Employee detail or Edit Particular
    // TimeSheetCategory Employee Detail
    public String insertOrUpdateTimesheetCategoryEmp() {
        TimesheetCategoryEmpVO timesheetCategoryEmpDet = timesheetCategoryEmpService.getTimeSheetCategoryEmpDetail(timesheetCategoryEmp);
        try {
            if (timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                timesheetCategoryEmp.setCreated(DateUtils.getCurrentDateTime());
                timesheetCategoryEmp.setCreatedBy(oEmp);
                timesheetCategoryEmp.setUpdatedBy(oEmp);
                timesheetCategoryEmp.setIsActive(1);
                timesheetCategoryEmpService.insertTimeSheetCategoryEmp(timesheetCategoryEmp);
                addActionMessage(getText("Added Successfully"));
            } else if (timesheetCategoryEmp.getHcmoTimesheetCategoryEmpId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                timesheetCategoryEmp.setUpdatedBy(oEmp);
                timesheetCategoryEmpService.updateTimeSheetCategoryEmp(timesheetCategoryEmp);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            addActionError(timesheetCategoryEmpDet.getEmployeeName().getEmpFirstName() + " & "
                + timesheetCategoryEmpDet.getTimesheetCategoryName().getName() + "'"
                + " already exists");
            return ERROR;
        }
        // For Drop down List
        loadValues.getAllNationalityName();
        return SUCCESS;
    }

    public TimesheetCategoryEmpService getTimesheetCategoryEmpService() {
        return timesheetCategoryEmpService;
    }

    public void setTimesheetCategoryEmpService(TimesheetCategoryEmpService timesheetCategoryEmpService) {
        this.timesheetCategoryEmpService = timesheetCategoryEmpService;
    }

    public List<TimesheetCategoryEmpVO> getTimesheetCategoryEmplist() {
        return timesheetCategoryEmplist;
    }

    public void setTimesheetCategoryEmplist(List<TimesheetCategoryEmpVO> timesheetCategoryEmplist) {
        this.timesheetCategoryEmplist = timesheetCategoryEmplist;
    }

    public TimesheetCategoryEmpVO getTimesheetCategoryEmp() {
        return timesheetCategoryEmp;
    }

    public void setTimesheetCategoryEmp(TimesheetCategoryEmpVO timesheetCategoryEmp) {
        this.timesheetCategoryEmp = timesheetCategoryEmp;
    }

    public void setTimesheetCategoryAssignObj(List<TimeSheetCategoryAssignVO> timesheetCategoryAssignObj) {
        this.timesheetCategoryAssignObj = timesheetCategoryAssignObj;
    }

    public List<TimeSheetCategoryAssignVO> getTimesheetCategoryAssignObj() {
        return timesheetCategoryAssignObj;
    }

    public void setTimesheetCategoryEmpCount(TimesheetCategoryEmpVO timesheetCategoryEmpCount) {
        this.timesheetCategoryEmpCount = timesheetCategoryEmpCount;
    }

    public TimesheetCategoryEmpVO getTimesheetCategoryEmpCount() {
        return timesheetCategoryEmpCount;
    }
}
