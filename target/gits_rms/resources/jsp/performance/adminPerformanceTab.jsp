<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:url var="getEmployeeAnsweredMainTab" action="employeePerformanceTab">
		<s:param name="employeeId" value="employeeId"></s:param>
	</s:url>
	<s:url var="getAppEmployeeAnsweredTab" action="appEmployeePerformanceTab">
		<s:param name="employeeId" value="employeeId"></s:param>
	</s:url>
	<s:url var="getPeerEmployeeAnsweredTab" action="peerEmployeePerformanceTab">
		<s:param name="employeeId" value="employeeId"></s:param>
	</s:url>
	
	<sj:tabbedpanel id="adminReviewTab" animate="true">
		
		<sj:tab id="getEmployeeAnsweredMainTab" key="MTEmployees" href="%{getEmployeeAnsweredMainTab}" />
		
		<s:if test="#session.ADMIN_APPROVINGEMPLOYEE=='APPROVINGEMPLOYEE'">
			<sj:tab id="getAppEmployeeAnsweredTab" key="MTIApprovingEmployee" href="%{getAppEmployeeAnsweredTab}" />
		</s:if>
		
		<s:if test="#session.ADMIN_PEEREMPLOYEE=='PEEREMPLOYEE'">
			<sj:tab id="getPeerEmployeeAnsweredTab" key="MTIPeerEmployee" href="%{getPeerEmployeeAnsweredTab}" />
		</s:if>
		
	</sj:tabbedpanel>