
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimeSheetCategoryAssignVO implements Serializable {

    private static final long serialVersionUID = 2776217492904471013L;
    private int approve;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO employeeName;
    
   
    private Date enterDate;
    private Double enterTime;
    private int isActive;
    private int rejected;
    private int rework;
    private Integer timeSheetCategoryAssignId;
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

    public TimeSheetCategoryAssignVO() {
    }

    public TimeSheetCategoryAssignVO(int clientid,Integer timeSheetCategoryAssignId, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,
       
        EmployeesVO employeeName, TimesheetCategoryVO timesheetCategoryName, Date enterDate,
        Double enterTime, int rejected, int rework, int approve) {
        super();
        this.clientId=clientid;
        this.timeSheetCategoryAssignId = timeSheetCategoryAssignId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.employeeName = employeeName;
        this.timesheetCategoryName = timesheetCategoryName;
       
        this.enterDate = enterDate;
        this.enterTime = enterTime;
        this.rejected = rejected;
        this.rework = rework;
        this.approve = approve;
    }

    public int getApprove() {
       
        return approve;
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

       public int getIsActive() {
       
        return isActive;
    }

    public int getRejected() {
       
        return rejected;
    }

    public int getRework() {
        
        return rework;
    }

    public Integer getTimeSheetCategoryAssignId() {
       
        return timeSheetCategoryAssignId;
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

    public void setApprove(int approve) {
        this.approve = approve;
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

  
      public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public void setRework(int rework) {
        this.rework = rework;
    }

    public void setTimeSheetCategoryAssignId(Integer timeSheetCategoryAssignId) {
        this.timeSheetCategoryAssignId = timeSheetCategoryAssignId;
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

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterTime(Double enterTime) {
		this.enterTime = enterTime;
	}

	public Double getEnterTime() {
		return enterTime;
	}
}