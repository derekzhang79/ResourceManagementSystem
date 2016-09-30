
package com.gits.rms.persistence;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public interface ExpensesApproveAndSubmitNextLevelDao {

    void approveAndSubmitToNextLevel(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

}
