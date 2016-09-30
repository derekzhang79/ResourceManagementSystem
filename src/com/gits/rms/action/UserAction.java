
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.UserDaoService;
import com.gits.rms.service.UserService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.UserVO;

public class UserAction extends ActionSupport {
    private static final long serialVersionUID = 2820453152886062016L;
    private UserService userService = new UserDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private RoleService roleService = new RoleDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private UserVO user;
    private RoleVO role;
    private List<UserVO> userList;
    private List<EmployeesVO> adminRoleId;
    private String userModifiedDateMail = "";

    // To get List of User
    @SkipValidation
    public String getAllUser() {
        userList = userService.getAllUser();
        return SUCCESS;
    }

    // To View the User Form
    @SkipValidation
    public String userSearchForm() {
        return SUCCESS;
    }

    // To Search User
    @SkipValidation
    public String userSearchResult() {
        userList = userService.userSearchResult(user);
        return SUCCESS;
    }

    // Based on EmployeeId get All User of that Employee
    @SkipValidation
    public String getEmployeeAllUser() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("user.empIdObj.employeeId"));
        userList = userService.getEmployeeAllUser(employeeId);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add User
    // it shows blank Form to enter New Data
    @SkipValidation
    public String setUpUser() {
        if ((user != null) && (user.getHcmouserId() != null)) {
            user = userService.getUser(user.getHcmouserId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new User for that Employee or Update data of
    // the User
    @SkipValidation
    public String setUpEmpUser() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("user.empIdObj.employeeId"));
        if ((user != null) && (user.getHcmouserId() != null) && (employeeId != null)) {
            user = userService.getUser(user.getHcmouserId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new User for that Employee or Update data of
    // the User
    @SkipValidation
    public String setUpEmpUserSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("user.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("user.hcmouserId"));
        if ((user != null) && (user.getHcmouserId() != null) && (employeeId != null)) {
            user = userService.getEmpUser(user);
        }
        return SUCCESS;
    }

    // To get Particular User Data
    @SkipValidation
    public String userView() {
        if ((user != null) && (user.getHcmouserId() != null)) {
            user = userService.getUser(user.getHcmouserId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new User or update
    // particular User Data
    public String insertOrUpdateUser() {
        try {
            if (user.getHcmouserId() == null) {
                EmployeesVO newAdminEmp = null;
                if ((hasSpace(user.getPassword()) == false)
                    && (hasSpace(user.getUserName()) == false)) {
                    userList = userService.getEmployeeAllUser(user.getEmpIdObj().getEmployeeId());
                    if (userList.size() == 1) {
                        addActionError(getText("errors.user.restriction"));
                        return INPUT;
                    } else {
                        Map session = ActionContext.getContext().getSession();
                        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                        user.setCreated(DateUtils.getCurrentDateTime());
                        user.setCreatedBy(oEmp);
                        user.setUpdatedBy(oEmp);
                        user.setIsActive(1);
                        userService.insertUser(user);

                        user = userService.getUser(user.getHcmouserId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = user.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.user.add");

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
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.add.addedToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.add.addedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.add.addedToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.add.addedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                }

                            }
                        }
                        addActionMessage(getText("Added Successfully"));
                    }
                } else {
                    addActionError(getText("label.header.user.hasSpace"));
                    return INPUT;
                }
            } else {
                EmployeesVO newAdminEmp = null;
                if ((hasSpace(user.getPassword()) == false)
                    && (hasSpace(user.getUserName()) == false)) {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    user.setUpdatedBy(oEmp);
                    userService.updateUser(user);

                    user = userService.getUser(user.getHcmouserId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = user.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.user.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.edit.updateToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);

                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.edit.updatedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.edit.updateToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.edit.updatedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }

                        }
                    }
                    // To set Username and User Object in Session if the Logged
                    // in Employee edited his own information
                    if (user.getEmpIdObj().getEmployeeId().equals(oEmp.getEmployeeId())) {
                        session.put("USER_NAME", user.getUserName());
                        session.put("USER_OBJECT", user);
                    }
                    addActionMessage(getText("Updated Successfully"));
                } else {
                    addActionError(getText("label.header.user.hasSpace"));
                    return INPUT;
                }
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
        return SUCCESS;
    }

    // To delete particular User Detail
    @SkipValidation
    public String deleteUser() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        user.setUpdatedBy(oEmp);
        userService.deleteUser(user);

        user = userService.getUser(user.getHcmouserId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = user.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.user.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.delete.deleteToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.delete.deletedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("user.delete.deleteToAdmin"), user.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("user.delete.deletedBy"), user.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(user.getEmpIdObj().getEmployeeId(), user.getEmpIdObj().getEmpFirstName(), getText("user.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }

            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    // To Check the UserName and Password fields have any space
    @SkipValidation
    private boolean hasSpace(String checkHasSpace) {
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(checkHasSpace);
        boolean hasSpace = matcher.find();
        return hasSpace;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {

        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date userModdate = user.getUpdated();
            userModifiedDateMail = updateddateformat.format(userModdate);

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
                + getText("label.header.user.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + user.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.user.userName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + user.getUserName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (DearEmp.equals(user.getEmpIdObj().getEmpFirstName())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.user.password") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + user.getPassword() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            } else {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.user.password") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + getText("label.header.user.password.star")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + userModifiedDateMail
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
            Date userModdate = user.getUpdated();
            userModifiedDateMail = updateddateformat.format(userModdate);

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
                + getText("label.header.user.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + user.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.user.userName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + user.getUserName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (DearEmp.equals(user.getEmpIdObj().getEmpFirstName())) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.user.password") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + user.getPassword() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            } else {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.user.password") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + getText("label.header.user.password.star")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + userModifiedDateMail
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

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public List<UserVO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserVO> userList) {
        this.userList = userList;
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