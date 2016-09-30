
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ReportsVO;

public interface MessageDao {

    List getAllMyMessage(Integer id);

    Integer getAllMyMessageURCount(Integer id);

    MessageVO getMessage(Integer id);

    void insertMessage(MessageVO message);

    void updateMessage(MessageVO message);

    void deleteMessage(MessageVO message);

    void deleteMsgAndBroadCastMessage(MessageVO message);

    List broadcastSearchResult(MessageVO broadcast);

    void readMessage(MessageVO message);

    void markAsUnRead(MessageVO message);

    List<MessageVO> getAllMyInAlert(Integer id);

    List getAllMyInAlertOpened(Integer id);

    List getAllMyInAlertUnOpened(Integer id);

    List<MessageVO> getAllBroadcastMessage();

    List<MessageVO> getAllMyInMessage(Integer id);

    List getAllMyInComposeMessageOpened(Integer id);

    List getAllMyInComposeMessageUnOpened(Integer id);

    List getAllMyInBroadcastMessageOpened(Integer id);

    List getAllMyInBroadcastMessageUnOpened(Integer id);

    List<MessageVO> getAllMyInDeleteAlert(Integer id);

    void deleteReceiver(MessageVO message);

    EmployeesVO getEmployeeId(String emailId);

    List getBroadcastMessageReports(ReportsVO report);

    // Send Item
    List<MessageVO> getSentItems();
}
