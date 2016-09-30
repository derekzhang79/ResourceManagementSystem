package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SaasContractVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Integer hcmoSaasContractId;
    private String saasName;
    private String saasType;
    private Integer saasSize;
    private String companyName;
    private String personName;
    private String designation;
    private Date saasSignDate;
    
    private Date created;
    private EmployeesVO createdBy;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public SaasContractVO() {

    }

    public SaasContractVO(Integer hcmoSaasContractId, String saasName, String saasType,
        Integer saasSize, String companyName, String personName, String designation,
        Date saasSignDate, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientId) {
        super();
        this.clientId=clientId;
        this.hcmoSaasContractId = hcmoSaasContractId;
        this.saasName = saasName;
        this.saasType = saasType;
        this.saasSize = saasSize;
        this.companyName = companyName;
        this.personName = personName;
        this.designation = designation;
        this.saasSignDate = saasSignDate;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoSaasContractId() {
        return hcmoSaasContractId;
    }

    public void setHcmoSaasContractId(Integer hcmoSaasContractId) {
        this.hcmoSaasContractId = hcmoSaasContractId;
    }

    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }

    public String getSaasType() {
        return saasType;
    }

    public void setSaasType(String saasType) {
        this.saasType = saasType;
    }

    public Integer getSaasSize() {
        return saasSize;
    }

    public void setSaasSize(Integer saasSize) {
        this.saasSize = saasSize;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getSaasSignDate() {
        return saasSignDate;
    }

    public void setSaasSignDate(Date saasSignDate) {
        this.saasSignDate = saasSignDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}