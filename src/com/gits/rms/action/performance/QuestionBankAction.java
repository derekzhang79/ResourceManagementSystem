package com.gits.rms.action.performance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.performance.QuestionBankDaoService;
import com.gits.rms.service.performance.QuestionBankService;
import com.gits.rms.service.performance.QuestionDaoService;
import com.gits.rms.service.performance.QuestionService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class QuestionBankAction extends ActionSupport{

    private static final long serialVersionUID = 2105806846536992350L;
    private List<QuestionBankVO> quesBankList;
    private QuestionBankService quesBankService=new QuestionBankDaoService();
    private RoleService roleService = new RoleDaoService();
    private QuestionService quesService=new QuestionDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private QuestionGroupNameIdentificationVO quesGroupId;
    private List<QuestionGroupNameIdentificationVO> questGroupList;
    private QuestionBankVO quesBank;
    private RoleVO role;
    private List<QuestionVO> questionList;
    private List<QuestionVO> questionOptionalList;
    private List<QuestionVO> questionNumberingList;
    private List<QuestionVO> questionSummaryList;
    private List<SignatureVO> signatureList;
    private List cbList;
    private String cbListId=""; 
    private String testing="";
    private SignatureVO sigObj;
    private String sSignature;
    private String modifiedDateMail = "";
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private Integer quesGroupNameIdentificationId;
    
    @SkipValidation
    public String getAllQuestions(){
        Map session = ActionContext.getContext().getSession();
        quesBankList=quesBankService.getAllQuestions();
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAllQuestionType(){
        Map session = ActionContext.getContext().getSession();
        questionOptionalList=quesService.getAllOptionalQuestions();
        questionNumberingList=quesService.getAllNumberingQuestions();
        questionSummaryList=quesService.getAllSummaryQuestions();
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAllQuestionGroupView(){
        questionList=quesBankService.getAllQuestionBank(quesGroupNameIdentificationId);
        questionOptionalList=quesService.getAllOptionQuestionsView(questionList);
        questionNumberingList=quesService.getAllNumberingQuestionsView(questionList);
        questionSummaryList=quesService.getAllSummaryQuestionsView(questionList);
        return SUCCESS;
    }
    
    
    public String insertQuestionBank(){
       
        if(quesBank.getHcmoQuestionBankId()==null){
            if(quesGroupId.getName().isEmpty()){
                questionOptionalList=quesService.getAllOptionalQuestions();
                questionNumberingList=quesService.getAllNumberingQuestions();
                questionSummaryList=quesService.getAllSummaryQuestions();
                addFieldError("quesGroupId.name", "Please Enter Group Name");
                return INPUT;
            }else{
                quesBankList=quesBankService.checkQuestionBank(quesGroupId);
                if(quesBankList.size()>0){
                    questionOptionalList=quesService.getAllOptionalQuestions();
                    questionNumberingList=quesService.getAllNumberingQuestions();
                    questionSummaryList=quesService.getAllSummaryQuestions();
                    addFieldError("quesGroupId.name", "Group Name Already Exist");
                    return INPUT;
                }
            }
//            if(quesBank.getName().isEmpty()){
//                questionOptionalList=quesService.getAllOptionalQuestions();
//                questionNumberingList=quesService.getAllNumberingQuestions();
//                questionSummaryList=quesService.getAllSummaryQuestions();
//                addFieldError("quesBank.name", "Please Enter Group Name");
//                return INPUT;
//            }
            if(cbList==null){
                questionOptionalList=quesService.getAllOptionalQuestions();
                questionNumberingList=quesService.getAllNumberingQuestions();
                questionSummaryList=quesService.getAllSummaryQuestions();
                addActionError("Please select atleast one checkbox");
                return INPUT;
            }
            
                Map session=ActionContext.getContext().getSession();
                EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                quesGroupId.setName(quesBank.getName());
               
                quesGroupId.setCreated(DateUtils.getCurrentDateTime());
                quesGroupId.setCreatedBy(oEmp);
                quesGroupId.setUpdatedBy(oEmp);
                quesGroupId.setIsActive(1);
                quesBankService.insertQuestionGroup(quesGroupId);
                
            for(int i=0;i<cbList.size();i++){
//            QuestionVO q=(QuestionVO) cbList.get(i);
              cbListId=(String) cbList.get(i);
              QuestionVO questionId=quesService.getQuestions(Integer.parseInt(cbListId));
              quesBank.setHcmoQuestionGroupNameIdentificationId(quesGroupId);
              quesBank.setHcmoQuestionId(questionId);
              quesBank.setCreated(DateUtils.getCurrentDateTime());
              quesBank.setCreatedBy(oEmp);
              quesBank.setUpdatedBy(oEmp);
              quesBank.setIsActive(1);
              quesBankService.insertQuestionBank(quesBank);
          }
            
            // Messaging
        	//Getting admin RoleID
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            EmployeesVO empIdObj;
            String sSubject = getText("message.subject.performancekpiGroupQuestion.add");
            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
            
            for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
            	empIdObj = it.next();
            	int loggedInEmpId =oEmp.getEmployeeId(); 
            	int adminEmpId = empIdObj.getEmployeeId();

            	if(loggedInEmpId != adminEmpId){
            		mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiGroupQuestion.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
            	}
            }
            
            mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiGroupQuestion.add.loggedInEmp"), sSubject);
            
            addActionMessage(getText("Added Successfully"));
        }
        loadValues.getAllQuestionBankName();
        return SUCCESS;
        
    }
    
    
    @SkipValidation
    public String getAllQuestionGroupIdentificationName(){
        questGroupList=quesBankService.getAllQuestionGroupNameIdentification();
        return SUCCESS;
    }
    
    
    public void mailToAddedPerson(Integer oFirstPerson, String DearEmp, String Message, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            modifiedDateMail = DateFormat.format(DateUtils.getCurrentDateTime());

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.performance.kpigroup") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.questionGroup.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + quesGroupId.getName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + modifiedDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

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
            mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mailToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            modifiedDateMail = DateFormat.format(DateUtils.getCurrentDateTime());

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sDummy), sMessage.lastIndexOf(sDummy)
                + sDummy.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.performance.kpigroup") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.questionGroup.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + quesGroupId.getName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + modifiedDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

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
            mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QuestionBankVO getQuesBank() {
        return quesBank;
    }

    public void setQuesBank(QuestionBankVO quesBank) {
        this.quesBank = quesBank;
    }

    public List<QuestionBankVO> getQuesBankList() {
        return quesBankList;
    }

    public void setQuesBankList(List<QuestionBankVO> quesBankList) {
        this.quesBankList = quesBankList;
    }

    public List<QuestionVO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionVO> questionList) {
        this.questionList = questionList;
    }

    public List getCbList() {
        return cbList;
    }

    public void setCbList(List cbList) {
        this.cbList = cbList;
    }

    public String getTesting() {
        return testing;
    }

    public void setTesting(String testing) {
        this.testing = testing;
    }

    public List<QuestionVO> getQuestionOptionalList() {
        return questionOptionalList;
    }

    public void setQuestionOptionalList(List<QuestionVO> questionOptionalList) {
        this.questionOptionalList = questionOptionalList;
    }

    public List<QuestionVO> getQuestionNumberingList() {
        return questionNumberingList;
    }

    public void setQuestionNumberingList(List<QuestionVO> questionNumberingList) {
        this.questionNumberingList = questionNumberingList;
    }

    public List<QuestionVO> getQuestionSummaryList() {
        return questionSummaryList;
    }

    public void setQuestionSummaryList(List<QuestionVO> questionSummaryList) {
        this.questionSummaryList = questionSummaryList;
    }

    public QuestionGroupNameIdentificationVO getQuesGroupId() {
        return quesGroupId;
    }

    public void setQuesGroupId(QuestionGroupNameIdentificationVO quesGroupId) {
        this.quesGroupId = quesGroupId;
    }

    public List<QuestionGroupNameIdentificationVO> getQuestGroupList() {
        return questGroupList;
    }

    public void setQuestGroupList(List<QuestionGroupNameIdentificationVO> questGroupList) {
        this.questGroupList = questGroupList;
    }

    public Integer getQuesGroupNameIdentificationId() {
        return quesGroupNameIdentificationId;
    }

    public void setQuesGroupNameIdentificationId(Integer quesGroupNameIdentificationId) {
        this.quesGroupNameIdentificationId = quesGroupNameIdentificationId;
    }


}
