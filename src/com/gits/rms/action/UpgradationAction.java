
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.SupportVO;

public class UpgradationAction extends ActionSupport {
    private static final long serialVersionUID = 7920659444208593441L;
    private String fromEmployee;
    private String empFirstName;
    private String empLastName;
    private String empJobTitle;
    private String client;
    private String subject;
    private SignatureService signatureService = new SignatureDaoService();
    private String sSignature;
    private SignatureVO sigObj;
    private List<SignatureVO> signatureList;
    java.util.Vector supportAttachmentsMail = new java.util.Vector();
    private SupportVO suppObject;
    // To send mail to support team to upgrade the HCMOne
    @SkipValidation
    public String upgradeHCMOne() {
        
    	addActionMessage(getText("label.common.upgradeSuccess"));
        return SUCCESS;
    }
    
    
    public String setUpUpgradation() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        fromEmployee=oEmp.getEmpWorkEmail();
        empFirstName=oEmp.getEmpFirstName();
        empLastName=oEmp.getEmpLastName();
        empJobTitle=oEmp.getJobTitleIdObj().getJobTitleName();
        client=(String) session.get("MASTER_CLIENT_NAME");
        subject=(getText("label.upgrade.em"));
        return SUCCESS;
    }

    public String upgradationMail(){
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        subject=(getText("label.upgrade.em"));
        String supportEmailId = getText("label.header.support.mailId");
        String supportMailReceiverName = getText("label.header.support.mailIdMessage");
        upgradationMail(supportEmailId, supportMailReceiverName, getText("upgradation.add.addTo"), oEmp.getEmpFirstName(), subject);
        addActionMessage(getText("label.common.upgradeSuccess"));
        return SUCCESS;
    }

    
    public void upgradationMail(String oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
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
            + getText("label.header.upgradationDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
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
            + getText("label.header.support.message") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + suppObject.getUpgradeMessage() + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
//        supportAttachmentsMail="";
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendSupportEmail(oFirstPerson, sSubject, sMessage, sSignature, supportAttachmentsMail);
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


    public SupportVO getSuppObject() {
        return suppObject;
    }


    public void setSuppObject(SupportVO suppObject) {
        this.suppObject = suppObject;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getsSignature() {
        return sSignature;
    }


    public void setsSignature(String sSignature) {
        this.sSignature = sSignature;
    }


    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }


    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }
    
    

}
