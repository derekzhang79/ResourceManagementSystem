
package com.gits.rms.persistence;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.TimeSheetAttachmentVO;

public interface TimeSheetAttachmentDao {

    List getAllTimeSheetAttachment(Integer id);

    void insertTimeSheetAttach(TimeSheetAttachmentVO tsAttach);

    void deleteTimeSheetAttachments(Integer id);

    TimeSheetAttachmentVO getTimeSheetAttachment(Integer id);

    List getAllEmpTimeSheetAttachment(Integer id);

    List getTimeSheetAttachTimeBase(Integer id, Date startDate, Date enddate);

}
