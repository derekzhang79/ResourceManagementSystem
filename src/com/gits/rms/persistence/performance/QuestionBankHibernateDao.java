package com.gits.rms.persistence.performance;

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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.AnswerVO;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;
import com.gits.rms.vo.QuestionVO;

public class QuestionBankHibernateDao implements QuestionBankDao{

    private List<QuestionGroupNameIdentificationVO> questionBankList;
    
    @Override
    public List getAllQuestions(){
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(QuestionVO.class);
            crit.addOrder(Order.asc("question"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public void insertQuestionBank(QuestionBankVO quesBank){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(quesBank);
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
    public List getAllQuestionBankName(){
        
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGroupNameIdentificationVO as q where q.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertQuestionGroup(QuestionGroupNameIdentificationVO quesGroupId){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(quesGroupId);
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
    public List checkQuestionBank(QuestionGroupNameIdentificationVO quesGroupId){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGroupNameIdentificationVO as q where q.name=:Name and q.isActive =:IsActive");
            q.setString("Name", quesGroupId.getName());
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionGroupNameIdentificationVO getQuestionGroupNameIdentificationVO(Integer id){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGroupNameIdentificationVO as d where d.hcmoQuestionGroupNameIdentificationId =:HcmoQuestionGroupNameIdentificationId");
            q.setInteger("HcmoQuestionGroupNameIdentificationId", id);
            return (QuestionGroupNameIdentificationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public EmployeesVO getEmployeeId(String empFullName){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as e where e.empFullName =:EmpFullName");
            q.setString("EmpFullName", empFullName);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllQuestionBank(Integer id){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionBankVO as q where q.hcmoQuestionGroupNameIdentificationId =:HcmoQuestionGroupNameIdentificationId");
            q.setInteger("HcmoQuestionGroupNameIdentificationId", id);
            return  q.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionBankVO getQuestionBank(Integer id){
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(QuestionBankVO.class);
            crit.add(Restrictions.eq("hcmoQuestionBankId",id));
            crit.add(Restrictions.eq("isActive",1));
            return (QuestionBankVO) crit.uniqueResult();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAnswerCountForQuestion(Integer id){
        Session session=HibernateUtil.getSession();
        List questionGroupNameList = new LinkedList();
        List dummyList = new LinkedList();
        try{
            Map mSession = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            Criteria crit=session.createCriteria(QuestionBankVO.class);
            crit.add(Restrictions.eq("hcmoQuestionGroupNameIdentificationId.hcmoQuestionGroupNameIdentificationId", id));
            crit.add(Restrictions.eq("isActive",1));
            crit.list();
            
            for (Iterator<QuestionBankVO> it = crit.list().iterator(); it.hasNext();) {
                QuestionBankVO quesBankId=it.next();
                questionGroupNameList.add(quesBankId.getHcmoQuestionBankId());
            }
            if(!questionGroupNameList.isEmpty()){
                Criteria critEmployee=session.createCriteria(AnswerVO.class);
                critEmployee.add(Restrictions.in("hcmoQuestionBankId.hcmoQuestionBankId", questionGroupNameList));
                critEmployee.add(Restrictions.eq("isActive",1));
                return critEmployee.list();
            }
        
        return dummyList;
        }finally{
            session.flush();
            session.close();          
        }
    }
 
    @Override
    public List getAllQuestionGroupNameIdentification(){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGroupNameIdentificationVO as q where q.isActive =:IsActive");
            q.setInteger("IsActive", 1);
            questionBankList=q.list();
            
            Set<String> setQuestionGroupName = new LinkedHashSet<String>();
            Set<QuestionGroupNameIdentificationVO> setVO = new LinkedHashSet<QuestionGroupNameIdentificationVO>();
            
            for (QuestionGroupNameIdentificationVO b : questionBankList) {
                if (setQuestionGroupName.add(b.getName())) {
                    setVO.add(b);
                }
            }
            questionBankList.clear();
            questionBankList.addAll(setVO);
            return questionBankList;
        } finally {
            session.flush();
            session.close();
        }
    }
}
