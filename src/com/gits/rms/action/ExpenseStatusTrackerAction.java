
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.ExpenseStatusTrackerDaoService;
import com.gits.rms.service.ExpenseStatusTrackerService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public class ExpenseStatusTrackerAction extends ActionSupport {
	private static final long serialVersionUID = -4692434158253899486L;
	private ExpenseStatusTrackerService expStatusTrackerService = new ExpenseStatusTrackerDaoService();
    private ExpenseStatusTrackerVO expStatusTracker;
    private List<ExpenseStatusTrackerVO> expStatusTrackerList;

    @SkipValidation
    public String getAllExpStatusTracker() {
        expStatusTrackerList = expStatusTrackerService.getAllExpenseStatusTracker();
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateExpStatusTracker() {
        if (expStatusTracker != null && expStatusTracker.getHcmoExpensesStatusTrackerId() != null) {
            expStatusTracker = expStatusTrackerService.getExpenseStatusTracker(expStatusTracker.getHcmoExpensesStatusTrackerId());
        }
        return SUCCESS;
    }

    public String insertOrUpdateExpStatusTracker() {
        if (expStatusTracker.getHcmoExpensesStatusTrackerId() == null) {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expStatusTracker.setCreated(DateUtils.getCurrentDateTime());
            expStatusTracker.setCreatedBy(oEmp);
            expStatusTracker.setUpdatedBy(oEmp);
            expStatusTrackerService.insertExpenseStatusTracker(expStatusTracker);
        } else {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expStatusTracker.setUpdatedBy(oEmp);
            expStatusTrackerService.updateExpenseStatusTracker(expStatusTracker);
        }
        return SUCCESS;
    }

    public ExpenseStatusTrackerVO getExpStatusTracker() {
        return expStatusTracker;
    }

    public void setExpStatusTracker(ExpenseStatusTrackerVO expStatusTracker) {
        this.expStatusTracker = expStatusTracker;
    }

    public List<ExpenseStatusTrackerVO> getExpStatusTrackerList() {
        return expStatusTrackerList;
    }

    public void setExpStatusTracker(List<ExpenseStatusTrackerVO> expStatusTrackerList) {
        this.expStatusTrackerList = expStatusTrackerList;
    }
}