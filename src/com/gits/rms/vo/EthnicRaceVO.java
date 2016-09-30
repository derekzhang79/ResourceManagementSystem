
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EthnicRaceVO implements Serializable {

    private static final long serialVersionUID = -4102903268036674492L;
    private String ethnicRaceDesc;
    private Integer ethnicRaceId;
    private int isActive;
 
    
    public EthnicRaceVO() {
    }

    public EthnicRaceVO(Integer ethnicRaceId, String ethnicRaceDesc, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.ethnicRaceId = ethnicRaceId;
        this.ethnicRaceDesc = ethnicRaceDesc;
       
    }

    public String getEthnicRaceDesc() {
        return ethnicRaceDesc;
    }

    public Integer getEthnicRaceId() {
        return ethnicRaceId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setEthnicRaceDesc(String ethnicRaceDesc) {
        this.ethnicRaceDesc = ethnicRaceDesc;
    }

    public void setEthnicRaceId(Integer ethnicRaceId) {
        this.ethnicRaceId = ethnicRaceId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

  }
