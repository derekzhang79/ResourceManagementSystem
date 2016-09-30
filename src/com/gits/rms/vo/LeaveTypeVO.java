
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class LeaveTypeVO implements Serializable {
    private static final long serialVersionUID = -3645259582183681361L;
    private Date created;
    private EmployeesVO createdBy;
    private int isActive;
    private Integer leaveTypeId;
    private String leaveTypeName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public LeaveTypeVO() {
    }

    public LeaveTypeVO(int clientid,Integer leaveTypeId, String leaveTypeName, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.leaveTypeId = leaveTypeId;
        this.clientId=clientid;
        this.leaveTypeName = leaveTypeName;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
