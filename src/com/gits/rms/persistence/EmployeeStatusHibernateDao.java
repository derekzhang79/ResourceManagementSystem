
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmployeeStatusVO;
import com.gits.rms.vo.EmployeesVO;

public class EmployeeStatusHibernateDao implements EmployeeStatusDao {

    @Override
    public List checkEmployeeStatusInEmployee(EmployeeStatusVO empStatus) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("empStatusIdObj.empStatusId", empStatus.getEmpStatusId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteEmployeeStatus(EmployeeStatusVO empStatus) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeStatusVO set updatedBy=:UpdatedBy,isActive=:IsActive where empStatusId=:EmpStatusId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", empStatus.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EmpStatusId", empStatus.getEmpStatusId());
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
    public List employeeStatusSearchResult(EmployeeStatusVO empStatus) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeStatusVO.class);
            if (!(empStatus.getStatusName().isEmpty())) {
                crit.add(Restrictions.like("statusName", empStatus.getStatusName(), MatchMode.ANYWHERE));
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
    public List getAllEmployeeStatus() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeStatusVO.class);
            crit.addOrder(Order.asc("statusName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeStatusVO getEmployeeStatus(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeeStatusVO as empstat where empstat.empStatusId =:EmpStatusId");
            q.setInteger("EmpStatusId", id);
            return (EmployeeStatusVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public int getEmployeeStatusCount() {
        Session session = HibernateUtil.getSession();
        try {
            String sHql = "select count(statusName) from EmployeeStatusVO";
            Query query = session.createQuery(sHql);
            query.iterate();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
            return 0;
        }
    }

    @Override
    public void insertEmployeeStatus(EmployeeStatusVO empStatus) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(empStatus);
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
    public void updateEmployeeStatus(EmployeeStatusVO empStatus) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeeStatusVO set EStatName=:EStatName, "
                + "UpdatedBy=:UpdatedBy " + "where empStatusId=:EmpStatusId";
            Query query = session.createQuery(sHql);
            query.setString("EStatName", empStatus.getStatusName());
            query.setInteger("UpdatedBy", empStatus.getUpdatedBy().getEmployeeId());
            query.setInteger("EmpStatusId", empStatus.getEmpStatusId());
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
