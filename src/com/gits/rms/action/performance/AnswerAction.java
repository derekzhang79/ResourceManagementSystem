package com.gits.rms.action.performance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.performance.AnswerDaoService;
import com.gits.rms.service.performance.AnswerService;
import com.gits.rms.service.performance.QuestionBankDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoService;
import com.gits.rms.service.performance.QuestionBankService;
import com.gits.rms.service.performance.QuestionGeneralInfoGroupDaoService;
import com.gits.rms.service.performance.QuestionGeneralInfoGroupService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.AnswerVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGeneralInfoGroupVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;

public class AnswerAction extends ActionSupport{

    private static final long serialVersionUID = 1153549055222363573L;
    private AnswerVO answer=new AnswerVO();
    private List<AnswerVO> answerList;
    private AnswerService answerService=new AnswerDaoService();
    private QuestionBankGeneralInfoVO quesBankGeneralInfo;
    private List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList;
    private QuestionBankGeneralInfoService quesBankGeneralInfoService=new QuestionBankGeneralInfoDaoService();
    private List<QuestionGroupNameIdentificationVO> questionGroupIdentifiList;
    private QuestionGroupNameIdentificationVO questionGroupIdentifi;
    private Integer questionGroupIdentifiId;
    private List<QuestionBankVO> questionBankList;
    private QuestionBankService questionBankService=new QuestionBankDaoService();
    private QuestionBankVO quesBank=new QuestionBankVO();
    private List qBankList;
    private QuestionBankVO quesBankId=new QuestionBankVO();
    private String answerEntered="";
    private Integer subEmployeeID;
    private EmployeesVO emp;
    private List<EmployeesVO> peerEmpList;
    private EmployeesService empService=new EmployeesDaoService();
    private List<AnswerVO> answerOptionalList;
    private List<AnswerVO> answerNumberingList;
    private List<AnswerVO> answerSummarryList;
    private Integer employeeId;
    private String answerSaved;
    private Integer answerId;
    private Integer peerEmployeeID;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private Integer questionGeneralInfoGroupIdListId;
    private QuestionGeneralInfoGroupService qGeneralInfoGroupService=new QuestionGeneralInfoGroupDaoService();
    private QuestionGeneralInfoGroupVO qGeneralInfoGroup;
    private String status="";
    private Integer questionGeneralInfoId;
    Integer empId;
    private Integer hcmoQuestionGeneralInfoId;
    private String employeeType="";
    
    @SkipValidation
    public String getAllEmployeeQuestionBankGroup(){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        if(mSession.get("Peer_Employee_Id")!=null){
            empId=(Integer) mSession.get("Peer_Employee_Id");    
        }
        
        if(mSession.get("subEmployeeID")!=null){
            Integer appEmpId=(Integer) mSession.get("subEmployeeID");    
        }
        if(empId!=null){
            emp=empService.getEmployees(empId);
            quesBankGeneralInfoList=answerService.getAllEmployeeQuestionBankName(emp.getEmployeeId());
            mSession.put("Peer_employee_id",emp.getEmployeeId());
            employeeType="Peer Employee";
        }else{
            quesBankGeneralInfoList=answerService.getAllEmployeeQuestionBankName(oEmp.getEmployeeId());
            employeeType="Employee";
        }
        mSession.remove("Peer_Employee_Id");
       
        return SUCCESS;
        
    }
    
    
    @SkipValidation
    public String getAllApproversSubEmployeeData(){
        employeeType="Approving Employee";
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAllEmployeePeerEmployeeList(){
        HttpServletRequest request = ServletActionContext.getRequest();
              peerEmpList=quesBankGeneralInfoService.getAllEmployeesPeerEmployeeList(subEmployeeID);
        request.setAttribute("peerEmployeeList", peerEmpList);
        return SUCCESS;
    }
    
    
    
    @SkipValidation
    public String getEmployeeAnswerEntered(){
        quesBankGeneralInfo=answerService.getQuestionBankGeneralInfoVO(subEmployeeID);
        questionGroupIdentifiId=quesBankGeneralInfo.getHcmoQuestionGroupNameIdentificationId().getHcmoQuestionGroupNameIdentificationId();
        answerOptionalList=answerService.getAllSubEmployeeAnswerOptional(subEmployeeID, questionGroupIdentifiId);
        answerNumberingList=answerService.getAllSubEmployeeAnswerNumbering(subEmployeeID, questionGroupIdentifiId);
        answerSummarryList=answerService.getAllSubEmployeeAnswerSummarry(subEmployeeID, questionGroupIdentifiId);
        return SUCCESS;
        
    }
    
    
    @SkipValidation
    public String getEmployeePeerAnswerEntered(){
        quesBankGeneralInfo=answerService.getQuestionBankPeerEmployees(subEmployeeID, peerEmployeeID);
        questionGroupIdentifiId=quesBankGeneralInfo.getHcmoQuestionGroupNameIdentificationId().getHcmoQuestionGroupNameIdentificationId();
        answerOptionalList=answerService.getAllSubEmployeePeerAnswerOptional(subEmployeeID, questionGroupIdentifiId,peerEmployeeID);
        answerNumberingList=answerService.getAllSubEmployeePeerAnswerNumbering(subEmployeeID, questionGroupIdentifiId,peerEmployeeID);
        answerSummarryList=answerService.getAllSubEmployeePeerAnswerSummarry(subEmployeeID, questionGroupIdentifiId,peerEmployeeID);
        return SUCCESS;
        
    }
    
    
//    @SkipValidation
//    public String getAllAppEmployeeQuestionBankGroup(){
//        quesBankGeneralInfoList=answerService.getAllEmployeeQuestionBankName();
//        return SUCCESS;
//        
//    }
    
   //Employee Answer List
    @SkipValidation
    public String getAllAnswerList(){
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//        quesBankGeneralInfo=answerService.getQuestionBankGeneralInfoVO(oEmp.getEmployeeId());
//        questionGroupIdentifiId=quesBankGeneralInfo.getHcmoQuestionGroupNameIdentificationId().getHcmoQuestionGroupNameIdentificationId();
        questionBankList=questionBankService.getAllQuestionBank(questionGroupIdentifiId);
        answerList=questionBankService.getAnswerCountForQuestion(questionGroupIdentifiId);
//        if(questionBankList.size()==answerList.size()){
//            addActionMessage("You already entered answers");
//            return "COMPLETEANSWERS";
//        }
        session.put("QUESTIONBANKLIST",questionBankList);
        if(!questionBankList.isEmpty()){
            for(int i=0;i<questionBankList.size();i++){
                quesBank=questionBankList.get(i);
                break;
            }
            session.get("Peer_Employee_Id");
            if(session.get("Peer_Employee_Id")!=null){
                Integer empId=(Integer) session.get("Peer_Employee_Id");
                answer=answerService.getAppPeerAnswer(quesBank.getHcmoQuestionBankId(),oEmp.getEmployeeId());
                employeeType="Peer Employee";
            }else{
                answer=answerService.getAnswer(quesBank.getHcmoQuestionBankId(),oEmp.getEmployeeId());
            }
            if(answer!=null){
                setAnswerEntered(answer.getAnswer());
                answerSaved="answerSaved";
            }else{
                answerSaved="notAnswerSaved";
            }
            quesBankId=questionBankService.getQuestionBank(quesBank.getHcmoQuestionBankId());
            qGeneralInfoGroup=qGeneralInfoGroupService.getQuestioinBankGeneralInfoGroup(questionGeneralInfoGroupIdListId);
    //        if(quesBankId.getHcmoQuestionId().getQuestionType().equals("optional")){
    //            return "OPTIONAL";
    //        }else if(quesBankId.getHcmoQuestionId().getQuestionType().equals("numbering")){
    //            return "NUMBERING";
    //        }
        }else{
            addActionError("There is no Questions for Particular Group");
        }
        return SUCCESS;
    }
    
   //Employee Answer insert
    @SkipValidation
    public String insertOrUpdateAnswer(){
        Map session = ActionContext.getContext().getSession();
        AnswerVO answerVO;
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        if( session.get("QuestionBankId")!=null){
            session.get("QuestionBankId");
            quesBankId=(QuestionBankVO) session.get("QuestionBankId");
        }
        qGeneralInfoGroup=qGeneralInfoGroupService.getQuestioinBankGeneralInfoGroup(questionGeneralInfoGroupIdListId);
        quesBank=questionBankService.getQuestionBank(quesBankId.getHcmoQuestionBankId());
        Integer empId=(Integer) session.get("Peer_Employee_Id");
        Integer appEmpSubEmpId=(Integer) session.get("App_Emp_Sub_Emp");
        if(empId!=null){
            Integer employeeId=(Integer) session.get("Peer_Employee_Id");
            employeeType="Peer Employee";
            answerVO=answerService.getAppPeerAnswer(quesBank.getHcmoQuestionBankId(),oEmp.getEmployeeId());
        }else{
            answerVO=answerService.getAnswer(quesBank.getHcmoQuestionBankId(),oEmp.getEmployeeId());
        }
        if(answerVO==null){
            answer.setHcmoQuestionBankId(quesBank);
            answer.setAnswer(answerEntered);
            answer.setHcmoAppraiserEmployeeId(oEmp);
            if(empId!=null){
                emp=empService.getEmployees(empId);
                answer.setHcmoAppraisingEmployeeId(emp);
                answer.setHcmoQuestionGeneralInfoGroup(qGeneralInfoGroup);
                answer.setStatus("peeremployee");
            }else if (appEmpSubEmpId!=null) {
                emp=empService.getEmployees(appEmpSubEmpId);
                answer.setHcmoAppraisingEmployeeId(emp);
                answer.setHcmoQuestionGeneralInfoGroup(qGeneralInfoGroup);
                answer.setStatus("approvingemployee");
            }
            else{
//                quesBankGeneralInfo=answerService.getQuestionGeneralInfoGroupName(oEmp.getEmployeeId(),"employee", answer.getHcmoQuestionBankId().getHcmoQuestionGroupNameIdentificationId().getHcmoQuestionGroupNameIdentificationId());
                answer.setHcmoQuestionGeneralInfoGroup(qGeneralInfoGroup);
                answer.setHcmoAppraisingEmployeeId(oEmp);
                answer.setStatus("employee");
            }
            answer.setCreated(DateUtils.getCurrentDateTime());
            answer.setCreatedBy(oEmp);
            answer.setUpdatedBy(oEmp);
            answer.setIsActive(1);
            answerService.insertAnswer(answer);
      }
        questionBankList=(List<QuestionBankVO>) session.get("QUESTIONBANKLIST");
        questionBankList.remove(0);
        if(!questionBankList.isEmpty()){
            for(int i=0;i<questionBankList.size();i++){
                quesBank=questionBankList.get(i);
                break;
            }
            quesBankId=questionBankService.getQuestionBank(quesBank.getHcmoQuestionBankId());
            session.put("QuestionBankId",quesBankId);
            if(empId!=null){
                answer=answerService.getAppPeerAnswer(quesBankId.getHcmoQuestionBankId(),oEmp.getEmployeeId());
            }else{
                answer=answerService.getAnswer(quesBankId.getHcmoQuestionBankId(),oEmp.getEmployeeId());
            }
            if(answer!=null){
                answerSaved="answerSaved";
                setAnswerEntered(answer.getAnswer());
            }else{
                setAnswerEntered("");
                answerSaved="notAnswerSaved";
            }
        }else if(appEmpSubEmpId!=null){
            session.put("subEmployeeID", appEmpSubEmpId);
            addActionMessage("Completed Your Questions");
            return "approvercompleted";
        }else if(empId!=null){
            session.put("employeeId", empId);
            session.remove("QUESTION_PEEREMPLOYEE");
            addActionMessage("Completed Your Questions");
            return "peercompleted";
        }else{
            session.remove("QUESTION_EMPLOYEE");
            addActionMessage("Completed Your Questions");
            return "completed";
        }
        return SUCCESS;
    }
    
    
    @SkipValidation
    public String getAllAnswer(){
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        quesBankGeneralInfo=quesBankGeneralInfoService.getEmployeeQuestionBankGeneralInfo(quesBankGeneralInfo.getHcmoQuestionGeneralInfoId(),questionGroupIdentifiId, questionGeneralInfoGroupIdListId);
        if(quesBankGeneralInfo.getStatus()==null|| quesBankGeneralInfo.getStatus().isEmpty()){
            if(quesBankGeneralInfo.getEmployeeType().equals("Employee")){
                answerList=answerService.getAllAnswers(questionGroupIdentifiId,questionGeneralInfoGroupIdListId,oEmp.getEmployeeId(),oEmp.getEmployeeId());
                status="employee";
            }else if(quesBankGeneralInfo.getEmployeeType().equals("Approving Employee")){
                answerList=answerService.getAllApprovingAnswers(questionGroupIdentifiId,questionGeneralInfoGroupIdListId,subEmployeeID);
                status="approvingemployee";
            }else if(quesBankGeneralInfo.getEmployeeType().equals("Peer Employee")){
                answerList=answerService.getAllPeerAnswers(questionGroupIdentifiId,questionGeneralInfoGroupIdListId,subEmployeeID);
                status="peeremployee";
            }
        }else if(quesBankGeneralInfo.getStatus().equals("Completed")){
            if(quesBankGeneralInfo.getEmployeeType().equals("Approving Employee")){
                mSession.put("subEmployeeID",subEmployeeID);
                addActionMessage("You already Complete and Submitted Your Answer");
                return "approvingEmployee";
            }else if(quesBankGeneralInfo.getEmployeeType().equals("Peer Employee")){
                mSession.put("employeeId",subEmployeeID);
                addActionMessage("You already Complete and Submitted Your Answer");
                return "peerEmployee";
            }if(quesBankGeneralInfo.getEmployeeType().equals("Employee")){
                mSession.put("subEmployeeID",subEmployeeID);
                addActionMessage("You already Complete and Submitted Your Answer");
                return "completed";
            }
        }
       
        return SUCCESS;
    }
    
    @SkipValidation
    public String getSubEmployeeQuestions(){
        Map mSession = ActionContext.getContext().getSession();
        if(mSession.get("subEmployeeID")!=null){
            subEmployeeID=(Integer) mSession.get("subEmployeeID");
        }
//        Integer employeeId=Integer.parseInt(subEmployeeID);
        emp=empService.getEmployees(subEmployeeID);
        quesBankGeneralInfoList=answerService.getAllAppEmployeeQuestionBankName(emp.getEmployeeId());
        mSession.remove("subEmployeeID");
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAllPeersSubEmployee(){
        HttpServletRequest request = ServletActionContext.getRequest();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        peerEmpList=quesBankGeneralInfoService.getEmployeeListForPeerEmployee(oEmp.getEmployeeId());
        request.setAttribute("peerEmployeeList", peerEmpList);
        return SUCCESS;
    }
    
    @SkipValidation
    public String getPeerEmployeeQuestionBank(){
        Map mSession = ActionContext.getContext().getSession();
        if(mSession.get("employeeId")!=null){
            employeeId=(Integer) mSession.get("employeeId");
        }
        quesBankGeneralInfoList=quesBankGeneralInfoService.getPeerEmployeeQuestionBank(employeeId);
        mSession.put("QUESTION_PEEREMPLOYEE","QUESTION_PEEREMPLOYEE");
        mSession.put("Peer_Employee_Id",employeeId);
        mSession.remove("employeeId");
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAppEmpSubEmpQuestions(){
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        questionBankList=questionBankService.getAllQuestionBank(questionGroupIdentifiId);
        answerList=questionBankService.getAnswerCountForQuestion(questionGroupIdentifiId);
//        if(questionBankList.size()==answerList.size()){
//            addActionMessage("You already entered answers");
//            return "COMPLETEANSWERS";
//        }
        session.put("QUESTIONBANKLIST",questionBankList);
        if(!questionBankList.isEmpty()){
            for(int i=0;i<questionBankList.size();i++){
                quesBank=questionBankList.get(i);
                break;
            }
            answer=answerService.getAppPeerAnswer(quesBank.getHcmoQuestionBankId(),oEmp.getEmployeeId());
            employeeType="Approving Employee";
            session.put("App_Emp_Sub_Emp",subEmployeeID);
            if(answer!=null){
                setAnswerEntered(answer.getAnswer());
                answerSaved="answerSaved";
            }else{
                answerSaved="notAnswerSaved";
            }
            quesBankId=questionBankService.getQuestionBank(quesBank.getHcmoQuestionBankId());
            qGeneralInfoGroup=qGeneralInfoGroupService.getQuestioinBankGeneralInfoGroup(questionGeneralInfoGroupIdListId);
    //        if(quesBankId.getHcmoQuestionId().getQuestionType().equals("optional")){
    //            return "OPTIONAL";
    //        }else if(quesBankId.getHcmoQuestionId().getQuestionType().equals("numbering")){
    //            return "NUMBERING";
    //        }
    }
        return SUCCESS;
 }
    
    @SkipValidation
    public String getAnswers(){
        answer=answerService.getParticularAnswer(answerId);
        setAnswerEntered(answer.getAnswer());
        return SUCCESS;
    }
    
    @SkipValidation
    public String updateAnswers(){
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        answer=answerService.getParticularAnswer(answerId);
        status=answer.getStatus();
        answer.setAnswer(answerEntered);
        answer.setCreated(DateUtils.getCurrentDateTime());
        answer.setCreatedBy(oEmp);
        answer.setUpdatedBy(oEmp);
        answer.setIsActive(1);
        answerService.updateAnswer(answer);
        addActionMessage("Updated Successfully");
        session.put("subEmployeeID", answer.getHcmoAppraisingEmployeeId().getEmployeeId());
        if(status.equals("employee")){
            return SUCCESS;
        }else if(status.equals("approvingemployee")){
            return "approvingemployee";
        }else if(status.equals("peeremployee")){
            session.put("employeeId", answer.getHcmoAppraisingEmployeeId().getEmployeeId());
            return "peeremployee";
        }
        return SUCCESS;
    }

    @SkipValidation
    public String getAllApproverSubEmployee(){
        loadValues.getAllApproversSubEmployeeList();
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAnswerView(){
        if ((answer != null) && (answer.getHcmoAnswerId() != null)) {
            answer = answerService.getAnswerView(answer.getHcmoAnswerId());
        }
        return SUCCESS;
    }
    
    public AnswerVO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerVO answer) {
        this.answer = answer;
    }

    public List<AnswerVO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerVO> answerList) {
        this.answerList = answerList;
    }

    public List<QuestionBankGeneralInfoVO> getQuesBankGeneralInfoList() {
        return quesBankGeneralInfoList;
    }

    public void setQuesBankGeneralInfoList(List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList) {
        this.quesBankGeneralInfoList = quesBankGeneralInfoList;
    }

    public List<QuestionGroupNameIdentificationVO> getQuestionGroupIdentifiList() {
        return questionGroupIdentifiList;
    }

    public void setQuestionGroupIdentifiList(List<QuestionGroupNameIdentificationVO> questionGroupIdentifiList) {
        this.questionGroupIdentifiList = questionGroupIdentifiList;
    }

    public QuestionGroupNameIdentificationVO getQuestionGroupIdentifi() {
        return questionGroupIdentifi;
    }

    public void setQuestionGroupIdentifi(QuestionGroupNameIdentificationVO questionGroupIdentifi) {
        this.questionGroupIdentifi = questionGroupIdentifi;
    }

    public Integer getQuestionGroupIdentifiId() {
        return questionGroupIdentifiId;
    }

    public void setQuestionGroupIdentifiId(Integer questionGroupIdentifiId) {
        this.questionGroupIdentifiId = questionGroupIdentifiId;
    }

    public List<QuestionBankVO> getQuestionBankList() {
        return questionBankList;
    }

    public void setQuestionBankList(List<QuestionBankVO> questionBankList) {
        this.questionBankList = questionBankList;
    }

    public QuestionBankVO getQuesBank() {
        return quesBank;
    }

    public void setQuesBank(QuestionBankVO quesBank) {
        this.quesBank = quesBank;
    }

    public QuestionBankGeneralInfoVO getQuesBankGeneralInfo() {
        return quesBankGeneralInfo;
    }

    public void setQuesBankGeneralInfo(QuestionBankGeneralInfoVO quesBankGeneralInfo) {
        this.quesBankGeneralInfo = quesBankGeneralInfo;
    }

    public List getqBankList() {
        return qBankList;
    }

    public void setqBankList(List qBankList) {
        this.qBankList = qBankList;
    }

    public QuestionBankVO getQuesBankId() {
        return quesBankId;
    }

    public void setQuesBankId(QuestionBankVO quesBankId) {
        this.quesBankId = quesBankId;
    }

    public String getAnswerEntered() {
        return answerEntered;
    }

    public void setAnswerEntered(String answerEntered) {
        this.answerEntered = answerEntered;
    }

    public EmployeesVO getEmp() {
        return emp;
    }

    public void setEmp(EmployeesVO emp) {
        this.emp = emp;
    }

    public Integer getSubEmployeeID() {
        return subEmployeeID;
    }

    public void setSubEmployeeID(Integer subEmployeeID) {
        this.subEmployeeID = subEmployeeID;
    }


    public List<AnswerVO> getAnswerOptionalList() {
        return answerOptionalList;
    }


    public void setAnswerOptionalList(List<AnswerVO> answerOptionalList) {
        this.answerOptionalList = answerOptionalList;
    }


    public List<AnswerVO> getAnswerNumberingList() {
        return answerNumberingList;
    }


    public void setAnswerNumberingList(List<AnswerVO> answerNumberingList) {
        this.answerNumberingList = answerNumberingList;
    }


    public List<AnswerVO> getAnswerSummarryList() {
        return answerSummarryList;
    }


    public void setAnswerSummarryList(List<AnswerVO> answerSummarryList) {
        this.answerSummarryList = answerSummarryList;
    }


    public List<EmployeesVO> getPeerEmpList() {
        return peerEmpList;
    }


    public void setPeerEmpList(List<EmployeesVO> peerEmpList) {
        this.peerEmpList = peerEmpList;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    public String getAnswerSaved() {
        return answerSaved;
    }


    public void setAnswerSaved(String answerSaved) {
        this.answerSaved = answerSaved;
    }


    public Integer getAnswerId() {
        return answerId;
    }


    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }


    public Integer getPeerEmployeeID() {
        return peerEmployeeID;
    }


    public void setPeerEmployeeID(Integer peerEmployeeID) {
        this.peerEmployeeID = peerEmployeeID;
    }


    public Integer getQuestionGeneralInfoGroupIdListId() {
        return questionGeneralInfoGroupIdListId;
    }


    public void setQuestionGeneralInfoGroupIdListId(Integer questionGeneralInfoGroupIdListId) {
        this.questionGeneralInfoGroupIdListId = questionGeneralInfoGroupIdListId;
    }


    public QuestionGeneralInfoGroupVO getqGeneralInfoGroup() {
        return qGeneralInfoGroup;
    }


    public void setqGeneralInfoGroup(QuestionGeneralInfoGroupVO qGeneralInfoGroup) {
        this.qGeneralInfoGroup = qGeneralInfoGroup;
    }


    public Integer getQuestionGeneralInfoId() {
        return questionGeneralInfoId;
    }


    public void setQuestionGeneralInfoId(Integer questionGeneralInfoId) {
        this.questionGeneralInfoId = questionGeneralInfoId;
    }


    public Integer getHcmoQuestionGeneralInfoId() {
        return hcmoQuestionGeneralInfoId;
    }


    public void setHcmoQuestionGeneralInfoId(Integer hcmoQuestionGeneralInfoId) {
        this.hcmoQuestionGeneralInfoId = hcmoQuestionGeneralInfoId;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getEmployeeType() {
        return employeeType;
    }


    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
    

   
    
}
