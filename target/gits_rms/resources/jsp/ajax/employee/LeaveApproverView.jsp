<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_essLeaveApproverView_div_id">
   <div class="submenu_bg">
	<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteLeaveApprForm" value="/setUpEmpInsertOrUpdateLeaveApprover.action">
		<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteLeaveApprView" value="/getEmployeeAllLeaveApprover.action">
		<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
	</s:url>
	
		<s:if test="#session.LEAVEAPPROVER_ADD==true">
			<sj:a href="%{remoteLeaveApprForm}" indicator="indicatorLeaveApprList" targets="submenu_essLeaveApproverView_div_id" cssClass="link"><s:text name="label.header.leaveapprover.add"/></sj:a> |
		</s:if>
			<s:if test="#session.LEAVEAPPROVER_VIEW==true">
			<sj:a href="%{remoteLeaveApprView}" indicator="indicatorLeaveApprList" targets="submenu_essLeaveApproverView_div_id" cssClass="link"><s:text name="label.header.leaveapprover.view"/></sj:a>
		</s:if>
	</div>		

<br />

<img id="indicatorLeaveApprList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form>
		<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.leaveapprover.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
	        <tr>
				<td class="inputtext"><s:text name="label.header.leaveapprover.leaveApproverId" /></td>
				<td class="employeedisplaytd"><s:property value="leaveApprover.hcmoLeaveApproverId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.leaveapprover.EmployeeName" /></td>
				<td class="employeedisplaytd"><s:property value="leaveApprover.hcmoEmployeeId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.leaveapprover.leaveApprovingEmployee" /></td>	
				<td class="employeedisplaytd"><s:property value="leaveApprover.hcmoApprovingEmpId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="leaveApprover.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="leaveApprover.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="leaveApprover.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="leaveApprover.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="leaveApprover.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>	