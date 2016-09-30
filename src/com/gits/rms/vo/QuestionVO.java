package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class QuestionVO implements Serializable{

    private static final long serialVersionUID = -1822743076885240148L;
    private Integer hcmoQuestionId;
    private CategoryVO hcmoCategoryId;
    private SubCategoryVO hcmoSubCategoryId;
    private String question;
    private String questionType;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String minRate;
    private String maxRate;
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
    
    public QuestionVO() {

    }

    public QuestionVO(int clientid,Integer hcmoQuestionId,CategoryVO hcmoCategoryId, SubCategoryVO hcmoSubCategoryId, String question,String questionType,
        String option1, String option2, String option3, String option4, String option5,
        String option6, String minRate, String maxRate, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoQuestionId = hcmoQuestionId;
        this.hcmoCategoryId=hcmoCategoryId;
        this.hcmoSubCategoryId = hcmoSubCategoryId;
        this.question = question;
        this.questionType=questionType;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoQuestionId() {
        return hcmoQuestionId;
    }

    public void setHcmoQuestionId(Integer hcmoQuestionId) {
        this.hcmoQuestionId = hcmoQuestionId;
    }

    public CategoryVO getHcmoCategoryId() {
        return hcmoCategoryId;
    }

    public void setHcmoCategoryId(CategoryVO hcmoCategoryId) {
        this.hcmoCategoryId = hcmoCategoryId;
    }

    public SubCategoryVO getHcmoSubCategoryId() {
        return hcmoSubCategoryId;
    }

    public void setHcmoSubCategoryId(SubCategoryVO hcmoSubCategoryId) {
        this.hcmoSubCategoryId = hcmoSubCategoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(String maxRate) {
        this.maxRate = maxRate;
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
