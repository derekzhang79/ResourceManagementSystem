
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class DeductionsVO implements Serializable {

    private static final long serialVersionUID = -7145557033535017515L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer deductionId;
    private String deductionMode;
    private String deductionName;
    private String deductionType;
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

    public DeductionsVO() {
    }

    public DeductionsVO(Integer deductionId, String deductionName, String deductionType,
        String deductionMode, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientId) {
        super();
        this.deductionId = deductionId;
        this.deductionName = deductionName;
        this.deductionType = deductionType;
        this.deductionMode = deductionMode;
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

    public Integer getDeductionId() {
        return deductionId;
    }

    public String getDeductionMode() {
        return deductionMode;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public String getDeductionType() {
        return deductionType;
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

    public void setDeductionId(Integer deductionId) {
        this.deductionId = deductionId;
    }

    public void setDeductionMode(String deductionMode) {
        this.deductionMode = deductionMode;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
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
