/**
 * 
 */
package com.gits.rms.action;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.MongoConfig;
import com.gits.rms.service.DocumentDaoService;
import com.gits.rms.service.DocumentService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.DocumentVO;
import com.gits.rms.vo.EmployeesVO;


/**
 *
 *
 */
public class DocumentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1333373065915485541L;
    static Logger log = Logger.getLogger(DocumentAction.class.getName());// for store log details
    private DocumentVO document;
    private DocumentService documentService = new DocumentDaoService();
    File file = null;
    byte[] byteArr;
    String documentType = "";
    String fileName = "";
  
    //Here we are using mongo in unsecure mode.
    @SkipValidation
    public String uploadDocument() {
    	try {
    		
    		try {
    			file = new File(document.getDocumentName());
    	        byteArr = new byte[(int) file.length()];
    	        fileName = file.getName();
    	        documentType = fileName.substring(fileName.lastIndexOf(".")+1);
    
				Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                document.setCreated(DateUtils.getCurrentDateTime());
                document.setCreatedBy(oEmp);
                document.setUpdatedBy(oEmp);           
                document.setIsActive(1);
				document.setDocumentType(documentType);
                documentService.insertDocument(document);
				uploadFileToMongo(byteArr,document.getHcmoDocumentId(),document.getDocumentName());
				System.out.println(document.getHcmoDocumentId());
	            System.out.println("Document inserted successfully");
			
    		} catch (Exception e) {
    			log.error("Exception occurred during the document insertion" + e);
    		}

    	}
    	catch(Exception e) {
    		log.error("exception occurred during mongo connection" + e);
    	}
    	return SUCCESS;
    }
    
    private void uploadFileToMongo(byte[] data,Integer documentId,String fileName) {
    	try {
	    	DB db = MongoConfig.createConnection();
			GridFS hbFS = new GridFS(db, "hcmodocument");
			GridFSInputFile gridFile = null;
			gridFile = hbFS.createFile(data);
			gridFile.setFilename(fileName);
			gridFile.setId(documentId);
			gridFile.save();
    	}
    	catch(Exception e) {
    		log.error("Exception occurred during the upload file to mongo process" + e);
    	}
	}
}
