
package com.gits.rms.mail;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MailConfigurationVO;
import com.gits.rms.vo.MessageVO;

public class HCMOneScheduleMailer {
    String sEmailBCC;
    private MailConfigurationService mailConfigService = new MailConfigurationDaoService();
    private List<MailConfigurationVO> mailConfigList;
    String smtpHostName = "";
    String smtpHostUserName = "";
    String smtpHostPassword = "";
    private MessageService messageService = new MessageDaoService();

    public HCMOneScheduleMailer() {

    }

    public HCMOneScheduleMailer(HttpServletRequest req) {
        HttpSession session = req.getSession();
        sEmailBCC = String.valueOf(session.getAttribute("UserId"));
        if ((sEmailBCC == null) || (sEmailBCC.equals("null"))) {
        sEmailBCC = "";
        }
    }

    public HCMOneScheduleMailer(String EmailBcc) {
        if (EmailBcc == null) {
           
            sEmailBCC = "";
        } else {
           
            sEmailBCC = EmailBcc;
        }
    }

    public String sendMail(String EmailFrom, String EmailTo, String sSub, String sMessage, java.util.Vector filepaths, String emailCC, String eamilBCC) {
        try {
           
            EmailTo = getEmailWithCommaSeperated(EmailTo);
            emailCC = getEmailWithCommaSeperated(emailCC);
            eamilBCC = getEmailWithCommaSeperated(eamilBCC);

            Properties props = new Properties();
           
            mailConfigList = mailConfigService.getAllMailConfig();
            if (!mailConfigList.isEmpty()) {
                Iterator<MailConfigurationVO> itr = mailConfigList.iterator();
                while (itr.hasNext()) {
                    MailConfigurationVO mailConfigurationVO = itr.next();
                    this.smtpHostName = mailConfigurationVO.getSmtpHost();
                    this.smtpHostUserName = mailConfigurationVO.getUsername();
                    this.smtpHostPassword = mailConfigurationVO.getPassword();

                }
            }
                       props.put("mail.smtp.host", this.smtpHostName);            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", "465"); // 587
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            if ((EmailFrom == null) || (EmailFrom.equals(""))) {
                EmailFrom = "hcmone@test.com";
            }
            try {
                if ((eamilBCC == null) || (eamilBCC.equals(""))) {
                   
                    if ((sEmailBCC == null) || (sEmailBCC.equals(""))) {
                        eamilBCC = "";
                    } else {
                        eamilBCC = "";
                    }

                }
            } catch (Exception e) {
            }

            Session ses = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HCMOneScheduleMailer.this.smtpHostUserName, HCMOneScheduleMailer.this.smtpHostPassword);                }
            });
            ses.setDebug(true);
            Message msg = new MimeMessage(ses);
            InternetAddress addressFrom = new InternetAddress(EmailFrom);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = null;
            if (EmailTo.indexOf(",") > -1) {
                java.util.StringTokenizer st = new java.util.StringTokenizer(EmailTo, ",");
                addressTo = new InternetAddress[st.countTokens()];
                int i = 0;
                while (st.hasMoreTokens()) {
                    addressTo[i++] = new InternetAddress(st.nextToken());
                }
            } else {
                addressTo = new InternetAddress[1];
                addressTo[0] = new InternetAddress(EmailTo);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            try {
                if ((emailCC == null) || (emailCC.equals("")) || (emailCC.equals("null"))) {

                } else {
                    InternetAddress[] addressCC = null;
                    if (emailCC.indexOf(",") > -1) {
                        java.util.StringTokenizer st = new java.util.StringTokenizer(emailCC, ",");
                        addressCC = new InternetAddress[st.countTokens()];
                        int i = 0;
                        while (st.hasMoreTokens()) {
                            addressCC[i++] = new InternetAddress(st.nextToken());
                        }
                    } else {
                        addressCC = new InternetAddress[1];
                        addressCC[0] = new InternetAddress(emailCC);
                    }
                    msg.setRecipients(Message.RecipientType.CC, addressCC);
                }
            } catch (Exception e) {

            }

            if ((eamilBCC == null) || (eamilBCC.equals("")) || (eamilBCC.equals("null"))) {

            } else {
                try {
                    InternetAddress[] addressBCC = null;
                    if (eamilBCC.indexOf(",") > -1) {
                        java.util.StringTokenizer st = new java.util.StringTokenizer(eamilBCC, ",");
                        addressBCC = new InternetAddress[st.countTokens()];
                        int i = 0;
                        while (st.hasMoreTokens()) {
                            addressBCC[i++] = new InternetAddress(st.nextToken());
                        }
                    } else {
                        addressBCC = new InternetAddress[1];
                        addressBCC[0] = new InternetAddress(eamilBCC);
                    }
                    msg.setRecipients(Message.RecipientType.BCC, addressBCC);
                } catch (Exception e) {

                }
            }
            // Setting the Subject and Content Type
            msg.setSubject(sSub);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(sMessage, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            DataSource source = null;
            java.io.File attachFile = null;
            String sFileName = "";
            if (filepaths == null) {
                filepaths = new java.util.Vector();
            }
            for (int i = 0; i < filepaths.size(); i++) {
                messageBodyPart = new MimeBodyPart();
                String filepath = String.valueOf(filepaths.elementAt(i));
                if ((filepath == null) || (filepath.equals(""))) {
                    continue;
                } else if (filepath.indexOf("@@@") > -1) {
                    sFileName = filepath.substring((filepath.indexOf("@@@") + 3));
                    filepath = filepath.substring(0, filepath.indexOf("@@@"));
                }
                attachFile = new java.io.File(filepath);
                sFileName = attachFile.getName();
                source = new FileDataSource(attachFile);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(sFileName);
                multipart.addBodyPart(messageBodyPart);
            }
            msg.setContent(multipart);
            Transport.send(msg);
            ses = null;
            return "Send OK";
        } catch (Exception e) {
            return e.toString();
        }
    }

    private String getEmailWithCommaSeperated(String sEmail) {
        if ((sEmail == null) || (sEmail.equals(""))) {
            return "";
        }
        sEmail = sEmail.trim();
        sEmail = sEmail.replaceAll(";", ",");
        sEmail = sEmail.replaceAll(" ", ",");
        return sEmail;
    }

    public void sendAlertEmail(String EmailTo, String Sub, String Message, String sSignature) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To send alert mails
        try {
            MessageVO message = new MessageVO();
            message.setSender(oEmp);
            message.setReceiver(EmailTo);
            message.setReceiverEmailId(EmailTo);
            String sSubject = Sub;
            sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
            message.setSubject(sSubject);
            String sMessage = Message;
            sMessage = StringUtils.replace(sMessage, "<second_person>", oEmp.getEmpFirstName());
            message.setMessage(sMessage);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setSignature(sSignature);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setIsActive(1);

            HCMOneScheduleMailer mailer = new HCMOneScheduleMailer();
            mailer.sendMail(message.getSender().getEmpWorkEmail(), message.getReceiver(), sSubject, sMessage, new Vector(), "", "");
            // To save alert mails
           
            messageService.insertMessage(message);

            // To Increase Alert count in the session if the Alert is for the
            // Logged in Person
            int value = message.getReceiver().indexOf(oEmp.getEmployeeId().toString());
            if (value != -1) {
                session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
                session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(oEmp.getEmployeeId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTimeSheetAlertEmail(String EmailTo, String Sub, String Message, String sSignature, EmployeesVO oEmp) {
        try {

            // To send alert mails
            MessageVO message = new MessageVO();
            message.setSender(oEmp);
            message.setReceiver(EmailTo);
            String sSubject = Sub;
            message.setSubject(sSubject);
            String sMessage = Message;
            message.setMessage(sMessage);
            message.setType(Constants.MESSAGE_IN_ALERT);
            message.setSignature(sSignature);
            message.setCreated(DateUtils.getCurrentDateTime());
            message.setCreatedBy(oEmp);
            message.setUpdatedBy(oEmp);
            message.setIsActive(1);

            HCMOneScheduleMailer mailer = new HCMOneScheduleMailer();
            mailer.sendMail(message.getSender().getEmpWorkEmail(), message.getReceiver(), sSubject, sMessage, new Vector(), "", "");
            // To save alert mails
           
            messageService.insertMessage(message);

            message.getReceiver().indexOf(oEmp.getEmployeeId().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAlertEmail(String EmailTo, String Sub, StringBuilder Message, String sSignature) {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To send alert mails
        MessageVO message = new MessageVO();
        message.setSender(oEmp);
        message.setReceiver(EmailTo);
        String sSubject = Sub;
        sSubject = StringUtils.replace(sSubject, "<second_person>", oEmp.getEmpFirstName());
        message.setSubject(sSubject);
        String sMessage = Message.toString();
        sMessage = StringUtils.replace(sMessage, "<second_person>", oEmp.getEmpFirstName());
        message.setMessage(sMessage);
        message.setType(Constants.MESSAGE_IN_ALERT);
        message.setSignature(sSignature);
        message.setCreated(DateUtils.getCurrentDateTime());
        message.setCreatedBy(oEmp);
        message.setUpdatedBy(oEmp);
        message.setIsActive(1);

        HCMOneMailer mailer = new HCMOneMailer();
        mailer.sendMail(message.getSender().getEmpWorkEmail(), message.getReceiver(), sSubject, sMessage, new Vector(), "", "");

        // To save alert mails
       
        messageService.insertMessage(message);

        // To Increase Alert count in the session if the AlertMail is for the
        // Logged in Person
        int receiverId = message.getReceiver().indexOf(oEmp.getEmployeeId().toString());
        if (receiverId != -1) {
            session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(oEmp.getEmployeeId()));
            session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(oEmp.getEmployeeId()));
        }
    }

}
