
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpensesAccountantApproverDaoService;
import com.gits.rms.service.ExpensesAccountantApproverService;
import com.gits.rms.service.ExpensesAccountantPayableDaoService;
import com.gits.rms.service.ExpensesAccountantPayableService;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesAccountantApproverVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.ExpensesDetailsVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.SignatureVO;

public class ExpensesAccountantPayableAction extends ActionSupport {
	private static final long serialVersionUID = 7286954318383624468L;
	private ExpensesAccountantPayableService expAccountPayableService = new ExpensesAccountantPayableDaoService();
    private EmployeeExpensesVO empExpenses = new EmployeeExpensesVO();
    private List<EmployeeExpensesVO> expenseList;
    private List<ExpensesDetailsVO> empExpenseDetailList;
    private List<ExpenseStatusTrackerVO> expenseStatusTrackerList;
    private ExpenseStatusTrackerVO expenseStatusStracker = new ExpenseStatusTrackerVO();
    private EmployeesService employeeService = new EmployeesDaoService();

    private CurrencyService currencyService = new CurrencyDaoService();
    private String currencyTypeValue;

    private List<ExpensesApproverVO> expApproverList;
    private ExpensesApproverService expApproverService = new ExpensesApproverDaoService();
    private List<ExpensesAccountantApproverVO> list = new ArrayList<ExpensesAccountantApproverVO>();
    private ExpensesAccountantApproverService expensesAccountantService = new ExpensesAccountantApproverDaoService();
    private MessageService messageService = new MessageDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private double reimbursementAmount;
    private String expenseDate = "";
    private String expenseReimburseDate = "";
    private String salFieldStringValue;
    private String curTypeValueForSalaryField;
    private String amountFieldStringValue;
    private String curTypeValueForAmountField;
    private EmployeeExpensesVO empExpForMail = new EmployeeExpensesVO();
    List<EmployeeExpensesVO> list1 = new ArrayList<EmployeeExpensesVO>();

    // Retrieve expenses for 'Accountant tab'
    @SkipValidation
    public String getAccountantTab() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expensestatus.processcompleted.value"));
        expenseList = expAccountPayableService.getAccountantTab(expenseStatusStracker, empExpenses);

        for (int i = 0; i < expenseList.size(); i++) {
            amountFieldStringValue = expenseList.get(i).getTotalAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                expenseList.get(i).setCurTypeValueForTotalAmountField(curTypeValueForAmountField);
            }
        }
        return SUCCESS;
    }

    // Retrieve Expenses details for particular expenses in for accountant tab
    @SkipValidation
    public String getAccountExpensesDetails() {
        Map session = ActionContext.getContext().getSession();
        session.put("id", empExpenses.getHcmoExpensesId());

        empExpenseDetailList = expAccountPayableService.getAccountExpensesDetails(empExpenses.getHcmoExpensesId());
        expenseStatusTrackerList = expAccountPayableService.getExpenseStatusTrackerForAccountant(empExpenses.getHcmoExpensesId());

        for (int i = 0; i < empExpenseDetailList.size(); i++) {
            salFieldStringValue = empExpenseDetailList.get(i).getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                empExpenseDetailList.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                empExpenseDetailList.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }
        }
        return SUCCESS;
    }

    // Inserted reimburse amount and get update in expenses table
    public String insertReimburse() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

        if (empExpenses.getReimbursementAmount() == null
            || empExpenses.getReimbursementAmount() < 0.0) {
            addActionError("Invalid Reimbursement Amount");
            return ERROR;
        }

        if (empExpenses.getReimbursementDate() == null) {
            addActionError("Invalid Reimbursement Date");
            return ERROR;
        }
        empExpenses.setStatus((getText("select.common.expenseapprover.reimburse.value")));
        empExpenses.setUpdatedBy(oEmp);
        expenseStatusStracker.setApprovalStatus((getText("select.common.expenseapprover.reimburse.value")));
        expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
        expenseStatusStracker.setCreatedBy(oEmp);
        expenseStatusStracker.setUpdatedBy(oEmp);
        expenseStatusStracker.setAccountantId(oEmp);
        expenseStatusStracker.setHcmoExpensesId(empExpenses);
        expAccountPayableService.insertReimburse(expenseStatusStracker, empExpenses);

        list1 = employeeService.getCurrentExpensesEmployee(empExpenses.getHcmoExpensesId());
        empExpForMail = employeeService.getCurrentExpensesEmployeeForMail(empExpenses.getHcmoExpensesId());

        EmployeesVO oFirstPerson = employeeService.getEmployees(list1.get(0).getHcmoEmployeeId().getEmployeeId());
        empExpenseDetailList = expAccountPayableService.getAccountExpensesDetails(empExpenses.getHcmoExpensesId());

        // For add currencyTypeValue value to Amount Field
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        }

        // To send alert mails
        MessageVO message = new MessageVO();
        message.setSender(oEmp);
        String sReceivers = new String();
        String sReceiversEmail = new String();
        Set<EmployeesVO> empArrList = new HashSet<EmployeesVO>();

        ExpensesApproverVO newExpenseApprover = null;
        expApproverList = expApproverService.getAllExpensesApprover();
        list = expensesAccountantService.getAllExpensesAccountantApprover();
        for (int i = 0; i < list.size(); i++) {
            message.setReceiver(list.get(i).getExpensesAccountantId().getEmpFirstName());
            message.setReceiverEmailId(list.get(i).getExpensesAccountantId().getEmpWorkEmail());
            String sMessage = getText("expense.approvedEmp.expenseRequest.Reimburse");
            sMessage = StringUtils.replace(sMessage, "<person>", oFirstPerson.getEmpFirstName());
            message.setMessage(sMessage);

            String sSubject = getText("message.subject.expense.reimburse");
            message.setSubject(sSubject);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setIsActive(1);
            String AccName = list.get(i).getExpensesAccountantId().getEmpFirstName();
            mailToAccountant(message, oEmp, AccName, sSubject);
        }

        for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
            newExpenseApprover = it.next();

            int empExpensesInt;
            int newExpAppInt;
            empExpensesInt = list1.get(0).getHcmoEmployeeId().getEmployeeId();
            newExpAppInt = newExpenseApprover.getHcmoEmployeeId().getEmployeeId();
            if ((empExpensesInt) == (newExpAppInt)) {
                sReceivers += newExpenseApprover.getApprovingEmployeeId().getEmployeeId() + ",";
                sReceiversEmail += newExpenseApprover.getApprovingEmployeeId().getEmpWorkEmail()
                    + ",";
                empArrList.add(newExpenseApprover.getHcmoEmployeeId());
                empArrList.add(newExpenseApprover.getApprovingEmployeeId());
            }
        }
        sReceivers += oFirstPerson.getEmployeeId() + "," + oEmp.getEmployeeId();
        sReceiversEmail += oFirstPerson.getEmpWorkEmail() + "," + oEmp.getEmpWorkEmail();
        String sSubject = getText("message.subject.expense.reimburse");
        sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
        sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
        message.setSubject(sSubject);
        String sMessage = getText("expense.employee.expenseRequest.Reimburse");
        sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
        message.setMessage(sMessage);
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setCreated(DateUtils.getCurrentDateTime());
        message.setCreatedBy(oEmp);
        message.setUpdatedBy(oEmp);
        message.setIsActive(1);

        for (EmployeesVO emp : empArrList) {
            if (emp.getEmpFirstName().equals(oFirstPerson.getEmpFirstName())
                && emp.getEmpLastName().equals(oFirstPerson.getEmpLastName())) {
                sMessage = getText("expense.employee.expenseRequest.Reimburse");
                sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
            } else {
                sMessage = getText("expense.allApprovers.expenseRequest.Reimburse");
                sMessage = StringUtils.replace(sMessage, "<employee_person>", oFirstPerson.getEmpFirstName());
                sMessage = StringUtils.replace(sMessage, "<loggedIn_person>", oEmp.getEmpFirstName());
            }
            message.setMessage(sMessage);

            message.setReceiver(emp.getEmpFirstName());
            message.setReceiverEmailId(emp.getEmpWorkEmail());
            sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
            sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
            message.setSubject(sSubject);
            sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());

            message.setMessage(sMessage);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(emp);
            message.setUpdatedBy(emp);
            message.setIsActive(1);
            mail(message, emp, sSubject);
        }
        addActionMessage((getText("select.common.expensereimburse.value")));
        return SUCCESS;
    }

    public EmployeeExpensesVO getEmpExpenses() {
        return empExpenses;
    }

    public void setEmpExpenses(EmployeeExpensesVO empExpenses) {
        this.empExpenses = empExpenses;
    }

    public List<EmployeeExpensesVO> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<EmployeeExpensesVO> expenseList) {
        this.expenseList = expenseList;
    }

    public ExpenseStatusTrackerVO getExpenseStatusStracker() {
        return expenseStatusStracker;
    }

    public void setExpenseStatusStracker(ExpenseStatusTrackerVO expenseStatusStracker) {
        this.expenseStatusStracker = expenseStatusStracker;
    }

    public List<ExpenseStatusTrackerVO> getExpenseStatusTrackerList() {
        return expenseStatusTrackerList;
    }

    public void setExpenseStatusTrackerList(List<ExpenseStatusTrackerVO> expenseStatusTrackerList) {
        this.expenseStatusTrackerList = expenseStatusTrackerList;
    }

    public List<ExpensesDetailsVO> getEmpExpenseDetailList() {
        return empExpenseDetailList;
    }

    public void setEmpExpenseDetailList(List<ExpensesDetailsVO> empExpenseDetailList) {
        this.empExpenseDetailList = empExpenseDetailList;
    }

    public String mailToAccountant(MessageVO message, EmployeesVO empVo, String AccName, String sSubject) {
        empExpForMail = employeeService.getCurrentExpensesEmployeeForMail(empExpenses.getHcmoExpensesId());
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        // For Reimbursement Amount Date Field
        DateFormat dateFormatForRe = new SimpleDateFormat("MM/dd/yyyy");
        Date expeDateForReimb = empExpForMail.getReimbursementDate();
        expenseReimburseDate = dateFormatForRe.format(expeDateForReimb);

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), AccName).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE
            + message.getMessage());
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        Iterator<ExpensesDetailsVO> itr = empExpenseDetailList.iterator();
        while (itr.hasNext()) {
            ExpensesDetailsVO expensesDetails1 = new ExpensesDetailsVO();
            expensesDetails1 = itr.next();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date expeDate = expensesDetails1.getExpensesDate();
            expenseDate = dateFormat.format(expeDate);
            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.expenseReqModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG_WIDTH
                + getText("label.header.expenseforapproval.expenseDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expenseDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.project.projectName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expensesDetails1.getProjectId().getProjectName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expenseType.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expensesDetails1.getHcmoExpensesTypeId().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.directDebit.amount") + " "
                + session.get("CURRENCY_TYPE_VALUE") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + " "
                + session.get("CURRENCY_TYPE_VALUE") + expensesDetails1.getAmount()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (expensesDetails1.getNote().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.form.fields.newexpenses.note")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + expensesDetails1.getNote() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            if (expensesDetails1.getDescription().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.form.fields.common.description")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + expensesDetails1.getDescription() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_BREAK);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.expenseReimburseModule.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG_WIDTH
            + getText("label.header.empexpenses.reimbursementDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + expenseReimburseDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empexpenses.reimbursementAmount") + " "
            + session.get("CURRENCY_TYPE_VALUE") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + " "
            + session.get("CURRENCY_TYPE_VALUE") + empExpForMail.getReimbursementAmount() + "0"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empExpForMail.getAccountingNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empexpenses.accountingNotes")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empExpForMail.getAccountingNotes()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        }
        sMessage.append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);

        sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
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
        message.setMessage(sMessage.toString());
        message.setReceiver(empVo.getEmployeeId().toString());
        mailer.sendMail(message.getSender().getEmpWorkEmail(), empVo.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), "", "");
        messageService.insertMessage(message);
        return sMessage.toString();
    }

    public String mail(MessageVO message, EmployeesVO empVo, String sSubject) {
        empExpForMail = employeeService.getCurrentExpensesEmployeeForMail(empExpenses.getHcmoExpensesId());
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // For Reimbursement Amount Date Field
        DateFormat dateFormatForRe = new SimpleDateFormat("MM/dd/yyyy");
        Date expeDateForReimb = empExpForMail.getReimbursementDate();
        expenseReimburseDate = dateFormatForRe.format(expeDateForReimb);

        HCMOneMailer mailer = new HCMOneMailer();
        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), empVo.getEmpFirstName()).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE
            + message.getMessage());
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        Iterator<ExpensesDetailsVO> itr = empExpenseDetailList.iterator();
        while (itr.hasNext()) {
            ExpensesDetailsVO expensesDetails1 = new ExpensesDetailsVO();
            expensesDetails1 = itr.next();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date expeDate = expensesDetails1.getExpensesDate();
            expenseDate = dateFormat.format(expeDate);
            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.expenseReqModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG_WIDTH
                + getText("label.header.expenseforapproval.expenseDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expenseDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.project.projectName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expensesDetails1.getProjectId().getProjectName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expenseType.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expensesDetails1.getHcmoExpensesTypeId().getName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.form.fields.directDebit.amount") + " "
                + session.get("CURRENCY_TYPE_VALUE") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + " "
                + session.get("CURRENCY_TYPE_VALUE") + expensesDetails1.getAmount()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (expensesDetails1.getNote().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.form.fields.newexpenses.note")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + expensesDetails1.getNote() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            if (expensesDetails1.getDescription().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.form.fields.common.description")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + expensesDetails1.getDescription() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG
                + HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_BREAK);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.expenseReimburseModule.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG_WIDTH
            + getText("label.header.empexpenses.reimbursementDate")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + expenseReimburseDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.empexpenses.reimbursementAmount") + " "
            + session.get("CURRENCY_TYPE_VALUE") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + " "
            + session.get("CURRENCY_TYPE_VALUE") + empExpForMail.getReimbursementAmount() + "0"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (empExpForMail.getAccountingNotes().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.empexpenses.accountingNotes")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empExpForMail.getAccountingNotes()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        }
        sMessage.append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);

        sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
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
        message.setMessage(sMessage.toString());
        message.setReceiver(empVo.getEmployeeId().toString());
        mailer.sendMail(message.getSender().getEmpWorkEmail(), empVo.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), "", "");
        messageService.insertMessage(message);
        return sMessage.toString();
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public String getSalFieldStringValue() {
        return salFieldStringValue;
    }

    public void setSalFieldStringValue(String salFieldStringValue) {
        this.salFieldStringValue = salFieldStringValue;
    }

    public String getCurTypeValueForSalaryField() {
        return curTypeValueForSalaryField;
    }

    public void setCurTypeValueForSalaryField(String curTypeValueForSalaryField) {
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setEmpExpForMail(EmployeeExpensesVO empExpForMail) {
        this.empExpForMail = empExpForMail;
    }

    public EmployeeExpensesVO getEmpExpForMail() {
        return empExpForMail;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
    }

    public void setAmountFieldStringValue(String amountFieldStringValue) {
        this.amountFieldStringValue = amountFieldStringValue;
    }

    public String getAmountFieldStringValue() {
        return amountFieldStringValue;
    }

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}
}
