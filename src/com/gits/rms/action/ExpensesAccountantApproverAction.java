
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
import com.gits.rms.service.ExpensesAccountantApproverDaoService;
import com.gits.rms.service.ExpensesAccountantApproverService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesAccountantApproverVO;
import com.gits.rms.vo.SignatureVO;

public class ExpensesAccountantApproverAction extends ActionSupport {
    private static final long serialVersionUID = -426828574947330776L;
    private ExpensesAccountantApproverService expAccountantService = new ExpensesAccountantApproverDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private ExpensesAccountantApproverVO expAccountantApprover;
    private List<ExpensesAccountantApproverVO> expAccountantApproverList;
    private List<ExpensesAccountantApproverVO> expAccountantApproverListForMail;
    private EmployeeExpensesVO empExpenses;
    private String expAccApproverModifiedDate = "";

    @SkipValidation
    public String getAllExpAccountantApprover() {
        expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String accountantSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String accountantSearchResult() {
        expAccountantApproverList = expAccountantService.accountantSearchResult(expAccountantApprover);
        return SUCCESS;
    }

    public String getAllEmployeesAccountantApprover() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expAccountantApprover.setExpensesAccountantId(oEmp);
        expAccountantApproverList = expAccountantService.getAllEmployeesAccountantApprover(expAccountantApprover);
        return SUCCESS;
    }

    @SkipValidation
    public String getEmployeeAllExpApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expApprover.hcmoEmployeeId.employeeId"));
        if (employeeId == null) {
            employeeId = expAccountantApprover.getExpensesAccountantId().getEmployeeId();
        }
        expAccountantApproverList = expAccountantService.getEmployeeAllExpAccountantApprover(employeeId);
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateExpAccountantApprover() {
        if ((expAccountantApprover != null)
            && (expAccountantApprover.getHcmoExpensesAccountantId() != null)) {
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String expAccountantApproverView() {
        if ((expAccountantApprover != null)
            && (expAccountantApprover.getHcmoExpensesAccountantId() != null)) {
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpEmpInsertOrUpdateExpAccountantApprover() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expAccountantApprover.hcmoEmployeeId.employeeId"));
        if ((expAccountantApprover != null)
            && (expAccountantApprover.getExpensesAccountantId() != null) && (employeeId != null)) {
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getExpensesAccountantId().getEmployeeId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpEmpInsertOrUpdateExpAccountantApproverSingle() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("expAccountantApprover.hcmoEmployeeId.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("expAccountantApprover.approverId"));
        if ((expAccountantApprover != null)
            && (expAccountantApprover.getExpensesAccountantId() != null) && (employeeId != null)) {
            expAccountantApprover = expAccountantService.getEmpExpensesAccountantApprover(expAccountantApprover);
        }
        return SUCCESS;
    }

    public String insertOrUpdateExpAccountantApprover() {
        try {
            if (expAccountantApprover.getHcmoExpensesAccountantId() == null) {

                expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
                if (!(expAccountantApproverList.isEmpty())) {
                    int accountantCount = expAccountantService.checkAccountantExists(expAccountantApprover);
                    if (accountantCount != 0) {
                        addActionError(getText("label.title.expenseaccountant.uniqueEmail"));
                        return INPUT;
                    }
                }

                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                expAccountantApprover.setCreated(DateUtils.getCurrentDateTime());
                expAccountantApprover.setCreatedBy(oEmp);
                expAccountantApprover.setUpdatedBy(oEmp);
                expAccountantApprover.setIsActive(1);
                expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
                expAccountantService.insertExpensesAccountantApprover(expAccountantApprover);
                expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeId = expAccountantApprover.getExpensesAccountantId().getEmployeeId();
                String sSubject = getText("message.subject.expAccApp.add");
                if ((expAccountantApproverList.size() != 0)
                    || (expAccountantApproverList.isEmpty() == false)) {
                    ExpensesAccountantApproverVO newExpAccApprover;
                    for (Iterator<ExpensesAccountantApproverVO> it = expAccountantApproverList.iterator(); it.hasNext();) {
                        newExpAccApprover = it.next();
                        mailForOtherAccountants(newExpAccApprover.getExpensesAccountantId().getEmployeeId(), newExpAccApprover.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.allOtherApprovers.add.Status"), oEmp.getEmpFirstName(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
                    }
                }

                // The Employee has added himself as Expense Accountant Approver
                if (sessionEmpId == employeeId) {
                    // Mail to the Logged-in Person
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // The employee has added another Expense Accountant Approver.
                else if (sessionEmpId != employeeId) {
                    // Mail to particular employee who is added as an Expense
                    // Accountant Approver.
                    mail(expAccountantApprover.getExpensesAccountantId().getEmployeeId(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    // Mail to Logged-in person.
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.add.addedBy"), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
                }

                // To Set Menu for Approving Employee
                if (expAccountantApprover.getExpensesAccountantId().getEmployeeId().equals(oEmp.getEmployeeId())) {
                    if (session.get("EXPENSES_ACCOUNTANT") != "ACCOUNTANT") {
                        session.put("EXPENSES_ACCOUNTANT", "ACCOUNTANT");
                    }
                }
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                expAccountantApprover.setUpdatedBy(oEmp);
                ExpensesAccountantApproverVO editExpAcc = new ExpensesAccountantApproverVO();
                editExpAcc = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
                editExpAcc.getExpensesAccountantId().getEmployeeId();
                String sSubject = getText("message.subject.expAccApp.edit");
                expAccountantApproverListForMail = expAccountantService.getAllExpensesAccountantApprover();
                expAccountantService.updateExpensesAccountantApprover(expAccountantApprover);

                if ((expAccountantApproverListForMail.size() != 0)
                    || (expAccountantApproverListForMail.isEmpty() == false)) {
                    ExpensesAccountantApproverVO newExpAccountant;
                    ExpensesAccountantApproverVO newExpAccountantInnerLoop;
                    for (Iterator<ExpensesAccountantApproverVO> it = expAccountantApproverListForMail.iterator(); it.hasNext();) {
                        newExpAccountant = it.next();
                        int outerApproverId = newExpAccountant.getHcmoExpensesAccountantId();
                        int outerApproverEmpId = newExpAccountant.getExpensesAccountantId().getEmployeeId();
                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeId = expAccountantApprover.getExpensesAccountantId().getEmployeeId();

                        expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
                        expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
                        for (Iterator<ExpensesAccountantApproverVO> ite = expAccountantApproverList.iterator(); ite.hasNext();) {
                            newExpAccountantInnerLoop = ite.next();
                            int innerApproverId = newExpAccountantInnerLoop.getHcmoExpensesAccountantId();
                            int innerEmployeeId = newExpAccountantInnerLoop.getExpensesAccountantId().getEmployeeId();

                            if (outerApproverId == innerApproverId) {
                                if (outerApproverEmpId != innerEmployeeId) {
                                    mailForOldAccountant(newExpAccountant.getExpensesAccountantId().getEmployeeId(), newExpAccountant.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.oldApprover.edit.changeStatus"), oEmp.getEmpFirstName(), sSubject);
                                    if (!(sessionEmpId == employeeId)) {
                                        mailFromTo(expAccountantApprover.getExpensesAccountantId().getEmployeeId(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.employee.edit.updateFromTo"), newExpAccountant.getExpensesAccountantId().getEmpFirstName(), newExpAccountantInnerLoop.getExpensesAccountantId().getEmpFirstName(), sSubject);
                                    }
                                } else if (outerApproverEmpId == innerEmployeeId) {
                                    mailForOtherAccountants(newExpAccountant.getExpensesAccountantId().getEmployeeId(), newExpAccountant.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.allOtherApprovers.edit.Status"), oEmp.getEmpFirstName(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }
                }
                expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
                int sessionEmpId = oEmp.getEmployeeId();
                int employeeId = expAccountantApprover.getExpensesAccountantId().getEmployeeId();
                if (sessionEmpId == employeeId) {
                    // Mail to the Logged-in Person
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // The employee has added another Expense Accountant Approver.
                else if (sessionEmpId != employeeId) {
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.edit.updatedBy"), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
                }

                // To Set Menu for Approving Employee
                Integer ExpenseAccountantCount = expAccountantService.checkExpensesAccountantApprover(oEmp.getEmployeeId());
                if (ExpenseAccountantCount > 0) {
                    session.put("EXPENSES_ACCOUNTANT", "ACCOUNTANT");
                } else {
                    session.put("EXPENSES_ACCOUNTANT", "NON-ACCOUNTANT");
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    @SkipValidation
    public String deleteExpAccountantApprover() {
        try {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expAccountantApprover.setUpdatedBy(oEmp);
            expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
            expAccountantService.deleteExpensesAccountantApprover(expAccountantApprover);
            expAccountantApproverList = expAccountantService.getAllExpensesAccountantApprover();
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeId = expAccountantApprover.getExpensesAccountantId().getEmployeeId();
            String sSubject = getText("message.subject.expAccApp.delete");
            if ((expAccountantApproverList.size() != 0)
                || (expAccountantApproverList.isEmpty() == false)) {
                ExpensesAccountantApproverVO newExpAccountant;
                for (Iterator<ExpensesAccountantApproverVO> it = expAccountantApproverList.iterator(); it.hasNext();) {
                    newExpAccountant = it.next();
                    mailForOtherAccountants(newExpAccountant.getExpensesAccountantId().getEmployeeId(), newExpAccountant.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.allOtherApprovers.delete.Status"), oEmp.getEmpFirstName(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
                }
            }

            // The Employee has deleted himself as Expense Accountant Approver
            if (sessionEmpId == employeeId) {
                // Mail to the Logged-in Person
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
            }
            // The employee has deleted another Expense Accountant Approver.
            else if (sessionEmpId != employeeId) {
                // Mail to particular employee who is deleted as an Expense
                // Accountant Approver.
                mail(expAccountantApprover.getExpensesAccountantId().getEmployeeId(), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), getText("expAccApp.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                // Mail to Logged-in person.
                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("expAccApp.loggedIn.delete.deletedBy"), expAccountantApprover.getExpensesAccountantId().getEmpFirstName(), sSubject);
            }

            // To Set Menu for Approving Employee
            Integer ExpenseAccountantCount = expAccountantService.checkExpensesAccountantApprover(oEmp.getEmployeeId());
            if (ExpenseAccountantCount > 0) {
                session.put("EXPENSES_ACCOUNTANT", "ACCOUNTANT");
            } else {
                session.put("EXPENSES_ACCOUNTANT", "NON-ACCOUNTANT");
            }
            addActionMessage(getText("Deleted Successfully"));
        } catch (Exception e) {

        }
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expAccountantAppModdate = expAccountantApprover.getUpdated();
            expAccApproverModifiedDate = formatter1.format(expAccountantAppModdate);

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
                + getText("label.header.expenseAccounantApprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.expenseAccountant")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + expAccountantApprover.getExpensesAccountantId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expAccApproverModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public void mailFromTo(Integer oFirstPerson, String DearEmp, String Message, String From, String To, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expAccountantAppModdate = expAccountantApprover.getUpdated();
            expAccApproverModifiedDate = formatter1.format(expAccountantAppModdate);

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), From);
            sMessage.replace(sMessage.lastIndexOf(sDummy), sMessage.lastIndexOf(sDummy)
                + sDummy.length(), To);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.expenseAccounantApprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.expenseAccountant")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + expAccountantApprover.getExpensesAccountantId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expAccApproverModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public void mailForOtherAccountants(Integer oFirstPerson, String DearEmp, String Message, String LoggedIn, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
            HCMOneMailer mailer = new HCMOneMailer();
            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expAccountantAppModdate = expAccountantApprover.getUpdated();
            expAccApproverModifiedDate = formatter1.format(expAccountantAppModdate);

            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));

            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);

            sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.expenseAccounantApprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.expenseAccountant")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + expAccountantApprover.getExpensesAccountantId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expAccApproverModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public void mailForOldAccountant(Integer oFirstPerson, String DearEmp, String Message, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");

            HCMOneMailer mailer = new HCMOneMailer();
            String sDummy = Constants.PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();

            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
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

    public void mailForAccDelete(Integer oFirstPerson, String DearEmp, String Message, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            expAccountantApprover = expAccountantService.getExpensesAccountantApprover(expAccountantApprover.getHcmoExpensesAccountantId());
            HCMOneMailer mailer = new HCMOneMailer();
            String sDummy = Constants.PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expAccountantAppModdate = expAccountantApprover.getUpdated();
            expAccApproverModifiedDate = formatter1.format(expAccountantAppModdate);

            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));

            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);

            sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.expenseAccounantApprover.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.expenseAccountant")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON
                + expAccountantApprover.getExpensesAccountantId().getEmpFirstName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expAccApproverModifiedDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

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

    public ExpensesAccountantApproverService getExpAccountantService() {
        return expAccountantService;
    }

    public void setExpAccountantService(ExpensesAccountantApproverService expAccountantService) {
        this.expAccountantService = expAccountantService;
    }

    public ExpensesAccountantApproverVO getExpAccountantApprover() {
        return expAccountantApprover;
    }

    public void setExpAccountantApprover(ExpensesAccountantApproverVO expAccountantApprover) {
        this.expAccountantApprover = expAccountantApprover;
    }

    public List<ExpensesAccountantApproverVO> getExpAccountantApproverList() {
        return expAccountantApproverList;
    }

    public void setExpAccountantApproverList(List<ExpensesAccountantApproverVO> expAccountantApproverList) {
        this.expAccountantApproverList = expAccountantApproverList;
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
