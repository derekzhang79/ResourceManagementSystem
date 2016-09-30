
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class LeaveApproverVO implements Serializable {

    private static final long serialVersionUID = 4541575126496124550L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO hcmoApprovingEmpId;
    private EmployeesVO hcmoEmployeeId;
    private Integer hcmoLeaveApproverId;
    private int isActive;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public LeaveApproverVO() {
    }

    public LeaveApproverVO(int clientid,Date created, EmployeesVO createdBy, EmployeesVO hcmoApprovingEmpId,
        EmployeesVO hcmoEmployeeId, Integer hcmoLeaveApproverId, int isActive, Timestamp updated,
        EmployeesVO updatedBy) {
        super();
        this.created = created;
        this.clientId=clientid;
        this.createdBy = createdBy;
        this.hcmoApprovingEmpId = hcmoApprovingEmpId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
        this.isActive = isActive;
        this.updated = updated;
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getHcmoApprovingEmpId() {
        return hcmoApprovingEmpId;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
    }

    public Integer getHcmoLeaveApproverId() {
        return hcmoLeaveApproverId;
    }

    public int getIsActive() {
        return isActive;
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

    public void setHcmoApprovingEmpId(EmployeesVO hcmoApprovingEmpId) {
        this.hcmoApprovingEmpId = hcmoApprovingEmpId;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public void setHcmoLeaveApproverId(Integer hcmoLeaveApproverId) {
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}