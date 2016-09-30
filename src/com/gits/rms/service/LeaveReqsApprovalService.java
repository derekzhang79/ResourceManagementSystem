
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public interface LeaveReqsApprovalService {

    void approved(LeaveReqsApprovalVO lrapp, EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist);

    void approveLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    // Assign Leave
    void assigned(EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist);

    // Cancel Leave
    void cancel(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist);

    void deleteLeaveReqsApproval(Integer id);

    void disApproved(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist);

    List employeeLeaveApproved(LeaveReqsApprovalVO lrapp);

    List getAllApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllDisApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllEmpAssignedList(LeaveReqsApprovalVO lrapp);

    List getAllEmpCancelList(LeaveReqsApprovalVO lrapp);

    List getAllForApprovalList(LeaveReqsApprovalVO lrapp);

    List getAllLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpDisApprovedList(LeaveReqsApprovalVO lrapp);

    List getAllSubEmpLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    LeaveApproverVO getApproverId(Integer empId);

    // DashBoard Leave For Approval
    List getDashLeaveForApproval();

    List getDashLeaveForApprovalOneWeek();

    List getDashLeaveForApprovalThreeDays();

    List getDashLeaveForApprovalToday();

    List getLeaveForApprovalEmployee(Integer id);

    LeaveReqsApprovalVO getLeaveReqsApproval(Integer id);

    List getLeaveReqsList(EmployeeLeaveQuotaVO empLeave);

    void insertLeaveReqsApproval(LeaveReqsApprovalVO lrapp);

    void searchLeaveRequest(LeaveReqsApprovalVO lrapp);

    void updateLeaveReqsApproval(LeaveReqsApprovalVO lrapp);
}
