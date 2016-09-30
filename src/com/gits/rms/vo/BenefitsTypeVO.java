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
public class BenefitsTypeVO implements Serializable {

	private static final long serialVersionUID = -2868356319897735245L;
    private Integer hcmoBenefitsTypeId;
	private String benefitsType;
    private int clientId;
    private int isActive;
    private Date created;
    private EmployeesVO createdBy;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    
	public Integer getHcmoBenefitsTypeId() {
		return hcmoBenefitsTypeId;
	}
	public void setHcmoBenefitsTypeId(Integer hcmoBenefitsTypeId) {
		this.hcmoBenefitsTypeId = hcmoBenefitsTypeId;
	}
	public String getBenefitsType() {
		return benefitsType;
	}
	public void setBenefitsType(String benefitsType) {
		this.benefitsType = benefitsType;
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
	public BenefitsTypeVO() {
		// TODO Auto-generated constructor stub
	}

	public BenefitsTypeVO(Integer hcmoBenefitsTypeId, String benefitsType, int clientId, int isActive, Date created,
			EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy) {
		super();
		this.hcmoBenefitsTypeId = hcmoBenefitsTypeId;
		this.benefitsType = benefitsType;
		this.clientId = clientId;
		this.isActive = isActive;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
	}
	 
}
