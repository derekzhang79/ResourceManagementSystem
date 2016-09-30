
package com.gits.rms.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.SupportDaoService;
import com.gits.rms.service.SupportService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.SupportVO;

public class SupportAction extends ActionSupport {
    private static final long serialVersionUID = 6379666671808056217L;
    public Integer maxiFolderSize;
    private SupportVO supportObj;
    private SupportVO suppObject;
    private SupportService supportService = new SupportDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private FileUploadUtil fileupload = new FileUploadUtil();
    private String sSignature;
    private SignatureVO sigObj;
    private List<File> upload = new ArrayList<File>();
    private List<String> uploadFileName = new ArrayList<String>();
    private List<String> supportAttachFileName = new ArrayList<String>();
    private String supportModifiedDateMail = "";
    private List<SignatureVO> signatureList;
    java.util.Vector supportAttachmentsMail = new java.util.Vector();
    private String fromEmployee;
    private String empFirstName;
    private String empLastName;
    private String empJobTitle;
    private String client;
    
    // For Support Form
    @SkipValidation
    public String setUpSupport() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        fromEmployee=oEmp.getEmpWorkEmail();
        empFirstName=oEmp.getEmpFirstName();
        empLastName=oEmp.getEmpLastName();
        empJobTitle=oEmp.getJobTitleIdObj().getJobTitleName();
        client=(String) session.get("MASTER_CLIENT_NAME");
        return SUCCESS;
    }

    // Insert Support Data's
    public String supportUpload() {
        try {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            int i = 0;
            String fName = "";
            String splitfNameArray = "";

            for (File u : upload) {
                fileupload.uploadFile(u.getAbsolutePath(), uploadFileName.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("SupportMailAttachments")
                    + "MASTER_CLIENTID_"
                    + session.get("MASTER_CLIENT_ID")
                    + "/"
                    + oEmp.getEmpFirstName() + "/");
                i++;
            }
            for (String fileName : uploadFileName) {
                fName += fileName + ",";
            }
            if(!fName.isEmpty()){
                splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                supportObj.setSupportAttachFileName(splitfNameArray);
            }
            supportObj.setCreated(DateUtils.getCurrentDateTime());
            supportObj.setCreatedBy(oEmp);
            supportObj.setUpdatedBy(oEmp);
            supportObj.setIsActive(1);
            supportService.insertSupport(supportObj);

            // For Attachment Download
            for (String fileAttName : uploadFileName) {
                String supportAttachmentMail = getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("SupportMailAttachments") + "MASTER_CLIENTID_"
                    + session.get("MASTER_CLIENT_ID") + "/" + oEmp.getEmpFirstName() + "/"
                    + fileAttName;
                supportAttachmentsMail.add(supportAttachmentMail);
            }
            // Alert Mail Concepts
            suppObject = supportService.getSupport(supportObj.getSupportId());
            oEmp.getEmployeeId();
            suppObject.getSupportMailSubject();
            String supportEmailId = getText("label.header.support.mailId");
            String supportMailReceiverName = getText("label.header.support.mailIdMessage");
            supportMail(supportEmailId, supportMailReceiverName, getText("support.add.addTo"), oEmp.getEmpFirstName(), suppObject.getSupportMailSubject(), supportAttachmentsMail);
            addActionMessage(getText("label.header.support.insertMessage"));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return SUCCESS;
    }

    // To validate file uploads more than 10MB
    @Override
    public void validate() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        int fileSize = Integer.parseInt(getText("label.header.totalFileSize"));
        int maxiFolderSize = fileSize / 1048576;

        Collection<?> tmp = getActionErrors();
        Collection<String> errors = new ArrayList<String>();
        for (Object o : tmp) {
            if (o.toString().contains("the request was rejected because its size")) {
                if (maxiFolderSize >= 10) {
                    errors.add(getText("Uploaded file was too large.Your file size exceed:"
                        + maxiFolderSize + " " + "MB"));
                }
            }
        }
        setActionErrors(errors);
    }

    // For Reset Form
    @SkipValidation
    public String resetSupport() {
        return SUCCESS;
    }

    public void supportMail(String oFirstPerson, String DearEmp, String Message, String From, String sSubject, java.util.Vector supportAttachmentsMail) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date supportModdate = suppObject.getUpdated();
        supportModifiedDateMail = updatedFormat.format(supportModdate);
        fromEmployee=oEmp.getEmpWorkEmail();
        empFirstName=oEmp.getEmpFirstName();
        empLastName=oEmp.getEmpLastName();
        empJobTitle=oEmp.getJobTitleIdObj().getJobTitleName();
        client=(String) session.get("MASTER_CLIENT_NAME");
        
        HCMOneMailer mailer = new HCMOneMailer();
        String replacement = HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + getText("message.common.dearEmployee");
        replacement = StringUtils.replace(replacement, "<person>", DearEmp)
            + HtmlConstants.HTML_PARA_END_TAG;

        String sPerson = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sPerson), sMessage.lastIndexOf(sPerson)
            + sPerson.length(), From);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.supportDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
          .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.support.client") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + client  + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.employee.firstName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empFirstName  + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
       .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.employee.lastName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empLastName  + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
      .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.jobTitle.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empJobTitle  + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

      .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.support.priority") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + suppObject.getPriority() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

      .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.support.message") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + suppObject.getSupportMailMessage() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

      .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + supportModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

      .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }

        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendSupportEmail(oFirstPerson, sSubject, sMessage, sSignature, supportAttachmentsMail);
    }

    public Integer getMaxiFolderSize() {
        return maxiFolderSize;
    }

    public void setMaxiFolderSize(Integer maxiFolderSize) {
        this.maxiFolderSize = maxiFolderSize;
    }

    public void setSupportObj(SupportVO supportObj) {
        this.supportObj = supportObj;
    }

    public List<String> getSupportAttachFileName() {
        return supportAttachFileName;
    }

    public void setSupportAttachFileName(List<String> supportAttachFileName) {
        this.supportAttachFileName = supportAttachFileName;
    }

    public SupportVO getSupportObj() {
        return supportObj;
    }

    public void setSupportService(SupportService supportService) {
        this.supportService = supportService;
    }

    public SupportService getSupportService() {
        return supportService;
    }

    public void setFileupload(FileUploadUtil fileupload) {
        this.fileupload = fileupload;
    }

    public FileUploadUtil getFileupload() {
        return fileupload;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<File> getUpload() {
        return upload;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setSignatureService(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    public SignatureService getSignatureService() {
        return signatureService;
    }

    public String getsSignature() {
        return sSignature;
    }

    public void setsSignature(String sSignature) {
        this.sSignature = sSignature;
    }

    public void setSigObj(SignatureVO sigObj) {
        this.sigObj = sigObj;
    }

    public SignatureVO getSigObj() {
        return sigObj;
    }

    public void setSuppObject(SupportVO suppObject) {
        this.suppObject = suppObject;
    }

    public SupportVO getSuppObject() {
        return suppObject;
    }

    public java.util.Vector getSupportAttachmentsMail() {
        return supportAttachmentsMail;
    }

    public void setSupportAttachmentsMail(java.util.Vector supportAttachmentsMail) {
        this.supportAttachmentsMail = supportAttachmentsMail;
    }

    public String getFromEmployee() {
        return fromEmployee;
    }

    public void setFromEmployee(String fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpJobTitle() {
        return empJobTitle;
    }

    public void setEmpJobTitle(String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    
}
