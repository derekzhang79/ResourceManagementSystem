<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:form id="ForApproval" method="post"
	action="insertOrUpdateEmpExpenses" validate="true">
</s:form>
<table class="borderAll" id="EmployeeExpenses">

		<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.employeeID" /></td>
			<td>
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_EMPLOYEE_LIST"
					listKey="employeeId"
					listValue="empFirstName"
				    name="empExpenses.hcmoEmployeeId.employeeId"
				    cssStyle="width: 200px;" 
				    />
			</td>
		</tr>
		<!--Button image added by, R.Deepika-->
		<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.createdDate" /></td>
			<td><sj:datepicker name="empExpenses.createdDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				<s:text name="label.date.format"/></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.empexpenses.totalAmount" /></td>
			<td><s:textfield name="empExpenses.totalAmount" size="10" /></td>
		</tr>
	</table>
	
	<table>
		<tr align="center">
			<td><sj:submit key="button.label.Approve" cssClass="butStnd" /></td>
		
			<td><sj:submit key="button.label.Reject" cssClass="butStnd" /></td>
		
			<td><sj:submit key="button.label.Review" cssClass="butStnd" /></td>
		
			<td><sj:submit key="button.label.SubmitToNextLevel" cssClass="butStnd" /></td>
		</tr>
	</table>