
package com.gits.rms.persistence;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ReportsVO;

public class MessageHibernateDao implements MessageDao {

    private List<MessageVO> messageList;

    @Override
    public MessageVO getMessage(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(MessageVO.class);
            crit.add(Restrictions.eq("hcmoMessageId", id));
            return (MessageVO) crit.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void insertMessage(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(message);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;

        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used to show the List of Alert Message both read and
    // not-read
    @Override
    public List getAllMyInAlert(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void readMessage(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MessageVO set updated=:Updated,updatedBy=:UpdatedBy,isRead=:IsRead,isNewMail=:IsNewMail where hcmoMessageId=:HcmoMessageId ";
            Query query = session.createQuery(sHql);
            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("IsRead", 0);
            query.setInteger("IsNewMail", 0);
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void markAsUnRead(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MessageVO set updated=:Updated,updatedBy=:UpdatedBy,isRead=:IsRead where hcmoMessageId=:HcmoMessageId ";
            Query query = session.createQuery(sHql);
            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("IsRead", 1);
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the UnReaded Alert Message List
    @Override
    public List getAllMyInAlertUnOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isRead", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllMyInAlertOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from MessageVO as mess where mess.isActive=:IsActive and mess.isRead=:IsRead and mess.receiver=:ReceiverId and mess.receiverDelete=:ReceiverDelete and mess.type=:MessageType");
            query.setInteger("ReceiverId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_ALERT);
            query.setInteger("IsActive", 1);
            query.setInteger("ReceiverDelete", 1);
            query.setInteger("IsRead", 0);
            messageList = query.list();
            return messageList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteReceiver(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MessageVO set updated=:Updated,updatedBy=:UpdatedBy,receiverDelete=:ReceiverDelete where hcmoMessageId=:HcmoMessageId ";
            Query query = session.createQuery(sHql);
            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("ReceiverDelete", 0);
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used for Compose Message(have to check)
    @Override
    public List getAllMyInMessage(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criterion rxId = Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion ccId = Restrictions.like("ccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion bccId = Restrictions.like("bccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion Id = Restrictions.or(rxId, ccId);

            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.or(Id, bccId)).add(Restrictions.eq("type", Constants.MESSAGE_IN_MESSAGE)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the Alert Viewed Message Count
    @Override
    public List getAllMyInComposeMessageOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from MessageVO as mess where mess.isActive=:IsActive and mess.isRead=:IsRead and mess.receiver=:ReceiverId and mess.receiverDelete=:ReceiverDelete and mess.type=:MessageType");
            query.setInteger("ReceiverId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_MESSAGE);
            query.setInteger("IsActive", 1);
            query.setInteger("ReceiverDelete", 1);
            query.setInteger("IsRead", 0);
            messageList = query.list();
            return messageList;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the UnReaded Alert Message List
    @Override
    public List getAllMyInComposeMessageUnOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_MESSAGE)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isRead", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used for BroadCast Message
    @Override
    public List<MessageVO> getAllBroadcastMessage() {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_BROADCAST)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the Readed Broadcast Message List
    @Override
    public List getAllMyInBroadcastMessageOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from MessageVO as mess where mess.isActive=:IsActive and mess.isRead=:IsRead and mess.receiver=:ReceiverId and mess.receiverDelete=:ReceiverDelete and mess.type=:MessageType");
            query.setInteger("ReceiverId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_BROADCAST);
            query.setInteger("IsActive", 1);
            query.setInteger("ReceiverDelete", 1);
            query.setInteger("IsRead", 0);
            messageList = query.list();
            return messageList;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the UnReaded Broadcast Message List
    @Override
    public List getAllMyInBroadcastMessageUnOpened(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_BROADCAST)).add(Restrictions.eq("receiverDelete", 1)).add(Restrictions.eq("isRead", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // Messages in inbox
    @Override
    public List getAllMyMessage(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criterion rxId = Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion ccId = Restrictions.like("ccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion bccId = Restrictions.like("bccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion Id = Restrictions.or(rxId, ccId);

            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.or(Id, bccId)).add(Restrictions.eq("isRead", 0)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method not Used
    public Integer getAllMyMessageCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoMessageId) from MessageVO as mess where mess.isActive=:IsActive and (:HcmoEmployeeId in (mess.receiver))");
            query.setInteger("HcmoEmployeeId", id);
            query.setInteger("IsActive", 1);
            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is for Message inbox count
    @Override
    public Integer getAllMyMessageURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criterion rxId = Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion ccId = Restrictions.like("ccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion bccId = Restrictions.like("bccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion Id = Restrictions.or(rxId, ccId);

            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.or(Id, bccId)).add(Restrictions.eq("isRead", 0)).add(Restrictions.eq("isActive", 1));
            Integer size = crit.list().size();
            return size;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method not Used
    public Integer getAllMyInBroadcastCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoMessageId) from MessageVO as mess where mess.isActive=:IsActive and (:HcmoEmployeeId in (mess.receiver)) and mess.type=:MessageType");
            query.setInteger("HcmoEmployeeId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_BROADCAST);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used for BroadCast Count
    public Integer getAllMyInBroadcastURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_BROADCAST)).add(Restrictions.eq("isRead", 0)).add(Restrictions.eq("isActive", 1));
            Integer size =crit.list().size(); 
            return size;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is not used
    public Integer getAllMyInMessageCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoMessageId) from MessageVO as mess where mess.isActive=:IsActive and (:HcmoEmployeeId in (mess.receiver)) and mess.type=:MessageType");
            query.setInteger("HcmoEmployeeId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_MESSAGE);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used for compose message count
    public Integer getAllMyInMessageURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criterion rxId = Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion ccId = Restrictions.like("ccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion bccId = Restrictions.like("bccEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE);
            Criterion Id = Restrictions.or(rxId, ccId);

            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.or(Id, bccId)).add(Restrictions.eq("type", Constants.MESSAGE_IN_MESSAGE)).add(Restrictions.eq("isRead", 0)).add(Restrictions.eq("isActive", 1));
            
            Integer size = crit.list().size(); 
            return size;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is not used
    public Integer getAllMyInAlertCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoMessageId) from MessageVO as mess where mess.isActive=:IsActive and (:HcmoEmployeeId in (mess.receiver)) and mess.type=:MessageType");
            query.setInteger("HcmoEmployeeId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_ALERT);
            query.setInteger("IsActive", 1);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used for Alert Message Count
    public Integer getAllMyInAlertURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT)).add(Restrictions.eq("isActive", 1));
            Integer size = crit.list().size(); 
            return size;
        } finally {
            session.flush();
            session.close();
        }
    }

    // This Method is used to show the Alert Viewed Message Count
    public Integer getAllMyInAlertOpenedURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT)).add(Restrictions.eq("isRead", 1)).add(Restrictions.eq("isActive", 1));
            return crit.list().size();
        } finally {
            session.flush();
            session.close();
        }
    }

    public Integer getAllMyInAlertUnOpenedURCount(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            int count = 0;
            session.beginTransaction();
            Query query = session.createQuery("select count(hcmoMessageId) from MessageVO as mess where mess.isActive=:IsActive and (:HcmoEmployeeId in (mess.receiver)) and mess.type=:MessageType and mess.isRead=:IsRead");
            query.setInteger("HcmoEmployeeId", id);
            query.setString("MessageType", Constants.MESSAGE_IN_ALERT);
            query.setInteger("IsActive", 1);
            query.setInteger("IsRead", 0);

            for (Iterator it = query.iterate(); it.hasNext();) {
                count = Integer.valueOf(String.valueOf(it.next()));
            }
            return count;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List broadcastSearchResult(MessageVO broadcast) {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(MessageVO.class);
            if (!(broadcast.getSubject().isEmpty())) {
                crit.add(Restrictions.like("subject", broadcast.getSubject(), MatchMode.ANYWHERE));
            }
            if (!(broadcast.getMessage().isEmpty())) {
                crit.add(Restrictions.like("message", broadcast.getMessage(), MatchMode.ANYWHERE));
            }
            if (!(broadcast.getType().isEmpty())) {
                if (broadcast.getType().equals(Constants.MESSAGE_IN_ALERT)) {
                    crit.add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT));
                } else if (broadcast.getType().equals(Constants.MESSAGE_IN_MESSAGE)) {
                    crit.add(Restrictions.eq("type", Constants.MESSAGE_IN_MESSAGE));
                } else if (broadcast.getType().equals(Constants.MESSAGE_IN_BROADCAST)) {
                    crit.add(Restrictions.eq("type", Constants.MESSAGE_IN_BROADCAST));
                }
            }
            crit.add(Restrictions.like("receiver", oEmp.getEmployeeId().toString(), MatchMode.ANYWHERE));
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void updateMessage(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MessageVO set isRead=:IsRead, " + "Updated=:Updated, "
                + "UpdatedBy=:UpdatedBy " + "where hcmoMessageId=:HcmoMessageId";
            Query query = session.createQuery(sHql);
            query.setInteger("IsRead", message.getIsRead());
            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteMessage(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update MessageVO set updated=:Updated,updatedBy=:UpdatedBy,IsActive=:IsActive where hcmoMessageId=:HcmoMessageId ";
            Query query = session.createQuery(sHql);
            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteMsgAndBroadCastMessage(MessageVO message) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String sDeletedReceiverId = message.getDeletedReceiverId();
            String sDeletedCcId = message.getDeletedCcId();
            String sDeletedBccId = message.getDeletedBccId();
            String sIsActive = String.valueOf(message.getIsActive());

            if (sDeletedReceiverId == null) {
                sDeletedReceiverId = "";
            }

            if (sDeletedCcId == null) {
                sDeletedCcId = "";
            }

            if (sDeletedBccId == null) {
                sDeletedBccId = "";
            }

            if (sIsActive == null) {
                sIsActive = "";
            }

            String sHql = "update MessageVO set";
            String sWhereClause = "";

            if (!(sDeletedReceiverId.equals(""))) {
                sWhereClause += " receiver=:Receiver,";
                sWhereClause += " deletedReceiverId=:DeletedReceiverId,";
            }

            if (!(sDeletedCcId.equals(""))) {
                sWhereClause += " cc=:Cc ,";
                sWhereClause += " deletedCcId=:DeletedCcId,";
            }

            if (!(sDeletedBccId.equals(""))) {
                sWhereClause += " bcc=:Bcc ,";
                sWhereClause += " deletedBccId=:DeletedBccId,";
            }

            if (!(sIsActive.equals(""))) {
                sWhereClause += " isActive=:IsActive,";
            }

            sHql += sWhereClause
                + " updated=:Updated,updatedBy=:UpdatedBy where hcmoMessageId=:HcmoMessageId ";
            Query query = session.createQuery(sHql);

            if (!(sDeletedReceiverId.equals(""))) {
                query.setString("Receiver", message.getReceiver());
                query.setString("DeletedReceiverId", message.getDeletedReceiverId());
            }

            if (!(sDeletedCcId.equals(""))) {
                query.setString("Cc", message.getCc());
                query.setString("DeletedCcId", message.getDeletedCcId());
            }

            if (!(sDeletedBccId.equals(""))) {
                query.setString("Bcc", message.getBcc());
                query.setString("DeletedBccId", message.getDeletedBccId());
            }

            if (!(sIsActive.equals(""))) {
                query.setInteger("IsActive", message.getIsActive());
            }

            query.setDate("Updated", DateUtils.getCurrentTime());
            query.setInteger("UpdatedBy", message.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoMessageId", message.getHcmoMessageId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO getEmployeeId(String emailId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.empWorkEmail=:EmpWorkEmail");
            query.setString("EmpWorkEmail", emailId);
            query.setInteger("IsActive", 1);

            return (EmployeesVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<MessageVO> getSentItems() {
        Map msession = ActionContext.getContext().getSession();
        Session session = HibernateUtil.getSession();

        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");

        try {
            Criteria crit = session.createCriteria(MessageVO.class);
            crit.add(Restrictions.ge("sender", oEmp.getEmployeeId()));
            crit.add(Restrictions.ge("isActive", 1));

            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getBroadcastMessageReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        try {

            Criteria crit = session.createCriteria(MessageVO.class);
            if (report.getFromDate() != null) {
                crit.add(Restrictions.ge("created", report.getFromDate()));
            }
            if (report.getToDate() != null) {
                crit.add(Restrictions.le("created", report.getToDate()));
            }
            crit.add(Restrictions.eq("type", Constants.MESSAGE_IN_BROADCAST));
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;

        } finally {
            session.flush();
            session.close();
        }
    }

    // This method is used to show the List of Alert Message both read and
    // not-read
    @Override
    public List getAllMyInDeleteAlert(Integer id) {
        Session session = HibernateUtil.getSession();
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        try {
            Criteria crit = session.createCriteria(MessageVO.class).add(Restrictions.like("receiverEmailId", oEmp.getEmpWorkEmail(), MatchMode.ANYWHERE)).add(Restrictions.eq("type", Constants.MESSAGE_IN_ALERT)).add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }
}
