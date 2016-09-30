
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class WorkExperienceVO implements Serializable {
    private static final long serialVersionUID = 3381671484050304403L;
    private String comments;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private String empJobTitle;
    private String employeerName;
    private Integer empWorkExpId;
    private Date fromDate;
    private Date fromDateEnds;
    private String fromDateValue;
    private int isActive;
    private List message = new LinkedList();
    private Date toDate;
    private Date toDateEnds;
    private String toDateValue;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId; 
    
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public WorkExperienceVO() {
    }

    public WorkExperienceVO(String comments, Date created, EmployeesVO createdBy, EmployeesVO empIdObj,
			String empJobTitle, String employeerName, Integer empWorkExpId, Date fromDate, Date fromDateEnds,
			String fromDateValue, int isActive, List message, Date toDate, Date toDateEnds, String toDateValue,
			Timestamp updated, EmployeesVO updatedBy, int clientId) {
		super();
		this.comments = comments;
		this.created = created;
		this.createdBy = createdBy;
		this.empIdObj = empIdObj;
		this.empJobTitle = empJobTitle;
		this.employeerName = employeerName;
		this.empWorkExpId = empWorkExpId;
		this.fromDate = fromDate;
		this.fromDateEnds = fromDateEnds;
		this.fromDateValue = fromDateValue;
		this.isActive = isActive;
		this.message = message;
		this.toDate = toDate;
		this.toDateEnds = toDateEnds;
		this.toDateValue = toDateValue;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.clientId = clientId;
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

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public String getEmpJobTitle() {
        return empJobTitle;
    }

    public String getEmployeerName() {
        return employeerName;
    }

    public Integer getEmpWorkExpId() {
        return empWorkExpId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getFromDateEnds() {
        return fromDateEnds;
    }

    public String getFromDateValue() {
        return fromDateValue;
    }

    public int getIsActive() {
        return isActive;
    }

    public List getMessage() {
        return message;
    }

    public Date getToDate() {
        return toDate;
    }

    public Date getToDateEnds() {
        return toDateEnds;
    }

    public String getToDateValue() {
        return toDateValue;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
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

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setEmpJobTitle(String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    public void setEmployeerName(String employeerName) {
        this.employeerName = employeerName;
    }

    public void setEmpWorkExpId(Integer empWorkExpId) {
        this.empWorkExpId = empWorkExpId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setFromDateEnds(Date fromDateEnds) {
        this.fromDateEnds = fromDateEnds;
    }

    public void setFromDateValue(String fromDateValue) {
        this.fromDateValue = fromDateValue;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setToDateEnds(Date toDateEnds) {
        this.toDateEnds = toDateEnds;
    }

    public void setToDateValue(String toDateValue) {
        this.toDateValue = toDateValue;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
