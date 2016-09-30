
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeReportToVO;

public interface EmployeeReportToService {

    void deleteEmployeeReportTo(EmployeeReportToVO emp);

    List empReportToSearchResult(EmployeeReportToVO emp);

    List getAllEmployeeReportTo(Integer clientId);
    
    List getAllSubEmployeeReportToList(List<Integer> empReportToEmpId);

    List<EmployeeReportToVO> getEmployeeAllPreSupervisor(Integer employeeId);

    EmployeeReportToVO getEmployeeReportTo(Integer id);

    List getempRToInvert(EmployeeReportToVO emp);

    void insertEmployeeReportTo(EmployeeReportToVO emp);

    void updateEmployeeReportTo(EmployeeReportToVO emp);
    
    boolean checkLoginEmployeeIsReportToEmp(int empId);
    
    List<Integer> getSubEmployeeList(int empId);
}
