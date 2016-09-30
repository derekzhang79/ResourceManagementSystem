<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:set name="OrgChartEmployeeId" value="orgChartEmployeeID"></s:set>
		
		<s:url var="orgChartEmployeeTimesheetApproverNewTab" action="orgChartEmployeeTimesheetApproverNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartSubEmployeeTimesheetsApproverNewTab" action="orgChartSubEmployeeTimesheetsApproverNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		
		<sj:tabbedpanel id="orgChartTimesheetApproverNewTabbedpannel" animate="true">
			<sj:tab id="OrgChartTimesheetApproverSubNewTab" key="Approver" href="%{orgChartEmployeeTimesheetApproverNewTab}"/>
			<s:if test="timeSheetSelfApp!=null || timeSheetAppDis!=null">
				<sj:tab id="OrgChartTimesheetApproverSubEmployeeNewSubTab" key="Sub Employee" href="%{orgChartSubEmployeeTimesheetsApproverNewTab}"/>
			</s:if>
		</sj:tabbedpanel>