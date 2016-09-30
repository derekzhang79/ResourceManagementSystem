
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.MailConfigurationVO;

public class MailConfigurationHibernateDao implements MailConfigurationDao {

    private List<MailConfigurationVO> mailConfigList;

    @Override
    public List getAllMailConfig() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from MailConfigurationVO mail where mail.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            mailConfigList = query.list();
            return mailConfigList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public MailConfigurationVO getMailConfig(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from MailConfigurationVO mail where mail.hcmoMailConfigurationId=:HcmoMailConfigurationId");
            q.setInteger("HcmoMailConfigurationId", id);
            return (MailConfigurationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertMailConfig(MailConfigurationVO mail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(mail);
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
    public void updateMailConfig(MailConfigurationVO mail) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MailConfigurationVO set smtpHost=:SmtpHost,"
                + "username=:Username,password=:Password,UpdatedBy=:UpdatedBy where hcmoMailConfigurationId=:HcmoMailConfigurationId";
            Query query = session.createQuery(sHql);
            query.setString("SmtpHost", mail.getSmtpHost());
            query.setString("Username", mail.getUsername());
            query.setString("Password", mail.getPassword());
            query.setInteger("UpdatedBy", mail.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoMailConfigurationId", mail.getHcmoMailConfigurationId());
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
            session.flush();
            session.close();
        }
    }
}
