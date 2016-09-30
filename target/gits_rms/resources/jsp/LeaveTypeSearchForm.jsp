<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LeaveTypeSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.LEAVETYPE_ADD == true">
		<sj:a href="setUpLeaveType.action" targets="submenu_LeaveTypeSearchId_div" indicator="indicatorSubMenuLeaveTypeSearchId_div" cssClass="link"><s:text name="MTIAddLeaveType" /></sj:a> |
	</s:if>
	<s:if test="#session.LEAVETYPE_VIEW == true">
		<sj:a href="getAllLeaveType.action" targets="submenu_LeaveTypeSearchId_div" indicator="indicatorSubMenuLeaveTypeSearchId_div" cssClass="link"><s:text name="MTIViewLeaveType"/></sj:a> |
		<sj:a href="leaveTypeSearchForm.action" targets="submenu_LeaveTypeSearchId_div" indicator="indicatorSubMenuLeaveTypeSearchId_div" cssClass="link"><s:text name="MTISearchLeaveType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLeaveTypeSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="leaveTypeSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.leaveType.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
				<tr>
					<td class="inputtext">
						<s:text	name="label.header.leaveType.leaveType" />
					</td>
					<td class="employeeinputtd">
						<s:textfield name="leave.leaveTypeName" cssClass="employeeinput" />
					</td>
					<td class="inputerrormessage"></td>
				</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLeaveTypeForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveTypeSearchId_div" indicator="indicatorLeaveTypeForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>