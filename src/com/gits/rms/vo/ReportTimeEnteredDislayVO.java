
package com.gits.rms.vo;

import java.io.Serializable;

public class ReportTimeEnteredDislayVO implements Serializable {

    private static final long serialVersionUID = 8521849504507348762L;
    private Double totalEnteredTime;
    private String estimatedHours;
    private String employeeName;
    private String projectName;
    private String activityName;
    private String companyName;
    private Double overTimeHours;
    private Double timeRemaining;
    private String status;
    
    public ReportTimeEnteredDislayVO() {
    
    }

	public ReportTimeEnteredDislayVO(Double totalEnteredTime,
			String estimatedHours, String employeeName, String projectName,
			String activityName, String companyName) {
		super();
		this.totalEnteredTime = totalEnteredTime;
		this.estimatedHours = estimatedHours;
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.activityName = activityName;
		this.companyName = companyName;
	}
	
	public Double getTotalEnteredTime() {
		return totalEnteredTime;
	}

	public void setTotalEnteredTime(Double totalEnteredTime) {
		this.totalEnteredTime = totalEnteredTime;
	}

	public String getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(String estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Double getOverTimeHours() {
		return overTimeHours;
	}

	public void setOverTimeHours(Double overTimeHours) {
		this.overTimeHours = overTimeHours;
	}

	public Double getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(Double timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}