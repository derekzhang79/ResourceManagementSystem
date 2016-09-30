
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class VendorVO implements Serializable {

    private static final long serialVersionUID = -3744836876600775520L;
    private String address1;
    private String address2;
    private String city;
    private String companyName;
    private String contactPerson1;
    private String contactPerson2;
    private CountryVO country;
    private Date created;
    private EmployeesVO createdBy;
    private String custom1;
    private String custom2;
    private String emailAddress1;
    private String emailAddress2;
    private String extension;
    private String fax;
    private int isActive;
    private String password;
    private String phone;
    private String state;
    private String taxId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String userName;
    private Integer vendorId;
    private String vendorName;
    private String vendorShName;
    private String webSite;
    private String zipCode;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public VendorVO() {
    }

    public VendorVO(Integer vendorId, String taxId, String vendorName, String vendorShName,
        String contactPerson1, String contactPerson2, String emailAddress1, String emailAddress2,
        String userName, String password, String companyName, String address1, String address2,
        String state, String city, String zipCode, String phone, String extension, int isActive,
        String fax, String webSite, String custom1, String custom2, CountryVO country,int clientId) {
    	this.clientId = clientId;
        this.vendorId = vendorId;
        this.taxId = taxId;
        this.vendorName = vendorName;
        this.vendorShName = vendorShName;
        this.contactPerson1 = contactPerson1;
        this.contactPerson2 = contactPerson2;
        this.emailAddress1 = emailAddress1;
        this.emailAddress2 = emailAddress2;
        this.userName = userName;
        this.password = password;
        this.companyName = companyName;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.phone = phone;
        this.extension = extension;
        this.fax = fax;
        this.isActive = isActive;
        this.webSite = webSite;
        this.custom1 = custom1;
        this.custom2 = custom2;
        this.country = country;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson1() {
        return contactPerson1;
    }

    public String getContactPerson2() {
        return contactPerson2;
    }

    public CountryVO getCountry() {
        return country;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getCustom1() {
        return custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public String getEmailAddress1() {
        return emailAddress1;
    }

    public String getEmailAddress2() {
        return emailAddress2;
    }

    public String getExtension() {
        return extension;
    }

    public String getFax() {
        return fax;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public String getTaxId() {
        return taxId;
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

    public Integer getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorShName() {
        return vendorShName;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getZipCode() {
        return zipCode;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactPerson1(String contactPerson1) {
        this.contactPerson1 = contactPerson1;
    }

    public void setContactPerson2(String contactPerson2) {
        this.contactPerson2 = contactPerson2;
    }

    public void setCountry(CountryVO country) {
        this.country = country;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    public void setEmailAddress2(String emailAddress2) {
        this.emailAddress2 = emailAddress2;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
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

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setVendorShName(String vendorShName) {
        this.vendorShName = vendorShName;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
