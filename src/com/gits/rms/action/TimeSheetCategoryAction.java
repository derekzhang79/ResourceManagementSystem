
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.TimesheetCategoryDaoService;
import com.gits.rms.service.TimesheetCategoryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimesheetCategoryVO;

public class TimeSheetCategoryAction extends ActionSupport {
    private static final long serialVersionUID = 8428067475024536572L;
    private TimesheetCategoryService timesheetCategoryService = new TimesheetCategoryDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<TimesheetCategoryVO> timesheetCategorylist;
    private List<TimeSheetCategoryAssignVO> timesheetCategoryAssignObj;
    private TimesheetCategoryVO timesheetCategory;

    // To get List of Nationality for an Employee
    @SkipValidation
    public String getAllTimesheetCategory() {
        timesheetCategorylist = timesheetCategoryService.getAllTimesheetCategory();
        return SUCCESS;
    }

    // //To View the Nationality Form
    @SkipValidation
    public String timesheetCategorySearchForm() {
        timesheetCategorylist = timesheetCategoryService.getAllTimesheetCategory();
        return SUCCESS;
    }

    // To Search Nationality
    @SkipValidation
    public String timesheetCategorySearchResult() {
        timesheetCategorylist = timesheetCategoryService.timesheetCategorySearchResult(timesheetCategory);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Nationality it shows blank Form to enter New Data
    @SkipValidation
    public String deleteTimesheetCategory() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the TimesheetCategory is being used in the Timesheet
        // Entered Page,If yes then we are not allowing the TimesheetCategory to
        // get delete.
        timesheetCategoryAssignObj = timesheetCategoryService.getAssignedTimesheetCategoryid(timesheetCategory.getHcmoTimesheetCategoryId());
        if (!timesheetCategoryAssignObj.isEmpty()) {
            addActionError(getText("label.header.timeSheet.msg.deleteAssignedTimesheetCategory"));
            return SUCCESS;
        }
        timesheetCategory.setUpdatedBy(oEmp);
        timesheetCategoryService.deleteTimesheetCategory(timesheetCategory);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllTimesheetCategoryName();
        return SUCCESS;
    }

    @SkipValidation
    public String setUpTimesheetCategory() {
        if ((timesheetCategory != null) && (timesheetCategory.getHcmoTimesheetCategoryId() != null)) {
            timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategory.getHcmoTimesheetCategoryId());
        }
        return SUCCESS;
    }

    // To get Particular Nationality Data of an Employee
    @SkipValidation
    public String timesheetCategoryView() {
        if ((timesheetCategory != null) && (timesheetCategory.getHcmoTimesheetCategoryId() != null)) {
            timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategory.getHcmoTimesheetCategoryId());
        }
        return SUCCESS;
    }

    // To insert new Nationality detail or Edit Particular Nationality Detail
    public String insertOrUpdateTimesheetCategory() {
        try {
            if (timesheetCategory.getHcmoTimesheetCategoryId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                timesheetCategory.setCreated(DateUtils.getCurrentDateTime());
                timesheetCategory.setCreatedBy(oEmp);
                timesheetCategory.setUpdatedBy(oEmp);
                timesheetCategory.setIsActive(1);
                timesheetCategoryService.insertTimesheetCategory(timesheetCategory);
                addActionMessage(getText("Added Successfully"));
            } else if (timesheetCategory.getHcmoTimesheetCategoryId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                timesheetCategory.setUpdatedBy(oEmp);
                timesheetCategoryService.updateTimesheetCategory(timesheetCategory);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "'" + timesheetCategory.getName() + "'"
                + " already exist");
            ErrorsAction errAction = new ErrorsAction();

            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllTimesheetCategoryName();
        return SUCCESS;
    }

    public TimesheetCategoryService getTimesheetCategoryService() {
        return timesheetCategoryService;
    }

    public void setTimesheetCategoryService(TimesheetCategoryService timesheetCategoryService) {
        this.timesheetCategoryService = timesheetCategoryService;
    }

    public List<TimesheetCategoryVO> getTimesheetCategorylist() {
        return timesheetCategorylist;
    }

    public void setTimesheetCategorylist(List<TimesheetCategoryVO> timesheetCategorylist) {
        this.timesheetCategorylist = timesheetCategorylist;
    }

    public TimesheetCategoryVO getTimesheetCategory() {
        return timesheetCategory;
    }

    public void setTimesheetCategory(TimesheetCategoryVO timesheetCategory) {
        this.timesheetCategory = timesheetCategory;
    }

    public void setTimesheetCategoryAssignObj(List<TimeSheetCategoryAssignVO> timesheetCategoryAssignObj) {
        this.timesheetCategoryAssignObj = timesheetCategoryAssignObj;
    }

    public List<TimeSheetCategoryAssignVO> getTimesheetCategoryAssignObj() {
        return timesheetCategoryAssignObj;
    }

    // To delete Particular Nationality Detail

}
