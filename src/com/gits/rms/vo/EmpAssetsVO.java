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
public class EmpAssetsVO implements Serializable {

	
	private static final long serialVersionUID = 7128598830138228744L;
	private Integer hcmoAssetId;
	private int srNo;
	private String assetName;
	private Date issuedDate;
	private Date releasedDate;
    private int clientId;
    private int isActive;
    private Date created; 
    private EmployeesVO createdBy;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private AssetsVO assetIdObj;
    private EmployeesVO empIdObj;
    
	public EmployeesVO getEmpIdObj() {
		return empIdObj;
	}
	public void setEmpIdObj(EmployeesVO empIdObj) {
		this.empIdObj = empIdObj;
	}
	public Integer getHcmoAssetId() {
		return hcmoAssetId;
	}
	public void setHcmoAssetId(Integer hcmoAssetId) {
		this.hcmoAssetId = hcmoAssetId;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Date getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
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
	public AssetsVO getAssetIdObj() {
		return assetIdObj;
	}
	public void setAssetIdObj(AssetsVO assetIdObj) {
		this.assetIdObj = assetIdObj;
	}
	public EmpAssetsVO(Integer hcmoAssetId, int srNo, String assetName, Date issuedDate, Date releasedDate,
			int clientId, int isActive, Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
			AssetsVO assetIdObj, EmployeesVO empIdObj) {
		super();
		this.hcmoAssetId = hcmoAssetId;
		this.srNo = srNo;
		this.assetName = assetName;
		this.issuedDate = issuedDate;
		this.releasedDate = releasedDate;
		this.clientId = clientId;
		this.isActive = isActive;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.assetIdObj = assetIdObj;
		this.empIdObj = empIdObj;
	}
	public EmpAssetsVO() {
		// TODO Auto-generated constructor stub
	}
    
}
