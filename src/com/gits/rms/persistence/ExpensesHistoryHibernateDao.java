
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesHistoryVO;

public class ExpensesHistoryHibernateDao implements ExpensesHistoryDao {
    private List<EmployeeExpensesVO> expenseList;
    private List<ExpenseStatusTrackerVO> expenseStatusTrackerList;

    @Override
    public List getExpOwnHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Date StartDate = expHist.getStartDate();
            Date EndDate = expHist.getEndDate();
            if ((StartDate != null) && (EndDate != null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where (ee.createdDate >=:StartDate and ee.createdDate<=:EndDate) and hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("StartDate", expHist.getStartDate());
                query.setDate("EndDate", expHist.getEndDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if (StartDate != null) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate >=:StartDate and hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("StartDate", expHist.getStartDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if (EndDate != null) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate<=:EndDate and hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("EndDate", expHist.getEndDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            }
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public List<ExpenseStatusTrackerVO> getExpHistoryDetails(Integer expenseId) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ExpenseStatusTrackerVO as ed where ed.hcmoExpensesId =:HcmoExpensesId");
            q.setInteger("HcmoExpensesId", expenseId);
            expenseStatusTrackerList = q.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseStatusTrackerList;

    }

    @Override
    public List getExpEmployeeHistorySearchResult(ExpensesHistoryVO expHist, EmployeeExpensesVO empExpense) {
        Session session = HibernateUtil.getSession();
        Map session1 = ActionContext.getContext().getSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Date StartDate = expHist.getStartDate();
            Date EndDate = expHist.getEndDate();
            if ((StartDate != null) && (EndDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() != null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where (ee.createdDate >=:StartDate and ee.createdDate<=:EndDate) or ee.hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("StartDate", expHist.getStartDate());
                query.setDate("EndDate", expHist.getEndDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if ((StartDate != null) && (EndDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() == null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where (ee.createdDate >=:StartDate and ee.createdDate<=:EndDate) and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("StartDate", expHist.getStartDate());
                query.setDate("EndDate", expHist.getEndDate());
                expenseList = query.list();
            } else if ((StartDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() != null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate >=:StartDate and ee.hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("StartDate", expHist.getStartDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if ((StartDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() == null)) {
                EmployeesVO oEmp = (EmployeesVO) session1.get("EMPLOYEE_OBJECT");
                if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))
                    && (session1.get("EXPENSES_ACCOUNTANT").equals("ACCOUNTANT"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate >=:StartDate and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("StartDate", expHist.getStartDate());
                    expenseList = query.list();

                } else if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate >=:StartDate and ee.hcmoEmployeeId in(select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive) or ee.hcmoEmployeeId =:ApprovingEmployeeId and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("StartDate", expHist.getStartDate());
                    query.setInteger("ApprovingEmployeeId", oEmp.getEmployeeId());
                    expenseList = query.list();
                } else {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate >=:StartDate and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("StartDate", expHist.getStartDate());
                    expenseList = query.list();
                }

            } else if ((EndDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() != null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate<=:EndDate and ee.hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setDate("EndDate", expHist.getEndDate());
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if ((EndDate != null)
                && (empExpense.getHcmoEmployeeId().getEmployeeId() == null)) {
                EmployeesVO oEmp = (EmployeesVO) session1.get("EMPLOYEE_OBJECT");
                if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))
                    && (session1.get("EXPENSES_ACCOUNTANT").equals("ACCOUNTANT"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate<=:EndDate and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("EndDate", expHist.getEndDate());
                    expenseList = query.list();

                } else if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate<=:EndDate and ee.hcmoEmployeeId in(select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive) or ee.hcmoEmployeeId =:ApprovingEmployeeId and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("EndDate", expHist.getEndDate());
                    query.setInteger("ApprovingEmployeeId", oEmp.getEmployeeId());
                    expenseList = query.list();
                } else {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.createdDate<=:EndDate and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setDate("EndDate", expHist.getEndDate());
                    expenseList = query.list();
                }

            } else if ((empExpense.getHcmoEmployeeId().getEmployeeId() != null)) {
                Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.hcmoEmployeeId =:HcmoEmployeeId and ee.isActive=:IsActive");
                query.setInteger("IsActive", 1);
                query.setInteger("HcmoEmployeeId", empExpense.getHcmoEmployeeId().getEmployeeId());
                expenseList = query.list();
            } else if ((empExpense.getHcmoEmployeeId().getEmployeeId() == null)
                || (empExpense.getHcmoEmployeeId().getEmployeeId() == null)) {
                EmployeesVO oEmp = (EmployeesVO) session1.get("EMPLOYEE_OBJECT");
                if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))
                    && (session1.get("EXPENSES_ACCOUNTANT").equals("ACCOUNTANT"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    expenseList = query.list();

                } else if ((session1.get("EXPENSES_APPROVER").equals("BOTH"))) {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.hcmoEmployeeId in(select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive) or ee.hcmoEmployeeId =:ApprovingEmployeeId and ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    query.setInteger("ApprovingEmployeeId", oEmp.getEmployeeId());
                    expenseList = query.list();
                } else {
                    Query query = session.createQuery("from EmployeeExpensesVO as ee where ee.isActive=:IsActive");
                    query.setInteger("IsActive", 1);
                    expenseList = query.list();
                }
            }

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseList;
    }

    @Override
    public List<ExpenseStatusTrackerVO> getExpEmployeeHistoryDetails(Integer expenseId) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from ExpenseStatusTrackerVO as ed where ed.hcmoExpensesId =:HcmoExpensesId");
            q.setInteger("HcmoExpensesId", expenseId);
            expenseStatusTrackerList = q.list();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
        return expenseStatusTrackerList;

    }
}
