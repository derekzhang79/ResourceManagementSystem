<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<div class="informationMessageSingle"><li><span><s:text name="label.title.empexpenses.list" /></span></li></div>
<table width=600 align=center>
	<tr>
		<td><a href="homePage.action">Home</a></td>
		<s:url var="setUpInsertOrUpdateEmpExpenses"
			action="setUpInsertOrUpdateEmpExpenses" />
		<td><s:a href="%{setUpInsertOrUpdateEmpExpenses}"> Add New Employee Expenses</s:a></td>
	</tr>
</table>
<br />

<table align=center class="borderAll">
	<tr>
		<th><s:text name="label.header.common.empName" /></th>
		<th><s:text name="label.header.empexpenses.hcmoAccountantId" /></th>
		<th><s:text name="label.header.empexpenses.projectId" /></th>
		<th><s:text name="label.header.empexpenses.createdDate" /></th>
		<th><s:text name="label.header.empexpenses.approvalStatus" /></th>
		<th><s:text name="label.header.empexpenses.hcmoApproverId" /></th>
		<th><s:text name="label.header.empexpenses.totalAmount" /></th>
		<th><s:text name="label.header.empexpenses.approvalTimestamp" /></th>
		<th><s:text name="label.header.empexpenses.reimbursementAmount" /></th>
		<th><s:text name="label.header.empexpenses.reimbursementDate" /></th>
		<th><s:text name="label.header.empexpenses.accountingNotes" /></th>
		<th><s:text name="label.header.common.edit" /></th>
		<th><s:text name="label.header.common.delete" /></th>
	</tr>
	<s:iterator value="empExpensesList" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			<td class="nowrap"><s:property value="empIdObj.empFirstName" /></td>
			<td class="nowrap"><s:property value="hcmoAccountantId" /></td>
			<td class="nowrap"><s:property value="projectId" /></td>
			<td class="nowrap"><s:property value="createdDate" /></td>
			<td class="nowrap"><s:property value="approvalStatus" /></td>
			<td class="nowrap"><s:property value="hcmoApproverId" /></td>
			<td class="nowrap"><s:property value="totalAmount" /></td>
			<td class="nowrap"><s:property value="approvalTimestamp" /></td>
			<td class="nowrap"><s:property value="reimbursementAmount" /></td>
			<td class="nowrap"><s:property value="reimbursementDate" /></td>
			<td class="nowrap"><s:property value="accountingNotes" /></td>
			<td class="nowrap"><s:url var="setUpInsertOrUpdateEmpExpenses"
				action="setUpInsertOrUpdateEmpExpenses">
				<s:param name="empExpenses.hcmoEmployeeId" value="hcmoEmployeeId" />
			</s:url> <s:a href="%{setUpInsertOrUpdateEmpExpenses}">Edit</s:a></td>
			<td><s:url var="deleteEmpExpenses" action="deleteEmpExpenses">
				<s:param name="empExpenses.hcmoExpensesId" value="hcmoExpensesId" />
			</s:url> <s:a href="%{deleteEmpExpenses}">Delete</s:a></td>
		</tr>
	</s:iterator>
</table>