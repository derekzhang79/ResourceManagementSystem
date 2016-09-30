
package com.gits.rms;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.gits.rms.action.utils.ClientConstants;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.vo.EmployeesVO;

public class SessionInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 4614410521339579694L;
    
    private Logger logger =  Logger.getLogger(SessionInterceptor.class);

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

    	logger.info("INSIDE SessionInterceptor");
        ActionContext ac = invocation.getInvocationContext();
        Map session = ac.getSession();

        // retrieve the login status from the session by key name.
        EmployeesVO oEmployees = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        // if the user object is non null, the user is logged in.
        logger.info("INSIDE SessionInterceptor oEmployees : " + oEmployees);
        if (oEmployees != null) {
            LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
            ServletContext context = ServletActionContext.getServletContext();
            logger.info("INSIDE SessionInterceptor load key values start");
            logger.info("INSIDE SessionInterceptor load key values context.getAttribute(ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST) : " + context.getAttribute(ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST)));
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_EMPLOYEE_LIST)) == null) {
                loadValues.getAllEmployeeName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_PROJECT_LIST)) == null) {
                loadValues.getAllProjectName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ROLE_LIST)) == null) {
                loadValues.getAllRoleName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_JOBTITLE_LIST)) == null) {
                loadValues.getAllJobTitleName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_NATIONALITY_LIST)) == null) {
                loadValues.getAllNationalityName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ETHNICRACE_LIST)) == null) {
                loadValues.getAllEthnicRaceName();
            }

            // Postal code has been changed to text field from dropdown
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ZIPCODE_LIST)) == null) {
                loadValues.getAllZipCode();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_COUNTRY_LIST)) == null) {
                loadValues.getAllCountry();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_CUSTOMER_LIST)) == null) {
                loadValues.getAllCustomerName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ORGANIZATION_LIST)) == null) {
                loadValues.getAllOrganizationName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_SALARYGRADE_LIST)) == null) {
                loadValues.getAllSalaryGradeName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_LOCATION_LIST)) == null) {
                loadValues.getAllLocationName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ORGANIZATIONTYPE_LIST)) == null) {
                loadValues.getAllOrgTypeName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVE_LIST)) == null) {
                loadValues.getAllLeaveName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_EXPENSESTYPE_LIST)) == null) {
                loadValues.getAllExpensesTypeName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_BENEFITS_YEAR_LIST)) == null) {
                loadValues.getAllBenefitsYear();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_CLIENT_LIST)) == null) {
                loadValues.getAllClients();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_VENDOR_LIST)) == null) {
                loadValues.getAllVendors();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.SESSION_EXPENSESAPPROVER_LIST)) == null) {
                loadValues.getAllExpAppName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.SESSION_EXPENSESEMPLOYEE_LIST)) == null) {
                loadValues.getAllExpEmpForApproverSearch();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_EMPSTATUS_LIST)) == null) {
                loadValues.getAllEmpStatus();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_DEPARTMENT_LIST)) == null) {
                loadValues.getAllDepartmentName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_TEAM_LIST)) == null) {
                loadValues.getAllTeamName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.TIMESHEET_CATEGORY)) == null) {
                loadValues.getAllTimesheetCategoryName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVEQUOTA_YEAR_LIST)) == null) {
                loadValues.getAllLeaveQuotaYear();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_SUBEMPLOYEE_LIST)) == null) {
                loadValues.getAllSubEmployeeForTimeSheet();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVEQUOTASUBEMPLOYEE_LIST)) == null) {
                loadValues.getAllSubEmployeeForLeaveQuota();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_LEAVESUBEMPLOYEE_LIST)) == null) {
                loadValues.getAllSubEmployeeForLeave();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_PROJECTACTIVITY_LIST)) == null) {
                loadValues.getAllProjectActivity();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_DEDUCTION_LIST)) == null) {
                loadValues.getAllDeductions();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.TIMESHEET_CATEGORYEMP)) == null) {
                loadValues.getAllTimesheetCategoryEmpName();
            }

            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_ACTIVITY_LIST)) == null) {
                loadValues.getProjectActivity();
            }
                  
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_CATEGORY_LIST)) == null) {
                loadValues.getAllCategoryName();
            }
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_SUB_CATEGORY_LIST)) == null) {
                loadValues.getAllSubCategoryName();
            }
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_QUESTIONBANK_LIST)) == null) {
                loadValues.getAllQuestionBankName();
            }
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_QUESTIONBANKGENERAL_INFO_LIST)) == null) {
                loadValues.getAllApproversSubEmployeeList();
            }
            if (context.getAttribute(ClientConstants.getClientContant(Constants.APPL_TARGETTYPE_LIST)) == null) {
                loadValues.getAllTargetType();
            }
            logger.info("INSIDE SessionInterceptor load key values end");
            return invocation.invoke();
        }

        return "notLoggedIn";
    }
}
