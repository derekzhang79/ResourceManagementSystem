
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.ProjectAssignEmpVO;

public interface ProjectAssignEmpService {
	
    void deleteProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp);

    List<ProjectAssignEmpVO> empScheduleProjSearchResult(Integer EmployeeId, Date date, Date endDate);

    List getAllProjectAssignEmpDao();

    ProjectAssignEmpVO getProjectAssignEmp(Integer projectAssignEmpId);

    ProjectAssignEmpVO insertProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp);

    List projectAssignEmpSearch(Integer EmployeeId, Integer projectName, Date date, Date endDate);

    List projectAssignEmpSearchDetail(Integer EmployeeId);

    List projectAssignEmpSearchResult(Integer EmployeeId, Date date, Date endDate);

    void updateProjectAssignEmpDao(ProjectAssignEmpVO projectAssignEmp);
    
    List<ProjectAssignEmpVO> getProjAssignListByProjActivityAndProj(Integer projId,Integer projActivityId);
}
