
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeeStatusDaoService;
import com.gits.rms.service.EmployeeStatusService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeStatusVO;
import com.gits.rms.vo.EmployeesVO;

public class EmployeeStatusAction extends ActionSupport {
    private static final long serialVersionUID = 954424060022834084L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private EmployeeStatusService empStatusService = new EmployeeStatusDaoService();
    private List<EmployeeStatusVO> empStatusList;
    private EmployeeStatusVO empStatus;
    private List<EmployeesVO> empList;

    // To get List of EmployeeStatus
    @SkipValidation
    public String getAllEmployeeStatus() {
        empStatusList = empStatusService.getAllEmployeeStatus();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String employeeStatusSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String employeeStatusSearchResult() {
        empStatusList = empStatusService.employeeStatusSearchResult(empStatus);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmployeeStatus it shows blank Form to enter New Data
    @SkipValidation
    public String setUpForInsertOrUpdateEmpStatus() {
        if ((empStatus != null) && (empStatus.getEmpStatusId() != null)) {
            empStatus = empStatusService.getEmployeeStatus(empStatus.getEmpStatusId());
        }
        return SUCCESS;
    }

    // To get Particular EmployeeStatus of an Employee
    @SkipValidation
    public String empStatusView() {
        if ((empStatus != null) && (empStatus.getEmpStatusId() != null)) {
            empStatus = empStatusService.getEmployeeStatus(empStatus.getEmpStatusId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EmployeeStatus or
    // update particular EmployeeStatus Data
    public String insertOrUpdateEmployeeStatus() {
        try {
            if (empStatus.getEmpStatusId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                empStatus.setCreated(DateUtils.getCurrentDateTime());
                empStatus.setCreatedBy(oEmp);
                empStatus.setUpdatedBy(oEmp);
                empStatus.setIsActive(1);
                empStatusService.insertEmployeeStatus(empStatus);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                empStatus.setUpdatedBy(oEmp);
                empStatusService.updateEmployeeStatus(empStatus);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllEmpStatus();
        return SUCCESS;
    }

    // To delete particular EmployeeStatus data
    @SkipValidation
    public String deleteEmployeeStatus() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empStatus.setUpdatedBy(oEmp);

        // To check that the Country is being used in the Client.If Yes then we
        // are not allowing the Country to get delete.
        empList = empStatusService.checkEmployeeStatusInEmployee(empStatus);
        if (!(empList.isEmpty())) {
            addActionError(getText("label.header.empStatus.msg.deleteEmployee"));
            return SUCCESS;
        }
        empStatusService.deleteEmployeeStatus(empStatus);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllEmpStatus();
        return SUCCESS;
    }

    public List<EmployeeStatusVO> getEmpStatusList() {
        return empStatusList;
    }

    public void setEmpStatusList(List<EmployeeStatusVO> empStatusList) {
        this.empStatusList = empStatusList;
    }

    public EmployeeStatusVO getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(EmployeeStatusVO empStatus) {
        this.empStatus = empStatus;
    }
}
