
package com.gits.rms.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.EventsDaoService;
import com.gits.rms.service.EventsService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.PropertyUtil;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EventsVO;
import com.gits.rms.vo.MenuVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.SignatureVO;
import com.gits.rms.xml.utils.Reader;

public class MenuAction extends ActionSupport implements ServletRequestAware, SessionAware {

    private static final long serialVersionUID = -5371734789573038198L;
    private Map session = ActionContext.getContext().getSession();

    Calendar thisMonth = Calendar.getInstance();
    private EmployeesService employeeService = new EmployeesDaoService();
    private String headerDesign;
    private String tableCellDesign;
    public SimpleDateFormat dfDateField = new SimpleDateFormat("M/d/yy");


    // display month for title and next/last month navigation
    public SimpleDateFormat monthName = new SimpleDateFormat("MMMM");
    // display year
    public SimpleDateFormat year = new SimpleDateFormat("yyyy");
    public SimpleDateFormat month = new SimpleDateFormat("M");
    public SimpleDateFormat day = new SimpleDateFormat("d");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("MM/d/yy");


    // matches format used by MySQL database
    public SimpleDateFormat dfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat dfDayOfMonth = new SimpleDateFormat("d");
    public SimpleDateFormat sdfNewEvent = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public SimpleDateFormat sdfNewEventFormat = new SimpleDateFormat("MM/dd/yyyy");
    public SimpleDateFormat dateformat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    private String pathURL = this.getText("ApplicationURL") + this.getText("ProjectName")
        + "/viewScheduler.action";
    private String yearList = "";

    private String monthValue = "";
    private int uniqueValue;

    private List birthdayList = new ArrayList();
    private List eventList = new ArrayList();
    private HashMap<String, List> dateAndList = new HashMap<String, List>();

    private List<EmployeesVO> empBirthdayList;
    private EmployeesService emplService = new EmployeesDaoService();
    private EventsService eventService = new EventsDaoService();

    private List<EmployeesVO> employees = new ArrayList<EmployeesVO>();
    private List<EventsVO> eventsList = new ArrayList<EventsVO>();
    private List<EventsVO> editEvent = new ArrayList<EventsVO>();
    private List<EventsVO> thisYearEvents = new ArrayList<EventsVO>();
    private List hourList = new ArrayList();

    private List timeZoneList = new ArrayList();
    private List employeeId = new ArrayList();


    private String eventName;
    private String eventTime;

    private String description;
    private String dateInput;
    private String monthInput;
    private String yearInput;
    private String selectedDate;
    private String hiddenDate;
    private String timeZone;


    private int eventId;


    private String startDate;
    private MessageVO message = new MessageVO();
    private List<SignatureVO> signatureList;
    private SignatureService signatureService = new SignatureDaoService();
    private String sSignature;
    private SignatureVO sigObj;

    private EmployeesVO employeeDetails = new EmployeesVO();
    Set list = new HashSet();
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    private ConfigurationVO config;
    
	@Override
    public void setServletRequest(HttpServletRequest req) {
    }

    @Override
    public String execute() throws Exception {
    	System.out.println("Execute method inside menu action");
        List<MenuVO> menuList;
        Date today = new Date();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();
        Integer clientId = oEmp.getClientId();


        if (dateInput==null) {
            if (session.get("EventDate")!=null) {
                selectedDate=session.get("EventDate").toString();
            }
        }


        if (dateInput!=null) {
        	if(monthInput.equals("")){
        		monthInput="0";
        	}
            if ((Integer.parseInt(monthInput)==0) || (monthInput==null)) {
                Calendar ca = new GregorianCalendar();
                monthInput=""+(ca.get(Calendar.MONTH)+ 1);
            }


            if ((Integer.parseInt(yearInput)==0)||(yearInput==null)) {
                Calendar ca = new GregorianCalendar();
                yearInput=""+ca.get(Calendar.YEAR);
            }

            selectedDate=monthInput+"/"+dateInput+"/"+yearInput;
        }


        hourList=timeList();

        Date date = new Date();
        String TimeZoneIds[] = TimeZone.getAvailableIDs();

        for (int i = 0; i < TimeZoneIds.length; i++) {
            TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
            tz.getDisplayName(tz.inDaylightTime(date), TimeZone.LONG);
            timeZoneList.add(TimeZoneIds[i]);
            }

        timeZoneList.add(0, "US/Alaska");
        timeZoneList.add(1, "US/Central");

        System.out.println("before getting employee detail");
        employees = employeeService.getAllEmployees(clientId);
        empBirthdayList = emplService.getEmployeeBirthDay(clientId);

        for (int i = 0;i<=(empBirthdayList.size()-1);i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());
        }


        eventsList = eventService.getAllEvents(EmployeeId);
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();

        for (int i=0;i<=(eventsList.size()-1);i++){
            new Date();

            Date eventDate = eventsList.get(i).getStartDate();
            eventList.add(eventDate);
            String keyDate =dfMySQLDate.format(eventDate);
            dateAndList.put(keyDate, eventsList);

            if (Integer.parseInt(simpleDateformat.format(eventDate))==cal.get(Calendar.YEAR)){
                thisYearEvents.add(eventsList.get(i));
            }

        }


        int dat =Integer.parseInt(year.format(today));
        yearList = "" + dat;


        if (session.get("menuList") == null) {
            menuList = createAppMenu();
            session.put("menuList", menuList);
        } else {
            menuList = (List<MenuVO>) session.get("menuList");
        }
        if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
            || (oEmp.getRoleObj().getRoleName().equals("admin"))
            || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
            configList=configService.getAllConfiguration(clientId);
           /* if (!configList.isEmpty()){
                for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
            }*/
            config=configList.get(0);
            if(config.getSkip()==1){
                session.remove("CONFIGURATION");
            }else if(config.getStatus().equals("skippedpermanantely")){
                session.remove("CONFIGURATION");               
            }else if(config.getMailConfiguration()==0 || config.getClient()==0 || config.getLocation()==0 || config.getRegion()==0||config.getSalaryGrade()==0
                ||config.getJobTitle()==0 || config.getDepartment()==0 || config.getTeam()==0 || config.getNationality()==0 || config.getEthnicRace()==0 
                || config.getEmployee()==0){
                String configCheck=(String) session.get("CONFIGURATION");
                if(configCheck=="REMOVE_CONFIGURATION"){
                    session.remove("CONFIGURATION");
                }else{
                    session.put("CONFIGURATION", "CONFIGURATION");  
                }
            }
        }
        return SUCCESS;
    }

    public String insertEvents() throws ParseException {
        Map session = ActionContext.getContext().getSession();
        Date today = new Date();

        int dat = Integer.parseInt(year.format(today));
        yearList = "" + dat;
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();
        String Subj = "";
        long current = System.currentTimeMillis();
        uniqueValue = (int) current;

        Date eventDate;

        eventDate = sdfNewEvent.parse(hiddenDate + " " + eventTime);
        // Convert time zone

        Date converted = toGMT(eventDate, timeZone);
        Timestamp ts_eventDate = new Timestamp(converted.getTime());


        if (employeeId.isEmpty()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Please select the employee(s)");

            session.put("EventDate", hiddenDate);
            return ERROR;
        }

         if (eventName.length() > 49) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Event Name should not be empty");

            session.put("EventDate", hiddenDate);
            return ERROR;
        }


            if (description.length() > 199) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Event Name is too Large");

            session.put("EventDate", hiddenDate);
            return ERROR;
        }


            if (description.length() > 199) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("error", "Description is too Large");

            session.put("EventDate", hiddenDate);
            return ERROR;
        }


        for (int i = 0; i <= (employeeId.size() - 1); i++) {
            employeeDetails = emplService.getEmployeeById(Integer.parseInt(employeeId.get(i).toString()));
            EventsVO eventsVO = new EventsVO();
             eventsVO.setEmployeeId(Integer.parseInt(employeeId.get(i).toString()));
            eventsVO.setEventName(eventName);
            eventsVO.setDescription(description);
            eventsVO.setStartDate(ts_eventDate);
            eventsVO.setGroupId(uniqueValue);
            eventsVO.setTimeZone(timeZone);
                       eventsVO.setCreated(DateUtils.getCurrentDateTime());
            eventsVO.setCreatedBy(oEmp);
            eventsVO.setUpdatedBy(oEmp);
            eventsVO.setIsActive(1);


            eventService.insertEvents(eventsVO);
            Subj = oEmp.getEmpFirstName() + " has created the Event for you";

            message.setMessage(Subj);
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setSender(oEmp);
            message.setIsActive(1);
            message.setReceiver(employeeId.get(i).toString());
            message.setReceiverEmailId(employeeDetails.getEmpWorkEmail());
            mail(eventsVO, message, employeeDetails);
        }


        empBirthdayList = emplService.getEmployeeBirthDay(oEmp.getClientId());
        for (int i = 0; i <= (empBirthdayList.size() - 1); i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());
        }

        eventsList = eventService.getAllEvents(EmployeeId);
        for (int i = 0; i <= (eventsList.size() - 1); i++) {
            Date eventDate1 = eventsList.get(i).getStartDate();
            eventList.add(eventDate1);
            String keyDate = dfMySQLDate.format(eventDate1);
            dateAndList.put(keyDate, eventsList);
        }
        return SUCCESS;
    }

    public String listEvents() {

        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();

        eventsList = eventService.getAllEventsById(EmployeeId);
        return SUCCESS;
    }

    public String getEditEvents() {
        try {


            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            oEmp.getEmployeeId();
             editEvent =eventService.getEventsById(eventId);
             employees =employeeService.getAllEmployees(oEmp.getClientId());
             hourList =timeList();
            Date date = new Date();
            String TimeZoneIds[] = TimeZone.getAvailableIDs();

            for (int i = 0; i < TimeZoneIds.length; i++) {
                TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
                tz.getDisplayName(tz.inDaylightTime(date), TimeZone.LONG);
                timeZoneList.add(TimeZoneIds[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


    public String editEvents() {
        try {


            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            oEmp.getEmployeeId();


            if (employeeId.isEmpty()) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Please select the employee(s)");

                session.put("EventDate", hiddenDate);
                return ERROR;
            }


                if ((eventName == null) || eventName.equals("")) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Event Name should not be empty");

                session.put("EventDate", hiddenDate);
                return ERROR;
            }


                if (eventName.length() > 49) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Event Name is too Large");

                session.put("EventDate", hiddenDate);
                return ERROR;
            }


                 if (description.length() > 199) {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("error", "Description is too Large");

                session.put("EventDate", hiddenDate);
                return ERROR;
            }

            Date eventDate;

             eventDate = sdfNewEvent.parse(startDate + " " +eventTime);
            // Convert time zone

            Date converted = toGMT(eventDate, timeZone);
            Timestamp ts_eventDate = new Timestamp(converted.getTime());


            for (int i = 0; i <= (employeeId.size() - 1); i++) {
                EventsVO eventsVO = new EventsVO();
                 eventsVO.setEventId(eventId);
                eventsVO.setEmployeeId(Integer.parseInt(employeeId.get(i).toString()));
                eventsVO.setEventName(eventName);
                eventsVO.setDescription(description);
                eventsVO.setStartDate(ts_eventDate);
                eventsVO.setTimeZone(timeZone);
                eventsVO.setGroupId(uniqueValue);
                               eventsVO.setCreated(DateUtils.getCurrentDateTime());
                eventsVO.setCreatedBy(oEmp);
                eventsVO.setUpdatedBy(oEmp);
                eventsVO.setIsActive(1);

                eventService.saveOrUpdateEvent(eventsVO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


    public String deleteEvents() {
        try {

            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            oEmp.getEmployeeId();

            EventsVO eventsVO = new EventsVO();
            eventsVO.setEventId(eventId);
            eventsVO.setEventName(eventName);
            eventsVO.setDescription(description);
            eventsVO.setGroupId(uniqueValue);
            eventsVO.setCreated(DateUtils.getCurrentDateTime());
            eventsVO.setCreatedBy(oEmp);
            eventsVO.setUpdatedBy(oEmp);
            eventsVO.setIsActive(0);

            eventService.removeEvent(eventsVO);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String increment() {
        ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();
        Calendar ca = new GregorianCalendar();
        int iYear = Integer.parseInt(yearList);
        int iTyear = ca.get(Calendar.YEAR);
        if (iYear == 0) {
            iYear = iTyear;
        }

        iYear = iYear + 1;

        empBirthdayList = emplService.getEmployeeBirthDay(oEmp.getClientId());
        for (int i = 0; i <= (empBirthdayList.size() - 1); i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());
        }


        eventsList = eventService.getAllEvents(EmployeeId);
        for (int i = 0; i <= (eventsList.size() - 1); i++) {
            Date eventDate = eventsList.get(i).getStartDate();
            eventList.add(eventDate);
            String keyDate = dfMySQLDate.format(eventDate);
            dateAndList.put(keyDate, eventsList);
        }

        yearList = "" + iYear;
        return SUCCESS;

    }

    public String decrement() {

        ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();
        Calendar ca = new GregorianCalendar();

        int iYear = Integer.parseInt(yearList);
        int iTyear = ca.get(Calendar.YEAR);

        if (iYear == 0) {
            iYear = iTyear;
        }

        iYear = iYear - 1;


        empBirthdayList = emplService.getEmployeeBirthDay(oEmp.getClientId());


        for (int i = 0; i <= (empBirthdayList.size() - 1); i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());
        }


        eventsList = eventService.getAllEvents(EmployeeId);
        for (int i = 0; i <= (eventsList.size() - 1); i++) {
            Date eventDate = eventsList.get(i).getStartDate();
            eventList.add(eventDate);
            String keyDate = dfMySQLDate.format(eventDate);
            dateAndList.put(keyDate, eventsList);
        }

        yearList = "" + iYear;
        return SUCCESS;
    }

    public String incrementMonth() {
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Calendar ca = new GregorianCalendar();
        int iTMonth=ca.get(Calendar.MONTH);

        int iMonth;
        if(monthInput.equals("")){
        	 iMonth=0;
        }else{
        	 iMonth=Integer.parseInt(monthInput);
        }
        if(iMonth==0){
            iMonth=iTMonth;
        }

        iMonth=iMonth+1;
        monthValue=""+iMonth;
        Integer EmployeeId =oEmp.getEmployeeId();
        empBirthdayList =emplService.getEmployeeBirthDay(oEmp.getClientId());


        for (int i = 0; i <= (empBirthdayList.size() - 1); i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());
        }


        eventsList = eventService.getAllEvents(EmployeeId);
        for (int i = 0; i <= (eventsList.size() - 1); i++) {
            Date eventDate = eventsList.get(i).getStartDate();
            eventList.add(eventDate);
            String keyDate = dfMySQLDate.format(eventDate);
            dateAndList.put(keyDate, eventsList);
        }
        return SUCCESS;

    }

    public String decrementMonth() {

        ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Calendar ca = new GregorianCalendar();
        int iTMonth = ca.get(Calendar.MONTH);
        int iMonth;
        if(monthInput.equals("")){
        	 iMonth=0;
        }else{
        	 iMonth=Integer.parseInt(monthInput);
        }


        if (iMonth==0){
            iMonth=iTMonth;
        }

        iMonth=iMonth-1;
        monthValue=""+iMonth;


        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        Integer EmployeeId = oEmp.getEmployeeId();


        empBirthdayList = emplService.getEmployeeBirthDay(oEmp.getClientId());


        for (int i=0;i<=(empBirthdayList.size()-1);i++) {
            birthdayList.add(empBirthdayList.get(i).getEmpBirthDate());

        }


        eventsList = eventService.getAllEvents(EmployeeId);
        for (int i=0; i<=(eventsList.size()-1);i++) {
            Date eventDate = eventsList.get(i).getStartDate();
            eventList.add(eventDate);

            String keyDate = dfMySQLDate.format(eventDate);
            dateAndList.put(keyDate, eventsList);
        }

        return SUCCESS;

    }

    public void tableHeader(Calendar thisMonth) {

        Calendar nextMonth = (Calendar) thisMonth.clone();
        nextMonth.add(Calendar.MONTH, +1);
        Calendar lastMonth = (Calendar) thisMonth.clone();
        lastMonth.add(Calendar.MONTH, -1);

        headerDesign = "" + "<div align=center><table><tr><td><A HREF=" + pathURL + "?date="
            + dfDateField.format(lastMonth.getTime()) + ">("
            + dfMySQLDate.format(lastMonth.getTime())
            + ")<FONT FACE=VERDANA SIZE=-1 COLOR=#000000><b>Previous</b></font></a></td>"

            + "<td><A HREF=" + pathURL + "?date=" + dfDateField.format(nextMonth.getTime())
            + "><b><FONT FACE=VERDANA SIZE=-1 COLOR=#000000>Next</FONT></b>("

            + dfMySQLDate.format(nextMonth.getTime()) + ")</A></td></tr>"

            + "</table></div>";

    }

    public void tableCells(Calendar thisMonth) {
        StringBuffer returnValue = new StringBuffer();

        // ** record the current value of the field Calendar.MONTH

        int iThisMonth = thisMonth.get(Calendar.MONTH);
        Calendar today = Calendar.getInstance(); // used to highlight today's
                                                 // date cell in the calendar
        Calendar endOfMonth = (Calendar) thisMonth.clone();
        endOfMonth.set(Calendar.DATE, endOfMonth.getActualMaximum(Calendar.DATE));
        endOfMonth.add(Calendar.DATE, 7 - endOfMonth.get(Calendar.DAY_OF_WEEK));

        // ** set calendar to first day of the first week (may move back 1
        // month)

        thisMonth.add(Calendar.DATE, 1 - thisMonth.get(Calendar.DAY_OF_WEEK));
        dfMySQLDate.format(thisMonth.getTime());
        String dayStr = ""

            + "  <table style=\"table-layout:fixed; width:98%;\"><tr><TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Sunday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Monday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Tuesday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Wednesday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Thursday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Friday</B></FONT></TD>"
            + "  <TD style=\"overflow: hidden;\"><FONT FACE=VERDANA SIZE=1 COLOR=#000000><B>Saturday</B></FONT></TD>"
            + "  </TR></table>";
        returnValue.append(dayStr);

        do {
            returnValue.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/js/cal/tooltip/style.css\" />");
            returnValue.append("<script language=\"javaScript\" type=\"text/javascript\" src=\"resources/js/cal/tooltip/script.js\">"
                + "function sample(){" + "	alert('hi');}"

                + "</script>");

            returnValue.append("<table style=\"table-layout:fixed; width:98%;\"><TR BORDER=1 VALIGN=TOP>");

            String urlForNewEvent = this.getText("ApplicationURL") + this.getText("ProjectName")
                + "/createNewScheduler.action";
            for (int i = 0; i < 7; i++) {

                returnValue.append("<TD style=\"overflow: hidden;\" HEIGHT=50");
                if (thisMonth.get(Calendar.MONTH) != iThisMonth) {
                    returnValue.append(" BGCOLOR=#e0e0e0"); // dark background

                } else if (dfDateField.format(thisMonth.getTime()).equals(dfDateField.format(today.getTime()))) {
                    returnValue.append(" BGCOLOR=#ffff00"); // grey background
                                                            // for today's date
                } else {
                    returnValue.append(" BGCOLOR=#ffffff"); // default white
                                                            // background
                }

                returnValue.append("><A HREF=" + urlForNewEvent + "?value="

                    + sdfNewEvent.format(thisMonth.getTime())
                    + "><FONT FACE=VERDANA COLOR=#000000 SIZE=1><B>"

                    + dfDayOfMonth.format(thisMonth.getTime()) + "</B></A>");

                returnValue.append("</TD>");
                thisMonth.add(Calendar.DATE, 1);
            }

            returnValue.append("</TR></table>");

        } while (thisMonth.getTime().before(endOfMonth.getTime()));

        thisMonth.add(Calendar.MONTH, -1);
        thisMonth.set(Calendar.DATE, 1);


        tableCellDesign = returnValue.toString();
    }

    List<MenuVO> createAppMenu() {
        Reader r = new Reader();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO employeesVO = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        String sRole = employeesVO.getRoleObj().getRoleName();


        String sXmlPath = PropertyUtil.getxmlMenuPath(); 
        String sMenusXmlFilePath = sXmlPath + sRole + ".xml";
        String sMenusXmlDefaultFilePath = sXmlPath + "Default.xml";
        List<MenuVO> lMenuObjs = r.getMenuItems(sMenusXmlFilePath, sMenusXmlDefaultFilePath);
        List<MenuVO> l = new ArrayList<MenuVO>();

        int iId = 0;
        String sAdd = "Add ";
        String sView = "View ";

        String sAddLink = "_Add";
        String sUpdateLink = "_Update";
        String sDeleteLink = "_Delete";
        String sViewLink = "_View";
        String sMenu = "_Menu";

        boolean isMenuPart1Active = false;
        boolean isMenuPart2Active = false;
        boolean isMenuPart3Active = false;

        for (MenuVO lMenuObj : lMenuObjs) {
            MenuVO lRootMenuItem = new MenuVO();

            if (lMenuObj.getLabel().equals("Home")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("Logout")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("ESS")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("ExpenseModule")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("LeaveModule")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("TimeSheetModule")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("OrgChart")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("Reports")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("Messaging")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("EmployeeSpace")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("PerformancemenuKpiQuestion")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("PerformancemenuKpiQuestionGroup")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getLabel().equals("PerformancemenuKpiAssignkpi")) {
                if (lMenuObj.getView()) {
                    iId++;
                    lRootMenuItem.setId(String.valueOf(iId));
                    lRootMenuItem.setLabel(lMenuObj.getLabel());
                    lRootMenuItem.setUrl(lMenuObj.getUrlRoot());
                    mSession.put(lMenuObj.getLabel() + sMenu, true);
                    l.add(lRootMenuItem);
                }
                if (lMenuObj.getView()) {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
                } else {
                    mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
                }
                continue;
            }
            if (lMenuObj.getAdd() || lMenuObj.getView()) {
                String sLabel = lMenuObj.getLabel();

                if (!isMenuPart1Active
                    && (sLabel.equals("Client") || sLabel.equals("Organization")
                        || sLabel.equals("Location") || sLabel.equals("OrganizationType") || sLabel.equals("Region"))) {
                    mSession.put("Part1" + sMenu, true);
                    isMenuPart1Active = true;
                }
                if (!isMenuPart2Active
                    && (sLabel.equals("Country") || sLabel.equals("Nationality")
                        || sLabel.equals("EthnicRace") || sLabel.equals("Customer")
                        || sLabel.equals("Project") || sLabel.equals("ProjectActivity")
                        || sLabel.equals("Holiday") || sLabel.equals("JobTitle")
                        || sLabel.equals("SalaryGrade") || sLabel.equals("Role")
                        || sLabel.equals("EmployeeStatus") || sLabel.equals("Deduction")
                        || sLabel.equals("LeaveType") || sLabel.equals("ExpenseType"))
                        || sLabel.equals("PerformanceCategory") || sLabel.equals("PerformanceSubCategory")
                      ) {
                    mSession.put("Part2" + sMenu, true);
                    isMenuPart2Active = true;
                }
                if (!isMenuPart3Active
                    && (sLabel.equals("Employee") || sLabel.equals("User")
                        || sLabel.equals("Department") || sLabel.equals("Team")
                        || sLabel.equals("Paystub") || sLabel.equals("License")
                        || sLabel.equals("Education") || sLabel.equals("Children")
                        || sLabel.equals("DirectDebit") || sLabel.equals("WorkExperience")
                        || sLabel.equals("ReportTo") || sLabel.equals("LocationHistory")
                        || sLabel.equals("EmployeeSchedule") || sLabel.equals("EmployeePassport")
                        || sLabel.equals("Benefits") || sLabel.equals("LeaveQuota")
                        || sLabel.equals("Currency") || sLabel.equals("ExpensesAccountant")
                        || sLabel.equals("ExpensesApprover") || sLabel.equals("LeaveApprover") || sLabel.equals("TimeSheetApprover"))) {
                    mSession.put("Part3" + sMenu, true);
                    isMenuPart3Active = true;
                }
                lRootMenuItem.setId(String.valueOf(iId));
                lRootMenuItem.setLabel(sLabel);
                lRootMenuItem.setUrl(lMenuObj.getUrlRoot());

                iId++;
                MenuVO lAddMenuItem = new MenuVO();
                lAddMenuItem.setId(String.valueOf(iId));
                lAddMenuItem.setLabel(sAdd + sLabel);
                lAddMenuItem.setUrl(lMenuObj.getUrlAdd());

                iId++;
                MenuVO lViewMenuItem = new MenuVO();
                lViewMenuItem.setId(String.valueOf(iId));
                lViewMenuItem.setLabel(sView + sLabel);
                lViewMenuItem.setUrl(lMenuObj.getUrlView());

                iId++;
                if (lMenuObj.getAdd()) {
                    lRootMenuItem.addMenu(lAddMenuItem);
                }
                if (lMenuObj.getView()) {
                    lRootMenuItem.addMenu(lViewMenuItem);
                }
                l.add(lRootMenuItem);
            }

            // Set Link Privileges In Session
            if (lMenuObj.getAdd()) {
                mSession.put((lMenuObj.getLabel() + sAddLink).toUpperCase(), true);
            } else {
                mSession.put((lMenuObj.getLabel() + sAddLink).toUpperCase(), false);
            }
            if (lMenuObj.getUpdate()) {
                mSession.put((lMenuObj.getLabel() + sUpdateLink).toUpperCase(), true);
            } else {
                mSession.put((lMenuObj.getLabel() + sUpdateLink).toUpperCase(), false);
            }
            if (lMenuObj.getDelete()) {
                mSession.put((lMenuObj.getLabel() + sDeleteLink).toUpperCase(), true);
            } else {
                mSession.put((lMenuObj.getLabel() + sDeleteLink).toUpperCase(), false);
            }
            if (lMenuObj.getView()) {
                mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), true);
            } else {
                mSession.put((lMenuObj.getLabel() + sViewLink).toUpperCase(), false);
            }

        }

        return l;

    }

    public List timeList() {

        List hrList = new ArrayList();

        for (int i = 0; i <= 23; i++) {
            if (i > 9) {
                hrList.add(i + ":00:00");
                hrList.add(i + ":30:00");
            } else {
                hrList.add("0" + i + ":00:00");
                hrList.add("0" + i + ":30:00");
            }
        }

        return hrList;
    }

    // Change a Date to GMT
    public Date toGMT(Date date, String zone) {

        return changeTimeZone(date, zone);
    }

    // Change a date in another timezone
    public Date changeTimeZone(Date date, String time) {
        new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Calendar first = Calendar.getInstance(TimeZone.getTimeZone(time));
        first.setTimeInMillis(date.getTime());

        Calendar output = Calendar.getInstance();
        output.set(Calendar.YEAR, first.get(Calendar.YEAR));
        output.set(Calendar.MONTH, first.get(Calendar.MONTH));
        output.set(Calendar.DAY_OF_MONTH, first.get(Calendar.DAY_OF_MONTH));
        output.set(Calendar.HOUR_OF_DAY, first.get(Calendar.HOUR_OF_DAY));
        output.set(Calendar.MINUTE, first.get(Calendar.MINUTE));
        output.set(Calendar.SECOND, first.get(Calendar.SECOND));
        output.set(Calendar.MILLISECOND, first.get(Calendar.MILLISECOND));

        return output.getTime();
    }

    public String displayMoreEvents() {
        return SUCCESS;
    }

    public String mail(EventsVO eventsVO, MessageVO message, EmployeesVO oEmp) {
        message.setCreated(new Date());
        HCMOneMailer mailer = new HCMOneMailer();
        new ArrayList();
        new SimpleDateFormat("MM/dd/yyyy");
        String sSubject = this.getText("message.common.subject");
        message.setSubject(sSubject);
        MessageService messageService = new MessageDaoService();
        String sDummy = Constants.PERSON;

        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + this.getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.dearEmployee"));
        sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), oEmp.getEmpFirstName());
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
            + HtmlConstants.HTML_COLON + eventsVO.getEventName().toString()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_BREAK);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Description  "
            + HtmlConstants.HTML_SPACE + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + eventsVO.getDescription() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Start Date  "
            + HtmlConstants.HTML_SPACE + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON

            + eventsVO.getStartDate()+ HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Time Zone  "
            + HtmlConstants.HTML_SPACE + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + eventsVO.getTimeZone() + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG);

        sMessage.append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + this.getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
            + HtmlConstants.HTML_BREAK);

        signatureList = signatureService.getAllSignatureForLoginEmp();
        if (signatureList.isEmpty() == true) {
            sSignature = this.getText("alert.common.signature");
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
        message.setReceiver(oEmp.getEmployeeId().toString());
        message.setReceiverEmailId(oEmp.getEmpWorkEmail());
        mailer.sendMail(message.getSender().getEmpWorkEmail(), oEmp.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), "", "");
        messageService.insertMessage(message);

        session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
        session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(oEmp.getEmployeeId()));

        return sMessage.toString();
    }

    @Override
    public void setSession(Map<String, Object> arg0) {

        session = arg0;
    }

    public String getYearList() {

        return yearList;
    }

    public void setYearList(String yearList) {
        this.yearList = yearList;
    }

    public List getBirthdayList() {

        return birthdayList;
    }

    public void setBirthdayList(List birthdayList) {
        this.birthdayList = birthdayList;
    }

    public List<EmployeesVO> getEmployees() {

        return employees;
    }

    public void setEmployees(List<EmployeesVO> employees) {
        this.employees = employees;
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

    public String getHiddenDate() {

        return hiddenDate;
    }

    public void setHiddenDate(String hiddenDate) {
        this.hiddenDate = hiddenDate;
    }

    public String getSelectedDate() {

        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<EventsVO> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<EventsVO> eventsList) {
        this.eventsList = eventsList;
    }

   public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public List<EventsVO> getEditEvent() {
        return editEvent;
    }

    public void setEditEvent(List<EventsVO> editEvent) {
        this.editEvent = editEvent;
    }

    public List getEventList() {
        return eventList;
    }

    public void setEventList(List eventList) {
        this.eventList = eventList;
    }

    public List<EventsVO> getThisYearEvents() {

        return thisYearEvents;
    }

    public void setThisYearEvents(List<EventsVO> thisYearEvents) {
        this.thisYearEvents = thisYearEvents;
    }

      public List getEmployeeId() {

        return employeeId;
    }

    public void setEmployeeId(List employeeId) {
        this.employeeId = employeeId;
    }

       public List getHourList() {

        return hourList;
    }

    public void setHourList(List hourList) {
        this.hourList = hourList;
    }

        public String getEventTime() {

        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;

    }

    public String getTimeZone() {

        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public HashMap<String, List> getDateAndList() {

        return dateAndList;
    }

    public void setDateAndList(HashMap<String, List> dateAndList) {
        this.dateAndList = dateAndList;
    }

    public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventName() {
		return eventName;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	public void setMonthValue(String monthValue) {
		this.monthValue = monthValue;
	}

	public String getMonthValue() {
		return monthValue;
	}
	public void setUniqueValue(int uniqueValue) {
		this.uniqueValue = uniqueValue;
	}

	public int getUniqueValue() {
		return uniqueValue;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setTimeZoneList(List timeZoneList) {
		this.timeZoneList = timeZoneList;
	}

	public List getTimeZoneList() {
		return timeZoneList;
	}
	public Set getList() {
		return list;
	}

	public void setList(Set list) {
		this.list = list;
	}

    public List<ConfigurationVO> getConfigList() {
        return configList;
    }

    public void setConfigList(List<ConfigurationVO> configList) {
        this.configList = configList;
    }

    public ConfigurationVO getConfig() {
        return config;
    }

    public void setConfig(ConfigurationVO config) {
        this.config = config;
    }
	
	}