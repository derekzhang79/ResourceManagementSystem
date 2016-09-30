
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TeamVO implements Serializable {

    private static final long serialVersionUID = -3801239360626288162L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoTeamId;
    private int isActive;
    private String teamName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public TeamVO() {
    }

    public TeamVO(Integer hcmoTeamId, String teamName, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.hcmoTeamId = hcmoTeamId;
        this.teamName = teamName;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.clientId=clientId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Integer getHcmoTeamId() {
        return hcmoTeamId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getTeamName() {
        return teamName;
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

    public void setHcmoTeamId(Integer hcmoTeamId) {
        this.hcmoTeamId = hcmoTeamId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
