
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimesheetAchivedTargetReportDisplayVO implements Serializable {

    private static final long serialVersionUID = 94093382370013155L;
    
    private Integer projectId;
    private String projectName;
    private Integer activityId;
    private String activityName;
    private Date activityStartDate;
    private Date activityEndDate;
    private String employeeName;
    private String targetName;
    private String targetType;
    private String targetValue;
    private String targetMode;
    private String targetAchieved;
    private String targetNotes;
    
    private Date startDate;
    private Date endDate;
    private Date DateFrom;
    private Date DateTo;
    
    public TimesheetAchivedTargetReportDisplayVO() {
    	
    }

	public TimesheetAchivedTargetReportDisplayVO(Integer projectId,
			String projectName, Integer activityId, String activityName,
			Date activityStartDate, Date activityEndDate, String employeeName,
			String targetName, String targetType, String targetValue,
			String targetMode, String targetAchieved, String targetNotes,
			Date startDate, Date endDate, Date dateFrom, Date dateTo) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityStartDate = activityStartDate;
		this.activityEndDate = activityEndDate;
		this.employeeName = employeeName;
		this.targetName = targetName;
		this.targetType = targetType;
		this.targetValue = targetValue;
		this.targetMode = targetMode;
		this.targetAchieved = targetAchieved;
		this.targetNotes = targetNotes;
		this.startDate = startDate;
		this.endDate = endDate;
		DateFrom = dateFrom;
		DateTo = dateTo;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Date getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public Date getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public String getTargetMode() {
		return targetMode;
	}

	public void setTargetMode(String targetMode) {
		this.targetMode = targetMode;
	}

	public String getTargetAchieved() {
		return targetAchieved;
	}

	public void setTargetAchieved(String targetAchieved) {
		this.targetAchieved = targetAchieved;
	}

	public String getTargetNotes() {
		return targetNotes;
	}

	public void setTargetNotes(String targetNotes) {
		this.targetNotes = targetNotes;
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

	public Date getDateFrom() {
		return DateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		DateFrom = dateFrom;
	}

	public Date getDateTo() {
		return DateTo;
	}

	public void setDateTo(Date dateTo) {
		DateTo = dateTo;
	}
    
}
