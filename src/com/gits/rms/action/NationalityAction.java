
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
import com.gits.rms.service.NationalityDaoService;
import com.gits.rms.service.NationalityService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.NationalityVO;

public class NationalityAction extends ActionSupport {
    private static final long serialVersionUID = 6152621187441516841L;
    private NationalityService natiService = new NationalityDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<NationalityVO> natiList;
    private List<EmployeesVO> employeeList;
    private NationalityVO nati;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();

    // To get List of Nationality for an Employee
    @SkipValidation
    public String getAllNationality() {
        natiList = natiService.getAllNationality();
        return SUCCESS;
    }

    // To View the Search Form
    @SkipValidation
    public String nationalitySearchForm() {
        return SUCCESS;
    }

    // To Search Nationality
    @SkipValidation
    public String nationalitySearchResult() {
        natiList = natiService.nationalitySearchResult(nati);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Nationality it shows blank Form to enter New Data
    @SkipValidation
    public String setUpNationality() {
        if ((nati != null) && (nati.getNationalityId() != null)) {
            nati = natiService.getNationality(nati.getNationalityId());
        }
        return SUCCESS;
    }

    // To get Particular Nationality Data of an Employee
    @SkipValidation
    public String nationalityView() {
        if ((nati != null) && (nati.getNationalityId() != null)) {
            nati = natiService.getNationality(nati.getNationalityId());
        }
        return SUCCESS;
    }

    // To insert new Nationality detail or Edit Particular Nationality Detail
    public String insertOrUpdateNationality() {
        try {
            if (nati.getNationalityId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                nati.setCreated(DateUtils.getCurrentDateTime());
//                nati.setCreatedBy(oEmp);
//                nati.setUpdatedBy(oEmp);
                nati.setIsActive(1);
                natiService.insertNationality(nati);
                addActionMessage(getText("Added Successfully"));
            } else if (nati.getNationalityId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //               nati.setUpdatedBy(oEmp);
                natiService.updateNationality(nati);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllNationalityName();
        String configure=(String) mSession.get("CONFIGURATION");
        if(mSession.get("CONFIGURATION")!=null){
        	EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        	configList=configService.getAllConfiguration(oEmp.getClientId());
            
            if (!configList.isEmpty()){
                for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("nationality");
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateNationalityConfiguration(config);
            }
        }
        return SUCCESS;
    }

    // To delete Particular Nationality Detail
    @SkipValidation
    public String deleteNationality() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the Nationality is being used in the Employee.If Yes
        // then we are not allowing the Nationality to get delete.
        employeeList = natiService.checkNationalityInEmployee(nati);
        if (!employeeList.isEmpty()) {
            addActionError(getText("label.header.nationality.msg.deleteEmployee"));
            return SUCCESS;
        }

        // To Delete nationality.
 //       nati.setUpdatedBy(oEmp);
        natiService.deleteNationality(nati);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllNationalityName();
        return SUCCESS;
    }

    public List<NationalityVO> getNatiList() {
        return natiList;
    }

    public void setNatiList(List<NationalityVO> natiList) {
        this.natiList = natiList;
    }

    public NationalityVO getNati() {
        return nati;
    }

    public void setNati(NationalityVO nati) {
        this.nati = nati;
    }

}
