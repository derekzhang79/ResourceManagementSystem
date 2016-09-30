
package com.gits.rms.action;

import java.math.BigDecimal;
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
import com.gits.rms.service.EmployeeExpensesDaoService;
import com.gits.rms.service.EmployeeExpensesService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpenseStatusTrackerDaoService;
import com.gits.rms.service.ExpenseStatusTrackerService;
import com.gits.rms.service.ExpensesAccountantApproverDaoService;
import com.gits.rms.service.ExpensesAccountantApproverService;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.ExpensesAttachmentDaoService;
import com.gits.rms.service.ExpensesAttachmentService;
import com.gits.rms.service.ExpensesDetailsDaoService;
import com.gits.rms.service.ExpensesDetailsService;
import com.gits.rms.service.ExpensesTypeDaoService;
import com.gits.rms.service.ExpensesTypeService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpenseStatusTrackerVO;
import com.gits.rms.vo.ExpensesAccountantApproverVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.ExpensesAttachmentVO;
import com.gits.rms.vo.ExpensesDetailsVO;
import com.gits.rms.vo.ExpensesTypeVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.SignatureVO;

public class ExpensesForApprovalAction extends ActionSupport {
	private static final long serialVersionUID = 9035713606166416835L;
	private EmployeeExpensesService empExpensesService = new EmployeeExpensesDaoService();
    private ExpensesTypeService expTypeService = new ExpensesTypeDaoService();
    private ProjectService proService = new ProjectDaoService();
    private ExpensesAttachmentService expensesAttachService = new ExpensesAttachmentDaoService();
    private ExpensesAccountantApproverService expensesAccountantService = new ExpensesAccountantApproverDaoService();

    private EmployeeExpensesVO empExpenses = new EmployeeExpensesVO();
    private EmployeeExpensesService employeeExpensesService = new EmployeeExpensesDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private List<EmployeeExpensesVO> expenseList;
    private List<ExpensesDetailsVO> empExpenseDetailList;
    private List<EmployeeExpensesVO> expenseApproveList;
    private List<ExpenseStatusTrackerVO> expenseStatusTrackerList;

    private ExpenseStatusTrackerVO expenseStatusStracker = new ExpenseStatusTrackerVO();
    private ExpensesDetailsVO expenseDetails;
    private ExpensesDetailsService detailsService = new ExpensesDetailsDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private CurrencyService currencyService = new CurrencyDaoService();
    private String currencyTypeValue;
    private String curTypeValForExpenseAmtField;
    private String amountFieldStringValue;
    private String curTypeValueForAmountField;

    private ExpensesTypeVO expTypeObj;
    private ProjectVO proNameObj;
    private ExpensesAttachmentVO expAttach;
    private ExpensesAttachmentVO expAttObj;
    private EmployeeExpensesVO newExpObj;
    private ExpensesAttachmentVO newExpAttObj;
    private List<ExpensesAttachmentVO> expensesAttachList;
    private List<ExpensesAttachmentVO> expensesAttachEmpList;
    private List<ExpensesAttachmentVO> expAttachIdList;
    private List<ExpensesAttachmentVO> expAttEmpListForReview;
    private HttpServletRequest request;
    private List<ExpensesApproverVO> expApproverList;
    private ExpensesApproverService expApproverService = new ExpensesApproverDaoService();
    private MessageService messageService = new MessageDaoService();

    private String total;
    private String expenseDate = "";
    private List<ExpensesAccountantApproverVO> list = new ArrayList<ExpensesAccountantApproverVO>();
    private ExpensesAccountantApproverVO expAccApproverObj;
    private String forApprovalCount = "";
    private String forApprovalToday = "";
    private String forApprovalThreeDays = "";
    private String forApprovalOneWeek = "";
    private ExpenseStatusTrackerService expenseStatusTrackerService = new ExpenseStatusTrackerDaoService();
    private String attachFile = "";

    // Insert All expenses details
    @SkipValidation
    public String insertEmployeeExpensesDetails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        request = ServletActionContext.getRequest();
        String resTotal = (String) request.getParameter("totalAmt");
        BigDecimal totalAmount = new BigDecimal(resTotal);
        empExpenses.setTotalAmount(totalAmount);
        empExpenses.setStatus((getText("select.common.expensestatus.inprocess.value")));
        empExpenses.setCreated(DateUtils.getCurrentDateTime());
        empExpenses.setCreatedBy(oEmp);
        empExpenses.setUpdatedBy(oEmp);
        empExpenses.setReimbursementAmount(0.00);
        empExpenses.setIsActive(1);
        empExpenses.setHcmoEmployeeId(oEmp);
        empExpenses.setCreatedDate(DateUtils.getCurrentDateTime());
        List expApprList = expApproverService.getEmployeeAllExpApprover(oEmp.getEmployeeId());
        if (expApprList.isEmpty()) {
            addActionError(getText("label.header.expRequest.checkApprover"));
            return INPUT;
        }
        if (empExpenses.getTotalAmount().toString().equals("0.00")) {
            addActionError(getText("Please Add Expense Details then you can Rise the Expense"));
            return INPUT;
        }
        empExpensesService.insertEmployeeExpenses(empExpenses); // inserted
                                                                // major
                                                                // expenses
                                                                // table
        String resDescArray = (String) request.getParameter("desArrayHide");
        String resNoteArray = (String) request.getParameter("noteArrayHide");
        String resAmountArray = (String) request.getParameter("amountArrayHide");
        String resExpdateArray = (String) request.getParameter("expDateArrayHide");
        String resExpTypeArray = (String) request.getParameter("expTypeArrayHide");
        String resProjectArray = (String) request.getParameter("proNameArrayHide");
        String[] descArray = new String[0];
        String[] noteArray = new String[0];
        String[] amountArray = new String[0];
        String[] expDateArray = new String[0];
        String[] expTypeArray = new String[0];
        String[] projectArray = new String[0];

        if (resDescArray == null || resDescArray.equals("")) {
        } else {
            String splitDescArray = resDescArray.substring(0, resDescArray.lastIndexOf(','));
            descArray = splitDescArray.split(",");
        }
        if (resNoteArray == null || resNoteArray.equals("")) {
        } else {
            String splitNoteArray = resNoteArray.substring(0, resNoteArray.lastIndexOf(','));
            noteArray = splitNoteArray.split(",");
        }
        if (resAmountArray == null || resAmountArray.equals("")) {
        } else {
            String splitAmountArray = resAmountArray.substring(0, resAmountArray.lastIndexOf(','));
            amountArray = splitAmountArray.split(",");
        }
        if (resExpdateArray == null || resExpdateArray.equals("")) {
        } else {
            String splitExpDateArray = resExpdateArray.substring(0, resExpdateArray.lastIndexOf(','));
            expDateArray = splitExpDateArray.split(",");
        }
        if (resExpTypeArray == null || resExpTypeArray.equals("")) {
        } else {
            String splitExpTypeArray = resExpTypeArray.substring(0, resExpTypeArray.lastIndexOf(','));
            expTypeArray = splitExpTypeArray.split(",");
        }
        if (resProjectArray == null || resProjectArray.equals("")) {
        } else {
            String splitProjectArray = resProjectArray.substring(0, resProjectArray.lastIndexOf(','));
            projectArray = splitProjectArray.split(",");
        }
        for (int i = 0; i < projectArray.length; i++) {
            expenseDetails.setExpensesDate(new Date(expDateArray[i]));
            BigDecimal amtArray = new BigDecimal(amountArray[i]);
            expenseDetails.setAmount(amtArray);
            expenseDetails.setHcmoExpensesId(empExpenses);
            ExpensesTypeVO exp = new ExpensesTypeVO();
            expTypeObj = expTypeService.getExpensesTypeId(expTypeArray[i]);
            exp.setHcmoExpensesTypeId(expTypeObj.getHcmoExpensesTypeId());
            expenseDetails.setHcmoExpensesTypeId(exp);

            ProjectVO proj = new ProjectVO();
            proNameObj = proService.getIdForProName(projectArray[i]);
            proj.setProjectId(proNameObj.getProjectId());
            expenseDetails.setProjectId(proj);
            if (descArray.length == 0) {
                expenseDetails.setDescription("");
            } else {
                if (descArray[i].equals(" ")) {
                    expenseDetails.setDescription("");
                } else {
                    expenseDetails.setDescription(descArray[i]);
                }
            }
            if (noteArray.length == 0) {
                expenseDetails.setNote("");
            } else {
                if (noteArray[i].equals(" ")) {
                    expenseDetails.setNote("");
                } else {
                    expenseDetails.setNote(noteArray[i]);
                }
            }
            expenseDetails.setCreated(DateUtils.getCurrentDateTime());
            expenseDetails.setCreatedBy(oEmp);
            expenseDetails.setUpdatedBy(oEmp);
            expenseDetails.setIsActive(1);
            // inserted all expenses details in detail table
            detailsService.insertExpDetails(expenseDetails);
        }
        expenseStatusStracker.setApprovalStatus((getText("select.common.expenseapprover.forapproval.value")));
        expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
        expenseStatusStracker.setCreatedBy(oEmp);
        expenseStatusStracker.setUpdatedBy(oEmp);
        expenseStatusStracker.setHcmoExpensesId(empExpenses);
        // insert status tracker values
        empExpensesService.insertStatusTracker(expenseStatusStracker);
        if (expenseStatusStracker.getHcmoExpensesId().getHcmoExpensesId() == null) {
            addActionError(getText("There is no Expense raised"));
            return INPUT;
        }
        addActionMessage((getText("select.common.expenseadded.value")));
        // Get Inserted expenses for upload expenses attachments
        empExpenses = empExpensesService.getInsertedExpensesInfo(empExpenses);
        empExpenseDetailList = detailsService.getExpensesEmpDetails(empExpenses.getHcmoExpensesId());
        EmployeesVO oFirstPerson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());
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
        String approverEmail = new String();
        Set<EmployeesVO> empArrList = new HashSet<EmployeesVO>();
        ExpensesApproverVO newExpenseApprover = null;
        expApproverList = expApproverService.getAllExpensesApprover();
        for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
            newExpenseApprover = it.next();
            int empExpensesInt;
            int newExpAppInt;
            empExpensesInt = empExpenses.getHcmoEmployeeId().getEmployeeId();
            newExpAppInt = newExpenseApprover.getHcmoEmployeeId().getEmployeeId();
            if ((empExpensesInt) == (newExpAppInt)) {
                sReceivers += newExpenseApprover.getApprovingEmployeeId().getEmployeeId() + ",";
                sReceiversEmail += newExpenseApprover.getApprovingEmployeeId().getEmpWorkEmail()
                    + ",";
                empArrList.add(newExpenseApprover.getApprovingEmployeeId());
                empArrList.add(newExpenseApprover.getHcmoEmployeeId());
            }
        }
        sReceivers += oFirstPerson.getEmployeeId() + "," + oEmp.getEmployeeId();
        String sSubject = getText("message.subject.expense.add");
        sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
        sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
        message.setSubject(sSubject);
        String sMessage = getText("expense.employee.expenseRequest.add");
        sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
        message.setMessage(sMessage);
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setCreated(DateUtils.getCurrentDateTime());
        message.setCreatedBy(oEmp);
        message.setUpdatedBy(oEmp);
        message.setSenderDelete(1);
        message.setReceiverDelete(1);
        message.setIsRead(1);
        message.setIsNewMail(1);
        message.setIsActive(1);
        for (EmployeesVO emp : empArrList) {
            if (emp.getEmpFirstName().equals(oEmp.getEmpFirstName())
                && emp.getEmpLastName().equals(oEmp.getEmpLastName())) {
                sMessage = getText("expense.employee.expenseRequest.add");
                sMessage = StringUtils.replace(sMessage, "<person>", oFirstPerson.getEmpFirstName());
            } else {
                sMessage = getText("expense.allApprovers.expenseRequest.add");
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
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            mailForExpenseReq(message, emp, sSubject);
        }
        return SUCCESS;
    }

    // Method is used for 'For Approval Tab' get all expenses for approval
    @SkipValidation
    public String getAllExpensesForApproval() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        empExpenses.setStatus((getText("select.common.expensestatus.inprocess.value")));
        expenseStatusStracker.setApprovalStatus((getText("select.common.expenseapprover.approved.value")));
        expenseList = empExpensesService.getAllExpensesForApproval(empExpenses); // to
                                                                                 // retrieve
                                                                                 // waiting
                                                                                 // for
                                                                                 // approval
                                                                                 // expenses
        expenseApproveList = empExpensesService.getAllExpensesApp(empExpenses); // to
                                                                                // retrieve
                                                                                // submit
                                                                                // to
                                                                                // next
                                                                                // leveled
                                                                                // expenses
        expensesAttachEmpList = expensesAttachService.viewExpensesAttachment(expenseList); // to
                                                                                           // retrive
                                                                                           // attachments
                                                                                           // for
                                                                                           // the
                                                                                           // expenses
                                                                                           // if
                                                                                           // any

        // Add currency type value with Total Amount values like ($) 6000.00
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
        for (Iterator<EmployeeExpensesVO> it = expenseList.iterator(); it.hasNext();) {
            newExpObj = it.next();
            int expIntId = newExpObj.getHcmoExpensesId();
            for (Iterator<ExpensesAttachmentVO> itOne = expensesAttachEmpList.iterator(); itOne.hasNext();) {
                newExpAttObj = itOne.next();
                int expIntIdForAttachment = newExpAttObj.getHcmoExpensesId().getHcmoExpensesId();
                if (expIntId == expIntIdForAttachment) {
                    newExpObj.setExpAttachId(newExpAttObj.getHcmoExpensesId().toString());
                } else {
                    newExpObj.setExpAttachFileName("");
                    newExpObj.setExpAttachId("0");
                }
            }
        }
        return SUCCESS;
    }

    // Retrieve particular expenses details inside 'For Approval Tab'
    @SkipValidation
    public String getExpensesForApprovalEmployee() {
        empExpenseDetailList = empExpensesService.getExpensesForApprovalEmployee(empExpenses.getHcmoExpensesId());
        expAttachIdList = expensesAttachService.getAllAttachmentId(empExpenses.getHcmoExpensesId());
        for (int i = 0; i < empExpenseDetailList.size(); i++) {
            amountFieldStringValue = empExpenseDetailList.get(i).getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                empExpenseDetailList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                empExpenseDetailList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            }
        }
        return SUCCESS;
    }

    // This method execute when click "Approve and Submit to Accountant Button "
    // in 'For Approval Tab'
    @SkipValidation
    public String approveAndSubmitToAccount() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses = employeeExpensesService.getEmployeeExpenses(empExpenses.getHcmoExpensesId());
        empExpenses.setStatus((getText("select.common.expensestatus.processcompleted.value")));
        empExpenses.setUpdatedBy(oEmp);
        expenseStatusStracker.setApprovalStatus((getText("select.common.expenseapprover.approved.value")));
        expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
        expenseStatusStracker.setCreatedBy(oEmp);
        expenseStatusStracker.setUpdatedBy(oEmp);
        expenseStatusStracker.setApproverId(oEmp);
        expenseStatusStracker.setHcmoExpensesId(empExpenses);
        empExpensesService.approveAndSubmitToAccount(expenseStatusStracker, empExpenses); // updated
                                                                                          // expenses
                                                                                          // table
                                                                                          // as
                                                                                          // approved
                                                                                          // and
                                                                                          // inserted
                                                                                          // status
                                                                                          // tracker
                                                                                          // info
        empExpenseDetailList = detailsService.getExpensesEmpDetails(empExpenses.getHcmoExpensesId());
        EmployeesVO oFirstPerson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());
        EmployeesVO updateperson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());

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
        Set set = new HashSet();
        Set mailIDs = new HashSet();
        ExpensesApproverVO newExpenseApprover = null;
        expApproverList = expApproverService.getAllExpensesApprover();
        list = expensesAccountantService.getAllExpensesAccountantApprover();

        for (Iterator<ExpensesAccountantApproverVO> it = list.iterator(); it.hasNext();) {
            expAccApproverObj = it.next();
            message.setReceiver(expAccApproverObj.getExpensesAccountantId().getEmpFirstName());
            message.setReceiverEmailId(expAccApproverObj.getExpensesAccountantId().getEmpWorkEmail());
            String sMessage = getText("expense.allApprovers.expenseRequest.approved");
            sMessage = StringUtils.replace(sMessage, "<person>", expAccApproverObj.getExpensesAccountantId().getEmpFirstName());
            sMessage = StringUtils.replace(sMessage, "<employee_person>", oFirstPerson.getEmpFirstName());
            sMessage = StringUtils.replace(sMessage, "<loggedIn_person>", oEmp.getEmpFirstName());
            message.setMessage(sMessage);
            String sSubject = getText("message.subject.expense.approvedAndSubmitToAcc");
            message.setSubject(sSubject);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setCreated(DateUtils.getCurrentDateTime());
            EmployeesVO emp = employeeService.getEmployees(expAccApproverObj.getExpensesAccountantId().getEmployeeId());
            message.setCreatedBy(emp);
            message.setUpdatedBy(emp);
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            String AccName = expAccApproverObj.getExpensesAccountantId().getEmpFirstName();
            mailToAccountant(message, emp, AccName, sSubject);
        }

        for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
            newExpenseApprover = it.next();
            set.add(newExpenseApprover.getApprovingEmployeeId().getEmployeeId());
            int empExpensesInt;
            int newExpAppInt;
            empExpensesInt = empExpenses.getHcmoEmployeeId().getEmployeeId();
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
        String sSubject = getText("message.subject.expense.approved");
        sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
        sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
        message.setSubject(sSubject);
        String sMessage = getText("expense.employee.expenseRequest.approved");
        sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
        message.setMessage(sMessage);
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setCreated(DateUtils.getCurrentDateTime());
        message.setCreatedBy(oEmp);
        message.setUpdatedBy(oEmp);
        message.setSenderDelete(1);
        message.setReceiverDelete(1);
        message.setIsRead(1);
        message.setIsNewMail(1);
        message.setIsActive(1);
        for (EmployeesVO emp : empArrList) {
            if (emp.getEmpFirstName().equals(oEmp.getEmpFirstName())
                && emp.getEmpLastName().equals(oEmp.getEmpLastName())) {
                sMessage = getText("expense.approvedEmp.expenseRequest.approved");
                sMessage = StringUtils.replace(sMessage, "<person>", oFirstPerson.getEmpFirstName());
            } else if (emp.getEmpFirstName().equals(oFirstPerson.getEmpFirstName())) {
                sMessage = getText("expense.employee.expenseRequest.approved");
                sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
            } else {
                sMessage = getText("expense.allApprovers.expenseRequest.approved");
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
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            mailForExpenseApprove(message, emp, sSubject);
        }
        addActionMessage((getText("select.common.expenseapproved.value")));
        return SUCCESS;
    }

    // Retrieve expenses rejected notes page
    @SkipValidation
    public String enterRejectedNotes() {
        return SUCCESS;
    }

    // Retrieve expenses Next level approver page
    @SkipValidation
    public String nextLevelApprover() {
        return SUCCESS;
    }

    // It rejected the expenses
    public String rejectEmployeeExpense() {
        try {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            empExpenses = employeeExpensesService.getEmployeeExpenses(empExpenses.getHcmoExpensesId());
            empExpenses.setStatus((getText("select.common.expenseapprover.rejected.value")));
            empExpenses.setUpdatedBy(oEmp);
            expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.rejected.value"));
            expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
            expenseStatusStracker.setCreatedBy(oEmp);
            expenseStatusStracker.setUpdatedBy(oEmp);
            expenseStatusStracker.setRejectedId(oEmp);
            expenseStatusStracker.setHcmoExpensesId(empExpenses);
            empExpensesService.rejectEmployeeExpense(expenseStatusStracker, empExpenses); // retrieved
                                                                                          // expenses
            Set<EmployeesVO> empArrList = new HashSet<EmployeesVO>();
            // Set set = new HashSet();
            EmployeesVO oFirstPerson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());

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
            empExpenseDetailList = detailsService.getExpensesEmpDetails(empExpenses.getHcmoExpensesId());
            MessageVO message = new MessageVO();
            message.setSender(oEmp);
            String sReceivers = new String();
            String sReceiversEmail = new String();

            ExpensesApproverVO newExpenseApprover = null;
            expApproverList = expApproverService.getAllExpensesApprover();
            for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
                newExpenseApprover = it.next();
                int empExpensesInt;
                int newExpAppInt;
                empExpensesInt = empExpenses.getHcmoEmployeeId().getEmployeeId();
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
            String sSubject = getText("message.subject.expense.rejected");
            sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
            sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
            message.setSubject(sSubject);
            String sMessage = getText("expense.employee.expenseRequest.rejected");
            sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
            message.setMessage(sMessage);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            // mail(message,oFirstPerson);
            for (EmployeesVO emp : empArrList) {
                if (emp.getEmpFirstName().equals(oEmp.getEmpFirstName())
                    && emp.getEmpLastName().equals(oEmp.getEmpLastName())) {
                    sMessage = getText("expense.approvedEmp.expenseRequest.rejected");
                    sMessage = StringUtils.replace(sMessage, "<person>", oFirstPerson.getEmpFirstName());
                } else if (emp.getEmpFirstName().equals(oFirstPerson.getEmpFirstName())) {
                    sMessage = getText("expense.employee.expenseRequest.rejected");
                    sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
                } else {
                    sMessage = getText("expense.allApprovers.expenseRequest.rejected");
                    sMessage = StringUtils.replace(sMessage, "<employee_person>", oFirstPerson.getEmpFirstName());
                    sMessage = StringUtils.replace(sMessage, "<loggedIn_person>", oEmp.getEmpFirstName());
                }
                message.setMessage(sMessage);
                message.setReceiver(emp.getEmpFirstName());
                message.setReceiverEmailId(emp.getEmpWorkEmail());
                // String sSubject = getText("message.common.subject");
                sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
                sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
                message.setSubject(sSubject);
                sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
                message.setMessage(sMessage);
                message.setType(Constants.MESSAGE_IN_ALERT);
                message.setCreated(DateUtils.getCurrentDateTime());
                message.setCreatedBy(emp);
                message.setUpdatedBy(emp);
                message.setSenderDelete(1);
                message.setReceiverDelete(1);
                message.setIsRead(1);
                message.setIsNewMail(1);
                message.setIsActive(1);
                mailForExpenseRejected(message, emp, sSubject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addActionMessage((getText("select.common.expenserejected.value")));
        return SUCCESS;
    }

    // review the expenses details
    @SkipValidation
    public String reviewEmployeeExpense() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses = employeeExpensesService.getEmployeeExpenses(empExpenses.getHcmoExpensesId());
        empExpenses.setStatus((getText("select.common.expenseapprover.review.value")));
        empExpenses.setUpdatedBy(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.review.value"));
        try {
            expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
            expenseStatusStracker.setCreatedBy(oEmp);
            expenseStatusStracker.setUpdatedBy(oEmp);
            expenseStatusStracker.setReviewedId(oEmp);
            expenseStatusStracker.setHcmoExpensesId(empExpenses);
            empExpensesService.reviewEmployeeExpense(expenseStatusStracker, empExpenses); // reviewed
                                                                                          // expenses
            EmployeesVO oFirstPerson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());
            // ArrayList<EmployeesVO> empArrList = new ArrayList<EmployeesVO>();
            Set<EmployeesVO> empArrList = new HashSet<EmployeesVO>();
            empExpenseDetailList = detailsService.getExpensesEmpDetails(empExpenses.getHcmoExpensesId());

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

            ExpensesApproverVO newExpenseApprover = null;
            expApproverList = expApproverService.getAllExpensesApprover();
            for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
                newExpenseApprover = it.next();
                int empExpensesInt;
                int newExpAppInt;
                empExpensesInt = empExpenses.getHcmoEmployeeId().getEmployeeId();
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
            String sSubject = getText("message.subject.expense.review");
            sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
            sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
            message.setSubject(sSubject);
            String sMessage = getText("expense.employee.expenseRequest.review");
            sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
            message.setMessage(sMessage);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            for (EmployeesVO emp : empArrList) {
                if (emp.getEmpFirstName().equals(oEmp.getEmpFirstName())
                    && emp.getEmpLastName().equals(oEmp.getEmpLastName())) {
                    sMessage = getText("expense.approvedEmp.expenseRequest.review");
                    sMessage = StringUtils.replace(sMessage, "<person>", oFirstPerson.getEmpFirstName());
                } else if (emp.getEmpFirstName().equals(oFirstPerson.getEmpFirstName())) {
                    sMessage = getText("expense.employee.expenseRequest.review");
                    sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
                } else {
                    sMessage = getText("expense.allApprovers.expenseRequest.review");
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
                message.setSenderDelete(1);
                message.setReceiverDelete(1);
                message.setIsRead(1);
                message.setIsNewMail(1);
                message.setIsActive(1);
                mailForExpenseReview(message, emp, sSubject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addActionMessage((getText("select.common.expensereviewed.value")));
        return SUCCESS;
    }

    // Retrieve approved expenses in 'Approved Tab'
    @SkipValidation
    public String forApprovedTab() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.approved.value"));
        expenseList = empExpensesService.forApprovedTab(expenseStatusStracker, empExpenses); // Retrieved
                                                                                             // 'Approved
                                                                                             // Tab'
                                                                                             // expenses

        // Add currency type value with Total Amount values like ($) 6000.00
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

    // Retrieved 'Rejected Tab' rejected expenses
    @SkipValidation
    public String forRejectedTab() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.rejected.value"));
        expenseList = empExpensesService.forRejectedTab(expenseStatusStracker, empExpenses); // Retrieved
                                                                                             // 'Rejected
                                                                                             // Tab'
                                                                                             // rejected
                                                                                             // expenses

        // Add currency type value with Total Amount values like ($) 6000.00
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

    // Retrieved 'Review Tab' reviewed expenses
    @SkipValidation
    public String forReviewTab() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.review.value"));
        expenseList = empExpensesService.forReviewTab(expenseStatusStracker, empExpenses); // Retrieved
                                                                                           // 'Review
                                                                                           // Tab'
                                                                                           // reviewed
                                                                                           // expenses

        // Add currency type value with Total Amount values like ($) 6000.00
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

    // Retrieved 'My Review Tab' review expenses
    @SkipValidation
    public String myExpensesReview() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empExpenses.setHcmoEmployeeId(oEmp);
        empExpenses.setStatus(getText("select.common.expenseapprover.review.value"));
        expenseList = empExpensesService.myExpensesReview(empExpenses); // Retrieved
                                                                        // 'My
                                                                        // Review
                                                                        // Tab'
                                                                        // review
                                                                        // expenses
        expensesAttachEmpList = expensesAttachService.viewExpensesAttachment(expenseList); // Retrievied
                                                                                           // Expenses
                                                                                           // Attachment
                                                                                           // for
                                                                                           // expenses
                                                                                           // if
                                                                                           // any

        // Add currency type value with Total Amount values like ($) 6000.00
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
        for (Iterator<EmployeeExpensesVO> it = expenseList.iterator(); it.hasNext();) {
            newExpObj = it.next();
            int expIntId = newExpObj.getHcmoExpensesId();
            for (Iterator<ExpensesAttachmentVO> itOne = expensesAttachEmpList.iterator(); itOne.hasNext();) {
                newExpAttObj = itOne.next();
                int expIntIdForAttachment = newExpAttObj.getHcmoExpensesId().getHcmoExpensesId();
                if (expIntId == expIntIdForAttachment) {
                    newExpObj.setExpAttachId(newExpAttObj.getHcmoExpensesId().toString());
                } else {
                    newExpObj.setExpAttachFileName("");
                    newExpObj.setExpAttachId("");
                }
            }
        }
        session.remove("expId");
        return SUCCESS;
    }

    @SkipValidation
    public String editExpensesDetails() {
        expenseDetails = empExpensesService.editExpensesDetails(expenseDetails.getHcmoExpensesDetailsId());
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
        }
        return SUCCESS;
    }

    // Update edited Expenses Details in 'My Review Tab'
    @SkipValidation
    public String updateExpensesDetails() {
        Map session = ActionContext.getContext().getSession();
        session.put("expId", expenseDetails.getHcmoExpensesId().getHcmoExpensesId());
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expenseDetails.setUpdatedBy(oEmp);
        int expId = Integer.valueOf(String.valueOf(session.get("expId")));
        empExpensesService.updateExpensesDetails(expenseDetails); // Update
                                                                  // edited
                                                                  // Expenses
                                                                  // Details in
                                                                  // 'My Review
                                                                  // Tab'
        return SUCCESS;
    }

    public String test() {
        return SUCCESS;
    }

    // Update the expenses in 'My Review Tab ' by click "Submit for Approval"
    @SkipValidation
    public String updateEditedExpensess() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // int
        // amount=Integer.getInteger(request.getParameter("empExpenses.totalAmount").toString());
        BigDecimal big = new BigDecimal(total);
        empExpenses.setTotalAmount(big);
        empExpenses.setHcmoEmployeeId(oEmp);
        empExpenses.setHcmoExpensesId(expenseDetails.getHcmoExpensesId().getHcmoExpensesId());
        empExpenses.setStatus(getText("select.common.expensestatus.inprocess.value"));
        empExpenses.setUpdatedBy(oEmp);
        expenseStatusStracker.setApprovalStatus(getText("select.common.expenseapprover.forapproval.value"));
        expenseStatusStracker.setCreated(DateUtils.getCurrentDateTime());
        expenseStatusStracker.setCreatedBy(oEmp);
        expenseStatusStracker.setUpdatedBy(oEmp);
        expenseStatusStracker.setReviewedId(oEmp);
        expenseStatusStracker.setHcmoExpensesId(empExpenses);
        empExpensesService.updateEditedExpenses(expenseStatusStracker, empExpenses); // Update
                                                                                     // expenses
        EmployeesVO oFirstPerson = employeeService.getEmployees(empExpenses.getHcmoEmployeeId().getEmployeeId());

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
        // ArrayList<EmployeesVO> empArrList = new ArrayList<EmployeesVO>();
        Set<EmployeesVO> empArrList = new HashSet<EmployeesVO>();
        empExpenseDetailList = detailsService.getExpensesEmpDetails(empExpenses.getHcmoExpensesId());

        ExpensesApproverVO newExpenseApprover = null;
        expApproverList = expApproverService.getAllExpensesApprover();
        for (Iterator<ExpensesApproverVO> it = expApproverList.iterator(); it.hasNext();) {
            newExpenseApprover = it.next();
            int empExpensesInt;
            int newExpAppInt;
            empExpensesInt = empExpenses.getHcmoEmployeeId().getEmployeeId();
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
        String sSubject = getText("message.subject.expense.update");
        sSubject = StringUtils.replace(sSubject, "<first_person>", oFirstPerson.getEmpFirstName());
        sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
        message.setSubject(sSubject);
        String sMessage = getText("expense.employee.expenseRequest.update");
        sMessage = StringUtils.replace(sMessage, "<first_person>", oFirstPerson.getEmpFirstName());
        sMessage = StringUtils.replace(sMessage, "<second_person>", oEmp.getEmpFirstName());
        message.setMessage(sMessage);
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setCreated(DateUtils.getCurrentDateTime());
        message.setCreatedBy(oEmp);
        message.setUpdatedBy(oEmp);
        message.setSenderDelete(1);
        message.setReceiverDelete(1);
        message.setIsRead(1);
        message.setIsNewMail(1);
        message.setIsActive(1);

        for (EmployeesVO emp : empArrList) {
            if (emp.getEmpFirstName().equals(oEmp.getEmpFirstName())
                && emp.getEmpLastName().equals(oEmp.getEmpLastName())) {
                sMessage = getText("expense.employeeToOwn.expenseRequest.updated");
            } else {
                sMessage = getText("expense.approvedEmpToOwn.expenseRequest.updated");
                sMessage = StringUtils.replace(sMessage, "<person>", oEmp.getEmpFirstName());
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
            message.setSenderDelete(1);
            message.setReceiverDelete(1);
            message.setIsRead(1);
            message.setIsNewMail(1);
            message.setIsActive(1);
            mailForExpenseUpdated(message, emp, sSubject);
        }
        addActionMessage((getText("select.common.expensereviewed.value")));
        return SUCCESS;
    }

    // retrieve expenses details for 'My Review Tab'
    @SkipValidation
    public String getMyReviewExpensesDetail() {
        Map session = ActionContext.getContext().getSession();
        if (session.get("expId") == null) {
        } else {
            int expId = Integer.valueOf(String.valueOf(session.get("expId")));
            empExpenses.setHcmoExpensesId(expId);
        }
        BigDecimal totalAmount = new BigDecimal(0);
        empExpenseDetailList = empExpensesService.getMyReviewExpensesDetails(empExpenses); // retrieve
                                                                                           // expenses
                                                                                           // details
                                                                                           // for
                                                                                           // 'My
                                                                                           // Review
                                                                                           // Tab'
        for (Iterator<ExpensesDetailsVO> it = empExpenseDetailList.iterator(); it.hasNext();) {
            expenseDetails = it.next();
            totalAmount = totalAmount.add(expenseDetails.getAmount());
            empExpenses.setTotalAmount(totalAmount);
        }
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            curTypeValForExpenseAmtField = "  " + currencyTypeValue + " " + ":";
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            curTypeValForExpenseAmtField = "  " + currencyTypeValue + " " + ":";
        }
        expAttachIdList = expensesAttachService.getAllAttachmentId(empExpenses.getHcmoExpensesId());
        return SUCCESS;
    }

    // DashBoard Expense For Approval
    @SkipValidation
    public String getExpensesForApproval() {
        List forApprovalTodayList;
        List lastThreeDaysList;
        List oneWeekList;
        List totalList;
        totalList = expenseStatusTrackerService.getDashExpenseForApproval();
        forApprovalCount = String.valueOf(totalList.size());
        forApprovalTodayList = expenseStatusTrackerService.getDashExpenseForApprovalToday();
        forApprovalToday = String.valueOf(forApprovalTodayList.size());
        lastThreeDaysList = expenseStatusTrackerService.getDashExpenseForApprovalThreeDays();
        forApprovalThreeDays = String.valueOf(lastThreeDaysList.size());
        oneWeekList = expenseStatusTrackerService.getDashExpenseForApprovalOneWeek();
        forApprovalOneWeek = String.valueOf(oneWeekList.size());
        return SUCCESS;
    }

    public String mail(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + getText("label.header.expenseModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + getText("label.form.fields.directDebit.amount")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + expensesDetails1.getAmount()
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailForExpenseReq(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailForExpenseApprove(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + getText("label.header.expenseApproveModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailForExpenseReview(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + getText("label.header.expenseReviewModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailForExpenseRejected(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + getText("label.header.expenseRejectModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailForExpenseUpdated(MessageVO message, EmployeesVO empVo, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
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
                + getText("label.header.expenseUpdateModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public String mailToAccountant(MessageVO message, EmployeesVO empVo, String AccName, String sSubject) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

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
                + getText("label.header.expenseModule.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
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
                + HtmlConstants.HTML_TABLE_END_TAG);
        }
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

    public List<EmployeeExpensesVO> getExpenseApproveList() {
        return expenseApproveList;
    }

    public void setExpenseApproveList(List<EmployeeExpensesVO> expenseApproveList) {
        this.expenseApproveList = expenseApproveList;
    }

    public ExpensesDetailsVO getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(ExpensesDetailsVO expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    public List<ExpensesDetailsVO> getEmpExpenseDetailList() {
        return empExpenseDetailList;
    }

    public void setEmpExpenseDetailList(List<ExpensesDetailsVO> empExpenseDetailList) {
        this.empExpenseDetailList = empExpenseDetailList;
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

    public List<ExpensesAttachmentVO> getExpensesAttachList() {
        return expensesAttachList;
    }

    public void setExpensesAttachList(List<ExpensesAttachmentVO> expensesAttachList) {
        this.expensesAttachList = expensesAttachList;
    }

    public List<ExpensesAttachmentVO> getExpensesAttachEmpList() {
        return expensesAttachEmpList;
    }

    public void setExpensesAttachEmpList(List<ExpensesAttachmentVO> expensesAttachEmpList) {
        this.expensesAttachEmpList = expensesAttachEmpList;
    }

    public ExpensesAttachmentVO getExpAttach() {
        return expAttach;
    }

    public void setExpAttach(ExpensesAttachmentVO expAttach) {
        this.expAttach = expAttach;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setAmountFieldStringValue(String amountFieldStringValue) {
        this.amountFieldStringValue = amountFieldStringValue;
    }

    public String getAmountFieldStringValue() {
        return amountFieldStringValue;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
    }

    public String getForApprovalCount() {
        return forApprovalCount;
    }

    public void setForApprovalCount(String forApprovalCount) {
        this.forApprovalCount = forApprovalCount;
    }

    public String getForApprovalToday() {
        return forApprovalToday;
    }

    public void setForApprovalToday(String forApprovalToday) {
        this.forApprovalToday = forApprovalToday;
    }

    public String getForApprovalThreeDays() {
        return forApprovalThreeDays;
    }

    public void setForApprovalThreeDays(String forApprovalThreeDays) {
        this.forApprovalThreeDays = forApprovalThreeDays;
    }

    public String getForApprovalOneWeek() {
        return forApprovalOneWeek;
    }

    public void setForApprovalOneWeek(String forApprovalOneWeek) {
        this.forApprovalOneWeek = forApprovalOneWeek;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setExpAccApproverObj(ExpensesAccountantApproverVO expAccApproverObj) {
        this.expAccApproverObj = expAccApproverObj;
    }

    public ExpensesAccountantApproverVO getExpAccApproverObj() {
        return expAccApproverObj;
    }

    public void setExpTypeObj(ExpensesTypeVO expTypeObj) {
        this.expTypeObj = expTypeObj;
    }

    public ExpensesTypeVO getExpTypeObj() {
        return expTypeObj;
    }

    public void setProNameObj(ProjectVO proNameObj) {
        this.proNameObj = proNameObj;
    }

    public ProjectVO getProNameObj() {
        return proNameObj;
    }

    public void setExpAttEmpListForReview(List<ExpensesAttachmentVO> expAttEmpListForReview) {
        this.expAttEmpListForReview = expAttEmpListForReview;
    }

    public List<ExpensesAttachmentVO> getExpAttEmpListForReview() {
        return expAttEmpListForReview;
    }

    public void setExpAttObj(ExpensesAttachmentVO expAttObj) {
        this.expAttObj = expAttObj;
    }

    public ExpensesAttachmentVO getExpAttObj() {
        return expAttObj;
    }

    public void setNewExpObj(EmployeeExpensesVO newExpObj) {
        this.newExpObj = newExpObj;
    }

    public EmployeeExpensesVO getNewExpObj() {
        return newExpObj;
    }

    public void setNewExpAttObj(ExpensesAttachmentVO newExpAttObj) {
        this.newExpAttObj = newExpAttObj;
    }

    public ExpensesAttachmentVO getNewExpAttObj() {
        return newExpAttObj;
    }

    public List<ExpensesAttachmentVO> getExpAttachIdList() {
        return expAttachIdList;
    }

    public void setExpAttachIdList(List<ExpensesAttachmentVO> expAttachIdList) {
        this.expAttachIdList = expAttachIdList;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getCurTypeValForExpenseAmtField() {
        return curTypeValForExpenseAmtField;
    }

    public void setCurTypeValForExpenseAmtField(String curTypeValForExpenseAmtField) {
        this.curTypeValForExpenseAmtField = curTypeValForExpenseAmtField;
    }
}
