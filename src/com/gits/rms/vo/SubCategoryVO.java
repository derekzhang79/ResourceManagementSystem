package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SubCategoryVO implements Serializable{

    private static final long serialVersionUID = 7116911331180505138L;
    private Integer hcmoSubCategoryId;
    private CategoryVO hcmoCategoryId;
    private String subCategoryName;
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
    
    
    
    public SubCategoryVO() {
    }



    public SubCategoryVO(int clientid,Integer hcmoSubCategoryId, CategoryVO hcmoCategoryId,
        String subCategoryName, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoSubCategoryId = hcmoSubCategoryId;
        this.hcmoCategoryId = hcmoCategoryId;
        this.subCategoryName = subCategoryName;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }



    public Integer getHcmoSubCategoryId() {
        return hcmoSubCategoryId;
    }



    public void setHcmoSubCategoryId(Integer hcmoSubCategoryId) {
        this.hcmoSubCategoryId = hcmoSubCategoryId;
    }



    public CategoryVO getHcmoCategoryId() {
        return hcmoCategoryId;
    }



    public void setHcmoCategoryId(CategoryVO hcmoCategoryId) {
        this.hcmoCategoryId = hcmoCategoryId;
    }



    public String getSubCategoryName() {
        return subCategoryName;
    }



    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
