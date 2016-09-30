
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.EmpUSTaxVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.RegionVO;

public class RegionHibernateDao implements RegionDao {

    private List<RegionVO> regList;

    @Override
    public List getAllRegion() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RegionVO.class);
            crit.addOrder(Order.asc("zipCode"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List regionSearchResult(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RegionVO.class);
            if (!(reg.getName().isEmpty())) {
                crit.add(Restrictions.like("name", reg.getName(), MatchMode.ANYWHERE));
            }
            if (reg.getCountry().getHcmocountryId() != null) {
                crit.add(Restrictions.eq("country.hcmocountryId", reg.getCountry().getHcmocountryId()));
            }
            if (!(reg.getZipCode().isEmpty())) {
                crit.add(Restrictions.like("zipCode", reg.getZipCode(), MatchMode.ANYWHERE));
            }
            if (!(reg.getCity().isEmpty())) {
                crit.add(Restrictions.like("city", reg.getCity(), MatchMode.ANYWHERE));
            }
            if (!(reg.getRegionCode().isEmpty())) {
                crit.add(Restrictions.like("regionCode", reg.getRegionCode(), MatchMode.ANYWHERE));
            }
            if (!(reg.getAreaCode().isEmpty())) {
                crit.add(Restrictions.like("areaCode", reg.getAreaCode(), MatchMode.ANYWHERE));
            }
            if (!(reg.getTimeZone().isEmpty())) {
                crit.add(Restrictions.like("timeZone", reg.getTimeZone(), MatchMode.ANYWHERE));
            }
            if (!(reg.getLatitude().isEmpty())) {
                crit.add(Restrictions.like("latitude", reg.getLatitude(), MatchMode.ANYWHERE));
            }
            if (!(reg.getLongitude().isEmpty())) {
                crit.add(Restrictions.like("longitude", reg.getLongitude(), MatchMode.ANYWHERE));
            }
            crit.add(Restrictions.like("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List loadRegion(Integer zipcode) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from RegionVO as r left join fetch r.country c where r.isActive=:IsActive and r.zipCode=:ZipCode");
            query.setInteger("IsActive", 1);
            query.setInteger("ZipCode", zipcode);
            regList = query.list();
            return regList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public RegionVO getRegion(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from RegionVO as r left join fetch r.country c where r.hcmoregionId =:HcmoRegionId");
            q.setInteger("HcmoRegionId", id);
            return (RegionVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertRegion(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(reg);
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
    public void updateRegion(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update RegionVO set HcmoCountryId=:HcmoCountryId, "
                + "ZipCode=:ZipCode, " + "City=:City, " + "Name=:Name, "
                + "RegionCode=:RegionCode, " + "CountyField=:CountyField, "
                + "AreaCode=:AreaCode, " + "TimeZone=:TimeZone, " + "Latitude=:Latitude, "
                + "Longitude=:Longitude, " + "UpdatedBy=:UpdatedBy "
                + "where hcmoregionId=:HcmoRegionId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoCountryId", reg.getCountry().getHcmocountryId());
            query.setString("ZipCode", reg.getZipCode());
            query.setString("City", reg.getCity());
            query.setString("Name", reg.getName());
            query.setString("RegionCode", reg.getRegionCode());
            query.setString("CountyField", reg.getCountyField());
            query.setString("AreaCode", reg.getAreaCode());
            query.setString("TimeZone", reg.getTimeZone());
            query.setString("Latitude", reg.getLatitude());
            query.setString("Longitude", reg.getLongitude());
//            query.setInteger("UpdatedBy", reg.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoRegionId", reg.getHcmoregionId());
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
    public void deleteRegion(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sHql = "update RegionVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoregionId=:HcmoRegionId";
            Query query = session.createQuery(sHql);
 //           query.setInteger("UpdatedBy", reg.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoRegionId", reg.getHcmoregionId());
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
    public List checkRegionInClient(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ClientVO.class);
            crit.add(Restrictions.eq("zipcode", reg.getZipCode()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkRegionInLocation(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LocationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("zipcode", reg.getZipCode()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkRegionInEmployees(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", reg.getHcmoregionId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkRegionInEmpUsTax(RegionVO reg) {
        Session session = HibernateUtil.getSession();
        try {
            Criterion taxState = Restrictions.eq("taxState.hcmoregionId", reg.getHcmoregionId());
            Criterion taxUnEmpState = Restrictions.eq("taxUnempState.hcmoregionId", reg.getHcmoregionId());
            Criterion taxWorkState = Restrictions.eq("taxWorkState.hcmoregionId", reg.getHcmoregionId());
            Criterion Id = Restrictions.or(taxState, taxUnEmpState);

            Criteria crit = session.createCriteria(EmpUSTaxVO.class).add(Restrictions.or(Id, taxWorkState)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
