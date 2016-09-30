/**
 * 
 */
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Parveen
 *
 */
public class DocumentVO implements Serializable{
    private static final long serialVersionUID = -7046417979861482147L;
    private String documentName;
    private String documentType;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO employee;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private int clientId;
    private Integer HcmoDocumentId;
    
    
	public DocumentVO() {
		
		// TODO Auto-generated constructor stub
	}
	public Integer getHcmoDocumentId() {
		return HcmoDocumentId;
	}
	public void setHcmoDocumentId(Integer HcmoDocumentId) {
		this.HcmoDocumentId = HcmoDocumentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
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
	public DocumentVO(String documentName, String documentType, Date created, EmployeesVO createdBy,
			EmployeesVO employee, Timestamp updated, EmployeesVO updatedBy, int isActive, int clientId,
			Integer HcmoDocumentId) {
		super();
		this.documentName = documentName;
		this.documentType = documentType;
		this.created = created;
		this.createdBy = createdBy;
		this.employee = employee;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.clientId = clientId;
		this.HcmoDocumentId = HcmoDocumentId;
	}
	
}
