
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_ExpenseAccountantApproverViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESACCOUNTANT_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpAccountantApprover.action" targets="submenu_ExpenseAccountantApproverViewId_div" indicator="indicatorSubMenuExpenseAccountantApproverViewId_div" cssClass="link"><s:text name="MTIAddExpenseAccountantApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESACCOUNTANT_VIEW == true">
			<sj:a href="getAllExpAccountantApprover.action" targets="submenu_ExpenseAccountantApproverViewId_div" indicator="indicatorSubMenuExpenseAccountantApproverViewId_div" cssClass="link"><s:text name="MTIViewExpenseAccountantApprover"/></sj:a> |
			<sj:a href="accountantSearchForm.action" targets="submenu_ExpenseAccountantApproverViewId_div" indicator="indicatorSubMenuExpenseAccountantApproverViewId_div" cssClass="link"><s:text name="MTISearchExpenseAccountantApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseAccountantApproverViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
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
						<s:text name="label.title.expaccountapprover.view" />
					  </td>
	                </tr>
             <tr>
               <td class="forminner"><table class="tablealign">
            <tr>
				<td class="inputtext"><s:text name="label.header.expaccountantapprover.expAccountantId" /></td>
				<td class="employeedisplaytd"><s:property value="expAccountantApprover.hcmoExpensesAccountantId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.expaccountantapprover.accountantEmployee" /></td>
				<td class="employeedisplaytd"><s:property value="expAccountantApprover.expensesAccountantId.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="expAccountantApprover.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="expAccountantApprover.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="expAccountantApprover.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="expAccountantApprover.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="expAccountantApprover.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>	