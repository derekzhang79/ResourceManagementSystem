package com.gits.rms;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.cxf.transport.jms.ClientBehaviorPolicyType;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
//import org.eclipse.jdt.internal.compiler.ast.MagicLiteral;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ClientConstants;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.ClientRegistrationDaoService;
import com.gits.rms.service.ClientRegistrationService;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.vo.ClientRegVO;
import com.gits.rms.vo.MailConfigurationVO;

public class Signup extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6291243883460613363L;
	private String userName;
	private String activationKey;
	private ClientRegVO client = new ClientRegVO();
	private ClientRegistrationService clientdao = new ClientRegistrationDaoService();
    Map session = ActionContext.getContext().getSession();
    private List<MailConfigurationVO> mailConfigList;
    MailConfigurationService mailConfigService = new MailConfigurationDaoService();
    static Logger log = Logger.getLogger(Signup.class.getName());// for store log details


    //To insert super user details
	public String clientRegistration() {
		  session.put("FIRSTNAME", client.getFirstName());
		  String key = encrypt(client.getFirstName());
          String sStatus = createSendActivationLink(client.getFirstName(),key);
		  client.setEntryDate(new Date());
		  client.setActivationKey(key);
		  client.setStatus(0);
		  clientdao.insertRegistration(client);
		  log.info("Client Registration done successfully");
		  return SUCCESS;
	}
	
	//To insert the module id for super user
	public String updateModuleId() {
		  String firstName = session.get("FIRSTNAME").toString();
		  client.setFirstName(firstName);
		  clientdao.updateRegistration(client);
		  log.info("Module id insertion done successfully");
		  return SUCCESS;
	}
	
	//To insert the activation status for super user
	public String activateAccount() {
		log.debug("key from mail click" + client.getActivationKey());
		client.setFirstName(getUserName());
		client.setActivationKey(getActivationKey());
		clientdao.updateRegistrationStatus(client);
		log.info("status updation success");
		return SUCCESS;
	}
	
	// To generate the activation key
	public String encrypt(String str)
    {
		String encodedKey = null;
          try
          {
                Cipher ecipher = Cipher.getInstance("DES");//No I18N
                SecretKey key = KeyGenerator.getInstance("DES").generateKey();//No I18N
                String keyValue = null;
                byte b[] = key.getEncoded();
                for( int i=0;i<b.length;i++ )
                {
                      if( keyValue == null )
                      {
                            keyValue = (int)b[i] + "";
                      }
                      else
                      {
                            keyValue += "_" + (int)b[i];//No I18N
                      }
                      
                }
                ecipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] utf8 = str.getBytes("UTF8");//No I18N
                byte[] enc = ecipher.doFinal(utf8);
                encodedKey = DatatypeConverter.printBase64Binary(enc);
          }
          catch(Exception e)
          {
                e.printStackTrace();
          }
          return encodedKey;
    }
	
	//To create and send an activation link
	public String createSendActivationLink(String firstName,String activationKey){
		String link = getText("ApplicationURL")+"verificationAction.action?userName=" +firstName+"&activationKey="+activationKey;
		String sMessage = "Please Click this link to activate your account " + link;
		log.debug("Mail Content" + sMessage);
		
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
		String sStatus = oMailer.sendActivationMail(newMailConfig.getSmtpHost(),newMailConfig.getPassword(),newMailConfig.getUsername(),client.getContactMail(), "Verification Mail", sMessage);
        if (sStatus.equalsIgnoreCase(new String("Send OK"))) {
            addActionMessage(getText("message.signupinfo.mailsent"));
        } else {
            addActionMessage(sStatus);
        }
        log.info("Mail sent successfully");
		return sStatus;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActivationKey() {
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	public ClientRegVO getClient() {
		return client;
	}

	public void setClient(ClientRegVO client) {
		this.client = client;
	}

	

}
