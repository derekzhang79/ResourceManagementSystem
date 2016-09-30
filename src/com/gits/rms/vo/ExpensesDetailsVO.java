
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ExpensesDetailsVO implements Serializable {

    private static final long serialVersionUID = 2072707056352438933L;
    private BigDecimal amount;
    private Date created;
    private EmployeesVO createdBy;
    private String curTypeValueForAmountField;
    private String curTypeValueForSalaryField;
    private String description;
    private Date expensesDate;
    private Integer hcmoExpensesDetailsId;
    private EmployeeExpensesVO hcmoExpensesId;
    private ExpensesTypeVO hcmoExpensesTypeId;
    private int isActive;
    private String note;
    private ProjectVO projectId;
    private BigDecimal totAmount;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ExpensesDetailsVO() {
    }

    public ExpensesDetailsVO(int clientid,Integer hcmoExpensesDetailsId, ExpensesTypeVO hcmoExpensesTypeId,
        EmployeeExpensesVO hcmoExpensesId, ProjectVO projectId, Date expensesDate,
        BigDecimal amount, String note, String description, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive, BigDecimal totAmount,
        String curTypeValueForSalaryField, String curTypeValueForAmountField) {
        super();
        this.clientId=clientid;
        this.hcmoExpensesDetailsId = hcmoExpensesDetailsId;
        this.hcmoExpensesTypeId = hcmoExpensesTypeId;
        this.hcmoExpensesId = hcmoExpensesId;
        this.projectId = projectId;
        this.expensesDate = expensesDate;
        this.amount = amount;
        this.note = note;
        this.description = description;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.totAmount = totAmount;
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
    }

    public String getCurTypeValueForSalaryField() {
        return curTypeValueForSalaryField;
    }

    public String getDescription() {
        return description;
    }

    public Date getExpensesDate() {
        return expensesDate;
    }

    public Integer getHcmoExpensesDetailsId() {
        return hcmoExpensesDetailsId;
    }

    public EmployeeExpensesVO getHcmoExpensesId() {
        return hcmoExpensesId;
    }

    public ExpensesTypeVO getHcmoExpensesTypeId() {
        return hcmoExpensesTypeId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getNote() {
        return note;
    }

    public ProjectVO getProjectId() {
        return projectId;
    }

    public BigDecimal getTotAmount() {
        return totAmount;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public void setCurTypeValueForSalaryField(String curTypeValueForSalaryField) {
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpensesDate(Date expensesDate) {
        this.expensesDate = expensesDate;
    }

    public void setHcmoExpensesDetailsId(Integer hcmoExpensesDetailsId) {
        this.hcmoExpensesDetailsId = hcmoExpensesDetailsId;
    }

    public void setHcmoExpensesId(EmployeeExpensesVO hcmoExpensesId) {
        this.hcmoExpensesId = hcmoExpensesId;
    }

    public void setHcmoExpensesTypeId(ExpensesTypeVO hcmoExpensesTypeId) {
        this.hcmoExpensesTypeId = hcmoExpensesTypeId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setProjectId(ProjectVO projectId) {
        this.projectId = projectId;
    }

    public void setTotAmount(BigDecimal totAmount) {
        this.totAmount = totAmount;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}