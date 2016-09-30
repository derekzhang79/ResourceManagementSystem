
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmployeesDao;
import com.gits.rms.persistence.EmployeesHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ReportsVO;

public class EmployeesDaoService implements EmployeesService {
    private EmployeesDao dao;

    public EmployeesDaoService() {
        dao = new EmployeesHibernateDao();
    }

    @Override
    public void deleteEmployees(EmployeesVO employee) {
        dao.deleteEmployees(employee);
    }

    @Override
    public void deleteEmployeesPic(EmployeesVO employee) {
        dao.deleteEmployeesPic(employee);
    }

    @Override
    public List employeeSearchResult(EmployeesVO employee) {
        return dao.employeeSearchResult(employee);
    }

    @Override
    public List<EmployeesVO> getAdminEmpListSize(Integer id) {
        return dao.getAdminEmpListSize(id);
    }

    @Override
    public List getAllEmployees(Integer clientId) {
        return dao.getAllEmployees(clientId);
    }

    @Override
    public List<EmployeesVO> getAllEmployeesGrouped() {
        return dao.getAllEmployeesGrouped();
    }

    @Override
    public List getCurrentEmployee() {
        return dao.getCurrentEmployee();
    }

    @Override
    public List getCurrentExpensesEmployee(int expenseID) {
        return dao.getCurrentExpensesEmployee(expenseID);
    }

    @Override
    public EmployeeExpensesVO getCurrentExpensesEmployeeForMail(int expenseID) {
        return dao.getCurrentExpensesEmployeeForMail(expenseID);
    }

    @Override
    public List getCurrentExpensesSubEmployee() {
        return dao.getCurrentExpensesSubEmployee();
    }

    @Override
    public List getCurrentSubEmployee() {
        return dao.getCurrentSubEmployee();
    }

    @Override
    public List getCurrentTimeSheetSubEmployee() {
        return dao.getCurrentTimeSheetSubEmployee();
    }

    @Override
    public List getEmployeeBirthDay(Integer clientId) {
        return dao.getEmployeeBirthDay(clientId);
    }

    @Override
    public List getEmployeeBirthdayReports(ReportsVO report) {
        return dao.getEmployeeBirthdayReports(report);
    }

    @Override
    public EmployeesVO getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    @Override
    public List getEmployeeReports(ReportsVO report) {
        return dao.getEmployeeReports(report);
    }

    @Override
    public EmployeesVO getEmployees(Integer id) {
        return dao.getEmployees(id);
    }

    @Override
    public EmployeesVO getOrgChartEmployee(Integer id) {
        return dao.getOrgChartEmployee(id);
    }

    @Override
    public List getReportingToSubEmpList() {
        return dao.getReportingToSubEmpList();
    }

    @Override
    public List getSelectedImportantNotes(Integer clientId) {
        return dao.getSelectedImportantNotes(clientId);
    }

    @Override
    public List getTodaysBirthDayEmployeeList() {
        return dao.getTodaysBirthDayEmployeeList();
    }

    @Override
    public EmployeesVO insertEmployees(EmployeesVO employee) {
         return dao.insertEmployees(employee);
    }

    @Override
    public void updateEmployees(EmployeesVO employee) {
        dao.updateEmployees(employee);
    }

    @Override
    public void uploadEmployeesPic(EmployeesVO employee) {
        dao.uploadEmployeesPic(employee);
    }
    
    @Override
    public EmployeesVO getEmployeeByEmailId(String emailId){
    	return dao.getEmployeeByEmailId(emailId);
    }

	@Override
	public List<EmployeesVO> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesVO updateEmployeeProfile(EmployeesVO employee) {
		// TODO Auto-generated method stub
		return dao.updateEmployeeProfile(employee);
		
	}
}
