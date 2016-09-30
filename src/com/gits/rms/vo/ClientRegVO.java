package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;



public class ClientRegVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String addressOptional;
	
	private Integer hcmoclientregId;
	
	private Integer clientid;
	
	private String firstName;
	
	private String lastName;
	
	private String companyName;
	
	private String contactMail;
	
	private String password;
	
	private String phone;
	
	private String contactAddress;
	
	private String adminuserid;
	
	private Date entryDate;
	
	private Timestamp updatedDate;
	
	private int status;
	
	private String activationKey;
	
	private int moduleId;
	
	public String getAddressOptional() {
		return addressOptional;
	}

	public void setAddressOptional(String addressOptional) {
		this.addressOptional = addressOptional;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getAdminuserid() {
		return adminuserid;
	}

	public void setAdminuserid(String adminuserid) {
		this.adminuserid = adminuserid;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getHcmoclientregId() {
		return hcmoclientregId;
	}

	public void setHcmoclientregId(Integer hcmoclientregId) {
		this.hcmoclientregId = hcmoclientregId;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ClientRegVO(){
		
	}
	
	public ClientRegVO(String addressOptional, Integer hcmoclientregId, Integer clientid, String firstName,
			String lastName, String companyName, String contactMail, String password, String phone,
			String contactAddress, String adminuserid, Date entryDate, Timestamp updatedDate, int status, String activationKey, int moduleId) {
		super();
		this.addressOptional = addressOptional;
		this.hcmoclientregId = hcmoclientregId;
		this.clientid = clientid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.contactMail = contactMail;
		this.password = password;
		this.phone = phone;
		this.contactAddress = contactAddress;
		this.adminuserid = adminuserid;
		this.entryDate = entryDate;
		this.updatedDate = updatedDate;
		this.status = status;
		this.activationKey = activationKey;
		this.moduleId = moduleId;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	

	
	

}
