
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
import com.gits.rms.service.JobTitleDaoService;
import com.gits.rms.service.JobTitleService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.JobTitleVO;

public class JobTitleAction extends ActionSupport {
    private static final long serialVersionUID = -2887283459683643182L;
    private JobTitleService jobTitleService = new JobTitleDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<JobTitleVO> jobTitleList;
    private List<EmployeesVO> employeeList;
    private JobTitleVO jobTitle;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();

    // To get List of JobTitle
    @SkipValidation
    public String getAllJobTitle() {
        jobTitleList = jobTitleService.getAllJobTitle();
        return SUCCESS;
    }

    // To View the Search Form
    @SkipValidation
    public String jobTitleSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String jobTitleSearchResult() {
        jobTitleList = jobTitleService.jobTitleSearchResult(jobTitle);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // JobTitle it shows blank Form to enter New Data
    @SkipValidation
    public String setUpJobTitle() {
        if ((jobTitle != null) && (jobTitle.getJobTitleId() != null)) {
            jobTitle = jobTitleService.getJobTitle(jobTitle.getJobTitleId());
        }
        return SUCCESS;
    }

    // To get Particular JobTitle Data
    @SkipValidation
    public String jobTitleView() {
        if ((jobTitle != null) && (jobTitle.getJobTitleId() != null)) {
            jobTitle = jobTitleService.getJobTitle(jobTitle.getJobTitleId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new JobTitle detail or
    // update particular JobTitle Data
    public String insertOrUpdateJobTitle() {
        try {
            if (jobTitle.getJobTitleId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                jobTitle.setCreated(DateUtils.getCurrentDateTime());
                jobTitle.setCreatedBy(oEmp);
                jobTitle.setUpdatedBy(oEmp);
                jobTitle.setIsActive(1);
                jobTitleService.insertJobTitle(jobTitle);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                jobTitle.setUpdatedBy(oEmp);
                jobTitleService.updateJobTitle(jobTitle);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllJobTitleName();

        if(mSession.get("CONFIGURATION")!=null){
            
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            configList=configService.getAllConfiguration(oEmp.getEmployeeId());
            if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("jobtitle");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateJobTitleConfiguration(config);
            }
        }

        return SUCCESS;
    }

    // To delete Particular JobTitle from the list
    @SkipValidation
    public String deleteJobTitle() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the JobTitle is being used in the Employee.If Yes then
        // we are not allowing the JobTitle to get delete.
        employeeList = jobTitleService.checkJobTitleInEmployee(jobTitle);
        if (!employeeList.isEmpty()) {
            addActionError(getText("label.header.jobTitle.msg.deleteEmployee"));
            return SUCCESS;
        }

        // To Delete EthnicRace.
        jobTitle.setUpdatedBy(oEmp);
        jobTitleService.deleteJobTitle(jobTitle);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllJobTitleName();
        return SUCCESS;
    }

    public List<JobTitleVO> getJobTitleList() {
        return jobTitleList;
    }

    public void setJobTitleList(List<JobTitleVO> jobTitleList) {
        this.jobTitleList = jobTitleList;
    }

    public JobTitleVO getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleVO jobTitle) {
        this.jobTitle = jobTitle;
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
