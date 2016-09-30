
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.TimeSheetAttachmentVO;

public class TimeSheetAttachmentHibernateDao implements TimeSheetAttachmentDao {

    
    private List<TimeSheetAttachmentVO> tsAttachList;
    List<EmployeeExpensesVO> expenseApproveList;
   
    private List<TimeSheetAttachmentVO> tsAttachList1 = new ArrayList<TimeSheetAttachmentVO>();

    @Override
  
    public void insertTimeSheetAttach(TimeSheetAttachmentVO tsAttach) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           
            session.save(tsAttach);
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
    public List getAllTimeSheetAttachment(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetAttachmentVO as tsavo where tsavo.isActive=:IsActive and tsavo.hcmoTsAttachmentId=:HcmoTsAttachmentId order by tsavo.fileName");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoTsAttachmentId", id);
            tsAttachList = query.list();
            return tsAttachList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmpTimeSheetAttachment(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetAttachmentVO as tsavo where tsavo.isActive=:IsActive and tsavo.hcmoEmployeeId=:hcmoEmployeeId order by tsavo.fileName");
            query.setInteger("IsActive", 1);
            query.setInteger("hcmoEmployeeId", id);
            tsAttachList = query.list();
            return tsAttachList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getAllEmpTimeSheetAttachmentDetails() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetAttachmentVO as tsavo where tsavo.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            tsAttachList = query.list();
            return tsAttachList; 
       } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public TimeSheetAttachmentVO getTimeSheetAttachment(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TimeSheetAttachmentVO as tsavo where tsavo.hcmoTsAttachmentId =:HcmoTsAttachmentId and tsavo.isActive=:IsActive");
            q.setInteger("HcmoTsAttachmentId", id);
            q.setInteger("IsActive", 1);
            return (TimeSheetAttachmentVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteTimeSheetAttachments(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TimeSheetAttachmentVO set isActive=:IsActive where hcmoTsAttachmentId=:HcmoTsAttachmentId";
            Query query = session.createQuery(sHql);
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoTsAttachmentId", id);
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
    public List getTimeSheetAttachTimeBase(Integer id, Date startDate, Date enddate) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from TimeSheetAttachmentVO as tsavo where tsavo.isActive=:IsActive and tsavo.hcmoEmployeeId=:hcmoEmployeeId and (tsavo.created<=:enddate and tsavo.created>=:startDate)  order by tsavo.fileName");
            query.setInteger("IsActive", 1);
            query.setInteger("hcmoEmployeeId", id);
            query.setDate("startDate", startDate);
            query.setDate("enddate", enddate);
            tsAttachList = query.list();
            return tsAttachList; 
       } finally {
            session.flush();
            session.close();
        }
    }
}