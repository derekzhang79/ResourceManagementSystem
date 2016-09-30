
package com.gits.rms.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.EmployeeExpensesDaoService;
import com.gits.rms.service.EmployeeExpensesService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesDetailsVO;
import com.gits.rms.vo.ExpensesTypeVO;
import com.gits.rms.vo.ProjectVO;

public class EmployeeExpensesAction extends ActionSupport {
    private static final long serialVersionUID = -4202449526682547297L;
    private EmployeeExpensesService empExpensesService = new EmployeeExpensesDaoService();
    private EmployeeExpensesVO empExpenses;
    private List<EmployeeExpensesVO> empExpensesList;
    private ExpensesDetailsVO expenseDetails;
    private CurrencyService currencyService = new CurrencyDaoService();
    private String currencyTypeValue;
    private ExpensesTypeVO expType;
    private ProjectVO proj;
    private HttpServletRequest request;

    @SkipValidation
    public String getAllEmpExpenses() {
        empExpensesList = empExpensesService.getAllEmployeeExpenses();
        return SUCCESS;
    }

    public String insertEmployeeExpensesDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        oEmp.getEmployeeId();
        request = ServletActionContext.getRequest();
        String resTotal = request.getParameter("totalAmt");
        new BigDecimal(resTotal);
        String resDescArray = request.getParameter("desArrayHide");
        String resNoteArray = request.getParameter("noteArrayHide");
        String resAmountArray = request.getParameter("amountArrayHide");
        String resExpdateArray = request.getParameter("expDateArrayHide");
        String resExpTypeArray = request.getParameter("expTypeArrayHide");
        StringTokenizer descStToken = null;
        StringTokenizer noteStToken = null;
        if ((resAmountArray != null) && (resAmountArray != "")) {
            descStToken = new StringTokenizer(resDescArray, ",");
        }
        if ((resNoteArray != null) && (resNoteArray != "")) {
            noteStToken = new StringTokenizer(resNoteArray, ",");
        }
        StringTokenizer amountStToken = new StringTokenizer(resAmountArray, ",");
        StringTokenizer expDateStToken = new StringTokenizer(resExpdateArray, ",");
        new StringTokenizer(resExpTypeArray, ",");
        expenseDetails = new ExpensesDetailsVO();
        expType = new ExpensesTypeVO();
        ArrayList empExpDetailsList = new ArrayList();
        if (!resNoteArray.equals(",")) {
            while (noteStToken.hasMoreTokens()) {
                String noteToken = noteStToken.nextToken();
                expenseDetails.setNote(noteToken);
            }
        }
        if (!resDescArray.equals(",")) {
            while (descStToken.hasMoreTokens()) {
                String desToken = descStToken.nextToken();
                expenseDetails.setDescription(desToken);
            }
        }
        while (amountStToken.hasMoreTokens() || expDateStToken.hasMoreTokens()) {
            String amountToken = amountStToken.nextToken();
            String expDateToken = expDateStToken.nextToken();
            expenseDetails.setExpensesDate(new Date(expDateToken));
            BigDecimal expenseAmountToken = new BigDecimal(amountToken);
            expenseDetails.setAmount((expenseAmountToken));
            empExpDetailsList.add(expenseDetails);
        }
        empExpenses.setHcmoExpensesId(1);
        expenseDetails.setHcmoExpensesId(empExpenses);
        empExpensesService.insertEmployeeExpenses(empExpenses);
        addActionMessage(getText("Added Successfully"));
        Iterator<ExpensesDetailsVO> expDetailsIterator = empExpDetailsList.iterator();
        while (expDetailsIterator.hasNext()) {
            ExpensesDetailsVO objExp = expDetailsIterator.next();
            expenseDetails.setExpensesDate(objExp.getExpensesDate());
            expenseDetails.setDescription(objExp.getDescription());
            expenseDetails.setAmount(objExp.getAmount());
            expenseDetails.setNote(objExp.getNote());
            expenseDetails.setHcmoExpensesId(empExpenses);
            expType.setHcmoExpensesTypeId(1);
            expenseDetails.setHcmoExpensesTypeId(expType);
            expenseDetails.setProjectId(proj);
            expenseDetails.setCreated(DateUtils.getCurrentDateTime());
            expenseDetails.setCreatedBy(oEmp);
            expenseDetails.setUpdatedBy(oEmp);
            expenseDetails.setIsActive(1);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateEmpExpenses() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        }
        return SUCCESS;
    }

    public EmployeeExpensesVO getEmpExpenses() {
        return empExpenses;
    }

    public EmployeeExpensesVO getNewExpenses() {
        return empExpenses;
    }

    public List<EmployeeExpensesVO> getEmpExpensesList() {
        return empExpensesList;
    }

    public void setEmpExpenses(List<EmployeeExpensesVO> empExpensesList) {
        this.empExpensesList = empExpensesList;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }
}