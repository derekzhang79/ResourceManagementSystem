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
public class AssetsVO implements Serializable {

	
	private static final long serialVersionUID = -4880506862422072703L;
	private Integer assetTypeId;
	private String assetTypeName;
    private int clientId;  
    private int isActive;
    private Date created;
    private EmployeesVO createdBy;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private EmployeesVO empIdObj;
    
    
    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }
    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }
    
	public Integer getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(Integer assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	public AssetsVO() {
		// TODO Auto-generated constructor stub
	}
    
	public AssetsVO(Integer assetTypeId, String assetTypeName, int clientId, int isActive, Date created,
			EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy) {
		super();
		this.assetTypeId = assetTypeId;
		this.assetTypeName = assetTypeName;
		this.clientId = clientId;
		this.isActive = isActive;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.empIdObj = empIdObj;
	}
	
}
