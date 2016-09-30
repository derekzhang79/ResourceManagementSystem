
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SupportVO implements Serializable {
    private static final long serialVersionUID = 5852718659279187638L;
    private Date created;
    private EmployeesVO createdBy;
    private String empEmail;
    private String empFullName;
    private int isActive;
    private String priority;
    private String supportAttachFileName;
    private Integer supportId;
    private String supportMailMessage;
    private String supportMailSubject;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String upgradeMessage;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    
    public SupportVO() {
    }

    public SupportVO(int clientid,Integer supportId, String empFullName, String empEmail, String priority,
        String supportMailSubject, String supportMailMessage, String supportAttachFileName,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,String upgradeMessage) {
        this.supportId = supportId;
        this.clientId=clientid;
        this.empFullName = empFullName;
        this.empEmail = empEmail;
        this.priority = priority;
        this.supportMailSubject = supportMailSubject;
        this.supportMailMessage = supportMailMessage;
        this.supportAttachFileName = supportAttachFileName;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.upgradeMessage=upgradeMessage;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPriority() {
        return priority;
    }

    public String getSupportAttachFileName() {
        return supportAttachFileName;
    }

    public Integer getSupportId() {
        return supportId;
    }

    public String getSupportMailMessage() {
        return supportMailMessage;
    }

    public String getSupportMailSubject() {
        return supportMailSubject;
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

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setSupportAttachFileName(String supportAttachFileName) {
        this.supportAttachFileName = supportAttachFileName;
    }

    public void setSupportId(Integer supportId) {
        this.supportId = supportId;
    }

    public void setSupportMailMessage(String supportMailMessage) {
        this.supportMailMessage = supportMailMessage;
    }

    public void setSupportMailSubject(String supportMailSubject) {
        this.supportMailSubject = supportMailSubject;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpgradeMessage() {
        return upgradeMessage;
    }

    public void setUpgradeMessage(String upgradeMessage) {
        this.upgradeMessage = upgradeMessage;
    }
    
}
