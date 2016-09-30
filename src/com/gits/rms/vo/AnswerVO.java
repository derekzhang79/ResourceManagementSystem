package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class AnswerVO implements Serializable{

    private static final long serialVersionUID = -7046417907861482147L;
    private Integer hcmoAnswerId;
    private QuestionBankVO hcmoQuestionBankId;
    private QuestionGeneralInfoGroupVO hcmoQuestionGeneralInfoGroup;
    private EmployeesVO hcmoAppraiserEmployeeId;
    private EmployeesVO hcmoAppraisingEmployeeId;
    private String answer;
    private String status;
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
    
    public AnswerVO() {

    }

    public AnswerVO(int clientid,Integer hcmoAnswerId, QuestionBankVO hcmoQuestionBankId,QuestionGeneralInfoGroupVO hcmoQuestionGeneralInfoGroup,
        EmployeesVO hcmoAppraiserEmployeeId, EmployeesVO hcmoAppraisingEmployeeId, String answer,String status,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoAnswerId = hcmoAnswerId;
        this.hcmoQuestionBankId = hcmoQuestionBankId;
        this.hcmoQuestionGeneralInfoGroup=hcmoQuestionGeneralInfoGroup;
        this.hcmoAppraiserEmployeeId = hcmoAppraiserEmployeeId;
        this.hcmoAppraisingEmployeeId = hcmoAppraisingEmployeeId;
        this.answer = answer;
        this.status=status;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoAnswerId() {
        return hcmoAnswerId;
    }

    public void setHcmoAnswerId(Integer hcmoAnswerId) {
        this.hcmoAnswerId = hcmoAnswerId;
    }

    public EmployeesVO getHcmoAppraiserEmployeeId() {
        return hcmoAppraiserEmployeeId;
    }

    public void setHcmoAppraiserEmployeeId(EmployeesVO hcmoAppraiserEmployeeId) {
        this.hcmoAppraiserEmployeeId = hcmoAppraiserEmployeeId;
    }

    public EmployeesVO getHcmoAppraisingEmployeeId() {
        return hcmoAppraisingEmployeeId;
    }

    public void setHcmoAppraisingEmployeeId(EmployeesVO hcmoAppraisingEmployeeId) {
        this.hcmoAppraisingEmployeeId = hcmoAppraisingEmployeeId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public QuestionBankVO getHcmoQuestionBankId() {
        return hcmoQuestionBankId;
    }

    public void setHcmoQuestionBankId(QuestionBankVO hcmoQuestionBankId) {
        this.hcmoQuestionBankId = hcmoQuestionBankId;
    }

    public QuestionGeneralInfoGroupVO getHcmoQuestionGeneralInfoGroup() {
        return hcmoQuestionGeneralInfoGroup;
    }

    public void setHcmoQuestionGeneralInfoGroup(QuestionGeneralInfoGroupVO hcmoQuestionGeneralInfoGroup) {
        this.hcmoQuestionGeneralInfoGroup = hcmoQuestionGeneralInfoGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
}
