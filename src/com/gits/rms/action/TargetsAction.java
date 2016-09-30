package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.GoalVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.TargetsVO;

public class TargetsAction extends ActionSupport{
	private static final long serialVersionUID = 231970618834055849L;
	private TargetsService targetService = new TargetsDaoService();
	private ProjectActivityService proActivityService = new ProjectActivityDaoService();
	private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
	private TargetsVO target;
	private List<TargetsVO> targetList;
	private GoalVO goal;
	private List<GoalVO> goalNameList;
	private String goalNameDiv="";
	 private int activity;
	
	// To get List of Targets
    @SkipValidation
    public String getAllTargets(){
    	targetList = targetService.getAllTargets();
    	return SUCCESS;
    }
    
    // when click Edit link it bring particular data into Form or click Add
    // Target it shows blank Form to enter New Data
    @SkipValidation
    public String setUpTargets(){
    	if ((target != null) && (target.getHcmoTargetId() != null)) {
			target = targetService.getTarget(target.getHcmoTargetId());
		}
    	return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Targets or update
    // particular Target Data
    public String insertOrUpdateTargets(){
    	try {
	    	Map session = ActionContext.getContext().getSession();
	        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	    	if (target.getHcmoTargetId() == null) {
	    		
	    		String testActivityId = Integer.toString(activity);
		        if (testActivityId.equals("0")) {
		            addActionError(getText("errors.messages.projActivity.projectActivity"));
		            return INPUT;
		        }
		        
		        ProjectActivityVO activityVO = new ProjectActivityVO();
                activityVO.setProjectActivityId(activity);
                activityVO = proActivityService.getProjectActivity(activityVO.getProjectActivityId());
                
                target.setProjActivityObj(activityVO);
                
	    		target.setCreated(DateUtils.getCurrentDateTime());
	    		target.setCreatedBy(oEmp);
	    		target.setUpdatedBy(oEmp);
	    		target.setIsActive(1);
	    		targetService.insertTarget(target);
	    		addActionMessage(getText("Added Successfully"));
			}else {
				target.setUpdatedBy(oEmp);
				targetService.updateTarget(target);
				addActionMessage(getText("Updated Successfully"));
			}
    	} catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getErrorMoreFields(e);
            addActionError(sError);
            throw e;
        }
    	// For Drop down List
    	loadValues.getAllTargetsName();
    	return SUCCESS;
    }
    
 // To delete Particular Target Detail
    @SkipValidation
    public String deleteTargets() {
    	try {
    		Map session = ActionContext.getContext().getSession();
	        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	        target.setUpdatedBy(oEmp);
	        targetService.deleteTarget(target);
	        addActionMessage(getText("Deleted Successfully"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
    }
    
 // To get Particular Target Data
    @SkipValidation
    public String viewTargets(){
    	if ((target != null) && (target.getHcmoTargetId() != null)) {
			target = targetService.getTarget(target.getHcmoTargetId());
		}
    	return SUCCESS;
    }
    
 // To View Search Form
    @SkipValidation
    public String targetsSearchForm(){
    	return SUCCESS;
    }
    
 // Search Result
    @SkipValidation
    public String targetsSearchResult(){
    	try {
    		targetList = targetService.targetsSearchResult(target);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
 // To get List of Targets
    @SkipValidation
    public String getAllGoal(){
    	goalNameList = targetService.getAllGoal();
    	return SUCCESS;
    }
    
 // Goal it shows blank Form to enter New Data
    @SkipValidation
    public String setUpGoalForm(){
    	if ((goal != null) && (goal.getHcmoGoalId() != null)) {
    		goal = targetService.getGoal(goal.getHcmoGoalId());
		}
    	return SUCCESS;
    }
    
 // In the New Form when click Submit button To insert new Goal or update
 // particular Target Data
    @SkipValidation
    public String insertOrUpdateGoal(){
    	try {
    		Map session = ActionContext.getContext().getSession();
	        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	        if (goal.getHcmoGoalId() == null) {
	        	goal.setCreated(DateUtils.getCurrentDateTime());
		    	goal.setCreatedBy(oEmp);
		    	goal.setUpdatedBy(oEmp);
		    	goal.setIsActive(1);
		    	targetService.insertGoal(goal);
		    	addActionMessage(getText("Added Successfully"));
			}else {
				goal.setUpdatedBy(oEmp);
				targetService.updateGoal(goal);
				addActionMessage(getText("Updated Successfully"));
			}
    	} catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
//    	For Multi Select List
    	loadValues.getAllGoalName();
    	if (goalNameDiv == null || goalNameDiv.equals("") || goalNameDiv.isEmpty()) {
			return SUCCESS;
		}else {
			return "GOALNAMEDIV";
		}
    }
    
 // To delete Particular Target Detail
    @SkipValidation
    public String deleteGoal(){
    	try {
    		Map session = ActionContext.getContext().getSession();
	        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
	        goal.setUpdatedBy(oEmp);
	        targetService.deleteGoal(goal);
	        addActionMessage(getText("Deleted Successfully"));
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	For Multi Select List
    	loadValues.getAllGoalName();
    	return SUCCESS;
    }
    
	public TargetsVO getTarget() {
		return target;
	}

	public void setTarget(TargetsVO target) {
		this.target = target;
	}

	public List<TargetsVO> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TargetsVO> targetList) {
		this.targetList = targetList;
	}

	public void setGoal(GoalVO goal) {
		this.goal = goal;
	}

	public GoalVO getGoal() {
		return goal;
	}

	public List<GoalVO> getGoalNameList() {
		return goalNameList;
	}

	public void setGoalNameList(List<GoalVO> goalNameList) {
		this.goalNameList = goalNameList;
	}

	public void setGoalNameDiv(String goalNameDiv) {
		this.goalNameDiv = goalNameDiv;
	}

	public String getGoalNameDiv() {
		return goalNameDiv;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}
}
