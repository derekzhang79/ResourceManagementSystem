
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EthnicRaceVO;

public class EthnicRaceHibernateDao implements EthnicRaceDao {

    @Override
    public List checkEthnicRaceInEmployee(EthnicRaceVO ethRace) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("ethnicRaceIdObj.ethnicRaceId", ethRace.getEthnicRaceId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteEthnicRace(EthnicRaceVO ethRace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EthnicRaceVO set updatedBy=:UpdatedBy,isActive=:IsActive where ethnicRaceId=:EthnicRaceId";
            Query query = session.createQuery(sHql);
//            query.setInteger("UpdatedBy", ethRace.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EthnicRaceId", ethRace.getEthnicRaceId());
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
    public List ethnicRaceSearchResult(EthnicRaceVO ethRace) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EthnicRaceVO.class);
            if (!(ethRace.getEthnicRaceDesc().isEmpty())) {
                crit.add(Restrictions.like("ethnicRaceDesc", ethRace.getEthnicRaceDesc(), MatchMode.ANYWHERE));
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
    public List getAllEthnicRace() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EthnicRaceVO.class);
            crit.addOrder(Order.asc("ethnicRaceDesc"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EthnicRaceVO getEthnicRace(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EthnicRaceVO as d where d.ethnicRaceId =:EthnicRaceId");
            q.setInteger("EthnicRaceId", id);
            return (EthnicRaceVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEthnicRace(EthnicRaceVO ethRace) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(ethRace);
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
    public void updateEthnicRace(EthnicRaceVO ethRace) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EthnicRaceVO set EthnicRaceDesc=:EthnicRaceDesc, "
                + "UpdatedBy=:UpdatedBy " + "where ethnicRaceId=:EthnicRaceId";
            Query query = session.createQuery(sHql);
            query.setString("EthnicRaceDesc", ethRace.getEthnicRaceDesc());
//            query.setInteger("UpdatedBy", ethRace.getUpdatedBy().getEmployeeId());
            query.setInteger("EthnicRaceId", ethRace.getEthnicRaceId());
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
