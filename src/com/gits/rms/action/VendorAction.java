
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.VendorDaoService;
import com.gits.rms.service.VendorService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.VendorVO;

public class VendorAction extends ActionSupport {
    private static final long serialVersionUID = -7345912038456566799L;
    private VendorVO vendor;
    private VendorVO venObj;
    private List<VendorVO> vendorList;
    private VendorService vendorService = new VendorDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private String vendorName;

    // To get List of Vendors
    @SkipValidation
    public String getAllVendor() {
        vendorName = "Not Applicable";
        vendorList = vendorService.getAllVendorExceptInitialVendorName(vendorName);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Vendor it shows blank Form to enter New Data
    @SkipValidation
    public String setUpForInsertOrUpdateVendor() {
        if ((vendor != null) && (vendor.getVendorId() != null)) {
            vendor = vendorService.getVendor(vendor.getVendorId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Vendor or update
    // particular Vendor Data
    public String insertOrUpdateVendor() {
        try {
            if (vendor.getVendorId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                vendor.setCreated(DateUtils.getCurrentDateTime());
                vendor.setCreatedBy(oEmp);
                vendor.setUpdatedBy(oEmp);
                vendor.setIsActive(1);
                vendorService.insertVendor(vendor);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                vendor.setUpdatedBy(oEmp);
                vendorService.updateVendor(vendor);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllVendors();
        return SUCCESS;
    }

    // To get Particular Vendor of an Employee
    @SkipValidation
    public String vendorView() {
        if ((vendor != null) && (vendor.getVendorId() != null)) {
            vendor = vendorService.getVendor(vendor.getVendorId());
        }
        return SUCCESS;
    }

    // To delete particular EmployeeStatus data
    @SkipValidation
    public String deleteVendor() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        vendor.setUpdatedBy(oEmp);

        vendorService.deleteVendor(vendor);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllVendors();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String vendorSearchForm() {
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String vendorSearchResult() {
        vendorList = vendorService.vendorSearchResult(vendor);
        for (int i = 0; i < vendorList.size(); i++) {
            vendorName = vendorList.get(i).getVendorName().toString();
            if (vendorName.equals("Not Applicable")) {
                vendorList.remove(i);
            }
        }
        return SUCCESS;
    }

    public void setVendor(VendorVO vendor) {
        this.vendor = vendor;
    }

    public VendorVO getVendor() {
        return vendor;
    }

    public void setVendorList(List<VendorVO> vendorList) {
        this.vendorList = vendorList;
    }

    public List<VendorVO> getVendorList() {
        return vendorList;
    }

    public void setVendorService(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public VendorService getVendorService() {
        return vendorService;
    }

    public void setVenObj(VendorVO venObj) {
        this.venObj = venObj;
    }

    public VendorVO getVenObj() {
        return venObj;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

}
