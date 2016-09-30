package com.gits.rms.action.performance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoService;
import com.gits.rms.service.performance.QuestionDaoService;
import com.gits.rms.service.performance.QuestionService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class QuestionAction extends ActionSupport{

    private static final long serialVersionUID = 1616682616231746510L;
    private QuestionBankGeneralInfoService queBankGeneralInfoService=new QuestionBankGeneralInfoDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private QuestionService quesService=new QuestionDaoService();
    private RoleService roleService = new RoleDaoService();
    private List<SignatureVO> signatureList;
    private List<QuestionVO> questionList;
    private List<EmployeesVO> empList;
    private QuestionVO question;
    private RoleVO role;
    private SignatureVO sigObj;
    private String sSignature;
    private String modifiedDateMail = "";
    
    
    @SkipValidation
    public String questionTab(){
        empList = queBankGeneralInfoService.getAllApproversSubEmployeeList();
        if(!empList.isEmpty()){
            Map session = ActionContext.getContext().getSession();
            session.put("APPROVING_EMPLOYEE","APPROVING_EMPLOYEE");
        }
        return SUCCESS;
    }
    
    
    
 // when click Edit link it bring particular data into Form or click Add
    // OptionalQuestions it shows blank Form to enter New Data
    @SkipValidation
    public String setUpOptionalQuestions(){
        if((question!=null)&&(question.getHcmoQuestionId()!=null)){
            question=quesService.getQuestions(question.getHcmoQuestionId());
        }
        return SUCCESS;
    }
    
 // NumberingQuestions it shows blank Form to enter New Data
    @SkipValidation
    public String setUpNumberingQuestions(){
        if((question!=null)&&(question.getHcmoQuestionId()!=null)){
            question=quesService.getQuestions(question.getHcmoQuestionId());
        }
        return SUCCESS;
    }
    
 // SummaryQuestions it shows blank Form to enter New Data
    @SkipValidation
    public String setUpSummaryQuestions(){
        if((question!=null)&&(question.getHcmoQuestionId()!=null)){
            question=quesService.getQuestions(question.getHcmoQuestionId());
        }
        return SUCCESS;
    }
    
    // To get List of OptionalQuestions
    @SkipValidation
    public String getAllOptionalQuestions(){
        questionList=quesService.getAllOptionalQuestions();
        return SUCCESS;
    }
    
 // To get List of NumberingQuestions
    @SkipValidation
    public String getAllNumberingQuestions(){
        questionList=quesService.getAllNumberingQuestions();
        return SUCCESS;
    }
    
 // To get List of SummaryQuestions
    @SkipValidation
    public String getAllSummaryQuestions(){
        questionList=quesService.getAllSummaryQuestions();
        return SUCCESS;
    }
    
 // To get Particular OptionalQuestions Data
    @SkipValidation
    public String getQuestionView() {
        if ((question != null) && (question.getHcmoQuestionId() != null)) {
            question = quesService.getQuestions(question.getHcmoQuestionId());
        }
        if(question.getQuestionType().equals("optional")){
            return "OptionalView";
        }else if (question.getQuestionType().equals("numbering")) {
            return "NumberingView";
        }else if (question.getQuestionType().equals("summary")) {
            return "SummaryView";
        }
        return NONE;
    }

    // In the New Form when click Submit button To insert new SubCategory or update
    // particular Questions Data
    public String insertOrUpdateQuestion(){
        try{
        if(question.getHcmoQuestionId()==null){
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            if(question.getHcmoCategoryId().getHcmoCategoryId()==null){
                addFieldError("question.hcmoCategoryId.hcmoCategoryId", "Category name is required field");
                return INPUT;
            }
            if(question.getHcmoSubCategoryId().getHcmoSubCategoryId()==null){
                addFieldError("question.hcmoSubCategoryId.hcmoSubCategoryId", "SubCategory name is required field");
                return INPUT;
            }
            if(question.getQuestion().isEmpty()){
                addFieldError("question.question", "Question is a required field");
                return INPUT;
            }
            if(question.getQuestionType().equals("optional")){
                if(question.getOption1().isEmpty()){
                    addFieldError("question.option1", "Option 1 is required field");
                    return INPUT;
                }
                if(question.getOption2().isEmpty()){
                    addFieldError("question.option2", "Option 2 is required field");
                    return INPUT;
                }
                if(question.getOption3().isEmpty()){
                    addFieldError("question.option3", "Option 3 is required field");
                    return INPUT;
                }
            }
            
            if(question.getQuestionType().equals("numbering")){
                if(question.getMinRate().isEmpty()){
                    addFieldError("question.minRate", "Minimum Rate is required field");
                    return INPUT;
                }
                if(question.getMaxRate().isEmpty()){
                    addFieldError("question.maxRate", "Maximum Rate is required field");
                    return INPUT;
                }
            }
            question.setCreated(DateUtils.getCurrentDateTime());
            question.setCreatedBy(oEmp);
            question.setUpdatedBy(oEmp);
            question.setIsActive(1);
            quesService.insertQuestion(question);
            
            // Messaging
            //Getting admin RoleID
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            EmployeesVO empIdObj;
            String sSubject = getText("message.subject.performancekpiQuestion.add");
            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
            
            for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
            	empIdObj = it.next();
            	int loggedInEmpId =oEmp.getEmployeeId(); 
            	int adminEmpId = empIdObj.getEmployeeId();

            	if(loggedInEmpId != adminEmpId){
            		mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiQuestion.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
            	}
            }
            
            mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiQuestion.add.loggedInEmp"), sSubject);
            
            addActionMessage(getText("Added Successfully"));
        }else{
            Map session=ActionContext.getContext().getSession();
            EmployeesVO oEmp=(EmployeesVO) session.get("EMPLOYEE_OBJECT");
            if(question.getHcmoCategoryId().getHcmoCategoryId()==null){
                addFieldError("question.hcmoCategoryId.hcmoCategoryId", "Category name is required field");
                return INPUT;
            }
            if(question.getHcmoSubCategoryId().getHcmoSubCategoryId()==null){
                addFieldError("question.hcmoSubCategoryId.hcmoSubCategoryId", "SubCategory name is required field");
                return INPUT;
            }
            if(question.getQuestion().isEmpty()){
                addFieldError("question.question", "Question is a required field");
                return INPUT;
            }
            if(question.getQuestionType().equals("optional")){
                if(question.getOption1().isEmpty()){
                    addFieldError("question.option1", "Option 1 is required field");
                    return INPUT;
                }
                if(question.getOption2().isEmpty()){
                    addFieldError("question.option2", "Option 2 is required field");
                    return INPUT;
                }
                if(question.getOption3().isEmpty()){
                    addFieldError("question.option3", "Option 3 is required field");
                    return INPUT;
                }
            }
            
            if(question.getQuestionType().equals("numbering")){
                if(question.getMinRate().isEmpty()){
                    addFieldError("question.minRate", "Minimum Rate is required field");
                    return INPUT;
                }
                if(question.getMaxRate().isEmpty()){
                    addFieldError("question.maxRate", "Maximum Rate is required field");
                    return INPUT;
                }
            }
            question.setUpdatedBy(oEmp);
            if(question.getQuestionType().equals("optional")){
                quesService.updateOptionalQuestion(question);
            }
            if(question.getQuestionType().equals("numbering")){
                quesService.updateNumberingQuestion(question);
            }
            if(question.getQuestionType().equals("summary")){
                quesService.updateSummaryQuestion(question);
            }
            // Messaging
            //Getting admin RoleID
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            EmployeesVO empIdObj;
            String sSubject = getText("message.subject.performancekpiQuestion.edit");
            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
            
            for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
                empIdObj = it.next();
                int loggedInEmpId =oEmp.getEmployeeId(); 
                int adminEmpId = empIdObj.getEmployeeId();

                if(loggedInEmpId != adminEmpId){
                    mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiQuestion.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                }
            }
            
            mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiQuestion.edit.loggedInEmp"), sSubject);
            
            addActionMessage(getText("Updated Successfully"));
        }
        }catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
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
                + getText("label.header.performance.question") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.category") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getHcmoCategoryId().getCategoryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
                
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.subCategory") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getHcmoSubCategoryId().getSubCategoryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
                
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.question.questionType") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getQuestionType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
             
             .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.questionTab") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getQuestion() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if(question.getOption1()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption1() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption2()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option2") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption2() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption3()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option3") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption3() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption4()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option4") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption4() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption5()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option5") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption5() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption6()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option6") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption6() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getMinRate()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.minRate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getMinRate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getMaxRate()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.maxRate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getMaxRate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

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
                + getText("label.header.performance.question") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
            
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.category") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getHcmoCategoryId().getCategoryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
                
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.subCategory") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getHcmoSubCategoryId().getSubCategoryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
                
            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.question.questionType") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getQuestionType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)
             
             .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.questionTab") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + question.getQuestion() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if(question.getOption1()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption1() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption2()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option2") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption2() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption3()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option3") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption3() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption4()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option4") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption4() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption5()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option5") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption5() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getOption6()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.option6") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getOption6() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getMinRate()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.minRate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getMinRate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            
            if(question.getMaxRate()!=null){
            	sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.question.maxRate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + question.getMaxRate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

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
    
    
    public QuestionVO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionVO question) {
        this.question = question;
    }

    public List<QuestionVO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionVO> questionList) {
        this.questionList = questionList;
    }

    public List<EmployeesVO> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmployeesVO> empList) {
        this.empList = empList;
    }
    
}
