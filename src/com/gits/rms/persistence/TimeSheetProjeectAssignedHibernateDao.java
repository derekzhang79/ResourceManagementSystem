
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetProjeectAssignedHibernateDao implements TimeSheetProjectAssignedDao {
    @Override
    public List getAllTsProjAss() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ProjectAssignEmpVO getTsProjAss(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ProjectAssignEmpVO as d where d.projectAssignEmpId =:HcmoTsEmpProjRelId");
            q.setInteger("HcmoTsEmpProjRelId", id);
            return (ProjectAssignEmpVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTsProjAss(ProjectAssignEmpVO tsempproj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tsempproj);
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
    public void updateTsProjAss(ProjectAssignEmpVO tsempproj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = new String("update ProjectAssignEmpVO set "
                + "HcmoEmployeeId=:HcmoEmployeeId, " + "HcmoProjectId=:HcmoProjectId, "
                + "projectActivityId=:projectActivityId, " + "ProjectStartDate=:ProjectStartDate, "
                + "ProjectEndDate=:ProjectEndDate, " + "UpdatedBy=:UpdatedBy, "
                + "projTargetName=:ProjTargetName, " + "projTargetType=:ProjTargetType, "
                + "projTargetMode=:ProjTargetMode, " + "projGoalName=:ProjGoalName, "
                + "allocatedHours=:AllocatedHours "
                + "where projectAssignEmpId=:HcmoTsEmpProjRelId and isActive=:isActive");
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", tsempproj.getEmployeeName().getEmployeeId());
            query.setInteger("HcmoProjectId", tsempproj.getProjectName().getProjectId());
            query.setInteger("projectActivityId", tsempproj.getProjectActivityId().getProjectActivityId());
            query.setString("ProjTargetName", tsempproj.getProjTargetName());
            query.setString("ProjTargetType", tsempproj.getProjTargetType());
            query.setString("ProjTargetMode", tsempproj.getProjTargetMode());
            query.setString("ProjGoalName", tsempproj.getProjGoalName());
            query.setString("AllocatedHours", tsempproj.getAllocatedHours());
            query.setDate("ProjectStartDate", tsempproj.getProjectStartDate());
            query.setDate("ProjectEndDate", tsempproj.getProjectEndDate());
            query.setInteger("UpdatedBy", tsempproj.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoTsEmpProjRelId", tsempproj.getProjectAssignEmpId());
            query.setShort("isActive", (short) 1);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public void deleteTsProjAss(ProjectAssignEmpVO tsempproj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ProjectAssignEmpVO set updatedBy=:UpdatedBy,isActive=:IsActive where projectAssignEmpId=:HcmoTsEmpProjRelId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", tsempproj.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoTsEmpProjRelId", tsempproj.getProjectAssignEmpId());
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
    public List tsProjAssSearchResult(ProjectAssignEmpVO tsempproj) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List<ProjectAssignEmpVO> tsProjAssList;
        List<Integer> empIdList = new LinkedList();
        ProjectAssignEmpVO tsProjAssignedForSubEmp = new ProjectAssignEmpVO();

        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            if (tsempproj.getEmployeeName().getEmployeeId() != null) {
                Integer selEmpId = tsempproj.getEmployeeName().getEmployeeId();
                crit.add(Restrictions.eq("employeeName.employeeId", selEmpId));
            } else {
                tsProjAssList = getCurrentSubEmployeeListForTsProjectAssigned();
                for (Iterator<ProjectAssignEmpVO> it = tsProjAssList.iterator(); it.hasNext();) {
                    tsProjAssignedForSubEmp = it.next();
                    empIdList.add(tsProjAssignedForSubEmp.getEmployeeName().getEmployeeId());
                }
            }
            if (!(empIdList.isEmpty())) {
                crit.add(Restrictions.in("employeeName.employeeId", empIdList));
            }

            if (tsempproj.getProjectName().getProjectId() != null) {
                crit.add(Restrictions.like("projectName.projectId", tsempproj.getProjectName().getProjectId()));
            }

            if (tsempproj.getProjectStartDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() != null)) {
                    crit.add(Restrictions.ge("projectStartDate", tsempproj.getProjectStartDate()));
                    tsempproj.getMessage().add("AssignProjectStartDateMessage");
                } else if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() == null)) {
                    crit.add(Restrictions.ge("projectStartDate", tsempproj.getProjectStartDate()));
                } else if ((tsempproj.getProjectStartDateEnds() != null)
                    && (tsempproj.getProjectStartDate() == null)) {
                    crit.add(Restrictions.ge("projectStartDate", tsempproj.getProjectStartDateEnds()));
                }
            } else if (tsempproj.getProjectStartDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() != null)) {
                    crit.add(Restrictions.le("projectStartDate", tsempproj.getProjectStartDate()));
                    tsempproj.getMessage().add("AssignProjectStartDateMessage");
                } else if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() == null)) {
                    crit.add(Restrictions.le("projectStartDate", tsempproj.getProjectStartDate()));
                } else if ((tsempproj.getProjectStartDateEnds() != null)
                    && (tsempproj.getProjectStartDate() == null)) {
                    crit.add(Restrictions.le("projectStartDate", tsempproj.getProjectStartDateEnds()));
                }
            } else if (tsempproj.getProjectStartDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() != null)) {
                    crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDate()));
                    tsempproj.getMessage().add("AssignProjectStartDateMessage");
                } else if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDate()));
                } else if ((tsempproj.getProjectStartDateEnds() != null)
                    && (tsempproj.getProjectStartDate() == null)) {
                    crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDateEnds()));
                }
            } else if (tsempproj.getProjectStartDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() != null)) {
                    if (tsempproj.getProjectStartDate().before(tsempproj.getProjectStartDateEnds())) {
                        crit.add(Restrictions.between("projectStartDate", tsempproj.getProjectStartDate(), tsempproj.getProjectStartDateEnds()));
                    } else if (tsempproj.getProjectStartDate().after(tsempproj.getProjectStartDateEnds())) {
                        crit.add(Restrictions.between("projectStartDate", tsempproj.getProjectStartDateEnds(), tsempproj.getProjectStartDate()));
                    } else if (tsempproj.getProjectStartDate().equals(tsempproj.getProjectStartDateEnds())) {
                        crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDate()));
                    }
                } else if ((tsempproj.getProjectStartDate() != null)
                    && (tsempproj.getProjectStartDateEnds() == null)) {
                    crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDate()));
                } else if ((tsempproj.getProjectStartDateEnds() != null)
                    && (tsempproj.getProjectStartDate() == null)) {
                    crit.add(Restrictions.eq("projectStartDate", tsempproj.getProjectStartDateEnds()));
                }
            }

            if (tsempproj.getProjectEndDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() != null)) {
                    crit.add(Restrictions.ge("projectEndDate", tsempproj.getProjectEndDate()));
                    tsempproj.getMessage().add("AssignProjectEndDateMessage");
                } else if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() == null)) {
                    crit.add(Restrictions.ge("projectEndDate", tsempproj.getProjectEndDate()));
                } else if ((tsempproj.getProjectEndDateEnds() != null)
                    && (tsempproj.getProjectEndDate() == null)) {
                    crit.add(Restrictions.ge("projectEndDate", tsempproj.getProjectEndDateEnds()));
                }
            } else if (tsempproj.getProjectEndDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() != null)) {
                    crit.add(Restrictions.le("projectEndDate", tsempproj.getProjectEndDate()));
                    tsempproj.getMessage().add("AssignProjectEndDateMessage");

                } else if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() == null)) {
                    crit.add(Restrictions.le("projectEndDate", tsempproj.getProjectEndDate()));
                } else if ((tsempproj.getProjectEndDateEnds() != null)
                    && (tsempproj.getProjectEndDate() == null)) {
                    crit.add(Restrictions.le("projectEndDate", tsempproj.getProjectEndDateEnds()));
                }
            } else if (tsempproj.getProjectEndDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() != null)) {
                    crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDate()));
                    tsempproj.getMessage().add("AssignProjectEndDateMessage");
                } else if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDate()));
                } else if ((tsempproj.getProjectEndDateEnds() != null)
                    && (tsempproj.getProjectEndDate() == null)) {
                    crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDateEnds()));
                }
            } else if (tsempproj.getProjectEndDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() != null)) {
                    if (tsempproj.getProjectEndDate().before(tsempproj.getProjectEndDateEnds())) {
                        crit.add(Restrictions.between("projectEndDate", tsempproj.getProjectEndDate(), tsempproj.getProjectEndDateEnds()));
                    } else if (tsempproj.getProjectEndDate().after(tsempproj.getProjectEndDateEnds())) {
                        crit.add(Restrictions.between("projectEndDate", tsempproj.getProjectEndDateEnds(), tsempproj.getProjectEndDate()));
                    } else if (tsempproj.getProjectEndDate().equals(tsempproj.getProjectEndDateEnds())) {
                        crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDate()));
                    }
                } else if ((tsempproj.getProjectEndDate() != null)
                    && (tsempproj.getProjectEndDateEnds() == null)) {
                    crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDate()));
                } else if ((tsempproj.getProjectEndDateEnds() != null)
                    && (tsempproj.getProjectEndDate() == null)) {
                    crit.add(Restrictions.eq("projectEndDate", tsempproj.getProjectEndDateEnds()));
                }
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
    public List getCurrentSubEmployeeForTimeSheet() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select t.hcmoEmployeeId from TimeSheetApproverVO as t where t.isActive=:IsActive and t.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectAssignEmpVO> getCurrentSubEmployeeListForTsProjectAssigned() {

        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectAssignEmpVO as emp where emp.isActive=:IsActive and emp.employeeName.employeeId in(select t.hcmoEmployeeId from TimeSheetApproverVO as t where t.isActive=:IsActive and t.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectAssignEmpVO> searchDuplicateProjectAssign(ProjectAssignEmpVO projAssignEmpVo) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectAssignEmpVO as emp where emp.isActive=:IsActive and emp.employeeName.employeeId=:employeeid and emp.projectName.projectId=:projectid and emp.projectActivityId.projectActivityId=:activityid)");
            query.setInteger("employeeid", projAssignEmpVo.getEmployeeName().getEmployeeId());
            query.setInteger("projectid", projAssignEmpVo.getProjectName().getProjectId());
            query.setInteger("activityid", projAssignEmpVo.getProjectActivityId().getProjectActivityId());
            query.setInteger("IsActive", 1);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectAssignEmpVO> isExistProjectAssign(ProjectAssignEmpVO projAssign) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectAssignEmpVO as emp where emp.isActive=:IsActive and emp.employeeName.employeeId=:employeeid and emp.projectName.projectId=:projectid and emp.projectActivityId.projectActivityId=:activityid and emp.projectAssignEmpId !=:projectAssignEmpId )");
            query.setInteger("projectAssignEmpId", projAssign.getProjectAssignEmpId());
            query.setInteger("employeeid", projAssign.getEmployeeName().getEmployeeId());
            query.setInteger("projectid", projAssign.getProjectName().getProjectId());
            query.setInteger("activityid", projAssign.getProjectActivityId().getProjectActivityId());
            query.setInteger("IsActive", 1);
            return query.list();
        }finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ProjectAssignEmpVO> getEmpTargetAndGoal(Integer empId){
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ProjectAssignEmpVO where employeeName.employeeId=:Employeeid " +
//            		"and projTargetName!='null' and projTargetType!='null' and projTargetMode!='null' and projGoalName!='null' " +
            		"and isActive=:IsActive");
            query.setInteger("Employeeid", empId);
//            query.setString("ProjTargetName", null);
//            query.setString("ProjTargetType", null);
//            query.setString("ProjTargetMode", null);
//            query.setString("ProjGoalName", null);
            query.setInteger("IsActive", 1);
            return query.list();
        }finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List<ProjectAssignEmpVO> getEmployeeProjectActivity(Integer empId, Integer projectId){
    	Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            crit.add(Restrictions.eq("employeeName.employeeId", empId));
            crit.add(Restrictions.eq("projectName.projectId", projectId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public ProjectAssignEmpVO getEmployeeTargetAndGoal(Integer empId, Integer projectId, Integer projActivityId){
    	Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            crit.add(Restrictions.eq("employeeName.employeeId", empId));
            crit.add(Restrictions.eq("projectName.projectId", projectId));
            crit.add(Restrictions.eq("projectActivityId.projectActivityId", projActivityId));
            crit.add(Restrictions.eq("isActive", 1));
            return (ProjectAssignEmpVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List<TimeSheetProjectAssignVO> checkAssignedProjInTimesheet(ProjectAssignEmpVO assignProj){
    	Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimeSheetProjectAssignVO.class);
            crit.add(Restrictions.eq("projectActivity.projectActivityId", assignProj.getProjectActivityId().getProjectActivityId()));
            crit.add(Restrictions.eq("projectName.projectId", assignProj.getProjectName().getProjectId()));
            crit.add(Restrictions.between("enterDate", assignProj.getProjectStartDate(),assignProj.getProjectEndDate()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
