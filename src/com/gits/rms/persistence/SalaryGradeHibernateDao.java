
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.SalaryGradeVO;

public class SalaryGradeHibernateDao implements SalaryGradeDao {

    @Override
    public List getAllSalaryGrade() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SalaryGradeVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List salaryGradeSearchResult(SalaryGradeVO salgra) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(SalaryGradeVO.class);
            if (!(salgra.getSalaryName().isEmpty())) {
                crit.add(Restrictions.like("salaryName", salgra.getSalaryName(), MatchMode.ANYWHERE));
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
    public SalaryGradeVO getSalaryGrade(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from SalaryGradeVO as s where s.hcmoSalaryGradeId =:HcmoSalaryGradeId");
            q.setInteger("HcmoSalaryGradeId", id);
            return (SalaryGradeVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertSalaryGrade(SalaryGradeVO salgra) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(salgra);
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
    public void updateSalaryGrade(SalaryGradeVO salgra) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SalaryGradeVO set SalGrdName=:SalGrdName, "
                + "UpdatedBy=:UpdatedBy " + "where hcmoSalaryGradeId=:HcmoSalaryGradeId";
            Query query = session.createQuery(sHql);
            query.setString("SalGrdName", salgra.getSalaryName());
            query.setInteger("UpdatedBy", salgra.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoSalaryGradeId", salgra.getHcmoSalaryGradeId());
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
    public void deleteSalaryGrade(SalaryGradeVO salgra) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update SalaryGradeVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoSalaryGradeId=:HcmoSalaryGradeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", salgra.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoSalaryGradeId", salgra.getHcmoSalaryGradeId());
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
    public List checkSalaryGradeInJobTitle(SalaryGradeVO salgra) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(JobTitleVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("salaryGradeIdObj.hcmoSalaryGradeId", salgra.getHcmoSalaryGradeId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
