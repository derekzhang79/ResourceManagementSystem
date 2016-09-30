<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:set name="OrgChartEmployeeId" value="orgChartEmployeeID"></s:set>
		<s:url var="orgChartEmployeeLeaveQuotaNewTab" action="orgChartEmployeeLeaveQuotaNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartSubEmployeeLeaveQuotaNewTab" action="orgChartSubEmployeeLeaveQuotaNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		
		<sj:tabbedpanel id="orgChartMainLeaveQuotaNewTabbedpannel" animate="true">
			<sj:tab id="OrgChartLeaveQuotaNewTab" key="MTLeaveQuota" href="%{orgChartEmployeeLeaveQuotaNewTab}"/>
			<s:if test="leaveAppDis!=null || leaveSelfApp!=null">
				<sj:tab id="OrgChartLeaveQuotaSubEmployeeNewTab" key="MTISubEmployee" href="%{orgChartSubEmployeeLeaveQuotaNewTab}"/>
			</s:if>
		</sj:tabbedpanel>