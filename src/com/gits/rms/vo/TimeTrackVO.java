
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimeTrackVO implements Serializable {

    private static final long serialVersionUID = 4128131698090864494L;
   
    private Integer activityId;
    private Integer categoryId;
    private String categoryName;
    private int checkIn;
    private int checkOut;
    private Date created;
    private EmployeesVO createdBy;
    
   
    private double duration;
    private Integer empId;
    private Integer hcmoEmpTimesheetId;
    private int isActive;
   
    private Integer projectId;
    private Timestamp start;
    private Timestamp stop;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public TimeTrackVO() {
    }

   
    public TimeTrackVO(int clientid,Integer hcmoEmpTimesheetId, Integer empId, Integer categoryId,
        Integer projectId, Integer activityId, Timestamp start, Timestamp stop, double duration,
        String categoryName, int checkIn, int checkOut, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive) {
        super();
        this.clientId=clientid;
        this.hcmoEmpTimesheetId = hcmoEmpTimesheetId;
       
        this.empId = empId;
        this.categoryId = categoryId;
        this.projectId = projectId;
        this.activityId = activityId;
        this.start = start;
        this.stop = stop;
        this.duration = duration;
        this.categoryName = categoryName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;

    }

   
    public Date getCreated() {
    	return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

   public Integer getHcmoEmpTimesheetId() {
        return hcmoEmpTimesheetId;
    }

    public int getIsActive() {
        return isActive;
    }

   
    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setHcmoEmpTimesheetId(Integer hcmoEmpTimesheetId) {
        this.hcmoEmpTimesheetId = hcmoEmpTimesheetId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCheckIn(int checkIn) {
		this.checkIn = checkIn;
	}

	public int getCheckIn() {
		return checkIn;
	}

	public void setCheckOut(int checkOut) {
		this.checkOut = checkOut;
	}

	public int getCheckOut() {
		return checkOut;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getDuration() {
		return duration;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStop(Timestamp stop) {
		this.stop = stop;
	}

	public Timestamp getStop() {
		return stop;
	}

}