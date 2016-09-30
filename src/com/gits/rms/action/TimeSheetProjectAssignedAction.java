
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetProjectAssignedAction extends ActionSupport {
    private static final long serialVersionUID = -5625012465802146302L;
    private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private SignatureService signatureService = new SignatureDaoService();
    private TargetsService targetService = new TargetsDaoService();
    private ProjectAssignEmpService projectAssignEmpService = new ProjectAssignEmpDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<ProjectAssignEmpVO> tsProjAssignedList;
    private List<EmployeesVO> employeeList;
    private ProjectAssignEmpVO tsProjAssigned;
    private List<EmployeesVO> empIdObj;
    private String tsProjAssignedStartDate = "";
    private String tsProjAssignedEndDate = "";
    private String tsProjAssignedModifiedDateMail = "";
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private List<EmployeesVO> pendingList = new ArrayList<EmployeesVO>();

    private List emptylist = new ArrayList();
    private List<ProjectActivityVO> projActivityList;
    private ProjectActivityVO projActivityObj;
    private String selectedActivity;
    private List<EmployeesVO> subEmpList = new ArrayList<EmployeesVO>();
    private int activity;
    private Set pendingEmpList;
    private String targetName="";
    private String targetTypeEmp="";
    private String targetModeEmp="";
    private List<String> selectedGoalList;
    private List<String> SelectedDoubleSelectTargetList;
    private EmployeesService emplService = new EmployeesDaoService();
    private ProjectActivityService proActivityService = new ProjectActivityDaoService();
    private List<ProjectAssignEmpVO> projAssignList;
    private ProjectAssignEmpVO projAssignObj;
    private List<Integer> assignProjId = new ArrayList<Integer>();

    // To get List of Timesheet Assigned Project for an Employee
    @SkipValidation
    public String getAllTsProjectAssigned() {
        tsProjAssignedList = tsProjAssService.getCurrentSubEmployeeListForTsProjectAssigned();
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // AssignProject link it shows blank Form to enter New Data
    @SkipValidation
    public String setUpTsProjectAssigned() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        int employeeId = oEmp.getEmployeeId();
        subEmpList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
        loadValues.getAllTargetsName();
        loadValues.getAllGoalName();
        if ((tsProjAssigned != null) && (tsProjAssigned.getProjectAssignEmpId() != null)) {
            tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
            loadValues.getProjectActivity(tsProjAssigned.getProjectName().getProjectId(), employeeId);
            activity = tsProjAssigned.getProjectActivityId().getProjectActivityId();
            if (tsProjAssigned.getProjTargetName() == null || tsProjAssigned.getProjTargetName().equals("") || tsProjAssigned.getProjTargetName().isEmpty()) {
            	loadValues.getEmptyTargetType();
			}else {
				loadValues.getTargetType(tsProjAssigned.getProjTargetName());
			}
            if (tsProjAssigned.getProjGoalName() == null || tsProjAssigned.getProjGoalName().equals("") || tsProjAssigned.getProjGoalName().isEmpty()) {
			}else {
				String[] goalName = tsProjAssigned.getProjGoalName().split(",");
				selectedGoalList = new ArrayList<String>();
				selectedGoalList = Arrays.asList(goalName);
				List<String> goalNameList = targetService.getAllGoalName();
				for (int g = 0; g < selectedGoalList.size(); g++) {
					if (goalNameList.contains(selectedGoalList.get(g))) {
						goalNameList.remove(selectedGoalList.get(g));
					}
				}
				loadValues.setGoalList(goalNameList);
			}
        }else {
        	loadValues.getEmptyTargetType();
		}
        return SUCCESS;
    }

    // To insert new AssignProject detail or Edit Particular AssignProject
    public String insertOrUpdateTsProjectAssigned() {
    	try {
	        // project Activity has empty list then it shows error
	        String testActivityId = Integer.toString(activity);
	        if (testActivityId.equals("0")) {
	            addActionError(getText("errors.messages.projActivity.projectActivity"));
	            return INPUT;
	        } else {
	            if (!(validationSDSuccessful())) {
	                return INPUT;
	            } else {
	                if (tsProjAssigned.getProjectAssignEmpId() == null) {
	                	
	                	if(tsProjAssigned.getEmpIdObjList().isEmpty()){
	                    	addActionError(getText("Please Select Min of One Employee"));
	                        return INPUT;
	                    }
	                	
	                    Map session = ActionContext.getContext().getSession();
	                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	                    ProjectActivityVO activityVO = new ProjectActivityVO();
	                    activityVO.setProjectActivityId(activity);
	                    activityVO = proActivityService.getProjectActivity(activityVO.getProjectActivityId());
	                    
	                    List<EmployeesVO> empIdList =  new LinkedList<EmployeesVO>();
	                    empIdList = tsProjAssigned.getEmpIdObjList();
	                    EmployeesVO empObj = new EmployeesVO();
	                    Integer totalAllocatHours = 0;
	                    
	                    for (Iterator<EmployeesVO> it = empIdList.iterator(); it.hasNext();) {
	                    	empObj = it.next();
	                    	empObj = emplService.getEmployeeByEmailId(empObj.getEmpWorkEmail());
	                    	String allocatedHour=ServletActionContext.getRequest().getParameter(empObj.getEmpWorkEmail());
	                    	
	                    	if(!(allocatedHour.trim().isEmpty())){
	                    		totalAllocatHours += Integer.valueOf(allocatedHour);
	                    	}
	                    	
	                    }
	                    
	                    projAssignList = projectAssignEmpService.getProjAssignListByProjActivityAndProj(tsProjAssigned.getProjectName().getProjectId(), activityVO.getProjectActivityId());
	                    
	                    if(!(projAssignList.isEmpty())){
	                    	
	                    	for (Iterator<ProjectAssignEmpVO> itee = projAssignList.iterator(); itee.hasNext();) {
	                    		projAssignObj = itee.next();
	                    		
	                    		if(projAssignObj.getAllocatedHours() !=null){
	                    			Integer assignHours = Integer.valueOf(projAssignObj.getAllocatedHours());
		                    		totalAllocatHours += assignHours;
	                    		}
	                    	}
	                    }
	                    
	                    //have to get the project activity estimated hours
	                    projActivityObj = proActivityService.getProjectActivity(activityVO.getProjectActivityId());
	                    if(!(projActivityObj.getEstimatedHours().trim().isEmpty())){
	                    	
	                    	Integer activityEstHours = Integer.valueOf(projActivityObj.getEstimatedHours());
	                    	if(activityEstHours < totalAllocatHours){
	                    		addActionError(getText("The Allocated Hours Exists the Over All Project Activity Hours"));
		                        return INPUT;
	                    	}
	                    }
	                    
	                    
	                    
	                    for (Iterator<EmployeesVO> it = empIdList.iterator(); it.hasNext();) {
	                    	empObj = it.next();
	                    	empObj = emplService.getEmployeeByEmailId(empObj.getEmpWorkEmail());
	                        String allocatedHour=ServletActionContext.getRequest().getParameter(empObj.getEmpWorkEmail());
	                        String goalName;
	                        String targetName;
	                        
	                        if (selectedGoalList == null || selectedGoalList.size() == 0) {
	                        	goalName = "";
	    					}else {
	    						goalName = Goal_List_To_String(selectedGoalList);
	    					}
	                        
	                        if (SelectedDoubleSelectTargetList == null || SelectedDoubleSelectTargetList.size() == 0) {
	                        	targetName = "";
	    					}else {
	    						targetName = Goal_List_To_String(SelectedDoubleSelectTargetList);
	    					}
	                        
	                        
	                        if(!(allocatedHour.trim().isEmpty())){
	                        	if(activityVO.getEstimatedHours() == null){
			                    	addActionError(getText("Selected Activity Dont Have Estimated Hour"));
			                        return INPUT;
			                    }
	                        }
	                        
	                        if(allocatedHour.trim().isEmpty()){
	                        	allocatedHour = null;
	                        }
	                        
	                        tsProjAssigned.setProjTargetName(targetName);
	                        tsProjAssigned.setAllocatedHours(allocatedHour);
	                        tsProjAssigned.setEmployeeName(empObj);
	                        tsProjAssigned.setProjectActivityId(activityVO);
	                        tsProjAssigned.setProjGoalName(goalName);
	                        tsProjAssigned.setCreated(DateUtils.getCurrentDateTime());
	                        tsProjAssigned.setCreatedBy(oEmp);
	                        tsProjAssigned.setUpdatedBy(oEmp);
	                        tsProjAssigned.setIsActive(1);
	                        tsProjAssService.insertTsProjAss(tsProjAssigned);

	                        Integer assign = tsProjAssigned.getProjectAssignEmpId();
	                        assignProjId.add(assign);
	                        // TsProjectAssigned Add Mailer
	                        tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
	                        
	                        int sessionEmpId = oEmp.getEmployeeId();
	                        int employeeID = tsProjAssigned.getEmployeeName().getEmployeeId();
	                        tsProjAssigned.getProjectName().getEmpIdObj().getEmployeeId();
	                        String sSubject = getText("message.subject.timesheetAssignProject.add");
	                        tsProjAssigned.getProjectName().getEmpIdObj().getEmpFirstName();
	                        tsProjAssigned.getProjectName().getProjectName();
	
	                        if (sessionEmpId != employeeID) {
	                            // Mail to the logged in employee
	                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetAssignProject.loggedIn.add.addedBy"), tsProjAssigned.getEmployeeName().getEmpFirstName(), sSubject);
	
	                            // Mail to the employee
	                            mail(tsProjAssigned.getEmployeeName().getEmployeeId(), tsProjAssigned.getEmployeeName().getEmpFirstName(), getText("timesheetAssignProject.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
	
	                            // Mail to the Project Owner
	                            mailToProjectOwner(tsProjAssigned.getProjectName().getEmpIdObj().getEmployeeId(), tsProjAssigned.getProjectName().getEmpIdObj().getEmpFirstName(), getText("timesheetAssignProject.loggedIn.add.addToProjectOwner"), tsProjAssigned.getEmployeeName().getEmpFirstName(), oEmp.getEmpFirstName(), tsProjAssigned.getProjectName().getProjectName(), sSubject);
	
	                        }
	                    }
	                    
	                    addActionMessage(getText("Added Successfully"));
	                    
	                } else {
	                    Map session = ActionContext.getContext().getSession();
	                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	                    List<TimeSheetProjectAssignVO> timeProjEnteredObjList = new ArrayList();
	                    TimeSheetProjectAssignVO assignObj;
	                    Double tsAllocatedHours = 0d;
	                    ProjectActivityVO activityVO = new ProjectActivityVO();
	                    activityVO.setProjectActivityId(activity);
	                    
	                    activityVO = proActivityService.getProjectActivity(activityVO.getProjectActivityId());
	                    
	                    if(!(tsProjAssigned.getAllocatedHours().trim().isEmpty())){
                        	if(activityVO.getEstimatedHours() == null){
		                    	addActionError(getText("Selected Activity Dont Have Estimated Hour"));
		                        return INPUT;
		                    }
                        }
	                    
	                    tsProjAssigned.setProjectActivityId(activityVO);
	                    System.out.println("tsProjAssigned.getProjectActivityId().getProjectActivityId() :"+tsProjAssigned.getProjectActivityId().getProjectActivityId());
	                    System.out.println("tsProjAssigned.getProjectName().getProjectId() :"+tsProjAssigned.getProjectName().getProjectId());

	                    timeProjEnteredObjList = tsProjAssService.checkAssignedProjInTimesheet(tsProjAssigned);
	                    if (!(timeProjEnteredObjList.isEmpty())) {
	                    	
	                    	for(Iterator<TimeSheetProjectAssignVO> itProjAssignVO = timeProjEnteredObjList.iterator(); itProjAssignVO.hasNext();){
	                    		assignObj = itProjAssignVO.next();
	                    		tsAllocatedHours += assignObj.getEnterTime();
	                    	}
	                    	
	                    	if(!(tsProjAssigned.getAllocatedHours().trim().isEmpty())){
	                    		Double allocatedHrs = Double.valueOf(tsProjAssigned.getAllocatedHours());
	                    		if(tsAllocatedHours > allocatedHrs){
	                    			addActionError(getText("Allocated Hours Is Lesser Than Entered Timesheet Hours"));
			                        return INPUT;
		                    	}
	                    	}
	                    }
	                    
	                    String goalName;
	                    if (selectedGoalList == null || selectedGoalList.size() == 0) {
	                    	goalName = "";
						}else {
							goalName = Goal_List_To_String(selectedGoalList);
						}
	                    
	                    tsProjAssigned.setProjectActivityId(activityVO);
	                    tsProjAssigned.setProjGoalName(goalName);
	                    tsProjAssigned.setUpdatedBy(oEmp);
	                    tsProjAssService.updateTsProjAss(tsProjAssigned);
	                    
	                    Integer assign = tsProjAssigned.getProjectAssignEmpId();
                        assignProjId.add(assign);
                        
	                    // TsProjectAssigned Update Mailer
	                    tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
	                    int sessionEmpId = oEmp.getEmployeeId();
	                    int employeeID = tsProjAssigned.getEmployeeName().getEmployeeId();
	                    String sSubject = getText("message.subject.timesheetAssignProject.edit");
	
	                    if (sessionEmpId != employeeID) {
	                        // Mail to the logged in employee
	                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetAssignProject.loggedIn.edit.updatedBy"), tsProjAssigned.getEmployeeName().getEmpFirstName(), sSubject);
	                        // Mail to the employee
	                        mail(tsProjAssigned.getEmployeeName().getEmployeeId(), tsProjAssigned.getEmployeeName().getEmpFirstName(), getText("timesheetAssignProject.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
	                        // Mail to the Project Owner
	                        mailToProjectOwner(tsProjAssigned.getProjectName().getEmpIdObj().getEmployeeId(), tsProjAssigned.getProjectName().getEmpIdObj().getEmpFirstName(), getText("timesheetAssignProject.loggedIn.edit.updateToProjectOwner"), tsProjAssigned.getEmployeeName().getEmpFirstName(), oEmp.getEmpFirstName(), tsProjAssigned.getProjectName().getProjectName(), sSubject);
	                    }
	                    addActionMessage(getText("Updated Successfully"));
	                }
	            }
	        }
    	} catch (Exception e) {
			e.printStackTrace();
		}
        loadValues.getAllSubEmployeeForTimeSheet();
        return SUCCESS;
    }

    // To delete Particular AssignProject Detail
    @SkipValidation
    public String deleteTsProjectAssigned() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        List<TimeSheetProjectAssignVO> timeProjEnteredObjList = new ArrayList();
        tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
        
        // To check whether the employee has entered the time for the assigned project.If Yes
        // then we are not allowing the Assigned Project to get delete.
        timeProjEnteredObjList = tsProjAssService.checkAssignedProjInTimesheet(tsProjAssigned);
        if (!(timeProjEnteredObjList.isEmpty())) {
            addActionError(getText("label.header.timeSheet.assignProject.msg.deleteAssignProject"));
            return SUCCESS;
        }

        tsProjAssigned.setUpdatedBy(oEmp);
        tsProjAssService.deleteTsProjAss(tsProjAssigned);

        // TsProjectAssigned delete Mailer
        tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = tsProjAssigned.getEmployeeName().getEmployeeId();
        String sSubject = getText("message.subject.timesheetAssignProject.delete");

        if (sessionEmpId != employeeID) {
            // Mail to the logged in employee
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("timesheetAssignProject.loggedIn.delete.deletedBy"), tsProjAssigned.getEmployeeName().getEmpFirstName(), sSubject);

            // Mail to the employee
            mail(tsProjAssigned.getEmployeeName().getEmployeeId(), tsProjAssigned.getEmployeeName().getEmpFirstName(), getText("timesheetAssignProject.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);

            // Mail to the Project Owner
            mailToProjectOwner(tsProjAssigned.getProjectName().getEmpIdObj().getEmployeeId(), tsProjAssigned.getProjectName().getEmpIdObj().getEmpFirstName(), getText("timesheetAssignProject.loggedIn.edit.deleteToProjectOwner"), tsProjAssigned.getEmployeeName().getEmpFirstName(), oEmp.getEmpFirstName(), tsProjAssigned.getProjectName().getProjectName(), sSubject);
        }
        addActionMessage(getText("Deleted Successfully"));
        loadValues.getAllSubEmployeeForTimeSheet();
        return SUCCESS;
    }

    // To get Particular AssignProject Data
    @SkipValidation
    public String tsProjectAssignedView() {
        if ((tsProjAssigned != null) && (tsProjAssigned.getProjectAssignEmpId() != null)) {
            tsProjAssigned = tsProjAssService.getTsProjAss(tsProjAssigned.getProjectAssignEmpId());
        }
        return SUCCESS;
    }

    // To View the AssignProject Search Form
    @SkipValidation
    public String tsProjectAssignSearchForm() {
        subEmpList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
        return SUCCESS;
    }

    @SkipValidation
    public String projectAssignedViewForm() {
        pendingEmpList = new TreeSet();
        subEmpList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
        for (int i = 0; i <= (subEmpList.size() - 1); i++) {
            pendingList = timeSheetProjectAssignService.getEmpPendingApprovalStatus(subEmpList.get(i).getEmployeeId());
            if (pendingList.size() > 0) {
                pendingEmpList.add(subEmpList.get(i).getEmpFirstName());
            }
        }
        loadValues.getAllSubEmployeeForTimeSheet();
        return SUCCESS;
    }

    // To Search Assigned Project
    @SkipValidation
    public String tsProjectAssignSearchResult() {
        tsProjAssignedList = tsProjAssService.tsProjAssSearchResult(tsProjAssigned);
        if (tsProjAssigned.getMessage() != null) {
            if (tsProjAssigned.getMessage().contains(getText("label.common.search.messageSetProAssignStartDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(tsProjAssigned.getProjectStartDate()) + "."));
            }
            if (tsProjAssigned.getMessage().contains(getText("label.common.search.messageSetProAssignEndDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(tsProjAssigned.getProjectEndDate()) + "."));
            }
        }
        return SUCCESS;
    }

    // Validation Method for TimesheetAssignProject StartDate
    private boolean validationSDSuccessful() {
        if (tsProjAssigned.getProjectEndDate().before(tsProjAssigned.getProjectStartDate())) {
			addActionError(getText("errors.common.endDate.before.startDate"));
            return false;
        }
        return true;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date tsProjAssignedStart = tsProjAssigned.getProjectStartDate();
            tsProjAssignedStartDate = dateformat.format(tsProjAssignedStart);
            Date tsProjAssignedEnd = tsProjAssigned.getProjectEndDate();
            tsProjAssignedEndDate = dateformat.format(tsProjAssignedEnd);
            Date tsProjAssignedModdate = tsProjAssigned.getUpdated();
            tsProjAssignedModifiedDateMail = updateddateformat.format(tsProjAssignedModdate);
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
                + getText("label.header.assignProject.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssigned.getEmployeeName().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.project.projectName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + tsProjAssigned.getProjectName().getProjectName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                
          if (tsProjAssigned.getProjTargetName() == null || tsProjAssigned.getProjTargetName().equals("") || tsProjAssigned.getProjTargetName().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjTargetType() == null || tsProjAssigned.getProjTargetType().equals("") || tsProjAssigned.getProjTargetType().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.type") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjTargetMode() == null || tsProjAssigned.getProjTargetMode().equals("") || tsProjAssigned.getProjTargetMode().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.mode") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetMode() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjGoalName() == null || tsProjAssigned.getProjGoalName().equals("") || tsProjAssigned.getProjGoalName().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.goal.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjGoalName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.startDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssignedStartDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.enddate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssignedEndDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + tsProjAssignedModifiedDateMail
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

    public void mailToProjectOwner(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String ProName, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date tsProjAssignedStart = tsProjAssigned.getProjectStartDate();
            tsProjAssignedStartDate = dateformat.format(tsProjAssignedStart);
            Date tsProjAssignedEnd = tsProjAssigned.getProjectEndDate();
            tsProjAssignedEndDate = dateformat.format(tsProjAssignedEnd);
            Date tsProjAssignedModdate = tsProjAssigned.getUpdated();
            tsProjAssignedModifiedDateMail = updateddateformat.format(tsProjAssignedModdate);

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            String sProjectName = Constants.PROJECT_NAME;
            StringBuilder sMessage = new StringBuilder();

            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));

            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);

            sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.replace(sMessage.indexOf(sProjectName), sMessage.indexOf(sProjectName)
                + sProjectName.length(), ProName);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.assignProject.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssigned.getEmployeeName().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.project.projectName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + tsProjAssigned.getProjectName().getProjectName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                
                if (tsProjAssigned.getProjTargetName() == null || tsProjAssigned.getProjTargetName().equals("") || tsProjAssigned.getProjTargetName().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjTargetType() == null || tsProjAssigned.getProjTargetType().equals("") || tsProjAssigned.getProjTargetType().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.type") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjTargetMode() == null || tsProjAssigned.getProjTargetMode().equals("") || tsProjAssigned.getProjTargetMode().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.targets.mode") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjTargetMode() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          if (tsProjAssigned.getProjGoalName() == null || tsProjAssigned.getProjGoalName().equals("") || tsProjAssigned.getProjGoalName().isEmpty()) {
			
          }else {
        	  sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                      + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                      + getText("label.header.goal.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                      + tsProjAssigned.getProjGoalName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                      + HtmlConstants.HTML_TABLE_ROW_END_TAG);
          }
          sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.startDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssignedStartDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.enddate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + tsProjAssignedEndDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + tsProjAssignedModifiedDateMail
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

    // To get List of Target Type for an Employee
    @SkipValidation
    public String getTargetType(){
    	try {
			loadValues.getTargetType(targetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    // To get Target Mode for an Employee
    @SkipValidation
    public String getTargetMode(){
    	try {
    		targetModeEmp = targetService.getTargetMode(targetName, targetTypeEmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    //  Convert Goal List To String
    private String Goal_List_To_String(List<String> goalList){
		String goalName = "";
		for (int i = 0; i < goalList.size(); i++) {
			goalName += goalList.get(i);
			if (i == goalList.size()-1) {
			}else {
				goalName +=",";
			}
		}
		return goalName;
	}
    
    public List<ProjectAssignEmpVO> getTsProjAssignedList() {
        return tsProjAssignedList;
    }

    public void setTsProjAssignedList(List<ProjectAssignEmpVO> tsProjAssignedList) {
        this.tsProjAssignedList = tsProjAssignedList;
    }

    public ProjectAssignEmpVO getTsProjAssigned() {
        return tsProjAssigned;
    }

    public void setTsProjAssigned(ProjectAssignEmpVO tsProjAssigned) {
        this.tsProjAssigned = tsProjAssigned;
    }

    public List<EmployeesVO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeesVO> employeeList) {
        this.employeeList = employeeList;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public Set getPendingEmpList() {
        return pendingEmpList;
    }

    public void setPendingEmpList(Set pendingEmpList) {
        this.pendingEmpList = pendingEmpList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public List getEmptylist() {
        return emptylist;
    }

    public void setEmptylist(List emptylist) {
        this.emptylist = emptylist;
    }

    public List<EmployeesVO> getSubEmpList() {
        return subEmpList;
    }

    public void setSubEmpList(List<EmployeesVO> subEmpList) {
        this.subEmpList = subEmpList;
    }

    public void setSelectedActivity(String selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public String getSelectedActivity() {
        return selectedActivity;
    }

    public void setProjActivityList(List<ProjectActivityVO> projActivityList) {
        this.projActivityList = projActivityList;
    }

    public List<ProjectActivityVO> getProjActivityList() {
        return projActivityList;
    }

	public void setActivity(int activity) {
		this.activity = activity;
	}

	public int getActivity() {
		return activity;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetTypeEmp(String targetTypeEmp) {
		this.targetTypeEmp = targetTypeEmp;
	}

	public String getTargetTypeEmp() {
		return targetTypeEmp;
	}

	public void setTargetModeEmp(String targetModeEmp) {
		this.targetModeEmp = targetModeEmp;
	}

	public String getTargetModeEmp() {
		return targetModeEmp;
	}

	public void setSelectedGoalList(List<String> selectedGoalList) {
		this.selectedGoalList = selectedGoalList;
	}

	public List<String> getSelectedGoalList() {
		return selectedGoalList;
	}

	public List<String> getSelectedDoubleSelectTargetList() {
		return SelectedDoubleSelectTargetList;
	}

	public void setSelectedDoubleSelectTargetList(
			List<String> selectedDoubleSelectTargetList) {
		SelectedDoubleSelectTargetList = selectedDoubleSelectTargetList;
	}

	public List<Integer> getAssignProjId() {
		return assignProjId;
	}

	public void setAssignProjId(List<Integer> assignProjId) {
		this.assignProjId = assignProjId;
	}

}
