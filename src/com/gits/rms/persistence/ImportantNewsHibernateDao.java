
package com.gits.rms.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.ImportantNewsVO;

public class ImportantNewsHibernateDao implements ImportantNewsDao {

    @Override
    public List getAllImportantNews() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ImportantNewsVO.class);
            crit.addOrder(Order.asc("subject"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ImportantNewsVO getImportantNews(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ImportantNewsVO as dept where dept.importantNewsId =:importantNewsId");
            q.setInteger("importantNewsId", id);
            return (ImportantNewsVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertImportantNews(ImportantNewsVO imp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(imp);
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
    public void updateImportantNews(ImportantNewsVO imp) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ImportantNewsVO set subject=:Subject, " + "message=:Message, "
                + "showMessage=:ShowMessage, " + "empIdObj=:EmpIdObj, " + "updatedBy=:UpdatedBy "
                + "where importantNewsId=:ImportantNewsId";
            Query query = session.createQuery(sHql);
            query.setString("Subject", imp.getSubject());
            query.setString("Message", imp.getMessage());
            query.setBoolean("ShowMessage", imp.isShowMessage());
            query.setInteger("EmpIdObj", imp.getEmpIdObj().getEmployeeId());
            query.setInteger("UpdatedBy", imp.getUpdatedBy().getEmployeeId());
            query.setInteger("ImportantNewsId", imp.getImportantNewsId());
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
    public void deleteImportantNews(ImportantNewsVO imp) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ImportantNewsVO set updatedBy=:UpdatedBy,isActive=:IsActive where importantNewsId=:importantNewsId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", imp.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("importantNewsId", imp.getImportantNewsId());
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
    public List importantNewsSearchResult(ImportantNewsVO imp) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ImportantNewsVO.class);
            if (!(imp.getSubject().isEmpty())) {
                crit.add(Restrictions.like("subject", imp.getSubject(), MatchMode.ANYWHERE));
            }
            if (!(imp.getMessage().isEmpty())) {
                crit.add(Restrictions.like("message", imp.getMessage(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }
}
