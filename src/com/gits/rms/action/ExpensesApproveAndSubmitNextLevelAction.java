
package com.gits.rms.action;

import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.ExpensesApproveAndSubmitNextLevelDaoService;
import com.gits.rms.service.ExpensesApproveAndSubmitNextLevelService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public class ExpensesApproveAndSubmitNextLevelAction extends ActionSupport {
    private static final long serialVersionUID = 1776647237231485533L;
    private ExpensesApproveAndSubmitNextLevelService expApproveASNLService = new ExpensesApproveAndSubmitNextLevelDaoService();
    private EmployeeExpensesVO empExpenses = new EmployeeExpensesVO();
    private ExpenseStatusTrackerVO expenseStatusStracker = new ExpenseStatusTrackerVO();

    // Update Expenses for click
    // approve and submit to next
    @SkipValidation
    public String approveAndSubmitToNextLevel() {
        Map session = ActionContext.getContext().getSession();
        int nextLevelID = expenseStatusStracker.getNextLevelId().getEmployeeId();

        if (nextLevelID == 0) {
            addActionError(getText("errors.expenseStatusStracker.nextLevelId.employeeId"));
            return ERROR;
        }

        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.approved.value"));
        expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
        expenseStatusStracker.setCreatedBy(oEmp);
        expenseStatusStracker.setUpdatedBy(oEmp);
        expenseStatusStracker.setApproverId(oEmp);
        expenseStatusStracker.setHcmoExpensesId(empExpenses);
        empExpenses.setNextLevelId(expenseStatusStracker.getNextLevelId().getEmployeeId());
        empExpenses.setUpdatedBy(oEmp);
        expApproveASNLService.approveAndSubmitToNextLevel(expenseStatusStracker, empExpenses);
        addActionMessage((getText("select.common.expensesubmittonextlevel.value")));

        return SUCCESS;
    }

    public EmployeeExpensesVO getEmpExpenses() {
        return empExpenses;
    }

    public void setEmpExpenses(EmployeeExpensesVO empExpenses) {
        this.empExpenses = empExpenses;
    }

    public void setExpenseStatusStracker(ExpenseStatusTrackerVO expenseStatusStracker) {
        this.expenseStatusStracker = expenseStatusStracker;
    }

}
