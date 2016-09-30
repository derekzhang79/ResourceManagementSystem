
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class OrganizationTypeVO implements Serializable {
    private static final long serialVersionUID = -7781029887278310763L;
    private Date created;
    private EmployeesVO createdBy;
    private String description;
    private Integer hcmoorgTypeId;
    private int isActive;
    private String orgTypeName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public OrganizationTypeVO() {
    }

    public OrganizationTypeVO(Integer hcmoorgTypeId, String orgTypeName, String description,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.hcmoorgTypeId = hcmoorgTypeId;
        this.orgTypeName = orgTypeName;
        this.description = description;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.clientId = clientId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHcmoorgTypeId() {
        return hcmoorgTypeId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHcmoorgTypeId(Integer hcmoorgTypeId) {
        this.hcmoorgTypeId = hcmoorgTypeId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
