
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesDetailsVO;

public class ExpensesAccountantPayableHibernateDao implements ExpensesAccountantPayableDao {

    private List<ExpensesDetailsVO> expensesDetailsList;
    ExpensesDetailsVO expenseDetails;
    private List<EmployeeExpensesVO> expenseList;
    private List<ExpenseStatusTrackerVO> empStatusTrackerList;

    @Override
    public List getAccountantTab(ExpenseStatusTrackerVO expenseStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as eev where eev.isActive=:IsActive and eev.status =:Status");
            query.setInteger("IsActive", 1);
            query.setString("Status", expenseStatusStracker.getApprovalStatus());
            expenseList = query.list();
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
    public List getAccountExpensesDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesDetailsVO as ed where ed.hcmoExpensesId =:HcmoExpensesId and ed.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            q.setInteger("HcmoExpensesId", id);
            expensesDetailsList = q.list();
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
        return expensesDetailsList;
    }

    @Override
    public void insertReimburse(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);

            String sHql = "update EmployeeExpensesVO as eev set eev.status=:status,eev.nextLevelId =:NextLevelId ,eev.reimbursementAmount =:ReimbursementAmount,eev.reimbursementDate=:ReimbursementDate, eev.accountingNotes=:AccountingNotes,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setString("status", empExpenses.getStatus());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
            query.setInteger("NextLevelId", 0);
            query.setDouble("ReimbursementAmount", empExpenses.getReimbursementAmount());
            query.setDate("ReimbursementDate", empExpenses.getReimbursementDate());
            query.setString("AccountingNotes", empExpenses.getAccountingNotes());
            query.setInteger("UpdatedBy", empExpenses.getUpdatedBy().getEmployeeId());
            query.executeUpdate();
            tx.commit();
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
    }

    @Override
    public List getExpenseStatusTrackerForAccountant(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.beginTransaction();
            Query q = session.createQuery("from ExpenseStatusTrackerVO as ed where ed.hcmoExpensesId =:HcmoExpensesId");
            q.setInteger("HcmoExpensesId", id);
            empStatusTrackerList = q.list();
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
        return empStatusTrackerList;
    }
}
