
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ExpensesAccountantApproverVO implements Serializable {

    private static final long serialVersionUID = 139267563483806851L;
    private Date created;
    private EmployeesVO createdBy;
    private String empFirstName;
    private EmployeesVO expensesAccountantId;
    private Integer hcmoExpensesAccountantId;
    private int isActive;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ExpensesAccountantApproverVO() {
    }

    public ExpensesAccountantApproverVO(int clientid,Integer hcmoExpensesAccountantId,
        EmployeesVO expensesAccountantId, Date created, String expEmpFirstName,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,
        String empFirstName) {
    	 super();
    	 this.clientId=clientid;
        this.hcmoExpensesAccountantId = hcmoExpensesAccountantId;
        this.expensesAccountantId = expensesAccountantId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.empFirstName = empFirstName;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public EmployeesVO getExpensesAccountantId() {
        return expensesAccountantId;
    }

    public Integer getHcmoExpensesAccountantId() {
        return hcmoExpensesAccountantId;
    }

    public int getIsActive() {
        return isActive;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public void setExpensesAccountantId(EmployeesVO expensesAccountantId) {
        this.expensesAccountantId = expensesAccountantId;
    }

    public void setHcmoExpensesAccountantId(Integer hcmoExpensesAccountantId) {
        this.hcmoExpensesAccountantId = hcmoExpensesAccountantId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}