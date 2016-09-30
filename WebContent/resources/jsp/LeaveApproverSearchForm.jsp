<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LeaveApproverSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateLeaveApprover.action" targets="submenu_LeaveApproverSearchId_div" indicator="indicatorSubMenuLeaveApproverSearchId_div" cssClass="link"><s:text name="MTIAddLeaveApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_VIEW == true">
			<sj:a href="getAllLeaveApprover.action" targets="submenu_LeaveApproverSearchId_div" indicator="indicatorSubMenuLeaveApproverSearchId_div" cssClass="link"><s:text name="MTIViewLeaveApprover"/></sj:a> |
			<sj:a href="leaveAppSearchForm.action" targets="submenu_LeaveApproverSearchId_div" indicator="indicatorSubMenuLeaveApproverSearchId_div" cssClass="link"><s:text name="MTISearchLeaveApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuLeaveApproverSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="leaveAppSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">                  
				  		<s:text name="label.title.leaveapprover.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
						<td class="inputtext"><s:text name="label.header.common.employeeName" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="leaveApprover.hcmoEmployeeId.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"></td>
				    </tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.approvingEmployeeName" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="leaveApprover.hcmoApprovingEmpId.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
					</tr>
					
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLeaveApproverForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveApproverSearchId_div" indicator="indicatorLeaveApproverForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
			</tr>
		</table>
	</s:form>
</div>	