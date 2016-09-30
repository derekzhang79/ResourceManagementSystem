
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public interface ExpensesAccountantPayableService {

    List getAccountantTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    List getAccountExpensesDetails(Integer id);

    List getExpenseStatusTrackerForAccountant(Integer id);

    void insertReimburse(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

}
