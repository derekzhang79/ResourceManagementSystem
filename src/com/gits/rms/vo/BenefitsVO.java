
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BenefitsVO implements Serializable {
    private static final long serialVersionUID = -3463972520870209940L;
    private String benefitsAttachFile;
    private String benefitsName;
    private Date created;
    private EmployeesVO createdBy;
    private String empId;
    private List<EmployeesVO> empIdObjList;
    private String employeeEmailId;
    private String employeeName;
    private Integer hcmoBenefitsId;
    private int isActive;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private Integer year;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public BenefitsVO() {
    }

    public BenefitsVO(Integer hcmoBenefitsId, Integer year, String benefitsName,
        String benefitsAttachFile, String empId, String employeeEmailId,
        List<EmployeesVO> empIdObjList, String employeeName, Date created, Timestamp updated,
        int isActive, EmployeesVO updatedBy, String benefitsFileNameLink, EmployeesVO createdBy,int clientid) {
        this.hcmoBenefitsId = hcmoBenefitsId;
        this.year = year;
        this.clientId=clientid;
        this.benefitsName = benefitsName;
        this.benefitsAttachFile = benefitsAttachFile;
        this.empId = empId;
        this.employeeEmailId = employeeEmailId;
        this.employeeName = employeeName;
        this.empIdObjList = empIdObjList;
        this.created = created;
        this.updated = updated;
        this.isActive = isActive;
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
    }

    public String getBenefitsAttachFile() {
        return benefitsAttachFile;
    }

    public String getBenefitsFileNameLink() {
        String benefitsFileNameLink = "<a href=\"mailto:" + benefitsAttachFile + "\">"
            + benefitsAttachFile + "</a>";
        return benefitsFileNameLink;
    }

    public String getBenefitsName() {
        return benefitsName;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getEmpId() {
        return empId;
    }

    public List<EmployeesVO> getEmpIdObjList() {
        return empIdObjList;
    }

    public String getEmployeeEmailId() {
        return employeeEmailId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getHcmoBenefitsId() {
        return hcmoBenefitsId;
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

    public Integer getYear() {
        return year;
    }

    public void setBenefitsAttachFile(String benefitsAttachFile) {
        this.benefitsAttachFile = benefitsAttachFile;
    }

    public void setBenefitsFileNameLink(String benefitsFileNameLink) {
    }

    public void setBenefitsName(String benefitsName) {
        this.benefitsName = benefitsName;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmpIdObjList(List<EmployeesVO> empIdObjList) {
        this.empIdObjList = empIdObjList;
    }

    public void setEmployeeEmailId(String employeeEmailId) {
        this.employeeEmailId = employeeEmailId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setHcmoBenefitsId(Integer hcmoBenefitsId) {
        this.hcmoBenefitsId = hcmoBenefitsId;
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

    public void setYear(Integer year) {
        this.year = year;
    }
}
