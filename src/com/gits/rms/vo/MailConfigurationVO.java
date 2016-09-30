
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class MailConfigurationVO implements Serializable {

    private static final long serialVersionUID = -1337221894383462911L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer hcmoMailConfigurationId;
    private int isActive;
    private String password;
    private String smtpHost;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String username;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public MailConfigurationVO() {
    }

    public MailConfigurationVO(Integer hcmoMailConfigurationId, String smtpHost, String username,
        String password, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientId) {
        this.hcmoMailConfigurationId = hcmoMailConfigurationId;
        this.smtpHost = smtpHost;
        this.username = username;
        this.password = password;
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

    public Integer getHcmoMailConfigurationId() {
        return hcmoMailConfigurationId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPassword() {
        return password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public String getUsername() {
        return username;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setHcmoMailConfigurationId(Integer hcmoMailConfigurationId) {
        this.hcmoMailConfigurationId = hcmoMailConfigurationId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
