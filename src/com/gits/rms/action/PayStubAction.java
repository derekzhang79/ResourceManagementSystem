
package com.gits.rms.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.PayStubArchiveDaoService;
import com.gits.rms.service.PayStubArchiveService;
import com.gits.rms.service.PayStubDaoService;
import com.gits.rms.service.PayStubDeductionDaoService;
import com.gits.rms.service.PayStubDeductionService;
import com.gits.rms.service.PayStubService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.PayStubArchiveVO;
import com.gits.rms.vo.PayStubDeductionsVO;
import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class PayStubAction extends ActionSupport {
    private static final long serialVersionUID = -8601423243663858024L;
    static Logger log = Logger.getLogger(PayStubAction.class.getName());// for store log details
    private PayStubService payStubService = new PayStubDaoService();
    private PayStubDeductionService payStubDeductionService = new PayStubDeductionDaoService();
    private PayStubArchiveService payStubArchiveService = new PayStubArchiveDaoService();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<PayStubVO> payStubList;
    private List<PayStubDeductionsVO> payStubDeductionList;
    private PayStubVO payStub;
    private PayStubDeductionsVO payStubDeduction;
    private int paystubDeductionCount;
    private RoleVO role;
    private String payStubDeclareDateMail = "";
    private String payStubModifiedDateMail = "";
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private String payStubDedEffectiveDateMail = "";
    private String payStubDedModifiedDateMail = "";
    private Integer clientId;

    // To get List of EmployeeStatus
    @SkipValidation
    public String getAllPayStubs() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		payStubList = payStubService.getAllSubEmployeePayStubList(employeeReportToList);
    	}else{
    		payStubList = payStubService.getAllPayStubs();
    	}
    	
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Employee it shows blank Form to enter New Data
    @SkipValidation
    public String setUpForInsertOrUpdatePayStub() {
        if ((payStub != null) && (payStub.getPayStubId() != null)) {
            payStub = payStubService.getPayStub(payStub.getPayStubId());
        }
        return SUCCESS;
    }

    // To get Particular EmployeeStatus of an Employee
    @SkipValidation
    public String payStubView() {
        if ((payStub != null) && (payStub.getPayStubId() != null)) {
            payStub = payStubService.getPayStub(payStub.getPayStubId());
        }
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String payStubSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String payStubSearchResult() {
        payStubList = payStubService.payStubSearchResult(payStub);
        return SUCCESS;
    }

    // To insert new PayStub detail or Edit Particular PayStub Detail
    @SkipValidation
    public String insertOrUpdatePayStub() {
        try {
        	log.debug("control enters into employee paystub details insertion");
            if (payStub.getPayStubId() == null) {
                Map session = ActionContext.getContext().getSession();
                clientId = (Integer) session.get("CLIENT_INFO");
            	if(clientId != null)
            		payStub.setClientId(clientId);
                
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                EmployeesVO newAdminEmp = null;
                payStub.setNetSalary(payStub.getGrossSalary());
                payStub.setCreated(DateUtils.getCurrentDateTime());
                payStub.setCreatedBy(oEmp);
                payStub.setUpdatedBy(oEmp);
                payStub.setIsActive(1);
                payStubService.insertPayStub(payStub);
                addActionMessage(getText("Added Successfully"));
                log.info("Paystub details added successfully");

                /*payStub = payStubService.getPayStub(payStub.getPayStubId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = payStub.getEmployee().getEmployeeId();
                String sSubject = getText("message.subject.payStub.add");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.add.addedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.add.addedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.add.addedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.add.addedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }*/
            } else if (payStub.getPayStubId() != null) {
            	log.debug("Control enters into paystub details updation");
                EmployeesVO newAdminEmp = null;
                PayStubDeductionsVO newPaystubObj;
                List<PayStubDeductionsVO> newPayStubDeductionList = new LinkedList<PayStubDeductionsVO>();
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                PayStubVO payStubObj = new PayStubVO();
                PayStubArchiveVO payStubArchive = new PayStubArchiveVO();

                Date today = new java.util.Date();
                Timestamp time = new java.sql.Timestamp(today.getTime());

                payStubObj = payStubService.getPayStub(payStub.getPayStubId());
                payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStubObj.getPayStubId());

                if (payStubDeductionList.isEmpty()) {
                    payStubArchive.setPayStubHcmoPayStubId(payStubObj.getPayStubId());
                    payStubArchive.setPayStubHcmoEmployeeId(payStubObj.getEmployee().getEmployeeId());
                    payStubArchive.setPayStubGrossSalary(payStubObj.getGrossSalary());
                    payStubArchive.setPayStubNetSalary(payStubObj.getNetSalary());
                    payStubArchive.setPayStubDeclarationDate(payStubObj.getDeclarationDate());
                    payStubArchive.setPayStubCreated(payStubObj.getCreated());
                    payStubArchive.setPayStubCreatedBy(payStubObj.getCreatedBy().getEmployeeId());
                    payStubArchive.setPayStubUpdated(payStubObj.getUpdated());
                    payStubArchive.setPayStubUpdatedBy(payStubObj.getUpdatedBy().getEmployeeId());
                    payStubArchive.setPayStubIsActive(payStubObj.getIsActive());
                    payStubArchive.setDeletedBy(oEmp.getEmployeeId());
                    payStubArchive.setDeleted(time);

                    payStubArchiveService.insertPayStubArchive(payStubArchive);

                } else if (!(payStubDeductionList.isEmpty())) {
                    for (Iterator<PayStubDeductionsVO> ite = payStubDeductionList.iterator(); ite.hasNext();) {
                        PayStubDeductionsVO payStubDeductionObj = ite.next();

                        payStubArchive.setPayStubHcmoPayStubId(payStubObj.getPayStubId());
                        payStubArchive.setPayStubHcmoEmployeeId(payStubObj.getEmployee().getEmployeeId());
                        payStubArchive.setPayStubGrossSalary(payStubObj.getGrossSalary());
                        payStubArchive.setPayStubNetSalary(payStubObj.getNetSalary());
                        payStubArchive.setPayStubDeclarationDate(payStubObj.getDeclarationDate());
                        payStubArchive.setPayStubCreated(payStubObj.getCreated());
                        payStubArchive.setPayStubCreatedBy(payStubObj.getCreatedBy().getEmployeeId());
                        payStubArchive.setPayStubUpdated(payStubObj.getUpdated());
                        payStubArchive.setPayStubUpdatedBy(payStubObj.getUpdatedBy().getEmployeeId());
                        payStubArchive.setPayStubIsActive(payStubObj.getIsActive());

                        payStubArchive.setPayStubDeductionHcmoPayStubDeductionsId(payStubDeductionObj.getPayStubDeductionId());
                        payStubArchive.setPayStubDeductionHcmoPayStubId(payStubDeductionObj.getPayStub().getPayStubId());
                        payStubArchive.setPayStubDeductionHcmoDeductionId(payStubDeductionObj.getDeduction().getDeductionId());
                        payStubArchive.setPayStubDeductionDeductionAmount(payStubDeductionObj.getDeductionAmount());
                        payStubArchive.setPayStubDeductionEffectiveDate(payStubDeductionObj.getEffectiveDate());
                        payStubArchive.setPayStubDeductionCreated(payStubDeductionObj.getCreated());
                        payStubArchive.setPayStubDeductionCreatedBy(payStubDeductionObj.getCreatedBy().getEmployeeId());
                        payStubArchive.setPayStubDeductionUpdated(payStubDeductionObj.getUpdated());
                        payStubArchive.setPayStubDeductionUpdatedBy(payStubDeductionObj.getUpdatedBy().getEmployeeId());
                        payStubArchive.setPayStubDeductionIsActive(payStubDeductionObj.getIsActive());

                        payStubArchive.setDeletedBy(oEmp.getEmployeeId());
                        payStubArchive.setDeleted(time);

                        payStubArchiveService.insertPayStubArchive(payStubArchive);

                    }
                }

                payStub.setUpdatedBy(oEmp);
                payStubService.updatePayStub(payStub);

                payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
                double grossSalary = payStub.getGrossSalary();

                payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
                paystubDeductionCount = payStubDeductionList.size();

                // changing the net salary besed on the entered deduction
                if (!(payStubDeductionList.isEmpty())) {

                    for (Iterator<PayStubDeductionsVO> it = payStubDeductionList.iterator(); it.hasNext();) {
                        newPaystubObj = it.next();

                        if (newPaystubObj.getDeduction().getDeductionType().equals("PreTax")) {
                            newPayStubDeductionList.add(newPaystubObj);
                        }
                    }

                    for (Iterator<PayStubDeductionsVO> ite = payStubDeductionList.iterator(); ite.hasNext();) {
                        newPaystubObj = ite.next();

                        if (newPaystubObj.getDeduction().getDeductionType().equals("Tax")) {
                            newPayStubDeductionList.add(newPaystubObj);
                        }
                    }

                    for (Iterator iterate = payStubDeductionList.iterator(); iterate.hasNext();) {
                        newPaystubObj = (PayStubDeductionsVO) iterate.next();

                        if (newPaystubObj.getDeduction().getDeductionType().equals("PostTax")) {
                            newPayStubDeductionList.add(newPaystubObj);
                        }
                    }

                    for (Iterator<PayStubDeductionsVO> iterator = newPayStubDeductionList.iterator(); iterator.hasNext();) {
                        newPaystubObj = iterator.next();

                        if (newPaystubObj.getDeduction().getDeductionMode().equals("Percentage")) {
                            double tempSalary = ((newPaystubObj.getDeductionAmount() / 100) * grossSalary);
                            grossSalary = grossSalary - tempSalary;

                        } else if (newPaystubObj.getDeduction().getDeductionMode().equals("Actuals")) {
                            grossSalary = grossSalary - newPaystubObj.getDeductionAmount();
                        }
                    }
                    payStubDeductionService.updatePayStubNetSalary(payStub.getPayStubId(), grossSalary);
                }

               /* payStub = payStubService.getPayStub(payStub.getPayStubId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = payStub.getEmployee().getEmployeeId();
                String sSubject = getText("message.subject.payStub.edit");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.edit.updatedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.edit.updatedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.edit.updatedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.edit.updatedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }*/
                addActionMessage(getText("Updated Successfully"));
                log.info("Paystub details updated successfully");
            }
        } catch (RuntimeException e) {
            log.error("Exception occurred during the paystub insertion/updation" + e);
            new ErrorsAction();
            String sError = "Employee name value already exists";
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    // To get Particular Paystub of an Employee
    @SkipValidation
    public String getEmployeePayStub() {
        payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
        paystubDeductionCount = payStubDeductionList.size();

        return SUCCESS;
    }

    // Based on EmployeeId Insert new PayStub for that Employee or Update data
    // of the PayStub
    @SkipValidation
    public String setUpEmpPayStub() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStub.employee.employeeId"));
        if ((payStub != null) && (payStub.getPayStubId() != null) && (employeeId != null)) {
            payStub = payStubService.getPayStub(payStub.getPayStubId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId get All PayStub of that Employee
    @SkipValidation
    public String getEmployeeAllPayStub() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("payStub.employee.employeeId"));
        payStubList = payStubService.getEmployeeAllPayStub(employeeId);
        return SUCCESS;
    }

    // To delete particular EmployeeStatus data
    @SkipValidation
    public String deletePayStub() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        PayStubDeductionsVO newPayStubDeduction = new PayStubDeductionsVO();
        PayStubArchiveVO payStubArchive = new PayStubArchiveVO();

        Date today = new Date();
        Timestamp time = new java.sql.Timestamp(today.getTime());

        payStub = payStubService.getPayStub(payStub.getPayStubId());
        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());

        if (payStubDeductionList.isEmpty()) {
            payStubArchive.setPayStubHcmoPayStubId(payStub.getPayStubId());
            payStubArchive.setPayStubHcmoEmployeeId(payStub.getEmployee().getEmployeeId());
            payStubArchive.setPayStubGrossSalary(payStub.getGrossSalary());
            payStubArchive.setPayStubNetSalary(payStub.getNetSalary());
            payStubArchive.setPayStubDeclarationDate(payStub.getDeclarationDate());
            payStubArchive.setPayStubCreated(payStub.getCreated());
            payStubArchive.setPayStubCreatedBy(payStub.getCreatedBy().getEmployeeId());
            payStubArchive.setPayStubUpdated(payStub.getUpdated());
            payStubArchive.setPayStubUpdatedBy(payStub.getUpdatedBy().getEmployeeId());
            payStubArchive.setPayStubIsActive(payStub.getIsActive());
            payStubArchive.setDeletedBy(oEmp.getEmployeeId());
            payStubArchive.setDeleted(time);

            payStubArchiveService.insertPayStubArchive(payStubArchive);

        } else if (!(payStubDeductionList.isEmpty())) {
            for (Iterator<PayStubDeductionsVO> ite = payStubDeductionList.iterator(); ite.hasNext();) {
                PayStubDeductionsVO payStubDeductionObj = ite.next();

                payStubArchive.setPayStubHcmoPayStubId(payStub.getPayStubId());
                payStubArchive.setPayStubHcmoEmployeeId(payStub.getEmployee().getEmployeeId());
                payStubArchive.setPayStubGrossSalary(payStub.getGrossSalary());
                payStubArchive.setPayStubNetSalary(payStub.getNetSalary());
                payStubArchive.setPayStubDeclarationDate(payStub.getDeclarationDate());
                payStubArchive.setPayStubCreated(payStub.getCreated());
                payStubArchive.setPayStubCreatedBy(payStub.getCreatedBy().getEmployeeId());
                payStubArchive.setPayStubUpdated(payStub.getUpdated());
                payStubArchive.setPayStubUpdatedBy(payStub.getUpdatedBy().getEmployeeId());
                payStubArchive.setPayStubIsActive(payStub.getIsActive());

                payStubArchive.setPayStubDeductionHcmoPayStubDeductionsId(payStubDeductionObj.getPayStubDeductionId());
                payStubArchive.setPayStubDeductionHcmoPayStubId(payStubDeductionObj.getPayStub().getPayStubId());
                payStubArchive.setPayStubDeductionHcmoDeductionId(payStubDeductionObj.getDeduction().getDeductionId());
                payStubArchive.setPayStubDeductionDeductionAmount(payStubDeductionObj.getDeductionAmount());
                payStubArchive.setPayStubDeductionEffectiveDate(payStubDeductionObj.getEffectiveDate());
                payStubArchive.setPayStubDeductionCreated(payStubDeductionObj.getCreated());
                payStubArchive.setPayStubDeductionCreatedBy(payStubDeductionObj.getCreatedBy().getEmployeeId());
                payStubArchive.setPayStubDeductionUpdated(payStubDeductionObj.getUpdated());
                payStubArchive.setPayStubDeductionUpdatedBy(payStubDeductionObj.getUpdatedBy().getEmployeeId());
                payStubArchive.setPayStubDeductionIsActive(payStubDeductionObj.getIsActive());

                payStubArchive.setDeletedBy(oEmp.getEmployeeId());
                payStubArchive.setDeleted(time);

                payStubArchiveService.insertPayStubArchive(payStubArchive);

            }

        }

        role = roleService.getRoleName(getText("message.label.common.adminName"));
        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = payStub.getEmployee().getEmployeeId();
        String sSubject = getText("message.subject.payStub.delete");

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // logged in person is admin and he is entering his own information
            if (adminEmpID == sessionEmpId) {
                if (adminEmpID == employeeID) {
                    // the mail content to Other admin Employees
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.delete.deletedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.delete.deletedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStub.delete.deletedToAdmin"), payStub.getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStub.delete.deletedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(payStub.getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStub.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }

        if (!(payStubDeductionList.isEmpty())) {
            for (Iterator<PayStubDeductionsVO> ite = payStubDeductionList.iterator(); ite.hasNext();) {
                newPayStubDeduction = ite.next();

                payStubDeductionService.deletePayStubDeduction(newPayStubDeduction);
            }
        }
        payStubService.deletePayStub(payStub);
        addActionMessage(getText("Deleted Successfully"));

        return SUCCESS;
    }

    // To get Particular Paystub of an Employee
    @SkipValidation
    public String getEssEmpPayStub() {
        payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
        paystubDeductionCount = payStubDeductionList.size();
        return SUCCESS;
    }

    // To get Particular PayStub of an Employee
    @SkipValidation
    public String payStubEssView() {
        if ((payStub != null) && (payStub.getPayStubId() != null)) {
            payStub = payStubService.getPayStub(payStub.getPayStubId());
        }
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date declarationDate = payStub.getDeclarationDate();
            payStubDeclareDateMail = DateFormat.format(declarationDate);

            Date payStubModdate = payStub.getUpdated();
            payStubModifiedDateMail = UpdatedDateFormat.format(payStubModdate);

            payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
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
                + getText("label.header.payStub.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStub.getEmployee().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.grossSalary")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + payStub.getGrossSalary()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.netSalary") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStub.getNetSalary() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.decDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStubDeclareDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + payStubModifiedDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (!payStubDeductionList.isEmpty()) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                    + getText("label.header.payStubDeduction.info")
                    + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);

                for (int i = 0; i < payStubDeductionList.size(); i++) {
                    PayStubDeductionsVO payStubDeduction = payStubDeductionList.get(i);

                    Date payStubDedEffectiveDate = payStubDeduction.getEffectiveDate();
                    payStubDedEffectiveDateMail = UpdatedDateFormat.format(payStubDedEffectiveDate);

                    Date payStubDedModDate = payStubDeduction.getUpdated();
                    payStubDedModifiedDateMail = UpdatedDateFormat.format(payStubDedModDate);

                    sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.name")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionName()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.type")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionType()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.mode")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionMode()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.amount")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeductionAmount()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.effDate")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDedEffectiveDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.common.message.modifiedDate")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDedModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                }
            }
            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG);

            sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
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

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date declarationDate = payStub.getDeclarationDate();
            payStubDeclareDateMail = DateFormat.format(declarationDate);

            Date payStubModdate = payStub.getUpdated();
            payStubModifiedDateMail = UpdatedDateFormat.format(payStubModdate);

            payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
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
            sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.payStub.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStub.getEmployee().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.grossSalary")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + payStub.getGrossSalary()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.netSalary") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStub.getNetSalary() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.paystub.decDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + payStub.getDeclarationDate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + payStubModifiedDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            if (!payStubDeductionList.isEmpty()) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                    + getText("label.header.payStubDeduction.info")
                    + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);

                for (int i = 0; i < payStubDeductionList.size(); i++) {
                    PayStubDeductionsVO payStubDeduction = payStubDeductionList.get(i);

                    Date payStubDedEffectiveDate = payStubDeduction.getEffectiveDate();
                    payStubDedEffectiveDateMail = UpdatedDateFormat.format(payStubDedEffectiveDate);

                    Date payStubDedModDate = payStubDeduction.getUpdated();
                    payStubDedModifiedDateMail = UpdatedDateFormat.format(payStubDedModDate);
                    sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.name")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionName()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.type")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionType()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.mode")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeduction().getDeductionMode()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.amount")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDeduction.getDeductionAmount()
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.header.deduction.effDate")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDedEffectiveDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + getText("label.common.message.modifiedDate")
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                        + payStubDedModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG).append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                    .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_DATA_START_TAG
                        + HtmlConstants.HTML_TABLE_DATA_END_TAG
                        + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                }
            }
            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG)

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

    public PayStubService getPayStubService() {
        return payStubService;
    }

    public void setPayStubService(PayStubService payStubService) {
        this.payStubService = payStubService;
    }

    public List<PayStubVO> getPayStubList() {
        return payStubList;
    }

    public void setPayStubList(List<PayStubVO> payStubList) {
        this.payStubList = payStubList;
    }

    public PayStubVO getPayStub() {
        return payStub;
    }

    public void setPayStub(PayStubVO payStub) {
        this.payStub = payStub;
    }

    public List<PayStubDeductionsVO> getPayStubDeductionList() {
        return payStubDeductionList;
    }

    public void setPayStubDeductionList(List<PayStubDeductionsVO> payStubDeductionList) {
        this.payStubDeductionList = payStubDeductionList;
    }

    public PayStubDeductionsVO getPayStubDeduction() {
        return payStubDeduction;
    }

    public void setPayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        this.payStubDeduction = payStubDeduction;
    }

    public int getPaystubDeductionCount() {
        return paystubDeductionCount;
    }

    public void setPaystubDeductionCount(int paystubDeductionCount) {
        this.paystubDeductionCount = paystubDeductionCount;
    }
}
