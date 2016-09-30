
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public interface ExpensesAccountantPayableDao {

    List getExpenseStatusTrackerForAccountant(Integer id);

    List getAccountantTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    List getAccountExpensesDetails(Integer id);

    void insertReimburse(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

}
