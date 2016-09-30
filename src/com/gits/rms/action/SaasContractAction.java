package com.gits.rms.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.SaasContractDaoService;
import com.gits.rms.service.SaasContractService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.SaasContractVO;

public class SaasContractAction extends ActionSupport{
    private SaasContractService saasService=new SaasContractDaoService();   
    private SaasContractVO saasContract=new SaasContractVO();
    Map session = ActionContext.getContext().getSession();
    Boolean isSaasCreated = Boolean.FALSE;
    private String sSaasPath;
    private String sSubdomainName;
    private String sAppsPath;
    private String sWebContent;
    private String sSaasImagePath;
    private String sFileType;
    private int sFileSize;
    
    public String execute(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        String domain_name=getText("ProjectName");
        session.put("domain_name",domain_name.substring(1) );
        
        if(session.get("USER_NAME")==null){
            try {
                session.put("APP_ERROR", getText("error.session.expired"));
                return "notLoggedIn";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        try {
            sSaasPath=getText("ApplicationAbsolutePath")+ServletActionContext.getServletContext().getContextPath()+"/"+ 
            getText("SaasContract")+"/"+"MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/";
            sAppsPath=getText("ApplicationAbsolutePath")+ServletActionContext.getServletContext().getContextPath();
            sWebContent=getText("WebContent");
            sSaasImagePath=getText("SaasImagePath");
            sSaasImagePath=sWebContent+sSaasImagePath;
            sSubdomainName=request.getServerName().trim().toLowerCase();
            
            isSaasCreated = saveSaasContract(oEmp.getEmpWorkEmail(), sSaasPath, saasContract.getCompanyName(), saasContract.getPersonName(), saasContract.getDesignation(), 
                sSubdomainName, oEmp, sAppsPath, sSaasImagePath);
            session.put("SAAS_CONTRACTED",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isSaasCreated){
            return SUCCESS;
        }else{
            return ERROR;   
        }
    }
    private boolean saveSaasContract(String sAdminEmail, String sSaasRootPath, String sCompanyName, String sPersonName, String sDesignation, String sSubdomainName, EmployeesVO oEmp, String sAppsPath, String sSaasImagePath){
        String sSaasFileName = new String();
        String sFileName = "Saas_Contract_" + sSubdomainName + "_" + DateUtils.getCurrentDateTime() + ".pdf";
        Boolean isSaasCreated = Boolean.FALSE;
        sSaasFileName = sFileName;
        SaasPDF pdf = new SaasPDF();
        String sSaasFullPath = sSaasRootPath + sSaasFileName;
        sSaasFullPath = pdf.saveSaasPDF(sAdminEmail, sSaasRootPath, sFileName, sAppsPath, sCompanyName, sPersonName, sDesignation, sSaasImagePath);
        
        try {
            sFileType = "application/pdf";
            sFileSize=4921;
            saasContract.setSaasName(sFileName);
            saasContract.setSaasSize(sFileSize);
            saasContract.setSaasType(sFileType);
            saasContract.setSaasSignDate(DateUtils.getCurrentDateTime());
            saasContract.setCreated(DateUtils.getCurrentDateTime());
            saasContract.setCreatedBy(oEmp);
            saasContract.setUpdated(DateUtils.getCurrentTime());
            saasContract.setUpdatedBy(oEmp);
            saasContract.setIsActive((short)1);
            
            session.put("SAAS_FILE_NAME",sFileName);
            session.put("SAAS_SIZE",sFileSize);
            session.put("SAAS_TYPE",sFileType);
            
            saasService.insertSaasContract(saasContract);
            
            isSaasCreated=Boolean.TRUE;
        } catch (Exception e1) {
            e1.printStackTrace();
            isSaasCreated = Boolean.FALSE;
        }finally{
        }

        return isSaasCreated;
    }
    public SaasContractVO getSaasContract() {
        return saasContract;
    }
    public void setSaasContract(SaasContractVO saasContract) {
        this.saasContract = saasContract;
    }
    public Map getSession() {
        return session;
    }
    public void setSession(Map session) {
        this.session = session;
    }
    public String getsSaasPath() {
        return sSaasPath;
    }
    public void setsSaasPath(String sSaasPath) {
        this.sSaasPath = sSaasPath;
    }
    public String getsAppsPath() {
        return sAppsPath;
    }
    public void setsAppsPath(String sAppsPath) {
        this.sAppsPath = sAppsPath;
    }
    public String getsWebContent() {
        return sWebContent;
    }
    public void setsWebContent(String sWebContent) {
        this.sWebContent = sWebContent;
    }
    public String getsSaasImagePath() {
        return sSaasImagePath;
    }
    public void setsSaasImagePath(String sSaasImagePath) {
        this.sSaasImagePath = sSaasImagePath;
    }
    public String getsFileType() {
        return sFileType;
    }
    public void setsFileType(String sFileType) {
        this.sFileType = sFileType;
    }
    public int getsFileSize() {
        return sFileSize;
    }
    public void setsFileSize(int sFileSize) {
        this.sFileSize = sFileSize;
    }
    
    
}

