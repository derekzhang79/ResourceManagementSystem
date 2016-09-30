
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.BenefitsDaoService;
import com.gits.rms.service.BenefitsService;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.ImportantNewsDaoService;
import com.gits.rms.service.ImportantNewsService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.LeaveReqsApprovalDaoService;
import com.gits.rms.service.LeaveReqsApprovalService;
import com.gits.rms.service.LeaveTypeDaoService;
import com.gits.rms.service.LeaveTypeService;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.VendorDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.ImportantNewsVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.VendorVO;

public class EmployeesAction extends ActionSupport {
    private static final long serialVersionUID = 2148707874603776282L;
    static Logger logger = Logger.getLogger(EmployeesAction.class.getName());
    
    private EmployeesService emplService = new EmployeesDaoService();
    private EmployeeReportToService empReportToService = new EmployeeReportToDaoService();
    private List<ProjectAssignEmpVO> assProjList;
    private ProjectAssignEmpService assProjService = new ProjectAssignEmpDaoService();
    private LeaveTypeService leaveTypeService = new LeaveTypeDaoService();
    private EmployeeLeaveQuotaService leaveQuotaService = new EmployeeLeaveQuotaDaoService();
    private FileUploadUtil fileupload = new FileUploadUtil();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private EmployeesVO employee;
    private RoleVO role;
    private BenefitsVO benefit;
    private List<EmployeesVO> emploList;
    private List<EmployeesVO> empBirthdayList;
    private List<EmployeesVO> adminListSize;
    private List<EmployeesVO> adminRoleId;
    private List<EmployeeReportToVO> empReportToList;
    private List<LeaveApproverVO> leaveApproverList;
    private List<ExpensesApproverVO> expApproverList;
    private List<TimeSheetApproverVO> timeSheetApproverList;
    private List<BenefitsVO> benefitList;
    private List<EmployeeLeaveQuotaVO> empLeaveList;
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private ExpensesApproverService expensesAppproverService = new ExpensesApproverDaoService();
    private TimeSheetApproverService tsApproverService = new TimeSheetApproverDaoService();
    private LeaveReqsApprovalService leaveReqsApprovalService = new LeaveReqsApprovalDaoService();
    private BenefitsService benefitService = new BenefitsDaoService();
    private InputStream inStream;
    private File userImage;
    private String userImageContentType;
    private String userImageFileName;
    private String employeePicturePath;
    private ProjectAssignEmpVO assProj;
    private ProjectActivityVO proActivity;
    private ProjectActivityService projActivityService = new ProjectActivityDaoService();
    private ProjectAssignEmpService projAssignService = new ProjectAssignEmpDaoService();
    private ProjectService proService = new ProjectDaoService();
    private List<ProjectActivityVO> projActivityList;

    private EmployeeLeaveQuotaVO employeeLeaveQuota = new EmployeeLeaveQuotaVO();
    private LeaveTypeVO leaveType = new LeaveTypeVO();
    private List<LeaveTypeVO> leaveTypeList;
    private String empBirthDateMail = "";
    private String empJoinDateMail = "";
    private String empModifiedDateMail = "";
    private String empLicenseExpDateMail = "";
    private String sGender = "";
    public Integer maxiFolderSize;
    private String leaveAppDis;
    private String leaveSelfApp;
    private String expenseAppDis;
    private String expenseSelfApp;
    private String timeSheetSelfApp;
    private String timeSheetAppDis;
    private String benefitEmployee = "";
    private Integer essEmployeeID;
    private Integer orgChartEmployeeID;
    private List<ImportantNewsVO> impList;
    private ImportantNewsService impServivce = new ImportantNewsDaoService();
    private Map session = ActionContext.getContext().getSession();
    
    private Integer selectedEmployeeId;

    @SkipValidation
    public String getHome() {
    	System.out.println("inside get home--------------------->");
    	 Integer clientId = (Integer) session.get("CLIENT_INFO");
    	System.out.println("get home ------------->>"+clientId);
        empBirthdayList = emplService.getEmployeeBirthDay(clientId);

        if (empBirthdayList.isEmpty()) {
            EmployeesVO empBirthday = new EmployeesVO();
            empBirthday.setEmpFirstName("Nothing to Display");
            empBirthday.setEmpLastName(" ");
            empBirthdayList.add(empBirthday);
        }
        impList = emplService.getSelectedImportantNotes(clientId);
        System.out.println("get home ------------->>");
        if (impList.isEmpty()) {
            ImportantNewsVO important = new ImportantNewsVO();
            important.setSubject("Nothing to Display");
            important.setMessage(" ");
            impList.add(important);
        }
        System.out.println("before returning success home ------------->>");
        return SUCCESS;
    }

    // To get ESS Employee ID Employee
    @SkipValidation
    public String getESSEmployeeID() {
        getEssEmployeeID();
        return SUCCESS;
    }

    // To get List of Employee
    @SkipValidation
    public String getAllEmp() {
    	emploList = emplService.getAllEmployees((Integer) session.get("CLIENT_INFO"));
        return SUCCESS;
    }

    // To get employees grouped by approvers for organization chart
    @SkipValidation
    public String getAllEmpGrouped() {
    	Integer clientId = (Integer) session.get("CLIENT_INFO");
        emploList = emplService.getAllEmployeesGrouped();
        empReportToList = empReportToService.getAllEmployeeReportTo(clientId);
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String employeeSearchForm() {
        return SUCCESS;
    }

    // Convert Int to String for Reporting Mode Field
    public void setGenderValues(EmployeesVO gender) {
        gender.setEmpGenderValue(gender.getEmpGender() == "M" ? getText("label.employee.length.value.Female")
            : getText("label.employee.length.value.male"));
    }

    // Search Result
    @SkipValidation
    public String employeeSearchResult() {
        emploList = emplService.employeeSearchResult(employee);

        if (employee.getMessage() != null) {
            if (employee.getMessage().contains(getText("label.common.search.messageSetEmployeeDOB"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(employee.getEmpBirthDate()) + "."));
            }
        }
        if (employee.getMessage() != null) {
            if (employee.getMessage().contains(getText("label.common.search.messageSetEmployeeLicenseExpDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(employee.getEmpDriLicenExpDate()) + "."));
            }
        }
        if (employee.getMessage() != null) {
            if (employee.getMessage().contains(getText("label.common.search.messageSetEmployeeJoinedDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(employee.getEmpJoineddate()) + "."));
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Employee it shows blank Form to enter New Data
/*    @SkipValidation
    public String setUpEmployees() {
        if ((employee != null) && (employee.getEmployeeId() != null)) {
            employee = emplService.getEmployees(employee.getEmployeeId());
            addActionMessage(getText("Added Successfully"));
        }
        return SUCCESS;
    }*/
    
    // when click Edit link it bring particular data into Form or click Add
    // Employee it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmployees() {
    	//skip001
    	loadValues.getDeptList();
    	loadValues.getAllEmployeeName();
    	// hardcoded
    	//logger.debug("setUpEmployees() employee : " + employee + " || employee.getEmployeeId() : " + employee.getEmployeeId());
    	logger.debug("setUpEmployees() employee : " + employee + " || emplService : " + emplService);
    	
    	Integer employeeId = new Integer(java.sql.Types.NULL);
    	
/*    	logger.debug("selectedEmployeeId : " + selectedEmployeeId);
    	if(selectedEmployeeId == null){
        	employeeId = new Integer(18);

    	}else{
    		employeeId = selectedEmployeeId;
    	}
    	logger.debug("employeeId : " + employeeId);*/
    	
    	if(employee != null){
    		employeeId = employee.getEmployeeId();
    	}
    	
    	Map session = ActionContext.getContext().getSession();
    	session.put("SELECTED_EMPLOYEE_ID", employeeId);
    	
        //if ((employee != null) && (employee.getEmployeeId() != null)) {
    	if (employeeId != null) {
            //employee = emplService.getEmployees(employee.getEmployeeId());
    		employee = emplService.getEmployees(employeeId);
    	    logger.debug("From set up employee " + employee);

    	    //assProj = projAssignService.getProjectAssignEmp(33);
    	   // logger.debug("From set up employee project assign" + assProj);
    	    
    	    //employee.setAssProj(assProj);
    		logger.debug("From Service setUpEmployees() employee : " + employee );
        	session.put("SELECTED_EMPLOYEE_OBJECT", employee);
        	//ActionContext.getContext().setSession(session);
            addActionMessage(getText("Added Successfully"));
        }
    	
    	if(employee != null) {
    		employeeId = employee.getEmployeeId();
    		employee = emplService.getEmployees(employeeId);
    	    logger.debug("From set up employee for ass proj " + employee);
    		assProj = projAssignService.getProjectAssignEmp(33);
    		employee.setAssProj(assProj);
    		logger.debug("From set up employee project assign" + assProj);
    	}
    	
        return SUCCESS;
    }
    

    @SkipValidation
    public String setUpEmployeesEditForm() {
        if ((employee != null) && (employee.getEmployeeId() != null)) {
            employee = emplService.getEmployees(employee.getEmployeeId());
        }
        return SUCCESS;
    }

    // To get Particular Employee Data
    @SkipValidation
    public String employeeView() {
    	 Map session = ActionContext.getContext().getSession();
        if ((employee != null) && (employee.getEmployeeId() != null)) {
            employee = emplService.getEmployees(employee.getEmployeeId());
        } else {
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            employee = emplService.getEmployees(oEmp.getEmployeeId());

        }
        if (employee.getEmpGender().equals("M")) {
            employee.setEmpGenderValue("Male");
        } else {
            employee.setEmpGenderValue("Female");
        }

        if(employee.getEmpPicturePath()!=null) {
        	String sClintId = String.valueOf(session.get("MASTER_CLIENT_ID"));
        	employeePicturePath = "./resources/images/employeepicture/"+"MASTER_CLIENTID_"+sClintId+"/"+employee.getEmployeeId()+"/"+employee.getEmpPicturePath();
        }else {
        	employeePicturePath = "./resources/images/employeepicture/Default/emp_picture.jpg";
        }

        String MaxSpaceValue = employee.getEmpSpace();
        int maxValueCheck = Integer.parseInt(MaxSpaceValue);
        maxiFolderSize = maxValueCheck / 1048576;
        employee.setEmpSpace(maxiFolderSize.toString());
        // setGenderValues(employee);
        return SUCCESS;
    }

    // Simply To get Employee Id
    @SkipValidation
    public String getOrgChartEmployeeTab() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

    	int empId = employee.getEmployeeId();
        int loggedInEmp = oEmp.getEmployeeId();
        if(empId != loggedInEmp){
        	addActionError(getText("You can only able to view your information."));
        	return "LOOGEDINEMPLOYEE";
        }
        
        return SUCCESS;
    }

    // To get OrgChart Employee ID Leave
    @SkipValidation
    public String getOrgChartEmployeeNewLeaveID() {
        employee = emplService.getEmployees(getOrgChartEmployeeID());
        LeaveApproverVO leaveAppId = leaveReqsApprovalService.getApproverId(employee.getEmployeeId());
        if (leaveAppId != null) {
            leaveSelfApp = getText("label.header.leaveRequest.selfApprover");
        }
        leaveApproverList = leaveAppproverService.getCurrentLeaveApprover(employee.getEmployeeId());
        if (!(leaveApproverList.isEmpty())) {
            leaveAppDis = getText("label.header.leaveRequest.approver");
        }
        getOrgChartEmployeeID();
        return SUCCESS;
    }

    // To get OrgChart Employee ID Exoense
    @SkipValidation
    public String getOrgChartEmployeeNewExpensesID() {
        employee = emplService.getEmployees(getOrgChartEmployeeID());
        ExpensesApproverVO expensesAppId = expensesAppproverService.getSelfApprover(employee.getEmployeeId());
        if (expensesAppId != null) {
            expenseSelfApp = getText("label.header.expapprover.selfApprover");
        }
        expApproverList = expensesAppproverService.getAllSubEmployee(employee.getEmployeeId());
        if (!(expApproverList.isEmpty())) {
            expenseAppDis = getText("label.header.expapprover.approver");
        }
        getOrgChartEmployeeID();
        return SUCCESS;
    }

    // To get OrgChart Employee ID TimeSheet
    @SkipValidation
    public String getOrgChartEmployeeNewTimeSheetID() {
        employee = emplService.getEmployees(getOrgChartEmployeeID());
        TimeSheetApproverVO tsAppId = tsApproverService.getTsSelfApprover(employee.getEmployeeId());
        if (tsAppId != null) {
            timeSheetSelfApp = getText("label.header.timesheetApprover.selfApprover");
        }
        timeSheetApproverList = tsApproverService.getAllTimeSheeetSubEmployee(employee.getEmployeeId());
        if (!(timeSheetApproverList.isEmpty())) {
            timeSheetAppDis = getText("label.header.timesheetApprover.approver");
        }
        getOrgChartEmployeeID();
        return SUCCESS;
    }

    // Simply To get Leave Approver Employee Id
    @SkipValidation
    public String getOrgChartEmployeeLeaveApproverTab() {
        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get Sub Employee Leave Approver Employee Id
    @SkipValidation
    public String getOrgChartSubEmployeeLeaveApproverTab() {
        leaveApproverList = leaveAppproverService.getCurrentLeaveApprover(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get Expense Approver Employee Id
    @SkipValidation
    public String getOrgChartEmployeeExpenseApproverTab() {
        expApproverList = expensesAppproverService.getEmployeeAllExpApprover(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get Sub Employee Leave Approver Employee Id
    @SkipValidation
    public String getOrgChartSubEmployeeExpensesApproverTab() {
        expApproverList = expensesAppproverService.getAllSubEmployee(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get TimeSheet Approver Employee Id
    @SkipValidation
    public String getOrgChartEmployeeTsApproverTab() {
        timeSheetApproverList = tsApproverService.getEmployeeAllTimeSheetApprover(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get Sub Employee Leave Approver Employee Id
    @SkipValidation
    public String getOrgChartSubEmployeeTsApproverTab() {
        timeSheetApproverList = tsApproverService.getAllTimeSheeetSubEmployee(employee.getEmployeeId());
        return SUCCESS;
    }

    // //Simply To get Benefit Employee Id
    public String getOrgChartEmployeeBenefitTab() {
        employee = emplService.getEmployees(getOrgChartEmployeeID());
        benefitList = benefitService.getEmployeeBenefit(employee.getEmployeeId());
        return SUCCESS;
    }

    // Simply To get Select Employee Leave Quota
    @SkipValidation
    public String getOrgChartEmployeeLeaveQuotaTab() {
        empLeaveList = leaveQuotaService.getSingleEmployeeLeaveQuotaList(employee.getEmployeeId());
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

    // Simply To get Selected Employee SubEmployeeLeave Quota
    @SkipValidation
    public String getOrgChartSubEmployeeLeaveQuotaTab() {
        empLeaveList = leaveQuotaService.getSubEmployeeLeaveQuota(employee.getEmployeeId());
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

    // To get Particular Employee Data
    @SkipValidation
    public String orgChartEmpView() {
    	Map session = ActionContext.getContext().getSession();
        employee = emplService.getOrgChartEmployee(employee.getEmployeeId());
        if (employee.getEmpGender().equals("M")) {
            employee.setEmpGenderValue("Male");
        } else {
            employee.setEmpGenderValue("Female");
        }

        if(employee.getEmpPicturePath()!=null) {
        	String sClintId = String.valueOf(session.get("MASTER_CLIENT_ID"));
        	employeePicturePath = "./resources/images/employeepicture/"+"MASTER_CLIENTID_"+sClintId+"/"+employee.getEmployeeId()+"/"+employee.getEmpPicturePath();
        }else {
        	employeePicturePath = "./resources/images/employeepicture/Default/emp_picture.jpg";
        }

        String MaxSpaceValue = employee.getEmpSpace();
        int maxValueCheck = Integer.parseInt(MaxSpaceValue);
        maxiFolderSize = maxValueCheck / 1048576;
        employee.setEmpSpace(maxiFolderSize.toString());
        return SUCCESS;
    }

    @SkipValidation
    public String userImageUpload() {

    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO empsObj = emplService.getEmployeeById(employee.getEmployeeId());

    	 if (userImageFileName != null) {
         	String folderLocation =
         			getText("ApplicationAbsolutePath")+
         			ServletActionContext.getServletContext().getContextPath()+getText("WebContent")+
         			"resources/images/employeepicture/"+"MASTER_CLIENTID_"+String.valueOf(session.get("MASTER_CLIENT_ID"));

         	File foldLocation = new File(folderLocation);
         	boolean exists = foldLocation.exists();

         	if(exists) {
         		fileupload.uploadFile(
         				userImage.getAbsolutePath(),userImageFileName,
                 		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                 		getText("WebContent")+"resources/images/employeepicture/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/"+empsObj.getEmployeeId()+"/");
         	}else {
         		boolean createrFolder = foldLocation.mkdir();

         		if(createrFolder) {
         			fileupload.uploadFile(
         					userImage.getAbsolutePath(),
         					userImageFileName,
                     		getText("ApplicationAbsolutePath"),ServletActionContext.getServletContext().getContextPath(),
                     		getText("WebContent")+"resources/images/employeepicture/MASTER_CLIENTID_"+session.get("MASTER_CLIENT_ID")+"/"+empsObj.getEmployeeId()+"/");
         		}
         	}
         	employee.setEmpPicturePath(userImageFileName);
         }
    	 return SUCCESS;
    }

    // This method is used to DownLoad a particular file
    @SkipValidation
    public String userImageDownload() throws Exception {
        employee = emplService.getEmployees(employee.getEmployeeId());
        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + getText("EmployeeImages")
            + employee.getEmployeeId() + "/" + employee.getEmpPicturePath()));
        return SUCCESS;
    }

    // To delete particular Benefit data of an employee
    @SkipValidation
    public String deleteEmployeesPic() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        emplService.deleteEmployeesPic(employee);
        addActionMessage(getText("Deleted Sucessfully"));
        return SUCCESS;
    }
    
     // In the New Form when click Submit button To insert new Employee or update
    // particular Employee Data
    @SkipValidation
    public String insertOrUpdateEmployees() {
    	logger.debug("control enters into insert employees");
        //CountryVO country = new CountryVO();
        //CountryDaoService countryDao = new CountryDaoService();

        // Adding vendor name in employee Add Form
       /* VendorVO venObj = new VendorVO();
        new VendorDaoService();*/

        role = roleService.getRoleName(getText("message.label.common.adminName"));
       /* if ((!validationBirthDate())) {
            return INPUT;
        } else {*/
            /*if (employee.getEmpDriLicenExpDate() != null) {
                if ((!validationDriExpDate())) {
                    return INPUT;
                }
            }*/
           // country = countryDao.getCountry(employee.getCountry().getHcmocountryId());

            try {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                System.out.println(oEmp);
                System.out.println("firstname" + employee.getEmpFirstName());
                employee.setCreated(DateUtils.getCurrentDateTime());
                employee.setCreatedBy(oEmp);
                employee.setUpdatedBy(oEmp);
                employee.setEmpFullName(employee.getEmpFirstName() + " "
                    + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")");
                employee.setIsActive(1);
                if (employee.getEmpGender() == null) {
                    addActionError(getText("Please Select a Gender"));
                    return INPUT;
                }
                // Adding vendor name in employee Add Form
              /*  if ((employee.getEmpType().equals("Contract"))
                    || employee.getEmpType().equals("contract")) {
                    if ((employee.getVendorIdObj().getVendorId() == null)
                        || (employee.getVendorIdObj().getVendorId() < 1)) {
                        addActionError(getText("Vendor Name is required for Contract Employee"));
                        return INPUT;
                    }
                    if (employee.getVendorIdObj().getVendorId() == 1) {
                        addActionError(getText("Please select valid Vendor Name"));
                        return INPUT;
                    }
                } *//*else {
                    if (employee.getVendorIdObj().getVendorId() != null) {
                        addActionError(getText("Vendor Name is invalid for ")
                            + employee.getEmpType());
                        return INPUT;
                    } else {
                        venObj.setVendorId(1);
                        employee.setVendorIdObj(venObj);
                    }
                }*/

                /*if ((country.getCountryName().equals("United States of America"))
                    || (employee.getCountry().getHcmocountryId() == 29)) {
                    if ((employee.getEmpSSNNumber() == null)
                        || (employee.getEmpSSNNumber().trim().length() < 1)) {
                        addActionError(getText("SSN Number is required for United States of America"));
                        return INPUT;
                    } else {
                        emplService.insertEmployees(employee);
                        addActionMessage(getText("Added Successfully"));
                    }
                } else {
                    if (employee.getEmpSSNNumber().isEmpty() == false) {
                        addActionError(getText("SSN Number is invalid for ")
                            + country.getCountryName());
                        return INPUT;
                    } else {
                        emplService.insertEmployees(employee);
                        addActionMessage(getText("Added Successfully"));
                    }
                }*/
                
                emplService.insertEmployees(employee);
                addActionMessage(getText("Added Successfully"));
                
                employee = emplService.getEmployees(employee.getEmployeeId());
                System.out.println(employee);
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = employee.getEmployeeId();
                System.out.println("empid" +employeeID);
               
                String sSubject = getText("message.subject.employee.add");

                // Retrieved the Many more Admin employee list
             /*   List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                //mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.add.addedToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.add.addedBy"), employee.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.add.addedToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.add.addedBy"), employee.getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        }

                    }
                }
*/            } catch (RuntimeException e) {
                e.printStackTrace();
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                e.printStackTrace();
                throw e;

            } catch (Exception e) {
                e.printStackTrace();
            }
   //    }
        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;
    }
    
 // To insert or update Employee assets Data
    @SkipValidation
    public String insertOrUpdateEmpProjectAssign() {
   	 Map session = ActionContext.getContext().getSession();
 	Integer employeeId = (Integer) session.get("SELECTED_EMPLOYEE_ID");
    EmployeesVO empVO = emplService.getEmployees(employeeId);
        logger.debug("inside project assign emp obj :" + empVO);
    	System.out.println("inside project assign");
    	
    	logger.debug("control enters into insert project assigning");
    	  
    	logger.debug(" Im in 1  ");
        try {     
        	logger.debug("employee : " + employee);
        	
        	if(employee != null){
        		assProj = employee.getAssProj();
        	}
        	
        //	logger.debug("emp id" + assProj.getEmployeeName().getEmployeeId());

        	logger.debug("assProj : " + assProj);
        	
        	if(assProj != null){
        		

        	if (assProj.getProjectAssignEmpId() == null) {
        		logger.debug("IF assProj.getProjectAssignEmpId() != null : " + assProj.getProjectAssignEmpId());
        		logger.debug(" Im in 2  ");
                EmployeesVO newAdminEmp = null;
                
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assProj.setCreated(DateUtils.getCurrentDateTime());
                assProj.setCreatedBy(oEmp);
                assProj.setUpdatedBy(oEmp);
                assProj.setEmployeeName(empVO);
                logger.debug(" Im in 2.1...........................................  ");
                //assProjService.insertProjectAssignEmp(assProj);
                ProjectAssignEmpVO projAssignVO = assProjService.insertProjectAssignEmp(assProj);
                //EmployeesVO empVO = emplService.insertEmployees(employee);

                if(projAssignVO != null){
                	session.put("CREATED_PROJECT_ASSIGN_ID", projAssignVO.getProjectAssignEmpId());
                }
                
             //  System.out.println("added successfully");
                //System.out.println("Id" + (Integer)session.get("CREATED_PROJECT_ASSIGN_ID"));
                addActionMessage(getText("Added Successfully"));
                logger.info("Project assigned successfully");
            } else {
            	logger.debug("IF assProj.getProjectAssignEmpId() == null : " + assProj.getProjectAssignEmpId());
            	logger.debug(" Im in 3  ");
                // To Update the EEO
                EmployeesVO newAdminEmp = null;
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                assProj.setUpdatedBy(oEmp);
                System.out.println(" Im in 4  ");
                assProjService.updateProjectAssignEmpDao(assProj);
                addActionMessage(getText("Updated Successfully"));
                logger.info("Project assigned updated successfully");
            }
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        	logger.error("exception occurred during employee project insertion or updation" + e);
        }
     return SUCCESS;
    }
    
    public List<ProjectAssignEmpVO> getAssProjList() {
		return assProjList;
	}

	public void setAssProjList(List<ProjectAssignEmpVO> assProjList) {
		this.assProjList = assProjList;
	}

	public ProjectAssignEmpService getAssProjService() {
		return assProjService;
	}

	public void setAssProjService(ProjectAssignEmpService assProjService) {
		this.assProjService = assProjService;
	}

	public ProjectAssignEmpVO getAssProj() {
		return assProj;
	}

	public void setAssProj(ProjectAssignEmpVO assProj) {
		this.assProj = assProj;
	}

	@SkipValidation
    public String insertOrUpdateProjectActivity() {
    	logger.debug("inside project activity");
    	ProjectVO projObj = new ProjectVO();
    	ProjectActivityVO projActivityObj = new ProjectActivityVO();
    	List<ProjectActivityVO> projActivityList = new ArrayList<ProjectActivityVO>();
    	proActivity = employee.getProActivity();
        //ProjectActivityVO isExist = projActivityService.getProjectAndActivity(proActivity.getActivityName().toString(), proActivity.getProObj().getProjectId());
        if (proActivity.getProjectActivityId() == null) {
        	logger.debug("control enters into task details insertion");
            projObj = proService.getProject(proActivity.getProObj().getProjectId());  
            logger.debug("projObj : " + projObj);
            Double projHour = Double.valueOf(projObj.getEstimatedHours());
            logger.debug("projHour : " + projHour);
            /*if(proActivity.getEstimatedHours() != null){
            	Double projActivityHour = Double.valueOf(proActivity.getEstimatedHours());
            	Double totalActivityEstHour =  Double.valueOf(proActivity.getEstimatedHours());
            	
            	 if(projActivityHour > projHour){
          	   		addActionError("Project Hours Is Lesser Than Entered Project Activity Hours");
          	   		return ERROR;
          	   	}
            	 
            	 projActivityList = projActivityService.getAllProjActivityByProject(projObj);
            	 for (Iterator<ProjectActivityVO> it = projActivityList.iterator(); it.hasNext();) {
         			projActivityObj = it.next();
         			
         			if(projActivityObj.getEstimatedHours() != null){
         				totalActivityEstHour += Double.valueOf(projActivityObj.getEstimatedHours());
         			}
            	 }
            	 
            	 if(totalActivityEstHour > projHour){
            		 addActionError("Project Hours Is Lesser Than Over All Project Activity Hours");
           	   		return ERROR;
            	 }
            }*/
            
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            proActivity.setCreated(DateUtils.getCurrentDateTime());
            proActivity.setCreatedBy(oEmp);
            proActivity.setUpdatedBy(oEmp);
            proActivity.setIsActive(1);
            projActivityService.insertProjectActivity(proActivity);
            logger.info("tasks added successfully");
            /*try {
                proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = proActivity.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.projectActivity.add");

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
                                mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.add.addTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);

                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);

                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.add.addTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            addActionMessage(getText("Added Successfully"));
        } else {
        	logger.debug("control enters into project task updation");
           /* ProjectActivityVO isss = projActivityService.isExistProjectActivity(proActivity);
            if (isss != null) {
                addActionError("You already have same Project activity for this Project");
                return ERROR;
            }
            
            projObj = proService.getProject(proActivity.getProObj().getProjectId());
            if(projObj.getEstimatedHours() == null){
            	addActionError("Please Enter Estimated Hour For Project Before Entering Estimated Hour For Activity");
                return ERROR;
     	   	}
            
            Double projHour = Double.valueOf(projObj.getEstimatedHours());
            
            if(proActivity.getEstimatedHours() != null){
            	Double projActivityHour = Double.valueOf(proActivity.getEstimatedHours());
            	Double totalActivityEstHour = Double.valueOf(proActivity.getEstimatedHours());
            	
            	 if(projActivityHour > projHour){
          	   		addActionError("Project Hours Is Lesser Than Entered Project Activity Hours");
          	   		return ERROR;
          	   	}
            	 
            	 projActivityList = projActivityService.getAllProjActivityByProject(projObj);
            	 for (Iterator<ProjectActivityVO> it = projActivityList.iterator(); it.hasNext();) {
         			projActivityObj = it.next();
         			
         			if(projActivityObj.getEstimatedHours() != null){
         				totalActivityEstHour += Double.valueOf(projActivityObj.getEstimatedHours());
         			}
            	 }
            	 
            	 if(totalActivityEstHour > projHour){
            		addActionError("Project Hours Is Lesser Than Over All Project Activity Hours");
           	   		return ERROR;
            	 }
            	 
            	 objectForProActivityCount = projectAssignEmpService.getProjAssignListByProjActivityAndProj(proActivity.getProObj().getProjectId(), proActivity.getProjectActivityId());
            	 
            	 if(!(objectForProActivityCount.isEmpty())){
            		 Integer countAllocatedHours = 0;
            		 Integer estimatedActivityHours = Integer.valueOf(proActivity.getEstimatedHours());
            		 
            		 for (Iterator<ProjectAssignEmpVO> it = objectForProActivityCount.iterator(); it.hasNext();) {
                		 ProAssignObj = it.next();
                		 if(ProAssignObj.getAllocatedHours() != null){
                			 countAllocatedHours += Integer.valueOf(ProAssignObj.getAllocatedHours());
                		 }
                	 }
            		 
            		 if(estimatedActivityHours < countAllocatedHours){
            			 addActionError("Project Activity Hours Is Lesser Than Over All Assigned Project Hours");
                	   	 return ERROR;
            		 }
            	 }
            }
            */
            
            
        	EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            proActivity.setUpdatedBy(oEmp);
            projActivityService.updateProjectActivity(proActivity);
            logger.info("task updated successfully");

            /*proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = proActivity.getEmpIdObj().getEmployeeId();

            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
            int lengthForAdminEmpList = adminRoleId.size();
            String sSubject = getText("message.subject.projectActivity.edit");

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
                            mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.edit.updateTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);

                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);

                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.edit.updateTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                    }

                }
            }*/
            addActionMessage(getText("Updated Successfully"));
        }
        return SUCCESS;
    }
    
    public String getAllProjectActivity() {
        projActivityList = projActivityService.getAllProjectActivity();
        return SUCCESS;
    }
    

    
    public String updateEmployees() {
        CountryVO country = new CountryVO();
        CountryDaoService countryDao = new CountryDaoService();

        // Updating vendor name in employee Edit Form
        VendorVO venObj = new VendorVO();
        new VendorDaoService();

        role = roleService.getRoleName(getText("message.label.common.adminName"));
        if ((!validationBirthDate())) {
            return INPUT;
        } else {
            if (employee.getEmpDriLicenExpDate() != null) {
                if ((!validationDriExpDate())) {
                    return INPUT;
                }
            }
            country = countryDao.getCountry(employee.getCountry().getHcmocountryId());
            try {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                employee.setUpdatedBy(oEmp);

                EmployeesVO empDataBeforUpdateInDB = emplService.getEmployees(employee.getEmployeeId());
                adminListSize = emplService.getAdminEmpListSize(role.getHcmoRoleId());
                int empRoleIdBeforeUpdate = empDataBeforUpdateInDB.getRoleObj().getHcmoRoleId();
                int currentRoleId = employee.getRoleObj().getHcmoRoleId();
                if (empRoleIdBeforeUpdate != currentRoleId) {
                    int adminRoleId = role.getHcmoRoleId();
                    int adminListSizeCount = adminListSize.size();
                    if ((empRoleIdBeforeUpdate == adminRoleId) && (adminListSizeCount == 1)) {
                        addActionError(getText("Entire application should have mininum of one Admin"));
                        return INPUT;
                    }
                }
                if (employee.getEmpGender() == null) {
                    addActionError(getText("Please Select a Gender"));
                    return INPUT;
                }
                // Updating vendor name in employee Edit Form
                if ((employee.getEmpType().equals("Contract"))
                    || employee.getEmpType().equals("contract")) {
                    if ((employee.getVendorIdObj().getVendorId() == null)
                        || (employee.getVendorIdObj().getVendorId() < 1)) {
                        addActionError(getText("Vendor Name is required for Contract Employee"));
                        return INPUT;
                    }
                    if (employee.getVendorIdObj().getVendorId() == 1) {
                        addActionError(getText("Please select valid Vendor Name"));
                        return INPUT;
                    }
                } else {
                    if (employee.getVendorIdObj().getVendorId() != null) {
                        addActionError(getText("Vendor Name is invalid for ")
                            + employee.getEmpType());
                        return INPUT;
                    } else {
                        venObj.setVendorId(1);
                        employee.setVendorIdObj(venObj);
                    }
                }

                if ((country.getCountryName().equals("United States of America"))
                    || (employee.getCountry().getHcmocountryId() == 29)) {
                    if ((employee.getEmpSSNNumber() == null)
                        || (employee.getEmpSSNNumber().trim().length() < 1)) {
                        addActionError(getText("SSN Number is required for United States of America"));
                        return INPUT;
                    } else {

                    	if (userImageFileName != null) {
                        	userImageUpload();
        				}else {
        					EmployeesVO empsObj = emplService.getEmployeeById(employee.getEmployeeId());

        					if(empsObj.getEmpPicturePath()!=null) {
        						employee.setEmpPicturePath(empsObj.getEmpPicturePath());
        					}else {
        						employee.setEmpPicturePath(null);
        					}
        				}

                        emplService.updateEmployees(employee);
                        addActionMessage(getText("Updated Successfully"));

                        // For Drop down List
                        loadValues.getAllEmployeeName();

                        // To Set Employee information values in session.If the
                        // LoggedIn Employee edited his own details
                        if (employee.getEmployeeId().equals(oEmp.getEmployeeId())) {
                            session.put("FIRST_NAME", employee.getEmpFirstName());
                            session.put("MIDDLE_NAME", employee.getEmpMidName());
                            session.put("LAST_NAME", employee.getEmpLastName());
                            session.put("ROLE", employee.getRoleObj().getRoleName());
                            session.put("EMPLOYEE_ID", employee.getEmployeeId());
                            session.put("EMPLOYEE_OBJECT", employee);
                            session.put("EMPLOYEE_EMAIL", employee.getEmpWorkEmail());
                            session.put("EMPLOYEE_SPACE_MAX_SIZE", employee.getEmpSpace());
                            session.put("SSN_NUMBER", employee.getEmpSSNNumber());
                        }

                        employee = emplService.getEmployees(employee.getEmployeeId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));
                        // EmployeesVO adminRoleId =
                        // roleService.getEmployeeId(role.getHcmoRoleId());

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = employee.getEmployeeId();
                        String sSubject = getText("message.subject.employee.edit");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updateToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedBy"), employee.getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updateToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedBy"), employee.getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        return SUCCESS;
                    }
                } else {
                    if (employee.getEmpSSNNumber().isEmpty() == false) {
                        addActionError(getText("SSN Number is invalid for ")
                            + country.getCountryName());
                        return INPUT;
                    }
                    if (empRoleIdBeforeUpdate != currentRoleId) {
                        int adminRoleId = role.getHcmoRoleId();
                        int adminListSizeCount = adminListSize.size();
                        if ((empRoleIdBeforeUpdate == adminRoleId) && (adminListSizeCount == 1)) {
                            addActionError(getText("Entire application should have mininum of one Admin"));
                            return INPUT;
                        }
                    }

                    if (userImageFileName != null) {
                    	userImageUpload();
    				}else {
    					EmployeesVO empsObj = emplService.getEmployeeById(employee.getEmployeeId());

    					if(empsObj.getEmpPicturePath()!=null) {
    						employee.setEmpPicturePath(empsObj.getEmpPicturePath());
    					}else {
    						employee.setEmpPicturePath(null);
    					}
    				}


                    emplService.updateEmployees(employee);
                    addActionMessage(getText("Updated Successfully"));

                    // For Drop down List
                    loadValues.getAllEmployeeName();

                    // To Set Employee information values in session.If the
                    // LoggedIn Employee edited his own details
                    if (employee.getEmployeeId().equals(oEmp.getEmployeeId())) {
                        session.put("FIRST_NAME", employee.getEmpFirstName());
                        session.put("MIDDLE_NAME", employee.getEmpMidName());
                        session.put("LAST_NAME", employee.getEmpLastName());
                        session.put("ROLE", employee.getRoleObj().getRoleName());
                        session.put("EMPLOYEE_ID", employee.getEmployeeId());
                        session.put("EMPLOYEE_OBJECT", employee);
                        session.put("EMPLOYEE_EMAIL", employee.getEmpWorkEmail());
                        session.put("EMPLOYEE_SPACE_MAX_SIZE", employee.getEmpSpace());
                        session.put("SSN_NUMBER", employee.getEmpSSNNumber());
                    }

                    employee = emplService.getEmployees(employee.getEmployeeId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = employee.getEmployeeId();
                    String sSubject = getText("message.subject.employee.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updateToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedBy"), employee.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.edit.updateToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.edit.updatedBy"), employee.getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }

                    return SUCCESS;
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                e.printStackTrace();
                throw e;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;

    }

    // To delete particular Employee data
    @SkipValidation
    public String deleteEmployees() {
        try {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            employee.setUpdatedBy(oEmp);
            emplService.deleteEmployees(employee);

            employee = emplService.getEmployees(employee.getEmployeeId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = employee.getEmployeeId();
            String sSubject = getText("message.subject.employee.delete");

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
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.delete.deleteToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.delete.deletedBy"), employee.getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employee.delete.deleteToAdmin"), employee.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employee.delete.deletedBy"), employee.getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(employee.getEmployeeId(), employee.getEmpFirstName(), getText("employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;
    }

    // Validation method for Employee BirthDate
    private boolean validationBirthDate() {
        if (DateUtils.isMajor(employee.getEmpBirthDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.dob.invalid"));
            return false;
        }
    }

    // Validation Method for Employee DriExpDate
    private boolean validationDriExpDate() {
        if (DateUtils.isGreaterDate(employee.getEmpDriLicenExpDate())) {
            return true;
        } else {
            addActionError(getText("errors.employee.driLicenseExpDate.invalid"));
            return false;
        }
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date empBdate = employee.getEmpBirthDate();
            empBirthDateMail = dateformat.format(empBdate);
            Date empJdate = employee.getEmpJoineddate();
            empJoinDateMail = dateformat.format(empJdate);
            Date empModdate = employee.getUpdated();
            empModifiedDateMail = updateddateformat.format(empModdate);
            if (employee.getEmpDriLicenExpDate() != null) {
                Date empLicdate = employee.getEmpDriLicenExpDate();
                empLicenseExpDateMail = dateformat.format(empLicdate);
            }

            String sDummy = Constants.PERSON;

            if ((employee.getEmpGender() == "F") || employee.getEmpGender().equals("F")) {
                sGender = "Female";
            } else {
                sGender = "Male";
            }
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
                + getText("label.header.employee.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.firstName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.lastName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpLastName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.birthDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empBirthDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.gender") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + sGender
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.maritalStatus")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpMaritalStatus()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.ethnicRace.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEthnicRaceIdObj().getEthnicRaceDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.country.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getCountry().getCountryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.department.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getDepartmentIdObj().getDeptName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.team.name")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getTeamIdObj().getTeamName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.empType") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getVendorIdObj().getVendorId() != 1) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.vendor.vendorName")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getVendorIdObj().getVendorName()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.empStatus")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpStatusIdObj().getStatusName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpDriLicenNum().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.driLicenseNo")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpDriLicenNum() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            if (employee.getEmpDriLicenExpDate() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.driLicenseExpDate")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + empLicenseExpDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.nationality.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getNationalityIdObj().getNationalityName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpZipCode().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.common.zipCode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpZipCode() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.city") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpCityName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpCounName().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.region.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpCounName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.street1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpStreet1() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpHmTelephone().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.homeTelephone")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpHmTelephone() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.mobile") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpMobile() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.role.name")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getRoleObj().getRoleName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.jobTitle.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getJobTitleIdObj().getJobTitleName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.workEmail")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpWorkEmail()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.employee.employeeWage")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmployeeWage()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.joinedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empJoinDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empModifiedDateMail
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
            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date empBdate = employee.getEmpBirthDate();
            empBirthDateMail = dateformat.format(empBdate);
            Date empJdate = employee.getEmpJoineddate();
            empJoinDateMail = dateformat.format(empJdate);
            Date empModdate = employee.getUpdated();
            empModifiedDateMail = updateddateformat.format(empModdate);
            if (employee.getEmpDriLicenExpDate() != null) {
                Date empLicdate = employee.getEmpDriLicenExpDate();
                empLicenseExpDateMail = dateformat.format(empLicdate);
            }

            String sDummy = Constants.PERSON;

            if ((employee.getEmpGender() == "F") || employee.getEmpGender().equals("F")) {
                sGender = "Female";
            } else {
                sGender = "Male";
            }
            StringBuilder sMessage = new StringBuilder();
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;

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
                + getText("label.header.employee.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.firstName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.lastName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpLastName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.birthDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empBirthDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.gender") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + sGender
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.maritalStatus")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpMaritalStatus()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.ethnicRace.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEthnicRaceIdObj().getEthnicRaceDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.country.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getCountry().getCountryName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.department.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getDepartmentIdObj().getDeptName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.team.name")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getTeamIdObj().getTeamName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.empType") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpType() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getVendorIdObj().getVendorId() != 1) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.vendor.vendorName")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getVendorIdObj().getVendorName()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.empStatus")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpStatusIdObj().getStatusName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpDriLicenNum().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.driLicenseNo")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpDriLicenNum() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            if (employee.getEmpDriLicenExpDate() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.driLicenseExpDate")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + empLicenseExpDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.nationality.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getNationalityIdObj().getNationalityName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpZipCode().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.common.zipCode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpZipCode() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.city") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpCityName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpCounName().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.region.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpCounName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.street1") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpStreet1() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (employee.getEmpHmTelephone().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employee.homeTelephone")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + employee.getEmpHmTelephone() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.mobile") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getEmpMobile() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.role.name")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getRoleObj().getRoleName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.jobTitle.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + employee.getJobTitleIdObj().getJobTitleName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.workEmail")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmpWorkEmail()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.employee.employeeWage")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + employee.getEmployeeWage()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employee.joinedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empJoinDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empModifiedDateMail
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

    public EmployeesVO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeesVO employee) {
        this.employee = employee;
    }

    public List<EmployeesVO> getEmploList() {
        return emploList;
    }

    public void setEmploList(List<EmployeesVO> emploList) {
        this.emploList = emploList;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public File getUserImage() {
        return userImage;
    }

    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }

    public String getUserImageContentType() {
        return userImageContentType;
    }

    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }

    public String getUserImageFileName() {
        return userImageFileName;
    }

    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }

    public EmployeesService getEmplService() {
        return emplService;
    }

    public void setEmplService(EmployeesService emplService) {
        this.emplService = emplService;
    }

    public LeaveTypeService getLeaveTypeService() {
        return leaveTypeService;
    }

    public void setLeaveTypeService(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    public EmployeeLeaveQuotaService getLeaveQuotaService() {
        return leaveQuotaService;
    }

    public void setLeaveQuotaService(EmployeeLeaveQuotaService leaveQuotaService) {
        this.leaveQuotaService = leaveQuotaService;
    }

    public EmployeeLeaveQuotaVO getEmployeeLeaveQuota() {
        return employeeLeaveQuota;
    }

    public void setEmployeeLeaveQuota(EmployeeLeaveQuotaVO employeeLeaveQuota) {
        this.employeeLeaveQuota = employeeLeaveQuota;
    }

    public LeaveTypeVO getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypeVO leaveType) {
        this.leaveType = leaveType;
    }

    public List<LeaveTypeVO> getLeaveTypeList() {
        return leaveTypeList;
    }

    public void setLeaveTypeList(List<LeaveTypeVO> leaveTypeList) {
        this.leaveTypeList = leaveTypeList;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public List<EmployeeReportToVO> getEmpReportToList() {
        return empReportToList;
    }

    public void setEmpReportToList(List<EmployeeReportToVO> empReportToList) {
        this.empReportToList = empReportToList;
    }

    public List<LeaveApproverVO> getLeaveApproverList() {
        return leaveApproverList;
    }

    public void setLeaveApproverList(List<LeaveApproverVO> leaveApproverList) {
        this.leaveApproverList = leaveApproverList;
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

    public List<ExpensesApproverVO> getExpApproverList() {
        return expApproverList;
    }

    public void setExpApproverList(List<ExpensesApproverVO> expApproverList) {
        this.expApproverList = expApproverList;
    }

    public String getExpenseAppDis() {
        return expenseAppDis;
    }

    public void setExpenseAppDis(String expenseAppDis) {
        this.expenseAppDis = expenseAppDis;
    }

    public String getExpenseSelfApp() {
        return expenseSelfApp;
    }

    public void setExpenseSelfApp(String expenseSelfApp) {
        this.expenseSelfApp = expenseSelfApp;
    }

    public List<TimeSheetApproverVO> getTimeSheetApproverList() {
        return timeSheetApproverList;
    }

    public void setTimeSheetApproverList(List<TimeSheetApproverVO> timeSheetApproverList) {
        this.timeSheetApproverList = timeSheetApproverList;
    }

    public String getTimeSheetSelfApp() {
        return timeSheetSelfApp;
    }

    public void setTimeSheetSelfApp(String timeSheetSelfApp) {
        this.timeSheetSelfApp = timeSheetSelfApp;
    }

    public String getTimeSheetAppDis() {
        return timeSheetAppDis;
    }

    public void setTimeSheetAppDis(String timeSheetAppDis) {
        this.timeSheetAppDis = timeSheetAppDis;
    }

    public List<BenefitsVO> getBenefitList() {
        return benefitList;
    }

    public void setBenefitList(List<BenefitsVO> benefitList) {
        this.benefitList = benefitList;
    }

    public BenefitsVO getBenefit() {
        return benefit;
    }

    public void setBenefit(BenefitsVO benefit) {
        this.benefit = benefit;
    }

    public String getBenefitEmployee() {
        return benefitEmployee;
    }

    public void setBenefitEmployee(String benefitEmployee) {
        this.benefitEmployee = benefitEmployee;
    }

    public List<EmployeeLeaveQuotaVO> getEmpLeaveList() {
        return empLeaveList;
    }

    public void setEmpLeaveList(List<EmployeeLeaveQuotaVO> empLeaveList) {
        this.empLeaveList = empLeaveList;
    }

    public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public List<EmployeesVO> getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminListSize(List<EmployeesVO> adminListSize) {
        this.adminListSize = adminListSize;
    }

    public List<EmployeesVO> getAdminListSize() {
        return adminListSize;
    }

    public List<ImportantNewsVO> getImpList() {
        return impList;
    }

    public void setImpList(List<ImportantNewsVO> impList) {
        this.impList = impList;
    }

    public List<EmployeesVO> getEmpBirthdayList() {
        return empBirthdayList;
    }

    public void setEmpBirthdayList(List<EmployeesVO> empBirthdayList) {
        this.empBirthdayList = empBirthdayList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public Integer getEssEmployeeID() {
        return essEmployeeID;
    }

    public void setEssEmployeeID(Integer essEmployeeID) {
        this.essEmployeeID = essEmployeeID;
    }

    public Integer getOrgChartEmployeeID() {
        return orgChartEmployeeID;
    }

    public void setOrgChartEmployeeID(Integer orgChartEmployeeID) {
        this.orgChartEmployeeID = orgChartEmployeeID;
    }

	public String getEmployeePicturePath() {
		return employeePicturePath;
	}

	public void setEmployeePicturePath(String employeePicturePath) {
		this.employeePicturePath = employeePicturePath;
	}
}
