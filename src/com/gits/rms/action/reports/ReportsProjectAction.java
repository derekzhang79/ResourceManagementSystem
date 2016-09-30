
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsProjectAction extends ActionSupport {

    private ReportsVO report;
    private List<ReportsVO> reportsList;
    private Collection<ProjectVO> projectList;
    private ProjectVO proj;
    private ProjectService projectService = new ProjectDaoService();

    @Override
    public String execute() throws Exception {
        try {
            projectList = projectService.getProjectsReports(report);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/projectReports/Project.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/projectReports/Project.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public List<ReportsVO> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<ReportsVO> reportsList) {
        this.reportsList = reportsList;
    }

    public Collection<ProjectVO> getProjectList() {
        return projectList;
    }

    public void setProjectList(Collection<ProjectVO> projectList) {
        this.projectList = projectList;
    }

    public ProjectVO getProj() {
        return proj;
    }

    public void setProj(ProjectVO proj) {
        this.proj = proj;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
