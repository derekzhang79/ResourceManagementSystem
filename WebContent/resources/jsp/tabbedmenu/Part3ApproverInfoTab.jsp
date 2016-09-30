<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
		<s:url var="getTabExpenseApproverList" action="getAllExpApprover"/>
		<s:url var="getTabExpenseAccountantList" action="getAllExpAccountantApprover"/>
		<s:url var="getTabLeaveApproverList" action="getAllLeaveApprover"/>
		<s:url var="getTabTimesheetApproverList" action="getAllTimeSheetApprover"/>
		
		<sj:tabbedpanel id="remoteSubTabsApproverInfo" animate="true">
			
			<s:if test="#session.EXPENSESAPPROVER_ADD == true || #session.EXPENSESAPPROVER_VIEW == true">
				<sj:tab id="expenseApproverMainTab" key="MTExpenseApprover" href="%{getTabExpenseApproverList}"/>
			</s:if>
			
			<s:if test="#session.EXPENSESACCOUNTANT_ADD == true || #session.EXPENSESACCOUNTANT_VIEW == true">
				<sj:tab id="expenseAccountantMainTab" key="MTExpenseAccountantApprover" href="%{getTabExpenseAccountantList}"/>
			</s:if>
			
			<s:if test="#session.LEAVEAPPROVER_ADD == true || #session.LEAVEAPPROVER_VIEW == true">
				<sj:tab id="leaveApproverMainTab" key="MTLeaveApprover" href="%{getTabLeaveApproverList}"/>
			</s:if>
			
			<s:if test="#session.TIMESHEETAPPROVER_ADD == true || #session.TIMESHEETAPPROVER_VIEW == true">
				<sj:tab id="timeSheetApproverMainTab" key="MTTimeSheetApprover" href="%{getTabTimesheetApproverList}"/>
			</s:if>
			
		</sj:tabbedpanel>