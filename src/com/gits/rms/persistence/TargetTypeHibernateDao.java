
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.TargetTypeVO;
import com.gits.rms.vo.TargetsVO;

public class TargetTypeHibernateDao implements TargetTypeDao {

    @Override
    public List getAllTargetType() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TargetTypeVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public TargetTypeVO getTargetType(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TargetTypeVO as d where d.hcmoTargetTypeId=:HcmoTargetTypeId");
            q.setInteger("HcmoTargetTypeId", id);
            return (TargetTypeVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertTargetType(TargetTypeVO targetType) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(targetType);
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
    public void updateTargetType(TargetTypeVO targetType) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TargetTypeVO set targetTypeName=:TargetTypeName, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoTargetTypeId=:HcmoTargetTypeId";
            Query query = session.createQuery(sHql);
            query.setString("TargetTypeName", targetType.getTargetTypeName());
            query.setInteger("UpdatedBy", targetType.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoTargetTypeId", targetType.getHcmoTargetTypeId());
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
    public void deleteTargetType(TargetTypeVO targetType) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TargetTypeVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoTargetTypeId=:HcmoTargetTypeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", targetType.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoTargetTypeId", targetType.getHcmoTargetTypeId());
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
    public List targetTypeSearchResult(TargetTypeVO targetType) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TargetTypeVO.class);
            if (!(targetType.getTargetTypeName().isEmpty())) {
                crit.add(Restrictions.like("targetTypeName", targetType.getTargetTypeName(), MatchMode.ANYWHERE));
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
    public List checkTargetTypeInTarget(TargetTypeVO targetType) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TargetsVO.class);
            crit.add(Restrictions.eq("targetTypeObj.hcmoTargetTypeId", targetType.getHcmoTargetTypeId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
