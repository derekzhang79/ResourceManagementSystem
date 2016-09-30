
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TimeSheetProjectAssignedDao;
import com.gits.rms.persistence.TimeSheetProjeectAssignedHibernateDao;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetProjeectAssignedDaoService implements TimeSheetProjeectAssignedService {

    private TimeSheetProjectAssignedDao dao;

    public TimeSheetProjeectAssignedDaoService() {
        dao = new TimeSheetProjeectAssignedHibernateDao();
    }

    @Override
    public void deleteTsProjAss(ProjectAssignEmpVO tsempproj) {
        dao.deleteTsProjAss(tsempproj);
    }

    @Override
    public List getAllTsProjAss() {
        return dao.getAllTsProjAss();
    }

    @Override
    public List getCurrentSubEmployeeForTimeSheet() {
        return dao.getCurrentSubEmployeeForTimeSheet();
    }

    @Override
    public List<ProjectAssignEmpVO> getCurrentSubEmployeeListForTsProjectAssigned() {
        return dao.getCurrentSubEmployeeListForTsProjectAssigned();
    }

    @Override
    public ProjectAssignEmpVO getTsProjAss(Integer id) {
        return dao.getTsProjAss(id);
    }

    @Override
    public void insertTsProjAss(ProjectAssignEmpVO tsempproj) {
        dao.insertTsProjAss(tsempproj);
    }

    @Override
    public List<ProjectAssignEmpVO> isExistProjectAssign(ProjectAssignEmpVO projAssign) {
        return dao.isExistProjectAssign(projAssign);
    }

    @Override
    public List<ProjectAssignEmpVO> searchDuplicateProjectAssign(ProjectAssignEmpVO projAssignEmpVo) {
        return dao.searchDuplicateProjectAssign(projAssignEmpVo);
    }

    @Override
    public List tsProjAssSearchResult(ProjectAssignEmpVO tsempproj) {
        return dao.tsProjAssSearchResult(tsempproj);
    }

    @Override
    public void updateTsProjAss(ProjectAssignEmpVO tsempproj) {
        dao.updateTsProjAss(tsempproj);
    }

    @Override
    public List<ProjectAssignEmpVO> getEmpTargetAndGoal(Integer empId){
    	return dao.getEmpTargetAndGoal(empId);
    }
    
    @Override
    public List<ProjectAssignEmpVO> getEmployeeProjectActivity(Integer empId, Integer projectId){
    	return dao.getEmployeeProjectActivity(empId, projectId);
    }
    
    @Override
    public ProjectAssignEmpVO getEmployeeTargetAndGoal(Integer empId, Integer projectId, Integer projActivityId){
    	return dao.getEmployeeTargetAndGoal(empId, projectId, projActivityId);
    }
    
    @Override
    public List<TimeSheetProjectAssignVO> checkAssignedProjInTimesheet(ProjectAssignEmpVO assignProj){
    	return dao.checkAssignedProjInTimesheet(assignProj);
    }
}
