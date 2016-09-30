
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class JobTitleVO implements Serializable {

    private static final long serialVersionUID = -1185098744191117194L;
    private Date created;
    private EmployeesVO createdBy;
    private int isActive;
    private String jobTitleComments;
    private String jobTitleDesc;
    private Integer jobTitleId;
    private String jobTitleName;
    private SalaryGradeVO salaryGradeIdObj;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public JobTitleVO() {
    }

    public JobTitleVO(int clientid,Integer jobTitleId, String jobTitleName, String jobTitleDesc,
        String jobTitleComments, SalaryGradeVO salaryGradeIdObj, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.jobTitleId = jobTitleId;
        this.jobTitleName = jobTitleName;
        this.clientId=clientid;
        this.jobTitleDesc = jobTitleDesc;
        this.jobTitleComments = jobTitleComments;
        this.salaryGradeIdObj = salaryGradeIdObj;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getJobTitleComments() {
        return jobTitleComments;
    }

    public String getJobTitleDesc() {
        return jobTitleDesc;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public SalaryGradeVO getSalaryGradeIdObj() {
        return salaryGradeIdObj;
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setJobTitleComments(String jobTitleComments) {
        this.jobTitleComments = jobTitleComments;
    }

    public void setJobTitleDesc(String jobTitleDesc) {
        this.jobTitleDesc = jobTitleDesc;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public void setSalaryGradeIdObj(SalaryGradeVO salaryGradeIdObj) {
        this.salaryGradeIdObj = salaryGradeIdObj;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
