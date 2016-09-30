
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ChildrenVO implements Serializable {
    private static final long serialVersionUID = -7178879067598326568L;
    private Date childDOB;
    private String childName;
    private Date created;
    private EmployeesVO createdBy;
    private Integer empChildrenId;
    private EmployeesVO empIdObj;
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

    public ChildrenVO() {
    }

    public ChildrenVO(int clientid,Integer empChildrenId, String childName, Date childDOB, EmployeesVO empIdObj,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive) {
        this.empChildrenId = empChildrenId;
        this.childName = childName;
        this.clientId=clientid;
        this.childDOB = childDOB;
        this.empIdObj = empIdObj;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Date getChildDOB() {
        return childDOB;
    }

    public String getChildName() {
        return childName;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Integer getEmpChildrenId() {
        return empChildrenId;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
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

    public void setChildDOB(Date childDOB) {
        this.childDOB = childDOB;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpChildrenId(Integer empChildrenId) {
        this.empChildrenId = empChildrenId;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
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

}
