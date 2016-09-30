package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class QuestionBankVO implements Serializable{

    private static final long serialVersionUID = -1106806405328646624L;
    private Integer hcmoQuestionBankId;
    private QuestionVO hcmoQuestionId;
    private QuestionGroupNameIdentificationVO hcmoQuestionGroupNameIdentificationId;
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
    
    public QuestionBankVO() {

    }

    public QuestionBankVO(Integer hcmoQuestionBankId, QuestionVO hcmoQuestionId,QuestionGroupNameIdentificationVO hcmoQuestionGroupNameIdentificationId,
        String name, Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.clientId = clientid;
        this.hcmoQuestionBankId = hcmoQuestionBankId;
        this.hcmoQuestionId = hcmoQuestionId;
        this.hcmoQuestionGroupNameIdentificationId=hcmoQuestionGroupNameIdentificationId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoQuestionBankId() {
        return hcmoQuestionBankId;
    }

    public void setHcmoQuestionBankId(Integer hcmoQuestionBankId) {
        this.hcmoQuestionBankId = hcmoQuestionBankId;
    }

    public QuestionVO getHcmoQuestionId() {
        return hcmoQuestionId;
    }

    public void setHcmoQuestionId(QuestionVO hcmoQuestionId) {
        this.hcmoQuestionId = hcmoQuestionId;
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

    public QuestionGroupNameIdentificationVO getHcmoQuestionGroupNameIdentificationId() {
        return hcmoQuestionGroupNameIdentificationId;
    }

    public void setHcmoQuestionGroupNameIdentificationId(QuestionGroupNameIdentificationVO hcmoQuestionGroupNameIdentificationId) {
        this.hcmoQuestionGroupNameIdentificationId = hcmoQuestionGroupNameIdentificationId;
    }
    
}
