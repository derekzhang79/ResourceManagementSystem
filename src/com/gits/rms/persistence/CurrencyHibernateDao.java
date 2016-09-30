
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.CurrencyVO;

public class CurrencyHibernateDao implements CurrencyDao {

    @Override
    public List currencySearchResult(CurrencyVO currency) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CurrencyVO.class);
            if (!(currency.getCurrencyType().isEmpty())) {
                crit.add(Restrictions.like("currencyType", currency.getCurrencyType(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteCurrency(CurrencyVO currency) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CurrencyVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoCurrencyId=:HcmoCurrencyId";
            Query query = session.createQuery(sHql);
//            query.setInteger("UpdatedBy", currency.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoCurrencyId", currency.getHcmoCurrencyId());
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
    public List getAllCurrency() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CurrencyVO.class);
            crit.addOrder(Order.asc("currencyType"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public CurrencyVO getCurrency(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from CurrencyVO as currency where currency.hcmoCurrencyId =:hcmoCurrencyId");
            q.setInteger("hcmoCurrencyId", id);
            return (CurrencyVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public String getCurrencyType() {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery(" select currencyType from CurrencyVO as currency where isActive=:IsActive");
            q.setInteger("IsActive", 1);
            return (String) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertCurrency(CurrencyVO currency) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(currency);
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
    public void updateCurrency(CurrencyVO currency) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CurrencyVO set currencyType=:CurrencyType, "
                + "updatedBy=:UpdatedBy " + "where hcmoCurrencyId=:HcmoCurrencyId";
            Query query = session.createQuery(sHql);
            query.setString("CurrencyType", currency.getCurrencyType());
//            query.setInteger("UpdatedBy", currency.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoCurrencyId", currency.getHcmoCurrencyId());
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
