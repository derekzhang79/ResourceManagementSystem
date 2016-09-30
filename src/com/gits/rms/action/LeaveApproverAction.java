
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
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.SignatureVO;

public class LeaveApproverAction extends ActionSupport {
    private static final long serialVersionUID = -1920838672738246659L;
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private LeaveApproverVO leaveApprover = new LeaveApproverVO();
    private List<LeaveApproverVO> leaveApproverList;
    private List<LeaveApproverVO> leaveApproverListForMail;
    private String leaAppModifiedDate = "";
    private Integer sameInsertLeaveApproverCount;

    // To get All Leave Approver List
    @SkipValidation
    public String getAllLeaveApprover() {
        leaveApproverList = leaveAppproverService.getAllLeaveApprover();
        return SUCCESS;
    }

    // To View the LeaveApprover Search Form
    @SkipValidation
    public String leaveAppSearchForm() {
        return SUCCESS;
    }

    // To Search LeaveApprover
    @SkipValidation
    public String leaveAppSearchResult() {
        leaveApproverList = leaveAppproverService.leaveAppSearchResult(leaveApprover);
        return SUCCESS;
    }

    // Based on EmployeeId get All Leave Approver of that Employee
    @SkipValidation
    public String getEmployeeAllLeaveApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("leaveApprover.hcmoEmployeeId.employeeId"));
        if (employeeId == null) {
            employeeId = leaveApprover.getHcmoEmployeeId().getEmployeeId();
        }
        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(employeeId);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // LeaveApprover it shows blank Form to enter New Data
    @SkipValidation
    public String setUpInsertOrUpdateLeaveApprover() {
        if ((leaveApprover != null) && (leaveApprover.getHcmoLeaveApproverId() != null)) {
            leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new LeaveApprover for that Employee or Update
    // data of the LeaveApprover
    @SkipValidation
    public String setUpEmpInsertOrUpdateLeaveApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("leaveApprover.hcmoEmployeeId.employeeId"));
        if ((leaveApprover != null) && (leaveApprover.getHcmoLeaveApproverId() != null)
            && (employeeId != null)) {
            leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new LeaveApprover for that Employee or Update
    // data of the LeaveApprover
    @SkipValidation
    public String setUpEmpInsertOrUpdateLeaveApproverSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("leaveApprover.hcmoEmployeeId.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("leaveApprover.hcmoLeaveApproverId"));
        if ((leaveApprover != null) && (leaveApprover.getHcmoLeaveApproverId() != null)
            && (employeeId != null)) {
            leaveApprover = leaveAppproverService.getEmpLeaveApprover(leaveApprover);
        }
        return SUCCESS;
    }

    // To get Particular LeaveApprover Data
    @SkipValidation
    public String leaveApproverView() {
        if ((leaveApprover != null) && (leaveApprover.getHcmoLeaveApproverId() != null)) {
            leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new LeaveApprover
    // detail or update particular LeaveApprover Data
    public String insertOrUpdateLeaveApprover() {
        try {
            if (leaveApprover.getHcmoLeaveApproverId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                leaveApprover.setCreated(DateUtils.getCurrentDateTime());
                leaveApprover.setCreatedBy(oEmp);
                leaveApprover.setUpdatedBy(oEmp);
                leaveApprover.setIsActive(1);
                leaveApproverListForMail = leaveAppproverService.getEmployeeAllLeaveApprover(leaveApprover.getHcmoEmployeeId().getEmployeeId());

                // To prevent the Duplicate Operation again.
                sameInsertLeaveApproverCount = leaveAppproverService.getLeaveApproverCount(leaveApprover);
                if (sameInsertLeaveApproverCount > 0) {
                    addActionError(getText("label.title.leaveapprover.sameOperationAgain"));
                    return INPUT;
                }
                leaveAppproverService.insertLeaveApprover(leaveApprover);
                leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = leaveApprover.getHcmoEmployeeId().getEmployeeId();
                int approvingempid = leaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                String sSubject = getText("message.subject.leaveApp.add");

                // logged in emp that he/she added to leave approver for another
                // employee
                // without having previous leave approver
                if (((sessionEmpId != employeeID) && (sessionEmpId != approvingempid) && (employeeID != approvingempid))
                    && ((leaveApproverListForMail.size() == 0) || (leaveApproverListForMail.isEmpty() == false))) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.add.addedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                    // mail content to another emp
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    // mail content to approving emp
                    mail(leaveApprover.getHcmoApprovingEmpId().getEmployeeId(), leaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                    // logged in emp that he/she added to leave approver for
                    // another employee
                    // with having previous leave approver
                    if ((leaveApproverListForMail.size() != 0)
                        || (leaveApproverListForMail.isEmpty() == false)) {
                        LeaveApproverVO newLeaveApprover;
                        for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                            newLeaveApprover = it.next();
                            mailForApprovers(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApprovers.add.Status"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in emp that he/she added to emp for his/her own as
                // employee
                // without having previous leave approver
                else if (((sessionEmpId == employeeID) && (sessionEmpId != approvingempid))
                    && ((leaveApproverListForMail.size() == 0) || (leaveApproverListForMail.isEmpty() == false))) // 3rd
                // step
                {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // mail content to approving emp
                    mail(leaveApprover.getHcmoApprovingEmpId().getEmployeeId(), leaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                    // logged in emp that he/she added to emp for his/her own as
                    // employee
                    // with having previous leave approver
                    if ((leaveApproverListForMail.size() != 0)
                        || (leaveApproverListForMail.isEmpty() == false)) {
                        LeaveApproverVO newLeaveApprover;
                        for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                            newLeaveApprover = it.next();
                            mailForApproversToHisOwn(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApproversToHisOwn.add.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }

                // logged in emp that he/she added to leave approver for his/her
                // own as Approver
                // without having previous leave approver
                else if (((leaveApproverListForMail.size() == 0) || (leaveApproverListForMail.isEmpty() == false))
                    && ((sessionEmpId == approvingempid) && (sessionEmpId != employeeID))) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // mail content to employee name list
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                    // logged in emp that he/she added to leave approver for
                    // his/her own as Approver
                    // with having previous leave approver
                    if ((leaveApproverListForMail.size() != 0)
                        || (leaveApproverListForMail.isEmpty() == false)) {
                        LeaveApproverVO newLeaveApprover;
                        for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                            newLeaveApprover = it.next();
                            mailForApprovers(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApprovers.add.Status"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in emp that he/she added to leave approver for another
                // employee as Approver and employee
                // without having previous leave approver
                else if (((employeeID == approvingempid) && (sessionEmpId != employeeID) && (sessionEmpId != approvingempid))
                    && ((leaveApproverListForMail.size() == 0) || (leaveApproverListForMail.isEmpty() == false))) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.add.addedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                    // mail content to approving emp
                    mail(leaveApprover.getHcmoApprovingEmpId().getEmployeeId(), leaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                    // logged in emp that he/she added to leave approver for
                    // another employee as Approver and employee
                    // with having previous leave approver
                    if ((leaveApproverListForMail.size() != 0)
                        || (leaveApproverListForMail.isEmpty() == false)) {
                        LeaveApproverVO newLeaveApprover;
                        for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                            newLeaveApprover = it.next();
                            mailForApprovers(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApprovers.add.Status"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in emp that he/she added to leave approver for his/her
                // own as Approver and employee
                // without having previous leave approver
                else {
                    if (((sessionEmpId == employeeID) && (sessionEmpId == approvingempid) && (employeeID == approvingempid))) {
                        // mail content to logged in emp
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);

                        // logged in emp that he/she added to leave approver for
                        // his/her own as Approver and employee
                        // with having previous leave approver
                        if ((leaveApproverListForMail.size() != 0)
                            || (leaveApproverListForMail.isEmpty() == false)) {
                            LeaveApproverVO newLeaveApprover;
                            for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                                newLeaveApprover = it.next();
                                mailForApproversToHisOwn(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApproversToHisOwn.add.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                }

                // To Set Menu for Approving Employee
                if (leaveApprover.getHcmoApprovingEmpId().getEmployeeId().equals(oEmp.getEmployeeId())) {
                    if (session.get("LEAVE_APPROVER") != "BOTH") {
                        session.put("LEAVE_APPROVER", "BOTH");
                    }
                }
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                leaveApprover.setUpdatedBy(oEmp);
                LeaveApproverVO editLeaveApprover = new LeaveApproverVO();
                editLeaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());

                editLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                leaveApproverListForMail = leaveAppproverService.getEmployeeAllLeaveApprover(leaveApprover.getHcmoEmployeeId().getEmployeeId());

                // To prevent the Duplicate Operation again.
                sameInsertLeaveApproverCount = leaveAppproverService.getLeaveApproverCount(leaveApprover);
                if (sameInsertLeaveApproverCount > 0) {
                    addActionError(getText("label.title.leaveapprover.sameOperationAgain"));
                    return INPUT;
                }

                leaveAppproverService.updateLeaveApprover(leaveApprover);
                editLeaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
                leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = leaveApprover.getHcmoEmployeeId().getEmployeeId();
                int approvingempid = leaveApprover.getHcmoApprovingEmpId().getEmployeeId();
                String sSubject = getText("message.subject.leaveApp.edit");

                // with having previous and old approver to all conditions
                if ((leaveApproverListForMail.size() != 0)
                    || (leaveApproverListForMail.isEmpty() == false)) {
                    LeaveApproverVO newLeaveApprover;
                    LeaveApproverVO newLeaveApproverInnerLoop;
                    for (Iterator<LeaveApproverVO> it = leaveApproverListForMail.iterator(); it.hasNext();) {
                        newLeaveApprover = it.next();
                        int outerApproverId = newLeaveApprover.getHcmoLeaveApproverId();
                        int outerApproverEmpId = newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();

                        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(leaveApprover.getHcmoEmployeeId().getEmployeeId());
                        for (Iterator<LeaveApproverVO> ite = leaveApproverList.iterator(); ite.hasNext();) {
                            newLeaveApproverInnerLoop = ite.next();
                            int innerApproverId = newLeaveApproverInnerLoop.getHcmoLeaveApproverId();
                            int innerEmployeeId = newLeaveApproverInnerLoop.getHcmoApprovingEmpId().getEmployeeId();

                            if (outerApproverId == innerApproverId) {
                                if (outerApproverEmpId != innerEmployeeId) {
                                    // Mail Content to Old Approver
                                    mailForOldApprover(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.oldApprover.edit.changeStatus"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    if (!((sessionEmpId == approvingempid) && (sessionEmpId != employeeID))
                                        && !(employeeID == approvingempid)) {
                                        // Mail Content to New Approver
                                        mail(newLeaveApproverInnerLoop.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApproverInnerLoop.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                } else if (outerApproverEmpId == innerEmployeeId) {
                                    // Mail Content to Previous Approver
                                    if (((sessionEmpId == employeeID) && (sessionEmpId != approvingempid))
                                        || ((sessionEmpId == employeeID)
                                            && (sessionEmpId == approvingempid) && (employeeID == approvingempid))) {
                                        mailForApproversToHisOwn(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApproversToHisOwn.edit.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                                    } else {
                                        mailForApprovers(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApprovers.edit.Status"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                        }

                    }
                }
                leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
                // logged in emp that he/she updated to leave approver for
                // his/her own as Approver and employee
                // without having previous leave approver
                if ((sessionEmpId == employeeID) && (sessionEmpId == approvingempid)
                    && (employeeID == approvingempid)) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // logged in emp that he/she added to emp for his/her own as
                // employee
                // without having previous leave approver
                else if ((sessionEmpId == employeeID) && (sessionEmpId != approvingempid)) {
                    // mail content to employee name list
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // logged in emp that he/she added to leave approver for another
                // employee as Approver and employee
                // without having previous leave approver
                else if ((employeeID == approvingempid) && (sessionEmpId != employeeID)
                    && (sessionEmpId != approvingempid)) {
                    // mail content to employee name list
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.edit.updatedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                }
                // logged in emp that he/she added to leave approver for his/her
                // own as Approver
                // without having previous leave approver
                else if ((sessionEmpId == approvingempid) && (sessionEmpId != employeeID)) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // mail content to employee name list
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    // mail content to employee name list
                    mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.edit.updatedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                }

                // To Set Menu for Approving Employee
                Integer LeaveApproverCount = leaveAppproverService.checkLeaveApprover(oEmp.getEmployeeId(),oEmp.getClientId());
                if (LeaveApproverCount > 0) {
                    session.put("LEAVE_APPROVER", "BOTH");
                } else {
                    session.put("LEAVE_APPROVER", "NON-APPROVER");
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    // Delete Particular LeaveApprover Detail form the List
    @SkipValidation
    public String deleteLeaveApprover() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        leaveApprover.setUpdatedBy(oEmp);
        leaveAppproverService.deleteLeaveApprover(leaveApprover);

        LeaveApproverVO newLeaveApprover;
        leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(leaveApprover.getHcmoEmployeeId().getEmployeeId());
        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = leaveApprover.getHcmoEmployeeId().getEmployeeId();
        int approvingempid = leaveApprover.getHcmoApprovingEmpId().getEmployeeId();
        String sSubject = getText("message.subject.leaveApp.delete");

        // with having previous approver to all conditions
        if ((leaveApproverList.size() != 0) || (leaveApproverList.isEmpty() == false)) {
            for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
                newLeaveApprover = it.next();
                if (((sessionEmpId == employeeID) && (sessionEmpId != approvingempid))
                    || ((sessionEmpId == employeeID) && (sessionEmpId == approvingempid) && (employeeID == approvingempid))) {
                    mailForApproversToHisOwn(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApproversToHisOwn.delete.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    mailForApprovers(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.allOtherApprovers.delete.Status"), newLeaveApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }

        // logged in emp that he/she added to emp for his/her own as employee
        // without having previous leave approver
        if ((sessionEmpId == employeeID) && (sessionEmpId != approvingempid)) {
            // mail content to employee name list
            mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
            // mail content to approving emp
            mail(leaveApprover.getHcmoApprovingEmpId().getEmployeeId(), leaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to leave approver for another
        // employee as Approver and employee
        // without having previous leave approver
        else if ((employeeID == approvingempid) && (sessionEmpId != employeeID)
            && (sessionEmpId != approvingempid)) {
            // mail content to employee name list
            mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.delete.deletedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to leave approver for his/her own as
        // Approver and employee
        // without having previous leave approver
        else if ((employeeID == approvingempid) && (sessionEmpId == employeeID)) {
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
        }
        // logged in emp that he/she added to emp for his/her own as approver
        // without having previous leave approver
        else if ((sessionEmpId == approvingempid) && (sessionEmpId != employeeID)) {
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
            // mail content to employee name list
            mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to leave approver for another
        // employee
        // without having previous leave approver
        else {
            // mail content to employee name list
            mail(leaveApprover.getHcmoEmployeeId().getEmployeeId(), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), getText("leaveApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to approving emp
            mail(leaveApprover.getHcmoApprovingEmpId().getEmployeeId(), leaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leaveApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("leaveApp.loggedIn.delete.deletedBy"), leaveApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
        }

        // To Set Menu for Approving Employee
        Integer LeaveApproverCount = leaveAppproverService.checkLeaveApprover(oEmp.getEmployeeId(),oEmp.getClientId());
        if (LeaveApproverCount > 0) {
            session.put("LEAVE_APPROVER", "BOTH");
        } else {
            session.put("LEAVE_APPROVER", "NON-APPROVER");
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date leaveApproverModdate = leaveApprover.getUpdated();
            leaAppModifiedDate = updateddateformat.format(leaveApproverModdate);

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
                + getText("label.header.leaveapprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.leaveapprover.leaveApprovingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoApprovingEmpId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppModifiedDate
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

    public void mailForApprovers(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
            HCMOneMailer mailer = new HCMOneMailer();
            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date leaveApproverModdate = leaveApprover.getUpdated();
            leaAppModifiedDate = updateddateformat.format(leaveApproverModdate);

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
                + getText("label.header.leaveapprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.leaveapprover.leaveApprovingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoApprovingEmpId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppModifiedDate
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

    public void mailForApproversToHisOwn(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            leaveApprover = leaveAppproverService.getLeaveApprover(leaveApprover.getHcmoLeaveApproverId());
            HCMOneMailer mailer = new HCMOneMailer();
            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.HISOWN_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date leaveApproverModdate = leaveApprover.getUpdated();
            leaAppModifiedDate = updateddateformat.format(leaveApproverModdate);

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
                + getText("label.header.leaveapprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.leaveapprover.leaveApprovingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + leaveApprover.getHcmoApprovingEmpId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaAppModifiedDate
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

    public void mailForOldApprover(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");

            HCMOneMailer mailer = new HCMOneMailer();
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

    public LeaveApproverVO getLeaveApprover() {
        return leaveApprover;
    }

    public void setLeaveApprover(LeaveApproverVO leaveApprover) {
        this.leaveApprover = leaveApprover;
    }

    public List<LeaveApproverVO> getLeaveApproverList() {
        return leaveApproverList;
    }

    public void setLeaveApprover(List<LeaveApproverVO> leaveApproverList) {
        this.leaveApproverList = leaveApproverList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }
}