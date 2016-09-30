
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetProjectAssignHibernateDao implements TimeSheetProjectAssignDao {

    @Override
    public void deleteTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetProjectAssignVO set updatedBy=:UpdatedBy,IsActive=:IsActive where timeSheetProjectAssignId=:timeSheetProjectAssignId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetProjectAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("timeSheetProjectAssignId", timeSheetProjectAssign.getTimeSheetProjectAssignId());
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
    public List getAllTimeSheetProjectAssign() {
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
    public TimeSheetProjectAssignVO getTimeSheetProjectAssign(Integer timeSheetProjectAssignId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO where timeSheetProjectAssignId=:timeSheetProjectAssignId");
            q.setInteger("timeSheetProjectAssignId", timeSheetProjectAssignId);
            return (TimeSheetProjectAssignVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
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
    public void updateTimeSheetProjectAssign(TimeSheetProjectAssignVO timeSheetProjectAssign) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetProjectAssignVO set updatedBy=:UpdatedBy,employeeName=:employeeid,projectName=:projectid,enterDate=:enter_date,enterTime=:enter_time,rejected=:rejected,rework=:rework,approve=:approve where timeSheetProjectAssignId=:timeSheetProjectAssignId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", timeSheetProjectAssign.getUpdatedBy().getEmployeeId());
            query.setInteger("timeSheetProjectAssignId", timeSheetProjectAssign.getTimeSheetProjectAssignId());
            query.setInteger("employeeid", timeSheetProjectAssign.getEmployeeName().getEmployeeId());
            query.setInteger("projectid", timeSheetProjectAssign.getProjectName().getProjectId());
            query.setDate("enter_date", timeSheetProjectAssign.getEnterDate());
            query.setDouble("enter_time", timeSheetProjectAssign.getEnterTime());
            if (timeSheetProjectAssign.getRejected() == 1) {
                query.setInteger("rejected", timeSheetProjectAssign.getRejected());
                query.setInteger("rework", 0);
                query.setInteger("approve", 0);
            } else if (timeSheetProjectAssign.getRework() == 1) {
                query.setInteger("rework", timeSheetProjectAssign.getRework());
                query.setInteger("rejected", 0);
                query.setInteger("approve", 0);
            } else {
                query.setInteger("approve", timeSheetProjectAssign.getApprove());
                query.setInteger("rework", 0);
                query.setInteger("rejected", 0);
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
    public List getEmployeeAssignedProjectList(Integer employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectAssignEmpVO.class);
            crit.add(Restrictions.eq("employeeName.employeeId", employeeId));
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List timeSheetProjectAssignSearchResult(Integer EmployeeId, Date date, Date endDate) {
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO as tcavo where tcavo.employeeName=:EmployeeId and (tcavo.enterDate>=:Startdate and tcavo.enterDate<=:Enddate) and tcavo.isActive=:IsActive");
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
    public List timeSheetProjectAssignSearch(Integer EmployeeId, Integer timeSheetProjectId, Date endDate, Integer CategoryId) {

        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO as tcavo where tcavo.employeeName=:EmployeeId and tcavo.enterDate=:enterdate and tcavo.projectName=:timeSheetProjectId and tcavo.projectActivity=:projectActivity and tcavo.isActive=:IsActive");

            q.setInteger("EmployeeId", EmployeeId);
            q.setInteger("timeSheetProjectId", timeSheetProjectId);
            q.setInteger("projectActivity", CategoryId);
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
    public void timeSheetProjectAssignDelete(EmployeesVO empVo, ProjectVO projVo) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetProjectAssignVO set IsActive=:IsActive where projectName=:projectName and employeeName=:employeeName";
            Query query = session.createQuery(sHql);
            query.setInteger("IsActive", 0);
            query.setInteger("projectName", projVo.getProjectId());
            query.setInteger("employeeName", empVo.getEmployeeId());
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
    public List getEmpPendingApprovalStatus(Integer empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO where IsActive=:IsActive and employee_id=:employee_id and approve=:approve");
            q.setInteger("IsActive", 1);
            q.setInteger("employee_id", empId);
            q.setInteger("approve", 0);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<TimeSheetProjectAssignVO> getEmpTimesheetDetails(Integer empId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetProjectAssignVO where IsActive=:IsActive and employee_id=:employee_id");
            q.setInteger("IsActive", 1);
            q.setInteger("employee_id", empId);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public ProjectActivityVO getEmpProjActivityByName(String projActivityName) {
    	 Session session = HibernateUtil.getSession();
         try {
        	 
        	 Criteria projActivity = session.createCriteria(ProjectActivityVO.class);
        	 	projActivity.add(Restrictions.eq("activityName", projActivityName));
        	 	projActivity.add(Restrictions.eq("isActive", 1));
        	 
             return (ProjectActivityVO)projActivity.uniqueResult();
         } finally {
             session.flush();
             session.close();
         }
    }
    
    @Override
    public List<TimeSheetProjectAssignVO> getEnteredHoursByProjActivity(int empid,int activityId) {
    	 Session session = HibernateUtil.getSession();
         try {
        	 Criteria crit = session.createCriteria(TimeSheetProjectAssignVO.class);
        	 crit.add(Restrictions.eq("employeeName.employeeId", empid));
        	 crit.add(Restrictions.eq("projectActivity.projectActivityId", activityId));
        	 crit.add(Restrictions.eq("isActive", 1));
        	 
             return crit.list();
         } finally {
             session.flush();
             session.close();
         }
    }
    
    @Override
    public List<ReportTimeEnteredDislayVO> getTimeEstimationReport(ReportsVO report) {
    	Session session = HibernateUtil.getSession();
    	Map mSession = ActionContext.getContext().getSession();
    	ReportTimeEnteredDislayVO timeObject = new ReportTimeEnteredDislayVO();
    	EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
    	String  timesheetApprover =  String.valueOf(mSession.get("TIMESHEET_APPROVER"));
    	String loggedInEmprole = oEmp.getRoleObj().getRoleName().trim().toLowerCase();
    	
    	String queryStr = "";
        try {
            session.beginTransaction();
            
            queryStr = " select new com.gits.rms.vo.ReportTimeEnteredDislayVO " +
            		   " (sum(time.enterTime), assign.allocatedHours, time.employeeName.empFirstName, time.projectName.projectName, time.projectActivity.activityName, " +
            		   " time.projectActivity.proObj.customerId.customerName) " +
            		   " from CustomerVO as cust, ProjectVO as proj, ProjectActivityVO as projActivity, TimeSheetProjectAssignVO as time, ProjectAssignEmpVO as assign " +
            		   " where cust.isActive=:CustIsActive and proj.isActive=:ProjIsActive and projActivity.isActive=:ProjActivityIsActive and time.isActive=:TimeIsActive " +
            		   " and cust.customerId=proj.customerId.customerId and proj.projectId=projActivity.proObj.projectId " +
            		   " and projActivity.projectActivityId=time.projectActivity.projectActivityId " +
            		   " and assign.employeeName.employeeId=time.employeeName.employeeId and assign.projectActivityId.projectActivityId=time.projectActivity.projectActivityId" +
            		   " and assign.projectName.projectId=time.projectName.projectId " +
            		   " and (time.enterDate>=:FromDate and time.enterDate<=:ToDate) and (time.enterDate BETWEEN assign.projectStartDate and assign.projectEndDate) ";
        	
            if(loggedInEmprole.equals("admin")){
            	if(report.getProjObj().getProjectId() != null){
                	queryStr += " and time.projectName.projectId=:TimeProjId ";
                }
                
                if(report.getProjActivityObj().getProjectActivityId() != null){
                	queryStr += " and time.projectActivity.projectActivityId=:TimeProjActivityId ";
                }
                
                if(report.getCust().getCustomerId() != null){
                	queryStr += " and time.projectActivity.proObj.customerId.customerId=:CustomerId ";
                }
            }
            
            if((timesheetApprover.equals("NON-APPROVER")) && (!(loggedInEmprole.equals("admin")))){
            	queryStr += " and time.employeeName.employeeId=:TimeEmpId ";
            	
            }else if(timesheetApprover.equals("BOTH")){
            	if(loggedInEmprole.equals("admin")){
            		if(report.getEmpObj().getEmployeeId() != null){
                    	queryStr += " and time.employeeName.employeeId=:TimeEmpId ";
                    } 
            	}else{
            		if(report.getEmpObj().getEmployeeId() != null){
            			queryStr += " and time.employeeName.employeeId=:TimeEmpId ";
	                }else{
	                	queryStr += " and (time.employeeName.employeeId = "+ oEmp.getEmployeeId() +" or time.employeeName.employeeId in (select t.hcmoEmployeeId from TimeSheetApproverVO as t where t.isActive=:IsActive and t.hcmoApprovingEmpId=:hcmoApprovingEmpId) ) ";
	                }
            	}
            	
            }else if(loggedInEmprole.equals("admin")){

            	if(report.getEmpObj().getEmployeeId() != null){
                	queryStr += " and time.employeeName.employeeId=:TimeEmpId ";
                }
            }
            
            queryStr += " group by time.employeeName.employeeId,time.projectActivity.projectActivityId";
            
            Query query = session.createQuery(queryStr);
            
            if(loggedInEmprole.equals("admin")){
            	if(report.getProjObj().getProjectId() != null){
                	query.setInteger("TimeProjId", report.getProjObj().getProjectId());
                }
                
                if(report.getProjActivityObj().getProjectActivityId() != null){
                	query.setInteger("TimeProjActivityId", report.getProjActivityObj().getProjectActivityId());
                }
                
                if(report.getCust().getCustomerId() != null){
                	query.setInteger("CustomerId", report.getCust().getCustomerId() );
                }
            }
            
            if((timesheetApprover.equals("NON-APPROVER")) && (!(loggedInEmprole.equals("admin")))){
            	query.setInteger("TimeEmpId", oEmp.getEmployeeId());
            	
            }else if(timesheetApprover.equals("BOTH")){
            	if(loggedInEmprole.equals("admin")){
            		if(report.getEmpObj().getEmployeeId() != null){
                		query.setInteger("TimeEmpId", report.getEmpObj().getEmployeeId());
                    }
            	}else{
	            	if(report.getEmpObj().getEmployeeId() != null){
	            		query.setInteger("TimeEmpId", report.getEmpObj().getEmployeeId());
	                }else{
	                	query.setInteger("IsActive", 1);
	                	query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
	                }
            	}
            }else if(loggedInEmprole.equals("admin")){
            	if(report.getEmpObj().getEmployeeId() != null){
            		query.setInteger("TimeEmpId", report.getEmpObj().getEmployeeId());
                }
            }

            query.setDate("FromDate", report.getFromDate());
            query.setDate("ToDate", report. getToDate());
            query.setInteger("CustIsActive", 1);
            query.setInteger("ProjIsActive", 1);
            query.setInteger("ProjActivityIsActive", 1);
            query.setInteger("TimeIsActive", 1);
            
            List<ReportTimeEnteredDislayVO> displayList = query.list(); 
            
            return displayList;
        } finally {
		      session.flush();
		      session.close();
        }
    }

}