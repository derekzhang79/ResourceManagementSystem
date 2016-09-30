
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.MailConfigurationVO;

public class MailConfigurationAction extends ActionSupport {
    private static final long serialVersionUID = -8923550290338430299L;
    private MailConfigurationService mailConfigService = new MailConfigurationDaoService();
    private List<MailConfigurationVO> mailConfigList;
    private MailConfigurationVO mailConfig;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();
    // To get List of Server Configured List
    @SkipValidation
    public String getAllMailConfig() {
        mailConfigList = mailConfigService.getAllMailConfig();
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add Mail
    // Configured it shows blank Form to enter New Data
    @SkipValidation
    public String setUpMailConfig() {
        if ((mailConfig != null) && (mailConfig.getHcmoMailConfigurationId() != null)) {
            mailConfig = mailConfigService.getMailConfig(mailConfig.getHcmoMailConfigurationId());
        }
        return SUCCESS;
    }

    // To get Particular Mail Configuration Data
    @SkipValidation
    public String mailConfigView() {
        if ((mailConfig != null) && (mailConfig.getHcmoMailConfigurationId() != null)) {
            mailConfig = mailConfigService.getMailConfig(mailConfig.getHcmoMailConfigurationId());
        }
        return SUCCESS;
    }

    // To insert new MailConfiguration detail or Edit Particular
    // MailConfiguration Detail
    public String insertOrUpdateMailConfiguration() {
        try {
            if (mailConfig.getHcmoMailConfigurationId() == null) {
                mailConfigList = mailConfigService.getAllMailConfig();
                if (mailConfigList.size() == 1) {
                    addActionError(getText("errors.mailConfig.restriction"));
                    return INPUT;
                } else {
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    mailConfig.setCreated(DateUtils.getCurrentDateTime());
                    mailConfig.setCreatedBy(oEmp);
                    mailConfig.setUpdatedBy(oEmp);
                    mailConfig.setIsActive(1);
                    mailConfigService.insertMailConfig(mailConfig);
                    
                    // Put MailConfiguration Values in Session
                    session.put("MAILCONFIG_SMTPHOST", mailConfig.getSmtpHost());
                    session.put("MAILCONFIG_USERNAME", mailConfig.getUsername());
                    session.put("MAILCONFIG_PASSWORD", mailConfig.getPassword());
                    addActionMessage(getText("Added Successfully"));
                }
            } else if (mailConfig.getHcmoMailConfigurationId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                mailConfig.setUpdatedBy(oEmp);
                mailConfigService.updateMailConfig(mailConfig);
                
                // Put MailConfiguration Values in Session
                session.put("MAILCONFIG_SMTPHOST", mailConfig.getSmtpHost());
                session.put("MAILCONFIG_USERNAME", mailConfig.getUsername());
                session.put("MAILCONFIG_PASSWORD", mailConfig.getPassword());
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        if(mSession.get("CONFIGURATION")!=null){
        	 EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        	configList=configService.getAllConfiguration(oEmp.getClientId());
           
            if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("mailconfiguration");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateConfiguration(config);
            }
        }
        return SUCCESS;
    }
    
    public String insertOrUpdateConfigureMailConfiguration() {
        try {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            if (mailConfig.getHcmoMailConfigurationId() == null) {
                mailConfigList = mailConfigService.getAllMailConfig();
                if (mailConfigList.size() == 1) {
                    addActionError(getText("errors.mailConfig.restriction"));
                    return INPUT;
                } else {
                    
                    mailConfig.setCreated(DateUtils.getCurrentDateTime());
                    mailConfig.setCreatedBy(oEmp);
                    mailConfig.setUpdatedBy(oEmp);
                    mailConfig.setIsActive(1);
                    mailConfigService.insertMailConfig(mailConfig);
                    session.put("CHECK_MAILCONFIGURATION", "AVAILABLE");
                    configList=configService.getAllConfiguration(oEmp.getClientId());
                    if (!configList.isEmpty()){
                        for(int i=0;i<configList.size();i++){
                            ConfigurationVO configuration=configList.get(i);
                            config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                        }
                        config.setCreated(DateUtils.getCurrentDateTime());
                        config.setCreatedBy(oEmp);
                        config.setUpdatedBy(oEmp);
                        config.setIsActive(1);
                        configService.updateConfiguration(config);
                        
                    }
                    // Put MailConfiguration Values in Session
                    session.put("MAILCONFIG_SMTPHOST", mailConfig.getSmtpHost());
                    session.put("MAILCONFIG_USERNAME", mailConfig.getUsername());
                    session.put("MAILCONFIG_PASSWORD", mailConfig.getPassword());
                    addActionMessage(getText("Added Successfully"));
                }
            } else if (mailConfig.getHcmoMailConfigurationId() != null) {
                mailConfig.setUpdatedBy(oEmp);
                mailConfigService.updateMailConfig(mailConfig);

                configList=configService.getAllConfiguration(oEmp.getClientId());
                if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                        ConfigurationVO configuration=configList.get(i);
                        config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                    }
                    config.setCreated(DateUtils.getCurrentDateTime());
                    config.setCreatedBy(oEmp);
                    config.setUpdatedBy(oEmp);
                    config.setIsActive(1);
                    configService.updateConfiguration(config);
                    
                }
                
                // Put MailConfiguration Values in Session
                session.put("MAILCONFIG_SMTPHOST", mailConfig.getSmtpHost());
                session.put("MAILCONFIG_USERNAME", mailConfig.getUsername());
                session.put("MAILCONFIG_PASSWORD", mailConfig.getPassword());
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    public List<MailConfigurationVO> getMailConfigList() {
        return mailConfigList;
    }

    public void setMailConfigList(List<MailConfigurationVO> mailConfigList) {
        this.mailConfigList = mailConfigList;
    }

    public MailConfigurationVO getMailConfig() {
        return mailConfig;
    }

    public void setMailConfig(MailConfigurationVO mailConfig) {
        this.mailConfig = mailConfig;
    }

}
