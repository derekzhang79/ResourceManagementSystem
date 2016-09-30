
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ProjectDao;
import com.gits.rms.persistence.ProjectHibernateDao;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class ProjectDaoService implements ProjectService {

    private ProjectDao dao;

    public ProjectDaoService() {
        dao = new ProjectHibernateDao();
    }

    @Override
    public List checkProjectInProActivity(ProjectVO proj) {
        return dao.checkProjectInProActivity(proj);
    }

    @Override
    public void deleteProject(ProjectVO proj) {
        dao.deleteProject(proj);
    }

    @Override
    public List getAllProjects() {
        return dao.getAllProjects();
    }

    @Override
    public List<EmployeesVO> getEmployeeId(Integer roleId) {
        return dao.getEmployeeId(roleId);
    }

    @Override
    public ProjectVO getIdForProName(String projectName) {
        return dao.getIdForProName(projectName);
    }

    @Override
    public ProjectVO getProject(Integer id) {
        return dao.getProject(id);
    }

    @Override
    public ProjectVO getProjectName(String projectName) {
        return dao.getProjectName(projectName);
    }

    @Override
    public List getProjectsReports(ReportsVO report) {
        return dao.getProjectsReports(report);
    }

    @Override
    public void insertProject(ProjectVO proj) {
        dao.insertProject(proj);
    }

    @Override
    public List<ProjectVO> isProjectOwner(int employeeId) {
        return dao.isProjectOwner(employeeId);

    }

    @Override
    public List<ProjectVO> loadProject(Integer projcode) {
        return dao.loadProject(projcode);
    }

    @Override
    public List projectsSearchResult(ProjectVO proj) {
        return dao.projectsSearchResult(proj);
    }

    @Override
    public void updateProject(ProjectVO proj) {
        dao.updateProject(proj);
    }
}
