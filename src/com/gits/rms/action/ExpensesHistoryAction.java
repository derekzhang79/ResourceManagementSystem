
package com.gits.rms.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.ExpensesHistoryDaoService;
import com.gits.rms.service.ExpensesHistoryService;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesHistoryVO;

public class ExpensesHistoryAction extends ActionSupport {
    private static final long serialVersionUID = 343392448528185089L;
    private ExpensesHistoryService expHistService = new ExpensesHistoryDaoService();
    private List<EmployeeExpensesVO> expenseList;
    private List<EmployeeExpensesVO> expenseEmployeeList;
    private EmployeeExpensesVO empExpense = new EmployeeExpensesVO();
    public ExpensesHistoryVO expHist = new ExpensesHistoryVO();
    private List<ExpenseStatusTrackerVO> expenseStatusTrackerList;
    private List<ExpenseStatusTrackerVO> expenseStatusTrackerEmployeeList;
    private String amountFieldStringValue;
    private String curTypeValueForAmountField;
    private String reimbudseAmountFieldStringValue;
    private String curTypeValueForReimbuseAmountField;
    private CurrencyService currencyService = new CurrencyDaoService();
    private String currencyTypeValue;

    public String getExpOwnHistorySearchForm() {
        return SUCCESS;
    }

    // Return search result for login employee
    public String getExpOwnHistorySearchResult() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpense.setHcmoEmployeeId(oEmp);
        expenseList = expHistService.getExpOwnHistorySearchResult(expHist, empExpense);

        EmployeeExpensesVO newTest = null;

        for (Iterator<EmployeeExpensesVO> it = expenseList.iterator(); it.hasNext();) {
            newTest = it.next();
            newTest.setExpenseEmpName(newTest.getHcmoEmployeeId().getEmpFirstName());
            if ((newTest.getReimbursementAmount() == null)) {
                newTest.setReimbursementAmount(0.00);
            } else {
                newTest.setReimbursementAmount(newTest.getReimbursementAmount());
            }
        }
        // Add currency type value with Total Amount values like ($) 6000.00
        for (int i = 0; i < expenseList.size(); i++) {
            amountFieldStringValue = expenseList.get(i).getTotalAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            }
        }

        // Add currency type value with Reimbursement Amount values like ($)
        // 6000.00
        for (int i = 0; i < expenseList.size(); i++) {
            reimbudseAmountFieldStringValue = expenseList.get(i).getReimbursementAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForReimbuseAmountField = currencyTypeValue + "   "
                    + reimbudseAmountFieldStringValue;
                curTypeValueForReimbuseAmountField = curTypeValueForReimbuseAmountField + "0";
                expenseList.get(i).setCurTypeValueForReImbAmountField(curTypeValueForReimbuseAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForReimbuseAmountField = currencyTypeValue + "   "
                    + reimbudseAmountFieldStringValue;
                curTypeValueForReimbuseAmountField = curTypeValueForReimbuseAmountField + "0";
                expenseList.get(i).setCurTypeValueForReImbAmountField(curTypeValueForReimbuseAmountField);
            }
        }
        return SUCCESS;
    }

    // Return search details for single expenses
    public String getExpHistoryDetails() {
        expenseStatusTrackerList = expHistService.getExpHistoryDetails(empExpense.getHcmoExpensesId());
        return SUCCESS;
    }

    // Shows Search Result
    public String getExpEmployeeHistorySearchResult() {
        expenseEmployeeList = expHistService.getExpEmployeeHistorySearchResult(expHist, empExpense);
        EmployeeExpensesVO newTest = null;
        for (Iterator<EmployeeExpensesVO> it = expenseEmployeeList.iterator(); it.hasNext();) {
            newTest = it.next();
            newTest.setExpenseEmpName(newTest.getHcmoEmployeeId().getEmpFirstName());
            if ((newTest.getReimbursementAmount() == null)) {
                newTest.setReimbursementAmount(0.00);
            } else {
                newTest.setReimbursementAmount(newTest.getReimbursementAmount());
            }
        }

        // Add currency type value with Total Amount values like ($) 6000.00
        for (int i = 0; i < expenseEmployeeList.size(); i++) {
            amountFieldStringValue = expenseEmployeeList.get(i).getTotalAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseEmployeeList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseEmployeeList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            }
        }

        // Add currency type value with Reimbursement Amount values like ($)
        // 6000.00
        for (int i = 0; i < expenseEmployeeList.size(); i++) {
            reimbudseAmountFieldStringValue = expenseEmployeeList.get(i).getReimbursementAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForReimbuseAmountField = currencyTypeValue + "   "
                    + reimbudseAmountFieldStringValue;
                curTypeValueForReimbuseAmountField = curTypeValueForReimbuseAmountField + "0";
                expenseEmployeeList.get(i).setCurTypeValueForReImbAmountField(curTypeValueForReimbuseAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForReimbuseAmountField = currencyTypeValue + "   "
                    + reimbudseAmountFieldStringValue;
                curTypeValueForReimbuseAmountField = curTypeValueForReimbuseAmountField + "0";
                expenseEmployeeList.get(i).setCurTypeValueForReImbAmountField(curTypeValueForReimbuseAmountField);
            }
        }
        return SUCCESS;
    }

    public String getExpEmployeeHistoryDetails() {
        expenseStatusTrackerEmployeeList = expHistService.getExpEmployeeHistoryDetails(empExpense.getHcmoExpensesId());
        return SUCCESS;
    }

    public String getExpEmployeeHistorySearchForm() {
        return SUCCESS;
    }

    public List<EmployeeExpensesVO> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<EmployeeExpensesVO> expenseList) {
        this.expenseList = expenseList;
    }

    public ExpensesHistoryVO getExpHist() {
        return expHist;
    }

    public void setExpHist(ExpensesHistoryVO expHist) {
        this.expHist = expHist;
    }

    public EmployeeExpensesVO getEmpExpense() {
        return empExpense;
    }

    public void setEmpExpense(EmployeeExpensesVO empExpense) {
        this.empExpense = empExpense;
    }

    public List<ExpenseStatusTrackerVO> getExpenseStatusTrackerList() {
        return expenseStatusTrackerList;
    }

    public void setExpenseStatusTrackerList(List<ExpenseStatusTrackerVO> expenseStatusTrackerList) {
        this.expenseStatusTrackerList = expenseStatusTrackerList;
    }

    public List<EmployeeExpensesVO> getExpenseEmployeeList() {
        return expenseEmployeeList;
    }

    public void setExpenseEmployeeList(List<EmployeeExpensesVO> expenseEmployeeList) {
        this.expenseEmployeeList = expenseEmployeeList;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public List<ExpenseStatusTrackerVO> getExpenseStatusTrackerEmployeeList() {
        return expenseStatusTrackerEmployeeList;
    }

    public void setExpenseStatusTrackerEmployeeList(List<ExpenseStatusTrackerVO> expenseStatusTrackerEmployeeList) {
        this.expenseStatusTrackerEmployeeList = expenseStatusTrackerEmployeeList;
    }

    public void setAmountFieldStringValue(String amountFieldStringValue) {
        this.amountFieldStringValue = amountFieldStringValue;
    }

    public String getAmountFieldStringValue() {
        return amountFieldStringValue;
    }

    public void setReimbudseAmountFieldStringValue(String reimbudseAmountFieldStringValue) {
        this.reimbudseAmountFieldStringValue = reimbudseAmountFieldStringValue;
    }

    public String getReimbudseAmountFieldStringValue() {
        return reimbudseAmountFieldStringValue;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
    }

    public void setCurTypeValueForReimbuseAmountField(String curTypeValueForReimbuseAmountField) {
        this.curTypeValueForReimbuseAmountField = curTypeValueForReimbuseAmountField;
    }

    public String getCurTypeValueForReimbuseAmountField() {
        return curTypeValueForReimbuseAmountField;
    }

}
