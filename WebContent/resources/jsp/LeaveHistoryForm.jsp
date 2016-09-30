<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<center>
<div class="informationMessageSingle"><li><span><s:if test="lhist==null || lhist.leaveHistoryId == null">
	<s:text name="label.leaveHistory.add" />
</s:if> <s:else>
	<s:text name="label.leaveHistory.edit" />
</s:else></span></li></div>
<jsp:include page="common/messages.jsp" flush="true"/>
<s:form>
	<table align="center" class="borderAll">

		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.date" /></td>
			<td><s:textfield name="lhist.leaveDate" size="30" /><s:text name="label.date.format"/></td>
			<td></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveLengthHrs" /></td>
			<td><s:textfield name="lhist.leaveLengthHours" size="30" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveLengthDays" /></td>
			<td><s:textfield name="lhist.leaveLengthDays" size="30" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveStatus" /></td>
			<td><s:select label="Select" name="lhist.leaveStatus"
				list="#{'Approved':'Approved','Rejected':'Rejected'}" /></td>

		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveComments" /></td>
			<td><s:textfield name="lhist.leaveComments" size="30" /></td>
		</tr>

		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveType" /></td>
			<td><s:select name="lhist.leaveTypeIdObj.leaveTypeId"
				list="#session.leaveTypeIdObj" listKey="leaveTypeId"
				listValue="leaveTypeName" /></td>

		</tr>


		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveStartTime" /></td>
			<td><s:textfield name="lhist.startTime" size="30" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.leaveh.leaveEndTime" /></td>
			<td><s:textfield name="lhist.endTime" size="30" /></td>
		</tr>

		<s:hidden name="lhist.hcmoLeaveHistoryId" />
	</table>
	<br />
	<table>
		<tr>
			<td><s:submit action="insertOrUpdateLeaveHistory" key="button.label.submit" cssClass="submitbutton117" /></td>
			<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
		</tr>
	</table>
</s:form></center>