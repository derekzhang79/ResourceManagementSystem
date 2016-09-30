
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.ProjectAssignEmpVO;

public interface ProjectAssignEmpDao {
	
    List getAllProjectAssignEmpDao();

    void updateProjectAssignEmpDao(ProjectAssignEmpVO projectAssignEmp);

    ProjectAssignEmpVO insertProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp);

    void deleteProjectAssignEmp(ProjectAssignEmpVO projectAssignEmp);

    ProjectAssignEmpVO getProjectAssignEmp(Integer projectAssignEmpId);

    List projectAssignEmpSearchResult(Integer EmployeeId, Date date, Date endDate);

    List projectAssignEmpSearchDetail(Integer EmployeeId);

    List projectAssignEmpSearch(Integer EmployeeId, Integer projectName, Date date, Date endDate);

    List<ProjectAssignEmpVO> empScheduleProjSearchResult(Integer EmployeeId, Date date, Date endDate);
    
    List<ProjectAssignEmpVO> getProjAssignListByProjActivityAndProj(Integer projId,Integer projActivityId);
}
