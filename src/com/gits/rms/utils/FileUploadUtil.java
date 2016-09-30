
package com.gits.rms.utils;

import java.io.File;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.persistence.PreDatabaseConnection;

public class FileUploadUtil {
    public String deleteFile(String sDestFileName, String sAppRootPath, String sContextPath, String sFolderPath) {
        try {
            String sDestPath = sAppRootPath + sContextPath + sFolderPath + sDestFileName;
            File fDelFile = new File(sDestPath);
            fDelFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();

        }
        return "SUCCESS";
    }

    public String deleteFolderPath(String sAppRootPath, String sContextPath, String sFolderPath) {
        try {
            String sFolderFullPath = sAppRootPath + sContextPath + sFolderPath;
            File fDelFile = new File(sFolderFullPath);
            fDelFile.delete();
        } catch (Exception e) {

            e.printStackTrace();
            return e.getMessage();

        }
        return "SUCCESS";
    }

    public String uploadFile(String sSourcePath, String sDestFileName, String sAppRootPath, String sContextPath, String sFolderPath) {
    	Map session = ActionContext.getContext().getSession();
    	ResourceBundle myResource = ResourceBundle.getBundle("ApplicationResources");
        String userType = myResource.getString(String.valueOf(session.get("MASTER_CLIENT_USERTYPE")));
        String applicationAbsolutePath = myResource.getString("ApplicationAbsolutePath");

        String empSpace = myResource.getString("EmployeeSpace");
        String benifit = myResource.getString("BenifitAttachements");
        String menu = myResource.getString("MenusXmlPath");
        String expenses = myResource.getString("ExpensesAttachments");
        String timesheet = myResource.getString("TimeSheet_Attachments");
        String webContent = myResource.getString("WebContent");

        long folderSize = 0;
        long allocatedSpace = Long.valueOf(userType);


        String employeeSpacePath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + empSpace + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String benifitPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + benifit + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String menuPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + menu + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String expensesPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + expenses + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String timesheetPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + timesheet + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String clientLogoPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + webContent + "resources/images/clientlogo/MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        String employeePicPath = applicationAbsolutePath+ ServletActionContext.getServletContext().getContextPath() + webContent + "resources/images/employeepicture/MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID");

        File empSpaceFile=new File(employeeSpacePath);
        File benifitFile=new File(benifitPath);
        File menuFile=new File(menuPath);
        File expensesFile=new File(expensesPath);
        File timesheetFile=new File(timesheetPath);
        File clientLogoFile=new File(clientLogoPath);
        File employeePicFile=new File(employeePicPath);

        boolean empSpaceFileExists = empSpaceFile.exists();
        boolean benifitFileExists = benifitFile.exists();
        boolean menuFileExists = menuFile.exists();
        boolean expensesFileExists = expensesFile.exists();
        boolean timesheetFileExists = timesheetFile.exists();
        boolean clientLogoFileExists = clientLogoFile.exists();
        boolean employeePicFileExists = employeePicFile.exists();

        if (empSpaceFileExists) {
        	long empSpaceFileSizeByte = getFileSize(new File(employeeSpacePath));
        	folderSize = folderSize + empSpaceFileSizeByte;
        }
        if (benifitFileExists) {
        	long benefitFileSizeByte = getFileSize(new File(benifitPath));
        	folderSize = folderSize + benefitFileSizeByte;
        }
        if (menuFileExists) {
        	long menuFileSizeByte = getFileSize(new File(menuPath));
        	folderSize = folderSize + menuFileSizeByte;
        }
        if (expensesFileExists) {
        	long expenseFileSizeByte = getFileSize(new File(expensesPath));
        	folderSize = folderSize + expenseFileSizeByte;
        }
        if (timesheetFileExists) {
        	long timesheetFileSizeByte = getFileSize(new File(timesheetPath));
        	folderSize = folderSize + timesheetFileSizeByte;
        }
        if (clientLogoFileExists) {
        	long clientLogoFileSizeByte = getFileSize(new File(clientLogoPath));
        	folderSize = folderSize + clientLogoFileSizeByte;
        }
        if (employeePicFileExists) {
        	long empPicFileSizeByte = getFileSize(new File(employeePicPath));
        	folderSize = folderSize + empPicFileSizeByte;
        }

        String sDestPath = "";

        try {

        	if((folderSize > allocatedSpace) || (folderSize == allocatedSpace)) {
        		 sDestPath = sAppRootPath + sContextPath + sFolderPath + sDestFileName;
                 File fSourceFile = new File(sSourcePath);
                 File fDestFile = new File(sDestPath);
                 FileUtils.copyFile(fSourceFile, fDestFile);

                 PreDatabaseConnection.restrictUserPrivileges();

                 Integer clientId = (Integer) session.get("MASTER_CLIENT_ID");
                 String subDomainName = String.valueOf(session.get("MASTER_CLIENT_SUBDOMAIN_NAME"));
                 String dbName = String.valueOf(session.get("MASTER_CLIENT_NAME"));
                 String dbUser = String.valueOf(session.get("MASTER_CLIENT_DB_USERNAME"));
                 String dbPassword = String.valueOf(session.get("MASTER_CLIENT_DB_PASSWORD"));

                 HibernateUtil.clearSessionAndAppendNewSessionFactory(clientId,subDomainName,dbUser,dbPassword);

        	}else if(folderSize < allocatedSpace) {
        		  sDestPath = sAppRootPath + sContextPath + sFolderPath + sDestFileName;
                  File fSourceFile = new File(sSourcePath);
                  File fDestFile = new File(sDestPath);
                  FileUtils.copyFile(fSourceFile, fDestFile);
        	}

        }catch (Exception e) {
        	e.printStackTrace();
            return e.getMessage();
		}

        return sDestPath;
    }

    public long getFileSize(File folder) {
        long foldersize = 0;
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
            } else {
                foldersize += filelist[i].length();
            }
        }
        return foldersize;
    }
}
