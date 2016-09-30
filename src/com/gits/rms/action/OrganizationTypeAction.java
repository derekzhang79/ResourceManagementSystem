
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.LocationDaoService;
import com.gits.rms.service.LocationService;
import com.gits.rms.service.OrganizationTypeDaoService;
import com.gits.rms.service.OrganizationTypeService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LocationVO;
import com.gits.rms.vo.OrganizationTypeVO;
import com.gits.rms.vo.OrganizationVO;

public class OrganizationTypeAction extends ActionSupport {
    private static final long serialVersionUID = -4798995319557291474L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private OrganizationTypeService orgtypeService = new OrganizationTypeDaoService();
    private LocationService locService = new LocationDaoService();
    private OrganizationTypeVO orgtype;
    private List<OrganizationTypeVO> orgtypeList;
    private List<LocationVO> location;
    private List<OrganizationVO> orgList;

    // To get All Type of OrganizationType List
    @SkipValidation
    public String getAllOrganizationType() {
        orgtypeList = orgtypeService.getAllOrganizationType();
        return SUCCESS;
    }

    // To get the Search Page
    @SkipValidation
    public String organizationTypeSearchForm() {
        return SUCCESS;
    }

    // Search Organization Type List
    @SkipValidation
    public String organizationTypeSearchResult() {
        orgtypeList = orgtypeService.organizationTypeSearchResult(orgtype);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // OrganizationType it shows blank Form to enter New Data
    @SkipValidation
    public String setUpOrganizationType() {
        if ((orgtype != null) && (orgtype.getHcmoorgTypeId() != null)) {
            orgtype = orgtypeService.getOrganizationType(orgtype.getHcmoorgTypeId());
        }
        return SUCCESS;
    }

    // To get Particular OrganizationType Data
    @SkipValidation
    public String organizationTypeView() {
        if ((orgtype != null) && (orgtype.getHcmoorgTypeId() != null)) {
            orgtype = orgtypeService.getOrganizationType(orgtype.getHcmoorgTypeId());
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new OrganizationType
    // or update particular OrganizationType Data
    public String insertOrUpdateOrganizationType() {
        try {
            if (orgtype.getHcmoorgTypeId() == null) {
                Map session = ActionContext.getContext().getSession();
                location = locService.getAllLocation();

                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                orgtype.setCreated(DateUtils.getCurrentDateTime());
                orgtype.setCreatedBy(oEmp);
                orgtype.setUpdatedBy(oEmp);
                orgtype.setIsActive(1);
                orgtypeService.insertOrganizationType(orgtype);
                addActionMessage(getText("Added Successfully"));

                // Help information Messages
                Boolean locationAddPriv = (Boolean) session.get("LOCATION_ADD");
                Boolean organizationAddPriv = (Boolean) session.get("ORGANIZATION_ADD");
                if ((location.size() == 0) && (locationAddPriv == true)) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.header.orgType.msg.location"));
                } else if (organizationAddPriv) {
                    session.put("HELP_INFORMATION_MESSAGE", getText("label.header.orgType.msg.Organization"));
                }
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                orgtype.setUpdatedBy(oEmp);
                orgtypeService.updateOrganizationType(orgtype);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllOrgTypeName();
        return SUCCESS;
    }

    // To delete particular OrganizationType
    @SkipValidation
    public String deleteOrganizationType() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        orgtype.setUpdatedBy(oEmp);

        // To check that the OrgType is being used in the Organization.If Yes
        // then we are not allowing the OrgType to get delete.
        orgList = orgtypeService.checkOrgTypeInOrganization(orgtype);
        if (!(orgList.isEmpty())) {
            addActionError(getText("label.header.orgType.msg.deleteOrganization"));
            return SUCCESS;
        }

        orgtypeService.deleteOrganizationType(orgtype);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllOrgTypeName();
        return SUCCESS;
    }

    public OrganizationTypeVO getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(OrganizationTypeVO orgtype) {
        this.orgtype = orgtype;
    }

    public List<OrganizationTypeVO> getOrgtypeList() {
        return orgtypeList;
    }

    public void setOrgtypeList(List<OrganizationTypeVO> orgtypeList) {
        this.orgtypeList = orgtypeList;
    }

}