
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class HolidayVO implements Serializable {

    private static final long serialVersionUID = -350238550704722552L;
    private Date created;
    private EmployeesVO createdBy;
    private HashMap<Integer, String> hmLength = new HashMap<Integer, String>(2);
    private HashMap<Boolean, String> hmRecurring = new HashMap<Boolean, String>(2);
    private Date holidayDate;
    private String holidayDateValue;
    private String holidayDescription;
    private Date holidayEndDate;
    private Integer holidayId;
    private int isActive;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    
    private int length;
    private String lengthValue;
    private String message;
   
    private boolean recurring;
    private String recurringValue;
    private Timestamp updated;
    private EmployeesVO updatedBy;

    public HolidayVO() {
    }

    public HolidayVO(Integer holidayId, String holidayDescription, Date holidayEndDate,
        Date holidayDate, String holidayDateValue, String LengthValue, String message,
       
        boolean recurring, int length,int clientId) {
        this.holidayId = holidayId;
        this.holidayDescription = holidayDescription;
        this.holidayDate = holidayDate;
        this.holidayDateValue = holidayDateValue;
        this.holidayEndDate = holidayEndDate;
       this.clientId=clientId;
        this.recurring = recurring;
        this.length = length;
        this.message = message;
        this.lengthValue =lengthValue;    }

    public Date getCreated() {
       
        return created;
    }

    public EmployeesVO getCreatedBy() {
       
        return createdBy;
    }

    public HashMap<Integer, String> getHmLength() {
       
        hmLength.put(Integer.valueOf(4), String.valueOf("Half Day"));
        hmLength.put(Integer.valueOf(8), String.valueOf("Full Day"));
        return hmLength;
    }

    public HashMap<Boolean, String> getHmRecurring() {
       
        hmRecurring.put(Boolean.TRUE, String.valueOf("Yes"));
        hmRecurring.put(Boolean.FALSE, String.valueOf("No"));
        return hmRecurring;
    }

    public Date getHolidayDate() {
       
        return holidayDate;
    }

    public String getHolidayDateValue() {
       
        return holidayDateValue;
    }

    public String getHolidayDescription() {
       
        return holidayDescription;
    }

    public Date getHolidayEndDate() {
        
        return holidayEndDate;
    }

    public Integer getHolidayId() {
       
        return holidayId;
    }

    public int getIsActive() {
       
        return isActive;
    }

       public String getLengthValue() {
       
        return lengthValue;
    }

    public String getMessage() {
       
        return message;
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

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public void setHolidayDateValue(String holidayDateValue) {
        this.holidayDateValue = holidayDateValue;
    }

    public void setHolidayDescription(String holidayDescription) {
        this.holidayDescription = holidayDescription;
    }

    public void setHolidayEndDate(Date holidayEndDate) {
        this.holidayEndDate = holidayEndDate;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

       public void setLengthValue(String lengthValue) {
        this.lengthValue = lengthValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }

      public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurringValue(String recurringValue) {
		this.recurringValue = recurringValue;
	}

	public String getRecurringValue() {
		return recurringValue;
	}
}
