
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmployeeReportToDao;
import com.gits.rms.persistence.EmployeeReportToHibernateDao;
import com.gits.rms.vo.EmployeeReportToVO;

public class EmployeeReportToDaoService implements EmployeeReportToService {

    private EmployeeReportToDao dao;

    public EmployeeReportToDaoService() {
        dao = new EmployeeReportToHibernateDao();
    }

    @Override
    public void deleteEmployeeReportTo(EmployeeReportToVO emp) {
        dao.deleteEmployeeReportTo(emp);
    }

    @Override
    public List empReportToSearchResult(EmployeeReportToVO emp) {
        return dao.empReportToSearchResult(emp);
    }

    @Override
    public List getAllEmployeeReportTo(Integer clientId) {
        return dao.getAllEmployeeReportTo(clientId);
    }
    
    @Override
    public List getAllSubEmployeeReportToList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeReportToList(empReportToEmpId);
    }

    @Override
    public List<EmployeeReportToVO> getEmployeeAllPreSupervisor(Integer employeeId) {
        return dao.getEmployeeAllPreSupervisor(employeeId);
    }

    @Override
    public EmployeeReportToVO getEmployeeReportTo(Integer id) {
        return dao.getEmployeeReportTo(id);
    }

    @Override
    public List getempRToInvert(EmployeeReportToVO emp) {
        return dao.getempRToInvert(emp);
    }

    @Override
    public void insertEmployeeReportTo(EmployeeReportToVO emp) {
        dao.insertEmployeeReportTo(emp);
    }

    @Override
    public void updateEmployeeReportTo(EmployeeReportToVO emp) {
        dao.updateEmployeeReportTo(emp);
    }
    
    @Override
    public boolean checkLoginEmployeeIsReportToEmp(int empId){
    	return dao.checkLoginEmployeeIsReportToEmp(empId);
    }
    
    @Override
    public List<Integer> getSubEmployeeList(int empId){
    	return dao.getSubEmployeeList(empId);
    }
}
