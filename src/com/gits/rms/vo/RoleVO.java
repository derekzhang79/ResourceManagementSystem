
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class RoleVO implements Serializable {
    private static final long serialVersionUID = 3837859073064823955L;
    private MenuVO clientmenu;
    private MenuVO countrymenu;
    private Date created;
    private EmployeesVO createdBy;
    private MenuVO currencymenu;
    private MenuVO customermenu;
    private MenuVO deductionmenu;
    private MenuVO departmentsmenu;
    private MenuVO employeesmenu;
    private MenuVO employeeSpace;
    private MenuVO targetmodulemenu;
    private MenuVO employeestatusmenu;
    private MenuVO empschedulermenu;
    private MenuVO ess;
    private MenuVO ethnicracemenu;
    private MenuVO expensemodule;
    private MenuVO expensesaccountantmenu;
    private MenuVO expensesapprovermenu;
    private MenuVO expensetypemenu;
    private Integer hcmoRoleId;
    private MenuVO holidaymenu;
    private MenuVO homemenu;
    private int isActive;
    private MenuVO jobtitlemenu;
    private MenuVO leaveapprovermenu;
    private MenuVO leavemodule;
    private MenuVO leavetypemenu;
    private MenuVO locationmenu;
    private MenuVO logoutmenu;
    private MenuVO messaging;
    private MenuVO nationalitymenu;
    private MenuVO orgChart;
    private MenuVO projectactivitymenu;
    private MenuVO projectmenu;
    private MenuVO regionmenu;
    private MenuVO reports;
    private MenuVO rolemenu;
    private String roleName;
    private MenuVO salarygrademenu;
    private MenuVO teamsmenu;
    private MenuVO timesheetapprovermenu;
    private MenuVO timesheetmodule;
    private MenuVO targetsmenu;
    private MenuVO targetstypemenu;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private MenuVO usermenu;
    private String xmlPath;
    
    private MenuVO childrenmenu;
    private MenuVO childrenmenu_childName;
    private MenuVO childrenmenu_dob;
    
    private MenuVO educationmenu;
    private MenuVO educationmenu_edumajor;
    private MenuVO educationmenu_eduyear;
    private MenuVO educationmenu_edugrade;
    private MenuVO educationmenu_edustartdate;
    private MenuVO educationmenu_eduenddate;
    private MenuVO educationmenu_eduuniversity;
    private MenuVO educationmenu_edudegree;
    
    private MenuVO licensemenu;
    private MenuVO licensemenu_licensenumber;
    private MenuVO licensemenu_licensedate;
    private MenuVO licensemenu_licenserenewaldate;
    private MenuVO licensemenu_licenseDesc;
    
    private MenuVO workexperiencemenu;
    private MenuVO workexperiencemenu_employeername;
    private MenuVO workexperiencemenu_empjobtitle;
    private MenuVO workexperiencemenu_fromdate;
    private MenuVO workexperiencemenu_todate;
    private MenuVO workexperiencemenu_comments;
    
    private MenuVO emplocationhistorymenu;
    private MenuVO emplocationhistorymenu_locationname;
    private MenuVO emplocationhistorymenu_companyname;
    private MenuVO emplocationhistorymenu_startdate;
    private MenuVO emplocationhistorymenu_enddate;
    
    private MenuVO directdebitmenu;
    private MenuVO directdebitmenu_routingnum;
    private MenuVO directdebitmenu_account;
    private MenuVO directdebitmenu_amount;
    private MenuVO directdebitmenu_accounttype;
    private MenuVO directdebitmenu_transactiontype;
    private MenuVO directdebitmenu_preAccountvalue;
    
    private MenuVO empreporttomenu;
    private MenuVO empreporttomenu_supempname;
    private MenuVO empreporttomenu_reportingmodevalue;
    
    private MenuVO employeepassportmenu;
    private MenuVO employeepassportmenu_passportno;
    private MenuVO employeepassportmenu_passportissuedate;
    private MenuVO employeepassportmenu_passportexpiredate;
    private MenuVO employeepassportmenu_passporttypeflg;
    private MenuVO employeepassportmenu_l9Status;
    private MenuVO employeepassportmenu_l9reviewdate;
    private MenuVO employeepassportmenu_countryname;
    private MenuVO employeepassportmenu_comments;
    
    private MenuVO benefitsmenu;
    private MenuVO benefitsmenu_year;
    private MenuVO benefitsmenu_benefitsname;
    private MenuVO benefitsmenu_attachfile;
    
    private MenuVO empleavequotamenu;
    private MenuVO empleavequotamenu_leavetype;
    private MenuVO empleavequotamenu_year;
    private MenuVO empleavequotamenu_preleavecarryforward;
    private MenuVO empleavequotamenu_empleavepending;
    private MenuVO empleavequotamenu_leaveallotteddays;
    private MenuVO empleavequotamenu_empleaverequest;
    
    private MenuVO paystubmenu;
    private MenuVO paystubmenu_grosssalary;
    private MenuVO paystubmenu_decdate;
    private MenuVO paystubmenu_netsalary;
    private MenuVO paystubmenu_deductionname;
    private MenuVO paystubmenu_deductiontype;
    private MenuVO paystubmenu_deductionmode;
    private MenuVO paystubmenu_deductionamount;
    private MenuVO paystubmenu_deductioneffdate;
    
    private MenuVO performancemenucategory;
    private MenuVO performancemenusubcategory;
    private MenuVO performancemenukpiquestion;
    private MenuVO performancemenukpiquestiongroup;
    private MenuVO performancemenukpiassignkpi;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
     public RoleVO() {
    	
    }
     
    public RoleVO(Integer hcmoRoleId) {
    	this.hcmoRoleId = hcmoRoleId;
    }

	public RoleVO(int clientid,MenuVO clientmenu, MenuVO countrymenu, Date created,
			EmployeesVO createdBy, MenuVO currencymenu, MenuVO customermenu,
			MenuVO deductionmenu, MenuVO departmentsmenu, MenuVO employeesmenu,
			MenuVO employeeSpace, MenuVO targetmodulemenu, MenuVO employeestatusmenu,
			MenuVO empschedulermenu, MenuVO ess, MenuVO ethnicracemenu,
			MenuVO expensemodule, MenuVO expensesaccountantmenu,
			MenuVO expensesapprovermenu, MenuVO expensetypemenu,
			Integer hcmoRoleId, MenuVO holidaymenu, MenuVO homemenu,
			int isActive, MenuVO jobtitlemenu, MenuVO leaveapprovermenu,
			MenuVO leavemodule, MenuVO leavetypemenu, MenuVO locationmenu,
			MenuVO logoutmenu, MenuVO messaging, MenuVO nationalitymenu,
			MenuVO orgChart, MenuVO projectactivitymenu, MenuVO projectmenu,
			MenuVO regionmenu, MenuVO reports, MenuVO rolemenu,
			String roleName, MenuVO salarygrademenu, MenuVO teamsmenu,
			MenuVO timesheetapprovermenu, MenuVO timesheetmodule,
			MenuVO targetsmenu,MenuVO targetstypemenu, Timestamp updated, EmployeesVO updatedBy,
			MenuVO usermenu, String xmlPath, MenuVO childrenmenu,
			MenuVO childrenmenu_childName, MenuVO childrenmenu_dob,
			MenuVO educationmenu, MenuVO educationmenu_edumajor,
			MenuVO educationmenu_eduyear, MenuVO educationmenu_edugrade,
			MenuVO educationmenu_edustartdate, MenuVO educationmenu_eduenddate,
			MenuVO educationmenu_eduuniversity, MenuVO educationmenu_edudegree,
			MenuVO licensemenu, MenuVO licensemenu_licensenumber,
			MenuVO licensemenu_licensedate,
			MenuVO licensemenu_licenserenewaldate,
			MenuVO licensemenu_licenseDesc, MenuVO workexperiencemenu,
			MenuVO workexperiencemenu_employeername,
			MenuVO workexperiencemenu_empjobtitle,
			MenuVO workexperiencemenu_fromdate,
			MenuVO workexperiencemenu_todate,
			MenuVO workexperiencemenu_comments, MenuVO emplocationhistorymenu,
			MenuVO emplocationhistorymenu_locationname,
			MenuVO emplocationhistorymenu_companyname,
			MenuVO emplocationhistorymenu_startdate,
			MenuVO emplocationhistorymenu_enddate, MenuVO directdebitmenu,
			MenuVO directdebitmenu_routingnum, MenuVO directdebitmenu_account,
			MenuVO directdebitmenu_amount, MenuVO directdebitmenu_accounttype,
			MenuVO directdebitmenu_transactiontype,
			MenuVO directdebitmenu_preAccountvalue, MenuVO empreporttomenu,
			MenuVO empreporttomenu_supempname,
			MenuVO empreporttomenu_reportingmodevalue,
			MenuVO employeepassportmenu,
			MenuVO employeepassportmenu_passportno,
			MenuVO employeepassportmenu_passportissuedate,
			MenuVO employeepassportmenu_passportexpiredate,
			MenuVO employeepassportmenu_passporttypeflg,
			MenuVO employeepassportmenu_l9Status,
			MenuVO employeepassportmenu_l9reviewdate,
			MenuVO employeepassportmenu_countryname,
			MenuVO employeepassportmenu_comments, MenuVO benefitsmenu,
			MenuVO benefitsmenu_year, MenuVO benefitsmenu_benefitsname,
			MenuVO benefitsmenu_attachfile, MenuVO empleavequotamenu,
			MenuVO empleavequotamenu_leavetype, MenuVO empleavequotamenu_year,
			MenuVO empleavequotamenu_preleavecarryforward,
			MenuVO empleavequotamenu_empleavepending,
			MenuVO empleavequotamenu_leaveallotteddays,
			MenuVO empleavequotamenu_empleaverequest, MenuVO paystubmenu,
			MenuVO paystubmenu_grosssalary, MenuVO paystubmenu_decdate,
			MenuVO paystubmenu_netsalary, MenuVO paystubmenu_deductionname,
			MenuVO paystubmenu_deductiontype, MenuVO paystubmenu_deductionmode,
			MenuVO paystubmenu_deductionamount,
			MenuVO paystubmenu_deductioneffdate,
			MenuVO performancemenucategory, MenuVO performancemenusubcategory,
			MenuVO performancemenukpiquestion,
			MenuVO performancemenukpiquestiongroup,
			MenuVO performancemenukpiassignkpi) {
		super();
		this.clientmenu = clientmenu;
		this.clientId=clientid;
		this.countrymenu = countrymenu;
		this.created = created;
		this.createdBy = createdBy;
		this.currencymenu = currencymenu;
		this.customermenu = customermenu;
		this.deductionmenu = deductionmenu;
		this.departmentsmenu = departmentsmenu;
		this.employeesmenu = employeesmenu;
		this.employeeSpace = employeeSpace;
		this.targetmodulemenu = targetmodulemenu;
		this.employeestatusmenu = employeestatusmenu;
		this.empschedulermenu = empschedulermenu;
		this.ess = ess;
		this.ethnicracemenu = ethnicracemenu;
		this.expensemodule = expensemodule;
		this.expensesaccountantmenu = expensesaccountantmenu;
		this.expensesapprovermenu = expensesapprovermenu;
		this.expensetypemenu = expensetypemenu;
		this.hcmoRoleId = hcmoRoleId;
		this.holidaymenu = holidaymenu;
		this.homemenu = homemenu;
		this.isActive = isActive;
		this.jobtitlemenu = jobtitlemenu;
		this.leaveapprovermenu = leaveapprovermenu;
		this.leavemodule = leavemodule;
		this.leavetypemenu = leavetypemenu;
		this.locationmenu = locationmenu;
		this.logoutmenu = logoutmenu;
		this.messaging = messaging;
		this.nationalitymenu = nationalitymenu;
		this.orgChart = orgChart;
		this.projectactivitymenu = projectactivitymenu;
		this.projectmenu = projectmenu;
		this.regionmenu = regionmenu;
		this.reports = reports;
		this.rolemenu = rolemenu;
		this.roleName = roleName;
		this.salarygrademenu = salarygrademenu;
		this.teamsmenu = teamsmenu;
		this.timesheetapprovermenu = timesheetapprovermenu;
		this.timesheetmodule = timesheetmodule;
		this.targetsmenu = targetsmenu;
		this.targetstypemenu = targetstypemenu;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.usermenu = usermenu;
		this.xmlPath = xmlPath;
		this.childrenmenu = childrenmenu;
		this.childrenmenu_childName = childrenmenu_childName;
		this.childrenmenu_dob = childrenmenu_dob;
		this.educationmenu = educationmenu;
		this.educationmenu_edumajor = educationmenu_edumajor;
		this.educationmenu_eduyear = educationmenu_eduyear;
		this.educationmenu_edugrade = educationmenu_edugrade;
		this.educationmenu_edustartdate = educationmenu_edustartdate;
		this.educationmenu_eduenddate = educationmenu_eduenddate;
		this.educationmenu_eduuniversity = educationmenu_eduuniversity;
		this.educationmenu_edudegree = educationmenu_edudegree;
		this.licensemenu = licensemenu;
		this.licensemenu_licensenumber = licensemenu_licensenumber;
		this.licensemenu_licensedate = licensemenu_licensedate;
		this.licensemenu_licenserenewaldate = licensemenu_licenserenewaldate;
		this.licensemenu_licenseDesc = licensemenu_licenseDesc;
		this.workexperiencemenu = workexperiencemenu;
		this.workexperiencemenu_employeername = workexperiencemenu_employeername;
		this.workexperiencemenu_empjobtitle = workexperiencemenu_empjobtitle;
		this.workexperiencemenu_fromdate = workexperiencemenu_fromdate;
		this.workexperiencemenu_todate = workexperiencemenu_todate;
		this.workexperiencemenu_comments = workexperiencemenu_comments;
		this.emplocationhistorymenu = emplocationhistorymenu;
		this.emplocationhistorymenu_locationname = emplocationhistorymenu_locationname;
		this.emplocationhistorymenu_companyname = emplocationhistorymenu_companyname;
		this.emplocationhistorymenu_startdate = emplocationhistorymenu_startdate;
		this.emplocationhistorymenu_enddate = emplocationhistorymenu_enddate;
		this.directdebitmenu = directdebitmenu;
		this.directdebitmenu_routingnum = directdebitmenu_routingnum;
		this.directdebitmenu_account = directdebitmenu_account;
		this.directdebitmenu_amount = directdebitmenu_amount;
		this.directdebitmenu_accounttype = directdebitmenu_accounttype;
		this.directdebitmenu_transactiontype = directdebitmenu_transactiontype;
		this.directdebitmenu_preAccountvalue = directdebitmenu_preAccountvalue;
		this.empreporttomenu = empreporttomenu;
		this.empreporttomenu_supempname = empreporttomenu_supempname;
		this.empreporttomenu_reportingmodevalue = empreporttomenu_reportingmodevalue;
		this.employeepassportmenu = employeepassportmenu;
		this.employeepassportmenu_passportno = employeepassportmenu_passportno;
		this.employeepassportmenu_passportissuedate = employeepassportmenu_passportissuedate;
		this.employeepassportmenu_passportexpiredate = employeepassportmenu_passportexpiredate;
		this.employeepassportmenu_passporttypeflg = employeepassportmenu_passporttypeflg;
		this.employeepassportmenu_l9Status = employeepassportmenu_l9Status;
		this.employeepassportmenu_l9reviewdate = employeepassportmenu_l9reviewdate;
		this.employeepassportmenu_countryname = employeepassportmenu_countryname;
		this.employeepassportmenu_comments = employeepassportmenu_comments;
		this.benefitsmenu = benefitsmenu;
		this.benefitsmenu_year = benefitsmenu_year;
		this.benefitsmenu_benefitsname = benefitsmenu_benefitsname;
		this.benefitsmenu_attachfile = benefitsmenu_attachfile;
		this.empleavequotamenu = empleavequotamenu;
		this.empleavequotamenu_leavetype = empleavequotamenu_leavetype;
		this.empleavequotamenu_year = empleavequotamenu_year;
		this.empleavequotamenu_preleavecarryforward = empleavequotamenu_preleavecarryforward;
		this.empleavequotamenu_empleavepending = empleavequotamenu_empleavepending;
		this.empleavequotamenu_leaveallotteddays = empleavequotamenu_leaveallotteddays;
		this.empleavequotamenu_empleaverequest = empleavequotamenu_empleaverequest;
		this.paystubmenu = paystubmenu;
		this.paystubmenu_grosssalary = paystubmenu_grosssalary;
		this.paystubmenu_decdate = paystubmenu_decdate;
		this.paystubmenu_netsalary = paystubmenu_netsalary;
		this.paystubmenu_deductionname = paystubmenu_deductionname;
		this.paystubmenu_deductiontype = paystubmenu_deductiontype;
		this.paystubmenu_deductionmode = paystubmenu_deductionmode;
		this.paystubmenu_deductionamount = paystubmenu_deductionamount;
		this.paystubmenu_deductioneffdate = paystubmenu_deductioneffdate;
		this.performancemenucategory = performancemenucategory;
		this.performancemenusubcategory = performancemenusubcategory;
		this.performancemenukpiquestion = performancemenukpiquestion;
		this.performancemenukpiquestiongroup = performancemenukpiquestiongroup;
		this.performancemenukpiassignkpi = performancemenukpiassignkpi;
	}

	public MenuVO getClientmenu() {
		return clientmenu;
	}

	public void setClientmenu(MenuVO clientmenu) {
		this.clientmenu = clientmenu;
	}

	public MenuVO getCountrymenu() {
		return countrymenu;
	}

	public void setCountrymenu(MenuVO countrymenu) {
		this.countrymenu = countrymenu;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public EmployeesVO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EmployeesVO createdBy) {
		this.createdBy = createdBy;
	}

	public MenuVO getCurrencymenu() {
		return currencymenu;
	}

	public void setCurrencymenu(MenuVO currencymenu) {
		this.currencymenu = currencymenu;
	}

	public MenuVO getCustomermenu() {
		return customermenu;
	}

	public void setCustomermenu(MenuVO customermenu) {
		this.customermenu = customermenu;
	}

	public MenuVO getDeductionmenu() {
		return deductionmenu;
	}

	public void setDeductionmenu(MenuVO deductionmenu) {
		this.deductionmenu = deductionmenu;
	}

	public MenuVO getDepartmentsmenu() {
		return departmentsmenu;
	}

	public void setDepartmentsmenu(MenuVO departmentsmenu) {
		this.departmentsmenu = departmentsmenu;
	}

	public MenuVO getEmployeesmenu() {
		return employeesmenu;
	}

	public void setEmployeesmenu(MenuVO employeesmenu) {
		this.employeesmenu = employeesmenu;
	}

	public MenuVO getEmployeeSpace() {
		return employeeSpace;
	}

	public void setEmployeeSpace(MenuVO employeeSpace) {
		this.employeeSpace = employeeSpace;
	}
	
	public MenuVO getTargetmodulemenu() {
		return targetmodulemenu;
	}

	public void setTargetmodulemenu(MenuVO targetmodulemenu) {
		this.targetmodulemenu = targetmodulemenu;
	}

	public MenuVO getEmployeestatusmenu() {
		return employeestatusmenu;
	}

	public void setEmployeestatusmenu(MenuVO employeestatusmenu) {
		this.employeestatusmenu = employeestatusmenu;
	}

	public MenuVO getEmpschedulermenu() {
		return empschedulermenu;
	}

	public void setEmpschedulermenu(MenuVO empschedulermenu) {
		this.empschedulermenu = empschedulermenu;
	}

	public MenuVO getEss() {
		return ess;
	}

	public void setEss(MenuVO ess) {
		this.ess = ess;
	}

	public MenuVO getEthnicracemenu() {
		return ethnicracemenu;
	}

	public void setEthnicracemenu(MenuVO ethnicracemenu) {
		this.ethnicracemenu = ethnicracemenu;
	}

	public MenuVO getExpensemodule() {
		return expensemodule;
	}

	public void setExpensemodule(MenuVO expensemodule) {
		this.expensemodule = expensemodule;
	}

	public MenuVO getExpensesaccountantmenu() {
		return expensesaccountantmenu;
	}

	public void setExpensesaccountantmenu(MenuVO expensesaccountantmenu) {
		this.expensesaccountantmenu = expensesaccountantmenu;
	}

	public MenuVO getExpensesapprovermenu() {
		return expensesapprovermenu;
	}

	public void setExpensesapprovermenu(MenuVO expensesapprovermenu) {
		this.expensesapprovermenu = expensesapprovermenu;
	}

	public MenuVO getExpensetypemenu() {
		return expensetypemenu;
	}

	public void setExpensetypemenu(MenuVO expensetypemenu) {
		this.expensetypemenu = expensetypemenu;
	}

	public Integer getHcmoRoleId() {
		return hcmoRoleId;
	}

	public void setHcmoRoleId(Integer hcmoRoleId) {
		this.hcmoRoleId = hcmoRoleId;
	}

	public MenuVO getHolidaymenu() {
		return holidaymenu;
	}

	public void setHolidaymenu(MenuVO holidaymenu) {
		this.holidaymenu = holidaymenu;
	}

	public MenuVO getHomemenu() {
		return homemenu;
	}

	public void setHomemenu(MenuVO homemenu) {
		this.homemenu = homemenu;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public MenuVO getJobtitlemenu() {
		return jobtitlemenu;
	}

	public void setJobtitlemenu(MenuVO jobtitlemenu) {
		this.jobtitlemenu = jobtitlemenu;
	}

	public MenuVO getLeaveapprovermenu() {
		return leaveapprovermenu;
	}

	public void setLeaveapprovermenu(MenuVO leaveapprovermenu) {
		this.leaveapprovermenu = leaveapprovermenu;
	}

	public MenuVO getLeavemodule() {
		return leavemodule;
	}

	public void setLeavemodule(MenuVO leavemodule) {
		this.leavemodule = leavemodule;
	}

	public MenuVO getLeavetypemenu() {
		return leavetypemenu;
	}

	public void setLeavetypemenu(MenuVO leavetypemenu) {
		this.leavetypemenu = leavetypemenu;
	}

	public MenuVO getLocationmenu() {
		return locationmenu;
	}

	public void setLocationmenu(MenuVO locationmenu) {
		this.locationmenu = locationmenu;
	}

	public MenuVO getLogoutmenu() {
		return logoutmenu;
	}

	public void setLogoutmenu(MenuVO logoutmenu) {
		this.logoutmenu = logoutmenu;
	}

	public MenuVO getMessaging() {
		return messaging;
	}

	public void setMessaging(MenuVO messaging) {
		this.messaging = messaging;
	}

	public MenuVO getNationalitymenu() {
		return nationalitymenu;
	}

	public void setNationalitymenu(MenuVO nationalitymenu) {
		this.nationalitymenu = nationalitymenu;
	}

	public MenuVO getOrgChart() {
		return orgChart;
	}

	public void setOrgChart(MenuVO orgChart) {
		this.orgChart = orgChart;
	}

	public MenuVO getProjectactivitymenu() {
		return projectactivitymenu;
	}

	public void setProjectactivitymenu(MenuVO projectactivitymenu) {
		this.projectactivitymenu = projectactivitymenu;
	}

	public MenuVO getProjectmenu() {
		return projectmenu;
	}

	public void setProjectmenu(MenuVO projectmenu) {
		this.projectmenu = projectmenu;
	}

	public MenuVO getRegionmenu() {
		return regionmenu;
	}

	public void setRegionmenu(MenuVO regionmenu) {
		this.regionmenu = regionmenu;
	}

	public MenuVO getReports() {
		return reports;
	}

	public void setReports(MenuVO reports) {
		this.reports = reports;
	}

	public MenuVO getRolemenu() {
		return rolemenu;
	}

	public void setRolemenu(MenuVO rolemenu) {
		this.rolemenu = rolemenu;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public MenuVO getSalarygrademenu() {
		return salarygrademenu;
	}

	public void setSalarygrademenu(MenuVO salarygrademenu) {
		this.salarygrademenu = salarygrademenu;
	}

	public MenuVO getTeamsmenu() {
		return teamsmenu;
	}

	public void setTeamsmenu(MenuVO teamsmenu) {
		this.teamsmenu = teamsmenu;
	}

	public MenuVO getTimesheetapprovermenu() {
		return timesheetapprovermenu;
	}

	public void setTimesheetapprovermenu(MenuVO timesheetapprovermenu) {
		this.timesheetapprovermenu = timesheetapprovermenu;
	}

	public MenuVO getTimesheetmodule() {
		return timesheetmodule;
	}

	public void setTimesheetmodule(MenuVO timesheetmodule) {
		this.timesheetmodule = timesheetmodule;
	}

	public MenuVO getTargetsmenu() {
		return targetsmenu;
	}

	public void setTargetsmenu(MenuVO targetsmenu) {
		this.targetsmenu = targetsmenu;
	}
	
	public MenuVO getTargetstypemenu() {
		return targetstypemenu;
	}

	public void setTargetstypemenu(MenuVO targetstypemenu) {
		this.targetstypemenu = targetstypemenu;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public EmployeesVO getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(EmployeesVO updatedBy) {
		this.updatedBy = updatedBy;
	}

	public MenuVO getUsermenu() {
		return usermenu;
	}

	public void setUsermenu(MenuVO usermenu) {
		this.usermenu = usermenu;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public MenuVO getChildrenmenu() {
		return childrenmenu;
	}

	public void setChildrenmenu(MenuVO childrenmenu) {
		this.childrenmenu = childrenmenu;
	}

	public MenuVO getChildrenmenu_childName() {
		return childrenmenu_childName;
	}

	public void setChildrenmenu_childName(MenuVO childrenmenu_childName) {
		this.childrenmenu_childName = childrenmenu_childName;
	}

	public MenuVO getChildrenmenu_dob() {
		return childrenmenu_dob;
	}

	public void setChildrenmenu_dob(MenuVO childrenmenu_dob) {
		this.childrenmenu_dob = childrenmenu_dob;
	}

	public MenuVO getEducationmenu() {
		return educationmenu;
	}

	public void setEducationmenu(MenuVO educationmenu) {
		this.educationmenu = educationmenu;
	}

	public MenuVO getEducationmenu_edumajor() {
		return educationmenu_edumajor;
	}

	public void setEducationmenu_edumajor(MenuVO educationmenu_edumajor) {
		this.educationmenu_edumajor = educationmenu_edumajor;
	}

	public MenuVO getEducationmenu_eduyear() {
		return educationmenu_eduyear;
	}

	public void setEducationmenu_eduyear(MenuVO educationmenu_eduyear) {
		this.educationmenu_eduyear = educationmenu_eduyear;
	}

	public MenuVO getEducationmenu_edugrade() {
		return educationmenu_edugrade;
	}

	public void setEducationmenu_edugrade(MenuVO educationmenu_edugrade) {
		this.educationmenu_edugrade = educationmenu_edugrade;
	}

	public MenuVO getEducationmenu_edustartdate() {
		return educationmenu_edustartdate;
	}

	public void setEducationmenu_edustartdate(MenuVO educationmenu_edustartdate) {
		this.educationmenu_edustartdate = educationmenu_edustartdate;
	}

	public MenuVO getEducationmenu_eduenddate() {
		return educationmenu_eduenddate;
	}

	public void setEducationmenu_eduenddate(MenuVO educationmenu_eduenddate) {
		this.educationmenu_eduenddate = educationmenu_eduenddate;
	}

	public MenuVO getEducationmenu_eduuniversity() {
		return educationmenu_eduuniversity;
	}

	public void setEducationmenu_eduuniversity(MenuVO educationmenu_eduuniversity) {
		this.educationmenu_eduuniversity = educationmenu_eduuniversity;
	}

	public MenuVO getEducationmenu_edudegree() {
		return educationmenu_edudegree;
	}

	public void setEducationmenu_edudegree(MenuVO educationmenu_edudegree) {
		this.educationmenu_edudegree = educationmenu_edudegree;
	}

	public MenuVO getLicensemenu() {
		return licensemenu;
	}

	public void setLicensemenu(MenuVO licensemenu) {
		this.licensemenu = licensemenu;
	}

	public MenuVO getLicensemenu_licensenumber() {
		return licensemenu_licensenumber;
	}

	public void setLicensemenu_licensenumber(MenuVO licensemenu_licensenumber) {
		this.licensemenu_licensenumber = licensemenu_licensenumber;
	}

	public MenuVO getLicensemenu_licensedate() {
		return licensemenu_licensedate;
	}

	public void setLicensemenu_licensedate(MenuVO licensemenu_licensedate) {
		this.licensemenu_licensedate = licensemenu_licensedate;
	}

	public MenuVO getLicensemenu_licenserenewaldate() {
		return licensemenu_licenserenewaldate;
	}

	public void setLicensemenu_licenserenewaldate(
			MenuVO licensemenu_licenserenewaldate) {
		this.licensemenu_licenserenewaldate = licensemenu_licenserenewaldate;
	}

	public MenuVO getLicensemenu_licenseDesc() {
		return licensemenu_licenseDesc;
	}

	public void setLicensemenu_licenseDesc(MenuVO licensemenu_licenseDesc) {
		this.licensemenu_licenseDesc = licensemenu_licenseDesc;
	}

	public MenuVO getWorkexperiencemenu() {
		return workexperiencemenu;
	}

	public void setWorkexperiencemenu(MenuVO workexperiencemenu) {
		this.workexperiencemenu = workexperiencemenu;
	}

	public MenuVO getWorkexperiencemenu_employeername() {
		return workexperiencemenu_employeername;
	}

	public void setWorkexperiencemenu_employeername(
			MenuVO workexperiencemenu_employeername) {
		this.workexperiencemenu_employeername = workexperiencemenu_employeername;
	}

	public MenuVO getWorkexperiencemenu_empjobtitle() {
		return workexperiencemenu_empjobtitle;
	}

	public void setWorkexperiencemenu_empjobtitle(
			MenuVO workexperiencemenu_empjobtitle) {
		this.workexperiencemenu_empjobtitle = workexperiencemenu_empjobtitle;
	}

	public MenuVO getWorkexperiencemenu_fromdate() {
		return workexperiencemenu_fromdate;
	}

	public void setWorkexperiencemenu_fromdate(MenuVO workexperiencemenu_fromdate) {
		this.workexperiencemenu_fromdate = workexperiencemenu_fromdate;
	}

	public MenuVO getWorkexperiencemenu_todate() {
		return workexperiencemenu_todate;
	}

	public void setWorkexperiencemenu_todate(MenuVO workexperiencemenu_todate) {
		this.workexperiencemenu_todate = workexperiencemenu_todate;
	}

	public MenuVO getWorkexperiencemenu_comments() {
		return workexperiencemenu_comments;
	}

	public void setWorkexperiencemenu_comments(MenuVO workexperiencemenu_comments) {
		this.workexperiencemenu_comments = workexperiencemenu_comments;
	}

	public MenuVO getEmplocationhistorymenu() {
		return emplocationhistorymenu;
	}

	public void setEmplocationhistorymenu(MenuVO emplocationhistorymenu) {
		this.emplocationhistorymenu = emplocationhistorymenu;
	}

	public MenuVO getEmplocationhistorymenu_locationname() {
		return emplocationhistorymenu_locationname;
	}

	public void setEmplocationhistorymenu_locationname(
			MenuVO emplocationhistorymenu_locationname) {
		this.emplocationhistorymenu_locationname = emplocationhistorymenu_locationname;
	}

	public MenuVO getEmplocationhistorymenu_companyname() {
		return emplocationhistorymenu_companyname;
	}

	public void setEmplocationhistorymenu_companyname(
			MenuVO emplocationhistorymenu_companyname) {
		this.emplocationhistorymenu_companyname = emplocationhistorymenu_companyname;
	}

	public MenuVO getEmplocationhistorymenu_startdate() {
		return emplocationhistorymenu_startdate;
	}

	public void setEmplocationhistorymenu_startdate(
			MenuVO emplocationhistorymenu_startdate) {
		this.emplocationhistorymenu_startdate = emplocationhistorymenu_startdate;
	}

	public MenuVO getEmplocationhistorymenu_enddate() {
		return emplocationhistorymenu_enddate;
	}

	public void setEmplocationhistorymenu_enddate(
			MenuVO emplocationhistorymenu_enddate) {
		this.emplocationhistorymenu_enddate = emplocationhistorymenu_enddate;
	}

	public MenuVO getDirectdebitmenu() {
		return directdebitmenu;
	}

	public void setDirectdebitmenu(MenuVO directdebitmenu) {
		this.directdebitmenu = directdebitmenu;
	}

	public MenuVO getDirectdebitmenu_routingnum() {
		return directdebitmenu_routingnum;
	}

	public void setDirectdebitmenu_routingnum(MenuVO directdebitmenu_routingnum) {
		this.directdebitmenu_routingnum = directdebitmenu_routingnum;
	}

	public MenuVO getDirectdebitmenu_account() {
		return directdebitmenu_account;
	}

	public void setDirectdebitmenu_account(MenuVO directdebitmenu_account) {
		this.directdebitmenu_account = directdebitmenu_account;
	}

	public MenuVO getDirectdebitmenu_amount() {
		return directdebitmenu_amount;
	}

	public void setDirectdebitmenu_amount(MenuVO directdebitmenu_amount) {
		this.directdebitmenu_amount = directdebitmenu_amount;
	}

	public MenuVO getDirectdebitmenu_accounttype() {
		return directdebitmenu_accounttype;
	}

	public void setDirectdebitmenu_accounttype(MenuVO directdebitmenu_accounttype) {
		this.directdebitmenu_accounttype = directdebitmenu_accounttype;
	}

	public MenuVO getDirectdebitmenu_transactiontype() {
		return directdebitmenu_transactiontype;
	}

	public void setDirectdebitmenu_transactiontype(
			MenuVO directdebitmenu_transactiontype) {
		this.directdebitmenu_transactiontype = directdebitmenu_transactiontype;
	}

	public MenuVO getDirectdebitmenu_preAccountvalue() {
		return directdebitmenu_preAccountvalue;
	}

	public void setDirectdebitmenu_preAccountvalue(
			MenuVO directdebitmenu_preAccountvalue) {
		this.directdebitmenu_preAccountvalue = directdebitmenu_preAccountvalue;
	}

	public MenuVO getEmpreporttomenu() {
		return empreporttomenu;
	}

	public void setEmpreporttomenu(MenuVO empreporttomenu) {
		this.empreporttomenu = empreporttomenu;
	}

	public MenuVO getEmpreporttomenu_supempname() {
		return empreporttomenu_supempname;
	}

	public void setEmpreporttomenu_supempname(MenuVO empreporttomenu_supempname) {
		this.empreporttomenu_supempname = empreporttomenu_supempname;
	}

	public MenuVO getEmpreporttomenu_reportingmodevalue() {
		return empreporttomenu_reportingmodevalue;
	}

	public void setEmpreporttomenu_reportingmodevalue(
			MenuVO empreporttomenu_reportingmodevalue) {
		this.empreporttomenu_reportingmodevalue = empreporttomenu_reportingmodevalue;
	}

	public MenuVO getEmployeepassportmenu() {
		return employeepassportmenu;
	}

	public void setEmployeepassportmenu(MenuVO employeepassportmenu) {
		this.employeepassportmenu = employeepassportmenu;
	}

	public MenuVO getEmployeepassportmenu_passportno() {
		return employeepassportmenu_passportno;
	}

	public void setEmployeepassportmenu_passportno(
			MenuVO employeepassportmenu_passportno) {
		this.employeepassportmenu_passportno = employeepassportmenu_passportno;
	}

	public MenuVO getEmployeepassportmenu_passportissuedate() {
		return employeepassportmenu_passportissuedate;
	}

	public void setEmployeepassportmenu_passportissuedate(
			MenuVO employeepassportmenu_passportissuedate) {
		this.employeepassportmenu_passportissuedate = employeepassportmenu_passportissuedate;
	}

	public MenuVO getEmployeepassportmenu_passportexpiredate() {
		return employeepassportmenu_passportexpiredate;
	}

	public void setEmployeepassportmenu_passportexpiredate(
			MenuVO employeepassportmenu_passportexpiredate) {
		this.employeepassportmenu_passportexpiredate = employeepassportmenu_passportexpiredate;
	}

	public MenuVO getEmployeepassportmenu_passporttypeflg() {
		return employeepassportmenu_passporttypeflg;
	}

	public void setEmployeepassportmenu_passporttypeflg(
			MenuVO employeepassportmenu_passporttypeflg) {
		this.employeepassportmenu_passporttypeflg = employeepassportmenu_passporttypeflg;
	}

	public MenuVO getEmployeepassportmenu_l9Status() {
		return employeepassportmenu_l9Status;
	}

	public void setEmployeepassportmenu_l9Status(
			MenuVO employeepassportmenu_l9Status) {
		this.employeepassportmenu_l9Status = employeepassportmenu_l9Status;
	}

	public MenuVO getEmployeepassportmenu_l9reviewdate() {
		return employeepassportmenu_l9reviewdate;
	}

	public void setEmployeepassportmenu_l9reviewdate(
			MenuVO employeepassportmenu_l9reviewdate) {
		this.employeepassportmenu_l9reviewdate = employeepassportmenu_l9reviewdate;
	}

	public MenuVO getEmployeepassportmenu_countryname() {
		return employeepassportmenu_countryname;
	}

	public void setEmployeepassportmenu_countryname(
			MenuVO employeepassportmenu_countryname) {
		this.employeepassportmenu_countryname = employeepassportmenu_countryname;
	}

	public MenuVO getEmployeepassportmenu_comments() {
		return employeepassportmenu_comments;
	}

	public void setEmployeepassportmenu_comments(
			MenuVO employeepassportmenu_comments) {
		this.employeepassportmenu_comments = employeepassportmenu_comments;
	}

	public MenuVO getBenefitsmenu() {
		return benefitsmenu;
	}

	public void setBenefitsmenu(MenuVO benefitsmenu) {
		this.benefitsmenu = benefitsmenu;
	}

	public MenuVO getBenefitsmenu_year() {
		return benefitsmenu_year;
	}

	public void setBenefitsmenu_year(MenuVO benefitsmenu_year) {
		this.benefitsmenu_year = benefitsmenu_year;
	}

	public MenuVO getBenefitsmenu_benefitsname() {
		return benefitsmenu_benefitsname;
	}

	public void setBenefitsmenu_benefitsname(MenuVO benefitsmenu_benefitsname) {
		this.benefitsmenu_benefitsname = benefitsmenu_benefitsname;
	}

	public MenuVO getBenefitsmenu_attachfile() {
		return benefitsmenu_attachfile;
	}

	public void setBenefitsmenu_attachfile(MenuVO benefitsmenu_attachfile) {
		this.benefitsmenu_attachfile = benefitsmenu_attachfile;
	}

	public MenuVO getEmpleavequotamenu() {
		return empleavequotamenu;
	}

	public void setEmpleavequotamenu(MenuVO empleavequotamenu) {
		this.empleavequotamenu = empleavequotamenu;
	}

	public MenuVO getEmpleavequotamenu_leavetype() {
		return empleavequotamenu_leavetype;
	}

	public void setEmpleavequotamenu_leavetype(MenuVO empleavequotamenu_leavetype) {
		this.empleavequotamenu_leavetype = empleavequotamenu_leavetype;
	}

	public MenuVO getEmpleavequotamenu_year() {
		return empleavequotamenu_year;
	}

	public void setEmpleavequotamenu_year(MenuVO empleavequotamenu_year) {
		this.empleavequotamenu_year = empleavequotamenu_year;
	}

	public MenuVO getEmpleavequotamenu_preleavecarryforward() {
		return empleavequotamenu_preleavecarryforward;
	}

	public void setEmpleavequotamenu_preleavecarryforward(
			MenuVO empleavequotamenu_preleavecarryforward) {
		this.empleavequotamenu_preleavecarryforward = empleavequotamenu_preleavecarryforward;
	}

	public MenuVO getEmpleavequotamenu_empleavepending() {
		return empleavequotamenu_empleavepending;
	}

	public void setEmpleavequotamenu_empleavepending(
			MenuVO empleavequotamenu_empleavepending) {
		this.empleavequotamenu_empleavepending = empleavequotamenu_empleavepending;
	}

	public MenuVO getEmpleavequotamenu_leaveallotteddays() {
		return empleavequotamenu_leaveallotteddays;
	}

	public void setEmpleavequotamenu_leaveallotteddays(
			MenuVO empleavequotamenu_leaveallotteddays) {
		this.empleavequotamenu_leaveallotteddays = empleavequotamenu_leaveallotteddays;
	}

	public MenuVO getEmpleavequotamenu_empleaverequest() {
		return empleavequotamenu_empleaverequest;
	}

	public void setEmpleavequotamenu_empleaverequest(
			MenuVO empleavequotamenu_empleaverequest) {
		this.empleavequotamenu_empleaverequest = empleavequotamenu_empleaverequest;
	}

	public MenuVO getPaystubmenu() {
		return paystubmenu;
	}

	public void setPaystubmenu(MenuVO paystubmenu) {
		this.paystubmenu = paystubmenu;
	}

	public MenuVO getPaystubmenu_grosssalary() {
		return paystubmenu_grosssalary;
	}

	public void setPaystubmenu_grosssalary(MenuVO paystubmenu_grosssalary) {
		this.paystubmenu_grosssalary = paystubmenu_grosssalary;
	}

	public MenuVO getPaystubmenu_decdate() {
		return paystubmenu_decdate;
	}

	public void setPaystubmenu_decdate(MenuVO paystubmenu_decdate) {
		this.paystubmenu_decdate = paystubmenu_decdate;
	}

	public MenuVO getPaystubmenu_netsalary() {
		return paystubmenu_netsalary;
	}

	public void setPaystubmenu_netsalary(MenuVO paystubmenu_netsalary) {
		this.paystubmenu_netsalary = paystubmenu_netsalary;
	}

	public MenuVO getPaystubmenu_deductionname() {
		return paystubmenu_deductionname;
	}

	public void setPaystubmenu_deductionname(MenuVO paystubmenu_deductionname) {
		this.paystubmenu_deductionname = paystubmenu_deductionname;
	}

	public MenuVO getPaystubmenu_deductiontype() {
		return paystubmenu_deductiontype;
	}

	public void setPaystubmenu_deductiontype(MenuVO paystubmenu_deductiontype) {
		this.paystubmenu_deductiontype = paystubmenu_deductiontype;
	}

	public MenuVO getPaystubmenu_deductionmode() {
		return paystubmenu_deductionmode;
	}

	public void setPaystubmenu_deductionmode(MenuVO paystubmenu_deductionmode) {
		this.paystubmenu_deductionmode = paystubmenu_deductionmode;
	}

	public MenuVO getPaystubmenu_deductionamount() {
		return paystubmenu_deductionamount;
	}

	public void setPaystubmenu_deductionamount(MenuVO paystubmenu_deductionamount) {
		this.paystubmenu_deductionamount = paystubmenu_deductionamount;
	}

	public MenuVO getPaystubmenu_deductioneffdate() {
		return paystubmenu_deductioneffdate;
	}

	public void setPaystubmenu_deductioneffdate(MenuVO paystubmenu_deductioneffdate) {
		this.paystubmenu_deductioneffdate = paystubmenu_deductioneffdate;
	}

	public MenuVO getPerformancemenucategory() {
		return performancemenucategory;
	}

	public void setPerformancemenucategory(MenuVO performancemenucategory) {
		this.performancemenucategory = performancemenucategory;
	}

	public MenuVO getPerformancemenusubcategory() {
		return performancemenusubcategory;
	}

	public void setPerformancemenusubcategory(MenuVO performancemenusubcategory) {
		this.performancemenusubcategory = performancemenusubcategory;
	}

	public MenuVO getPerformancemenukpiquestion() {
		return performancemenukpiquestion;
	}

	public void setPerformancemenukpiquestion(MenuVO performancemenukpiquestion) {
		this.performancemenukpiquestion = performancemenukpiquestion;
	}

	public MenuVO getPerformancemenukpiquestiongroup() {
		return performancemenukpiquestiongroup;
	}

	public void setPerformancemenukpiquestiongroup(
			MenuVO performancemenukpiquestiongroup) {
		this.performancemenukpiquestiongroup = performancemenukpiquestiongroup;
	}

	public MenuVO getPerformancemenukpiassignkpi() {
		return performancemenukpiassignkpi;
	}

	public void setPerformancemenukpiassignkpi(MenuVO performancemenukpiassignkpi) {
		this.performancemenukpiassignkpi = performancemenukpiassignkpi;
	}
    
}
