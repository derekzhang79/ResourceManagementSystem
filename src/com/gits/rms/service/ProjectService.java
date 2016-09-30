
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public interface ProjectService {

    List checkProjectInProActivity(ProjectVO proj);

    void deleteProject(ProjectVO proj);

    List getAllProjects();

    List<EmployeesVO> getEmployeeId(Integer roleId);

    ProjectVO getIdForProName(String projectName);

    ProjectVO getProject(Integer id);

    ProjectVO getProjectName(String projectName);

    List getProjectsReports(ReportsVO report);

    void insertProject(ProjectVO proj);

    List<ProjectVO> isProjectOwner(int employeeId);

    List<ProjectVO> loadProject(Integer projcode);

    List projectsSearchResult(ProjectVO proj);

    void updateProject(ProjectVO proj);
}
