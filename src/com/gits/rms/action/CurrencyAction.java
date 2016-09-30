
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.CurrencyVO;
import com.gits.rms.vo.EmployeesVO;

public class CurrencyAction extends ActionSupport {
    private static final long serialVersionUID = 4394325136098499926L;
    private CurrencyService currencyService = new CurrencyDaoService();
    private List<CurrencyVO> currencyList;
    private List<EmployeesVO> employeeList;
    private CurrencyVO currency;

    // To get List of Currency
    @SkipValidation
    public String getAllCurrency() {
        currencyList = currencyService.getAllCurrency();
        return SUCCESS;
    }

    // To View the Currency Search Form
    @SkipValidation
    public String currencySearchForm() {
        return SUCCESS;
    }

    // To Search Currency
    @SkipValidation
    public String currencySearchResult() {
        currencyList = currencyService.currencySearchResult(currency);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Currency it shows blank Form to enter New Data
    @SkipValidation
    public String setUpCurrency() {
        if ((currency != null) && (currency.getHcmoCurrencyId() != null)) {
            currency = currencyService.getCurrency(currency.getHcmoCurrencyId());
        }
        return SUCCESS;
    }

    // To get Particular Currency Data
    @SkipValidation
    public String currencyView() {
        if ((currency != null) && (currency.getHcmoCurrencyId() != null)) {
            currency = currencyService.getCurrency(currency.getHcmoCurrencyId());
        }
        return SUCCESS;
    }

    // To insert new Currency detail or Edit Particular Currency Detail
    public String insertOrUpdateCurrency() {
        if (currency.getHcmoCurrencyId() == null) {
            currencyList = currencyService.getAllCurrency();
            if (currencyList.size() == 1) {
                addActionError(getText("errors.currency.restriction"));
                return INPUT;
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
//                currency.setCreated(DateUtils.getCurrentDateTime());
//                currency.setCreatedBy(oEmp);
 //               currency.setUpdatedBy(oEmp);
                currency.setIsActive(1);
                currencyService.insertCurrency(currency);
                addActionMessage(getText("Added Successfully"));
            }
        } else {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
 //           currency.setUpdatedBy(oEmp);
            currencyService.updateCurrency(currency);
            addActionMessage(getText("Updated Successfully"));
        }
        // For Drop down List
        return SUCCESS;
    }

    // To delete Particular Currency Detail
    @SkipValidation
    public String deleteCurrency() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

 //       currency.setUpdatedBy(oEmp);
        currencyService.deleteCurrency(currency);
        addActionMessage(getText("Deleted Successfully"));

        return SUCCESS;
    }

    public List<CurrencyVO> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyVO> currencyList) {
        this.currencyList = currencyList;
    }

    public List<EmployeesVO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeesVO> employeeList) {
        this.employeeList = employeeList;
    }

    public CurrencyVO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyVO currency) {
        this.currency = currency;
    }

}
