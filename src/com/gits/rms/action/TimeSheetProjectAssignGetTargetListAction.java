
package com.gits.rms.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TargetsVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimesheetAchievedTargetVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class TimeSheetProjectAssignGetTargetListAction extends ActionSupport {
	private static final long serialVersionUID = 3855548561285064991L;
	private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
	
	private TargetsService targetService = new TargetsDaoService();
	private List<TargetsVO> targetList;
	private int projectId;
	private int activityId;
	private int employeeId;
//	private List projectAssignEmpId = new ArrayList();
	private String projectAssignEmpId;
	private List assignProjTargetList;
	private TimesheetAssignProjectTargetVO assignTargetObj = new TimesheetAssignProjectTargetVO();
	private TimesheetAchievedTargetVO achivedTargetObj = new TimesheetAchievedTargetVO();
	private TimeSheetProjeectAssignedService tsProjAssignService = new TimeSheetProjeectAssignedDaoService();
	private TimeSheetApproverService timeSheetAppproverService = new TimeSheetApproverDaoService();
	private EmployeesService emplService = new EmployeesDaoService();
	private ProjectAssignEmpVO projAssignEmpObj;
	private TargetsVO target;
	private EmployeesVO empObj;
	private List<TimesheetAssignProjectTargetVO> assignTargetList;
	private List<TimeSheetApproverVO> timeSheetApproverList;
	private List<TimesheetAchievedTargetVO> achivedTargetList;
	
    @SkipValidation
    public String getTargetListByProjAndActivityId() {
    	targetList = targetService.getAllTargetByProjAndActivity(projectId, activityId);
    	List<Integer> projAssList = new ArrayList();
    	
    	String result = projectAssignEmpId.replace("[","");
    	result = result.replace("]","");
    	result = result.replace(" ","");
    	
    	projectAssignEmpId = result.trim();
    	String str = projectAssignEmpId;
    	String[] temp;
    	String delimiter = ",";
    	temp = str.trim().split(delimiter);
    	
    	for(int i =0; i < temp.length ; i++){
    		System.out.println("temp[i] :"+temp[i]);
    		projAssList.add(Integer.valueOf((temp[i])));
    	}
    	
    	assignTargetList = targetService.getAllAssignedTargetByAssignedId(projAssList);
    	return SUCCESS;
    }
    
    @SkipValidation
    public String insertAssignProjectTarget(){
    	Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	String targetId;

    	List<Integer> projAssList = new ArrayList();
    	String result = projectAssignEmpId.replace("[","");
    	result = result.replace("]","");
    	result = result.replace(" ","");
    	
    	System.out.println("projectAssignEmpId :"+projectAssignEmpId);
    	System.out.println("result :"+result);
    	projectAssignEmpId = result.trim();
    	System.out.println("projectAssignEmpId :"+projectAssignEmpId);
    	String str = projectAssignEmpId;
    	String[] temp;
    	String delimiter = ",";
    	temp = str.trim().split(delimiter);
    	
    	for(int i =0; i < temp.length ; i++){
    		System.out.println("temp[i] :"+temp[i]);
    		projAssList.add(Integer.valueOf((temp[i])));
    	}
    	
    	if(assignProjTargetList == null){
    		addActionError(getText("Please select min of one target to assign"));
    		return INPUT;
    	}else{
    		
    		for(int j=0;j<projAssList.size();j++){
    			projAssignEmpObj = tsProjAssignService.getTsProjAss(Integer.valueOf(projAssList.get(j)));
    			empObj = emplService.getEmployeeById(projAssignEmpObj.getEmployeeName().getEmployeeId());
        		for(int i=0;i<assignProjTargetList.size();i++){
	    			targetId = String.valueOf(assignProjTargetList.get(i));
	    			target = targetService.getTarget(Integer.valueOf(targetId));
	    			
	    			boolean checkExisting = targetService.checkAssignedTarget(empObj, target, projAssignEmpObj);
	    			if(checkExisting){
	    				assignTargetList = targetService.getAllAssignedTargetByAssignedId(projAssList);
	    				
	    				addActionError(getText("Target Already Exists"));
	    				return SUCCESS;
	    			}else{
	    				assignTargetObj.setTargetObj(target);
		    			assignTargetObj.setProAssignObj(projAssignEmpObj);
		    			assignTargetObj.setEmployeeObj(empObj);
		    			assignTargetObj.setCreated(DateUtils.getCurrentDateTime());
		    			assignTargetObj.setCreatedBy(oEmp);
		    			assignTargetObj.setUpdatedBy(oEmp);
		    			assignTargetObj.setIsActive(1);
		    			targetService.insertAssignProjTarget(assignTargetObj);
	    			}
	    			
    			}
    		}
    		
    		addActionMessage(getText("Added Successfully"));
        	assignTargetList = targetService.getAllAssignedTargetByAssignedId(projAssList);
    	}
    	
    	return SUCCESS;
    }
    
    @SkipValidation
	public String getAllEmpAssignedTargetList(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            
            assignTargetList = targetService.getAllEmpAssignedTarget(oEmp.getEmployeeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
	}
    
    @SkipValidation
    public String getAssignedTarget(){
    	try {
			if ((assignTargetObj != null) || (assignTargetObj.getHcmoTsAssignProjTargetId() != null)) {
				assignTargetObj = targetService.getEmpAssignedTarget(assignTargetObj.getHcmoTsAssignProjTargetId());
				
				 Map session = ActionContext.getContext().getSession();
				 session.put("ASSIGNEDTARGETMODE", assignTargetObj.getTargetObj().getTargetMode());
				 
				 achivedTargetList = targetService.getAllEmpAchivedTargets(assignTargetObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String UpdateTsAchievedTarget(){
    	 Map session = ActionContext.getContext().getSession();
         EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

         assignTargetObj = targetService.getEmpAssignedTarget(assignTargetObj.getHcmoTsAssignProjTargetId());
         
         if(achivedTargetObj.getTargetAchieved().isEmpty()){
        	 addActionError(getText("errors.target.achieved"));
        	 return INPUT;
         }
         
         if(assignTargetObj.getTargetObj().getTargetMode().equals("Daily")){
        	 
        	 if(achivedTargetObj.getDate()==null){
        		 addActionError(getText("Date is a required field"));
        		 return INPUT;
        	 }
        	 
        	 if(!(achivedTargetObj.getDate().equals(assignTargetObj.getProAssignObj().getProjectStartDate()))){
        		 
        		 if(!(achivedTargetObj.getDate().equals(assignTargetObj.getProAssignObj().getProjectEndDate()))){
        			 
        			 if(achivedTargetObj.getDate().before(assignTargetObj.getProAssignObj().getProjectStartDate())){
                		 addActionError(getText("Date is before the start date"));
                		 return INPUT;
                	 }
                	 
                	 if(achivedTargetObj.getDate().after(assignTargetObj.getProAssignObj().getProjectEndDate())){
                		 addActionError(getText("Date is after the end date"));
                		 return INPUT;
                	 } 
        		 }
        	 }
        	 
        	 achivedTargetList = targetService.getAllEmpAchivedTargets(assignTargetObj);
        	 
        	 for (Iterator<TimesheetAchievedTargetVO> it = achivedTargetList.iterator(); it.hasNext();) {
        		 TimesheetAchievedTargetVO achivedTarget = it.next();
        		 
        		 if(achivedTarget.getDate().equals(achivedTargetObj.getDate())){
        			 addActionError(getText("Data exists for "+achivedTarget.getDate()));
            		 return INPUT;
        		 }
             }
        	 
         }else if(assignTargetObj.getTargetObj().getTargetMode().equals("Weekly")){

        	 if(achivedTargetObj.getStartDate()==null){
        		 addActionError(getText("Start Date is a required field"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate()==null){
        		 addActionError(getText("End Date is a required field"));
        		 return INPUT;
        	 }

        	 if(achivedTargetObj.getStartDate().compareTo(assignTargetObj.getProAssignObj().getProjectStartDate())<0){
        		 addActionError(getText("Start Date is before the project start date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getStartDate().compareTo(assignTargetObj.getProAssignObj().getProjectEndDate())>0){
        		 addActionError(getText("Start Date is after the project end date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate().compareTo(assignTargetObj.getProAssignObj().getProjectStartDate())<0){
        		 addActionError(getText("End Date is before the project start date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate().compareTo(assignTargetObj.getProAssignObj().getProjectEndDate())>0){
        		 addActionError(getText("End Date is after the project end date"));
        		 return INPUT;
        	 }
        	 
        	 long timeDiff = Math.abs(achivedTargetObj.getStartDate().getTime() - achivedTargetObj.getEndDate().getTime());
        	 String diff = String.format("%d", TimeUnit.MILLISECONDS.toHours(timeDiff));
        	 Integer diffInt = Integer.valueOf(diff);
        	 diffInt += 24;
        	 diffInt = diffInt /24;
        	 
        	 if((diffInt < 7)||(diffInt >7)){
        		 addActionError(getText("For weekly target mode, you are allowed to enter data for a week"));
        		 return INPUT;
        	 }
         }else if(assignTargetObj.getTargetObj().getTargetMode().equals("Monthly")){

        	 if(achivedTargetObj.getStartDate()==null){
        		 addActionError(getText("Start Date is a required field"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate()==null){
        		 addActionError(getText("End Date is a required field"));
        		 return INPUT;
        	 }

        	 if(achivedTargetObj.getStartDate().compareTo(assignTargetObj.getProAssignObj().getProjectStartDate())<0){
        		 addActionError(getText("Start Date is before the project start date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getStartDate().compareTo(assignTargetObj.getProAssignObj().getProjectEndDate())>0){
        		 addActionError(getText("Start Date is after the project end date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate().compareTo(assignTargetObj.getProAssignObj().getProjectStartDate())<0){
        		 addActionError(getText("End Date is before the project start date"));
        		 return INPUT;
        	 }
        	 
        	 if(achivedTargetObj.getEndDate().compareTo(assignTargetObj.getProAssignObj().getProjectEndDate())>0){
        		 addActionError(getText("End Date is after the project end date"));
        		 return INPUT;
        	 }
        	 
        	 long timeDiff = Math.abs(achivedTargetObj.getStartDate().getTime() - achivedTargetObj.getEndDate().getTime());
        	 String diff = String.format("%d", TimeUnit.MILLISECONDS.toHours(timeDiff));
        	 Integer diffInt = Integer.valueOf(diff);
        	 diffInt += 24;
        	 diffInt = diffInt /24;
        	 
        	 if(diffInt > 31){
        		 addActionError(getText("For Monthly target mode, you are allowed to enter data for a month"));
        		 return INPUT;
        	 }
        	 
        	 if(diffInt < 29){
        		 addActionError(getText("For Monthly target mode, you are allowed to enter data for a month"));
        		 return INPUT;
        	 }
         }
         
         if(assignTargetObj.getTargetObj().getTargetMode()=="Daily"){
        	 achivedTargetObj.setDate(achivedTargetObj.getDate());
        	 achivedTargetObj.setStartDate(achivedTargetObj.getDate());
         }else{
        	 achivedTargetObj.setStartDate(achivedTargetObj.getStartDate());
        	 achivedTargetObj.setEndDate(achivedTargetObj.getEndDate());
         }
         
         achivedTargetObj.setStatus(assignTargetObj.getTargetObj().getTargetMode());
         achivedTargetObj.setTargetAchieved(achivedTargetObj.getTargetAchieved());
         achivedTargetObj.setTargetNotes(achivedTargetObj.getTargetNotes());
         achivedTargetObj.setHcmoTsAssignedTargetId(assignTargetObj);
         achivedTargetObj.setCreated(DateUtils.getCurrentDateTime());
         achivedTargetObj.setCreatedBy(oEmp);
         achivedTargetObj.setUpdatedBy(oEmp);
         achivedTargetObj.setIsActive(1);
         targetService.insertAchivedTarget(achivedTargetObj);
         
         addActionMessage(getText("Added Successfully"));
         
    	return SUCCESS;
    }
    
    @SkipValidation
    public String getSubEmpAssignedTargetSearchForm(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO empVOObj = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            HttpServletRequest request = ServletActionContext.getRequest();
            
            timeSheetApproverList = timeSheetAppproverService.getAllTimeSheeetSubEmployee(empVOObj.getEmployeeId());
            request.setAttribute("timesheetsubemplist", timeSheetApproverList);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String getAssignedTargetSearchList(){
    	try {
    		assignTargetList = targetService.getAssignedTargetSearchList(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String getEmployeeAchivedTargetList(){
    	try {
    		if ((assignTargetObj != null) || (assignTargetObj.getHcmoTsAssignProjTargetId() != null)) {
				assignTargetObj = targetService.getEmpAssignedTarget(assignTargetObj.getHcmoTsAssignProjTargetId());
				
				 Map session = ActionContext.getContext().getSession();
				 session.put("ASSIGNEDTARGETMODE", assignTargetObj.getTargetObj().getTargetMode());
				 
				 achivedTargetList = targetService.getAllEmpAchivedTargets(assignTargetObj);
			}
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    	

	public List<TargetsVO> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TargetsVO> targetList) {
		this.targetList = targetList;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getProjectAssignEmpId() {
		return projectAssignEmpId;
	}

	public void setProjectAssignEmpId(String projectAssignEmpId) {
		this.projectAssignEmpId = projectAssignEmpId;
	}

	public List getAssignProjTargetList() {
		return assignProjTargetList;
	}

	public void setAssignProjTargetList(List assignProjTargetList) {
		this.assignProjTargetList = assignProjTargetList;
	}

	public TimesheetAssignProjectTargetVO getAssignTargetObj() {
		return assignTargetObj;
	}

	public void setAssignTargetObj(TimesheetAssignProjectTargetVO assignTargetObj) {
		this.assignTargetObj = assignTargetObj;
	}

	public EmployeesService getEmplService() {
		return emplService;
	}

	public void setEmplService(EmployeesService emplService) {
		this.emplService = emplService;
	}

	public ProjectAssignEmpVO getProjAssignEmpObj() {
		return projAssignEmpObj;
	}

	public void setProjAssignEmpObj(ProjectAssignEmpVO projAssignEmpObj) {
		this.projAssignEmpObj = projAssignEmpObj;
	}

	public TargetsVO getTarget() {
		return target;
	}

	public void setTarget(TargetsVO target) {
		this.target = target;
	}

	public EmployeesVO getEmpObj() {
		return empObj;
	}

	public void setEmpObj(EmployeesVO empObj) {
		this.empObj = empObj;
	}

	public List<TimesheetAssignProjectTargetVO> getAssignTargetList() {
		return assignTargetList;
	}

	public void setAssignTargetList(
			List<TimesheetAssignProjectTargetVO> assignTargetList) {
		this.assignTargetList = assignTargetList;
	}

	public TimesheetAchievedTargetVO getAchivedTargetObj() {
		return achivedTargetObj;
	}

	public void setAchivedTargetObj(TimesheetAchievedTargetVO achivedTargetObj) {
		this.achivedTargetObj = achivedTargetObj;
	}

	public List<TimesheetAchievedTargetVO> getAchivedTargetList() {
		return achivedTargetList;
	}

	public void setAchivedTargetList(
			List<TimesheetAchievedTargetVO> achivedTargetList) {
		this.achivedTargetList = achivedTargetList;
	}
	
}
