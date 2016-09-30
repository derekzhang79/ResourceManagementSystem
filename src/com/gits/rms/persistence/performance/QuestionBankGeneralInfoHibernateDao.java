package com.gits.rms.persistence.performance;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;

public class QuestionBankGeneralInfoHibernateDao implements QuestionBankGeneralInfoDao{
    
    private EmployeesService empService=new EmployeesDaoService();
    private List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList;
    
    @Override
    public List getAllEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        Session session=HibernateUtil.getSession();
        List empIdList = new LinkedList();
        List list = new LinkedList();
        List projAssignEmpIdList = new LinkedList();
        List employeeList= new LinkedList();
        List dummyList= new LinkedList();
        try{
            
                
            Criteria crit=session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("departmentIdObj.hcmoDepartmentId", departmentId));
            crit.add(Restrictions.eq("teamIdObj.hcmoTeamId", teamId));
            crit.add(Restrictions.eq("jobTitleIdObj.jobTitleId", jobTitleId));
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<EmployeesVO> it = crit.list().iterator(); it.hasNext();) {
                EmployeesVO empId=it.next();
                empIdList.add(empId.getEmployeeId());
            }
            if(!empIdList.isEmpty()){
                Criteria critProjectAssign=session.createCriteria(ProjectAssignEmpVO.class);
                critProjectAssign.add(Restrictions.ge("projectStartDate", startDate));
                critProjectAssign.add(Restrictions.le("projectEndDate", endDate));
                critProjectAssign.add(Restrictions.eq("projectName.projectId", projectId));
                critProjectAssign.add(Restrictions.in("employeeName.employeeId", empIdList));
                
                for (Iterator<ProjectAssignEmpVO> it = critProjectAssign.list().iterator(); it.hasNext();) {
                    ProjectAssignEmpVO projAssignEmpId=it.next();
                    projAssignEmpIdList.add(projAssignEmpId.getEmployeeName().getEmployeeId());
                }
            }else{
                return dummyList;
            }
            if(!projAssignEmpIdList.isEmpty()){
            Criteria critEmp=session.createCriteria(EmployeesVO.class);
              crit.add(Restrictions.in("employeeId", projAssignEmpIdList));
              crit.add(Restrictions.eq("isActive",1));
              crit.list();
              
              for (Iterator<EmployeesVO> it = crit.list().iterator(); it.hasNext();) {
                  EmployeesVO empId=it.next();
                  employeeList.add(empId);
              }
            }
            
//            }else if(employeeType.equals("Approving Employee")){
//                
//                Criteria crit=session.createCriteria(EmployeesVO.class);
//                crit.add(Restrictions.eq("departmentIdObj.hcmoDepartmentId", departmentId));
//                crit.add(Restrictions.eq("teamIdObj.hcmoTeamId", teamId));
//                crit.add(Restrictions.eq("jobTitleIdObj.jobTitleId", jobTitleId));
//                crit.add(Restrictions.eq("isActive",1));
//                crit.list();
//                
//                for (Iterator<EmployeesVO> it = crit.list().iterator(); it.hasNext();) {
//                    EmployeesVO empId=it.next();
//                    empIdList.add(empId.getEmployeeId());
//                }
//                
//                Criteria critEmployeeReportTo=session.createCriteria(EmployeeReportToVO.class);
//                critEmployeeReportTo.add(Restrictions.in("supEmpNumber.employeeId", empIdList));
//                
//                for (Iterator<EmployeeReportToVO> it = critEmployeeReportTo.list().iterator(); it.hasNext();) {
//                    EmployeeReportToVO empReportId=it.next();
//                    empReportToEmpId.add(empReportId.getSupEmpNumber().getEmployeeId());
//                }
//                
//                if(!empReportToEmpId.isEmpty()){
//                    Criteria critProjectAssign=session.createCriteria(ProjectAssignEmpVO.class);
//                    critProjectAssign.add(Restrictions.ge("projectStartDate", startDate));
//                    critProjectAssign.add(Restrictions.le("projectEndDate", endDate));
//                    critProjectAssign.add(Restrictions.eq("projectName.projectId", projectId));
//                    critProjectAssign.add(Restrictions.in("employeeName.employeeId", empReportToEmpId));
//                    
//                    for (Iterator<ProjectAssignEmpVO> it = critProjectAssign.list().iterator(); it.hasNext();) {
//                        ProjectAssignEmpVO projAssignEmpId=it.next();
//                        projAssignEmpIdList.add(projAssignEmpId.getEmployeeName().getEmpFullName());
//                    }
            else{
                        return dummyList;
                    }
            
            return employeeList;
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAllPeerEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        List empIdList = new LinkedList();
        List list = new LinkedList();
        List projAssignEmpIdList = new LinkedList();
        List empReportToEmpId= new LinkedList();
        List dummyList= new LinkedList();
        Session session=HibernateUtil.getSession();
        try{
        Criteria crit=session.createCriteria(EmployeesVO.class);
        crit.add(Restrictions.eq("departmentIdObj.hcmoDepartmentId", departmentId));
        crit.add(Restrictions.eq("teamIdObj.hcmoTeamId", teamId));
        crit.add(Restrictions.eq("jobTitleIdObj.jobTitleId", jobTitleId));
        crit.add(Restrictions.eq("isActive",1));
        crit.list();
        
        for (Iterator<EmployeesVO> it = crit.list().iterator(); it.hasNext();) {
            EmployeesVO empId=it.next();
            empIdList.add(empId.getEmployeeId());
        }
        
        Criteria critProjectAssign=session.createCriteria(ProjectAssignEmpVO.class);
        critProjectAssign.add(Restrictions.ge("projectStartDate", startDate));
        critProjectAssign.add(Restrictions.le("projectEndDate", endDate));
        critProjectAssign.add(Restrictions.le("projectName.projectId", projectId));
        critProjectAssign.add(Restrictions.in("employeeName.employeeId", empIdList));
        
        for (Iterator<ProjectAssignEmpVO> it = critProjectAssign.list().iterator(); it.hasNext();) {
            ProjectAssignEmpVO projAssignEmpId=it.next();
            EmployeesVO empVO=empService.getEmployees(projAssignEmpId.getEmployeeName().getEmployeeId());
            projAssignEmpIdList.add(empVO.getEmpFullName());
        }
        }finally{
            session.flush();
            session.close();          
        }
        return projAssignEmpIdList;
    }
    
    @Override
    public void insertQuestionBankGeneralInfo(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(quesBankGeneralInfo);
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
    public List getAllApprovingEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        List empIdList = new LinkedList();
        List dummyList= new LinkedList();
        Session session=HibernateUtil.getSession();
        try{
            
            Criteria crit=session.createCriteria(EmployeeReportToVO.class);
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<EmployeeReportToVO> it = crit.list().iterator(); it.hasNext();) {
                EmployeeReportToVO empReportToId=it.next();
                empIdList.add(empReportToId.getSupEmpNumber().getEmpFullName());
            }
            
            if(empIdList.isEmpty()){
                return dummyList;
            }
        
        return empIdList;
    }finally{
        session.flush();
        session.close();          
        }
    }
    
    @Override
    public List getAllApproversSubEmployeeList(){
        Session session=HibernateUtil.getSession();
        List empIdList = new LinkedList();
        List dummyList = new LinkedList();
        try{
            Map mSession = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            Criteria crit=session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("peerEmployeeId.employeeId", oEmp.getEmployeeId()));
            crit.add(Restrictions.eq("employeeType", "Approving Employee"));
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<QuestionBankGeneralInfoVO> it = crit.list().iterator(); it.hasNext();) {
                QuestionBankGeneralInfoVO quesBankEmpId=it.next();
                empIdList.add(quesBankEmpId.getHcmoEmployeeId().getEmployeeId());
            }
            if(!empIdList.isEmpty()){
                Criteria critEmployee=session.createCriteria(EmployeesVO.class);
                critEmployee.add(Restrictions.in("employeeId", empIdList));
                critEmployee.add(Restrictions.eq("isActive",1));
                return critEmployee.list();
            }
            else{
                return dummyList;
            }
            }finally{
                session.flush();
                session.close();          
          }
    }
    
    @Override
    public List getAllEmployeesPeerEmployeeList(Integer EmployeeId){
        Session session=HibernateUtil.getSession();
        List empIdList = new LinkedList();
        List dummyList = new LinkedList();
        try{
            Map mSession = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            Criteria crit=session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", EmployeeId));
            crit.add(Restrictions.eq("employeeType","Peer Employee"));
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<QuestionBankGeneralInfoVO> it = crit.list().iterator(); it.hasNext();) {
                QuestionBankGeneralInfoVO quesBankEmpId=it.next();
                empIdList.add(quesBankEmpId.getPeerEmployeeId().getEmployeeId());
            }
            if(!empIdList.isEmpty()){
                Criteria critEmployee=session.createCriteria(EmployeesVO.class);
                critEmployee.add(Restrictions.in("employeeId", empIdList));
                critEmployee.add(Restrictions.eq("isActive",1));
                critEmployee.list();
                return critEmployee.list();
            }else{
                return dummyList;
            }
            
            }finally{
                session.flush();
                session.close();          
          }
    }
    
    @Override
    public List getEmployeeListForPeerEmployee(Integer EmployeeId){
        Session session=HibernateUtil.getSession();
        List peerEmpIdList = new LinkedList();
        List empIdList = new LinkedList();
        List dummyList = new LinkedList();
        try{
            Map mSession = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            Criteria crit=session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("peerEmployeeId.employeeId", EmployeeId));
            crit.add(Restrictions.eq("employeeType","Peer Employee"));
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<QuestionBankGeneralInfoVO> it = crit.list().iterator(); it.hasNext();) {
                QuestionBankGeneralInfoVO quesBankEmpId=it.next();
                peerEmpIdList.add(quesBankEmpId.getHcmoEmployeeId().getEmployeeId());
            }
            if(peerEmpIdList.isEmpty()){
                return dummyList;
            }else{
                Criteria critEmployee=session.createCriteria(EmployeesVO.class);
                critEmployee.add(Restrictions.in("employeeId", peerEmpIdList));
                critEmployee.add(Restrictions.eq("isActive",1));
            for (Iterator<EmployeesVO> it = critEmployee.list().iterator(); it.hasNext();) {
                EmployeesVO empId=it.next();
                EmployeesVO employeeId=empService.getEmployees(empId.getEmployeeId());
                empIdList.add(employeeId);
            }
            
            if(empIdList.isEmpty()){
                return dummyList;
            }
            return empIdList;
            }
            
        }finally{
            session.flush();
            session.close();          
      }
    }
    
    @Override
    public List getPeerEmployeeQuestionBank(Integer EmployeeId){
        Session session=HibernateUtil.getSession();
        List empIdList = new LinkedList();
        try{
            Map mSession = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            Criteria crit=session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("peerEmployeeId.employeeId", oEmp.getEmployeeId()));
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", EmployeeId));
            crit.add(Restrictions.eq("employeeType","Peer Employee"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
            }finally{
                session.flush();
                session.close();          
          }
    }
    
    @Override
    public void updateGeneralInfoStatus(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionBankGeneralInfoVO set Status=:Status, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionGeneralInfoId=:HcmoQuestionGeneralInfoId";
            Query query = session.createQuery(sHql);
            query.setString("Status","Completed");
            query.setInteger("UpdatedBy", quesBankGeneralInfo.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionGeneralInfoId", quesBankGeneralInfo.getHcmoQuestionGeneralInfoId());
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
    public QuestionBankGeneralInfoVO getEmployeeQuestionBankGeneralInfo(Integer questionBankGeneralInfoId,Integer questionGroupIdentifiId,Integer questionGeneralInfoGroupIdListId){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionBankGeneralInfoVO as q where q.hcmoQuestionGeneralInfoGroup =:HcmoQuestionGeneralInfoGroup and q.hcmoQuestionGroupNameIdentificationId=:HcmoQuestionGroupNameIdentificationId and q.hcmoQuestionGeneralInfoId=:HcmoQuestionGeneralInfoId and q.isActive=:IsActive");
            q.setInteger("HcmoQuestionGeneralInfoGroup", questionGeneralInfoGroupIdListId);
            q.setInteger("HcmoQuestionGroupNameIdentificationId", questionGroupIdentifiId);
            q.setInteger("HcmoQuestionGeneralInfoId", questionBankGeneralInfoId);
//            q.setString("EmployeeType", "Employee");
            q.setInteger("IsActive", 1);
            return (QuestionBankGeneralInfoVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionBankGeneralInfo(Integer questionBankGeneralInfoId){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionBankGeneralInfoVO as q where q.hcmoQuestionGeneralInfoId=:HcmoQuestionGeneralInfoId and q.isActive=:IsActive");
            q.setInteger("HcmoQuestionGeneralInfoId", questionBankGeneralInfoId);
            q.setInteger("IsActive", 1);
            return (QuestionBankGeneralInfoVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void updateGeneralInfoaApproverComments(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionBankGeneralInfoVO set Status=:Status, " 
                           + "HcmoApprovingEmployeeId=:HcmoApprovingEmployeeId,"
                           + "ApprovingEmpComments=:ApprovingEmpComments,"
                           + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionGeneralInfoId=:HcmoQuestionGeneralInfoId";
            Query query = session.createQuery(sHql);
            query.setString("Status","Completed");
            query.setString("ApprovingEmpComments","Completed");
            query.setInteger("HcmoApprovingEmployeeId", quesBankGeneralInfo.getHcmoApprovingEmpId().getEmployeeId());
            query.setInteger("UpdatedBy", quesBankGeneralInfo.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionGeneralInfoId", quesBankGeneralInfo.getHcmoQuestionGeneralInfoId());
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
    public QuestionBankGeneralInfoVO getAllassignedEmployee(Date startDate,Date endDate,Integer employeeId){
        Session session=HibernateUtil.getSession();
        List projAssignEmpIdList = new LinkedList();
        List employeeList= new LinkedList();
        List dummyList= new LinkedList();
        try{
            
                Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
                critQuesBankGeneralInfo.add(Restrictions.ge("performanceIndStartDate", startDate));
                critQuesBankGeneralInfo.add(Restrictions.le("performanceIndEndDate", endDate));
                critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Employee"));
//                critQuesBankGeneralInfo.add(Restrictions.eq("projectName.projectId", projectId));
                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
//                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                return (QuestionBankGeneralInfoVO) critQuesBankGeneralInfo.uniqueResult();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public QuestionBankGeneralInfoVO getAllAssignedAppEmployee(Date startDate,Date endDate,Integer employeeId,Integer approvingEmployeeId){
        Session session=HibernateUtil.getSession();
        try{
            
                Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
                critQuesBankGeneralInfo.add(Restrictions.ge("performanceIndStartDate", startDate));
                critQuesBankGeneralInfo.add(Restrictions.le("performanceIndEndDate", endDate));
                critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Approving Employee"));
                critQuesBankGeneralInfo.add(Restrictions.eq("peerEmployeeId.employeeId",approvingEmployeeId));
//                critQuesBankGeneralInfo.add(Restrictions.eq("projectName.projectId", projectId));
                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
//                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                return (QuestionBankGeneralInfoVO) critQuesBankGeneralInfo.uniqueResult();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public QuestionBankGeneralInfoVO getAllAssignedPeerEmployee(Date startDate,Date endDate,Integer employeeId,Integer peerEmployeeId){
        Session session=HibernateUtil.getSession();
        try{
            
                Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
                critQuesBankGeneralInfo.add(Restrictions.ge("performanceIndStartDate", startDate));
                critQuesBankGeneralInfo.add(Restrictions.le("performanceIndEndDate", endDate));
                critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Peer Employee"));
                critQuesBankGeneralInfo.add(Restrictions.eq("peerEmployeeId.employeeId",peerEmployeeId));
//                critQuesBankGeneralInfo.add(Restrictions.eq("projectName.projectId", projectId));
                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
//                critQuesBankGeneralInfo.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                return (QuestionBankGeneralInfoVO) critQuesBankGeneralInfo.uniqueResult();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAdminEmployeeQuesGeneralInfoList(Integer employeeId){
        Session session=HibernateUtil.getSession();
        try{
            Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
            critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Employee"));
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            return critQuesBankGeneralInfo.list();
    }finally{
        session.flush();
        session.close();          
        }
   }
    
    @Override
    public List getAdminApprovingEmployeeQuesGeneralInfoList(Integer employeeId){
        Session session=HibernateUtil.getSession();
        try{
            Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
            critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Approving Employee"));
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            return critQuesBankGeneralInfo.list();
    }finally{
        session.flush();
        session.close();          
        }
    }
    
    @Override
    public List getAdminPeerEmployeeQuesGeneralInfoList(Integer employeeId){
        Session session=HibernateUtil.getSession();
        try{
            Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
            critQuesBankGeneralInfo.add(Restrictions.eq("employeeType", "Peer Employee"));
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            return critQuesBankGeneralInfo.list();
    }finally{
        session.flush();
        session.close();          
        }
    }
    
    @Override
    public void updateAdminEmployeeComments(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionBankGeneralInfoVO set AdminComments=:AdminComments, "
                + "HcmoAdminId=:HcmoAdminId, "
                + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionGeneralInfoId=:HcmoQuestionGeneralInfoId";
            Query query = session.createQuery(sHql);
            query.setString("AdminComments",quesBankGeneralInfo.getAdminComments());
            query.setInteger("HcmoAdminId", quesBankGeneralInfo.getHcmoAdminId().getEmployeeId());
            query.setInteger("UpdatedBy", quesBankGeneralInfo.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionGeneralInfoId", quesBankGeneralInfo.getHcmoQuestionGeneralInfoId());
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
    public List getQuestionBankGeneralInfoList(Integer questionGroupIdentifiId,Integer questionGeneralInfoGroupIdListId,Integer employeeId,Integer peerEmpId){
        Session session=HibernateUtil.getSession();
        try{
            Criteria critQuesBankGeneralInfo=session.createCriteria(QuestionBankGeneralInfoVO.class);
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId",questionGeneralInfoGroupIdListId));
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupIdentifiId));
            critQuesBankGeneralInfo.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            critQuesBankGeneralInfo.add(Restrictions.eq("peerEmployeeId.employeeId", peerEmpId));
            return critQuesBankGeneralInfo.list();
    }finally{
        session.flush();
        session.close();          
        }
    }
    
    @Override
    public List getAllQuestionGeneralInfoGroupId(){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionBankGeneralInfoVO as q where q.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            quesBankGeneralInfoList=q.list();
            
            Set<String> setQuestionBankGeneralGroupName = new LinkedHashSet<String>();
            Set<QuestionBankGeneralInfoVO> setVO = new LinkedHashSet<QuestionBankGeneralInfoVO>();
            
            for (QuestionBankGeneralInfoVO b : quesBankGeneralInfoList) {
                if (setQuestionBankGeneralGroupName.add(b.getHcmoQuestionGeneralInfoGroup().getName())) {
                    setVO.add(b);
                }
            }
            quesBankGeneralInfoList.clear();
            quesBankGeneralInfoList.addAll(setVO);
            return quesBankGeneralInfoList;
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllQuestionBankGeneralInfo(Integer quesGeneralInfoGroupId){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId",quesGeneralInfoGroupId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}

