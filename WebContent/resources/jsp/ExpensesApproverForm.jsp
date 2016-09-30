<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ExpenseApproverFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpApprover.action" targets="submenu_ExpenseApproverFormId_div" indicator="submenu_ExpenseApproverFormId_div" cssClass="link"><s:text name="MTIAddExpenseApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW == true">
			<sj:a href="getAllExpApprover.action" targets="submenu_ExpenseApproverFormId_div" indicator="submenu_ExpenseApproverFormId_div" cssClass="link"><s:text name="MTIViewExpenseApprover"/></sj:a> |
			<sj:a href="expAppSearchForm.action" targets="submenu_ExpenseApproverFormId_div" indicator="submenu_ExpenseApproverFormId_div" cssClass="link"><s:text name="MTISearchExpenseApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="submenu_ExpenseApproverFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateExpApprover">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="expApprover==null || expApprover.approverId == null">
								<s:text name="label.title.expapprover.add" />
							</s:if> <s:else>
								<s:text name="label.title.expapprover.edit" />
							</s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<s:if test="expApprover==null || expApprover.approverId == null">
					<td class="inputtext"><s:text name="label.form.fields.employeeInExpenses.name" /></td>
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
					<td class="inputerrormessage"><s:fielderror fieldName="expApprover.hcmoEmployeeId.employeeId" /></td>
				</s:if>
				<s:else>
					<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="expApprover.hcmoEmployeeId.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="expApprover.hcmoEmployeeId.employeeId" />
				</s:else>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.expapprover.approvingEmployee" /></td>
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
				<td class="inputerrormessage"><s:fielderror fieldName="expApprover.approvingEmployeeId.employeeId" /> </td>
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
					<img id="indicatorExpenseApproverForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ExpenseApproverFormId_div" indicator="indicatorExpenseApproverForm"/>
				</td>
				<s:if test="expApprover==null || expApprover.approverId==null">
	    		    <td>
	    		    	<s:url var="resetExpensesApprover" action="resetExpensesApprover"></s:url>
	    	            <sj:submit href="%{resetExpensesApprover}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_ExpenseApproverFormId_div" indicator="indicatorExpenseApproverForm"/>
					</td>
				</s:if>
				<s:else>
    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	        </s:else>
			</tr>
		</table>
		<s:hidden name="expApprover.approverId" />
	</s:form>
</div>