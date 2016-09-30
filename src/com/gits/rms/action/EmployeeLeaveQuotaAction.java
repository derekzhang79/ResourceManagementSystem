
package com.gits.rms.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeeLeaveQuotaDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.LeaveReqsApprovalDaoService;
import com.gits.rms.service.LeaveReqsApprovalService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmployeeLeaveQuotaAction extends ActionSupport {
    private static final long serialVersionUID = 1561324844218430524L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private EmployeeLeaveQuotaService empLeaveService = new EmployeeLeaveQuotaDaoService();
    private EmployeeLeaveQuotaVO empLeaveQuota = new EmployeeLeaveQuotaVO();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmployeeLeaveQuotaVO> empLeaveList;
    private String empLeaveModifiedDate = "";
    private RoleVO role;
    private EmployeesService employeeService = new EmployeesDaoService();
    private List<EmployeesVO> adminRoleId;
    private List<LeaveReqsApprovalVO> leaveReqsList;
    private LeaveReqsApprovalService leaveReqsService = new LeaveReqsApprovalDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();

    // To get List of EmployeeLeaveQuota
    @SkipValidation
    public String getAllEmployeeLeaveQuota() {
    	
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		empLeaveList = empLeaveService.getAllSubEmployeeLeaveQuotaList(employeeReportToList);
    	}else{
    		empLeaveList = empLeaveService.getAllEmployeeLeaveQuota();
    	}
    	
        for (int i = 0; i < empLeaveList.size(); i++) {
            EmployeeLeaveQuotaVO empLeaveId = empLeaveList.get(i);

            String noOfDays = empLeaveId.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveId.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveId.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");

            String noOfRemainDays = empLeaveId.getRemainDays().toString();
            noOfRemainDays = noOfRemainDays.replace(".00", "");
            String noOfRemainHours = empLeaveId.getRemainHours().toString();
            noOfRemainHours = noOfRemainHours.replace(".00", "");
            String noOfRemainMins = empLeaveId.getRemainMinutes().toString();
            noOfRemainMins = noOfRemainMins.replace(".00", "");

            String noOfLeaveTakenDays = empLeaveId.getLeaveTakenDays().toString();
            noOfLeaveTakenDays = noOfLeaveTakenDays.replace(".00", "");
            String noOfLeaveTakenHours = empLeaveId.getLeaveTakenHours().toString();
            noOfLeaveTakenHours = noOfLeaveTakenHours.replace(".00", "");
            String noOfLeaveTakenMins = empLeaveId.getLeaveTakenMinutes().toString();
            noOfLeaveTakenMins = noOfLeaveTakenMins.replace(".00", "");

            String noOfPreYearDays = empLeaveId.getPreviousCarryFwdDays().toString();
            noOfPreYearDays = noOfPreYearDays.replace(".00", "");
            String noOfPreYearHours = empLeaveId.getPreviousCarryFwdHours().toString();
            noOfPreYearHours = noOfPreYearHours.replace(".00", "");
            String noOfPreYearMins = empLeaveId.getPreviousCarryFwdMinutes().toString();
            noOfPreYearMins = noOfPreYearMins.replace(".00", "");

            empLeaveId.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
            empLeaveId.setEmpLeavePending(noOfRemainDays + "  Days" + "," + noOfRemainHours
                + "  Hours" + "," + noOfRemainMins + "  Minutes");
            empLeaveId.setEmpLeaveRequest(noOfLeaveTakenDays + "  Days" + "," + noOfLeaveTakenHours
                + "  Hours" + "," + noOfLeaveTakenMins + "  Minutes");
            empLeaveId.setPreviousLeaveCarryDays(noOfPreYearDays + "  Days" + ","
                + noOfPreYearHours + "  Hours" + "," + noOfPreYearMins + "  Minutes");

        }
        return SUCCESS;
    }

    // To View the Employee LeaveQuota Search Form
    @SkipValidation
    public String leaveQuotaSearchForm() {
        // loadValues.getAllSubEmployeeForLeaveQuota();
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String leaveQuotaSearchResult() {

        empLeaveList = empLeaveService.leaveQuotaSearchResult(empLeaveQuota);
        for (int i = 0; i < empLeaveList.size(); i++) {
            EmployeeLeaveQuotaVO empLeaveId = empLeaveList.get(i);
            String noOfDays = empLeaveId.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveId.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveId.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");

            String noOfRemainDays = empLeaveId.getRemainDays().toString();
            noOfRemainDays = noOfRemainDays.replace(".00", "");
            String noOfRemainHours = empLeaveId.getRemainHours().toString();
            noOfRemainHours = noOfRemainHours.replace(".00", "");
            String noOfRemainMins = empLeaveId.getRemainMinutes().toString();
            noOfRemainMins = noOfRemainMins.replace(".00", "");

            String noOfLeaveTakenDays = empLeaveId.getLeaveTakenDays().toString();
            noOfLeaveTakenDays = noOfLeaveTakenDays.replace(".00", "");
            String noOfLeaveTakenHours = empLeaveId.getLeaveTakenHours().toString();
            noOfLeaveTakenHours = noOfLeaveTakenHours.replace(".00", "");
            String noOfLeaveTakenMins = empLeaveId.getLeaveTakenMinutes().toString();
            noOfLeaveTakenMins = noOfLeaveTakenMins.replace(".00", "");

            String noOfPreYearDays = empLeaveId.getPreviousCarryFwdDays().toString();
            noOfPreYearDays = noOfPreYearDays.replace(".00", "");
            String noOfPreYearHours = empLeaveId.getPreviousCarryFwdHours().toString();
            noOfPreYearHours = noOfPreYearHours.replace(".00", "");
            String noOfPreYearMins = empLeaveId.getPreviousCarryFwdMinutes().toString();
            noOfPreYearMins = noOfPreYearMins.replace(".00", "");

            empLeaveId.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
            empLeaveId.setEmpLeavePending(noOfRemainDays + "  Days" + "," + noOfRemainHours
                + "  Hours" + "," + noOfRemainMins + "  Minutes");
            empLeaveId.setEmpLeaveRequest(noOfLeaveTakenDays + "  Days" + "," + noOfLeaveTakenHours
                + "  Hours" + "," + noOfLeaveTakenMins + "  Minutes");
            empLeaveId.setPreviousLeaveCarryDays(noOfPreYearDays + "  Days" + ","
                + noOfPreYearHours + "  Hours" + "," + noOfPreYearMins + "  Minutes");
        }
        return SUCCESS;
    }

    // when click Add EmployeeLeaveQuota it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmployeeLeaveQuota() {
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        if ((empLeaveQuota != null) && (empLeaveQuota.getEmpLeaveQuotaId() != null)) {
            empLeaveQuota = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
            if (empLeaveQuota.getYear() != currentYear) {
                addActionError(getText("label.header.leaveQuota.previousYearEdited.msg.info"));
                return ERROR;
            }
            leaveReqsList = leaveReqsService.getLeaveReqsList(empLeaveQuota);
            if (!(leaveReqsList.isEmpty())) {
                addActionError(getText("label.header.leaveQuota.editedLeaveRequestExist.msg.info"));
                return ERROR;
            }
            empLeaveQuota = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
            String noOfDays = empLeaveQuota.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveQuota.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveQuota.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");

            String noOfRemainDays = empLeaveQuota.getRemainDays().toString();
            noOfRemainDays = noOfRemainDays.replace(".00", "");
            String noOfRemainHours = empLeaveQuota.getRemainHours().toString();
            noOfRemainHours = noOfRemainHours.replace(".00", "");
            String noOfRemainMins = empLeaveQuota.getRemainMinutes().toString();
            noOfRemainMins = noOfRemainMins.replace(".00", "");

            String noOfLeaveTakenDays = empLeaveQuota.getLeaveTakenDays().toString();
            noOfLeaveTakenDays = noOfLeaveTakenDays.replace(".00", "");
            String noOfLeaveTakenHours = empLeaveQuota.getLeaveTakenHours().toString();
            noOfLeaveTakenHours = noOfLeaveTakenHours.replace(".00", "");
            String noOfLeaveTakenMins = empLeaveQuota.getLeaveTakenMinutes().toString();
            noOfLeaveTakenMins = noOfLeaveTakenMins.replace(".00", "");

            String noOfPreYearDays = empLeaveQuota.getPreviousCarryFwdDays().toString();
            noOfPreYearDays = noOfPreYearDays.replace(".00", "");
            String noOfPreYearHours = empLeaveQuota.getPreviousCarryFwdHours().toString();
            noOfPreYearHours = noOfPreYearHours.replace(".00", "");
            String noOfPreYearMins = empLeaveQuota.getPreviousCarryFwdMinutes().toString();
            noOfPreYearMins = noOfPreYearMins.replace(".00", "");

            empLeaveQuota.setNoOfDays(new BigDecimal(noOfDays));
            empLeaveQuota.setHours(new BigDecimal(noOfHours));
            empLeaveQuota.setMinutes(new BigDecimal(noOfMins));
            empLeaveQuota.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours"
                + "," + noOfMins + "  Minutes");
            empLeaveQuota.setEmpLeavePending(noOfRemainDays + "  Days" + "," + noOfRemainHours
                + "  Hours" + "," + noOfRemainMins + "  Minutes");
            empLeaveQuota.setEmpLeaveRequest(noOfLeaveTakenDays + "  Days" + ","
                + noOfLeaveTakenHours + "  Hours" + "," + noOfLeaveTakenMins + "  Minutes");
            empLeaveQuota.setPreviousLeaveCarryDays(noOfPreYearDays + "  Days" + ","
                + noOfPreYearHours + "  Hours" + "," + noOfPreYearMins + "  Minutes");
        }

        return SUCCESS;
    }

    // To get Particular EmployeeLeaveQuota Data
    @SkipValidation
    public String employeeLeaveQuotaView() {
        if ((empLeaveQuota != null) && (empLeaveQuota.getEmpLeaveQuotaId() != null)) {
            empLeaveQuota = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
            String noOfDays = empLeaveQuota.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveQuota.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveQuota.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");

            String noOfRemainDays = empLeaveQuota.getRemainDays().toString();
            noOfRemainDays = noOfRemainDays.replace(".00", "");
            String noOfRemainHours = empLeaveQuota.getRemainHours().toString();
            noOfRemainHours = noOfRemainHours.replace(".00", "");
            String noOfRemainMins = empLeaveQuota.getRemainMinutes().toString();
            noOfRemainMins = noOfRemainMins.replace(".00", "");

            String noOfLeaveTakenDays = empLeaveQuota.getLeaveTakenDays().toString();
            noOfLeaveTakenDays = noOfLeaveTakenDays.replace(".00", "");
            String noOfLeaveTakenHours = empLeaveQuota.getLeaveTakenHours().toString();
            noOfLeaveTakenHours = noOfLeaveTakenHours.replace(".00", "");
            String noOfLeaveTakenMins = empLeaveQuota.getLeaveTakenMinutes().toString();
            noOfLeaveTakenMins = noOfLeaveTakenMins.replace(".00", "");

            String noOfPreYearDays = empLeaveQuota.getPreviousCarryFwdDays().toString();
            noOfPreYearDays = noOfPreYearDays.replace(".00", "");
            String noOfPreYearHours = empLeaveQuota.getPreviousCarryFwdHours().toString();
            noOfPreYearHours = noOfPreYearHours.replace(".00", "");
            String noOfPreYearMins = empLeaveQuota.getPreviousCarryFwdMinutes().toString();
            noOfPreYearMins = noOfPreYearMins.replace(".00", "");

            empLeaveQuota.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours"
                + "," + noOfMins + "  Minutes");
            empLeaveQuota.setEmpLeavePending(noOfRemainDays + "  Days" + "," + noOfRemainHours
                + "  Hours" + "," + noOfRemainMins + "  Minutes");
            empLeaveQuota.setEmpLeaveRequest(noOfLeaveTakenDays + "  Days" + ","
                + noOfLeaveTakenHours + "  Hours" + "," + noOfLeaveTakenMins + "  Minutes");
            empLeaveQuota.setPreviousLeaveCarryDays(noOfPreYearDays + "  Days" + ","
                + noOfPreYearHours + "  Hours" + "," + noOfPreYearMins + "  Minutes");
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EmployeeLeaveQuota
    // or update particular EmployeeLeaveQuota Data
    public String insertOrUpdateEmployeeLeaveQuota() {
        BigDecimal daysToMins;
        BigDecimal hoursToMins;
        BigDecimal Mins;
        BigDecimal preDaysToMins;
        BigDecimal preHoursToMins;
        BigDecimal preMinsToMins;
        BigDecimal totMinsAllotted;
        BigDecimal totPreMins;
        BigDecimal totalResultMinutes;
        BigDecimal totHrsPerDay;
        BigDecimal totHrsPerMin;
        BigDecimal totConHrs;
        String key = "";
        String val = "";
        BigDecimal conHrsToDays;
        String finalDay = "";
        String daysToHrs = "";
        BigDecimal days;
        BigDecimal hrs;
        BigDecimal mins;
        Calendar calendar = new GregorianCalendar();
        int prvYearData = calendar.get(Calendar.YEAR) - 1;
        int currentYear = calendar.get(Calendar.YEAR);

        EmployeesVO newAdminEmp = null;
        try {
            if (empLeaveQuota.getEmpLeaveQuotaId() == null) {
                Map msession = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

                // To check whether any leave quota present for previous year
                EmployeeLeaveQuotaVO empLeave = empLeaveService.getEmpPrevYearLeaveQuota(empLeaveQuota, prvYearData);

                // if LeaveQuota Present
                if (empLeave != null) {
                    // set previousyear LCFwd as Remaining days,hours and
                    // minutes
                    empLeaveQuota.setPreviousCarryFwdDays(empLeave.getRemainDays());
                    empLeaveQuota.setPreviousCarryFwdHours(empLeave.getRemainHours());
                    empLeaveQuota.setPreviousCarryFwdMinutes(empLeave.getRemainMinutes());

                    totHrsPerDay = new BigDecimal(8);
                    totHrsPerMin = new BigDecimal(60);

                    // Calculation to convert allotted days to mins
                    daysToMins = totHrsPerDay.multiply(empLeaveQuota.getNoOfDays().multiply(totHrsPerMin));
                    hoursToMins = empLeaveQuota.getHours().multiply(totHrsPerMin);
                    Mins = empLeaveQuota.getMinutes();

                    totMinsAllotted = daysToMins.add(hoursToMins).add(Mins);

                    // calculation to covert preyearcfwd to mins
                    preDaysToMins = totHrsPerDay.multiply(empLeaveQuota.getPreviousCarryFwdDays().multiply(totHrsPerMin));
                    preHoursToMins = empLeaveQuota.getPreviousCarryFwdHours().multiply(totHrsPerMin);
                    preMinsToMins = empLeaveQuota.getPreviousCarryFwdMinutes();

                    if (preDaysToMins.toString().contains("-")) {
                        totPreMins = preDaysToMins.multiply(new BigDecimal(-1)).add(preHoursToMins).add(preMinsToMins);
                        totPreMins = new BigDecimal(-1).multiply(totPreMins);
                    } else if (preHoursToMins.toString().contains("-")) {
                        totPreMins = preDaysToMins.add(preHoursToMins.multiply(new BigDecimal(-1))).add(preMinsToMins);
                        totPreMins = new BigDecimal(-1).multiply(totPreMins);
                    } else if (preMinsToMins.toString().contains("-")) {
                        totPreMins = preDaysToMins.add(preHoursToMins).add(preMinsToMins.multiply(new BigDecimal(-1)));
                        totPreMins = new BigDecimal(-1).multiply(totPreMins);
                    } else {
                        totPreMins = preDaysToMins.add(preHoursToMins).add(preMinsToMins);
                    }

                    // Added both alloted minutes and prelcfwd minutes
                    totalResultMinutes = totMinsAllotted.add(totPreMins);

                    // converted minutes to hours
                    totConHrs = totalResultMinutes.divide(totHrsPerMin);

                    // converted hours value set to string
                    String conHourssplitter = totConHrs.toString();

                    // split the value after dot
                    StringTokenizer st = new StringTokenizer(conHourssplitter, ".");
                    while (st.hasMoreTokens()) {
                        key = st.nextToken();
                        val = st.nextToken();
                    }

                    // convert the resulted hrs to days
                    conHrsToDays = new BigDecimal(key).divide(totHrsPerDay);

                    String hrsToDaysplitter = conHrsToDays.toString();
                    Boolean b = hrsToDaysplitter.contains(".");
                    if (b == true) {
                    } else {
                        hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                    }
                    StringTokenizer hrsToDayToken = new StringTokenizer(hrsToDaysplitter, ".");
                    while (hrsToDayToken.hasMoreTokens()) {
                        finalDay = hrsToDayToken.nextToken();
                        daysToHrs = hrsToDayToken.nextToken();
                    }
                    // final days
                    days = new BigDecimal(finalDay);
                    daysToHrs = "." + daysToHrs;
                    // final hours
                    hrs = new BigDecimal(daysToHrs).multiply(totHrsPerDay);
                    val = "." + val;
                    // final minutes
                    mins = new BigDecimal(val).multiply(totHrsPerMin);

                    empLeaveQuota.setRemainDays(days);
                    empLeaveQuota.setRemainHours(hrs);
                    empLeaveQuota.setRemainMinutes(mins);
                } else {
                    empLeaveQuota.setPreviousCarryFwdDays(new BigDecimal(0));
                    empLeaveQuota.setPreviousCarryFwdHours(new BigDecimal(0));
                    empLeaveQuota.setPreviousCarryFwdMinutes(new BigDecimal(0));
                    empLeaveQuota.setRemainDays(empLeaveQuota.getNoOfDays());
                    empLeaveQuota.setRemainHours(empLeaveQuota.getHours());
                    empLeaveQuota.setRemainMinutes(empLeaveQuota.getMinutes());
                }

                empLeaveQuota.setLeaveTakenDays(new BigDecimal(0));
                empLeaveQuota.setLeaveTakenHours(new BigDecimal(0));
                empLeaveQuota.setLeaveTakenMinutes(new BigDecimal(0));
                empLeaveQuota.setCreated(DateUtils.getCurrentDateTime());
                empLeaveQuota.setCreatedBy(oEmp);
                empLeaveQuota.setUpdatedBy(oEmp);
                empLeaveQuota.setIsActive(1);
                empLeaveList = empLeaveService.getEmployeeLeaveQuotaCheckInEmpLeave(empLeaveQuota);
                if (!(empLeaveList.isEmpty())) {
                    addActionError(getText("label.header.leaveQuota.exist.msg.info"));
                    return INPUT;
                }
                if (currentYear != empLeaveQuota.getYear()) {
                    addActionError(getText("Please Enter Leave Quota for Current Year"));
                    return INPUT;
                }
                empLeaveService.insertEmployeeLeaveQuota(empLeaveQuota);

                empLeaveQuota = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = empLeaveQuota.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.employeeLeaveQuota.add");

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();

                Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                    newAdminEmp = it.next();
                    int adminEmpID = newAdminEmp.getEmployeeId();

                    // logged in person is admin and he is entering his own
                    // information
                    if (adminEmpID == sessionEmpId) {
                        if (adminEmpID == employeeID) {
                            // the mail content to Other admin Employees
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedToAdmin"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(empLeaveQuota.getEmpIdObj().getEmployeeId(), empLeaveQuota.getEmpIdObj().getEmpFirstName(), getText("employeeLeaveQuota.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedBy"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedToAdmin"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.add.addedBy"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(empLeaveQuota.getEmpIdObj().getEmployeeId(), empLeaveQuota.getEmpIdObj().getEmpFirstName(), getText("employeeLeaveQuota.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                addActionMessage(getText("Added Successfully"));
            } else {
                totHrsPerDay = new BigDecimal(8);
                totHrsPerMin = new BigDecimal(60);
                BigDecimal checkMins;
                Map msession = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

                EmployeeLeaveQuotaVO empLeave = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
                daysToMins = totHrsPerDay.multiply(empLeave.getNoOfDays().multiply(totHrsPerMin));
                hoursToMins = empLeave.getHours().multiply(totHrsPerMin);
                Mins = empLeave.getMinutes();

                checkMins = daysToMins.add(hoursToMins).add(Mins);

                empLeaveQuota.setUpdatedBy(oEmp);

                // Calculation to convert allotted days to mins
                daysToMins = totHrsPerDay.multiply(empLeaveQuota.getNoOfDays().multiply(totHrsPerMin));
                hoursToMins = empLeaveQuota.getHours().multiply(totHrsPerMin);
                Mins = empLeaveQuota.getMinutes();

                totMinsAllotted = daysToMins.add(hoursToMins).add(Mins);

                Integer checkMinsInteger = checkMins.intValue();
                Integer totMinsInteger = totMinsAllotted.intValue();

                if (checkMinsInteger > totMinsInteger) {
                    addActionError(getText("label.header.leaveQuota.reduce.msg.info"));
                    return INPUT;
                } else {
                    empLeave = empLeaveService.getEmpPrevYearLeaveQuota(empLeaveQuota, prvYearData);

                    // if LeaveQuota Present
                    if (empLeave != null) {
                        // set previousyear LCFwd as Remaining days,hours and
                        // minutes
                        empLeaveQuota.setPreviousCarryFwdDays(empLeave.getRemainDays());
                        empLeaveQuota.setPreviousCarryFwdHours(empLeave.getRemainHours());
                        empLeaveQuota.setPreviousCarryFwdMinutes(empLeave.getRemainMinutes());

                        totHrsPerDay = new BigDecimal(8);
                        totHrsPerMin = new BigDecimal(60);

                        // Calculation to convert allotted days to mins
                        daysToMins = totHrsPerDay.multiply(empLeaveQuota.getNoOfDays().multiply(totHrsPerMin));
                        hoursToMins = empLeaveQuota.getHours().multiply(totHrsPerMin);
                        Mins = empLeaveQuota.getMinutes();

                        totMinsAllotted = daysToMins.add(hoursToMins).add(Mins);

                        // calculation to covert preyearcfwd to mins
                        preDaysToMins = totHrsPerDay.multiply(empLeaveQuota.getPreviousCarryFwdDays().multiply(totHrsPerMin));

                        preHoursToMins = empLeaveQuota.getPreviousCarryFwdHours().multiply(totHrsPerMin);

                        preMinsToMins = empLeaveQuota.getPreviousCarryFwdMinutes();

                        if (preDaysToMins.toString().contains("-")) {
                            totPreMins = preDaysToMins.multiply(new BigDecimal(-1)).add(preHoursToMins).add(preMinsToMins);
                            totPreMins = new BigDecimal(-1).multiply(totPreMins);
                        } else if (preHoursToMins.toString().contains("-")) {
                            totPreMins = preDaysToMins.add(preHoursToMins.multiply(new BigDecimal(-1))).add(preMinsToMins);
                            totPreMins = new BigDecimal(-1).multiply(totPreMins);
                        } else if (preMinsToMins.toString().contains("-")) {
                            totPreMins = preDaysToMins.add(preHoursToMins).add(preMinsToMins.multiply(new BigDecimal(-1)));
                            totPreMins = new BigDecimal(-1).multiply(totPreMins);
                        } else {
                            totPreMins = preDaysToMins.add(preHoursToMins).add(preMinsToMins);
                        }

                        // Added both alloted minutes and prelcfwd minutes
                        totalResultMinutes = totMinsAllotted.add(totPreMins);

                        // converted minutes to hours
                        totConHrs = totalResultMinutes.divide(totHrsPerMin);

                        // converted hours value set to string
                        String conHourssplitter = totConHrs.toString();

                        // split the value after dot
                        StringTokenizer st = new StringTokenizer(conHourssplitter, ".");
                        while (st.hasMoreTokens()) {
                            key = st.nextToken();
                            val = st.nextToken();
                        }

                        // convert the resulted hrs to days
                        conHrsToDays = new BigDecimal(key).divide(totHrsPerDay);

                        String hrsToDaysplitter = conHrsToDays.toString();
                        Boolean b = hrsToDaysplitter.contains(".");
                        if (b == true) {
                        } else {
                            hrsToDaysplitter = hrsToDaysplitter.concat(".00");
                        }
                        StringTokenizer hrsToDayToken = new StringTokenizer(hrsToDaysplitter, ".");
                        while (hrsToDayToken.hasMoreTokens()) {
                            finalDay = hrsToDayToken.nextToken();
                            daysToHrs = hrsToDayToken.nextToken();
                        }
                        // final days
                        days = new BigDecimal(finalDay);

                        daysToHrs = "." + daysToHrs;
                        // final hours
                        hrs = new BigDecimal(daysToHrs).multiply(totHrsPerDay);
                        val = "." + val;
                        // final minutes
                        mins = new BigDecimal(val).multiply(totHrsPerMin);

                        empLeaveQuota.setRemainDays(days);
                        empLeaveQuota.setRemainHours(hrs);
                        empLeaveQuota.setRemainMinutes(mins);
                    } else {
                        empLeaveQuota.setPreviousCarryFwdDays(new BigDecimal(0));
                        empLeaveQuota.setPreviousCarryFwdHours(new BigDecimal(0));
                        empLeaveQuota.setPreviousCarryFwdMinutes(new BigDecimal(0));
                        empLeaveQuota.setRemainDays(empLeaveQuota.getNoOfDays());
                        empLeaveQuota.setRemainHours(empLeaveQuota.getHours());
                        empLeaveQuota.setRemainMinutes(empLeaveQuota.getMinutes());
                    }
                }

                empLeaveQuota.setLeaveTakenDays(new BigDecimal(0));
                empLeaveQuota.setLeaveTakenHours(new BigDecimal(0));
                empLeaveQuota.setLeaveTakenMinutes(new BigDecimal(0));
                empLeaveService.updateEmployeeLeaveQuota(empLeaveQuota);

                empLeaveQuota = empLeaveService.getEmployeeLeaveQuota(empLeaveQuota.getEmpLeaveQuotaId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = empLeaveQuota.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.employeeLeaveQuota.edit");

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();

                Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                    newAdminEmp = it.next();
                    int adminEmpID = newAdminEmp.getEmployeeId();

                    // logged in person is admin and he is entering his own
                    // information
                    if (adminEmpID == sessionEmpId) {
                        if (adminEmpID == employeeID) {
                            // the mail content to Other admin Employees
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedToAdmin"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(empLeaveQuota.getEmpIdObj().getEmployeeId(), empLeaveQuota.getEmpIdObj().getEmpFirstName(), getText("employeeLeaveQuota.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedBy"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedToAdmin"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLeaveQuota.edit.updatedBy"), empLeaveQuota.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(empLeaveQuota.getEmpIdObj().getEmployeeId(), empLeaveQuota.getEmpIdObj().getEmpFirstName(), getText("employeeLeaveQuota.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadValues.getAllLeaveQuotaYear();
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {

        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date employeeLeaveModdate = empLeaveQuota.getUpdated();
            empLeaveModifiedDate = updateddateformat.format(employeeLeaveModdate);

            String noOfDays = empLeaveQuota.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveQuota.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveQuota.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");
            empLeaveQuota.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours"
                + "," + noOfMins + "  Minutes");

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
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
                + getText("label.header.leave.leaveQuota.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empLeaveQuota.getEmpIdObj().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveQuota.getLeaveTypeIdObj().getLeaveTypeName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employeeLeaveQuota.year")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveQuota.getYear()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.noOfDays") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empLeaveQuota.getLeaveAllottedDays() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveModifiedDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mailToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {

        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date employeeLeaveModdate = empLeaveQuota.getUpdated();
            empLeaveModifiedDate = updateddateformat.format(employeeLeaveModdate);

            String noOfDays = empLeaveQuota.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = empLeaveQuota.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = empLeaveQuota.getMinutes().toString();
            noOfMins = noOfMins.replace(".00", "");
            empLeaveQuota.setLeaveAllottedDays(noOfDays + "  Days" + "," + noOfHours + "  Hours"
                + "," + noOfMins + "  Minutes");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();

            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);

            sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.replace(sMessage.lastIndexOf(sLoggedInPerson), sMessage.lastIndexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.leave.leaveQuota.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empLeaveQuota.getEmpIdObj().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveQuota.getLeaveTypeIdObj().getLeaveTypeName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employeeLeaveQuota.year")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveQuota.getYear()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.noOfDays") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empLeaveQuota.getLeaveAllottedDays() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empLeaveModifiedDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<EmployeeLeaveQuotaVO> getEmpLeaveList() {
        return empLeaveList;
    }

    public void setEmpLeaveList(List<EmployeeLeaveQuotaVO> empLeaveList) {
        this.empLeaveList = empLeaveList;
    }

    public EmployeeLeaveQuotaVO getEmpLeaveQuota() {
        return empLeaveQuota;
    }

    public void setEmpLeaveQuota(EmployeeLeaveQuotaVO empLeaveQuota) {
        this.empLeaveQuota = empLeaveQuota;
    }

    public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public List<EmployeesVO> getAdminRoleId() {
        return adminRoleId;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

}
