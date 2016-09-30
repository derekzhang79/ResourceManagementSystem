
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class ProjectActivityAction extends ActionSupport {
    private static final long serialVersionUID = 5613294933419444499L;
    static Logger log = Logger.getLogger(ProjectActivityAction.class.getName());// for store log details
    private ProjectActivityService projActivityService = new ProjectActivityDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private ProjectAssignEmpService projectAssignEmpService = new ProjectAssignEmpDaoService();
    private ProjectService proService = new ProjectDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<ProjectActivityVO> projActivityList;
    private ProjectActivityVO proActivity;
    private ProjectActivityVO proActivityCount;
    private List<ProjectAssignEmpVO> objectForProActivityCount;
    private ProjectAssignEmpVO ProAssignObj;
    private List<EmployeesVO> adminRoleId;
    private String projActivityModifiedDateMail = "";
    private RoleService roleService = new RoleDaoService();
    private RoleVO role;

    // To get List of ProjectActivity for an Employee
    @SkipValidation
    public String getAllProjectActivity() {
        projActivityList = projActivityService.getAllProjectActivity();
        return SUCCESS;
    }
    
    
    // To View Search Page
    @SkipValidation
    public String projectActivitySearcForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String projectActivitySearcResult() {
        projActivityList = projActivityService.projectActivitySearchResult(proActivity);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // ProjectActivity it shows blank Form to enter New Data
    @SkipValidation
    public String setUpProjectActivity() {
        if ((proActivity != null) && (proActivity.getProjectActivityId() != null)) {
            proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
        }
        return SUCCESS;
    }

    // To get Particular ProjectActivity Data
    @SkipValidation
    public String projectActivityView() {
        if ((proActivity != null) && (proActivity.getProjectActivityId() != null)) {
            proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
        }
        return SUCCESS;
    }
    
    @SkipValidation
    public String getPrevAddedProjActivityByProj() {
    	if(proActivity.getProObj().getProjectId() == null ){
    		addActionError("Please Select a Project");
    		return SUCCESS;
    	}
       projActivityList = projActivityService.getPrevAddedProjActivityByProj(proActivity.getProObj().getProjectId());
       
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new ProjectActivity or
    // update particular ProjectActivity Data
    @SkipValidation
    public String insertOrUpdateProjectActivity() {
    	System.out.println("inside project activity");
    	ProjectVO projObj = new ProjectVO();
    	ProjectActivityVO projActivityObj = new ProjectActivityVO();
    	List<ProjectActivityVO> projActivityList = new ArrayList<ProjectActivityVO>();
    	
        //ProjectActivityVO isExist = projActivityService.getProjectAndActivity(proActivity.getActivityName().toString(), proActivity.getProObj().getProjectId());
        if (proActivity.getProjectActivityId() == null) {
        	log.debug("control enters into task details insertion");
            projObj = proService.getProject(proActivity.getProObj().getProjectId());        
            Double projHour = Double.valueOf(projObj.getEstimatedHours());
            
            /*if(proActivity.getEstimatedHours() != null){
            	Double projActivityHour = Double.valueOf(proActivity.getEstimatedHours());
            	Double totalActivityEstHour =  Double.valueOf(proActivity.getEstimatedHours());
            	
            	 if(projActivityHour > projHour){
          	   		addActionError("Project Hours Is Lesser Than Entered Project Activity Hours");
          	   		return ERROR;
          	   	}
            	 
            	 projActivityList = projActivityService.getAllProjActivityByProject(projObj);
            	 for (Iterator<ProjectActivityVO> it = projActivityList.iterator(); it.hasNext();) {
         			projActivityObj = it.next();
         			
         			if(projActivityObj.getEstimatedHours() != null){
         				totalActivityEstHour += Double.valueOf(projActivityObj.getEstimatedHours());
         			}
            	 }
            	 
            	 if(totalActivityEstHour > projHour){
            		 addActionError("Project Hours Is Lesser Than Over All Project Activity Hours");
           	   		return ERROR;
            	 }
            }*/
            
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            proActivity.setCreated(DateUtils.getCurrentDateTime());
            proActivity.setCreatedBy(oEmp);
            proActivity.setUpdatedBy(oEmp);
            proActivity.setIsActive(1);
            projActivityService.insertProjectActivity(proActivity);
            log.info("tasks added successfully");
            /*try {
                proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = proActivity.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.projectActivity.add");

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
                                mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.add.addTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);

                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);

                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.add.addedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.add.addedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.add.addTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            addActionMessage(getText("Added Successfully"));
        } else {
        	log.debug("control enters into project task updation");
           /* ProjectActivityVO isss = projActivityService.isExistProjectActivity(proActivity);
            if (isss != null) {
                addActionError("You already have same Project activity for this Project");
                return ERROR;
            }
            
            projObj = proService.getProject(proActivity.getProObj().getProjectId());
            if(projObj.getEstimatedHours() == null){
            	addActionError("Please Enter Estimated Hour For Project Before Entering Estimated Hour For Activity");
                return ERROR;
     	   	}
            
            Double projHour = Double.valueOf(projObj.getEstimatedHours());
            
            if(proActivity.getEstimatedHours() != null){
            	Double projActivityHour = Double.valueOf(proActivity.getEstimatedHours());
            	Double totalActivityEstHour = Double.valueOf(proActivity.getEstimatedHours());
            	
            	 if(projActivityHour > projHour){
          	   		addActionError("Project Hours Is Lesser Than Entered Project Activity Hours");
          	   		return ERROR;
          	   	}
            	 
            	 projActivityList = projActivityService.getAllProjActivityByProject(projObj);
            	 for (Iterator<ProjectActivityVO> it = projActivityList.iterator(); it.hasNext();) {
         			projActivityObj = it.next();
         			
         			if(projActivityObj.getEstimatedHours() != null){
         				totalActivityEstHour += Double.valueOf(projActivityObj.getEstimatedHours());
         			}
            	 }
            	 
            	 if(totalActivityEstHour > projHour){
            		addActionError("Project Hours Is Lesser Than Over All Project Activity Hours");
           	   		return ERROR;
            	 }
            	 
            	 objectForProActivityCount = projectAssignEmpService.getProjAssignListByProjActivityAndProj(proActivity.getProObj().getProjectId(), proActivity.getProjectActivityId());
            	 
            	 if(!(objectForProActivityCount.isEmpty())){
            		 Integer countAllocatedHours = 0;
            		 Integer estimatedActivityHours = Integer.valueOf(proActivity.getEstimatedHours());
            		 
            		 for (Iterator<ProjectAssignEmpVO> it = objectForProActivityCount.iterator(); it.hasNext();) {
                		 ProAssignObj = it.next();
                		 if(ProAssignObj.getAllocatedHours() != null){
                			 countAllocatedHours += Integer.valueOf(ProAssignObj.getAllocatedHours());
                		 }
                	 }
            		 
            		 if(estimatedActivityHours < countAllocatedHours){
            			 addActionError("Project Activity Hours Is Lesser Than Over All Assigned Project Hours");
                	   	 return ERROR;
            		 }
            	 }
            }
            */
            
            
        	EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            proActivity.setUpdatedBy(oEmp);
            projActivityService.updateProjectActivity(proActivity);
            log.info("task updated successfully");

            /*proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = proActivity.getEmpIdObj().getEmployeeId();

            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
            int lengthForAdminEmpList = adminRoleId.size();
            String sSubject = getText("message.subject.projectActivity.edit");

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
                            mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.edit.updateTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);

                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);

                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.edit.updatedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.edit.updatedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.edit.updateTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                    }

                }
            }*/
            addActionMessage(getText("Updated Successfully"));
        }
        return SUCCESS;
    }

    // To delete Particular Projectactivity Detail
    @SkipValidation
    public String deleteProjectActivity() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the ProjectActivity is being used in the Timesheet
        // Entered Page,If yes then we are not allowing the ProjectActivity to
        // delete.
        proActivityCount = projActivityService.getProjectActivityCount(proActivity.getProjectActivityId());
        if (proActivityCount != null) {
        	objectForProActivityCount = projActivityService.getAllProjectActivityCount(proActivityCount);
            if (!(objectForProActivityCount.isEmpty())) {
                addActionError(getText("label.header.timeSheet.msg.deleteTimesheetProjectActivity"));
                return SUCCESS;
            }
        }
        proActivity.setUpdatedBy(oEmp);
        projActivityService.deleteProjectActivity(proActivity);
        proActivity = projActivityService.getProjectActivity(proActivity.getProjectActivityId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = proActivity.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.projectActivity.delete");

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
                        mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.delete.deletedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.delete.deletedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.delete.deleteTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);

                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.delete.deletedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mailEmpToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);

                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.delete.deletedByOwn"), getText("message.common.myOwn.name"), proActivity.getProObj().getProjectName(), sSubject);

                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("projectActivity.delete.deletedToAdmin"), proActivity.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);

                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("projectActivity.delete.deletedBy"), proActivity.getEmpIdObj().getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(proActivity.getEmpIdObj().getEmployeeId(), proActivity.getEmpIdObj().getEmpFirstName(), getText("projectActivity.delete.deleteTo"), oEmp.getEmpFirstName(), proActivity.getProObj().getProjectName(), sSubject);
                }

            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String ProjectName, String sSubject) {

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date projActivityModdate = proActivity.getUpdated();
        projActivityModifiedDateMail = updatedFormat.format(projActivityModdate);

        HCMOneMailer mailer = new HCMOneMailer();
        String sPerson = Constants.PERSON;
        String sProject = Constants.PROJECT;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sPerson), sMessage.lastIndexOf(sPerson)
            + sPerson.length(), From);
        sMessage.replace(sMessage.indexOf(sProject), sMessage.indexOf(sProject) + sProject.length(), ProjectName);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.projActivity.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectOwner")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proActivity.getProObj().getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectActivity")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getActivityName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (proActivity.getActivityNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.notes") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + proActivity.getActivityNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + projActivityModifiedDateMail
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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
        Date projActivityModdate = proActivity.getUpdated();
        projActivityModifiedDateMail = updatedFormat.format(projActivityModdate);

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
            + getText("label.header.projActivity.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectOwner")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proActivity.getProObj().getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectActivity")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getActivityName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (proActivity.getActivityNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.notes") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + proActivity.getActivityNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + projActivityModifiedDateMail
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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
        Date projActivityModdate = proActivity.getUpdated();
        projActivityModifiedDateMail = updatedFormat.format(projActivityModdate);

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
            + getText("label.header.projActivity.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectOwner")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proActivity.getProObj().getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectActivity")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getActivityName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (proActivity.getActivityNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.notes") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + proActivity.getActivityNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + projActivityModifiedDateMail
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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
        Date projActivityModdate = proActivity.getUpdated();
        projActivityModifiedDateMail = updatedFormat.format(projActivityModdate);

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
            + getText("label.header.projActivity.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectOwner")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.project.projectName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + proActivity.getProObj().getProjectName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.projActivity.projectActivity")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + proActivity.getActivityName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (proActivity.getActivityNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.common.notes") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + proActivity.getActivityNotes() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.common.message.modifiedDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + projActivityModifiedDateMail
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public List<ProjectActivityVO> getProjActivityList() {
        return projActivityList;
    }

    public void setProjActivityList(List<ProjectActivityVO> projActivityList) {
        this.projActivityList = projActivityList;
    }

    public ProjectActivityVO getProActivity() {
        return proActivity;
    }

    public void setProActivity(ProjectActivityVO proActivity) {
        this.proActivity = proActivity;
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

	public void setProActivityCount(ProjectActivityVO proActivityCount) {
		this.proActivityCount = proActivityCount;
	}

	public ProjectActivityVO getProActivityCount() {
		return proActivityCount;
	}

	public void setObjectForProActivityCount(
			List<ProjectAssignEmpVO> objectForProActivityCount) {
		this.objectForProActivityCount = objectForProActivityCount;
	}

	public List<ProjectAssignEmpVO> getObjectForProActivityCount() {
		return objectForProActivityCount;
	}
}
