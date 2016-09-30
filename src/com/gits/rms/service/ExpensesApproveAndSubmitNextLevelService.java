
package com.gits.rms.service;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public interface ExpensesApproveAndSubmitNextLevelService {

    void approveAndSubmitToNextLevel(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

}
