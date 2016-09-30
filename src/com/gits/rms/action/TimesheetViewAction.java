
package com.gits.rms.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.ProjectActivityDaoService;
import com.gits.rms.service.ProjectActivityService;
import com.gits.rms.service.ProjectAssignEmpService;
import com.gits.rms.service.ProjectAssignEmpDaoService;
import com.gits.rms.service.ProjectDaoService;
import com.gits.rms.service.ProjectService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetAttachmentDaoService;
import com.gits.rms.service.TimeSheetAttachmentService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetNotesDaoService;
import com.gits.rms.service.TimeSheetNotesService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimeTrackDaoService;
import com.gits.rms.service.TimeTrackService;
import com.gits.rms.service.TimesheetCategoryDaoService;
import com.gits.rms.service.TimesheetCategoryEmpDaoService;
import com.gits.rms.service.TimesheetCategoryEmpService;
import com.gits.rms.service.TimesheetCategoryService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimeSheetAttachmentVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimeTrackVO;
import com.gits.rms.vo.TimesheetCategoryEmpVO;
import com.gits.rms.vo.TimesheetCategoryVO;
import com.gits.rms.vo.TimesheetNotesVO;

public class TimesheetViewAction extends ActionSupport {
    private static final long serialVersionUID = -3476954340533826599L;
    private String dateInput;
    private String monthInput;
    private String yearInput;
    private List weekList;
    private String weekDaysList;
    private String sbTimeSheetDetails;
    private String proTimeSheetDetails;
    private String timeSheetEnteredDetails;
    private String protimeSheetEnteredDetails;
    private String totalhoursForCategoryAndProject;
    private String enteredTotalhoursMessageValue;
    private String enteredTotalhoursFalseValue;

    private String submit;
    private String checkedLists = "";
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private ProjectService proService = new ProjectDaoService();
    private TimeTrackService timeTrackService = new TimeTrackDaoService();

    private TimeSheetCategoryAssignVO timeSheetCategoryAssignVO;
    private TimeTrackVO timeTrackVO;

    private TimesheetCategoryVO timesheetCategory = new TimesheetCategoryVO();
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList;
    private List<TimesheetCategoryVO> timesheetCategoryList;
    private TimesheetCategoryService timesheetCategoryService = new TimesheetCategoryDaoService();
    private TimesheetCategoryEmpService timesheetCategoryEmpService = new TimesheetCategoryEmpDaoService();
    private List<TimesheetCategoryEmpVO> timesheetCategoryEmpList;

    private List<ProjectAssignEmpVO> projectAssignEmpList;

    private List<ProjectAssignEmpVO> projectDropDownList = new ArrayList<ProjectAssignEmpVO>();

    private ProjectAssignEmpVO projectAssignEmpVO;
    private ProjectAssignEmpService projectAssignEmpService = new ProjectAssignEmpDaoService();
    private ProjectVO projectVO;
    private List<ProjectVO> projectList;

    private ProjectService projectService = new ProjectDaoService();
    private ProjectActivityService activityService = new ProjectActivityDaoService();

    private TimeSheetProjectAssignVO timeSheetProjectAssign;
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList;

    private EmployeesService employeesService = new EmployeesDaoService();
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private TimeSheetAttachmentService timeSheetAttachService = new TimeSheetAttachmentDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private TimeSheetAttachmentVO tsAttach;
    private List<TimeSheetAttachmentVO> timeSheetAttachList;
    private List dateList = new ArrayList();
    private String monthList = "";
    private String yearList = "";
    private String monthList1 = "";
    private String yearList1 = "";
    private Boolean isCurrentYear;
    private Boolean isCurrentMonth;
    private Boolean isEntered;
    private Boolean isRejected;
    private Boolean isApproved;
    private Boolean isReworked;
    public HCMOneMailer hcmOneMailer = new HCMOneMailer();
    private ArrayList categoryList = new ArrayList<String>();
    private TreeMap<String, TreeMap<String, ArrayList<String>>> timeSheetCategoryMap = new TreeMap<String, TreeMap<String, ArrayList<String>>>();
    private TreeMap<String, ArrayList<String>> dateDetail = new TreeMap<String, ArrayList<String>>();
    private TreeMap<String, ArrayList<String>> dateDetailNew = new TreeMap<String, ArrayList<String>>();
    private TreeMap<String, TreeMap<String, ArrayList<String>>> projectMap = new TreeMap<String, TreeMap<String, ArrayList<String>>>();
    private TreeMap<String, ArrayList<String>> projectdateDetail = new TreeMap<String, ArrayList<String>>();
    public ArrayList<ArrayList> mailDetailLists = new ArrayList<ArrayList>();
    private TimeSheetApproverVO timeSheetApprover = new TimeSheetApproverVO();
    private TimeSheetApproverService timeSheetApproverService = new TimeSheetApproverDaoService();
    private List<TimeSheetApproverVO> timeSheetApproverList;
    private List<TimeTrackVO> timetrackHistoryList;
    private List<TimeTrackVO> timetrackUpdateList;
    private List<TimeTrackVO> chktimetrackInOutList;

    private MessageVO message = new MessageVO();
    private EmployeesVO empVao;
    private RoleVO role;
    private RoleService roleSerivce = new RoleDaoService();

    private List categoryTotalHrs = new ArrayList();
    private List activityTotalHrs = new ArrayList();

    private List dateTotalHrs = new ArrayList();

    private String forApprovalCount = "";
    private String forApprovalToday = "";
    private String forApprovalThreeDays = "";
    private String forApprovalOneWeek = "";
    private String today;

    private String buttonValue;

    private String projectAssignEmpId;
    private ProjectAssignEmpVO projectAssigndetails;
    private String dropdownStatus;
    private ProjectAssignEmpVO projectAssign;
    private TimeSheetNotesService timesheetNotesService = new TimeSheetNotesDaoService();
    private TimesheetNotesVO notesObj;
    private String notesDate;
    private List<TimesheetNotesVO> notesList;
    private TimeSheetProjectAssignVO proActEnteredHrsObj;
    private List<TimeSheetProjectAssignVO> proActEnteredHrsList;
    private ArrayList activityEnteredHourList = new ArrayList<String>();

    @SkipValidation
    public String getTimesheetNotes() {
    	try{
    		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    		SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
            Date theDate = dateFormat.parse(notesDate); 
            String str = dateFormatNew.format(theDate);
            notesObj=timesheetNotesService.getTimeSheetNotes(str);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	return SUCCESS;
    }
    
    // To insert new Timesheet Notes detail or Edit Particular Timesheet Notes Detail
    @SkipValidation
    public String insertOrUpdateTimesheetNotes() {
        try {
            if (notesObj.getHcmoTimesheetNotesId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                notesObj.setCreated(DateUtils.getCurrentDateTime());
                notesObj.setCreatedBy(oEmp);
                notesObj.setUpdatedBy(oEmp);
                notesObj.setIsActive(1);
                
                if(notesObj.getNotes().trim().isEmpty()){
                	addFieldError("notesObj.notes",getText("Notes is a Required Field"));
                	return INPUT;
                }
                	timesheetNotesService.insertOrUpdateTimesheetNotes(notesObj);
                    addActionMessage(getText("Added Successfully"));
                
            } else if (notesObj.getHcmoTimesheetNotesId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                notesObj.setUpdatedBy(oEmp);
                
                if(notesObj.getNotes().trim().isEmpty()){
                	addFieldError("notesObj.notes",getText("Notes is a Required Field"));
                	return INPUT;
                }
                
                timesheetNotesService.updateTimesheetNotes(notesObj);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
        	ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        } catch (Exception e) {
        	e.printStackTrace();
		} 
        return SUCCESS;
    }
    
    @SkipValidation
    public Double getProjActivityEstHrs(String projActivityName) {
    	Double time = 0d;
    	try{
    		Map session = ActionContext.getContext().getSession();
    		Integer EmployeeId = (Integer) session.get("EmployeeId");
    		ProjectActivityVO projActivityObj = new ProjectActivityVO();
    		
    		projActivityObj = timeSheetProjectAssignService.getEmpProjActivityByName(projActivityName);
    		projActivityObj = activityService.getProjectActivity(projActivityObj.getProjectActivityId());
    		
    		if(projActivityObj.getEstimatedHours() !=null){
    			time = Double.valueOf(projActivityObj.getEstimatedHours());
    		}else{
    			time = 0.0;
    		}
    		
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return time;
    }
    
    @SkipValidation
    public Double getProjActivTotalWorkedHrs(String projActivityName) {
    	Double time = 0d;
    	
    	try{
    		Map session = ActionContext.getContext().getSession();
    		Integer EmployeeId = (Integer) session.get("EmployeeId");
    		ProjectActivityVO projActivityObj = new ProjectActivityVO();
    		TimeSheetProjectAssignVO enteredTimeObj = new TimeSheetProjectAssignVO();
    		List<TimeSheetProjectAssignVO> enteredTimeList = new LinkedList<TimeSheetProjectAssignVO>();
    		projActivityObj = timeSheetProjectAssignService.getEmpProjActivityByName(projActivityName);
    		projActivityObj = activityService.getProjectActivity(projActivityObj.getProjectActivityId());
    		if(projActivityObj.getEstimatedHours() !=null){
    			enteredTimeList = timeSheetProjectAssignService.getEnteredHoursByProjActivity(EmployeeId, projActivityObj.getProjectActivityId());
    			
    			if(!(enteredTimeList.isEmpty())){
        			for (Iterator<TimeSheetProjectAssignVO> it = enteredTimeList.iterator(); it.hasNext();) {
            			enteredTimeObj = (TimeSheetProjectAssignVO)it.next();
            			time += enteredTimeObj.getEnterTime();
                    }
        		}
    		}else{
    			time = 0.0;
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return time;
    }
    
    @SuppressWarnings("deprecation")
    @SkipValidation
    public String getTimeSheetDetails() {
        Map session = ActionContext.getContext().getSession();
        if (session.get("Display") != null) {
            addActionMessage(session.get("Display").toString());
        }
        Date datetoday = new Date();
        SimpleDateFormat Today = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
        today = Today.format(datetoday);
        SimpleDateFormat sDate = new SimpleDateFormat("dd");
        SimpleDateFormat sMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sYear = new SimpleDateFormat("yyyy");
        weekList = new ArrayList();

        if (dateInput == null) {
            Date date = new Date();
            dateInput = sDate.format(date);
            monthInput = sMonth.format(date);
            yearInput = sYear.format(date);
        }
        Date date = new Date();
        Date startDate = new Date();
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yearInput), Integer.parseInt(monthInput), Integer.parseInt(dateInput));
        date.setTime(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, -(date.getDay()));
        startDate.setTime(calendar.getTimeInMillis());
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        timesheetCategoryEmpList = timesheetCategoryEmpService.getEmpTimeSheetCategory(EmployeeId);
        projectAssignEmpList = projectAssignEmpService.projectAssignEmpSearchDetail(EmployeeId);

        if(!(projectAssignEmpList.isEmpty())) {
        	for (Iterator<ProjectAssignEmpVO> iteratorPro = projectAssignEmpList.iterator(); iteratorPro.hasNext();) {
            	projectAssign =  iteratorPro.next();
            	projectAssign.setProjectWithProActivity(projectAssign.getProjectName().getProjectName()+"("+projectAssign.getProjectActivityId().getActivityName()+")");
            	projectDropDownList.add(projectAssign);
            }
        }

        Iterator<TimesheetCategoryEmpVO> itrr = timesheetCategoryEmpList.iterator();
        timesheetCategoryList = new ArrayList<TimesheetCategoryVO>();
        while (itrr.hasNext()) {
            TimesheetCategoryEmpVO timesheetCategoryEmpVO = itrr.next();
            timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategoryEmpVO.getTimesheetCategoryName().getHcmoTimesheetCategoryId());
            timesheetCategoryList.add(timesheetCategory);

        }
        Iterator<TimesheetCategoryVO> itr = timesheetCategoryList.iterator();
        while (itr.hasNext()) {
            timesheetCategory = itr.next();
            Integer timeSheetCategoryId = timesheetCategory.getHcmoTimesheetCategoryId();
            weekList = new ArrayList();
            dateDetail = new TreeMap<String, ArrayList<String>>();
            for (int i = 0; i < 7; i++) {
            	tsAttach = new TimeSheetAttachmentVO();
                endDate.setTime(calendar.getTimeInMillis());
                String displayDate = sMonth.format(endDate) + "-" + sDate.format(endDate) + "-"
                    + sYear.format(endDate);
                weekList.add(displayDate);
                timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(EmployeeId, timeSheetCategoryId, new Date(endDate.getTime()));

                if (timeSheetCategoryAssignList.size() == 0) {
                    categoryList = new ArrayList<String>();
                    try {
                        categoryList.add(0);
                        categoryList.add("Entered");
                        dateDetail.put(displayDate, categoryList);
                        dateDetailNew.put(displayDate, categoryList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Iterator<TimeSheetCategoryAssignVO> it = timeSheetCategoryAssignList.iterator();
                while (it.hasNext()) {
                    categoryList = new ArrayList<String>();
                    timeSheetCategoryAssignVO = it.next();
                    Calendar.getInstance();
                    Date enterDate = timeSheetCategoryAssignVO.getEnterDate();
                    String fullDate = sMonth.format(enterDate) + "-" + sDate.format(enterDate)
                        + "-" + sYear.format(enterDate);
                    if (timeSheetCategoryAssignVO.getRejected() == 1) {
                        categoryList.add(timeSheetCategoryAssignVO.getEnterTime());
                        categoryList.add("Rejected");
                        dateDetail.put(fullDate, categoryList);
                        dateDetailNew.put(fullDate, categoryList);
                    } else if (timeSheetCategoryAssignVO.getRework() == 1) {
                        categoryList.add(timeSheetCategoryAssignVO.getEnterTime());
                        categoryList.add("Rework");
                        dateDetail.put(fullDate, categoryList);
                        dateDetailNew.put(fullDate, categoryList);
                    } else if (timeSheetCategoryAssignVO.getApprove() == 1) {
                        categoryList.add(timeSheetCategoryAssignVO.getEnterTime());
                        categoryList.add("Approve");
                        dateDetail.put(fullDate, categoryList);
                        dateDetailNew.put(fullDate, categoryList);
                    } else {
                        categoryList.add(timeSheetCategoryAssignVO.getEnterTime());
                        categoryList.add("Entered");
                        dateDetail.put(fullDate, categoryList);
                        dateDetailNew.put(fullDate, categoryList);
                    }
                    dateList.add(sDate.format(enterDate));
                    monthList = sMonth.format(enterDate);
                    yearList = sYear.format(enterDate);
                }
                calendar.add(Calendar.DATE, 1);
            }
            timeSheetCategoryMap.put(timesheetCategory.getName(), dateDetail);
            calendar.add(Calendar.DATE, -7);
        }
        endDate.setTime(calendar.getTimeInMillis());
        Collections.sort(weekList);
        try {
            projectList = new ArrayList<ProjectVO>();
            Iterator<ProjectAssignEmpVO> itrproject = projectAssignEmpList.iterator();
            while (itrproject.hasNext()) {
                ProjectAssignEmpVO projectAssignEmpVO = itrproject.next();
                projectVO = projectService.getProject(projectAssignEmpVO.getProjectName().getProjectId());
                projectList.add(projectVO);
            }
            Iterator<ProjectAssignEmpVO> itr1 = projectAssignEmpList.iterator();
            while (itr1.hasNext()) {
                projectAssignEmpVO = itr1.next();
                if (projectAssignEmpVO.getProjectActivityId() != null) {
                    Integer projectId = projectAssignEmpVO.getProjectName().getProjectId();
                    weekList = new ArrayList();
                    projectdateDetail = new TreeMap<String, ArrayList<String>>();
                    int count = 0;
                    if (projectAssignEmpList.size() > 0) {
                        for (int i = 0; i < projectAssignEmpList.size(); i++) {
                        }
                    }

                    for (int i = 0; i < 7; i++) {
                        endDate.setTime(calendar.getTimeInMillis());
                        String displayDate = sMonth.format(endDate) + "-" + sDate.format(endDate) + "-" + sYear.format(endDate);
                        weekList.add(displayDate);
                        projectAssignEmpList = projectAssignEmpService.projectAssignEmpSearch(EmployeeId, projectId, new Date(endDate.getTime()), new Date(endDate.getTime()));
                        if (!projectAssignEmpList.isEmpty()) {
                            timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearch(EmployeeId, projectId, new Date(endDate.getTime()), projectAssignEmpVO.getProjectActivityId().getProjectActivityId());
                            if (timeSheetProjectAssignList.size() == 0) {
                                categoryList = new ArrayList<String>();
                                try {
                                    categoryList.add(0);
                                    categoryList.add("Entered");
                                    projectdateDetail.put(displayDate, categoryList);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            Iterator<TimeSheetProjectAssignVO> it = timeSheetProjectAssignList.iterator();
                            while (it.hasNext()) {
                                categoryList = new ArrayList<String>();
                                timeSheetProjectAssign = it.next();
                                Calendar.getInstance();
                                Date enterDate = timeSheetProjectAssign.getEnterDate();
                                String fullDate = sMonth.format(enterDate) + "-"
                                    + sDate.format(enterDate) + "-" + sYear.format(enterDate);
                                if (timeSheetProjectAssign.getRejected() == 1) {
                                    categoryList.add(timeSheetProjectAssign.getEnterTime());
                                    categoryList.add("Rejected");
                                    projectdateDetail.put(fullDate, categoryList);
                                } else if (timeSheetProjectAssign.getRework() == 1) {
                                    categoryList.add(timeSheetProjectAssign.getEnterTime());
                                    categoryList.add("Rework");
                                    projectdateDetail.put(fullDate, categoryList);
                                } else if (timeSheetProjectAssign.getApprove() == 1) {
                                    categoryList.add(timeSheetProjectAssign.getEnterTime());
                                    categoryList.add("Approve");
                                    projectdateDetail.put(fullDate, categoryList);
                                } else {
                                    categoryList.add(timeSheetProjectAssign.getEnterTime());
                                    categoryList.add("Entered");
                                    projectdateDetail.put(fullDate, categoryList);
                                }
                                dateList.add(sDate.format(enterDate));
                                monthList = sMonth.format(enterDate);
                                yearList = sYear.format(enterDate);
                            }
                        } else {
                            categoryList = new ArrayList<String>();
                            count++;
                            try {
                                categoryList.add(0);
                                categoryList.add("Empty");
                                projectdateDetail.put(displayDate, categoryList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        calendar.add(Calendar.DATE, 1);
                    }
                    
                    if (count != 7) {
                        projectMap.put(projectAssignEmpVO.getProjectName().getProjectName() + "("
                            + projectAssignEmpVO.getProjectActivityId().getActivityName() + ")", projectdateDetail);
                    }
                    
//                    if (count != 7) {
//                    	String projWithProActivity = projectAssignEmpVO.getProjectName().getProjectName() + "("
//                                					+ projectAssignEmpVO.getProjectActivityId().getActivityName() ;
//                    	
//                    	if(projectAssignEmpVO.getProjectActivityId().getEstimatedHours() !=null){
//                    		projWithProActivity += "-" + projectAssignEmpVO.getProjectActivityId().getEstimatedHours() + ")";
//                    	}else{
//                    		projWithProActivity += ")";
//                    	}
//                    	
//                        projectMap.put(projWithProActivity, projectdateDetail);
//                    }
                    calendar.add(Calendar.DATE, -7);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(weekList);

        Set list = new TreeSet();
        list = timeSheetCategoryMap.keySet();
        Iterator iterator = list.iterator();
        // Displaying the Tree set data
        while (iterator.hasNext()) {
            double total = 0;
            Map datewise = new TreeMap();
            datewise = timeSheetCategoryMap.get(iterator.next().toString());
            Iterator iterator1 = datewise.values().iterator();
            while (iterator1.hasNext()) {
                String valueString = iterator1.next().toString().trim();
                int index = valueString.indexOf(",");
                total += Double.parseDouble(valueString.substring(1, index).trim());
            }
            categoryTotalHrs.add(total);
        }
        Set list1 = new TreeSet();
        list1 = projectMap.keySet();
        Iterator iterator1 = list1.iterator();

        // Displaying the Tree set data
        while (iterator1.hasNext()) {
            double total = 0;
            Map datewise1 = new TreeMap();
            datewise1 = projectMap.get(iterator1.next().toString());
            Iterator iterator2 = datewise1.values().iterator();
            while (iterator2.hasNext()) {
                String valueString = iterator2.next().toString().trim();
                int index = valueString.indexOf(",");
                total += Double.parseDouble(valueString.substring(1, index).trim());
            }
            activityTotalHrs.add(total);
        }
        for (int i = 0; i <= 6; i++) {
            Map datewise = new TreeMap();
            new HashSet();
            Set datelist = new TreeSet();
            datelist = projectMap.keySet();
            Iterator dateiterator = datelist.iterator();
            double CatwiseTotal = 0;

            while (dateiterator.hasNext()) {
                String CatvalueString = dateiterator.next().toString();
                datewise = projectMap.get(CatvalueString);
                Object[] keys = datewise.keySet().toArray();
                int index = datewise.get(keys[i]).toString().indexOf(",");
                CatwiseTotal += Double.parseDouble(datewise.get(keys[i]).toString().substring(1, index).trim());
            }
            Map datewise1 = new TreeMap();
            new HashSet();
            Set datelist1 = new TreeSet();
            datelist1 = timeSheetCategoryMap.keySet();
            Iterator dateiterator1 = datelist1.iterator();
            double CatwiseTotal1 = 0;
            while (dateiterator1.hasNext()) {
                String CatvalueString = dateiterator1.next().toString();
                datewise1 = timeSheetCategoryMap.get(CatvalueString);
                Object[] keys = datewise1.keySet().toArray();
                int index = datewise1.get(keys[i]).toString().indexOf(",");
                CatwiseTotal1 += Double.parseDouble(datewise1.get(keys[i]).toString().substring(1, index).trim());
            }
            dateTotalHrs.add(CatwiseTotal + CatwiseTotal1);
        }
        timeSheetAttachList = timeSheetAttachService.getTimeSheetAttachTimeBase(EmployeeId, new Date(startDate.getTime()), new Date(endDate.getTime()));
        timeTrackVO = new TimeTrackVO();
        timeTrackVO.setEmpId(oEmp.getEmployeeId());
        timeTrackVO.setCreated(DateUtils.getCurrentDateTime());
        timeTrackVO.setCreatedBy(oEmp);
        timeTrackVO.setUpdatedBy(oEmp);
        timeTrackVO.setIsActive(0);
        chktimetrackInOutList = timeTrackService.chkInOutTimeTrack(timeTrackVO);
        Date newdate = new Date();
        Timestamp ts1 = new Timestamp(newdate.getTime());
        timeTrackVO.setEmpId(oEmp.getEmployeeId());
        timeTrackVO.setStart(ts1);

        timetrackHistoryList = timeTrackService.getAllTimeTrack(timeTrackVO);
        if (timetrackHistoryList.size() > 0) {
            for (int i = 0; i <= (timetrackHistoryList.size() - 1); i++) {
                try {
                    java.util.Date date1 = new java.util.Date(timetrackHistoryList.get(i).getStart().getTime());
                    java.util.Date date2 = new java.util.Date(timetrackHistoryList.get(i).getStop().getTime());
                    long d1Ms = date2.getTime();
                    long d2Ms = date1.getTime();
                    long minute = Math.abs((d1Ms - d2Ms) / 60000);
                    int Hours = (int) minute / 60;
                    int Minutes = (int) minute % 60;
                    double duration = Double.parseDouble(Hours + "." + Minutes);
                    timetrackHistoryList.get(i).setDuration(duration);
                    if (timetrackHistoryList.get(i).getCategoryId()== 0) {
                        ProjectVO projectNameVo = proService.getProject(timetrackHistoryList.get(i).getProjectId());
                        ProjectActivityVO projectActivityVo = activityService.getProjectActivity(timetrackHistoryList.get(i).getActivityId());
                        timetrackHistoryList.get(i).setCategoryName(projectNameVo.getProjectName()
                            + "(" + projectActivityVo.getActivityName() + ")");
                    } else {
                        timesheetCategory = timesheetCategoryService.getTimesheetCategory(timetrackHistoryList.get(i).getCategoryId());
                        timetrackHistoryList.get(i).setCategoryName(timesheetCategory.getName());
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
        
        try{
        	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    		SimpleDateFormat dateFormatNew = new SimpleDateFormat("yyyy-MM-dd");
    		Date firstFieldDate = new Date();
    		Date lastFieldDate = new Date();
    		String firstFieldStr = "";
    		String lastFieldStr ="";
            
            for (int i = 0; i < weekList.size(); i++) {
            	
            	firstFieldDate = dateFormat.parse(String.valueOf(weekList.get(0))); 
            	firstFieldStr = dateFormatNew.format(firstFieldDate);
            	
            	lastFieldDate = dateFormat.parse(String.valueOf(weekList.get(6))); 
            	lastFieldStr = dateFormatNew.format(lastFieldDate);
            	
            	break;
            }
            
            notesList = timesheetNotesService.getTimeSheetNotesList(firstFieldDate, lastFieldDate,EmployeeId);
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
        session.remove("Display");
        return SUCCESS;
    }

    @SkipValidation
    public String insertTimesheetDetails() {
        Map session = ActionContext.getContext().getSession();
        if (sbTimeSheetDetails.equals("") && proTimeSheetDetails.equals("")) {
            if (buttonValue.equals("Back")) {
                return "back";
            }
        }
        if (sbTimeSheetDetails.equals("") && proTimeSheetDetails.equals("")) {
            session.put("Display", "Project is not assigned for you. Please contact your Admin to Assign Project");
            return ERROR;
        }
        if (!((enteredTotalhoursMessageValue == null) && (enteredTotalhoursFalseValue == null))) {
            if (enteredTotalhoursMessageValue == null) {
                if (enteredTotalhoursFalseValue.equals("0")) {
                    return SUCCESS;
                }
            }
            if (enteredTotalhoursMessageValue.equals("")) {
                if (enteredTotalhoursFalseValue.equals("0")) {
                    return SUCCESS;
                }
            }
            if (enteredTotalhoursMessageValue != null) {
                Double enteredTotalhoursMessageDoubleValue;
                enteredTotalhoursMessageDoubleValue = Double.parseDouble(enteredTotalhoursMessageValue);
                // For Total hours validate messages
                if (!enteredTotalhoursMessageValue.equals("0.0")) {
                    if ((enteredTotalhoursMessageDoubleValue > 8.00)
                        || (enteredTotalhoursMessageDoubleValue > 8.0)
                        || (enteredTotalhoursMessageDoubleValue > 8)) {
                        if (enteredTotalhoursFalseValue.equals("0")) {
                            return SUCCESS;
                        }
                    }
                }
            }
        }
        // Total hours validation
        if (weekDaysList != null) {
            String stringArray = "";
            String enteredValueStringArray = "";
            String enteredValueForTotalValidation = "";
            String stringDateArray = "";
            Date DateValue = null;
            String splittedWeekDateArray = "";
            String stringDateConversionArray = "";
            String finalDateConversionString = "";
            String enteredValue = "";
            String entValueForTotal = "";
            Double enteredIntegerValue = 0.00;
            Double enteredDoubleValue = 0.00;
            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String weekDaysString = weekDaysList.substring(weekDaysList.indexOf("[") + 1, weekDaysList.lastIndexOf("]")).trim();

            String[] weekDaysStringArray = weekDaysString.split(",");
            totalhoursForCategoryAndProject = sbTimeSheetDetails + proTimeSheetDetails
                + timeSheetEnteredDetails + protimeSheetEnteredDetails;
            String[] splitSplCharForTotalHours = totalhoursForCategoryAndProject.split("<<<<<");

            for (int i = 0; i < 7; i++) {
                splittedWeekDateArray = weekDaysStringArray[i].trim();
                for (int j = 0; j < splitSplCharForTotalHours.length; j++) {
                    stringArray = splitSplCharForTotalHours[j];
                    enteredValueStringArray = splitSplCharForTotalHours[j];
                    enteredValueForTotalValidation = splitSplCharForTotalHours[j];
                    stringDateArray = stringArray.substring(stringArray.lastIndexOf("$") + 1, stringArray.indexOf("=")).trim();

                    // Through alert information that you entered hours value
                    // beyond 24 hours in particular field only
                    entValueForTotal = enteredValueForTotalValidation.substring(enteredValueForTotalValidation.indexOf("=") + 1).trim();
                    enteredDoubleValue = Double.parseDouble(entValueForTotal);
                    if ((enteredDoubleValue > 24.00) || (enteredDoubleValue > 24.0)
                        || (enteredDoubleValue > 24)) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("error", "Your time sheet entry cannot exceed beyond  24 hours per day !");
                        return SUCCESS;
                    }
                    if (entValueForTotal.equals("NaN") || (entValueForTotal == "NaN")) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("error", "Invalid Entry");
                        return SUCCESS;
                    }
                    try {
                        DateValue = formatter.parse(stringDateArray);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                    stringDateConversionArray = DateFormat.format(DateValue);
                    String[] splittedWeekDateConversionArray = splittedWeekDateArray.split("-");
                    finalDateConversionString =splittedWeekDateConversionArray[0]+"/"
                        +splittedWeekDateConversionArray[1]+"/"
                        +splittedWeekDateConversionArray[2];

                    if (finalDateConversionString.equals(stringDateConversionArray)) {
                        enteredValue = enteredValueStringArray.substring(enteredValueStringArray.indexOf("=") + 1).trim();
                        enteredIntegerValue += Double.parseDouble(enteredValue);
                        if ((enteredIntegerValue > 24.00) || (enteredIntegerValue > 24.0)
                            || (enteredIntegerValue > 24)) {
                            HttpServletRequest request = ServletActionContext.getRequest();
                            request.setAttribute("error", "Your time sheet entry cannot exceed beyond  24 hours per day !");
                            return SUCCESS;
                        }
                    }
                }
                enteredIntegerValue = 0.00;
            }
        }

        String str = "";
        String category = "";
        String date = "";
        String sDate = "";
        String eValue = "";
        String[] acheckList = { "" };
        String status = "";
        String Subj = "";
        EmployeesVO employeesVO = new EmployeesVO();
        timeSheetCategoryAssignVO = new TimeSheetCategoryAssignVO();
        timeSheetProjectAssign = new TimeSheetProjectAssignVO();

        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        timeSheetApproverList = timeSheetApproverService.getEmployeeAllTimeSheetApprover(EmployeeId);
        employeesVO.setEmployeeId(EmployeeId);

        submit = buttonValue;
        if (buttonValue.equals("Back")) {
            return "back";
        }
        if ((sbTimeSheetDetails == null) || (sbTimeSheetDetails == "")) {
            return ERROR;
        }

        try {
            if (!sbTimeSheetDetails.isEmpty()) {
                String[] astr = sbTimeSheetDetails.split("<<<<<");
                if (checkedLists.length() > 0) {
                    acheckList = checkedLists.split("<<<<<");
                }
                if ((checkedLists.length() == 0)
                    && (submit.equals("Approve") || submit.equals("Reject") || submit.equals("Rework"))) {
                    HttpServletRequest request = ServletActionContext.getRequest();
                    request.setAttribute("error", "Please select the check-box before clicking "
                        + submit + " Button");
                    return SUCCESS;
                }

                for (int i = 0; i < astr.length; i++) {
                    str = astr[i];
                    category = str.substring(0, str.indexOf("$$$"));
                    date = str.substring(str.lastIndexOf("$") + 1, str.indexOf("=")).trim();
                    String[] aDate = date.split("-");
                    sDate = aDate[0] + "/" + aDate[1] + "/" + aDate[2];
                    Date dDate = new Date(Integer.parseInt(aDate[2]) - 1900, (Integer.parseInt(aDate[1]) - 1), Integer.parseInt(aDate[0]));
                    eValue = str.substring(str.indexOf("=") + 1).trim();

                    // //To block that without having any values
                    if (eValue.equals("NaN") || (eValue == "NaN")) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("error", "Invalid Entry");
                        return SUCCESS;
                    }
                    // String
                    // strActivity=category.substring(category.indexOf("(")+1,category.lastIndexOf(")"));
                    if (eValue.equals("") || (eValue == null)) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        request.setAttribute("error", "Invalid Entry");
                        return ERROR;
                    }
                    if (!eValue.equals("0")) {
                        timesheetCategory = timesheetCategoryService.getTimesheetCategoryId(category);
                        timeSheetCategoryAssignVO.setEnterDate(dDate);
                        timeSheetCategoryAssignVO.setEnterTime(Double.valueOf(eValue));
                        timeSheetCategoryAssignVO.setEmployeeName(employeesVO);
                        timeSheetCategoryAssignVO.setIsActive(1);
                        timeSheetCategoryAssignVO.setApprove(0);
                        timeSheetCategoryAssignVO.setRejected(0);
                        timeSheetCategoryAssignVO.setRework(0);
                        timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                        if (submit.equals("Approve")) {
                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Approve";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                    timeSheetCategoryAssignVO.setApprove(1);
                                    sendMail(category, "Apporved", sDate, EmployeeId);
                                }
                            }
                        } else if (submit.equals("Reject")) {

                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Reject";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")).trim())) {
                                    timeSheetCategoryAssignVO.setRejected(1);
                                    sendMail(category, "Rejected", sDate, EmployeeId);
                                }
                            }
                        } else if (submit.equals("Rework")) {
                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Rework";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                    timeSheetCategoryAssignVO.setRework(1);
                                    sendMail(category, "Reworked", sDate, EmployeeId);
                                }
                            }
                        } else {
                            timeSheetCategoryAssignVO.setApprove(0);
                            timeSheetCategoryAssignVO.setRejected(0);
                            timeSheetCategoryAssignVO.setRework(0);
                        }
                        if (submit.equals("Save All")) {
                            status = "Save";
                            List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(EmployeeId, timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId(), timeSheetCategoryAssignVO.getEnterDate());
                            if (timeSheetCategoryAssignList.isEmpty()) {
                                timeSheetCategoryAssignVO.setCreated(DateUtils.getCurrentDateTime());
                                timeSheetCategoryAssignVO.setCreatedBy(oEmp);
                                timeSheetCategoryAssignService.insertTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                            } else {
                                Iterator<TimeSheetCategoryAssignVO> itr = timeSheetCategoryAssignList.iterator();
                                while (itr.hasNext()) {
                                    try {
                                        timeSheetCategoryAssignVO.setTimeSheetCategoryAssignId(itr.next().getTimeSheetCategoryAssignId());
                                        timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId());
                                        timeSheetCategoryAssignVO.setTimesheetCategoryName(timesheetCategory);
                                        timeSheetCategoryAssignService.updateTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(EmployeeId, timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId(), timeSheetCategoryAssignVO.getEnterDate());
                            Iterator<TimeSheetCategoryAssignVO> itr = timeSheetCategoryAssignList.iterator();
                            while (itr.hasNext()) {
                                try {
                                    timeSheetCategoryAssignVO.setTimeSheetCategoryAssignId(itr.next().getTimeSheetCategoryAssignId());
                                    timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId());
                                    timeSheetCategoryAssignVO.setTimesheetCategoryName(timesheetCategory);
                                    timeSheetCategoryAssignService.updateTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    if (!eValue.equals("0")) {
                        ArrayList nameList = new ArrayList();
                        nameList.add(oEmp.getEmpFirstName());
                        nameList.add("Entered");
                        nameList.add(date);
                        nameList.add(category);
                        mailDetailLists.add(nameList);
                    }
                }
            }
            date = "";
            sDate = "";
            if (proTimeSheetDetails.length() > 0) {
                String[] astr1 = proTimeSheetDetails.split("<<<<<");
                if (checkedLists.length() > 0) {
                    acheckList = checkedLists.split("<<<<<");
                }
                for (int k = 0; k < astr1.length; k++) {
                    str = astr1[k];
                    category = str.substring(0, str.indexOf("$$$"));
                    date = str.substring(str.lastIndexOf("$") + 1, str.indexOf("=")).trim();
                    String[] aDate = date.split("-");
                    sDate = aDate[0] + "/" + aDate[1] + "/" + aDate[2];
                    Date dDate = new Date(Integer.parseInt(aDate[2]) - 1900, (Integer.parseInt(aDate[1]) - 1), Integer.parseInt(aDate[0]));
                    eValue = str.substring(str.indexOf("=") + 1).trim();
                    String strActivity = category.substring(category.indexOf("(") + 1, category.lastIndexOf(")"));
                    if (!eValue.equals("0")) {
                        timeSheetProjectAssign.setEnterDate(dDate);
                        timeSheetProjectAssign.setEnterTime(Double.valueOf(eValue));
                        timeSheetProjectAssign.setProjectName(projectService.getProjectName(category.substring(0, category.indexOf("("))));
                        ProjectVO test = projectService.getProjectName(category.substring(0, category.indexOf("(")));
                        List activitylist = activityService.getActivityList(test.getProjectId(), strActivity);
                        TimeSheetApproverVO approver = timeSheetApproverService.getTimeSheetApprover(EmployeeId);

                        timeSheetProjectAssign.setEmployeeName(employeesVO);
                        timeSheetProjectAssign.setIsActive(1);
                        timeSheetProjectAssign.setApprove(0);
                        timeSheetProjectAssign.setRejected(0);
                        timeSheetProjectAssign.setRework(0);
                        timeSheetProjectAssign.setUpdatedBy(oEmp);

                        if (submit.equals("Approve")) {
                            timeSheetProjectAssign.setProjectActivity(activityService.isProjectAndActivityExists(strActivity, test.getProjectId(), EmployeeId));
                            if (timeSheetProjectAssign.getProjectActivity() == null) {
                                timeSheetProjectAssign.setProjectActivity(activityService.getProjectAndActivity(strActivity, test.getProjectId()));
                            }
                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Approve";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                    timeSheetProjectAssign.setApprove(1);
                                    sendMail(category, "Approved", sDate, EmployeeId);
                                }
                            }
                        } else if (submit.equals("Reject")) {
                            timeSheetProjectAssign.setProjectActivity(activityService.isProjectAndActivityExists(strActivity, test.getProjectId(), EmployeeId));
                            if (timeSheetProjectAssign.getProjectActivity() == null) {
                                timeSheetProjectAssign.setProjectActivity(activityService.getProjectAndActivity(strActivity, test.getProjectId()));
                            }

                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Reject";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")).trim())) {
                                    timeSheetProjectAssign.setRejected(1);
                                    sendMail(category, "Rejected", sDate, EmployeeId);
                                }
                            }
                        } else if (submit.equals("Rework")) {
                            timeSheetProjectAssign.setProjectActivity(activityService.isProjectAndActivityExists(strActivity, test.getProjectId(), EmployeeId));
                            if (timeSheetProjectAssign.getProjectActivity() == null) {
                                timeSheetProjectAssign.setProjectActivity(activityService.getProjectAndActivity(strActivity, test.getProjectId()));
                            }
                            for (int j = 0; j < acheckList.length; j++) {
                                status = "Rework";
                                if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                    timeSheetProjectAssign.setRework(1);
                                    sendMail(category, "Reworked", sDate, EmployeeId);
                                }
                            }
                        } else {
                            timeSheetProjectAssign.setApprove(0);
                            timeSheetProjectAssign.setRejected(0);
                            timeSheetProjectAssign.setRework(0);
                        }
                        if (submit.equals("Save All")) {
                            status = "Save";
                            if (activitylist.size() > 1) {
                                timeSheetProjectAssign.setProjectActivity(activityService.isProjectAndActivityExists(strActivity, test.getProjectId(), approver.getHcmoApprovingEmpId().getEmployeeId()));
                            } else {
                                timeSheetProjectAssign.setProjectActivity(activityService.getProjectAndActivity(strActivity, test.getProjectId()));
                            }

                            List<TimeSheetProjectAssignVO> timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearch(EmployeeId, timeSheetProjectAssign.getProjectName().getProjectId(), timeSheetProjectAssign.getEnterDate(), timeSheetProjectAssign.getProjectActivity().getProjectActivityId());
                            if (timeSheetProjectAssignList.isEmpty()) {
                                timeSheetProjectAssign.setCreated(DateUtils.getCurrentDateTime());
                                timeSheetProjectAssign.setCreatedBy(oEmp);
                                timeSheetProjectAssignService.insertTimeSheetProjectAssign(timeSheetProjectAssign);
                            } else {
                                Iterator<TimeSheetProjectAssignVO> itr = timeSheetProjectAssignList.iterator();
                                while (itr.hasNext()) {
                                    try {
                                        timeSheetProjectAssign.setTimeSheetProjectAssignId(itr.next().getTimeSheetProjectAssignId());
                                        timeSheetProjectAssignService.updateTimeSheetProjectAssign(timeSheetProjectAssign);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            if (activitylist.size() > 1) {
                                timeSheetProjectAssign.setProjectActivity(activityService.isProjectAndActivityExists(strActivity, test.getProjectId(), approver.getHcmoApprovingEmpId().getEmployeeId()));
                            } else {
                                timeSheetProjectAssign.setProjectActivity(activityService.getProjectAndActivity(strActivity, test.getProjectId()));
                            }
                            List<TimeSheetProjectAssignVO> timeSheetProjectAssignList = timeSheetProjectAssignService.timeSheetProjectAssignSearch(EmployeeId, timeSheetProjectAssign.getProjectName().getProjectId(), timeSheetProjectAssign.getEnterDate(), timeSheetProjectAssign.getProjectActivity().getProjectActivityId());
                            Iterator<TimeSheetProjectAssignVO> itr = timeSheetProjectAssignList.iterator();
                            while (itr.hasNext()) {
                                try {
                                    timeSheetProjectAssign.setTimeSheetProjectAssignId(itr.next().getTimeSheetProjectAssignId());
                                    timeSheetProjectAssignService.updateTimeSheetProjectAssign(timeSheetProjectAssign);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                    if (!eValue.equals("0")) {
                        ArrayList nameList = new ArrayList();
                        nameList.add(oEmp.getEmpFirstName());
                        nameList.add("Entered");
                        nameList.add(date);
                        nameList.add(category);
                        mailDetailLists.add(nameList);
                    }
                }
            }

            if (status.equals("Save")) {
                EmployeesVO empVao1 = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                session.put("Display", "Added Successfully");

                if (sbTimeSheetDetails.length() > 0) {
                    String[] astr1 = sbTimeSheetDetails.split("<<<<<");
                    if (checkedLists.length() > 0) {
                        acheckList = checkedLists.split("<<<<<");
                    }
                    for (int k = 0; k < astr1.length; k++) {
                        str = astr1[k];
                        category = str.substring(0, str.indexOf("$$$"));
                        date = str.substring(str.lastIndexOf("$") + 1, str.indexOf("=")).trim();
                        String[] aDate = date.split("-");
                        sDate = aDate[1] + "/" + aDate[0] + "/" + aDate[2];
                        Date dDate = new Date(Integer.parseInt(aDate[2]) - 1900, (Integer.parseInt(aDate[1]) - 1), Integer.parseInt(aDate[0]));
                        eValue = str.substring(str.indexOf("=") + 1).trim();

                        if (!eValue.equals("0")) {
                            timeSheetCategoryAssignVO.setEnterDate(dDate);
                            timeSheetCategoryAssignVO.setEnterTime(Double.valueOf(eValue));
                            timeSheetCategoryAssignVO.setTimesheetCategoryName(timesheetCategoryService.getTimesheetCategoryId(category));
                            timeSheetCategoryAssignVO.setEmployeeName(employeesVO);
                            timeSheetCategoryAssignVO.setIsActive(1);
                            timeSheetCategoryAssignVO.setApprove(0);
                            timeSheetCategoryAssignVO.setRejected(0);
                            timeSheetCategoryAssignVO.setRework(0);
                            timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                            if (submit.equals("Approve")) {
                                for (int j = 0; j < acheckList.length; j++) {
                                    status = "Approve";
                                    if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                        timeSheetCategoryAssignVO.setApprove(1);
                                        sendMail(category, "Apporved", sDate, EmployeeId);
                                    }
                                }

                            } else if (submit.equals("Reject")) {
                                for (int j = 0; j < acheckList.length; j++) {
                                    status = "Reject";
                                    if (acheckList[j].equals(str.substring(0, str.indexOf("=")).trim())) {
                                        timeSheetCategoryAssignVO.setRejected(1);
                                        sendMail(category, "Rejected", sDate, EmployeeId);
                                    }
                                }
                            } else if (submit.equals("Rework")) {
                                for (int j = 0; j < acheckList.length; j++) {
                                    status = "Rework";
                                    if (acheckList[j].equals(str.substring(0, str.indexOf("=")))) {
                                        timeSheetCategoryAssignVO.setRework(1);
                                        sendMail(category, "Reworked", sDate, EmployeeId);
                                    }
                                }
                            } else {
                                timeSheetCategoryAssignVO.setApprove(0);
                                timeSheetCategoryAssignVO.setRejected(0);
                                timeSheetCategoryAssignVO.setRework(0);
                            }
                            if (submit.equals("Save All")) {
                                status = "Save";
                                List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(EmployeeId, timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId(), timeSheetCategoryAssignVO.getEnterDate());
                                if (timeSheetCategoryAssignList.isEmpty()) {
                                    timeSheetCategoryAssignVO.setCreated(DateUtils.getCurrentDateTime());
                                    timeSheetCategoryAssignVO.setCreatedBy(oEmp);
                                    timeSheetCategoryAssignService.insertTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                                } else {
                                    Iterator<TimeSheetCategoryAssignVO> itr = timeSheetCategoryAssignList.iterator();
                                    while (itr.hasNext()) {
                                        try {
                                            timeSheetCategoryAssignVO.setTimeSheetCategoryAssignId(itr.next().getTimeSheetCategoryAssignId());
                                            timeSheetCategoryAssignService.updateTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            } else {
                                List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(EmployeeId, timesheetCategoryService.getTimesheetCategoryId(category).getHcmoTimesheetCategoryId(), timeSheetCategoryAssignVO.getEnterDate());
                                Iterator<TimeSheetCategoryAssignVO> itr = timeSheetCategoryAssignList.iterator();
                                while (itr.hasNext()) {
                                    try {
                                        timeSheetCategoryAssignVO.setTimeSheetCategoryAssignId(itr.next().getTimeSheetCategoryAssignId());
                                        timeSheetCategoryAssignService.updateTimeSheetCategoryAssign(timeSheetCategoryAssignVO);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }

                    }
                }
                new SimpleDateFormat("dd");
                new SimpleDateFormat("MM");
                new SimpleDateFormat("yyyy");
                String sSubject = getText("message.subject.timesheetEntered");
                Subj = empVao1.getEmpFirstName() + " Timesheet been Entered.";
                message.setMessage(Subj);
                message.setCreatedBy(oEmp);
                message.setUpdatedBy(oEmp);
                message.setSender(oEmp);
                message.setType(Constants.MESSAGE_IN_ALERT);
                message.setIsActive(1);
                message.setReceiver(empVao1.getEmployeeId().toString());
                for (TimeSheetApproverVO timeSheetApproverVO : timeSheetApproverList) {
                    mail(mailDetailLists, message, timeSheetApproverVO.getHcmoApprovingEmpId(), sSubject);
                    message.setReceiver(timeSheetApproverVO.getHcmoApprovingEmpId().getEmployeeId().toString());
                    message.setReceiverEmailId(timeSheetApproverVO.getHcmoApprovingEmpId().getEmpWorkEmail());
                }
                Subj = oEmp.getEmpFirstName() + " has Entered your Timesheet.";
                message.setMessage(Subj);
                message.setSender(oEmp);
                message.setReceiverEmailId(oEmp.getEmpWorkEmail());
                mail(mailDetailLists, message, empVao1, sSubject);

                // Sending mail to admin is blocked and need to check the requirement
//                role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
//                EmployeesVO adminRoleId = roleSerivce.getEmployeeId(role.getHcmoRoleId());
//                if (adminRoleId.getEmployeeId() != oEmp.getEmployeeId()) {
//                    Subj = oEmp.getEmpFirstName() + " has Entered " + empVao1.getEmpFirstName()
//                        + " Timesheet.";
//                    message.setMessage(Subj);
//                    message.setSender(oEmp);
//                    message.setReceiverEmailId(adminRoleId.getEmpWorkEmail());
//                    mail(mailDetailLists, message, adminRoleId, sSubject);
//                }

            } else if (status.equals("Approve")) {
                session.put("Display", "Approved Successfully");
                String sSubject = getText("message.subject.timesheetApprove");
                Subj = empVao.getEmpFirstName() + " Timesheet been Approved.";
                message.setMessage(Subj);
                message.setCreatedBy(oEmp);
                message.setUpdatedBy(oEmp);
                message.setSender(oEmp);
                message.setType(Constants.MESSAGE_IN_ALERT);
                message.setIsActive(1);
                message.setReceiver(empVao.getEmployeeId().toString());

//                role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
//                EmployeesVO adminRoleId = roleSerivce.getEmployeeId(role.getHcmoRoleId());
                for (TimeSheetApproverVO timeSheetApproverVO : timeSheetApproverList) {
//                    if (!(adminRoleId.getEmployeeId().equals(timeSheetApproverVO.getHcmoApprovingEmpId().getEmployeeId()))) {
                        mail(mailDetailLists, message, timeSheetApproverVO.getHcmoApprovingEmpId(), sSubject);
//                    }
                }

                Subj = oEmp.getEmpFirstName() + " has Approved your Timesheet.";
                message.setMessage(Subj);
                message.setSender(oEmp);
                message.setReceiverEmailId(oEmp.getEmpWorkEmail());
                mail(mailDetailLists, message, empVao, sSubject);

//                if (adminRoleId.getEmployeeId() != oEmp.getEmployeeId()) {
//                    Subj = oEmp.getEmpFirstName() + " has approved " + empVao.getEmpFirstName()
//                        + " Timesheet.";
//                } else {
//                    Subj = "You have approved " + empVao.getEmpFirstName() + " ";
//                }
//                message.setMessage(Subj);
//                message.setSender(oEmp);
//                message.setReceiverEmailId(adminRoleId.getEmpWorkEmail());
//                mail(mailDetailLists, message, adminRoleId, sSubject);
            } else if (status.equals("Reject")) {
                session.put("Display", "Rejected Successfully");
                String sSubject = getText("message.subject.timesheetReject");
                Subj = empVao.getEmpFirstName() + " Timesheet been Rejected.";
                message.setMessage(Subj);
                message.setCreatedBy(oEmp);
                message.setUpdatedBy(oEmp);
                message.setType(Constants.MESSAGE_IN_ALERT);
                message.setSender(oEmp);
                message.setIsActive(1);
                message.setReceiver(empVao.getEmployeeId().toString());
                for (TimeSheetApproverVO timeSheetApproverVO : timeSheetApproverList) {
                    mail(mailDetailLists, message, timeSheetApproverVO.getHcmoApprovingEmpId(), sSubject);
                    message.setReceiver(timeSheetApproverVO.getHcmoApprovingEmpId().getEmployeeId().toString());
                }
                Subj = oEmp.getEmpFirstName() + " has Rejected your Timesheet.";
                message.setMessage(Subj);
                message.setSender(oEmp);
                mail(mailDetailLists, message, empVao, sSubject);

//                role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
//                EmployeesVO adminRoleId = roleSerivce.getEmployeeId(role.getHcmoRoleId());
//                if (adminRoleId.getEmployeeId() != oEmp.getEmployeeId()) {
//                    Subj = oEmp.getEmpFirstName() + " has Rejected " + empVao.getEmpFirstName()
//                        + " Timesheet.";
//                    message.setMessage(Subj);
//                    message.setSender(oEmp);
//                    message.setReceiverEmailId(adminRoleId.getEmpWorkEmail());
//                    mail(mailDetailLists, message, adminRoleId, sSubject);
//                }
            } else if (status.equals("Rework")) {
                session.put("Display", "Reworked Successfully");
                String sSubject = getText("message.subject.timesheetRework");
                Subj = empVao.getEmpFirstName() + " Timesheet been Reworked.";
                message.setMessage(Subj);
                message.setCreatedBy(oEmp);
                message.setUpdatedBy(oEmp);
                message.setSender(oEmp);
                message.setIsActive(1);
                message.setType(Constants.MESSAGE_IN_ALERT);
                message.setReceiver(empVao.getEmployeeId().toString());
                for (TimeSheetApproverVO timeSheetApproverVO : timeSheetApproverList) {
                    mail(mailDetailLists, message, timeSheetApproverVO.getHcmoApprovingEmpId(), sSubject);
                    message.setReceiver(timeSheetApproverVO.getHcmoApprovingEmpId().getEmployeeId().toString());
                    message.setReceiverEmailId(timeSheetApproverVO.getHcmoApprovingEmpId().getEmpWorkEmail());
                }
                Subj = oEmp.getEmpFirstName() + " has Reworked your Timesheet.";
                message.setMessage(Subj);
                message.setSender(oEmp);
                message.setReceiverEmailId(oEmp.getEmpWorkEmail());
                mail(mailDetailLists, message, empVao, sSubject);

                // Blocked mail to admin
//                role = roleSerivce.getRoleName(getText("message.label.common.adminName"));
//                EmployeesVO adminRoleId = roleSerivce.getEmployeeId(role.getHcmoRoleId());
//                if (adminRoleId.getEmployeeId() != oEmp.getEmployeeId()) {
//                    Subj = oEmp.getEmpFirstName() + " has Reworked " + empVao.getEmpFirstName()
//                        + " Timesheet.";
//                    message.setMessage(Subj);
//                    message.setSender(oEmp);
//                    message.setReceiverEmailId(adminRoleId.getEmpWorkEmail());
//                    mail(mailDetailLists, message, adminRoleId, sSubject);
//                }
            } else {
                session.put("Display", "Updated Successfully");
            }
        } catch (NumberFormatException e) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Invalid Entry");
            e.printStackTrace();
        }
        return SUCCESS;
    }

//    //For Timesheet Notes
//    @SkipValidation
//    public String timesheetNotes() {
//    	return SUCCESS;
//    }


    @SkipValidation
    public String timeTrackCheckInOut() {
        if (buttonValue.equals("Check In")) {
            return "checkin";
        } else {
            if (timesheetCategory.getHcmoTimesheetCategoryId() != null) {
                return "CategoryCheckOut";
            } else {
                return "ProjectCheckOut";
            }
        }
    }

    @SkipValidation
    public String insertTimeTrackValues() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        session.get("EmployeeId");
        timeTrackVO = new TimeTrackVO();
        if ((projectAssignEmpId == null) || projectAssignEmpId.equals("")) {
            dropdownStatus = "category";
        } else {
            dropdownStatus = "project";
        }

        if (dropdownStatus.equals("project")) {
            projectAssigndetails = projectAssignEmpService.getProjectAssignEmp(Integer.parseInt(projectAssignEmpId));
        }
        try {
            Calendar.getInstance();
            Date newdate = new Date();
            Timestamp ts1 = new Timestamp(newdate.getTime());

            timeTrackVO.setEmpId(oEmp.getEmployeeId());
            if (dropdownStatus.equals("category")) {
                timeTrackVO.setCategoryId(timesheetCategory.getHcmoTimesheetCategoryId());
            } else {
                timeTrackVO.setCategoryId(0);
            }
            if (dropdownStatus.equals("project")) {
                timeTrackVO.setProjectId(projectAssigndetails.getProjectName().getProjectId());
                timeTrackVO.setActivityId(projectAssigndetails.getProjectActivityId().getProjectActivityId());
            } else {
                timeTrackVO.setProjectId(0);
                timeTrackVO.setActivityId(0);
            }
                        timeTrackVO.setStart(ts1);
            timeTrackVO.setStop(ts1);
            timeTrackVO.setDuration(0.0);
            timeTrackVO.setCheckIn(1);
            timeTrackVO.setCheckOut(1);
            timeTrackVO.setCreated(DateUtils.getCurrentDateTime());
            timeTrackVO.setCreatedBy(oEmp);
            timeTrackVO.setUpdatedBy(oEmp);
            timeTrackVO.setIsActive(1);

            chktimetrackInOutList = timeTrackService.chkInOutTimeTrack(timeTrackVO);
            if (chktimetrackInOutList.size() > 0) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Please Check Out the previous Entry");
                return ERROR;
            } else {
                timeTrackService.insertInOutTime(timeTrackVO);
            }
                   } catch (Exception e) {
        }
        return SUCCESS;
    }

    @SkipValidation
    public String checkOutActivity() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");

        ProjectAssignEmpVO projectAssigndetails = projectAssignEmpService.getProjectAssignEmp(Integer.parseInt(projectAssignEmpId));
        timeTrackVO = new TimeTrackVO();
        try {
            Calendar.getInstance();
            Date newdate = new Date();
            Timestamp ts1 = new Timestamp(newdate.getTime());
            double duration = 0.0;
            timeTrackVO.setEmpId(oEmp.getEmployeeId());
            if (timesheetCategory.getHcmoTimesheetCategoryId() != null) {
                timeTrackVO.setCategoryId(timesheetCategory.getHcmoTimesheetCategoryId());
            } else {
                timeTrackVO.setCategoryId(0);
            }
            if (projectAssigndetails.getProjectName().getProjectId() != null) {
                timeTrackVO.setProjectId(projectAssigndetails.getProjectName().getProjectId());
                timeTrackVO.setActivityId(projectAssigndetails.getProjectActivityId().getProjectActivityId());
            } else {
                timeTrackVO.setProjectId(0);
                timeTrackVO.setActivityId(0);
            }

            timeTrackVO.setStart(ts1);
            timeTrackVO.setStop(ts1);
            timeTrackVO.setDuration(0.0);
            timeTrackVO.setCheckIn(1);
            timeTrackVO.setCheckOut(1);
            timeTrackVO.setCreated(DateUtils.getCurrentDateTime());
            timeTrackVO.setCreatedBy(oEmp);
            timeTrackVO.setUpdatedBy(oEmp);
            timeTrackVO.setIsActive(1);
            timeTrackService.updateCheckOut(timeTrackVO);
            timetrackHistoryList = timeTrackService.getAllTimeDetails(timeTrackVO);
            if (timetrackHistoryList.size() > 0) {
                for (int j = 0; j <= (timetrackHistoryList.size() - 1); j++) {
                    try {
                        java.util.Date date1 = new java.util.Date(timetrackHistoryList.get(j).getStart().getTime());
                        java.util.Date date2 = new java.util.Date(timetrackHistoryList.get(j).getStop().getTime());
                        long d1Ms = date2.getTime();
                        long d2Ms = date1.getTime();
                        long minute = Math.abs((d1Ms - d2Ms) / 60000);
                        int Hours = (int) minute / 60;
                        int Minutes = (int) minute % 60;
                        duration += Double.parseDouble(Hours + "." + Minutes);
                        timetrackHistoryList.get(j).setDuration(duration);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
                List chkExistingTimeList = new ArrayList();
                chkExistingTimeList = timeTrackService.timeSheetProjectAssignSearch(oEmp.getEmployeeId(), timeTrackVO, DateUtils.getCurrentDateTime());
                EmployeesVO employeesVO = new EmployeesVO();
                employeesVO.setEmployeeId(EmployeeId);
                ProjectActivityVO activityVo = new ProjectActivityVO();
                activityVo.setProjectActivityId(timeTrackVO.getActivityId());
                ProjectVO projectVo = new ProjectVO();
                projectVo.setProjectId(timeTrackVO.getProjectId());

                if (chkExistingTimeList.size() > 0) {
                    TimeSheetProjectAssignVO timeSheetProjectAssignVO = new TimeSheetProjectAssignVO();
                    timeSheetProjectAssignVO.setEmployeeName(employeesVO);
                    timeSheetProjectAssignVO.setEnterTime(duration);
                    timeSheetProjectAssignVO.setProjectActivity(activityVo);
                    timeSheetProjectAssignVO.setProjectName(projectVo);
                    timeSheetProjectAssignVO.setUpdatedBy(oEmp);
                    timeSheetProjectAssignVO.setUpdated(DateUtils.getCurrentTime());
                    timeTrackService.updateTimeSheetProjectAssign(timeSheetProjectAssignVO, EmployeeId);
                } else {
                    TimeSheetProjectAssignVO timeSheetProjectAssignVO = new TimeSheetProjectAssignVO();
                    timeSheetProjectAssignVO.setEnterDate(new Date());
                    timeSheetProjectAssignVO.setEnterTime(duration);
                    timeSheetProjectAssignVO.setEmployeeName(employeesVO);
                    timeSheetProjectAssignVO.setProjectActivity(activityVo);
                    timeSheetProjectAssignVO.setProjectName(projectVo);
                    timeSheetProjectAssignVO.setIsActive(1);
                    timeSheetProjectAssignVO.setApprove(0);
                    timeSheetProjectAssignVO.setRejected(0);
                    timeSheetProjectAssignVO.setRework(0);
                    timeSheetProjectAssignVO.setCreated(new Date());
                    timeSheetProjectAssignVO.setCreatedBy(oEmp);
                    timeSheetProjectAssignVO.setUpdatedBy(oEmp);
                    timeTrackService.insertTimeSheetActivity(timeSheetProjectAssignVO, EmployeeId);
                }
                duration = 0.0;
            }
            if (timetrackHistoryList.size() == 0) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Please select the valid");
                return ERROR;
            }
        } catch (Exception e) {
        }
        return SUCCESS;
    }

    @SkipValidation
    public String updateTimeTrackValues() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = (Integer) session.get("EmployeeId");
        timesheetCategoryEmpList = timesheetCategoryEmpService.getEmpTimeSheetCategory(EmployeeId);
        Iterator<TimesheetCategoryEmpVO> itrr = timesheetCategoryEmpList.iterator();
        timesheetCategoryList = new ArrayList<TimesheetCategoryVO>();
        while (itrr.hasNext()) {
            TimesheetCategoryEmpVO timesheetCategoryEmpVO = itrr.next();
            timesheetCategory = timesheetCategoryService.getTimesheetCategory(timesheetCategoryEmpVO.getTimesheetCategoryName().getHcmoTimesheetCategoryId());
            timesheetCategoryList.add(timesheetCategory);
        }
        if (timesheetCategoryList.size() > 0) {
            for (int i = 0; i <= (timesheetCategoryList.size() - 1); i++) {
                timeTrackVO = new TimeTrackVO();
                try {
                    Calendar.getInstance();
                    Date newdate = new Date();
                    Timestamp ts1 = new Timestamp(newdate.getTime());
                    double duration = 0.0;
                    timeTrackVO.setEmpId(oEmp.getEmployeeId());
                    timeTrackVO.setCategoryId(timesheetCategoryList.get(i).getHcmoTimesheetCategoryId());
                    timeTrackVO.setStop(ts1);
                    timeTrackVO.setDuration(0.0);
                    timeTrackVO.setCheckIn(1);
                    timeTrackVO.setCheckOut(1);
                    timeTrackVO.setCreated(DateUtils.getCurrentDateTime());
                    timeTrackVO.setCreatedBy(oEmp);
                    timeTrackVO.setUpdatedBy(oEmp);
                    timeTrackVO.setIsActive(0);
                    timeTrackService.updateCheckOut(timeTrackVO);
                    timetrackHistoryList = timeTrackService.getAllTimeTrack(timeTrackVO);
                    if (timetrackHistoryList.size() > 0) {
                        for (int j = 0; j <= (timetrackHistoryList.size() - 1); j++) {
                            try {
                                java.util.Date date1 = new java.util.Date(timetrackHistoryList.get(j).getStart().getTime());
                                java.util.Date date2 = new java.util.Date(timetrackHistoryList.get(j).getStop().getTime());
                                long d1Ms = date2.getTime();
                                long d2Ms = date1.getTime();
                                long minute = Math.abs((d1Ms - d2Ms) / 60000);
                                int Hours = (int) minute / 60;
                                int Minutes = (int) minute % 60;
                                duration += Double.parseDouble(Hours + "." + Minutes);

                                timetrackHistoryList.get(j).setDuration(duration);
                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                            duration = 0.0;
                        }
                    }
                    timetrackUpdateList = timeTrackService.getAllTimeTrackById(timeTrackVO);
                    if (timetrackUpdateList.size() > 0) {
                        double updateDuration = 0.0;
                        for (int j = 0; j <= (timetrackUpdateList.size() - 1); j++) {
                            try {
                                java.util.Date date1 = new java.util.Date(timetrackUpdateList.get(j).getStart().getTime());
                                java.util.Date date2 = new java.util.Date(timetrackUpdateList.get(j).getStop().getTime());
                                long d1Ms = date2.getTime();
                                long d2Ms = date1.getTime();
                                long minute = Math.abs((d1Ms - d2Ms) / 60000);
                                int Hours = (int) minute / 60;
                                int Minutes = (int) minute % 60;
                                updateDuration += round(Double.parseDouble(Hours + "." + Minutes), 2);

                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                        }
                        List chkExistingTimeList = new ArrayList();
                        chkExistingTimeList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearch(oEmp.getEmployeeId(), timesheetCategoryList.get(i).getHcmoTimesheetCategoryId(), DateUtils.getCurrentDateTime());
                        EmployeesVO employeesVO = new EmployeesVO();
                        employeesVO.setEmployeeId(EmployeeId);
                        if (chkExistingTimeList.size() > 0) {
                            timeSheetCategoryAssignVO = new TimeSheetCategoryAssignVO();
                            timeSheetCategoryAssignVO.setEmployeeName(employeesVO);
                            timeSheetCategoryAssignVO.setEnterTime(updateDuration);
                            timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                            timeSheetCategoryAssignVO.setUpdated(DateUtils.getCurrentTime());
                            timeTrackService.updateTimeSheetCategoryAssign(timeSheetCategoryAssignVO, EmployeeId, timesheetCategoryList.get(i).getHcmoTimesheetCategoryId());
                        } else {
                            timeSheetCategoryAssignVO = new TimeSheetCategoryAssignVO();
                            timeSheetCategoryAssignVO.setEnterDate(new Date());
                            timeSheetCategoryAssignVO.setEnterTime(updateDuration);

                            timeSheetCategoryAssignVO.setTimesheetCategoryName(timesheetCategoryService.getTimesheetCategoryId(timesheetCategoryList.get(i).getName()));

                            timeSheetCategoryAssignVO.setEmployeeName(employeesVO);
                            timeSheetCategoryAssignVO.setIsActive(1);
                            timeSheetCategoryAssignVO.setApprove(0);
                            timeSheetCategoryAssignVO.setRejected(0);
                            timeSheetCategoryAssignVO.setRework(0);
                            timeSheetCategoryAssignVO.setCreated(new Date());
                            timeSheetCategoryAssignVO.setCreatedBy(oEmp);
                            timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                            timeTrackService.insertTimeSheet(timeSheetCategoryAssignVO, EmployeeId, timesheetCategoryList.get(i).getHcmoTimesheetCategoryId());
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (timetrackHistoryList.size() == 0) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Select a valid project/Category name for checkout");
            return ERROR;
        }
        return SUCCESS;
    }

    // DashBoard TimeSheet For Approval
    @SkipValidation
    public String getTimeSheetForApproval() {
        forApprovalCount = timeSheetApproverService.getDashTsForApproval().toString();
        forApprovalToday = timeSheetApproverService.getDashTsForApprovalToday().toString();
        forApprovalThreeDays = timeSheetApproverService.getDashTsForApprovalThreeDays().toString();

        forApprovalOneWeek = timeSheetApproverService.getDashTsForApprovalOneWeek().toString();

        return SUCCESS;
    }

    public void sendMail(String sCategoryName, String sStatus, String Date, Integer iEmpId) {
        empVao = employeesService.getEmployees(iEmpId);
        ArrayList mailList = new ArrayList();
        mailList.add(empVao.getEmpFirstName());
        mailList.add(sStatus);
        mailList.add(Date);
        mailList.add(sCategoryName);
        mailDetailLists.add(mailList);
    }

    public String mail(ArrayList<ArrayList> mailDetailLists, MessageVO message, EmployeesVO empVo, String sSubject) {
        message.setCreated(new Date());
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        HCMOneMailer mailer = new HCMOneMailer();
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        MessageService messageService = new MessageDaoService();
        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG + getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), empVo.getEmpFirstName());

        sMessage.append(HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE + message.getMessage());
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Name  " + HtmlConstants.HTML_SPACE
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + mailDetailLists.get(0).get(0).toString()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_BREAK);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Status  " + HtmlConstants.HTML_SPACE
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + mailDetailLists.get(0).get(1)
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "" + HtmlConstants.HTML_SPACE
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        for (int i = 0; i < mailDetailLists.size(); i++) {
            arrayList = mailDetailLists.get(i);

            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Entered Date"
                + HtmlConstants.HTML_SPACE + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + arrayList.get(2) + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Category"
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + arrayList.get(3)
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + arrayList.get(1) + " Date"
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + simpleDateFormat.format(message.getCreated())
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        }
        sMessage.append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);
        sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);
        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = getText("alert.common.signature");
        } else {
            for (Iterator it = signatureList.iterator(); it.hasNext();) {
                sigObj = (SignatureVO) it.next();
                if (sigObj.isPreSignature() == true) {
                    sSignature = sigObj.getSignatureName();
                }
            }
        }
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        message.setMessage(sMessage.toString());
        message.setReceiver(empVo.getEmployeeId().toString());
        message.setReceiverEmailId(empVo.getEmpWorkEmail());
        mailer.sendMail(message.getSender().getEmpWorkEmail(), empVo.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), new String(), new String());
        messageService.insertMessage(message);
        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
        session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(oEmp.getEmployeeId()));
        return sMessage.toString();
    }

    public double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public TimeSheetCategoryAssignService getTimeSheetCategoryAssignService() {
        return timeSheetCategoryAssignService;
    }

    public void setTimeSheetCategoryAssignService(TimeSheetCategoryAssignService timeSheetCategoryAssignService) {
        this.timeSheetCategoryAssignService = timeSheetCategoryAssignService;
    }

    public TimeSheetCategoryAssignVO getTimeSheetCategoryAssignVO() {
        return timeSheetCategoryAssignVO;
    }

    public void setTimeSheetCategoryAssignVO(TimeSheetCategoryAssignVO timeSheetCategoryAssignVO) {
        this.timeSheetCategoryAssignVO = timeSheetCategoryAssignVO;
    }

    public List<TimeSheetCategoryAssignVO> getTimeSheetCategoryAssignList() {
        return timeSheetCategoryAssignList;
    }

    public void setTimeSheetCategoryAssignList(List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList) {
        this.timeSheetCategoryAssignList = timeSheetCategoryAssignList;
    }

    public List getDateList() {
        return dateList;
    }

    public void setDateList(List dateList) {
        this.dateList = dateList;
    }

    public String getMonthList() {
        return monthList;
    }

    public void setMonthList(String monthList) {
        this.monthList = monthList;
    }

    public String getYearList() {
        return yearList;
    }

    public void setYearList(String yearList) {
        this.yearList = yearList;
    }

    public String getMonthList1() {
        return monthList1;
    }

    public void setMonthList1(String monthList1) {
        this.monthList1 = monthList1;
    }

    public String getYearList1() {
        return yearList1;
    }

    public void setYearList1(String yearList1) {
        this.yearList1 = yearList1;
    }

    public Boolean getIsCurrentYear() {
        return isCurrentYear;
    }

    public void setIsCurrentYear(Boolean isCurrentYear) {
        this.isCurrentYear = isCurrentYear;
    }

    public Boolean getIsCurrentMonth() {
        return isCurrentMonth;
    }

    public void setIsCurrentMonth(Boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
    }

    public Boolean getIsEntered() {
        return isEntered;
    }

    public void setIsEntered(Boolean isEntered) {
        this.isEntered = isEntered;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsReworked() {
        return isReworked;
    }

    public void setIsReworked(Boolean isReworked) {
        this.isReworked = isReworked;
    }

    public TreeMap<String, ArrayList<String>> getDateDetail() {
        return dateDetail;
    }

    public void setDateDetail(TreeMap<String, ArrayList<String>> dateDetail) {
        this.dateDetail = dateDetail;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getMonthInput() {
        return monthInput;
    }

    public void setMonthInput(String monthInput) {
        this.monthInput = monthInput;
    }

    public String getYearInput() {
        return yearInput;
    }

    public void setYearInput(String yearInput) {
        this.yearInput = yearInput;
    }

    public List getWeekList() {
        return weekList;
    }

    public void setWeekList(List weekList) {
        this.weekList = weekList;
    }

    public ArrayList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList categoryList) {
        this.categoryList = categoryList;
    }

    public List<TimesheetCategoryVO> getTimesheetCategoryList() {
        return timesheetCategoryList;
    }

    public void setTimesheetCategoryList(List<TimesheetCategoryVO> timesheetCategoryList) {
        this.timesheetCategoryList = timesheetCategoryList;
    }

    public TimesheetCategoryService getTimesheetCategoryService() {
        return timesheetCategoryService;
    }

    public void setTimesheetCategoryService(TimesheetCategoryService timesheetCategoryService) {
        this.timesheetCategoryService = timesheetCategoryService;
    }

    public TimesheetCategoryVO getTimesheetCategory() {
        return timesheetCategory;
    }

    public void setTimesheetCategory(TimesheetCategoryVO timesheetCategory) {
        this.timesheetCategory = timesheetCategory;
    }

    public TreeMap<String, TreeMap<String, ArrayList<String>>> getTimeSheetCategoryMap() {
        return timeSheetCategoryMap;
    }

    public void setTimeSheetCategoryMap(TreeMap<String, TreeMap<String, ArrayList<String>>> timeSheetCategoryMap) {
        this.timeSheetCategoryMap = timeSheetCategoryMap;
    }

    public String getSbTimeSheetDetails() {
        return sbTimeSheetDetails;
    }

    public void setSbTimeSheetDetails(String sbTimeSheetDetails) {
        this.sbTimeSheetDetails = sbTimeSheetDetails;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getCheckedLists() {
        return checkedLists;
    }

    public void setCheckedLists(String checkedLists) {
        this.checkedLists = checkedLists;
    }

    public TreeMap<String, TreeMap<String, ArrayList<String>>> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(TreeMap<String, TreeMap<String, ArrayList<String>>> projectMap) {
        this.projectMap = projectMap;
    }

    public TreeMap<String, ArrayList<String>> getProjectdateDetail() {
        return projectdateDetail;
    }

    public void setProjectdateDetail(TreeMap<String, ArrayList<String>> projectdateDetail) {
        this.projectdateDetail = projectdateDetail;
    }

    public String getProTimeSheetDetails() {
        return proTimeSheetDetails;
    }

    public void setProTimeSheetDetails(String proTimeSheetDetails) {
        this.proTimeSheetDetails = proTimeSheetDetails;
    }

    public String getForApprovalCount() {
        return forApprovalCount;
    }

    public void setForApprovalCount(String forApprovalCount) {
        this.forApprovalCount = forApprovalCount;
    }

    public String getForApprovalToday() {
        return forApprovalToday;
    }

    public void setForApprovalToday(String forApprovalToday) {
        this.forApprovalToday = forApprovalToday;
    }

    public String getForApprovalThreeDays() {
        return forApprovalThreeDays;
    }

    public void setForApprovalThreeDays(String forApprovalThreeDays) {
        this.forApprovalThreeDays = forApprovalThreeDays;
    }

    public String getForApprovalOneWeek() {
        return forApprovalOneWeek;
    }

    public void setForApprovalOneWeek(String forApprovalOneWeek) {
        this.forApprovalOneWeek = forApprovalOneWeek;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public TimeTrackService getTimeTrackService() {
        return timeTrackService;
    }

    public void setTimeTrackService(TimeTrackService timeTrackService) {
        this.timeTrackService = timeTrackService;
    }

    public TimeTrackVO getTimeTrackVO() {
        return timeTrackVO;
    }

    public void setTimeTrackVO(TimeTrackVO timeTrackVO) {
        this.timeTrackVO = timeTrackVO;
    }

    public List<TimeTrackVO> getTimetrackHistoryList() {
        return timetrackHistoryList;
    }

    public void setTimetrackHistoryList(List<TimeTrackVO> timetrackHistoryList) {
        this.timetrackHistoryList = timetrackHistoryList;
    }

    public List<TimeTrackVO> getChktimetrackInOutList() {
        return chktimetrackInOutList;
    }

    public void setChktimetrackInOutList(List<TimeTrackVO> chktimetrackInOutList) {
        this.chktimetrackInOutList = chktimetrackInOutList;
    }

    public List getCategoryTotalHrs() {
        return categoryTotalHrs;
    }

    public void setCategoryTotalHrs(List categoryTotalHrs) {
        this.categoryTotalHrs = categoryTotalHrs;
    }

    public List getDateTotalHrs() {
        return dateTotalHrs;
    }

    public void setDateTotalHrs(List dateTotalHrs) {
        this.dateTotalHrs = dateTotalHrs;
    }

    public List getActivityTotalHrs() {
        return activityTotalHrs;
    }

    public void setActivityTotalHrs(List activityTotalHrs) {
        this.activityTotalHrs = activityTotalHrs;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public List<ProjectVO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectVO> projectList) {
        this.projectList = projectList;
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }

    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public List<ProjectAssignEmpVO> getProjectAssignEmpList() {
        return projectAssignEmpList;
    }

    public void setProjectAssignEmpList(List<ProjectAssignEmpVO> projectAssignEmpList) {
        this.projectAssignEmpList = projectAssignEmpList;
    }

    public String getWeekDaysList() {
        return weekDaysList;
    }

    public void setWeekDaysList(String weekDaysList) {
        this.weekDaysList = weekDaysList;
    }

    public String getProjectAssignEmpId() {
        return projectAssignEmpId;
    }

    public void setProjectAssignEmpId(String projectAssignEmpId) {
        this.projectAssignEmpId = projectAssignEmpId;
    }

    public List<ProjectAssignEmpVO> getProjectDropDownList() {
        return projectDropDownList;
    }

    public void setProjectDropDownList(List<ProjectAssignEmpVO> projectDropDownList) {
        this.projectDropDownList = projectDropDownList;
    }

    public void setTotalhoursForCategoryAndProject(String totalhoursForCategoryAndProject) {
        this.totalhoursForCategoryAndProject = totalhoursForCategoryAndProject;
    }

    public String getTotalhoursForCategoryAndProject() {
        return totalhoursForCategoryAndProject;
    }

    public void setTimeSheetEnteredDetails(String timeSheetEnteredDetails) {
        this.timeSheetEnteredDetails = timeSheetEnteredDetails;
    }

    public String getTimeSheetEnteredDetails() {
        return timeSheetEnteredDetails;
    }

    public void setProtimeSheetEnteredDetails(String protimeSheetEnteredDetails) {
        this.protimeSheetEnteredDetails = protimeSheetEnteredDetails;
    }

    public String getProtimeSheetEnteredDetails() {
        return protimeSheetEnteredDetails;
    }

    public void setEnteredTotalhoursMessageValue(String enteredTotalhoursMessageValue) {
        this.enteredTotalhoursMessageValue = enteredTotalhoursMessageValue;
    }

    public String getEnteredTotalhoursMessageValue() {
        return enteredTotalhoursMessageValue;
    }

    public void setEnteredTotalhoursFalseValue(String enteredTotalhoursFalseValue) {
        this.enteredTotalhoursFalseValue = enteredTotalhoursFalseValue;
    }

    public String getEnteredTotalhoursFalseValue() {
        return enteredTotalhoursFalseValue;
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

	public ProjectAssignEmpVO getProjectAssign() {
		return projectAssign;
	}

	public void setProjectAssign(ProjectAssignEmpVO projectAssign) {
		this.projectAssign = projectAssign;
	}

	public TimesheetNotesVO getNotesObj() {
		return notesObj;
	}

	public void setNotesObj(TimesheetNotesVO notesObj) {
		this.notesObj = notesObj;
	}

	public String getNotesDate() {
		return notesDate;
	}

	public void setNotesDate(String notesDate) {
		this.notesDate = notesDate;
	}

	public List<TimesheetNotesVO> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<TimesheetNotesVO> notesList) {
		this.notesList = notesList;
	}

	public ArrayList getActivityEnteredHourList() {
		return activityEnteredHourList;
	}

	public void setActivityEnteredHourList(ArrayList activityEnteredHourList) {
		this.activityEnteredHourList = activityEnteredHourList;
	}


}