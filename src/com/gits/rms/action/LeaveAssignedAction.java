
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
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeeLeaveQuotaDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
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

public class LeaveAssignedAction extends ActionSupport {

    private static final long serialVersionUID = 1370117824979677878L;
    private LeaveReqsApprovalService leaveReqsApprovalService = new LeaveReqsApprovalDaoService();
    private LeaveReqsApprovalVO lrapp = new LeaveReqsApprovalVO();
    private List<LeaveReqsApprovalVO> lrappList;
    private List<EmployeeLeaveQuotaVO> empLeaveList;
    private EmployeeLeaveQuotaService empLeaveService = new EmployeeLeaveQuotaDaoService();
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private List<LeaveApproverVO> leaveApproverList;
    private LeaveTypeService leaveTypeService = new LeaveTypeDaoService();
    private LeaveHistoryVO lhist = new LeaveHistoryVO();
    private EmployeeLeaveQuotaVO empLeaveQuota1;
    private List<EmployeesVO> empIdObj;
    private EmployeesService employeeService = new EmployeesDaoService();
    private LeaveApproverService leavAppService = new LeaveApproverDaoService();
    private List<LeaveApproverVO> leaveAppList;
    private LeaveTypeVO ltype;
    private String leaAppDate = "";
    private String leaModifiedDate = "";
    private String leaAppDisAppDate = "";
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;

    // when click Add LeaveRequestApproval it shows blank Form
    // to enter New leaveRequest
    @SkipValidation
    public String setUpLeaveReqsAssigned() {
    	LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    	
        lrapp.setLeaveReqStatus(getText("select.common.leaveReqStatus.forapproval.value"));
        lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
        loadValues.getAllSubEmployeeForLeave();
        if ((lrapp != null) && (lrapp.getHcmoLeaveReqsApprovalId() != null)) {
            lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new LeaveRequest
    public String insertOrUpdateLeaveReqsAssigned() {
        BigDecimal totalNoOfDays;
        BigDecimal totalNoOfHours;
        BigDecimal totalNoOfMins;
        BigDecimal totNoOfHoursPerDay;
        BigDecimal totNoOfMinPerDay;
        BigDecimal totNoOfMinRequest;
        Calendar calendar = new GregorianCalendar();
        calendar.get(Calendar.YEAR);

        // Leave Quota
        BigDecimal leaveReqTotalNoOfDays;
        BigDecimal leaveReqTotalNoOfHours;
        BigDecimal leaveReqTotalNoOfMins;
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
        if (lrapp.getHcmoLeaveReqsApprovalId() == null) {
            Date today = new Date();
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            formatter.format(today);
            if (lrapp.getNoOfDays().equals(new BigDecimal(0))
                && (lrapp.getHours().equals(new BigDecimal(0)) && lrapp.getMins().equals(new BigDecimal(0)))) {
                addActionError(getText("Please Specify either No Of Days (or) hours"));
                return INPUT;
            }
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            EmployeesVO oEmp1 = lrapp.getEmpIdObj();
            Integer yearEntered = 1900 + lrapp.getDateApplied().getYear();
            LeaveTypeVO leaveName = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
            empLeaveList = empLeaveService.getEmployeeLeaveQuotaList(lrapp, oEmp1, yearEntered);
            if (empLeaveList.isEmpty()) {
                addActionError(getText("label.header.leaveRequest.checkleaveType")
                    + leaveName.getLeaveTypeName()
                    + getText("label.header.leaveRequest.checkleaveEmployee"));
                return INPUT;
            }

            totNoOfHoursPerDay = new BigDecimal(8);
            totNoOfMinPerDay = new BigDecimal(60);
            totalNoOfDays = totNoOfHoursPerDay.multiply(lrapp.getNoOfDays().multiply(totNoOfMinPerDay));

            totalNoOfHours = lrapp.getHours().multiply(totNoOfMinPerDay);
            totalNoOfMins = lrapp.getMins();
            totNoOfMinRequest = totalNoOfDays.add(totalNoOfHours).add(totalNoOfMins);

            new BigDecimal(0.125);
            lrapp.setCreated(DateUtils.getCurrentDateTime());
            lrapp.setCreatedBy(oEmp);
            lrapp.setUpdatedBy(oEmp);
            lrapp.setHcmoLeaveApproverId(oEmp);
            lrapp.setIsActive(1);
            lrapp.setLeaveReqStatus(getText("select.common.leaveReqStatus.assigned.value"));
            lrapp.setDateApprDisappr(DateUtils.getCurrentDateTime());
            leaveReqsApprovalService.insertLeaveReqsApproval(lrapp);
            addActionMessage(getText("Leave Assigned Successfully"));

            lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
            lrapp.setLeaveReqStatus((getText("select.common.leaveReqStatus.approved.value")));
            lrapp.setHcmoApproverId(oEmp);
            lrapp.setDisApproveNotes("");
            empLeaveQuota1 = empLeaveService.getEmployeeLeaveQuotaList(lrapp);

            if ((lrapp.getLeaveTypeIdObj().getLeaveTypeId() != null)
                && (empLeaveQuota1.getLeaveTypeIdObj().getLeaveTypeId() != null)) {
                if (lrapp.getLeaveTypeIdObj().getLeaveTypeName().equals(empLeaveQuota1.getLeaveTypeIdObj().getLeaveTypeName())) {
                    totNoOfHoursPerDay = new BigDecimal(8);
                    totNoOfMinPerDay = new BigDecimal(60);

                    // Calculation for Allotted Days subtract Leave Request to
                    // save Remaining Days
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

                    if ((booremainDays == true) || (booremainHrs == true)
                        || (booremainMins == true)) {
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

                    // Calculation to save no of days leave requested to get
                    // leave taken value
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
                lhist.setLeaveStatus(getText("select.common.leaveReqStatus.assigned.value"));
                lhist.setLeaveRequestId(lrapp.getHcmoLeaveReqsApprovalId());
                lhist.setLeaveDate(lrapp.getDateApplied());
                lhist.setEmpIdObj(lrapp.getEmpIdObj());
                lhist.setLeaveComments(lrapp.getComments());
                lhist.setStartTime((getText("select.common.leaveHist.startTime.value")));
                lhist.setEndTime((getText("select.common.leaveHist.endTime.value")));
                lhist.setCreated(DateUtils.getCurrentDateTime());
                lhist.setNoOfDays(lrapp.getNoOfDays());
                lhist.setHours(lrapp.getHours());
                lhist.setMins(lrapp.getMins());
                lhist.setCreatedBy(oEmp);
                lhist.setHcmoLeaveApproverId(oEmp);
                lhist.setLeaveTypeIdObj(lrapp.getLeaveTypeIdObj());
                lhist.setUpdatedBy(oEmp);

                lhist.setIsActive(1);
                leaveReqsApprovalService.assigned(empLeaveQuota1, lhist);

                ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
                String sSubject = getText("message.subject.leave.assigned");
                LeaveApproverVO newLeaveApprover = null;

                int selfAppId = lrapp.getEmpIdObj().getEmployeeId();
                int approvingEmpId = lrapp.getHcmoApproverId().getEmployeeId();
                if (selfAppId == approvingEmpId) {
                    mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), getText("leave.employee.leaveRequest.selfApproved.assigned"), getText("message.common.myOwn.name"), sSubject);
                } else {
                    leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(lrapp.getEmpIdObj().getEmployeeId());
                    for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                        newLeaveApprover = it.next();
                        int leaveAppList = newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                        oEmp.getEmployeeId();
                        newLeaveApprover.getHcmoEmployeeId().getEmployeeId();
                        if (approvingEmpId == leaveAppList) {
                            // Approving Employee
                            mail(lrapp.getHcmoApproverId().getEmployeeId(), lrapp.getHcmoApproverId().getEmpFirstName(), getText("leave.approvedEmp.leaveRequest.assigned"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);

                            // employee
                            mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), getText("leave.employee.leaveRequest.assigned"), lrapp.getHcmoApproverId().getEmpFirstName(), sSubject);
                        } else if (approvingEmpId != leaveAppList) {
                            // Other leave approver
                            mailForAllApprover(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leave.allApprovers.leaveRequest.assigned"), lrapp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String viewAssignedForm() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empVOObj = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        leaveAppList = leavAppService.getCurrentLeaveApprover(empVOObj.getEmployeeId());
        if (leaveAppList.isEmpty()) {
            emp();
        } else {
            subEmployeeList();
        }
        return SUCCESS;
    }

    @SkipValidation
    public String viewAssignedList() {
        lrapp.setLeaveReqStatus(getText("select.common.leaveReqStatus.assigned.value"));
        lrappList = leaveReqsApprovalService.getAllEmpAssignedList(lrapp);
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

    private void emp() {
        empIdObj = employeeService.getCurrentEmployee();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("empsList", empIdObj);
    }

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
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.lrapp.dateApplied") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.lrapp.dateAppDisApp1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
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
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
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
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.lrapp.dateApplied") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.lrapp.dateAppDisApp1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
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
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
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
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.lrapp.dateApplied") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getLeaveReqStatus().equals("Approved")
            || lrapp.getLeaveReqStatus().equals("DisApproved")) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.lrapp.dateAppDisApp1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.forAnyInfo")
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
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

    public LeaveReqsApprovalVO getLrapp() {
        return lrapp;
    }

    public void setLrapp(LeaveReqsApprovalVO lrapp) {
        this.lrapp = lrapp;
    }

    public List<LeaveReqsApprovalVO> getLrappList() {
        return lrappList;
    }

    public void setLrappList(List<LeaveReqsApprovalVO> lrappList) {
        this.lrappList = lrappList;
    }

    public List<EmployeeLeaveQuotaVO> getEmpLeaveList() {
        return empLeaveList;
    }

    public void setEmpLeaveList(List<EmployeeLeaveQuotaVO> empLeaveList) {
        this.empLeaveList = empLeaveList;
    }

    public List<LeaveApproverVO> getLeaveApproverList() {
        return leaveApproverList;
    }

    public void setLeaveApproverList(List<LeaveApproverVO> leaveApproverList) {
        this.leaveApproverList = leaveApproverList;
    }

    public LeaveHistoryVO getLhist() {
        return lhist;
    }

    public void setLhist(LeaveHistoryVO lhist) {
        this.lhist = lhist;
    }

    public EmployeeLeaveQuotaVO getEmpLeaveQuota1() {
        return empLeaveQuota1;
    }

    public void setEmpLeaveQuota1(EmployeeLeaveQuotaVO empLeaveQuota1) {
        this.empLeaveQuota1 = empLeaveQuota1;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public List<LeaveApproverVO> getLeaveAppList() {
        return leaveAppList;
    }

    public void setLeaveAppList(List<LeaveApproverVO> leaveAppList) {
        this.leaveAppList = leaveAppList;
    }

    public LeaveTypeVO getLtype() {
        return ltype;
    }

    public void setLtype(LeaveTypeVO ltype) {
        this.ltype = ltype;
    }

}
