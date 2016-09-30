
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SalaryGradeVO implements Serializable {

    private static final long serialVersionUID = -7032946546932797805L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoSalaryGradeId;
    private int isActive;
    private String salaryName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public SalaryGradeVO() {
    }

    public SalaryGradeVO(Integer hcmoSalaryGradeId, String salaryName, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.hcmoSalaryGradeId = hcmoSalaryGradeId;
        this.salaryName = salaryName;
        this.created = created;
        this.createdBy = createdBy;
        this.clientId=clientId;
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

    public Integer getHcmoSalaryGradeId() {
        return hcmoSalaryGradeId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getSalaryName() {
        return salaryName;
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

    public void setHcmoSalaryGradeId(Integer hcmoSalaryGradeId) {
        this.hcmoSalaryGradeId = hcmoSalaryGradeId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
