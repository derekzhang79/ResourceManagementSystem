<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TsProjectAssigned_div">
		<div class="submenu_bg">
			<sj:a href="setUpTsProjectAssigned.action" targets="submenu_TsProjectAssigned_div" indicator="indicatorSubMenuTsProjectAssignedViewId_div" cssClass="link"><s:text name="MTIAddAssignProject" /></sj:a> |
				<sj:a href="getAllTsProjectAssigned.action" targets="submenu_TsProjectAssigned_div" indicator="indicatorSubMenuTsProjectAssignedViewId_div" cssClass="link"><s:text name="MTIViewAssignProject"/></sj:a>|
				<sj:a href="tsProjectAssignSearchForm.action" targets="submenu_TsProjectAssigned_div" indicator="indicatorSubMenuTsProjectAssignedViewId_div" cssClass="link"><s:text name="MTISearchAssignProject"/></sj:a>
		</div>
		<br/>
		<img id="indicatorSubMenuTsProjectAssignedViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.assignProject.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
	        <tr>
				<td class="inputtext"><s:text name="label.header.assignProjectId" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.projectAssignEmpId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.employeeName.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.project.projectName" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.projectName.projectName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.projectActivity.name" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.projectActivityId.activityName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.startDate" /></td>
				<td class="employeedisplaytd"><s:date name="tsProjAssigned.projectStartDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.enddate" /></td>
				<td class="employeedisplaytd"><s:date name="tsProjAssigned.projectEndDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="tsProjAssigned.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="tsProjAssigned.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="tsProjAssigned.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="tsProjAssigned.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
</s:form>
</div>