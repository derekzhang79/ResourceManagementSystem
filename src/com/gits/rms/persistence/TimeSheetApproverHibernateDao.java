
package com.gits.rms.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimeSheetApproverVO;

public class TimeSheetApproverHibernateDao implements TimeSheetApproverDao {

   
    private List<TimeSheetApproverVO> timeSheetApproverList;

    @Override
    public List getAllTimeSheetApprover() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetApproverVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeAppSearchResult(TimeSheetApproverVO time) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetApproverVO.class);
            if (time.getHcmoEmployeeId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", time.getHcmoEmployeeId().getEmployeeId()));
            }
            if (time.getHcmoApprovingEmpId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", time.getHcmoApprovingEmpId().getEmployeeId()));
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
    public Integer getTimeSheetApproverCount(TimeSheetApproverVO timeSheetApprover) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoApprovingEmpId) from TimeSheetApproverVO as ea where ea.hcmoEmployeeId =:HcmoEmployeeId and ea.hcmoApprovingEmpId =:HcmoApprovingEmpId and ea.isActive =:IsActive");
            query.setInteger("HcmoEmployeeId", timeSheetApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("HcmoApprovingEmpId", timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId());
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getEmployeeAllTimeSheetApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.isActive=:IsActive and t.hcmoEmployeeId.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            timeSheetApproverList = query.list();
            return timeSheetApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllTsSubEmployees() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.isActive=:IsActive and t.hcmoApprovingEmpId=:HcmoApprovingEmpId and t.clientId=:clientId");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("clientId", oEmp.getClientId());
            timeSheetApproverList = query.list();
            
            return timeSheetApproverList; 
       } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetApproverVO getTimeSheetApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.hcmoApproverId=:hcmoApproverId");
            q.setInteger("hcmoApproverId", id);
            return (TimeSheetApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetApproverVO getEmpTimeSheetApprover(TimeSheetApproverVO timesheetApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.hcmoApproverId=:hcmoApproverId and t.hcmoEmployeeId.employeeId=:HcmoEmployeeId and t.isActive=:IsActive");
            q.setInteger("hcmoApproverId", timesheetApprover.getHcmoApproverId());
            q.setInteger("HcmoEmployeeId", timesheetApprover.getHcmoEmployeeId().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (TimeSheetApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetApproverVO getEmployeeTimeSheetApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.hcmoApproverId=:hcmoApproverId");
            q.setInteger("hcmoApproverId", id);
            return (TimeSheetApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timeSheetApprover);
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
    public void updateTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetApproverVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "HcmoApprovingEmpId=:HcmoApprovingEmpId, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoApproverId=:hcmoApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", timeSheetApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("HcmoApprovingEmpId", timeSheetApprover.getHcmoApprovingEmpId().getEmployeeId());
            query.setInteger("UpdatedBy", timeSheetApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoApproverId", timeSheetApprover.getHcmoApproverId());
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
    public void deleteTimeSheetApprover(TimeSheetApproverVO timeSheetApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetApproverVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoApproverId=:hcmoApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoApproverId", timeSheetApprover.getHcmoApproverId());
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
    public Integer checkTimesheetApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoApprovingEmpId) from TimeSheetApproverVO as ea where ea.hcmoApprovingEmpId =:hcmoApprovingEmpId and ea.isActive=:IsActive");
            query.setInteger("hcmoApprovingEmpId", id);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public TimeSheetApproverVO getEmployeeTimeSheetApproverDetails(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetApproverVO as t where t.hcmoEmployeeId=:hcmoEmployeeId and t.isActive=:IsActive");
            q.setInteger("hcmoEmployeeId", id);
            q.setInteger("IsActive", 1);
            return (TimeSheetApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<TimeSheetApproverVO> getAllTimeSheeetSubEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetApproverVO as t left join fetch t.hcmoEmployeeId where t.isActive=:IsActive and t.hcmoApprovingEmpId=:HcmoApprovingEmpId");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoApprovingEmpId", id);
            timeSheetApproverList = query.list();
            return timeSheetApproverList; 
       } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetApproverVO getTsSelfApprover(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetApproverVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", id));
            crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return (TimeSheetApproverVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer getDashTsForApproval() {
        Integer totalForApproval;
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetProjectAssignVO as t where t.approve=:approve and t.rejected=:rejected and t.rework=:rework and t.employeeName=:hcmoEmployeeId and t.isActive =:IsActive)");
            query.setInteger("approve", 0);
            query.setInteger("rejected", 0);
            query.setInteger("rework", 0);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);

            Query queryCategory = session.createQuery("from TimeSheetCategoryAssignVO as tc where tc.approve=:approve and tc.rejected=:rejected and tc.rework=:rework and tc.employeeName=:hcmoEmployeeId and tc.isActive =:IsActive)");
            queryCategory.setInteger("approve", 0);
            queryCategory.setInteger("rejected", 0);
            queryCategory.setInteger("rework", 0);
            queryCategory.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            queryCategory.setInteger("IsActive", 1);

            totalForApproval = query.list().size() + queryCategory.list().size();
            return totalForApproval;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer getDashTsForApprovalToday() {
        Integer totalForApprovalToday;
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Date todayDate = new Date();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetProjectAssignVO as t where t.approve=:approve and t.rejected=:rejected and t.rework=:rework and t.employeeName=:hcmoEmployeeId and t.created=:Created and t.isActive =:IsActive)");
            query.setInteger("approve", 0);
            query.setInteger("rejected", 0);
            query.setInteger("rework", 0);
            query.setDate("Created", todayDate);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);

            Query queryCategory = session.createQuery("from TimeSheetCategoryAssignVO as tc where tc.approve=:approve and tc.rejected=:rejected and tc.rework=:rework and tc.employeeName=:hcmoEmployeeId and tc.created=:Created and tc.isActive =:IsActive)");
            queryCategory.setInteger("approve", 0);
            queryCategory.setInteger("rejected", 0);
            queryCategory.setInteger("rework", 0);
            queryCategory.setDate("Created", todayDate);
            queryCategory.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            queryCategory.setInteger("IsActive", 1);

            totalForApprovalToday = query.list().size() + queryCategory.list().size();
            return totalForApprovalToday;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer getDashTsForApprovalThreeDays() {
        Integer totalForApprovalThreeDays;
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -3);
        Date threeDays = cal.getTime();
        Date todayDate = new Date();
        Integer year = todayDate.getYear() + 1900;
        Integer month = todayDate.getMonth() + 1;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetProjectAssignVO as t where t.approve=:approve and t.rejected=:rejected and t.rework=:rework and t.employeeName=:hcmoEmployeeId and year(t.created)="
                + String.valueOf(year)
                + " and day(t.created)>="
                + String.valueOf(threeDays.getDate())
                + " and day(t.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(t.created)="
                + String.valueOf(month) + "and t.isActive=:IsActive");
            query.setInteger("approve", 0);
            query.setInteger("rejected", 0);
            query.setInteger("rework", 0);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);

            Query queryCategory = session.createQuery("from TimeSheetCategoryAssignVO as tc where tc.approve=:approve and tc.rejected=:rejected and tc.rework=:rework and tc.employeeName=:hcmoEmployeeId and year(tc.created)="
                + String.valueOf(year)
                + " and day(tc.created)>="
                + String.valueOf(threeDays.getDate())
                + " and day(tc.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(tc.created)="
                + String.valueOf(month) + "and tc.isActive=:IsActive");
            queryCategory.setInteger("approve", 0);
            queryCategory.setInteger("rejected", 0);
            queryCategory.setInteger("rework", 0);
            queryCategory.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            queryCategory.setInteger("IsActive", 1);

            totalForApprovalThreeDays = query.list().size() + queryCategory.list().size();
            return totalForApprovalThreeDays;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public Integer getDashTsForApprovalOneWeek() {
        Integer totalForApprovalOneWeek;
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date lastweek = cal.getTime();
        Date todayDate = new Date();
        Integer year = todayDate.getYear() + 1900;
        Integer month = todayDate.getMonth() + 1;
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetProjectAssignVO as t where t.approve=:approve and t.rejected=:rejected and t.rework=:rework and t.employeeName=:hcmoEmployeeId and year(t.created)="
                + String.valueOf(year)
                + " and day(t.created)>="
                + String.valueOf(lastweek.getDate())
                + " and day(t.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(t.created)="
                + String.valueOf(month) + "and t.isActive=:IsActive");

            query.setInteger("approve", 0);
            query.setInteger("rejected", 0);
            query.setInteger("rework", 0);
            query.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);

            Query queryCategory = session.createQuery("from TimeSheetCategoryAssignVO as tc where tc.approve=:approve and tc.rejected=:rejected and tc.rework=:rework and tc.employeeName=:hcmoEmployeeId and year(tc.created)="
                + String.valueOf(year)
                + " and day(tc.created)>="
                + String.valueOf(lastweek.getDate())
                + " and day(tc.created)<="
                + String.valueOf(todayDate.getDate())
                + "and month(tc.created)="
                + String.valueOf(month) + "and tc.isActive=:IsActive");

            queryCategory.setInteger("approve", 0);
            queryCategory.setInteger("rejected", 0);
            queryCategory.setInteger("rework", 0);
            queryCategory.setInteger("hcmoEmployeeId", oEmp.getEmployeeId());
            queryCategory.setInteger("IsActive", 1);

            totalForApprovalOneWeek = query.list().size() + queryCategory.list().size();
            return totalForApprovalOneWeek;
        } finally {
            session.flush();
            session.close();
        }
    }
}