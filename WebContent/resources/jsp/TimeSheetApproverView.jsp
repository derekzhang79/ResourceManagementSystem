<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimeSheetApprover_div">
	<div class="submenu_bg">
		<s:if test="#session.TIMESHEETAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateTimeSheetApprover.action" targets="submenu_TimeSheetApprover_div" indicator="indicatorSubMenuTimeSheetApproverViewId_div" cssClass="link"><s:text name="MTIAddTimeSheetApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW == true">
			<sj:a href="getAllTimeSheetApprover.action" targets="submenu_TimeSheetApprover_div" indicator="indicatorSubMenuTimeSheetApproverViewId_div" cssClass="link"><s:text name="MTIViewTimeSheetApprover"/></sj:a> |
			<sj:a href="timeAppSearchForm.action" targets="submenu_TimeSheetApprover_div" indicator="indicatorSubMenuTimeSheetApproverViewId_div" cssClass="link"><s:text name="MTISearchTimeSheetApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetApproverViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.timesheetapprover.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
	        <tr>
				<td class="inputtext"><s:text name="label.header.timesheet.hcmoTimesheetId" /></td>
				<td class="employeedisplaytd"><s:property value="timeSheetApprover.hcmoApproverId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.timesheet.EmployeeName" /></td>
				<td class="employeedisplaytd"><s:property value="timeSheetApprover.hcmoEmployeeId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.timesheet.timesheetApprovingEmployee" /></td>
				<td class="employeedisplaytd"><s:property value="timeSheetApprover.hcmoApprovingEmpId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="timeSheetApprover.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="timeSheetApprover.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="timeSheetApprover.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="timeSheetApprover.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="timeSheetApprover.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>	