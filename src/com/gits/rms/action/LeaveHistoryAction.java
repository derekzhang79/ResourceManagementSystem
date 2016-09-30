
package com.gits.rms.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;
import com.gits.rms.vo.LeaveTypeVO;

public class LeaveHistoryAction extends ActionSupport {
    private static final long serialVersionUID = -7711128194066353191L;
    private LeaveHistoryService leaveHistoryService = new LeaveHistoryDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private List<LeaveHistoryVO> lHistList;
    private LeaveHistoryVO lhist;
    private LeaveReqsApprovalVO lrapp;
    private List<EmployeesVO> empIdObj;
    private EmployeesVO emps;
    private List<LeaveTypeVO> leaveTypeIdObj;
    private LeaveTypeVO ltype;

    // It Show All Leave Hisory of particular employee
    public String getAllLeaveHistory() {
        lHistList = leaveHistoryService.getAllLeaveHistory();
        return SUCCESS;
    }

    // It shows blank form to search Leave History of Approver and their
    // SubEmployee
    public String setUpSubEmpLeaveHistory() {
        employee();
        // leave();
        return SUCCESS;

    }

    public String setUpLeaveHistory() {
        if ((lhist != null) && (lhist.getHcmoLeaveHistoryId() != null)) {
            lhist = leaveHistoryService.getLeaveHistory(lhist.getHcmoLeaveHistoryId());
        }
        return SUCCESS;
    }

    // It shows Employee Approver and their SubEmployee List
    private void employee() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        List employeeIdList = new LinkedList();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empIdObj = employeeService.getCurrentSubEmployee();

        EmployeesVO employee = new EmployeesVO();
        for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
            employee = it.next();
            employeeIdList.add(employee.getEmployeeId());
        }
        if (employeeIdList.contains(empSelf.getEmployeeId())) {
            request.setAttribute("empsList", empIdObj);
        } else {
            empIdObj.add(empSelf);
            request.setAttribute("empsList", empIdObj);
        }
    }

    // It shows History search result of particular Employee
    @SkipValidation
    public String getAllLeaveHistorySearch() {
        lHistList = leaveHistoryService.getLeaveHistorySearch(lhist);
        for (int i = 0; i < lHistList.size(); i++) {
            LeaveHistoryVO lhist = lHistList.get(i);
            String noOfDays = lhist.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lhist.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lhist.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lhist.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        return SUCCESS;
    }

    // It shows Approver and their SubEmployee Leave History List
    public String getSubEmpLeaveHistorySearch() {
        lHistList = leaveHistoryService.getSubEmpLeaveHistorySearch(lhist);
        for (int i = 0; i < lHistList.size(); i++) {
            LeaveHistoryVO lhist = lHistList.get(i);
            String noOfDays = lhist.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lhist.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lhist.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lhist.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        return SUCCESS;
    }

    // When new LeaveRequsetApproval added it enter same data into LeaveHistory
    public String insertOrUpdateLeaveHistory() {
        if (lhist.getHcmoLeaveHistoryId() == null) {
            lhist.getLeaveStartDate();
            lhist.getLeaveEndDate();
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            lhist.setLeaveStatus((getText("select.common.leaveReqStatus.approved.value")));
            lhist.setCreated(DateUtils.getCurrentDateTime());
            lhist.setCreatedBy(oEmp);
            lhist.setUpdatedBy(oEmp);
            lhist.setIsActive(1);
            leaveHistoryService.insertLeaveHistory(lhist);
        } else {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            lhist.setUpdatedBy(oEmp);
            leaveHistoryService.updateLeaveHistory(lhist);
        }
        return SUCCESS;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public EmployeesVO getEmps() {
        return emps;
    }

    public void setEmps(EmployeesVO emps) {
        this.emps = emps;
    }

    public LeaveHistoryVO getLhist() {
        return lhist;
    }

    public void setLhist(LeaveHistoryVO lhist) {
        this.lhist = lhist;
    }

    public LeaveTypeVO getLtype() {
        return ltype;
    }

    public void setLtype(LeaveTypeVO ltype) {
        this.ltype = ltype;
    }

    public List<LeaveTypeVO> getLeaveTypeIdObj() {
        return leaveTypeIdObj;
    }

    public void setLeaveTypeIdObj(List<LeaveTypeVO> leaveTypeIdObj) {
        this.leaveTypeIdObj = leaveTypeIdObj;
    }

    public LeaveReqsApprovalVO getLrapp() {
        return lrapp;
    }

    public void setLrapp(LeaveReqsApprovalVO lrapp) {
        this.lrapp = lrapp;
    }

    public List<LeaveHistoryVO> getLHistList() {
        return lHistList;
    }

    public void setLHistList(List<LeaveHistoryVO> histList) {
        lHistList = histList;
    }
}
