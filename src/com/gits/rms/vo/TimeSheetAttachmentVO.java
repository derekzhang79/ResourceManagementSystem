
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class TimeSheetAttachmentVO implements Serializable {

    private static final long serialVersionUID = -7866277316338934113L;
    private Date created;
    private EmployeesVO createdBy;
    private Integer date;
    private Integer daysOfMonth;
   
    private Integer employeeId;
    private String fileName;
    private EmployeesVO hcmoEmployeeId;
    private ProjectVO hcmoProjectId;
    private Integer hcmoTsAttachmentId;
    private int isActive;
    private String isOwnTS;
    private String location;
    public String month;
    private Integer monthInt;
    private String role;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int week;
   
    private String weekEndDate;
    private String weekStartDate;
    private int year;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public TimeSheetAttachmentVO() {
    }

    public TimeSheetAttachmentVO(Integer hcmoTsAttachmentId, ProjectVO hcmoProjectId,
        EmployeesVO hcmoEmployeeId, int year, String month, int week, String fileName,
        String location, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive, Integer monthInt, Integer date,
       
        String weekStartDate, String weekEndDate, Integer employeeId, String role,
        Integer daysOfMonth, String isOwnTS,int clientid) {
        super();
        this.clientId=clientid;
        this.hcmoTsAttachmentId = hcmoTsAttachmentId;
        this.hcmoProjectId = hcmoProjectId;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.year = year;
        this.month = month;
        this.week = week;
        this.fileName = fileName;
        this.location = location;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.monthInt = monthInt;
        this.date = date;
       
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.employeeId = employeeId;
        this.role = role;
        this.daysOfMonth = daysOfMonth;
        this.isOwnTS = isOwnTS;
    }

    public Date getCreated() {
        
        return created;
    }

    public EmployeesVO getCreatedBy() {
       
        return createdBy;
    }

    public Integer getDate() {
       
        return date;
    }

    public Integer getDaysOfMonth() {
       
        return daysOfMonth;
    }

       public String getFileName() {
       
        return fileName;
    }

    public EmployeesVO getHcmoEmployeeId() {
       
        return hcmoEmployeeId;
    }

    public ProjectVO getHcmoProjectId() {
       
        return hcmoProjectId;
    }

    public Integer getHcmoTsAttachmentId() {
       
        return hcmoTsAttachmentId;
    }

    public int getIsActive() {
       
        return isActive;
    }

    public String getIsOwnTS() {
       
        return isOwnTS;
    }

    public String getLocation() {
       
        return location;
    }

    public String getMonth() {
       
        return month;
    }

    public Integer getMonthInt() {
       
        return monthInt;
    }

    public String getRole() {
       
        return role;
    }

    public Timestamp getUpdated() {
       
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
       
        return updatedBy;
    }

    public int getWeek() {
       
        return week;
    }

       public int getYear() {
       
        return year;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setDaysOfMonth(Integer daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

  
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public void setHcmoProjectId(ProjectVO hcmoProjectId) {
        this.hcmoProjectId = hcmoProjectId;
    }

    public void setHcmoTsAttachmentId(Integer hcmoTsAttachmentId) {
        this.hcmoTsAttachmentId = hcmoTsAttachmentId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setIsOwnTS(String isOwnTS) {
        this.isOwnTS = isOwnTS;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMonthInt(Integer monthInt) {
        this.monthInt = monthInt;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setWeek(int week) {
        this.week = week;
    }

   
    public void setYear(int year) {
        this.year = year;
    }

  
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

   
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}
}