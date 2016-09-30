
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.TimeSheetAttachmentDaoService;
import com.gits.rms.service.TimeSheetAttachmentService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TimeSheetAttachmentVO;

public class TimeSheetAttachmentAction extends ActionSupport {
	
    private static final long serialVersionUID = -7324160747221034801L;
    private TimeSheetAttachmentService timeSheetAttachService = new TimeSheetAttachmentDaoService();
    
    private TimeSheetAttachmentVO timeSheetAttach;
    private List<TimeSheetAttachmentVO> timeSheetAttachList;
    private FileUploadUtil fileupload = new FileUploadUtil();
    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    private TimeSheetAttachmentVO tsAttach = new TimeSheetAttachmentVO();
    private InputStream inStream;
    private HttpServletRequest request;
    private String filename;

    public List<File> getUpload() {
       
        return uploads;
    }

    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileName() {
       
        return uploadFileNames;
    }

    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<String> getUploadContentType() {
       
        return uploadContentTypes;
    }

    public void setUploadContentType(List<String> contentTypes) {
       
        uploadContentTypes = contentTypes;
    }

   
    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public String upload() throws Exception {
        int monthInt = 0;
        int date = 0;
        String month = "";
        int year = 0;
        String week_start_date = "";
        String week_end_date = "";
        int employee_id = 0;
        String role = "";
        int daysOfMonth = 0;
        int week_of_month = 0;
        String isOwnTS = "";
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        request = ServletActionContext.getRequest();
       
        if ((request.getParameter("monthInt")) == null) {
        } else {
            monthInt = Integer.parseInt(request.getParameter("monthInt"));
           
            tsAttach.setMonthInt(monthInt);
        }
      
        if (request.getParameter("date") != null) {
            date = Integer.parseInt(request.getParameter("date"));
          
            tsAttach.setDate(date);
        }
      
        if (request.getParameter("month") != null) {
            month = request.getParameter("month");
           
            tsAttach.setMonth(month);
        }

        if (Integer.parseInt(request.getParameter("year")) != 0) {
            year = Integer.parseInt(request.getParameter("year"));
           
            tsAttach.setYear(year);
        }

        if ((String) request.getParameter("weekStartDate") != null) {
            week_start_date = (String) request.getParameter("weekStartDate");
            tsAttach.setWeekStartDate(week_start_date);        }

       if ((String) request.getParameter("weekEndDate") != null) {
            week_end_date = (String) request.getParameter("weekEndDate");
            tsAttach.setWeekEndDate(week_end_date);        }

        if (Integer.parseInt(request.getParameter("employee_id")) != 0) {
            employee_id = Integer.parseInt(request.getParameter("employee_id"));
            oEmp.setEmployeeId(employee_id);
          
            tsAttach.setHcmoEmployeeId(oEmp);
        }

        
        if (request.getParameter("role") != null) {
            role = request.getParameter("role");
           
            tsAttach.setRole(role);
        }

       
        if (request.getParameter("daysOfMonth") != null) {
            daysOfMonth = Integer.parseInt(request.getParameter("daysOfMonth"));
           
            tsAttach.setDaysOfMonth(daysOfMonth);
        }

        if (Integer.parseInt(request.getParameter("week_of_month")) != 0) {
            week_of_month = Integer.parseInt(request.getParameter("week_of_month"));
           
            tsAttach.setWeek(week_of_month);
        }

       
        if (request.getParameter("isOwnTS") != null) {
            isOwnTS = request.getParameter("isOwnTS");
           
            tsAttach.setIsOwnTS(isOwnTS);
        }

        String fName = "";
        String fType = "";
        int i = 0;
        if (uploads.size() < 1) {
            addActionError(getText("label.common.message.fileupload"));
            return ERROR;
        } else {
            for (File upload : uploads) {
                fileupload.uploadFile(upload.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")
                   
                    + tsAttach.getHcmoEmployeeId().getEmployeeId()
                    + "/"
                    + year
                    + "/"
                    + month
                    + "/"
                    + week_of_month + "/");
                i++;
            }

            for (String fileName : uploadFileNames) {
                fName += fileName + ",";
            }
            for (String fileType : uploadContentTypes) {
                fType += fileType + ",";
            }

            String splitfTypeArray = fType.substring(0, fType.lastIndexOf(','));
          
            splitfTypeArray.split(",");

            String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
            String[] fNameArray = splitfNameArray.split(",");
            for (int j = 0; j < i; j++) {
                
            	tsAttach.setFileName(fNameArray[j]);
            	tsAttach.getHcmoEmployeeId();
            	tsAttach.getYear();
            	tsAttach.getMonth();
            	tsAttach.getWeek();
            	tsAttach.setCreated(DateUtils.getCurrentDateTime());
            	tsAttach.setCreatedBy(oEmp);
            	tsAttach.setUpdatedBy(oEmp);
            	tsAttach.setIsActive(1);
                timeSheetAttachService.insertTimeSheetAttach(tsAttach);
            }
           
            timeSheetAttachList = timeSheetAttachService.getAllTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
            return SUCCESS;
        }
    }

    @SkipValidation
    public String deleteTimeSheetAttachments() {
        
     int timesheetAttachId = tsAttach.getHcmoTsAttachmentId();
        tsAttach = timeSheetAttachService.getTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        int employeeId = tsAttach.getHcmoEmployeeId().getEmployeeId();
        int year = tsAttach.getYear();
        String month = tsAttach.getMonth();
        int week = tsAttach.getWeek();
        String fileName = tsAttach.getFileName();

      
        timeSheetAttachService.deleteTimeSheetAttachments(tsAttach.getHcmoTsAttachmentId());

       
        String success = fileupload.deleteFile(tsAttach.getFileName(), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")
            + employeeId + "/" + year + "/" + month + "/" + week + "/");

        if (success.equals("SUCCESS")) {
            addActionMessage(getText("Deleted Sucessfully"));
        }

       
        fileupload.deleteFolderPath(getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")
            + employeeId + "/");
       
        timeSheetAttachList = timeSheetAttachService.getAllEmpTimeSheetAttachment(tsAttach.getHcmoEmployeeId().getEmployeeId());
        return SUCCESS;
    }

    @SkipValidation
    public String timesheetFileDownload() throws Exception {
       
    	tsAttach = timeSheetAttachService.getTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        inStream = new FileInputStream(new File(tsAttach.getLocation()));
        return SUCCESS;
    }

    public String getFilename() {
       
        filename = tsAttach.getFileName();
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

	public void setTimeSheetAttach(TimeSheetAttachmentVO timeSheetAttach) {
		this.timeSheetAttach = timeSheetAttach;
	}

	public TimeSheetAttachmentVO getTimeSheetAttach() {
		return timeSheetAttach;
	}

	public void setTimeSheetAttachList(List<TimeSheetAttachmentVO> timeSheetAttachList) {
		this.timeSheetAttachList = timeSheetAttachList;
	}

	public List<TimeSheetAttachmentVO> getTimeSheetAttachList() {
		return timeSheetAttachList;
	}

	public void setTsAttach(TimeSheetAttachmentVO tsAttach) {
		this.tsAttach = tsAttach;
	}

	public TimeSheetAttachmentVO getTsAttach() {
		return tsAttach;
	}

}