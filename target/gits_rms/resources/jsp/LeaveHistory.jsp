<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<br />

<table align="center" class="borderAll">
	<tr>
		<th><s:text name="label.form.fields.common.empName" /></th>
		<th><s:text name="label.leaveh.leaveType" /></th>
		<th><s:text name="label.leaveh.startDate" /></th>
		<th><s:text name="label.leaveh.endDate" /></th>
<!--	<th><s:text name="label.leaveh.leaveLengthHrs1" /></th>-->
		<th><s:text name="label.leaveh.leaveLengthDays1" /></th>
		<th><s:text name="label.leaveh.leaveStatus" /></th>
		<th><s:text name="label.leaveh.leaveComments" /></th>
<!--	<th><s:text name="label.leaveh.leaveStartTime1" /></th>
		<th><s:text name="label.leaveh.leaveEndTime1" /></th>-->
		<th>&nbsp;</th>
	</tr>

	<s:iterator value="lHistList" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		
			<td class="nowrap"><s:property value="empIdObj.empFirstName" /></td>
			<td class="nowrap"><s:property value="leaveTypeIdObj.leaveTypeName" /></td>
			<td class="nowrap"><s:property value="leaveDate" /></td>
			<td class="nowrap"><s:property value="leaveDate" /></td>
			<td class="nowrap"><s:property value="leaveLengthDays" /></td>
			<td class="nowrap"><s:property value="leaveStatus" /></td>
			<td class="nowrap"><s:property value="leaveComments" /></td>
		<!--<td class="nowrap"><s:property value="startTime" /></td>
			<td class="nowrap"><s:property value="endTime" /></td>
			--><!--
			<td class="nowrap">
			
			<s:url var="setUpLeaveHistory" action="setUpLeaveHistory">
				<s:param name="lhist.hcmoLeaveHistoryId" value="hcmoLeaveHistoryId" />
			</s:url> 
			<s:a href="%{setUpLeaveHistory}">Edit</s:a> &nbsp;&nbsp;&nbsp;
			 
			<s:url var="deleteLeaveHistory" action="deleteLeaveHistory">
				<s:param name="lhist.hcmoLeaveHistoryId" value="hcmoLeaveHistoryId" />
			</s:url>
			<s:a href="%{deleteLeaveHistory}">Delete</s:a></td>
			
		--></tr>
	</s:iterator>
</table>