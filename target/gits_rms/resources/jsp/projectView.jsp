<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectViewId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECT_ADD == true">
		<sj:a href="setUpProject.action" targets="submenu_ProjectViewId_div" indicator="indicatorSubMenuProjectViewId_div" cssClass="link"><s:text name="MTIAddProject" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECT_VIEW == true">
		<sj:a href="getAllProjects.action" targets="submenu_ProjectViewId_div" indicator="indicatorSubMenuProjectViewId_div" cssClass="link"><s:text name="MTIViewProject"/></sj:a> |
		<sj:a href="projectsSearchForm.action" targets="submenu_ProjectViewId_div" indicator="indicatorSubMenuProjectViewId_div" cssClass="link"><s:text name="MTISearchProject"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
		                 <td class="formtitle">
							<s:text name="label.title.project.view" />
		                  </td>
	                </tr>
   			<tr>
               	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.project.projectId" /></td>
				<td class="employeedisplaytd"><s:property value="proj.projectId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.projActivity.projectOwner" /></td>
				<td class="employeedisplaytd"><s:property value="proj.empIdObj.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.customer.name" /></td>
				<td class="employeedisplaytd"><s:property value="proj.customerId.customerName"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.project.projectName" /></td>
				<td class="employeedisplaytd"><s:property value="proj.projectName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.project.estimatedHours" /></td>
				<td class="employeedisplaytd"><s:property value="proj.estimatedHours"/></td>
			</tr>			
			<tr>
				<td class="inputtext"><s:text name="label.header.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="proj.projectDesc"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="proj.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="proj.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="proj.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			 <tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="proj.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="proj.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
			
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>