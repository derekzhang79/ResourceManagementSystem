
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.DeductionsVO;
import com.gits.rms.vo.PayStubDeductionsVO;

public class DeductionsHibernateDao implements DeductionsDao {

    @Override
    public List checkDeductionInPaystubDeduction(DeductionsVO deduction) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(PayStubDeductionsVO.class);
            crit.add(Restrictions.eq("deduction.deductionId", deduction.getDeductionId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List deductionSearchResult(DeductionsVO deduction) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DeductionsVO.class);
            if (!(deduction.getDeductionName().isEmpty())) {
                crit.add(Restrictions.like("deductionName", deduction.getDeductionName(), MatchMode.ANYWHERE));
            }
            if (deduction.getDeductionType() != null) {
                crit.add(Restrictions.eq("deductionType", deduction.getDeductionType()));
            }
            if ((deduction.getDeductionMode() != null)
                && !("".equalsIgnoreCase(deduction.getDeductionMode()))) {
                crit.add(Restrictions.eq("deductionMode", deduction.getDeductionMode()));
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
    public void deleteDeduction(DeductionsVO deduction) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DeductionsVO set updatedBy=:UpdatedBy,isActive=:IsActive where deductionId=:DeductionId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", deduction.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("DeductionId", deduction.getDeductionId());
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
    public List getAllDeductions() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DeductionsVO.class);
            crit.addOrder(Order.asc("deductionName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public DeductionsVO getDeduction(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from DeductionsVO as deduction where deduction.deductionId =:DeductionId");
            q.setInteger("DeductionId", id);
            return (DeductionsVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getDeductionList(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(DeductionsVO.class);
            crit.add(Restrictions.eq("deductionId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertDeduction(DeductionsVO deduction) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(deduction);
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
    public void updateDeduction(DeductionsVO deduction) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update DeductionsVO set DeductionName=:EDedName, "
                + "DeductionType=:DeductionType, " + "DeductionMode=:DeductionMode, "
                + "UpdatedBy=:UpdatedBy " + "where deductionId=:DeductionId";
            Query query = session.createQuery(sHql);
            query.setString("EDedName", deduction.getDeductionName());
            query.setString("DeductionType", deduction.getDeductionType());
            query.setString("DeductionMode", deduction.getDeductionMode());
            query.setInteger("UpdatedBy", deduction.getUpdatedBy().getEmployeeId());
            query.setInteger("DeductionId", deduction.getDeductionId());
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
