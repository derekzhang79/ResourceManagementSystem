
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PayStubDeductionsVO implements Serializable {

    private static final long serialVersionUID = -5694178445427806276L;
    private Double amountDeduced;
    private Date created;
    private EmployeesVO createdBy;
    private DeductionsVO deduction;
    private Double deductionAmount;
    private Date effectiveDate;
    private int isActive;
    private PayStubVO payStub;
    private Integer payStubDeductionId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public PayStubDeductionsVO() {
    }

    public PayStubDeductionsVO(int clientid,Integer payStubDeductionId, PayStubVO payStub,
        DeductionsVO deduction, Double deductionAmount, Double amountDeduced, Date effectiveDate,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.payStubDeductionId = payStubDeductionId;
        this.payStub = payStub;
        this.deduction = deduction;
        this.clientId=clientid;
        this.deductionAmount = deductionAmount;
        this.amountDeduced = amountDeduced;
        this.effectiveDate = effectiveDate;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Double getAmountDeduced() {
        return amountDeduced;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public DeductionsVO getDeduction() {
        return deduction;
    }

    public Double getDeductionAmount() {
        return deductionAmount;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public int getIsActive() {
        return isActive;
    }

    public PayStubVO getPayStub() {
        return payStub;
    }

    public Integer getPayStubDeductionId() {
        return payStubDeductionId;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAmountDeduced(Double amountDeduced) {
        this.amountDeduced = amountDeduced;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDeduction(DeductionsVO deduction) {
        this.deduction = deduction;
    }

    public void setDeductionAmount(Double deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPayStub(PayStubVO payStub) {
        this.payStub = payStub;
    }

    public void setPayStubDeductionId(Integer payStubDeductionId) {
        this.payStubDeductionId = payStubDeductionId;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
