
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.SignatureVO;

public class ExpensesApproverAction extends ActionSupport {

    private static final long serialVersionUID = 5927621952247735352L;
    static Logger log = Logger.getLogger(ExpensesApproverAction.class.getName());// for store log details
    private ExpensesApproverService expAppproverService = new ExpensesApproverDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private ExpensesApproverVO expApprover = new ExpensesApproverVO();
    private List<ExpensesApproverVO> expApproverList;
    private List<ExpensesApproverVO> expApproverListForMail;
    private String expAppModifiedDate = "";
    private Integer sameInsertExpApproverOpr;
    private EmployeeExpensesVO empExpenses;

    @SkipValidation
    public String getAllExpApprover() {
        expApproverList = expAppproverService.getAllExpensesApprover();
        return SUCCESS;
    }

    // To View the Search Page
    @SkipValidation
    public String expAppSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String expAppSearchResult() {
        expApproverList = expAppproverService.expAppSearchResult(expApprover);
        return SUCCESS;
    }

    public String getAllEmployeesApprover() {

        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expApprover.setHcmoEmployeeId(oEmp);
        expApproverList = expAppproverService.getAllEmployeesApprover(expApprover);
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeAllExpApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expApprover.hcmoEmployeeId.employeeId"));
        if (employeeId == null) {
            employeeId = expApprover.getHcmoEmployeeId().getEmployeeId();
        }
        expApproverList = expAppproverService.getEmployeeAllExpApprover(employeeId);
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateExpApprover() {
        if ((expApprover != null) && (expApprover.getApproverId() != null)) {
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpEmpInsertOrUpdateExpApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expApprover.hcmoEmployeeId.employeeId"));
        if ((expApprover != null) && (expApprover.getApproverId() != null) && (employeeId != null)) {
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpEmpInsertOrUpdateExpApproverSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expApprover.hcmoEmployeeId.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("expApprover.approverId"));
        if ((expApprover != null) && (expApprover.getApproverId() != null) && (employeeId != null)) {
            expApprover = expAppproverService.getEmpExpensesApprover(expApprover);
        }
        return SUCCESS;
    }

    @SkipValidation
    public String expensesApproverView() {
        if ((expApprover != null) && (expApprover.getApproverId() != null)) {
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String insertOrUpdateExpApprover() {
        if (expApprover.getApproverId() == null) {
        	log.debug("control enters into approver insertion");
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expApprover.setCreated(DateUtils.getCurrentDateTime());
            expApprover.setCreatedBy(oEmp);
            expApprover.setUpdatedBy(oEmp);
            expApprover.setIsActive(1);
            /*expApproverListForMail = expAppproverService.getEmployeeAllExpApprover(expApprover.getHcmoEmployeeId().getEmployeeId());

            // To prevent the Duplicate Operation again.
            sameInsertExpApproverOpr = expAppproverService.getExpApproverCount(expApprover);
            if (sameInsertExpApproverOpr > 0) {
                addActionError(getText("label.title.expapprover.sameOperationAgain"));
                return INPUT;
            }*/
            expAppproverService.insertExpensesApprover(expApprover);
            log.info("approver details added successfully");
            
            /*expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            String sSubject = getText("message.subject.expApp.add");
            int sessionEmpId = oEmp.getEmployeeId();
            int employeeId = expApprover.getHcmoEmployeeId().getEmployeeId();
            int approvingEmpId = expApprover.getApprovingEmployeeId().getEmployeeId();

            // logged in emp that he/she added to expense approver for another
            // employee
            // without having previous expense approver
            if (((sessionEmpId != employeeId) && (sessionEmpId != approvingEmpId) && (employeeId != approvingEmpId))
                && ((expApproverListForMail.size() == 0) || (expApproverListForMail.isEmpty() == false))) {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.add.addedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                // mail content to another emp
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                // mail content to approving emp
                mail(expApprover.getApprovingEmployeeId().getEmployeeId(), expApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                // logged in emp that he/she added to expense approver for
                // another employee
                // with having previous expense approver
                if ((expApproverListForMail.size() != 0)
                    || (expApproverListForMail.isEmpty() == false)) {
                    ExpensesApproverVO newExpensesApprover;
                    for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                        newExpensesApprover = it.next();
                        mailForApprovers(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApprovers.add.Status"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in emp that he/she added to emp for his/her own as
            // employee
            // without having previous expense approver
            else if (((sessionEmpId == employeeId) && (sessionEmpId != approvingEmpId))
                && ((expApproverListForMail.size() == 0) || (expApproverListForMail.isEmpty() == false))) // 3rd
            // step
            {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                // mail content to approving emp
                mail(expApprover.getApprovingEmployeeId().getEmployeeId(), expApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                // logged in emp that he/she added to emp for his/her own as
                // employee
                // with having previous expense approver
                if ((expApproverListForMail.size() != 0)
                    || (expApproverListForMail.isEmpty() == false)) {
                    ExpensesApproverVO newExpensesApprover;
                    for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                        newExpensesApprover = it.next();
                        mailForApproversToHisOwn(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApproversToHisOwn.add.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in emp that he/she added to expense approver for his/her
            // own as Approver
            // without having previous expense approver
            else if (((expApproverListForMail.size() == 0) || (expApproverListForMail.isEmpty() == false))
                && ((sessionEmpId == approvingEmpId) && (sessionEmpId != employeeId))) {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                // mail content to employee name list
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                // logged in emp that he/she added to expense approver for
                // his/her own as Approver
                // with having previous expense approver
                if ((expApproverListForMail.size() != 0)
                    || (expApproverListForMail.isEmpty() == false)) {
                    ExpensesApproverVO newExpensesApprover;
                    for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                        newExpensesApprover = it.next();
                        mailForApprovers(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApprovers.add.Status"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in emp that he/she added to expense approver for another
            // employee as Approver and employee
            // without having previous expense approver
            else if (((employeeId == approvingEmpId) && (sessionEmpId != employeeId) && (sessionEmpId != approvingEmpId))
                && ((expApproverListForMail.size() == 0) || (expApproverListForMail.isEmpty() == false))) {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.add.addedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
                // mail content to approving emp
                mail(expApprover.getApprovingEmployeeId().getEmployeeId(), expApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);

                // logged in emp that he/she added to expense approver for
                // another employee as Approver and employee
                // with having previous expense approver
                if ((expApproverListForMail.size() != 0)
                    || (expApproverListForMail.isEmpty() == false)) {
                    ExpensesApproverVO newExpensesApprover;
                    for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                        newExpensesApprover = it.next();
                        mailForApprovers(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApprovers.add.Status"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in emp that he/she added to expense approver for his/her
            // own as Approver and employee
            // without having previous expense approver
            else {
                if (((sessionEmpId == employeeId) && (sessionEmpId == approvingEmpId) && (employeeId == approvingEmpId))) {
                    // mail content to logged in emp
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    // logged in emp that he/she added to expense approver for
                    // his/her own as Approver and employee
                    // with having previous expense approver
                    if ((expApproverListForMail.size() != 0)
                        || (expApproverListForMail.isEmpty() == false)) {
                        ExpensesApproverVO newExpensesApprover;
                        for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                            newExpensesApprover = it.next();
                            mailForApproversToHisOwn(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApproversToHisOwn.add.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
            }
            // To Set Menu for Approving Employee
            if (expApprover.getApprovingEmployeeId().getEmployeeId().equals(oEmp.getEmployeeId())) {
                if (session.get("EXPENSES_APPROVER") != "BOTH") {
                    session.put("EXPENSES_APPROVER", "BOTH");
                }
            }*/
            addActionMessage(getText("Added Successfully"));
        } else {
        	log.debug("control enters into approver updation");
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expApprover.setUpdatedBy(oEmp);

            /*ExpensesApproverVO editExpApprover = new ExpensesApproverVO();
            editExpApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            editExpApprover.getApprovingEmployeeId().getEmployeeId();
            expApproverListForMail = expAppproverService.getEmployeeAllExpApprover(expApprover.getHcmoEmployeeId().getEmployeeId());

            // To prevent the Duplicate Operation again.
            sameInsertExpApproverOpr = expAppproverService.getExpApproverCount(expApprover);
            if (sameInsertExpApproverOpr > 0) {
                addActionError(getText("label.title.expapprover.sameOperationAgain"));
                return INPUT;
            }*/

            expAppproverService.updateExpensesApprover(expApprover);
            log.info("approver details updated successfully");
            /*editExpApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeId = expApprover.getHcmoEmployeeId().getEmployeeId();
            int approvingEmpId = expApprover.getApprovingEmployeeId().getEmployeeId();
            String sSubject = getText("message.subject.expApp.edit");

            // with having previous and old approver to all conditions
            if ((expApproverListForMail.size() != 0) || (expApproverListForMail.isEmpty() == false)) {
                ExpensesApproverVO newExpensesApprover;
                ExpensesApproverVO newExpensesApproverInnerLoop;
                for (Iterator<ExpensesApproverVO> it = expApproverListForMail.iterator(); it.hasNext();) {
                    newExpensesApprover = it.next();
                    int outerApproverId = newExpensesApprover.getApproverId();
                    int outerApproverEmpId = newExpensesApprover.getApprovingEmployeeId().getEmployeeId();

                    expApproverList = expAppproverService.getEmployeeAllExpApprover(expApprover.getHcmoEmployeeId().getEmployeeId());
                    for (Iterator<ExpensesApproverVO> ite = expApproverList.iterator(); ite.hasNext();) {
                        newExpensesApproverInnerLoop = ite.next();
                        int innerApproverId = newExpensesApproverInnerLoop.getApproverId();
                        int innerEmployeeId = newExpensesApproverInnerLoop.getApprovingEmployeeId().getEmployeeId();

                        if (outerApproverId == innerApproverId) {
                            if (outerApproverEmpId != innerEmployeeId) {
                                // Mail Content to Old Approver
                                mailForOldApprover(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.oldApprover.edit.changeStatus"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                if (!((sessionEmpId == approvingEmpId) && (sessionEmpId != employeeId))
                                    && !(employeeId == approvingEmpId)) {
                                    // Mail Content to New Approver
                                    mail(newExpensesApproverInnerLoop.getApprovingEmployeeId().getEmployeeId(), newExpensesApproverInnerLoop.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            } else if (outerApproverEmpId == innerEmployeeId) {
                                // Mail Content to Previous Approver
                                if (((sessionEmpId == employeeId) && (sessionEmpId != approvingEmpId))
                                    || ((sessionEmpId == employeeId)
                                        && (sessionEmpId == approvingEmpId) && (employeeId == approvingEmpId))) {
                                    mailForApproversToHisOwn(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApproversToHisOwn.edit.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                                } else {
                                    mailForApprovers(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApprovers.edit.Status"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }

                }
            }

            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            // logged in emp that he/she updated to leave approver for his/her
            // own as Approver and employee
            // without having previous expense approver
            if ((sessionEmpId == employeeId) && (sessionEmpId == approvingEmpId)
                && (employeeId == approvingEmpId)) {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
            }
            // logged in emp that he/she added to emp for his/her own as
            // employee
            // without having previous expense approver
            else if ((sessionEmpId == employeeId) && (sessionEmpId != approvingEmpId)) {
                // mail content to employee name list
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
            }
            // logged in emp that he/she added to expense approver for another
            // employee as Approver and employee
            // without having previous expense approver
            else if ((employeeId == approvingEmpId) && (sessionEmpId != employeeId)
                && (sessionEmpId != approvingEmpId)) {
                // mail content to employee name list
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.edit.updatedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
            }
            // logged in emp that he/she added to expense approver for his/her
            // own as Approver
            // without having previous expense approver
            else if ((sessionEmpId == approvingEmpId) && (sessionEmpId != employeeId)) {
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                // mail content to employee name list
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
            } else {
                // mail content to employee name list
                mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                // mail content to logged in emp
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.edit.updatedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
            }

            // To Set Menu for Approving Employee
            Integer ApproverCount = expAppproverService.checkExpensesApprover(oEmp.getEmployeeId(),oEmp.getClientId());
            if (ApproverCount > 0) {
                session.put("EXPENSES_APPROVER", "BOTH");
            } else {
                session.put("EXPENSES_APPROVER", "NON-APPROVER");
            }*/
            addActionMessage(getText("Updated Successfully"));
        }
        // For Drop down List
        loadValues.getAllExpAppName();
        return SUCCESS;
    }

    @SkipValidation
    public String deleteExpApprover() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expApprover.setUpdatedBy(oEmp);
        expAppproverService.deleteExpensesApprover(expApprover);

        expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
        int sessionEmpId = oEmp.getEmployeeId();
        int employeeId = expApprover.getHcmoEmployeeId().getEmployeeId();
        int approvingEmpId = expApprover.getApprovingEmployeeId().getEmployeeId();
        String sSubject = getText("message.subject.expApp.delete");
        expApproverList = expAppproverService.getEmployeeAllExpApprover(expApprover.getHcmoEmployeeId().getEmployeeId());

        // with having previous approver to all conditions
        if ((expApproverList.size() != 0) || (expApproverList.isEmpty() == false)) {
            ExpensesApproverVO newExpensesApprover;
            for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
                newExpensesApprover = it.next();
                if (((sessionEmpId == employeeId) && (sessionEmpId != approvingEmpId))
                    || ((sessionEmpId == employeeId) && (sessionEmpId == approvingEmpId) && (employeeId == approvingEmpId))) {
                    mailForApproversToHisOwn(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApproversToHisOwn.delete.Status"), getText("message.common.hisOwn.name"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    mailForApprovers(newExpensesApprover.getApprovingEmployeeId().getEmployeeId(), newExpensesApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.allOtherApprovers.delete.Status"), newExpensesApprover.getHcmoEmployeeId().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());

        // logged in emp that he/she added to emp for his/her own as employee
        // without having previous expense approver
        if ((sessionEmpId == employeeId) && (sessionEmpId != approvingEmpId)) {
            // mail content to employee name list
            mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
            // mail content to approving emp
            mail(expApprover.getApprovingEmployeeId().getEmployeeId(), expApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to expense approver for another
        // employee as Approver and employee
        // without having previous expense approver
        else if ((employeeId == approvingEmpId) && (sessionEmpId != employeeId)
            && (sessionEmpId != approvingEmpId)) {
            // mail content to employee name list
            mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.delete.deletedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to expense approver for his/her own
        // as Approver and employee
        // without having previous expense approver
        else if ((employeeId == approvingEmpId) && (sessionEmpId == employeeId)) {
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
        }
        // logged in emp that he/she added to emp for his/her own as approver
        // without having previous expense approver
        else if ((sessionEmpId == approvingEmpId) && (sessionEmpId != employeeId)) {
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
            // mail content to employee name list
            mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
        }
        // logged in emp that he/she added to expense approver for another
        // employee
        // without having previous expense approver
        else {
            // mail content to employee name list
            mail(expApprover.getHcmoEmployeeId().getEmployeeId(), expApprover.getHcmoEmployeeId().getEmpFirstName(), getText("expApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to approving emp
            mail(expApprover.getApprovingEmployeeId().getEmployeeId(), expApprover.getApprovingEmployeeId().getEmpFirstName(), getText("expApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            // mail content to logged in emp
            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expApp.loggedIn.delete.deletedBy"), expApprover.getHcmoEmployeeId().getEmpFirstName(), sSubject);
        }

        // For Drop down List
        loadValues.getAllExpAppName();

        // To Set Menu for Approving Employee
        Integer ApproverCount = expAppproverService.checkExpensesApprover(oEmp.getEmployeeId(),oEmp.getClientId());
        if (ApproverCount > 0) {
            session.put("EXPENSES_APPROVER", "BOTH");
        } else {
            session.put("EXPENSES_APPROVER", "NON-APPROVER");
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expenseApproverModdate = expApprover.getUpdated();
            expAppModifiedDate = formatter1.format(expenseApproverModdate);

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
                + getText("message.header.expapprover.name")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expapprover.approvingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expApprover.getApprovingEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expAppModifiedDate
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
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expenseApproverModdate = expApprover.getUpdated();
            expAppModifiedDate = formatter1.format(expenseApproverModdate);

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
                + getText("message.header.expapprover.name")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expapprover.approvingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expApprover.getApprovingEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expAppModifiedDate
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
            expApprover = expAppproverService.getExpensesApprover(expApprover.getApproverId());
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expenseApproverModdate = expApprover.getUpdated();
            expAppModifiedDate = formatter1.format(expenseApproverModdate);

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.HISOWN_PERSON;
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
                + getText("message.header.expapprover.name")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expApprover.getHcmoEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expapprover.approvingEmployee")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expApprover.getApprovingEmployeeId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expAppModifiedDate
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

    public ExpensesApproverVO getExpApprover() {
        return expApprover;
    }

    public void setExpApprover(ExpensesApproverVO expApprover) {
        this.expApprover = expApprover;
    }

    public List<ExpensesApproverVO> getExpApproverList() {
        return expApproverList;
    }

    public void setExpApprover(List<ExpensesApproverVO> expApproverList) {
        this.expApproverList = expApproverList;
    }

    public EmployeeExpensesVO getEmpExpenses() {
        return empExpenses;
    }

    public void setEmpExpenses(EmployeeExpensesVO empExpenses) {
        this.empExpenses = empExpenses;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

}
