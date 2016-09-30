<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_essExpenseApproverView_div_id">
<div class="submenu_bg">
		<s:set name="UniqueExpApprEmployeeId" value="expApprover.hcmoEmployeeId.employeeId"></s:set>
		<s:url var="remoteExpApprForm" value="/setUpEmpInsertOrUpdateExpApprover.action">
			<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#UniqueExpApprEmployeeId"></s:param>
		</s:url>
		<s:set name="UniqueExpApprEmployeeId" value="expApprover.hcmoEmployeeId.employeeId"></s:set>
		<s:url var="remoteExpApprView" value="/getEmployeeAllExpApprover.action">
			<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#UniqueExpApprEmployeeId"></s:param>
		</s:url>

		<s:if test="#session.EXPENSESAPPROVER_ADD==true">
			<sj:a href="%{remoteExpApprForm}" indicator="indicatorExpenseApprList" targets="submenu_essExpenseApproverView_div_id" cssClass="link"><s:text name="label.header.expenses.add"/></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW==true">
			<sj:a href="%{remoteExpApprView}" indicator="indicatorExpenseApprList" targets="submenu_essExpenseApproverView_div_id" cssClass="link"><s:text name="label.header.expenses.view"/></sj:a>
		</s:if>
	</div>		
<br />		

<img id="indicatorExpenseApprList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.expapprover.view" />
					  </td>
	                </tr>
	                <tr>
	                 <td class="forminner"><table class="tablealign">
	        <tr>
				<td class="inputtext"><s:text name="label.header.expapprover.expApproverId" /></td>
				<td class="employeedisplaytd"><s:property value="expApprover.approverId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.expapprover.EmployeeName" /></td>
				<td class="employeedisplaytd"><s:property value="expApprover.hcmoEmployeeId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.expapprover.approvingEmployee" /></td>	
				<td class="employeedisplaytd"><s:property value="expApprover.approvingEmployeeId.empFullName"/></td>
			</tr>
		   	<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="expApprover.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="expApprover.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="expApprover.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="expApprover.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="expApprover.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>	