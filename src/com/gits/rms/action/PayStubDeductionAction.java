
package com.gits.rms.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
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

public class PayStubDeductionAction extends ActionSupport {
    private static final long serialVersionUID = -4804641390388708877L;
    private PayStubDeductionService payStubDeductionService = new PayStubDeductionDaoService();
    private PayStubArchiveService payStubArchiveService = new PayStubArchiveDaoService();
    private PayStubService payStubService = new PayStubDaoService();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<PayStubDeductionsVO> payStubDeductionList;
    private List<PayStubVO> payStubList;
    private PayStubDeductionsVO payStubDeduction;
    private PayStubVO payStub;
    private int paystubDeductionCount;
    private RoleVO role;
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private String payStubDeclareDateMail = "";
    private String payStubModifiedDateMail = "";
    private String payStubDedEffectiveDateMail = "";
    private String payStubDedModifiedDateMail = "";
    PayStubDeductionsVO newPayStub = new PayStubDeductionsVO();

    // To get List of All PaystubDeduction for a particular paystubs
    @SkipValidation
    public String getAllEmployeePayStubsDeduction() {
        payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
        paystubDeductionCount = payStubDeductionList.size();

        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // payStubDeduction it shows blank Form to enter New Data
    @SkipValidation
    public String setUpPayStubDeduction() {
        if ((payStubDeduction != null) && (payStubDeduction.getPayStubDeductionId() != null)) {
            payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());

            payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());

            payStubDeductionList = payStubDeductionService.getAllEmpPayStubsDedWithOutSelectedDeduction(payStubDeduction.getPayStubDeductionId(), payStubDeduction.getPayStub().getPayStubId());
            paystubDeductionCount = payStubDeductionList.size();

        }
        return SUCCESS;
    }

    // To get Particular payStubDeduction of an paystub
    @SkipValidation
    public String payStubDeductionView() {
        if ((payStubDeduction != null) && (payStubDeduction.getPayStubDeductionId() != null)) {
            payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
        }
        return SUCCESS;
    }

    public void validation() {
        payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStub.getPayStubId());
        paystubDeductionCount = payStubDeductionList.size();
    }

    // To insert new payStubDeduction detail or Edit Particular payStubDeduction
    // Detail
    public String insertOrUpdatePayStubDeduction() {
        try {
            if (payStubDeduction.getDeduction().getDeductionId() == null) {
                addActionError("Deduction is a required field");
                validation();
                return ERROR;
            } else if (payStubDeduction.getDeductionAmount() == null) {
                addActionError("Deduction Amount is a required field");
                validation();
                return ERROR;
            } else if (payStubDeduction.getEffectiveDate() == null) {
                addActionError("Deduction Amount is a required field");
                validation();
                return ERROR;
            }

            if (payStubDeduction.getPayStubDeductionId() == null) {

                if (payStubDeduction.getDeduction().getDeductionMode().equals("Percentage")) {
                    if (payStubDeduction.getDeductionAmount() > 100) {
                        validation();
                        addActionError("Percentage cannot be entered more than 100");
                        return ERROR;
                    }
                }
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                List<PayStubDeductionsVO> newPayStubDeductionList = new LinkedList<PayStubDeductionsVO>();
                PayStubDeductionsVO newPaystubObj;
                Calendar.getInstance();

                payStub = payStubService.getPayStub(payStub.getPayStubId());
                double grossSalary = payStub.getGrossSalary();

                payStubDeduction.setPayStub(payStub);
                payStubDeduction.setCreated(DateUtils.getCurrentDateTime());
                payStubDeduction.setCreatedBy(oEmp);
                payStubDeduction.setUpdatedBy(oEmp);
                payStubDeduction.setIsActive(1);
                payStubDeductionService.insertPayStubDeduction(payStubDeduction);

                payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = payStubDeduction.getPayStub().getEmployee().getEmployeeId();
                String sSubject = getText("message.subject.payStubDeduction.add");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.add.addedToAdmin"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(payStubDeduction.getPayStub().getEmployee().getEmployeeId(), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), getText("payStubDeduction.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.add.addedBy"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.add.addedToAdmin"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.add.addedBy"), payStub.getEmployee().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(payStubDeduction.getPayStub().getEmployee().getEmployeeId(), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), getText("payStubDeduction.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }

                // checking the effective date is todays date or lesser than
                // todays date
                if (DateUtils.isEqual(payStubDeduction.getEffectiveDate())
                    || (DateUtils.isLesserDate(payStubDeduction.getEffectiveDate()))) {

                    // changing the net salary besed on the entered deduction
                    payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStubDeduction.getPayStub().getPayStubId());

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

                        for (Iterator<PayStubDeductionsVO> iterate = payStubDeductionList.iterator(); iterate.hasNext();) {
                            newPaystubObj = iterate.next();

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

                        addActionMessage(getText("Added Successfully. NetSalary has been updated based on the entered Deduction"));
                    }
                } else {
                    addActionMessage(getText("Added Successfully. NetSalary will be updated in the future based on the  entered Effective date"));
                }

            } else if (payStubDeduction.getPayStubDeductionId() != null) {

                if (payStubDeduction.getDeduction().getDeductionMode().equals("Percentage")) {
                    if (payStubDeduction.getDeductionAmount() > 100) {
                        validation();
                        addActionError("Percentage cannot be entered more than 100");
                        return ERROR;
                    }
                }
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                List<PayStubDeductionsVO> newPayStubDeductionList = new LinkedList<PayStubDeductionsVO>();
                PayStubDeductionsVO newPaystubObj;

                // inserting the paystub and its deduction in the archive table
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

                // update the paystub deduction
                payStubDeduction.setUpdatedBy(oEmp);
                payStubDeductionService.updatePayStubDeduction(payStubDeduction);

                payStub = payStubService.getPayStub(payStub.getPayStubId());
                payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
                double grossSalary = payStub.getGrossSalary();

                payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
                payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(payStubDeduction.getPayStub().getPayStubId());
                paystubDeductionCount = payStubDeductionList.size();

                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = payStubDeduction.getPayStub().getEmployee().getEmployeeId();
                String sSubject = getText("message.subject.payStubDeduction.edit");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedToAdmin"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(payStubDeduction.getPayStub().getEmployee().getEmployeeId(), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), getText("payStubDeduction.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedBy"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedToAdmin"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.edit.updatedBy"), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(payStubDeduction.getPayStub().getEmployee().getEmployeeId(), payStubDeduction.getPayStub().getEmployee().getEmpFirstName(), getText("payStubDeduction.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }

                addActionMessage(getText("Updated Successfully"));

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

                    for (Iterator<PayStubDeductionsVO> iterate = payStubDeductionList.iterator(); iterate.hasNext();) {
                        newPaystubObj = iterate.next();

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
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
        return SUCCESS;
    }

    // To delete particular payStubDeduction data
    @SkipValidation
    public String deletePayStubDeduction() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO newAdminEmp = null;
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        List<PayStubDeductionsVO> newPayStubDeductionList = new LinkedList<PayStubDeductionsVO>();
        PayStubDeductionsVO newPaystubObj;
        payStub = payStubService.getPayStub(payStub.getPayStubId());

        // Inserting paystub and its deduction in the archive table
        PayStubArchiveVO payStubArchive = new PayStubArchiveVO();

        Date today = new Date();
        Timestamp time = new java.sql.Timestamp(today.getTime());
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

        // Deleting paystub deduction
        newPayStub = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
        payStubDeductionService.deletePayStubDeduction(payStubDeduction);

        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeduction(newPayStub.getPayStub().getPayStubId());
        paystubDeductionCount = payStubDeductionList.size();
        addActionMessage(getText("Deleted Successfully"));

        double grossSalary = payStub.getGrossSalary();

        // changing the net salary based on the entered deduction
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

            for (Iterator<PayStubDeductionsVO> iterate = payStubDeductionList.iterator(); iterate.hasNext();) {
                newPaystubObj = iterate.next();

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
        } else {
            payStubDeductionService.updatePayStubNetSalary(payStub.getPayStubId(), payStub.getGrossSalary());
        }

        payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
        payStubDeduction = payStubDeductionService.getPayStubDeduction(newPayStub.getPayStubDeductionId());

        // Messaging Starts
        role = roleService.getRoleName(getText("message.label.common.adminName"));

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = newPayStub.getPayStub().getEmployee().getEmployeeId();
        String sSubject = getText("message.subject.payStubDeduction.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedToAdmin"), newPayStub.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(newPayStub.getPayStub().getEmployee().getEmployeeId(), payStub.getEmployee().getEmpFirstName(), getText("payStubDeduction.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedBy"), newPayStub.getPayStub().getEmployee().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedToAdmin"), newPayStub.getPayStub().getEmployee().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("payStubDeduction.delete.deletedBy"), newPayStub.getPayStub().getEmployee().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(newPayStub.getPayStub().getEmployee().getEmployeeId(), newPayStub.getPayStub().getEmployee().getEmpFirstName(), getText("payStubDeduction.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        return SUCCESS;
    }

    // To get Particular Employee payStubDeduction of an paystub
    @SkipValidation
    public String payStubDeductionEssView() {
        if ((payStubDeduction != null) && (payStubDeduction.getPayStubDeductionId() != null)) {
            payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // payStubDeduction it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEssPayStubDeduction() {
        if ((payStubDeduction != null) && (payStubDeduction.getPayStubDeductionId() != null)) {
            payStubDeduction = payStubDeductionService.getPayStubDeduction(payStubDeduction.getPayStubDeductionId());
            payStubList = payStubService.getEmployeePayStubs(payStub.getPayStubId());
            payStubDeductionList = payStubDeductionService.getAllEmpPayStubsDedWithOutSelectedDeduction(payStubDeduction.getPayStubDeductionId(), payStubDeduction.getPayStub().getPayStubId());
            paystubDeductionCount = payStubDeductionList.size();

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

            if (newPayStub.getPayStubDeductionId() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                    + getText("label.header.paystubDeduction.deletedDeduction")
                    + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.name")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionName()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.type")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionType()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.mode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionMode()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.amount")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeductionAmount() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.effDate")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getEffectiveDate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
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

            if (newPayStub.getPayStubDeductionId() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                    + getText("label.header.paystubDeduction.deletedDeduction")
                    + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.name")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionName()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.type")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionType()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.mode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeduction().getDeductionMode()
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.amount")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getDeductionAmount() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.deduction.effDate")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + newPayStub.getEffectiveDate() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

                .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
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

    public PayStubVO getPayStub() {
        return payStub;
    }

    public void setPayStub(PayStubVO payStub) {
        this.payStub = payStub;
    }

    public List<PayStubVO> getPayStubList() {
        return payStubList;
    }

    public void setPayStubList(List<PayStubVO> payStubList) {
        this.payStubList = payStubList;
    }

    public int getPaystubDeductionCount() {
        return paystubDeductionCount;
    }

    public void setPaystubDeductionCount(int paystubDeductionCount) {
        this.paystubDeductionCount = paystubDeductionCount;
    }
}
