package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class QuestionBankGeneralInfoVO implements Serializable{

    private static final long serialVersionUID = -4678547174874713037L;
    private Integer hcmoQuestionGeneralInfoId;
    private QuestionBankVO hcmoQuestionBankId;
    private EmployeesVO hcmoApprovingEmpId;
    private QuestionGeneralInfoGroupVO hcmoQuestionGeneralInfoGroup;
    private QuestionGroupNameIdentificationVO hcmoQuestionGroupNameIdentificationId;
    private EmployeesVO hcmoAdminId;
    private DepartmentVO hcmoDepartmentId;
    private JobTitleVO hcmoJobTitleId;
    private TeamVO hcmoTeamId;
    private EmployeesVO hcmoEmployeeId;
    private EmployeesVO peerEmployeeId;
    private String employeeType;
    private String approvingEmpComments;
    private String adminComments;
    private Date performanceIndStartDate;
    private Date performanceIndEndDate;
    private Date created;
    private EmployeesVO createdBy;
    private Timestamp updated;private int clientId;  
    public int getClientId() {
        return clientId;
       }
      public void setClientId(int clientId) {
        this.clientId = clientId;
       }
    private EmployeesVO updatedBy;
    private int isActive;
    private String status;
    
    public QuestionBankGeneralInfoVO() {

    }

    public QuestionBankGeneralInfoVO(int clientid,Integer hcmoQuestionGeneralInfoId,EmployeesVO hcmoApprovingEmpId,String approvingEmpComments,EmployeesVO hcmoAdminId,
        QuestionBankVO hcmoQuestionBankId,QuestionGroupNameIdentificationVO hcmoQuestionGroupNameIdentificationId,QuestionGeneralInfoGroupVO hcmoQuestionGeneralInfoGroup,
        DepartmentVO hcmoDepartmentId,JobTitleVO hcmoJobTitleId, TeamVO hcmoTeamId, EmployeesVO hcmoEmployeeId,
        EmployeesVO peerEmployeeId, String employeeType, Date performanceIndStartDate,
        Date performanceIndEndDate, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,String status,String adminComments) {
        super();
        this.clientId=clientid;
        this.hcmoQuestionGeneralInfoId = hcmoQuestionGeneralInfoId;
        this.hcmoApprovingEmpId=hcmoApprovingEmpId;
        this.approvingEmpComments=approvingEmpComments;
        this.hcmoQuestionBankId = hcmoQuestionBankId;
        this.hcmoQuestionGroupNameIdentificationId=hcmoQuestionGroupNameIdentificationId;
        this.hcmoQuestionGeneralInfoGroup=hcmoQuestionGeneralInfoGroup;
        this.hcmoDepartmentId = hcmoDepartmentId;
        this.hcmoJobTitleId = hcmoJobTitleId;
        this.hcmoTeamId = hcmoTeamId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.peerEmployeeId = peerEmployeeId;
        this.employeeType = employeeType;
        this.performanceIndStartDate = performanceIndStartDate;
        this.performanceIndEndDate = performanceIndEndDate;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.status=status;
        this.hcmoAdminId=hcmoAdminId;
        this.adminComments=adminComments;
    }

    public Integer getHcmoQuestionGeneralInfoId() {
        return hcmoQuestionGeneralInfoId;
    }

    public void setHcmoQuestionGeneralInfoId(Integer hcmoQuestionGeneralInfoId) {
        this.hcmoQuestionGeneralInfoId = hcmoQuestionGeneralInfoId;
    }

    public QuestionBankVO getHcmoQuestionBankId() {
        return hcmoQuestionBankId;
    }

    public void setHcmoQuestionBankId(QuestionBankVO hcmoQuestionBankId) {
        this.hcmoQuestionBankId = hcmoQuestionBankId;
    }

    public DepartmentVO getHcmoDepartmentId() {
        return hcmoDepartmentId;
    }

    public void setHcmoDepartmentId(DepartmentVO hcmoDepartmentId) {
        this.hcmoDepartmentId = hcmoDepartmentId;
    }

    public JobTitleVO getHcmoJobTitleId() {
        return hcmoJobTitleId;
    }

    public void setHcmoJobTitleId(JobTitleVO hcmoJobTitleId) {
        this.hcmoJobTitleId = hcmoJobTitleId;
    }

    public TeamVO getHcmoTeamId() {
        return hcmoTeamId;
    }

    public void setHcmoTeamId(TeamVO hcmoTeamId) {
        this.hcmoTeamId = hcmoTeamId;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public EmployeesVO getPeerEmployeeId() {
        return peerEmployeeId;
    }

    public void setPeerEmployeeId(EmployeesVO peerEmployeeId) {
        this.peerEmployeeId = peerEmployeeId;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Date getPerformanceIndStartDate() {
        return performanceIndStartDate;
    }

    public void setPerformanceIndStartDate(Date performanceIndStartDate) {
        this.performanceIndStartDate = performanceIndStartDate;
    }

    public Date getPerformanceIndEndDate() {
        return performanceIndEndDate;
    }

    public void setPerformanceIndEndDate(Date performanceIndEndDate) {
        this.performanceIndEndDate = performanceIndEndDate;
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

    public EmployeesVO getHcmoApprovingEmpId() {
        return hcmoApprovingEmpId;
    }

    public void setHcmoApprovingEmpId(EmployeesVO hcmoApprovingEmpId) {
        this.hcmoApprovingEmpId = hcmoApprovingEmpId;
    }

    public String getApprovingEmpComments() {
        return approvingEmpComments;
    }

    public void setApprovingEmpComments(String approvingEmpComments) {
        this.approvingEmpComments = approvingEmpComments;
    }

    public EmployeesVO getHcmoAdminId() {
        return hcmoAdminId;
    }

    public void setHcmoAdminId(EmployeesVO hcmoAdminId) {
        this.hcmoAdminId = hcmoAdminId;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

           
}
