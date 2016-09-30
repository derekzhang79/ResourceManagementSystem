package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class QuestionGroupNameIdentificationVO implements Serializable{

    private static final long serialVersionUID = -3669187557684112719L;
    private Integer hcmoQuestionGroupNameIdentificationId;
    private String name;
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
    
    public QuestionGroupNameIdentificationVO() {

    }

    public QuestionGroupNameIdentificationVO(int clientid,Integer hcmoQuestionGroupNameIdentificationId,
        String name, Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
        int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoQuestionGroupNameIdentificationId = hcmoQuestionGroupNameIdentificationId;
        this.name = name;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoQuestionGroupNameIdentificationId() {
        return hcmoQuestionGroupNameIdentificationId;
    }

    public void setHcmoQuestionGroupNameIdentificationId(Integer hcmoQuestionGroupNameIdentificationId) {
        this.hcmoQuestionGroupNameIdentificationId = hcmoQuestionGroupNameIdentificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
