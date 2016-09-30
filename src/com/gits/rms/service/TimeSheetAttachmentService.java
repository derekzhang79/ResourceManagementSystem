
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.TimeSheetAttachmentVO;

public interface TimeSheetAttachmentService {

    void deleteTimeSheetAttachments(Integer id);

    List getAllEmpTimeSheetAttachment(Integer id);

    List getAllTimeSheetAttachment(Integer id);

    TimeSheetAttachmentVO getTimeSheetAttachment(Integer id);

    List getTimeSheetAttachTimeBase(Integer id, Date startDate, Date endDate);

    void insertTimeSheetAttach(TimeSheetAttachmentVO tsAttach);

}
