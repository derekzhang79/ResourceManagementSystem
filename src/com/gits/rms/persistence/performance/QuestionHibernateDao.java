package com.gits.rms.persistence.performance;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.SubCategoryVO;

public class QuestionHibernateDao implements QuestionDao{

    @Override
    public QuestionVO getQuestions(Integer id) {
        Session session=HibernateUtil.getSession();
        try{
            Query q=session.createQuery("from QuestionVO as q where q.hcmoQuestionId=:HcmoQuestionId");
            q.setInteger("HcmoQuestionId", id);
            return (QuestionVO) q.uniqueResult();
        }finally{
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void insertQuestion(QuestionVO question){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(question);
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
    public List getAllOptionalQuestions(){
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(QuestionVO.class);
            crit.addOrder(Order.asc("question"));
            crit.add(Restrictions.eq("questionType","optional"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAllNumberingQuestions(){
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(QuestionVO.class);
            crit.addOrder(Order.asc("question"));
            crit.add(Restrictions.eq("questionType","numbering"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAllSummaryQuestions(){
        Session session=HibernateUtil.getSession();
        try{
            Criteria crit=session.createCriteria(QuestionVO.class);
            crit.addOrder(Order.asc("question"));
            crit.add(Restrictions.eq("questionType","summary"));
            crit.add(Restrictions.eq("isActive",1));
            return crit.list();
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public void updateOptionalQuestion(QuestionVO question){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionVO set Question=:Question, " 
                + "Option1=:Option1, " + "Option2=:Option2, "
                + "HcmoCategoryId=:HcmoCategoryId, " + "Option3=:Option3, "
                + "HcmoSubCategoryId=:HcmoSubCategoryId, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionId=:HcmoQuestionId";
            Query query = session.createQuery(sHql);
            query.setString("Question", question.getQuestion());
            query.setString("Option1", question.getOption1());
            query.setString("Option2", question.getOption2());
            query.setString("Option3", question.getOption3());
            query.setInteger("HcmoCategoryId", question.getHcmoCategoryId().getHcmoCategoryId());
            query.setInteger("HcmoSubCategoryId", question.getHcmoSubCategoryId().getHcmoSubCategoryId());
            query.setInteger("UpdatedBy", question.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionId", question.getHcmoQuestionId());
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
    public void updateNumberingQuestion(QuestionVO question){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionVO set Question=:Question, " 
                + "MinRate=:MinRate, " + "MaxRate=:MaxRate, "
                + "HcmoCategoryId=:HcmoCategoryId, " 
                + "HcmoSubCategoryId=:HcmoSubCategoryId, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionId=:HcmoQuestionId";
            Query query = session.createQuery(sHql);
            query.setString("Question", question.getQuestion());
            query.setString("MinRate", question.getMinRate());
            query.setString("MaxRate", question.getMaxRate());
            query.setInteger("HcmoCategoryId", question.getHcmoCategoryId().getHcmoCategoryId());
            query.setInteger("HcmoSubCategoryId", question.getHcmoSubCategoryId().getHcmoSubCategoryId());
            query.setInteger("UpdatedBy", question.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionId", question.getHcmoQuestionId());
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
    public void updateSummaryQuestion(QuestionVO question){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update QuestionVO set Question=:Question, " 
                + "HcmoCategoryId=:HcmoCategoryId, " 
                + "HcmoSubCategoryId=:HcmoSubCategoryId, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoQuestionId=:HcmoQuestionId";
            Query query = session.createQuery(sHql);
            query.setString("Question", question.getQuestion());
            query.setInteger("HcmoCategoryId", question.getHcmoCategoryId().getHcmoCategoryId());
            query.setInteger("HcmoSubCategoryId", question.getHcmoSubCategoryId().getHcmoSubCategoryId());
            query.setInteger("UpdatedBy", question.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoQuestionId", question.getHcmoQuestionId());
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
    public List getAllOptionQuestionsView(List questionList){
        Session session=HibernateUtil.getSession();
        List quesList = new LinkedList();
        List dummyList = new LinkedList();
        for(int i=0;i<questionList.size();i++){
            QuestionBankVO quesBankId=(QuestionBankVO) questionList.get(i);
            QuestionVO quesId=getQuestions(quesBankId.getHcmoQuestionId().getHcmoQuestionId());
            if(quesId.getQuestionType().equals("optional")){
                quesList.add(quesId.getHcmoQuestionId());
            }
        }
        try{
            if(!quesList.isEmpty()){
                Criteria crit=session.createCriteria(QuestionVO.class);
                crit.add(Restrictions.eq("questionType","optional"));
                crit.add(Restrictions.in("hcmoQuestionId",quesList));
                crit.add(Restrictions.eq("isActive",1));
                return crit.list();
            }else{
                return dummyList;
            }
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAllNumberingQuestionsView(List questionList){
        Session session=HibernateUtil.getSession();
        List quesList = new LinkedList();
        List dummyList = new LinkedList();
        for(int i=0;i<questionList.size();i++){
            QuestionBankVO quesBankId=(QuestionBankVO) questionList.get(i);
            QuestionVO quesId=getQuestions(quesBankId.getHcmoQuestionId().getHcmoQuestionId());
            if(quesId.getQuestionType().equals("numbering")){
                quesList.add(quesId.getHcmoQuestionId());
            }
        }
        try{
            if(!quesList.isEmpty()){
                Criteria crit=session.createCriteria(QuestionVO.class);
                crit.add(Restrictions.eq("questionType","numbering"));
                crit.add(Restrictions.in("hcmoQuestionId",quesList));
                crit.add(Restrictions.eq("isActive",1));
                return crit.list();
            }else{
                return dummyList;
            }
        }finally{
            session.flush();
            session.close();          
        }
    }
    
    @Override
    public List getAllSummaryQuestionsView(List questionList){
        Session session=HibernateUtil.getSession();
        List quesList = new LinkedList();
        List dummyList = new LinkedList();
        for(int i=0;i<questionList.size();i++){
            QuestionBankVO quesBankId=(QuestionBankVO) questionList.get(i);
            QuestionVO quesId=getQuestions(quesBankId.getHcmoQuestionId().getHcmoQuestionId());
            if(quesId.getQuestionType().equals("summary")){
                quesList.add(quesId.getHcmoQuestionId());
            }
        }
        try{
            if(!quesList.isEmpty()){
                Criteria crit=session.createCriteria(QuestionVO.class);
                crit.add(Restrictions.eq("questionType","summary"));
                crit.add(Restrictions.in("hcmoQuestionId",quesList));
                crit.add(Restrictions.eq("isActive",1));
                return crit.list();
            }else{
                return dummyList;
            }
        }finally{
            session.flush();
            session.close();          
        }
    }
    
}
