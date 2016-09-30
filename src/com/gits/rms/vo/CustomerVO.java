
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class CustomerVO implements Serializable {

    private static final long serialVersionUID = -973825025882780304L;
    private String addr1;
    private String addr2;
    private String contactName;
    private Date created;
    private EmployeesVO createdBy;
    private Integer customerId;
    private String customerName;
    private String desc;
    private String email;
    private String fax;
    private int isActive;
    private String phoneNumber;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;
    
    

    public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public CustomerVO() {
    }

    public CustomerVO(Integer customerId, String customerName, String desc, String phoneNumber,
        String fax, String addr1, String addr2, String contactName, String email, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.desc = desc;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.contactName = contactName;
        this.email = email;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.clientId = clientId;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getContactName() {
        return contactName;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
