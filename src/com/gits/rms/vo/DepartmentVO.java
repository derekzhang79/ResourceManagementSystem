
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class DepartmentVO implements Serializable {

    private static final long serialVersionUID = 7737189060324418966L;
    private Date created;
    private EmployeesVO createdBy;
    private String deptName;
    private Integer hcmoDepartmentId;
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

    public DepartmentVO() {
    }
    
    public DepartmentVO(Integer hcmoDepartmentId) {
    	this.hcmoDepartmentId = hcmoDepartmentId;
    }

    public DepartmentVO(Integer hcmoDepartmentId, String deptName, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.hcmoDepartmentId = hcmoDepartmentId;
        this.deptName = deptName;
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

    public String getDeptName() {
        return deptName;
    }

    public Integer getHcmoDepartmentId() {
        return hcmoDepartmentId;
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

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setHcmoDepartmentId(Integer hcmoDepartmentId) {
        this.hcmoDepartmentId = hcmoDepartmentId;
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
