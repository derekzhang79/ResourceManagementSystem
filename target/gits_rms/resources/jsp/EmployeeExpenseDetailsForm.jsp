<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	int count=0;
%>
<div class="textContent">
<div id="insertExp">
<jsp:include page="common/messages.jsp" flush="true"/>

	<s:div cssClass="helpinformationmessage">
	   	<s:text name="label.title.expenses.msg.info"/>
	</s:div>

<s:form name="expenseForm" id="expenseForm" action="insertEmployeeExpensesDetails">
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
                  <td class="forminner"><table class="tablealign" border="0">
	<tr>
	<td>
		<s:hidden id="edit" name="editHidden"></s:hidden></td>
		<s:hidden id="delete" name="deleteHidden"></s:hidden>
		<td colspan="2"><s:hidden id="divEdit" name="divHidden"></s:hidden></td>
	</tr>
	<tr>
		    <td class="inputtextexpense" ><s:text name="label.header.expdetails.employeeName" />
		    <s:property value="#session.FIRST_NAME" /></td>
			<td class="inputtextexpense"><s:text name="label.header.expdetails.requestDate" />
			<%
				Date currentDate=new Date();
				SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
				dateFormat.format(currentDate);
				out.println(dateFormat.format(currentDate));
			%>
			</td>
           	<td class="inputtextexpense">
           	<table align="center" cellpadding="0" cellspacing="0" border="0" style="position: relative;top: -2px;">
           		<tr>
           			<td valign="top">
           			<s:text name="label.header.expdetails.totalAmount"/> <s:property value="#session.CURRENCY_TYPE_VALUE"/>
           			</td>
           			<td valign="top">
           			<s:text name="label.header.expdetails.totalAmountcolon"/> 
           			</td>
           			<td valign="top"><s:textfield id="empExpenses.totalAmount" value="0.00" readonly="true" /></td>
           		</tr>
           	</table>
           	<s:hidden id="totalAmt" name="totalAmt"></s:hidden>
           	</td>
		</tr>
		<tr height="20"><td colspan="3">&nbsp;</td></tr>
	</table>
<!--Button image added by, R.Deepika-->
	<table id="details" class="borderAllExp" align="center">
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.expensedate" /></td>
			<td class="employeeinputtd">
				<sj:datepicker id="empExpenses.expensesDate" name="text" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/><s:text name="label.date.format"/>
			</td>
			<td class="inputtext" ><s:text name="label.form.fields.newexpenses.projectname" /></td>
			<td class="employeeinputtd">
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_PROJECT_LIST"
					listKey="projectId"
					listValue="projectName"
				    name="empExpenses.hcmoProjectId.projectId"
				    id="empExpenses.hcmoProjectId.projectId"
				    cssClass="employeeselect" 
				    />
			</td>
		</tr>
		<tr rowspan="2" colspan="4">
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.expensetype" /></td>
			<td class="employeeinputtd">
				<s:select 
					headerKey=""
					headerValue="-- Please Select --"
					list="#request.APPL_EXPENSESTYPE_LIST"
					listKey="hcmoExpensesTypeId"
					listValue="name"
				    name="expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId"
				    id="expenseDetails.hcmoExpensesTypeId.hcmoExpensesTypeId"
				    cssClass="employeeselect"
				    />
			</td>	
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.amountwithoutmanadatory" /> <s:property value="#session.CURRENCY_TYPE_VALUE"/> <s:text name="label.form.common.mandatory"/></td>
			<td class="employeeinputtd"><s:textfield id="expenseDetails.amount" name="empExpVO.amount" cssClass="employeeinput"  onblur="amountValidation()"/></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.note" /></td>
			<!-- textarea size has been changed : venkat -->
			<td class="employeeinputtd"><s:textarea cssClass="employeetextarea" rows="4" cols="26" id="expenseDetails.note" /></td>
			<td class="inputtext"><s:text name="label.form.fields.newexpenses.description" /></td>
			<!-- textarea size has been changed : venkat -->
			<td class="employeeinputtd"><s:textarea cssClass="employeetextarea" rows="4" cols="26" id="expenseDetails.description" /></td>
		</tr>
		<tr></tr>
		<tr align="left">
			 <td colspan="4" align="left"><s:submit cssClass="button-midle" href="javascript:void(0)" onclick="doInsert()" key="label.form.fields.newexpenses.addExpenseDetails"></s:submit></td>
		</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
	<div  id="test">
	</div>
	<s:hidden name="desArrayHide" id="desArrayHide"></s:hidden>
	<s:hidden name="amountArrayHide" id="amountArrayHide"></s:hidden>
	<s:hidden name="noteArrayHide" id="noteArrayHide"></s:hidden>
	<s:hidden name="expDateArrayHide" id="expDateArrayHide"></s:hidden>
	<s:hidden name="expTypeArrayHide" id="expTypeArrayHide"></s:hidden>
	<s:hidden name="proNameArrayHide" id="proNameArrayHide"></s:hidden>
	<input type="hidden" name="pageCount" id="pageCount" value="<%=count%>">
	<%count++; %>
	
	<table align="center">
		<tr align="center">
			<td>
			    <img id="indicatorExpenseReqTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			    <sj:submit id="submitAction" targets="insertExp" indicator="indicatorExpenseReqTabs" value="Submit" cssClass="submitbutton117" onclick="expSubmitAction()"></sj:submit>
		   	</td>
			<td>
			    <s:url var="setUpInsertOrUpdateEmpExpenses" action="setUpInsertOrUpdateEmpExpenses"></s:url>
   	            <sj:submit href="%{setUpInsertOrUpdateEmpExpenses}"  key="button.label.reset" cssClass="submitbutton117" targets="insertExp" indicator="indicatorExpenseReqTabs"/>
			</td>
		</tr>
	</table>
	</s:form>
</div></div>