
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmployeeExpensesDao;
import com.gits.rms.persistence.EmployeeExpensesHibernateDao;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesAttachmentVO;
import com.gits.rms.vo.ExpensesDetailsVO;

public class EmployeeExpensesDaoService implements EmployeeExpensesService {

    private EmployeeExpensesDao dao;

    public EmployeeExpensesDaoService() {
        dao = new EmployeeExpensesHibernateDao();
    }

    @Override
    public void approveAndSubmitToAccount(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {

        dao.approveAndSubmitToAccount(expensesStatusStracker, empExpenses);
    }

    @Override
    public Integer checkExpensesReviewer(Integer id, String ApprovalStatus) {
        return dao.checkExpensesReviewer(id, ApprovalStatus);
    }

    @Override
    public Integer checkSubmitToNextLevel(Integer id) {
        return dao.checkSubmitToNextLevel(id);
    }

    @Override
    public ExpensesDetailsVO editExpensesDetails(Integer id) {
        return dao.editExpensesDetails(id);
    }

    @Override
    public List forApprovedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {

        return dao.forApprovedTab(expenseStatusStracker, empExpenses);
    }

    @Override
    public List forRejectedTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {

        return dao.forRejectedTab(expenseStatusStracker, empExpenses);
    }

    @Override
    public List forReviewTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {

        return dao.forReviewTab(expenseStatusStracker, empExpenses);
    }

    @Override
    public List getAllEmployeeExpenses() {

        return dao.getAllEmployeeExpenses();
    }

    @Override
    public List getAllExpensesApp(EmployeeExpensesVO empExpenses) {
        return dao.getAllExpensesApp(empExpenses);
    }

    @Override
    public List getAllExpensesForApproval(EmployeeExpensesVO empExpenses) {

        return dao.getAllExpensesForApproval(empExpenses);
    }

    @Override
    public EmployeeExpensesVO getEmployeeExpenses(Integer id) {

        return dao.getEmployeeExpenses(id);
    }

    @Override
    public List getExpensesForApprovalEmployee(Integer id) {

        return dao.getExpensesForApprovalEmployee(id);
    }

    @Override
    public List getExpenseStatusTrackerForAccountant(Integer id) {
        return dao.getExpenseStatusTrackerForAccountant(id);
    }

    @Override
    public EmployeeExpensesVO getInsertedExpensesInfo(EmployeeExpensesVO empExpenses) {
        return dao.getInsertedExpensesInfo(empExpenses);
    }

    @Override
    public List getMyReviewExpensesDetails(EmployeeExpensesVO empExpenses) {
        return dao.getMyReviewExpensesDetails(empExpenses);
    }

    @Override
    public void insertEmployeeExpenses(EmployeeExpensesVO empExpenses) {
        dao.insertEmployeeExpenses(empExpenses);
    }

    @Override
    public void insertExpenseAttachments(ExpensesAttachmentVO expenseAttach) {
        dao.insertExpenseAttachments(expenseAttach);
    }

    @Override
    public void insertStatusTracker(ExpenseStatusTrackerVO expensesStatusStracker) {
        dao.insertStatusTracker(expensesStatusStracker);
    }

    @Override
    public List myExpensesReview(EmployeeExpensesVO empExpenses) {
        return dao.myExpensesReview(empExpenses);
    }

    @Override
    public void rejectEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {

        dao.rejectEmployeeExpense(expensesStatusStracker, empExpenses);
    }

    @Override
    public void reviewEmployeeExpense(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {

        dao.reviewEmployeeExpense(expensesStatusStracker, empExpenses);
    }

    @Override
    public void updateEditedExpenses(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        dao.updateEditedExpenses(expenseStatusStracker, empExpenses);
    }

    @Override
    public void updateExpensesDetails(ExpensesDetailsVO expenseDetails) {
        dao.updateExpensesDetails(expenseDetails);
    }
}
