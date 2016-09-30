
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
import com.gits.rms.service.LeaveTypeDaoService;
import com.gits.rms.service.LeaveTypeService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.SignatureVO;

public class LeaveTypeAction extends ActionSupport {
    private static final long serialVersionUID = 7358784901626122400L;
    private LeaveTypeService leaveService = new LeaveTypeDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private LeaveTypeVO leave;
    private List<LeaveTypeVO> leaveList;
    private List<EmployeesVO> employeeList;
    private EmployeesVO emp;
    private String leaveModifiedDateMail = "";

    // To get List of LeaveType for an Employee
    @SkipValidation
    public String getAllLeaveType() {
        leaveList = leaveService.getAllLeaveType();
        return SUCCESS;
    }

    // To View Leave Type Search Form
    @SkipValidation
    public String leaveTypeSearchForm() {
        return SUCCESS;
    }

    // Leave Type SearchForm Result Page
    @SkipValidation
    public String leaveTypeSearchResult() {
        leaveList = leaveService.leaveTypeSearchResult(leave);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // LeaveType it shows blank Form to enter New Data
    @SkipValidation
    public String setUpLeaveType() {
        if ((leave != null) && (leave.getLeaveTypeId() != null)) {
            leave = leaveService.getLeaveType(leave.getLeaveTypeId());
        }
        return SUCCESS;
    }

    // To get Particular LeaveType Data
    @SkipValidation
    public String leaveTypeView() {
        if ((leave != null) && (leave.getLeaveTypeId() != null)) {
            leave = leaveService.getLeaveType(leave.getLeaveTypeId());

        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new LeaveType or
    // update particular LeaveType Data
    public String insertOrUpdateLeaveType() {

        try {
            if (leave.getLeaveTypeId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                leave.setCreated(DateUtils.getCurrentDateTime());
                leave.setCreatedBy(oEmp);
                leave.setUpdatedBy(oEmp);
                leave.setIsActive(1);
                leaveService.insertLeaveType(leave);

                // Leave Type Add Mailer
                employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                leave = leaveService.getLeaveType(leave.getLeaveTypeId());
                String sSubject = getText("message.subject.leaveType.add");

                for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                    emp = it.next();
                    int listEmployeeID = emp.getEmployeeId();
                    int sessionEmployeeID = oEmp.getEmployeeId();
                    if (listEmployeeID != sessionEmployeeID) {
                        // Mail to all the employees except the logged in person
                        mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("leaveType.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    } else {
                        // Mail to the logged in person
                        mail(oEmp, oEmp.getEmpFirstName(), getText("leaveType.add.addedBy"), sSubject);
                    }
                }

                // For Help Information Messages
                Boolean leaveQuotaAddPriv = (Boolean) session.get("LEAVEQUOTA_ADD");
                if (leaveQuotaAddPriv) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.title.leaveType.msg.leaveQuota"));
                }

                addActionMessage(getText("Added Successfully"));

            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                leave.setUpdatedBy(oEmp);
                leaveService.updateLeaveType(leave);

                // Leave Type Edit Mailer
                employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                leave = leaveService.getLeaveType(leave.getLeaveTypeId());
                String sSubject = getText("message.subject.leaveType.edit");

                for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                    emp = it.next();
                    int listEmployeeID = emp.getEmployeeId();
                    int sessionEmployeeID = oEmp.getEmployeeId();
                    if (listEmployeeID != sessionEmployeeID) {
                        // Mail to all the employees except the logged in person
                        mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("leaveType.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    } else {
                        // Mail to the logged in person
                        mail(oEmp, oEmp.getEmpFirstName(), getText("leaveType.edit.updatedBy"), sSubject);
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
        loadValues.getAllLeaveName();
        return SUCCESS;
    }

    // To Delete Particular LeaveType
    @SkipValidation
    public String deleteLeaveType() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        List<EmployeeLeaveQuotaVO> leaveQuotaList;

        leaveQuotaList = leaveService.getValidateLeaveQuota(leave);
        if (!(leaveQuotaList.isEmpty())) {
            addActionError(getText("label.header.leaveType.delete.leaveType"));
            return SUCCESS;
        } else {
            leave.setUpdatedBy(oEmp);
            leaveService.deleteLeaveType(leave);

            leave = leaveService.getLeaveType(leave.getLeaveTypeId());
            employeeList = employeeService.getAllEmployees(oEmp.getClientId());
            leave = leaveService.getLeaveType(leave.getLeaveTypeId());
            String sSubject = getText("message.subject.leaveType.delete");

            for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                emp = it.next();
                int listEmployeeID = emp.getEmployeeId();
                int sessionEmployeeID = oEmp.getEmployeeId();

                if (listEmployeeID != sessionEmployeeID) {
                    // Mail to all the employees except the logged in person
                    mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("leaveType.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    // Mail to the logged in person
                    mail(oEmp, oEmp.getEmpFirstName(), getText("leaveType.delete.deletedBy"), sSubject);
                }
            }
            addActionMessage(getText("Deleted Successfully"));

            // For Drop down List
            loadValues.getAllLeaveName();
            return SUCCESS;
        }
    }

    public void mail(String oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date leaveModdate = leave.getUpdated();
            leaveModifiedDateMail = dateformat.format(leaveModdate);

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
                + getText("label.header.leaveType.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leave.getLeaveTypeName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaveModifiedDateMail
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
            DateFormat updatedFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date leaveModdate = leave.getUpdated();
            leaveModifiedDateMail = updatedFormat.format(leaveModdate);

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
                + getText("label.header.leaveType.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leave.getLeaveTypeName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + leaveModifiedDateMail
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
            mailer.sendAlertEmail(oFirstPerson.getEmployeeId().toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LeaveTypeVO getLeave() {
        return leave;
    }

    public void setLeave(LeaveTypeVO leave) {
        this.leave = leave;
    }

    public List<LeaveTypeVO> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<LeaveTypeVO> leaveList) {
        this.leaveList = leaveList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }
}