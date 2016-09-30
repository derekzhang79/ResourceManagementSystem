
package com.gits.rms.action.utils;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.service.BenefitsDaoService;
import com.gits.rms.service.BenefitsService;
import com.gits.rms.service.ClientDaoService;
import com.gits.rms.service.ClientService;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.service.CustomerDaoService;
import com.gits.rms.service.CustomerService;
import com.gits.rms.service.DeductionService;
import com.gits.rms.service.DeductionsDaoService;
import com.gits.rms.service.DepartmentDaoService;
import com.gits.rms.service.DepartmentService;
import com.gits.rms.service.EmployeeLeaveQuotaDaoService;
import com.gits.rms.service.EmployeeLeaveQuotaService;
import com.gits.rms.service.EmployeeStatusDaoService;
import com.gits.rms.service.EmployeeStatusService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.EthnicRaceDaoService;
import com.gits.rms.service.EthnicRaceService;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.ExpensesTypeDaoService;
import com.gits.rms.service.ExpensesTypeService;
import com.gits.rms.service.JobTitleDaoService;
import com.gits.rms.service.JobTitleService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.LeaveHistoryDaoService;
import com.gits.rms.service.LeaveHistoryService;
import com.gits.rms.service.LeaveTypeDaoService;
import com.gits.rms.service.LeaveTypeService;
import com.gits.rms.service.LocationDaoService;
import com.gits.rms.service.LocationService;
import com.gits.rms.service.NationalityDaoService;
import com.gits.rms.service.NationalityService;
import com.gits.rms.service.OrganizationDaoService;
import com.gits.rms.service.OrganizationService;
import com.gits.rms.service.OrganizationTypeDaoService;
import com.gits.rms.service.OrganizationTypeService;
import com.gits.rms.service.PayStubDaoService;
import com.gits.rms.service.PayStubService;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RegionDaoService;
import com.gits.rms.service.RegionService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SalaryGradeDaoService;
import com.gits.rms.service.SalaryGradeService;
import com.gits.rms.service.TargetTypeDaoService;
import com.gits.rms.service.TargetTypeService;
import com.gits.rms.service.TargetsDaoService;
import com.gits.rms.service.TargetsService;
import com.gits.rms.service.TeamDaoService;
import com.gits.rms.service.TeamService;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.service.TimesheetCategoryDaoService;
import com.gits.rms.service.TimesheetCategoryEmpDaoService;
import com.gits.rms.service.TimesheetCategoryEmpService;
import com.gits.rms.service.TimesheetCategoryService;
import com.gits.rms.service.VendorDaoService;
import com.gits.rms.service.VendorService;
import com.gits.rms.service.performance.CategoryDaoService;
import com.gits.rms.service.performance.CategoryService;
import com.gits.rms.service.performance.QuestionBankDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoService;
import com.gits.rms.service.performance.QuestionBankService;
import com.gits.rms.service.performance.SubCategoryDaoService;
import com.gits.rms.service.performance.SubCategoryService;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.CategoryVO;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.DeductionsVO;
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeeStatusVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EthnicRaceVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.ExpensesTypeVO;
import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.OrganizationTypeVO;
import com.gits.rms.vo.OrganizationVO;
import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.RegionVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SalaryGradeVO;
import com.gits.rms.vo.SubCategoryVO;
import com.gits.rms.vo.TargetTypeVO;
import com.gits.rms.vo.TeamVO;
import com.gits.rms.vo.TimesheetCategoryEmpVO;
import com.gits.rms.vo.TimesheetCategoryVO;
import com.gits.rms.vo.VendorVO;



public class LoadKeyValuesAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6239114953401728110L;
	
	private Logger logger = Logger.getLogger(LoadKeyValuesAction.class);
	
	private RegionService regService = new RegionDaoService();
    private List<RegionVO> region;
    private InputStream inStream;

    private CountryService couService = new CountryDaoService();
    private List<CountryVO> country;
    private EmployeesService emplService = new EmployeesDaoService();
    private List<EmployeesVO> emploList;
    private List<EmployeesVO> adminRoleId;

    private CustomerService custService = new CustomerDaoService();
    private List<CustomerVO> custList;

    private OrganizationService orgService = new OrganizationDaoService();
	Map mSession = ActionContext.getContext().getSession();
    private List<OrganizationVO> orgList;

    private SalaryGradeService salgraService = new SalaryGradeDaoService();
    private List<SalaryGradeVO> salarygrade;

    private LocationService locService = new LocationDaoService();
    private List<LocationVO> location;

    private ExpensesTypeService expTypeService = new ExpensesTypeDaoService();
    private List<ExpensesTypeVO> expTypeList;
    
    private OrganizationTypeService orgtypeService = new OrganizationTypeDaoService();
    private List<OrganizationTypeVO> orgtypeList;

    private EthnicRaceService ethnicRaceService = new EthnicRaceDaoService();
    private List<EthnicRaceVO> ethnicRaceList;

    private NationalityService natiService = new NationalityDaoService();
    private List<NationalityVO> natiList;
    
    private TargetTypeService targetTypeService = new TargetTypeDaoService();
    private List<TargetTypeVO> targetTypeVOList;

    private JobTitleService jobTitleService = new JobTitleDaoService();
    private List<JobTitleVO> jobTitleList;

    private RoleService roleService = new RoleDaoService();
    private List<RoleVO> roleList;

    private ProjectService proService = new ProjectDaoService();
    private List<ProjectVO> proList;

    private LeaveTypeService leaveTypeService = new LeaveTypeDaoService();
    private List<LeaveTypeVO> leaveTypeIdObj;

    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private List<LeaveApproverVO> leaveApproverList;

    private LeaveHistoryService leaveHistoryService = new LeaveHistoryDaoService();
    private List<LeaveHistoryVO> lHistList;

    private BenefitsService benefitsService = new BenefitsDaoService();
    private List<BenefitsVO> benefitsList;

    private ClientService clientService = new ClientDaoService();
    private List<ClientVO> clientList;

    private EmployeeStatusService empStatusService = new EmployeeStatusDaoService();
    private List<EmployeeStatusVO> empStatusList;

    private VendorService vendorService = new VendorDaoService();
    private List<VendorVO> vendorList;
    private String vendorName;

    private DepartmentService deptService = new DepartmentDaoService();
    private List<DepartmentVO> deptList;

    private TeamService teamService = new TeamDaoService();
    private List<TeamVO> teamList;
    
    private ProjectActivityService projectActivityService = new ProjectActivityDaoService();
    private List<ProjectActivityVO> projectActivityList;
    private ProjectActivityVO projectActivityObj;

    private RoleVO role;
    private List<EmployeesVO> empListForTeam;
    private List<EmployeesVO> empListForDepartment;
    private List<EmployeesVO> empListForExpApprover;
    private List<EmployeesVO> empListForLeaveApprover;
    private List<EmployeesVO> empListForTimesheetApprover;
    private List<EmployeesVO> empListForRole;

    private TimesheetCategoryService timesheetCategoryService = new TimesheetCategoryDaoService();
    private List<TimesheetCategoryVO> timesheetCategoryList;

    private TimesheetCategoryEmpService timesheetCategoryEmpService = new TimesheetCategoryEmpDaoService();
    private List<TimesheetCategoryEmpVO> timesheetCategoryEmplist;

    private EmployeeLeaveQuotaService leaveQuotaService = new EmployeeLeaveQuotaDaoService();
    private List<EmployeeLeaveQuotaVO> leaveQuotaList;
    private List<EmployeesVO> leaveQuotaSubEmpList;

    private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();

    private DeductionService deductionService = new DeductionsDaoService();
    private List<DeductionsVO> dedList;

    private PayStubService payStubService = new PayStubDaoService();
    private PayStubVO payStubObj;

    private ExpensesApproverService expAppproverService = new ExpensesApproverDaoService();
    private List<ExpensesApproverVO> expApproverList;
    private ExpensesApproverVO expApprover = new ExpensesApproverVO();

    private ProjectActivityService projActivityService = new ProjectActivityDaoService();
    private List<ProjectActivityVO> projActivityList = new ArrayList<ProjectActivityVO>();
    
    private CategoryService categoryService=new CategoryDaoService();
    private List<CategoryVO> categoryList;
    
    private SubCategoryService subCatService=new SubCategoryDaoService();
    private List<SubCategoryVO> subCategoryList;
    
    private QuestionBankService quesBankService=new QuestionBankDaoService();
    private List<QuestionBankVO> quesBankList;
    
    private QuestionBankGeneralInfoService quesBankGeneralInfoService=new QuestionBankGeneralInfoDaoService();
    private List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList;
    private List employeeList;

    private TargetsService targetService = new TargetsDaoService();
    private List<String> targetsNameList;
    private List<String> targetTypeList;
    private List<String> goalNameList;
   	private List<String> targetList;
   	private List<EmployeesVO> kpiAssignEmplList;

    
    public String getAllLeaveApproverName() {
        leaveApproverList = leaveAppproverService.getAllLeaveApprover();
        StringBuilder sbResult = new StringBuilder("[");
        for (LeaveApproverVO l : leaveApproverList) {
            sbResult.append("[\"");
            sbResult.append(l.getHcmoLeaveApproverId());
            sbResult.append("\",\"");
            sbResult.append(l.getHcmoLeaveApproverId());
            sbResult.append("\"],");
        }
        sbResult.append("]");

        inStream = new StringBufferInputStream(String.valueOf(sbResult));
        return SUCCESS;
    }
    
    public void getCardExpYear() {
    	List payYearList=new ArrayList();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String curyr=sdf.format(new Date());
		int cy=Integer.valueOf(curyr);
		int endyr=cy+11;
		//payYearList.add(" ");
		for(int i=cy;i<endyr;i++){
			payYearList.add(i);
		}
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.PAY_EXPIRY_YEAR), payYearList);
    }
    
    public String getAllLeaveName() {
        leaveTypeIdObj = leaveTypeService.getAllLeaveType();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVE_LIST), leaveTypeIdObj);
        return SUCCESS;
    }

    public void getAllProjectName() {
        proList = proService.getAllProjects();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_PROJECT_LIST), proList);
    }
    
    public void setEmpProjectName(List<ProjectVO> projectList){
    	ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EMP_PROJECT_LIST), projectList);
    }
    
    public void getAllRoleName() {
        roleList = roleService.getAllRole();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ROLE_LIST), roleList);
    }

    public void getAllExpensesTypeName() {
        expTypeList = expTypeService.getAllExpensesType();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EXPENSESTYPE_LIST), expTypeList);
    }

    public void getAllJobTitleName() {
        jobTitleList = jobTitleService.getAllJobTitle();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_JOBTITLE_LIST), jobTitleList);
    }

    public void getAllNationalityName() {
        natiList = natiService.getAllNationality();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_NATIONALITY_LIST), natiList);
    }
    
    public void getAllTargetType() {
    	targetTypeVOList = targetTypeService.getAllTargetType();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_TARGETTYPE_LIST), targetTypeVOList);
    }

    public void getAllEthnicRaceName() {
        ethnicRaceList = ethnicRaceService.getAllEthnicRace();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ETHNICRACE_LIST), ethnicRaceList);
    }

    // Postal code has been changed to text field from dropdown
    public void getAllZipCode() {
        region = regService.getAllRegion();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ZIPCODE_LIST), region);
    }

    public void getAllCountry() {
        country = couService.getAllCountry();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_COUNTRY_LIST), country);
    }

    public String getRegionDetails() {
        Integer zipcode = Integer.valueOf(ServletActionContext.getRequest().getParameter("zipcode"));
        String sRegionDetails = new String();
        region = regService.loadRegion(zipcode);
        for (RegionVO r : region) {
            sRegionDetails = r.getCity();
            sRegionDetails += "$$$";
            sRegionDetails += r.getName();
            break;
        }

        inStream = new StringBufferInputStream(sRegionDetails);
        return SUCCESS;
    }

    public String getProjectDetails() {
        Integer projcode = Integer.valueOf(ServletActionContext.getRequest().getParameter("projName"));
        String sProjDetails = new String();
        proList = proService.loadProject(projcode);
        for (ProjectVO pro : proList) {

            sProjDetails = String.valueOf(pro.getEmpIdObj().getEmployeeId());
            sProjDetails += "$$$";
            sProjDetails += pro.getEmpIdObj().getEmpFirstName();
            sProjDetails += "$$$";
            sProjDetails += String.valueOf(pro.getCustomerId().getCustomerId());
            sProjDetails += "$$$";
            sProjDetails += pro.getCustomerId().getCustomerName();
            sProjDetails += "$$$";
            break;
        }

        inStream = new StringBufferInputStream(sProjDetails);
        return SUCCESS;
    }

    public String getDeductionDetails() {
    	
    	//skip001
        //Integer payStubDeduction = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStubDeduction"));
    	Integer payStubDeduction = new Integer(java.sql.Types.NULL);
    	if(ServletActionContext.getRequest().getParameter("payStubDeduction") != null){
    		payStubDeduction = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStubDeduction"));
    	}
    	
        String sDeductionDetails = new String();
        
        if(payStubDeduction > 0){
        	dedList = deductionService.getDeductionList(payStubDeduction);
        }
        
		if(dedList != null){
			
			if(!dedList.isEmpty()){
			    for (DeductionsVO deduction : dedList) {
			        sDeductionDetails = deduction.getDeductionType();
			        sDeductionDetails += "$$$";
			        sDeductionDetails += deduction.getDeductionMode();
			        break;
			    }
			}
		
		}


        inStream = new StringBufferInputStream(sDeductionDetails);
        return SUCCESS;
    }

   public String getDeductionAmount() {
	   
	   //skip001 start
	   /*        
	    Double payStubDeductionAmount = Double.valueOf(ServletActionContext.getRequest().getParameter("payStubDeductionAmount")); 
	    String payStubDeductionMode = String.valueOf(ServletActionContext.getRequest().getParameter("payStubDeductionMode"));
       Integer payStubId = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStubId"));*/
	   
	   Double payStubDeductionAmount = new Double(java.sql.Types.NULL);
	   if(ServletActionContext.getRequest().getParameter("payStubDeductionAmount") != null){
		   payStubDeductionAmount = Double.valueOf(ServletActionContext.getRequest().getParameter("payStubDeductionAmount"));
	   }
	   String payStubDeductionMode = "";
	   if(ServletActionContext.getRequest().getParameter("payStubDeductionMode") != null){
		   payStubDeductionMode = String.valueOf(ServletActionContext.getRequest().getParameter("payStubDeductionMode"));
	   }
        
	   Integer payStubId = new Integer(java.sql.Types.NULL);
	   
	   if(ServletActionContext.getRequest().getParameter("payStubId") != null){
		   payStubId = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStubId"));
	   }
	   //skip001 end

        String sDeductionAmountDetails = new String();
        if (payStubDeductionMode.equals("Actuals")) {
            sDeductionAmountDetails = String.valueOf(payStubDeductionAmount);

        } else if (payStubDeductionMode.equals("Percentage")) {
            payStubObj = payStubService.getPayStub(payStubId);
            if(payStubObj != null){
                Double tempSalary = ((payStubDeductionAmount / 100) * payStubObj.getNetSalary());
                sDeductionAmountDetails = String.valueOf(tempSalary);
            }
        }

        inStream = new StringBufferInputStream(sDeductionAmountDetails);
        return SUCCESS;
    }

   public String getProjectEstimatedHours() {
       ProjectVO projObj = new ProjectVO();
	   Integer projectId = Integer.valueOf(ServletActionContext.getRequest().getParameter("proIdEstHours"));
	   projObj = proService.getProject(projectId);
	   String sEstimatedHrs = "";
	   
	   if(projObj.getEstimatedHours() !=null){
		   sEstimatedHrs =  projObj.getEstimatedHours();
	   }else{
		   sEstimatedHrs = "No Estimated Hour Entered To Display";
	   }
	   
	   inStream = new StringBufferInputStream(sEstimatedHrs);
       return SUCCESS;
   }
   
   public String getRemainingProjectActivityHours() {
       ProjectVO projObj = new ProjectVO();
	   Integer projectId = Integer.valueOf(ServletActionContext.getRequest().getParameter("proIdEstHours"));
	   projObj = proService.getProject(projectId);
	   
	   String remainingHours = "";
	   Integer hours = 0;
	   
	   if(projObj.getEstimatedHours() != null){
		   projectActivityList = projActivityService.getAllProjActivityByProject(projObj);
		   
		   for (Iterator<ProjectActivityVO> it = projectActivityList.iterator(); it.hasNext();) {
			   projectActivityObj = it.next();

			   if(projectActivityObj.getEstimatedHours() != null){
				   hours += Integer.valueOf(projectActivityObj.getEstimatedHours());
				   
			   }
		   }
		   
		   Integer estProjHours = Integer.valueOf(projObj.getEstimatedHours()); 
		   Integer sub = estProjHours - hours;
		   remainingHours = String.valueOf(sub);
	   }else{
		   remainingHours = "No Estimated Hour Entered To Display";
	   }
	   
	   inStream = new StringBufferInputStream(remainingHours);
       return SUCCESS;
   }
   
    public String getProjectActivityEstimatedHours() {
	   ProjectActivityVO projActivityObj = new ProjectActivityVO();
	   Integer projectActivityId = Integer.valueOf(ServletActionContext.getRequest().getParameter("proActivityIdEstHours"));
	   projActivityObj = projActivityService.getProjectActivity(projectActivityId);
	   String sEstimatedHrs = "";
	   
	   if(projActivityObj.getEstimatedHours() !=null){
		   sEstimatedHrs =  projActivityObj.getEstimatedHours();
	   }else{
		   sEstimatedHrs = "No Estimated Hour Entered To Display";
	   }
	   
	   inStream = new StringBufferInputStream(sEstimatedHrs);
       return SUCCESS;
    }

   
    public void getAllEmployeeName() {

    	logger.debug("INSIDE LoadKeyValuePair getAllEmployeeName()");
    	logger.debug("INSIDE LoadKeyValuePair getAllEmployeeName() mSession.get('CLIENT_INFO') : " + mSession.get("CLIENT_INFO"));
        emploList = emplService.getAllEmployees((Integer) mSession.get("CLIENT_INFO"));
        logger.debug("INSIDE LoadKeyValuePair getAllEmployeeName() emploList : " + emploList);
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST), emploList);
        logger.debug("Employee Context key : " + ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST));
        Map session = ActionContext.getContext().getSession();
        session.put(ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST), emploList);
    }

    public void getAllCustomerName() {
    	logger.debug("INSIDE LoadKeyValuePair getAllCustomerName()");
        custList = custService.getAllCustomer();
        logger.debug("INSIDE LoadKeyValuePair getAllCustomerName() custList : " + custList);
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_CUSTOMER_LIST), custList);
        
    }

    public void getAllOrganizationName() {
        orgList = orgService.getAllOrganization();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ORGANIZATION_LIST), orgList);
    }

    public void getAllSalaryGradeName() {
        salarygrade = salgraService.getAllSalaryGrade();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_SALARYGRADE_LIST), salarygrade);
    }

    public void getAllLocationName() {
        location = locService.getAllLocation();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LOCATION_LIST), location);
    }

    public void getAllOrgTypeName() {
        orgtypeList = orgtypeService.getAllOrganizationType();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ORGANIZATIONTYPE_LIST), orgtypeList);
    }

    public void getAllBenefitsYear() {
        benefitsList = benefitsService.getAllBenefitYear();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_BENEFITS_YEAR_LIST), benefitsList);
    }

    public void getAllClients() {
        clientList = clientService.getAllClient();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_CLIENT_LIST), clientList);
    }

    public void getAllEmpStatus() {
        empStatusList = empStatusService.getAllEmployeeStatus();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EMPSTATUS_LIST), empStatusList);
    }

    public void getAllVendors() {
        vendorName = "Not Applicable";
        vendorList = vendorService.getAllVendorExceptInitialVendorName(vendorName);
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_VENDOR_LIST), vendorList);
    }

    public void getAllExpAppName() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expApprover.setHcmoEmployeeId(oEmp);
        expApproverList = expAppproverService.getAllEmployeesApprover(expApprover);
        session.put(Constants.SESSION_EXPENSESAPPROVER_LIST, expApproverList);
    }

    public void getAllExpEmpForApproverSearch() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        List employeeIdList = new LinkedList();
        expApproverList = expAppproverService.getAllExpEmpForApproverSearch(expApprover);

        if (!(expApproverList.isEmpty())) {
            ExpensesApproverVO Expemployee = new ExpensesApproverVO();
            for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
                Expemployee = it.next();
                employeeIdList.add(Expemployee.getApprovingEmployeeId().getEmployeeId());
            }
            if (employeeIdList.contains(oEmp.getEmployeeId())) {
                session.put(Constants.SESSION_EXPENSESEMPLOYEE_LIST, expApproverList);
            } else {
                expApprover.setHcmoEmployeeId(oEmp);
                expApproverList.add(expApprover);
                session.put(Constants.SESSION_EXPENSESEMPLOYEE_LIST, expApproverList);
            }
        }
    }

    public void getAllDepartmentName() {
        deptList = deptService.getAllDepartment();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_DEPARTMENT_LIST), deptList);
     
        Map session = ActionContext.getContext().getSession();
        session.put(ClientConstants.getClientContant(Constants.APPL_DEPARTMENT_LIST), deptList);
    }

    public void getAllTeamName() {
        teamList = teamService.getAllTeam();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_TEAM_LIST), teamList);
    }

    public void getAllTimesheetCategoryName() {
        timesheetCategoryList = timesheetCategoryService.getAllTimesheetCategory();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.TIMESHEET_CATEGORY), timesheetCategoryList);
    }

    public void getAllTimesheetCategoryEmpName() {
        timesheetCategoryEmplist = timesheetCategoryEmpService.getAllTimeSheetCategoryEmp();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.TIMESHEET_CATEGORYEMP), timesheetCategoryEmplist);
    }

    public void getAllLeaveQuotaYear() {
        leaveQuotaList = leaveQuotaService.getAllEmployeeLeaveQuotaYear();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVEQUOTA_YEAR_LIST), leaveQuotaList);
    }

    public void getAllSubEmployeeForTimeSheet() {
        emploList = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_SUBEMPLOYEE_LIST), emploList);
    }

    public void getAllSubEmployeeForLeaveQuota() {
        EmployeesVO newAdminEmp = null;
        leaveQuotaSubEmpList = leaveQuotaService.getCurrentSubEmployeeForLeaveQuota();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        role = roleService.getRoleName(getText("message.label.common.adminName"));

        int sessionEmpId = oEmp.getEmployeeId();
        leaveQuotaSubEmpList.add(oEmp);

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();

            int lengthForAdminEmpList = adminRoleId.size();
            for (int i = 1; i < lengthForAdminEmpList; i++) {
                int adminEmpID = newAdminEmp.getEmployeeId();

                if (sessionEmpId == adminEmpID) {
                    emploList = emplService.getAllEmployees((Integer) mSession.get("CLIENT_INFO"));
                    ServletContext context = ServletActionContext.getServletContext();
                    context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVEQUOTASUBEMPLOYEE_LIST), emploList);
                } else {
                    Set<Integer> setEmp = new LinkedHashSet<Integer>();
                    Set<EmployeesVO> setVO = new LinkedHashSet<EmployeesVO>();
                    for (EmployeesVO b : leaveQuotaSubEmpList) {
                        if (setEmp.add(b.getEmployeeId())) {
                            setVO.add(b);
                        }
                    }
                    leaveQuotaSubEmpList.clear();
                    leaveQuotaSubEmpList.addAll(setVO);
                    ServletContext context = ServletActionContext.getServletContext();
                    context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVEQUOTASUBEMPLOYEE_LIST), leaveQuotaSubEmpList);
                }
            }

        }
    }

    public void getAllSubEmployeeForLeave() {
        emploList = emplService.getCurrentSubEmployee();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVESUBEMPLOYEE_LIST), emploList);
    }

    public void getAllProjectActivity() {
        projectActivityList = projectActivityService.getAllProjectActivity();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_PROJECTACTIVITY_LIST), projectActivityList);
    }

    public void getAllDeductions() {
        dedList = deductionService.getAllDeductions();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_DEDUCTION_LIST), dedList);
    }

    public void getProjectActivity(int projectId, int employeeId) {
        projActivityList = projActivityService.getProjectActivityList(projectId, employeeId);
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ACTIVITY_LIST), projActivityList);
    }
    
    public void getProjectActivity() {
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_ACTIVITY_LIST), projActivityList);
    }
    
    public void setEmpProjectActivity(List<ProjectActivityVO> projectActivityList){
    	ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EMP_PROJECT_ACTIVITY_LIST), projectActivityList);
    }
    
    public void setEmptyEmpProjectActivity(List<ProjectActivityVO> projectActivityList){
    	ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_EMP_PROJECT_ACTIVITY_LIST), projectActivityList);
    }
    
    public void getAllCategoryName() {
        categoryList = categoryService.getAllCategory();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_CATEGORY_LIST), categoryList);
    }

    public void getAllSubCategoryName() {
        subCategoryList = subCatService.getAllSubCategory();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_SUB_CATEGORY_LIST), subCategoryList);
    }
    
    public void getAllSubCategoryName(int hcmoCategoryId) {
        subCategoryList = subCatService.subCategoryForCategory(hcmoCategoryId);
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_SUB_CATEGORY_LIST), subCategoryList);
    }
    
    public void getAllQuestionBankName() {
        quesBankList = quesBankService.getAllQuestionBankName();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_QUESTIONBANK_LIST), quesBankList);
    }
    
    public void getAllApproversSubEmployeeList(){
        emploList = quesBankGeneralInfoService.getAllApproversSubEmployeeList();
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_QUESTIONBANKGENERAL_INFO_LIST), emploList);
    }
    
    public void getAllTargetsName(){
    	targetsNameList = targetService.getAllTargetsName();
    	ServletContext context = ServletActionContext.getServletContext();
    	context.setAttribute(ClientConstants.getClientContant(Constants.APPL_TARGET_NAME_LIST), targetsNameList);
    }
    
    public void getAllGoalName(){
    	goalNameList = targetService.getAllGoalName();
    	ServletContext context = ServletActionContext.getServletContext();
    	context.setAttribute(ClientConstants.getClientContant(Constants.APPL_GOALNAME_LIST), goalNameList);
    }
    
    public void setGoalList(List<String> goalNameList){
    	ServletContext context = ServletActionContext.getServletContext();
    	context.setAttribute(ClientConstants.getClientContant(Constants.APPL_GOALNAME_LIST), goalNameList);
    }
    
    public void getTargetType(String targetName){
    	targetTypeList = targetService.getTargetType(targetName);
    	ServletContext context = ServletActionContext.getServletContext();
    	context.setAttribute(ClientConstants.getClientContant(Constants.APPL_TARGET_TYPE_LIST), targetTypeList);
    }
    
    public void getEmptyTargetType(){
    	targetTypeList = new ArrayList<String>();
    	ServletContext context = ServletActionContext.getServletContext();
    	context.setAttribute(ClientConstants.getClientContant(Constants.APPL_TARGET_TYPE_LIST), targetTypeList);
    }
    
    public void getAssignKPIEmpList(List<EmployeesVO> kpiemplList) {
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute(ClientConstants.getClientContant(Constants.APPL_KPI_ASSIGN_EMPLOYEE_LIST), kpiemplList);
    }
    
    public List<RegionVO> getRegion() {
        return region;
    }

    public ProjectService getProService() {
        return proService;
    }

    public void setProService(ProjectService proService) {
        this.proService = proService;
    }

    public List<ProjectVO> getProList() {
        return proList;
    }

    public void setProList(List<ProjectVO> proList) {
        this.proList = proList;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public List<CountryVO> getCountry() {
        return country;
    }

    public EmployeesService getEmplService() {
        return emplService;
    }

    public void setEmplService(EmployeesService emplService) {
        this.emplService = emplService;
    }

    public List<EmployeesVO> getEmploList() {
        return emploList;
    }

    public void setEmploList(List<EmployeesVO> emploList) {
        this.emploList = emploList;
    }

    public LeaveTypeService getLeaveTypeService() {
        return leaveTypeService;
    }

    public void setLeaveTypeService(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    public List<LeaveTypeVO> getLeaveTypeIdObj() {
        return leaveTypeIdObj;
    }

    public void setLeaveTypeIdObj(List<LeaveTypeVO> leaveTypeIdObj) {
        this.leaveTypeIdObj = leaveTypeIdObj;
    }

    public LeaveApproverService getLeaveAppproverService() {
        return leaveAppproverService;
    }

    public void setLeaveAppproverService(LeaveApproverService leaveAppproverService) {
        this.leaveAppproverService = leaveAppproverService;
    }

    public List<LeaveApproverVO> getLeaveApproverList() {
        return leaveApproverList;
    }

    public void setLeaveApproverList(List<LeaveApproverVO> leaveApproverList) {
        this.leaveApproverList = leaveApproverList;
    }

    public LeaveHistoryService getLeaveHistoryService() {
        return leaveHistoryService;
    }

    public void setLeaveHistoryService(LeaveHistoryService leaveHistoryService) {
        this.leaveHistoryService = leaveHistoryService;
    }

    public List<LeaveHistoryVO> getLHistList() {
        return lHistList;
    }

    public void setLHistList(List<LeaveHistoryVO> histList) {
        lHistList = histList;
    }

    public List<ExpensesApproverVO> getExpApproverList() {
        return expApproverList;
    }

    public void setExpApproverList(List<ExpensesApproverVO> expApproverList) {
        this.expApproverList = expApproverList;
    }

    public ExpensesApproverVO getExpApprover() {
        return expApprover;
    }

    public void setExpApprover(ExpensesApproverVO expApprover) {
        this.expApprover = expApprover;
    }

    public BenefitsService getBenefitsService() {
        return benefitsService;
    }

    public void setBenefitsService(BenefitsService benefitsService) {
        this.benefitsService = benefitsService;
    }

    public List<BenefitsVO> getBenefitsList() {
        return benefitsList;
    }

    public void setBenefitsList(List<BenefitsVO> benefitsList) {
        this.benefitsList = benefitsList;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<ClientVO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientVO> clientList) {
        this.clientList = clientList;
    }

    public EmployeeStatusService getEmpStatusService() {
        return empStatusService;
    }

    public void setEmpStatusService(EmployeeStatusService empStatusService) {
        this.empStatusService = empStatusService;
    }

    public List<EmployeeStatusVO> getEmpStatusList() {
        return empStatusList;
    }

    public void setEmpStatusList(List<EmployeeStatusVO> empStatusList) {
        this.empStatusList = empStatusList;
    }

    public List<DepartmentVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DepartmentVO> deptList) {
        this.deptList = deptList;
    }

    public List<TeamVO> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<TeamVO> teamList) {
        this.teamList = teamList;
    }

    public TimesheetCategoryService getTimesheetCategoryService() {
        return timesheetCategoryService;
    }

    public void setTimesheetCategoryService(TimesheetCategoryService timesheetCategoryService) {
        this.timesheetCategoryService = timesheetCategoryService;
    }

    public List<TimesheetCategoryVO> getTimesheetCategoryList() {
        return timesheetCategoryList;
    }

    public void setTimesheetCategoryList(List<TimesheetCategoryVO> timesheetCategoryList) {
        this.timesheetCategoryList = timesheetCategoryList;
    }

    public void setLeaveQuotaSubEmpList(List<EmployeesVO> leaveQuotaSubEmpList) {
        this.leaveQuotaSubEmpList = leaveQuotaSubEmpList;
    }

    public List<EmployeesVO> getLeaveQuotaSubEmpList() {
        return leaveQuotaSubEmpList;
    }

    public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public List<EmployeesVO> getAdminRoleId() {
        return adminRoleId;
    }

    public List<EmployeesVO> getEmpListForTeam() {
        return empListForTeam;
    }

    public void setEmpListForTeam(List<EmployeesVO> empListForTeam) {
        this.empListForTeam = empListForTeam;
    }

    public void setEmpListForDepartment(List<EmployeesVO> empListForDepartment) {
        this.empListForDepartment = empListForDepartment;
    }

    public List<EmployeesVO> getEmpListForDepartment() {
        return empListForDepartment;
    }

    public void setEmpListForExpApprover(List<EmployeesVO> empListForExpApprover) {
        this.empListForExpApprover = empListForExpApprover;
    }

    public List<EmployeesVO> getEmpListForExpApprover() {
        return empListForExpApprover;
    }

    public void setEmpListForLeaveApprover(List<EmployeesVO> empListForLeaveApprover) {
        this.empListForLeaveApprover = empListForLeaveApprover;
    }

    public List<EmployeesVO> getEmpListForLeaveApprover() {
        return empListForLeaveApprover;
    }

    public void setEmpListForTimesheetApprover(List<EmployeesVO> empListForTimesheetApprover) {
        this.empListForTimesheetApprover = empListForTimesheetApprover;
    }

    public List<EmployeesVO> getEmpListForTimesheetApprover() {
        return empListForTimesheetApprover;
    }

    public List<EmployeesVO> getEmpListForRole() {
        return empListForRole;
    }

    public void setEmpListForRole(List<EmployeesVO> empListForRole) {
        this.empListForRole = empListForRole;
    }

    public List<ProjectActivityVO> getProjectActivityList() {
        return projectActivityList;
    }

    public void setProjectActivityList(List<ProjectActivityVO> projectActivityList) {
        this.projectActivityList = projectActivityList;
    }

    public void setVendorList(List<VendorVO> vendorList) {
        this.vendorList = vendorList;
    }

    public List<VendorVO> getVendorList() {
        return vendorList;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public List<TimesheetCategoryEmpVO> getTimesheetCategoryEmplist() {
        return timesheetCategoryEmplist;
    }

    public void setTimesheetCategoryEmplist(List<TimesheetCategoryEmpVO> timesheetCategoryEmplist) {
        this.timesheetCategoryEmplist = timesheetCategoryEmplist;
    }

    public DeductionService getDeductionService() {
        return deductionService;
    }

    public void setDeductionService(DeductionService deductionService) {
        this.deductionService = deductionService;
    }

    public List<DeductionsVO> getDedList() {
        return dedList;
    }

    public void setDedList(List<DeductionsVO> dedList) {
        this.dedList = dedList;
    }

    public List<CategoryVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryVO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<SubCategoryVO> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategoryVO> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public List<QuestionBankVO> getQuesBankList() {
        return quesBankList;
    }

    public void setQuesBankList(List<QuestionBankVO> quesBankList) {
        this.quesBankList = quesBankList;
    }

    public List<QuestionBankGeneralInfoVO> getQuesBankGeneralInfoList() {
        return quesBankGeneralInfoList;
    }

    public void setQuesBankGeneralInfoList(List<QuestionBankGeneralInfoVO> quesBankGeneralInfoList) {
        this.quesBankGeneralInfoList = quesBankGeneralInfoList;
    }

    public List getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List employeeList) {
        this.employeeList = employeeList;
    }

	public ProjectActivityVO getProjectActivityObj() {
		return projectActivityObj;
	}

	public void setProjectActivityObj(ProjectActivityVO projectActivityObj) {
		this.projectActivityObj = projectActivityObj;
	}

	public List<String> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<String> targetList) {
		this.targetList = targetList;
	}

	public List<TargetTypeVO> getTargetTypeVOList() {
		return targetTypeVOList;
	}

	public void setTargetTypeVOList(List<TargetTypeVO> targetTypeVOList) {
		this.targetTypeVOList = targetTypeVOList;
	}

	public List<EmployeesVO> getKpiAssignEmplList() {
		return kpiAssignEmplList;
	}

	public void setKpiAssignEmplList(List<EmployeesVO> kpiAssignEmplList) {
		this.kpiAssignEmplList = kpiAssignEmplList;
	}
}
