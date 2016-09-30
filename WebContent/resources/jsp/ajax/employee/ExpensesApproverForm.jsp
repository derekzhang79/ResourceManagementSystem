<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssExpensesApprover_Form_div">
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
			<sj:a href="%{remoteExpApprForm}" indicator="indicatorExpenseApprList" targets="submenu_EssExpensesApprover_Form_div" cssClass="link"><s:text name="label.header.expenses.add"/></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_ADD==true">
			<sj:a href="%{remoteExpApprView}" indicator="indicatorExpenseApprList" targets="submenu_EssExpensesApprover_Form_div" cssClass="link"><s:text name="label.header.expenses.view"/></sj:a>
		</s:if>
	</div>		
<br />		

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form action="insertOrUpdateExpApproverAjax">
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
			<td class="inputtext"><s:text name="label.form.fields.expapprover.approvingEmployee" /></td>
			<td>
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
			<td class="inputerrormessage"><s:fielderror fieldName="expApprover.approvingEmployeeId.employeeId" /></td>
		</tr>
		<tr>
			<td></td>
			<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
		</tr>
	</table>
	<br />
	<table align="center">
		<tr>
			<td>
				<img id="indicatorExpenseApprForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssExpensesApprover_Form_div" indicator="indicatorExpenseApprForm"/>
			</td>
			<td><s:if test="expApprover==null || expApprover.approverId == null">
		    	        	<s:url var="resetExpApproverFormAjax" action="setUpEmpInsertOrUpdateExpApprover">
		    	        		<s:param name="expApprover.hcmoEmployeeId.employeeId" value="expApprover.hcmoEmployeeId.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetExpApproverFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssExpensesApprover_Form_div" indicator="indicatorExpenseApprForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
		</tr>
	</table>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
		<s:hidden name="expApprover.hcmoEmployeeId.employeeId"/> 
		<s:hidden name="expApprover.approverId" />
</s:form>
</div>