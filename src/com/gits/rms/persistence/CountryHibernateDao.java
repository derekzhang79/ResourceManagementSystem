
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmpPassportVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.RegionVO;

public class CountryHibernateDao implements CountryDao {

    private List<CountryVO> couList;

    @Override
    public List checkCountryInClient(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ClientVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", cou.getHcmocountryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkCountryInEmployees(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", cou.getHcmocountryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkCountryInLocation(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LocationVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", cou.getHcmocountryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkCountryInPassport(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpPassportVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", cou.getHcmocountryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List checkCountryInRegion(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(RegionVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("country.hcmocountryId", cou.getHcmocountryId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List countrySearchResult(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CountryVO.class);
            if (!(cou.getCountryName().isEmpty())) {
                crit.add(Restrictions.like("countryName", cou.getCountryName(), MatchMode.ANYWHERE));
            }
            if (!(cou.getCountryCode().isEmpty())) {
                crit.add(Restrictions.like("countryCode", cou.getCountryCode(), MatchMode.ANYWHERE));
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
    public void deleteCountry(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CountryVO set updatedBy=:UpdatedBy,IsActive=:IsActive where HcmoCountryId=:HcmoCountryId";
            Query query = session.createQuery(sHql);
//            query.setInteger("UpdatedBy", cou.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoCountryId", cou.getHcmocountryId());
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
    public List getAllCountry() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(CountryVO.class);
            crit.addOrder(Order.asc("countryName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCountriesStartingWith(String sCountry) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from CountryVO where IsActive=:IsActive and Name like ':sCountry%'");
            query.setInteger("IsActive", 1);
            query.setString("sCountry", sCountry);
            couList = query.list();
            return couList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public CountryVO getCountry(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from CountryVO as d where d.hcmocountryId =:HcmocountryId");
            q.setInteger("HcmocountryId", id);
            return (CountryVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertCountry(CountryVO cou) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(cou);
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
    public void updateCountry(CountryVO cou) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update CountryVO set Name=:Name, " + "Description=:Description, "
                + "CountryCode=:CountryCode, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoCountryId=:HcmoCountryId";
            Query query = session.createQuery(sHql);
            query.setString("Name", cou.getCountryName());
            query.setString("Description", cou.getDescription());
            query.setString("CountryCode", cou.getCountryCode());
//            query.setInteger("UpdatedBy", cou.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoCountryId", cou.getHcmocountryId());
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
