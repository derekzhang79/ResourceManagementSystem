
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SignatureVO implements Serializable {

    private static final long serialVersionUID = 4121496024398582301L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private Integer hcmoSignatureId;
    private int isActive;
    private boolean preSignature;
    private String preSignatureValue;
    private String signatureName;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public SignatureVO() {
    }

    public SignatureVO(int clientid,Integer hcmoSignatureId, EmployeesVO empIdObj, String signatureName,
        boolean preSignature, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive, String preSignatureValue) {
        this.hcmoSignatureId = hcmoSignatureId;
        this.empIdObj = empIdObj;
        this.signatureName = signatureName;
        this.preSignature = preSignature;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.clientId=clientid;
        this.isActive = isActive;
        this.preSignatureValue = preSignatureValue;
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

    public Integer getHcmoSignatureId() {
        return hcmoSignatureId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPreSignatureValue() {
        return preSignatureValue;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public boolean isPreSignature() {
        return preSignature;
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

    public void setHcmoSignatureId(Integer hcmoSignatureId) {
        this.hcmoSignatureId = hcmoSignatureId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPreSignature(boolean preSignature) {
        this.preSignature = preSignature;
    }

    public void setPreSignatureValue(String preSignatureValue) {
        this.preSignatureValue = preSignatureValue;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
