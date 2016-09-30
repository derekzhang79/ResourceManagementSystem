
package com.gits.rms.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.gits.rms.vo.UserVO;

public class UserHibernateDao implements UserDao {

    private List<UserVO> userList;

    @Override
    public List getAllUser() {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(UserVO.class);
            crit.addOrder(Order.asc("userName"));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeAllUser(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from UserVO as u left join fetch u.empIdObj where u.isActive=:IsActive and u.empIdObj.employeeId=:HcmoEmployeeId");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            userList = query.list();
            return userList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List userSearchResult(UserVO user) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(UserVO.class);
            if (user.getEmpIdObj().getEmployeeId() != null) {
                crit.add(Restrictions.eq("empIdObj.employeeId", user.getEmpIdObj().getEmployeeId()));
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
    public UserVO getUser(Integer id) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from UserVO as u left join fetch u.empIdObj where u.hcmouserId =:HcmoUserId");
            q.setInteger("HcmoUserId", id);
            return (UserVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public UserVO getEmpUser(UserVO user) {

        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from UserVO as u left join fetch u.empIdObj where u.hcmouserId =:HcmoUserId and u.empIdObj.employeeId=:HcmoEmployeeId and u.isActive=:IsActive");
            q.setInteger("HcmoUserId", user.getHcmouserId());
            q.setInteger("HcmoEmployeeId", user.getEmpIdObj().getEmployeeId());
            q.setInteger("IsActive", 1);
            return (UserVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertUser(UserVO user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(user);
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
    public void updateUser(UserVO user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update UserVO set HcmoEmployeeId=:HcmoEmployeeId, "
                + "UserName=:UserName," + "Password=:Password, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoUserId=:HcmoUserId";
            Query query = session.createQuery(sHql);
            query.setInteger("HcmoEmployeeId", user.getEmpIdObj().getEmployeeId());
            query.setString("UserName", user.getUserName());
            query.setString("Password", user.getPassword());
            query.setInteger("UpdatedBy", user.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoUserId", user.getHcmouserId());
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
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteUser(UserVO user) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update UserVO set updatedBy=:UpdatedBy,IsActive=:IsActive where hcmouserId=:HcmoUserId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", user.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoUserId", user.getHcmouserId());
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
            session.flush();
            session.close();
        }
    }

    public UserVO checkUserToLogin(UserVO user) {
    	System.out.println("inside checkusertologin");
    	System.out.println("HibernateUtil "+HibernateUtil.getSession());
        Session session = HibernateUtil.getSession();
        System.out.println("session "+session);
        try {
            session.beginTransaction();
            System.out.println("session.beginTransaction(); ");
            Query q = session.createQuery("from UserVO as u left join fetch u.empIdObj "
            		+ " left join fetch u.empIdObj.roleObj where u.userName =:UserName "
            		+ " and u.password =:Password and u.isActive =:IsUserActive and u.empIdObj.isActive =:IsEmpActive");
            System.out.println("after creating the query");
 /*           Query q = session.createQuery("from UserVO as u  where u.userName =:UserName "
            		+ " and u.password =:Password and u.isActive =:IsUserActive and u.empIdObj.isActive =:IsEmpActive");*/
            q.setString("UserName", user.getUserName());
            q.setString("Password", user.getPassword());
            q.setInteger("IsUserActive", 1);
            q.setInteger("IsEmpActive", 1);
            System.out.println("result "+q.uniqueResult());
            return (UserVO) q.uniqueResult();
        } catch (Exception e ){
        	e.printStackTrace();
        	
        }finally {
        
        	
            session.flush();
            session.close();
        }
		return user;
    }

    public UserVO checkUserForgotPassword(UserVO user) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from UserVO as u left join fetch u.empIdObj where u.userName =:UserName"
                + "");
            q.setString("UserName", user.getUserName());
            return (UserVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public int updateUserForgotPasswordOn(UserVO user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update UserVO set ForgotPassword=:ForgotPassword "
                + "where userName=:UserName";
            Query query = session.createQuery(sHql);
            query.setInteger("ForgotPassword", 1);
            query.setString("UserName", user.getUserName());
            int iResult = query.executeUpdate();
            tx.commit();
            return iResult;
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

    public void updateUserForgotPasswordOff(UserVO user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update UserVO set ForgotPassword=:ForgotPassword "
                + "where userName=:UserName";
            Query query = session.createQuery(sHql);
            query.setInteger("ForgotPassword", 0);
            query.setString("UserName", user.getUserName());
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

    public UserVO checkUserForgotPasswordOn(UserVO user) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from UserVO as u left join fetch u.empIdObj where u.userName =:UserName"
                + "");
            q.setString("UserName", user.getUserName());
            return (UserVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public int resetUserPassword(UserVO user) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update UserVO set Password=:Password, "
                + "ForgotPassword=:ForgotPassword " + "where hcmouserId=:HcmoUserId";
            Query query = session.createQuery(sHql);
            query.setString("Password", user.getPassword());
            query.setInteger("ForgotPassword", 0);
            query.setInteger("HcmoUserId", user.getHcmouserId());
            int iResult = query.executeUpdate();
            tx.commit();
            return iResult;
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
