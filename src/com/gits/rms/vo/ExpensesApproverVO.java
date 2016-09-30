
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ExpensesApproverVO implements Serializable {

    private static final long serialVersionUID = -4567513697541841612L;
    private Integer approverId;
    private EmployeesVO approvingEmployeeId;
    private Date created;
    private EmployeesVO createdBy;
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

    public ExpensesApproverVO() {
    }

    public ExpensesApproverVO(int clientid,Integer approverId, EmployeesVO hcmoEmployeeId,
        EmployeesVO approvingEmployeeId, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive) {

        this.approverId = approverId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.clientId=clientid;
        this.approvingEmployeeId = approvingEmployeeId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public EmployeesVO getApprovingEmployeeId() {
        return approvingEmployeeId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
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

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public void setApprovingEmployeeId(EmployeesVO approvingEmployeeId) {
        this.approvingEmployeeId = approvingEmployeeId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
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