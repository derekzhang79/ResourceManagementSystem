<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ExpenseApproverViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpApprover.action" targets="submenu_ExpenseApproverViewId_div" indicator="indicatorSubMenuExpenseApproverViewId_div" cssClass="link"><s:text name="MTIAddExpenseApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW == true">
			<sj:a href="getAllExpApprover.action" targets="submenu_ExpenseApproverViewId_div" indicator="indicatorSubMenuExpenseApproverViewId_div" cssClass="link"><s:text name="MTIViewExpenseApprover"/></sj:a> |
			<sj:a href="expAppSearchForm.action" targets="submenu_ExpenseApproverViewId_div" indicator="indicatorSubMenuExpenseApproverViewId_div" cssClass="link"><s:text name="MTISearchExpenseApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseApproverViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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