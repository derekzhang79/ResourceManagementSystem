
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CountryVO implements Serializable {

    private static final long serialVersionUID = 5214796527539037417L;
    private String countryCode;
    private String countryName;
    private String description;
    private Integer hcmocountryId;
    private int isActive;
   
    public CountryVO() {
    }

    public CountryVO(Integer hcmocountryId, String countryName, String countryCode,
        String description,  int isActive) {
        this.hcmocountryId = hcmocountryId;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.description = description;
        this.isActive = isActive;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

  
    public String getDescription() {
        return description;
    }

    public Integer getHcmocountryId() {
        return hcmocountryId;
    }

    public int getIsActive() {
        return isActive;
    }

   

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setHcmocountryId(Integer hcmocountryId) {
        this.hcmocountryId = hcmocountryId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

   }
