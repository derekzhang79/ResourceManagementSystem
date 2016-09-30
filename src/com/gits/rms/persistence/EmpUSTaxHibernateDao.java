
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmpUSTaxVO;

public class EmpUSTaxHibernateDao implements EmpUSTaxDao {

    @Override
    public void deleteEmpUSTax(EmpUSTaxVO empUSTax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmpUSTaxVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoEmpUsTaxId=:HcmoEmpUsTaxId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", empUSTax.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoEmpUsTaxId", empUSTax.getHcmoEmpUsTaxId());
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
    public List getAllEmpUSTax() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpUSTaxVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmpUSTaxVO getEmpUSTax(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmpUSTaxVO as et left join fetch et.hcmoEmployeeId where et.hcmoEmpUsTaxId =:HcmoEmpUsTaxId");
            q.setInteger("HcmoEmpUsTaxId", id);
            return (EmpUSTaxVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertEmpUSTax(EmpUSTaxVO empUSTax) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(empUSTax);
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
    public void updateEmpUSTax(EmpUSTaxVO empUSTax) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            if ((empUSTax.getTaxFederalExceptions() != null)
                && (empUSTax.getTaxStateExceptions() == null)) {
                String sHql = "update EmpUSTaxVO set TaxFederalStatus=:TaxFederalStatus, "
                    + "HcmoEmployeeId=:HcmoEmployeeId, "
                    + "TaxFederalExceptions=:TaxFederalExceptions, " + "TaxState=:TaxState, "
                    + "TaxStateStatus=:TaxStateStatus, " + "TaxUnempState=:TaxUnempState, "
                    + "TaxWorkState=:TaxWorkState, " + "UpdatedBy=:UpdatedBy "
                    + "where HcmoEmpUsTaxId=:HcmoEmpUsTaxId";
                Query query = session.createQuery(sHql);
                query.setString("TaxFederalStatus", empUSTax.getTaxFederalStatus());
                query.setInteger("HcmoEmployeeId", empUSTax.getHcmoEmployeeId().getEmployeeId());
                query.setString("TaxFederalExceptions", empUSTax.getTaxFederalExceptions());
                query.setInteger("TaxState", empUSTax.getTaxState().getHcmoregionId());
                query.setString("TaxStateStatus", empUSTax.getTaxStateStatus());
                query.setInteger("TaxUnempState", empUSTax.getTaxUnempState().getHcmoregionId());
                query.setInteger("TaxWorkState", empUSTax.getTaxWorkState().getHcmoregionId());
                query.setInteger("UpdatedBy", empUSTax.getUpdatedBy().getEmployeeId());
                query.setInteger("HcmoEmpUsTaxId", empUSTax.getHcmoEmpUsTaxId());
                query.executeUpdate();
                tx.commit();
            } else if ((empUSTax.getTaxFederalExceptions() == null)
                && (empUSTax.getTaxStateExceptions() != null)) {
                String sHql = "update EmpUSTaxVO set TaxFederalStatus=:TaxFederalStatus, "
                    + "HcmoEmployeeId=:HcmoEmployeeId, "
                    + "TaxFederalExceptions=:TaxFederalExceptions, " + "TaxState=:TaxState, "
                    + "TaxStateStatus=:TaxStateStatus, "
                    + "TaxStateExceptions=:TaxStateExceptions, " + "TaxUnempState=:TaxUnempState, "
                    + "TaxWorkState=:TaxWorkState, " + "UpdatedBy=:UpdatedBy "
                    + "where HcmoEmpUsTaxId=:HcmoEmpUsTaxId";
                Query query = session.createQuery(sHql);
                query.setString("TaxFederalStatus", empUSTax.getTaxFederalStatus());
                query.setInteger("HcmoEmployeeId", empUSTax.getHcmoEmployeeId().getEmployeeId());
                query.setInteger("TaxState", empUSTax.getTaxState().getHcmoregionId());
                query.setString("TaxStateStatus", empUSTax.getTaxStateStatus());
                query.setString("TaxStateExceptions", empUSTax.getTaxStateExceptions());
                query.setInteger("TaxUnempState", empUSTax.getTaxUnempState().getHcmoregionId());
                query.setInteger("TaxWorkState", empUSTax.getTaxWorkState().getHcmoregionId());
                query.setInteger("UpdatedBy", empUSTax.getUpdatedBy().getEmployeeId());
                query.setInteger("HcmoEmpUsTaxId", empUSTax.getHcmoEmpUsTaxId());
                query.executeUpdate();
                tx.commit();
            } else {
                String sHql = "update EmpUSTaxVO set TaxFederalStatus=:TaxFederalStatus, "
                    + "HcmoEmployeeId=:HcmoEmployeeId, "
                    + "TaxFederalExceptions=:TaxFederalExceptions, " + "TaxState=:TaxState, "
                    + "TaxStateStatus=:TaxStateStatus, "
                    + "TaxStateExceptions=:TaxStateExceptions, " + "TaxUnempState=:TaxUnempState, "
                    + "TaxWorkState=:TaxWorkState, " + "UpdatedBy=:UpdatedBy "
                    + "where HcmoEmpUsTaxId=:HcmoEmpUsTaxId";
                Query query = session.createQuery(sHql);
                query.setString("TaxFederalStatus", empUSTax.getTaxFederalStatus());
                query.setInteger("HcmoEmployeeId", empUSTax.getHcmoEmployeeId().getEmployeeId());
                query.setString("TaxFederalExceptions", empUSTax.getTaxFederalExceptions());
                query.setInteger("TaxState", empUSTax.getTaxState().getHcmoregionId());
                query.setString("TaxStateStatus", empUSTax.getTaxStateStatus());
                query.setString("TaxStateExceptions", empUSTax.getTaxStateExceptions());
                query.setInteger("TaxUnempState", empUSTax.getTaxUnempState().getHcmoregionId());
                query.setInteger("TaxWorkState", empUSTax.getTaxWorkState().getHcmoregionId());
                query.setInteger("UpdatedBy", empUSTax.getUpdatedBy().getEmployeeId());
                query.setInteger("HcmoEmpUsTaxId", empUSTax.getHcmoEmpUsTaxId());
                query.executeUpdate();
                tx.commit();
            }
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
    public List usTaxSearchResult(EmpUSTaxVO empUSTax) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmpUSTaxVO.class);
            if (empUSTax.getHcmoEmployeeId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", empUSTax.getHcmoEmployeeId().getEmployeeId()));
            }
            if (!(empUSTax.getTaxFederalStatus().isEmpty())) {
                crit.add(Restrictions.like("taxFederalStatus", empUSTax.getTaxFederalStatus(), MatchMode.ANYWHERE));
            }
            if (empUSTax.getTaxFederalExceptions() != null) {
                crit.add(Restrictions.eq("taxFederalExceptions", empUSTax.getTaxFederalExceptions()));
            }

            if (empUSTax.getTaxState().getHcmoregionId() != null) {
                crit.add(Restrictions.eq("taxState.hcmoregionId", empUSTax.getTaxState().getHcmoregionId()));
            }
            if (!(empUSTax.getTaxStateStatus().isEmpty())) {
                crit.add(Restrictions.like("taxStateStatus", empUSTax.getTaxStateStatus(), MatchMode.ANYWHERE));
            }
            if (empUSTax.getTaxStateExceptions() != null) {
                crit.add(Restrictions.eq("taxStateExceptions", empUSTax.getTaxStateExceptions()));
            }

            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

}
