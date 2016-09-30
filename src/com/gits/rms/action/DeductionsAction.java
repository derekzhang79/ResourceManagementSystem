
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.DeductionService;
import com.gits.rms.service.DeductionsDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.DeductionsVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.PayStubDeductionsVO;

public class DeductionsAction extends ActionSupport {
    private static final long serialVersionUID = 4401184268740484057L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private DeductionService deductionService = new DeductionsDaoService();
    private List<DeductionsVO> dedList;
    private List<PayStubDeductionsVO> payStubDeductionList;
    private DeductionsVO deduction;

    // To get List of EmployeeStatus
    @SkipValidation
    public String getAllDeductions() {
        dedList = deductionService.getAllDeductions();
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Employee it shows blank Form to enter New Data
    @SkipValidation
    public String setUpForInsertOrUpdateDeduction() {
        if ((deduction != null) && (deduction.getDeductionId() != null)) {
            deduction = deductionService.getDeduction(deduction.getDeductionId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new deduction or
    // update particular deduction Data
    public String insertOrUpdateDeduction() {
        try {
            if (deduction.getDeductionId() == null) {
                EmployeesVO oEmp = new EmployeesVO();
                Map session = ActionContext.getContext().getSession();
                oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                deduction.setCreated(DateUtils.getCurrentDateTime());
                deduction.setCreatedBy(oEmp);
                deduction.setUpdatedBy(oEmp);
                deduction.setIsActive(1);
                deductionService.insertDeduction(deduction);
                addActionMessage(getText("Added Successfully"));
            } else {
                EmployeesVO oEmp = new EmployeesVO();
                Map session = ActionContext.getContext().getSession();
                oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                deduction.setUpdatedBy(oEmp);
                deductionService.updateDeduction(deduction);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getErrorMoreFields(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllDeductions();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String deductionSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String deductionSearchResult() {
        dedList = deductionService.deductionSearchResult(deduction);
        return SUCCESS;
    }

    // To get Particular Deduction of an Employee
    @SkipValidation
    public String deductionView() {
        if ((deduction != null) && (deduction.getDeductionId() != null)) {
            deduction = deductionService.getDeduction(deduction.getDeductionId());
        }
        return SUCCESS;
    }

    // To delete particular EmployeeStatus data
    @SkipValidation
    public String deleteDeduction() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the Deduction which is being used in the Paystub
        // Deduction.If Yes then we are not allowing the Deduction to get
        // delete.
        payStubDeductionList = deductionService.checkDeductionInPaystubDeduction(deduction);
        if (!payStubDeductionList.isEmpty()) {
            addActionError(getText("label.header.deduction.msg.deletePaystubDeduction"));
            return SUCCESS;
        }

        deduction.setUpdatedBy(oEmp);
        deductionService.deleteDeduction(deduction);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllDeductions();
        return SUCCESS;
    }

    public List<DeductionsVO> getDedList() {
        return dedList;
    }

    public void setDedList(List<DeductionsVO> dedList) {
        this.dedList = dedList;
    }

    public DeductionService getDeductionService() {
        return deductionService;
    }

    public void setDeductionService(DeductionService deductionService) {
        this.deductionService = deductionService;
    }

    public DeductionsVO getDeduction() {
        return deduction;
    }

    public void setDeduction(DeductionsVO deduction) {
        this.deduction = deduction;
    }

}
