
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.ExpensesDetailsVO;

public class ExpensesDetailsHibernateDao implements ExpensesDetailsDao {

    private List<ExpensesDetailsVO> expDetailsList;

    @Override
    public List getAllExpensesDetails() {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from ExpensesDetailsVO where IsActive=:IsActive order by hcmoExpensesTypeId");
            query.setInteger("IsActive", 1);
            expDetailsList = query.list();
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
        return expDetailsList;
    }

    @Override
    public ExpensesDetailsVO getExpensesDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        Query q;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            q = session.createQuery("from ExpensesDetailsVO as d where d.hcmoExpensesDetailsId =:HcmoExpensesDetailsId");
            q.setInteger("hcmoExpensesDetailsId", id);

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
        return (ExpensesDetailsVO) q.uniqueResult();
    }

    @Override
    public void insertExpensesDetails(ExpensesDetailsVO expDetails) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expDetails);
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
    public void updateExpensesDetails(ExpensesDetailsVO expDetails) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesDetailsVO set Amount=:Amount," + "Note=:Note,"
                + "Description=:Description, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoExpensesDetailsId=:HcmoExpensesDetailsId";
            Query query = session.createQuery(sHql);
            query.setBigDecimal("Amount", expDetails.getAmount());
            query.setString("Note", expDetails.getNote());
            query.setString("Description", expDetails.getDescription());
            query.setInteger("UpdatedBy", expDetails.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoExpensesDetailsId", expDetails.getHcmoExpensesDetailsId());
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
    public void deleteExpensesDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesDetailsVO set IsActive=:IsActive where HcmoExpensesDetailsId=:HcmoExpensesDetailsId";
            Query query = session.createQuery(sHql);
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoExpensesDetailsId", id);
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
    public List getExpensesEmpDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        Query q;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            q = session.createQuery("from ExpensesDetailsVO as d where d.hcmoExpensesId=:hcmoExpensesId");
            q.setInteger("hcmoExpensesId", id);
            expDetailsList = q.list();
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
        return expDetailsList;
    }
}