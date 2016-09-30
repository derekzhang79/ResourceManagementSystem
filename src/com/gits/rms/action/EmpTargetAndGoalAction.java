package com.gits.rms.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.jhlabs.image.LightFilter.AmbientLight;
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
import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class EmpTargetAndGoalAction extends ActionSupport{
	private static final long serialVersionUID = -277665036338771987L;
	private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();
	private TimeSheetApproverService timeSheetAppproverService = new TimeSheetApproverDaoService();
    private List<TimeSheetApproverVO> timeSheetApproverList;
	private EmployeesService employeeService = new EmployeesDaoService();
	private TargetsService targetService = new TargetsDaoService();
	private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
	private Integer employeeId;
	private EmpTargetAndGoalVO empTAGObj;
	private ProjectAssignEmpVO empTGValue;
	private List<ProjectAssignEmpVO> empAssignTargetGoalList;
	private List<EmployeesVO> empIdObj;
	private List<EmpTargetAndGoalVO> empTargetGoalList;
	private Integer empTargetGoalCount;
	private List<ProjectVO> projectList;
	private List<ProjectActivityVO> projectActivityList;
	private Integer projectId;
	private Integer projectActivityId;
	private InputStream inStream;
	
	
	private List<TimesheetAssignProjectTargetVO> assignedTargetList;
	
	@SkipValidation
	public String getAllEmpTarget(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            
            assignedTargetList = targetService.getAllEmpAssignedTarget(oEmp.getEmployeeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
	}
	
	// Target it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmpAddTargetGoal(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            empAssignTargetGoalList = tsProjAssService.getEmpTargetAndGoal(oEmp.getEmployeeId());
            empTargetGoalCount = empAssignTargetGoalList.size();
            if (empAssignTargetGoalList == null || empAssignTargetGoalList.size() == 0) {
				addActionError("Target and Goals are not Assigned for your project.");
			}else {
				projectList = new ArrayList<ProjectVO>();
				projectActivityList = new ArrayList<ProjectActivityVO>();
				for (int e = 0; e < empAssignTargetGoalList.size(); e++) {
					projectList.add(empAssignTargetGoalList.get(e).getProjectName());
				}
				loadValues.setEmpProjectName(projectList);
				loadValues.setEmptyEmpProjectActivity(projectActivityList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String setUpViewTargetGoal(){
    	Map session = ActionContext.getContext().getSession();
        EmployeesVO empVOObj = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        timeSheetApproverList = timeSheetAppproverService.getAllTimeSheeetSubEmployee(empVOObj.getEmployeeId());
        if (timeSheetApproverList.isEmpty()) {
            emp();
        } else {
        	getTimeSheetSubEmployee();
        }
    	return SUCCESS;
    }
    
    @SkipValidation
    public String viewEmpTargetAndGoal(){
    	try {
			if ((empTAGObj != null) || (empTAGObj.getHcmoEmpTgId() != null)) {
				empTAGObj = targetService.viewEmpTargetAndGoal(empTAGObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String getAllEmpTargetAndGoal(){
    	try {
            empTargetGoalList = targetService.getAllEmpTargetAndGoal(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    @SkipValidation
    public String getEmpProjectActivity(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            empAssignTargetGoalList = tsProjAssService.getEmployeeProjectActivity(oEmp.getEmployeeId(),projectId);
            projectActivityList = new ArrayList<ProjectActivityVO>();
            if (empAssignTargetGoalList == null || empAssignTargetGoalList.size() == 0) {
            	loadValues.setEmptyEmpProjectActivity(projectActivityList);
			}else {
				for (int e = 0; e < empAssignTargetGoalList.size(); e++) {
					projectActivityList.add(empAssignTargetGoalList.get(e).getProjectActivityId());
				}
				loadValues.setEmpProjectActivity(projectActivityList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
    }
    
    @SkipValidation
    public String getEmployeeTargetAndGoal(){
    	try {
    		Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            String empTGDetails = new String();
            if (projectActivityId == null) {
            	
			}else {
				empTGValue = tsProjAssService.getEmployeeTargetAndGoal(oEmp.getEmployeeId(),projectId,projectActivityId);
                empTGDetails = empTGValue.getProjTargetName();
                empTGDetails += "$$$";
                empTGDetails += empTGValue.getProjTargetType();
                empTGDetails += "$$$";
                empTGDetails += empTGValue.getProjTargetMode();
                empTGDetails += "$$$";
                empTGDetails += empTGValue.getProjGoalName();
			}
            inStream = new StringBufferInputStream(empTGDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    public String insertOrUpdateEmpTargetGoal(){
    	try {
			if (empTAGObj.getHcmoEmpTgId() == null) {
				Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                empTAGObj.setEmployeeName(oEmp);
                empTAGObj.setCreated(DateUtils.getCurrentDateTime());
                empTAGObj.setCreatedBy(oEmp);
                empTAGObj.setUpdatedBy(oEmp);
                empTAGObj.setIsActive(1);
                targetService.insertEmpTargetAndGoal(empTAGObj);
                addActionMessage(getText("Added Successfully"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
 // It shows Employee List in the Dropdown field
    private void emp() {
        empIdObj = employeeService.getCurrentEmployee();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("empsList", empIdObj);
    }
    
    private void getTimeSheetSubEmployee() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        List employeeIdList = new LinkedList();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empIdObj = employeeService.getCurrentTimeSheetSubEmployee();

        EmployeesVO employee = new EmployeesVO();
        for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
            employee = it.next();
            employeeIdList.add(employee.getEmployeeId());
        }
        if (employeeIdList.contains(empSelf.getEmployeeId())) {
            request.setAttribute("empsList", empIdObj);
        } else {
            empIdObj.add(empSelf);
            request.setAttribute("empsList", empIdObj);
        }

    }
    
	public Integer getEmpTargetGoalCount() {
		return empTargetGoalCount;
	}
	public void setEmpTargetGoalCount(Integer empTargetGoalCount) {
		this.empTargetGoalCount = empTargetGoalCount;
	}
	public void setEmpTAGObj(EmpTargetAndGoalVO empTAGObj) {
		this.empTAGObj = empTAGObj;
	}
	public EmpTargetAndGoalVO getEmpTAGObj() {
		return empTAGObj;
	}

	public void setEmpTargetGoalList(List<EmpTargetAndGoalVO> empTargetGoalList) {
		this.empTargetGoalList = empTargetGoalList;
	}

	public List<EmpTargetAndGoalVO> getEmpTargetGoalList() {
		return empTargetGoalList;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectActivityId(Integer projectActivityId) {
		this.projectActivityId = projectActivityId;
	}

	public Integer getProjectActivityId() {
		return projectActivityId;
	}

	public ProjectAssignEmpVO getEmpTGValue() {
		return empTGValue;
	}

	public void setEmpTGValue(ProjectAssignEmpVO empTGValue) {
		this.empTGValue = empTGValue;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	public InputStream getInStream() {
		return inStream;
	}

	public List<TimesheetAssignProjectTargetVO> getAssignedTargetList() {
		return assignedTargetList;
	}

	public void setAssignedTargetList(
			List<TimesheetAssignProjectTargetVO> assignedTargetList) {
		this.assignedTargetList = assignedTargetList;
	}
	
}
