
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.TimesheetCategoryEmpVO;
import com.gits.rms.vo.TimesheetCategoryVO;

public class TimesheetCategoryHibernateDao implements TimesheetCategoryDao {

    @Override
    public void deleteTimeSheetCategory(TimesheetCategoryVO timeSheetCategory) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetCategoryVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoTimesheetCategoryId=:hcmoTimesheetCategoryId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategory.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoTimesheetCategoryId", timeSheetCategory.getHcmoTimesheetCategoryId());
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
    public List getAllTimeSheetCategory() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetCategoryVO.class);
            crit.addOrder(Order.asc("name"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAssignedTimesheetCategoryid(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetCategoryEmpVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("timesheetCategoryName.hcmoTimesheetCategoryId", id));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryVO getTimeSheetCategory(Integer hcmoTimesheetCategoryId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryVO where hcmoTimesheetCategoryId=:hcmoTimesheetCategoryId");
            q.setInteger("hcmoTimesheetCategoryId", hcmoTimesheetCategoryId);
            return (TimesheetCategoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryCronJob(Integer hcmoTimesheetCategoryId,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryVO where hcmoTimesheetCategoryId=:hcmoTimesheetCategoryId");
            q.setInteger("hcmoTimesheetCategoryId", hcmoTimesheetCategoryId);
            return (TimesheetCategoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTimeSheetCategory(TimesheetCategoryVO TimeSheetCategory) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(TimeSheetCategory);
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
    public void updateTimeSheetCategory(TimesheetCategoryVO TimeSheetCategory) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetCategoryVO set updatedBy=:UpdatedBy,name=:name where hcmoTimesheetCategoryId=:hcmoTimesheetCategoryId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", TimeSheetCategory.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoTimesheetCategoryId", TimeSheetCategory.getHcmoTimesheetCategoryId());
            query.setString("name", TimeSheetCategory.getName());
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
    public List timesheetCategorySearchResult(TimesheetCategoryVO TimeSheetCategory) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetCategoryVO.class);
            if (!(TimeSheetCategory.getName().isEmpty())) {
                crit.add(Restrictions.like("name", TimeSheetCategory.getName(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List timesheetCategorylist = crit.list();
            return timesheetCategorylist;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryId(String hcmoTimesheetCategoryName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryVO where name=:hcmoTimesheetCategoryName");
            q.setString("hcmoTimesheetCategoryName", hcmoTimesheetCategoryName);
            return (TimesheetCategoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryVO getTimesheetCategoryIdCronJob(String hcmoTimesheetCategoryName, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryVO where name=:hcmoTimesheetCategoryName");
            q.setString("hcmoTimesheetCategoryName", hcmoTimesheetCategoryName);
            return (TimesheetCategoryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
}