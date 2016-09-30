
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ExpensesTypeVO implements Serializable {

    private static final long serialVersionUID = -3801080095796833498L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoExpensesTypeId;
    private int isActive;
    private String name;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public ExpensesTypeVO() {
    }

    public ExpensesTypeVO(Integer hcmoExpensesTyepId, String name, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {

        hcmoExpensesTypeId = hcmoExpensesTyepId;
        this.name = name;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.clientId = clientId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Integer getHcmoExpensesTypeId() {
        return hcmoExpensesTypeId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getName() {
        return name;
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

    public void setHcmoExpensesTypeId(Integer hcmoExpensesTypeId) {
        this.hcmoExpensesTypeId = hcmoExpensesTypeId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}