<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<div id="submenu_ExpenseApproverSearchForm_div">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpApprover.action" targets="submenu_ExpenseApproverSearchForm_div" indicator="indicatorSubMenuExpenseApproverSearchFormId_div" cssClass="link"><s:text name="MTIAddExpenseApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW == true">
			<sj:a href="getAllExpApprover.action" targets="submenu_ExpenseApproverSearchForm_div" indicator="indicatorSubMenuExpenseApproverSearchFormId_div" cssClass="link"><s:text name="MTIViewExpenseApprover"/></sj:a> |
			<sj:a href="expAppSearchForm.action" targets="submenu_ExpenseApproverSearchForm_div" indicator="indicatorSubMenuExpenseApproverSearchFormId_div" cssClass="link"><s:text name="MTISearchExpenseApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseApproverSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="expAppSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.expapprover.search" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName"/></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="expApprover.hcmoEmployeeId.employeeId"
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
					    name="expApprover.approvingEmployeeId.employeeId"
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
						<td class="inputerrormessage"></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
									
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorExpenseApproverSearchFormSubmit" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ExpenseApproverSearchForm_div" indicator="indicatorExpenseApproverSearchFormSubmit"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
			</tr>
		</table>
	</s:form>
</div>