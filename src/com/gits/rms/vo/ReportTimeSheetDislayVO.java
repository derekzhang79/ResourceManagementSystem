
package com.gits.rms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReportTimeSheetDislayVO implements Serializable {

    private static final long serialVersionUID = 8521849504507348762L;
    private String approved;
    private String categoryName;
    private String categoryTotalHour;
    private List emplist;
    private String empName;
    private Date enteredDate;
    private Double enteredTime;
    private String forApproval;
    private String projectName;
    private String projectTotalHour;
    private String rejected;
    private String rework;
    private String status;

    public ReportTimeSheetDislayVO() {
    }

    public ReportTimeSheetDislayVO(String projectName, String categoryName, Date enteredDate,
        String empName, Double enteredTime, String forApproval, String approved, String rejected,
        String rework, String status, String projectTotalHour, String categoryTotalHour,
        List emplist) {
        super();
        this.projectName = projectName;
        this.categoryName = categoryName;
        this.enteredDate = enteredDate;
        this.empName = empName;
        this.enteredTime = enteredTime;
        this.forApproval = forApproval;
        this.approved = approved;
        this.rejected = rejected;
        this.rework = rework;
        this.status = status;
        this.projectTotalHour = projectTotalHour;
        this.categoryTotalHour = categoryTotalHour;
        this.emplist = emplist;
    }

    public String getApproved() {
        return approved;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryTotalHour() {
        return categoryTotalHour;
    }

    public List getEmplist() {
        return emplist;
    }

    public String getEmpName() {
        return empName;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public Double getEnteredTime() {
        return enteredTime;
    }

    public String getForApproval() {
        return forApproval;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectTotalHour() {
        return projectTotalHour;
    }

    public String getRejected() {
        return rejected;
    }

    public String getRework() {
        return rework;
    }

    public String getStatus() {
        return status;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryTotalHour(String categoryTotalHour) {
        this.categoryTotalHour = categoryTotalHour;
    }

    public void setEmplist(List emplist) {
        this.emplist = emplist;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public void setEnteredTime(Double enteredTime) {
        this.enteredTime = enteredTime;
    }

    public void setForApproval(String forApproval) {
        this.forApproval = forApproval;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectTotalHour(String projectTotalHour) {
        this.projectTotalHour = projectTotalHour;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public void setRework(String rework) {
        this.rework = rework;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}