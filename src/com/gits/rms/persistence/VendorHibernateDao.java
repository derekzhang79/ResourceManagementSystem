
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.VendorVO;

public class VendorHibernateDao implements VendorDao {

    private List<VendorVO> vendorList;

    @Override
    public List getAllVendor() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(VendorVO.class);
            crit.addOrder(Order.asc("vendorName"));
            crit.add(Restrictions.eq("isActive", 1));
            vendorList = crit.list();
            return vendorList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllVendorExceptInitialVendorName(String vendorName) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from VendorVO as vid where vid.vendorName!=:VendorName and vid.isActive=:IsActive");
            q.setString("VendorName", vendorName);
            q.setInteger("IsActive", 1);
            vendorList = q.list();
            return vendorList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List vendorSearchResult(VendorVO vendor) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(VendorVO.class);
            if (!(vendor.getTaxId().isEmpty())) {
                crit.add(Restrictions.like("taxId", vendor.getTaxId(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getVendorName().isEmpty())) {
                crit.add(Restrictions.like("vendorName", vendor.getVendorName(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getContactPerson1().isEmpty())) {
                crit.add(Restrictions.like("contactPerson1", vendor.getContactPerson1(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getEmailAddress1().isEmpty())) {
                crit.add(Restrictions.like("emailAddress1", vendor.getEmailAddress1(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getContactPerson2().isEmpty())) {
                crit.add(Restrictions.like("contactPerson2", vendor.getContactPerson2(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getEmailAddress2().isEmpty())) {
                crit.add(Restrictions.like("emailAddress2", vendor.getEmailAddress2(), MatchMode.ANYWHERE));
            }
            if (!(vendor.getCompanyName().isEmpty())) {
                crit.add(Restrictions.like("companyName", vendor.getCompanyName(), MatchMode.ANYWHERE));
            }
            if (vendor.getCountry().getHcmocountryId() != null) {
                crit.add(Restrictions.eq("country.hcmocountryId", vendor.getCountry().getHcmocountryId()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            vendorList = crit.list();
            return vendorList;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public VendorVO getVendor(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from VendorVO as vid where vid.vendorId=:VendorId");
            q.setInteger("VendorId", id);
            return (VendorVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertVendor(VendorVO vendor) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(vendor);
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
    public void updateVendor(VendorVO vendor) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update VendorVO set TaxId=:TaxId," + "VendorName=:VendorName,"
                + "VendorShName=:VendorShName," + "ContactPerson1=:ContactPerson1,"
                + "EmailAddress1=:EmailAddress1," + "ContactPerson2=:ContactPerson2,"
                + "EmailAddress2=:EmailAddress2," + "UserName=:UserName," + "Password=:Password,"
                + "CompanyName=:CompanyName," + "Address1=:Address1," + "Address2=:Address2,"
                + "State=:State," + "City=:City," + "ZipCode=:ZipCode," + "Phone=:Phone,"
                + "Extension=:Extension," + "Fax=:Fax," + "Website=:Website," + "Custom1=:Custom1,"
                + "Custom2=:Custom2," + "HcmoCountryId=:HcmoCountryId," + "UpdatedBy=:UpdatedBy "
                + "where vendorId=:HcmoVendorId";
            Query query = session.createQuery(sHql);
            query.setString("TaxId", vendor.getTaxId());
            query.setString("VendorName", vendor.getVendorName());
            query.setString("VendorShName", vendor.getVendorShName());
            query.setString("ContactPerson1", vendor.getContactPerson1());
            query.setString("EmailAddress1", vendor.getEmailAddress1());
            query.setString("ContactPerson2", vendor.getContactPerson2());
            query.setString("EmailAddress2", vendor.getEmailAddress2());
            query.setString("UserName", vendor.getUserName());
            query.setString("Password", vendor.getPassword());
            query.setString("CompanyName", vendor.getCompanyName());
            query.setString("Address1", vendor.getAddress1());
            query.setString("Address2", vendor.getAddress2());
            query.setString("State", vendor.getState());
            query.setString("City", vendor.getCity());
            query.setString("ZipCode", vendor.getZipCode());
            query.setString("Phone", vendor.getPhone());
            query.setString("Extension", vendor.getExtension());
            query.setString("Fax", vendor.getFax());
            query.setString("Website", vendor.getWebSite());
            query.setString("Custom1", vendor.getCustom1());
            query.setString("Custom2", vendor.getCustom2());
            query.setInteger("HcmoCountryId", vendor.getCountry().getHcmocountryId());
            query.setInteger("UpdatedBy", vendor.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoVendorId", vendor.getVendorId());
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
    public void deleteVendor(VendorVO vendor) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update VendorVO set updatedBy=:UpdatedBy, isActive=:IsActive where HcmoVendorId=:HcmoVendorId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", vendor.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoVendorId", vendor.getVendorId());
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
