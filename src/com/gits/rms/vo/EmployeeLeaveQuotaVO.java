
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class EmployeeLeaveQuotaVO implements Serializable {

    private static final long serialVersionUID = 7335542433839863699L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private String empLeavePending;
    private Integer empLeaveQuotaId;
    private String empLeaveRequest;
    private BigDecimal hours;
    private int isActive;
    private String leaveAllottedDays;
    private BigDecimal leaveCarryingForward = new BigDecimal(0);
    private BigDecimal leaveTaken = new BigDecimal(0);
    private BigDecimal leaveTakenDays;
    private BigDecimal leaveTakenHours;
    private BigDecimal leaveTakenMinutes;
    private LeaveTypeVO leaveTypeIdObj;
    private BigDecimal minutes;
    Map mSession;
    private BigDecimal noOfDays;
    private BigDecimal previousCarryFwdDays;
    private BigDecimal previousCarryFwdHours;
    private BigDecimal previousCarryFwdMinutes;
    private String previousLeaveCarryDays;
    private Integer prvyearcarry;
    private BigDecimal prvYearCarryingForward = new BigDecimal(0);
    private BigDecimal remainDays;
    private BigDecimal remainHours;
    private BigDecimal remainMinutes;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private Integer year;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public EmployeeLeaveQuotaVO() {
    }

    public EmployeeLeaveQuotaVO(int clientid,Integer empLeaveQuotaId, EmployeesVO empIdObj, Integer year,
        BigDecimal noOfDays, BigDecimal hours, BigDecimal minutes, BigDecimal remainDays,
        BigDecimal remainHours, BigDecimal remainMinutes, BigDecimal leaveTaken,
        BigDecimal leaveTakenHours, BigDecimal leaveTakenDays, BigDecimal leaveTakenMinutes,
        BigDecimal prvYearCarryingForward, BigDecimal leaveCarryingForward,
        LeaveTypeVO leaveTypeIdObj, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive, Integer prvyearcarry, String empLeaveRequest,
        String empLeavePending, String leaveAllottedDays) {
        this.empLeaveQuotaId = empLeaveQuotaId;
        this.empIdObj = empIdObj;
        this.year = year;
        this.clientId=clientid;
        this.noOfDays = noOfDays;
        this.hours = hours;
        this.minutes = minutes;
        this.remainDays = remainDays;
        this.remainHours = remainHours;
        this.remainMinutes = remainMinutes;
        this.leaveTaken = leaveTaken;
        this.leaveTakenDays = leaveTakenDays;
        this.leaveTakenHours = leaveTakenHours;
        this.leaveTakenMinutes = leaveTakenMinutes;
        this.prvYearCarryingForward = prvYearCarryingForward;
        this.leaveCarryingForward = leaveCarryingForward;
        this.leaveTypeIdObj = leaveTypeIdObj;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.prvyearcarry = prvyearcarry;
        this.empLeaveRequest = empLeaveRequest;
        this.empLeavePending = empLeavePending;
        this.leaveAllottedDays = leaveAllottedDays;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public String getEmpLeavePending() {
        return empLeavePending;
    }

    public Integer getEmpLeaveQuotaId() {
        return empLeaveQuotaId;
    }

    public String getEmpLeaveRequest() {
        return empLeaveRequest;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getLeaveAllottedDays() {
        return leaveAllottedDays;
    }

    public BigDecimal getLeaveCarryingForward() {
        return leaveCarryingForward;
    }

    public BigDecimal getLeaveTaken() {
        return leaveTaken;
    }

    public BigDecimal getLeaveTakenDays() {
        return leaveTakenDays;
    }

    public BigDecimal getLeaveTakenHours() {
        return leaveTakenHours;
    }

    public BigDecimal getLeaveTakenMinutes() {
        return leaveTakenMinutes;
    }

    public LeaveTypeVO getLeaveTypeIdObj() {
        return leaveTypeIdObj;
    }

    public BigDecimal getMinutes() {
        return minutes;
    }

    public BigDecimal getNoOfDays() {
        return noOfDays;
    }

    public BigDecimal getPreviousCarryFwdDays() {
        return previousCarryFwdDays;
    }

    public BigDecimal getPreviousCarryFwdHours() {
        return previousCarryFwdHours;
    }

    public BigDecimal getPreviousCarryFwdMinutes() {
        return previousCarryFwdMinutes;
    }

    public String getPreviousLeaveCarryDays() {
        return previousLeaveCarryDays;
    }

    public Integer getPrvyearcarry() {
        return prvyearcarry;
    }

    public BigDecimal getPrvYearCarryingForward() {
        return prvYearCarryingForward;
    }

    public BigDecimal getRemainDays() {
        return remainDays;
    }

    public BigDecimal getRemainHours() {
        return remainHours;
    }

    public BigDecimal getRemainMinutes() {
        return remainMinutes;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public Integer getYear() {
        return year;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setEmpLeavePending(String empLeavePending) {
        this.empLeavePending = empLeavePending;
    }

    public void setEmpLeaveQuotaId(Integer empLeaveQuotaId) {
        this.empLeaveQuotaId = empLeaveQuotaId;
    }

    public void setEmpLeaveRequest(String empLeaveRequest) {
        this.empLeaveRequest = empLeaveRequest;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLeaveAllottedDays(String leaveAllottedDays) {
        this.leaveAllottedDays = leaveAllottedDays;
    }

    public void setLeaveCarryingForward(BigDecimal leaveCarryingForward) {
        this.leaveCarryingForward = leaveCarryingForward;
    }

    public void setLeaveTaken(BigDecimal leaveTaken) {
        this.leaveTaken = leaveTaken;
    }

    public void setLeaveTakenDays(BigDecimal leaveTakenDays) {
        this.leaveTakenDays = leaveTakenDays;
    }

    public void setLeaveTakenHours(BigDecimal leaveTakenHours) {
        this.leaveTakenHours = leaveTakenHours;
    }

    public void setLeaveTakenMinutes(BigDecimal leaveTakenMinutes) {
        this.leaveTakenMinutes = leaveTakenMinutes;
    }

    public void setLeaveTypeIdObj(LeaveTypeVO leaveTypeIdObj) {
        this.leaveTypeIdObj = leaveTypeIdObj;
    }

    public void setMinutes(BigDecimal minutes) {
        this.minutes = minutes;
    }

    public void setNoOfDays(BigDecimal noOfDays) {
        this.noOfDays = noOfDays;
    }

    public void setPreviousCarryFwdDays(BigDecimal previousCarryFwdDays) {
        this.previousCarryFwdDays = previousCarryFwdDays;
    }

    public void setPreviousCarryFwdHours(BigDecimal previousCarryFwdHours) {
        this.previousCarryFwdHours = previousCarryFwdHours;
    }

    public void setPreviousCarryFwdMinutes(BigDecimal previousCarryFwdMinutes) {
        this.previousCarryFwdMinutes = previousCarryFwdMinutes;
    }

    public void setPreviousLeaveCarryDays(String previousLeaveCarryDays) {
        this.previousLeaveCarryDays = previousLeaveCarryDays;
    }

    public void setPrvyearcarry(Integer prvyearcarry) {
        this.prvyearcarry = prvyearcarry;
    }

    public void setPrvYearCarryingForward(BigDecimal prvYearCarryingForward) {
        this.prvYearCarryingForward = prvYearCarryingForward;
    }

    public void setRemainDays(BigDecimal remainDays) {
        this.remainDays = remainDays;
    }

    public void setRemainHours(BigDecimal remainHours) {
        this.remainHours = remainHours;
    }

    public void setRemainMinutes(BigDecimal remainMinutes) {
        this.remainMinutes = remainMinutes;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
