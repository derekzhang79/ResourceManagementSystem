
package com.gits.rms.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.PayStubArchiveVO;

public class PayStubArchiveHibernateDao implements PayStubArchiveDao {

    @Override
    public void insertPayStubArchive(PayStubArchiveVO payStubArchive) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(payStubArchive);
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
}