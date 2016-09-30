<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<jsp:include page="common/messages.jsp" flush="true"/>
<s:form action="insertOrUpdateEmployeeExpensesDetails.action">
	<table class="borderAll" id="EmployeeExpenses">

	<tr>
	<td>
		<s:hidden id="edit" name="editHidden"></s:hidden></td>
		<s:hidden id="delete" name="deleteHidden"></s:hidden>
		<td><s:hidden id="divEdit" name="divHidden"></s:hidden></td>
	</tr>
		<tr>
		
			
			<td class="tdLabel"><s:text
				name="label.header.expdetails.employeeName" /></td>
				
			<td> <s:property value="#session.FIRST_NAME" /></td></tr>
			
			<tr>		 
			<td class="tdLabel"><s:text name="label.header.expdetails.requestDate"/></td>
			<td >
			<%Date currentDate=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.format(currentDate);
			out.println(dateFormat.format(currentDate));
			
			%>
			</td>
           </tr>
           <tr>
			<td class="tdLabel"><s:text
				name="label.header.expdetails.totalAmount" /> <s:text name="label.header.expdetails.totalAmountcolon"/></td>
			<td><s:textfield id="empVO.totalAmount" size="10" value="0.00"
				readonly="true" /></td>
				<s:hidden id="totalAmt" name="totalAmt"></s:hidden>
		</tr>
	
	</table>
<!--Button image added by, R.Deepika-->
	<table id="details" class="borderAll">
		<tr >
			<td class="tdLabel"  ><s:text
				name="label.form.fields.newexpenses.expensedate" /></td>
			
			<td><sj:datepicker id="empVO.expensesDate" name="empVO.expensesDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/> 
				<s:text name="label.date.format"/>
			</td>

<td class="tdLabel" ><s:text
				name="label.form.fields.newexpenses.projectname" /></td>

			<!--<td><s:textfield name="empExpenses.projectId.projectId"
				id="empVO.hcmoProjectId" size="10" /></td>--->

			<td>
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_PROJECT_LIST"
					listKey="projectId"
					listValue="projectName"
				    name="empVO.hcmoProjectId.projectId"
				    id="empVO.hcmoProjectId.projectId"
				    cssStyle="width: 200px;" 
				    />
			</td>	

</tr>
<tr rowspan="2" colspan="4">
			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.expensetype" /></td>
				
			<td>
			<s:url var="loadExpensesType" value="/loadExpensesType.action"/>
				<sj:autocompleter  
					indicator="indicator2ExpDetFormId_div"
					id="empExpVO.hcmoExpensesTypeId.hcmoExpensesTypeId"
				    name="empExpVO.hcmoExpensesTypeId.hcmoExpensesTypeId"
				    href="%{loadExpensesType}" 
				    cssStyle="width: 200px;" 
				    autoComplete="true"
				    forceValidOption="true" 
				    />
			<img id="indicator2ExpDetFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			</td>		

		<!--	<td><s:textfield
				id="empExpVO.hcmoExpensesTypeId" size="10" /></td>

			<td><s:url var="ExpensesTypeList"
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
			<td><s:textfield id="empExpVO.amount" name="empExpVO.amount" size="10"  onblur="amountValidation()"/></td>
</tr>
<tr>
			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.note" /></td>
			<!-- textarea size has been changed : venkat -->
			<td><s:textarea id="empExpVO.note"  cols="26" rows="4" /></td>

			<td class="tdLabel"><s:text
				name="label.form.fields.newexpenses.description" /></td>
			<!-- textarea size has been changed : venkat -->
			<td><s:textarea id="empExpVO.description" cols="26" rows="4" /></td>
		</tr>
		<tr></tr>
		<tr align="center">
			<td align="center" width="60%"><s:a cssClass="butStnd" onclick="doInsert()"><s:text name="label.common.link.add"/></s:a></td>
		</tr>
	</table>
	<s:hidden name="desArrayHide" id="desArrayHide"></s:hidden>
	<s:hidden name="amountArrayHide" id="amountArrayHide"></s:hidden>
	<s:hidden name="noteArrayHide" id="noteArrayHide"></s:hidden>
	<s:hidden name="expDateArrayHide" id="expDateArrayHide"></s:hidden>
	<s:hidden name="expTypeArrayHide" id="expTypeArrayHide"></s:hidden>
	<s:hidden name="proNameArrayHide" id="proNameArrayHide"></s:hidden>
	
	<div  id="test">
	
	
	</div>
	<table>
		<tr align="center">

			<td><s:submit action="insertOrUpdateEmployeeExpensesDetails"
				id="submitAction" key="button.label.submit" cssClass="submitbutton117" onclick="formDetails()"
				disabled="true" /></td>

			<td><s:reset key="button.label.reset" cssClass="butStnd" onclick="clearForm()" /></td>

		</tr>
	</table>
</s:form>