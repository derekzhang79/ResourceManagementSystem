
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ReportsVO;

public interface ExpenseStatusTrackerService {

    void deleteExpenseStatusTracker(Integer id);

    List getAllExpenseStatusTracker();

    // DashBoard Expense For Approval
    List getDashExpenseForApproval();

    List getDashExpenseForApprovalOneWeek();

    List getDashExpenseForApprovalThreeDays();

    List getDashExpenseForApprovalToday();

    List getExpensesHistReports(ReportsVO report);

    ExpenseStatusTrackerVO getExpenseStatusTracker(Integer id);

    void insertExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker);

    void updateExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker);
}
