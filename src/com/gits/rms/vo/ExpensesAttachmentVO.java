
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ExpensesAttachmentVO implements Serializable {

    private static final long serialVersionUID = -290227001587590950L;
    private Date created;
    private EmployeesVO createdBy;
    public String expensesAttachFileName;
    private int expensesAttachSize;
    public String expensesAttachType;
    private EmployeesVO hcmoEmployeeId;
    private Integer hcmoExpensesAttachId;
    private EmployeeExpensesVO hcmoExpensesId;
    private int isActive;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ExpensesAttachmentVO() {
    }

    public ExpensesAttachmentVO(int clientid,Integer hcmoExpensesAttachId, EmployeeExpensesVO hcmoExpensesId,
        EmployeesVO hcmoEmployeeId, String expensesAttachFileName, int expensesAttachSize,
        String expensesAttachType, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoExpensesAttachId = hcmoExpensesAttachId;
        this.hcmoExpensesId = hcmoExpensesId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.expensesAttachFileName = expensesAttachFileName;
        this.expensesAttachSize = expensesAttachSize;
        this.expensesAttachType = expensesAttachType;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getExpensesAttachFileName() {
        return expensesAttachFileName;
    }

    public int getExpensesAttachSize() {
        return expensesAttachSize;
    }

    public String getExpensesAttachType() {
        return expensesAttachType;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
    }

    public Integer getHcmoExpensesAttachId() {
        return hcmoExpensesAttachId;
    }

    public EmployeeExpensesVO getHcmoExpensesId() {
        return hcmoExpensesId;
    }

    public int getIsActive() {
        return isActive;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setExpensesAttachFileName(String expensesAttachFileName) {
        this.expensesAttachFileName = expensesAttachFileName;
    }

    public void setExpensesAttachSize(int expensesAttachSize) {
        this.expensesAttachSize = expensesAttachSize;
    }

    public void setExpensesAttachType(String expensesAttachType) {
        this.expensesAttachType = expensesAttachType;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public void setHcmoExpensesAttachId(Integer hcmoExpensesAttachId) {
        this.hcmoExpensesAttachId = hcmoExpensesAttachId;
    }

    public void setHcmoExpensesId(EmployeeExpensesVO hcmoExpensesId) {
        this.hcmoExpensesId = hcmoExpensesId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}