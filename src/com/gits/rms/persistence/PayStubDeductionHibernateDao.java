
package com.gits.rms.persistence;

import java.util.Calendar;
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
import com.gits.rms.vo.PayStubDeductionsVO;

public class PayStubDeductionHibernateDao implements PayStubDeductionDao {

    @Override
    public List getAllEmployeePayStubsDeduction(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.eq("payStub.payStubId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployeePayStubsDeductionCronJob(Integer id, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.eq("payStub.payStubId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmpPayStubsDedWithOutSelectedDeduction(Integer payStubDeductionId, Integer payStubId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.not(Restrictions.eq("payStubDeductionId", payStubDeductionId)));
            crit.add(Restrictions.eq("payStub.payStubId", payStubId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public PayStubDeductionsVO getPayStubDeduction(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from PayStubDeductionsVO as pay where pay.payStubDeductionId=:PayStubDeductionId");
            q.setInteger("PayStubDeductionId", id);
            return (PayStubDeductionsVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertPayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(payStubDeduction);
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
    public void updatePayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update PayStubDeductionsVO set deduction=:DeductionId, "
                + "deductionAmount=:DeductionAmount, " + "effectiveDate=:EffectiveDate, "
                + "UpdatedBy=:UpdatedBy " + "where payStubDeductionId=:PayStubDeductionId";
            Query query = session.createQuery(sHql);
            query.setInteger("DeductionId", payStubDeduction.getDeduction().getDeductionId());
            query.setDouble("DeductionAmount", payStubDeduction.getDeductionAmount());
            query.setDate("EffectiveDate", payStubDeduction.getEffectiveDate());
            query.setInteger("UpdatedBy", payStubDeduction.getUpdatedBy().getEmployeeId());
            query.setInteger("PayStubDeductionId", payStubDeduction.getPayStubDeductionId());
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
    public void deletePayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "delete PayStubDeductionsVO where payStubDeductionId=:PayStubDeductionId";
            Query query = session.createQuery(sHql);
            query.setInteger("PayStubDeductionId", payStubDeduction.getPayStubDeductionId());
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
    public void updatePayStubNetSalary(int payStubID, double netSalary) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update PayStubVO set netSalary=:NetSalary, updatedBy=:UpdatedBy where payStubId=:PayStubId";
            Query query = session.createQuery(sHql);
            query.setDouble("NetSalary", netSalary);
            query.setInteger("UpdatedBy", oEmp.getEmployeeId());
            query.setInteger("PayStubId", payStubID);
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
    public void updatePayStubNetSalaryCronJob(int payStubID, double netSalary, String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update PayStubVO set netSalary=:NetSalary where payStubId=:PayStubId";
            Query query = session.createQuery(sHql);
            query.setDouble("NetSalary", netSalary);
            query.setInteger("PayStubId", payStubID);
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
    public boolean checkTodaysPayStubsDeduction(String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Calendar currentDate = Calendar.getInstance();
        Date todaysDate = currentDate.getTime();

        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.eq("effectiveDate", todaysDate));
            crit.add(Restrictions.eq("isActive", 1));

            if (crit.list().isEmpty()) {
                return false;
            } else {
                return true;
            }

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllTodaysPayStubsDeduction(String clientId) {
        Session session = HibernateUtil.getSessionForCronJob(clientId);
        Calendar currentDate = Calendar.getInstance();
        Date todaysDate = currentDate.getTime();

        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.le("effectiveDate", todaysDate));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

}
