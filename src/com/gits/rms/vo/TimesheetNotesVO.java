
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TimesheetNotesVO implements Serializable {
    
	private static final long serialVersionUID = 7459718761819342959L;
	private Integer hcmoTimesheetNotesId;
	private String notes;
	private Date date;
	private EmployeesVO empIdObj;
	
    private Date created;
    private EmployeesVO createdBy;
    private int isActive;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public TimesheetNotesVO() {
    	
    }

	public TimesheetNotesVO(Integer hcmoTimesheetNotesId, String notes,
			Date date, EmployeesVO empIdObj, Date created,
			EmployeesVO createdBy, int isActive, Timestamp updated,
			EmployeesVO updatedBy,int clientid) {
		super();
		this.clientId=clientid;
		this.hcmoTimesheetNotesId = hcmoTimesheetNotesId;
		this.notes = notes;
		this.date = date;
		this.empIdObj = empIdObj;
		this.created = created;
		this.createdBy = createdBy;
		this.isActive = isActive;
		this.updated = updated;
		this.updatedBy = updatedBy;
	}

	public Integer getHcmoTimesheetNotesId() {
		return hcmoTimesheetNotesId;
	}

	public void setHcmoTimesheetNotesId(Integer hcmoTimesheetNotesId) {
		this.hcmoTimesheetNotesId = hcmoTimesheetNotesId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EmployeesVO getEmpIdObj() {
		return empIdObj;
	}

	public void setEmpIdObj(EmployeesVO empIdObj) {
		this.empIdObj = empIdObj;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public EmployeesVO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EmployeesVO createdBy) {
		this.createdBy = createdBy;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

}
