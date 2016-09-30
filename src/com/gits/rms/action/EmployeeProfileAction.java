/**
 * 
 */
package com.gits.rms.action;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.BenefitsDaoService;
import com.gits.rms.service.BenefitsService;
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
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.ImportantNewsVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.TimeSheetApproverVO;

/**
 *  * This class maintains all employee profile details
 */
public class EmployeeProfileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6269756048735425788L;
	
    static Logger logger = Logger.getLogger(EmployeeProfileAction.class);
    
    private EmployeesService emplService = new EmployeesDaoService();
    private EmployeeReportToService empReportToService = new EmployeeReportToDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private RoleService roleService = new RoleDaoService();
    private EmployeesVO employee;
    private RoleVO role;
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

    // To get List of Employee
    @SkipValidation
    public String getAllEmp() {
    	emploList = emplService.getAllEmployees((Integer) session.get("CLIENT_INFO"));
        return SUCCESS;
    }
    
 // Convert Int to String for Reporting Mode Field
    @SkipValidation
    public void setGenderValues(EmployeesVO gender) {
        gender.setEmpGenderValue(gender.getEmpGender() == "M" ? getText("label.employee.length.value.Female")
            : getText("label.employee.length.value.male"));
    }
    
    // In the New Form when click Submit button To insert new Employee 
    // particular Employee profile Data
    @SkipValidation
    public String insertEmployeeProfile() {
    	logger.debug("control enters into insert employee profile");

        role = roleService.getRoleName(getText("message.label.common.adminName"));
        try {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                logger.debug("oEmp.getEmployeeId() : " + oEmp.getEmployeeId());
                logger.debug("firstname" + employee.getEmpFirstName());
                employee.setCreated(DateUtils.getCurrentDateTime());
                employee.setCreatedBy(oEmp);
                employee.setUpdatedBy(oEmp);
                employee.setEmpFullName(employee.getEmpFirstName() + " "
                    + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")");
                employee.setIsActive(1);
                
                emplService.insertEmployees(employee);
                session.put("HCMO_EMPLOYEE_ID", employee.getEmployeeId());
                addActionMessage(getText("Added Successfully"));
                logger.info("Profile added successfully");
                Integer employeeId = (Integer)session.get("HCMO_EMPLOYEE_ID");
            	System.out.println("emp id" + employeeId);
                /* employee = emplService.getEmployees(employee.getEmployeeId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = employee.getEmployeeId();
                String sSubject = getText("message.subject.employee.add");

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
                }*/
            } catch (RuntimeException e) {
                e.printStackTrace();
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                e.printStackTrace();
                throw e;

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception occurred during the employee profile insertion" + e);
            }
        
        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;
    }
    
    // To update employee profile data
    @SkipValidation
    public String updateEmployeeProfile() {
    	
        logger.debug("Control enters into update employee profile details");

            try {
            	
            	logger.debug("employee : " + employee);
                //EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                logger.debug("oEmp : " + oEmp);
                employee.setUpdatedBy(oEmp);
                logger.debug("After set employee.setUpdatedBy(oEmp)");
                //on 24JUNE2016
                
                EmployeesVO employeeFromDB = null;
                Integer selectedEmployeeId = new Integer(java.sql.Types.NULL);
                
                if(session.get("SELECTED_EMPLOYEE_ID") != null){
                	selectedEmployeeId = (Integer) session.get("SELECTED_EMPLOYEE_ID");
                	logger.debug("selectedEmployeeId : " + selectedEmployeeId);
                }
                
                
                if(employee != null){
                	logger.debug("Before SET session's employee ID employee.getEmployeeId() : " + employee.getEmployeeId());
                    if(selectedEmployeeId > 0){
                    	employee.setEmployeeId(selectedEmployeeId);
                    }
                    logger.debug("Afeter SET session's employee ID employee.getEmployeeId() : " + employee.getEmployeeId());
                	if(employee.getEmployeeId() != null){
                		logger.debug("employee.getEmployeeId() : " + employee.getEmployeeId());
                		if(employee.getEmployeeId() > 0){
                			// get employee by id
                			employeeFromDB = emplService.getEmployeeById(employee.getEmployeeId());
                			logger.debug("employeeFromDB : " + employeeFromDB);
                		}
                	}
                	
                    if(employeeFromDB != null){
                    	
                    	try {
            				String empFirstName = employee.getEmpFirstName();
            				String empLastName = employee.getEmpLastName();
            				String empFullName = employee.getEmpFirstName() + " " + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")";
            				Date empBirthDay = employee.getEmpBirthDate();
            				String empGender = employee.getEmpGender();
            				String empType = employee.getEmpType();
//            				String empStreet1 = employee.getEmpStreet1();
            				String empCityName = employee.getEmpCityName();
            				String empCountryName = employee.getEmpCounName();
            				String empMobile = employee.getEmpMobile();
            				String empWorkEmail = employee.getEmpWorkEmail();
            				Integer hcmoRoleId = employee.getRoleObj().getHcmoRoleId();
            				Integer hcmoDepartmentId = employee.getDepartmentIdObj().getHcmoDepartmentId();
            				/*Integer hcmoUpdatedById = employee.getUpdatedBy().getEmployeeId();
            				Integer hcmoDocumentId = employee.getDocumentIdObj().getHcmoDocumentId();*/
            				String accessType = employee.getAccessType();
            				Boolean rLiteAccess = employee.getrLiteAccess();
            				
            				employeeFromDB.setEmpFirstName(empFirstName);
            				employeeFromDB.setEmpBirthDate(empBirthDay);
            				employeeFromDB.setEmpLastName(empLastName);
            				employeeFromDB.setEmpFullName(empFullName);
            				employeeFromDB.setEmpGender(empGender);
            				employeeFromDB.setEmpType(empType);
            				employeeFromDB.setEmpCityName(empCityName);
            				employeeFromDB.setEmpCounName(empCountryName);
            				employeeFromDB.setEmpMobile(empMobile);
            				employeeFromDB.setEmpWorkEmail(empWorkEmail);
            				employeeFromDB.setAccessType(accessType);
            				employeeFromDB.setrLiteAccess(rLiteAccess);
            				
            				employeeFromDB.setRoleObj(new RoleVO(hcmoRoleId));
            				employeeFromDB.setDepartmentIdObj(new DepartmentVO(hcmoDepartmentId));
            				
						} catch (Exception e) {
							logger.debug("Error while getting employee values from page : " + e);
						}

        				
        				logger.debug("### employeeFromDB : " + employeeFromDB);
                        employee = emplService.updateEmployeeProfile(employeeFromDB);
                        addActionMessage(getText("Updated Successfully"));
                        logger.info("Profile updated successfully");
                    	
                    }else{
                    	// new employee
                    	//EmployeesVO newAdminEmp = null;
                        //Map session = ActionContext.getContext().getSession();
                        //EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    	
                    	Integer clientId = (Integer) session.get("CLIENT_INFO");
                        logger.debug("oEmp.getEmployeeId() : " + oEmp.getEmployeeId());
                        logger.debug("firstname" + employee.getEmpFirstName());
                        employee.setCreated(DateUtils.getCurrentDateTime());
                        employee.setCreatedBy(oEmp);
                        employee.setUpdatedBy(oEmp);
                        employee.setEmpFullName(employee.getEmpFirstName() + " "
                            + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")");
                        employee.setIsActive(1);
                        employee.setClientId(clientId);
                        
                        employee = emplService.insertEmployees(employee);
                        
                        if(employee != null){
                        	session.put("SELECTED_EMPLOYEE_ID", employee.getEmployeeId());
                        }
                        
                    }
                	
                }
                

                



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

                       /* employee = emplService.getEmployees(employee.getEmployeeId());
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
                        }*/                 
            } catch (RuntimeException e) {
                e.printStackTrace();
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                e.printStackTrace();
                throw e;

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception occurred during the profile updation" + e);
            }
        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;
    }
    
    
    // To delete Employee profile data
    @SkipValidation
    public String deleteEmployeeProfile() {
        try {
        	logger.debug("control enters into employee profile deletion");
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            employee.setUpdatedBy(oEmp);
            emplService.deleteEmployees(employee);

            employee = emplService.getEmployees(employee.getEmployeeId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

           /* int sessionEmpId = oEmp.getEmployeeId();
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
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception occurred during the profile deletion" + e);
        }
        addActionMessage(getText("Deleted Successfully"));
        logger.info("Profile deleted successfully");
        
        // For Drop down List
        loadValues.getAllEmployeeName();
        return SUCCESS;
    }

	public EmployeesService getEmplService() {
		return emplService;
	}

	public void setEmplService(EmployeesService emplService) {
		this.emplService = emplService;
	}

	public LoadKeyValuesAction getLoadValues() {
		return loadValues;
	}

	public void setLoadValues(LoadKeyValuesAction loadValues) {
		this.loadValues = loadValues;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public EmployeesVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeesVO employee) {
		this.employee = employee;
	}

	public RoleVO getRole() {
		return role;
	}

	public void setRole(RoleVO role) {
		this.role = role;
	}

	public List<EmployeesVO> getEmploList() {
		return emploList;
	}

	public void setEmploList(List<EmployeesVO> emploList) {
		this.emploList = emploList;
	}

	public List<EmployeesVO> getAdminListSize() {
		return adminListSize;
	}

	public void setAdminListSize(List<EmployeesVO> adminListSize) {
		this.adminListSize = adminListSize;
	}

	public List<EmployeesVO> getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public InputStream getInStream() {
		return inStream;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
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

	public List<ImportantNewsVO> getImpList() {
		return impList;
	}

	public void setImpList(List<ImportantNewsVO> impList) {
		this.impList = impList;
	}

	public ImportantNewsService getImpServivce() {
		return impServivce;
	}

	public void setImpServivce(ImportantNewsService impServivce) {
		this.impServivce = impServivce;
	}

}
