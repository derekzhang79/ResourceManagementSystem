
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmployeeReportToAction extends ActionSupport {
    private static final long serialVersionUID = -3380471965643629150L;
    private EmployeeReportToService empService = new EmployeeReportToDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private EmployeeReportToVO emp;
    private RoleService roleService = new RoleDaoService();
    private List<EmployeeReportToVO> empList;
    private List<EmployeeReportToVO> empListForPreSupervisor;
    private RoleVO role;
    private List<EmployeesVO> empIdObj;
    private List<EmployeesVO> adminRoleId;
    private String emprepModifiedDate = "";
    Map session = ActionContext.getContext().getSession();
	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

    // To get All Type of EmployeeReportTo List
    @SkipValidation
    public String getAllEmployeeReportTo() {
    	
    	
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		empList = empService.getAllSubEmployeeReportToList(employeeReportToList);
    		
    	}else{
    		empList = empService.getAllEmployeeReportTo(oEmp.getClientId());
    	}
    	
        for (Iterator<EmployeeReportToVO> it = empList.iterator(); it.hasNext();) {
            setreportingMode(it.next());
        }
        return SUCCESS;
    }

    // To View Search Page
    @SkipValidation
    public String empReportToSearcForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String empReportToSearcResult() {
        empList = empService.empReportToSearchResult(emp);
        for (Iterator<EmployeeReportToVO> it = empList.iterator(); it.hasNext();) {
            setreportingMode(it.next());
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmployeeReportTo it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmployeeReportTo() {
        if ((emp != null) && (emp.getEmpReportToId() != null)) {
            emp = empService.getEmployeeReportTo(emp.getEmpReportToId());
        }
        return SUCCESS;
    }

    // To get Particular EmployeeReportTo Data
    @SkipValidation
    public String employeeReportToView() {
        if ((emp != null) && (emp.getEmpReportToId() != null)) {
            emp = empService.getEmployeeReportTo(emp.getEmpReportToId());
            setreportingMode(emp);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EmployeeReportTo
    // or update particular EmployeeReportTo Data
    public String insertOrUpdateEmployeeReportTo() {
        if (!validationSelfRep()) {
            return INPUT;
        } else {
            if (emp.getEmpReportToId() == null) {
                if (!validationRepToExists()) {
                    return INPUT;
                } else {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    emp.setCreated(DateUtils.getCurrentDateTime());
                    emp.setCreatedBy(oEmp);
                    emp.setUpdatedBy(oEmp);
                    emp.setIsActive(1);
                    empList = empService.getempRToInvert(emp);
                    if (!(empList.isEmpty())) {
                        EmployeeReportToVO newEmployeeReportToInvertObj;
                        for (Iterator<EmployeeReportToVO> itInv = empList.iterator(); itInv.hasNext();) {
                            newEmployeeReportToInvertObj = itInv.next();
                            String newEmpRepToInvertForEmpName = newEmployeeReportToInvertObj.getEmpIdObj().getEmpFirstName();
                            String newEmpRepToInvertForSupName = newEmployeeReportToInvertObj.getSupEmpNumber().getEmpFirstName();
                            addActionError(newEmpRepToInvertForSupName
                                + " is already the supervisor for " + newEmpRepToInvertForEmpName);
                            return INPUT;
                        }
                    }

                    empService.insertEmployeeReportTo(emp);

                    emp = empService.getEmployeeReportTo(emp.getEmpReportToId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = emp.getEmpIdObj().getEmployeeId();
                    int SupID = emp.getSupEmpNumber().getEmployeeId();
                    String sSubject = getText("message.subject.employeeReportTo.add");

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();

                    Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                        newAdminEmp = it.next();
                        int adminEmpID = newAdminEmp.getEmployeeId();

                        // logged in person is admin
                        if (adminEmpID == sessionEmpId) {
                            // the logged-in person is admin and he is entering
                            // his own information
                            if (adminEmpID == employeeID) {
                                // the mail content to Other admin Employees
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to the login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // the mail content to the Supervisor
                                mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                            // the logged-in person is admin and he is as
                            // Supervisor
                            else if (adminEmpID == SupID) {
                                // the mail content to Other admin Employees
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to the login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // Mail to the employee
                                mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if ((adminEmpID != employeeID) && (adminEmpID != SupID)) {
                                // the mail content to Other admin Employees
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to the login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                                // Mail to the employee
                                mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                // the mail content to the Supervisor
                                mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that particular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                // the mail content to the Supervisor
                                mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                            // logged in person as Supervisor
                            else if (sessionEmpId == SupID) {
                                // mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // the mail content to the logged in person
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                // Mail to the employee
                                mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                            // if this employee is entering information for some
                            // other employee
                            else if ((sessionEmpId != employeeID) && (sessionEmpId != SupID)) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.add.addedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.add.addedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail - to the person whom he is adding
                                // the info
                                mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                // the mail content to the Supervisor
                                mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Added Successfully"));
                }
            } else {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                emp.setUpdatedBy(oEmp);
                if (emp.getEmpRepReportingMode() == 0) {
                } else if (emp.getEmpRepReportingMode() == 1) {
                }
                empListForPreSupervisor = empService.getEmployeeAllPreSupervisor(emp.getEmpIdObj().getEmployeeId());
                empList = empService.getempRToInvert(emp);
                if (!(empList.isEmpty())) {
                    EmployeeReportToVO newEmployeeReportToInvertObj;
                    for (Iterator<EmployeeReportToVO> itInv = empList.iterator(); itInv.hasNext();) {
                        newEmployeeReportToInvertObj = itInv.next();
                        String newEmpRepToInvertForEmpName = newEmployeeReportToInvertObj.getEmpIdObj().getEmpFirstName();
                        String newEmpRepToInvertForSupName = newEmployeeReportToInvertObj.getSupEmpNumber().getEmpFirstName();
                        addActionError(newEmpRepToInvertForSupName
                            + " is already the supervisor for " + newEmpRepToInvertForEmpName);
                        return INPUT;
                    }
                }
                empService.updateEmployeeReportTo(emp);

                emp = empService.getEmployeeReportTo(emp.getEmpReportToId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = emp.getEmpIdObj().getEmployeeId();
                int SupID = emp.getSupEmpNumber().getEmployeeId();
                String sSubject = getText("message.subject.employeeReportTo.edit");

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();

                Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                    newAdminEmp = it.next();
                    int adminEmpID = newAdminEmp.getEmployeeId();

                    // logged in person is admin
                    if (adminEmpID == sessionEmpId) {
                        // the logged-in person is admin and he is entering his
                        // own information
                        if (adminEmpID == employeeID) {
                            // the mail content to Other admin Employees
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to the login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // the mail content to the Supervisor
                            mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                        // the logged-in person is admin and he is as Supervisor
                        else if (adminEmpID == SupID) {
                            // the mail content to Other admin Employees
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to the login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // Mail to the employee
                            mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if ((adminEmpID != employeeID) && (adminEmpID != SupID)) {
                            // the mail content to Other admin Employees
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to the login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                            // Mail to the employee
                            mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // the mail content to the Supervisor
                            mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that particular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            // the mail content to the Supervisor
                            mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                        // logged in person as Supervisor
                        else if (sessionEmpId == SupID) {
                            // mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // the mail content to the logged in person
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            // Mail to the employee
                            mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                        // if this employee is entering information for some
                        // other employee
                        else if ((sessionEmpId != employeeID) && (sessionEmpId != SupID)) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.edit.updatedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail - to the person whom he is adding the
                            // info
                            mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // the mail content to the Supervisor
                            mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }

                addActionMessage(getText("Updated Successfully"));
            }
        }
        return SUCCESS;
    }

    // To delete particular EmployeeReportTo
    @SkipValidation
    public String deleteEmployeeReportTo() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        emp.setUpdatedBy(oEmp);
        empService.deleteEmployeeReportTo(emp);

        emp = empService.getEmployeeReportTo(emp.getEmpReportToId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = emp.getEmpIdObj().getEmployeeId();
        int SupID = emp.getSupEmpNumber().getEmployeeId();

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();
        String sSubject = getText("message.subject.employeeReportTo.delete");

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // logged in person is admin
            if (adminEmpID == sessionEmpId) {
                // the logged-in person is admin and he is entering his own
                // information
                if (adminEmpID == employeeID) {
                    // the mail content to Other admin Employees
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to the login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // the mail content to the Supervisor
                    mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
                // the logged-in person is admin and he is as Supervisor
                else if (adminEmpID == SupID) {
                    // the mail content to Other admin Employees
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to the login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // Mail to the employee
                    mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
                // logged in as admin and he is entering for another employee
                else if ((adminEmpID != employeeID) && (adminEmpID != SupID)) {
                    // the mail content to Other admin Employees
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to the login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                    // Mail to the employee
                    mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // the mail content to the Supervisor
                    mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that particular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // the mail content to the Supervisor
                    mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
                // logged in person as Supervisor
                else if (sessionEmpId == SupID) {
                    // mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // the mail content to the logged in person
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // Mail to the employee
                    mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
                // if this employee is entering information for some other
                // employee
                else if ((sessionEmpId != employeeID) && (sessionEmpId != SupID)) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedToAdmin"), emp.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeReportTo.delete.deletedBy"), emp.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail - to the person whom he is adding the info
                    mail(emp.getEmpIdObj().getEmployeeId(), emp.getEmpIdObj().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // the mail content to the Supervisor
                    mail(emp.getSupEmpNumber().getEmployeeId(), emp.getSupEmpNumber().getEmpFirstName(), getText("employeeReportTo.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    private boolean validationSelfRep() {
        if (emp.getEmpIdObj().getEmployeeId().equals(emp.getSupEmpNumber().getEmployeeId())) {
            addActionError(getText("errors.employeeReportTo.selfReportTo"));
            return false;
        } else {
            return true;
        }
    }
    

    private boolean validationRepToExists() {
        EmployeeReportToVO newEmpReportTo;
        empList = empService.getAllEmployeeReportTo(oEmp.getClientId());
        for (Iterator<EmployeeReportToVO> it = empList.iterator(); it.hasNext();) {
            newEmpReportTo = it.next();
            if (emp.getEmpIdObj().getEmployeeId().equals(newEmpReportTo.getEmpIdObj().getEmployeeId())) {
                addActionError(getText("errors.employeeReportTo.ReportToExists"));
                return false;
            }
        }
        return true;
    }

    // Convert Int to String for Reporting Mode Field
    public void setreportingMode(EmployeeReportToVO rep) {
        if (rep.getEmpRepReportingMode() == -1) {
            rep.setEmpRepReportingModeValue(rep.getEmpRepReportingMode() == -1 ? getText("label.reportTo.length.value.empty")
                : getText("label.reportTo.length.value.empty"));
        } else {
            rep.setEmpRepReportingModeValue(rep.getEmpRepReportingMode() == 0 ? getText("label.reportTo.length.value.direct")
                : getText("label.reportTo.length.value.inDirect"));
        }
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            setreportingMode(emp);
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date employeereportModdate = emp.getUpdated();
            emprepModifiedDate = updatedDateFormat.format(employeereportModdate);

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
                + getText("label.header.employeeReportTo.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + emp.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employeeReportTo.Supervisor")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + emp.getSupEmpNumber().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            if (emp.getEmpRepReportingModeValue().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employeeReportTo.ReportingMode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + emp.getEmpRepReportingModeValue() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + emprepModifiedDate
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
            setreportingMode(emp);
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date employeereportModdate = emp.getUpdated();
            emprepModifiedDate = updatedDateFormat.format(employeereportModdate);

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
                + getText("label.header.employeeReportTo.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + emp.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.employeeReportTo.Supervisor")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + emp.getSupEmpNumber().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (emp.getEmpRepReportingModeValue().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.employeeReportTo.ReportingMode")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + emp.getEmpRepReportingModeValue() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + emprepModifiedDate
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

    public void mailToOldSupervisor(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            setreportingMode(emp);
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date employeereportModdate = emp.getUpdated();
            emprepModifiedDate = updatedDateFormat.format(employeereportModdate);

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
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG)

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

    public EmployeeReportToVO getEmp() {
        return emp;
    }

    public void setEmp(EmployeeReportToVO emp) {
        this.emp = emp;
    }

    public List<EmployeeReportToVO> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmployeeReportToVO> empList) {
        this.empList = empList;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
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