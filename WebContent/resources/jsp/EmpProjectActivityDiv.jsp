<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gits.rms.action.utils.ClientConstants"%>
<% request.setAttribute("APPL_EMP_PROJECT_ACTIVITY_LIST", ClientConstants.getApplicationConstant("APPL_EMP_PROJECT_ACTIVITY_LIST")); %>
<s:select 
	headerKey=""
	headerValue="-- Please Select --"
	list="#request.APPL_EMP_PROJECT_ACTIVITY_LIST"
	listKey="projectActivityId"
	listValue="activityName"
	name="empTAGObj.projectActivityName.projectActivityId"
	cssClass="employeeselect" 
	onchange="getEmployeeTargetAndGoal(this);"
    />