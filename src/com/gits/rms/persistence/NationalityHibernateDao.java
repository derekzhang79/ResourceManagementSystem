
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
import com.gits.rms.vo.NationalityVO;

public class NationalityHibernateDao implements NationalityDao {

    @Override
    public List getAllNationality() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(NationalityVO.class);
            crit.addOrder(Order.asc("nationalityName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllNationalityCronJob(String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            Criteria crit = session.createCriteria(NationalityVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List nationalitySearchResult(NationalityVO nati) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(NationalityVO.class);
            if (!(nati.getNationalityName().isEmpty())) {
                crit.add(Restrictions.like("nationalityName", nati.getNationalityName(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List natiList = crit.list();
            return natiList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public NationalityVO getNationality(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from NationalityVO as d where d.nationalityId =:NationalityId");
            q.setInteger("NationalityId", id);
            return (NationalityVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertNationality(NationalityVO nati) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(nati);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
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
    public void updateNationality(NationalityVO nati) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update NationalityVO set NatName=:NatName, " + "UpdatedBy=:UpdatedBy "
                + "where nationalityId=:NationalityId";
            Query query = session.createQuery(sHql);
            query.setString("NatName", nati.getNationalityName());
//            query.setInteger("UpdatedBy", nati.getUpdatedBy().getEmployeeId());
            query.setInteger("NationalityId", nati.getNationalityId());
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
    public void deleteNationality(NationalityVO nati) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update NationalityVO set updatedBy=:UpdatedBy,isActive=:IsActive where nationalityId=:NationalityId";
            Query query = session.createQuery(sHql);
//            query.setInteger("UpdatedBy", nati.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("NationalityId", nati.getNationalityId());
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
    public List checkNationalityInEmployee(NationalityVO nati) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("nationalityIdObj.nationalityId", nati.getNationalityId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
