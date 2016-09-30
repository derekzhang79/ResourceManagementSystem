
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpenseStatusTrackerDao;
import com.gits.rms.persistence.ExpenseStatusTrackerHibernateDao;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ReportsVO;

public class ExpenseStatusTrackerDaoService implements ExpenseStatusTrackerService {

    private ExpenseStatusTrackerDao dao;

    public ExpenseStatusTrackerDaoService() {
        dao = new ExpenseStatusTrackerHibernateDao();
    }

    @Override
    public void deleteExpenseStatusTracker(Integer id) {
        dao.deleteExpenseStatusTracker(id);
    }

    @Override
    public List getAllExpenseStatusTracker() {
        return dao.getAllExpenseStatusTracker();
    }

    // DashBoard Expense For Approval
    @Override
    public List getDashExpenseForApproval() {
        return dao.getDashExpenseForApproval();
    }

    @Override
    public List getDashExpenseForApprovalOneWeek() {
        return dao.getDashExpenseForApprovalOneWeek();
    }

    @Override
    public List getDashExpenseForApprovalThreeDays() {
        return dao.getDashExpenseForApprovalThreeDays();
    }

    @Override
    public List getDashExpenseForApprovalToday() {
        return dao.getDashExpenseForApprovalToday();
    }

    @Override
    public List getExpensesHistReports(ReportsVO report) {
        return dao.getExpensesHistReports(report);
    }

    @Override
    public ExpenseStatusTrackerVO getExpenseStatusTracker(Integer id) {
        return dao.getExpenseStatusTracker(id);
    }

    @Override
    public void insertExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker) {
        dao.insertExpenseStatusTracker(expStatusTracker);
    }

    @Override
    public void updateExpenseStatusTracker(ExpenseStatusTrackerVO expStatusTracker) {
        updateExpenseStatusTracker(expStatusTracker);
    }

}