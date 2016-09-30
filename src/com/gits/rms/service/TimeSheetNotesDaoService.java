
package com.gits.rms.service;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.TimeSheetNotesHibernateDao;
import com.gits.rms.persistence.TimesheetNotesDao;
import com.gits.rms.vo.TimesheetNotesVO;

public class TimeSheetNotesDaoService implements TimeSheetNotesService {
	
    private TimesheetNotesDao dao;

    public TimeSheetNotesDaoService() {
        dao = new TimeSheetNotesHibernateDao();
    }

    @Override
    public TimesheetNotesVO getTimeSheetNotes(String date) {
        return dao.getTimeSheetNotes(date);
    }
    
    @Override
    public List getTimeSheetNotesList(Date startingDate,Date endDate,int empid) {
    	return dao.getTimeSheetNotesList(startingDate,endDate,empid);
    }
    
    @Override
    public void insertOrUpdateTimesheetNotes(TimesheetNotesVO notesObj){
    	dao.insertOrUpdateTimesheetNotes(notesObj);
    }
    
    @Override
    public void updateTimesheetNotes(TimesheetNotesVO notesObj){
    	dao.updateTimesheetNotes(notesObj);
    }
    
    @Override
    public TimesheetNotesVO getEmpTimeSheetNotes(String date,int empId){
    	return dao.getEmpTimeSheetNotes(date,empId);
    }
    
}
