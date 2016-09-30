
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EmpLocationHistoryVO implements Serializable {

    private static final long serialVersionUID = -619739857446262963L;
    private ClientVO clientIdObj;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private Date endDate;
    private Date endDateEnds;
    private String endDateValue;
    private Integer hcmoEmpLocHistoryId;
    private int isActive;
    private LocationVO locationIdObj;
    private List message = new LinkedList();
    private Date startDate;
    private Date startDateEnds;
    private String startDateValue;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EmpLocationHistoryVO() {
    }

    public EmpLocationHistoryVO(int clientid,Integer hcmoEmpLocHistoryId, Date startDate, Date startDateEnds,
        String startDateValue, Date endDate, Date endDateEnds, String endDateValue, List message,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
        int isActive, EmployeesVO empIdObj, LocationVO locationIdObj, ClientVO clientIdObj) {
        super();
        this.clientId=clientid;
        this.hcmoEmpLocHistoryId = hcmoEmpLocHistoryId;
        this.startDate = startDate;
        this.startDateEnds = startDateEnds;
        this.startDateValue = startDateValue;
        this.endDate = endDate;
        this.endDateEnds = endDateEnds;
        this.endDateValue = endDateValue;
        this.message = message;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.empIdObj = empIdObj;
        this.locationIdObj = locationIdObj;
        this.clientIdObj = clientIdObj;
    }

    public ClientVO getClientIdObj() {
        return clientIdObj;
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

    public Date getEndDate() {
        return endDate;
    }

    public Date getEndDateEnds() {
        return endDateEnds;
    }

    public String getEndDateValue() {
        return endDateValue;
    }

    public Integer getHcmoEmpLocHistoryId() {
        return hcmoEmpLocHistoryId;
    }

    public int getIsActive() {
        return isActive;
    }

    public LocationVO getLocationIdObj() {
        return locationIdObj;
    }

    public List getMessage() {
        return message;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getStartDateEnds() {
        return startDateEnds;
    }

    public String getStartDateValue() {
        return startDateValue;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setClientIdObj(ClientVO clientIdObj) {
        this.clientIdObj = clientIdObj;
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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDateEnds(Date endDateEnds) {
        this.endDateEnds = endDateEnds;
    }

    public void setEndDateValue(String endDateValue) {
        this.endDateValue = endDateValue;
    }

    public void setHcmoEmpLocHistoryId(Integer hcmoEmpLocHistoryId) {
        this.hcmoEmpLocHistoryId = hcmoEmpLocHistoryId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setLocationIdObj(LocationVO locationIdObj) {
        this.locationIdObj = locationIdObj;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDateEnds(Date startDateEnds) {
        this.startDateEnds = startDateEnds;
    }

    public void setStartDateValue(String startDateValue) {
        this.startDateValue = startDateValue;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
