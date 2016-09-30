
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class ProjectAction extends ActionSupport {
    private static final long serialVersionUID = 7052717786672510129L;
    private ProjectService proService = new ProjectDaoService();
    private ProjectActivityService projActivityService = new ProjectActivityDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<ProjectVO> proList;
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private ProjectVO proj;
    private String proModifiedDateMail = "";
    private RoleService roleService = new RoleDaoService();
    private List<EmployeesVO> adminRoleId;
    private RoleVO role;
    private List<ProjectActivityVO> proActivityList;

    // To get List of Project for an Employee
    @SkipValidation
    public String getAllProjects() {
        proList = proService.getAllProjects();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String projectsSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String projectsSearchResult() {
        proList = proService.projectsSearchResult(proj);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Project it shows blank Form to enter New Data
    @SkipValidation
    public String setUpProject() {
        if ((proj != null) && (proj.getProjectId() != null)) {
            proj = proService.getProject(proj.getProjectId());
        }
        return SUCCESS;
    }

    // To get Particular Project Data
    @SkipValidation
    public String projectView() {
        if ((proj != null) && (proj.getProjectId() != null)) {
            proj = proService.getProject(proj.getProjectId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Project or update
    // particular Project Data
    public String insertOrUpdateProject() {
        try {
            if (proj.getProjectId() == null) {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                proj.setCreated(DateUtils.getCurrentDateTime());
                proj.setCreatedBy(oEmp);
                proj.setUpdatedBy(oEmp);
                proj.setIsActive(1);
                proService.insertProject(proj);
                try {
                    proj = proService.getProject(proj.getProjectId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = proj.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.project.add");

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
                                    mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.add.addedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.add.addedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.add.addTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);

                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.add.addedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.add.addedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);

                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.add.addedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.add.addedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.add.addTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                addActionMessage(getText("Added Successfully"));

                // For Help Information Messages
                Boolean projectActivityAddPriv = (Boolean) session.get("PROJECTACTIVITY_ADD");
                if (projectActivityAddPriv) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.header.project.msg.projectActivity"));
                }

            } else {
            	
            	if(proj.getEstimatedHours() != null){
	            	List<ProjectActivityVO> projActivityList = new ArrayList<ProjectActivityVO>();
	            	ProjectActivityVO projActivityObj = new ProjectActivityVO();
	            	projActivityList = projActivityService.getAllProjActivityByProject(proj);
	            	Double estHour = 0d;
	            	
	            	if(!(projActivityList.isEmpty())){
	            		for (Iterator<ProjectActivityVO> it = projActivityList.iterator(); it.hasNext();) {
	            			projActivityObj = it.next();
	            			if(projActivityObj.getEstimatedHours() != null){
	            				estHour += Double.valueOf(projActivityObj.getEstimatedHours());
	            			}
	                    }
	               	 	
	           	 		double projEstHour = Double.valueOf(proj.getEstimatedHours());
	                   	if(estHour > projEstHour){
	                   		 addActionError(getText("Project Activity Estimated Is Greater Than Project Estimated Hour"));
	                   		 return INPUT;
	                   	}
	            	}
            	}
            		
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                proj.setUpdatedBy(oEmp);
                proService.updateProject(proj);

                proj = proService.getProject(proj.getProjectId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = proj.getEmpIdObj().getEmployeeId();

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();
                String sSubject = getText("message.subject.project.edit");

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
                                mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.edit.updatedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.edit.updatedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.edit.updateTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);

                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.edit.updatedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.edit.updatedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);

                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.edit.updatedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.edit.updatedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.edit.updateTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);
                        }

                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllProjectName();
        return SUCCESS;
    }

    // To delete Particular Project Detail
    @SkipValidation
    public String deleteProject() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        proj.setUpdatedBy(oEmp);

        // To check that the Project is being used in the ProjectActivity.If Yes
        // then we are not allowing the Project to get delete.
        proActivityList = proService.checkProjectInProActivity(proj);
        if (!(proActivityList.isEmpty())) {
            addActionError(getText("label.header.project.msg.deleteProjectActivity"));
            return SUCCESS;
        }

        proService.deleteProject(proj);
        proj = proService.getProject(proj.getProjectId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = proj.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.project.delete");

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
                        mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.delete.deletedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.delete.deletedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.delete.deleteTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);

                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.delete.deletedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.delete.deletedByOwn"), getText("message.common.myOwn.name"), proj.getProjectName(), sSubject);

                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("project.delete.deletedToAdmin"), proj.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("project.delete.deletedBy"), proj.getEmpIdObj().getEmpFirstName(), proj.getProjectName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(proj.getEmpIdObj().getEmployeeId(), proj.getEmpIdObj().getEmpFirstName(), getText("project.delete.deleteTo"), oEmp.getEmpFirstName(), proj.getProjectName(), sSubject);
                }

            }
        }
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllProjectName();
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String ProjectName, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date proModdate = proj.getUpdated();
        proModifiedDateMail = updatedFormat.format(proModdate);

        HCMOneMailer mailer = new HCMOneMailer();
        String replacement = HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + getText("message.common.dearEmployee");
        replacement = StringUtils.replace(replacement, "<person>", DearEmp)
            + HtmlConstants.HTML_PARA_END_TAG;

        String sPerson = Constants.PERSON;
        String sProject = Constants.PROJECT;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sPerson), sMessage.lastIndexOf(sPerson)
            + sProject.length(), From);
        sMessage.replace(sMessage.indexOf(sProject), sMessage.indexOf(sProject) + sProject.length(), ProjectName);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.projectDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectOwner") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.customer.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proj.getCustomerId().getCustomerName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (proj.getProjectDesc().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + proj.getProjectDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String ProjectName, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date proModdate = proj.getUpdated();
        proModifiedDateMail = updatedFormat.format(proModdate);

        HCMOneMailer mailer = new HCMOneMailer();
        String replacement = HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + getText("message.common.dearEmployee");
        replacement = StringUtils.replace(replacement, "<person>", DearEmp)
            + HtmlConstants.HTML_PARA_END_TAG;

        String sPerson = Constants.PERSON;
        String sProject = Constants.PROJECT;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.indexOf(sProject), sMessage.indexOf(sProject) + sProject.length(), ProjectName);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.projectDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectOwner") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.customer.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proj.getCustomerId().getCustomerName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (proj.getProjectDesc().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + proj.getProjectDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
        Date proModdate = proj.getUpdated();
        proModifiedDateMail = updatedFormat.format(proModdate);

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
            + getText("label.header.projectDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectOwner") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.customer.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proj.getCustomerId().getCustomerName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (proj.getProjectDesc().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + proj.getProjectDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

    public void mailEmpToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();
        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date proModdate = proj.getUpdated();
        proModifiedDateMail = updatedFormat.format(proModdate);

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
            + getText("label.header.projectDetails.name") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectOwner") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.customer.name")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proj.getCustomerId().getCustomerName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proj.getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        if (proj.getProjectDesc().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + proj.getProjectDesc()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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

    public List<ProjectVO> getProList() {
        return proList;
    }

    public void setProList(List<ProjectVO> proList) {
        this.proList = proList;
    }

    public ProjectVO getProj() {
        return proj;
    }

    public void setProj(ProjectVO proj) {
        this.proj = proj;
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
