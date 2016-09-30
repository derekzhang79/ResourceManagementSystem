package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TargetsVO implements Serializable{
	private static final long serialVersionUID = 3325921025927830640L;
	private Integer hcmoTargetId;
	private String targetName;
	private String targetMode;
	private String targetValue;
	private TargetTypeVO targetTypeObj;
	private Date created;
	private EmployeesVO createdBy;
	private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private ProjectVO projObj;
    private ProjectActivityVO projActivityObj;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    
	public TargetsVO() {
	
    }

	public TargetsVO(Integer hcmoTargetId, String targetName,
			String targetMode, String targetValue, TargetTypeVO targetTypeObj,
			Date created, EmployeesVO createdBy, Timestamp updated,
			EmployeesVO updatedBy, int isActive, ProjectVO projObj,
			ProjectActivityVO projActivityObj,int clientid) {
		super();
		this.hcmoTargetId = hcmoTargetId;
		this.clientId=clientid;
		this.targetName = targetName;
		this.targetMode = targetMode;
		this.targetValue = targetValue;
		this.targetTypeObj = targetTypeObj;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.projObj = projObj;
		this.projActivityObj = projActivityObj;
	}

	public Integer getHcmoTargetId() {
		return hcmoTargetId;
	}

	public void setHcmoTargetId(Integer hcmoTargetId) {
		this.hcmoTargetId = hcmoTargetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetMode() {
		return targetMode;
	}

	public void setTargetMode(String targetMode) {
		this.targetMode = targetMode;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public TargetTypeVO getTargetTypeObj() {
		return targetTypeObj;
	}

	public void setTargetTypeObj(TargetTypeVO targetTypeObj) {
		this.targetTypeObj = targetTypeObj;
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

	public ProjectVO getProjObj() {
		return projObj;
	}

	public void setProjObj(ProjectVO projObj) {
		this.projObj = projObj;
	}

	public ProjectActivityVO getProjActivityObj() {
		return projActivityObj;
	}

	public void setProjActivityObj(ProjectActivityVO projActivityObj) {
		this.projActivityObj = projActivityObj;
	}
}
