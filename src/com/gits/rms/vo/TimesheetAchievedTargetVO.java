
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimesheetAchievedTargetVO implements Serializable {

    private static final long serialVersionUID = 94093382370013155L;
    private Integer hcmoTsAchivedTargetId;
    private Date date;
    private Date startDate;
    private Date endDate;
    private String status;
    private String targetAchieved;
    private String targetNotes;
    private TimesheetAssignProjectTargetVO hcmoTsAssignedTargetId;
    
    private Date created;
    private EmployeesVO createdBy;
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
    public TimesheetAchievedTargetVO() {
    	
    }

	public TimesheetAchievedTargetVO(int clientid,Integer hcmoTsAchivedTargetId, Date date,
			Date startDate, Date endDate, String status, String targetAchieved,
			String targetNotes,
			TimesheetAssignProjectTargetVO hcmoTsAssignedTargetId,
			Date created, EmployeesVO createdBy, int isActive,
			Timestamp updated, EmployeesVO updatedBy) {
		super();
		this.clientId=clientid;
		this.hcmoTsAchivedTargetId = hcmoTsAchivedTargetId;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.targetAchieved = targetAchieved;
		this.targetNotes = targetNotes;
		this.hcmoTsAssignedTargetId = hcmoTsAssignedTargetId;
		this.created = created;
		this.createdBy = createdBy;
		this.isActive = isActive;
		this.updated = updated;
		this.updatedBy = updatedBy;
	}

	public Integer getHcmoTsAchivedTargetId() {
		return hcmoTsAchivedTargetId;
	}

	public void setHcmoTsAchivedTargetId(Integer hcmoTsAchivedTargetId) {
		this.hcmoTsAchivedTargetId = hcmoTsAchivedTargetId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTargetAchieved() {
		return targetAchieved;
	}

	public void setTargetAchieved(String targetAchieved) {
		this.targetAchieved = targetAchieved;
	}

	public String getTargetNotes() {
		return targetNotes;
	}

	public void setTargetNotes(String targetNotes) {
		this.targetNotes = targetNotes;
	}

	public TimesheetAssignProjectTargetVO getHcmoTsAssignedTargetId() {
		return hcmoTsAssignedTargetId;
	}

	public void setHcmoTsAssignedTargetId(
			TimesheetAssignProjectTargetVO hcmoTsAssignedTargetId) {
		this.hcmoTsAssignedTargetId = hcmoTsAssignedTargetId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public EmployeesVO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EmployeesVO createdBy) {
		this.createdBy = createdBy;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public EmployeesVO getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(EmployeesVO updatedBy) {
		this.updatedBy = updatedBy;
	}

}
