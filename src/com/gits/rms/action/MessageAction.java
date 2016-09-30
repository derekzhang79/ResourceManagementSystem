
package com.gits.rms.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.SignatureVO;

public class MessageAction extends ActionSupport {
    private static final long serialVersionUID = 3838880962158192223L;
    private MessageService messageService = new MessageDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<MessageVO> messageList;
    private String sSignature;
    private SignatureVO sigObj;
    private List<SignatureVO> signatureList;
    private MessageVO message;
    private EmployeesVO empId;

    // To get List of Messages
    @SkipValidation
    public String getAllMyMessage() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyMessage(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of Messages
    @SkipValidation
    public String getAllBroadcastMessage() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllBroadcastMessage();
        return SUCCESS;
    }

    // To get List of Messages
    @SkipValidation
    public String getAllMyInMessage() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInMessage(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of Messages
    @SkipValidation
    public String getAllMyInAlert() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInAlert(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String broadcastMsgSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String broadcastSearchResult() {
        messageList = messageService.broadcastSearchResult(message);
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Message detail or
    // update particular Message Data
    public String insertOrUpdateMessage() {
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
                if ((message.getReceiver() != null) || (message.getReceiver().isEmpty() == false)) {
                    message.setType(Constants.MESSAGE_IN_MESSAGE);

                    String checkRxId = message.getReceiver();
                    String checkBccId = message.getBcc();
                    String checkCcId = message.getCc();

                    String emptyString = "";

                    if (checkRxId.isEmpty() == false) {
                        String receiverId = message.getReceiver();
                        String[] rxId = receiverId.split(",");

                        for (String empEmail : rxId) {
                            empId = messageService.getEmployeeId(empEmail);

                            message.setReceiver(empId.getEmployeeId().toString().trim());
                            message.setReceiverEmailId(empId.getEmpWorkEmail().trim());
                            message.setCc(emptyString);
                            message.setCcEmailId(emptyString);
                            message.setBcc(emptyString);
                            message.setBccEmailId(emptyString);
                            messageService.insertMessage(message);

                        }
                    }

                    if (checkBccId.isEmpty() == false) {
                        String bccId = checkBccId;
                        String[] rxBccId = bccId.split(",");

                        for (String empEmail : rxBccId) {
                            empId = messageService.getEmployeeId(empEmail);

                            message.setBcc(empId.getEmployeeId().toString().trim());
                            message.setBccEmailId(empId.getEmpWorkEmail().trim());
                            message.setReceiver(emptyString);
                            message.setReceiverEmailId(emptyString);
                            message.setCc(emptyString);
                            message.setCcEmailId(emptyString);
                            messageService.insertMessage(message);
                        }
                    }

                    if (checkCcId.isEmpty() == false) {
                        String bccId = checkCcId;
                        String[] rxCcId = bccId.split(",");

                        for (String empEmail : rxCcId) {
                            empId = messageService.getEmployeeId(empEmail);

                            message.setCc(empId.getEmployeeId().toString().trim());
                            message.setCcEmailId(empId.getEmpWorkEmail().trim());
                            message.setReceiver(emptyString);
                            message.setReceiverEmailId(emptyString);
                            message.setBcc(emptyString);
                            message.setBccEmailId(emptyString);
                            messageService.insertMessage(message);
                        }
                    }

                    // To Increase Message count in the session if the Message
                    // Mail is for the Logged in Person
                    int receiverId = message.getReceiver().indexOf(oEmp.getEmployeeId().toString());
                    message.getCc().indexOf(oEmp.getEmployeeId().toString());
                    message.getBcc().indexOf(oEmp.getEmployeeId().toString());
                    if (receiverId != -1) {
                        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
                        session.put("MESSAGE_IN_MESSAGE", new MessageHibernateDao().getAllMyInMessageURCount(oEmp.getEmployeeId()));
                    } else if (receiverId != -1) {
                        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
                        session.put("MESSAGE_IN_MESSAGE", new MessageHibernateDao().getAllMyInMessageURCount(oEmp.getEmployeeId()));
                    } else if (receiverId != -1) {
                        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
                        session.put("MESSAGE_IN_MESSAGE", new MessageHibernateDao().getAllMyInMessageURCount(oEmp.getEmployeeId()));
                    }
                    addActionMessage(this.getText("Message Sent Successfully"));
                    return "mymessage_success";
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

    // when click Edit link it bring particular data into Form or click Add
    // Message it shows blank Form to enter New Data
    @SkipValidation
    public String setUpMessage() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
        }
        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String setUpBroadcastMessage() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
        }
        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
        } else {
            for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                sigObj = it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        return SUCCESS;
    }

    // To get Particular Message Data and make it as Readed one
    @SkipValidation
    public String messageView() {
        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
            messageService.readMessage(message);
        }
        messageSessionCount();
        return SUCCESS;
    }

    // To get Particular Message Data in Opened Mail List Page
    @SkipValidation
    public String openedMailMessageView() {
        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
        }
        messageSessionCount();
        return SUCCESS;
    }

    // To get Particular Message Data and mark that as readed message
    @SkipValidation
    public String markAsUnread() {
        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
            messageService.markAsUnRead(message);
        }
        messageSessionCount();
        return SUCCESS;
    }

    // Delete particular Message from the Alert List
    @SkipValidation
    public String deleteMessage() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        message.setUpdatedBy(oEmp);
        messageService.deleteMessage(message);
        addActionMessage(this.getText("Deleted Successfully"));
        return "alert";
    }

    // Delete particular Message from the Message or Broadcast List
    @SkipValidation
    public String deleteMsgAndBroadCastMessage() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        message = messageService.getMessage(message.getHcmoMessageId());
        message.setUpdatedBy(oEmp);
        String receiverId = message.getReceiver();
        String ccId = message.getCc();
        String bccId = message.getBcc();

        if (receiverId.isEmpty() == false) {
            List<String> rxIdList = new LinkedList<String>();
            String remainingReceiverId = "";
            String[] receiverIdArray = receiverId.split(",");

            for (String rxId : receiverIdArray) {
                if (Integer.valueOf(rxId).equals(oEmp.getEmployeeId())) {
                    if ((message.getDeletedReceiverId() == null)
                        || message.getDeletedReceiverId().isEmpty()) {
                        message.setDeletedReceiverId(rxId.trim());
                    } else {
                        String deletedRxId = message.getDeletedReceiverId() + "," + rxId;
                        message.setDeletedReceiverId(deletedRxId.trim());
                    }
                } else {
                    rxIdList.add(rxId);
                }
            }
            if (rxIdList.isEmpty() == false) {
                for (Iterator<String> it = rxIdList.iterator(); it.hasNext();) {
                    remainingReceiverId += it.next() + ",";
                }
                remainingReceiverId = remainingReceiverId.substring(0, remainingReceiverId.lastIndexOf(","));
                message.setReceiver(remainingReceiverId.trim());
            } else if (rxIdList.isEmpty() == true) {
                message.setReceiver(null);
                message.setIsActive(0);
            }
        } else if (ccId.isEmpty() == false) {
            List<String> ccIdList = new LinkedList<String>();
            String remainingCcId = "";
            String[] ccIdArray = ccId.split(",");

            for (String ccIds : ccIdArray) {
                if (Integer.valueOf(ccIds).equals(oEmp.getEmployeeId())) {
                    if ((message.getDeletedCcId() == null) || message.getDeletedCcId().isEmpty()) {
                        message.setDeletedCcId(ccIds.trim());
                    } else {
                        String deletedCcId = message.getDeletedCcId() + "," + ccIds;
                        message.setDeletedCcId(deletedCcId.trim());
                    }
                } else {
                    ccIdList.add(ccIds);
                }
            }
            if (ccIdList.isEmpty() == false) {
                for (Iterator<String> it = ccIdList.iterator(); it.hasNext();) {
                    remainingCcId += it.next() + ",";
                }
                remainingCcId = remainingCcId.substring(0, remainingCcId.lastIndexOf(","));
                message.setCc(remainingCcId.trim());
            } else if (ccIdList.isEmpty() == true) {
                message.setCc(null);
                message.setIsActive(0);
            }
        } else if (bccId.isEmpty() == false) {
            List<String> bccIdList = new LinkedList<String>();
            String remainingBccId = "";
            String[] bccIdArray = bccId.split(",");

            for (String bccIds : bccIdArray) {
                if (Integer.valueOf(bccIds).equals(oEmp.getEmployeeId())) {
                    if ((message.getDeletedBccId() == null) || message.getDeletedBccId().isEmpty()) {
                        message.setDeletedBccId(bccIds.trim());
                    } else {
                        String deletedBccId = message.getDeletedBccId() + "," + bccIds;
                        message.setDeletedBccId(deletedBccId.trim());
                    }
                } else {
                    bccIdList.add(bccIds);
                }
            }
            if (bccIdList.isEmpty() == false) {
                for (Iterator<String> it = bccIdList.iterator(); it.hasNext();) {
                    remainingBccId += it.next() + ",";
                }
                remainingBccId = remainingBccId.substring(0, remainingBccId.lastIndexOf(","));
                message.setBcc(remainingBccId.trim());
            } else if (bccIdList.isEmpty() == true) {
                message.setBcc(null);
                message.setIsActive(0);
            }
        }

        if (message.getType().equals(Constants.MESSAGE_IN_BROADCAST)) {
            messageService.deleteMsgAndBroadCastMessage(message);
            return "broadcast";
        } else if (message.getType().equals(Constants.MESSAGE_IN_MESSAGE)) {
            messageService.deleteMsgAndBroadCastMessage(message);
            return "message";
        }
        addActionMessage(this.getText("Deleted Successfully"));
        return SUCCESS;
    }

    // To get List of Opened Alert Message
    @SkipValidation
    public String getAllOpenedAlertMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInAlertOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of UnOpened Alert Message
    @SkipValidation
    public String getAllUnOpenedAlertMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInAlertUnOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of Opened Compose Message
    @SkipValidation
    public String getAllOpenedComposeMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInComposeMessageOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of UnOpened Compose Message
    @SkipValidation
    public String getAllUnOpenedComposeMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInComposeMessageUnOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of Opened Broadcast Message
    @SkipValidation
    public String getAllOpenedBroadcastMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInBroadcastMessageOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To get List of UnOpened Broadcast Message
    @SkipValidation
    public String getAllUnOpenedBroadcastMails() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        messageList = messageService.getAllMyInBroadcastMessageUnOpened(oEmp.getEmployeeId());
        return SUCCESS;
    }

    // To set session values for Messaging
    @SkipValidation
    private void messageSessionCount() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        session.put("MESSAGE_IN_ALERT", messageService.getAllMyInAlert(oEmp.getEmployeeId()));
        session.put("MESSAGE_IN_ALERT_OPENED", messageService.getAllMyInAlertOpened(oEmp.getEmployeeId()).size());
        session.put("MESSAGE_IN_ALERT_UNOPENED", messageService.getAllMyInAlertUnOpened(oEmp.getEmployeeId()).size());
        session.put("MESSAGE_IN_MESSAGE", messageService.getAllMyInMessage(oEmp.getEmployeeId()));
        session.put("MESSAGE_IN_COMPOSE_OPENED", messageService.getAllMyInComposeMessageOpened(oEmp.getEmployeeId()).size());
        session.put("MESSAGE_IN_COMPOSE_UNOPENED", messageService.getAllMyInComposeMessageUnOpened(oEmp.getEmployeeId()).size());
        session.put("MESSAGE_IN_BROADCAST", messageService.getAllBroadcastMessage());
        session.put("MESSAGE_IN_BROADCAST_OPENED", messageService.getAllMyInBroadcastMessageOpened(oEmp.getEmployeeId()).size());
        session.put("MESSAGE_IN_BROADCAST_UNOPENED", messageService.getAllMyInBroadcastMessageUnOpened(oEmp.getEmployeeId()).size());
    }

    // To get the List of Sent item
    @SkipValidation
    public String getSentItems() {
        messageList = messageService.getSentItems();
        return SUCCESS;
    }

    // To get List of Messages after delete alert
    @SkipValidation
    public String getAllMyInDeleteAlert() {

        if ((message != null) && (message.getHcmoMessageId() != null)) {
            message = messageService.getMessage(message.getHcmoMessageId());
            messageService.deleteReceiver(message);
        }
        return SUCCESS;
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

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public void setSSignature(String sSignature) {
        this.sSignature = sSignature;
    }

    public String getSSignature() {
        return sSignature;
    }
}
