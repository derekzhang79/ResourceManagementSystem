
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeesVO;

public class DepartmentHibernateDao implements DepartmentDao {

    @Override
    public List checkDepartmentInEmployee(DepartmentVO dept) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("departmentIdObj.hcmoDepartmentId", dept.getHcmoDepartmentId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteDepartment(DepartmentVO dept) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DepartmentVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoDepartmentId=:hcmoDepartmentId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", dept.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoDepartmentId", dept.getHcmoDepartmentId());
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
    public List departmentSearchResult(DepartmentVO dept) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DepartmentVO.class);
            if (!(dept.getDeptName().isEmpty())) {
                crit.add(Restrictions.like("deptName", dept.getDeptName(), MatchMode.ANYWHERE));
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
    public List getAllDepartment() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DepartmentVO.class);
            crit.addOrder(Order.asc("deptName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public DepartmentVO getDepartment(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from DepartmentVO as dept where dept.hcmoDepartmentId =:hcmoDepartmentId");
            q.setInteger("hcmoDepartmentId", id);
            return (DepartmentVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertDepartment(DepartmentVO dept) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(dept);
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
    public void updateDepartment(DepartmentVO dept) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DepartmentVO set deptName=:DeptName, " + "updatedBy=:UpdatedBy "
                + "where hcmoDepartmentId=:DepartmentId";
            Query query = session.createQuery(sHql);
            query.setString("DeptName", dept.getDeptName());
            query.setInteger("UpdatedBy", dept.getUpdatedBy().getEmployeeId());
            query.setInteger("DepartmentId", dept.getHcmoDepartmentId());
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
