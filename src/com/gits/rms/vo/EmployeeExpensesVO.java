
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class EmployeeExpensesVO implements Serializable {

    private static final long serialVersionUID = -4222232124701218738L;
    private String accountingNotes;
    private Date created;
    private EmployeesVO createdBy;
    private Date createdDate;
    private String curTypeValueForReImbAmountEmptyValue;
    private String curTypeValueForReImbAmountField;
    private String curTypeValueForTotalAmountField;
    private String expAttachFileName;
    private String expAttachId;
    private String expenseEmpName;
    private Date expenseFromDate;
    private String expenseName;
    private Date expenseToDate;
    private EmployeesVO hcmoAccountantId;
    private EmployeesVO hcmoEmployeeId;
    private Integer hcmoExpensesId;
    private int isActive;
    private int nextLevelId;
    private String receipt;
    private String reImbAmtFieldStringValue;
    private Double reimbursementAmount;
    private Date reimbursementDate;
    private String status;
    private BigDecimal totalAmount;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public EmployeeExpensesVO() {
    }

    public EmployeeExpensesVO(int clientid,Integer hcmoExpensesId, EmployeesVO hcmoEmployeeId,
        EmployeesVO hcmoAccountantId, int nextLevelId, String expAttachId, String status,
        Date createdDate, BigDecimal totalAmount, Double reimbursementAmount,
        String expAttachFileName, Date reimbursementDate, String accountingNotes, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,
        String expenseName, String receipt, Date expenseFromDate, Date expenseToDate,
        String expenseEmpName, String curTypeValueForReImbAmountField,
        String curTypeValueForReImbAmountEmptyValue, String curTypeValueForTotalAmountField,
        String reImbAmtFieldStringValue) {
        super();
        this.hcmoExpensesId = hcmoExpensesId;
        this.clientId=clientid;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.hcmoAccountantId = hcmoAccountantId;
        this.nextLevelId = nextLevelId;
        this.expAttachId = expAttachId;
        this.status = status;
        this.createdDate = createdDate;
        this.totalAmount = totalAmount;
        this.reimbursementAmount = reimbursementAmount;
        this.expAttachFileName = expAttachFileName;
        this.reimbursementDate = reimbursementDate;
        this.accountingNotes = accountingNotes;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.expenseName = expenseName;
        this.receipt = receipt;
        this.expenseFromDate = expenseFromDate;
        this.expenseToDate = expenseToDate;
        this.curTypeValueForReImbAmountField = curTypeValueForReImbAmountField;
        this.curTypeValueForTotalAmountField = curTypeValueForTotalAmountField;
        this.curTypeValueForReImbAmountEmptyValue = curTypeValueForReImbAmountEmptyValue;
        this.reImbAmtFieldStringValue = reImbAmtFieldStringValue;
        this.expenseEmpName = expenseEmpName;
    }

    public String getAccountingNotes() {
        return accountingNotes;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getCurTypeValueForReImbAmountEmptyValue() {
        return curTypeValueForReImbAmountEmptyValue;
    }

    public String getCurTypeValueForReImbAmountField() {
        return curTypeValueForReImbAmountField;
    }

    public String getCurTypeValueForTotalAmountField() {
        return curTypeValueForTotalAmountField;
    }

    public String getExpAttachFileName() {
        return expAttachFileName;
    }

    public String getExpAttachId() {
        return expAttachId;
    }

    public String getExpenseEmpName() {
        return expenseEmpName;
    }

    public Date getExpenseFromDate() {
        return expenseFromDate;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Date getExpenseToDate() {
        return expenseToDate;
    }

    public EmployeesVO getHcmoAccountantId() {
        return hcmoAccountantId;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
    }

    public Integer getHcmoExpensesId() {
        return hcmoExpensesId;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getNextLevelId() {
        return nextLevelId;
    }

    public String getReceipt() {
        return receipt;
    }

    public String getReImbAmtFieldStringValue() {
        return reImbAmtFieldStringValue;
    }

    public Double getReimbursementAmount() {
        return reimbursementAmount;
    }

    public Date getReimbursementDate() {
        return reimbursementDate;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAccountingNotes(String accountingNotes) {
        this.accountingNotes = accountingNotes;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCurTypeValueForReImbAmountEmptyValue(String curTypeValueForReImbAmountEmptyValue) {
        this.curTypeValueForReImbAmountEmptyValue = curTypeValueForReImbAmountEmptyValue;
    }

    public void setCurTypeValueForReImbAmountField(String curTypeValueForReImbAmountField) {
        this.curTypeValueForReImbAmountField = curTypeValueForReImbAmountField;
    }

    public void setCurTypeValueForTotalAmountField(String curTypeValueForTotalAmountField) {
        this.curTypeValueForTotalAmountField = curTypeValueForTotalAmountField;
    }

    public void setExpAttachFileName(String expAttachFileName) {
        this.expAttachFileName = expAttachFileName;
    }

    public void setExpAttachId(String expAttachId) {
        this.expAttachId = expAttachId;
    }

    public void setExpenseEmpName(String expenseEmpName) {
        this.expenseEmpName = expenseEmpName;
    }

    public void setExpenseFromDate(Date expenseFromDate) {
        this.expenseFromDate = expenseFromDate;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setExpenseToDate(Date expenseToDate) {
        this.expenseToDate = expenseToDate;
    }

    public void setHcmoAccountantId(EmployeesVO hcmoAccountantId) {
        this.hcmoAccountantId = hcmoAccountantId;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public void setHcmoExpensesId(Integer hcmoExpensesId) {
        this.hcmoExpensesId = hcmoExpensesId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setNextLevelId(int nextLevelId) {
        this.nextLevelId = nextLevelId;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public void setReImbAmtFieldStringValue(String reImbAmtFieldStringValue) {
        this.reImbAmtFieldStringValue = reImbAmtFieldStringValue;
    }

    public void setReimbursementAmount(Double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public void setReimbursementDate(Date reimbursementDate) {
        this.reimbursementDate = reimbursementDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}