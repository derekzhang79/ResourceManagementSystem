
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class LeaveHistoryVO implements Serializable {

    private static final long serialVersionUID = -7612028934049678039L;
    private String approveNotes;
    private Date created;
    private EmployeesVO createdBy;
    private String disApproveNotes;
    private EmployeesVO empIdObj;
    private String endTime;
    private EmployeesVO hcmoLeaveApproverId;
    private Integer hcmoLeaveHistoryId;
    private BigDecimal hours;
    private int isActive;
    private String leaveComments;
    private Date leaveDate;
    private String leaveDateShow;
    private Date leaveEndDate;
    private BigDecimal leaveLengthDaysShow = new BigDecimal(0);
    private String leaveRequested;
    private Integer leaveRequestId;
    private Date leaveStartDate;
    private String leaveStatus;
    private String leaveTypeAll;
    private Integer leaveTypeId;
    private LeaveTypeVO leaveTypeIdObj;
    private BigDecimal mins;
    private BigDecimal noOfDays;
    private String startTime;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    
    public LeaveHistoryVO() {
    }

    public LeaveHistoryVO(Integer hcmoLeaveHistoryId, EmployeesVO hcmoLeaveApproverId,
        Date leaveDate, Date leaveStartDate, Date leaveEndDate, String leaveStatus,
        String leaveComments, Integer leaveRequestId, Integer leaveTypeId, String startTime,
        String endTime, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive, EmployeesVO empIdObj, LeaveTypeVO leaveTypeIdObj,
        String leaveTypeAll, String approveNotes, String disApproveNotes, BigDecimal noOfDays,
        BigDecimal hours, BigDecimal mins, String leaveRequested,int clientid) {
        this.hcmoLeaveHistoryId = hcmoLeaveHistoryId;
        this.clientId=clientid;
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
        this.leaveDate = leaveDate;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.leaveStatus = leaveStatus;
        this.leaveComments = leaveComments;
        this.leaveRequestId = leaveRequestId;
        this.leaveTypeId = leaveTypeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.empIdObj = empIdObj;
        this.leaveTypeIdObj = leaveTypeIdObj;
        this.leaveTypeAll = leaveTypeAll;
        this.approveNotes = approveNotes;
        this.disApproveNotes = disApproveNotes;
        this.noOfDays = noOfDays;
        this.hours = hours;
        this.mins = mins;
        this.leaveRequested = leaveRequested;
    }

    public String getApproveNotes() {
        return approveNotes;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getDisApproveNotes() {
        return disApproveNotes;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public String getEndTime() {
        return endTime;
    }

    public EmployeesVO getHcmoLeaveApproverId() {
        return hcmoLeaveApproverId;
    }

    public Integer getHcmoLeaveHistoryId() {
        return hcmoLeaveHistoryId;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLeaveComments() {
        return leaveComments;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public String getLeaveDateShow() {
        return leaveDateShow;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public BigDecimal getLeaveLengthDaysShow() {
        return leaveLengthDaysShow;
    }

    public String getLeaveRequested() {
        return leaveRequested;
    }

    public Integer getLeaveRequestId() {
        return leaveRequestId;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public String getLeaveTypeAll() {
        return leaveTypeAll;
    }

    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    public LeaveTypeVO getLeaveTypeIdObj() {
        return leaveTypeIdObj;
    }

    public BigDecimal getMins() {
        return mins;
    }

    public BigDecimal getNoOfDays() {
        return noOfDays;
    }

    public String getStartTime() {
        return startTime;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setApproveNotes(String approveNotes) {
        this.approveNotes = approveNotes;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDisApproveNotes(String disApproveNotes) {
        this.disApproveNotes = disApproveNotes;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setHcmoLeaveApproverId(EmployeesVO hcmoLeaveApproverId) {
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
    }

    public void setHcmoLeaveHistoryId(Integer hcmoLeaveHistoryId) {
        this.hcmoLeaveHistoryId = hcmoLeaveHistoryId;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLeaveComments(String leaveComments) {
        this.leaveComments = leaveComments;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setLeaveDateShow(String leaveDateShow) {
        Map mSession = ActionContext.getContext().getSession();
        mSession.put("leaveDateShow", leaveDateShow + "");
        this.leaveDateShow = leaveDateShow;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public void setLeaveLengthDaysShow(BigDecimal leaveLengthDaysShow) {
        Map mSession = ActionContext.getContext().getSession();
        mSession.put("leaveLengthDaysShow", leaveLengthDaysShow + "");
        this.leaveLengthDaysShow = leaveLengthDaysShow;
    }

    public void setLeaveRequested(String leaveRequested) {
        this.leaveRequested = leaveRequested;
    }

    public void setLeaveRequestId(Integer leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public void setLeaveTypeAll(String leaveTypeAll) {
        this.leaveTypeAll = leaveTypeAll;
    }

    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public void setLeaveTypeIdObj(LeaveTypeVO leaveTypeIdObj) {
        this.leaveTypeIdObj = leaveTypeIdObj;
    }

    public void setMins(BigDecimal mins) {
        this.mins = mins;
    }

    public void setNoOfDays(BigDecimal noOfDays) {
        this.noOfDays = noOfDays;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
