
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ExpenseStatusTrackerVO implements Serializable {

    private static final long serialVersionUID = 8263523818448510707L;
    private EmployeesVO accountantId;
    private String approvalStatus;
    private EmployeesVO approverId;
    private Date created;
    private EmployeesVO createdBy;
    private String curTypeValueForReImbAmountField;
    private String curTypeValueForTotalAmountField;
    private EmployeeExpensesVO hcmoExpensesId;
    private Integer hcmoExpensesStatusTrackerId;
    private EmployeesVO nextLevelId;
    private String reImbAmtFieldStringValue;
    private EmployeesVO rejectedId;
    private String rejectedNotes;
    private EmployeesVO reviewedId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ExpenseStatusTrackerVO() {
    }

    public ExpenseStatusTrackerVO(int clientid,Integer hcmoExpensesStatusTrackerId,
        EmployeeExpensesVO hcmoExpensesId, String approvalStatus, EmployeesVO approverId,
        EmployeesVO rejectedId, EmployeesVO reviewedId, EmployeesVO nextLevelId, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, EmployeesVO accountantId,
        String rejectedNotes, String curTypeValueForReImbAmountField,
        String curTypeValueForTotalAmountField, String reImbAmtFieldStringValue) {
        super();
        this.hcmoExpensesStatusTrackerId = hcmoExpensesStatusTrackerId;
        this.hcmoExpensesId = hcmoExpensesId;
        this.clientId=clientid;
        this.approvalStatus = approvalStatus;
        this.approverId = approverId;
        this.rejectedId = rejectedId;
        this.reviewedId = reviewedId;
        this.nextLevelId = nextLevelId;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.accountantId = accountantId;
        this.curTypeValueForReImbAmountField = curTypeValueForReImbAmountField;
        this.curTypeValueForTotalAmountField = curTypeValueForTotalAmountField;
        this.reImbAmtFieldStringValue = reImbAmtFieldStringValue;
    }

    public EmployeesVO getAccountantId() {
        return accountantId;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public EmployeesVO getApproverId() {
        return approverId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getCurTypeValueForReImbAmountField() {
        return curTypeValueForReImbAmountField;
    }

    public String getCurTypeValueForTotalAmountField() {
        return curTypeValueForTotalAmountField;
    }

    public EmployeeExpensesVO getHcmoExpensesId() {
        return hcmoExpensesId;
    }

    public Integer getHcmoExpensesStatusTrackerId() {
        return hcmoExpensesStatusTrackerId;
    }

    public EmployeesVO getNextLevelId() {
        return nextLevelId;
    }

    public String getReImbAmtFieldStringValue() {
        return reImbAmtFieldStringValue;
    }

    public EmployeesVO getRejectedId() {
        return rejectedId;
    }

    public String getRejectedNotes() {
        return rejectedNotes;
    }

    public EmployeesVO getReviewedId() {
        return reviewedId;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setAccountantId(EmployeesVO accountantId) {
        this.accountantId = accountantId;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setApproverId(EmployeesVO approverId) {
        this.approverId = approverId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCurTypeValueForReImbAmountField(String curTypeValueForReImbAmountField) {
        this.curTypeValueForReImbAmountField = curTypeValueForReImbAmountField;
    }

    public void setCurTypeValueForTotalAmountField(String curTypeValueForTotalAmountField) {
        this.curTypeValueForTotalAmountField = curTypeValueForTotalAmountField;
    }

    public void setHcmoExpensesId(EmployeeExpensesVO hcmoExpensesId) {
        this.hcmoExpensesId = hcmoExpensesId;
    }

    public void setHcmoExpensesStatusTrackerId(Integer hcmoExpensesStatusTrackerId) {
        this.hcmoExpensesStatusTrackerId = hcmoExpensesStatusTrackerId;
    }

    public void setNextLevelId(EmployeesVO nextLevelId) {
        this.nextLevelId = nextLevelId;
    }

    public void setReImbAmtFieldStringValue(String reImbAmtFieldStringValue) {
        this.reImbAmtFieldStringValue = reImbAmtFieldStringValue;
    }

    public void setRejectedId(EmployeesVO rejectedId) {
        this.rejectedId = rejectedId;
    }

    public void setRejectedNotes(String rejectedNotes) {
        this.rejectedNotes = rejectedNotes;
    }

    public void setReviewedId(EmployeesVO reviewedId) {
        this.reviewedId = reviewedId;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}