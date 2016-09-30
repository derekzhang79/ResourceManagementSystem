<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gits.rms.action.utils.ClientConstants"%>

<s:actionmessage cssClass="successMessageSingle"/>
<s:actionerror cssClass="actionMessageSingle"/>

<s:if test="#session.HELP_INFORMATION_MESSAGE!=null">
	<ul class="helpinformation">
		<li>
			<span><s:property value="#session.HELP_INFORMATION_MESSAGE"/></span>
		</li>	
	</ul>
	   <%session.removeAttribute("HELP_INFORMATION_MESSAGE");%>
</s:if>
<%
   	request.setAttribute("APPL_EMPLOYEE_LIST", ClientConstants.getApplicationConstant("APPL_EMPLOYEE_LIST"));
	request.setAttribute("APPL_PROJECT_LIST", ClientConstants.getApplicationConstant("APPL_PROJECT_LIST"));
	request.setAttribute("APPL_ROLE_LIST", ClientConstants.getApplicationConstant("APPL_ROLE_LIST"));
	request.setAttribute("APPL_JOBTITLE_LIST", ClientConstants.getApplicationConstant("APPL_JOBTITLE_LIST"));
	request.setAttribute("APPL_NATIONALITY_LIST", ClientConstants.getApplicationConstant("APPL_NATIONALITY_LIST"));
	request.setAttribute("APPL_ETHNICRACE_LIST", ClientConstants.getApplicationConstant("APPL_ETHNICRACE_LIST"));
	request.setAttribute("APPL_ZIPCODE_LIST", ClientConstants.getApplicationConstant("APPL_ZIPCODE_LIST"));
	request.setAttribute("APPL_COUNTRY_LIST", ClientConstants.getApplicationConstant("APPL_COUNTRY_LIST"));
	request.setAttribute("APPL_CUSTOMER_LIST", ClientConstants.getApplicationConstant("APPL_CUSTOMER_LIST"));
	request.setAttribute("APPL_ORGANIZATION_LIST", ClientConstants.getApplicationConstant("APPL_ORGANIZATION_LIST"));
	request.setAttribute("APPL_SALARYGRADE_LIST", ClientConstants.getApplicationConstant("APPL_SALARYGRADE_LIST"));
	request.setAttribute("APPL_LOCATION_LIST", ClientConstants.getApplicationConstant("APPL_LOCATION_LIST"));
	request.setAttribute("APPL_ORGANIZATIONTYPE_LIST", ClientConstants.getApplicationConstant("APPL_ORGANIZATIONTYPE_LIST"));
	request.setAttribute("APPL_LEAVE_LIST", ClientConstants.getApplicationConstant("APPL_LEAVE_LIST"));
	request.setAttribute("APPL_EXPENSESTYPE_LIST", ClientConstants.getApplicationConstant("APPL_EXPENSESTYPE_LIST"));
	request.setAttribute("APPL_BENEFITS_YEAR_LIST", ClientConstants.getApplicationConstant("APPL_BENEFITS_YEAR_LIST"));
	request.setAttribute("APPL_CLIENT_LIST", ClientConstants.getApplicationConstant("APPL_CLIENT_LIST"));
	request.setAttribute("APPL_VENDOR_LIST", ClientConstants.getApplicationConstant("APPL_VENDOR_LIST"));
	/* request.setAttribute("SESSION_EXPENSESAPPROVER_LIST", ClientConstants.getApplicationConstant("SESSION_EXPENSESAPPROVER_LIST")); */
	/* request.setAttribute("SESSION_EXPENSESEMPLOYEE_LIST", ClientConstants.getApplicationConstant("SESSION_EXPENSESEMPLOYEE_LIST")); */
	request.setAttribute("APPL_EMPSTATUS_LIST", ClientConstants.getApplicationConstant("APPL_EMPSTATUS_LIST"));
	request.setAttribute("APPL_DEPARTMENT_LIST", ClientConstants.getApplicationConstant("APPL_DEPARTMENT_LIST"));
	request.setAttribute("APPL_TEAM_LIST", ClientConstants.getApplicationConstant("APPL_TEAM_LIST"));
	request.setAttribute("TIMESHEET_CATEGORY", ClientConstants.getApplicationConstant("TIMESHEET_CATEGORY"));
	request.setAttribute("APPL_LEAVEQUOTA_YEAR_LIST", ClientConstants.getApplicationConstant("APPL_LEAVEQUOTA_YEAR_LIST"));
	request.setAttribute("APPL_SUBEMPLOYEE_LIST", ClientConstants.getApplicationConstant("APPL_SUBEMPLOYEE_LIST"));
	request.setAttribute("APPL_LEAVEQUOTASUBEMPLOYEE_LIST", ClientConstants.getApplicationConstant("APPL_LEAVEQUOTASUBEMPLOYEE_LIST"));
	request.setAttribute("APPL_LEAVESUBEMPLOYEE_LIST", ClientConstants.getApplicationConstant("APPL_LEAVESUBEMPLOYEE_LIST"));
	request.setAttribute("APPL_PROJECTACTIVITY_LIST", ClientConstants.getApplicationConstant("APPL_PROJECTACTIVITY_LIST"));
	request.setAttribute("APPL_DEDUCTION_LIST", ClientConstants.getApplicationConstant("APPL_DEDUCTION_LIST"));
	request.setAttribute("TIMESHEET_CATEGORYEMP", ClientConstants.getApplicationConstant("TIMESHEET_CATEGORYEMP"));
	request.setAttribute("APPL_ACTIVITY_LIST", ClientConstants.getApplicationConstant("APPL_ACTIVITY_LIST"));
	request.setAttribute("APPL_CATEGORY_LIST", ClientConstants.getApplicationConstant("APPL_CATEGORY_LIST"));
	request.setAttribute("APPL_SUB_CATEGORY_LIST", ClientConstants.getApplicationConstant("APPL_SUB_CATEGORY_LIST"));
	request.setAttribute("APPL_QUESTIONBANK_LIST", ClientConstants.getApplicationConstant("APPL_QUESTIONBANK_LIST"));
	request.setAttribute("APPL_QUESTIONBANKGENERAL_INFO_LIST", ClientConstants.getApplicationConstant("APPL_QUESTIONBANKGENERAL_INFO_LIST"));
	request.setAttribute("APPL_TARGET_NAME_LIST", ClientConstants.getApplicationConstant("APPL_TARGET_NAME_LIST"));
	request.setAttribute("APPL_TARGET_TYPE_LIST", ClientConstants.getApplicationConstant("APPL_TARGET_TYPE_LIST"));
	request.setAttribute("APPL_GOALNAME_LIST", ClientConstants.getApplicationConstant("APPL_GOALNAME_LIST"));
	request.setAttribute("APPL_EMP_PROJECT_LIST", ClientConstants.getApplicationConstant("APPL_EMP_PROJECT_LIST"));
	request.setAttribute("APPL_EMP_PROJECT_ACTIVITY_LIST", ClientConstants.getApplicationConstant("APPL_EMP_PROJECT_ACTIVITY_LIST"));
	request.setAttribute("APPL_TARGETTYPE_LIST", ClientConstants.getApplicationConstant("APPL_TARGETTYPE_LIST"));
	request.setAttribute("APPL_KPI_ASSIGN_EMPLOYEE_LIST", ClientConstants.getApplicationConstant("APPL_KPI_ASSIGN_EMPLOYEE_LIST"));
%>