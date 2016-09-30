
package com.gits.rms.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimeTrackVO;

public class TimeTrackHibernateDao implements TimeTrackDao {
    private List<TimeTrackVO> timeTrackList;

    @Override
    public void insertInOutTime(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timetrack);
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
    public void updateCheckOut(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeTrackVO set stop=:Stop,isActive=0 where empId=:EmpId and categoryId=:CategoryId and isActive=:IsActive";
            Query query = session.createQuery(sHql);
            query.setTimestamp("Stop", timetrack.getStop());
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("CategoryId", timetrack.getCategoryId());
            query.setInteger("IsActive", 1);
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
    public ProjectVO getProjectAllDetailsCronJob(Integer id,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectVO as pro left join fetch pro.customerId cust left join fetch pro.empIdObj emp where pro.projectId=:ProjectId and pro.isActive=:IsActive");
            query.setInteger("ProjectId", id);
            query.setInteger("IsActive", 1);
            return (ProjectVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO getProjectActivityAllDetailsCronJob(Integer id,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro left join fetch pro.empIdObj emp left join fetch pro.proObj where pro.projectActivityId=:ProjectActivityId and pro.isActive=:IsActive");
            q.setInteger("ProjectActivityId", id);
            q.setInteger("IsActive", 1);
            return (ProjectActivityVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateCheckOutCronJob(TimeTrackVO timetrack, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeTrackVO set stop=:Stop,isActive=0 where empId=:EmpId and CategoryId=:CategoryId and isActive=:IsActive";
            Query query = session.createQuery(sHql);
            query.setTimestamp("Stop", timetrack.getStop());
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("CategoryId", timetrack.getCategoryId());
            query.setInteger("IsActive", 1);
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
    public List chkInOutTimeTrack(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);
            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("IsActive", 1);
            timeTrackList = query.list();

            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getAllTimeTrack(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);
            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("IsActive", 0);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getAllTimeTrackCronJob(TimeTrackVO timetrack, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);
            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("IsActive", 0);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getAllTimeDetails(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);
            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.projectId=:ProjectId and b.activityId=:ActivityId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("ProjectId", timetrack.getProjectId());
            query.setInteger("ActivityId", timetrack.getActivityId());
            query.setInteger("IsActive", 0);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getAllTimeTrackById(TimeTrackVO timetrack) {
        Session session = HibernateUtil.getSession();
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);

            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.categoryId=:CategoryId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("CategoryId", timetrack.getCategoryId());
            query.setInteger("IsActive", 0);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllTimeTrackByIdCronJob(TimeTrackVO timetrack, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            Date newdate = new Date();
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);

            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.empId=:EmpId and b.categoryId=:CategoryId and b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("EmpId", timetrack.getEmpId());
            query.setInteger("CategoryId", timetrack.getCategoryId());
            query.setInteger("IsActive", 0);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateTimeSheetCategoryAssign(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetCategoryAssignVO set updatedBy=:UpdatedBy,enterTime=:enter_time where enterDate=:enter_date and employee_id=:employeeid and category_id=:categoryid";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("employeeid", empid);
            query.setInteger("categoryid", categoryId);
            query.setDate("enter_date", DateUtils.getCurrentDateTime());
            query.setDouble("enter_time", timeSheetCategoryAssign.getEnterTime());

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
    public void updateTimeSheetCategoryAssignCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetCategoryAssignVO set updatedBy=:UpdatedBy,enterTime=:enter_time where enterDate=:enter_date and employee_id=:employeeid and category_id=:categoryid";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetCategoryAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("employeeid", empid);
            query.setInteger("categoryid", categoryId);
            query.setDate("enter_date", DateUtils.getCurrentDateTime());
            query.setDouble("enter_time", timeSheetCategoryAssign.getEnterTime());

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
    public void insertTimeSheet(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timeSheetCategoryAssign);
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
    public void insertTimeSheetCronJob(TimeSheetCategoryAssignVO timeSheetCategoryAssign, int empid, int categoryId, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(timeSheetCategoryAssign);
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
    public List resetimeTrack(TimeTrackVO timetrack, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            Date newdate = new Date();
            SimpleDateFormat sdfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
            String end_Date = sdfMySQLDate.format(newdate);

            session.beginTransaction();
            Query query = session.createQuery("from TimeTrackVO as b where b.isActive=:IsActive and b.start like '%%%"
                + end_Date + "%%%'");
            query.setInteger("IsActive", 1);
            timeTrackList = query.list();
            return timeTrackList;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List timeSheetProjectAssignSearch(Integer EmployeeId, TimeTrackVO timetrackVO, Date enterDate) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.enterDate=:enterdate and tcavo.projectName=:ProjectId and tcavo.projectActivity=:ActivityId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("ProjectId", timetrackVO.getProjectId());
            q.setInteger("ActivityId", timetrackVO.getActivityId());
            q.setDate("enterdate", enterDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetProjectAssignSearchCronJob(Integer EmployeeId, ProjectVO proTimeObj,ProjectActivityVO proActivityTimeObj, Date enterDate,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.enterDate=:enterdate and tcavo.projectName=:ProjectId and tcavo.projectActivity=:ActivityId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("ProjectId", proTimeObj.getProjectId());
            q.setInteger("ActivityId", proActivityTimeObj.getProjectActivityId());
            q.setDate("enterdate", enterDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetProjectAssignVO set updatedBy=:UpdatedBy,enterTime=:enter_time where enterDate=:enter_date and employee_id=:employeeid and project_id=:project_id and projectActivity_id=:projectActivity_id";

            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetProjectAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("employeeid", empid);
            query.setInteger("project_id", timeSheetProjectAssign.getProjectName().getProjectId());
            query.setInteger("projectActivity_id", timeSheetProjectAssign.getProjectActivity().getProjectActivityId());
            query.setDate("enter_date", DateUtils.getCurrentDateTime());
            query.setDouble("enter_time", timeSheetProjectAssign.getEnterTime());

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
    public void updateTimeSheetProjectAssignCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetProjectAssignVO set updatedBy=:UpdatedBy,enterTime=:enter_time where enterDate=:enter_date and employee_id=:employeeid and project_id=:project_id and projectActivity_id=:projectActivity_id";

            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetProjectAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("employeeid", empid);
            query.setInteger("project_id", timeSheetProjectAssign.getProjectName().getProjectId());
            query.setInteger("projectActivity_id", timeSheetProjectAssign.getProjectActivity().getProjectActivityId());
            query.setDate("enter_date", DateUtils.getCurrentDateTime());
            query.setDouble("enter_time", timeSheetProjectAssign.getEnterTime());

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
    public void insertTimeSheetActivity(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(timeSheetProjectAssign);
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
    public void insertTimeSheetActivityCronJob(TimeSheetProjectAssignVO timeSheetProjectAssign, int empid,String clientId) {
    	Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(timeSheetProjectAssign);
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
