
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesHistoryDao;
import com.gits.rms.persistence.ExpensesHistoryHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesHistoryVO;

public class ExpensesHistoryDaoService implements ExpensesHistoryService {
    private ExpensesHistoryDao dao;

    public ExpensesHistoryDaoService() {
        dao = new ExpensesHistoryHibernateDao();
    }

    @Override
    public List<ExpenseStatusTrackerVO> getExpEmployeeHistoryDetails(Integer expenseId) {
        return dao.getExpEmployeeHistoryDetails(expenseId);
    }

    @Override
    public List getExpEmployeeHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense) {
        return dao.getExpEmployeeHistorySearchResult(expHist, empExpense);
    }

    @Override
    public List<ExpenseStatusTrackerVO> getExpHistoryDetails(Integer expenseId) {
        return dao.getExpHistoryDetails(expenseId);
    }

    @Override
    public List getExpOwnHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense) {
        return dao.getExpOwnHistorySearchResult(expHist, empExpense);
    }
}
