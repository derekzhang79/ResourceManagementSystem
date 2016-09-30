
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.MessageDao;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ReportsVO;

public class MessageDaoService implements MessageService {

    private MessageDao dao;

    public MessageDaoService() {
        dao = new MessageHibernateDao();
    }

    @Override
    public List broadcastSearchResult(MessageVO broadcast) {
        return dao.broadcastSearchResult(broadcast);
    }

    @Override
    public void deleteMessage(MessageVO message) {
        dao.deleteMessage(message);
    }

    @Override
    public void deleteMsgAndBroadCastMessage(MessageVO message) {
        dao.deleteMsgAndBroadCastMessage(message);
    }

    @Override
    public void deleteReceiver(MessageVO message) {
        dao.deleteReceiver(message);
    }

    @Override
    public List<MessageVO> getAllBroadcastMessage() {
        return dao.getAllBroadcastMessage();
    }

    @Override
    public List<MessageVO> getAllMyInAlert(Integer id) {
        return dao.getAllMyInAlert(id);
    }

    @Override
    public List getAllMyInAlertOpened(Integer id) {
        return dao.getAllMyInAlertOpened(id);
    }

    @Override
    public List getAllMyInAlertUnOpened(Integer id) {
        return dao.getAllMyInAlertUnOpened(id);
    }

    @Override
    public List getAllMyInBroadcastMessageOpened(Integer id) {
        return dao.getAllMyInBroadcastMessageOpened(id);
    }

    @Override
    public List getAllMyInBroadcastMessageUnOpened(Integer id) {
        return dao.getAllMyInBroadcastMessageUnOpened(id);
    }

    @Override
    public List getAllMyInComposeMessageOpened(Integer id) {
        return dao.getAllMyInComposeMessageOpened(id);
    }

    @Override
    public List getAllMyInComposeMessageUnOpened(Integer id) {
        return dao.getAllMyInComposeMessageUnOpened(id);
    }

    @Override
    public List<MessageVO> getAllMyInDeleteAlert(Integer id) {
        return dao.getAllMyInDeleteAlert(id);
    }

    @Override
    public List<MessageVO> getAllMyInMessage(Integer id) {
        return dao.getAllMyInMessage(id);
    }

    @Override
    public List getAllMyMessage(Integer id) {
        return dao.getAllMyMessage(id);
    }

    @Override
    public Integer getAllMyMessageURCount(Integer id) {
        return dao.getAllMyMessageURCount(id);
    }

    @Override
    public List getBroadcastMessageReports(ReportsVO report) {
        return dao.getBroadcastMessageReports(report);
    }

    @Override
    public EmployeesVO getEmployeeId(String emailId) {
        return dao.getEmployeeId(emailId);
    }

    @Override
    public MessageVO getMessage(Integer id) {
        return dao.getMessage(id);
    }

    @Override
    public List<MessageVO> getSentItems() {
        return dao.getSentItems();
    }

    @Override
    public void insertMessage(MessageVO message) {
        dao.insertMessage(message);
    }

    @Override
    public void markAsUnRead(MessageVO message) {
        dao.markAsUnRead(message);
    }

    @Override
    public void readMessage(MessageVO message) {
        dao.readMessage(message);
    }

    @Override
    public void updateMessage(MessageVO message) {
        dao.updateMessage(message);
    }
}
