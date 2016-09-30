
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsEmployeeAction extends ActionSupport {

    private ReportsVO report = new ReportsVO();
    private List<ReportsVO> reportsList;
    private Collection<EmployeesVO> empList;
    private EmployeesVO emp;
    private EmployeesService empService = new EmployeesDaoService();

    @Override
    public String execute() throws Exception {
        try {
            empList = empService.getEmployeeReports(report);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/employeeReports/EmployeesReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/employeeReports/EmployeesReport.jasper");
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

    public Collection<EmployeesVO> getEmpList() {
        return empList;
    }

    public void setEmpList(Collection<EmployeesVO> empList) {
        this.empList = empList;
    }

    public EmployeesVO getEmp() {
        return emp;
    }

    public void setEmp(EmployeesVO emp) {
        this.emp = emp;
    }

    public EmployeesService getEmpService() {
        return empService;
    }

    public void setEmpService(EmployeesService empService) {
        this.empService = empService;
    }
}
