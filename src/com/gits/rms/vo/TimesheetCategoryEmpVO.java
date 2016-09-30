
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class TimesheetCategoryEmpVO implements Serializable {

    private static final long serialVersionUID = -5214994345555722597L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO employeeName;
    private Integer hcmoTimesheetCategoryEmpId;
    private int isActive;
    private TimesheetCategoryVO timesheetCategoryName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public TimesheetCategoryEmpVO() {
    }

    public TimesheetCategoryEmpVO(int clientid,Integer hcmoTimesheetCategoryEmpId, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,
        EmployeesVO employeeName, TimesheetCategoryVO timesheetCategoryName) {
        super();
        this.clientId=clientid;
        this.hcmoTimesheetCategoryEmpId = hcmoTimesheetCategoryEmpId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.employeeName = employeeName;
        this.timesheetCategoryName = timesheetCategoryName;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getEmployeeName() {
        return employeeName;
    }

    public Integer getHcmoTimesheetCategoryEmpId() {
        return hcmoTimesheetCategoryEmpId;
    }

    public int getIsActive() {
        return isActive;
    }

    public TimesheetCategoryVO getTimesheetCategoryName() {
        return timesheetCategoryName;
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

    public void setEmployeeName(EmployeesVO employeeName) {
        this.employeeName = employeeName;
    }

    public void setHcmoTimesheetCategoryEmpId(Integer hcmoTimesheetCategoryEmpId) {
        this.hcmoTimesheetCategoryEmpId = hcmoTimesheetCategoryEmpId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setTimesheetCategoryName(TimesheetCategoryVO timesheetCategoryName) {
        this.timesheetCategoryName = timesheetCategoryName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}