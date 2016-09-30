
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
import com.gits.rms.service.SalaryGradeDaoService;
import com.gits.rms.service.SalaryGradeService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.JobTitleVO;
import com.gits.rms.vo.SalaryGradeVO;

public class SalaryGradeAction extends ActionSupport {
    private static final long serialVersionUID = 9069519643718881197L;
    private SalaryGradeService salgraService = new SalaryGradeDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<SalaryGradeVO> salarygrade;
    private SalaryGradeVO salgra;
    private List<JobTitleVO> jobList;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();

    // To get All Type of SalaryGrade List
    @SkipValidation
    public String getAllSalaryGrade() {
        salarygrade = salgraService.getAllSalaryGrade();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String salaryGradeSearchForm() {
        return SUCCESS;
    }

    // To Search Result
    @SkipValidation
    public String salaryGradeSearchResult() {
        salarygrade = salgraService.salaryGradeSearchResult(salgra);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // SalaryGrade it shows blank Form to enter New Data
    @SkipValidation
    public String setUpSalaryGrade() {
        if ((salgra != null) && (salgra.getHcmoSalaryGradeId() != null)) {
            salgra = salgraService.getSalaryGrade(salgra.getHcmoSalaryGradeId());
        }
        return SUCCESS;
    }

    // To get Particular SalaryGrade Data
    @SkipValidation
    public String salaryGradeView() {
        if ((salgra != null) && (salgra.getHcmoSalaryGradeId() != null)) {
            salgra = salgraService.getSalaryGrade(salgra.getHcmoSalaryGradeId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new SalaryGrade or
    // update particular SalaryGrade Data
    public String insertOrUpdateSalaryGrade() {
        try {
            if (salgra.getHcmoSalaryGradeId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                salgra.setCreated(DateUtils.getCurrentDateTime());
                salgra.setCreatedBy(oEmp);
                salgra.setUpdatedBy(oEmp);
                salgra.setIsActive(1);
                salgraService.insertSalaryGrade(salgra);
                addActionMessage(getText("Added Succesfully"));

                // Help information Messages
                Boolean jobTitleAddPriv = (Boolean) session.get("JOBTITLE_ADD");
                if (jobTitleAddPriv) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.header.salaryGrade.msg.jobTitle"));
                }
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                salgra.setUpdatedBy(oEmp);
                salgraService.updateSalaryGrade(salgra);
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
                config.setStatus("salarygrade");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateSalaryGradeConfiguration(config);
            }
        }
        // For Drop down List
        loadValues.getAllSalaryGradeName();
        return SUCCESS;
    }

    // To delete particular SalaryGrade
    @SkipValidation
    public String deleteSalaryGrade() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        salgra.setUpdatedBy(oEmp);

        // To check that the SalaryGrade is being used in the Jobtitle Yes then
        // we are not allowing the SalaryGrade to get delete.
        jobList = salgraService.checkSalaryGradeInJobTitle(salgra);
        if (!(jobList.isEmpty())) {
            addActionError(getText("label.header.salaryGrade.msg.deleteJobTitle"));
            return SUCCESS;
        }
        salgraService.deleteSalaryGrade(salgra);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllSalaryGradeName();
        return SUCCESS;
    }

    public List<SalaryGradeVO> getSalarygrade() {
        return salarygrade;
    }

    public void setSalarygrade(List<SalaryGradeVO> salarygrade) {
        this.salarygrade = salarygrade;
    }

    public SalaryGradeVO getSalgra() {
        return salgra;
    }

    public void setSalgra(SalaryGradeVO salgra) {
        this.salgra = salgra;
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
