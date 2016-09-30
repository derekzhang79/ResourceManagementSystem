<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LeaveTypeViewId_div">
<div class="submenu_bg">
	<s:if test="#session.LEAVETYPE_ADD == true">
		<sj:a href="setUpLeaveType.action" targets="submenu_LeaveTypeViewId_div" indicator="indicatorSubMenuLeaveTypeViewId_div" cssClass="link"><s:text name="MTIAddLeaveType" /></sj:a> |
	</s:if>
	<s:if test="#session.LEAVETYPE_VIEW == true">
		<sj:a href="getAllLeaveType.action" targets="submenu_LeaveTypeViewId_div" indicator="indicatorSubMenuLeaveTypeViewId_div" cssClass="link"><s:text name="MTIViewLeaveType"/></sj:a> |
				<sj:a href="leaveTypeSearchForm.action" targets="submenu_LeaveTypeViewId_div" indicator="indicatorSubMenuLeaveTypeViewId_div" cssClass="link"><s:text name="MTISearchLeaveType"/></sj:a>
	</s:if>

</div>
<br/>
<img id="indicatorSubMenuLeaveTypeViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.leaveType.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
	       <tr>
				<td class="inputtext"><s:text name="label.header.leaveType.leaveTypeId"/></td>
	            <td class="employeedisplaytd"><s:property value="leave.leaveTypeId"/></td>
			</tr>     
			<tr>
				<td class="inputtext"><s:text name="label.header.leaveType.leaveType" /></td>
				<td class="employeedisplaytd"><s:property value="leave.leaveTypeName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="leave.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="leave.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="leave.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="leave.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="leave.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>