
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CurrencyVO implements Serializable {

    private static final long serialVersionUID = 4594027348765503294L;
    private String currencyType;
    private Integer hcmoCurrencyId;
    private int isActive;
    public CurrencyVO() {
    }

    public CurrencyVO(Integer hcmoCurrencyId, String currencyType, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.hcmoCurrencyId = hcmoCurrencyId;
        this.currencyType = currencyType;
        this.isActive = isActive;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public Integer getHcmoCurrencyId() {
        return hcmoCurrencyId;
    }

    public int getIsActive() {
        return isActive;
    }

   public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setHcmoCurrencyId(Integer hcmoCurrencyId) {
        this.hcmoCurrencyId = hcmoCurrencyId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

   }
