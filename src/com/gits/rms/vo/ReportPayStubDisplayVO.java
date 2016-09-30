
package com.gits.rms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ReportPayStubDisplayVO implements Serializable {

    private static final long serialVersionUID = -5713227664313706404L;
    private Double amount;
    private Date declarationDate;
    private String deductionName;
    private Date effectiveDate;
    private String empDeptName;
    private String empEmailId;
    private String empJobTitle;
    private Date empJoiningDate;
    private Integer employeeID;
    private String empName;
    private ArrayList<PayStubDeductionsVO> empPayStubDeductionList = new ArrayList<PayStubDeductionsVO>();
    private Double grossSalary;
    private String leaveRemainingCount;
    private String leaveTakenCount;
    private Double netSalary;
    private Integer payStubID;
    private String reportDate;
    private Integer reportDateMonth;
    private Integer reportDateYear;
    private String subreportDir;
    private Double timesheetCategoryCount;
    private Double timesheetProjectCount;

    public ReportPayStubDisplayVO() {
    }

    public ReportPayStubDisplayVO(String empName, Integer employeeID, Date empJoiningDate,
        String empEmailId, String empDeptName, String empJobTitle, String reportDate,
        Integer reportDateMonth, Integer reportDateYear, Double timesheetProjectCount,
        Double timesheetCategoryCount, String leaveTakenCount, String leaveRemainingCount,
        Integer payStubID, Double grossSalary, Double netSalary, Date declarationDate,
        String deductionName, Double amount, Date effectiveDate, String subreportDir,
        ArrayList<PayStubDeductionsVO> empPayStubDeductionList) {
        super();
        this.empName = empName;
        this.employeeID = employeeID;
        this.empJoiningDate = empJoiningDate;
        this.empEmailId = empEmailId;
        this.empDeptName = empDeptName;
        this.empJobTitle = empJobTitle;
        this.reportDate = reportDate;
        this.reportDateMonth = reportDateMonth;
        this.reportDateYear = reportDateYear;
        this.timesheetProjectCount = timesheetProjectCount;
        this.timesheetCategoryCount = timesheetCategoryCount;
        this.leaveTakenCount = leaveTakenCount;
        this.leaveRemainingCount = leaveRemainingCount;
        this.payStubID = payStubID;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.declarationDate = declarationDate;
        this.deductionName = deductionName;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
        this.subreportDir = subreportDir;
        this.empPayStubDeductionList = empPayStubDeductionList;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public String getEmpDeptName() {
        return empDeptName;
    }

    public String getEmpEmailId() {
        return empEmailId;
    }

    public String getEmpJobTitle() {
        return empJobTitle;
    }

    public Date getEmpJoiningDate() {
        return empJoiningDate;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getEmpName() {
        return empName;
    }

    public ArrayList<PayStubDeductionsVO> getEmpPayStubDeductionList() {
        return empPayStubDeductionList;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public String getLeaveRemainingCount() {
        return leaveRemainingCount;
    }

    public String getLeaveTakenCount() {
        return leaveTakenCount;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public Integer getPayStubID() {
        return payStubID;
    }

    public String getReportDate() {
        return reportDate;
    }

    public Integer getReportDateMonth() {
        return reportDateMonth;
    }

    public Integer getReportDateYear() {
        return reportDateYear;
    }

    public String getSubreportDir() {
        return subreportDir;
    }

    public Double getTimesheetCategoryCount() {
        return timesheetCategoryCount;
    }

    public Double getTimesheetProjectCount() {
        return timesheetProjectCount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setEmpDeptName(String empDeptName) {
        this.empDeptName = empDeptName;
    }

    public void setEmpEmailId(String empEmailId) {
        this.empEmailId = empEmailId;
    }

    public void setEmpJobTitle(String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    public void setEmpJoiningDate(Date empJoiningDate) {
        this.empJoiningDate = empJoiningDate;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpPayStubDeductionList(ArrayList<PayStubDeductionsVO> empPayStubDeductionList) {
        this.empPayStubDeductionList = empPayStubDeductionList;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setLeaveRemainingCount(String leaveRemainingCount) {
        this.leaveRemainingCount = leaveRemainingCount;
    }

    public void setLeaveTakenCount(String leaveTakenCount) {
        this.leaveTakenCount = leaveTakenCount;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public void setPayStubID(Integer payStubID) {
        this.payStubID = payStubID;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setReportDateMonth(Integer reportDateMonth) {
        this.reportDateMonth = reportDateMonth;
    }

    public void setReportDateYear(Integer reportDateYear) {
        this.reportDateYear = reportDateYear;
    }

    public void setSubreportDir(String subreportDir) {
        this.subreportDir = subreportDir;
    }

    public void setTimesheetCategoryCount(Double timesheetCategoryCount) {
        this.timesheetCategoryCount = timesheetCategoryCount;
    }

    public void setTimesheetProjectCount(Double timesheetProjectCount) {
        this.timesheetProjectCount = timesheetProjectCount;
    }

}