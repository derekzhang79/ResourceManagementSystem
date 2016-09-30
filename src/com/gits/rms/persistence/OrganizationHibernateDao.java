
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.OrganizationVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;

public class OrganizationHibernateDao implements OrganizationDao {

    @Override
    public List getAllOrganization() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(OrganizationVO.class);
            crit.addOrder(Order.asc("orgName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
        }
    }

    @Override
    public List organizationSearchResult(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(OrganizationVO.class);
            if (!(org.getOrgName().isEmpty())) {
                crit.add(Restrictions.like("orgName", org.getOrgName(), MatchMode.ANYWHERE));
            }
            if (org.getLocation().getHcmolocationId() != null) {
                crit.add(Restrictions.eq("location.hcmolocationId", org.getLocation().getHcmolocationId()));
            }
            if (org.getOrgtype().getHcmoorgTypeId() != null) {
                crit.add(Restrictions.eq("orgtype.hcmoorgTypeId", org.getOrgtype().getHcmoorgTypeId()));
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
    public OrganizationVO getOrganization(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from OrganizationVO as o left join fetch o.location l left join fetch o.orgtype where o.orgId =:OrgId");
            q.setInteger("OrgId", id);
            return (OrganizationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertOrganization(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(org);
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
    public void updateOrganization(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            if (org.getParentOrgId() == null) {
                String sHql = "update OrganizationVO set HcmoLocationId=:HcmoLocationId, "
                    + "HcmoOrgTypeId=:HcmoOrgTypeId, " + "Name=:Name, "
                    + "Description=:Description, " + "UpdatedBy=:UpdatedBy " + "where orgId=:OrgId";
                Query query = session.createQuery(sHql);
                query.setInteger("HcmoLocationId", org.getLocation().getHcmolocationId());
                query.setInteger("HcmoOrgTypeId", org.getOrgtype().getHcmoorgTypeId());
                query.setString("Name", org.getOrgName());
                query.setString("Description", org.getDescription());
                query.setInteger("UpdatedBy", org.getUpdatedBy().getEmployeeId());
                query.setInteger("OrgId", org.getOrgId());
                query.executeUpdate();
                tx.commit();
            } else {
                String sHql = "update OrganizationVO set HcmoLocationId=:HcmoLocationId, "
                    + "HcmoOrgTypeId=:HcmoOrgTypeId, " + "ParentOrgId=:ParentOrgId, "
                    + "Name=:Name, " + "Description=:Description, " + "UpdatedBy=:UpdatedBy "
                    + "where orgId=:OrgId";
                Query query = session.createQuery(sHql);
                query.setInteger("HcmoLocationId", org.getLocation().getHcmolocationId());
                query.setInteger("HcmoOrgTypeId", org.getOrgtype().getHcmoorgTypeId());
                query.setInteger("ParentOrgId", org.getParentOrgId());
                query.setString("Name", org.getOrgName());
                query.setString("Description", org.getDescription());
                query.setInteger("UpdatedBy", org.getUpdatedBy().getEmployeeId());
                query.setInteger("OrgId", org.getOrgId());
                query.executeUpdate();
                tx.commit();
            }

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
    public void deleteOrganization(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update OrganizationVO set  updatedBy=:UpdatedBy,isActive=:IsActive where orgId=:OrgId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", org.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("OrgId", org.getOrgId());
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
    public List checkOrganizationInOrganization(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(OrganizationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("parentOrgId", org.getParentOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrganizationInProject(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("orgObj.orgId", org.getOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrganizationInLeaveType(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveTypeVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("orgList.orgId", org.getOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrganizationInRole(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RoleVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("hcmoOrgId.orgId", org.getOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrganizationInEmployees(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("orgObj.orgId", org.getOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkOrganizationInEmpLocHistory(OrganizationVO org) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpLocationHistoryVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("orgIdObj.orgId", org.getOrgId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
