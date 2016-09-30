
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EventsVO implements Serializable {

    private static final long serialVersionUID = 1182571427185389142L;
    private Date created;
    private EmployeesVO createdBy;
   
    private String description;
    private Integer employeeId;
    private Integer eventId;
    private String eventName;
    private Integer groupId;
    private int isActive;
    
    private Timestamp startDate;
    private String timeZone;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EventsVO() {
    }

    
    public EventsVO(Integer eventId, Integer employeeId, String eventName, String description,
        Timestamp startDate, Integer groupId, String timeZone, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.clientId=clientid;
        this.eventId = eventId;
        this.employeeId = employeeId;
        this.eventName = eventName;
        this.description = description;
        this.startDate = startDate;
        this.groupId = groupId;
        this.timeZone = timeZone;
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

     
 public void setIsActive(int isActive) {
        this.isActive = isActive;
    }   

   public void setEventName(String eventName) {
		this.eventName = eventName;
	}
   public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
       public String getEventName() {
		return eventName;
	}   public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
    
	public void setDescription(String description) {
		this.description = description;
	}

  
	public String getDescription() {
		return description;
	}

  	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
   
   public Integer getEventId() {
		return eventId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

   
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZone() {
		return timeZone;
	}

}
