
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmployeeReportToVO implements Serializable {

    private static final long serialVersionUID = -5045802148034793641L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private Integer empReportToId;
    private int empRepReportingMode;
    private String empRepReportingModeValue;
    private int isActive;
    private EmployeesVO supEmpNumber;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EmployeeReportToVO() {
    }

    public EmployeeReportToVO(int clientid,Integer empReportToId, EmployeesVO empIdObj,
        EmployeesVO supEmpNumber, int empRepReportingMode, String empRepReportingModeValue,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.empReportToId = empReportToId;
        this.empIdObj = empIdObj;
        this.clientId=clientid;
        this.supEmpNumber = supEmpNumber;
        this.empRepReportingMode = empRepReportingMode;
        this.empRepReportingModeValue = empRepReportingModeValue;
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

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public Integer getEmpReportToId() {
        return empReportToId;
    }

    public int getEmpRepReportingMode() {
        return empRepReportingMode;
    }

    public String getEmpRepReportingModeValue() {
        return empRepReportingModeValue;
    }

    public int getIsActive() {
        return isActive;
    }

    public EmployeesVO getSupEmpNumber() {
        return supEmpNumber;
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

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setEmpReportToId(Integer empReportToId) {
        this.empReportToId = empReportToId;
    }

    public void setEmpRepReportingMode(int empRepReportingMode) {
        this.empRepReportingMode = empRepReportingMode;
    }

    public void setEmpRepReportingModeValue(String empRepReportingModeValue) {
        this.empRepReportingModeValue = empRepReportingModeValue;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setSupEmpNumber(EmployeesVO supEmpNumber) {
        this.supEmpNumber = supEmpNumber;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
