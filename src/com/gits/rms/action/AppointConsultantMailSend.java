
package com.gits.rms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class AppointConsultantMailSend extends ActionSupport {
    private static final long serialVersionUID = -1756351923611048835L;
    private EmployeesService employeesService = new EmployeesDaoService();
    private ProjectService projectService = new ProjectDaoService();
    private RoleVO role;
    private RoleService roleSerivce = new RoleDaoService();
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;

    public void sendMail(String employee_id, String project_id, String project_startdate, String project_enddate, String created_Id, String sTypeOfAction) {
        EmployeesVO empVo = new EmployeesVO();
        MessageVO message = new MessageVO();
        EmployeesVO creatVo = new EmployeesVO();
        ProjectVO projectVO = new ProjectVO();
        projectVO = projectService.getProject(Integer.parseInt(project_id));
        empVo = employeesService.getEmployees(Integer.parseInt(employee_id));
        creatVo = employeesService.getEmployees(Integer.parseInt(created_Id));
        message.setSubject(this.getText("message.common.subject"));
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setCreated(new Date());
        message.setCreatedBy(creatVo);
        message.setUpdatedBy(creatVo);
        message.setSender(creatVo);
        message.setReceiver(empVo.getEmployeeId().toString());
        role = roleSerivce.getRoleName(this.getText("message.label.common.adminName"));
        EmployeesVO adminRoleId = roleSerivce.getEmployeeId(role.getHcmoRoleId());
        if (sTypeOfAction.equals("Add")) {
            message.setMessage(creatVo.getEmpFirstName() + " has Assigned Project");
            mail(message, empVo, project_startdate, project_enddate, projectVO);
            message.setSender(creatVo);
            message.setReceiver(creatVo.getEmployeeId().toString());
            message.setSubject(this.getText("message.common.subject"));
            message.setMessage(empVo.getEmpFirstName() + " been Assigned Project");
            mail(message, creatVo, project_startdate, project_enddate, projectVO);
            if (adminRoleId.getEmployeeId() != creatVo.getEmployeeId()) {
                message.setReceiver(adminRoleId.getEmployeeId().toString());
                message.setMessage(creatVo.getEmpFirstName() + " Assigned Project to "
                    + empVo.getEmpFirstName());
                mail(message, adminRoleId, project_startdate, project_enddate, projectVO);
            }
        } else if (sTypeOfAction.equals("Delete")) {
            timeSheetProjectAssignService.timeSheetProjectAssignDelete(empVo, projectVO);
            message.setMessage(creatVo.getEmpFirstName() + " has Deleted "
                + empVo.getEmpFirstName() + " Assigned Project");
            mail(message, empVo, project_startdate, project_enddate, projectVO);
            message.setSender(creatVo);
            message.setReceiver(creatVo.getEmployeeId().toString());
            message.setSubject(this.getText("message.common.subject"));
            message.setMessage(empVo.getEmpFirstName() + " Assigned Project been Deleted");
            mail(message, creatVo, project_startdate, project_enddate, projectVO);
            if (adminRoleId.getEmployeeId() != creatVo.getEmployeeId()) {
                message.setReceiver(adminRoleId.getEmployeeId().toString());
                message.setMessage(creatVo.getEmpFirstName() + " Deleted Assigned Project of "
                    + empVo.getEmpFirstName());
                mail(message, adminRoleId, project_startdate, project_enddate, projectVO);
            }
        } else if (sTypeOfAction.equals("Update")) {
            message.setMessage(creatVo.getEmpFirstName() + " has Updated "
                + empVo.getEmpFirstName() + " Assigned Project");
            mail(message, empVo, project_startdate, project_enddate, projectVO);
            message.setSender(creatVo);
            message.setReceiver(creatVo.getEmployeeId().toString());
            message.setSubject(this.getText("message.common.subject"));
            message.setMessage(empVo.getEmpFirstName() + " Assigned Project been Updated");
            mail(message, creatVo, project_startdate, project_enddate, projectVO);
            if (adminRoleId.getEmployeeId() != creatVo.getEmployeeId()) {
                message.setReceiver(adminRoleId.getEmployeeId().toString());
                message.setMessage(creatVo.getEmpFirstName() + " Updated Assigned Project of "
                    + empVo.getEmpFirstName());
                mail(message, adminRoleId, project_startdate, project_enddate, projectVO);
            }
        }
    }

    public String mail(MessageVO message, EmployeesVO empVo, String project_startdate, String project_enddate, ProjectVO projectVo) {
        message.setCreated(new Date());
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();
        new ArrayList();
        String sSubject = this.getText("message.common.subject");
        message.setSubject(sSubject);
        MessageService messageService = new MessageDaoService();
        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + this.getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), empVo.getEmpFirstName()).append(HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE
            + message.getMessage());
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Project Name"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + projectVo.getProjectName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Start Date"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + project_startdate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "End Date"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + project_enddate + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Assigned By"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + oEmp.getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)
        /*
         * .append(HtmlConstants.HTML_TABLE_ROW_START_TAG +
         * HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG +
         * getText("label.form.fields.newexpenses.note") +
         * HtmlConstants.HTML_TABLE_DATA_END_TAG +
         * HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON +
         * expensesDetails1.getNote() + HtmlConstants.HTML_TABLE_DATA_END_TAG +
         * HtmlConstants.HTML_TABLE_ROW_END_TAG)
         * 
         * .append(HtmlConstants.HTML_TABLE_ROW_START_TAG +
         * HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG +
         * getText("label.form.fields.common.description") +
         * HtmlConstants.HTML_TABLE_DATA_END_TAG +
         * HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON +
         * expensesDetails1.getDescription() +
         * HtmlConstants.HTML_TABLE_DATA_END_TAG +
         * HtmlConstants.HTML_TABLE_ROW_END_TAG)
         */

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);
        sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);
        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = this.getText("alert.common.signature");
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
        message.setIsActive(1);
        message.setReceiverEmailId(empVo.getEmpWorkEmail());
        mailer.sendMail(message.getSender().getEmpWorkEmail(), empVo.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), "", "");
        messageService.insertMessage(message);
        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
        session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(oEmp.getEmployeeId()));

        return sMessage.toString();
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

}
