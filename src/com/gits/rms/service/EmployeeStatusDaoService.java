
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmployeeStatusDao;
import com.gits.rms.persistence.EmployeeStatusHibernateDao;
import com.gits.rms.vo.EmployeeStatusVO;

public class EmployeeStatusDaoService implements EmployeeStatusService {

    private EmployeeStatusDao dao;

    public EmployeeStatusDaoService() {
        dao = new EmployeeStatusHibernateDao();
    }

    @Override
    public List checkEmployeeStatusInEmployee(EmployeeStatusVO empStatus) {
        return dao.checkEmployeeStatusInEmployee(empStatus);
    }

    @Override
    public void deleteEmployeeStatus(EmployeeStatusVO empStatus) {
        dao.deleteEmployeeStatus(empStatus);
    }

    @Override
    public List employeeStatusSearchResult(EmployeeStatusVO empStatus) {
        return dao.employeeStatusSearchResult(empStatus);
    }

    @Override
    public List getAllEmployeeStatus() {
        return dao.getAllEmployeeStatus();
    }

    @Override
    public EmployeeStatusVO getEmployeeStatus(Integer id) {
        return dao.getEmployeeStatus(id);
    }

    @Override
    public void insertEmployeeStatus(EmployeeStatusVO empStatus) {
        dao.insertEmployeeStatus(empStatus);
    }

    @Override
    public void updateEmployeeStatus(EmployeeStatusVO empStatus) {
        dao.updateEmployeeStatus(empStatus);
    }
}
