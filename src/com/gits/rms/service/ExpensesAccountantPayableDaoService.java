
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesAccountantPayableDao;
import com.gits.rms.persistence.ExpensesAccountantPayableHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public class ExpensesAccountantPayableDaoService implements ExpensesAccountantPayableService {

    private ExpensesAccountantPayableDao dao;

    public ExpensesAccountantPayableDaoService() {
        dao = new ExpensesAccountantPayableHibernateDao();
    }

    @Override
    public List getAccountantTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        return dao.getAccountantTab(expenseStatusStracker, empExpenses);
    }

    @Override
    public List getAccountExpensesDetails(Integer id) {
        return dao.getAccountExpensesDetails(id);
    }

    @Override
    public List getExpenseStatusTrackerForAccountant(Integer id) {
        return dao.getExpenseStatusTrackerForAccountant(id);
    }

    @Override
    public void insertReimburse(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        dao.insertReimburse(expensesStatusStracker, empExpenses);
    }

}
