package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.GoalVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TargetsVO;
import com.gits.rms.vo.TimesheetAchievedTargetVO;
import com.gits.rms.vo.TimesheetAchivedTargetReportDisplayVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class TargetsHibernateDao implements TargetsDao{
	private List<TargetsVO> targetList;
	private List<GoalVO> goalNameList;
	Map session = ActionContext.getContext().getSession();
    Integer clientId = (Integer) session.get("CLIENT_INFO");
	
//	Target Methods
	@Override
	public List getAllTargets() {
		Session session = HibernateUtil.getSession();
		try {
            Criteria crit = session.createCriteria(TargetsVO.class);
            crit.addOrder(Order.asc("targetName"));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<String> getAllTargetsName(){
		Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select DISTINCT targetName from TargetsVO where isActive=:IsActive and clientId=:clientId");
            query.setInteger("IsActive", 1);
            query.setInteger("clientId", clientId);
            return query.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public TargetsVO getTarget(Integer id){
		Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TargetsVO where isActive=:IsActive and hcmoTargetId=:HcmoTargetId and clientId=:clientId");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoTargetId", id);
            query.setInteger("clientId", clientId);
            return (TargetsVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public void insertTarget(TargetsVO target){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(target);
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
	public void updateTarget(TargetsVO target){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TargetsVO set " + "targetName=:TargetName, "
                + "targetTypeObj=:TargetTypeObj, " + "targetMode=:TargetMode,targetValue=:TargetValue, "
                + "UpdatedBy=:UpdatedBy " + "where hcmoTargetId=:HcmoTargetId";
            Query query = session.createQuery(sHql);
            query.setString("TargetName", target.getTargetName());
            query.setInteger("TargetTypeObj", target.getTargetTypeObj().getHcmoTargetTypeId());
            query.setString("TargetValue", target.getTargetValue());
            query.setString("TargetMode", target.getTargetMode());
            query.setInteger("UpdatedBy", target.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoTargetId", target.getHcmoTargetId());
            query.setInteger("clientId", clientId);
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
	public void deleteTarget(TargetsVO target){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TargetsVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoTargetId=:HcmoTargetId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", target.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoTargetId", target.getHcmoTargetId());
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
	public List targetsSearchResult(TargetsVO target){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TargetsVO.class);

            if (!(target.getTargetName().isEmpty())) {
                crit.add(Restrictions.like("targetName", target.getTargetName(), MatchMode.ANYWHERE));
            }
            if (!(target.getTargetTypeObj().getHcmoTargetTypeId() != null)) {
				crit.add(Restrictions.eq("targetTypeObj.hcmoTargetTypeId", target.getTargetTypeObj().getHcmoTargetTypeId()));
			}
            if (!(target.getTargetMode().isEmpty())) {
                crit.add(Restrictions.eq("targetMode", target.getTargetMode()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            targetList = crit.list();
            return targetList;
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<String> getTargetType(String targetName){
		Session session = HibernateUtil.getSession();
        try {
        	Criteria crit = session.createCriteria(TargetsVO.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("targetType"));
			crit.setProjection(projList);
			crit.add(Restrictions.eq("targetName", targetName));
			crit.addOrder(Order.asc("targetType"));
			crit.add( Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public String getTargetMode(String targetName, String targetType){
		Session session = HibernateUtil.getSession();
        try {
        	Criteria crit = session.createCriteria(TargetsVO.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("targetMode"));
			crit.setProjection(projList);
			crit.add(Restrictions.eq("targetName", targetName));
			crit.add(Restrictions.eq("targetType", targetType));
			crit.add( Restrictions.eq("isActive", 1));
            return (String) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
	}
	
//	Goal Methods
	@Override
	public List getAllGoal(){
		Session session = HibernateUtil.getSession();
		try {
            Criteria crit = session.createCriteria(GoalVO.class);
            crit.addOrder(Order.asc("goalName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<String> getAllGoalName(){
		Session session = HibernateUtil.getSession();
		try {
            Criteria crit = session.createCriteria(GoalVO.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.property("goalName"));
            crit.setProjection(projList);
            crit.addOrder(Order.asc("goalName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public void insertGoal(GoalVO goal){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(goal);
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
	public void updateGoal(GoalVO goal){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update GoalVO set " + "goalName=:GoalName, "
                + "UpdatedBy=:UpdatedBy " + "where hcmoGoalId=:HcmoGoalId";
            Query query = session.createQuery(sHql);
            query.setString("GoalName", goal.getGoalName());
            query.setInteger("UpdatedBy", goal.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoGoalId", goal.getHcmoGoalId());
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
	public void deleteGoal(GoalVO goal){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update GoalVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoGoalId=:HcmoGoalId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", goal.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoGoalId", goal.getHcmoGoalId());
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
	public GoalVO getGoal(Integer id){
		Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from GoalVO where isActive=:IsActive and hcmoGoalId=:HcmoGoalId");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoGoalId", id);
            return (GoalVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
	}
	
//	Employee Target & Goal Methods
	@Override
	public List<EmpTargetAndGoalVO> getAllEmpTargetAndGoal(Integer empId){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpTargetAndGoalVO.class);
            crit.add(Restrictions.eq("employeeName.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public void insertEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(empTAGObj);
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
	public EmpTargetAndGoalVO viewEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpTargetAndGoalVO.class);
            crit.add(Restrictions.eq("hcmoEmpTgId", empTAGObj.getHcmoEmpTgId()));
            crit.add(Restrictions.eq("isActive", 1));
            return (EmpTargetAndGoalVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<TimesheetAchivedTargetReportDisplayVO> getTargetAndGoalReport(ReportsVO report){
		Session session = HibernateUtil.getSession();
		Map msession = ActionContext.getContext().getSession();
	    EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
	    List emptyList = new ArrayList();
	    TimesheetAchivedTargetReportDisplayVO targetDisplayReportObj = new TimesheetAchivedTargetReportDisplayVO();
	    List<TimesheetAchivedTargetReportDisplayVO> finalTargetDisplayReportList = new ArrayList<TimesheetAchivedTargetReportDisplayVO>();
	    
        try {
        	Date startDate;
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            
            if(report.getEmpObj() !=null){
            	if(report.getEmpObj().getEmployeeId() !=null ){
                	crit.add(Restrictions.eq("employeeName.employeeId", report.getEmpObj().getEmployeeId()));
                }
            }else{
            	crit.add(Restrictions.eq("employeeName.employeeId", oEmp.getEmployeeId()));
            }
            
            crit.add(Restrictions.ge("projectStartDate", report.getFromDate()));
            crit.add(Restrictions.le("projectEndDate", report.getToDate()));
            crit.add(Restrictions.eq("isActive", 1));
            
            List<ProjectAssignEmpVO> assignProjList = new ArrayList<ProjectAssignEmpVO>(); 
            ProjectAssignEmpVO assignProjObj;
            assignProjList.addAll(crit.list());
            
            if(!(assignProjList.isEmpty())){
            	for (Iterator<ProjectAssignEmpVO> it = assignProjList.iterator(); it.hasNext();) {
                	assignProjObj = it.next();
                	System.out.println(assignProjObj.getProjectAssignEmpId());
//                	startDate = assignProjObj.getProjectStartDate();
                	
                	Criteria critAssignTarget = session.createCriteria(TimesheetAssignProjectTargetVO.class);
                	critAssignTarget.add(Restrictions.eq("proAssignObj.projectAssignEmpId", assignProjObj.getProjectAssignEmpId()));
                	critAssignTarget.add(Restrictions.eq("isActive", 1));
                	
                	List<TimesheetAssignProjectTargetVO> assignTargetList = new ArrayList<TimesheetAssignProjectTargetVO>();
                	TimesheetAssignProjectTargetVO assignedTarget;
                	assignTargetList.addAll(critAssignTarget.list());
                	
                	if(!(assignTargetList.isEmpty())){
                		targetDisplayReportObj.setProjectId(assignProjObj.getProjectName().getProjectId());
                		targetDisplayReportObj.setProjectName(assignProjObj.getProjectName().getProjectName());
                		
                		targetDisplayReportObj.setActivityId(assignProjObj.getProjectActivityId().getProjectActivityId());
                		targetDisplayReportObj.setActivityName(assignProjObj.getProjectActivityId().getActivityName());
                		
                		targetDisplayReportObj.setActivityStartDate(assignProjObj.getProjectStartDate());
                		targetDisplayReportObj.setActivityEndDate(assignProjObj.getProjectEndDate());
                		
                		for (Iterator<TimesheetAssignProjectTargetVO> itAssignedProj = assignTargetList.iterator(); itAssignedProj.hasNext();) {
                			assignedTarget = itAssignedProj.next();
                			System.out.println("assignedTarget.getHcmoTsAssignProjTargetId() :"+assignedTarget.getHcmoTsAssignProjTargetId());
                			
                			Criteria critAchivedTarget = session.createCriteria(TimesheetAchievedTargetVO.class);
                			critAchivedTarget.add(Restrictions.eq("hcmoTsAssignedTargetId", assignedTarget.getHcmoTsAssignProjTargetId()));
                			critAchivedTarget.add(Restrictions.eq("isActive", 1));
                        	
                        	
                        	List<TimesheetAchievedTargetVO> achivedTargetList = new ArrayList<TimesheetAchievedTargetVO>();
                        	TimesheetAchievedTargetVO achivedTargetObj;
                        	achivedTargetList.addAll(critAchivedTarget.list());
                        	
                        	if(!(achivedTargetList.isEmpty())){
                        		for (Iterator<TimesheetAchievedTargetVO> achivedTargetIterator = achivedTargetList.iterator(); itAssignedProj.hasNext();) {
                        			achivedTargetObj = achivedTargetIterator.next();
                        			
                        			targetDisplayReportObj.setDateFrom(achivedTargetObj.getStartDate());
                        			targetDisplayReportObj.setDateTo(achivedTargetObj.getEndDate());
                        			targetDisplayReportObj.setTargetAchieved(achivedTargetObj.getTargetAchieved());
                        			targetDisplayReportObj.setTargetNotes(achivedTargetObj.getTargetNotes());
                        			
                        			finalTargetDisplayReportList.add(targetDisplayReportObj);
                        		}
                        	}else{
                        		finalTargetDisplayReportList.add(targetDisplayReportObj);
                        	}
                		}
                	}
                }
            }else{
            	return emptyList;
            }
            return finalTargetDisplayReportList;
            
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<TargetsVO> getAllTargetByProjAndActivity(Integer projId,Integer activityId){
		Session session = HibernateUtil.getSession();
        try {
        	String sHql = " from TargetsVO where projObj.projectId=:ProId and projActivityObj.projectActivityId=:ActivityId and isActive=:IsActive";
            Query query = session.createQuery(sHql);
            query.setInteger("ProId", projId);
            query.setInteger("ActivityId", activityId);
            query.setInteger("IsActive", 1);
            return query.list();
            
        	
//            Criteria crit = session.createCriteria(TargetsVO.class);
//            crit.add(Restrictions.eq("projObj.projectId", projId));
//            crit.add(Restrictions.eq("projActivityObj.projectActivityId", activityId));
//            crit.add(Restrictions.eq("isActive", 1));
//            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public List<TimesheetAssignProjectTargetVO> getAllAssignedTargetByAssignedId(List<Integer> assignTargetId){
		Session session = HibernateUtil.getSession();
        try {
        	
            Criteria crit = session.createCriteria(TimesheetAssignProjectTargetVO.class);
//            crit.add(Restrictions.eq("proAssignObj.projectAssignEmpId", assignTargetId));
            crit.add(Restrictions.in("proAssignObj.projectAssignEmpId", assignTargetId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public boolean checkAssignedTarget(EmployeesVO empObj,TargetsVO targetObj,ProjectAssignEmpVO proAssignObj){
		Session session = HibernateUtil.getSession();
        try {
        	
            Criteria crit = session.createCriteria(TimesheetAssignProjectTargetVO.class);
            crit.add(Restrictions.eq("employeeObj.employeeId", empObj.getEmployeeId()));
            crit.add(Restrictions.eq("targetObj.hcmoTargetId", targetObj.getHcmoTargetId()));
            crit.add(Restrictions.eq("proAssignObj.projectAssignEmpId", proAssignObj.getProjectAssignEmpId()));
            crit.add(Restrictions.eq("isActive", 1));
            
            if( crit.list().isEmpty()){
            	return false;
            }else {
            	return true;
            }
            
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public void insertAssignProjTarget(TimesheetAssignProjectTargetVO assignTarget){

		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(assignTarget);
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
	public List<TimesheetAssignProjectTargetVO> getAllEmpAssignedTarget(Integer empId){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetAssignProjectTargetVO.class);
            crit.add(Restrictions.eq("employeeObj.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public TimesheetAssignProjectTargetVO getEmpAssignedTarget(Integer assignedTargetId){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetAssignProjectTargetVO.class);
            crit.add(Restrictions.eq("hcmoTsAssignProjTargetId", assignedTargetId));
            crit.add(Restrictions.eq("isActive", 1));
            return (TimesheetAssignProjectTargetVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
    public void UpdateTsAchievedTarget(TimesheetAssignProjectTargetVO assignTargetObj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimesheetAssignProjectTargetVO set targetAchieved=:TargetAchieved, " + "targetNotes=:TargetNotes," + "updatedBy=:UpdatedBy "
                + "where hcmoTsAssignProjTargetId=:HcmoTsAssignProjTargetId";
            Query query = session.createQuery(sHql);
            query.setString("TargetAchieved", assignTargetObj.getTargetAchieved());
            query.setString("TargetNotes", assignTargetObj.getTargetNotes());
            query.setInteger("HcmoTsAssignProjTargetId", assignTargetObj.getHcmoTsAssignProjTargetId());
            query.setInteger("UpdatedBy", assignTargetObj.getUpdatedBy().getEmployeeId());
            
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
	public List<TimesheetAssignProjectTargetVO> getAssignedTargetSearchList(Integer empId){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetAssignProjectTargetVO.class);
            crit.add(Restrictions.eq("employeeObj.employeeId", empId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
	
	@Override
	public void insertAchivedTarget(TimesheetAchievedTargetVO achivedTarget){
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(achivedTarget);
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
	public List<TimesheetAchievedTargetVO> getAllEmpAchivedTargets(TimesheetAssignProjectTargetVO assignTargetObj){
		Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TimesheetAchievedTargetVO.class);
            crit.add(Restrictions.eq("hcmoTsAssignedTargetId.hcmoTsAssignProjTargetId", assignTargetObj.getHcmoTsAssignProjTargetId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
	}
}
