<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_RoleSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.ROLE_ADD == true">
		<sj:a href="setUpRole.action" targets="submenu_RoleSearchId_div" indicator="indicatorSubMenuRoleSearchId_div" cssClass="link"><s:text name="MTIAddRole" /></sj:a> |
	</s:if>
	<s:if test="#session.ROLE_VIEW == true">
		<sj:a href="getAllRole.action" targets="submenu_RoleSearchId_div" indicator="indicatorSubMenuRoleSearchId_div" cssClass="link"><s:text name="MTIViewRole"/></sj:a> |
		<sj:a href="roleSearchForm.action" targets="submenu_RoleSearchId_div" indicator="indicatorSubMenuRoleSearchId_div" cssClass="link"><s:text name="MTISearchRole"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRoleSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="roleSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.role.search" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.role.name" /></td>
				<td class="employeeinputtd"><s:textfield name="role.roleName" cssClass="employeeinput" /></td>
				<td class="inputerrormessage"><s:fielderror fieldName="role.roleName" /> </td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorRoleForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_RoleSearchId_div" indicator="indicatorRoleForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>