
package com.gits.rms.action.reports;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.service.PayStubDaoService;
import com.gits.rms.service.PayStubService;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;
import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ReportPayStubDisplayVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsPayStubAction extends ActionSupport {

    private ReportsVO report = new ReportsVO();
    private ArrayList finalPayStubReportList = null;
    private PayStubService payStubService = new PayStubDaoService();
    private List<EmployeesVO> employeeList;
    private List<PayStubVO> payStubList;
    private List<EmployeeLeaveQuotaVO> employeeLeaveQuotaList;
    private List<LeaveReqsApprovalVO> employeeReqApprovalList;

    private EmployeesVO empObj = new EmployeesVO();
    private PayStubVO payStubObj = new PayStubVO();
    private EmployeeLeaveQuotaVO empLeaveQuotaObj = new EmployeeLeaveQuotaVO();
    private LeaveReqsApprovalVO empLeaveReqApprovalObj = new LeaveReqsApprovalVO();
    private HashMap<String, Object> map = new HashMap<String, Object>();
    private Map reportParameters = new HashMap();
    private ReportPayStubDisplayVO reportPayStubDisplayObj = new ReportPayStubDisplayVO();

    @Override
    public String execute() throws Exception {
        try {

            if (report.getDate() == null) {
                addActionError(getText("errors.report.paystub.date"));
                return INPUT;
            } else {

                finalPayStubReportList = new ArrayList();
                Date date = new Date();

                BigDecimal totalDaysLeaveQuota = new BigDecimal(0f);
                BigDecimal totalHoursLeaveQuota = new BigDecimal(0f);
                BigDecimal totalMinsLeaveQuota = new BigDecimal(0f);

                BigDecimal totalDaysLeaveReq = new BigDecimal(0f);
                BigDecimal totalHoursLeaveReq = new BigDecimal(0f);
                BigDecimal totalMinsLeaveReq = new BigDecimal(0f);

                BigDecimal totHrsPerDayLeaveQuota = new BigDecimal(8);
                BigDecimal totHrsPerMinLeaveQuota = new BigDecimal(60);

                BigDecimal totHrsPerDayLeaveReq = new BigDecimal(8);
                BigDecimal totHrsPerMinLeaveReq = new BigDecimal(60);

                BigDecimal daysToMinsLeaveQuota;
                BigDecimal hoursToMinsLeaveQuota;
                BigDecimal MinsLeaveQuota;
                BigDecimal totMinsAllottedLeaveQuota;
                BigDecimal totConHrsLeaveQuota;
                BigDecimal conHrsToDaysLeaveQuota;

                BigDecimal daysToMinsLeaveReq;
                BigDecimal hoursToMinsLeaveReq;
                BigDecimal MinsLeaveReq;
                BigDecimal totMinsAllottedLeaveReq;
                BigDecimal totConHrsLeaveReq;
                BigDecimal conHrsToDaysLeaveReq;

                String keyLeaveQuota = "";
                String valLeaveQuota = "";
                String finalDayLeaveQuota = "";
                String daysToHrsLeaveQuota = "";
                BigDecimal daysLeaveQuota;
                BigDecimal hrsLeaveQuota;
                BigDecimal minsLeaveQuota;

                String keyLeaveReq = "";
                String valLeaveReq = "";
                String finalDayLeaveReq = "";
                String daysToHrsLeaveReq = "";
                BigDecimal daysLeaveReq;
                BigDecimal hrsLeaveReq;
                BigDecimal minsLeaveReq;

                employeeList = payStubService.getPayStubEmployeeReports(report);
                payStubList = payStubService.getPayStubReports(report);
                employeeLeaveQuotaList = payStubService.getPayStubLeaveQuotaReports(report);
                employeeReqApprovalList = payStubService.getPayStubLeaveReqApprovalReports(report);

                date = report.getDate();
                SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
                SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
                reportPayStubDisplayObj.setReportDate(sdfMonth.format(date) + "-"
                    + sdfYear.format(date));

                Calendar cal = Calendar.getInstance();
                cal.setTime(report.getDate());
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                reportPayStubDisplayObj.setReportDateMonth(month + 1);
                reportPayStubDisplayObj.setReportDateYear(year);

                if (!(employeeList.isEmpty())) {
                    for (Iterator<EmployeesVO> itEmp = employeeList.iterator(); itEmp.hasNext();) {
                        empObj = itEmp.next();

                        reportPayStubDisplayObj.setEmpName(empObj.getEmpFirstName());
                        reportPayStubDisplayObj.setEmployeeID(empObj.getEmployeeId());
                        reportPayStubDisplayObj.setEmpEmailId(empObj.getEmpWorkEmail());
                        reportPayStubDisplayObj.setEmpDeptName(empObj.getDepartmentIdObj().getDeptName());
                        reportPayStubDisplayObj.setEmpJobTitle(empObj.getJobTitleIdObj().getJobTitleName());
                        reportPayStubDisplayObj.setEmpJoiningDate(empObj.getEmpJoineddate());
                    }
                }

                if (!(payStubList.isEmpty())) {
                    for (Iterator<PayStubVO> itPaystub = payStubList.iterator(); itPaystub.hasNext();) {
                        payStubObj = itPaystub.next();

                        reportPayStubDisplayObj.setPayStubID(payStubObj.getPayStubId());
                        reportPayStubDisplayObj.setGrossSalary(payStubObj.getGrossSalary());
                        reportPayStubDisplayObj.setNetSalary(payStubObj.getNetSalary());
                        reportPayStubDisplayObj.setDeclarationDate(payStubObj.getDeclarationDate());
                    }
                }

                if (!(employeeLeaveQuotaList.isEmpty())) {
                    for (Iterator<EmployeeLeaveQuotaVO> itLeaveQuota = employeeLeaveQuotaList.iterator(); itLeaveQuota.hasNext();) {
                        empLeaveQuotaObj = itLeaveQuota.next();

                        totalDaysLeaveQuota = totalDaysLeaveQuota.add(empLeaveQuotaObj.getRemainDays());
                        totalHoursLeaveQuota = totalHoursLeaveReq.add(empLeaveQuotaObj.getRemainHours());
                        totalMinsLeaveQuota = totalMinsLeaveQuota.add(empLeaveQuotaObj.getRemainMinutes());
                    }

                    // Calculation to convert allotted days to mins
                    daysToMinsLeaveQuota = totHrsPerDayLeaveQuota.multiply(totalDaysLeaveQuota.multiply(totHrsPerMinLeaveQuota));
                    hoursToMinsLeaveQuota = totalHoursLeaveQuota.multiply(totHrsPerMinLeaveQuota);
                    MinsLeaveQuota = totalMinsLeaveQuota;
                    totMinsAllottedLeaveQuota = daysToMinsLeaveQuota.add(hoursToMinsLeaveQuota).add(MinsLeaveQuota);

                    // converted minutes to hours
                    totConHrsLeaveQuota = totMinsAllottedLeaveQuota.divide(totHrsPerMinLeaveQuota);

                    // converted hours value set to string
                    String conHourssplitter = totConHrsLeaveQuota.toString();

                    // split the value after dot
                    StringTokenizer st = new StringTokenizer(conHourssplitter, ".");
                    while (st.hasMoreTokens()) {
                        keyLeaveQuota = st.nextToken();
                        valLeaveQuota = st.nextToken();
                    }

                    // convert the resulted hrs to days
                    conHrsToDaysLeaveQuota = new BigDecimal(keyLeaveQuota).divide(totHrsPerDayLeaveQuota);

                    String hrsToDaysplitter = conHrsToDaysLeaveQuota.toString();
                    Boolean b = hrsToDaysplitter.contains(".");
                    if (b == false) {
                        hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                    }

                    StringTokenizer hrsToDayToken = new StringTokenizer(hrsToDaysplitter, ".");
                    while (hrsToDayToken.hasMoreTokens()) {
                        finalDayLeaveQuota = hrsToDayToken.nextToken();
                        daysToHrsLeaveQuota = hrsToDayToken.nextToken();
                    }

                    // final days
                    daysLeaveQuota = new BigDecimal(finalDayLeaveQuota);
                    daysToHrsLeaveQuota = "." + daysToHrsLeaveQuota;

                    // final hours
                    hrsLeaveQuota = new BigDecimal(daysToHrsLeaveQuota).multiply(totHrsPerDayLeaveQuota);
                    valLeaveQuota = "." + valLeaveQuota;

                    // final minutes
                    minsLeaveQuota = new BigDecimal(valLeaveQuota).multiply(totHrsPerMinLeaveQuota);

                    String noOfDaysRemaining = daysLeaveQuota.toString();
                    String noOfHoursRemaining = hrsLeaveQuota.toString();
                    String noOfMinsRemaining = minsLeaveQuota.toString();
                    String remainingDays = "";
                    String remainingHours = "";
                    String remainingMins = "";

                    if (noOfDaysRemaining.contains(".")) {
                        StringTokenizer noOfDaysRemainingToken = new StringTokenizer(noOfDaysRemaining, ".");
                        while (noOfDaysRemainingToken.hasMoreTokens()) {
                            remainingDays = noOfDaysRemainingToken.nextToken();
                            noOfDaysRemainingToken.nextToken();
                        }
                    } else {
                        remainingDays = daysLeaveQuota.toString();
                    }

                    if (noOfHoursRemaining.contains(".")) {
                        StringTokenizer noOfHoursToken = new StringTokenizer(noOfHoursRemaining, ".");
                        while (noOfHoursToken.hasMoreTokens()) {
                            remainingHours = noOfHoursToken.nextToken();
                            noOfHoursToken.nextToken();
                        }
                    } else {
                        remainingDays = hrsLeaveQuota.toString();
                    }

                    if (noOfMinsRemaining.contains(".")) {
                        StringTokenizer noOfMinsToken = new StringTokenizer(noOfMinsRemaining, ".");
                        while (noOfMinsToken.hasMoreTokens()) {
                            remainingMins = noOfMinsToken.nextToken();
                            noOfMinsToken.nextToken();
                        }
                    } else {
                        remainingMins = minsLeaveQuota.toString();
                    }

                    reportPayStubDisplayObj.setLeaveRemainingCount(remainingDays + "  Days" + ","
                        + remainingHours + "  Hours" + "," + remainingMins + "  Minutes");
                }

                if (!(employeeReqApprovalList.isEmpty())) {
                    for (Iterator<LeaveReqsApprovalVO> itLeaveApproval = employeeReqApprovalList.iterator(); itLeaveApproval.hasNext();) {
                        empLeaveReqApprovalObj = itLeaveApproval.next();

                        totalDaysLeaveReq = totalDaysLeaveReq.add(empLeaveReqApprovalObj.getNoOfDays());
                        totalHoursLeaveReq = totalHoursLeaveReq.add(empLeaveReqApprovalObj.getHours());
                        totalMinsLeaveReq = totalMinsLeaveReq.add(empLeaveReqApprovalObj.getMins());
                    }

                    // Calculation to convert allotted days to mins
                    daysToMinsLeaveReq = totHrsPerDayLeaveReq.multiply(totalDaysLeaveReq.multiply(totHrsPerMinLeaveReq));
                    hoursToMinsLeaveReq = totalHoursLeaveReq.multiply(totHrsPerMinLeaveReq);
                    MinsLeaveReq = totalMinsLeaveReq;
                    totMinsAllottedLeaveReq = daysToMinsLeaveReq.add(hoursToMinsLeaveReq).add(MinsLeaveReq);

                    // converted minutes to hours
                    totConHrsLeaveReq = totMinsAllottedLeaveReq.divide(totHrsPerMinLeaveReq);

                    // converted hours value set to string
                    String conHourssplitter = totConHrsLeaveReq.toString();

                    // split the value after dot
                    StringTokenizer st = new StringTokenizer(conHourssplitter, ".");
                    while (st.hasMoreTokens()) {
                        keyLeaveReq = st.nextToken();
                        valLeaveReq = st.nextToken();
                    }

                    // convert the resulted hrs to days
                    conHrsToDaysLeaveReq = new BigDecimal(keyLeaveReq).divide(totHrsPerDayLeaveReq);

                    String hrsToDaysplitter = conHrsToDaysLeaveReq.toString();
                    Boolean b = hrsToDaysplitter.contains(".");
                    if (b == false) {
                        hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                    }

                    StringTokenizer hrsToDayToken = new StringTokenizer(hrsToDaysplitter, ".");
                    while (hrsToDayToken.hasMoreTokens()) {
                        finalDayLeaveReq = hrsToDayToken.nextToken();
                        daysToHrsLeaveReq = hrsToDayToken.nextToken();
                    }

                    // final days
                    daysLeaveReq = new BigDecimal(finalDayLeaveReq);
                    daysToHrsLeaveReq = "." + daysToHrsLeaveReq;

                    // final hours
                    hrsLeaveReq = new BigDecimal(daysToHrsLeaveReq).multiply(totHrsPerDayLeaveReq);
                    valLeaveReq = "." + valLeaveReq;

                    // final minutes
                    minsLeaveReq = new BigDecimal(valLeaveReq).multiply(totHrsPerMinLeaveReq);

                    String noOfDaysTaken = daysLeaveReq.toString();
                    String noOfHoursTaken = hrsLeaveReq.toString();
                    String noOfMinsTaken = minsLeaveReq.toString();
                    String leaveTakenDays = "";
                    String leaveTakenHours = "";
                    String leaveTakenMins = "";

                    if (noOfDaysTaken.contains(".")) {
                        StringTokenizer noOfDaysTakenToken = new StringTokenizer(noOfDaysTaken, ".");
                        while (noOfDaysTakenToken.hasMoreTokens()) {
                            leaveTakenDays = noOfDaysTakenToken.nextToken();
                            noOfDaysTakenToken.nextToken();
                        }
                    } else {
                        leaveTakenDays = daysLeaveReq.toString();
                    }

                    if (noOfHoursTaken.contains(".")) {
                        StringTokenizer noOfHoursToken = new StringTokenizer(noOfHoursTaken, ".");
                        while (noOfHoursToken.hasMoreTokens()) {
                            leaveTakenHours = noOfHoursToken.nextToken();
                            noOfHoursToken.nextToken();
                        }
                    } else {
                        leaveTakenHours = hrsLeaveReq.toString();
                    }

                    if (noOfMinsTaken.contains(".")) {
                        StringTokenizer noOfMinsToken = new StringTokenizer(noOfMinsTaken, ".");
                        while (noOfMinsToken.hasMoreTokens()) {
                            leaveTakenMins = noOfMinsToken.nextToken();
                            noOfMinsToken.nextToken();
                        }
                    } else {
                        leaveTakenMins = minsLeaveReq.toString();
                    }

                    reportPayStubDisplayObj.setLeaveTakenCount(leaveTakenDays + "  Days" + ","
                        + leaveTakenHours + "  Hours" + "," + leaveTakenMins + "  Minutes");
                }

                String reportsDirPath = getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent") + "/resources/reports/paystubReports/";
                finalPayStubReportList.add(reportPayStubDisplayObj);
                reportParameters.put("SUBREPORT_DIR", reportsDirPath);
                reportParameters.put("HIBERNATE_SESSION", HibernateUtil.getSession());

                JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportDeduction.jrxml", getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportDeduction.jasper");

                JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportTimesheet.jrxml", getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportTimesheet.jasper");

                JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportTimesheetCategory.jrxml", getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport_subreportTimesheetCategory.jasper");

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(finalPayStubReportList);
                JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport.jrxml", getText("ApplicationAbsolutePath")
                    + ServletActionContext.getServletContext().getContextPath()
                    + getText("WebContent")
                    + "resources/reports/paystubReports/PayStubReport.jasper");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public ArrayList getFinalPayStubReportList() {
        return finalPayStubReportList;
    }

    public void setFinalPayStubReportList(ArrayList finalPayStubReportList) {
        this.finalPayStubReportList = finalPayStubReportList;
    }

    public PayStubVO getPayStubObj() {
        return payStubObj;
    }

    public void setPayStubObj(PayStubVO payStubObj) {
        this.payStubObj = payStubObj;
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public List<PayStubVO> getPayStubList() {
        return payStubList;
    }

    public void setPayStubList(List<PayStubVO> payStubList) {
        this.payStubList = payStubList;
    }

    public ReportPayStubDisplayVO getReportPayStubDisplayObj() {
        return reportPayStubDisplayObj;
    }

    public void setReportPayStubDisplayObj(ReportPayStubDisplayVO reportPayStubDisplayObj) {
        this.reportPayStubDisplayObj = reportPayStubDisplayObj;
    }

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }

    public Map getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(Map reportParameters) {
        this.reportParameters = reportParameters;
    }

}
