
package com.gits.rms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gits.rms.service.TimeSheetNotesDaoService;
import com.gits.rms.service.TimeSheetNotesService;
import com.gits.rms.vo.TimesheetNotesVO;


public class TimesheetUtil {
	
	private TimeSheetNotesService timesheetNotesService = new TimeSheetNotesDaoService();
    private TimesheetNotesVO notesObj;
  
	public Boolean checkTimesheetNotes(String date,String empId) {
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
	        Date theDate = dateFormat.parse(date); 
	        String strDate = dateFormatNew.format(theDate);
	    	
			notesObj=timesheetNotesService.getEmpTimeSheetNotes(strDate,Integer.valueOf(empId));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if((notesObj != null)&&(notesObj.getHcmoTimesheetNotesId() != null)){
			return true;
		}else{
			return false;
		}
		
    }
	
	public Boolean checkTimesheetNotesIsAfterToday(String date) {
		boolean comparingDate = false;
		
		try{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//get current date time with Date()
		    Date todaysDate = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	        Date theDate = format.parse(date); 
		    comparingDate = DateUtils.compareDate(todaysDate, theDate);
		    
		}catch (Exception e) {
			e.printStackTrace();
		}
			return comparingDate;
	}
	

	public TimesheetNotesVO getNotesObj() {
		return notesObj;
	}

	public void setNotesObj(TimesheetNotesVO notesObj) {
		this.notesObj = notesObj;
	}
    
	

}
