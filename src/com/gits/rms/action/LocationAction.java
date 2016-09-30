
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
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.service.LocationDaoService;
import com.gits.rms.service.LocationService;
import com.gits.rms.service.RegionDaoService;
import com.gits.rms.service.RegionService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.RegionVO;

public class LocationAction extends ActionSupport {
    private static final long serialVersionUID = -577612873266797583L;
    private LocationService locService = new LocationDaoService();
    private CountryService couService = new CountryDaoService();
    private RegionService regService = new RegionDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private LocationVO loc;
    private List<LocationVO> location;
    private List<CountryVO> country;
    private List<RegionVO> region;
    private List<EmpLocationHistoryVO> empLocationHistList;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();
    // To get List of Location
    @SkipValidation
    public String getAllLocation() {
        location = locService.getAllLocation();
        return SUCCESS;
    }

    // Search Form
    @SkipValidation
    public String locationSearchForm() {
        return SUCCESS;
    }

    // Search Result of Location
    @SkipValidation
    public String locationSearchResult() {
        location = locService.locationSearchResult(loc);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Location it shows blank Form to enter New Data
    @SkipValidation
    public String setUpLocation() {
        if ((loc != null) && (loc.getHcmolocationId() != null)) {
            loc = locService.getLocation(loc.getHcmolocationId());
        }
        return SUCCESS;
    }

    // To get Particular Location Data
    @SkipValidation
    public String locationView() {
        if ((loc != null) && (loc.getHcmolocationId() != null)) {
            loc = locService.getLocation(loc.getHcmolocationId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Location or update
    // particular Location Data
    public String insertOrUpdateLocation() {
        try {
            if (loc.getHcmolocationId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
           /*     loc.setCreated(DateUtils.getCurrentDateTime());
                loc.setCreatedBy(oEmp);
                loc.setUpdatedBy(oEmp);*/
                loc.setIsActive(1);
                locService.insertLocation(loc);
                addActionMessage(getText("Added Successfully"));

                session.get("ORGANIZATIONTYPE_ADD");
                session.get("ORGANIZATION_ADD");

            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //               loc.setUpdatedBy(oEmp);
                locService.updateLocation(loc);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllLocationName();
        if(mSession.get("CONFIGURATION")!=null){
          
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            configList=configService.getAllConfiguration(oEmp.getClientId());
            if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("location");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateLocationConfiguration(config);
            }
        }
        return SUCCESS;
    }

    // To delete particular Location data of an employee
    @SkipValidation
    public String deleteLocation() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //       loc.setUpdatedBy(oEmp);
        loc = locService.getLocation(loc.getHcmolocationId());

        // To check that the Location is being used in the Employee Location
        // History Yes then we are not allowing the Location to get delete.
        empLocationHistList = locService.checkLocationInEmpLocationHistory(loc);
        if (!(empLocationHistList.isEmpty())) {
            addActionError(getText("label.header.location.msg.deleteEmpLocHist"));
            return SUCCESS;
        }

        locService.deleteLocation(loc);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllLocationName();
        return SUCCESS;
    }

    public String insertOrUpdateConfigurationLocation() {
        try {
            if (loc.getHcmolocationId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 /*               loc.setCreated(DateUtils.getCurrentDateTime());
                loc.setCreatedBy(oEmp);
                loc.setUpdatedBy(oEmp);
    */            loc.setIsActive(1);
                locService.insertLocation(loc);
                addActionMessage(getText("Added Successfully"));

                session.get("ORGANIZATIONTYPE_ADD");
                session.get("ORGANIZATION_ADD");
                configList=configService.getAllConfiguration(oEmp.getClientId());
                if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                        ConfigurationVO configuration=configList.get(i);
                        config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                    }
                    config.setMailConfiguration(config.getMailConfiguration());
                    config.setCreated(DateUtils.getCurrentDateTime());
                    config.setCreatedBy(oEmp);
                    config.setUpdatedBy(oEmp);
                    config.setIsActive(1);
                    configService.updateClientConfiguration(config);
                    
                }

            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                loc.setUpdatedBy(oEmp);
                locService.updateLocation(loc);
                addActionMessage(getText("Updated Successfully"));
                configList=configService.getAllConfiguration(oEmp.getClientId());
                if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                        ConfigurationVO configuration=configList.get(i);
                        config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                    }
                    config.setMailConfiguration(config.getMailConfiguration());
                    config.setCreated(DateUtils.getCurrentDateTime());
                    config.setCreatedBy(oEmp);
                    config.setUpdatedBy(oEmp);
                    config.setIsActive(1);
                    configService.updateClientConfiguration(config);
                    
                }
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllLocationName();
        return SUCCESS;
    }
    
    public LocationVO getLoc() {
        return loc;
    }

    public void setLoc(LocationVO loc) {
        this.loc = loc;
    }

    public List<LocationVO> getLocation() {
        return location;
    }

    public void setLocation(List<LocationVO> location) {
        this.location = location;
    }

    @SkipValidation
    public List getCountry() {
        country = couService.getAllCountry();
        return country;
    }

    @SkipValidation
    public List getRegion() {
        region = regService.getAllRegion();
        return region;
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