
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.ProjectAssignEmpDao;
import com.gits.rms.persistence.ProjectAssignEmpHibernateDao;
import com.gits.rms.vo.ProjectAssignEmpVO;

public class ProjectAssignEmpDaoService implements ProjectAssignEmpService {

    private ProjectAssignEmpDao dao;

    public ProjectAssignEmpDaoService() {
        dao = new ProjectAssignEmpHibernateDao();
    }

    @Override
    public void deleteProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp) {
        dao.deleteProjectAssignEmp(projectAssignEmp);
    }

    @Override
    public List<ProjectAssignEmpVO> empScheduleProjSearchResult(Integer EmployeeId, Date date, Date endDate) {
        return dao.empScheduleProjSearchResult(EmployeeId, date, endDate);

    }

    @Override
    public List getAllProjectAssignEmpDao() {
        return dao.getAllProjectAssignEmpDao();
    }

    @Override
    public ProjectAssignEmpVO getProjectAssignEmp(Integer projectAssignEmpId) {
        return dao.getProjectAssignEmp(projectAssignEmpId);
    }

    @Override
    public ProjectAssignEmpVO insertProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp) {
        return dao.insertProjectAssignEmp(projectAssignEmp);
    }

    @Override
    public List projectAssignEmpSearch(Integer EmployeeId, Integer projectName, Date date, Date endDate) {
        return dao.projectAssignEmpSearch(EmployeeId, projectName, date, endDate);
    }

    @Override
    public List projectAssignEmpSearchDetail(Integer EmployeeId) {
        return dao.projectAssignEmpSearchDetail(EmployeeId);
    }

    @Override
    public List projectAssignEmpSearchResult(Integer EmployeeId, Date date, Date endDate) {
        return dao.projectAssignEmpSearchResult(EmployeeId, date, endDate);
    }

    @Override
    public void updateProjectAssignEmpDao(ProjectAssignEmpVO projectAssignEmp) {
        dao.updateProjectAssignEmpDao(projectAssignEmp);

    }
    
    @Override
    public List<ProjectAssignEmpVO> getProjAssignListByProjActivityAndProj(Integer projId,Integer projActivityId){
    	return dao.getProjAssignListByProjActivityAndProj(projId, projActivityId);
    }

}
