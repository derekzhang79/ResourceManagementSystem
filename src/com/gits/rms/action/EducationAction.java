
package com.gits.rms.action;

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
import com.gits.rms.service.EducationDaoService;
import com.gits.rms.service.EducationService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeReportToVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EducationAction extends ActionSupport {
    private static final long serialVersionUID = 3758633793706722570L;
    static Logger log = Logger.getLogger(EducationAction.class.getName());// for store log details
    private EducationService eduService = new EducationDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EducationVO> eduList;
    private List<EmployeesVO> adminRoleId;
    private EducationVO edu;
    private RoleService roleService = new RoleDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private RoleVO role;
    private String educationStartDate = "";
    private String educationEndDate = "";
    private String eduModifiedDateMail = "";
    private EmployeeReportToAction reportToAction;
    private Map session;
    private Integer clientId;

    // To get List of Education
    @SkipValidation
    public String getAllEducation() {
    	Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		eduList = eduService.getAllSubEmployeeEducationList(employeeReportToList);
    	}else{
    		eduList = eduService.getAllEducation();
    	}
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String educationSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String educationSearchResult() {
        eduList = eduService.educationSearchResult(edu);

        if (edu.getMessage() != null) {
            if (edu.getMessage().contains(getText("label.common.search.messageSetEduStartDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(edu.getEduStartDate()) + "."));
            }
            if (edu.getMessage().contains(getText("label.common.search.messageSetEduEndDate"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(edu.getEduEndDate()) + "."));
            }
        }
        return SUCCESS;
    }

    // Based on EmployeeId get All Education of that Employee
    @SkipValidation
    public String getEmployeeAllEducation() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("edu.empIdObj.employeeId"));
        if (employeeId == null) {
            employeeId = edu.getEmpIdObj().getEmployeeId();
        }
        eduList = eduService.getEmployeeAllEducation(employeeId);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Education it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEducation() {
        if ((edu != null) && (edu.getEmpEducationId() != null)) {
            edu = eduService.getEducation(edu.getEmpEducationId());
        }
        return SUCCESS;
    }

    // To get Particular Education Data
    @SkipValidation
    public String educationView() {

        if ((edu != null) && (edu.getEmpEducationId() != null)) {
            edu = eduService.getEducation(edu.getEmpEducationId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new Education for that Employee or Update data
    // of the Education
    @SkipValidation
    public String setUpEmpEducation() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("edu.empIdObj.employeeId"));
        if ((edu != null) && (edu.getEmpEducationId() != null) && (employeeId != null)) {
            edu = eduService.getEducation(edu.getEmpEducationId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new Education for that Employee or Update data
    // of the Education
    @SkipValidation
    public String setUpEmpEducationSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("edu.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("edu.empEducationId"));
        if ((edu != null) && (edu.getEmpEducationId() != null) && (employeeId != null)) {
            edu = eduService.getEmpEducation(edu);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Education or
    // update particular Education Data
    @SkipValidation
    public String insertOrUpdateEducation() {
        try {
        	log.debug("Control enters into employee education details insertion");
        	session = ActionContext.getContext().getSession();
        	clientId = (Integer) session.get("CLIENT_INFO");
        	if(clientId != null)
        		edu.setClientId(clientId);
        	System.out.println("deg" + edu.getEduDegree());
        	System.out.println("deg" + edu.getEmpIdObj().getEmployeeId());
           /* if ((!validationSDSuccessful()) || (!validationEDSuccessful())
                || (!validationSDValid())||(!validationSDNotEqualEDValid())) {
                return INPUT;
            } else {
           */     if (edu.getEmpEducationId() == null) {
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    edu.setCreated(DateUtils.getCurrentDateTime());
                    edu.setCreatedBy(oEmp);
                    edu.setUpdatedBy(oEmp);
                    edu.setIsActive(1);
                    eduService.insertEducation(edu);
                    
                    // Education Add Mailer
                  /*  edu = eduService.getEducation(edu.getEmpEducationId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = edu.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.education.add");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.add.addedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.add.addedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);

                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.add.addedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.add.addedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                            }

                        }
                    }*/

                    addActionMessage(getText("Added Successfully"));
                    log.info("Education details added successfully");
                } else {
                	log.debug("control enters into employee education details updation");
                    EmployeesVO newAdminEmp = null;
                    //Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    edu.setUpdatedBy(oEmp);
                    eduService.updateEducation(edu);

                    // Education Edit Mailer
                    /*edu = eduService.getEducation(edu.getEmpEducationId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = edu.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.education.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.edit.updatedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);

                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.edit.updatedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);

                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.edit.updatedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.edit.updatedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }

                        }
                    }*/
                    addActionMessage(getText("Updated Successfully"));
                    log.info("Education details updated successfully");
                }
            //}
        } catch (RuntimeException e) {
            e.printStackTrace();
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getErrorMoreFields(e);
            addActionError(sError);
            e.printStackTrace();
            log.error("Exception occurred during the education details insertion/updation" + e);
            throw e;
        }
        return SUCCESS;
    }

    // Validation Method for Education StartDate
    private boolean validationSDSuccessful() {
        if (DateUtils.isLesserDate(edu.getEduStartDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.startDate.invalid"));
            return false;
        }
    }

    private boolean validationEDSuccessful() {
        if (DateUtils.isLesserDate(edu.getEduEndDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.enddate.invalid"));
            return false;
        }
    }

    private boolean validationSDValid() {
        if (DateUtils.compareDate(edu.getEduStartDate(), edu.getEduEndDate())) {
            return true;
        } else {
            addActionError(getText("errors.common.startDate.invalid"));
            return false;
        }
    }
    
    private boolean validationSDNotEqualEDValid() {
        if (DateUtils.compareGreaterOrEqual(edu.getEduStartDate(), edu.getEduEndDate())) {
            return true;
        } else {
            addActionError(getText("label.header.education.dateValidation"));
            return false;
        }
    }

    // To Delete particular Employee Education Detail
    @SkipValidation
    public String deleteEducation() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        edu.setUpdatedBy(oEmp);
        eduService.deleteEducation(edu);

        // Education Delete Mailer
        edu = eduService.getEducation(edu.getEmpEducationId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = edu.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.education.delete");

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
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.delete.deletedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);

                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.delete.deletedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);

                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("education.delete.deletedToAdmin"), edu.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("education.delete.deletedBy"), edu.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(edu.getEmpIdObj().getEmployeeId(), edu.getEmpIdObj().getEmpFirstName(), getText("education.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
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
            Date eduStart = edu.getEduStartDate();
            educationStartDate = dateformat.format(eduStart);
            Date eduEnd = edu.getEduEndDate();
            educationEndDate = dateformat.format(eduEnd);
            Date eduModdate = edu.getUpdated();
            eduModifiedDateMail = updateddateformat.format(eduModdate);
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
                + getText("label.header.education.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.major") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduMajor() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.year") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduYear() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            if (edu.getEduGrade().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.education.grade")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + edu.getEduGrade() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.startDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + educationStartDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.enddate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + educationEndDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.univ") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduUniversity() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.degree") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduDegree() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + eduModifiedDateMail
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
            Date eduStart = edu.getEduStartDate();
            educationStartDate = dateformat.format(eduStart);
            Date eduEnd = edu.getEduEndDate();
            educationEndDate = dateformat.format(eduEnd);
            Date eduModdate = edu.getUpdated();
            eduModifiedDateMail = updateddateformat.format(eduModdate);

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
                + getText("label.header.education.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.major") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduMajor() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.year") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduYear() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (edu.getEduGrade().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.education.grade")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + edu.getEduGrade() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.startDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + educationStartDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.enddate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + educationEndDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.univ") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduUniversity() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.education.degree") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + edu.getEduDegree() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + eduModifiedDateMail
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

    public List<EducationVO> getEduList() {
        return eduList;
    }

    public void setEduList(List<EducationVO> eduList) {
        this.eduList = eduList;
    }

    public EducationVO getEdu() {
        return edu;
    }

    public void setEdu(EducationVO edu) {
        this.edu = edu;
    }

    public List<EmployeesVO> getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

}
