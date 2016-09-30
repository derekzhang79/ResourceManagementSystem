
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeReportToVO;

public interface EmployeeReportToDao {

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
