
package com.gits.rms.persistence;

import java.math.BigDecimal;
import java.util.List;

import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;

public interface EmployeeLeaveQuotaDao {
    List getAllEmployeeApprover(List sList);

    List getAllEmployeeLeaveQuota();
    
    List getAllSubEmployeeLeaveQuotaList(List<Integer> empReportToEmpId);

    List getAllEmployeeLeaveQuotaYear();

    List getCurrentSubEmployeeForLeaveQuota();

    EmployeeLeaveQuotaVO getEmployeeLeaveQuota(Integer id);

    List getEmployeeLeaveQuotaCheckInEmpLeave(EmployeeLeaveQuotaVO empLeave);

    EmployeeLeaveQuotaVO getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp);

    List getEmployeeLeaveQuotaList(LeaveReqsApprovalVO lrapp, EmployeesVO emp, Integer year);

    // Already any data present for same employee,leave type and same year
    EmployeeLeaveQuotaVO getEmpPrevYearLeaveQuota(EmployeeLeaveQuotaVO leave, Integer year);

    EmployeeLeaveQuotaVO getPreviousLCFwd(EmployeeLeaveQuotaVO empLeaveQuota);

    EmployeeLeaveQuotaVO getPreviousLeaveCarryForward(BigDecimal x);

    List getSingleEmployeeLeaveQuotaList(Integer employeeId);

    List getSubEmployeeLeaveQuota(Integer empId);

    void insertEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota);

    List leaveQuotaSearchResult(EmployeeLeaveQuotaVO leave);

    List leaveQuotaSearchResultForAdminLogin(EmployeeLeaveQuotaVO leave);

    void updateEmployeeLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota);
}
