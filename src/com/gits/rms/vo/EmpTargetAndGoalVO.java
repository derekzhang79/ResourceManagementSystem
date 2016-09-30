package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmpTargetAndGoalVO implements Serializable{
	private static final long serialVersionUID = -7674921764974722401L;
	private Integer hcmoEmpTgId;
	private EmployeesVO employeeName;
	private ProjectVO projectName;
	private ProjectActivityVO projectActivityName;
	private String empTargetName;
	private String empTargetType;
	private String empTargetMode;
	private String empGoalName;
	private String empTargetAchieved;
	private String empTargetNotes;
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
    
	public EmpTargetAndGoalVO() {
		// TODO Auto-generated constructor stub
	}

	public EmpTargetAndGoalVO(Integer hcmoEmpTgId, EmployeesVO employeeName,
			ProjectVO projectName, ProjectActivityVO projectActivityName,
			String empTargetName, String empTargetType, String empTargetMode,
			String empGoalName, String empTargetAchieved,
			String empTargetNotes, Date created, EmployeesVO createdBy,
			Timestamp updated, EmployeesVO updatedBy, int isActive,int clientid) {
		this.hcmoEmpTgId = hcmoEmpTgId;
		this.clientId=clientid;
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.projectActivityName = projectActivityName;
		this.empTargetName = empTargetName;
		this.empTargetType = empTargetType;
		this.empTargetMode = empTargetMode;
		this.empGoalName = empGoalName;
		this.empTargetAchieved = empTargetAchieved;
		this.empTargetNotes = empTargetNotes;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
	}

	public Integer getHcmoEmpTgId() {
		return hcmoEmpTgId;
	}

	public void setHcmoEmpTgId(Integer hcmoEmpTgId) {
		this.hcmoEmpTgId = hcmoEmpTgId;
	}

	public EmployeesVO getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(EmployeesVO employeeName) {
		this.employeeName = employeeName;
	}

	public ProjectVO getProjectName() {
		return projectName;
	}

	public void setProjectName(ProjectVO projectName) {
		this.projectName = projectName;
	}

	public ProjectActivityVO getProjectActivityName() {
		return projectActivityName;
	}

	public void setProjectActivityName(ProjectActivityVO projectActivityName) {
		this.projectActivityName = projectActivityName;
	}

	public String getEmpTargetName() {
		return empTargetName;
	}

	public void setEmpTargetName(String empTargetName) {
		this.empTargetName = empTargetName;
	}

	public String getEmpTargetType() {
		return empTargetType;
	}

	public void setEmpTargetType(String empTargetType) {
		this.empTargetType = empTargetType;
	}

	public String getEmpTargetMode() {
		return empTargetMode;
	}

	public void setEmpTargetMode(String empTargetMode) {
		this.empTargetMode = empTargetMode;
	}

	public String getEmpGoalName() {
		return empGoalName;
	}

	public void setEmpGoalName(String empGoalName) {
		this.empGoalName = empGoalName;
	}

	public String getEmpTargetAchieved() {
		return empTargetAchieved;
	}

	public void setEmpTargetAchieved(String empTargetAchieved) {
		this.empTargetAchieved = empTargetAchieved;
	}

	public String getEmpTargetNotes() {
		return empTargetNotes;
	}

	public void setEmpTargetNotes(String empTargetNotes) {
		this.empTargetNotes = empTargetNotes;
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
