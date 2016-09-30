
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
import com.gits.rms.service.EmpLocationHistoryDaoService;
import com.gits.rms.service.EmpLocationHistoryService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmpLocationHistoryAction extends ActionSupport {
    private static final long serialVersionUID = -346132777534313158L;
    private EmpLocationHistoryService empLocationHistoryService = new EmpLocationHistoryDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmpLocationHistoryVO> elHistList;
    private EmpLocationHistoryVO elhist;
    private String emplocFromDate = "";
    private List<EmployeesVO> adminRoleId;
    private String emplocToDate = "";
    private String emplocModifiedDate = "";
    private RoleService roleService = new RoleDaoService();
    private RoleVO role;

    // To get All Type of EmployeeLocationHistory List
    @SkipValidation
    public String getAllEmpLocationHistory() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		elHistList = empLocationHistoryService.getAllSubEmployeeLocationHistoryList(employeeReportToList);
    	}else{
    		elHistList = empLocationHistoryService.getAllEmpLocationHistory();
    	}
    	
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String empLocHistSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String empLocHistSearchResult() {
        elHistList = empLocationHistoryService.empLocHistSearchResult(elhist);

        if (elhist.getMessage() != null) {
            if (elhist.getMessage().contains(getText("label.common.search.messageSetLocHistStartDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(elhist.getStartDate()) + "."));
            }
            if (elhist.getMessage().contains(getText("label.common.search.messageSetLocHistEndDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(elhist.getEndDate()) + "."));
            }
        }

        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmployeeLocationHistory it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEmpLocationHistory() {
        if ((elhist != null) && (elhist.getHcmoEmpLocHistoryId() != null)) {
            elhist = empLocationHistoryService.getEmpLocationHistory(elhist.getHcmoEmpLocHistoryId());
        }
        return SUCCESS;
    }

    // To get Particular EmployeeLocationHistory Data
    @SkipValidation
    public String empLocationHistoryView() {
        if ((elhist != null) && (elhist.getHcmoEmpLocHistoryId() != null)) {
            elhist = empLocationHistoryService.getEmpLocationHistory(elhist.getHcmoEmpLocHistoryId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new
    // EmployeeLocationHistory or update particular EmployeeLocationHistory Data
    public String insertOrUpdateEmpLocationHistory() {
        if ((!validationSDSuccessful()) || (!validationEDSuccessfull()) || (!validationSDValid())) {
            return INPUT;
        } else {
            if (elhist.getHcmoEmpLocHistoryId() == null) {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                elhist.setCreated(DateUtils.getCurrentDateTime());
                elhist.setCreatedBy(oEmp);
                elhist.setUpdatedBy(oEmp);
                elhist.setIsActive(1);
                empLocationHistoryService.insertEmpLocationHistory(elhist);

                elhist = empLocationHistoryService.getEmpLocationHistory(elhist.getHcmoEmpLocHistoryId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = elhist.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.employeeLocationHistory.add");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.add.addedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }

                addActionMessage(getText("Added Successfully"));
            } else {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                elhist.setUpdatedBy(oEmp);
                empLocationHistoryService.updateEmpLocationHistory(elhist);

                elhist = empLocationHistoryService.getEmpLocationHistory(elhist.getHcmoEmpLocHistoryId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = elhist.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.employeeLocationHistory.edit");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.edit.updatedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        }
        return SUCCESS;
    }

    // Validation method for startDate
    private boolean validationSDSuccessful() {
        if (DateUtils.isLesserDate(elhist.getStartDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.startDate.invalid"));
            return false;
        }
    }

    // Validation method for End Date
    private boolean validationEDSuccessfull() {
        if (DateUtils.isLesserDate(elhist.getEndDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.enddate.invalid"));
            return false;
        }
    }

    // Validation method for Compare Date of End Date and startDate
    private boolean validationSDValid() {
        if (DateUtils.compareDate(elhist.getStartDate(), elhist.getEndDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.startDate.invalid"));
            return false;
        }
    }

    // To delete particular EmployeeLocationHistory data of an employee
    @SkipValidation
    public String deleteEmpLocationHistory() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        elhist.setUpdatedBy(oEmp);
        empLocationHistoryService.deleteEmpLocationHistory(elhist);

        elhist = empLocationHistoryService.getEmpLocationHistory(elhist.getHcmoEmpLocHistoryId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = elhist.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.employeeLocationHistory.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedToAdmin"), elhist.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("employeeLocationHistory.delete.deletedBy"), elhist.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(elhist.getEmpIdObj().getEmployeeId(), elhist.getEmpIdObj().getEmpFirstName(), getText("employeeLocationHistory.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
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

        Date emplocationFromDate = elhist.getStartDate();
        emplocFromDate = dateFormat.format(emplocationFromDate);
        Date emplocationTodate = elhist.getEndDate();
        emplocToDate = dateFormat.format(emplocationTodate);
        Date emplocationModdate = elhist.getUpdated();
        emplocModifiedDate = updatedFormat.format(emplocationModdate);

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
            + getText("label.header.locationHistory.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.location.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getLocationIdObj().getLocationName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.client.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getClientIdObj().getCompanyName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.startDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + emplocFromDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.endDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + emplocToDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + emplocModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

        Date emplocationFromDate = elhist.getStartDate();
        emplocFromDate = dateFormat.format(emplocationFromDate);
        Date emplocationTodate = elhist.getEndDate();
        emplocToDate = dateFormat.format(emplocationTodate);
        Date emplocationModdate = elhist.getUpdated();
        emplocModifiedDate = updatedFormat.format(emplocationModdate);

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
            + getText("label.header.locationHistory.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.location.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getLocationIdObj().getLocationName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.client.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + elhist.getClientIdObj().getCompanyName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.startDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + emplocFromDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.endDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + emplocToDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + emplocModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

    public EmpLocationHistoryService getEmpLocationHistoryService() {
        return empLocationHistoryService;
    }

    public void setEmpLocationHistoryService(EmpLocationHistoryService empLocationHistoryService) {
        this.empLocationHistoryService = empLocationHistoryService;
    }

    public List<EmpLocationHistoryVO> getElHistList() {
        return elHistList;
    }

    public void setElHistList(List<EmpLocationHistoryVO> elHistList) {
        this.elHistList = elHistList;
    }

    public EmpLocationHistoryVO getElhist() {
        return elhist;
    }

    public void setElhist(EmpLocationHistoryVO elhist) {
        this.elhist = elhist;
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