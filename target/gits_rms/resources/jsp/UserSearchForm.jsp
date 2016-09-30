<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_UserSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.USER_ADD == true">
			<sj:a href="setUpUser.action" targets="submenu_UserSearchId_div" indicator="indicatorSubMenuUserSearchId_div" cssClass="link"><s:text name="MTIAddUser" /></sj:a> |
		</s:if>
		<s:if test="#session.USER_VIEW == true">
			<sj:a href="getAllUser.action" targets="submenu_UserSearchId_div" indicator="indicatorSubMenuUserSearchId_div" cssClass="link"><s:text name="MTIViewUser"/></sj:a> |
			<sj:a href="userSearchForm.action" targets="submenu_UserSearchId_div" indicator="indicatorSubMenuUserSearchId_div" cssClass="link"><s:text name="MTISearchUser"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuUserSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="userSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.user.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
			            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="user.empIdObj.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"></td>
        			</tr><!-- text added by, R.Deepika -->
        			<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSubMenuUserForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_UserSearchId_div" indicator="indicatorSubMenuUserForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>