
package com.gits.rms.action.reports;

import java.util.Collection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;

public class ReportsTimeSheetCategorySummationAction extends ActionSupport {

    private ReportsVO report;
    private TimeSheetCategoryAssignService timesheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private Collection<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;

    @Override
    public String execute() throws Exception {
        try {

            timeSheetCategoryAssignList = timesheetCategoryAssignService.timeSheetCategoryAssignSummationReport(report);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(timeSheetCategoryAssignList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/timeSheetReports/summation/category/TimeSheetCategorySummationReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/timeSheetReports/summation/category/TimeSheetCategorySummationReport.jasper");
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

    public Collection<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
        return timeSheetCategoryAssignList;
    }

    public void setTimeSheetCategoryAssignList(Collection<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
        this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
    }

    public TimeSheetCategoryAssignService getTimesheetCategoryAssignService() {
        return timesheetCategoryAssignService;
    }

    public void setTimesheetCategoryAssignService(TimeSheetCategoryAssignService timesheetCategoryAssignService) {
        this.timesheetCategoryAssignService = timesheetCategoryAssignService;
    }
}
