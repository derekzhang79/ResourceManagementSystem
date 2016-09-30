<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ExpenseAccountantApproverFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESACCOUNTANT_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpAccountantApprover.action" targets="submenu_ExpenseAccountantApproverFormId_div" indicator="indicatorSubMenuExpenseAccountantApproverFormId_div" cssClass="link"><s:text name="MTIAddExpenseAccountantApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESACCOUNTANT_VIEW == true">
			<sj:a href="getAllExpAccountantApprover.action" targets="submenu_ExpenseAccountantApproverFormId_div" indicator="indicatorSubMenuExpenseAccountantApproverFormId_div" cssClass="link"><s:text name="MTIViewExpenseAccountantApprover"/></sj:a> |
			<sj:a href="accountantSearchForm.action" targets="submenu_ExpenseAccountantApproverFormId_div" indicator="indicatorSubMenuExpenseAccountantApproverFormId_div" cssClass="link"><s:text name="MTISearchExpenseAccountantApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseAccountantApproverFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateExpAccountantApprover">
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 	<s:if test="expAccountantApprover==null || expAccountantApprover.hcmoExpensesAccountantId == null">
							<s:text name="label.title.expaccountapprover.add" />
						</s:if> <s:else>
							<s:text name="label.title.expaccountapprover.edit" />
						</s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.expaccountapprover.accountantname" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="expAccountantApprover.expensesAccountantId.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="expAccountantApprover.expensesAccountantId.employeeId" /> </td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorExpenseAccountantApprFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ExpenseAccountantApproverFormId_div" indicator="indicatorExpenseAccountantApprFormId_div"/>
				</td>
				<s:if test="expAccountantApprover==null || expAccountantApprover.hcmoExpensesAccountantId==null">
	    		    <td>
	    		    	<s:url var="resetExpensesAccountantApprover" action="resetExpensesAccountantApprover"></s:url>
	    	            <sj:submit href="%{resetExpensesAccountantApprover}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_ExpenseAccountantApproverFormId_div" indicator="indicatorExpenseAccountantApprFormId_div"/>
					</td>
				</s:if>
				<s:else>
    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	        </s:else>
			</tr>
		</table>
		<s:hidden name="expAccountantApprover.hcmoExpensesAccountantId" />
	</s:form>
</div>	