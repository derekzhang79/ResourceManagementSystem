package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CategoryVO implements Serializable{

    private static final long serialVersionUID = 8941317967069939702L;
    private Integer hcmoCategoryId;
    private String categoryName;
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
    
    public CategoryVO() {

    }

    public CategoryVO(Integer hcmoCategoryId, String categoryName, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        super();
        this.hcmoCategoryId = hcmoCategoryId;
        this.clientId = clientId;
        this.categoryName = categoryName;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoCategoryId() {
        return hcmoCategoryId;
    }

    public void setHcmoCategoryId(Integer hcmoCategoryId) {
        this.hcmoCategoryId = hcmoCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    
    

}
