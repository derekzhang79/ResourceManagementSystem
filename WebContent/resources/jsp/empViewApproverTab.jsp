<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:set name="UniqueEmployeeId" value="essEmployeeID"></s:set>
		<s:url var="essExpenseApproverEmpTab" action="getEmployeeAllExpApprover">
			<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="essLeaveApproverEmpTab" action="getEmployeeAllLeaveApprover">
			<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="essTimesheetApproverEmpTab" action="getEmployeeAllTimeSheetApprover">
			<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		
		<sj:tabbedpanel id="remoteESSApproverMainTab" animate="true">
			<sj:tab id="essExpenseApproverTab" key="MTExpenseApprover" href="%{essExpenseApproverEmpTab}"/>
			<sj:tab id="essLeaveApproverTab" key="MTLeaveApprover" href="%{essLeaveApproverEmpTab}"/>
			<sj:tab id="essTimesheetApproverTab" key="MTTimeSheetApprover" href="%{essTimesheetApproverEmpTab}"/>
		</sj:tabbedpanel>