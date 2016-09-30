
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LeaveQuotaSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEQUOTA_ADD == true">
			<sj:a href="setUpEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaSearchId_div" indicator="indicatorSubMenuLeaveQuotaSearchId_div" cssClass="link"><s:text name="MTIAddLeaveQuota" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEQUOTA_VIEW == true">
			<sj:a href="getAllEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaSearchId_div" indicator="indicatorSubMenuLeaveQuotaSearchId_div" cssClass="link"><s:text name="MTIViewLeaveQuota"/></sj:a> |
			<sj:a href="leaveQuotaSearchForm.action" targets="submenu_LeaveQuotaSearchId_div" indicator="indicatorSubMenuLeaveQuotaSearchId_div" cssClass="link"><s:text name="MTISearchLeaveQuota"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuLeaveQuotaSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form action="leaveQuotaSearchResult">
		<table class="maintable">
	    <tr>
	        <td align="center" ><table class="formouter">
	        <tr>
	          <td><table class="employeeformiinertable">
	          <tr>
	             <td class="formtitle">
					 <s:text name="label.title.empLeaveQuota.search"/>
	             </td>
	          </tr>
	  		 <tr>
	              <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
			  	<td class="employeeinputtd">
					<sj:autocompleter  
					    name="empLeaveQuota.empIdObj.employeeId"
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
				<td class="inputtext"><s:text name="label.leaveh.leaveType" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="empLeaveQuota.leaveTypeIdObj.leaveTypeId"
						list="#request.APPL_LEAVE_LIST"
						listKey="leaveTypeId"
						listValue="leaveTypeName"
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
			<tr>
 				<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.year" /></td>
				<td class="employeeinputtd">
					<s:select 
					   		 id="empLeaveQuota.year"
							 headerKey=""
							 headerValue="-- Please Select --"
							 list="#request.APPL_LEAVEQUOTA_YEAR_LIST"
							 listKey="year"
							 listValue="year"
					    	 name="empLeaveQuota.year"
					    	 cssClass="employeeselect"
					   	 />
				</td>
				<td  class="inputerrormessage"></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeLeaveQuotaFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveQuotaSearchId_div" indicator="indicatorEmployeeLeaveQuotaFormSearchId_div"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
			</tr>
		</table>
	 </s:form>
</div> 