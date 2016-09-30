<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationTypeViewId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATIONTYPE_ADD == true">
		<sj:a href="setUpOrganizationType.action" targets="submenu_OrganizationTypeViewId_div" indicator="indicatorSubMenuOrganizationTypeViewId_div" cssClass="link"><s:text name="MTIAddOrganizationType" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATIONTYPE_VIEW == true">
		<sj:a href="getAllOrganizationType.action" targets="submenu_OrganizationTypeViewId_div" indicator="indicatorSubMenuOrganizationTypeViewId_div" cssClass="link"><s:text name="MTIViewOrganizationType"/></sj:a> |
		<sj:a href="organizationTypeSearchForm.action" targets="submenu_OrganizationTypeViewId_div" indicator="indicatorSubMenuOrganizationTypeViewId_div" cssClass="link"><s:text name="MTISearchOrganizationType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationTypeViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
								<s:text name="label.title.orgType.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.orgType.hcmoorgTypeId" /></td>
				<td class="employeedisplaytd"><s:property value="orgtype.hcmoorgTypeId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.orgType.name" /></td>
				<td class="employeedisplaytd"><s:property value="orgtype.orgTypeName"/></td>
			</tr>		
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="orgtype.description"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="orgtype.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="orgtype.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="orgtype.updated" format="%{getText('label.date.simpleDateFormatWithTime')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="orgtype.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="orgtype.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>