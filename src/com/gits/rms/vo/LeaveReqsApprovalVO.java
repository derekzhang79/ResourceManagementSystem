
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class LeaveReqsApprovalVO implements Serializable {

    private static final long serialVersionUID = -2745733322766467108L;
    private String approveNotes;
    private String comments;
    private Date created;
    private EmployeesVO createdBy;
    private Date dateApplied;
    private Date dateApprDisappr;
    private String disApproveNotes;
    private EmployeesVO empIdObj;
    private EmployeesVO hcmoApproverId;
    private EmployeesVO hcmoLeaveApproverId;
    private Integer hcmoLeaveReqsApprovalId;
    private BigDecimal hourDay;
    private BigDecimal hours;
    private BigDecimal hoursPerDay = BigDecimal.valueOf(8);
    private int isActive;
    private String leaveMessageTitle;
    private String leaveReqStatus;
    private String leaveRequested;
    private LeaveTypeVO leaveTypeIdObj;
    private BigDecimal mins;
    private BigDecimal noOfDays;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public LeaveReqsApprovalVO() {
    }

    public LeaveReqsApprovalVO(int clientid,EmployeesVO empIdObj, LeaveTypeVO leaveTypeIdObj,
        Integer hcmoLeaveReqsApprovalId, EmployeesVO hcmoLeaveApproverId, Date dateApplied,
        BigDecimal noOfDays, Date dateApprDisappr, String leaveReqStatus, String comments,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
        int isActive, EmployeesVO hcmoApproverId, BigDecimal hourDay, String leaveRequested,
        BigDecimal mins, BigDecimal hours, BigDecimal hoursPerDay, String approveNotes,
        String disApproveNotes) {
        this.empIdObj = empIdObj;
        this.hcmoApproverId = hcmoApproverId;
        this.leaveTypeIdObj = leaveTypeIdObj;
        this.clientId=clientid;
        this.hcmoLeaveReqsApprovalId = hcmoLeaveReqsApprovalId;
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
        this.dateApplied = dateApplied;
        this.noOfDays = noOfDays;
        this.dateApprDisappr = dateApprDisappr;
        this.leaveReqStatus = leaveReqStatus;
        this.comments = comments;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.hourDay = hourDay;
        this.leaveRequested = leaveRequested;
        this.hours = hours;
        this.mins = mins;
        this.hoursPerDay = hoursPerDay;
        this.approveNotes = approveNotes;
        this.disApproveNotes = disApproveNotes;
    }

    public String getApproveNotes() {
        return approveNotes;
    }

    public String getComments() {
        return comments;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public Date getDateApprDisappr() {
        return dateApprDisappr;
    }

    public String getDisApproveNotes() {
        return disApproveNotes;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public EmployeesVO getHcmoApproverId() {
        return hcmoApproverId;
    }

    public EmployeesVO getHcmoLeaveApproverId() {
        return hcmoLeaveApproverId;
    }

    public Integer getHcmoLeaveReqsApprovalId() {
        return hcmoLeaveReqsApprovalId;
    }

    public BigDecimal getHourDay() {
        return hourDay;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public BigDecimal getHoursPerDay() {
        return hoursPerDay;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLeaveMessageTitle() {
        return leaveMessageTitle;
    }

    public String getLeaveReqStatus() {
        return leaveReqStatus;
    }

    public String getLeaveRequested() {
        return leaveRequested;
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

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setApproveNotes(String approveNotes) {
        this.approveNotes = approveNotes;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public void setDateApprDisappr(Date dateApprDisappr) {
        this.dateApprDisappr = dateApprDisappr;
    }

    public void setDisApproveNotes(String disApproveNotes) {
        this.disApproveNotes = disApproveNotes;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setHcmoApproverId(EmployeesVO hcmoApproverId) {
        this.hcmoApproverId = hcmoApproverId;
    }

    public void setHcmoLeaveApproverId(EmployeesVO hcmoLeaveApproverId) {
        this.hcmoLeaveApproverId = hcmoLeaveApproverId;
    }

    public void setHcmoLeaveReqsApprovalId(Integer hcmoLeaveReqsApprovalId) {
        this.hcmoLeaveReqsApprovalId = hcmoLeaveReqsApprovalId;
    }

    public void setHourDay(BigDecimal hourDay) {
        this.hourDay = hourDay;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public void setHoursPerDay(BigDecimal hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLeaveMessageTitle(String leaveMessageTitle) {
        this.leaveMessageTitle = leaveMessageTitle;
    }

    public void setLeaveReqStatus(String leaveReqStatus) {
        this.leaveReqStatus = leaveReqStatus;
    }

    public void setLeaveRequested(String leaveRequested) {
        this.leaveRequested = leaveRequested;
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

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
