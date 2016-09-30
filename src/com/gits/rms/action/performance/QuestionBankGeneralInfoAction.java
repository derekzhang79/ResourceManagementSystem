package com.gits.rms.action.performance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.DepartmentDaoService;
import com.gits.rms.service.DepartmentService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.JobTitleDaoService;
import com.gits.rms.service.JobTitleService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TeamDaoService;
import com.gits.rms.service.TeamService;
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
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGeneralInfoGroupVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.TeamVO;

public class QuestionBankGeneralInfoAction extends ActionSupport{

    private static final long serialVersionUID = 1L;
    private QuestionBankGeneralInfoVO quesBankGeneralInfo;
    private QuestionBankGeneralInfoService quesBankGeneralInfoService=new QuestionBankGeneralInfoDaoService();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList;
    private Integer departmentName;
    private Integer teamName;
    private Integer jobTitleName;
    private String employeeType;
    private Date startDate;
    private Date endDate;
    private Integer groupName;
    private Integer projectName;
    private List employeeList;
    private List<EmployeesVO> emplList;
    private List<EmployeesVO> approvingEmployeeList;
    private List<EmployeesVO> assignedEmployeeList;
    private EmployeesService empService=new EmployeesDaoService();
    
    private DepartmentVO dept;
    private DepartmentService deptService=new DepartmentDaoService();
    private TeamVO team;
    private RoleVO role;
    private TeamService teamService=new TeamDaoService();
    private JobTitleVO jobTitle;
    private JobTitleService jobTitleService=new JobTitleDaoService();
    private QuestionBankService quesBankService=new QuestionBankDaoService();
    private QuestionGroupNameIdentificationVO questGroupIdentifi;
    private List peerEmployeeList;
    private List selectedAppEmployeeList;
    private List employeeIdList;
    private Integer employeeFullName;
    private EmployeeReportToService empReportToSetvice=new EmployeeReportToDaoService();
    private Integer employeeId;
    private String name="";
    private Integer approvingEmpGroupName;
    private Integer peerEmpGroupName;
    private Integer questionGroupIdentifiId;
    private Integer subEmployeeID;
    private Integer questionGeneralInfoGroupIdListId;
    private Integer questionGeneralInfoId;
    
    private QuestionGeneralInfoGroupVO quesGeneralInfoGroup;
    private QuestionGeneralInfoGroupService quesGeneralInfoGroupService=new QuestionGeneralInfoGroupDaoService();
    private List<QuestionGeneralInfoGroupVO> quesGeneralInfoGroupList;
    
    private List<QuestionBankVO> quesBankList;
    private List<AnswerVO> answerList;
    private AnswerService ansService=new AnswerDaoService();
    private String approverComments="";
    
    private List<QuestionBankGeneralInfoVO> quesBankAppEmployeeList;
    private List<QuestionBankGeneralInfoVO> quesBankPeerEmployeeList;
    private String adminComments="";
    private EmployeesVO appEmpId;
    private EmployeesVO peerEmpId;
    
    private SignatureVO sigObj;
    private String sSignature;
    private String modifiedDateMail = "";
    private List<SignatureVO> signatureList;
    
    public String setUpQuestBankGeneralInfoForm(){
        return SUCCESS;
    }
    
    @SkipValidation
    public String getGeneralInfoGroup(){
        Map session = ActionContext.getContext().getSession();
        if(departmentName==null){
            addFieldError("departmentName","Please choose Department name");
            return INPUT; 
        }
        if(teamName==null){
            addFieldError("teamName","Please choose Team name");
            return INPUT; 
        }
        if(jobTitleName==null){
            addFieldError("jobTitleName","Please choose Job Title name");
            return INPUT; 
        }
        if(projectName==null){
            addFieldError("projectName","Please choose Project Name");
            return INPUT; 
        }
        if(startDate==null){
            addFieldError("startDate","Please choose Start Date");
            return INPUT; 
        }
        if(endDate==null){
            addFieldError("endDate","Please choose End Date name");
            return INPUT; 
        }
        session.put("PROJECTNAME",projectName);
        session.put("DEPARTMENTNAME",departmentName);
        session.put("TEAMNAME",teamName);
        session.put("JOBTITLENAME",jobTitleName);
        session.put("STARTDATE",startDate);
        session.put("ENDDATE",endDate);
        session.put("EMPLOYEETYPE",employeeType);
        
        emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
        if(emplList.isEmpty()){
          session.remove("PROJECTNAME");
          session.remove("DEPARTMENTNAME");
          session.remove("TEAMNAME");
          session.remove("JOBTITLENAME");
          session.remove("EMPLOYEETYPE");
          session.remove("STARTDATE");
          session.remove("ENDDATE");
          session.remove("GROUPNAME");
          addActionError("There is no employee for search result");
          return INPUT;
        }
        
//        HttpServletRequest request = ServletActionContext.getRequest();
//        request.setAttribute("emplList", emplList);
        LoadKeyValuesAction loadKey = new LoadKeyValuesAction();
        loadKey.getAssignKPIEmpList(emplList);
        approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
        assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
        
    return SUCCESS;
   }
    
    @SkipValidation
    public String questionBankGeneralInfoEmployeeList(){
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        quesGeneralInfoGroup=new QuestionGeneralInfoGroupVO();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        EmployeesVO empId;
        System.out.println("name :"+name);
        if(name.isEmpty()){
        	addActionError("Group Name is a required field");
        	return INPUT;
        }
        quesGeneralInfoGroupList=quesGeneralInfoGroupService.getQuesBGenInfoGroup(name);
        if(quesGeneralInfoGroupList.size()>0){
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            projectName=(Integer) session.get("PROJECTNAME");
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("emplList", emplList);
            approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
            assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
            addActionError("Please choose different Employee Group Name");
            return INPUT;
        }else{
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            projectName=(Integer) session.get("PROJECTNAME");
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            quesBankGeneralInfo=quesBankGeneralInfoService.getAllassignedEmployee(startDate, endDate, employeeId);
            if(quesBankGeneralInfo!=null){
                emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("emplList", emplList);
                approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                addActionError("Please choose different Employee Name these employee already assigned");
                return INPUT;
            }
//            if(groupName==null){
//                emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
//                HttpServletRequest request = ServletActionContext.getRequest();
//                request.setAttribute("emplList", emplList);
//                approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
//                assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
//                addActionError("Please Choose Group Name for Employee");
//                return INPUT;
//            }
            for(int i=0;i<selectedAppEmployeeList.size();i++){
                String empEmailId=(String) selectedAppEmployeeList.get(i);
                appEmpId=quesBankService.getEmployeeId(empEmailId);
                Integer appEmpCompare=appEmpId.getEmployeeId();
                if(appEmpCompare.equals(employeeId)){
                      emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                      HttpServletRequest request = ServletActionContext.getRequest();
                      request.setAttribute("emplList", emplList);
                      approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                      assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                      addActionError("Employee and Approving Employee are not same");
                      return INPUT;
                }
                if(appEmpCompare!=null){
                    if(approvingEmpGroupName==null){
                        emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("emplList", emplList);
                        approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                        assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                        addActionError("Please Choose Approving Employee Group Name");
                        return INPUT;
                    }
                }
                quesBankGeneralInfo=quesBankGeneralInfoService.getAllAssignedAppEmployee(startDate, endDate, employeeId,appEmpId.getEmployeeId());
                if(quesBankGeneralInfo!=null){
                    emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("emplList", emplList);
                    approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                    assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                    addActionError("Please choose different Approving Employee Name these "+ appEmpId.getEmpFullName() +" already assigned to employee");
                    return INPUT;
                }
            }
            
            for(int i=0;i<peerEmployeeList.size();i++){
                String empEmailId=(String) peerEmployeeList.get(i);
                peerEmpId=quesBankService.getEmployeeId(empEmailId);
                Integer peerEmpCompare=peerEmpId.getEmployeeId();
                if(peerEmpCompare.equals(employeeId)){
                  emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                  HttpServletRequest request = ServletActionContext.getRequest();
                  request.setAttribute("emplList", emplList);
                  approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                  assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                  addActionError("Employee and Peer Employee are not same");
                  return INPUT;
                }
                
                if(peerEmpCompare!=null){
                    if(peerEmpGroupName==null){
                        emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("emplList", emplList);
                        approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                        assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                        addActionError("Please Choose Peer Employee Group Name");
                        return INPUT;
                    }
                }
                
                quesBankGeneralInfo=quesBankGeneralInfoService.getAllAssignedPeerEmployee(startDate, endDate, employeeId,peerEmpId.getEmployeeId());
                if(quesBankGeneralInfo!=null){
                    emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("emplList", emplList);
                    approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                    assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                    addActionError("Please choose different Peer Employee Name these "+peerEmpId.getEmpFullName()+" employee already assigned");
                    return INPUT;
                }
            }
            
        quesGeneralInfoGroup.setName(name);
        quesGeneralInfoGroup.setCreated(DateUtils.getCurrentDateTime());
        quesGeneralInfoGroup.setCreatedBy(oEmp);
        quesGeneralInfoGroup.setUpdatedBy(oEmp);
        quesGeneralInfoGroup.setIsActive(1);
        quesGeneralInfoGroupService.insertQuestioinBankGeneralInfoGroup(quesGeneralInfoGroup);
        quesGeneralInfoGroup=quesGeneralInfoGroupService.getQuestioinBankGeneralInfoGroup(quesGeneralInfoGroup.getHcmoQuestionGeneralInfoGroupId());
        
        empId=empService.getEmployees(employeeId);
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        quesBankGeneralInfo.setHcmoEmployeeId(empId);
        departmentName=(Integer) session.get("DEPARTMENTNAME");
        projectName=(Integer) session.get("PROJECTNAME");
        teamName=(Integer) session.get("TEAMNAME");
        jobTitleName=(Integer) session.get("JOBTITLENAME");
        startDate=(Date) session.get("STARTDATE");
        endDate=(Date) session.get("ENDDATE");
        
        dept=deptService.getDepartment(departmentName);
        team=teamService.getTeam(teamName);
        jobTitle=jobTitleService.getJobTitle(jobTitleName);
        questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(groupName);
        quesBankGeneralInfo.setHcmoQuestionGeneralInfoGroup(quesGeneralInfoGroup);
        quesBankGeneralInfo.setHcmoDepartmentId(dept);
        quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
        quesBankGeneralInfo.setHcmoTeamId(team);
        quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
        quesBankGeneralInfo.setEmployeeType("Employee");
        quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
        quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
        quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
        quesBankGeneralInfo.setCreatedBy(oEmp);
        quesBankGeneralInfo.setUpdatedBy(oEmp);
        quesBankGeneralInfo.setIsActive(1);
        quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
        
        for(int i=0;i<selectedAppEmployeeList.size();i++){
            String empEmailId=(String) selectedAppEmployeeList.get(i);
            EmployeesVO appEmpId=quesBankService.getEmployeeId(empEmailId);
//            if(peerEmpId==empId){
//                addActionError("Please choose some other Approving Employee");
//                emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
//                HttpServletRequest request = ServletActionContext.getRequest();
//                request.setAttribute("emplList", emplList);
//                approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
//                assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
//                addActionError("Employee and Approving Employee are not same");
//                return INPUT;
//            }
            quesBankGeneralInfo=quesBankGeneralInfoService.getAllAssignedAppEmployee(startDate, endDate, employeeId,appEmpId.getEmployeeId());
            if(quesBankGeneralInfo!=null){
                emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("emplList", emplList);
                approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
                addActionError("Please choose different Approving Employee Name these employee already assigned");
                return INPUT;
            }
            quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
//            quesBankGeneralInfo.setPeerEmployeeId(peerEmpId);
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            
            dept=deptService.getDepartment(departmentName);
            team=teamService.getTeam(teamName);
            jobTitle=jobTitleService.getJobTitle(jobTitleName);
            questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(approvingEmpGroupName);
//            quesBankGeneralInfo.setHcmoQuestionGeneralInfoGroup(quesGeneralInfoGroup);
//            quesBankGeneralInfo.setPeerEmployeeId(appEmpId);
//            quesBankGeneralInfo.setHcmoEmployeeId(empId);
//            quesBankGeneralInfo.setHcmoDepartmentId(dept);
//            quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
//            quesBankGeneralInfo.setHcmoTeamId(team);
//            quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
//            quesBankGeneralInfo.setEmployeeType("Approving Employee");
//            quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
//            quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
//            quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
//            quesBankGeneralInfo.setCreatedBy(oEmp);
//            quesBankGeneralInfo.setUpdatedBy(oEmp);
//            quesBankGeneralInfo.setIsActive(1);
//            quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
       
        for(int j=0;j<peerEmployeeList.size();j++){
            empEmailId=(String) peerEmployeeList.get(j);
            EmployeesVO peerEmpId=quesBankService.getEmployeeId(empEmailId);
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            groupName=(Integer) session.get("GROUPNAME");
            
            dept=deptService.getDepartment(departmentName);
            team=teamService.getTeam(teamName);
            jobTitle=jobTitleService.getJobTitle(jobTitleName);
            
            quesBankGeneralInfo.setPeerEmployeeId(appEmpId);
            quesBankGeneralInfo.setHcmoQuestionGeneralInfoGroup(quesGeneralInfoGroup);
            quesBankGeneralInfo.setHcmoEmployeeId(empId);
            quesBankGeneralInfo.setHcmoDepartmentId(dept);
            quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
            quesBankGeneralInfo.setHcmoTeamId(team);
            quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
            quesBankGeneralInfo.setEmployeeType("Approving Employee");
            quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
            quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
            quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
            quesBankGeneralInfo.setCreatedBy(oEmp);
            quesBankGeneralInfo.setUpdatedBy(oEmp);
            quesBankGeneralInfo.setIsActive(1);
            quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
            
            questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(peerEmpGroupName);
            quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
            quesBankGeneralInfo.setPeerEmployeeId(peerEmpId);
            quesBankGeneralInfo.setHcmoQuestionGeneralInfoGroup(quesGeneralInfoGroup);
            quesBankGeneralInfo.setHcmoEmployeeId(empId);
            quesBankGeneralInfo.setHcmoDepartmentId(dept);
            quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
            quesBankGeneralInfo.setHcmoTeamId(team);
            quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
            quesBankGeneralInfo.setEmployeeType("Peer Employee");
            quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
            quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
            quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
            quesBankGeneralInfo.setCreatedBy(oEmp);
            quesBankGeneralInfo.setUpdatedBy(oEmp);
            quesBankGeneralInfo.setIsActive(1);
            quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
        }
        }
        
        }
//        questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(groupName);
//        quesBankGeneralInfo.setHcmoEmployeeId(empId);
//        quesBankGeneralInfo.setHcmoQuestionGeneralInfoGroup(quesGeneralInfoGroup);
//        quesBankGeneralInfo.setHcmoDepartmentId(dept);
//        quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
//        quesBankGeneralInfo.setHcmoTeamId(team);
//        quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
//        quesBankGeneralInfo.setEmployeeType("Employee");
//        quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
//        quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
//        quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
//        quesBankGeneralInfo.setCreatedBy(oEmp);
//        quesBankGeneralInfo.setUpdatedBy(oEmp);
//        quesBankGeneralInfo.setIsActive(1);
//        quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
        
//        quesGeneralInfoGroup.setName(name);
//        quesGeneralInfoGroup.setCreated(DateUtils.getCurrentDateTime());
//        quesGeneralInfoGroup.setCreatedBy(oEmp);
//        quesGeneralInfoGroup.setUpdatedBy(oEmp);
//        quesGeneralInfoGroup.setIsActive(1);
//        quesGeneralInfoGroupService.insertQuestioinBankGeneralInfoGroup(quesGeneralInfoGroup);
        addActionMessage("Assigned Questions to Employee Successfully");
        return SUCCESS;
        
    }
    
    @SkipValidation
    public String getGeneralInfo(){
        Map session = ActionContext.getContext().getSession();
        if(departmentName==null){
            addFieldError("departmentName","Please choose Department name");
            return INPUT; 
        }
        if(teamName==null){
            addFieldError("teamName","Please choose Team name");
            return INPUT; 
        }
        if(jobTitleName==null){
            addFieldError("jobTitleName","Please choose Job Title name");
            return INPUT; 
        }
        if(employeeType==null){
            addFieldError("employeeType","Please choose Employee Type");
            return INPUT; 
        }
        if(projectName==null){
            addFieldError("projectName","Please choose Project Name");
            return INPUT; 
        }
        if(startDate==null){
            addFieldError("startDate","Please choose Start Date");
            return INPUT; 
        }
        if(endDate==null){
            addFieldError("endDate","Please choose End Date name");
            return INPUT; 
        }
        session.put("PROJECTNAME",projectName);
        session.put("DEPARTMENTNAME",departmentName);
        session.put("TEAMNAME",teamName);
        session.put("JOBTITLENAME",jobTitleName);
        session.put("EMPLOYEETYPE",employeeType);
        session.put("STARTDATE",startDate);
        session.put("ENDDATE",endDate);
        session.put("GROUPNAME",groupName);
           
        return SUCCESS;
        
    }
    
    @SkipValidation
    public String getEmployeeListType(){
        Map session = ActionContext.getContext().getSession();
        projectName=(Integer) session.get("PROJECTNAME");
        departmentName=(Integer) session.get("DEPARTMENTNAME");
        teamName=(Integer) session.get("TEAMNAME");
        jobTitleName=(Integer) session.get("JOBTITLENAME");
        employeeType=(String) session.get("EMPLOYEETYPE");
        startDate=(Date) session.get("STARTDATE");
        endDate=(Date) session.get("ENDDATE");
        groupName=(Integer) session.get("GROUPNAME");
        emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate);
        approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
        assignedEmployeeList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("emplList", emplList);
//        employeeList= session.get(employeeList);
//        if(employeeType.equals("Employee")){
//            emplList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate,employeeType);
//            return "EMPLOYEE";
//        }else if(employeeType.equals("Approving Employee")){
//            HttpServletRequest request = ServletActionContext.getRequest();
//            emplList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName, projectName,startDate, endDate,employeeType);
//            approvingEmployeeList=quesBankGeneralInfoService.getAllApprovingEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate,employeeType);
//            return "APPROVINGEMPLOYEE";
//        }else if(employeeType.equals("Peer Employee")){
//            HttpServletRequest request = ServletActionContext.getRequest();
//            assignedEmployeeList=quesBankGeneralInfoService.getAllEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate,employeeType);
//            emplList=quesBankGeneralInfoService.getAllPeerEmployeeId(departmentName, teamName, jobTitleName,projectName, startDate, endDate,employeeType);
//            request.setAttribute("emplList", emplList);
//            return "PEEREMPLOYEE";
//        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String setUpAssignEmployeeQuestions(){
        Map session = ActionContext.getContext().getSession();
        session.remove("DEPARTMENTNAME");
        session.remove("TEAMNAME");
        session.remove("JOBTITLENAME");
        session.remove("EMPLOYEETYPE");
        session.remove("STARTDATE");
        session.remove("ENDDATE");
        session.remove("GROUPNAME");
        return SUCCESS;
    }
    
    
    
    @SkipValidation
    public String questionBankGeneralInfoApprovingEmployeeList(){
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        for(int i=0;i<selectedAppEmployeeList.size();i++){
            String empEmailId=(String) selectedAppEmployeeList.get(i);
            EmployeesVO peerEmpId=quesBankService.getEmployeeId(empEmailId);
            quesBankGeneralInfo.setPeerEmployeeId(peerEmpId);
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            employeeType=(String) session.get("EMPLOYEETYPE");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            groupName=(Integer) session.get("GROUPNAME");
            
            dept=deptService.getDepartment(departmentName);
            team=teamService.getTeam(teamName);
            jobTitle=jobTitleService.getJobTitle(jobTitleName);
            questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(groupName);
            EmployeesVO empId=empService.getEmployees(employeeFullName);
            quesBankGeneralInfo.setHcmoEmployeeId(empId);
            quesBankGeneralInfo.setHcmoDepartmentId(dept);
            quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
            quesBankGeneralInfo.setHcmoTeamId(team);
            quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
            quesBankGeneralInfo.setEmployeeType(employeeType);
            quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
            quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
            quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
            quesBankGeneralInfo.setCreatedBy(oEmp);
            quesBankGeneralInfo.setUpdatedBy(oEmp);
            quesBankGeneralInfo.setIsActive(1);
            quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
        }
        addActionMessage("Assigned Questions to Approving Employee Successfully");
        return SUCCESS;
        
    }
    
    @SkipValidation
    public String questionBankGeneralInfoPeerEmployeeList(){
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        quesBankGeneralInfo=new QuestionBankGeneralInfoVO();
        for(int i=0;i<peerEmployeeList.size();i++){
            String empEmailId=(String) peerEmployeeList.get(i);
            EmployeesVO peerEmpId=quesBankService.getEmployeeId(empEmailId);
            quesBankGeneralInfo.setPeerEmployeeId(peerEmpId);
            departmentName=(Integer) session.get("DEPARTMENTNAME");
            
            teamName=(Integer) session.get("TEAMNAME");
            jobTitleName=(Integer) session.get("JOBTITLENAME");
            employeeType=(String) session.get("EMPLOYEETYPE");
            startDate=(Date) session.get("STARTDATE");
            endDate=(Date) session.get("ENDDATE");
            groupName=(Integer) session.get("GROUPNAME");
            
            dept=deptService.getDepartment(departmentName);
            team=teamService.getTeam(teamName);
            jobTitle=jobTitleService.getJobTitle(jobTitleName);
            questGroupIdentifi=quesBankService.getQuestionGroupNameIdentificationVO(groupName);
            EmployeesVO empId=empService.getEmployees(employeeFullName);
            quesBankGeneralInfo.setHcmoEmployeeId(empId);
            quesBankGeneralInfo.setHcmoDepartmentId(dept);
            quesBankGeneralInfo.setHcmoQuestionGroupNameIdentificationId(questGroupIdentifi);
            quesBankGeneralInfo.setHcmoTeamId(team);
            quesBankGeneralInfo.setHcmoJobTitleId(jobTitle);
            quesBankGeneralInfo.setEmployeeType(employeeType);
            quesBankGeneralInfo.setPerformanceIndStartDate(startDate);
            quesBankGeneralInfo.setPerformanceIndEndDate(endDate);
            quesBankGeneralInfo.setCreated(DateUtils.getCurrentDateTime());
            quesBankGeneralInfo.setCreatedBy(oEmp);
            quesBankGeneralInfo.setUpdatedBy(oEmp);
            quesBankGeneralInfo.setIsActive(1);
            quesBankGeneralInfoService.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
            addActionMessage("Assigned Questions to Peer Employee Successfully");
        }
        return SUCCESS;
        
    }
    
    @SkipValidation
    public String updateGeneralInfoStatus(){
        Map session = ActionContext.getContext().getSession();
        quesBankGeneralInfo=quesBankGeneralInfoService.getQuestionBankGeneralInfo(quesBankGeneralInfo.getHcmoQuestionGeneralInfoId());
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        if(quesBankGeneralInfo.getEmployeeType().equals("Employee")){
            quesBankList=quesBankService.getAllQuestionBank(questionGroupIdentifiId);
            answerList=ansService.getAllAnswers(questionGroupIdentifiId,quesBankGeneralInfo.getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId(),oEmp.getEmployeeId(),oEmp.getEmployeeId());
            if(quesBankList.size()==answerList.size()){
                quesBankGeneralInfo.setUpdatedBy(oEmp);
                quesBankGeneralInfoService.updateGeneralInfoStatus(quesBankGeneralInfo);
                
            // Messaging
        	//Getting admin RoleID
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            EmployeesVO empIdObj;
            String sSubject = getText("message.subject.performancekpiMyReview.add");
            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
            
            for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
            	empIdObj = it.next();
            	int loggedInEmpId =oEmp.getEmployeeId(); 
            	int adminEmpId = empIdObj.getEmployeeId();

            	if(loggedInEmpId != adminEmpId){
            		mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiMyReview.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
            	}
            }
            
            mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiMyReview.add.loggedInEmp"), sSubject);
                
            }else{
                addActionMessage("Please complete the Answers and then click complete button");
            }
            return SUCCESS;
        }else if(quesBankGeneralInfo.getEmployeeType().equals("Peer Employee")){
            quesBankList=quesBankService.getAllQuestionBank(questionGroupIdentifiId);
            answerList=ansService.getAllPeerAnswers(questionGroupIdentifiId,quesBankGeneralInfo.getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId(),subEmployeeID);
            if(quesBankList.size()==answerList.size()){
                quesBankGeneralInfo.setUpdatedBy(oEmp);
                quesBankGeneralInfoService.updateGeneralInfoStatus(quesBankGeneralInfo);
                session.put("employeeId", subEmployeeID);
                
             // Messaging
            	//Getting admin RoleID
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                EmployeesVO empIdObj;
                String sSubject = getText("message.subject.performancekpiMyReview.add");
                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
                
                for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
                	empIdObj = it.next();
                	int loggedInEmpId =oEmp.getEmployeeId(); 
                	int adminEmpId = empIdObj.getEmployeeId();

                	if(loggedInEmpId != adminEmpId){
                		mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiMyReview.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                	}
                }
                
                mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiMyReview.add.loggedInEmp"), sSubject);
                
            }else{
                addActionMessage("Please complete the Answers and then click complete button");
            }
            return "peeremployee";
        }
        session.put("COMPLETED","COMPLETED");
        return SUCCESS;
    }
    
    
    @SkipValidation
    public String updateGeneralInfoApproverCommentsStatus(){
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        quesBankGeneralInfo=quesBankGeneralInfoService.getQuestionBankGeneralInfo(quesBankGeneralInfo.getHcmoQuestionGeneralInfoId());
        
        if(quesBankGeneralInfo.getEmployeeType().equals("Approving Employee")){
            quesBankList=quesBankService.getAllQuestionBank(questionGroupIdentifiId);
//            answerList=ansService.getAllAnswers(questionGroupIdentifiId,quesBankGeneralInfo.getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId());
//            if(quesBankList.size()==answerList.size()){
                session.put("subEmployeeID", employeeId);
                quesBankGeneralInfo.setUpdatedBy(oEmp);
                quesBankGeneralInfo.setHcmoApprovingEmpId(oEmp);
                quesBankGeneralInfo.setApprovingEmpComments(approverComments);
                quesBankGeneralInfoService.updateGeneralInfoaApproverComments(quesBankGeneralInfo);
//            }else{
                addActionMessage("Comments Added Successfully");
//            }
        }
        session.put("COMPLETED","COMPLETED");
        return SUCCESS;
    }
    
    
    @SkipValidation
    public String adminPerformance(){
        Map session = ActionContext.getContext().getSession();
       
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAdminEmployeeQuesGeneralInfoList(employeeId);
        quesBankAppEmployeeList=quesBankGeneralInfoService.getAdminApprovingEmployeeQuesGeneralInfoList(employeeId);
        if(!quesBankAppEmployeeList.isEmpty()){
            session.put("ADMIN_APPROVINGEMPLOYEE","APPROVINGEMPLOYEE");
        }
        quesBankPeerEmployeeList=quesBankGeneralInfoService.getAdminPeerEmployeeQuesGeneralInfoList(employeeId);
        if(!quesBankPeerEmployeeList.isEmpty()){
            session.put("ADMIN_PEEREMPLOYEE","PEEREMPLOYEE");
        }
       
        return SUCCESS;
    }
    
    @SkipValidation
    public String employeePerformanceTab(){
        Map session = ActionContext.getContext().getSession();
        if(session.get("admin_Employee_id")!=null){
            employeeId=(Integer) session.get("admin_Employee_id");
        }
        if(session.get("sub_employeeId")!=null){
            employeeId=(Integer) session.get("sub_employeeId");
        }
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAdminEmployeeQuesGeneralInfoList(employeeId);
        session.remove("admin_Employee_id");
        if(session.get("admin_Employee_id")!=null){
            session.remove("admin_Employee_id");
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String appEmployeePerformanceTab(){
        Map session = ActionContext.getContext().getSession();
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAdminApprovingEmployeeQuesGeneralInfoList(employeeId);
        if(!quesBankGeneralInfoList.isEmpty()){
            session.put("ADMIN_APPROVINGEMPLOYEE","APPROVINGEMPLOYEE");
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String peerEmployeePerformanceTab(){
        Map session = ActionContext.getContext().getSession();
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAdminPeerEmployeeQuesGeneralInfoList(employeeId);
        if(!quesBankGeneralInfoList.isEmpty()){
            session.put("ADMIN_PEEREMPLOYEE","PEEREMPLOYEE");
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String getAdminCommentsForm(){
        Map session = ActionContext.getContext().getSession();
        quesBankList=quesBankService.getAllQuestionBank(questionGroupIdentifiId);
        quesBankGeneralInfo=quesBankGeneralInfoService.getQuestionBankGeneralInfo(questionGeneralInfoId);
        answerList=ansService.getAllAnswers(questionGroupIdentifiId,quesBankGeneralInfo.getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId(),subEmployeeID,subEmployeeID);
        if(quesBankList.size()!=answerList.size()){
            addActionMessage("Employee didnt complete Answer");
            session.put("sub_employeeId",subEmployeeID);
            return INPUT;
        }else if(quesBankGeneralInfo.getAdminComments()!=null){
            addActionMessage("Admin Already entered Comment to this employee");
            session.put("sub_employeeId",subEmployeeID);
            return INPUT; 
        }else{
            return SUCCESS;
        }
//        if(quesBankGeneralInfo.getAdminComments()!=null){
//            return SUCCESS; 
//        }else{
//            addActionMessage("Admin Already entered Comment to this employee");
//            session.put("sub_employeeId",subEmployeeID);
//            return INPUT;
//        }
    }
    
    @SkipValidation
    public String updateAdminComments(){
        Map session = ActionContext.getContext().getSession();
        quesBankGeneralInfo=quesBankGeneralInfoService.getQuestionBankGeneralInfo(questionGeneralInfoId);
        session.put("admin_Employee_id",quesBankGeneralInfo.getHcmoEmployeeId().getEmployeeId());
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        quesBankGeneralInfo.setUpdatedBy(oEmp);
        quesBankGeneralInfo.setHcmoAdminId(oEmp);
        quesBankGeneralInfo.setAdminComments(adminComments);
        quesBankGeneralInfoService.updateAdminEmployeeComments(quesBankGeneralInfo);
        
     // Messaging
        //Getting admin RoleID
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        EmployeesVO empIdObj;
        String sSubject = getText("message.subject.performancekpiMyReview.add");
        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminList = roleService.getAllAdmin(role.getHcmoRoleId());
        
        for (Iterator<EmployeesVO> it = adminList.iterator(); it.hasNext();) {
        	empIdObj = it.next();
        	int loggedInEmpId =oEmp.getEmployeeId(); 
        	int adminEmpId = empIdObj.getEmployeeId();

        	if(loggedInEmpId != adminEmpId){
        		mailToAdmin(empIdObj.getEmployeeId(), empIdObj.getEmpFirstName(), getText("performancekpiAdminComments.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
        	}
        }
        
        mailToAddedPerson(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("performancekpiAdminComments.add.loggedInEmp"), sSubject);
        
        return SUCCESS;
    }
    
    @SkipValidation
    public String approverCommentsForm(){
        return SUCCESS;
    }
    
    @SkipValidation
    public String viewAssignedQuestionsList(){
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAllQuestionGeneralInfoGroupId();
        return SUCCESS;
    }
    
    @SkipValidation
    public String viewAssignedQuestionGeneralInfoGroup(){
        System.out.println("questionGeneralInfoGroupIdListId:"+questionGeneralInfoGroupIdListId);
        quesBankGeneralInfoList=quesBankGeneralInfoService.getAllQuestionBankGeneralInfo(questionGeneralInfoGroupIdListId);
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
    
    public QuestionBankGeneralInfoVO getQuesBankGeneralInfo() {
        return quesBankGeneralInfo;
    }

    public void setQuesBankGeneralInfo(QuestionBankGeneralInfoVO quesBankGeneralInfo) {
        this.quesBankGeneralInfo = quesBankGeneralInfo;
    }

    public List<QuestionBankGeneralInfoVO> getQuesBankGeneralInfoList() {
        return quesBankGeneralInfoList;
    }

    public void setQuesBankGeneralInfoList(List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList) {
        this.quesBankGeneralInfoList = quesBankGeneralInfoList;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Integer departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getTeamName() {
        return teamName;
    }

    public void setTeamName(Integer teamName) {
        this.teamName = teamName;
    }

    public Integer getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(Integer jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public DepartmentVO getDept() {
        return dept;
    }

    public void setDept(DepartmentVO dept) {
        this.dept = dept;
    }

    public TeamVO getTeam() {
        return team;
    }

    public void setTeam(TeamVO team) {
        this.team = team;
    }

    public JobTitleVO getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleVO jobTitle) {
        this.jobTitle = jobTitle;
    }

    public QuestionGroupNameIdentificationVO getQuestGroupIdentifi() {
        return questGroupIdentifi;
    }

    public void setQuestGroupIdentifi(QuestionGroupNameIdentificationVO questGroupIdentifi) {
        this.questGroupIdentifi = questGroupIdentifi;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List employeeList) {
        this.employeeList = employeeList;
    }

    public List getAssignedEmployeeList() {
        return assignedEmployeeList;
    }

    public void setAssignedEmployeeList(List assignedEmployeeList) {
        this.assignedEmployeeList = assignedEmployeeList;
    }

    public Integer getGroupName() {
        return groupName;
    }

    public void setGroupName(Integer groupName) {
        this.groupName = groupName;
    }

    public List getPeerEmployeeList() {
        return peerEmployeeList;
    }

    public void setPeerEmployeeList(List peerEmployeeList) {
        this.peerEmployeeList = peerEmployeeList;
    }

    public List getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public Integer getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(Integer employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public List<EmployeesVO> getEmplList() {
        return emplList;
    }

    public void setEmplList(List<EmployeesVO> emplList) {
        this.emplList = emplList;
    }

    public List<EmployeesVO> getApprovingEmployeeList() {
        return approvingEmployeeList;
    }

    public void setApprovingEmployeeList(List<EmployeesVO> approvingEmployeeList) {
        this.approvingEmployeeList = approvingEmployeeList;
    }

    public List getSelectedAppEmployeeList() {
        return selectedAppEmployeeList;
    }

    public void setSelectedAppEmployeeList(List selectedAppEmployeeList) {
        this.selectedAppEmployeeList = selectedAppEmployeeList;
    }

    public Integer getProjectName() {
        return projectName;
    }

    public void setProjectName(Integer projectName) {
        this.projectName = projectName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionGeneralInfoGroupVO getQuesGeneralInfoGroup() {
        return quesGeneralInfoGroup;
    }

    public void setQuesGeneralInfoGroup(QuestionGeneralInfoGroupVO quesGeneralInfoGroup) {
        this.quesGeneralInfoGroup = quesGeneralInfoGroup;
    }

    public Integer getApprovingEmpGroupName() {
        return approvingEmpGroupName;
    }

    public void setApprovingEmpGroupName(Integer approvingEmpGroupName) {
        this.approvingEmpGroupName = approvingEmpGroupName;
    }

    public Integer getPeerEmpGroupName() {
        return peerEmpGroupName;
    }

    public void setPeerEmpGroupName(Integer peerEmpGroupName) {
        this.peerEmpGroupName = peerEmpGroupName;
    }

    public List<QuestionGeneralInfoGroupVO> getQuesGeneralInfoGroupList() {
        return quesGeneralInfoGroupList;
    }

    public void setQuesGeneralInfoGroupList(List<QuestionGeneralInfoGroupVO> quesGeneralInfoGroupList) {
        this.quesGeneralInfoGroupList = quesGeneralInfoGroupList;
    }

    public Integer getQuestionGroupIdentifiId() {
        return questionGroupIdentifiId;
    }

    public void setQuestionGroupIdentifiId(Integer questionGroupIdentifiId) {
        this.questionGroupIdentifiId = questionGroupIdentifiId;
    }

    public Integer getSubEmployeeID() {
        return subEmployeeID;
    }

    public void setSubEmployeeID(Integer subEmployeeID) {
        this.subEmployeeID = subEmployeeID;
    }

    public Integer getQuestionGeneralInfoGroupIdListId() {
        return questionGeneralInfoGroupIdListId;
    }

    public void setQuestionGeneralInfoGroupIdListId(Integer questionGeneralInfoGroupIdListId) {
        this.questionGeneralInfoGroupIdListId = questionGeneralInfoGroupIdListId;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public List<QuestionBankGeneralInfoVO> getQuesBankAppEmployeeList() {
        return quesBankAppEmployeeList;
    }

    public void setQuesBankAppEmployeeList(List<QuestionBankGeneralInfoVO> quesBankAppEmployeeList) {
        this.quesBankAppEmployeeList = quesBankAppEmployeeList;
    }

    public List<QuestionBankGeneralInfoVO> getQuesBankPeerEmployeeList() {
        return quesBankPeerEmployeeList;
    }

    public void setQuesBankPeerEmployeeList(List<QuestionBankGeneralInfoVO> quesBankPeerEmployeeList) {
        this.quesBankPeerEmployeeList = quesBankPeerEmployeeList;
    }

    public Integer getQuestionGeneralInfoId() {
        return questionGeneralInfoId;
    }

    public void setQuestionGeneralInfoId(Integer questionGeneralInfoId) {
        this.questionGeneralInfoId = questionGeneralInfoId;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

    public EmployeesVO getAppEmpId() {
        return appEmpId;
    }

    public void setAppEmpId(EmployeesVO appEmpId) {
        this.appEmpId = appEmpId;
    }

    public EmployeesVO getPeerEmpId() {
        return peerEmpId;
    }

    public void setPeerEmpId(EmployeesVO peerEmpId) {
        this.peerEmpId = peerEmpId;
    }

}

