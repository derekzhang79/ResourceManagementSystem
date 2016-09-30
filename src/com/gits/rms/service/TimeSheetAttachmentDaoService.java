
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.TimeSheetAttachmentDao;
import com.gits.rms.persistence.TimeSheetAttachmentHibernateDao;
import com.gits.rms.vo.TimeSheetAttachmentVO;

public class TimeSheetAttachmentDaoService implements TimeSheetAttachmentService {

    private TimeSheetAttachmentDao dao;

    public TimeSheetAttachmentDaoService() {
        dao = new TimeSheetAttachmentHibernateDao();
    }

    @Override
    public void deleteTimeSheetAttachments(Integer id) {
        dao.deleteTimeSheetAttachments(id);
    }

    @Override
    public List getAllEmpTimeSheetAttachment(Integer id) {
        return dao.getAllEmpTimeSheetAttachment(id);
    }

    @Override
    public List getAllTimeSheetAttachment(Integer id) {
        return dao.getAllTimeSheetAttachment(id);
    }

    @Override
    public TimeSheetAttachmentVO getTimeSheetAttachment(Integer id) {
        return dao.getTimeSheetAttachment(id);
    }

    @Override
    public List getTimeSheetAttachTimeBase(Integer id, Date startDate, Date endDate) {
        return dao.getTimeSheetAttachTimeBase(id, startDate, endDate);
    }

    @Override
    public void insertTimeSheetAttach(TimeSheetAttachmentVO tsAttach) {
        dao.insertTimeSheetAttach(tsAttach);    }

}