/**
 * 
 */
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Parveen
 *
 */
public class EmployeeShiftVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -405295536142184207L;
    private Integer hcmoShiftId;
    private Date startDate;
    private Date endDate;
    private Date created;
    private Timestamp startTime;
    private Timestamp endTime;
    private String shiftType;
    private EmployeesVO createdBy;
    private EmployeesVO employee;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private int clientId;
    
    
	public EmployeeShiftVO() {
		// TODO Auto-generated constructor stub
	}
	public Integer getHcmoShiftId() {
		return hcmoShiftId;
	}
	public void setHcmoShiftId(Integer hcmoShiftId) {
		this.hcmoShiftId = hcmoShiftId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public EmployeesVO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(EmployeesVO createdBy) {
		this.createdBy = createdBy;
	}
	public EmployeesVO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeesVO employee) {
		this.employee = employee;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public EmployeesVO getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(EmployeesVO updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public EmployeeShiftVO(Integer hcmoShiftId, Date startDate, Date endDate, Date created, Timestamp startTime,
			Timestamp endTime, String shiftType, EmployeesVO createdBy, EmployeesVO employee, Timestamp updated,
			EmployeesVO updatedBy, int isActive, int clientId) {
		super();
		this.hcmoShiftId = hcmoShiftId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.created = created;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftType = shiftType;
		this.createdBy = createdBy;
		this.employee = employee;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.isActive = isActive;
		this.clientId = clientId;
	}
}
