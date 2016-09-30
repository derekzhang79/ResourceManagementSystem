
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

import sun.util.logging.resources.logging;

public class ProjectAssignEmpHibernateDao implements ProjectAssignEmpDao {

    @Override
    public void deleteProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectAssignEmpVO set updatedBy=:UpdatedBy,IsActive=:IsActive where projectAssignEmpId=:projectAssignEmpId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", projectAssignEmp.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("projectAssignEmpId", projectAssignEmp.getProjectAssignEmpId());
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
    public List getAllProjectAssignEmpDao() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetProjectAssignVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectAssignEmpVO getProjectAssignEmp(Integer projectAssignEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO where projectAssignEmpId=:projectAssignEmpId");
            q.setInteger("projectAssignEmpId", projectAssignEmpId);
            return (ProjectAssignEmpVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectAssignEmpVO insertProjectAssignEmp(ProjectAssignEmpVO timeSheetProjectAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Log.debug("timeSheetProjectAssign : " + timeSheetProjectAssign);
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
       return timeSheetProjectAssign;
    }

    @Override
    public List projectAssignEmpSearch(Integer EmployeeId, Integer projectName, Date date, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO as tcavo where tcavo.employeeName=:EmployeeId and (tcavo.projectStartDate<=:enterdate and tcavo.projectEndDate>=:enterdate) and tcavo.projectName=:projectName and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("projectName", projectName);
            q.setDate("enterdate", endDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List projectAssignEmpSearchResult(Integer EmployeeId, Date date, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO as tcavo where tcavo.employeeName=:EmployeeId and (tcavo.projectStartDate>=:Startdate and tcavo.projectEndDate<=:Enddate) and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setDate("Startdate", date);
            q.setDate("Enddate", endDate);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateProjectAssignEmpDao(ProjectAssignEmpVO projectAssignEmp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           // String sHql = "update ProjectAssignEmpVO set updatedBy=:UpdatedBy,employeeName=:employeeid,projectName=:projectid where projectAssignEmpId=:timeSheetCategoryAssignId";
            String sHql = "update ProjectAssignEmpVO set updatedBy=:UpdatedBy,employeeName=:employeeid,projectName=:projectid where projectid=:projectid";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", projectAssignEmp.getUpdatedBy().getEmployeeId());
            query.setInteger("projectAssignEmpId", projectAssignEmp.getProjectAssignEmpId());
            query.setInteger("employeeid", projectAssignEmp.getEmployeeName().getEmployeeId());
            query.setInteger("projectid", projectAssignEmp.getProjectName().getProjectId());

            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            Log.error("Error while updating ProjectAssignEmpVO : " + e);
            throw e;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List projectAssignEmpSearchDetail(Integer EmployeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectAssignEmpVO> empScheduleProjSearchResult(Integer EmployeeId, Date date, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.isActive=:IsActive");
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("IsActive", 1);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List<ProjectAssignEmpVO> getProjAssignListByProjActivityAndProj(Integer projId,Integer projActivityId) {
    	 Session session = HibernateUtil.getSession();
         try {
             Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
             crit.add(Restrictions.eq("projectName.projectId", projId));
             crit.add(Restrictions.eq("projectActivityId.projectActivityId", projActivityId));
             crit.add(Restrictions.eq("isActive", 1));
             return crit.list();
         } finally {
             session.flush();
             session.close();
         }
    }

}