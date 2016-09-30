package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class GoalVO implements Serializable{
	private static final long serialVersionUID = 5341512490335205628L;
	private Integer hcmoGoalId;
	private String goalName;
	private Date created;
	private EmployeesVO createdBy;
	private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    
	public GoalVO() {
		// TODO Auto-generated constructor stub
	}

	public GoalVO(int clientid,Integer hcmoGoalId, String goalName, Date created,
			EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
			int isActive) {
		this.clientId=clientid;
		this.hcmoGoalId = hcmoGoalId;
		this.goalName = goalName;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
	}

	public Integer getHcmoGoalId() {
		return hcmoGoalId;
	}

	public void setHcmoGoalId(Integer hcmoGoalId) {
		this.hcmoGoalId = hcmoGoalId;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
}
