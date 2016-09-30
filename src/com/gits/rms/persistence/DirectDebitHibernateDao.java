
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.DirectDebitVO;
import com.gits.rms.vo.EducationVO;

public class DirectDebitHibernateDao implements DirectDebitDao {
    private List<DirectDebitVO> dirDebitList;

    @Override
    public void deleteDirectDebit(DirectDebitVO dirDebit) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DirectDebitVO set preAccount=:preAccount,updatedBy=:UpdatedBy,isActive=:IsActive where empDirectDebitId=:EmpDirectDebitId";
            Query query = session.createQuery(sHql);
            query.setBoolean("preAccount", dirDebit.isPreAccount());
            query.setInteger("UpdatedBy", dirDebit.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("EmpDirectDebitId", dirDebit.getEmpDirectDebitId());
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
    public List directDebitSearchResult(DirectDebitVO dirDebit) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DirectDebitVO.class);
            if (dirDebit.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", dirDebit.getEmpIdObj().getEmployeeId()));
            }
            if (dirDebit.getRoutingNum() != null) {
                crit.add(Restrictions.eq("routingNum", dirDebit.getRoutingNum()));
            }
            if (!(dirDebit.getAccount().isEmpty())) {
                crit.add(Restrictions.like("account", dirDebit.getAccount(), MatchMode.ANYWHERE));
            }
            if (dirDebit.getAmount() != null) {
                crit.add(Restrictions.eq("amount", dirDebit.getAmount()));
            }
            if (!(dirDebit.getAccountType().isEmpty())) {
                crit.add(Restrictions.like("accountType", dirDebit.getAccountType(), MatchMode.ANYWHERE));
            }
            if (!(dirDebit.getTransactionType().isEmpty())) {
                crit.add(Restrictions.like("transactionType", dirDebit.getTransactionType(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllDirectDebit() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DirectDebitVO.class);
            crit.addOrder(Order.asc("routingNum"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeDirectDebitList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DirectDebitVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public List getAllDirectDebitForAEmp(DirectDebitVO dirDebit) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from DirectDebitVO as dd left join fetch dd.empIdObj where dd.empDirectDebitId!=:empDirectDebitId and dd.isActive=:IsActive and dd.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("empDirectDebitId", dirDebit.getEmpDirectDebitId());
            query.setInteger("HcmoEmployeeId", dirDebit.getEmpIdObj().getEmployeeId());
            query.setInteger("IsActive", 1);
            dirDebitList = query.list();
            return dirDebitList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public DirectDebitVO getDirectDebit(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from DirectDebitVO as dd left join fetch dd.empIdObj where dd.empDirectDebitId =:empDirectDebitId");
            q.setInteger("empDirectDebitId", id);
            return (DirectDebitVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public DirectDebitVO getEmpDirectDebit(DirectDebitVO dirDebit) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from DirectDebitVO as dd left join fetch dd.empIdObj where dd.empDirectDebitId =:empDirectDebitId and dd.empIdObj.employeeId=:HcmoEmployeeId and dd.isActive=:IsActive");
            q.setInteger("empDirectDebitId", dirDebit.getEmpDirectDebitId());
            q.setInteger("HcmoEmployeeId", dirDebit.getEmpIdObj().getEmployeeId());
            return (DirectDebitVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllDirectDebit(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from DirectDebitVO as dd left join fetch dd.empIdObj where dd.isActive=:IsActive and dd.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            dirDebitList = query.list();
            return dirDebitList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public DirectDebitVO getEmployeeDirectDebit(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from DirectDebitVO as dd left join fetch dd.empIdObj where dd.empDirectDebitId =:empDirectDebitId");
            q.setInteger("empDirectDebitId", id);
            return (DirectDebitVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertDirectDebit(DirectDebitVO dirDebit) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(dirDebit);
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
    public void updateDirectDebit(DirectDebitVO dirDebit) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DirectDebitVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "DDRoutingNum=:DDRoutingNum, " + "DDAccount=:DDAccount, "
                + "DDAmount=:DDAmount, " + "DDAccountType=:DDAccountType, "
                + "DDTransactionType=:DDTransactionType, "
                + "DDPreferedAccount=:DDPreferedAccount, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoEmpDirectDebitId=:HcmoEmpDirectDebitId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", dirDebit.getEmpIdObj().getEmployeeId());
            query.setInteger("DDRoutingNum", dirDebit.getRoutingNum());
            query.setString("DDAccount", dirDebit.getAccount());
            query.setBigDecimal("DDAmount", dirDebit.getAmount());
            query.setString("DDAccountType", dirDebit.getAccountType());
            query.setString("DDTransactionType", dirDebit.getTransactionType());
            query.setBoolean("DDPreferedAccount", dirDebit.isPreAccount());
            query.setInteger("UpdatedBy", dirDebit.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpDirectDebitId", dirDebit.getEmpDirectDebitId());
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

}
