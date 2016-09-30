
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.LeaveReqsApprovalDao;
import com.gits.rms.persistence.LeaveReqsApprovalHibernateDao;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public class LeaveReqsApprovalDaoService implements LeaveReqsApprovalService {
    private LeaveReqsApprovalDao dao;

    public LeaveReqsApprovalDaoService() {
        dao = new LeaveReqsApprovalHibernateDao();
    }

    @Override
    public void approved(LeaveReqsApprovalVO lrapp, EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist) {
        dao.approved(lrapp, empLeaveQuota, lhist);
    }

    @Override
    public void approveLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        dao.approve(lrapp);
    }

    // Assign Leave
    @Override
    public void assigned(EmployeeLeaveQuotaVO empLeaveQuota, LeaveHistoryVO lhist) {
        dao.assigned(empLeaveQuota, lhist);
    }

    // Cancel Leave
    @Override
    public void cancel(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist) {
        dao.cancel(lrapp, lhist);
    }

    @Override
    public void deleteLeaveReqsApproval(Integer id) {
        dao.delete(id);
    }

    @Override
    public void disApproved(LeaveReqsApprovalVO lrapp, LeaveHistoryVO lhist) {
        dao.disApproved(lrapp, lhist);
    }

    @Override
    public List employeeLeaveApproved(LeaveReqsApprovalVO lrapp) {
        return dao.employeeLeaveApproved(lrapp);
    }

    @Override
    public List getAllApprovedList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllApprovedList(lrapp);
    }

    @Override
    public List getAllDisApprovedList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllDisApprovedList(lrapp);
    }

    @Override
    public List getAllEmpAssignedList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllEmpAssignedList(lrapp);
    }

    @Override
    public List getAllEmpCancelList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllEmpCancelList(lrapp);
    }

    @Override
    public List getAllForApprovalList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllForApprovalList(lrapp);
    }

    @Override
    public List getAllLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        return dao.getAllLeaveReqsApproval(lrapp);
    }

    @Override
    public List getAllSubEmpApprovedList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllSubEmpApprovedList(lrapp);
    }

    @Override
    public List getAllSubEmpDisApprovedList(LeaveReqsApprovalVO lrapp) {
        return dao.getAllSubEmpDisApprovedList(lrapp);
    }

    @Override
    public List getAllSubEmpLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        return dao.getAllSubEmpLeaveReqsApproval(lrapp);
    }

    @Override
    public LeaveApproverVO getApproverId(Integer empId) {
        return dao.getApproverId(empId);
    }

    // DashBoard Leave For Approval
    @Override
    public List getDashLeaveForApproval() {
        return dao.getDashLeaveForApproval();
    }

    @Override
    public List getDashLeaveForApprovalOneWeek() {
        return dao.getDashLeaveForApprovalOneWeek();
    }

    @Override
    public List getDashLeaveForApprovalThreeDays() {
        return dao.getDashLeaveForApprovalThreeDays();
    }

    @Override
    public List getDashLeaveForApprovalToday() {
        return dao.getDashLeaveForApprovalToday();
    }

    @Override
    public List getLeaveForApprovalEmployee(Integer id) {
        return dao.getLeaveForApprovalEmployee(id);
    }

    @Override
    public LeaveReqsApprovalVO getLeaveReqsApproval(Integer id) {
        return dao.getLeaveReqsApproval(id);
    }

    @Override
    public List getLeaveReqsList(EmployeeLeaveQuotaVO empLeave) {
        return dao.getLeaveReqsList(empLeave);
    }

    @Override
    public void insertLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        dao.insert(lrapp);
    }

    @Override
    public void searchLeaveRequest(LeaveReqsApprovalVO lrapp) {
        dao.approve(lrapp);
    }

    @Override
    public void updateLeaveReqsApproval(LeaveReqsApprovalVO lrapp) {
        dao.update(lrapp);
    }
}
