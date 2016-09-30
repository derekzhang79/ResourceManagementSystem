
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
import com.gits.rms.vo.TeamVO;

public class TeamHibernateDao implements TeamDao {

    @Override
    public List getAllTeam() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TeamVO.class);
            crit.addOrder(Order.asc("teamName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List teamSearchResult(TeamVO team) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(TeamVO.class);
            if (!(team.getTeamName().isEmpty())) {
                crit.add(Restrictions.like("teamName", team.getTeamName(), MatchMode.ANYWHERE));
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
    public TeamVO getTeam(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from TeamVO as team where team.hcmoTeamId =:hcmoTeamId");
            q.setInteger("hcmoTeamId", id);
            return (TeamVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertTeam(TeamVO team) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(team);
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
    public void updateTeam(TeamVO team) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TeamVO set teamName=:teamName, " + "updatedBy=:UpdatedBy "
                + "where hcmoTeamId=:hcmoTeamId";
            Query query = session.createQuery(sHql);
            query.setString("teamName", team.getTeamName());
            query.setInteger("UpdatedBy", team.getUpdatedBy().getEmployeeId());
            query.setInteger("hcmoTeamId", team.getHcmoTeamId());
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
    public void deleteTeam(TeamVO team) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update TeamVO set updatedBy=:UpdatedBy,isActive=:IsActive where hcmoTeamId=:hcmoTeamId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", team.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("hcmoTeamId", team.getHcmoTeamId());
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
    public List checkTeamInEmployees(TeamVO team) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("teamIdObj.hcmoTeamId", team.getHcmoTeamId()));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
