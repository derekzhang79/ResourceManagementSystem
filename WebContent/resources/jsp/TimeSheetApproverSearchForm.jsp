<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimeSheetApproverSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.TIMESHEETAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateTimeSheetApprover.action" targets="submenu_TimeSheetApproverSearchId_div" indicator="indicatorSubMenuTimeSheetApproverSearchId_div" cssClass="link"><s:text name="MTIAddTimeSheetApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW == true">
			<sj:a href="getAllTimeSheetApprover.action" targets="submenu_TimeSheetApproverSearchId_div" indicator="indicatorSubMenuTimeSheetApproverSearchId_div" cssClass="link"><s:text name="MTIViewTimeSheetApprover"/></sj:a> |
			<sj:a href="timeAppSearchForm.action" targets="submenu_TimeSheetApproverSearchId_div" indicator="indicatorSubMenuTimeSheetApproverSearchId_div" cssClass="link"><s:text name="MTISearchTimeSheetApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetApproverSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="timeAppSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.timesheetapprover.search" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="timeSheetApprover.hcmoEmployeeId.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.approvingEmployeeName" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="timeSheetApprover.hcmoApprovingEmpId.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="timeSheetApprover.hcmoApprovingEmpId.employeeId" /> </td>
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
				<img id="indicatorTimesheetApprForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" indicator="indicatorTimesheetApprForm" targets="submenu_TimeSheetApproverSearchId_div"/>
			</td>
			<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
		</tr>
		</table>
	</s:form>
</div>	