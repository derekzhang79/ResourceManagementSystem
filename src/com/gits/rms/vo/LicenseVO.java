
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class LicenseVO implements Serializable {

    private static final long serialVersionUID = 782585093666061650L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private Integer empLicenseId;
    private int isActive;
    private Date licenseDate;
    private String licenseDateValue;
    private String licenseDesc;
    private Date licenseEndDate;
    private String licenseNumber;
    private Date licenseRenewalDate;
    private String licenseRenewalDateValue;
    private Date licenseRenewalEndDate;
    private List message = new LinkedList();
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public LicenseVO() {
    }

    public LicenseVO(Integer empLicenseId, String licenseNumber, List message, Date licenseDate,
        Date licenseEndDate, String licenseDateValue, Date licenseRenewalDate,
        Date licenseRenewalEndDate, String licenseRenewalDateValue, String licenseDesc,
        EmployeesVO empIdObj, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.clientId=clientid;
        this.empLicenseId = empLicenseId;
        this.licenseNumber = licenseNumber;
        this.licenseDate = licenseDate;
        this.licenseEndDate = licenseEndDate;
        this.licenseDateValue = licenseDateValue;
        this.licenseRenewalDate = licenseRenewalDate;
        this.licenseRenewalEndDate = licenseRenewalEndDate;
        this.licenseRenewalDateValue = licenseRenewalDateValue;
        this.licenseDesc = licenseDesc;
        this.message = message;
        this.empIdObj = empIdObj;
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

    public Integer getEmpLicenseId() {
        return empLicenseId;
    }

    public int getIsActive() {
        return isActive;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public String getLicenseDateValue() {
        return licenseDateValue;
    }

    public String getLicenseDesc() {
        return licenseDesc;
    }

    public Date getLicenseEndDate() {
        return licenseEndDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Date getLicenseRenewalDate() {
        return licenseRenewalDate;
    }

    public String getLicenseRenewalDateValue() {
        return licenseRenewalDateValue;
    }

    public Date getLicenseRenewalEndDate() {
        return licenseRenewalEndDate;
    }

    public List getMessage() {
        return message;
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

    public void setEmpLicenseId(Integer empLicenseId) {
        this.empLicenseId = empLicenseId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public void setLicenseDateValue(String licenseDateValue) {
        this.licenseDateValue = licenseDateValue;
    }

    public void setLicenseDesc(String licenseDesc) {
        this.licenseDesc = licenseDesc;
    }

    public void setLicenseEndDate(Date licenseEndDate) {
        this.licenseEndDate = licenseEndDate;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setLicenseRenewalDate(Date licenseRenewalDate) {
        this.licenseRenewalDate = licenseRenewalDate;
    }

    public void setLicenseRenewalDateValue(String licenseRenewalDateValue) {
        this.licenseRenewalDateValue = licenseRenewalDateValue;
    }

    public void setLicenseRenewalEndDate(Date licenseRenewalEndDate) {
        this.licenseRenewalEndDate = licenseRenewalEndDate;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
