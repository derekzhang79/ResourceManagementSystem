
package com.gits.rms.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneScheduleMailer;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeSheetProjectAssignService;
import com.gits.rms.service.TimesheetProjectDaoService;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;

public class TimeSheetAlert implements Job {
    private List<EmployeesVO> employeesList = new ArrayList<EmployeesVO>();
    private EmployeesService employeesService = new EmployeesDaoService();
    private EmployeesVO employees;
    private ArrayList<EmployeesVO> dailyList = new ArrayList();
    private ArrayList<EmployeesVO> weeklyList = new ArrayList();
    private ArrayList<EmployeesVO> monthlyList = new ArrayList();
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetCategoryAssignVO timeSheetCategoryAssign;
    private List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList = new ArrayList<TimeSheetCategoryAssignVO>();
    private TimeSheetProjectAssignVO timeSheetProjectAssignVO;
    private TimeSheetProjectAssignService timeSheetProjectAssignService = new TimesheetProjectDaoService();
    private List<TimeSheetProjectAssignVO> timeSheetProjectAssignList = new ArrayList<TimeSheetProjectAssignVO>();
    private TimeSheetApproverVO timeSheetApprover = new TimeSheetApproverVO();
    private TimeSheetApproverService timeSheetApproverService = new TimeSheetApproverDaoService();
    private List<TimeSheetApproverVO> timeSheetApproverList;
    private MessageService messageService = new MessageDaoService();

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
//          CLIENT ID ADDED BY BALA
        	Map mSession = ActionContext.getContext().getSession();
            Integer clientId = (Integer) mSession.get("CLIENT_INFO"); 
            HCMOneScheduleMailer hcmOneMailer = new HCMOneScheduleMailer();
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            timeSheetCategoryAssignList = new ArrayList<TimeSheetCategoryAssignVO>();
            timeSheetApproverList = new ArrayList<TimeSheetApproverVO>();
            employeesList = new ArrayList<EmployeesVO>();
            EmployeesVO empAdmin = new EmployeesVO();
            MessageVO message = new MessageVO();
            employeesList = employeesService.getAllEmployees(clientId);

            employees = new EmployeesVO();
            Iterator<EmployeesVO> empItr = employeesList.iterator();

            while (empItr.hasNext()) {
                employees = empItr.next();
                String wage = employees.getEmployeeWage();
                if (!employees.getRoleObj().getRoleName().equalsIgnoreCase("Admin")) {
                    if (wage.equals("Daily")) {
                        dailyList.add(employees);
                    } else if (wage.equals("Weekly")) {
                        weeklyList.add(employees);
                    } else {
                        monthlyList.add(employees);
                    }
                } else {
                    empAdmin = employees;
                }
            }

            for (int i = 0; i < dailyList.size(); i++) {
                String sApproverEmailId = "";
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                message.setCreatedBy(dailyList.get(i));
                message.setUpdatedBy(dailyList.get(i));
                message.setCreated(new Date());
                message.setIsActive(1);
                message.setReceiver(sApproverEmailId + dailyList.get(i).getEmployeeId().toString());
                message.setReceiverEmailId(dailyList.get(i).getEmpWorkEmail());
                message.setSender(empAdmin);
                message.setType(Constants.MESSAGE_IN_ALERT);
                timeSheetApproverList = timeSheetApproverService.getEmployeeAllTimeSheetApprover(dailyList.get(i).getEmployeeId());

                Date date = new Date();
                date.setTime(calendar.getTimeInMillis());
                if (dailyList.get(i).getEmpJoineddate().getTime() <= date.getTime()) {
                    timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(dailyList.get(i).getEmployeeId(), date, date);
                    if (timeSheetCategoryAssignList.isEmpty()) {
                        message.setSubject(dailyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Yesterdays TimeSheet on date "
                            + format.format(date));
                        message.setMessage(dailyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Yesterdays TimeSheet on date "
                            + format.format(date));
                        mail(message, empAdmin);
                        for (TimeSheetApproverVO timeSheetAppVO : timeSheetApproverList) {
                            sApproverEmailId += timeSheetAppVO.getHcmoApprovingEmpId().getEmpWorkEmail()
                                + ",";
                            message.setMessage(dailyList.get(i).getEmpFirstName()
                                + " Forgot To Enter Yesterdays TimeSheet on date "
                                + format.format(date));
                            mail(message, timeSheetAppVO.getHcmoApprovingEmpId());
                        }
                        message.setSubject("You Forgot To Enter Yesterdays TimeSheet on date "
                            + format.format(date));
                        message.setMessage("Please Enter Your Time Sheet on date "
                            + format.format(date));
                        mail(message, dailyList.get(i));
                    }
                }
            }
            for (int i = 0; i < weeklyList.size(); i++) {
                String sApproverEmailId = "";
                Date startDate = new Date();
                Date endDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -(endDate.getDay() + 7));
                startDate.setTime(calendar.getTimeInMillis());
                calendar.add(Calendar.DATE, 7);
                endDate.setTime(calendar.getTimeInMillis());
                message.setCreatedBy(weeklyList.get(i));
                message.setUpdatedBy(weeklyList.get(i));
                message.setCreated(new Date());
                message.setIsActive(1);
                message.setReceiver(sApproverEmailId + weeklyList.get(i).getEmployeeId().toString());
                message.setReceiverEmailId(weeklyList.get(i).getEmpWorkEmail());
                message.setSender(empAdmin);
                message.setType(Constants.MESSAGE_IN_ALERT);
                timeSheetApproverList = timeSheetApproverService.getEmployeeAllTimeSheetApprover(weeklyList.get(i).getEmployeeId());
                if (endDate.before(new Date())
                    && ((new Date().getTime() - weeklyList.get(i).getEmpJoineddate().getTime()) / (24 * 60 * 60 * 1000)) >= 7) {
                    timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(weeklyList.get(i).getEmployeeId(), startDate, endDate);
                    if (timeSheetCategoryAssignList.isEmpty()) {
                        message.setSubject(weeklyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Last Week TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        message.setMessage(weeklyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Last Week TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        mail(message, empAdmin);
                        for (TimeSheetApproverVO timeSheetAppVO : timeSheetApproverList) {
                            sApproverEmailId += timeSheetAppVO.getHcmoApprovingEmpId().getEmpWorkEmail()
                                + ",";
                            message.setMessage(weeklyList.get(i).getEmpFirstName()
                                + " Forgot To Enter Last Week TimeSheet on date from "
                                + format.format(startDate) + " to " + format.format(endDate));
                            mail(message, timeSheetAppVO.getHcmoApprovingEmpId());
                        }
                        message.setSubject("You Forgot To Enter Last Week TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        message.setMessage("Please Enter Last Week TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        mail(message, weeklyList.get(i));
                    }
                }
            }
            for (int i = 0; i < monthlyList.size(); i++) {
                Date startDate = new Date();
                Date endDate = new Date();
                String sApproverEmailId = "";
                message.setCreatedBy(monthlyList.get(i));
                message.setUpdatedBy(monthlyList.get(i));
                message.setCreated(new Date());
                message.setIsActive(1);
                message.setReceiver(sApproverEmailId
                    + monthlyList.get(i).getEmployeeId().toString());
                message.setReceiverEmailId(monthlyList.get(i).getEmpWorkEmail());
                message.setSender(empAdmin);
                message.setType(Constants.MESSAGE_IN_ALERT);
                timeSheetApproverList = timeSheetApproverService.getEmployeeAllTimeSheetApprover(monthlyList.get(i).getEmployeeId());
                Calendar calendar = Calendar.getInstance();
                calendar.add(calendar.MONTH, -1);
                calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), 1);
                startDate.setTime(calendar.getTimeInMillis());
                Date date = new Date();
                calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                endDate.setTime(calendar.getTimeInMillis());
                if (((new Date().getTime() - monthlyList.get(i).getEmpJoineddate().getTime()) / (24 * 60 * 60 * 1000)) >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    timeSheetCategoryAssignList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchResult(monthlyList.get(i).getEmployeeId(), startDate, endDate);
                    if (timeSheetCategoryAssignList.isEmpty()) {
                        message.setSubject(monthlyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Last Month TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        message.setMessage(monthlyList.get(i).getEmpFirstName()
                            + " Forgot To Enter Last Month TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        mail(message, empAdmin);
                        for (TimeSheetApproverVO timeSheetAppVO : timeSheetApproverList) {
                            sApproverEmailId += timeSheetAppVO.getHcmoApprovingEmpId().getEmpWorkEmail()
                                + ",";
                            message.setMessage(monthlyList.get(i).getEmpFirstName()
                                + " Forgot To Enter Last Month TimeSheet on date from "
                                + format.format(startDate) + " to " + format.format(endDate));
                            mail(message, timeSheetAppVO.getHcmoApprovingEmpId());
                        }
                        message.setSubject("You Forgot To Enter Last Month TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        message.setMessage("Please Enter Last Month TimeSheet on date from "
                            + format.format(startDate) + " to " + format.format(endDate));
                        mail(message, monthlyList.get(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String mail(MessageVO message, EmployeesVO empVo) {
        HCMOneScheduleMailer hcmOneMailer = new HCMOneScheduleMailer();
        String sSubject = message.getSubject();

        String sDummy = Constants.PERSON;
        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + "This is an automated message, please do not reply to this email"
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + "Dear " + empVo.getEmpFirstName());
        sMessage.append(HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE
            + "This is an automated message, please do not reply to this email");
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);
        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + message.getMessage()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
            + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

        .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + "Please contact your manager or admin for any questions"
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_BREAK);
        String sSignature = "HCMOne Automated Message";
        sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
            + HtmlConstants.HTML_PARA_END_TAG);
        message.setMessage(sMessage.toString());
        message.setReceiver(empVo.getEmployeeId().toString());
        message.setReceiverEmailId(empVo.getEmpWorkEmail());
        hcmOneMailer.sendMail(message.getSender().getEmpWorkEmail(), empVo.getEmpWorkEmail(), sSubject, sMessage.toString(), new Vector(), "", "");
        messageService.insertMessage(message);
        message.setMessage("");
        return sMessage.toString();
    }
}
