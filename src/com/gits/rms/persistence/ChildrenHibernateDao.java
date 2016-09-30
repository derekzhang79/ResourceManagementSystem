
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.ChildrenVO;
import com.gits.rms.vo.EducationVO;

public class ChildrenHibernateDao implements ChildrenDao {

    @Override
    public List childrenSearchResult(ChildrenVO child) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ChildrenVO.class);
            if (child.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", child.getEmpIdObj().getEmployeeId()));
            }
            if (!(child.getChildName().isEmpty())) {
                crit.add(Restrictions.like("childName", child.getChildName(), MatchMode.ANYWHERE));
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
    public void deleteChildren(ChildrenVO child) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ChildrenVO set updatedBy=:UpdatedBy, isActive=:IsActive where HcmoEmpChildrenId=:HcmoEmpChildrenId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", child.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoEmpChildrenId", child.getEmpChildrenId());
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
    public List getAllChildren() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ChildrenVO.class);
            crit.addOrder(Order.asc("childName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public List getAllSubEmployeeChildrenList(List<Integer> empReportToEmpId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ChildrenVO.class);
            crit.add(Restrictions.in("empIdObj.employeeId", empReportToEmpId));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
        	session.flush();
        	session.close();
        }
    }

    @Override
    public ChildrenVO getChildren(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ChildrenVO as chi left join fetch chi.empIdObj where chi.empChildrenId =:empChildrenId");
            q.setInteger("empChildrenId", id);
            return (ChildrenVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public ChildrenVO getEmpChildren(ChildrenVO child) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ChildrenVO as chi left join fetch chi.empIdObj where chi.empChildrenId =:empChildrenId and chi.empIdObj.employeeId=:HcmoEmployeeId and chi.isActive=:IsActive");
            q.setInteger("empChildrenId", child.getEmpChildrenId());
            q.setInteger("HcmoEmployeeId", child.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (ChildrenVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllChildren(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ChildrenVO.class);
            crit.add(Restrictions.eq("empIdObj.employeeId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertChildren(ChildrenVO child) {
        Boolean isUnique = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(child);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            isUnique = true;
            e.printStackTrace();
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
    public void updateChildren(ChildrenVO child) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ChildrenVO set ECName=:ECName,"
                + "ECDateOfBirth=:ECDateOfBirth,UpdatedBy=:UpdatedBy where HcmoEmpChildrenId=:HcmoEmpChildrenId";
            Query query = session.createQuery(sHql);
            query.setString("ECName", child.getChildName());
            query.setDate("ECDateOfBirth", child.getChildDOB());
            query.setInteger("UpdatedBy", child.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmpChildrenId", child.getEmpChildrenId());
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
