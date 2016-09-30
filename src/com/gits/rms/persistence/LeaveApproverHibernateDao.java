
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.LeaveApproverVO;

public class LeaveApproverHibernateDao implements LeaveApproverDao {

    private List<LeaveApproverVO> leaveApproverList;

    @Override
    public List getAllLeaveApprover() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveApproverVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List leaveAppSearchResult(LeaveApproverVO leave) {
        Session session = HibernateUtil.getSession();
        try {

            Criteria crit = session.createCriteria(LeaveApproverVO.class);
            if (leave.getHcmoEmployeeId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", leave.getHcmoEmployeeId().getEmployeeId()));
            }
            if (leave.getHcmoApprovingEmpId().getEmployeeId() != null) {
                crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", leave.getHcmoApprovingEmpId().getEmployeeId()));
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
    public Integer getLeaveApproverCount(LeaveApproverVO leaveApprover) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoApprovingEmpId) from LeaveApproverVO as ea where ea.hcmoEmployeeId =:HcmoEmployeeId and ea.hcmoApprovingEmpId =:ApprovingEmployeeId and ea.isActive =:IsActive");
            query.setInteger("HcmoEmployeeId", leaveApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("ApprovingEmployeeId", leaveApprover.getHcmoApprovingEmpId().getEmployeeId());
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
    public Integer checkLeaveApprover(Integer id,Integer clientid) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoApprovingEmpId) from LeaveApproverVO as ea where ea.hcmoApprovingEmpId=:hcmoApprovingEmpId and ea.isActive=:IsActive and ea.clientId=:clientId");
            query.setInteger("hcmoApprovingEmpId", id);
            query.setInteger("IsActive", 1);
            query.setInteger("clientId", clientid);
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
    public List getEmployeeAllLeaveApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from LeaveApproverVO as l left join fetch l.hcmoEmployeeId where l.isActive=:IsActive and l.hcmoEmployeeId.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            leaveApproverList = query.list();
            return leaveApproverList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LeaveApproverVO getLeaveApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveApproverVO as l left join fetch l.hcmoEmployeeId where l.hcmoLeaveApproverId=:HcmoLeaveApproverId");
            q.setInteger("HcmoLeaveApproverId", id);
            return (LeaveApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LeaveApproverVO getEmpLeaveApprover(LeaveApproverVO leaveApprover) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveApproverVO as l left join fetch l.hcmoEmployeeId where l.hcmoLeaveApproverId=:HcmoLeaveApproverId and l.hcmoEmployeeId.employeeId=:HcmoEmployeeId and l.isActive=:IsActive");
            q.setInteger("HcmoLeaveApproverId", leaveApprover.getHcmoLeaveApproverId());
            q.setInteger("HcmoEmployeeId", leaveApprover.getHcmoEmployeeId().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (LeaveApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public LeaveApproverVO getEmployeeLeaveApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveApproverVO as l left join fetch l.hcmoEmployeeId where l.hcmoLeaveApproverId=:HcmoLeaveApproverId");
            q.setInteger("HcmoLeaveApproverId", id);
            return (LeaveApproverVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertLeaveApprover(LeaveApproverVO leaveApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(leaveApprover);
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
    public void updateLeaveApprover(LeaveApproverVO leaveApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveApproverVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "HcmoApprovingEmpId=:HcmoApprovingEmpId, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoLeaveApproverId=:HcmoLeaveApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", leaveApprover.getHcmoEmployeeId().getEmployeeId());
            query.setInteger("HcmoApprovingEmpId", leaveApprover.getHcmoApprovingEmpId().getEmployeeId());
            query.setInteger("UpdatedBy", leaveApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoLeaveApproverId", leaveApprover.getHcmoLeaveApproverId());
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
    public void deleteLeaveApprover(LeaveApproverVO leaveApprover) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update LeaveApproverVO set updatedBy=:UpdatedBy,IsActive=:IsActive where HcmoLeaveApproverId=:HcmoLeaveApproverId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", leaveApprover.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoLeaveApproverId", leaveApprover.getHcmoLeaveApproverId());
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
    public List<LeaveApproverVO> getCurrentLeaveApprover(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from LeaveApproverVO as l left join fetch l.hcmoEmployeeId where l.hcmoApprovingEmpId=:HcmoApprovingEmpId and l.isActive=:IsActive");
            q.setInteger("HcmoApprovingEmpId", id);
            q.setInteger("IsActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public LeaveApproverVO getSelfApprover(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(LeaveApproverVO.class);
            crit.add(Restrictions.eq("hcmoEmployeeId.employeeId", id));
            crit.add(Restrictions.eq("hcmoApprovingEmpId.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return (LeaveApproverVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

}