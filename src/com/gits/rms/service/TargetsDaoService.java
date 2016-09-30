package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TargetsDao;
import com.gits.rms.persistence.TargetsHibernateDao;
import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.GoalVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TargetsVO;
import com.gits.rms.vo.TimesheetAchievedTargetVO;
import com.gits.rms.vo.TimesheetAchivedTargetReportDisplayVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class TargetsDaoService implements TargetsService{

	private TargetsDao dao;
	
	public TargetsDaoService(){
		dao = new TargetsHibernateDao();
	}
	
//	Target Methods
	
	@Override
    public List getAllTargets() {
        return dao.getAllTargets();
    }
	
	@Override
	public TargetsVO getTarget(Integer id){
		return dao.getTarget(id);
	}
	
	@Override
	public void insertTarget(TargetsVO target){
		dao.insertTarget(target);
	}
	
	@Override
	public void updateTarget(TargetsVO target){
		dao.updateTarget(target);
	}
	
	@Override
	public void deleteTarget(TargetsVO target){
		dao.deleteTarget(target);
	}
	
	@Override
	public List targetsSearchResult(TargetsVO target){
		return dao.targetsSearchResult(target);
	}
	
	@Override
	public List<String> getAllTargetsName(){
		return dao.getAllTargetsName();
	}
	
	@Override
	public List<String> getTargetType(String targetName){
		return dao.getTargetType(targetName);
	}
	
	@Override
	public String getTargetMode(String targetName, String targetType){
		return dao.getTargetMode(targetName, targetType);
	}
	
//	Goal Methods
	
	@Override
	public List getAllGoal(){
		return dao.getAllGoal();
	}
	
	@Override
	public List<String> getAllGoalName(){
		return dao.getAllGoalName();
	}
	
	@Override
	public void insertGoal(GoalVO goal){
		dao.insertGoal(goal);
	}
	
	@Override
	public void updateGoal(GoalVO goal){
		dao.updateGoal(goal);
	}
	
	@Override
	public void deleteGoal(GoalVO goal){
		dao.deleteGoal(goal);
	}
	
	@Override
	public GoalVO getGoal(Integer id){
		return dao.getGoal(id);
	}
	
//	Employee Target & Goal Methods
	
	@Override
	public List<EmpTargetAndGoalVO> getAllEmpTargetAndGoal(Integer empId){
		return dao.getAllEmpTargetAndGoal(empId);
	}
	
	@Override
	public void insertEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj){
		dao.insertEmpTargetAndGoal(empTAGObj);
	}
	
	@Override
	public EmpTargetAndGoalVO viewEmpTargetAndGoal(EmpTargetAndGoalVO empTAGObj){
		return dao.viewEmpTargetAndGoal(empTAGObj);
	}
	
	@Override
	public List<TimesheetAchivedTargetReportDisplayVO> getTargetAndGoalReport(ReportsVO report){
		return dao.getTargetAndGoalReport(report);
	}
	
	@Override
	public List<TargetsVO> getAllTargetByProjAndActivity(Integer projId,Integer activityId){
		return dao.getAllTargetByProjAndActivity(projId,activityId);
	}
	
	@Override
	public List<TimesheetAssignProjectTargetVO> getAllAssignedTargetByAssignedId(List<Integer> assignTargetId){
		return dao.getAllAssignedTargetByAssignedId(assignTargetId);
	}
	
	@Override
	public boolean checkAssignedTarget(EmployeesVO empObj,TargetsVO targetObj,ProjectAssignEmpVO proAssignObj){
		return dao.checkAssignedTarget(empObj, targetObj, proAssignObj);
	}
	
	@Override
	public void insertAssignProjTarget(TimesheetAssignProjectTargetVO assignTarget){
		dao.insertAssignProjTarget(assignTarget);
	}
	
	@Override
	public List<TimesheetAssignProjectTargetVO> getAllEmpAssignedTarget(Integer empId){
		return dao.getAllEmpAssignedTarget(empId);
	}
	
	@Override
	public TimesheetAssignProjectTargetVO getEmpAssignedTarget(Integer assignedTargetId){
		return dao.getEmpAssignedTarget(assignedTargetId);
	}
	
	@Override
	public void UpdateTsAchievedTarget(TimesheetAssignProjectTargetVO assignTargetObj){
		dao.UpdateTsAchievedTarget(assignTargetObj);
	}
	
	@Override
	public List<TimesheetAssignProjectTargetVO> getAssignedTargetSearchList(Integer empId){
		return dao.getAssignedTargetSearchList(empId);
	}
	
	@Override
	public void insertAchivedTarget(TimesheetAchievedTargetVO achivedTarget){
		dao.insertAchivedTarget(achivedTarget);
	}
	
	@Override
	public List<TimesheetAchievedTargetVO> getAllEmpAchivedTargets(TimesheetAssignProjectTargetVO assignTargetObj){
		return dao.getAllEmpAchivedTargets(assignTargetObj);
	}
}
