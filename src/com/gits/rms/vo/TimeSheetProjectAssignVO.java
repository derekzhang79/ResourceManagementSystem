
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimeSheetProjectAssignVO implements Serializable {

    private static final long serialVersionUID = 6878056819749370023L;
    private int approve;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO employeeName;
   
    private Date enterDate;
    private Double enterTime;
    private int isActive;
    private ProjectActivityVO projectActivity;
    private ProjectVO projectName;
    private int rejected;
    private int rework;
    private Integer timeSheetProjectAssignId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private Double totalEnteredHours;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public TimeSheetProjectAssignVO() {
    }

    public TimeSheetProjectAssignVO(Integer timeSheetProjectAssignId, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,
        EmployeesVO employeeName, ProjectVO projectName, ProjectActivityVO projectActivity,
        Date enterDate, Double enterTime, int rejected, int rework, int clientid,int approve,Double totalEnteredHours) {
        super();
        this.timeSheetProjectAssignId = timeSheetProjectAssignId;
        this.clientId=clientid;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.projectActivity = projectActivity;
       
        this.enterDate = enterDate;
        this.enterTime = enterTime;
        this.rejected = rejected;
        this.rework = rework;
        this.approve = approve;
        this.totalEnteredHours = totalEnteredHours;
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

    public ProjectActivityVO getProjectActivity() {
        return projectActivity;
    }

    public ProjectVO getProjectName() {
        return projectName;
    }

    public int getRejected() {
        return rejected;
    }

    public int getRework() {
        return rework;
    }

    public Integer getTimeSheetProjectAssignId() {
        return timeSheetProjectAssignId;
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

    public void setProjectActivity(ProjectActivityVO projectActivity) {
        this.projectActivity = projectActivity;
    }

    public void setProjectName(ProjectVO projectName) {
        this.projectName = projectName;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public void setRework(int rework) {
        this.rework = rework;
    }

    public void setTimeSheetProjectAssignId(Integer timeSheetProjectAssignId) {
        this.timeSheetProjectAssignId = timeSheetProjectAssignId;
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

	public Double getTotalEnteredHours() {
		return totalEnteredHours;
	}

	public void setTotalEnteredHours(Double totalEnteredHours) {
		this.totalEnteredHours = totalEnteredHours;
	}
}