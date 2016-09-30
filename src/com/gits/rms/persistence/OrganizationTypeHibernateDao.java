
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.OrganizationTypeVO;
import com.gits.rms.vo.OrganizationVO;

public class OrganizationTypeHibernateDao implements OrganizationTypeDao {

    @Override
    public List getAllOrganizationType() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(OrganizationTypeVO.class);
            crit.addOrder(Order.asc("orgTypeName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List organizationTypeSearchResult(OrganizationTypeVO orgtype) {
        Session session = HibernateUtil.getSession();
        try {

            Criteria crit = session.createCriteria(OrganizationTypeVO.class);
            if (!(orgtype.getOrgTypeName().isEmpty())) {
                crit.add(Restrictions.like("orgTypeName", orgtype.getOrgTypeName(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List orgTypeList = crit.list();

            return orgTypeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public OrganizationTypeVO getOrganizationType(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from OrganizationTypeVO o where o.hcmoorgTypeId =:HcmoOrgTypeId");
            q.setInteger("HcmoOrgTypeId", id);
            return (OrganizationTypeVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertOrganizationType(OrganizationTypeVO orgtype) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(orgtype);
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
    public void updateOrganizationType(OrganizationTypeVO orgtype) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update OrganizationTypeVO set Name=:Name, "
                + "Description=:Description, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoorgTypeId=:HcmoOrgTypeId";
            Query query = session.createQuery(sHql);
            query.setString("Name", orgtype.getOrgTypeName());
            query.setString("Description", orgtype.getDescription());
            query.setInteger("UpdatedBy", orgtype.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoOrgTypeId", orgtype.getHcmoorgTypeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteOrganizationType(OrganizationTypeVO orgtype) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update OrganizationTypeVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoorgTypeId=:HcmoOrgTypeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", orgtype.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoOrgTypeId", orgtype.getHcmoorgTypeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrgTypeInOrganization(OrganizationTypeVO orgtype) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(OrganizationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("orgtype.hcmoorgTypeId", orgtype.getHcmoorgTypeId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
