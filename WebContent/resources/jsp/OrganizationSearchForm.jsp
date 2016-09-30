<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationSearchResId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATION_ADD == true">
		<sj:a href="setUpOrganization.action" targets="submenu_OrganizationSearchResId_div" indicator="indicatorSubMenuOrganizationSearchResId_div" cssClass="link"><s:text name="MTIAddOrganization" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATION_VIEW == true">
		<sj:a href="getAllOrganization.action" targets="submenu_OrganizationSearchResId_div" indicator="indicatorSubMenuOrganizationSearchResId_div" cssClass="link"><s:text name="MTIViewOrganization"/></sj:a> |
		<sj:a href="organizationSearchForm.action" targets="submenu_OrganizationSearchResId_div" indicator="indicatorSubMenuOrganizationSearchResId_div" cssClass="link"><s:text name="MTISearchOrganization"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form action="organizationSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.organization.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
					  <td class="inputtext">
					  	<s:text	name="label.header.leaveType.orgName" /></td>
					  <td class="employeeinputtd">
					  	<s:textfield name="org.orgName" cssClass="employeeinput"/></td>
					  	<td></td>
				  </tr>
				  <tr>
						<td class="inputtext">
							<s:text name="label.header.location.name"/></td>
					    <td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_LOCATION_LIST"
							listKey="hcmolocationId"
							listValue="locationName"
					   	    name="org.location.hcmolocationId"
					    	cssClass="employeeselect" 
					  	  />
						</td>
				  </tr>
				  <tr>
					<td class="inputtext">
						<s:text name="label.header.orgType.name"/></td>
					<td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_ORGANIZATIONTYPE_LIST"
							listKey="hcmoorgTypeId"
							listValue="orgTypeName"
					   		name="org.orgtype.hcmoorgTypeId"
					    	cssClass="employeeselect"  
					    	/>
					</td>
				  </tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorOrganizationForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_OrganizationSearchResId_div" indicator="indicatorOrganizationForm"/>				    
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
		<s:hidden name="org.orgId"/>
	</s:form>
</div>