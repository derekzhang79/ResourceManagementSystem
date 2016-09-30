
package com.gits.rms.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;

public class ExpensesApproveAndSubmitNextLevelHibernateDao implements
    ExpensesApproveAndSubmitNextLevelDao {

    @Override
    public void approveAndSubmitToNextLevel(ExpenseStatusTrackerVO expensesStatusStracker, EmployeeExpensesVO empExpenses) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expensesStatusStracker);

            String sHql = "update EmployeeExpensesVO as eev set eev.nextLevelId=:NextLevelId,eev.updatedBy=:UpdatedBy where eev.hcmoExpensesId=:HcmoExpensesId";
            Query query = session.createQuery(sHql);
            query.setInteger("NextLevelId", empExpenses.getNextLevelId());
            query.setInteger("HcmoExpensesId", empExpenses.getHcmoExpensesId());
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

}
