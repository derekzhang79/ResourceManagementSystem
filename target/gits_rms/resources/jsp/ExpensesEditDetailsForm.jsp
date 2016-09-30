<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="editUpdateExpensesDiv">
<jsp:include page="common/messages.jsp" flush="true"/>
<s:form action="updateExpensesDetails">
	<!--  <table class="borderAll" id="EmployeeExpenses" align="center">

		<tr>
           	<td class="tdLabel" width="200"><s:text
				name="label.header.expdetails.totalAmount" /> <s:text name="label.header.expdetails.totalAmountcolon"/>
				<s:textfield id="empExpenses.totalAmount" name="empExpenses.totalAmount" size="10" readonly="true"></s:textfield></td>
				
		</tr>
	
	</table>-->
     <div class="informationMessageSingle"><li><span><s:text name="label.title.expensesUpdateDetailView"/></span></li></div>
     <img id="indicatorUpdateReviewExpense" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
     <table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
				 		
				  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.expensedate" /></td>
			
			<td class="employeeinputtd">
			<!--Button image added by, R.Deepika-->
				<sj:datepicker id="expenseDetails.expensesDate" name="expenseDetails.expensesDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/>
				<s:text name="label.date.format"/>
			</td>
			<td class="inputtext" ><s:text name="label.form.fields.newexpenses.projectname" /></td>
			<td class="employeeinputtd">
			    <sj:autocompleter  
							    name="expenseDetails.projectId.projectId"
								list="#request.APPL_PROJECT_LIST"
								listKey="projectId"
								listValue="projectName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
				/></br></br>
				<s:text name="label.common.message.autocompleteSelect"></s:text>
			</td>
		</tr><tr></tr>
		<tr rowspan="2" colspan="4">
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.expensetype" /></td>
			<td class="employeeinputtd">
			<sj:autocompleter  
							    name="expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId"
								list="#request.APPL_EXPENSESTYPE_LIST"
								listKey="hcmoExpensesTypeId"
								listValue="name"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
				/></br></br>
				<s:text name="label.common.message.autocompleteSelect"></s:text>
			</td>	
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.amount" /><s:text name="currencyTypeValue"/></td>
			<td class="employeeinputtd"><s:textfield id="expenseDetails.amount" name="expenseDetails.amount" onblur="amtvalidate()" cssClass="employeeinput"/></td>
		</tr>
		<tr></tr>
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.note" /></td>
			<!-- textarea size has been changed : venkat -->
			<td class="employeeinputtd"><s:textarea id="expenseDetails.note" name="expenseDetails.note"  cssClass="employeetextarea" rows="4" cols="26" /></td>

			<td class="inputtext"><s:text name="label.form.fields.newexpenses.description" /></td>
			<!-- textarea size has been changed : venkat -->
			<td class="employeeinputtd"><s:textarea id="expenseDetails.description" name="expenseDetails.description" cssClass="employeetextarea" rows="4" cols="26" /></td>
		</tr>
		<tr>
		<s:hidden id="expenseDetails.hcmoExpensesDetailsId" name="expenseDetails.hcmoExpensesDetailsId" />
		<s:hidden id="expenseDetails.hcmoExpensesId.hcmoExpensesId" name="expenseDetails.hcmoExpensesId.hcmoExpensesId" />
		</tr>
		<table align="center">
			<tr align="center">
				<td class="nowrap">
	           		<sj:submit targets="editUpdateExpensesDiv" indicator="indicatorUpdateReviewExpense" key="label.common.link.update" cssClass="submitbutton117"></sj:submit>
	           	</td>
	           	<td>
	           		<s:reset key="button.label.reset" cssClass="submitbutton117"></s:reset>
	           	</td>
	           	<td>
	           	    <img id="indicatorForBackButtonUpdateViewExpense" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	           		<s:url var="getMyReviewExpensesDetail" action="getMyReviewExpensesDetail">
	           			<s:param name="empExpenses.hcmoExpensesId" value="expenseDetails.hcmoExpensesId.hcmoExpensesId"></s:param>
	           		</s:url>
					<sj:submit href="%{getMyReviewExpensesDetail}" targets="editUpdateExpensesDiv" indicator="indicatorForBackButtonUpdateViewExpense" cssClass="submitbutton117" key="button.label.ExpBackButton"></sj:submit>
				</td>
			</tr>
		</table>
	</table></td></tr></table></td></tr></table></td></tr></table>

</s:form>
</div>