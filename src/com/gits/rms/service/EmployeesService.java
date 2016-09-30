
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ReportsVO;

public interface EmployeesService {

    void deleteEmployees(EmployeesVO employee);

    void deleteEmployeesPic(EmployeesVO employee);

    List employeeSearchResult(EmployeesVO employee);

    List<EmployeesVO> getAdminEmpListSize(Integer id);

    List getAllEmployees(Integer clientId);

    List<EmployeesVO> getAllEmployeesGrouped();

    List getCurrentEmployee();

    List getCurrentExpensesEmployee(int expenseID);

    EmployeeExpensesVO getCurrentExpensesEmployeeForMail(int expenseID);

    List getCurrentExpensesSubEmployee();

    List getCurrentSubEmployee();

    List getCurrentTimeSheetSubEmployee();

    List getEmployeeBirthDay(Integer clientId);

    List getEmployeeBirthdayReports(ReportsVO report);

    EmployeesVO getEmployeeById(int id);

    List getEmployeeReports(ReportsVO report);

    EmployeesVO getEmployees(Integer id);

    EmployeesVO getOrgChartEmployee(Integer id);

    List getReportingToSubEmpList();

    List getSelectedImportantNotes(Integer clientId);

    List getTodaysBirthDayEmployeeList();
    
    EmployeesVO insertEmployees(EmployeesVO employee);
    
    void updateEmployees(EmployeesVO employee);
    
    EmployeesVO updateEmployeeProfile(EmployeesVO employee);

    void uploadEmployeesPic(EmployeesVO employee);
    
    EmployeesVO getEmployeeByEmailId(String emailId);

	List<EmployeesVO> getAllEmployees();
}
