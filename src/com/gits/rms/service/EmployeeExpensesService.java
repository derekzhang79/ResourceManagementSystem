
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesAttachmentVO;
import com.gits.rms.vo.ExpensesDetailsVO;

public interface EmployeeExpensesService {

    void approveAndSubmitToAccount(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

    Integer checkExpensesReviewer(Integer id, String ApprovalStatus);

    Integer checkSubmitToNextLevel(Integer id);

    ExpensesDetailsVO editExpensesDetails(Integer id);

    List forApprovedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    List forRejectedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    List forReviewTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    List getAllEmployeeExpenses();

    List getAllExpensesApp(EmployeeExpensesVO empExpenses);

    List getAllExpensesForApproval(EmployeeExpensesVO empExpenses);

    EmployeeExpensesVO getEmployeeExpenses(Integer id);

    List getExpensesForApprovalEmployee(Integer id);

    List getExpenseStatusTrackerForAccountant(Integer id);

    EmployeeExpensesVO getInsertedExpensesInfo(EmployeeExpensesVO empExpenses);

    List getMyReviewExpensesDetails(EmployeeExpensesVO empExpenses);

    void insertEmployeeExpenses(EmployeeExpensesVO empExpenses);

    void insertExpenseAttachments(ExpensesAttachmentVO expenseAttach);

    void insertStatusTracker(ExpenseStatusTrackerVO expensesStatusStracker);

    List myExpensesReview(EmployeeExpensesVO empExpenses);

    void rejectEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

    void reviewEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses);

    void updateEditedExpenses(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses);

    void updateExpensesDetails(ExpensesDetailsVO expenseDetails);

}
