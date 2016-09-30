
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public interface ProjectDao {

    List getAllProjects();

    ProjectVO getProject(Integer id);

    void insertProject(ProjectVO proj);

    void updateProject(ProjectVO proj);

    void deleteProject(ProjectVO proj);

    List loadProject(Integer projcode);

    ProjectVO getProjectName(String projectName);

    ProjectVO getIdForProName(String projectName);

    List projectsSearchResult(ProjectVO proj);

    List getProjectsReports(ReportsVO report);

    List<EmployeesVO> getEmployeeId(Integer roleId);

    List checkProjectInProActivity(ProjectVO proj);

    List<ProjectVO> isProjectOwner(int employeeId);
}
