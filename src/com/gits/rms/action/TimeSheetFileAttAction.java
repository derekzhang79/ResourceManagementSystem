
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

public class TimeSheetFileAttAction extends ActionSupport {
	
    private static final long serialVersionUID = -2740321275493694734L;
    private TimeSheetAttachmentService timeSheetAttachService = new TimeSheetAttachmentDaoService();
   
    private TimeSheetAttachmentVO timeSheetAttach;
    private List<TimeSheetAttachmentVO> timeSheetAttachList;
    private FileUploadUtil fileupload = new FileUploadUtil();
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
   
    private TimeSheetAttachmentVO tsAttach = new TimeSheetAttachmentVO();
    private InputStream inStream;
    private Integer employeeId;
    private HttpServletRequest request;
    private String filename;

    public String fileUploadAction() throws Exception {
        Calendar cal = new GregorianCalendar();
       
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };
        String month = monthName[cal.get(Calendar.MONTH)];
        int year = cal.get(Calendar.YEAR);
       
        int week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
       
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        request = ServletActionContext.getRequest();
        
        tsAttach.setHcmoEmployeeId(oEmp);
       
        if (upload.length() < 0) {
            addActionError(getText("label.common.message.fileupload"));
            return ERROR;
        } else {
            String filePath = fileupload.uploadFile(upload.getAbsolutePath(), uploadFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")
                + "/"
                + "MASTER_CLIENTID_"
                + session.get("MASTER_CLIENT_ID")
                + "/"
              
                + tsAttach.getHcmoEmployeeId().getEmployeeId()
                + "/"
                + year
                + "/"
                + month
                + "/"
                + week_of_month + "/");
           
            tsAttach.setFileName(uploadFileName);
            tsAttach.setYear(year);
            tsAttach.setMonth(month);
            tsAttach.setWeek(week_of_month);
            tsAttach.setLocation(filePath);
            tsAttach.getHcmoEmployeeId();
            tsAttach.setCreated(DateUtils.getCurrentDateTime());
            tsAttach.setCreatedBy(oEmp);
            tsAttach.setUpdatedBy(oEmp);
            tsAttach.setIsActive(1);
            timeSheetAttachService.insertTimeSheetAttach(tsAttach);

           
            timeSheetAttachList = timeSheetAttachService.getAllEmpTimeSheetAttachment(tsAttach.getHcmoEmployeeId().getEmployeeId());
            addActionMessage(getText("Uploaded Successfully"));
            return SUCCESS;

        }
    }

    @SkipValidation
  
    public String timeSheetAttachmentsList() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
     
        tsAttach.setHcmoEmployeeId(oEmp);
        timeSheetAttachList = timeSheetAttachService.getAllEmpTimeSheetAttachment(tsAttach.getHcmoEmployeeId().getEmployeeId());
        return SUCCESS;
    }

    @SkipValidation
    public String deleteTimeSheetAttachments() {
        Map session = ActionContext.getContext().getSession();
        int timesheetAttachId = tsAttach.getHcmoTsAttachmentId();
        tsAttach = timeSheetAttachService.getTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        int employeeId = tsAttach.getHcmoEmployeeId().getEmployeeId();
        int year = tsAttach.getYear();
        String month = tsAttach.getMonth();
        int week = tsAttach.getWeek();
        String fileName = tsAttach.getFileName();
        
        timeSheetAttachService.deleteTimeSheetAttachments(tsAttach.getHcmoTsAttachmentId());

        String success = fileupload.deleteFile(tsAttach.getFileName(), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")            + "/"
            + "MASTER_CLIENTID_"
            + session.get("MASTER_CLIENT_ID")
            + "/"
            + employeeId
            + "/"
            + year + "/" + month + "/" + week + "/");

       
        fileupload.deleteFolderPath(getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("TimeSheet_Attachments")
            + "/" + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID") + "/" + employeeId + "/");
       
        timeSheetAttachList = timeSheetAttachService.getAllTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        return SUCCESS;
    }

    @SkipValidation
    public String timesheetFileDownload() throws Exception {
        Map session = ActionContext.getContext().getSession();
       
        tsAttach = timeSheetAttachService.getTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath()
            + getText("TimeSheet_Attachments") + "/" + "MASTER_CLIENTID_"
            + session.get("MASTER_CLIENT_ID") + "/" + "/"
            
            + tsAttach.getHcmoEmployeeId().getEmployeeId() + "/" + tsAttach.getYear() + "/"
            + tsAttach.getMonth() + "/" + tsAttach.getWeek() + "/" + tsAttach.getFileName()));
        return SUCCESS;
    }

    @SkipValidation
    public String fileDownload() throws Exception {
       
    	tsAttach = timeSheetAttachService.getTimeSheetAttachment(tsAttach.getHcmoTsAttachmentId());
        inStream = new FileInputStream(new File(tsAttach.getLocation()));
        return SUCCESS;
    }

    @SkipValidation
    public String getAllTmesheetFileAttachment() throws Exception {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        
        tsAttach.setHcmoEmployeeId(oEmp);
        timeSheetAttachList = timeSheetAttachService.getAllEmpTimeSheetAttachment(Integer.parseInt(session.get("EmployeeId").toString()));
        return SUCCESS;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFilename() {
       
        filename = tsAttach.getFileName();
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentTypes() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

   
    public InputStream getInStream() {
        return inStream;
    }

   
    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
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

   
	public void setTimeSheetAttach(TimeSheetAttachmentVO timeSheetAttach) {
		this.timeSheetAttach = timeSheetAttach;
	}

   
	public TimeSheetAttachmentVO getTimeSheetAttach() {
		return timeSheetAttach;
	}

}