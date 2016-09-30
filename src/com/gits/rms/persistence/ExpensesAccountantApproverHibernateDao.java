
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ExpensesAccountantApproverVO;

public class ExpensesAccountantApproverHibernateDao implements ExpensesAccountantApproverDao {

    private List<ExpensesAccountantApproverVO> expAccountantApproverList;
    private ExpensesAccountantApproverVO approverAccountantCheck = new ExpensesAccountantApproverVO();

    @Override
    public List getAllExpensesAccountantApprover() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesAccountantApproverVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List accountantSearchResult(ExpensesAccountantApproverVO empAcc) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            String empFirstName = empAcc.getEmpFirstName();
            Query query = session.createQuery("from ExpensesAccountantApproverVO acc left join fetch acc.expensesAccountantId as emp "
                + "where acc.isActive=:IsActive and acc.expensesAccountantId in(from emp.employeeId where emp.empFirstName like '%%%"
                + empFirstName + "%%%' and emp.isActive=:IsActive )");

            query.setInteger("IsActive", 1);
            expAccountantApproverList = query.list();
            return expAccountantApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllExpAccountantApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAccountantApproverVO as e where e.isActive=:IsActive and e.expensesAccountantId.employeeId=:ExpensesAccountantId");
            query.setInteger("ExpensesAccountantId", id);
            query.setInteger("IsActive", 1);
            expAccountantApproverList = query.list();
            return expAccountantApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployeesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAccountantApproverVO as ea where and ea.isActive=:IsActive ");
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                it.next();
            }
            expAccountantApproverList = query.list();
            return expAccountantApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesAccountantApproverVO getExpensesAccountantApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesAccountantApproverVO as e where e.hcmoExpensesAccountantId =:HcmoExpensesAccountantId");
            q.setInteger("HcmoExpensesAccountantId", id);
            return (ExpensesAccountantApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesAccountantApproverVO getEmpExpenseAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesAccountantApproverVO as e left join fetch e.hcmoEmployeeId where e.hcmoExpensesAccountantId =:HcmoExpensesAccountantId and e.isActive=:IsActive");
            q.setInteger("HcmoExpensesAccountantId", expAccountantApprover.getHcmoExpensesAccountantId());
            q.setInteger("IsActive", 1);
            return (ExpensesAccountantApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public ExpensesAccountantApproverVO getEmployeeExpenseAccountantApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesAccountantApproverVO as e where e.hcmoExpensesAccountantId =:HcmoExpensesAccountantId");
            q.setInteger("HcmoExpensesAccountantId", id);
            return (ExpensesAccountantApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(expAccountantApprover);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isUnique = true;
            throw e;

        } finally {
            if (isUnique) {
                session.close();
            } else {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void updateExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesAccountantApproverVO set expensesAccountantId=:ExpensesAccountantId, "
                + "UpdatedBy=:UpdatedBy "
                + "where hcmoExpensesAccountantId=:HcmoExpensesAccountantId";
            Query query = session.createQuery(sHql);
            query.setInteger("ExpensesAccountantId", expAccountantApprover.getExpensesAccountantId().getEmployeeId());
            query.setInteger("UpdatedBy", expAccountantApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoExpensesAccountantId", expAccountantApprover.getHcmoExpensesAccountantId());
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
    public void deleteExpensesAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesAccountantApproverVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoExpensesAccountantId=:HcmoExpensesAccountantId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", expAccountantApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoExpensesAccountantId", expAccountantApprover.getHcmoExpensesAccountantId());
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
    public Integer checkExpensesAccountantApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from ExpensesAccountantApproverVO as ea where ea.expensesAccountantId =:ExpensesAccountantId and ea.isActive=:IsActive");
            query.setInteger("ExpensesAccountantId", id);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer checkAccountantExists(ExpensesAccountantApproverVO empAcc) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesAccountantApproverVO.class);
            crit.add(Restrictions.eq("expensesAccountantId.employeeId", empAcc.getExpensesAccountantId().getEmployeeId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list().size();
        } finally {
            session.flush();
            session.close();
        }
    }
}
