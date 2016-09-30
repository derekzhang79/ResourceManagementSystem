<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="common/messages.jsp" flush="true"/>
<s:form name="expenseRequestForm"
	action="insertOrUpdateEmpExpensesDetails.action">
	<table class="borderAll" id="EmployeeExpenses">
		<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.employeeName" /></td>
			<s:property value="#session.FIRST_NAME" />
			<td></td>
			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.projectname" /></td>

			<td><s:textfield name="empExpenses.projectId.projectId"
				id="empExpenses.projectId" size="10" /></td>

			<!-- <td><s:url var="loadProjects" value="/loadProjectName.action" />
			<sj:autocompleter indicator="indicator2"
				name="empExpenses.projectId.projectId" href="%{loadProjects}"
				cssStyle="width: 200px;" autoComplete="true" forceValidOption="true" />
			<img id="indicator2"
				src="${pageContext.request.contextPath}/resources/images/indicator.gif"
				alt="Loading..." style="display: none" /></td>
			  -->

			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.createdDate" /></td>
			<td><s:textfield name="empExpenses.created" size="10" />
				<s:text name="label.date.format"></s:text>
			</td>

			<!-- <td><sj:datepicker name="empExpenses.createdDate"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/> Format: MM/dd/yyyy</td>
			  -->

			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.totalAmount" /></td>
			<td><s:textfield id="empExpenses.totalAmount" size="10"
				readonly="true" /></td>
		</tr>
	</table>

	<table id="details" class="borderAll">
		<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.expensedate" /></td>
			<td><s:textfield id="expDetails.expensesDate" size="10" />
				<s:text name="label.date.format"></s:text>
			</td>
<!--Button image added by, R.Deepika-->
			<!--<td><sj:datepicker name="expDetails.created"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/> Format: MM/dd/yyyy</td>
			  -->



			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.expensetype" /></td>

			<td><s:textfield
				id="expDetails.hcmoExpensesTypeId.hcmoExpensesTypeId" size="10" /></td>

			<!--<td><s:url var="ExpensesTypeList"
				value="/expenseTypeList.action" /> <sj:autocompleter
				indicator="indicator3"
				name="empExpenses.hcmoExpensesTypeId.hcmoExpensesTypeId"
				href="%{ExpensesTypeList}" cssStyle="width: 200px;"
				autoComplete="true" forceValidOption="true" /> <img
				id="indicator3"
				src="${pageContext.request.contextPath}/resources/images/indicator.gif"
				alt="Loading..." style="display: none" /></td>
			
			  -->

			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.amount" /></td>
			<td><s:textfield id="expDetails.amount" size="10" /></td>

			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.note" /></td>
			<td><s:textfield id="expDetails.note" size="10" /></td>

			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.description" /></td>
			<td><s:textfield id="expDetails.description" size="10" /></td>
		</tr>
		<tr></tr>
		<tr align="right">
			<td><s:a cssClass="butStnd" onclick="doInsert()"><s:text name="label.common.link.add"></s:text></s:a></td>
		</tr>
	</table>
	<div id="test"></div>
	<table>
		<tr align="center">

			<td><s:submit action="insertOrUpdateEmpExpensesDetails"
				id="submitAction" key="button.label.submit" cssClass="submitbutton117"
				disabled="true" /></td>

			<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>

			<td><s:submit key="button.label.cancel" cssClass="butStnd" /></td>
		</tr>
	</table>
</s:form>