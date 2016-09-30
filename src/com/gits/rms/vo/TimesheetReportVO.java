
package com.gits.rms.vo;

import java.io.Serializable;
import java.util.Date;

public class TimesheetReportVO implements Serializable {

    private static final long serialVersionUID = 2979315564626842801L;
    public String approverName;
    public String employeeName;
    public Date endDate;
    public Date startDate;

    public TimesheetReportVO() {
    }

    public TimesheetReportVO(String employeeName, String approverName, Date startDate, Date endDate) {
        this.approverName = approverName;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getApproverName() {
        return approverName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
