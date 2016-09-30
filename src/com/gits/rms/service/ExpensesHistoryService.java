
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesHistoryVO;

public interface ExpensesHistoryService {

    List<ExpenseStatusTrackerVO> getExpEmployeeHistoryDetails(Integer expenseId);

    List getExpEmployeeHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense);

    List<ExpenseStatusTrackerVO> getExpHistoryDetails(Integer expenseId);

    List getExpOwnHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense);
}
