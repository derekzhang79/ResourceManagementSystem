
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ClientVO implements Serializable {
    private static final long serialVersionUID = 6090469794010952136L;
    private String address1;
    private String address2;
    private String city;
    private String comments;
    private String companyName;
    private CountryVO country;
    private Date created;
    private EmployeesVO createdBy;
    private String fax;
    private Integer hcmoclientId;
    private int isActive;
    private Integer noOfEmp;
    private String phone;
    private String state;
    private String taxId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String zipcode;
    private String logoName;
    private Integer noOfBranch;

    public ClientVO() {

    }

    public ClientVO(Integer hcmoclientId, String companyName, Integer noOfEmp, String taxId,
        String phone, String fax, String city, CountryVO country, String zipcode, String address1,
        String address2, String state, String comments, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive, String logoName,Integer noOfBranch) {
        this.hcmoclientId = hcmoclientId;
        this.companyName = companyName;
        this.noOfEmp = noOfEmp;
        this.taxId = taxId;
        this.phone = phone;
        this.fax = fax;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.comments = comments;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.logoName = logoName;
        this.noOfBranch = noOfBranch;
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

    public String getCompanyName() {
        return companyName;
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

    public String getFax() {
        return fax;
    }

    public Integer getHcmoclientId() {
        return hcmoclientId;
    }

    public int getIsActive() {
        return isActive;
    }

    public Integer getNoOfEmp() {
        return noOfEmp;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setHcmoclientId(Integer hcmoclientId) {
        this.hcmoclientId = hcmoclientId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setNoOfEmp(Integer noOfEmp) {
        this.noOfEmp = noOfEmp;
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

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public Integer getNoOfBranch() {
		return noOfBranch;
	}

	public void setNoOfBranch(Integer noOfBranch) {
		this.noOfBranch = noOfBranch;
	}
}
