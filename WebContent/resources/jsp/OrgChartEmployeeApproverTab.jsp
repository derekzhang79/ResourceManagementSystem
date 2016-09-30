<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<div id="submenu_OrgNewEmployeesTab_div">
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
    	<s:set name="OrgChartEmployeeId" value="employee.employeeId"></s:set>
    	
    	<s:url var="orgChartLeaveApproverNewTab" action="orgChartLeaveApproverNewTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartExpenseApproverNewTab" action="orgChartExpenseApproverNewTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartTimesheetApproverNewTab" action="orgChartTimesheetApproverNewTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
    	
    	<sj:tabbedpanel id="menuOrgChartApproverNewTabbedpannel" animate="true">
    		<sj:tab id="OrgChartLeaveApproverNewMainTab" key="MTLeaveApprover" href="%{orgChartLeaveApproverNewTab}"/>
    		<sj:tab id="OrgChartExpenseApproverNewMainTab" key="MTExpenseApprover" href="%{orgChartExpenseApproverNewTab}"/>
    		<sj:tab id="OrgChartTimeSheetApproverNewMainTab" key="MTTimeSheetApprover" href="%{orgChartTimesheetApproverNewTab}"/>
    	</sj:tabbedpanel>
    	
	</div>