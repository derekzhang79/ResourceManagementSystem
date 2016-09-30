
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class OrganizationVO implements Serializable {
    private static final long serialVersionUID = 8963785380686409230L;
    private Date created;
    private EmployeesVO createdBy;
    private String description;
    private int isActive;
    private LocationVO location;
    private Integer orgId;
    private String orgName;
    private OrganizationTypeVO orgtype;
    private Integer parentOrgId;
    private String parentOrgValue;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public OrganizationVO() {
    }

    public OrganizationVO(int clientid,Integer orgId, Integer parentOrgId, String orgName, String description,
        LocationVO location, OrganizationTypeVO orgtype, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive, String parentOrgValue) {
        this.orgId = orgId;
        this.parentOrgId = parentOrgId;
        this.clientId=clientid;
        this.orgName = orgName;
        this.description = description;
        this.location = location;
        this.orgtype = orgtype;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.parentOrgValue = parentOrgValue;
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

    public int getIsActive() {
        return isActive;
    }

    public LocationVO getLocation() {
        return location;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public OrganizationTypeVO getOrgtype() {
        return orgtype;
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public String getParentOrgValue() {
        return parentOrgValue;
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLocation(LocationVO location) {
        this.location = location;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setOrgtype(OrganizationTypeVO orgtype) {
        this.orgtype = orgtype;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public void setParentOrgValue(String parentOrgValue) {
        this.parentOrgValue = parentOrgValue;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
