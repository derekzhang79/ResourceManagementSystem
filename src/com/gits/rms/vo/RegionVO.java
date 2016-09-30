
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RegionVO implements Serializable {
    private static final long serialVersionUID = 8207678274972183841L;
    private String areaCode;
    private String city;
    private CountryVO country;
    private String countyField;
    private Integer hcmoregionId;
    private int isActive;
    private String latitude;
    private String longitude;
    private String name;
    private String regionCode;
    private String timeZone;
    private String zipCode;

    public RegionVO() {
    }

    public RegionVO(Integer hcmoregionId, String name, String zipCode, String city,
        String countryField, String regionCode, String areaCode, String timeZone, String latitude,
        String longitude, CountryVO country,         int isActive) {
        this.hcmoregionId = hcmoregionId;
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        countyField = countyField;
        this.regionCode = regionCode;
        this.areaCode = areaCode;
        this.timeZone = timeZone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
      
        this.isActive = isActive;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getCity() {
        return city;
    }

    public CountryVO getCountry() {
        return country;
    }

    public String getCountyField() {
        return countyField;
    }

  
    public Integer getHcmoregionId() {
        return hcmoregionId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

  
    public String getZipCode() {
        return zipCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(CountryVO country) {
        this.country = country;
    }

    public void setCountyField(String countyField) {
        this.countyField = countyField;
    }

    
    public void setHcmoregionId(Integer hcmoregionId) {
        this.hcmoregionId = hcmoregionId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

  
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
