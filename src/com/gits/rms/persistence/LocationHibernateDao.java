
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.LocationVO;

public class LocationHibernateDao implements LocationDao {

    @Override
    public List getAllLocation() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LocationVO.class);
            crit.addOrder(Order.asc("locationName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List locationSearchResult(LocationVO loc) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LocationVO.class);
            if (!(loc.getLocationName().isEmpty())) {
                crit.add(Restrictions.like("locationName", loc.getLocationName(), MatchMode.ANYWHERE));
            }
            if (loc.getCountry().getHcmocountryId() != null) {
                crit.add(Restrictions.eq("country.hcmocountryId", loc.getCountry().getHcmocountryId()));
            }
            if (!(loc.getZipcode().isEmpty())) {
                crit.add(Restrictions.like("zipcode", loc.getZipcode(), MatchMode.ANYWHERE));
            }
            if (!(loc.getCity().isEmpty())) {
                crit.add(Restrictions.like("city", loc.getCity(), MatchMode.ANYWHERE));
            }
            if (!(loc.getRegion().isEmpty())) {
                crit.add(Restrictions.like("region", loc.getRegion(), MatchMode.ANYWHERE));
            }
            if (!(loc.getAddress1().isEmpty())) {
                crit.add(Restrictions.like("address1", loc.getAddress1(), MatchMode.ANYWHERE));
            }
            if (!(loc.getPhone().isEmpty())) {
                crit.add(Restrictions.like("phone", loc.getPhone(), MatchMode.ANYWHERE));
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
    public LocationVO getLocation(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LocationVO loc left join fetch loc.country where loc.hcmolocationId=:HcmolocationId");
            q.setInteger("HcmolocationId", id);
            return (LocationVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertLocation(LocationVO loc) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(loc);
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
    public void updateLocation(LocationVO loc) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LocationVO set Region=:Region, "
                + "HcmoCountryId=:HcmoCountryId, " + "Name=:Name, " + "Address1=:Address1, "
                + "Address2=:Address2, " + "City=:City, " + "Phone=:Phone, " + "Fax=:Fax, "
                + "Comments=:Comments, " + "ZipCode=:ZipCode, " + "UpdatedBy=:UpdatedBy "
                + "where hcmolocationId=:HcmolocationId";
            Query query = session.createQuery(sHql);
            query.setString("Region", loc.getRegion());
            query.setInteger("HcmoCountryId", loc.getCountry().getHcmocountryId());
            query.setString("Name", loc.getLocationName());
            query.setString("Address1", loc.getAddress1());
            query.setString("Address2", loc.getAddress2());
            query.setString("City", loc.getCity());
            query.setString("Phone", loc.getPhone());
            query.setString("Fax", loc.getFax());
            query.setString("Comments", loc.getComments());
            query.setString("ZipCode", loc.getZipcode());
//            query.setInteger("UpdatedBy", loc.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmolocationId", loc.getHcmolocationId());
            query.executeUpdate();
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
    public void deleteLocation(LocationVO loc) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LocationVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmolocationId=:HcmolocationId";
            Query query = session.createQuery(sHql);
//            query.setInteger("UpdatedBy", loc.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmolocationId", loc.getHcmolocationId());
            query.executeUpdate();
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
    public List checkLocationInEmpLocationHistory(LocationVO loc) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpLocationHistoryVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("locationIdObj.hcmolocationId", loc.getHcmolocationId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
