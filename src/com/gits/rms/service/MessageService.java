
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ReportsVO;

public interface MessageService {

    List broadcastSearchResult(MessageVO broadcast);

    void deleteMessage(MessageVO message);

    void deleteMsgAndBroadCastMessage(MessageVO message);

    void deleteReceiver(MessageVO message);

    List<MessageVO> getAllBroadcastMessage();

    List<MessageVO> getAllMyInAlert(Integer id);

    List getAllMyInAlertOpened(Integer id);

    List getAllMyInAlertUnOpened(Integer id);

    List getAllMyInBroadcastMessageOpened(Integer id);

    List getAllMyInBroadcastMessageUnOpened(Integer id);

    List getAllMyInComposeMessageOpened(Integer id);

    List getAllMyInComposeMessageUnOpened(Integer id);

    List<MessageVO> getAllMyInDeleteAlert(Integer id);

    List<MessageVO> getAllMyInMessage(Integer id);

    List getAllMyMessage(Integer id);

    Integer getAllMyMessageURCount(Integer id);

    List getBroadcastMessageReports(ReportsVO report);

    EmployeesVO getEmployeeId(String emailId);

    MessageVO getMessage(Integer id);

    // Send Item
    List<MessageVO> getSentItems();

    void insertMessage(MessageVO message);

    void markAsUnRead(MessageVO message);

    void readMessage(MessageVO message);

    void updateMessage(MessageVO message);
}
