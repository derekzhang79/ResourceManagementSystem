<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.gits.rms.utils.DateUtils"%>
<%@page import="java.util.Calendar"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					
					<tr>
						<td align="center" valign="top">
							<!--centerpart-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	
		<!-- Main Content -->
		<td align="center" valign="top">
		<%-- 
			<s:text name="application.themes"></s:text> 
			<s:a href="ApplyTheme.action?theme=blue">Blue</s:a>
			<s:a href="ApplyTheme.action?theme=black">Black</s:a>
		--%>
			<img id="indicatorMainMenuTSoptionsId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
			<div id="mainmenuTSOptionsId_div">
				
	<table width="300" align="center">
	<tr>
		<th>Timesheet</th>
	</tr>
 	<s:set name="EMPLOYEE_ID" value="#session.EMPLOYEE_ID"></s:set>	
	<s:if test="#session.TIMESHEET_APPROVER == 'BOTH'">
		<tr>
			<s:url var="TimeSheetUpdate" action="TimeSheetUpdate">
				<s:param name="consultant_id" value="#session.EMPLOYEE_ID"></s:param>
			</s:url>
			<td><sj:a targets="mainmenuTSOptionsId_div" href="TimeSheetUpdate.action" > Enter Timesheet</sj:a></td>
		</tr>
		<tr>
			<td><sj:a targets="mainmenuTSOptionsId_div" href="ListTimeSheet.action" > View Timesheet</sj:a></td>
		</tr>
		<tr>
			<td><s:a href="AppointConsultant.action" cssStyle="text-decoration:none"> Assign Project </s:a></td>
		</tr>
	</s:if>
	<s:if test="#session.TIMESHEET_APPROVER == 'NON-APPROVER'">
		<tr>
			
			<td>
				<sj:a targets="mainmenuTSOptionsId_div" href="TimeSheetUpdate.action"> Enter Timesheet</sj:a>
			</td>
			
		</tr>
	</s:if>
		<tr>
			
			<td>
				<sj:a targets="mainmenuTSOptionsId_div" href="fileUploadListAction.action"> File Upload</sj:a>
			</td>
			
		</tr>
	</table>
	</div>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>