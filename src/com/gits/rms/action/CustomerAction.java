
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.CustomerDaoService;
import com.gits.rms.service.CustomerService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectVO;

public class CustomerAction extends ActionSupport {

    private static final long serialVersionUID = -3008187444080202526L;
    private CustomerService custService = new CustomerDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<CustomerVO> custList;
    private List<ProjectVO> projectList;
    private CustomerVO cust;
    private String errorMessageValueForCustomer;

    // To get List of Customer
    @SkipValidation
    public String getAllCustomer() {
        custList = custService.getAllCustomer();
        return SUCCESS;
    }

    // To View the Search Form
    @SkipValidation
    public String customerSearchForm() {
        return SUCCESS;
    }

    // To View the Search Form
    @SkipValidation
    public String customerSearchResult() {
        custList = custService.customerSearchResult(cust);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Customer it shows blank Form to enter New Data
    @SkipValidation
    public String setUpCustomer() {
        if ((cust != null) && (cust.getCustomerId() != null)) {
            cust = custService.getCustomer(cust.getCustomerId());
        }
        return SUCCESS;
    }

    // To get Particular Customer Data
    @SkipValidation
    public String customerView() {
        if ((cust != null) && (cust.getCustomerId() != null)) {
            cust = custService.getCustomer(cust.getCustomerId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Customer or update
    // particular Customer Data
    public String insertOrUpdateCustomer() {
        try {
            if (cust.getCustomerId() == null) {

                // To insert a new Customer Details
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                cust.setCreated(DateUtils.getCurrentDateTime());
                cust.setCreatedBy(oEmp);
                cust.setUpdatedBy(oEmp);
                cust.setIsActive(1);
                custService.insertCustomer(cust);
                addActionMessage(getText("Added Successfully"));

                // For Help Information Messages
                Boolean projectAddPriv = (Boolean) session.get("PROJECT_ADD");
                if (projectAddPriv) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.header.customer.msg.project"));
                }
            } else {
                // To Update the Customer Details
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                cust.setUpdatedBy(oEmp);
                custService.updateCustomer(cust);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllCustomerName();
        return SUCCESS;
    }

    // To delete particular Customer data
    @SkipValidation
    public String deleteCustomer() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the Customer is being used in the Project.If Yes then
        // we are not allowing the Customer to get delete.
        projectList = custService.checkCustomerInProject(cust);
        if (!projectList.isEmpty()) {
            errorMessageValueForCustomer = getText("label.header.customer.msg.deleteProject");
            return SUCCESS;
        }

        // To Delete Customer.
        cust.setUpdatedBy(oEmp);
        custService.deleteCustomer(cust);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllCustomerName();
        return SUCCESS;
    }

    public List<CustomerVO> getCustList() {
        return custList;
    }

    public void setCustList(List<CustomerVO> custList) {
        this.custList = custList;
    }

    public CustomerVO getCust() {
        return cust;
    }

    public void setCust(CustomerVO cust) {
        this.cust = cust;
    }

    public String getErrorMessageValueForCustomer() {
        return errorMessageValueForCustomer;
    }

    public void setErrorMessageValueForCustomer(String errorMessageValueForCustomer) {
        this.errorMessageValueForCustomer = errorMessageValueForCustomer;
    }
}
