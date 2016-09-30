
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class TimesheetCategoryVO implements Serializable {

    private static final long serialVersionUID = -4583694243228524110L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoTimesheetCategoryId;
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
    public TimesheetCategoryVO() {
    }

    public TimesheetCategoryVO(Integer hcmoTimesheetCategoryId, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive, String name,int clientId) {
        super();
        this.hcmoTimesheetCategoryId = hcmoTimesheetCategoryId;
        this.clientId=clientId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Integer getHcmoTimesheetCategoryId() {
        return hcmoTimesheetCategoryId;
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

    public void setHcmoTimesheetCategoryId(Integer hcmoTimesheetCategoryId) {
        this.hcmoTimesheetCategoryId = hcmoTimesheetCategoryId;
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