<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_RoleViewId_div">
<div class="submenu_bg">
	<s:if test="#session.ROLE_ADD == true">
		<sj:a href="setUpRole.action" targets="submenu_RoleViewId_div" indicator="indicatorSubMenuRoleViewId_div" cssClass="link"><s:text name="MTIAddRole" /></sj:a> |
	</s:if>
	<s:if test="#session.ROLE_VIEW == true">
		<sj:a href="getAllRole.action" targets="submenu_RoleViewId_div" indicator="indicatorSubMenuRoleViewId_div" cssClass="link"><s:text name="MTIViewRole"/></sj:a> |
		<sj:a href="roleSearchForm.action" targets="submenu_RoleViewId_div" indicator="indicatorSubMenuRoleViewId_div" cssClass="link"><s:text name="MTISearchRole"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRoleViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
						<s:text name="label.title.role.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.role.hcmoRoleId" /></td>
				<td class="employeedisplaytd"> <s:property value="role.hcmoRoleId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.role.name" /></td>
				<td class="employeedisplaytd"> <s:property value="role.roleName"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="role.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="role.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="role.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="role.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="role.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>