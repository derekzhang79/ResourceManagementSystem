
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ReportsVO;

public interface ExpenseStatusTrackerDao {

    List getAllExpenseStatusTracker();

    void updateExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker);

    void insertExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker);

    void deleteExpenseStatusTracker(Integer id);

    ExpenseStatusTrackerVO getExpenseStatusTracker(Integer id);

    List getExpensesHistReports(ReportsVO report);

    // DashBoard Expense For Approval
    List getDashExpenseForApproval();

    List getDashExpenseForApprovalToday();

    List getDashExpenseForApprovalThreeDays();

    List getDashExpenseForApprovalOneWeek();
}
