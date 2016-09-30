
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
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmpPassportDaoService;
import com.gits.rms.service.EmpPassportService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpPassportVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmpPassportAction extends ActionSupport {
    private static final long serialVersionUID = 6809430027805182519L;
    private EmpPassportService empPassService = new EmpPassportDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmpPassportVO> empPassList;
    private List<EmployeesVO> adminRoleId;
    private EmpPassportVO empPass;

    private String empPassIssueDate = "";
    private String empPassExpireDate = "";
    private String empPassReviewDate = "";
    private String empPassModifiedDate = "";
    private RoleVO role;
    private RoleService roleSerivce = new RoleDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();

    // To get List of EmpPassport
    @SkipValidation
    public String getAllEmpPassport() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		empPassList = empPassService.getAllSubEmployeePasportList(employeeReportToList);
    	}else{
    		empPassList = empPassService.getAllEmpPassport();
    	}
    	
        for (int i = 0; i < empPassList.size(); i++) {
            EmpPassportVO empPass = empPassList.get(i);
            if (empPass.getEpPassportTypeFlg().equals("0")) {
                empPass.setEpPassportTypeFlg("Passport");
            } else {
                empPass.setEpPassportTypeFlg("Visa");
            }
        }
        return SUCCESS;
    }

    // To View the Employee Passport Search Form
    @SkipValidation
    public String passportSearchForm() {
        return SUCCESS;
    }

    // To Search Employee Passport
    @SkipValidation
    public String passportSearchResult() {
        empPassList = empPassService.passportSearchResult(empPass);
        for (int i = 0; i < empPassList.size(); i++) {
            EmpPassportVO empPass = empPassList.get(i);
            if (empPass.getEpPassportTypeFlg().equals("0")) {
                empPass.setEpPassportTypeFlg("Passport");
            } else {
                empPass.setEpPassportTypeFlg("Visa");
            }
        }
        if (empPass.getMessage() != null) {
            if (empPass.getMessage().contains(getText("label.common.search.messageSetPassIssueDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(empPass.getEpPassportIssueDate()) + "."));
            }
        }
        if (empPass.getMessage() != null) {
            if (empPass.getMessage().contains(getText("label.common.search.messageSetPassExpireDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(empPass.getEpPassportExpireDate()) + "."));
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmpPassport it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmpPassport() {
        if ((empPass != null) && (empPass.getHcmoEmpPassportId() != null)) {
            empPass = empPassService.getEmpPassport(empPass.getHcmoEmpPassportId());
        }
        return SUCCESS;
    }

    // To get Particular EmpPassport Data
    @SkipValidation
    public String empPassportView() {
        if ((empPass != null) && (empPass.getHcmoEmpPassportId() != null)) {
            empPass = empPassService.getEmpPassport(empPass.getHcmoEmpPassportId());
            if (empPass.getEpPassportTypeFlg().equals("0")) {
                empPass.setEpPassportTypeFlg("Passport");
            } else {
                empPass.setEpPassportTypeFlg("Visa");
            }
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EmpPassport detail
    // or update particular EmpPassport Data
    public String insertOrUpdateEmpPassport() {
        try {
            if ((!validationPIDSuccessful()) || (!validationPEDSuccessfull())
                || (!validationPL9RDSuccess())) {
                return INPUT;
            } else {
                if (empPass.getHcmoEmpPassportId() == null) {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    empPass.setCreated(DateUtils.getCurrentDateTime());
                    empPass.setCreatedBy(oEmp);
                    empPass.setUpdatedBy(oEmp);
                    empPass.setIsActive(1);
                    if (empPass.getEpPassportTypeFlg() == null) {
                        addActionError(getText("Please Select a Passport/Visa Type"));
                        return INPUT;
                    }
                    empPassService.insertEmpPassport(empPass);

                    empPass = empPassService.getEmpPassport(empPass.getHcmoEmpPassportId());
                    role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleSerivce.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = empPass.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.employeePassport.add");

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleSerivce.getAllAdmin(role.getHcmoRoleId());
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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.add.addedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.add.addedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.add.addedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.add.addedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Added Successfully"));
                } else {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    empPass.setUpdatedBy(oEmp);
                    empPassService.updateEmpPassport(empPass);

                    empPass = empPassService.getEmpPassport(empPass.getHcmoEmpPassportId());
                    role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
                    role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleSerivce.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = empPass.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.employeePassport.edit");

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleSerivce.getAllAdmin(role.getHcmoRoleId());
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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.edit.updatedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.edit.updatedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.edit.updatedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.edit.updatedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Updated Successfully"));
                }
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    // Validation method for EmpPassport IssueDate
    private boolean validationPIDSuccessful() {
        if (DateUtils.isLesserDate(empPass.getEpPassportIssueDate())) {
            return true;
        } else {
            addActionError(getText("errors.empPassport.PassportIssueDate.invalid"));
            return false;
        }
    }

    // Validation method for EmpPassport Expire Date
    private boolean validationPEDSuccessfull() {
        if (DateUtils.isGreaterDate(empPass.getEpPassportExpireDate())) {
            return true;
        } else {
            addActionError(getText("errors.empPassport.PassportExpireDate.invalid"));
            return false;
        }
    }

    // Validation method for EmpPassport L9ReviewDate
    private boolean validationPL9RDSuccess() {
        if (empPass.getEpL9ReviewDate() == null) {
            return true;
        } else {
            if (DateUtils.isGreaterDate(empPass.getEpL9ReviewDate())) {
                return true;
            } else {
                addActionError(getText("errors.empPassport.epL9ReviewDate.invalid"));
                return false;
            }
        }
    }

    // To delete particular EmpPassport detail
    @SkipValidation
    public String deleteEmpPassport() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empPass.setUpdatedBy(oEmp);
        empPassService.deleteEmpPassport(empPass);

        empPass = empPassService.getEmpPassport(empPass.getHcmoEmpPassportId());
        role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleSerivce.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = empPass.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.employeePassport.delete");

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleSerivce.getAllAdmin(role.getHcmoRoleId());
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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.delete.deletedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.delete.deletedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeePassport.delete.deletedToAdmin"), empPass.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeePassport.delete.deletedBy"), empPass.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(empPass.getEmpIdObj().getEmployeeId(), empPass.getEmpIdObj().getEmpFirstName(), getText("employeePassport.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

        Date empPassportIssueDate = empPass.getEpPassportIssueDate();
        empPassIssueDate = dateFormat.format(empPassportIssueDate);
        Date empPassportExpireDate = empPass.getEpPassportExpireDate();
        empPassExpireDate = dateFormat.format(empPassportExpireDate);
        if (empPass.getEpL9ReviewDate() != null) {
            Date empPassportReviewDate = empPass.getEpL9ReviewDate();
            empPassReviewDate = dateFormat.format(empPassportReviewDate);
        }

        Date empPassportModdate = empPass.getUpdated();
        empPassModifiedDate = updatedFormat.format(empPassportModdate);
        if (empPass.getEpPassportTypeFlg().equals("0")) {
            empPass.setEpPassportTypeFlg("Passport");
        } else {
            empPass.setEpPassportTypeFlg("Visa");
        }
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
            + getText("label.header.empPassport.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportNum")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEpPassportNo()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportIssueDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPassIssueDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportExpireDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPassExpireDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.passportOrVisa")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEpPassportTypeFlg()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empPass.getEpComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epComments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPass.getEpComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empPass.getEpL9Status().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epL9Status")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPass.getEpL9Status()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empPass.getEpL9ReviewDate() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epL9ReviewDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPassReviewDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.countryName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getCountry().getCountryName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empPassModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public void mailToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

        Date empPassportIssueDate = empPass.getEpPassportIssueDate();
        empPassIssueDate = dateFormat.format(empPassportIssueDate);
        Date empPassportExpireDate = empPass.getEpPassportExpireDate();
        empPassExpireDate = dateFormat.format(empPassportExpireDate);
        if (empPass.getEpL9ReviewDate() != null) {
            Date empPassportReviewDate = empPass.getEpL9ReviewDate();
            empPassReviewDate = dateFormat.format(empPassportReviewDate);
        }
        Date empPassportModdate = empPass.getUpdated();
        empPassModifiedDate = updatedFormat.format(empPassportModdate);

        if (empPass.getEpPassportTypeFlg().equals("0")) {
            empPass.setEpPassportTypeFlg("Passport");
        } else {
            empPass.setEpPassportTypeFlg("Visa");
        }

        String sDummy = Constants.PERSON;
        String sFirstPerson = Constants.EMPLOYEE_PERSON;
        String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
        StringBuilder sMessage = new StringBuilder();

        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));

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
            + getText("label.header.empPassport.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportNum")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEpPassportNo()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportIssueDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPassIssueDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.PassportExpireDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPassExpireDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.passportOrVisa")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getEpPassportTypeFlg()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empPass.getEpComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epComments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPass.getEpComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empPass.getEpL9Status().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epL9Status")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPass.getEpL9Status()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empPass.getEpL9ReviewDate() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empPassport.epL9ReviewDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empPassReviewDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empPassport.countryName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empPass.getCountry().getCountryName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empPassModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public List<EmpPassportVO> getEmpPassList() {
        return empPassList;
    }

    public void setEmpPassList(List<EmpPassportVO> empPassList) {
        this.empPassList = empPassList;
    }

    public EmpPassportVO getEmpPass() {
        return empPass;
    }

    public void setEmpPass(EmpPassportVO empPass) {
        this.empPass = empPass;
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
