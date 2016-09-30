
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class TimeSheetApproverVO implements Serializable {

    private static final long serialVersionUID = 7173513713069060549L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoApproverId;
    private EmployeesVO hcmoApprovingEmpId;
    private EmployeesVO hcmoEmployeeId;
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
    public TimeSheetApproverVO() {
    }

    public TimeSheetApproverVO(Integer hcmoApproverId, EmployeesVO hcmoEmployeeId,
        EmployeesVO hcmoApprovingEmpId, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.hcmoApproverId = hcmoApproverId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.clientId=clientid;
        this.hcmoApprovingEmpId = hcmoApprovingEmpId;
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

    public Integer getHcmoApproverId() {
        return hcmoApproverId;
    }

    public EmployeesVO getHcmoApprovingEmpId() {
        return hcmoApprovingEmpId;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
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

    public void setHcmoApproverId(Integer hcmoApproverId) {
        this.hcmoApproverId = hcmoApproverId;
    }

    public void setHcmoApprovingEmpId(EmployeesVO hcmoApprovingEmpId) {
        this.hcmoApprovingEmpId = hcmoApprovingEmpId;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
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