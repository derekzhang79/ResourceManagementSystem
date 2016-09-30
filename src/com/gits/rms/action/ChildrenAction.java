
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.ChildrenDaoService;
import com.gits.rms.service.ChildrenService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ChildrenVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class ChildrenAction extends ActionSupport {
    private static final long serialVersionUID = -6147778930069184743L;
    private ChildrenService childService = new ChildrenDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private RoleService roleService = new RoleDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<ChildrenVO> childList;
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private ChildrenVO child;
    private List<EmployeesVO> adminRoleId;
    private RoleVO role;
    private String childBDateMail = "";
    private String childModifiedDateMail = "";

    // To get All Children
    @SkipValidation
    public String getAllChildren() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		childList = childService.getAllSubEmployeeChildrenList(employeeReportToList);
    	}else{
    		childList = childService.getAllChildren();
    	}
    	
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String childrenSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String childrenSearchResult() {
        childList = childService.childrenSearchResult(child);
        return SUCCESS;
    }

    // Based on EmployeeId get All Children of that Employee
    @SkipValidation
    public String getEmployeeAllChildren() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("child.empIdObj.employeeId"));
        childList = childService.getEmployeeAllChildren(employeeId);
        return SUCCESS;
    }

    // To get Particular Children Data
    @SkipValidation
    public String childrenView() {
        if ((child != null) && (child.getEmpChildrenId() != null)) {
            child = childService.getChildren(child.getEmpChildrenId());
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Children it shows blank Form to enter New Data
    @SkipValidation
    public String setUpChildren() {
        if ((child != null) && (child.getEmpChildrenId() != null)) {
            child = childService.getChildren(child.getEmpChildrenId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new children for that Employee or Update data
    // of the Children
    @SkipValidation
    public String setUpEmpChildren() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("child.empIdObj.employeeId"));
        if ((child != null) && (child.getEmpChildrenId() != null) && (employeeId != null)) {
            child = childService.getChildren(child.getEmpChildrenId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new children for that Employee or Update data
    // of the Children
    @SkipValidation
    public String setUpEmpChildrenSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("child.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("child.empChildrenId"));
        if ((child != null) && (child.getEmpChildrenId() != null) && (employeeId != null)) {
            child = childService.getEmpChildren(child);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Children or update
    // particular Children Data
    public String insertOrUpdateChildren() {
        try {
            if (!validationSuccessfull()) {
                return "input";
            } else {
                if (child.getEmpChildrenId() == null) {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    child.setCreated(DateUtils.getCurrentDateTime());
                    child.setCreatedBy(oEmp);
                    child.setUpdatedBy(oEmp);
                    child.setIsActive(1);
                    childService.insertChildren(child);

                    child = childService.getChildren(child.getEmpChildrenId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = child.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.children.add");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.add.addedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.add.addedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.add.addedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.add.addedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Added Successfully"));
                } else {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    child.setUpdatedBy(oEmp);
                    childService.updateChildren(child);

                    child = childService.getChildren(child.getEmpChildrenId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = child.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.children.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.edit.updatedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.edit.updatedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.edit.updatedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.edit.updatedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Updated Sucessfully"));
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getErrorMoreFields(e);
            addActionError(sError);
            e.printStackTrace();
            throw e;
        }
        return SUCCESS;
    }

    // Validation method for children dateOfBirth
    private boolean validationSuccessfull() {
        if (child.getChildDOB() == null) {
            return true;
        } else {
            if (DateUtils.isLesserDate(child.getChildDOB())) {
                return true;
            } else {
                addActionError(getText("errors.common.dob.invalid"));
                return false;
            }
        }
    }

    // To delete particular Children data of an employee
    @SkipValidation
    public String deleteChildren() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        child.setUpdatedBy(oEmp);
        childService.deleteChildren(child);

        child = childService.getChildren(child.getEmpChildrenId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = child.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.children.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.delete.deletedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.delete.deletedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("children.delete.deletedToAdmin"), child.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("children.delete.deletedBy"), child.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(child.getEmpIdObj().getEmployeeId(), child.getEmpIdObj().getEmpFirstName(), getText("children.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage(getText("Deleted Sucessfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            if (child.getChildDOB() != null) {
                Date chiBirthDate = child.getChildDOB();
                childBDateMail = DateFormat.format(chiBirthDate);
            }
            Date childModdate = child.getUpdated();
            childModifiedDateMail = UpdatedDateFormat.format(childModdate);

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
                + getText("label.header.children.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + child.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.children.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + child.getChildName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (child.getChildDOB() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.common.dob") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + childBDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + childModifiedDateMail
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

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            if (child.getChildDOB() != null) {
                Date chiBirthDate = child.getChildDOB();
                childBDateMail = DateFormat.format(chiBirthDate);
            }
            Date childModdate = child.getUpdated();
            childModifiedDateMail = UpdatedDateFormat.format(childModdate);

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
                + getText("label.header.children.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + child.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.children.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + child.getChildName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (child.getChildDOB() != null) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.common.dob") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + childBDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + childModifiedDateMail
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

    public List<ChildrenVO> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildrenVO> childList) {
        this.childList = childList;
    }

    public ChildrenVO getChild() {
        return child;
    }

    public void setChild(ChildrenVO child) {
        this.child = child;
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
