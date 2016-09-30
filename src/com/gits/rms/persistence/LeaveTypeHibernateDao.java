
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.LeaveTypeVO;

public class LeaveTypeHibernateDao implements LeaveTypeDao {

    @Override
    public List getAllLeaveType() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveTypeVO.class);
            crit.addOrder(Order.asc("leaveTypeName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List leaveTypeSearchResult(LeaveTypeVO leave) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveTypeVO.class);
            if (!(leave.getLeaveTypeName().isEmpty())) {
                crit.add(Restrictions.like("leaveTypeName", leave.getLeaveTypeName(), MatchMode.ANYWHERE));
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
    public LeaveTypeVO getLeaveType(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveTypeVO as l where l.leaveTypeId =:LeaveTypeId");
            q.setInteger("LeaveTypeId", id);
            return (LeaveTypeVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertLeaveType(LeaveTypeVO leave) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(leave);
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
    public void updateLeaveType(LeaveTypeVO leave) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveTypeVO set " + "LeaveTypeName=:LeaveTypeName, "
                + "UpdatedBy=:UpdatedBy " + "where HcmoLeaveTypeId=:HcmoLeaveTypeId";
            Query query = session.createQuery(sHql);
            query.setString("LeaveTypeName", leave.getLeaveTypeName());
            query.setInteger("UpdatedBy", leave.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoLeaveTypeId", leave.getLeaveTypeId());
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
    public void deleteLeaveType(LeaveTypeVO leave) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveTypeVO set updatedBy=:UpdatedBy,isActive=:IsActive where leaveTypeId=:LeaveTypeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", leave.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("LeaveTypeId", leave.getLeaveTypeId());
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
    public List getValidateLeaveQuota(LeaveTypeVO leave) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeeLeaveQuotaVO.class);
            crit.add(Restrictions.eq("leaveTypeIdObj.leaveTypeId", leave.getLeaveTypeId()));
            crit.add(Restrictions.eq("isActive", 1));
            crit.list();
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
