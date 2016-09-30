
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EmpPassportVO implements Serializable {
    private static final long serialVersionUID = -9063092343534657055L;
    private CountryVO country;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private String epComments;
    private Date epL9ReviewDate;
    private String epL9Status;
    private Date epPassportExpireDate;
    private Date epPassportExpireDateEnds;
    private String epPassportExpireDateValue;
    private Date epPassportIssueDate;
    private Date epPassportIssueDateEnds;
    private String epPassportIssueDateValue;
    private String epPassportNo;
    private String epPassportTypeFlg;
    private Integer hcmoEmpPassportId;
    private int isActive;
    private List message = new LinkedList();
    private String passportOrVisaValue;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EmpPassportVO() {
    }

    public EmpPassportVO(Integer hcmoEmpPassportId, String epPassportNo, Date epPassportIssueDate,
        Date epPassportIssueDateEnds, String epPassportIssueDateValue, Date epPassportExpireDate,
        Date epPassportExpireDateEnds, String epPassportExpireDateValue, List message,
        String epComments, String epPassportTypeFlg, String passportOrVisaValue, String epL9Status,
        Date epL9ReviewDate, EmployeesVO empIdObj, CountryVO country, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.clientId=clientid;
        this.hcmoEmpPassportId = hcmoEmpPassportId;
        this.epPassportNo = epPassportNo;
        this.epPassportIssueDate = epPassportIssueDate;
        this.epPassportIssueDateEnds = epPassportIssueDateEnds;
        this.epPassportIssueDateValue = epPassportIssueDateValue;
        this.epPassportExpireDate = epPassportExpireDate;
        this.epPassportExpireDateEnds = epPassportExpireDateEnds;
        this.epPassportExpireDateValue = epPassportExpireDateValue;
        this.message = message;
        this.epComments = epComments;
        this.epPassportTypeFlg = epPassportTypeFlg;
        this.passportOrVisaValue = passportOrVisaValue;
        this.epL9Status = epL9Status;
        this.epL9ReviewDate = epL9ReviewDate;
        this.empIdObj = empIdObj;
        this.country = country;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public CountryVO getCountry() {
        return country;
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

    public String getEpComments() {
        return epComments;
    }

    public Date getEpL9ReviewDate() {
        return epL9ReviewDate;
    }

    public String getEpL9Status() {
        return epL9Status;
    }

    public Date getEpPassportExpireDate() {
        return epPassportExpireDate;
    }

    public Date getEpPassportExpireDateEnds() {
        return epPassportExpireDateEnds;
    }

    public String getEpPassportExpireDateValue() {
        return epPassportExpireDateValue;
    }

    public Date getEpPassportIssueDate() {
        return epPassportIssueDate;
    }

    public Date getEpPassportIssueDateEnds() {
        return epPassportIssueDateEnds;
    }

    public String getEpPassportIssueDateValue() {
        return epPassportIssueDateValue;
    }

    public String getEpPassportNo() {
        return epPassportNo;
    }

    public String getEpPassportTypeFlg() {
        return epPassportTypeFlg;
    }

    public Integer getHcmoEmpPassportId() {
        return hcmoEmpPassportId;
    }

    public int getIsActive() {
        return isActive;
    }

    public List getMessage() {
        return message;
    }

    public String getPassportOrVisaValue() {
        return passportOrVisaValue;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCountry(CountryVO country) {
        this.country = country;
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

    public void setEpComments(String epComments) {
        this.epComments = epComments;
    }

    public void setEpL9ReviewDate(Date epL9ReviewDate) {
        this.epL9ReviewDate = epL9ReviewDate;
    }

    public void setEpL9Status(String epL9Status) {
        this.epL9Status = epL9Status;
    }

    public void setEpPassportExpireDate(Date epPassportExpireDate) {
        this.epPassportExpireDate = epPassportExpireDate;
    }

    public void setEpPassportExpireDateEnds(Date epPassportExpireDateEnds) {
        this.epPassportExpireDateEnds = epPassportExpireDateEnds;
    }

    public void setEpPassportExpireDateValue(String epPassportExpireDateValue) {
        this.epPassportExpireDateValue = epPassportExpireDateValue;
    }

    public void setEpPassportIssueDate(Date epPassportIssueDate) {
        this.epPassportIssueDate = epPassportIssueDate;
    }

    public void setEpPassportIssueDateEnds(Date epPassportIssueDateEnds) {
        this.epPassportIssueDateEnds = epPassportIssueDateEnds;
    }

    public void setEpPassportIssueDateValue(String epPassportIssueDateValue) {
        this.epPassportIssueDateValue = epPassportIssueDateValue;
    }

    public void setEpPassportNo(String epPassportNo) {
        this.epPassportNo = epPassportNo;
    }

    public void setEpPassportTypeFlg(String epPassportTypeFlg) {
        this.epPassportTypeFlg = epPassportTypeFlg;
    }

    public void setHcmoEmpPassportId(Integer hcmoEmpPassportId) {
        this.hcmoEmpPassportId = hcmoEmpPassportId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setPassportOrVisaValue(String passportOrVisaValue) {
        this.passportOrVisaValue = passportOrVisaValue;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
