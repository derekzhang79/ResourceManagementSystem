
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;

import sun.util.logging.resources.logging;

public class ProjectActivityHibernateDao implements ProjectActivityDao {
    private List<ProjectActivityVO> activityList;

    @Override
    public List getAllProjectActivity() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectActivityVO.class);
            crit.addOrder(Order.asc("activityName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO getProjectActivityCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro where pro.isActive=:IsActive and pro.projectActivityId=:ProjectActivityId");
            q.setInteger("ProjectActivityId", id);
            q.setInteger("IsActive", 1);
            return (ProjectActivityVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllProjectActivityCount(ProjectActivityVO proActivity) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            crit.add(Restrictions.eq("projectActivityId.projectActivityId", proActivity.getProjectActivityId()));
            crit.add(Restrictions.eq("isActive", 1));
            
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List projectActivitySearchResult(ProjectActivityVO proActivity) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectActivityVO.class);
            if (proActivity.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", proActivity.getEmpIdObj().getEmployeeId()));
            }
            if (proActivity.getProObj().getProjectId() != null) {
                crit.add(Restrictions.eq("proObj.projectId", proActivity.getProObj().getProjectId()));
            }
            if (!(proActivity.getActivityName().isEmpty())) {
                crit.add(Restrictions.like("activityName", proActivity.getActivityName(), MatchMode.ANYWHERE));
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
    public List getPrevAddedProjActivityByProj(Integer projectId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectActivityVO.class);
                crit.add(Restrictions.eq("proObj.projectId", projectId));
                crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO getProjectActivity(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro left join fetch pro.empIdObj emp left join fetch pro.proObj where pro.projectActivityId =:ProjectActivityId");
            q.setInteger("ProjectActivityId", id);
            return (ProjectActivityVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertProjectActivity(ProjectActivityVO proActivity) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Log.debug("proActivity : " + proActivity);
            session.save(proActivity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            Log.error("Error while inserting task record : " + e);
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateProjectActivity(ProjectActivityVO proActivity) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectActivityVO set HcmoProjectId=:HcmoProjectId, "
                + "ActivityName=:ActivityName, " + "ProjectActivityOwner=:ProjectActivityOwner, "
                + "Notes=:Notes," + "UpdatedBy=:UpdatedBy " + "HcmoDepartmentId=:HcmoDepartmentId" + "taskStartDate=:taskStartDate"
                + "taskEndDate=:taskEndDate" + "description=:description";
            
            if(proActivity.getEstimatedHours()!=null){
            	sHql += ", estimatedHours=:EstimatedHours";
            }
            
            sHql += " where HcmoProjectActivityId=:HcmoProjectActivityId";
            
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoProjectId", proActivity.getProObj().getProjectId());
            query.setString("ActivityName", proActivity.getActivityName());
            query.setInteger("ProjectActivityOwner", proActivity.getEmpIdObj().getEmployeeId());
            query.setString("Notes", proActivity.getActivityNotes());
            query.setInteger("UpdatedBy", proActivity.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoDepartmentId", proActivity.getDepartmentIdObj().getHcmoDepartmentId());
            query.setDate("taskStartDate", proActivity.getTaskStartDate());
            query.setDate("taskEndDate", proActivity.getTaskEndDate());
            query.setString("description", proActivity.getDescription());
            query.setInteger("HcmoProjectActivityId", proActivity.getProjectActivityId());
            
            if(proActivity.getEstimatedHours()!=null){
            	query.setString("EstimatedHours", proActivity.getEstimatedHours());
            }
            
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
    public void deleteProjectActivity(ProjectActivityVO proActivity) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectActivityVO set updatedBy=:UpdatedBy,isActive=:IsActive where projectActivityId=:ProjectActivityId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", proActivity.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("ProjectActivityId", proActivity.getProjectActivityId());
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
    public List getProjectActivityList(Integer ProjectId, Integer EmployeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectActivityVO where HcmoProjectId=:HcmoProjectId and isActive=:isActive");
            query.setInteger("HcmoProjectId", ProjectId);
            query.setInteger("isActive", 1);

            activityList = query.list();
            return activityList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO getProjectActivity(String ActivityName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro where pro.activityName =:activityName and pro.isActive=:isActive");
            q.setString("activityName", ActivityName);
            q.setInteger("isActive", 1);
            return (ProjectActivityVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO getProjectAndActivity(String ActivityName, Integer projectId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro where pro.activityName =:activityName and pro.proObj=:projectId and pro.isActive=:isActive");
            q.setString("activityName", ActivityName);
            q.setInteger("projectId", projectId);
            q.setInteger("isActive", 1);
            return (ProjectActivityVO) q.uniqueResult();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO isExistProjectActivity(ProjectActivityVO projectActivity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro where pro.activityName =:activityName and pro.proObj=:projectId and pro.projectActivityId !=:ActivityId and pro.isActive=:isActive");
            q.setString("activityName", projectActivity.getActivityName());
            q.setInteger("projectId", projectActivity.getProObj().getProjectId());
            q.setInteger("ActivityId", projectActivity.getProjectActivityId());
            q.setInteger("isActive", 1);
            return (ProjectActivityVO) q.uniqueResult();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectActivityVO isProjectAndActivityExists(String ActivityName, Integer projectId, Integer EmployeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectActivityVO as pro where pro.activityName =:activityName and pro.proObj=:projectId and pro.empIdObj=:EmployeeId and pro.isActive=:isActive");
            q.setString("activityName", ActivityName);
            q.setInteger("projectId", projectId);
            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("isActive", 1);
            return (ProjectActivityVO) q.uniqueResult();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getActivityList(Integer ProjectId, String ActivityName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectActivityVO where HcmoProjectId=:HcmoProjectId and activityName=:activityName and isActive=:isActive");
            query.setInteger("HcmoProjectId", ProjectId);
            query.setString("activityName", ActivityName);

            query.setInteger("isActive", 1);

            activityList = query.list();
            return activityList;
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List<ProjectActivityVO> getAllProjActivityByProject(ProjectVO projObj) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectActivityVO.class);
            crit.add(Restrictions.eq("proObj.projectId", projObj.getProjectId()));
            crit.add(Restrictions.eq("isActive", 1));
            
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
