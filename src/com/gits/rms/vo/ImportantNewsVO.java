
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ImportantNewsVO implements Serializable {

    private static final long serialVersionUID = 2395485129426300156L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private Integer importantNewsId;
    private int isActive;
    private String message;
    private boolean showMessage;
    private String subject;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ImportantNewsVO() {
    }

    public ImportantNewsVO(Integer importantNewsId, EmployeesVO empIdObj, String subject,
        String message, boolean showMessage, Date created, EmployeesVO createdBy,
        Timestamp updated, EmployeesVO updatedBy, int isActive,int clientId) {
        super();
        this.importantNewsId = importantNewsId;
        this.clientId = clientId;
        this.empIdObj = empIdObj;
        this.subject = subject;
        this.message = message;
        this.showMessage = showMessage;
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

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public Integer getImportantNewsId() {
        return importantNewsId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public boolean isShowMessage() {
        return showMessage;
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

    public void setImportantNewsId(Integer importantNewsId) {
        this.importantNewsId = importantNewsId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
