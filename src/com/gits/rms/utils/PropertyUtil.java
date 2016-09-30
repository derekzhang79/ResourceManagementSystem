package com.gits.rms.utils;

import java.util.Map;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class PropertyUtil {
	

	
private static Properties properties;
	
	@PostConstruct
	public void init(){
		
		properties=new Properties();
		try{
			InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			properties.load(resourceStream);		
			System.out.println("Property loader intialization done");
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.err.println("exception occured in propertyutil ==>"+ex);
		}
	}

	public static String getPropoerty(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * Method to construct the menus xml folder path. 
	 * @return Menus xml folder path  for the current client id 
	 */
	
	public static String getClientId(){
		Map mSession = ActionContext.getContext().getSession();
		/*String sXmlPath = System.getProperty("catalina.base")+"/webapps/" +ServletActionContext.getServletContext().getContextPath()
				           +"/MenusXml/" + "MASTER_CLIENTID_"    + mSession.get("CLIENT_INFO") + "/";
*/
		String clientId = (String)mSession.get("CLIENT_INFO");
		return clientId.trim();		
	}
	
	public static String getxmlMenuPath(){
		  Map mSession = ActionContext.getContext().getSession();
		  String sXmlPath = System.getProperty("catalina.base")+"/webapps/" +ServletActionContext.getServletContext().getContextPath()
		               +"/MenusXml/" + "MASTER_CLIENTID_"    + mSession.get("CLIENT_INFO") + "/";

		  return sXmlPath.trim();
		  
		  
		 }
}
