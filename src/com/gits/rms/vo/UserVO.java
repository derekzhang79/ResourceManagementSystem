
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class UserVO implements Serializable {

    private static final long serialVersionUID = -5030256005427370378L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private int forgotPassword;
    private Integer hcmouserId;
    private int isActive;
    private Date lastFailureLogin;
    private Date lastSucessfulLogin;
    private String password;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String userName;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public UserVO() {
    }

    public UserVO(int clientid,Integer hcmouserId, EmployeesVO empIdObj, String userName, String password,
        int forgotPassword, Date lastSucessfulLogin, Date lastFailureLogin, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.hcmouserId = hcmouserId;
        this.empIdObj = empIdObj;
        this.userName = userName;
        this.clientId=clientid;
        this.password = password;
        this.forgotPassword = forgotPassword;
        this.lastSucessfulLogin = lastSucessfulLogin;
        this.lastFailureLogin = lastFailureLogin;
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

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public int getForgotPassword() {
        return forgotPassword;
    }

    public Integer getHcmouserId() {
        return hcmouserId;
    }

    public int getIsActive() {
        return isActive;
    }

    public Date getLastFailureLogin() {
        return lastFailureLogin;
    }

    public Date getLastSucessfulLogin() {
        return lastSucessfulLogin;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setForgotPassword(int forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public void setHcmouserId(Integer hcmouserId) {
        this.hcmouserId = hcmouserId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLastFailureLogin(Date lastFailureLogin) {
        this.lastFailureLogin = lastFailureLogin;
    }

    public void setLastSucessfulLogin(Date lastSucessfulLogin) {
        this.lastSucessfulLogin = lastSucessfulLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
