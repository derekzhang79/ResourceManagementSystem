
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimesheetAssignProjectTargetVO implements Serializable {

    private static final long serialVersionUID = 94093382370013155L;
    
    private Integer hcmoTsAssignProjTargetId;
    private ProjectAssignEmpVO proAssignObj;
    private TargetsVO targetObj;
    private EmployeesVO employeeObj;
    
    private String targetAchieved;
    private String targetNotes;
    
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private Date created;
    private EmployeesVO createdBy;
    private int isActive;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public TimesheetAssignProjectTargetVO() {
   
    }

	public TimesheetAssignProjectTargetVO(int clientid,Integer hcmoTsAssignProjTargetId,
			ProjectAssignEmpVO proAssignObj, TargetsVO targetObj,
			EmployeesVO employeeObj, String targetAchieved, String targetNotes,
			Timestamp updated, EmployeesVO updatedBy, Date created,
			EmployeesVO createdBy, int isActive) {
		super();
		this.clientId=clientid;
		this.hcmoTsAssignProjTargetId = hcmoTsAssignProjTargetId;
		this.proAssignObj = proAssignObj;
		this.targetObj = targetObj;
		this.employeeObj = employeeObj;
		this.targetAchieved = targetAchieved;
		this.targetNotes = targetNotes;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.created = created;
		this.createdBy = createdBy;
		this.isActive = isActive;
	}

	public Integer getHcmoTsAssignProjTargetId() {
		return hcmoTsAssignProjTargetId;
	}

	public void setHcmoTsAssignProjTargetId(Integer hcmoTsAssignProjTargetId) {
		this.hcmoTsAssignProjTargetId = hcmoTsAssignProjTargetId;
	}

	public ProjectAssignEmpVO getProAssignObj() {
		return proAssignObj;
	}

	public void setProAssignObj(ProjectAssignEmpVO proAssignObj) {
		this.proAssignObj = proAssignObj;
	}

	public TargetsVO getTargetObj() {
		return targetObj;
	}

	public void setTargetObj(TargetsVO targetObj) {
		this.targetObj = targetObj;
	}

	public EmployeesVO getEmployeeObj() {
		return employeeObj;
	}

	public void setEmployeeObj(EmployeesVO employeeObj) {
		this.employeeObj = employeeObj;
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
}
