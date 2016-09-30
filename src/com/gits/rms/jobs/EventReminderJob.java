
package com.gits.rms.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneScheduleMailer;
import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.EventsDaoService;
import com.gits.rms.service.EventsService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.vo.ClientInformationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EventsVO;
import com.gits.rms.vo.MessageVO;

public class EventReminderJob extends ActionSupport implements Job {

    private EventsService eventService = new EventsDaoService();
    private EmployeesService emplService = new EmployeesDaoService();
    List<EventsVO> eventslist = new ArrayList<EventsVO>();
    private EmployeesVO empDetail;
    MessageVO message = new MessageVO();
    private MessageService messageService = new MessageDaoService();

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        List<ClientInformationVO> clientList = HibernateUtil.getClientInfoList();

        for (Iterator<ClientInformationVO> it = clientList.iterator(); it.hasNext();) {
            ClientInformationVO cliInfoObj = it.next();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);

            try {
                String str = sdf.format(cal.getTime());
                eventslist = eventService.getAllEventsByDate(str, String.valueOf(cliInfoObj.getClientId()));

                for (int i = 0; i <= (eventslist.size() - 1); i++) {
                    empDetail = new EmployeesVO();
                    empDetail = emplService.getEmployeeById(eventslist.get(i).getEmployeeId());

                    EventsVO eventsVO = eventslist.get(i);
                    message.setCreatedBy(empDetail);
                    message.setUpdatedBy(empDetail);
                    message.setCreated(new Date());
                    message.setIsActive(1);
                    message.setReceiver(empDetail.getEmpFirstName());
                    message.setReceiverEmailId(empDetail.getEmpWorkEmail());
                    message.setSender(empDetail);
                    message.setType(Constants.MESSAGE_IN_ALERT);
                    message.setSubject("Event Reminder : " + eventslist.get(i).getEventName());
                    message.setMessage(eventslist.get(i).getCreatedBy().getEmpFirstName()
                        + " have added the Event for " + empDetail.getEmpFirstName());

                    String empName = empDetail.getEmpFirstName();
                    mail(message, empDetail, eventsVO);

                    empDetail = new EmployeesVO();
                    empDetail = emplService.getEmployeeById(eventslist.get(i).getCreatedBy().getEmployeeId());
                    message.setCreatedBy(empDetail);
                    message.setUpdatedBy(empDetail);
                    message.setCreated(new Date());
                    message.setIsActive(1);
                    message.setReceiver(empDetail.getEmpFirstName());
                    message.setReceiverEmailId(empDetail.getEmpWorkEmail());
                    message.setSender(empDetail);
                    message.setType(Constants.MESSAGE_IN_ALERT);
                    message.setSubject("Event Reminder : " + eventslist.get(i).getEventName());
                    message.setMessage("You have added the Event for " + empName);

                    mail(message, empDetail, eventsVO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String mail(MessageVO message, EmployeesVO empVo, EventsVO eventsVO) {
        HCMOneScheduleMailer hcmOneMailer = new HCMOneScheduleMailer();
        String sSubject = message.getSubject();

        StringBuilder sMessage = new StringBuilder();
        sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
            + "This is an automated message, please do not reply to this email"
            + HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
            + "Dear " + empVo.getEmpFirstName());
        sMessage.append(HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE
            + "This is an automated message, please do not reply to this email");
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_PARA_END_TAG + HtmlConstants.HTML_PARA_FONT_START_TAG
            + HtmlConstants.HTML_SPACE + message.getMessage());
        sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

        sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
            + HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
            + HtmlConstants.HTML_TABLE_INNER_START_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Event Name"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + eventsVO.getEventName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Event Date"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + eventsVO.getStartDate()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Created By"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + eventsVO.getCreatedBy().getEmpFirstName()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Time Zone"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + eventsVO.getTimeZone()
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

        .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
            + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG + "Description"
            + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
            + HtmlConstants.HTML_COLON + eventsVO.getDescription()
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
