
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ExpensesTypeVO;

public class ExpensesTypeHibernateDao implements ExpensesTypeDao {

    private List<ExpensesTypeVO> expTypeList;

    public ExpensesTypeHibernateDao() {

    }

    @Override
    public List getAllExpensesType() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesTypeVO.class);
            crit.addOrder(Order.asc("name"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List expensesTypeSearchResult(ExpensesTypeVO expType) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesTypeVO.class);
            if (!(expType.getName().isEmpty())) {
                crit.add(Restrictions.like("name", expType.getName(), MatchMode.ANYWHERE));
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
    public ExpensesTypeVO getExpensesType(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesTypeVO as d where d.hcmoExpensesTypeId =:hcmoExpensesTypeId");
            q.setInteger("hcmoExpensesTypeId", id);
            return (ExpensesTypeVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getExpensesTypesStartingWith(String expType) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesTypeVO where IsActive=:IsActive and Name like ':expType%'");
            query.setInteger("IsActive", 1);
            query.setString("expType", expType);
            expTypeList = query.list();
            return expTypeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesTypeVO getExpensesTypeId(String sExpTYpeName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesTypeVO where IsActive=:IsActive and Name=:expType");
            query.setInteger("IsActive", 1);
            query.setString("expType", sExpTYpeName);
            return (ExpensesTypeVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpensesType(ExpensesTypeVO expType) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(expType);
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
    public void updateExpensesType(ExpensesTypeVO expType) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesTypeVO set Name=:Name, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoExpensesTypeId=:hcmoExpensesTypeId";
            Query query = session.createQuery(sHql);
            query.setString("Name", expType.getName());
            query.setInteger("UpdatedBy", expType.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoExpensesTypeId", expType.getHcmoExpensesTypeId());
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
    public void deleteExpensesType(ExpensesTypeVO expType) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesTypeVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoExpensesTypeId=:hcmoExpensesTypeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", expType.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoExpensesTypeId", expType.getHcmoExpensesTypeId());
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