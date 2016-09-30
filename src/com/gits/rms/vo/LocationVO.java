
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class LocationVO implements Serializable {
    private static final long serialVersionUID = 3567921187188407107L;
    private String address1;
    private String address2;
    private String city;
    private String comments;
    private CountryVO country;
    private String fax;
    private Integer hcmolocationId;
    private int isActive;
    private String locationName;
    private String phone;
    private String region;
    private String zipcode;
    public LocationVO() {
    }

    public LocationVO(Integer hcmolocationId, String locationName, String region, String address1,
        String address2, String city, String phone, String fax, String zipcode, CountryVO country,
        String comments, int isActive) {
        this.hcmolocationId = hcmolocationId;
        this.locationName = locationName;
         this.region = region;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.phone = phone;
        this.fax = fax;
        this.zipcode = zipcode;
        this.country = country;
        this.comments = comments;
        this.isActive = isActive;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getComments() {
        return comments;
    }

    public CountryVO getCountry() {
        return country;
    }

    public String getFax() {
        return fax;
    }

    public Integer getHcmolocationId() {
        return hcmolocationId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegion() {
        return region;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCountry(CountryVO country) {
        this.country = country;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setHcmolocationId(Integer hcmolocationId) {
        this.hcmolocationId = hcmolocationId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
