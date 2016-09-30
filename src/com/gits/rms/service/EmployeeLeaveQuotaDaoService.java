
package com.gits.rms.service;

import java.math.BigDecimal;
import java.util.List;

import com.gits.rms.persistence.EmployeeLeaveQuotaDao;
import com.gits.rms.persistence.EmployeeLeaveQuotaHibernateDao;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public class EmployeeLeaveQuotaDaoService implements EmployeeLeaveQuotaService {
    private EmployeeLeaveQuotaDao dao;

    public EmployeeLeaveQuotaDaoService() {
        dao = new EmployeeLeaveQuotaHibernateDao();
    }

    @Override
    public List getAllEmployeeApprover(List sList) {
        return dao.getAllEmployeeApprover(sList);
    }

    @Override
    public List getAllEmployeeLeaveQuota() {
        return dao.getAllEmployeeLeaveQuota();
    }
    
    @Override
    public List getAllSubEmployeeLeaveQuotaList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeLeaveQuotaList(empReportToEmpId);
    }

    @Override
    public List getAllEmployeeLeaveQuotaYear() {
        return dao.getAllEmployeeLeaveQuotaYear();
    }

    @Override
    public List getCurrentSubEmployeeForLeaveQuota() {
        return dao.getCurrentSubEmployeeForLeaveQuota();
    }

    @Override
    public EmployeeLeaveQuotaVO getEmployeeLeaveQuota(Integer id) {
        return dao.getEmployeeLeaveQuota(id);
    }

    @Override
    public List getEmployeeLeaveQuotaCheckInEmpLeave(EmployeeLeaveQuotaVO empLeave) {
        return dao.getEmployeeLeaveQuotaCheckInEmpLeave(empLeave);
    }

    @Override
    public EmployeeLeaveQuotaVO getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp) {
        return dao.getEmployeeLeaveQuotaList(lrapp);
    }

    @Override
    public List getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp, EmployeesVO emp, Integer year) {
        return dao.getEmployeeLeaveQuotaList(lrapp, emp, year);
    }

    // Already any data present for same employee,leave type and same year
    @Override
    public EmployeeLeaveQuotaVO getEmpPrevYearLeaveQuota(EmployeeLeaveQuotaVO leave, Integer year) {
        return dao.getEmpPrevYearLeaveQuota(leave, year);
    }

    @Override
    public EmployeeLeaveQuotaVO getPreviousLCFwd(EmployeeLeaveQuotaVO empLeaveQuota) {
        return dao.getPreviousLCFwd(empLeaveQuota);
    }

    @Override
    public EmployeeLeaveQuotaVO getPreviousLeaveCarryForward(BigDecimal x) {
        return dao.getPreviousLeaveCarryForward(x);
    }

    @Override
    public List getSingleEmployeeLeaveQuotaList(Integer employeeId) {
        return dao.getSingleEmployeeLeaveQuotaList(employeeId);
    }

    @Override
    public List getSubEmployeeLeaveQuota(Integer empId) {
        return dao.getSubEmployeeLeaveQuota(empId);
    }

    @Override
    public void insertEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {
        dao.insertEmployeeLeaveQuota(empLeaveQuota);
    }

    @Override
    public List leaveQuotaSearchResult(EmployeeLeaveQuotaVO leave) {
        return dao.leaveQuotaSearchResult(leave);
    }

    @Override
    public List leaveQuotaSearchResultForAdminLogin(EmployeeLeaveQuotaVO leave) {
        return dao.leaveQuotaSearchResultForAdminLogin(leave);
    }

    @Override
    public void updateEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {
        dao.updateEmployeeLeaveQuota(empLeaveQuota);
    }

    @Override
    public void updateEmployeeLeaveQuotaLeaveTaken(EmployeeLeaveQuotaVO empLeaveQuota1) {
    }
}
