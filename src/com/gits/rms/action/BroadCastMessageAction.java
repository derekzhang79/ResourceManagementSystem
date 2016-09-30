
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;

public class BroadCastMessageAction extends ActionSupport {
    private static final long serialVersionUID = -7102580114820266627L;
    private MessageService messageService = new MessageDaoService();
    private List<MessageVO> messageList;
    private MessageVO message;
    private EmployeesVO empId;

    public String insertOrUpdateBroadCastMessage() {
        try {
            if (message.getHcmoMessageId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                message.setSender(oEmp);
                message.setCreated(DateUtils.getCurrentDateTime());
                message.setCreatedBy(oEmp);
                message.setUpdatedBy(oEmp);
                message.setSenderDelete(1);
                message.setReceiverDelete(1);
                message.setIsRead(1);
                message.setIsNewMail(1);
                message.setIsActive(1);
                if ((message.getReceiver() == null) || message.getReceiver().equals("")) {
                    message.setType(Constants.MESSAGE_IN_BROADCAST);
                    EmployeesService emplService = new EmployeesDaoService();
                    List<EmployeesVO> oEmpList = emplService.getAllEmployees(oEmp.getClientId());
                    String sReceivers = "";
                    String sReceiversEmailId = "";
                    for (EmployeesVO e : oEmpList) {
                        sReceivers = e.getEmployeeId().toString();
                        sReceiversEmailId = e.getEmpWorkEmail();
                        message.setReceiver(sReceivers);
                        message.setReceiverEmailId(sReceiversEmailId);
                        messageService.insertMessage(message);
                    }

                    // For Inbox Count and Broadcast Message Count
                    session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
                    session.put("MESSAGE_IN_BROADCAST", new MessageHibernateDao().getAllMyInBroadcastURCount(oEmp.getEmployeeId()));
                    addActionMessage(this.getText("Message Sent Successfully"));
                    return "broadcast_success";
                }
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                message.setUpdatedBy(oEmp);
                messageService.updateMessage(message);
                addActionMessage(this.getText("updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<MessageVO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageVO> messageList) {
        this.messageList = messageList;
    }

    public MessageVO getMessage() {
        return message;
    }

    public void setMessage(MessageVO message) {
        this.message = message;
    }

    public EmployeesVO getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeesVO empId) {
        this.empId = empId;
    }

}
