
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmployeeExpenseDetailsVO implements Serializable {
    private static final long serialVersionUID = 3901063491457164736L;
    private double amount;
    private Date created;
    private EmployeesVO createdBy;
    private String description;
    private Date expensesDate;
    private Integer hcmoExpensesDetailsId;
    private EmployeeExpensesVO hcmoExpensesId;
    private ExpensesTypeVO hcmoExpensesTypeId;
    private int isActive;
    private String note;
    private ProjectVO projectId;
    private Timestamp updated;
    private EmployeesVO updatedBy;

    public EmployeeExpenseDetailsVO() {
        super();
    }

    public EmployeeExpenseDetailsVO(Integer hcmoExpensesDetailsId, ProjectVO projectId,
        ExpensesTypeVO hcmoExpensesTypeId, EmployeeExpensesVO hcmoExpensesId, double amount,
        String note, String description, Date expensesDate, Date created, Timestamp updated,
        int isActive, EmployeesVO updatedBy, EmployeesVO createdBy) {
        super();
        this.hcmoExpensesDetailsId = hcmoExpensesDetailsId;
        this.projectId = projectId;
        this.hcmoExpensesTypeId = hcmoExpensesTypeId;
        this.hcmoExpensesId = hcmoExpensesId;
        this.amount = amount;
        this.note = note;
        this.description = description;
        this.expensesDate = expensesDate;
        this.created = created;
        this.updated = updated;
        this.isActive = isActive;
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
    }

    public double getAmount() {
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

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAmount(double amount) {
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

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
