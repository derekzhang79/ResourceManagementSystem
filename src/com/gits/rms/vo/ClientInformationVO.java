
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ClientInformationVO implements Serializable {
    private static final long serialVersionUID = -4544383033709491919L;
    private String clientDbPassword;
    private String clientDbPath;
    private String clientDbUsername;
    private Integer clientId;
    private String clientName;
    private String clientSubDomainName;
    private String userType;
    private Date created;
    private int isActive;
    private Timestamp lastLogin;

    public ClientInformationVO() {

    }

    public ClientInformationVO(Integer clientId, String clientName,String userType,
        String clientSubDomainName,Integer clientFolderSize, String clientDbPath, String clientDbUsername,
        String clientDbPassword, int isActive, Date created, Timestamp lastLogin) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientSubDomainName = clientSubDomainName;
        this.clientDbPath = clientDbPath;
        this.clientDbUsername = clientDbUsername;
        this.clientDbPassword = clientDbPassword;
        this.userType = userType;
        this.isActive = isActive;
        this.created = created;
        this.lastLogin = lastLogin;    }

    public String getClientDbPassword() {
	   return clientDbPassword;
    }

    public String getClientDbPath() {
	   return clientDbPath;
    }

    public String getClientDbUsername() {
	   return clientDbUsername;
    }

    public Integer getClientId() {
	   return clientId;
    }

    public String getClientName() {
	   return clientName;
    }

    public String getClientSubDomainName() {
	   return clientSubDomainName;
    }

    public Date getCreated() {
	   return created;
    }

    public int getIsActive() {
	   return isActive;
    }

    public Timestamp getLastLogin() {
	   return lastLogin;
    }

    public void setClientDbPassword(String clientDbPassword) {
	   this.clientDbPassword = clientDbPassword;
    }

    public void setClientDbPath(String clientDbPath) {
	   this.clientDbPath = clientDbPath;
    }

    public void setClientDbUsername(String clientDbUsername) {
	   this.clientDbUsername = clientDbUsername;
    }

    public void setClientId(Integer clientId) {
	   this.clientId = clientId;
    }

    public void setClientName(String clientName) {
    	this.clientName = clientName;
	}

    public void setClientSubDomainName(String clientSubDomainName) {
    	this.clientSubDomainName = clientSubDomainName;
	}

    public void setCreated(Date created) {
    	this.created = created;
	}

    public void setIsActive(int isActive) {
    	this.isActive = isActive;
	}

    public void setLastLogin(Timestamp lastLogin) {
    	this.lastLogin = lastLogin;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
