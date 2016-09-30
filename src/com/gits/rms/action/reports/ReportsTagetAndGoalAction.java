
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.EmpTargetAndGoalVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ReportTimeEnteredDislayVO;
import com.gits.rms.vo.ReportsVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.TimesheetAchivedTargetReportDisplayVO;
import com.gits.rms.vo.TimesheetAssignProjectTargetVO;

public class ReportsTagetAndGoalAction extends ActionSupport {

    private ReportsVO report = new ReportsVO();
    
    private TargetsService targetService = new TargetsDaoService();
    private List<TimesheetAssignProjectTargetVO> targetAndGoalList;
    
    private List<TimesheetAchivedTargetReportDisplayVO> assignProjectList;
    
    @Override
    public String execute() throws Exception {
    	Map session = ActionContext.getContext().getSession();
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
            
            if(session.get("ROLE").toString().toLowerCase().equals("admin")){
            	if (report.getEmpObj().getEmployeeId() == null) {
                    addActionError("Please Select A Employee");
                    return INPUT;
                }
            }
            assignProjectList = targetService.getTargetAndGoalReport(report);
            
//            targetAndGoalList = targetService.getTargetAndGoalReport(report);
            
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assignProjectList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "/resources/reports/targetAndGoals/TargetAndGoals.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/targetAndGoals/TargetAndGoals.jasper");
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

	public List<TimesheetAssignProjectTargetVO> getTargetAndGoalList() {
		return targetAndGoalList;
	}

	public void setTargetAndGoalList(
			List<TimesheetAssignProjectTargetVO> targetAndGoalList) {
		this.targetAndGoalList = targetAndGoalList;
	}
	
}
