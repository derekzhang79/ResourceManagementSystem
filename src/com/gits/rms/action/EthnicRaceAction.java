
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
import com.gits.rms.service.EthnicRaceDaoService;
import com.gits.rms.service.EthnicRaceService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.EthnicRaceVO;

public class EthnicRaceAction extends ActionSupport {
    private static final long serialVersionUID = 5281007414257123237L;
    private EthnicRaceService ethnicRaceService = new EthnicRaceDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<EthnicRaceVO> ethnicRaceList;
    private List<EmployeesVO> employeeList;
    private EthnicRaceVO ethRace;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();

    // To get List of EthnicRace
    @SkipValidation
    public String getAllEthnicRace() {
        ethnicRaceList = ethnicRaceService.getAllEthnicRace();
        return SUCCESS;
    }

    // To View the Search Page
    @SkipValidation
    public String ethnicRaceSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String ethnicRaceSearchResult() {
        ethnicRaceList = ethnicRaceService.ethnicRaceSearchResult(ethRace);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EthnicRace it shows blank Form to enter New Data
    @SkipValidation
    public String setUpEthnicRace() {
        if ((ethRace != null) && (ethRace.getEthnicRaceId() != null)) {
            ethRace = ethnicRaceService.getEthnicRace(ethRace.getEthnicRaceId());
        }
        return SUCCESS;
    }

    // To get Particular EthnicRace Data
    @SkipValidation
    public String ethnicRaceView() {
        if ((ethRace != null) && (ethRace.getEthnicRaceId() != null)) {
            ethRace = ethnicRaceService.getEthnicRace(ethRace.getEthnicRaceId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new EthnicRace detail
    // or update particular EthnicRace Data
    public String insertOrUpdateEthnicRace() {
        try {
            if (ethRace.getEthnicRaceId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                ethRace.setCreated(DateUtils.getCurrentDateTime());
//                ethRace.setCreatedBy(oEmp);
 //               ethRace.setUpdatedBy(oEmp);
                ethRace.setIsActive(1);
                ethnicRaceService.insertEthnicRace(ethRace);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                ethRace.setUpdatedBy(oEmp);
                ethnicRaceService.updateEthnicRace(ethRace);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllEthnicRaceName();
        String configure=(String) mSession.get("CONFIGURATION");
        if(mSession.get("CONFIGURATION")!=null){
            
            EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
            configList=configService.getAllConfiguration(oEmp.getClientId());
            if (!configList.isEmpty()){
                    for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("ethnicrace");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateEthnicRaceConfiguration(config);
            }
        }
        return SUCCESS;
    }

    // To delete particular EthnicRace detail
    @SkipValidation
    public String deleteEthnicRace() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the EthnicRace is being used in the Employee.If Yes
        // then we are not allowing the EthnicRace to get delete.
        employeeList = ethnicRaceService.checkEthnicRaceInEmployee(ethRace);
        if (!employeeList.isEmpty()) {
            addActionError(getText("label.header.ethnicRace.msg.deleteEmployee"));
            return SUCCESS;
        }

        // To Delete EthnicRace.
//        ethRace.setUpdatedBy(oEmp);
        ethnicRaceService.deleteEthnicRace(ethRace);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllEthnicRaceName();
        return SUCCESS;
    }

    public EthnicRaceVO getEthRace() {
        return ethRace;
    }

    public void setEthRace(EthnicRaceVO ethRace) {
        this.ethRace = ethRace;
    }

    public List<EthnicRaceVO> getEthnicRaceList() {
        return ethnicRaceList;
    }

    public void setEthnicRaceList(List<EthnicRaceVO> ethnicRaceList) {
        this.ethnicRaceList = ethnicRaceList;
    }
}
