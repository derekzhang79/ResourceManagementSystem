
package com.gits.rms.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.SupportVO;

public class SupportHibernateDao implements SupportDao {

    @Override
    public void insertSupport(SupportVO supportObj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(supportObj);
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
    public SupportVO getSupport(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SupportVO as supp where supp.supportId =:SupportId and supp.isActive=:IsActive");
            q.setInteger("SupportId", id);
            q.setInteger("IsActive", 1);
            return (SupportVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

}
