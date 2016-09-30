
package com.gits.rms.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;

public class RoleHibernateDao implements RoleDao {
//	static Map session = ActionContext.getContext().getSession();
//    static Integer clientId = (Integer) session.get("CLIENT_INFO");

    @Override
    public List getAllRole() {
    	Map mSession = ActionContext.getContext().getSession();
    	Integer clientId = (Integer) mSession.get("CLIENT_INFO");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RoleVO.class);
            crit.addOrder(Order.asc("roleName"));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public RoleVO getRole(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from RoleVO as role where role.hcmoRoleId =:HcmoRoleId");
            q.setInteger("HcmoRoleId", id);
            return (RoleVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List roleSearchResult(RoleVO role) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RoleVO.class);
            if (!(role.getRoleName().isEmpty())) {
                crit.add(Restrictions.like("roleName", role.getRoleName(), MatchMode.ANYWHERE));
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
    public void insertRole(RoleVO role) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(role);
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
    public void updateRole(RoleVO role) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update RoleVO set " + "RoleName=:RoleName, " + "xmlPath=:XMLPath, "
                + "UpdatedBy=:UpdatedBy " + "where hcmoRoleId=:HcmoRoleId";
            Query query = session.createQuery(sHql);
            query.setString("RoleName", role.getRoleName());
            query.setString("XMLPath", role.getXmlPath());
            query.setInteger("UpdatedBy", role.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoRoleId", role.getHcmoRoleId());
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
    public void deleteRole(RoleVO role) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update RoleVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoRoleId=:HcmoRoleId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", role.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoRoleId", role.getHcmoRoleId());
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
    public RoleVO getRoleName(String roleName) {
        Session session = HibernateUtil.getSession();
         Map mSession = ActionContext.getContext().getSession();
         Integer clientId = (Integer) mSession.get("CLIENT_INFO");
        try {
            session.beginTransaction();
            Query q = session.createQuery("from RoleVO as role where Lower(role.roleName)=:roleName and role.isActive=:IsActive and role.clientId=:clientId");
            q.setString("roleName", roleName);
            q.setInteger("IsActive", 1);
            q.setInteger("clientId", clientId);
            return (RoleVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public EmployeesVO getEmployeeId(Integer roleId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("roleObj.hcmoRoleId", roleId));
            crit.add(Restrictions.eq("isActive", 1));
            return (EmployeesVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllAdmin(Integer roleId) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        Integer clientId = (Integer) mSession.get("CLIENT_INFO");
        
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("roleObj.hcmoRoleId", roleId));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkRoleInEmployee(RoleVO role) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        Integer clientId = (Integer) mSession.get("CLIENT_INFO");
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("roleObj.hcmoRoleId", role.getHcmoRoleId()));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
