
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.vo.ClientInformationVO;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static HashMap<String, SessionFactory> clientMap = null;
    private static Connection databaseConnection ;
	private static List<ClientInformationVO> clientInfoList = new ArrayList<ClientInformationVO>();
    
    /*public static void createSessionConnectionFactory() {
    	databaseConnection = PreDatabaseConnection.createDatabaseConnection();
    	
    }*/

    public static void createSessionFactory() {
    	sessionFactory = PreDatabaseConnection.masterDatabseConnection();
    	System.out.println("sessionfactory "+sessionFactory);
    }

    /*public static void appendNewSessionFactory(List<ClientInformationVO> list) {
        for (Iterator<ClientInformationVO> it = list.iterator(); it.hasNext();) {
            ClientInformationVO cliInfoObj = it.next();
            SessionFactory sessionFactory = HibernateConfig.setup(cliInfoObj.getClientSubDomainName(), cliInfoObj.getClientDbUsername(), cliInfoObj.getClientDbPassword());
            appendListAndMap(String.valueOf(cliInfoObj.getClientId()), sessionFactory, cliInfoObj);
        }
    }*/

    public static void clearSessionAndAppendNewSessionFactory(int clientId,String subDomainName, String clientDBUserName, String clientDBPassword) {
        clientMap.put(String.valueOf(clientId), null);
        SessionFactory sessionFactory = HibernateConfig.setup(subDomainName, clientDBUserName, clientDBPassword);
        clientMap.put(String.valueOf(clientId), sessionFactory);
    }

   /* public static Session getSession() {
        Map session = ActionContext.getContext().getSession();
        String sClintId = String.valueOf(session.get("MASTER_CLIENT_ID"));
        SessionFactory sessionFactory = clientMap.get(sClintId);
        return sessionFactory.openSession();
    }*/
    
    public static Session getSession() {
    	return sessionFactory.openSession();
    }

    public static Session getSessionForCronJob(String sClintId) {
        SessionFactory sessionFactory = clientMap.get(sClintId);
        return sessionFactory.openSession();
    }

   /* private static void appendListAndMap(String sClientId, SessionFactory sessionFactory, ClientInformationVO cliInfoObj) {
        clientMap.put(sClientId, sessionFactory);
        clientInfoList.add(cliInfoObj);
    }
*/
    private static HashMap getMap() {
        return clientMap;
    }

    private static void putInMap(String sClientId, SessionFactory sessionFactory) {
    	clientMap.put(sClientId, sessionFactory);
  }


    public static List<ClientInformationVO> getClientInfoList() {
        return clientInfoList;
    }
}
