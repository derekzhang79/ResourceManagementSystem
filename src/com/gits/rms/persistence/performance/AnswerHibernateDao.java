package com.gits.rms.persistence.performance;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.AnswerVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;
import com.gits.rms.vo.QuestionVO;

public class AnswerHibernateDao implements AnswerDao{

    @Override
    public QuestionBankGeneralInfoVO getQuestionBankGeneralInfoVO(Integer employeeId){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            crit.add(Restrictions.eq("employeeType", "Employee"));
            return (QuestionBankGeneralInfoVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionBankPeerEmployees(Integer employeeId,Integer peerEmployee){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            crit.add(Restrictions.eq("peerEmployeeId.employeeId", peerEmployee));
            crit.add(Restrictions.eq("employeeType", "Peer Employee"));
            return (QuestionBankGeneralInfoVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertAnswer(AnswerVO answer) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(answer);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
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
    public List getAllEmployeeQuestionBankName(Integer employeeId){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            crit.add(Restrictions.eq("employeeType", "Employee"));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public AnswerVO getAnswer(Integer QuestionBankId,Integer appraisingEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(AnswerVO.class);
            crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", appraisingEmpId));
            crit.add(Restrictions.eq("hcmoQuestionBankId.hcmoQuestionBankId", QuestionBankId));
            return (AnswerVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        } 
    }
    
    @Override
    public void updateAnswer(AnswerVO answer){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update AnswerVO set Answer=:Answer, " 
                 +"UpdatedBy=:UpdatedBy "    
                + "where HcmoAnswerId=:HcmoAnswerId";
            
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", answer.getUpdatedBy().getEmployeeId());
            query.setString("Answer", answer.getAnswer());
            query.setInteger("HcmoAnswerId", answer.getHcmoAnswerId());
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
    public List getAllSubEmployeeAnswerOptional(Integer employeeId,Integer questionGroupNameId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","optional"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
             if(!questionId.isEmpty()){
                 Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                    critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                    critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                    critQBank.add(Restrictions.eq("isActive",1));
                    critQBank.list();
                    for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                        QuestionBankVO qBank=it.next();
                        qBankId.add(qBank.getHcmoQuestionBankId());
                    }
             }else{
                 return dummyList;
             }
             if(!qBankId.isEmpty()){
                Criteria crit = session.createCriteria(AnswerVO.class);
                crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", employeeId));
                crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                return crit.list();
             }else{
                 return dummyList;
             }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    
    @Override
    public List getAllSubEmployeeAnswerNumbering(Integer employeeId,Integer questionGroupNameId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","numbering"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
                 
                 if(!questionId.isEmpty()){
                     Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                    critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                    critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                    critQBank.add(Restrictions.eq("isActive",1));
                    critQBank.list();
                    for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                        QuestionBankVO qBank=it.next();
                        qBankId.add(qBank.getHcmoQuestionBankId());
                    }
                 }else{
                     return dummyList;
                 }
                  if(!qBankId.isEmpty()){
                    Criteria crit = session.createCriteria(AnswerVO.class);
                    crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", employeeId));
                    crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                    crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    return crit.list();
                  }else{
                      return dummyList;
                  }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public List getAllSubEmployeeAnswerSummarry(Integer employeeId,Integer questionGroupNameId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","summary"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
                 if(!questionId.isEmpty()){
                     Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                        critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                        critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                        critQBank.add(Restrictions.eq("isActive",1));
                        critQBank.list();
                        for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                            QuestionBankVO qBank=it.next();
                            qBankId.add(qBank.getHcmoQuestionBankId());
                        }
                 }else{
                     return dummyList;
                 }
                 if(!qBankId.isEmpty()){
                        Criteria crit = session.createCriteria(AnswerVO.class);
                        crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", employeeId));
                        crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                        crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                        return crit.list();
                 }else{
                     return dummyList;
                 }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    
    @Override
    public List getAllAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer appraiserId,Integer appraisingId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
                Criteria crit = session.createCriteria(QuestionBankVO.class);
                crit.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                crit.list();
                
                for (Iterator<QuestionBankVO> it = crit.list().iterator(); it.hasNext();) {
                    QuestionBankVO qBank=it.next();
                    qBankId.add(qBank.getHcmoQuestionBankId());
                }
                 
                if(!qBankId.isEmpty()){
                    Criteria answerCrit = session.createCriteria(AnswerVO.class);
                    answerCrit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId",appraiserId));
                    answerCrit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId",appraisingId));
                    answerCrit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    answerCrit.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                    return answerCrit.list();
                }else{
                    return dummyList;
                }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public AnswerVO getParticularAnswer(Integer id){
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(AnswerVO.class);
            crit.add(Restrictions.eq("hcmoAnswerId", id));
            return (AnswerVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        } 
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerOptional(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","optional"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
                 if(!questionId.isEmpty()){    
                     Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                        critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                        critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                        critQBank.add(Restrictions.eq("isActive",1));
                        critQBank.list();
                        for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                            QuestionBankVO qBank=it.next();
                            qBankId.add(qBank.getHcmoQuestionBankId());
                        }
                 }else{
                     return dummyList;
                 } 
                 if(!qBankId.isEmpty()){   
                    Criteria crit = session.createCriteria(AnswerVO.class);
                    crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", selectPeerEmpId));
                    crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                    crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    return crit.list();
                 }else{
                     return dummyList;
                 }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerNumbering(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","numbering"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
                 if(!questionId.isEmpty()){        
                     Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                        critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                        critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                        critQBank.add(Restrictions.eq("isActive",1));
                        critQBank.list();
                        for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                            QuestionBankVO qBank=it.next();
                            qBankId.add(qBank.getHcmoQuestionBankId());
                        }
                 }else{
                     return dummyList;
                 }
                 if(!qBankId.isEmpty()){   
                    Criteria crit = session.createCriteria(AnswerVO.class);
                    crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", selectPeerEmpId));
                    crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                    crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    return crit.list();
                 }else{
                     return dummyList;
                 }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerSummarry(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
             Criteria critQuestion=session.createCriteria(QuestionVO.class);
                 critQuestion.add(Restrictions.eq("questionType","summary"));
                 critQuestion.add(Restrictions.eq("isActive",1));
                 critQuestion.list();
                 for (Iterator<QuestionVO> it = critQuestion.list().iterator(); it.hasNext();) {
                     QuestionVO question=it.next();
                     questionId.add(question.getHcmoQuestionId());
                 }
                 
                 if(!questionId.isEmpty()){   
                     Criteria critQBank=session.createCriteria(QuestionBankVO.class);
                        critQBank.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                        critQBank.add(Restrictions.in("hcmoQuestionId.hcmoQuestionId",questionId));
                        critQBank.add(Restrictions.eq("isActive",1));
                        critQBank.list();
                        for (Iterator<QuestionBankVO> it = critQBank.list().iterator(); it.hasNext();) {
                            QuestionBankVO qBank=it.next();
                            qBankId.add(qBank.getHcmoQuestionBankId());
                        }
                 }else{
                     return dummyList;
                 }
                 if(!qBankId.isEmpty()){   
                    Criteria crit = session.createCriteria(AnswerVO.class);
                    crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", selectPeerEmpId));
                    crit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", employeeId));
                    crit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    return crit.list();
                 }else{
                     return dummyList;
                 }
                
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public List getAllAppEmployeeQuestionBankName(Integer employeeId){
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("peerEmployeeId.employeeId", oEmp.getEmployeeId()));
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            crit.add(Restrictions.eq("employeeType", "Approving Employee"));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionGeneralInfoGroupName(Integer employeeId,String employeeType,Integer hcmoQuestionGroupNameIdentifiId){
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(QuestionBankGeneralInfoVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", employeeId));
            crit.add(Restrictions.eq("employeeType", "employee"));
            crit.add(Restrictions.eq("QuestionGroupNameIdentificationVO.hcmoQuestionGroupNameIdentificationId", hcmoQuestionGroupNameIdentifiId));
            return (QuestionBankGeneralInfoVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllApprovingAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
                Criteria crit = session.createCriteria(QuestionBankVO.class);
                crit.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                crit.list();
                
                for (Iterator<QuestionBankVO> it = crit.list().iterator(); it.hasNext();) {
                    QuestionBankVO qBank=it.next();
                    qBankId.add(qBank.getHcmoQuestionBankId());
                }
                if(!qBankId.isEmpty()){
                    Criteria answerCrit = session.createCriteria(AnswerVO.class);
                    answerCrit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", oEmp.getEmployeeId()));
                    answerCrit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", subEmpId));
                    answerCrit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    answerCrit.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                    return answerCrit.list();
                }else{
                    return dummyList;
                }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public List getAllPeerAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        List qBankId = new LinkedList();
        List questionId = new LinkedList();
        List dummyList = new LinkedList();
       try {
                Criteria crit = session.createCriteria(QuestionBankVO.class);
                crit.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", questionGroupNameId));
                crit.list();
                
                for (Iterator<QuestionBankVO> it = crit.list().iterator(); it.hasNext();) {
                    QuestionBankVO qBank=it.next();
                    qBankId.add(qBank.getHcmoQuestionBankId());
                }
                if(!qBankId.isEmpty()){
                    Criteria answerCrit = session.createCriteria(AnswerVO.class);
                    answerCrit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", oEmp.getEmployeeId()));
                    answerCrit.add(Restrictions.eq("hcmoAppraisingEmployeeId.employeeId", subEmpId));
                    answerCrit.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", qBankId));
                    answerCrit.add(Restrictions.eq("hcmoQuestionGeneralInfoGroup.hcmoQuestionGeneralInfoGroupId", questionGeneralInfoGroupIdListId));
                    return answerCrit.list();
                }else{
                    return dummyList;
                }
            } finally {
                session.flush();
                session.close();
            } 
    }
    
    @Override
    public AnswerVO getAppPeerAnswer(Integer QuestionBankId,Integer appraserEmpId){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(AnswerVO.class);
            crit.add(Restrictions.eq("hcmoAppraiserEmployeeId.employeeId", appraserEmpId));
            crit.add(Restrictions.eq("hcmoQuestionBankId.hcmoQuestionBankId", QuestionBankId));
            return (AnswerVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        } 
    }
    
    @Override
    public AnswerVO getAnswerView(Integer answerID){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from AnswerVO as a where a.hcmoAnswerId =:HcmoAnswerId");
            q.setInteger("HcmoAnswerId", answerID);
            return (AnswerVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
}