
package com.gits.rms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.TimeSheetApproverAction;
import com.gits.rms.persistence.MessageHibernateDao;
import com.gits.rms.persistence.UserHibernateDao;
import com.gits.rms.service.ClientDaoService;
import com.gits.rms.service.ClientService;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.EmployeeExpensesDaoService;
import com.gits.rms.service.EmployeeExpensesService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.ExpensesAccountantApproverDaoService;
import com.gits.rms.service.ExpensesAccountantApproverService;
import com.gits.rms.service.ExpensesApproverDaoService;
import com.gits.rms.service.ExpensesApproverService;
import com.gits.rms.service.LeaveApproverDaoService;
import com.gits.rms.service.LeaveApproverService;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.service.SaasContractDaoService;
import com.gits.rms.service.SaasContractService;
import com.gits.rms.service.TimeSheetApproverDaoService;
import com.gits.rms.service.TimeSheetApproverService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoDaoService;
import com.gits.rms.service.performance.QuestionBankGeneralInfoService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.CurrencyVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesApproverVO;
import com.gits.rms.vo.MailConfigurationVO;
import com.gits.rms.vo.SaasContractVO;
import com.gits.rms.vo.TimeSheetApproverVO;
import com.gits.rms.vo.UserVO;

public class Login extends ActionSupport {

    private static final long serialVersionUID = 8480226893455382535L;
    private static final Log logger = LogFactory.getLog(Login.class);
    private ExpensesApproverVO approverCheck = new ExpensesApproverVO();
    private List<ExpensesApproverVO> approverCheckList;
    private String captchacode;

    private List<CurrencyVO> currencyList;

    private CurrencyService currencyService = new CurrencyDaoService();
    private List<EmployeesVO> empIdList;

    private EmployeesService employeeService = new EmployeesDaoService();

    private LeaveApproverService leaveAppproverService = new LeaveApproverDaoService();
    private MailConfigurationVO mailConfig = new MailConfigurationVO();

    private List<MailConfigurationVO> mailConfigList;
    MailConfigurationService mailConfigService = new MailConfigurationDaoService();
    private String password;

    private TimeSheetApproverAction timesheetApprover = new TimeSheetApproverAction();
    private TimeSheetApproverVO timesheetApproverCheck = new TimeSheetApproverVO();

    private List<TimeSheetApproverVO> timesheetApproverCheckList;
    private UserVO user;

    private List<ClientVO> clientList;
    private ClientService cliService = new ClientDaoService();

    private String username;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    private ConfigurationVO config;

    private String IsResumeParserNeeded="";
    private SaasContractService saasService=new SaasContractDaoService();
    private List<SaasContractVO> saasContractList = new ArrayList<SaasContractVO>();
    private SaasContractVO saasContract= new SaasContractVO();
    private ClientVO client = new ClientVO();
    
    private QuestionBankGeneralInfoService quesBankGeneralInfoService=new QuestionBankGeneralInfoDaoService();
    private List<EmployeesVO> peerEmpList;
    @Override
    public String execute() throws Exception {
        user = new UserVO();
        user.setUserName(username);
        user.setPassword(password);
        System.out.println("user "+username);
        System.out.println("password "+password);
        UserHibernateDao userHibDao = new UserHibernateDao();
        UserVO userCheck = userHibDao.checkUserToLogin(user);
        System.out.println("userCheck : "+userCheck);
        TimeSheetApproverService timesheetAppproverService = new TimeSheetApproverDaoService();
        ExpensesApproverService expAppproverService = new ExpensesApproverDaoService();
        EmployeeExpensesService empExpensesService = new EmployeeExpensesDaoService();
        ExpensesAccountantApproverService expAccountantService = new ExpensesAccountantApproverDaoService();

        Map session = ActionContext.getContext().getSession();
        String sKey = String.valueOf(session.get("key"));
        if (!(sKey.equals(("null")))) {
            if (!(sKey.equals(getCaptchacode()))) {
                addActionError(this.getText("errors.captcha.invalid"));
                return ERROR;
            }
        }
        if (userCheck == null) {
            addActionError(this.getText("errors.authentication.failed"));
            logger.info("User Authentication Failed");
            return ERROR;
        }

        if (userCheck.getEmpIdObj() == null) {
            addActionError(this.getText("errors.authentication.failed"));
            logger.info("User Authentication Failed");
            return ERROR;
        } else {
            session.put("USER_NAME", getUsername());
            session.put("FIRST_NAME", userCheck.getEmpIdObj().getEmpFirstName());
            session.put("MIDDLE_NAME", userCheck.getEmpIdObj().getEmpMidName());
            session.put("LAST_NAME", userCheck.getEmpIdObj().getEmpLastName());
            session.put("SSN_NUMBER", userCheck.getEmpIdObj().getEmpSSNNumber());
            session.put("ROLE", userCheck.getEmpIdObj().getRoleObj().getRoleName());
            Integer clientId = userCheck.getEmpIdObj().getClientId();
            session.put("CLIENT_INFO",userCheck.getEmpIdObj().getClientId());
            logger.debug("CLIENT_INFO =========> "+userCheck.getEmpIdObj().getClientId());
            session.put("USER_OBJECT", userCheck);
            session.put("EMPLOYEE_ID", userCheck.getEmpIdObj().getEmployeeId());
            session.put("EMPLOYEE_OBJECT", userCheck.getEmpIdObj());
            session.put("EMPLOYEE_EMAIL", userCheck.getEmpIdObj().getEmpWorkEmail());
            session.put("EMPLOYEE_SPACE_MAX_SIZE", userCheck.getEmpIdObj().getEmpSpace());
            session.put("NO_OF_RECORDS", Integer.valueOf(20));
            session.put("TS_APPROVERS_SUBEMP_LIST", timesheetApprover.getAllTsSubEmployees());
            session.get("TS_APPROVERS_SUBEMP_LIST");

            Integer LeaveApproverCount = leaveAppproverService.checkLeaveApprover(userCheck.getEmpIdObj().getEmployeeId(),userCheck.getEmpIdObj().getClientId());
            if (LeaveApproverCount > 0) {
                session.put("LEAVE_APPROVER", "BOTH");
            } else {
                session.put("LEAVE_APPROVER", "NON-APPROVER");
            }
            session.get("LEAVE_APPROVER");

            Integer ApproverCount = expAppproverService.checkExpensesApprover(userCheck.getEmpIdObj().getEmployeeId(),userCheck.getEmpIdObj().getClientId());
            if (ApproverCount > 0) {
                session.put("EXPENSES_APPROVER", "BOTH");
            } else {
                session.put("EXPENSES_APPROVER", "NON-APPROVER");
            }

            Integer TimesheetApproverCount = timesheetAppproverService.checkTimesheetApprover(userCheck.getEmpIdObj().getEmployeeId());
            if (TimesheetApproverCount > 0) {
                session.put("TIMESHEET_APPROVER", "BOTH");
            } else {
                session.put("TIMESHEET_APPROVER", "NON-APPROVER");
            }

            Integer SubmitToNextLevelCount = empExpensesService.checkSubmitToNextLevel(userCheck.getEmpIdObj().getEmployeeId());
            if (SubmitToNextLevelCount > 0) {
                session.put("EXPENSES_NEXT_LEVEL_APPROVER", "APPROVER");
            } else {
                session.put("EXPENSES_NEXT_LEVEL_APPROVER", "NON-APPROVER");
            }

            Integer ExpenseAccountantCount = expAccountantService.checkExpensesAccountantApprover(userCheck.getEmpIdObj().getEmployeeId());
            if (ExpenseAccountantCount > 0) {
                session.put("EXPENSES_ACCOUNTANT", "ACCOUNTANT");
            } else {
                session.put("EXPENSES_ACCOUNTANT", "NON-ACCOUNTANT");
            }

            String ApprovalStatus = (this.getText("select.common.expenseapprover.review.value"));
            Integer ExpensesReviewCount = empExpensesService.checkExpensesReviewer(userCheck.getEmpIdObj().getEmployeeId(), ApprovalStatus);
            if (ExpensesReviewCount > 0) {
                session.put("EXPENSES_REVIEWER", "REVIEWER");
            } else {
                session.put("EXPENSES_REVIEWER", "NON-REVIEWER");
            }

            empIdList = employeeService.getReportingToSubEmpList();
            if (empIdList.size() > 0) {
                session.put("EMPLOYEE_REPORTING_TO", "REPORTING-EMPLOYEE");
            }

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
            if(mailConfigList.size()>0){
                session.put("CHECK_MAILCONFIGURATION", "AVAILABLE");
            }
            // check the client is being available if yes then we are showing
            // the AddClient Link or else we are hiding the AddClient Link
			// Adding client logo and putting that in session
//            clientList = cliService.getAllClient();
            client = cliService.getClient(userCheck.getEmpIdObj().getClientId());
            if (client != null) {
                session.put("CHECK_CLIENT", "AVAILABLE");

               if(client.getLogoName()!=null) {
                		int sClintId = userCheck.getEmpIdObj().getClientId();
                		String logoPath = "./resources/images/clientlogo/"+"MASTER_CLIENTID_"+sClintId+"/"+client.getLogoName() ;
                		System.out.println("logoPath "+logoPath);
                		session.put("CLIENT_LOGO", logoPath);
                	}else {
                		session.put("CLIENT_LOGO", "./resources/images/clientlogo/Default/clientlogo.png");
                	}
                

            } else {
                session.put("CHECK_CLIENT", "NOT_AVAILABLE");
                session.put("CLIENT_LOGO", "./resources/images/clientlogo/Default/clientlogo.png");
            }

            // check the client is being available if yes then we are showing
            // the AddClient Link or else we are hiding the AddClient Link
            currencyList = currencyService.getAllCurrency();
            if (currencyList.size() > 0) {
                session.put("CHECK_CURRENCY", "AVAILABLE");
            } else {
                session.put("CHECK_CURRENCY", "NOT_AVAILABLE");
            }
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            HttpServletRequest request = ServletActionContext.getRequest();
            peerEmpList=quesBankGeneralInfoService.getEmployeeListForPeerEmployee(oEmp.getEmployeeId());
            request.setAttribute("peerEmpList", peerEmpList);
            if(!peerEmpList.isEmpty()){
                session.put("PEER_EMPLOYEE", "PEER_EMPLOYEE");
            }

            session.put("MESSAGE_IN", new MessageHibernateDao().getAllMyMessageURCount(userCheck.getEmpIdObj().getEmployeeId()));
            session.put("MESSAGE_IN_ALERT", new MessageHibernateDao().getAllMyInAlertURCount(userCheck.getEmpIdObj().getEmployeeId()));
            session.put("MESSAGE_IN_ALERT_OPENED", new MessageHibernateDao().getAllMyInAlertOpened(userCheck.getEmpIdObj().getEmployeeId()).size());
            session.put("MESSAGE_IN_ALERT_UNOPENED", new MessageHibernateDao().getAllMyInAlertUnOpened(userCheck.getEmpIdObj().getEmployeeId()).size());
            session.put("MESSAGE_IN_MESSAGE", new MessageHibernateDao().getAllMyInMessageURCount(userCheck.getEmpIdObj().getEmployeeId()));
            session.put("MESSAGE_IN_COMPOSE_OPENED", new MessageHibernateDao().getAllMyInComposeMessageOpened(userCheck.getEmpIdObj().getEmployeeId()).size());
            session.put("MESSAGE_IN_COMPOSE_UNOPENED", new MessageHibernateDao().getAllMyInComposeMessageUnOpened(userCheck.getEmpIdObj().getEmployeeId()).size());
            session.put("MESSAGE_IN_BROADCAST", new MessageHibernateDao().getAllMyInBroadcastURCount(userCheck.getEmpIdObj().getEmployeeId()));
            session.put("MESSAGE_IN_BROADCAST_OPENED", new MessageHibernateDao().getAllMyInBroadcastMessageOpened(userCheck.getEmpIdObj().getEmployeeId()).size());
            session.put("MESSAGE_IN_BROADCAST_UNOPENED", new MessageHibernateDao().getAllMyInBroadcastMessageUnOpened(userCheck.getEmpIdObj().getEmployeeId()).size());

            session.put("JQUERY_THEME", "smoothness");

            logger.info("User Authentication Success");
            if (userCheck.getEmpIdObj() == null) {
                addActionError(this.getText("errors.authentication.failed"));
                logger.info("User Authentication Failed");
                return ERROR;
            }


            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
                
            	saasContractList = saasService.getSaasContract();

                if((saasContractList.size()==0) || (saasContractList==null) ){
                    return "SaasContract";
                }else{
                    session.put("SAAS_CONTRACTED",true);
                    for(int s=0;s<saasContractList.size();s++){
                        saasContract = saasContractList.get(s);
                        session.put("SAAS_FILE_NAME",saasContract.getSaasName());
                        session.put("SAAS_SIZE",saasContract.getSaasSize());
                        session.put("SAAS_TYPE",saasContract.getSaasType());
                        session.put("SAAS_CONTRACTED_ID",saasContract.getHcmoSaasContractId());
                    }
                }
                
                configList=configService.getAllConfiguration(userCheck.getEmpIdObj().getClientId());
                logger.info("Config List "+configList.size());

                if (!configList.isEmpty()){
                  for(int i=0;i<configList.size();i++){
                      ConfigurationVO configuration=configList.get(i);
                      config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                  }
                  if((config.getMailConfiguration()==0) || (config.getClient()==0) || (config.getLocation()==0) || (config.getRegion()==0)||(config.getSalaryGrade()==0)
                      ||(config.getJobTitle()==0) || (config.getDepartment()==0) || (config.getTeam()==0) || (config.getNationality()==0) || (config.getEthnicRace()==0)
                      || (config.getEmployee()==0)){
                      if(config.getSkip()==1){
                          config.setCreated(DateUtils.getCurrentDateTime());
                          config.setCreatedBy(oEmp);
                          config.setUpdatedBy(oEmp);
                          config.setIsActive(1);
                          config.setSkip(0);
                          configService.updateSkip(config);
                      }
                  }
              }
                logger.info("Before returning success");
                return SUCCESS;
            }else{
                return SUCCESS;
//                configList=configService.getAllConfiguration();
//                if (!configList.isEmpty()){
//                    for(int i=0;i<configList.size();i++){
//                        ConfigurationVO configuration=configList.get(i);
//                        config=configService.getConfiguration(configuration.getHcmoConfigurationId());
//                    }
//                    if(config.getMailConfiguration()==0 || config.getClient()==0 || config.getLocation()==0 || config.getRegion()==0||config.getSalaryGrade()==0
//                        ||config.getJobTitle()==0 || config.getDepartment()==0 || config.getTeam()==0 || config.getNationality()==0 || config.getEthnicRace()==0
//                        || config.getEmployee()==0){
//                        if(config.getSkip()==1){
//                            config.setCreated(DateUtils.getCurrentDateTime());
//                            config.setCreatedBy(oEmp);
//                            config.setUpdatedBy(oEmp);
//                            config.setIsActive(1);
//                            config.setSkip(0);
//                            configService.updateSkip(config);
//                        }
//                    }
//                }
            }

        }
    }

    public ExpensesApproverVO getApproverCheck() {
        return approverCheck;
    }

    public List<ExpensesApproverVO> getApproverCheckList() {
        return approverCheckList;
    }

    public String getCaptchacode() {
        return captchacode;
    }

    public String getPassword() {
        return password;
    }

    public TimeSheetApproverVO getTimesheetApproverCheck() {
        return timesheetApproverCheck;
    }

    public List<TimeSheetApproverVO> getTimesheetApproverCheckList() {
        return timesheetApproverCheckList;
    }

    public String getUsername() {
        return username;
    }

    public void setApproverCheck(ExpensesApproverVO approverCheck) {
        this.approverCheck = approverCheck;
    }

    public void setApproverCheckList(List<ExpensesApproverVO> approverCheckList) {
        this.approverCheckList = approverCheckList;
    }

    public void setCaptchacode(String captchacode) {
        this.captchacode = captchacode;
    }

    public void setPassword(String value) {
        password = value;
    }

    public void setTimesheetApproverCheck(TimeSheetApproverVO timesheetApproverCheck) {
        this.timesheetApproverCheck = timesheetApproverCheck;
    }

    public void setTimesheetApproverCheckList(List<TimeSheetApproverVO> timesheetApproverCheckList) {
        this.timesheetApproverCheckList = timesheetApproverCheckList;
    }

    public void setUsername(String value) {
        username = value;
    }

    public List<SaasContractVO> getSaasContractList() {
        return saasContractList;
    }

    public void setSaasContractList(List<SaasContractVO> saasContractList) {
        this.saasContractList = saasContractList;
    }

    public SaasContractVO getSaasContract() {
        return saasContract;
    }

    public void setSaasContract(SaasContractVO saasContract) {
        this.saasContract = saasContract;
    }

    public List<EmployeesVO> getPeerEmpList() {
        return peerEmpList;
    }

    public void setPeerEmpList(List<EmployeesVO> peerEmpList) {
        this.peerEmpList = peerEmpList;
    }

}
