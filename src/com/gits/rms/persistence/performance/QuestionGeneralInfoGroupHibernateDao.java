package com.gits.rms.persistence.performance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.QuestionGeneralInfoGroupVO;

public class QuestionGeneralInfoGroupHibernateDao implements QuestionGeneralInfoGroupDao{
    
    @Override
    public void insertQuestioinBankGeneralInfoGroup(QuestionGeneralInfoGroupVO group){
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(group);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
                session.flush();
                session.close();
            }
    }
    
    @Override
    public QuestionGeneralInfoGroupVO getQuestioinBankGeneralInfoGroup(Integer id){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGeneralInfoGroupVO as q where q.hcmoQuestionGeneralInfoGroupId =:HcmoQuestionGeneralInfoGroupId");
            q.setInteger("HcmoQuestionGeneralInfoGroupId", id);
            return (QuestionGeneralInfoGroupVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getQuesBGenInfoGroup(String name){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGeneralInfoGroupVO as q where q.name =:Name");
            q.setString("Name", name);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public QuestionGeneralInfoGroupVO getQuesBankGeneralInfoGroup(String name){
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from QuestionGeneralInfoGroupVO as q where q.name =:Name");
            q.setString("Name", name);
            return (QuestionGeneralInfoGroupVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

}
