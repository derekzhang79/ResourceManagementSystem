
package com.gits.rms.action.utils;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.action.EmployeeProfileAction;

public class ClientConstants {

	static Logger logger = Logger.getLogger(ClientConstants.class);
	
    public static String getClientContant(String sName) {
        Map session = ActionContext.getContext().getSession();
        //String sClient_Id = String.valueOf(session.get("MASTER_CLIENT_ID"));
        String sClient_Id = String.valueOf(session.get("CLIENT_INFO"));
        logger.debug("sClient_Id : " + sClient_Id);
        logger.debug("with client ID sClient_Id : " +  sName + "_" + sClient_Id);
        return sName + "_" + sClient_Id;
    }

    public static Object getApplicationConstant(String sName) {
        ServletContext context = ServletActionContext.getServletContext();
        return context.getAttribute(getClientContant(sName));
    }
}
