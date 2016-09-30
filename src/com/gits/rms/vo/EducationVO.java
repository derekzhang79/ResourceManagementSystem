
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EducationVO implements Serializable {

    private static final long serialVersionUID = 4808026374835161217L;
    private Date created;
    private EmployeesVO createdBy;
    private String eduDegree;
    private Date eduEndDate;
    private Date eduEndDateEnds;
    private String eduEndDateValue;
    private String eduGrade;
    private String eduMajor;
    private Date eduStartDate;
    private Date eduStartDateEnds;
    private String eduStartDateValue;
    private String eduUniversity;
    private Integer eduYear;
    private Integer empEducationId;
    private EmployeesVO empIdObj;
    private int isActive;
    private List message = new LinkedList();
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId; 
    private boolean gradStatus;
    
   
	public boolean isGradStatus() {
		return gradStatus;
	}
	public void setGradStatus(boolean gradStatus) {
		this.gradStatus = gradStatus;
	}
	
	public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public EducationVO() {
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public Date getEduEndDate() {
        return eduEndDate;
    }

    public Date getEduEndDateEnds() {
        return eduEndDateEnds;
    }

    public String getEduEndDateValue() {
        return eduEndDateValue;
    }

    public String getEduGrade() {
        return eduGrade;
    }

    public String getEduMajor() {
        return eduMajor;
    }

    public Date getEduStartDate() {
        return eduStartDate;
    }

    public Date getEduStartDateEnds() {
        return eduStartDateEnds;
    }

    public String getEduStartDateValue() {
        return eduStartDateValue;
    }

    public String getEduUniversity() {
        return eduUniversity;
    }

    public Integer getEduYear() {
        return eduYear;
    }

    public Integer getEmpEducationId() {
        return empEducationId;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public int getIsActive() {
        return isActive;
    }

    public List getMessage() {
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

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public void setEduEndDate(Date eduEndDate) {
        this.eduEndDate = eduEndDate;
    }

    public void setEduEndDateEnds(Date eduEndDateEnds) {
        this.eduEndDateEnds = eduEndDateEnds;
    }

    public void setEduEndDateValue(String eduEndDateValue) {
        this.eduEndDateValue = eduEndDateValue;
    }

    public void setEduGrade(String eduGrade) {
        this.eduGrade = eduGrade;
    }

    public void setEduMajor(String eduMajor) {
        this.eduMajor = eduMajor;
    }

    public void setEduStartDate(Date eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public void setEduStartDateEnds(Date eduStartDateEnds) {
        this.eduStartDateEnds = eduStartDateEnds;
    }

    public void setEduStartDateValue(String eduStartDateValue) {
        this.eduStartDateValue = eduStartDateValue;
    }

    public void setEduUniversity(String eduUniversity) {
        this.eduUniversity = eduUniversity;
    }

    public void setEduYear(Integer eduYear) {
        this.eduYear = eduYear;
    }

    public void setEmpEducationId(Integer empEducationId) {
        this.empEducationId = empEducationId;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
	public EducationVO(Date created, EmployeesVO createdBy, String eduDegree, Date eduEndDate, Date eduEndDateEnds,
			String eduEndDateValue, String eduGrade, String eduMajor, Date eduStartDate, Date eduStartDateEnds,
			String eduStartDateValue, String eduUniversity, Integer eduYear, Integer empEducationId,
			EmployeesVO empIdObj, int isActive, List message, Timestamp updated, EmployeesVO updatedBy, int clientId,
			boolean gradStatus) {
		super();
		this.created = created;
		this.createdBy = createdBy;
		this.eduDegree = eduDegree;
		this.eduEndDate = eduEndDate;
		this.eduEndDateEnds = eduEndDateEnds;
		this.eduEndDateValue = eduEndDateValue;
		this.eduGrade = eduGrade;
		this.eduMajor = eduMajor;
		this.eduStartDate = eduStartDate;
		this.eduStartDateEnds = eduStartDateEnds;
		this.eduStartDateValue = eduStartDateValue;
		this.eduUniversity = eduUniversity;
		this.eduYear = eduYear;
		this.empEducationId = empEducationId;
		this.empIdObj = empIdObj;
		this.isActive = isActive;
		this.message = message;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.clientId = clientId;
		this.gradStatus = gradStatus;
	}
	
}
