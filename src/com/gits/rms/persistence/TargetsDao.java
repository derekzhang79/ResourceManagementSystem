package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.GoalVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TargetsVO;
import com.gits.rms.vo.TimesheetAchievedTargetVO;
import com.gits.rms.vo.TimesheetAchivedTargetReportDisplayVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public interface TargetsDao {
	
//	Target Methods
	List getAllTargets();
	
	List<String> getAllTargetsName();
	
	TargetsVO getTarget(Integer id);
	
	void insertTarget(TargetsVO target);
	
	void updateTarget(TargetsVO target);
	
	void deleteTarget(TargetsVO target);
	
	List targetsSearchResult(TargetsVO target);
	
	List<String> getTargetType(String targetName);
	
	String getTargetMode(String targetName, String targetType);
	
//	Goal Methods
	List getAllGoal();
	
	List<String> getAllGoalName();
	
	GoalVO getGoal(Integer id);
	
	void insertGoal(GoalVO goal);
	
	void updateGoal(GoalVO goal);
	
	void deleteGoal(GoalVO goal);
	
//	Employee Target & Goal Methods
	List<EmpTargetAndGoalVO> getAllEmpTargetAndGoal(Integer empId);
	
	void insertEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj);
	
	EmpTargetAndGoalVO viewEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj);
	
	List<TimesheetAchivedTargetReportDisplayVO> getTargetAndGoalReport(ReportsVO report);
	
	List<TargetsVO> getAllTargetByProjAndActivity(Integer projId,Integer activityId);
	
	List<TimesheetAssignProjectTargetVO> getAllAssignedTargetByAssignedId(List<Integer> assignTargetId);
	
	boolean checkAssignedTarget(EmployeesVO empObj,TargetsVO targetObj,ProjectAssignEmpVO proAssignObj);
	
	void insertAssignProjTarget(TimesheetAssignProjectTargetVO assignTarget);
	
	List<TimesheetAssignProjectTargetVO> getAllEmpAssignedTarget(Integer empId);
	
	TimesheetAssignProjectTargetVO getEmpAssignedTarget(Integer assignedTargetId);
	
	void UpdateTsAchievedTarget(TimesheetAssignProjectTargetVO assignTargetObj);
	
	List<TimesheetAssignProjectTargetVO> getAssignedTargetSearchList(Integer empId);
	
	void insertAchivedTarget(TimesheetAchievedTargetVO achivedTarget);
	
	List<TimesheetAchievedTargetVO> getAllEmpAchivedTargets(TimesheetAssignProjectTargetVO assignTargetObj);
}
