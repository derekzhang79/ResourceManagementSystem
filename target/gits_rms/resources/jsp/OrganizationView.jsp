<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationviewId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATION_ADD == true">
		<sj:a href="setUpOrganization.action" targets="submenu_OrganizationviewId_div" indicator="indicatorSubMenuOrganizationViewId_div" cssClass="link"><s:text name="MTIAddOrganization" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATION_VIEW == true">
		<sj:a href="getAllOrganization.action" targets="submenu_OrganizationviewId_div" indicator="indicatorSubMenuOrganizationViewId_div" cssClass="link"><s:text name="MTIViewOrganization"/></sj:a> |
	</s:if>
		<sj:a href="organizationSearchForm.action" targets="submenu_OrganizationviewId_div" indicator="indicatorSubMenuOrganizationViewId_div" cssClass="link"><s:text name="MTISearchOrganization"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuOrganizationViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
								<s:text name="label.title.organization.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.org.orgId" /></td>
				<td class="employeedisplaytd"><s:property value="org.orgId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.org.parentOrgId" /></td>
				<td class="employeedisplaytd"><s:property value="org.parentOrgValue"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.org.name" /></td>
				<td class="employeedisplaytd"><s:property value="org.orgName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.location.name" /></td>
				<td class="employeedisplaytd"><s:property value="org.location.locationName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.orgType.name" /></td>
				<td class="employeedisplaytd"><s:property value="org.orgtype.orgTypeName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="org.description"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="org.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="org.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="org.updated" format="%{getText('label.date.simpleDateFormatWithTime')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="org.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="org.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
			
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>