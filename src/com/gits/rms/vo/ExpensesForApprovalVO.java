
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ExpensesForApprovalVO implements Serializable {

    private static final long serialVersionUID = -3401287846578393503L;
    private BigDecimal amount;
    private Date created;
    private EmployeesVO createdBy;
    private String description;
    private Integer hcmoExpensesDetailsId;
    private EmployeeExpensesVO hcmoExpensesId;
    private ExpensesTypeVO hcmoExpensesTypeId;
    private int isActive;
    private String note;
    private ProjectVO projectId;
    private Timestamp updated;
    private EmployeesVO updatedBy;

    public ExpensesForApprovalVO() {
    }

    public ExpensesForApprovalVO(Integer hcmoExpensesDetailsId, ExpensesTypeVO hcmoExpensesTypeId,
        ProjectVO projectId, EmployeeExpensesVO hcmoExpensesId, BigDecimal amount, String note,
        String description, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive) {
        super();
        this.hcmoExpensesDetailsId = hcmoExpensesDetailsId;
        this.hcmoExpensesTypeId = hcmoExpensesTypeId;
        this.projectId = projectId;
        this.hcmoExpensesId = hcmoExpensesId;
        this.amount = amount;
        this.note = note;
        this.description = description;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
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

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
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

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}