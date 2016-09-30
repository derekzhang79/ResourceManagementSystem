<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_essUserView_div_id">
	<div class="submenu_bg">
		<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
		<s:url var="remoteUserForm" value="/setUpEmpUser.action">
			<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
		</s:url>
		<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
		<s:url var="remoteUserView" value="/getEmployeeAllUser.action">
			<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
		</s:url>
		 <s:if test="#session.USER_ADD==true">
		    <sj:a href="%{remoteUserForm}" indicator="indicatorUserList" targets="submenu_essUserView_div_id" cssClass="link"><s:text name="label.header.user.add"/></sj:a> |
		</s:if>
		<s:if test="#session.USER_VIEW==true">
			<sj:a href="%{remoteUserView}" indicator="indicatorUserList" targets="submenu_essUserView_div_id" cssClass="link"><s:text name="label.header.user.view"/></sj:a>
		</s:if>
	</div>		
<br />		


<img id="indicatorUserList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
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
				<s:text name="label.title.user.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.user.hcmouserId" /></td>
				<td class="employeedisplaytd"><s:property value="user.hcmouserId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="user.empIdObj.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.user.userName" /></td>
				<td class="employeedisplaytd"><s:property value="user.userName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="user.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="user.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="user.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="user.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="user.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>