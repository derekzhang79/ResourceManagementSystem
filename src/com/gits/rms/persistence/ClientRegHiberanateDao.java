package com.gits.rms.persistence;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gits.rms.vo.ClientRegVO;

public class ClientRegHiberanateDao implements ClientRegistrationDao{

	@Override
	public void insertRegistration(ClientRegVO regVO) {
		System.out.println("inside insert registration");
		Session session = HibernateUtil.getSession();
		System.out.println("session "+session);
        Transaction tx = null;
        System.out.println("tx "+tx);
        try {
            tx = session.beginTransaction();
            System.out.println("tx "+tx);
            session.save(regVO);
            System.out.println("after saving "+tx);
            tx.commit();
            System.out.println("after commiting");
        } catch (RuntimeException e) {
            if (tx != null) {
            	System.out.println("error occurred.. rolling back");
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
        	System.out.println("finally");
            session.flush();
            session.close();
        }
		
	}

	@Override
	public ClientRegVO getRegDetails(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRegistration(ClientRegVO regVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update ClientRegVO set moduleId=:moduleId " + "where firstName=:firstName";
            Query query = session.createQuery(sHql);
            query.setInteger("moduleId", regVO.getModuleId());
            query.setString("firstName", regVO.getFirstName());
            query.executeUpdate();
            tx.commit();
            System.out.println("after updating");
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
	public void updateRegistrationStatus(ClientRegVO regVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
        	int count = 0;
            tx = session.beginTransaction();
            System.out.println("inside update");
            String sHql = "select count(firstName) from ClientRegVO where activationKey=:activationKey and firstName=:firstName";
            Query query = session.createQuery(sHql);
            query.setString("activationKey", regVO.getActivationKey());
            query.setString("firstName", regVO.getFirstName());
            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
                System.out.println("count" + count);
            }
            if(count == 1) {
              System.out.println("inside updating status");
              sHql = "update ClientRegVO set status=:status " + "where firstName=:firstName and activationKey=:activationKey";
              query = session.createQuery(sHql);
              query.setInteger("status", 1);
              query.setString("firstName", regVO.getFirstName());
              query.setString("activationKey", regVO.getActivationKey());
              query.executeUpdate();
              tx.commit();
              System.out.println("after updating status");
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

}
