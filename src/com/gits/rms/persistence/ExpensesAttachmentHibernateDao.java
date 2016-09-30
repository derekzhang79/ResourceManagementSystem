
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.ExpensesAttachmentVO;

public class ExpensesAttachmentHibernateDao implements ExpensesAttachmentDao {

    private List<ExpensesAttachmentVO> expAttachList;
    List<EmployeeExpensesVO> expenseApproveList;
    private List<ExpensesAttachmentVO> expAttachList1 = new ArrayList<ExpensesAttachmentVO>();
    private EmployeeExpensesVO empExpense;

    @Override
    public List getAllExpensesAttachment(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAttachmentVO as eavo where eavo.isActive=:IsActive and eavo.hcmoExpensesId=:HcmoExpensesId order by eavo.expensesAttachFileName");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoExpensesId", id);
            expAttachList = query.list();
            return expAttachList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllAttachmentId(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAttachmentVO as eavo where eavo.isActive=:IsActive and eavo.hcmoExpensesId=:HcmoExpensesId");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoExpensesId", id);
            expAttachList = query.list();
            return expAttachList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesAttachmentVO getAllExpensesAttachmentFile(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAttachmentVO as eavo where eavo.isActive=:IsActive and eavo.hcmoExpensesAttachId=:HcmoExpensesAttachId order by eavo.expensesAttachFileName");
            query.setInteger("IsActive", 1);
            query.setInteger("HcmoExpensesAttachId", id);

            return (ExpensesAttachmentVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getExpAttachmentsStartingWith(String fileName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesAttachmentVO where IsActive=:IsActive and Name like ':fileName%'");
            query.setInteger("IsActive", 1);
            query.setString("fileName", fileName);
            expAttachList = query.list();
            return expAttachList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesAttachmentVO getExpensesAttachment(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesAttachmentVO as d where d.hcmoExpensesAttachId =:HcmoExpensesAttachId and isActive=:IsActive");
            q.setInteger("HcmoExpensesAttachId", id);
            q.setInteger("IsActive", 1);
            return (ExpensesAttachmentVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpensesAttachment(ExpensesAttachmentVO expAttach) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expAttach);
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
    public void updateExpensesAttachment(ExpensesAttachmentVO expAttach) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesAttachmentVO set ExpensesAttachFileName=:ExpensesAttachFileName, "
                + "ExpensesAttachSize=:ExpensesAttachSize, "
                + "HcmoEmployeeId=:HcmoEmployeeId,"
                + "ExpensesAttachType=:ExpensesAttachType, "
                + "UpdatedBy=:UpdatedBy "
                + "where ExpensesAttachId=:ExpensesAttachId";
            Query query = session.createQuery(sHql);
            query.setString("ExpensesAttachFileName", expAttach.getExpensesAttachFileName());
            query.setInteger("ExpensesAttachSize", expAttach.getExpensesAttachSize());
            query.setInteger("HcmoEmployeeId", expAttach.getHcmoEmployeeId().getEmployeeId());
            query.setString("ExpensesAttachType", expAttach.getExpensesAttachType());
            query.setInteger("UpdatedBy", expAttach.getUpdatedBy().getEmployeeId());
            query.setInteger("ExpensesAttachId", expAttach.getHcmoExpensesAttachId());
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
    public void deleteExpensesAttachment(Integer id) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesAttachmentVO set isActive=:IsActive where hcmoExpensesAttachId=:HcmoExpensesAttachId";
            Query query = session.createQuery(sHql);
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoExpensesAttachId", id);
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
    public void insertExpensesAttach(ExpensesAttachmentVO expAttach) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expAttach);
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
    public List viewExpensesAttachment(List<EmployeeExpensesVO> expenseList) {
        Session session = HibernateUtil.getSession();
        for (Iterator<EmployeeExpensesVO> it = expenseList.iterator(); it.hasNext();) {
            empExpense = it.next();
            try {
                session.beginTransaction();
                Query query = session.createQuery("from ExpensesAttachmentVO as eavo where eavo.isActive=:IsActive and eavo.hcmoExpensesId=:HcmoExpensesId");
                query.setInteger("IsActive", 1);
                query.setInteger("HcmoExpensesId", empExpense.getHcmoExpensesId());
                expAttachList = query.list();
                expAttachList1.addAll(expAttachList);
            } finally {
            }
        }
        return expAttachList1;
    }

    @Override
    public List viewExpensesAttachmentForApproval(List<EmployeeExpensesVO> expenseApproveList) {
        Session session = HibernateUtil.getSession();
        for (Iterator<EmployeeExpensesVO> it = expenseApproveList.iterator(); it.hasNext();) {
            empExpense = it.next();

            try {
                session.beginTransaction();
                Query query = session.createQuery("from ExpensesAttachmentVO as eavo where eavo.isActive=:IsActive and eavo.hcmoExpensesId=:HcmoExpensesId order by eavo.expensesAttachFileName");
                query.setInteger("IsActive", 1);
                query.setInteger("HcmoExpensesId", empExpense.getHcmoExpensesId());
                expAttachList = query.list();
            } finally {
                session.flush();
                session.close();
            }
        }
        return expAttachList;
    }

}