<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationTypeSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATIONTYPE_ADD == true">
		<sj:a href="setUpOrganizationType.action" targets="submenu_OrganizationTypeSearchId_div" indicator="indicatorSubMenuOrganizationTypeSearchId_div" cssClass="link"><s:text name="MTIAddOrganizationType" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATIONTYPE_VIEW == true">
		<sj:a href="getAllOrganizationType.action" targets="submenu_OrganizationTypeSearchId_div" indicator="indicatorSubMenuOrganizationTypeSearchId_div" cssClass="link"><s:text name="MTIViewOrganizationType"/></sj:a> |
		<sj:a href="organizationTypeSearchForm.action" targets="submenu_OrganizationTypeSearchId_div" indicator="indicatorSubMenuOrganizationTypeSearchId_div" cssClass="link"><s:text name="MTISearchOrganizationType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationTypeSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form action="organizationTypeSearchResult">
		<table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						<s:text name="label.title.orgType.search" />
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
	         <tr>
				<td class="inputtext"><s:text name="label.header.orgType.name" /></td>
				<td class="employeeinputtd"><s:textfield name="orgtype.orgTypeName" cssClass="employeeinput"/></td>
				<td></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorOrganizationTypeForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_OrganizationTypeSearchId_div" indicator="indicatorOrganizationTypeForm"/>				    
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>