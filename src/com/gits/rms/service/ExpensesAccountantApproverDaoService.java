
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesAccountantApproverDao;
import com.gits.rms.persistence.ExpensesAccountantApproverHibernateDao;
import com.gits.rms.vo.ExpensesAccountantApproverVO;

public class ExpensesAccountantApproverDaoService implements ExpensesAccountantApproverService {

    private ExpensesAccountantApproverDao dao;

    public ExpensesAccountantApproverDaoService() {
        dao = new ExpensesAccountantApproverHibernateDao();
    }

    @Override
    public List accountantSearchResult(ExpensesAccountantApproverVO empAcc) {
        return dao.accountantSearchResult(empAcc);
    }

    @Override
    public Integer checkAccountantExists(ExpensesAccountantApproverVO empAcc) {
        return dao.checkAccountantExists(empAcc);
    }

    @Override
    public Integer checkExpensesAccountantApprover(Integer id) {
        return dao.checkExpensesAccountantApprover(id);
    }

    @Override
    public void deleteExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        dao.deleteExpensesAccountantApprover(expAccountantApprover);
    }

    @Override
    public List getAllEmployeesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        return dao.getAllEmployeesAccountantApprover(expAccountantApprover);
    }

    @Override
    public List getAllExpensesAccountantApprover() {
        return dao.getAllExpensesAccountantApprover();
    }

    @Override
    public ExpensesAccountantApproverVO getEmpExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        return dao.getEmpExpenseAccountantApprover(expAccountantApprover);
    }

    @Override
    public List<ExpensesAccountantApproverVO> getEmployeeAllExpAccountantApprover(Integer employeeId) {
        return dao.getEmployeeAllExpAccountantApprover(employeeId);
    }

    @Override
    public ExpensesAccountantApproverVO getExpensesAccountantApprover(Integer id) {
        return dao.getExpensesAccountantApprover(id);
    }

    @Override
    public void insertExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        dao.insertExpensesAccountantApprover(expAccountantApprover);
    }

    @Override
    public void updateExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        dao.updateExpensesAccountantApprover(expAccountantApprover);
    }
}
