
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.SalaryDao;
import com.gits.rms.persistence.SalaryHibernateDao;
import com.gits.rms.vo.SalaryVO;

public class SalaryDaoService implements SalaryService {
    private SalaryDao dao;

    public SalaryDaoService() {
        dao = new SalaryHibernateDao();
    }

    @Override
    public void deleteSalary(SalaryVO sal) {
        dao.deleteSalary(sal);
    }

    @Override
    public List getAllSalary() {
        return dao.getAllSalary();
    }

    @Override
    public List<SalaryVO> getEmployeeAllSalary(Integer employeeId) {
        return dao.getEmployeeAllSalary(employeeId);
    }

    @Override
    public SalaryVO getEmpSalary(SalaryVO sal) {
        return dao.getEmpSalary(sal);
    }

    @Override
    public SalaryVO getSalary(Integer id) {
        return dao.getSalary(id);
    }

    @Override
    public void insertSalary(SalaryVO sal) {
        dao.insertSalary(sal);
    }

    @Override
    public List salarySearchResult(SalaryVO sal) {
        return dao.salarySearchResult(sal);
    }

    @Override
    public void updateSalary(SalaryVO sal) {
        dao.updateSalary(sal);
    }
}
