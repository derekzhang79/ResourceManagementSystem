
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ProjectVO implements Serializable {

    private static final long serialVersionUID = -114934216034446369L;
    private Date created;
    private EmployeesVO createdBy;
    private CustomerVO customerId;
    private EmployeesVO empIdObj;
    private int isActive;
    private String projectDesc;
    private Integer projectId;
    private String projectName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String estimatedHours;
    private String remainingHours;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ProjectVO() {
    	
    }

	public ProjectVO(int clientid,Date created, EmployeesVO createdBy,
			CustomerVO customerId, EmployeesVO empIdObj, int isActive,
			String projectDesc, Integer projectId, String projectName,
			Timestamp updated, EmployeesVO updatedBy, String estimatedHours,
			String remainingHours) {
		super();
		this.clientId=clientid;
		this.created = created;
		this.createdBy = createdBy;
		this.customerId = customerId;
		this.empIdObj = empIdObj;
		this.isActive = isActive;
		this.projectDesc = projectDesc;
		this.projectId = projectId;
		this.projectName = projectName;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.estimatedHours = estimatedHours;
		this.remainingHours = remainingHours;
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

	public CustomerVO getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerVO customerId) {
		this.customerId = customerId;
	}

	public EmployeesVO getEmpIdObj() {
		return empIdObj;
	}

	public void setEmpIdObj(EmployeesVO empIdObj) {
		this.empIdObj = empIdObj;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(String estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public String getRemainingHours() {
		return remainingHours;
	}

	public void setRemainingHours(String remainingHours) {
		this.remainingHours = remainingHours;
	}
}
