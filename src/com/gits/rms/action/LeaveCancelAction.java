
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.LeaveReqsApprovalDaoService;
import com.gits.rms.service.LeaveReqsApprovalService;
import com.gits.rms.service.LeaveTypeDaoService;
import com.gits.rms.service.LeaveTypeService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveApproverVO;
import com.gits.rms.vo.LeaveHistoryVO;
import com.gits.rms.vo.LeaveReqsApprovalVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.SignatureVO;

public class LeaveCancelAction extends ActionSupport {
    private static final long serialVersionUID = -6459982159744117232L;
    private LeaveReqsApprovalVO lrapp = new LeaveReqsApprovalVO();
    private LeaveReqsApprovalService leaveReqsApprovalService = new LeaveReqsApprovalDaoService();
    private List<LeaveReqsApprovalVO> lrappList;
    private LeaveHistoryVO lhist = new LeaveHistoryVO();
    private LeaveTypeVO ltype;
    private LeaveTypeService leaveTypeService = new LeaveTypeDaoService();
    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private List<LeaveApproverVO> leaveApproverList;
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String leaAppDate = "";
    private String leaModifiedDate = "";
    private String leaAppDisAppDate = "";
    private String sSignature;
    private List<EmployeesVO> empIdObj;
    private EmployeesService employeeService = new EmployeesDaoService();

    @SkipValidation
    public String leaveRequestCanceled() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        lrapp = leaveReqsApprovalService.getLeaveReqsApproval(lrapp.getHcmoLeaveReqsApprovalId());
        lrapp.setLeaveReqStatus((getText("select.common.leaveReqStatus.canceled.value")));
        lrapp.setApproveNotes("");
        lrapp.setDisApproveNotes("");

        lhist.setLeaveStatus(lrapp.getLeaveReqStatus());
        lhist.setLeaveRequestId(lrapp.getHcmoLeaveReqsApprovalId());
        lhist.setLeaveDate(lrapp.getDateApplied());
        lhist.setEmpIdObj(lrapp.getEmpIdObj());
        lhist.setStartTime((getText("select.common.leaveHist.startTime.value")));
        lhist.setEndTime((getText("select.common.leaveHist.endTime.value")));
        lhist.setNoOfDays(lrapp.getNoOfDays());
        lhist.setHours(lrapp.getHours());
        lhist.setMins(lrapp.getMins());
        lhist.setCreated(DateUtils.getCurrentDateTime());
        lhist.setCreatedBy(oEmp);
        lhist.setLeaveTypeIdObj(lrapp.getLeaveTypeIdObj());
        lhist.setEmpIdObj(lrapp.getEmpIdObj());
        lhist.setUpdatedBy(oEmp);
        lhist.setIsActive(1);
        lhist.setLeaveComments(lrapp.getComments());
        leaveReqsApprovalService.cancel(lrapp, lhist);

        String sSubject = getText("message.subject.leave.canceled");
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        LeaveApproverVO newLeaveApprover = null;

        lrapp.getEmpIdObj().getEmployeeId();
        mail(lrapp.getEmpIdObj().getEmployeeId(), lrapp.getEmpIdObj().getEmpFirstName(), getText("leave.employee.leaveRequest.cancelled"), sSubject);
        leaveApproverList = leaveAppproverService.getEmployeeAllLeaveApprover(lrapp.getEmpIdObj().getEmployeeId());
        for (Iterator<LeaveApproverVO> it = leaveApproverList.iterator(); it.hasNext();) {
            newLeaveApprover = it.next();
            newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId();
            oEmp.getEmployeeId();
            newLeaveApprover.getHcmoEmployeeId().getEmployeeId();
            mailForAllApprover(newLeaveApprover.getHcmoApprovingEmpId().getEmployeeId(), newLeaveApprover.getHcmoApprovingEmpId().getEmpFirstName(), getText("leave.allApprovers.leaveRequest.cancelled"), lrapp.getEmpIdObj().getEmpFirstName(), sSubject);
        }
        addActionMessage((getText("Leave Cancelled Successfully")));
        return SUCCESS;
    }

    @SkipValidation
    public String viewCancelForm() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO empVOObj = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        leaveApproverList = leaveAppproverService.getCurrentLeaveApprover(empVOObj.getEmployeeId());
        if (leaveApproverList.isEmpty()) {
            emp();
        } else {
            subEmployeeList();
        }
        return SUCCESS;
    }

    private void emp() {
        empIdObj = employeeService.getCurrentEmployee();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("empsList", empIdObj);
    }

    private void subEmployeeList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map session = ActionContext.getContext().getSession();
        List employeeIdList = new LinkedList();
        EmployeesVO empSelf = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empIdObj = employeeService.getCurrentSubEmployee();

        EmployeesVO employee = new EmployeesVO();
        for (Iterator<EmployeesVO> it = empIdObj.iterator(); it.hasNext();) {
            employee = it.next();
            employeeIdList.add(employee.getEmployeeId());
        }
        if (employeeIdList.contains(empSelf.getEmployeeId())) {
            request.setAttribute("empsList", empIdObj);
        } else {
            empIdObj.add(empSelf);
            request.setAttribute("empsList", empIdObj);
        }

    }

    @SkipValidation
    public String viewCancelList() {
        lrapp.setLeaveReqStatus(getText("select.common.leaveReqStatus.canceled.value"));
        lrappList = leaveReqsApprovalService.getAllEmpAssignedList(lrapp);
        for (int i = 0; i < lrappList.size(); i++) {
            LeaveReqsApprovalVO lrapp = lrappList.get(i);
            String noOfDays = lrapp.getNoOfDays().toString();
            noOfDays = noOfDays.replace(".00", "");
            String noOfHours = lrapp.getHours().toString();
            noOfHours = noOfHours.replace(".00", "");
            String noOfMins = lrapp.getMins().toString();
            noOfMins = noOfMins.replace(".00", "");
            lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + ","
                + noOfMins + "  Minutes");
        }
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.leaveCancelledDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.dateAppDisApp1")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
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

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and Minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

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
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.leaveCancelledDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.dateAppDisApp1")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
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

    public void mailForAllApprover(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        ltype = leaveTypeService.getLeaveType(lrapp.getLeaveTypeIdObj().getLeaveTypeId());
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date leaveApplieddate = lrapp.getDateApplied();
        leaAppDate = formatter.format(leaveApplieddate);
        Date leaveModifi = lrapp.getUpdated();
        leaModifiedDate = formatter1.format(leaveModifi);
        Date leaAppDis = lrapp.getDateApprDisappr();
        leaAppDisAppDate = formatter.format(leaAppDis);

        // Leave Requested Days,hours and Minutes
        String noOfDays = lrapp.getNoOfDays().toString();
        noOfDays = noOfDays.replace(".00", "");
        String noOfHours = lrapp.getHours().toString();
        noOfHours = noOfHours.replace(".00", "");
        String noOfMins = lrapp.getMins().toString();
        noOfMins = noOfMins.replace(".00", "");
        lrapp.setLeaveRequested(noOfDays + "  Days" + "," + noOfHours + "  Hours" + "," + noOfMins
            + "  Minutes");

        String sDummy = Constants.PERSON;
        String sEmployee = Constants.EMPLOYEE_PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
        sMessage.replace(sMessage.lastIndexOf(sEmployee), sMessage.lastIndexOf(sEmployee)
            + sEmployee.length(), From);
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
            + getText("label.header.leaveReqsApproval.info")
            + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.common.empName")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getEmpIdObj().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.leaveh.leaveType")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + ltype.getLeaveTypeName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.header.leaveCancelledDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaAppDate
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.dateAppDisApp1")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + leaAppDisAppDate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.header.lrapp.noOfDays")
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + lrapp.getLeaveRequested()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        if (lrapp.getComments().isEmpty() == false) {
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + getText("label.lrapp.comments")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + lrapp.getComments()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
            + getText("label.common.message.modifiedDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + leaModifiedDate
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

    public LeaveReqsApprovalVO getLrapp() {
        return lrapp;
    }

    public void setLrapp(LeaveReqsApprovalVO lrapp) {
        this.lrapp = lrapp;
    }

    public LeaveHistoryVO getLhist() {
        return lhist;
    }

    public void setLhist(LeaveHistoryVO lhist) {
        this.lhist = lhist;
    }

    public LeaveTypeVO getLtype() {
        return ltype;
    }

    public void setLtype(LeaveTypeVO ltype) {
        this.ltype = ltype;
    }

    public List<LeaveApproverVO> getLeaveApproverList() {
        return leaveApproverList;
    }

    public void setLeaveApproverList(List<LeaveApproverVO> leaveApproverList) {
        this.leaveApproverList = leaveApproverList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public SignatureVO getSigObj() {
        return sigObj;
    }

    public void setSigObj(SignatureVO sigObj) {
        this.sigObj = sigObj;
    }

    public String getLeaAppDate() {
        return leaAppDate;
    }

    public void setLeaAppDate(String leaAppDate) {
        this.leaAppDate = leaAppDate;
    }

    public String getLeaModifiedDate() {
        return leaModifiedDate;
    }

    public void setLeaModifiedDate(String leaModifiedDate) {
        this.leaModifiedDate = leaModifiedDate;
    }

    public String getLeaAppDisAppDate() {
        return leaAppDisAppDate;
    }

    public void setLeaAppDisAppDate(String leaAppDisAppDate) {
        this.leaAppDisAppDate = leaAppDisAppDate;
    }

    public String getsSignature() {
        return sSignature;
    }

    public void setsSignature(String sSignature) {
        this.sSignature = sSignature;
    }

    public List<LeaveReqsApprovalVO> getLrappList() {
        return lrappList;
    }

    public void setLrappList(List<LeaveReqsApprovalVO> lrappList) {
        this.lrappList = lrappList;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

}
