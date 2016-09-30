package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.service.ClientDaoService;
import com.gits.rms.service.ClientService;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.DepartmentDaoService;
import com.gits.rms.service.DepartmentService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.EthnicRaceDaoService;
import com.gits.rms.service.EthnicRaceService;
import com.gits.rms.service.JobTitleDaoService;
import com.gits.rms.service.JobTitleService;
import com.gits.rms.service.LocationDaoService;
import com.gits.rms.service.LocationService;
import com.gits.rms.service.MailConfigurationDaoService;
import com.gits.rms.service.MailConfigurationService;
import com.gits.rms.service.NationalityDaoService;
import com.gits.rms.service.NationalityService;
import com.gits.rms.service.RegionDaoService;
import com.gits.rms.service.RegionService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SalaryGradeDaoService;
import com.gits.rms.service.SalaryGradeService;
import com.gits.rms.service.TeamDaoService;
import com.gits.rms.service.TeamService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EthnicRaceVO;
import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.MailConfigurationVO;
import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.RegionVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SalaryGradeVO;
import com.gits.rms.vo.TeamVO;

public class ConfigurationAction extends ActionSupport{

    private static final long serialVersionUID = 8976817517882862082L;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    private MailConfigurationService mailService=new MailConfigurationDaoService();
    private MailConfigurationVO mail;
    private RoleService roleService=new RoleDaoService();
    private RoleVO role;
    private ClientService cliService=new ClientDaoService();
    private List<ClientVO> client;
    private LocationService locService=new LocationDaoService();
    private List<LocationVO> location;
    private RegionService regService=new RegionDaoService();
    private List<RegionVO> region;
    private SalaryGradeService salGradeService=new SalaryGradeDaoService();
    private List<SalaryGradeVO> salarygrade;
    private List<JobTitleVO> jobTitleList;
    private JobTitleService jobService=new JobTitleDaoService();
    private List<DepartmentVO> deptList;
    private List<TeamVO> teamList;
    private List<EthnicRaceVO> ethnicRaceList;
    private List<NationalityVO> natiList;
    private List<EmployeesVO> emploList;
    private DepartmentService deptService=new DepartmentDaoService();
    private TeamService teamService=new TeamDaoService();
    private EthnicRaceService ethnicService=new EthnicRaceDaoService();
    private NationalityService natService=new NationalityDaoService();
    private EmployeesService empService=new EmployeesDaoService();
    private List<MailConfigurationVO> mailConfigList;
    private MailConfigurationService mailConfigService = new MailConfigurationDaoService();

    Map session = ActionContext.getContext().getSession();
    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

    @SkipValidation
    public String getConfigurationHome() {
            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                    || (oEmp.getRoleObj().getRoleName().equals("admin"))
                    || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
                session.put("CONFIGURATION","CONFIGURATION");
                configList=configService.getAllConfiguration(oEmp.getClientId());
                if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                        ConfigurationVO configuration=configList.get(i);
                        config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                    }
                        if((config.getMailConfiguration()==0) && config.getStatus().equals("mailconfiguration")){
                        	mailConfigList = mailConfigService.getAllMailConfig();
                            return "mailConfiguration";
                        }else if ((config.getClient()==0) && config.getStatus().equals("client")) {
                            client=cliService.getAllClient();
                            return "client";
                        }else if((config.getLocation()==0) && config.getStatus().equals("location")) {
                            location=locService.getAllLocation();
                            return "location";
                        }else if((config.getRegion()==0) && config.getStatus().equals("region")) {
                            region=regService.getAllRegion();
                            return "region";
                        }else if((config.getSalaryGrade()==0) && config.getStatus().equals("salarygrade")) {
                            salarygrade=salGradeService.getAllSalaryGrade();
                            return "salarygrade";
                        }else if((config.getJobTitle()==0) && config.getStatus().equals("jobtitle")) {
                            jobTitleList=jobService.getAllJobTitle();
                            return "jobtitle";
                        }else if((config.getDepartment()==0) && config.getStatus().equals("department")) {
                            deptList=deptService.getAllDepartment();
                            return "department";
                        }else if((config.getTeam()==0) && config.getStatus().equals("team")) {
                            teamList=teamService.getAllTeam();
                            return "team";
                        }else if((config.getNationality()==0) && config.getStatus().equals("nationality")) {
                            natiList=natService.getAllNationality();
                            return "nationality";
                        }else if((config.getEthnicRace()==0) && config.getStatus().equals("ethnicrace")) {
                            ethnicRaceList=ethnicService.getAllEthnicRace();
                            return "ethnicrace";
                        }else if((config.getEmployee()==0) && config.getStatus().equals("employee")) {
                            emploList=empService.getAllEmployees(oEmp.getClientId());
                            return "employee";
                        }

                }
            }
            return SUCCESS;
    }


    public String insertOrUpdateConfiguration() {
        try {
            if (config.getHcmoConfigurationId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.insertConfiguration(config);
                addActionMessage(getText("Added Successfully"));
            }
            }catch (RuntimeException e) {
                ErrorsAction errAction = new ErrorsAction();
                String sError = errAction.getError(e);
                addActionError(sError);
                throw e;
            }
            return SUCCESS;
        }


    public String nextConfigurationAction() {
        configList=configService.getAllConfiguration(oEmp.getClientId());
        if (!configList.isEmpty()){
            for(int i=0;i<configList.size();i++){
                ConfigurationVO configuration=configList.get(i);
                config=configService.getConfiguration(configuration.getHcmoConfigurationId());
            }
        }
            if(config.getStatus().equals("mailconfiguration")){
                    config.setCreated(DateUtils.getCurrentDateTime());
                    config.setStatus("client");
                    config.setCreatedBy(oEmp);
                    config.setUpdatedBy(oEmp);
                    config.setIsActive(1);
                    configService.updateConfiguration(config);
                    client=cliService.getAllClient();
                    return "client";
            }
            if(config.getStatus().equals("client")){
                        config.setCreated(DateUtils.getCurrentDateTime());
                        config.setStatus("location");
                        config.setCreatedBy(oEmp);
                        config.setUpdatedBy(oEmp);
                        config.setIsActive(1);
                        configService.updateClientConfiguration(config);
                        location=locService.getAllLocation();
                return "location";
            }
            if(config.getStatus().equals("location")){
                        config.setCreated(DateUtils.getCurrentDateTime());
                        config.setStatus("region");
                        config.setCreatedBy(oEmp);
                        config.setUpdatedBy(oEmp);
                        config.setIsActive(1);
                        configService.updateLocationConfiguration(config);
                        region=regService.getAllRegion();
                return "region";
            }
            if(config.getStatus().equals("region")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("salarygrade");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateRegionConfiguration(config);
                salarygrade=salGradeService.getAllSalaryGrade();
                return "salarygrade";
            }
            if(config.getStatus().equals("salarygrade")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("jobtitle");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateSalaryGradeConfiguration(config);
                jobTitleList=jobService.getAllJobTitle();
                return "jobtitle";
            }
            if(config.getStatus().equals("jobtitle")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("department");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateJobTitleConfiguration(config);
                deptList=deptService.getAllDepartment();
                return "department";
            }
            if(config.getStatus().equals("department")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("team");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateDepartmentConfiguration(config);
                teamList=teamService.getAllTeam();
                return "team";
            }
            if(config.getStatus().equals("team")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("nationality");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateTeamConfiguration(config);
                natiList=natService.getAllNationality();
                return "nationality";
            }
            if(config.getStatus().equals("nationality")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("ethnicrace");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateNationalityConfiguration(config);
                ethnicRaceList=ethnicService.getAllEthnicRace();
                return "ethnicrace";
            }
            if(config.getStatus().equals("ethnicrace")){
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setStatus("employee");
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateEthnicRaceConfiguration(config);
                emploList=empService.getAllEmployees(oEmp.getClientId());
                return "employee";
            }
            return null;
    }

    public String skippedPermanently() {
        configList=configService.getAllConfiguration(oEmp.getClientId());
        if (!configList.isEmpty()){
            for(int i=0;i<configList.size();i++){
                ConfigurationVO configuration=configList.get(i);
                config=configService.getConfiguration(configuration.getHcmoConfigurationId());
            }
            config.setCreated(DateUtils.getCurrentDateTime());
            if(config.getStatus().equals("employee")){
                configService.updateEmployeeConfiguration(config);
                emploList=empService.getAllEmployees(oEmp.getClientId());
            }
            config.setStatus("skippedpermanantely");
            config.setCreatedBy(oEmp);
            config.setUpdatedBy(oEmp);
            config.setIsActive(1);

            configService.updateStatus(config);
            session.remove("CONFIGURATION");
        }
        return "skippedpermanantely";
    }

    public String skip() {
        session.remove("CONFIGURATION");
        configList=configService.getAllConfiguration(oEmp.getClientId());
        if (!configList.isEmpty()){
            for(int i=0;i<configList.size();i++){
                ConfigurationVO configuration=configList.get(i);
                config=configService.getConfiguration(configuration.getHcmoConfigurationId());
            }

            if((config.getMailConfiguration()==1) && config.getStatus().equals("mailconfiguration")){
            	config.setStatus("client");
            }else if ((config.getClient()==1) && config.getStatus().equals("client")) {
            	config.setStatus("location");
            }else if((config.getLocation()==1) && config.getStatus().equals("location")) {
            	config.setStatus("region");
            }else if((config.getRegion()==1) && config.getStatus().equals("region")) {
            	config.setStatus("salarygrade");
            }else if((config.getSalaryGrade()==1) && config.getStatus().equals("salarygrade")) {
            	config.setStatus("jobtitle");
            }else if((config.getJobTitle()==1) && config.getStatus().equals("jobtitle")) {
            	config.setStatus("department");
            }else if((config.getDepartment()==1) && config.getStatus().equals("department")) {
            	config.setStatus("team");
            }else if((config.getTeam()==1) && config.getStatus().equals("team")) {
            	config.setStatus("nationality");
            }else if((config.getNationality()==1) && config.getStatus().equals("nationality")) {
            	config.setStatus("ethnicrace");
            }else if((config.getEthnicRace()==1) && config.getStatus().equals("ethnicrace")) {
            	config.setStatus("employee");
            }else if((config.getEmployee()==1) && config.getStatus().equals("employee")) {
            	config.setStatus("skippedpermanantely");
            }


            if((config.getMailConfiguration()==0) && config.getStatus().equals("mailconfiguration")){
            	config.setStatus("mailconfiguration");
            }else if ((config.getClient()==0) && config.getStatus().equals("client")) {
            	config.setStatus("client");
            }else if((config.getLocation()==0) && config.getStatus().equals("location")) {
            	config.setStatus("location");
            }else if((config.getRegion()==0) && config.getStatus().equals("region")) {
            	config.setStatus("region");
            }else if((config.getSalaryGrade()==0) && config.getStatus().equals("salarygrade")) {
            	config.setStatus("salarygrade");
            }else if((config.getJobTitle()==0) && config.getStatus().equals("jobtitle")) {
            	config.setStatus("jobtitle");
            }else if((config.getDepartment()==0) && config.getStatus().equals("department")) {
            	config.setStatus("department");
            }else if((config.getTeam()==0) && config.getStatus().equals("team")) {
            	config.setStatus("team");
            }else if((config.getNationality()==0) && config.getStatus().equals("nationality")) {
            	config.setStatus("nationality");
            }else if((config.getEthnicRace()==0) && config.getStatus().equals("ethnicrace")) {
            	config.setStatus("ethnicrace");
            }else if((config.getEmployee()==0) && config.getStatus().equals("employee")) {
            	config.setStatus("employee");
            }


            config.setSkip(1);
            config.setCreated(DateUtils.getCurrentDateTime());
            config.setCreatedBy(oEmp);
            config.setUpdatedBy(oEmp);
            config.setIsActive(1);
            configService.updateSkip(config);
        }
        return SUCCESS;
    }

    public ConfigurationVO getConfig() {
        return config;
    }

    public void setConfig(ConfigurationVO config) {
        this.config = config;
    }

    public List<ConfigurationVO> getConfigList() {
        return configList;
    }

    public void setConfigList(List<ConfigurationVO> configList) {
        this.configList = configList;
    }

    public MailConfigurationVO getMail() {
        return mail;
    }

    public void setMail(MailConfigurationVO mail) {
        this.mail = mail;
    }

    public List<ClientVO> getClient() {
        return client;
    }

    public void setClient(List<ClientVO> client) {
        this.client = client;
    }

    public List<LocationVO> getLocation() {
        return location;
    }

    public void setLocation(List<LocationVO> location) {
        this.location = location;
    }

    public List<RegionVO> getRegion() {
        return region;
    }

    public void setRegion(List<RegionVO> region) {
        this.region = region;
    }

    public List<SalaryGradeVO> getSalarygrade() {
        return salarygrade;
    }

    public void setSalarygrade(List<SalaryGradeVO> salarygrade) {
        this.salarygrade = salarygrade;
    }

    public List<JobTitleVO> getJobTitleList() {
        return jobTitleList;
    }

    public void setJobTitleList(List<JobTitleVO> jobTitleList) {
        this.jobTitleList = jobTitleList;
    }

    public List<DepartmentVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DepartmentVO> deptList) {
        this.deptList = deptList;
    }

    public List<TeamVO> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<TeamVO> teamList) {
        this.teamList = teamList;
    }

    public List<NationalityVO> getNatiList() {
        return natiList;
    }

    public void setNatiList(List<NationalityVO> natiList) {
        this.natiList = natiList;
    }

    public List<EmployeesVO> getEmploList() {
        return emploList;
    }

    public void setEmploList(List<EmployeesVO> emploList) {
        this.emploList = emploList;
    }

    public List<EthnicRaceVO> getEthnicRaceList() {
        return ethnicRaceList;
    }

    public void setEthnicRaceList(List<EthnicRaceVO> ethnicRaceList) {
        this.ethnicRaceList = ethnicRaceList;
    }

	public List<MailConfigurationVO> getMailConfigList() {
		return mailConfigList;
	}

	public void setMailConfigList(List<MailConfigurationVO> mailConfigList) {
		this.mailConfigList = mailConfigList;
	}
}
