
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimesheetCategoryEmpVO;

public class TimesheetCategoryEmpHibernateDao implements TimesheetCategoryEmpDao {

    @Override
    public void deleteTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetCategoryEmpVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoTimesheetCategoryEmpId=:hcmoTimesheetCategoryEmpId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryEmp.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoTimesheetCategoryEmpId", timeSheetCategoryEmp.getHcmoTimesheetCategoryEmpId());
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
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmpCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryEmpVO as emp where emp.isActive=:IsActive and emp.hcmoTimesheetCategoryEmpId=:HcmoTimesheetCategoryEmpId");
            q.setInteger("HcmoTimesheetCategoryEmpId", id);
            q.setInteger("IsActive", 1);
            return (TimesheetCategoryEmpVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkTimeSheetCategoryInTimeSheetCategoryAssign(TimesheetCategoryEmpVO timesheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetCategoryAssignVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("timesheetCategoryName.hcmoTimesheetCategoryId", timesheetCategoryEmp.getTimesheetCategoryName().getHcmoTimesheetCategoryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllTimeSheetCategoryEmp() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetCategoryEmpVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmp(Integer hcmoTimesheetCategoryEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryEmpVO where hcmoTimesheetCategoryEmpId=:hcmoTimesheetCategoryEmpId");
            q.setInteger("hcmoTimesheetCategoryEmpId", hcmoTimesheetCategoryEmpId);
            return (TimesheetCategoryEmpVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timeSheetCategoryEmp);
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
    public List timeSheetCategoryEmpSearchResult(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        try {

            Criteria crit = session.createCriteria(TimesheetCategoryEmpVO.class);
            try {
                if ((timeSheetCategoryEmp.getTimesheetCategoryName().getHcmoTimesheetCategoryId() == null)
                    || (timeSheetCategoryEmp.getTimesheetCategoryName() == null)) {
                } else {
                    crit.add(Restrictions.eq("timesheetCategoryName.hcmoTimesheetCategoryId", timeSheetCategoryEmp.getTimesheetCategoryName().getHcmoTimesheetCategoryId()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            if ((timeSheetCategoryEmp.getEmployeeName().getEmployeeId() == null)
                || (timeSheetCategoryEmp.getEmployeeName() == null)) {
            } else {
                crit.add(Restrictions.eq("employeeName.employeeId", timeSheetCategoryEmp.getEmployeeName().getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List timesheetCategoryEmplist = crit.list();
            return timesheetCategoryEmplist;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateTimeSheetCategoryEmp(TimesheetCategoryEmpVO timeSheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetCategoryEmpVO set updatedBy=:UpdatedBy,employeeName=:employeeid,timesheetCategoryName=:categoryid where hcmoTimesheetCategoryEmpId=:hcmoTimesheetCategoryEmpId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryEmp.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoTimesheetCategoryEmpId", timeSheetCategoryEmp.getHcmoTimesheetCategoryEmpId());
            query.setInteger("employeeid", timeSheetCategoryEmp.getEmployeeName().getEmployeeId());
            query.setInteger("categoryid", timeSheetCategoryEmp.getTimesheetCategoryName().getHcmoTimesheetCategoryId());
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
    public List getEmpTimeSheetCategory(Integer EmployeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryEmpVO where employeeName=:EmployeeId and isActive=:isActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("isActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimesheetCategoryEmpVO getTimeSheetCategoryEmpDetail(TimesheetCategoryEmpVO timesheetCategoryEmp) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimesheetCategoryEmpVO where employeeName=:hcmoEmployeeId and timesheetCategoryName=:hcmoTimesheetCategoryId");
            q.setInteger("hcmoTimesheetCategoryId", timesheetCategoryEmp.getTimesheetCategoryName().getHcmoTimesheetCategoryId());
            q.setInteger("hcmoEmployeeId", timesheetCategoryEmp.getEmployeeName().getEmployeeId());
            return (TimesheetCategoryEmpVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
}