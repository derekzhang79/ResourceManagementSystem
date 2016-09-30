
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientVO;
import com.gits.rms.vo.CountryVO;
import com.gits.rms.vo.EmpPassportVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.RegionVO;

public class CountryAction extends ActionSupport {
    private static final long serialVersionUID = 6466558712602893945L;
    private CountryService couService = new CountryDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private CountryVO cou;
    private List<CountryVO> country;
    private List<ClientVO> clientList;
    private List<LocationVO> locationList;
    private List<RegionVO> regList;
    private List<EmployeesVO> empList;
    private List<EmpPassportVO> empPassList;

    // To get List of Client
    @SkipValidation
    public String getAllCountry() {
        country = couService.getAllCountry();
        return SUCCESS;
    }

    // To View Country Search Form
    @SkipValidation
    public String countrySearchForm() {
        return SUCCESS;
    }

    // To View Country Search Form Result
    @SkipValidation
    public String countrySearchResult() {
        country = couService.countrySearchResult(cou);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Country it shows blank Form to enter New Data
    @SkipValidation
    public String setUpInsertOrUpdateCountry() {
        if ((cou != null) && (cou.getHcmocountryId() != null)) {
            cou = couService.getCountry(cou.getHcmocountryId());
        }
        return SUCCESS;
    }

    // To get Particular Country Data
    @SkipValidation
    public String countryView() {
        if ((cou != null) && (cou.getHcmocountryId() != null)) {
            cou = couService.getCountry(cou.getHcmocountryId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Country or update
    // particular Country Data
    public String insertOrUpdateCountry() {
        try {
            if (cou.getHcmocountryId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //               cou.setCreated(DateUtils.getCurrentDateTime());
  //             cou.setCreatedBy(oEmp);
  //            cou.setUpdatedBy(oEmp);
                cou.setIsActive(1);
                couService.insertCountry(cou);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                cou.setUpdatedBy(oEmp);
                couService.updateCountry(cou);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllCountry();
        return SUCCESS;
    }

    // To delete particular Country data
    @SkipValidation
    public String deleteCountry() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //       cou.setUpdatedBy(oEmp);

        // To check that the Country is being used in the Client.If Yes then we
        // are not allowing the Country to get delete.
        clientList = couService.checkCountryInClient(cou);
        if (!(clientList.isEmpty())) {
            addActionError(getText("label.header.country.msg.deleteClient"));
            return SUCCESS;
        }

        // To check that the Country is being used in the Location.If Yes then
        // we are not allowing the Country to get delete.
        locationList = couService.checkCountryInLocation(cou);
        if (!(locationList.isEmpty())) {
            addActionError(getText("label.header.country.msg.deleteLocation"));
            return SUCCESS;
        }

        // To check that the Country is being used in the Region.If Yes then we
        // are not allowing the Country to get delete.
        regList = couService.checkCountryInRegion(cou);
        if (!(regList.isEmpty())) {
            addActionError(getText("label.header.country.msg.deleteRegion"));
            return SUCCESS;
        }

        // To check that the Country is being used in the Employees.If Yes then
        // we are not allowing the Country to get delete.
        empList = couService.checkCountryInEmployees(cou);
        if (!(empList.isEmpty())) {
            addActionError(getText("label.header.country.msg.deleteEmployee"));
            return SUCCESS;
        }

        // To check that the Country is being used in the Passport.If Yes then
        // we are not allowing the Country to get delete.
        empPassList = couService.checkCountryInPassport(cou);
        if (!(empPassList.isEmpty())) {
            addActionError(getText("label.header.country.msg.deletePassport"));
            return SUCCESS;
        }

        couService.deleteCountry(cou);
        LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
        loadValues.getAllCountry();
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllCountry();
        return SUCCESS;
    }

    public CountryVO getCou() {
        return cou;
    }

    public void setCou(CountryVO cou) {
        this.cou = cou;
    }

    public List<CountryVO> getCountry() {
        return country;
    }

    public void setCountry(List<CountryVO> country) {
        this.country = country;
    }

}