
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ClientVO;

public class ClientHibernateDao implements ClientDao {

    @Override
    public void deleteClient(ClientVO client) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ClientVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoclientId=:HcmoClientId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", client.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoClientId", client.getHcmoclientId());
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
    public List getAllClient() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ClientVO.class);
            crit.addOrder(Order.asc("companyName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ClientVO getClient(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ClientVO as c left join fetch c.country cou where c.hcmoclientId =:hcmoclientId");
            q.setInteger("hcmoclientId", id);
            return (ClientVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertClient(ClientVO client) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(client);
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
    public void updateClient(ClientVO client) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ClientVO set HcmoCountryId=:HcmoCountryId, "
                + "CompanyName=:CompanyName, " + "NoOfEmployee=:NoOfEmployee, "
                + "Address1=:Address1, " + "Address2=:Address2, " + "ZipCode=:ZipCode, "
                + "Region=:Region, " + "City=:City, " + "Phone=:Phone, " + "TaxId=:TaxId," + "LogoName=:LogoName,"
                + "Fax=:Fax, " + "Comments=:Comments, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoClientId=:HcmoClientId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoCountryId", client.getCountry().getHcmocountryId());
            query.setString("CompanyName", client.getCompanyName());
            query.setInteger("NoOfEmployee", client.getNoOfEmp());
            query.setString("Address1", client.getAddress1());
            query.setString("Address2", client.getAddress2());
            query.setString("ZipCode", client.getZipcode());
            query.setString("Region", client.getState());
            query.setString("City", client.getCity());
            query.setString("Phone", client.getPhone());
            query.setString("TaxId", client.getTaxId());
            query.setString("LogoName", client.getLogoName());
            query.setString("Fax", client.getFax());
            query.setString("Comments", client.getComments());
            query.setInteger("UpdatedBy", client.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoClientId", client.getHcmoclientId());
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
