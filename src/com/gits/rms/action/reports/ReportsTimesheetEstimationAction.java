
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsTimesheetEstimationAction extends ActionSupport {

    private ReportsVO report = new ReportsVO();
    private List<ReportsVO> reportsList;
    
    private TimesheetProjectDaoService timesheetProjService = new TimesheetProjectDaoService();
    private List<ReportTimeEnteredDislayVO> enteredTimeDisplayList;
    private Collection<ReportTimeEnteredDislayVO> timeDisplayList;
    private ReportTimeEnteredDislayVO enteredTimeDisplayObj;

    @Override
    public String execute() throws Exception {
        try {
        	
        	if (report.getFromDate() == null) {
                addActionError(getText("errors.report.timeEstimation.fromDate"));
                return INPUT;
            }
        	
            if (report.getToDate() == null) {
            	addActionError(getText("errors.report.timeEstimation.toDate"));
                return INPUT;
            }
            
            if (report.getToDate().before(report.getFromDate())) {
                addActionError("To Date must be after the From date");
                return INPUT;
            }
            
            enteredTimeDisplayList = timesheetProjService.getTimeEstimationReport(report);
            
            for (Iterator<ReportTimeEnteredDislayVO> it = enteredTimeDisplayList.iterator(); it.hasNext();) {
            	enteredTimeDisplayObj = (ReportTimeEnteredDislayVO)it.next();
            	
            	if( enteredTimeDisplayObj.getEstimatedHours() !=null ){
            		if(enteredTimeDisplayObj.getTotalEnteredTime() !=null){
            			Double estimatedHour = Double.valueOf(enteredTimeDisplayObj.getEstimatedHours());
            			Double enteredHour = enteredTimeDisplayObj.getTotalEnteredTime();
            			
            			if(estimatedHour > enteredHour){
            				enteredTimeDisplayObj.setOverTimeHours(0d);
            				enteredTimeDisplayObj.setTimeRemaining(estimatedHour-enteredHour);
            				enteredTimeDisplayObj.setStatus("Normal");
            			}else if(estimatedHour < enteredHour){
            				enteredTimeDisplayObj.setOverTimeHours(enteredHour-estimatedHour);
            				enteredTimeDisplayObj.setTimeRemaining(0d);
            				enteredTimeDisplayObj.setStatus("Over time");
            			}
            			
            		}
            		
            	}
            }
            
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(enteredTimeDisplayList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/timeSheetReports/estimationReport/TimeSheetEstimationReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/timeSheetReports/estimationReport/TimeSheetEstimationReport.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return INPUT;
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

	public List<ReportTimeEnteredDislayVO> getEnteredTimeDisplayList() {
		return enteredTimeDisplayList;
	}

	public void setEnteredTimeDisplayList(
			List<ReportTimeEnteredDislayVO> enteredTimeDisplayList) {
		this.enteredTimeDisplayList = enteredTimeDisplayList;
	}

	public Collection<ReportTimeEnteredDislayVO> getTimeDisplayList() {
		return timeDisplayList;
	}

	public void setTimeDisplayList(
			Collection<ReportTimeEnteredDislayVO> timeDisplayList) {
		this.timeDisplayList = timeDisplayList;
	}

	public ReportTimeEnteredDislayVO getEnteredTimeDisplayObj() {
		return enteredTimeDisplayObj;
	}

	public void setEnteredTimeDisplayObj(
			ReportTimeEnteredDislayVO enteredTimeDisplayObj) {
		this.enteredTimeDisplayObj = enteredTimeDisplayObj;
	}

    
}
