
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
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.LicenseDaoService;
import com.gits.rms.service.LicenseService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LicenseVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class LicenseAction extends ActionSupport {
    private static final long serialVersionUID = 3143425778025922529L;
    private LicenseService licenseService = new LicenseDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private RoleService roleService = new RoleDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<LicenseVO> licenseList;
    private LicenseVO license;
    private RoleVO role;
    private List<EmployeesVO> adminRoleId;
    private EmployeesVO emps;
    private String licDateMail = "";
    private String liceRenewDateMail = "";
    private String liceModifiedDateMail = "";

    // To get List of License
    @SkipValidation
    public String getAllLicense() {
        Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		licenseList = licenseService.getAllSubEmployeeLicenseList(employeeReportToList);
    	}else{
    		licenseList = licenseService.getAllLicense();
    	}
    	
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String licenseSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String licenseSearchResult() {
        licenseList = licenseService.licenseSearchResult(license);
        if (license.getMessage() != null) {
            if (license.getMessage().contains(getText("label.common.search.messageSetLicenseDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(license.getLicenseDate()) + "."));
            }
            if (license.getMessage().contains(getText("label.common.search.messageSetLicenseRenewalDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(license.getLicenseRenewalDate()) + "."));
            }
        }
        return SUCCESS;
    }

    // Based on EmployeeId get All License of that Employee
    @SkipValidation
    public String getEmployeeAllLicense() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("license.empIdObj.employeeId"));
        if (employeeId == null) {
            employeeId = license.getEmpIdObj().getEmployeeId();
        }
        licenseList = licenseService.getEmployeeAllLicense(employeeId);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // License it shows blank Form to enter New Data
    @SkipValidation
    public String setUpLicense() {
        if ((license != null) && (license.getEmpLicenseId() != null)) {
            license = licenseService.getLicense(license.getEmpLicenseId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new License for that Employee or Update data
    // of the License
    @SkipValidation
    public String setUpEmpLicense() {
        Integer.valueOf(ServletActionContext.getRequest().getParameter("license.empIdObj.employeeId"));
        if ((license != null) && (license.getEmpLicenseId() != null) && (license != null)) {
            license = licenseService.getLicense(license.getEmpLicenseId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new License for that Employee or Update data
    // of the License
    @SkipValidation
    public String setUpEmpLicenseSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("license.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("license.empLicenseId"));
        if ((license != null) && (license.getEmpLicenseId() != null) && (employeeId != null)) {
            license = licenseService.getEmpLicense(license);
        }
        return SUCCESS;
    }

    // To get Particular License Data
    @SkipValidation
    public String licenseView() {
        if ((license != null) && (license.getEmpLicenseId() != null)) {
            license = licenseService.getLicense(license.getEmpLicenseId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new License or update
    // particular License Data
    public String insertOrUpdateLicense() {
        try {
            if (!validationLDSuccessfull() || !validationLRDSuccessful()) {
                return INPUT;
            } else {
                if (license.getEmpLicenseId() == null) {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    license.setCreated(DateUtils.getCurrentDateTime());
                    license.setCreatedBy(oEmp);
                    license.setUpdatedBy(oEmp);
                    license.setIsActive(1);
                    licenseService.insertLicense(license);

                    license = licenseService.getLicense(license.getEmpLicenseId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = license.getEmpIdObj().getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();
                    String sSubject = getText("message.subject.license.add");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.add.addedToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.add.addedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.add.addedToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.add.addedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }

                        }
                    }

                    addActionMessage(getText("Added Successfully"));
                } else {
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    license.setUpdatedBy(oEmp);
                    licenseService.updateLicense(license);

                    license = licenseService.getLicense(license.getEmpLicenseId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = license.getEmpIdObj().getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();
                    String sSubject = getText("message.subject.license.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.edit.updateToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.edit.updatedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.edit.updateToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.edit.updatedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Updated Successfully"));
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

    // Validation Method for LicenseRenewaldate
    private boolean validationLRDSuccessful() {
        if (DateUtils.isGreaterDate(license.getLicenseRenewalDate())) {
            return true;
        } else {
            addActionError(getText("errors.license.renewalDate.invalid"));
            return false;
        }
    }

    // Validation Method for LicenseDate
    private boolean validationLDSuccessfull() {
        if (DateUtils.isLesserDate(license.getLicenseDate())) {
            return true;
        } else {
            addActionError(getText("errors.license.licenseDate.invalid"));
            return false;
        }
    }

    // To delete particular License Detail of an employee
    @SkipValidation
    public String deletelicense() {
        try {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            license.setUpdatedBy(oEmp);
            licenseService.deleteLicense(license);

            license = licenseService.getLicense(license.getEmpLicenseId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));
            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = license.getEmpIdObj().getEmployeeId();
            String sSubject = getText("message.subject.license.delete");

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
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.delete.deleteToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.delete.deletedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("license.delete.deleteToAdmin"), license.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("license.delete.deletedBy"), license.getEmpIdObj().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(license.getEmpIdObj().getEmployeeId(), license.getEmpIdObj().getEmpFirstName(), getText("license.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {

        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date licenseDate = license.getLicenseDate();
            licDateMail = dateformat.format(licenseDate);
            Date licenseRenewalDate = license.getLicenseRenewalDate();
            liceRenewDateMail = dateformat.format(licenseRenewalDate);
            Date licenseModifiedDate = license.getUpdated();
            liceModifiedDateMail = updateddateformat.format(licenseModifiedDate);

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
                + getText("label.header.license.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + license.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.licNumber") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + license.getLicenseNumber() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.licenseDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + licDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.renewalDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + liceRenewDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + license.getLicenseDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + liceModifiedDateMail
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

            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date licenseDate = license.getLicenseDate();
            licDateMail = dateformat.format(licenseDate);
            Date licenseRenewalDate = license.getLicenseRenewalDate();
            liceRenewDateMail = dateformat.format(licenseRenewalDate);
            Date licenseModifiedDate = license.getUpdated();
            liceModifiedDateMail = updateddateformat.format(licenseModifiedDate);

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
                + getText("label.header.license.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + license.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.licNumber") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + license.getLicenseNumber() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.licenseDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + licDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.license.renewalDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + liceRenewDateMail
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + license.getLicenseDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + liceModifiedDateMail
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

    public List<LicenseVO> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<LicenseVO> licenseList) {
        this.licenseList = licenseList;
    }

    public LicenseVO getLicense() {
        return license;
    }

    public void setLicense(LicenseVO license) {
        this.license = license;
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
