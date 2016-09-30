/**
 * 
 */
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Parveen
 *
 */
public class EmployeeEEOVO implements Serializable {
	private static final long serialVersionUID = -7046417977861482147L;
	private Integer hcmoEEOId;
	private String maritalStatus;
	private int dependents;
	private String dependDetail;
	private String veteran;
	private String militaryStatus;
	private String disability;
	private String emergenConName;
	private int emergenPhNo;
	private Date created;
	private EmployeesVO createdBy;
	private EmployeesVO employee;
	private EthnicRaceVO ethnicRaceIdObj;
	/*
	 * private DocumentVO ethnicDocumentIdObj; private DocumentVO
	 * veteranDocumentIdObj; private DocumentVO militaryDocumentIdObj; private
	 * DocumentVO disabilityDocumentIdObj;
	 */
	private Timestamp updated;
	private EmployeesVO updatedBy;
	private int isActive;
	private int clientId;

	private Integer ethnicDocumentId;
	private Integer veteranDocumentId;
	private Integer militaryDocumentId;
	private Integer disabilityDocumentId;

	/*
	 * public DocumentVO getEthnicDocumentIdObj() { return ethnicDocumentIdObj;
	 * } public void setEthnicDocumentIdObj(DocumentVO ethnicDocumentIdObj) {
	 * this.ethnicDocumentIdObj = ethnicDocumentIdObj; } public DocumentVO
	 * getVeteranDocumentIdObj() { return veteranDocumentIdObj; } public void
	 * setVeteranDocumentIdObj(DocumentVO veteranDocumentIdObj) {
	 * this.veteranDocumentIdObj = veteranDocumentIdObj; } public DocumentVO
	 * getMilitaryDocumentIdObj() { return militaryDocumentIdObj; } public void
	 * setMilitaryDocumentIdObj(DocumentVO militaryDocumentIdObj) {
	 * this.militaryDocumentIdObj = militaryDocumentIdObj; } public DocumentVO
	 * getDisabilityDocumentIdObj() { return disabilityDocumentIdObj; } public
	 * void setDisabilityDocumentIdObj(DocumentVO disabilityDocumentIdObj) {
	 * this.disabilityDocumentIdObj = disabilityDocumentIdObj; }
	 */
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public EthnicRaceVO getEthnicRaceIdObj() {
		return ethnicRaceIdObj;
	}

	public void setEthnicRaceIdObj(EthnicRaceVO ethnicRaceIdObj) {
		this.ethnicRaceIdObj = ethnicRaceIdObj;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getDependents() {
		return dependents;
	}

	public void setDependents(int dependents) {
		this.dependents = dependents;
	}

	public String getDependDetail() {
		return dependDetail;
	}

	public void setDependDetail(String dependDetail) {
		this.dependDetail = dependDetail;
	}

	public String getVeteran() {
		return veteran;
	}

	public void setVeteran(String veteran) {
		this.veteran = veteran;
	}

	public String getMilitaryStatus() {
		return militaryStatus;
	}

	public void setMilitaryStatus(String militaryStatus) {
		this.militaryStatus = militaryStatus;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getEmergenConName() {
		return emergenConName;
	}

	public void setEmergenConName(String emergenConName) {
		this.emergenConName = emergenConName;
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

	public EmployeesVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeesVO employee) {
		this.employee = employee;
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

	public int getEmergenPhNo() {
		return emergenPhNo;
	}

	public void setEmergenPhNo(int emergenPhNo) {
		this.emergenPhNo = emergenPhNo;
	}

	public Integer getHcmoEEOId() {
		return hcmoEEOId;
	}

	public void setHcmoEEOId(Integer hcmoEEOId) {
		this.hcmoEEOId = hcmoEEOId;
	}

	public EmployeeEEOVO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEthnicDocumentId() {
		return ethnicDocumentId;
	}

	public void setEthnicDocumentId(Integer ethnicDocumentId) {
		this.ethnicDocumentId = ethnicDocumentId;
	}

	public Integer getVeteranDocumentId() {
		return veteranDocumentId;
	}

	public void setVeteranDocumentId(Integer veteranDocumentId) {
		this.veteranDocumentId = veteranDocumentId;
	}

	public Integer getMilitaryDocumentId() {
		return militaryDocumentId;
	}

	public void setMilitaryDocumentId(Integer militaryDocumentId) {
		this.militaryDocumentId = militaryDocumentId;
	}

	public Integer getDisabilityDocumentId() {
		return disabilityDocumentId;
	}

	public void setDisabilityDocumentId(Integer disabilityDocumentId) {
		this.disabilityDocumentId = disabilityDocumentId;
	}

	public EmployeeEEOVO(Integer hcmoEEOId, String maritalStatus, int dependents, String dependDetail, String veteran,
			String militaryStatus, String disability, String emergenConName, int emergenPhNo, Date created,
			EmployeesVO createdBy, EmployeesVO employee, EthnicRaceVO ethnicRaceIdObj, DocumentVO ethnicDocumentIdObj,
			DocumentVO veteranDocumentIdObj, DocumentVO militaryDocumentIdObj, DocumentVO disabilityDocumentIdObj,
			Timestamp updated, EmployeesVO updatedBy, int isActive, int clientId) {
		super();
		this.hcmoEEOId = hcmoEEOId;
		this.maritalStatus = maritalStatus;
		this.dependents = dependents;
		this.dependDetail = dependDetail;
		this.veteran = veteran;
		this.militaryStatus = militaryStatus;
		this.disability = disability;
		this.emergenConName = emergenConName;
		this.emergenPhNo = emergenPhNo;
		this.created = created;
		this.createdBy = createdBy;
		this.employee = employee;
		this.ethnicRaceIdObj = ethnicRaceIdObj;
		/*
		 * this.ethnicDocumentIdObj = ethnicDocumentIdObj;
		 * this.veteranDocumentIdObj = veteranDocumentIdObj;
		 * this.militaryDocumentIdObj = militaryDocumentIdObj;
		 * this.disabilityDocumentIdObj = disabilityDocumentIdObj;
		 */
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.clientId = clientId;
	}

}
