
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.ProjectVO;

public class CustomerHiernateDao implements CustomerDao {

    @Override
    public Integer checkCustomerEmailExists(CustomerVO cust) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CustomerVO.class);
            crit.add(Restrictions.eq("email", cust.getEmail()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list().size();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkCustomerInProject(CustomerVO cust) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ProjectVO.class);
            crit.add(Restrictions.eq("customerId.customerId", cust.getCustomerId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List customerSearchResult(CustomerVO cust) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CustomerVO.class);
            if (!(cust.getCustomerName().isEmpty())) {
                crit.add(Restrictions.like("customerName", cust.getCustomerName(), MatchMode.ANYWHERE));
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
    public void deleteCustomer(CustomerVO cust) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CustomerVO set updatedBy=:UpdatedBy,isActive=:IsActive where customerId=:CustomerId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", cust.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("CustomerId", cust.getCustomerId());
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
    public List getAllCustomer() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CustomerVO.class);
            crit.addOrder(Order.asc("customerName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public CustomerVO getCustomer(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from CustomerVO as cust where cust.customerId =:customerId");
            q.setInteger("customerId", id);
            return (CustomerVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertCustomer(CustomerVO cust) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(cust);
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
    public void updateCustomer(CustomerVO cust) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CustomerVO set " + "Name=:Name, " + "Description=:Description,"
                + "PhoneNumber=:PhoneNumber," + "Fax=:Fax," + "Address1=:Address1,"
                + "Address2=:Address2," + "ContactName=:ContactName," + "Email=:Email,"
                + "UpdatedBy=:UpdatedBy " + "where customerId=:CustomerId";
            Query query = session.createQuery(sHql);
            query.setString("Name", cust.getCustomerName());
            query.setString("Description", cust.getDesc());
            query.setString("PhoneNumber", cust.getPhoneNumber());
            query.setString("Fax", cust.getFax());
            query.setString("Address1", cust.getAddr1());
            query.setString("Address2", cust.getAddr2());
            query.setString("ContactName", cust.getContactName());
            query.setString("Email", cust.getEmail());
            query.setInteger("UpdatedBy", cust.getUpdatedBy().getEmployeeId());
            query.setInteger("CustomerId", cust.getCustomerId());
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
