
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsLeaveHistAction extends ActionSupport {

    private Collection<LeaveHistoryVO> leaveList;
    private LeaveHistoryService leaveService = new LeaveHistoryDaoService();
    private ReportsVO report = new ReportsVO();
    private LeaveHistoryVO lhist;

    @Override
    public String execute() throws Exception {

        // Normally we would provide a pre-compiled .jrxml file
        try {
            // Get All Leave Reports
            leaveList = leaveService.getLeaveHistReports(report);
            for (Iterator<LeaveHistoryVO> it = leaveList.iterator(); it.hasNext();) {
                lhist = it.next();
                String noOfDays = lhist.getNoOfDays().toString();
                noOfDays = noOfDays.replace(".00", "");
                String noOfHours = lhist.getHours().toString();
                noOfHours = noOfHours.replace(".00", "");
                String noOfMins = lhist.getMins().toString();
                noOfMins = noOfMins.replace(".00", "");
                lhist.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                    + noOfMins + "  Minutes");
            }
            new JRBeanCollectionDataSource(leaveList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/leaveReports/LeaveReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/leaveReports/LeaveReport.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public Collection<LeaveHistoryVO> getLeaveList() {
        return leaveList;
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public LeaveHistoryVO getLhist() {
        return lhist;
    }

    public void setLhist(LeaveHistoryVO lhist) {
        this.lhist = lhist;
    }

}
