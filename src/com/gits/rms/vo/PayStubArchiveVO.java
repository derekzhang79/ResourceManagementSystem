
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PayStubArchiveVO implements Serializable {

    private static final long serialVersionUID = 2464211591865157833L;
    private Timestamp deleted;
    private Integer deletedBy;
    private Integer hcmoPayStubArchiveId;
    private Date payStubCreated;
    private Integer payStubCreatedBy;
    private Date payStubDeclarationDate;
    private Double payStubGrossSalary;
    private Integer payStubHcmoEmployeeId;
    private Integer payStubHcmoPayStubId;
    private int payStubIsActive;
    private Double payStubNetSalary;
    private Timestamp payStubUpdated;
    private Integer payStubUpdatedBy;
    private Date payStubDeductionCreated;
    private Integer payStubDeductionCreatedBy;
    private Double payStubDeductionDeductionAmount;
    private Date payStubDeductionEffectiveDate;
    private Integer payStubDeductionHcmoPayStubDeductionsId;
    private Integer payStubDeductionHcmoDeductionId;
    private Integer payStubDeductionHcmoPayStubId;
    private int payStubDeductionIsActive;
    private Timestamp payStubDeductionUpdated;
    private Integer payStubDeductionUpdatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public PayStubArchiveVO() {
    }

    public PayStubArchiveVO(Timestamp deleted, Integer deletedBy, Integer hcmoPayStubArchiveId,
        Date payStubCreated, Integer payStubCreatedBy, Date payStubDeclarationDate,
        Double payStubGrossSalary, Integer payStubHcmoEmployeeId, Integer payStubHcmoPayStubId,
        int payStubIsActive, Double payStubNetSalary, Timestamp payStubUpdated,
        Integer payStubUpdatedBy, Date payStubDeductionCreated, Integer payStubDeductionCreatedBy,
        Double payStubDeductionDeductionAmount, Date payStubDeductionEffectiveDate,
        Integer payStubDeductionHcmoPayStubDeductionsId, Integer payStubDeductionHcmoDeductionId,int clientid,
        Integer payStubDeductionHcmoPayStubId, int payStubDeductionIsActive,
        Timestamp payStubDeductionUpdated, Integer payStubDeductionUpdatedBy) {
        super();
        this.deleted = deleted;
        this.deletedBy = deletedBy;
        this.hcmoPayStubArchiveId = hcmoPayStubArchiveId;
        this.clientId=clientid;
        this.payStubCreated = payStubCreated;
        this.payStubCreatedBy = payStubCreatedBy;
        this.payStubDeclarationDate = payStubDeclarationDate;
        this.payStubGrossSalary = payStubGrossSalary;
        this.payStubHcmoEmployeeId = payStubHcmoEmployeeId;
        this.payStubHcmoPayStubId = payStubHcmoPayStubId;
        this.payStubIsActive = payStubIsActive;
        this.payStubNetSalary = payStubNetSalary;
        this.payStubUpdated = payStubUpdated;
        this.payStubUpdatedBy = payStubUpdatedBy;
        this.payStubDeductionCreated = payStubDeductionCreated;
        this.payStubDeductionCreatedBy = payStubDeductionCreatedBy;
        this.payStubDeductionDeductionAmount = payStubDeductionDeductionAmount;
        this.payStubDeductionEffectiveDate = payStubDeductionEffectiveDate;
        this.payStubDeductionHcmoPayStubDeductionsId = payStubDeductionHcmoPayStubDeductionsId;
        this.payStubDeductionHcmoDeductionId = payStubDeductionHcmoDeductionId;
        this.payStubDeductionHcmoPayStubId = payStubDeductionHcmoPayStubId;
        this.payStubDeductionIsActive = payStubDeductionIsActive;
        this.payStubDeductionUpdated = payStubDeductionUpdated;
        this.payStubDeductionUpdatedBy = payStubDeductionUpdatedBy;
    }

    public Timestamp getDeleted() {
        return deleted;
    }

    public void setDeleted(Timestamp deleted) {
        this.deleted = deleted;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Integer getHcmoPayStubArchiveId() {
        return hcmoPayStubArchiveId;
    }

    public void setHcmoPayStubArchiveId(Integer hcmoPayStubArchiveId) {
        this.hcmoPayStubArchiveId = hcmoPayStubArchiveId;
    }

    public Date getPayStubCreated() {
        return payStubCreated;
    }

    public void setPayStubCreated(Date payStubCreated) {
        this.payStubCreated = payStubCreated;
    }

    public Integer getPayStubCreatedBy() {
        return payStubCreatedBy;
    }

    public void setPayStubCreatedBy(Integer payStubCreatedBy) {
        this.payStubCreatedBy = payStubCreatedBy;
    }

    public Date getPayStubDeclarationDate() {
        return payStubDeclarationDate;
    }

    public void setPayStubDeclarationDate(Date payStubDeclarationDate) {
        this.payStubDeclarationDate = payStubDeclarationDate;
    }

    public Double getPayStubGrossSalary() {
        return payStubGrossSalary;
    }

    public void setPayStubGrossSalary(Double payStubGrossSalary) {
        this.payStubGrossSalary = payStubGrossSalary;
    }

    public Integer getPayStubHcmoEmployeeId() {
        return payStubHcmoEmployeeId;
    }

    public void setPayStubHcmoEmployeeId(Integer payStubHcmoEmployeeId) {
        this.payStubHcmoEmployeeId = payStubHcmoEmployeeId;
    }

    public Integer getPayStubHcmoPayStubId() {
        return payStubHcmoPayStubId;
    }

    public void setPayStubHcmoPayStubId(Integer payStubHcmoPayStubId) {
        this.payStubHcmoPayStubId = payStubHcmoPayStubId;
    }

    public int getPayStubIsActive() {
        return payStubIsActive;
    }

    public void setPayStubIsActive(int payStubIsActive) {
        this.payStubIsActive = payStubIsActive;
    }

    public Double getPayStubNetSalary() {
        return payStubNetSalary;
    }

    public void setPayStubNetSalary(Double payStubNetSalary) {
        this.payStubNetSalary = payStubNetSalary;
    }

    public Timestamp getPayStubUpdated() {
        return payStubUpdated;
    }

    public void setPayStubUpdated(Timestamp payStubUpdated) {
        this.payStubUpdated = payStubUpdated;
    }

    public Integer getPayStubUpdatedBy() {
        return payStubUpdatedBy;
    }

    public void setPayStubUpdatedBy(Integer payStubUpdatedBy) {
        this.payStubUpdatedBy = payStubUpdatedBy;
    }

    public Date getPayStubDeductionCreated() {
        return payStubDeductionCreated;
    }

    public void setPayStubDeductionCreated(Date payStubDeductionCreated) {
        this.payStubDeductionCreated = payStubDeductionCreated;
    }

    public Integer getPayStubDeductionCreatedBy() {
        return payStubDeductionCreatedBy;
    }

    public void setPayStubDeductionCreatedBy(Integer payStubDeductionCreatedBy) {
        this.payStubDeductionCreatedBy = payStubDeductionCreatedBy;
    }

    public Double getPayStubDeductionDeductionAmount() {
        return payStubDeductionDeductionAmount;
    }

    public void setPayStubDeductionDeductionAmount(Double payStubDeductionDeductionAmount) {
        this.payStubDeductionDeductionAmount = payStubDeductionDeductionAmount;
    }

    public Date getPayStubDeductionEffectiveDate() {
        return payStubDeductionEffectiveDate;
    }

    public void setPayStubDeductionEffectiveDate(Date payStubDeductionEffectiveDate) {
        this.payStubDeductionEffectiveDate = payStubDeductionEffectiveDate;
    }

    public Integer getPayStubDeductionHcmoPayStubDeductionsId() {
        return payStubDeductionHcmoPayStubDeductionsId;
    }

    public void setPayStubDeductionHcmoPayStubDeductionsId(Integer payStubDeductionHcmoPayStubDeductionsId) {
        this.payStubDeductionHcmoPayStubDeductionsId = payStubDeductionHcmoPayStubDeductionsId;
    }

    public Integer getPayStubDeductionHcmoDeductionId() {
        return payStubDeductionHcmoDeductionId;
    }

    public void setPayStubDeductionHcmoDeductionId(Integer payStubDeductionHcmoDeductionId) {
        this.payStubDeductionHcmoDeductionId = payStubDeductionHcmoDeductionId;
    }

    public Integer getPayStubDeductionHcmoPayStubId() {
        return payStubDeductionHcmoPayStubId;
    }

    public void setPayStubDeductionHcmoPayStubId(Integer payStubDeductionHcmoPayStubId) {
        this.payStubDeductionHcmoPayStubId = payStubDeductionHcmoPayStubId;
    }

    public int getPayStubDeductionIsActive() {
        return payStubDeductionIsActive;
    }

    public void setPayStubDeductionIsActive(int payStubDeductionIsActive) {
        this.payStubDeductionIsActive = payStubDeductionIsActive;
    }

    public Timestamp getPayStubDeductionUpdated() {
        return payStubDeductionUpdated;
    }

    public void setPayStubDeductionUpdated(Timestamp payStubDeductionUpdated) {
        this.payStubDeductionUpdated = payStubDeductionUpdated;
    }

    public Integer getPayStubDeductionUpdatedBy() {
        return payStubDeductionUpdatedBy;
    }

    public void setPayStubDeductionUpdatedBy(Integer payStubDeductionUpdatedBy) {
        this.payStubDeductionUpdatedBy = payStubDeductionUpdatedBy;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
