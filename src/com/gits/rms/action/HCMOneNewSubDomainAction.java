
package com.gits.rms.action;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.PreDatabaseConnection;

public class HCMOneNewSubDomainAction extends ActionSupport {

	public String execute() throws Exception {
		
		PreDatabaseConnection preConnection = new PreDatabaseConnection();
//		COMMENTED BY BALA
	//	int preConnectionCount = preConnection.addNewSubdomain();
		//System.out.println("PreDatabaseConnection Checking Process :"+preConnectionCount);
		
		return SUCCESS;

	}

   
}