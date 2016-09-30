
package com.gits.rms;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.persistence.UserHibernateDao;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.utils.DesEncrypter;
import com.gits.rms.vo.MailConfigurationVO;
import com.gits.rms.vo.UserVO;

public class ForgotPassword extends ActionSupport {

    private static final long serialVersionUID = -5226206604089346002L;
    private String userName;
    private String emailId;
    private UserVO user;
    private String captchacode;
    private List<MailConfigurationVO> mailConfigList;
    MailConfigurationService mailConfigService = new MailConfigurationDaoService();

    @Override
    public String execute() throws Exception {
        user = new UserVO();
        user.setUserName(userName);

        Map session = ActionContext.getContext().getSession();
        String sKey = String.valueOf(session.get("key"));
        UserHibernateDao userHibDao = new UserHibernateDao();
        UserVO userCheck = userHibDao.checkUserForgotPassword(user);

        if ((userCheck == null) || (userCheck.getEmpIdObj() == null)) {
            addActionError(getText("errors.authentication.info.failed"));
            return ERROR;
        } else if ((userCheck.getEmpIdObj().getEmpWorkEmail() == null)
            || (!userCheck.getEmpIdObj().getEmpWorkEmail().equals(new String(getEmailId())))) {
            addActionError(getText("errors.authentication.email.failed"));
            return ERROR;
        } else {
            if (!(sKey.equals(new String("null")))) {
                if (!(sKey.equals(getCaptchacode()))) {
                    addActionError(getText("errors.captcha.invalid"));
                    return ERROR;
                }
            }
            String sPassPhrase = new String("4Y2349324HH8932C4HC9UHE9RHW9EY823496723647823678C4627836478C568C6234CWGRGWEGRWEY");
            DesEncrypter oEncrypter = new DesEncrypter(sPassPhrase);
            String sValues = getUserName() + "," + getEmailId() + "," + userCheck.getHcmouserId();
            String sEncryptedString = oEncrypter.encrypt(sValues);
            String sMessage = getText("ApplicationURL") + "requestResetPassword.action?sEnc="
                + sEncryptedString;
            int iUpdated = userHibDao.updateUserForgotPasswordOn(user);
            if (iUpdated == 1) {
                MailConfigurationVO newMailConfig = null;
                mailConfigList = mailConfigService.getAllMailConfig();
                if ((mailConfigList.isEmpty() != true) || (mailConfigList.size() != 0)
                    || (mailConfigList != null)) {
                    for (Iterator<MailConfigurationVO> it = mailConfigList.iterator(); it.hasNext();) {
                        newMailConfig = it.next();
                        session.put("MAILCONFIG_SMTPHOST", newMailConfig.getSmtpHost());
                        session.put("MAILCONFIG_USERNAME", newMailConfig.getUsername());
                        session.put("MAILCONFIG_PASSWORD", newMailConfig.getPassword());
                    }
                }

                HCMOneMailer oMailer = new HCMOneMailer();
                String sStatus = oMailer.sendMail(new String(), userCheck.getEmpIdObj().getEmpWorkEmail(), "Your URL to reset the password", sMessage, new Vector<String>(), new String(), new String());
                if (sStatus.equalsIgnoreCase(new String("Send OK"))) {
                    addActionMessage(getText("message.logininfo.mailsent"));
                } else {
                    addActionMessage(sStatus);
                }
            }
        }
        return SUCCESS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getCaptchacode() {
        return captchacode;
    }

    public void setCaptchacode(String captchacode) {
        this.captchacode = captchacode;
    }

}