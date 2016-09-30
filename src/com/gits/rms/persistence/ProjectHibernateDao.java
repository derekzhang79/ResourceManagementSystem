
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class ProjectHibernateDao implements ProjectDao {

    private List<ProjectVO> projList;

    @Override
    public List getAllProjects() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectVO.class);
            crit.addOrder(Order.asc("projectName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List projectsSearchResult(ProjectVO proj) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectVO.class);

            if (proj.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", proj.getEmpIdObj().getEmployeeId()));
            }
            if (proj.getCustomerId().getCustomerId() != null) {
                crit.add(Restrictions.eq("customerId.customerId", proj.getCustomerId().getCustomerId()));
            }
            if (!(proj.getProjectName().isEmpty())) {
                crit.add(Restrictions.like("projectName", proj.getProjectName(), MatchMode.ANYWHERE));
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
    public List loadProject(Integer projcode) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectVO as pro left join fetch pro.customerId as cust where pro.isActive=:IsActive and pro.projectId=:Name");
            query.setInteger("IsActive", 1);
            query.setInteger("Name", projcode);
            projList = query.list();
            return projList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectVO getProject(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectVO as pro left join fetch pro.customerId cust left join fetch pro.empIdObj emp where pro.projectId =:ProjectId");
            q.setInteger("ProjectId", id);
            return (ProjectVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectVO getIdForProName(String projectName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectVO where isActive=:IsActive and Name=:proName");
            query.setInteger("IsActive", 1);
            query.setString("proName", projectName);
            return (ProjectVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertProject(ProjectVO proj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(proj);
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
    public void updateProject(ProjectVO proj) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            String sHql = "update ProjectVO set " + "HcmoCustomerId=:HcmoCustomerId, "
                    + "ProjectOwner=:ProjectOwner, " + "Name=:Name, " + "Description=:Description, "
                    + "UpdatedBy=:UpdatedBy ";
            
            if(proj.getEstimatedHours()!=null){
            	sHql += ", estimatedHours=:EstimatedHours";
            }
            
            sHql += " where projectId=:ProjectId";
           
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoCustomerId", proj.getCustomerId().getCustomerId());
            query.setInteger("ProjectOwner", proj.getEmpIdObj().getEmployeeId());
            query.setString("Name", proj.getProjectName());
            query.setString("Description", proj.getProjectDesc());
            query.setInteger("UpdatedBy", proj.getUpdatedBy().getEmployeeId());
            query.setInteger("ProjectId", proj.getProjectId());
            
            if(proj.getEstimatedHours()!=null){
            	query.setString("EstimatedHours", proj.getEstimatedHours());
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
    public void deleteProject(ProjectVO proj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectVO set updatedBy=:UpdatedBy,isActive=:IsActive where projectId=:projectId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", proj.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("projectId", proj.getProjectId());
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
    public List getProjectsReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectVO.class);
            if (report.getFromDate() != null) {
                crit.add(Restrictions.ge("created", report.getFromDate()));
            }
            if (report.getToDate() != null) {
                crit.add(Restrictions.le("created", report.getToDate()));
            }
            if (report.getCust().getCustomerId() != null) {
                crit.add(Restrictions.eq("customerId.customerId", report.getCust().getCustomerId()));
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
    public ProjectVO getProjectName(String projectName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectVO as pro left join fetch pro.customerId cust left join fetch pro.empIdObj emp where pro.projectName=:projectName");
            q.setString("projectName", projectName);
            return (ProjectVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<EmployeesVO> getEmployeeId(Integer roleId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("roleObj.hcmoRoleId", roleId));
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkProjectInProActivity(ProjectVO proj) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectActivityVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("proObj.projectId", proj.getProjectId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectVO> isProjectOwner(int employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectVO where isActive=:IsActive and empIdObj.employeeId=:employeeId");
            query.setInteger("IsActive", 1);
            query.setInteger("employeeId", employeeId);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
