
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesApproverDao;
import com.gits.rms.persistence.ExpensesApproverHibernateDao;
import com.gits.rms.vo.ExpensesApproverVO;

public class ExpensesApproverDaoService implements ExpensesApproverService {

    private ExpensesApproverDao dao;

    public ExpensesApproverDaoService() {
        dao = new ExpensesApproverHibernateDao();
    }

    @Override
    public Integer checkExpensesApprover(Integer id,Integer clientId) {
        return dao.checkExpensesApprover(id,clientId);
    }

    @Override
    public void deleteExpensesApprover(ExpensesApproverVO expApprover) {
        dao.deleteExpensesApprover(expApprover);

    }

    @Override
    public List expAppSearchResult(ExpensesApproverVO exp) {
        return dao.expAppSearchResult(exp);
    }

    @Override
    public List getAllEmployeesApprover(ExpensesApproverVO expApprover) {
        return dao.getAllEmployeesApprover(expApprover);
    }

    @Override
    public List getAllExpEmpForApproverSearch(ExpensesApproverVO expApprover) {
        return dao.getAllExpEmpForApproverSearch(expApprover);
    }

    @Override
    public List getAllExpensesApprover() {
        return dao.getAllExpensesApprover();
    }

    @Override
    public List<ExpensesApproverVO> getAllSubEmployee(Integer id) {
        return dao.getAllSubEmployee(id);
    }

    @Override
    public ExpensesApproverVO getEmpExpensesApprover(ExpensesApproverVO expApprover) {
        return dao.getEmpExpenseApprover(expApprover);
    }

    @Override
    public List<ExpensesApproverVO> getEmployeeAllExpApprover(Integer employeeId) {
        return dao.getEmployeeAllExpApprover(employeeId);
    }

    @Override
    public Integer getExpApproverCount(ExpensesApproverVO expApprover) {
        return dao.getExpApproverCount(expApprover);
    }

    @Override
    public ExpensesApproverVO getExpensesApprover(Integer id) {
        return dao.getExpensesApprover(id);
    }

    @Override
    public ExpensesApproverVO getSelfApprover(Integer id) {
        return dao.getSelfApprover(id);
    }

    @Override
    public void insertExpensesApprover(ExpensesApproverVO expApprover) {
        dao.insertExpensesApprover(expApprover);
    }

    @Override
    public void updateExpensesApprover(ExpensesApproverVO expApprover) {
        dao.updateExpensesApprover(expApprover);
    }

}
