<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:set name="OrgChartEmployeeId" value="orgChartEmployeeID"></s:set>
		
		<s:url var="orgChartEmployeeLeaveApproverNewTab" action="orgChartEmployeeLeaveApproverNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartEmployeeLeaveAppSubEmployeeNewTab" action="orgChartEmployeeLeaveAppSubEmployeeNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		
		<sj:tabbedpanel id="orgChartLeaveApproverNewTabbedpannel" animate="true">
			<sj:tab id="OrgChartLeaveApproverNewSubTab" key="Approver" href="%{orgChartEmployeeLeaveApproverNewTab}"/>
			<s:if test="leaveAppDis!=null || leaveSelfApp!=null">
				<sj:tab id="OrgChartLeaveApproverSubEmployeeNewSubTab" key="Sub Employee" href="%{orgChartEmployeeLeaveAppSubEmployeeNewTab}"/>
			</s:if>
		</sj:tabbedpanel>