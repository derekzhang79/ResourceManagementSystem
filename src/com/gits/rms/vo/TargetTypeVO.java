
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TargetTypeVO implements Serializable {

    private static final long serialVersionUID = 94093382370013155L;
    
    private Integer hcmoTargetTypeId;
    private String targetTypeName;
    
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
    public TargetTypeVO() {

    }

	public TargetTypeVO(int clientid,Integer hcmoTargetTypeId, String targetTypeName,
			Date created, EmployeesVO createdBy, Timestamp updated,
			EmployeesVO updatedBy, int isActive) {
		super();
		this.hcmoTargetTypeId = hcmoTargetTypeId;
		this.targetTypeName = targetTypeName;
		this.clientId=clientid;
		this.created = created;
		this.createdBy = createdBy;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
	}

	public Integer getHcmoTargetTypeId() {
		return hcmoTargetTypeId;
	}

	public void setHcmoTargetTypeId(Integer hcmoTargetTypeId) {
		this.hcmoTargetTypeId = hcmoTargetTypeId;
	}

	public String getTargetTypeName() {
		return targetTypeName;
	}

	public void setTargetTypeName(String targetTypeName) {
		this.targetTypeName = targetTypeName;
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
