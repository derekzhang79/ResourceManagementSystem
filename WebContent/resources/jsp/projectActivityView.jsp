<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectActivityViewId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECTACTIVITY_ADD == true">
		<sj:a href="setUpProjectActivity.action" targets="submenu_ProjectActivityViewId_div" indicator="indicatorSubMenuProjectActivityViewId_div" cssClass="link"><s:text name="MTIAddProjectActivity" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECTACTIVITY_VIEW == true">
		<sj:a href="getAllProjectActivity.action" targets="submenu_ProjectActivityViewId_div" indicator="indicatorSubMenuProjectActivityViewId_div" cssClass="link"><s:text name="MTIViewProjectActivity"/></sj:a> |
		<sj:a href="projectActivitySearcForm.action" targets="submenu_ProjectActivityViewId_div" indicator="indicatorSubMenuProjectActivityViewId_div" cssClass="link"><s:text name="MTISearchProjectActivity"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectActivityViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				 <s:text name="label.title.projectActivity.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.projActivity.projectActivityId" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.projectActivityId"/></td>
			</tr>
			<tr>

				<td class="inputtext"><s:text name="label.header.projActivity.projectOwner" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.empIdObj.empFullName"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.project.projectName" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.proObj.projectName"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.projActivity.projectActivity" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.activityName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.projectActivity.estHours" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.estimatedHours"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.notes" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.activityNotes"/></td>
			</tr>			
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="proActivity.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="proActivity.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="proActivity.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="proActivity.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
			</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>