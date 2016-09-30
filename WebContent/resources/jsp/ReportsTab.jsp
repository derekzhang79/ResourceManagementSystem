<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.header.reports.module"/>
			</td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="Reports" class="vidbox2" href="http://www.youtube.com/watch?v=MWCsb5U5UK0"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
	<s:url var="getSetUpEmployeeReportFormTab" action="showEmployeeReports"></s:url>
	<s:url var="getSetUpBroadcastReportFormTab" action="showBroadcastReports"></s:url>
	<s:url var="getSetUpBirthdayReportFormTab" action="showBirthdayReports"></s:url>
	<s:url var="getSetUpClientProjectReportFormTab" action="showProjectReports"></s:url>
	<s:url var="getSetUpPayStubReportFormTab" action="showPayStubReports"></s:url>
	<s:url var="getSetUpBirthdayReportTOReportFormTab" action="showBirthdayReports"></s:url>
	<s:url var="getSetUpLeaveReportFormTab" action="showLeaveReports"></s:url>
	<s:url var="getSetUpExpenseReportFormTab" action="showExpensesHistReports"></s:url>
	<s:url var="getSetUpTimesheetReportFormTab" action="showTimeSheetReports"></s:url>
	<s:url var="getSetUpTargetAndGoalReportFormTab" action="showTargetAndGoalReports"></s:url>

	<sj:tabbedpanel id="menuReportsTabbedpannel" animate="true">
		<sj:tab id="EmployeeReportMainTab" label="Employee" href="%{getSetUpEmployeeReportFormTab}" />
		<sj:tab id="BroadcastReportMainTab" label="Broadcast" href="%{getSetUpBroadcastReportFormTab}" />
		
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			<sj:tab id="BirthdayReportMainTab" label="Birthday" href="%{getSetUpBirthdayReportFormTab}" />
			<sj:tab id="ClientProjectReportMainTab" label="Customer/Project" href="%{getSetUpClientProjectReportFormTab}" />
			<sj:tab id="PayStubReportMainTab" label="PayStub" href="%{getSetUpPayStubReportFormTab}" />
		</s:if>
		<s:elseif test="#session.EMPLOYEE_REPORTING_TO == 'REPORTING-EMPLOYEE'">
			<sj:tab id="BirthdayReportTOReportMainTab" label="Birthday" href="%{getSetUpBirthdayReportTOReportFormTab}" />
		</s:elseif>
		
		<sj:tab id="LeaveReportMainTab" label="Leave" href="%{getSetUpLeaveReportFormTab}" />
		<sj:tab id="ExpenseReportMainTab" label="Expense" href="%{getSetUpExpenseReportFormTab}" />
		<sj:tab id="TimesheetReportMainTab" label="Timesheet" href="%{getSetUpTimesheetReportFormTab}" />
		<sj:tab id="TargetAndGoalReportMainTab" label="Target" href="%{getSetUpTargetAndGoalReportFormTab}" />
	</sj:tabbedpanel>
	
 <script>
	//Pop-up for Video in modules
	jQuery(document).ready(function() {
		try{
   			jQuery(".vidbox2").jqvideobox({
   			'width' : 600,
   			'height' : 400,
    		'getimage' : false,
   			'navigation' : true
   			});	
  		}catch(e){
  
  		 }
	}); 
 </script>
	