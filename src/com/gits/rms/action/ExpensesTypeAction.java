
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
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpensesTypeDaoService;
import com.gits.rms.service.ExpensesTypeService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesTypeVO;
import com.gits.rms.vo.SignatureVO;

public class ExpensesTypeAction extends ActionSupport {
    private static final long serialVersionUID = 3784499720194387907L;
    private ExpensesTypeService expTypeService = new ExpensesTypeDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<ExpensesTypeVO> expTypeList;
    private ExpensesTypeVO expType = new ExpensesTypeVO();
    private List<EmployeesVO> employeeList;
    private EmployeesVO emp;
    private String expenseTypeModifiedDateMail = "";

    // Retrieve all expenses type
    @SkipValidation
    public String getAllExpType() {
        expTypeList = expTypeService.getAllExpensesType();
        return SUCCESS;
    }

    // To View Expenses Type Result
    @SkipValidation
    public String expTypeSearchForm() {
        return SUCCESS;
    }

    // Search Result for Expenses Type
    @SkipValidation
    public String expTypeSearchResult() {
        expTypeList = expTypeService.expensesTypeSearchResult(expType);
        return SUCCESS;
    }

    // Get expenses type to show
    @SkipValidation
    public String setUpInsertOrUpdateExpType() {
        if ((expType != null) && (expType.getHcmoExpensesTypeId() != null)) {
            expType = expTypeService.getExpensesType(expType.getHcmoExpensesTypeId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String expensesTypeView() {
        if ((expType != null) && (expType.getHcmoExpensesTypeId() != null)) {
            expType = expTypeService.getExpensesType(expType.getHcmoExpensesTypeId());
        }
        return SUCCESS;
    }

    // Insert or update expenses type
    public String insertOrUpdateExpType() {
        try {
            if (expType.getHcmoExpensesTypeId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                expType.setCreated(DateUtils.getCurrentDateTime());
                expType.setCreatedBy(oEmp);
                expType.setUpdatedBy(oEmp);
                expType.setIsActive(1);
                expTypeService.insertExpensesType(expType);

                employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                expType = expTypeService.getExpensesType(expType.getHcmoExpensesTypeId());
                String sSubject = getText("message.subject.expensestype.add");

                for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                    emp = it.next();
                    int listEmployeeID = emp.getEmployeeId();
                    int sessionEmployeeID = oEmp.getEmployeeId();
                    if (listEmployeeID != sessionEmployeeID) {
                        // Mail to all the employees except the logged in person
                        mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("expensestype.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    } else {
                        // Mail to the logged in person
                        mail(oEmp, oEmp.getEmpFirstName(), getText("expensestype.add.addedBy"), sSubject);
                    }
                }
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                expType.setUpdatedBy(oEmp);
                expTypeService.updateExpensesType(expType);

                employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                expType = expTypeService.getExpensesType(expType.getHcmoExpensesTypeId());
                String sSubject = getText("message.subject.expensestype.edit");

                for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                    emp = it.next();
                    int listEmployeeID = emp.getEmployeeId();
                    int sessionEmployeeID = oEmp.getEmployeeId();
                    if (listEmployeeID != sessionEmployeeID) {
                        // Mail to all the employees except the logged in person
                        mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("expensestype.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    } else {
                        // Mail to the logged in person
                        mail(oEmp, oEmp.getEmpFirstName(), getText("expensestype.edit.updatedBy"), sSubject);
                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            e.printStackTrace();
            throw e;
        }
        // For Drop down List
        loadValues.getAllExpensesTypeName();
        return SUCCESS;
    }

    // delete expenses type
    @SkipValidation
    public String deleteExpType() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        expType.setUpdatedBy(oEmp);
        expTypeService.deleteExpensesType(expType);

        expType = expTypeService.getExpensesType(expType.getHcmoExpensesTypeId());
        employeeList = employeeService.getAllEmployees(oEmp.getClientId());
        String sSubject = getText("message.subject.expensestype.delete");

        for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
            emp = it.next();
            int listEmployeeID = emp.getEmployeeId();
            int sessionEmployeeID = oEmp.getEmployeeId();
            if (listEmployeeID != sessionEmployeeID) {
                // Mail to all the employees except the logged in person
                mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("expensestype.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            } else {
                // Mail to the logged in person
                mail(oEmp, oEmp.getEmpFirstName(), getText("expensestype.delete.deletedBy"), sSubject);
            }
        }
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllExpensesTypeName();
        return SUCCESS;
    }

    public void mail(String oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expenseModdate = expType.getUpdated();
            expenseTypeModifiedDateMail = updatedDateFormat.format(expenseModdate);

            String sPerson = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson)
                + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sPerson), sMessage.lastIndexOf(sPerson)
                + sPerson.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.title.expenseType.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expenseType.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expType.getName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expenseTypeModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
            mailer.sendAlertEmail(oFirstPerson, sSubject, sMessage, sSignature);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void mail(Object empId, String DearEmp, String Message, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            EmployeesVO oFirstPerson = (EmployeesVO) empId;

            HCMOneMailer mailer = new HCMOneMailer();
            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date expenseModdate = expType.getUpdated();
            expenseTypeModifiedDateMail = updatedDateFormat.format(expenseModdate);

            String sPerson = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson)
                + sPerson.length(), DearEmp);
            sMessage.append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.title.expenseType.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.expenseType.name") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expType.getName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.updated") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + expenseTypeModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
            mailer.sendAlertEmail(oFirstPerson.getEmployeeId().toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public ExpensesTypeVO getExpType() {
        return expType;
    }

    public void setExpType(ExpensesTypeVO expType) {
        this.expType = expType;
    }

    public List<ExpensesTypeVO> getExpTypeList() {
        return expTypeList;
    }

    public void setExpType(List<ExpensesTypeVO> expTypeList) {
        this.expTypeList = expTypeList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }
}