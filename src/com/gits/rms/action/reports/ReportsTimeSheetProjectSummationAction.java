
package com.gits.rms.action.reports;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class ReportsTimeSheetProjectSummationAction extends ActionSupport {

    private ReportsVO report;
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;
    private TimeSheetCategoryAssignService timesheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private ArrayList timeSheetReportList = new ArrayList();

    @Override
    public String execute() throws Exception {
        try {
            timeSheetProjectAssignList = timesheetCategoryAssignService.timeSheetProjectAssignSummationReport(report);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(timeSheetProjectAssignList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/timeSheetReports/summation/project/TimeSheetProjectSummationReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/timeSheetReports/summation/project/TimeSheetProjectSummationReport.jasper");
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

    public List<TimeSheetProjectAssignVO> getTimeSheetProjectAssignList() {
        return timeSheetProjectAssignList;
    }

    public void setTimeSheetProjectAssignList(List<TimeSheetProjectAssignVO> timeSheetProjectAssignList) {
        this.timeSheetProjectAssignList = timeSheetProjectAssignList;
    }

    public ArrayList getTimeSheetReportList() {
        return timeSheetReportList;
    }

    public void setTimeSheetReportList(ArrayList timeSheetReportList) {
        this.timeSheetReportList = timeSheetReportList;
    }

    public TimeSheetCategoryAssignService getTimesheetCategoryAssignService() {
        return timesheetCategoryAssignService;
    }

    public void setTimesheetCategoryAssignService(TimeSheetCategoryAssignService timesheetCategoryAssignService) {
        this.timesheetCategoryAssignService = timesheetCategoryAssignService;
    }
}
