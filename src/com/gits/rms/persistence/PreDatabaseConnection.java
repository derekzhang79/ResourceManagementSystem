
package com.gits.rms.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientInformationVO;

public class PreDatabaseConnection {
	public static SessionFactory masterDatabseConnection(){
		SessionFactory sessionFactory = null;
			    try {
			       Class.forName("com.mysql.jdbc.Driver").newInstance();
			     }
			     catch (Exception ex){
			        ex.printStackTrace();
			     }

			     try {
			    	 ResourceBundle myResource = ResourceBundle.getBundle("ApplicationResources");
			         String sDBUserName = myResource.getString("DBMasterTableUserName").trim();
			         String sDBpassword = myResource.getString("DBMasterTablePasword").trim();
			         String sDBName = myResource.getString("DBName").trim();
			         System.out.println("DBNAME =========>"+sDBName);
			         System.out.println("sDBUserName =========>"+sDBUserName);
			         System.out.println("sDBpassword =========>"+sDBpassword);
			         sessionFactory = HibernateConfig.setup(sDBName, sDBUserName, sDBpassword);
	     }
			     catch (Exception e){
			        e.printStackTrace();
			     }
			     return sessionFactory;		
			}

   /* public static List masterDatabseConnection() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ClientInformationVO cliInfo = null;
        List<ClientInformationVO> clientInfoList = new ArrayList<ClientInformationVO>();

        ResourceBundle myResource = ResourceBundle.getBundle("ApplicationResources");
        String sDBUserName = myResource.getString("DBMasterTableUserName");
        String sDBpassword = myResource.getString("DBMasterTablePasword");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hanzolabs_hcm"
                , sDBUserName, sDBpassword);
            stmt = connection.createStatement();
            String query = "select * from client_information where IsActive='1' ";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                cliInfo = new ClientInformationVO();
                cliInfo.setClientId(rs.getInt("Client_id"));
                cliInfo.setClientName(rs.getString("Client_name"));
                cliInfo.setClientSubDomainName(rs.getString("Client_sub_domain_name"));
                cliInfo.setClientDbPath(rs.getString("Client_db_path"));
                cliInfo.setClientDbUsername(rs.getString("Client_db_user_name"));
                cliInfo.setClientDbPassword(rs.getString("Client_db_password"));
                cliInfo.setIsActive(rs.getInt("IsActive"));
                cliInfo.setCreated(rs.getDate("Created"));
                cliInfo.setLastLogin(rs.getTimestamp("Last_login"));
                cliInfo.setUserType(rs.getString("User_type"));
                clientInfoList.add(cliInfo);
            }
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientInfoList;

    }*/
   /* public static void main(String[] args) {
    	PreDatabaseConnection.addNewSubdomain();
    }*/
    /*public static int addNewSubdomain() {
        List<ClientInformationVO> clientNewList = masterDatabseConnection();
        List<ClientInformationVO> clientOldList = HibernateUtil.getClientInfoList();
        ClientInformationVO cliInfoNewObj;
        ClientInformationVO cliInfoOldObj;
        List newList = new ArrayList();
        for (Iterator<ClientInformationVO> it = clientNewList.iterator(); it.hasNext();) {
            cliInfoNewObj = it.next();
            boolean bFound = false;
            for (Iterator<ClientInformationVO> itera = clientOldList.iterator(); itera.hasNext();) {
                cliInfoOldObj = itera.next();
                if (cliInfoNewObj.getClientId()== cliInfoOldObj.getClientId()) {
                    bFound = true;
                }
            }
            if (bFound == false) {
                newList.add(cliInfoNewObj);
            }

        }
        if (newList.size() > 0) {
            HibernateUtil.appendNewSessionFactory(newList);
        }
        return newList.size();
    }*/

    public static void updateUserEntry(int sClientId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResourceBundle myResource = ResourceBundle.getBundle("ApplicationResources");
        String sDBUserName = myResource.getString("DBMasterTableUserName");
        String sDBpassword = myResource.getString("DBMasterTablePasword");

        try {
            Date datewithTime = DateUtils.getCurrentDateTime();
            datewithTime = DateUtils.getCurrentTime();

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcmone_master_db"
                , sDBUserName, sDBpassword);
            String query = "update client_information set Last_login='" + datewithTime
                + "' where Client_id='" + sClientId + "';";
            stmt = connection.prepareStatement(query);
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void restrictUserPrivileges() {
    	 Map session = ActionContext.getContext().getSession();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResourceBundle myResource = ResourceBundle.getBundle("ApplicationResources");
        String sDBUserName = myResource.getString("DBMasterTableUserName");
        String sDBpassword = myResource.getString("DBMasterTablePasword");

        String dbName = String.valueOf(session.get("MASTER_CLIENT_NAME"));
        String dbUser = String.valueOf(session.get("MASTER_CLIENT_DB_USERNAME"));
        String dbPassword = String.valueOf(session.get("MASTER_CLIENT_DB_PASSWORD"));


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcmone_master_db"
                , sDBUserName, sDBpassword);

            String revokeQuery = "REVOKE INSERT ON *.* FROM '"+dbUser+"'@'localhost'";
            stmt = connection.prepareStatement(revokeQuery);
            stmt.executeUpdate();

            String flushQuery = "FLUSH PRIVILEGES";
            stmt = connection.prepareStatement(flushQuery);
            stmt.executeUpdate();

//            String grantQuery = "grant select, update, delete on "+dbName+".* to "+ dbUser+"@'localhost' identified by '"+dbPassword+"'";
//            stmt = connection.prepareStatement(grantQuery);
//            stmt.executeUpdate();
//
//            String flushGrantQuery = "FLUSH PRIVILEGES";
//            stmt = connection.prepareStatement(flushGrantQuery);
//            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
