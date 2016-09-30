
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class MessageVO implements Serializable {

    private static final long serialVersionUID = -3484121684024669876L;
    private String bcc;
    private String bccEmailId;
    private String cc;
    private String ccEmailId;
    private Date created;
    private EmployeesVO createdBy;
    private String deletedBccId;
    private String deletedCcId;
    private String deletedReceiverId;
    private Integer hcmoMessageId;
    private int isActive;
    private int isNewMail;
    private int isRead;
    private String message;
    private String receiver;
    private int receiverDelete;
    private String receiverEmailId;
    private EmployeesVO sender;
    private int senderDelete;
    private String signature;
    private String subject;
    private String type;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public MessageVO() {
    }

    public MessageVO(Integer hcmoMessageId, String receiver, String deletedReceiverId,
        String deletedCcId, String deletedBccId, String receiverEmailId, String cc, String bcc,
        String subject, String message, String signature, String type, int isRead,
        EmployeesVO sender, String ccEmailId, String bccEmailId, int receiverDelete,
        int senderDelete, int isNewMail, Date created, EmployeesVO createdBy, Timestamp updated,
        EmployeesVO updatedBy, int isActive,int clientId) {
        super();
        this.clientId=clientId;
        this.hcmoMessageId = hcmoMessageId;
        this.receiver = receiver;
        this.deletedReceiverId = deletedReceiverId;
        this.deletedCcId = deletedCcId;
        this.deletedBccId = deletedBccId;
        this.receiverEmailId = receiverEmailId;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.message = message;
        this.signature = signature;
        this.type = type;
        this.isRead = isRead;
        this.sender = sender;
        this.senderDelete = senderDelete;
        this.receiverDelete = receiverDelete;
        this.isNewMail = isNewMail;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.ccEmailId = ccEmailId;
        this.bccEmailId = bccEmailId;
    }

    public String getBcc() {
        return bcc;
    }

    public String getBccEmailId() {
        return bccEmailId;
    }

    public String getCc() {
        return cc;
    }

    public String getCcEmailId() {
        return ccEmailId;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getDeletedBccId() {
        return deletedBccId;
    }

    public String getDeletedCcId() {
        return deletedCcId;
    }

    public String getDeletedReceiverId() {
        return deletedReceiverId;
    }

    public Integer getHcmoMessageId() {
        return hcmoMessageId;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getIsNewMail() {
        return isNewMail;
    }

    public int getIsRead() {
        return isRead;
    }

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getReceiverDelete() {
        return receiverDelete;
    }

    public String getReceiverEmailId() {
        return receiverEmailId;
    }

    public EmployeesVO getSender() {
        return sender;
    }

    public int getSenderDelete() {
        return senderDelete;
    }

    public String getSignature() {
        return signature;
    }

    public String getSubject() {
        return subject;
    }

    public String getType() {
        return type;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public void setBccEmailId(String bccEmailId) {
        this.bccEmailId = bccEmailId;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setCcEmailId(String ccEmailId) {
        this.ccEmailId = ccEmailId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setDeletedBccId(String deletedBccId) {
        this.deletedBccId = deletedBccId;
    }

    public void setDeletedCcId(String deletedCcId) {
        this.deletedCcId = deletedCcId;
    }

    public void setDeletedReceiverId(String deletedReceiverId) {
        this.deletedReceiverId = deletedReceiverId;
    }

    public void setHcmoMessageId(Integer hcmoMessageId) {
        this.hcmoMessageId = hcmoMessageId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setIsNewMail(int isNewMail) {
        this.isNewMail = isNewMail;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setReceiverDelete(int receiverDelete) {
        this.receiverDelete = receiverDelete;
    }

    public void setReceiverEmailId(String receiverEmailId) {
        this.receiverEmailId = receiverEmailId;
    }

    public void setSender(EmployeesVO sender) {
        this.sender = sender;
    }

    public void setSenderDelete(int senderDelete) {
        this.senderDelete = senderDelete;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
