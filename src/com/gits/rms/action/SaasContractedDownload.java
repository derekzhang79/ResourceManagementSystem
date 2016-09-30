package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.SaasContractDaoService;
import com.gits.rms.service.SaasContractService;
import com.gits.rms.vo.SaasContractVO;

public class SaasContractedDownload extends ActionSupport{
    private SaasContractService saasService=new SaasContractDaoService();
    private SaasContractVO saasContract;
    private InputStream inStream;
    private String saasFileName;
    private Long fileSize;
    private String fileType;
    private String fileName;
    private Long bufferSize;
    private String contentType;


    @Override
	public String execute () {
        Map session = ActionContext.getContext().getSession();
        if(session.get("USER_NAME")==null){
            try {
                session.put("APP_ERROR", getText("error.session.expired"));
                return "notLoggedIn";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	    try{
	        fileName=saasFileName;
	        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")+ServletActionContext.getServletContext().getContextPath()+"/"+
	                getText("SaasContract")+"/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/"+saasFileName));
	       
	        bufferSize = fileSize;
	        contentType=fileType;
	    }catch(IllegalArgumentException ill){
	    	ill.printStackTrace();
	        addActionError(getText("label.common.filenotfound"));
	    }catch (FileNotFoundException file){
	    	file.printStackTrace();
	        addActionError(getText("label.common.filenotfound"));
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    return SUCCESS;
    }


    public SaasContractVO getSaasContract() {
        return saasContract;
    }


    public void setSaasContract(SaasContractVO saasContract) {
        this.saasContract = saasContract;
    }


    public InputStream getInStream() {
        return inStream;
    }


    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }


    public String getSaasFileName() {
        return saasFileName;
    }


    public void setSaasFileName(String saasFileName) {
        this.saasFileName = saasFileName;
    }


    public Long getFileSize() {
        return fileSize;
    }


    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }


    public String getFileType() {
        return fileType;
    }


    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getFileName() {
        return fileName;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getContentType() {
        return contentType;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
