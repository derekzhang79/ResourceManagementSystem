
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
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.HolidayDaoService;
import com.gits.rms.service.HolidayService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.HolidayVO;
import com.gits.rms.vo.SignatureVO;

public class HolidayAction extends ActionSupport {
    private static final long serialVersionUID = -2937372940369041746L;
    private HolidayService holidayService = new HolidayDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<HolidayVO> holidayList;
    private List<EmployeesVO> employeeList;
    private List<SignatureVO> signatureList;
    private String sSignature;
    private SignatureVO sigObj;
    private HolidayVO holiday;
    private EmployeesVO emp;
    private String holiDateMail = "";
    private String holiModifiedDateMail = "";

    // To get List of Holidays
    @SkipValidation
    public String getAllHoliday() {
        holidayList = holidayService.getAllHoliday();
        for (Iterator<HolidayVO> it = holidayList.iterator(); it.hasNext();) {
            setHolidayLengthValue(it.next());
        }
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String holidaySearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String holidaySearchResult() {
        holidayList = holidayService.holidaySearchResult(holiday);
        for (Iterator<HolidayVO> it = holidayList.iterator(); it.hasNext();) {
            setHolidayLengthValue(it.next());
        }
        if (holiday.getMessage() != null) {
            if (holiday.getMessage().equals(getText("label.common.search.messageSet"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(holiday.getHolidayDate()) + "."));
            }
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Holiday detail or
    // update particular Holiday Data
    public String insertOrUpdateHoliday() {
        if (!validationSuccessful()) {
            return INPUT;
        } else {
            try {
                if (holiday.getHolidayId() == null) {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    holiday.setCreated(DateUtils.getCurrentDateTime());
                    holiday.setCreatedBy(oEmp);
                    holiday.setUpdatedBy(oEmp);
                    holiday.setIsActive(1);
                    holidayService.insertHoliday(holiday);

                    employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                    holiday = holidayService.getHoliday(holiday.getHolidayId());
                    String sSubject = getText("message.subject.holiday.add");

                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        emp = it.next();

                        int listEmployeeID = emp.getEmployeeId();
                        int sessionEmployeeID = oEmp.getEmployeeId();
                        if (listEmployeeID != sessionEmployeeID) {
                            // Mail to all the employees except the logged in
                            // person
                            mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("holiday.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        } else {
                            // Mail to the logged in person
                            mail(oEmp, oEmp.getEmpFirstName(), getText("holiday.add.addedBy"), sSubject);
                        }
                    }
                    addActionMessage(getText("Added Successfully"));

                } else {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    holiday.setUpdatedBy(oEmp);
                    holidayService.updateHoliday(holiday);

                    employeeList = employeeService.getAllEmployees(oEmp.getClientId());
                    holiday = holidayService.getHoliday(holiday.getHolidayId());
                    String sSubject = getText("message.subject.holiday.edit");

                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        emp = it.next();
                        int listEmployeeID = emp.getEmployeeId();
                        int sessionEmployeeID = oEmp.getEmployeeId();
                        if (listEmployeeID != sessionEmployeeID) {
                            // Mail to all the employees except the logged in
                            // person
                            mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("holiday.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        } else {
                            // Mail to the logged in person
                            mail(oEmp, oEmp.getEmpFirstName(), getText("holiday.edit.updatedBy"), sSubject);
                        }
                    }
                    addActionMessage(getText("updated Successfully"));
                }
            } catch (RuntimeException e) {
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                throw e;
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Holiday it shows blank Form to enter New Data
    @SkipValidation
    public String setUpHoliday() {
        if ((holiday != null) && (holiday.getHolidayId() != null)) {
            holiday = holidayService.getHoliday(holiday.getHolidayId());
        }
        return SUCCESS;
    }

    // To get Particular Holiday Data
    @SkipValidation
    public String holidayView() {
        if ((holiday != null) && (holiday.getHolidayId() != null)) {
            holiday = holidayService.getHoliday(holiday.getHolidayId());
            setHolidayLengthValue(holiday);
        }
        return SUCCESS;
    }

    // Delete particular Holiday from the List
    @SkipValidation
    public String deleteHoliday() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        holiday.setUpdatedBy(oEmp);
        holidayService.deleteHoliday(holiday);

        holiday = holidayService.getHoliday(holiday.getHolidayId());
        employeeList = employeeService.getAllEmployees(oEmp.getClientId());
        String sSubject = getText("message.subject.holiday.delete");

        for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
            emp = it.next();

            int listEmployeeID = emp.getEmployeeId();
            int sessionEmployeeID = oEmp.getEmployeeId();
            if (listEmployeeID != sessionEmployeeID) {
                // Mail to all the employees except the logged in person
                mail(emp.getEmployeeId().toString(), emp.getEmpFirstName(), getText("holiday.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
            } else {
                // Mail to the logged in person
                mail(oEmp, oEmp.getEmpFirstName(), getText("holiday.delete.deletedBy"), sSubject);
            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    // Validation method for Holiday Date
    private boolean validationSuccessful() {
        if (DateUtils.isGreaterDate(holiday.getHolidayDate())) {
            return true;
        } else {
            addActionError(getText("errors.holiday.date.invalid"));
            return false;
        }
    }

    // Convert Int to String for Holiday Length Field
    public void setHolidayLengthValue(HolidayVO holiday) {
        if (holiday.getLength() == 0) {
            holiday.setLengthValue("");
        } else {
            holiday.setLengthValue(holiday.getLength() == 4 ? getText("label.holiday.length.value.half")
                : getText("label.holiday.length.value.full"));
        }
    }

    public void mail(String oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        setHolidayLengthValue(holiday);
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date holidayDateModdate = holiday.getHolidayDate();
        holiDateMail = dateformat.format(holidayDateModdate);
        Date holidayModdate = holiday.getUpdated();
        holiModifiedDateMail = updateddateformat.format(holidayModdate);

        HCMOneMailer mailer = new HCMOneMailer();
        String sPerson = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
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
            + getText("label.header.holiday.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.holiday.description") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + holiday.getHolidayDescription() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.holiday.date")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + holiDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (holiday.getLengthValue().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.holiday.length") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + holiday.getLengthValue() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + holiModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
        mailer.sendAlertEmail(oFirstPerson, sSubject, sMessage, sSignature);
    }

    public void mail(Object empId, String DearEmp, String Message, String sSubject) {
        setHolidayLengthValue(holiday);
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        EmployeesVO oFirstPerson = (EmployeesVO) empId;

        HCMOneMailer mailer = new HCMOneMailer();
        DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
        Date holidayDateModdate = holiday.getHolidayDate();
        holiDateMail = dateformat.format(holidayDateModdate);
        DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date holidayModdate = holiday.getUpdated();
        holiModifiedDateMail = updateddateformat.format(holidayModdate);

        String sPerson = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sPerson), sMessage.indexOf(sPerson) + sPerson.length(), DearEmp);
        sMessage.append(HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE + Message);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.holiday.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.holiday.description") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + holiday.getHolidayDescription() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.holiday.date")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + holiDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (holiday.getLengthValue().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.holiday.length") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + holiday.getLengthValue() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + holiModifiedDateMail + HtmlConstants.HTML_TABLE_DATA_END_TAG
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
        mailer.sendAlertEmail(oFirstPerson.getEmployeeId().toString(), sSubject, sMessage, sSignature);
    }

    public List<HolidayVO> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<HolidayVO> holidayList) {
        this.holidayList = holidayList;
    }

    public HolidayVO getHoliday() {
        return holiday;
    }

    public void setHoliday(HolidayVO holiday) {
        this.holiday = holiday;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }
}
