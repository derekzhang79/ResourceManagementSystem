
package com.gits.rms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ReportsVO implements Serializable {
    private static final long serialVersionUID = -3693273426861513710L;
    private List<CustomerVO> custIdObjList = new LinkedList<CustomerVO>();
    private Date date;
    private List<EmployeesVO> empIdObjList = new LinkedList<EmployeesVO>();
    private Date fromDate;
    private String leaveStatus;
    private List<ProjectActivityVO> projActivityIdObjList = new LinkedList<ProjectActivityVO>();
    private List<ProjectVO> projIdObjList = new LinkedList<ProjectVO>();
    private String timeSheetType;
    private Date toDate;
    private EmployeesVO empObj;
    private ProjectVO projObj;
    private ProjectActivityVO projActivityObj;
    private CustomerVO cust;

    public ReportsVO() {
    }

    public ReportsVO(CustomerVO cust, List<CustomerVO> custIdObjList, Date date,
        List<EmployeesVO> empIdObjList, Date fromDate, String leaveStatus,
        List<ProjectActivityVO> projActivityIdObjList, List<ProjectVO> projIdObjList,
        String timeSheetType, Date toDate, EmployeesVO empObj, ProjectVO projObj, ProjectActivityVO projActivityObj) {
        super();
        this.cust = cust;
        this.custIdObjList = custIdObjList;
        this.date = date;
        this.empIdObjList = empIdObjList;
        this.fromDate = fromDate;
        this.leaveStatus = leaveStatus;
        this.projActivityIdObjList = projActivityIdObjList;
        this.projIdObjList = projIdObjList;
        this.timeSheetType = timeSheetType;
        this.toDate = toDate;
        this.empObj = empObj;
        this.projObj = projObj;
        this.projActivityObj = projActivityObj;
    }

    public CustomerVO getCust() {
        return cust;
    }

    public List<CustomerVO> getCustIdObjList() {
        return custIdObjList;
    }

    public Date getDate() {
        return date;
    }

    public List<EmployeesVO> getEmpIdObjList() {
        return empIdObjList;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public List<ProjectActivityVO> getProjActivityIdObjList() {
        return projActivityIdObjList;
    }

    public List<ProjectVO> getProjIdObjList() {
        return projIdObjList;
    }

    public String getTimeSheetType() {
        return timeSheetType;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setCust(CustomerVO cust) {
        this.cust = cust;
    }

    public void setCustIdObjList(List<CustomerVO> custIdObjList) {
        this.custIdObjList = custIdObjList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEmpIdObjList(List<EmployeesVO> empIdObjList) {
        this.empIdObjList = empIdObjList;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public void setProjActivityIdObjList(List<ProjectActivityVO> projActivityIdObjList) {
        this.projActivityIdObjList = projActivityIdObjList;
    }

    public void setProjIdObjList(List<ProjectVO> projIdObjList) {
        this.projIdObjList = projIdObjList;
    }

    public void setTimeSheetType(String timeSheetType) {
        this.timeSheetType = timeSheetType;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

	public EmployeesVO getEmpObj() {
		return empObj;
	}

	public void setEmpObj(EmployeesVO empObj) {
		this.empObj = empObj;
	}

	public ProjectVO getProjObj() {
		return projObj;
	}

	public void setProjObj(ProjectVO projObj) {
		this.projObj = projObj;
	}

	public ProjectActivityVO getProjActivityObj() {
		return projActivityObj;
	}

	public void setProjActivityObj(ProjectActivityVO projActivityObj) {
		this.projActivityObj = projActivityObj;
	}
}
