package com.gits.rms.action.reports;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.vo.ReportTimeSheetDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;


public class ReportsTimeSheetSummationAction extends ActionSupport {

	private ReportsVO report;
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;
	private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;
	private TimeSheetCategoryAssignService timesheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
	
	private ArrayList timeSheetReportList=new ArrayList();
	
    
    public String execute() throws Exception {
        try {
        	
        	Double projectTotal=0.0;
        	Double categoryTotal=0.0;
        	String projectName="";
        	String categoryName="";
        	
        	timeSheetProjectAssignList = timesheetCategoryAssignService.timeSheetProAssignProjectSummationReport(report);
//        	timeSheetCategoryAssignList = timesheetCategoryAssignService.timeSheetCategoryAssignSummationReport(report);
        	TimeSheetProjectAssignVO timeSheetProjectAssign = new TimeSheetProjectAssignVO();
//        	TimeSheetCategoryAssignVO timeSheetCategoryAssign = new TimeSheetCategoryAssignVO();
        	ReportTimeSheetDislayVO reportTimesheetDisplay = new ReportTimeSheetDislayVO();
        	
        	HashSet projectNameHashSet = new HashSet();
//        	HashSet catNameHashSet = new HashSet();
        	
        	if(!timeSheetProjectAssignList.isEmpty()){
        		for (Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator(); it.hasNext();) {
            		timeSheetProjectAssign = (TimeSheetProjectAssignVO) it.next();
            		projectTotal += timeSheetProjectAssign.getEnterTime();
            		projectNameHashSet.add(timeSheetProjectAssign.getProjectName().getProjectName());
            	}
        		
        		if(!projectNameHashSet.isEmpty()){
        			for (Iterator iter = projectNameHashSet.iterator(); iter.hasNext();) {
        				projectName += (String)iter.next()+",";
            		}
        			projectName = projectName.substring(0,projectName.lastIndexOf(","));
        		}
        	}
        	
//        	if(!timeSheetCategoryAssignList.isEmpty()){
//        		for (Iterator it = timeSheetCategoryAssignList.iterator(); it.hasNext();) {
//            		timeSheetCategoryAssign = (TimeSheetCategoryAssignVO) it.next();
//            		categoryTotal += timeSheetCategoryAssign.getEnterTime();
//            		catNameHashSet.add(timeSheetCategoryAssign.getTimesheetCategoryName().getName());
//            	}
//        		
//        		if(!catNameHashSet.isEmpty()){
//        			for (Iterator iter = catNameHashSet.iterator(); iter.hasNext();) {
//        				categoryName += (String)iter.next()+",";
//            		}
//        			categoryName = categoryName.substring(0,categoryName.lastIndexOf(","));
//        		}
//        	}
        	
        	if((!timeSheetProjectAssignList.isEmpty())){
        		
//        		if(!timeSheetProjectAssignList.isEmpty()){
        			reportTimesheetDisplay.setEmpName(timeSheetProjectAssign.getEmployeeName().getEmpFullName());
//        		}else if (!timeSheetCategoryAssignList.isEmpty()) {
//        			reportTimesheetDisplay.setEmpName(timeSheetProjectAssign.getEmployeeName().getEmpFullName());
//				}
        		reportTimesheetDisplay.setProjectTotalHour(projectTotal.toString());
        		reportTimesheetDisplay.setProjectName(projectName);
//        		reportTimesheetDisplay.setCategoryTotalHour(categoryTotal);
//        		reportTimesheetDisplay.setCategoryName(categoryName);
        		reportTimesheetDisplay.setStatus(report.getTimeSheetType());
        		timeSheetReportList.add(reportTimesheetDisplay);
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(timeSheetReportList);
        	JRDataSource jrDataSource = dataSource;
            JasperCompileManager.compileReportToFile(
            		getText("ApplicationAbsolutePath")+ServletActionContext.getServletContext().getContextPath()+getText("WebContent")+"resources/reports/timeSheetReports/projectSummation/TimeSheetSummationReport.jrxml",
            		getText("ApplicationAbsolutePath")+ServletActionContext.getServletContext().getContextPath()+getText("WebContent")+"resources/reports/timeSheetReports/projectSummation/TimeSheetSummationReport.jasper");
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

	public List<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
		return timeSheetCategoryAssignList;
	}

	public void setTimeSheetCategoryAssignList(	List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
		this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
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
