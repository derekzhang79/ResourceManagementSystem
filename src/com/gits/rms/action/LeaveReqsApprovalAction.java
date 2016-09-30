
package com.gits.rms.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeeLeaveQuotaDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.service.LeaveReqsApprovalDaoService;
import com.gits.rms.service.LeaveReqsApprovalService;
import com.gits.rms.service.LeaveTypeDaoService;
import com.gits.rms.service.LeaveTypeService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.SignatureVO;

public class LeaveReqsApprovalAction extends ActionSupport {
    private static final long serialVersionUID = 6382414953781261817L;
    private LeaveReqsApprovalService leaveReqsApprovalService = new LeaveReqsApprovalDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private LeaveTypeService leaveTypeService = new LeaveTypeDaoService();
    private LeaveHistoryService leaveHistoryService = new LeaveHistoryDaoService();
    private LeaveReqsApprovalVO lrapp = new LeaveReqsApprovalVO();
    private LeaveHistoryVO lhist = new LeaveHistoryVO();
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private EmployeeLeaveQuotaVO empLeaveQuota = new EmployeeLeaveQuotaVO();
    private EmployeeLeaveQuotaService empLeaveService = new EmployeeLeaveQuotaDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmployeesVO> empIdObj;
    private EmployeesVO emps;
    private List<LeaveTypeVO> leaveTypeIdObj;
    private LeaveTypeVO ltype;
    private List<LeaveApproverVO> leaveApproverList;
    private List<EmployeeLeaveQuotaVO> empLeaveList;
    private List<LeaveReqsApprovalVO> lrappList;
    private EmployeeLeaveQuotaVO newEmpLeaveQuota;
    private String leaAppDate = "";
    private String leaModifiedDate = "";
    private String leaAppDisAppDate = "";
    private String leaveAppDis;
    private String leaveSelfApp;
    private String approved;
    private String disApproved;
    private String forApprovalCount = "";
    private String forApprovalToday = "";
    private String forApprovalThreeDays = "";
    private String forApprovalOneWeek = "";
    private String notes = "";
    private String employee;
    private String notesEmployeeId;

    // It shows LeaveModuleTab for Non-Approver(Employee)
    @SkipValidation
    public String getAllLeaveTab() {
        Map session = ActionContext.getContext().getSession();
        String sApprover = String.valueOf(session.get("LEAVE_APPROVER"));
        if (sApprover.equals("BOTH")) {
            return "redirect";
        } else {
            return SUCCESS;
        }
    }

    // It shows LeaveModuleTab for Approver
    @SkipValidation
    public String getAllSubEmpLeaveTab() {
        return SUCCESS;
    }

    // It shows form to search particular Employee in the Approve Tab
    @SkipValidation
    public String getAllEmpLeaveApprovalTab() {
        emp();
        return SUCCESS;
    }

    // It shows Employee List in the Dropdown field
    private void emp() {
        empIdObj = employeeService.getCurrentEmployee();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("empsList", empIdObj);
    }

    // It shows form with list of employee in the DisAppprove Tab
    @SkipValidation
    public String getAllEmpLeaveDisApprovalTab() {
        emp();
        return SUCCESS;
    }

    // It shows EmployeeName in the ForApproval Tab
    @SkipValidation
    public String getAllEmpLeaveForApprovalTab() {
        emp();
        return SUCCESS;
    }

    // It shows List of ForApproval of a particular Employee
    @SkipValidation
    public String getAllLeaveReqsApproval() {
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));

        lrappList = leaveReqsApprovalService.getAllLeaveReqsApproval(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        employee = this.getText("select.common.employee");
        lrapp.setLeaveMessageTitle(this.getText("label.title.Leave.leaveForApproval"));
        return SUCCESS;
    }

    // It shows Approver Name and their SubEmployee Name in the List in
    // AppoveTab
    @SkipValidation
    public String getAllLeaveApprovalTab() {
        subEmployeeList();
        return SUCCESS;
    }

    // It shows Approver Name and their subEmployeeName in DisApproveTab
    @SkipValidation
    public String getAllLeaveDisApprovalTab() {
        subEmployeeList();
        return SUCCESS;
    }

    // It shows Approver name and their SubEmployee Name in ForApprovalTab
    @SkipValidation
    public String getAllLeaveForApprovalTab() {
        subEmployeeList();
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        lrappList = leaveReqsApprovalService.getAllForApprovalList(lrapp);
        return SUCCESS;
    }

    // It shows List of Approver and their SubEmployee name in dropdown field
    private void subEmployeeList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        List employeeIdList = new LinkedList();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empIdObj = employeeService.getCurrentSubEmployee();

        EmployeesVO employee = new EmployeesVO();
        for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
            employee = it.next();
            employeeIdList.add(employee.getEmployeeId());
        }
        if (employeeIdList.contains(empSelf.getEmployeeId())) {
            request.setAttribute("empsList", empIdObj);
        } else {
            empIdObj.add(empSelf);
            request.setAttribute("empsList", empIdObj);
        }

    }

    // It shows Forapproval List in the ForApprovalTab
    @SkipValidation
    public String getAllSubEmpLeaveReqsApproval() {
        ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empVOObj = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        if (lrapp.getEmpIdObj().getEmployeeId().equals(empVOObj.getEmployeeId())) {
            LeaveApproverVO leaveAppId = leaveReqsApprovalService.getApproverId(empVOObj.getEmployeeId());
            if (leaveAppId != null) {
                leaveSelfApp = this.getText("label.header.leaveRequest.selfApprover");
            }
            leaveAppDis = this.getText("label.header.leaveRequest.approver");
        }
        lrappList = leaveReqsApprovalService.getAllSubEmpLeaveReqsApproval(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        return SUCCESS;
    }

    // It shows List of ForApproval Leaves
    @SkipValidation
    public String employeeLeaveApproved() {
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        lrappList = leaveReqsApprovalService.employeeLeaveApproved(lrapp);
        return SUCCESS;
    }

    // It shows List of Approved Leaves for Employee
    @SkipValidation
    public String getAllApprovedList() {
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.approved.value")));
        lrappList = leaveReqsApprovalService.getAllApprovedList(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        lrapp.setLeaveMessageTitle(this.getText("label.title.Leave.leaveApproved"));
        employee = this.getText("select.common.leaveReqStatus.approved.value");
        return SUCCESS;
    }

    // It Shows List of DisApproved Leaves
    @SkipValidation
    public String getAllDisApprovedList() {
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.disApproved.value")));
        lrappList = leaveReqsApprovalService.getAllDisApprovedList(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        lrapp.setLeaveMessageTitle(this.getText("label.title.Leave.leaveDisApproved"));
        employee = this.getText("select.common.leaveReqStatus.disApproved.value");
        return SUCCESS;
    }

    // It shows Approved Employee List
    @SkipValidation
    public String getAllSubEmpApprovedList() {
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.approved.value")));
        lrappList = leaveReqsApprovalService.getAllSubEmpApprovedList(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        approved = this.getText("label.title.lrapp.Approved");
        return SUCCESS;
    }

    // It shows List of DisApproved List in DisApproveTab
    @SkipValidation
    public String getAllSubEmpDisApprovedList() {
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.disApproved.value")));
        lrappList = leaveReqsApprovalService.getAllSubEmpDisApprovedList(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        disApproved = this.getText("label.title.lrapp.disApprove");
        return SUCCESS;
    }

    // when click Add LeaveRequestApproval it shows blank Form to enter New
    // LeaveRequest
    @SkipValidation
    public String setUpLeaveReqsApproval() {
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
        return SUCCESS;
    }

    // when click Edit LeaveRequestApproval it shows blank Form to enter New
    // LeaveRequest
    @SkipValidation
    public String setUpLeaveReqsApprovalEditForm() {
        BigDecimal hours;
        BigDecimal days;
        BigDecimal minutes;
        String hrsStr;
        String daysStr;
        String minsStr;
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
        if ((lrapp != null) && (lrapp.getHcmoLeaveReqsApprovalId() != null)) {
            lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
            hrsStr = lrapp.getHours().toString();
            hrsStr = hrsStr.replace(".00", "");
            hours = new BigDecimal(hrsStr);
            lrapp.setHours(hours);

            daysStr = lrapp.getNoOfDays().toString();
            daysStr = daysStr.replace(".00", "");
            days = new BigDecimal(daysStr);
            lrapp.setNoOfDays(days);

            minsStr = lrapp.getMins().toString();
            minsStr = minsStr.replace(".00", "");
            minutes = new BigDecimal(minsStr);
            lrapp.setMins(minutes);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String getLeaveForApprovalEmployee() {
        lrappList = leaveReqsApprovalService.getLeaveForApprovalEmployee(lrapp.getHcmoLeaveReqsApprovalId());
        return SUCCESS;
    }

    // This Method is used to add Notes for Leave DisApproved
    @SkipValidation
    public String leaveRequestDisApprovedNotes() {
        getNotesEmployeeId();
        return SUCCESS;
    }

    // This Method is used to add Notes for Leave Approved
    @SkipValidation
    public String leaveRequestApprovedNotes() {
        getNotesEmployeeId();
        return SUCCESS;
    }

    // This Method is used to Approve Employee Leave and enter updated data into
    // LeaveHistory and also Update NoofDays value into LeaveTaken field of
    // EmployeeLeaveQuota
    @SkipValidation
    public String leaveRequestapproved() {
        BigDecimal leaveReqTotalNoOfDays;
        BigDecimal leaveReqTotalNoOfHours;
        BigDecimal leaveReqTotalNoOfMins;
        BigDecimal totNoOfHoursPerDay;
        BigDecimal totNoOfMinPerDay;
        BigDecimal totNoOfMinRequest;
        BigDecimal totNoOfLeaveAlotted;
        BigDecimal totSubMin;
        BigDecimal totConvertedHours;
        String key = "";
        String val = "";
        BigDecimal conHrsToDays;
        String finalDay = "";
        String daysToHrs = "";
        BigDecimal days;
        BigDecimal hrs;
        BigDecimal mins;

        BigDecimal empLeavetotNoOfDays;
        BigDecimal empLeavetotNoOfHours;
        BigDecimal empLeavetotNoOfMins;

        BigDecimal empLeaveRequestedDays;
        BigDecimal empLeaveRequestedHours;
        BigDecimal empLeaveRequestedMinutes;
        BigDecimal totNoOfLeaveRequested;
        BigDecimal totLeaveReqMin;
        BigDecimal totConvertedLeaveHours;
        Boolean b;

        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        notes = lrapp.getApproveNotes();
        lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.approved.value")));
        lrapp.setHcmoApproverId(oEmp);
        lrapp.setApproveNotes(notes);
        lrapp.setDisApproveNotes("");
        EmployeeLeaveQuotaVO empLeaveQuota1 = new EmployeeLeaveQuotaVO();
        empLeaveQuota1 = empLeaveService.getEmployeeLeaveQuotaList(lrapp);

        if (empLeaveQuota1 == null) {
            newEmpLeaveQuota = new EmployeeLeaveQuotaVO();
            LeaveTypeVO leaveTypeObj = new LeaveTypeVO();
            leaveTypeObj.setLeaveTypeId(lrapp.getLeaveTypeIdObj().getLeaveTypeId());

            EmployeesVO employeeObj = new EmployeesVO();
            employeeObj.setEmployeeId(lrapp.getEmpIdObj().getEmployeeId());

            newEmpLeaveQuota.setLeaveTypeIdObj(leaveTypeObj);
            newEmpLeaveQuota.setEmpIdObj(employeeObj);
            newEmpLeaveQuota.setYear(lrapp.getDateApplied().getYear() + 1900);
            newEmpLeaveQuota.setNoOfDays(BigDecimal.ZERO);
            newEmpLeaveQuota.setLeaveTaken(BigDecimal.ZERO);
            newEmpLeaveQuota.setPrvYearCarryingForward(BigDecimal.ZERO);
            newEmpLeaveQuota.setLeaveCarryingForward(BigDecimal.ZERO);
            newEmpLeaveQuota.setCreated(DateUtils.getCurrentDateTime());
            EmployeesVO oEmpVO = new EmployeesVO();
            oEmpVO.setEmployeeId(1);
            newEmpLeaveQuota.setCreatedBy(oEmpVO);
            newEmpLeaveQuota.setUpdatedBy(oEmpVO);
            newEmpLeaveQuota.setIsActive(1);
            empLeaveService.insertEmployeeLeaveQuota(newEmpLeaveQuota);
            empLeaveQuota1 = newEmpLeaveQuota;
        }
        // EmployeeLeaveQuotaVO employeeLeaveQuota = new EmployeeLeaveQuotaVO();

        if ((lrapp.getLeaveTypeIdObj().getLeaveTypeId() != null)
            && (empLeaveQuota1.getLeaveTypeIdObj().getLeaveTypeId() != null)) {
            if (lrapp.getLeaveTypeIdObj().getLeaveTypeName().equals(empLeaveQuota1.getLeaveTypeIdObj().getLeaveTypeName())) {
                totNoOfHoursPerDay = new BigDecimal(8);
                totNoOfMinPerDay = new BigDecimal(60);

                leaveReqTotalNoOfDays = totNoOfHoursPerDay.multiply(lrapp.getNoOfDays().multiply(totNoOfMinPerDay));

                leaveReqTotalNoOfHours = lrapp.getHours().multiply(totNoOfMinPerDay);

                leaveReqTotalNoOfMins = lrapp.getMins();

                totNoOfMinRequest = leaveReqTotalNoOfDays.add(leaveReqTotalNoOfHours).add(leaveReqTotalNoOfMins);

                String remainDays = empLeaveQuota1.getRemainDays().toString();
                Boolean booremainDays = remainDays.contains("-");
                BigDecimal minus = new BigDecimal(-1);
                if (booremainDays == true) {
                    BigDecimal d = empLeaveQuota1.getRemainDays().multiply(minus);
                    empLeaveQuota1.setRemainDays(d);
                }
                String remainHrs = empLeaveQuota1.getRemainHours().toString();
                Boolean booremainHrs = remainHrs.contains("-");
                if (booremainHrs == true) {
                    BigDecimal h = empLeaveQuota1.getRemainHours().multiply(minus);
                    empLeaveQuota1.setRemainHours(h);
                }
                String remainMins = empLeaveQuota1.getRemainMinutes().toString();
                Boolean booremainMins = remainMins.contains("-");
                if (booremainMins == true) {
                    BigDecimal m = empLeaveQuota1.getRemainMinutes().multiply(minus);
                    empLeaveQuota1.setRemainMinutes(m);
                }

                empLeavetotNoOfDays = empLeaveQuota1.getRemainDays().multiply(totNoOfHoursPerDay).multiply(totNoOfMinPerDay);
                empLeavetotNoOfHours = empLeaveQuota1.getRemainHours().multiply(totNoOfMinPerDay);
                empLeavetotNoOfMins = empLeaveQuota1.getRemainMinutes();

                totNoOfLeaveAlotted = empLeavetotNoOfDays.add(empLeavetotNoOfHours).add(empLeavetotNoOfMins);

                if ((booremainDays == true) || (booremainHrs == true) || (booremainMins == true)) {
                    totNoOfLeaveAlotted = totNoOfLeaveAlotted.multiply(minus);
                }
                totSubMin = totNoOfLeaveAlotted.subtract(totNoOfMinRequest);

                totNoOfLeaveAlotted.toString();

                totConvertedHours = totSubMin.divide(totNoOfMinPerDay);

                String conHourssplitter = totConvertedHours.toString();
                b = conHourssplitter.contains(".");
                if (b == true) {
                } else {
                    conHourssplitter = conHourssplitter.concat(".00");
                }
                StringTokenizer st = new StringTokenizer(conHourssplitter, ".");
                while (st.hasMoreTokens()) {
                    key = st.nextToken();
                    val = st.nextToken();
                }

                conHrsToDays = new BigDecimal(key).divide(totNoOfHoursPerDay);
                String hrsToDaysplitter = conHrsToDays.toString();
                b = hrsToDaysplitter.contains(".");
                if (b == true) {
                } else {
                    hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                }
                StringTokenizer hrsToDayToken = new StringTokenizer(hrsToDaysplitter, ".");
                while (hrsToDayToken.hasMoreTokens()) {
                    finalDay = hrsToDayToken.nextToken();
                    daysToHrs = hrsToDayToken.nextToken();
                }

                days = new BigDecimal(finalDay);

                if (hrsToDaysplitter.contains("-")) {
                    if (days.toString().equals("0") || days.toString().equals("-0")) {
                        daysToHrs = "." + daysToHrs;
                        daysToHrs = "-" + daysToHrs;
                    } else {
                        daysToHrs = "." + daysToHrs;
                    }
                } else {
                    daysToHrs = "." + daysToHrs;
                }
                hrs = new BigDecimal(daysToHrs).multiply(totNoOfHoursPerDay);

                if (conHourssplitter.contains("-")) {
                    if (key.toString().equals("0") || key.toString().equals("-0")) {
                        val = "." + val;
                        val = "-" + val;
                    } else {
                        val = "." + val;
                    }
                } else {
                    val = "." + val;
                }
                mins = new BigDecimal(val).multiply(totNoOfMinPerDay);

                empLeaveQuota1.setRemainDays(days);
                empLeaveQuota1.setRemainHours(hrs);
                empLeaveQuota1.setRemainMinutes(mins);

                // Calculation to save no of days leave requested to get leave
                // taken value
                empLeaveRequestedDays = empLeaveQuota1.getLeaveTakenDays().multiply(totNoOfHoursPerDay).multiply(totNoOfMinPerDay);
                empLeaveRequestedHours = empLeaveQuota1.getLeaveTakenHours().multiply(totNoOfMinPerDay);
                empLeaveRequestedMinutes = empLeaveQuota1.getLeaveTakenMinutes();

                totNoOfLeaveRequested = empLeaveRequestedDays.add(empLeaveRequestedHours).add(empLeaveRequestedMinutes);
                totLeaveReqMin = totNoOfLeaveRequested.add(totNoOfMinRequest);
                totConvertedLeaveHours = totLeaveReqMin.divide(totNoOfMinPerDay);

                String conLeaveReqHourssplitter = totConvertedLeaveHours.toString();
                b = conLeaveReqHourssplitter.contains(".");
                if (b == true) {
                } else {
                    conLeaveReqHourssplitter = conLeaveReqHourssplitter.concat(".00");
                }
                StringTokenizer str = new StringTokenizer(conLeaveReqHourssplitter, ".");
                while (str.hasMoreTokens()) {
                    key = str.nextToken();
                    val = str.nextToken();
                }

                conHrsToDays = new BigDecimal(key).divide(totNoOfHoursPerDay);
                hrsToDaysplitter = conHrsToDays.toString();
                b = hrsToDaysplitter.contains(".");
                if (b == true) {
                } else {
                    hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                }
                StringTokenizer hrsToDayLeaveReq = new StringTokenizer(hrsToDaysplitter, ".");
                while (hrsToDayLeaveReq.hasMoreTokens()) {
                    finalDay = hrsToDayLeaveReq.nextToken();
                    daysToHrs = hrsToDayLeaveReq.nextToken();
                }

                days = new BigDecimal(finalDay);

                daysToHrs = "." + daysToHrs;
                hrs = new BigDecimal(daysToHrs).multiply(totNoOfHoursPerDay);
                val = "." + val;
                mins = new BigDecimal(val).multiply(totNoOfMinPerDay);

                empLeaveQuota1.setLeaveTakenDays(days);
                empLeaveQuota1.setLeaveTakenHours(hrs);
                empLeaveQuota1.setLeaveTakenMinutes(mins);
                empLeaveQuota1.setCreated(DateUtils.getCurrentDateTime());
                empLeaveQuota1.setCreatedBy(oEmp);
                empLeaveQuota1.setUpdatedBy(oEmp);
                empLeaveQuota1.setIsActive(1);
            }
            lhist.setLeaveStatus(this.getText("select.common.leaveHist.approved.value"));
            lhist.setLeaveRequestId(lrapp.getHcmoLeaveReqsApprovalId());
            lhist.setLeaveDate(lrapp.getDateApplied());
            lhist.setEmpIdObj(lrapp.getEmpIdObj());
            lhist.setLeaveComments(lrapp.getComments());
            lhist.setApproveNotes(notes);
            lhist.setStartTime((this.getText("select.common.leaveHist.startTime.value")));
            lhist.setEndTime((this.getText("select.common.leaveHist.endTime.value")));
            lhist.setCreated(DateUtils.getCurrentDateTime());
            lhist.setNoOfDays(lrapp.getNoOfDays());
            lhist.setHours(lrapp.getHours());
            lhist.setMins(lrapp.getMins());
            lhist.setCreatedBy(oEmp);
            lhist.setHcmoLeaveApproverId(oEmp);
            lhist.setLeaveTypeIdObj(lrapp.getLeaveTypeIdObj());
            lhist.setUpdatedBy(oEmp);

            lhist.setIsActive(1);
            leaveReqsApprovalService.approved(lrapp, empLeaveQuota1, lhist);

            ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            String sSubject = this.getText("message.subject.leave.approved");
            LeaveApproverVO newLeaveApprover = null;

            int selfAppId = lrapp.getEmpIdObj().getEmployeeId();
            int approvingEmpId = lrapp.getHcmoApproverId().getEmployeeId();
            if (selfAppId == approvingEmpId) {
                this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.selfApproved"), this.getText("message.common.myOwn.name"), sSubject);
            } else {
                leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(lrapp.getEmpIdObj().getEmployeeId());
                for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                    newLeaveApprover = it.next();
                    int leaveAppList = newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                    oEmp.getEmployeeId();
                    newLeaveApprover.getHcmoEmployeeId().getEmployeeId();
                    if (approvingEmpId == leaveAppList) {
                        // Approving Employee
                        this.mail(lrapp.getHcmoApproverId().getEmployeeId(), lrapp.getHcmoApproverId().getEmpFirstName(), this.getText("leave.approvedEmp.leaveRequest.approved"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);

                        // employee
                        this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.approved"), lrapp.getHcmoApproverId().getEmpFirstName(), sSubject);
                    } else if (approvingEmpId != leaveAppList) {
                        // Other leave approver
                        mailForAllApprover(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), this.getText("leave.allApprovers.leaveRequest.approved"), lrapp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage((this.getText("select.common.leaveApproved.value")));
        }
        return SUCCESS;
    }

    // This Method is used to DisApprove Employee Leave
    @SkipValidation
    public String leaveRequestdisapproved() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        notes = lrapp.getDisApproveNotes();
        lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
        lrapp.setLeaveReqStatus((this.getText("select.common.leaveReqStatus.disApproved.value")));
        lrapp.setHcmoApproverId(oEmp);
        lrapp.setApproveNotes("");
        lrapp.setDisApproveNotes(notes);
        lhist.setLeaveStatus(lrapp.getLeaveReqStatus());
        lhist.setLeaveRequestId(lrapp.getHcmoLeaveReqsApprovalId());
        lhist.setLeaveDate(lrapp.getDateApplied());
        lhist.setEmpIdObj(lrapp.getEmpIdObj());
        lhist.setLeaveComments(lrapp.getComments());
        lhist.setDisApproveNotes(notes);
        lhist.setStartTime((this.getText("select.common.leaveHist.startTime.value")));
        lhist.setEndTime((this.getText("select.common.leaveHist.endTime.value")));
        lhist.setNoOfDays(lrapp.getNoOfDays());
        lhist.setHours(lrapp.getHours());
        lhist.setMins(lrapp.getMins());
        lhist.setCreated(DateUtils.getCurrentDateTime());
        lhist.setCreatedBy(oEmp);
        lhist.setLeaveTypeIdObj(lrapp.getLeaveTypeIdObj());
        lhist.setEmpIdObj(lrapp.getEmpIdObj());
        lhist.setHcmoLeaveApproverId(oEmp);
        lhist.setUpdatedBy(oEmp);
        lhist.setIsActive(1);
        leaveReqsApprovalService.disApproved(lrapp, lhist);

        String sSubject = this.getText("message.subject.leave.rejected");
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        LeaveApproverVO newLeaveApprover = null;

        int selfAppId = lrapp.getEmpIdObj().getEmployeeId();
        int approvingEmpId = lrapp.getHcmoApproverId().getEmployeeId();
        if (selfAppId == approvingEmpId) {
            this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.selfApproved.rejected"), this.getText("message.common.myOwn.name"), sSubject);
        } else {
            leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(lrapp.getEmpIdObj().getEmployeeId());
            for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                newLeaveApprover = it.next();
                int leaveAppList = newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                oEmp.getEmployeeId();
                newLeaveApprover.getHcmoEmployeeId().getEmployeeId();
                if (approvingEmpId == leaveAppList) {
                    // Approving Employee
                    this.mail(lrapp.getHcmoApproverId().getEmployeeId(), lrapp.getHcmoApproverId().getEmpFirstName(), this.getText("leave.approvedEmp.leaveRequest.rejected"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);

                    // employee
                    this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.rejected"), lrapp.getHcmoApproverId().getEmpFirstName(), sSubject);
                } else if (approvingEmpId != leaveAppList) {
                    // Other leave approver
                    mailForAllApprover(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), this.getText("leave.allApprovers.leaveRequest.rejected"), lrapp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage((this.getText("select.common.leaveDisApproved.value")));
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new LeaveRequest
    public String insertOrUpdateLeaveReqsApproval() {
        BigDecimal totalNoOfDays;
        BigDecimal totalNoOfHours;
        BigDecimal totalNoOfMins;
        BigDecimal totNoOfHoursPerDay;
        BigDecimal totNoOfMinPerDay;
        Calendar calendar = new GregorianCalendar();
        calendar.get(Calendar.YEAR);
        if (lrapp.getHcmoLeaveReqsApprovalId() == null) {
            Date today = new Date();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            formatter.format(today);
            if (!(formatter.format(lrapp.getDateApplied()).equals(formatter.format(today)) || (DateUtils.isGreaterDate(lrapp.getDateApplied())))) {
                addActionError(this.getText("Please select a valid request date"));
                return INPUT;
            }
            if (lrapp.getNoOfDays().equals(new BigDecimal(0))
                && (lrapp.getHours().equals(new BigDecimal(0)) && lrapp.getMins().equals(new BigDecimal(0)))) {
                addActionError(this.getText("Please Specify either No Of Days (or) hours"));
                return INPUT;
            }
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

            Integer yearEntered = 1900 + lrapp.getDateApplied().getYear();
            LeaveTypeVO leaveName = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            empLeaveList = empLeaveService.getEmployeeLeaveQuotaList(lrapp, oEmp, yearEntered);
            if (empLeaveList.isEmpty()) {
                addActionError(this.getText("label.header.leaveRequest.checkleaveType")
                    + leaveName.getLeaveTypeName()
                    + this.getText("label.header.leaveRequest.checkleaveEmployee"));
                return INPUT;
            }
            leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(oEmp.getEmployeeId());
            if (leaveApproverList.isEmpty()) {
                addActionError(this.getText("label.header.leaveRequest.checkApprover"));
                return INPUT;
            }
            totNoOfHoursPerDay = new BigDecimal(8);
            totNoOfMinPerDay = new BigDecimal(60);
            totalNoOfDays = totNoOfHoursPerDay.multiply(lrapp.getNoOfDays().multiply(totNoOfMinPerDay));

            totalNoOfHours = lrapp.getHours().multiply(totNoOfMinPerDay);
            totalNoOfMins = lrapp.getMins();
            totalNoOfDays.add(totalNoOfHours).add(totalNoOfMins);

            new BigDecimal(0.125);
            lrapp.setEmpIdObj(oEmp);
            lrapp.setCreated(DateUtils.getCurrentDateTime());
            lrapp.setCreatedBy(oEmp);
            lrapp.setUpdatedBy(oEmp);
            lrapp.setIsActive(1);
            lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
            lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());

            leaveReqsApprovalService.insertLeaveReqsApproval(lrapp);
            LeaveHistoryVO leaveHis = new LeaveHistoryVO();
            leaveHis.setLeaveRequestId(lrapp.getHcmoLeaveReqsApprovalId());
            leaveHis.setStartTime((this.getText("select.common.leaveHist.startTime.value")));
            leaveHis.setEndTime((this.getText("select.common.leaveHist.endTime.value")));
            leaveHis.setLeaveStatus((this.getText("select.common.leaveHist.forapproval.value")));
            leaveHis.setLeaveDate(lrapp.getDateApplied());
            leaveHis.setEmpIdObj(lrapp.getEmpIdObj());
            leaveHis.setLeaveComments(lrapp.getComments());
            leaveHis.setLeaveTypeIdObj(lrapp.getLeaveTypeIdObj());
            leaveHis.setNoOfDays(lrapp.getNoOfDays());
            leaveHis.setHours(lrapp.getHours());
            leaveHis.setMins(lrapp.getMins());
            leaveHis.setCreated(DateUtils.getCurrentDateTime());
            leaveHis.setCreatedBy(oEmp);
            leaveHis.setUpdatedBy(oEmp);
            leaveHis.setIsActive(1);
            leaveHistoryService.insertLeaveHistory(leaveHis);

            LeaveApproverVO newLeaveApprover = null;
            leaveApproverList = leaveAppproverService.getAllLeaveApprover();
            lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
            LeaveApproverVO leaveSelfApp = leaveAppproverService.getSelfApprover(lrapp.getEmpIdObj().getEmployeeId());
            int selfAppId = lrapp.getEmpIdObj().getEmployeeId();
            int loggedInEmp = oEmp.getEmployeeId();
            String sSubject = this.getText("message.subject.leave.add");

            // Logged in person request a leave
            if (loggedInEmp == selfAppId) {
                this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.add"), sSubject);
            }
            if (leaveSelfApp != null) {
                int approvingEmpId = leaveSelfApp.getHcmoApprovingEmpId().getEmployeeId();
                if (selfAppId == approvingEmpId) {
                    // self approver will get the mail
                    this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.add"), sSubject);
                }
            } else {
                ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
                lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
                for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                    newLeaveApprover = it.next();
                    int leaveHistInt = lrapp.getEmpIdObj().getEmployeeId();
                    int newLeaveAppInt = newLeaveApprover.getHcmoEmployeeId().getEmployeeId();

                    if ((leaveHistInt) == (newLeaveAppInt)) {
                        // Other approvers will get a mail
                        this.mail(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), this.getText("leave.allApprovers.leaveRequest.add"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage(this.getText("Added Successfully"));
        } else {
            lrapp.setIsActive(1);
            lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
            lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
            leaveReqsApprovalService.updateLeaveReqsApproval(lrapp);
            addActionMessage(this.getText("Updated Successfully"));
        }
        return SUCCESS;
    }

    public String updateLeaveReqsApproval() {
        BigDecimal totalNoOfDays;
        BigDecimal totalNoOfHours;
        BigDecimal totalNoOfMins;
        BigDecimal totNoOfHoursPerDay;
        BigDecimal totNoOfMinPerDay;
        Date today = new Date();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.format(today);
        if (lrapp.getNoOfDays().equals(new BigDecimal(0))
            && (lrapp.getHours().equals(new BigDecimal(0)) && lrapp.getMins().equals(new BigDecimal(0)))) {
            addActionError(this.getText("Please Specify either No Of Days (or) hours"));
            return INPUT;
        }
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        Integer yearEntered = 1900 + lrapp.getDateApplied().getYear();
        LeaveTypeVO leaveName = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        empLeaveList = empLeaveService.getEmployeeLeaveQuotaList(lrapp, oEmp, yearEntered);
        if (empLeaveList.isEmpty()) {
            addActionError(this.getText("label.header.leaveRequest.checkleaveType")
                + leaveName.getLeaveTypeName()
                + this.getText("label.header.leaveRequest.checkleaveEmployee"));
            return INPUT;
        }
        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(oEmp.getEmployeeId());
        if (leaveApproverList.isEmpty()) {
            addActionError(this.getText("label.header.leaveRequest.checkApprover"));
            return INPUT;
        }
        totNoOfHoursPerDay = new BigDecimal(8);
        totNoOfMinPerDay = new BigDecimal(60);
        totalNoOfDays = totNoOfHoursPerDay.multiply(lrapp.getNoOfDays().multiply(totNoOfMinPerDay));

        totalNoOfHours = lrapp.getHours().multiply(totNoOfMinPerDay);
        totalNoOfMins = lrapp.getMins();
        totalNoOfDays.add(totalNoOfHours).add(totalNoOfMins);

        new BigDecimal(0.125);
        lrapp.setEmpIdObj(oEmp);
        lrapp.setCreated(DateUtils.getCurrentDateTime());
        lrapp.setCreatedBy(oEmp);
        lrapp.setUpdatedBy(oEmp);
        lrapp.setIsActive(1);
        lrapp.setLeaveReqStatus(this.getText("select.common.leaveReqStatus.forapproval.value"));
        lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
        leaveReqsApprovalService.updateLeaveReqsApproval(lrapp);

        LeaveApproverVO newLeaveApprover = null;
        leaveApproverList = leaveAppproverService.getAllLeaveApprover();
        lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
        LeaveApproverVO leaveSelfApp = leaveAppproverService.getSelfApprover(lrapp.getEmpIdObj().getEmployeeId());
        int selfAppId = lrapp.getEmpIdObj().getEmployeeId();
        int loggedInEmp = oEmp.getEmployeeId();
        String sSubject = this.getText("message.subject.leave.add");

        // Logged in person request a leave
        if (loggedInEmp == selfAppId) {
            this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.edit"), sSubject);
        }
        if (leaveSelfApp != null) {
            int approvingEmpId = leaveSelfApp.getHcmoApprovingEmpId().getEmployeeId();
            if (selfAppId == approvingEmpId) {
                // self approver will get the mail
                this.mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), this.getText("leave.employee.leaveRequest.edit"), sSubject);
            }
        } else {
            ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
            for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                newLeaveApprover = it.next();
                int leaveHistInt = lrapp.getEmpIdObj().getEmployeeId();
                int newLeaveAppInt = newLeaveApprover.getHcmoEmployeeId().getEmployeeId();

                if ((leaveHistInt) == (newLeaveAppInt)) {
                    // Other approvers will get a mail
                    this.mail(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), this.getText("leave.allApprovers.leaveRequest.edit"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage(this.getText("Updated Successfully"));
        return SUCCESS;

    }

    // LeaveApprove back button
    @SkipValidation
    public String getAllEmpAppBackLeaveTab() {
        return SUCCESS;
    }

    // LeaveDisApprove back button
    @SkipValidation
    public String getAllEmpDisAppBackLeaveTab() {
        return SUCCESS;
    }

    // LeaveEdit back button
    @SkipValidation
    public String getAllEmpEditBackLeaveTab() {
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + this.getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + this.getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + this.getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.dateApplied")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.dateAppDisApp1")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppDisAppDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (!(lrapp.getApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.approveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            if (!(lrapp.getDisApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.disApproveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getDisApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.noOfDays") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getLeaveRequested() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.comments") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + lrapp.getComments() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = this.getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and Minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + this.getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sDummy), sMessage.lastIndexOf(sDummy)
            + sDummy.length(), From);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + this.getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + this.getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.dateApplied")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.dateAppDisApp1")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppDisAppDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (!(lrapp.getApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.approveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            if (!(lrapp.getDisApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.disApproveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getDisApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.noOfDays") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getLeaveRequested() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.comments") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + lrapp.getComments() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = this.getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    public void mailForAllApprover(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and Minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

        String sDummy = Constants.PERSON;
        String sEmployee = Constants.EMPLOYEE_PERSON;
        String sLoggedIN = Constants.LOGGEDIN_PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + this.getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sEmployee), sMessage.lastIndexOf(sEmployee)
            + sEmployee.length(), From);
        sMessage.replace(sMessage.lastIndexOf(sLoggedIN), sMessage.lastIndexOf(sLoggedIN)
            + sLoggedIN.length(), LoggedIn);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + this.getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + this.getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.dateApplied")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.dateAppDisApp1")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppDisAppDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (!(lrapp.getApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.approveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            if (!(lrapp.getDisApproveNotes().isEmpty())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + this.getText("label.header.lhist.disApproveNotes")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + lrapp.getDisApproveNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.header.lrapp.noOfDays") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + lrapp.getLeaveRequested() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + this.getText("label.lrapp.comments") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + lrapp.getComments() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + this.getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = this.getText("alert.common.signature");
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
    }

    // DashBoard Leave For Approval
    @SkipValidation
    public String getLeaveForApproval() {
        List forApprovalTodayList;
        List lastThreeDaysList;
        List oneWeekList;
        List totalList;
        totalList = leaveReqsApprovalService.getDashLeaveForApproval();
        forApprovalCount = String.valueOf(totalList.size());
        forApprovalTodayList = leaveReqsApprovalService.getDashLeaveForApprovalToday();
        forApprovalToday = String.valueOf(forApprovalTodayList.size());
        lastThreeDaysList = leaveReqsApprovalService.getDashLeaveForApprovalThreeDays();
        forApprovalThreeDays = String.valueOf(lastThreeDaysList.size());
        oneWeekList = leaveReqsApprovalService.getDashLeaveForApprovalOneWeek();
        forApprovalOneWeek = String.valueOf(oneWeekList.size());
        return SUCCESS;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public EmployeesVO getEmps() {
        return emps;
    }

    public void setEmps(EmployeesVO emps) {
        this.emps = emps;
    }

    public List<LeaveReqsApprovalVO> getLrappList() {
        return lrappList;
    }

    public void setLrappList(List<LeaveReqsApprovalVO> lrappList) {
        this.lrappList = lrappList;
    }

    public LeaveReqsApprovalVO getLrapp() {
        return lrapp;
    }

    public void setLrapp(LeaveReqsApprovalVO lrapp) {
        this.lrapp = lrapp;
    }

    public List<LeaveTypeVO> getLeaveTypeIdObj() {
        return leaveTypeIdObj;
    }

    public void setLeaveTypeIdObj(List<LeaveTypeVO> leaveTypeIdObj) {
        this.leaveTypeIdObj = leaveTypeIdObj;
    }

    public LeaveTypeVO getLtype() {
        return ltype;
    }

    public void setLtype(LeaveTypeVO ltype) {
        this.ltype = ltype;
    }

    public EmployeeLeaveQuotaVO getEmpLeaveQuota() {
        return empLeaveQuota;
    }

    public void setEmpLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {
        this.empLeaveQuota = empLeaveQuota;
    }

    public List<EmployeeLeaveQuotaVO> getEmpLeaveList() {
        return empLeaveList;
    }

    public void setEmpLeaveList(List<EmployeeLeaveQuotaVO> empLeaveList) {
        this.empLeaveList = empLeaveList;
    }

    public String getLeaveAppDis() {
        return leaveAppDis;
    }

    public void setLeaveAppDis(String leaveAppDis) {
        this.leaveAppDis = leaveAppDis;
    }

    public String getLeaveSelfApp() {
        return leaveSelfApp;
    }

    public void setLeaveSelfApp(String leaveSelfApp) {
        this.leaveSelfApp = leaveSelfApp;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getDisApproved() {
        return disApproved;
    }

    public void setDisApproved(String disApproved) {
        this.disApproved = disApproved;
    }

    public String getForApprovalCount() {
        return forApprovalCount;
    }

    public void setForApprovalCount(String forApprovalCount) {
        this.forApprovalCount = forApprovalCount;
    }

    public String getForApprovalToday() {
        return forApprovalToday;
    }

    public void setForApprovalToday(String forApprovalToday) {
        this.forApprovalToday = forApprovalToday;
    }

    public String getForApprovalThreeDays() {
        return forApprovalThreeDays;
    }

    public void setForApprovalThreeDays(String forApprovalThreeDays) {
        this.forApprovalThreeDays = forApprovalThreeDays;
    }

    public String getForApprovalOneWeek() {
        return forApprovalOneWeek;
    }

    public void setForApprovalOneWeek(String forApprovalOneWeek) {
        this.forApprovalOneWeek = forApprovalOneWeek;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getNotesEmployeeId() {
        return notesEmployeeId;
    }

    public void setNotesEmployeeId(String notesEmployeeId) {
        this.notesEmployeeId = notesEmployeeId;
    }

}