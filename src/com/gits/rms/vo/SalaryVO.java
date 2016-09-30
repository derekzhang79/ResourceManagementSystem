
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SalaryVO implements Serializable {

    private static final long serialVersionUID = 6654570436852363723L;
    private Date created;
    private EmployeesVO createdBy;
    private String curTypeValueForSalaryField;
    private String dateValue;
    private Date declarationDate;
    private Date declarationEndDate;
    private EmployeesVO empIdObj;
    private Integer hcmosalaryId;
    private int isActive;
    private List message = new LinkedList();
    private String newSalary1;
    private Double salary;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public SalaryVO() {
    }

    public SalaryVO(int clientid,Integer hcmosalaryId, Date declarationDate, Double salary, String dateValue,
        EmployeesVO empIdObj, String curTypeValueForSalaryField, Date created,
        Date declarationEndDate, List message, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive) {
        this.hcmosalaryId = hcmosalaryId;
        this.declarationDate = declarationDate;
        this.salary = salary;
        this.clientId=clientid;
        this.empIdObj = empIdObj;
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
        this.declarationEndDate = declarationEndDate;
        this.dateValue = dateValue;
        this.message = message;
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

    public String getCurTypeValueForSalaryField() {
        return curTypeValueForSalaryField;

    }

    public String getDateValue() {
        return dateValue;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public Date getDeclarationEndDate() {
        return declarationEndDate;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public Integer getHcmosalaryId() {
        return hcmosalaryId;
    }

    public int getIsActive() {
        return isActive;
    }

    public List getMessage() {
        return message;
    }

    public String getnewSalary1() {
        return newSalary1;
    }

    public Double getSalary() {
        return salary;
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

    public void setCurTypeValueForSalaryField(String curTypeValueForSalaryField) {
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public void setDeclarationEndDate(Date declarationEndDate) {
        this.declarationEndDate = declarationEndDate;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setHcmosalaryId(Integer hcmosalaryId) {
        this.hcmosalaryId = hcmosalaryId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setnewSalary1(String newSalary1) {
        this.newSalary1 = newSalary1;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
