
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
import com.gits.rms.service.EmpUSTaxDaoService;
import com.gits.rms.service.EmpUSTaxService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpUSTaxVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmpUSTaxAction extends ActionSupport {
    private static final long serialVersionUID = -9171792194670841316L;
    private EmpUSTaxService empUSTaxService = new EmpUSTaxDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmpUSTaxVO> empUSTaxList;
    private EmpUSTaxVO empUSTax;
    private List<EmployeesVO> empIdObj;
    private List<EmployeesVO> adminRoleId;
    private EmployeesVO emps;
    private String empUsTaxModifiedDate = "";
    private RoleService roleService = new RoleDaoService();
    private RoleVO role;

    // To get List of EmpUsTax
    @SkipValidation
    public String getAllEmpUSTax() {
        empUSTaxList = empUSTaxService.getAllEmpUSTax();
        return SUCCESS;
    }

    // To View the Employee USTaxSearch Form
    @SkipValidation
    public String usTaxSearchForm() {
        return SUCCESS;
    }

    // To Search Employee USTax
    @SkipValidation
    public String usTaxSearchResult() {
        empUSTaxList = empUSTaxService.usTaxSearchResult(empUSTax);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmpUsTax it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmpUSTax() {
        if ((empUSTax != null) && (empUSTax.getHcmoEmpUsTaxId() != null)) {
            empUSTax = empUSTaxService.getEmpUSTax(empUSTax.getHcmoEmpUsTaxId());
        }
        return SUCCESS;
    }

    // To get Particular EmpUSTax Data
    @SkipValidation
    public String empUSTaxView() {
        if ((empUSTax != null) && (empUSTax.getHcmoEmpUsTaxId() != null)) {
            empUSTax = empUSTaxService.getEmpUSTax(empUSTax.getHcmoEmpUsTaxId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EmpUSTax or update
    // particular EmpUSTax Data
    public String insertOrUpdateEmpUSTax() {
        if (empUSTax.getHcmoEmpUsTaxId() == null) {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            empUSTax.setCreated(DateUtils.getCurrentDateTime());
            empUSTax.setCreatedBy(oEmp);
            empUSTax.setUpdatedBy(oEmp);
            empUSTax.setIsActive(1);
            empUSTaxService.insertEmpUSTax(empUSTax);

            empUSTax = empUSTaxService.getEmpUSTax(empUSTax.getHcmoEmpUsTaxId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            // EmployeesVO adminRoleId =
            // roleService.getEmployeeId(role.getHcmoRoleId());

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = empUSTax.getHcmoEmployeeId().getEmployeeId();
            String sSubject = getText("message.subject.employeeUSTax.add");

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
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.add.addedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.add.addedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.add.addedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.add.addedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage(getText("Added Successfully"));
        } else {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            empUSTax.setUpdatedBy(oEmp);
            empUSTaxService.updateEmpUSTax(empUSTax);

            empUSTax = empUSTaxService.getEmpUSTax(empUSTax.getHcmoEmpUsTaxId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            // EmployeesVO adminRoleId =
            // roleService.getEmployeeId(role.getHcmoRoleId());

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = empUSTax.getHcmoEmployeeId().getEmployeeId();
            String sSubject = getText("message.subject.employeeUSTax.edit");

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
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.edit.updatedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage(getText("Updated Successfully"));
        }
        return SUCCESS;
    }

    // To delete particular EmpUSTax
    @SkipValidation
    public String deleteEmpUSTax() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empUSTax.setUpdatedBy(oEmp);
        empUSTaxService.deleteEmpUSTax(empUSTax);

        empUSTax = empUSTaxService.getEmpUSTax(empUSTax.getHcmoEmpUsTaxId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = empUSTax.getHcmoEmployeeId().getEmployeeId();
        String sSubject = getText("message.subject.employeeUSTax.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedToAdmin"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeUSTax.delete.deletedBy"), empUSTax.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(empUSTax.getHcmoEmployeeId().getEmployeeId(), empUSTax.getHcmoEmployeeId().getEmpFirstName(), getText("employeeUSTax.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
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

        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date employeeUsTaxModdate = empUSTax.getUpdated();
        empUsTaxModifiedDate = updatedFormat.format(employeeUsTaxModdate);

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
            + getText("label.header.employeeUsTax.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getHcmoEmployeeId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.common.message.federalIncomeTax")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empUSTax.federalStatus")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getTaxStateStatus()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (empUSTax.getTaxFederalExceptions() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.federalException")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxFederalExceptions()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.common.message.stateIncomeTax")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.empUSTax.state")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getTaxState().getName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empUSTax.stateStatus") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empUSTax.getTaxStateStatus() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empUSTax.getTaxStateExceptions() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.stateException")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxStateExceptions()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empUSTax.getTaxUnempState() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.unempState")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxUnempState().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empUSTax.getTaxWorkState() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.workState")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxWorkState().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empUsTaxModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date employeeUsTaxModdate = empUSTax.getUpdated();
        empUsTaxModifiedDate = updatedFormat.format(employeeUsTaxModdate);

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
            + getText("label.header.employeeUsTax.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getHcmoEmployeeId().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.common.message.federalIncomeTax")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empUSTax.federalStatus")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getTaxStateStatus()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (empUSTax.getTaxFederalExceptions() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.federalException")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxFederalExceptions()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.common.message.stateIncomeTax")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.empUSTax.state")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + empUSTax.getTaxState().getName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empUSTax.stateStatus") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empUSTax.getTaxStateStatus() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empUSTax.getTaxStateExceptions() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.stateException")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxStateExceptions()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empUSTax.getTaxUnempState() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.unempState")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxUnempState().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        if (empUSTax.getTaxWorkState() != null) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empUSTax.workState")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empUSTax.getTaxWorkState().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + empUsTaxModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

    public List<EmpUSTaxVO> getEmpUSTaxList() {
        return empUSTaxList;
    }

    public void setEmpUSTaxList(List<EmpUSTaxVO> empUSTaxList) {
        this.empUSTaxList = empUSTaxList;
    }

    public EmpUSTaxVO getEmpUSTax() {
        return empUSTax;
    }

    public void setEmpUSTax(EmpUSTaxVO empUSTax) {
        this.empUSTax = empUSTax;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public EmployeesVO getEmps() {
        return emps;
    }

    public void setEmps(EmployeesVO emps) {
        this.emps = emps;
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
