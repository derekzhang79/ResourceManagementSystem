<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LeaveQuotaViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEQUOTA_ADD == true">
			<sj:a href="setUpEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaViewId_div" indicator="indicatorSubMenuLeaveQuotaViewId_div" cssClass="link"><s:text name="MTIAddLeaveQuota" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEQUOTA_VIEW == true">
			<sj:a href="getAllEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaViewId_div" indicator="indicatorSubMenuLeaveQuotaViewId_div" cssClass="link"><s:text name="MTIViewLeaveQuota"/></sj:a> |
			<sj:a href="leaveQuotaSearchForm.action" targets="submenu_LeaveQuotaViewId_div" indicator="indicatorSubMenuLeaveQuotaViewId_div" cssClass="link"><s:text name="MTISearchLeaveQuota"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuLeaveQuotaViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
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
					<s:text name="label.title.empLeaveQuota.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
	        <tr>
				<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.leaveQuotaId" /></td>
				<td class="employeedisplaytd"><s:property value="empLeaveQuota.empLeaveQuotaId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="empLeaveQuota.empIdObj.empFullName"/></td>
			</tr>
			<s:if test="#session.LEAVEQUOTA_LEAVETYPE_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.leaveType.leaveType" /></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.leaveTypeIdObj.leaveTypeName"/></td>
				</tr>
			</s:if>
			<s:if test="#session.LEAVEQUOTA_YEAR_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.year"/></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.year"/></td>
				</tr>
			</s:if>
			<s:if test="#session.LEAVEQUOTA_PRELEAVECARRYFORWARD==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.preLeaveCarryForward" /></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.previousLeaveCarryDays"/></td>
				</tr>
			</s:if>
			<s:if test="#session.LEAVEQUOTA_EMPLEAVEPENDING==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.carryForward" /></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.empLeavePending"/></td>
				</tr>
			</s:if>
			<s:if test="#session.LEAVEQUOTA_LEAVEALLOTTEDDAYS_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.daysAlloted" /></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.leaveAllottedDays"/></td>
				</tr>
			</s:if>
			<s:if test="#session.LEAVEQUOTA_EMPLEAVEREQUEST==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.leaveTaken" /></td>
					<td class="employeedisplaytd"><s:property value="empLeaveQuota.empLeaveRequest"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="empLeaveQuota.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
		   </tr>
		   <tr>
		   		<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="empLeaveQuota.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="empLeaveQuota.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="empLeaveQuota.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="empLeaveQuota.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
 </s:form>
</div>