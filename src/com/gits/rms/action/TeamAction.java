
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.TeamDaoService;
import com.gits.rms.service.TeamService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TeamVO;

public class TeamAction extends ActionSupport {
    private static final long serialVersionUID = 1988795199635208732L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private TeamService teamService = new TeamDaoService();
    private List<TeamVO> teamList;
    private TeamVO team;
    private List<EmployeesVO> empList;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();
    
    // To get List of Team
    @SkipValidation
    public String getAllTeam() {
        teamList = teamService.getAllTeam();
        return SUCCESS;
    }

    // To View the Team Search Form
    @SkipValidation
    public String teamSearchForm() {
        return SUCCESS;
    }

    // To Search Team
    @SkipValidation
    public String teamSearchResult() {
        teamList = teamService.teamSearchResult(team);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add Team
    // it shows blank Form to enter New Data
    @SkipValidation
    public String setUpTeam() {
        if ((team != null) && (team.getHcmoTeamId() != null)) {
            team = teamService.getTeam(team.getHcmoTeamId());
        }
        return SUCCESS;
    }

    // To get Particular Team Data
    @SkipValidation
    public String teamView() {
        if ((team != null) && (team.getHcmoTeamId() != null)) {
            team = teamService.getTeam(team.getHcmoTeamId());
        }
        return SUCCESS;
    }

    // To insert new Team detail or Edit Particular Team Detail
    public String insertOrUpdateTeam() {
        try {
            if (team.getHcmoTeamId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                team.setCreated(DateUtils.getCurrentDateTime());
                team.setCreatedBy(oEmp);
                team.setUpdatedBy(oEmp);
                team.setIsActive(1);
                teamService.insertTeam(team);
                addActionMessage(getText("Added Successfully"));
            } else if (team.getHcmoTeamId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                team.setUpdatedBy(oEmp);
                teamService.updateTeam(team);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllTeamName();
        String configure=(String) mSession.get("CONFIGURATION");
        if(mSession.get("CONFIGURATION")!=null){
        	EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        	configList=configService.getAllConfiguration(oEmp.getClientId());
            
            if (!configList.isEmpty()){
                for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("team");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateTeamConfiguration(config);
            }
        }
        return SUCCESS;
    }

    // To delete Particular Team Detail
    @SkipValidation
    public String deleteTeam() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        team.setUpdatedBy(oEmp);

        // To check that the Team is being used in the Employees.If Yes then we
        // are not allowing the Team to get delete.
        empList = teamService.checkTeamInEmployees(team);
        if (!(empList.isEmpty())) {
            addActionError(getText("label.header.team.msg.deleteEmployee"));
            return SUCCESS;
        }

        teamService.deleteTeam(team);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllTeamName();
        return SUCCESS;
    }

    public TeamService getTeamService() {
        return teamService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public List<TeamVO> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<TeamVO> teamList) {
        this.teamList = teamList;
    }

    public TeamVO getTeam() {
        return team;
    }

    public void setTeam(TeamVO team) {
        this.team = team;
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

}
