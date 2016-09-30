
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.ExpenseStatusTrackerDaoService;
import com.gits.rms.service.ExpenseStatusTrackerService;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsExpensesHistAction extends ActionSupport {

    private Collection<ExpenseStatusTrackerVO> expensesList;
    private ExpenseStatusTrackerService expensesService = new ExpenseStatusTrackerDaoService();
    private ExpenseStatusTrackerVO expStatus = new ExpenseStatusTrackerVO();
    private CurrencyService currencyService = new CurrencyDaoService();
    private ReportsVO report = new ReportsVO();

    private String reimbAmtFieldStringValue;
    private String reimbAmtFieldStringEmptyValue;
    private String curTypeValueForReimbAmtField;
    private String currencyTypeValue;
    private String totalAmtFieldStringValue;
    private String curTypeValueForTotalAmtField;

    @Override
    public String execute() throws Exception {

        // Normally we would provide a pre-compiled .jrxml file
        try {
            // Get All Expenses History Reports
            expensesList = expensesService.getExpensesHistReports(report);

            // //Add currency type value with Reimbursement Amount values like
            // ($) 6000.00
            if (!(expensesList.isEmpty())) {
                for (Iterator<ExpenseStatusTrackerVO> it = expensesList.iterator(); it.hasNext();) {
                    expStatus = it.next();
                    totalAmtFieldStringValue = expStatus.getHcmoExpensesId().getTotalAmount().toString();
                    currencyTypeValue = currencyService.getCurrencyType();
                    if (currencyTypeValue == null) {
                        currencyTypeValue = "";
                        curTypeValueForTotalAmtField = currencyTypeValue + "   "
                            + totalAmtFieldStringValue;
                        expStatus.getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
                        expStatus.setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
                    } else {
                        currencyTypeValue = "(" + currencyTypeValue + ")";
                        curTypeValueForTotalAmtField = currencyTypeValue + "   "
                            + totalAmtFieldStringValue;
                        expStatus.getHcmoExpensesId().setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
                        expStatus.setCurTypeValueForTotalAmountField(curTypeValueForTotalAmtField);
                    }
                    if (expStatus.getApprovalStatus().equals("ForApproval")) {
                        reimbAmtFieldStringValue = "0.00";
                        currencyTypeValue = currencyService.getCurrencyType();

                        if (currencyTypeValue == null) {
                            currencyTypeValue = "";
                            curTypeValueForReimbAmtField = currencyTypeValue + "   "
                                + reimbAmtFieldStringValue;
                            expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                        }
                        currencyTypeValue = "(" + currencyTypeValue + ")";
                        curTypeValueForReimbAmtField = currencyTypeValue + "   "
                            + reimbAmtFieldStringValue;
                        expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                    }
                    if ((expStatus.getApprovalStatus().equals("Approved"))) {
                        reimbAmtFieldStringValue = "0.00";
                        currencyTypeValue = currencyService.getCurrencyType();

                        if (currencyTypeValue == null) {
                            currencyTypeValue = "";
                            curTypeValueForReimbAmtField = currencyTypeValue + "   "
                                + reimbAmtFieldStringValue;
                            expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                        }
                        currencyTypeValue = "(" + currencyTypeValue + ")";
                        curTypeValueForReimbAmtField = currencyTypeValue + "   "
                            + reimbAmtFieldStringValue;
                        expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                    }
                    if (expStatus.getApprovalStatus().equals("Reimbursed")) {
                        reimbAmtFieldStringValue = expStatus.getHcmoExpensesId().getReimbursementAmount().toString();
                        currencyTypeValue = currencyService.getCurrencyType();

                        if (currencyTypeValue == null) {
                            currencyTypeValue = "";
                            curTypeValueForReimbAmtField = currencyTypeValue + "   "
                                + reimbAmtFieldStringValue;
                            expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                        }
                        currencyTypeValue = "(" + currencyTypeValue + ")";
                        curTypeValueForReimbAmtField = currencyTypeValue + "   "
                            + reimbAmtFieldStringValue + "0";
                        expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                    } else {
                        reimbAmtFieldStringValue = "0.00";
                        currencyTypeValue = currencyService.getCurrencyType();

                        if (currencyTypeValue == null) {
                            currencyTypeValue = "";
                            curTypeValueForReimbAmtField = currencyTypeValue + "   "
                                + reimbAmtFieldStringValue;
                            expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                        }
                        currencyTypeValue = "(" + currencyTypeValue + ")";
                        curTypeValueForReimbAmtField = currencyTypeValue + "   "
                            + reimbAmtFieldStringValue;
                        expStatus.setCurTypeValueForReImbAmountField(curTypeValueForReimbAmtField);
                    }
                }
            }
            new JRBeanCollectionDataSource(expensesList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/expensesReports/ExpensesReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/expensesReports/ExpensesReport.jasper");

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public Collection<ExpenseStatusTrackerVO> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(Collection<ExpenseStatusTrackerVO> expensesList) {
        this.expensesList = expensesList;
    }

    public ExpenseStatusTrackerService getExpensesService() {
        return expensesService;
    }

    public void setExpensesService(ExpenseStatusTrackerService expensesService) {
        this.expensesService = expensesService;
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public void setReimbAmtFieldStringValue(String reimbAmtFieldStringValue) {
        this.reimbAmtFieldStringValue = reimbAmtFieldStringValue;
    }

    public String getReimbAmtFieldStringValue() {
        return reimbAmtFieldStringValue;
    }

    public void setReimbAmtFieldStringEmptyValue(String reimbAmtFieldStringEmptyValue) {
        this.reimbAmtFieldStringEmptyValue = reimbAmtFieldStringEmptyValue;
    }

    public String getReimbAmtFieldStringEmptyValue() {
        return reimbAmtFieldStringEmptyValue;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurTypeValueForReimbAmtField(String curTypeValueForReimbAmtField) {
        this.curTypeValueForReimbAmtField = curTypeValueForReimbAmtField;
    }

    public String getCurTypeValueForReimbAmtField() {
        return curTypeValueForReimbAmtField;
    }

    public String getTotalAmtFieldStringValue() {
        return totalAmtFieldStringValue;
    }

    public void setTotalAmtFieldStringValue(String totalAmtFieldStringValue) {
        this.totalAmtFieldStringValue = totalAmtFieldStringValue;
    }

    public String getCurTypeValueForTotalAmtField() {
        return curTypeValueForTotalAmtField;
    }

    public void setCurTypeValueForTotalAmtField(String curTypeValueForTotalAmtField) {
        this.curTypeValueForTotalAmtField = curTypeValueForTotalAmtField;
    }

}
