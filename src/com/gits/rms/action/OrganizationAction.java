
package com.gits.rms.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.OrganizationDaoService;
import com.gits.rms.service.OrganizationService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmpLocationHistoryVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.LeaveTypeVO;
import com.gits.rms.vo.OrganizationVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.RoleVO;

public class OrganizationAction extends ActionSupport {
    private static final long serialVersionUID = 5222338093397302019L;
    private OrganizationService orgService = new OrganizationDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private OrganizationVO org;
    private List<OrganizationVO> orgList;
    private List<ProjectVO> proList;
    private List<LeaveTypeVO> leaveTypeList;
    private List<RoleVO> roleList;
    private List<EmployeesVO> empList;
    private List<EmpLocationHistoryVO> empLocHistList;

    // To get All Type of Organization List
    @SkipValidation
    public String getAllOrganization() {
        OrganizationVO newOrg = new OrganizationVO();
        orgList = orgService.getAllOrganization();

        for (Iterator<OrganizationVO> it = orgList.iterator(); it.hasNext();) {
            newOrg = it.next();
            if (newOrg.getParentOrgId() != null) {
                setParentOrgValue(newOrg);
            }
        }
        return SUCCESS;
    }

    // Organization Search Page
    @SkipValidation
    public String organizationSearchForm() {
        return SUCCESS;
    }

    // Organization Search Result
    @SkipValidation
    public String organizationSearchResult() {
        OrganizationVO newOrg = new OrganizationVO();
        orgList = orgService.organizationSearchResult(org);
        for (Iterator<OrganizationVO> it = orgList.iterator(); it.hasNext();) {
            newOrg = it.next();
            if (newOrg.getParentOrgId() != null) {
                setParentOrgValue(newOrg);
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Organization it shows blank Form to enter New Data
    @SkipValidation
    public String setUpOrganization() {
        if ((org != null) && (org.getOrgId() != null)) {
            org = orgService.getOrganization(org.getOrgId());
        }
        return SUCCESS;
    }

    // To get Particular Organization Data
    @SkipValidation
    public String organizationView() {
        if ((org != null) && (org.getOrgId() != null)) {
            org = orgService.getOrganization(org.getOrgId());
        }
        if (org.getParentOrgId() != null) {
            setParentOrgValue(org);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Organization or
    // update particular Organization Data
    public String insertOrUpdateOrganization() {
        try {
            if (org.getOrgId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                org.setCreated(DateUtils.getCurrentDateTime());
                org.setCreatedBy(oEmp);
                org.setUpdatedBy(oEmp);
                org.setIsActive(1);
                orgService.insertOrganization(org);
                addActionMessage(getText("Added Successfully"));

                // Help information Messages
                session.put("HELP_INFORMATION_MESSAGE", getText("label.header.org.msg.manageLookUp"));

            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                org.setUpdatedBy(oEmp);
                orgService.updateOrganization(org);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllOrganizationName();
        return SUCCESS;
    }

    // To delete particular Organization
    @SkipValidation
    public String deleteOrganization() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        org.setUpdatedBy(oEmp);
        org = orgService.getOrganization(org.getOrgId());

        // To check that the Organization is being used in the Parent
        // Organization.If Yes then we are not allowing the Organization to get
        // delete.
        orgList = orgService.checkOrganizationInOrganization(org);
        if (!(orgList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteParentOrganization"));
            return SUCCESS;
        }

        // To check that the Organization is being used in the Project.If Yes
        // then we are not allowing the Organization to get delete.
        proList = orgService.checkOrganizationInProject(org);
        if (!(proList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteProject"));
            return SUCCESS;
        }

        // To check that the Organization is being used in the Leave Type.If Yes
        // then we are not allowing the Organization to get delete.
        leaveTypeList = orgService.checkOrganizationInLeaveType(org);
        if (!(leaveTypeList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteLeaveType"));
            return SUCCESS;
        }

        // To check that the Organization is being used in the Role.If Yes then
        // we are not allowing the Organization to get delete.
        roleList = orgService.checkOrganizationInRole(org);
        if (!(roleList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteRole"));
            return SUCCESS;
        }

        // To check that the Organization is being used in the Employees.If Yes
        // then we are not allowing the Organization to get delete.
        empList = orgService.checkOrganizationInEmployees(org);
        if (!(empList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteEmployees"));
            return SUCCESS;
        }

        // To check that the Organization is being used in the Employee Location
        // History.If Yes then we are not allowing the Organization to get
        // delete.
        empLocHistList = orgService.checkOrganizationInEmpLocHistory(org);
        if (!(empLocHistList.isEmpty())) {
            addActionError(getText("label.header.org.msg.deleteEmpLocHist"));
            return SUCCESS;
        }
        orgService.deleteOrganization(org);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllOrganizationName();
        return SUCCESS;
    }

    public void setParentOrgValue(OrganizationVO org) {
        OrganizationVO orgVo = new OrganizationVO();
        orgVo = orgService.getOrganization(org.getParentOrgId());
        org.setParentOrgValue(orgVo.getOrgName());
    }

    public OrganizationVO getOrg() {
        return org;
    }

    public void setOrg(OrganizationVO org) {
        this.org = org;
    }

    public List<OrganizationVO> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<OrganizationVO> orgList) {
        this.orgList = orgList;
    }

}