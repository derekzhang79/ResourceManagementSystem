
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmployeeStatusVO implements Serializable {

    private static final long serialVersionUID = 5734073209255675954L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer empStatusId;
    private int isActive;
    private String statusName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EmployeeStatusVO() {
    }

    public EmployeeStatusVO(Integer empStatusId, String statusName, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.empStatusId = empStatusId;
        this.statusName = statusName;
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

    public Integer getEmpStatusId() {
        return empStatusId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getStatusName() {
        return statusName;
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

    public void setEmpStatusId(Integer empStatusId) {
        this.empStatusId = empStatusId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
