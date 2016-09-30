
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ExpensesApproverVO;

public class ExpensesApproverHibernateDao implements ExpensesApproverDao {

    private List<ExpensesApproverVO> expApproverList;
    private ExpensesApproverVO expApprover;

    private ExpensesApproverVO approverCheck = new ExpensesApproverVO();

    @Override
    public List getAllExpensesApprover() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesApproverVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List expAppSearchResult(ExpensesApproverVO exp) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesApproverVO.class);
            if (exp.getHcmoEmployeeId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", exp.getHcmoEmployeeId().getEmployeeId()));
            }
            if (exp.getApprovingEmployeeId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("approvingEmployeeId.employeeId", exp.getApprovingEmployeeId().getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));

            List list = crit.list();
            return list;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllExpApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesApproverVO as e left join fetch e.hcmoEmployeeId  where e.isActive=:IsActive and e.hcmoEmployeeId.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            expApproverList = query.list();
            return expApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployeesApprover(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesApproverVO as ea where ea.hcmoEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive ");
            query.setInteger("ApprovingEmployeeId", expApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                it.next();
            }
            expApproverList = query.list();
            return expApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesApproverVO getExpensesApprover(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesApproverVO as e left join fetch e.hcmoEmployeeId where e.approverId =:ApproverId");
            q.setInteger("ApproverId", id);
            return (ExpensesApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesApproverVO getEmpExpenseApprover(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesApproverVO as e left join fetch e.hcmoEmployeeId where e.approverId =:ApproverId and e.hcmoEmployeeId.employeeId=:HcmoEmployeeId and e.isActive=:IsActive");
            q.setInteger("ApproverId", expApprover.getApproverId());
            q.setInteger("HcmoEmployeeId", expApprover.getHcmoEmployeeId().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (ExpensesApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public ExpensesApproverVO getEmployeeExpenseApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ExpensesApproverVO as e left join fetch e.hcmoEmployeeId where e.approverId =:ApproverId");
            q.setInteger("ApproverId", id);
            return (ExpensesApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertExpensesApprover(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(expApprover);
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
    public void updateExpensesApprover(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesApproverVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "ApprovingEmployeeId=:ApprovingEmployeeId, " + "UpdatedBy=:UpdatedBy "
                + "where ApproverId=:ApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", expApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("ApprovingEmployeeId", expApprover.getApprovingEmployeeId().getEmployeeId());
            query.setInteger("UpdatedBy", expApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("ApproverId", expApprover.getApproverId());
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
    public void deleteExpensesApprover(ExpensesApproverVO expApprover) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ExpensesApproverVO set updatedBy=:UpdatedBy,IsActive=:IsActive where ApproverId=:ApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", expApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("ApproverId", expApprover.getApproverId());
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
    public Integer checkExpensesApprover(Integer id,Integer clientId) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(approvingEmployeeId) from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive and ea.clientId=:clientid");
            query.setInteger("ApprovingEmployeeId", id);
            query.setInteger("IsActive", 1);
            query.setInteger("clientid",clientId);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public Integer getExpApproverCount(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(approvingEmployeeId) from ExpensesApproverVO as ea where ea.hcmoEmployeeId =:HcmoEmployeeId and ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive =:IsActive");
            query.setInteger("HcmoEmployeeId", expApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("ApprovingEmployeeId", expApprover.getApprovingEmployeeId().getEmployeeId());
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public List getAllExpEmpForApproverSearch(ExpensesApproverVO expApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive ");
            query.setInteger("ApprovingEmployeeId", expApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                it.next();
            }
            expApproverList = query.list();
            return expApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ExpensesApproverVO getSelfApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ExpensesApproverVO.class);

            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", id));
            crit.add(Restrictions.eq("approvingEmployeeId.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return (ExpensesApproverVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<ExpensesApproverVO> getAllSubEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ExpensesApproverVO as ea where ea.approvingEmployeeId =:ApprovingEmployeeId and ea.isActive=:IsActive ");
            query.setInteger("ApprovingEmployeeId", id);
            query.setInteger("IsActive", 1);
            expApproverList = query.list();

            return expApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<ExpensesApproverVO> getExpApproverList() {
        return expApproverList;
    }

    public void setExpApproverList(List<ExpensesApproverVO> expApproverList) {
        this.expApproverList = expApproverList;
    }

    public ExpensesApproverVO getExpApprover() {
        return expApprover;
    }

    public void setExpApprover(ExpensesApproverVO expApprover) {
        this.expApprover = expApprover;
    }

    public ExpensesApproverVO getApproverCheck() {
        return approverCheck;
    }

    public void setApproverCheck(ExpensesApproverVO approverCheck) {
        this.approverCheck = approverCheck;
    }

}
