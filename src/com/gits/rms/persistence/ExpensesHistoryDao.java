
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesHistoryVO;

public interface ExpensesHistoryDao {
    List getExpOwnHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense);

    List<ExpenseStatusTrackerVO> getExpHistoryDetails(Integer expenseId);

    List getExpEmployeeHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense);

    List<ExpenseStatusTrackerVO> getExpEmployeeHistoryDetails(Integer expenseId);
}
