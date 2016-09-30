
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public interface LeaveReqsApprovalDao {
    List getAllLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    LeaveReqsApprovalVO getLeaveReqsApproval(Integer id);

    void update(LeaveReqsApprovalVO lrapp);

    void insert(LeaveReqsApprovalVO lrapp);

    void approve(LeaveReqsApprovalVO lrapp);

    void searchLeaveRequest(LeaveReqsApprovalVO lrapp);

    void delete(Integer id);

    void approved(LeaveReqsApprovalVO lrappt, EmployeeLeaveQuotaVO empLeaveQuotall, LeaveHistoryVO lhist);

    // void disApproved(LeaveReqsApprovalVO lrapp);
    void disApproved(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist);

    List employeeLeaveApproved(LeaveReqsApprovalVO lrapp);

    List getLeaveForApprovalEmployee(Integer id);

    List getAllApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllDisApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpDisApprovedList(LeaveReqsApprovalVO lrapp);

    LeaveApproverVO getApproverId(Integer empId);

    List getAllForApprovalList(LeaveReqsApprovalVO lrapp);

    // DashBoard Leave For Approval
    List getDashLeaveForApproval();

    List getDashLeaveForApprovalToday();

    List getDashLeaveForApprovalThreeDays();

    List getDashLeaveForApprovalOneWeek();

    List getLeaveReqsList(EmployeeLeaveQuotaVO empLeave);

    // Assign Leave
    void assigned(EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist);

    List getAllEmpAssignedList(LeaveReqsApprovalVO lrapp);

    // Cancel Leave
    void cancel(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist);

    List getAllEmpCancelList(LeaveReqsApprovalVO lrapp);
}
