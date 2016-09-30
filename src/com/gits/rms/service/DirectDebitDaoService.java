
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.DirectDebitDao;
import com.gits.rms.persistence.DirectDebitHibernateDao;
import com.gits.rms.vo.DirectDebitVO;

public class DirectDebitDaoService implements DirectDebitService {

    private DirectDebitDao dao;

    public DirectDebitDaoService() {
        dao = new DirectDebitHibernateDao();
    }

    @Override
    public void deleteDirectDebit(DirectDebitVO dirDebit) {
        dao.deleteDirectDebit(dirDebit);
    }

    @Override
    public List directDebitSearchResult(DirectDebitVO dirDebit) {
        return dao.directDebitSearchResult(dirDebit);
    }

    @Override
    public List getAllDirectDebit() {
        return dao.getAllDirectDebit();
    }
    
    @Override
    public List getAllSubEmployeeDirectDebitList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeDirectDebitList(empReportToEmpId);
    }

    @Override
    public List<DirectDebitVO> getAllDirectDebitForAEmp(DirectDebitVO dirDebit) {
        return dao.getAllDirectDebitForAEmp(dirDebit);
    }

    @Override
    public DirectDebitVO getDirectDebit(Integer id) {
        return dao.getDirectDebit(id);
    }

    @Override
    public DirectDebitVO getEmpDirectDebit(DirectDebitVO dirDebit) {
        return dao.getEmpDirectDebit(dirDebit);
    }

    @Override
    public List<DirectDebitVO> getEmployeeAllDirectDebit(Integer employeeId) {
        return dao.getEmployeeAllDirectDebit(employeeId);
    }

    @Override
    public void insertDirectDebit(DirectDebitVO dirDebit) {
        dao.insertDirectDebit(dirDebit);
    }

    @Override
    public void updateDirectDebit(DirectDebitVO dirDebit) {
        dao.updateDirectDebit(dirDebit);
    }
}
