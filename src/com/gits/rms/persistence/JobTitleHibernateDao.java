
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.JobTitleVO;

public class JobTitleHibernateDao implements JobTitleDao {

    @Override
    public List getAllJobTitle() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(JobTitleVO.class);
            crit.addOrder(Order.asc("jobTitleName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List jobTitleSearchResult(JobTitleVO jobTitle) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(JobTitleVO.class);
            if (!(jobTitle.getJobTitleName().isEmpty())) {
                crit.add(Restrictions.like("jobTitleName", jobTitle.getJobTitleName(), MatchMode.ANYWHERE));
            }
            if (jobTitle.getSalaryGradeIdObj().getHcmoSalaryGradeId() != null) {
                crit.add(Restrictions.eq("salaryGradeIdObj.hcmoSalaryGradeId", jobTitle.getSalaryGradeIdObj().getHcmoSalaryGradeId()));
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
    public JobTitleVO getJobTitle(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from JobTitleVO as job left join fetch job.salaryGradeIdObj where job.jobTitleId =:jobTitleId");
            q.setInteger("jobTitleId", id);
            return (JobTitleVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertJobTitle(JobTitleVO jobTitle) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(jobTitle);
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
    public void updateJobTitle(JobTitleVO jobTitle) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update JobTitleVO set JobTitleName=:JobTitleName, "
                + "HcmoSalaryGradeId=:HcmoSalaryGradeId, " + "JobTitleDesc=:JobTitleDesc, "
                + "JobTitleComm=:JobTitleComm, " + "HcmoSalaryGradeId=:HcmoSalaryGradeId, "
                + "UpdatedBy=:UpdatedBy " + "where HcmoJobTitleId=:HcmoJobTitleId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoSalaryGradeId", jobTitle.getSalaryGradeIdObj().getHcmoSalaryGradeId());
            query.setString("JobTitleName", jobTitle.getJobTitleName());
            query.setString("JobTitleDesc", jobTitle.getJobTitleDesc());
            query.setString("JobTitleComm", jobTitle.getJobTitleComments());
            query.setInteger("HcmoSalaryGradeId", jobTitle.getSalaryGradeIdObj().getHcmoSalaryGradeId());
            query.setInteger("UpdatedBy", jobTitle.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoJobTitleId", jobTitle.getJobTitleId());
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
    public void deleteJobTitle(JobTitleVO jobTitle) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update JobTitleVO set updatedBy=:UpdatedBy,IsActive=:IsActive where jobTitleId=:jobTitleId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", jobTitle.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("jobTitleId", jobTitle.getJobTitleId());
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
    public List checkJobTitleInEmployee(JobTitleVO jobTitle) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("jobTitleIdObj.jobTitleId", jobTitle.getJobTitleId()));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

}
