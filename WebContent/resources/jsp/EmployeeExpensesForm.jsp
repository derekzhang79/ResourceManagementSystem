<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<center>
<div class="informationMessageSingle">
	<li>
		<span>
			<s:if test="empExpenses==null || empExpenses.hcmoExpensesId == null">
				<s:text name="label.title.empexpenses.add" />
			</s:if><s:else>
				<s:text name="label.title.empexpenses.edit" />
			</s:else>
		</span>
	</li>
</div>

<jsp:include page="common/messages.jsp" flush="true"/>
<s:form>
	<table align="center" class="borderAll">
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.employeeID" /></td>
			<td>
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_EMPLOYEE_LIST"
					listKey="employeeId"
					listValue="empFirstName"
				    name="empExpenses.empIdObj.employeeId"
				    cssStyle="width: 200px;" 
				/>
			</td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.accountantID" /></td>
			<td>
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_EMPLOYEE_LIST"
					listKey="employeeId"
					listValue="empFirstName"
				    name="empExpenses.empIdObj.employeeId"
				    cssStyle="width: 200px;" 
				 />
			</td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.projectId" /></td>
			<td><s:textfield name="empExpenses.projectId" size="11" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.createdDate" /></td>
			<td><s:textfield name="empExpenses.createdDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				<s:text name="label.date.format"/>
			</td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.approvalStatus1" /></td>
			<td><s:textfield name="empExpenses.approvalStatus1" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.approverID" /></td>
			<td><s:textfield name="empExpenses.hcmoApproverId1" /></td>
		</tr>
		<!--Button image added by, R.Deepika-->
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.totalAmount" /></td>
			<td><sj:datepicker name="empexpenses.totalAmount" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.approvalTimestamp" /></td>
			<td><sj:datepicker name="empexpenses.approval1_Time" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				<s:text name="label.date.format"/></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.reimbursementAmount" /></td>
			<td><sj:datepicker name="empexpenses.reimbursementAmount" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.totalAmount" /></td>
			<td><sj:datepicker name="empexpenses.totalAmount" /></td>
		</tr>
		<tr>
			<td class="tdLabel"><s:text name="label.form.fields.empexpenses.totalAmount" /></td>
			<td><sj:datepicker name="empexpenses.totalAmount" /></td>
		</tr>
	</table>
</s:form></center>