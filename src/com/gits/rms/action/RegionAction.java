
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.service.RegionDaoService;
import com.gits.rms.service.RegionService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.RegionVO;

public class RegionAction extends ActionSupport {
	
    private static final long serialVersionUID = 3683803460619544238L;
    private RegionService regService = new RegionDaoService();
    private CountryService couService = new CountryDaoService();
    private RegionVO reg;
    private List<RegionVO> region;
    private List<CountryVO> country;
    private Map<Integer, String> mCountry;
    private CountryVO cou;
    private List<ClientVO> clientList;
    private List<LocationVO> locList;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();

    @SkipValidation
    public String getAllRegion() {
        region = regService.getAllRegion();
        return SUCCESS;
    }

    // To View the Region Search Form
    @SkipValidation
    public String regionSearchForm() {
        return SUCCESS;
    }

    // Region Search Result
    @SkipValidation
    public String regionSearchResult() {
        region = regService.regionSearchResult(reg);
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateRegion() {
       
        if ((reg != null) && (reg.getHcmoregionId() != null)) {
            reg = regService.getRegion(reg.getHcmoregionId());
        }
        return SUCCESS;
    }

    @SkipValidation
    public String regionView() {
       
        if ((reg != null) && (reg.getHcmoregionId() != null)) {
            reg = regService.getRegion(reg.getHcmoregionId());
        }
        return SUCCESS;
    }

    public String insertOrUpdateRegion() {
    	  Map session = ActionContext.getContext().getSession();
//        CLIENT ID ADDED BY BALA
        Integer clientId = (Integer) session.get("CLIENT_INFO"); 
        if (reg.getHcmoregionId() == null) {
              EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //           reg.setCreated(DateUtils.getCurrentDateTime());
 //           reg.setCreatedBy(oEmp);
 //           reg.setUpdatedBy(oEmp);
            reg.setIsActive(1);
            regService.insertRegion(reg);
            addActionMessage(getText("Added Successfully"));
        } else {
//            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
  //          reg.setUpdatedBy(oEmp);
            regService.updateRegion(reg);
            addActionMessage(getText("Updated Successfully"));
        }
        if(mSession.get("CONFIGURATION")!=null){
//        	CLIENT ID ADDED BY BALA
            configList=configService.getAllConfiguration(clientId);
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            if (!configList.isEmpty()){
                for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("region");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateRegionConfiguration(config);
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String deleteRegion() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //       reg.setUpdatedBy(oEmp);
        reg = regService.getRegion(reg.getHcmoregionId());

        // To check that the Region is being used in the Client.If Yes then we
        // are not allowing the Region to get delete.
        clientList = regService.checkRegionInClient(reg);
        if (!(clientList.isEmpty())) {
            addActionError(getText("label.header.region.msg.deleteClient"));
            return SUCCESS;
        }

        // To check that the Region is being used in the Location.If Yes then we
        // are not allowing the Region to get delete.
        locList = regService.checkRegionInLocation(reg);
        if (!(locList.isEmpty())) {
            addActionError(getText("label.header.region.msg.deleteLocation"));
            return SUCCESS;
        }

        regService.deleteRegion(reg);
        addActionMessage(getText("Deleted Successfully"));

       
        return SUCCESS;
    }

    public Map<Integer, String> getMCountry() {
        return mCountry;
    }

    public RegionVO getReg() {
        return reg;
    }

    public void setReg(RegionVO reg) {
        this.reg = reg;
    }

    public List<RegionVO> getRegion() {
        return region;
    }

    public void setRegion(List<RegionVO> region) {
        this.region = region;
    }

    public CountryVO getCou() {
        return cou;
    }

    public void setCou(CountryVO cou) {
        this.cou = cou;
    }

    public List getCountry() {
        country = couService.getAllCountry();
        return country;
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
