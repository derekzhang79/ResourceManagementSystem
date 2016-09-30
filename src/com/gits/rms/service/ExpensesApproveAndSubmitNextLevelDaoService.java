
package com.gits.rms.service;

import com.gits.rms.persistence.ExpensesApproveAndSubmitNextLevelDao;
import com.gits.rms.persistence.ExpensesApproveAndSubmitNextLevelHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public class ExpensesApproveAndSubmitNextLevelDaoService implements
    ExpensesApproveAndSubmitNextLevelService {

    private ExpensesApproveAndSubmitNextLevelDao dao;

    public ExpensesApproveAndSubmitNextLevelDaoService() {
        dao = new ExpensesApproveAndSubmitNextLevelHibernateDao();
    }

    @Override
    public void approveAndSubmitToNextLevel(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {

        dao.approveAndSubmitToNextLevel(expensesStatusStracker, empExpenses);
    }

}
