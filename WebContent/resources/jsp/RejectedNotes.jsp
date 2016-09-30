<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<div id="expenseRejectedNotesDivId">
<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form action="rejectEmployeeExpense">
    	<table class="maintable">
      		<tr><td align="center" ><table class="formouter">
          		<tr><td><table class="employeeformiinertable">
                	<tr><td class="formtitle">
				 		<s:text name="label.title.expensesreject.notes"/>
				  	</td></tr>
                <tr>
               	 	<td class="forminner"><table class="tablealign">
			 		<tr><td class="inputtext"><s:text name="label.form.fields.expenses.rejectnotes"/></td>
         	        <!-- textarea size has been changed : venkat -->
         	        <td class="employeeinputtd"><s:textarea name="expenseStatusStracker.rejectedNotes" cssClass="employeetextarea" rows="4" cols="26" /></td>
         	        <td class="inputerrormessage"><s:fielderror fieldName="expenseStatusStracker.rejectedNotes" /> </td>
             	</tr> 
    </table></td></tr></table></td></tr></table></td></tr></table>
    <table align="center"> 
  		<s:set name="empExpenses.hcmoExpensesId" value="empExpenses.hcmoExpensesId"></s:set>
  		<s:hidden name="empExpenses.hcmoExpensesId"/>
   	     <tr>
	   	     <td class="nowrap">
		     	 <img id="expenseRejectNotesIndicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			 	 <sj:submit targets="expenseRejectedNotesDivId" indicator="expenseRejectNotesIndicator" cssClass="submitbutton117" key="button.label.reject.submit"></sj:submit>
	   		 </td>
	   		 <td> 
	   		      <img id="IndicatorForBackButtonExpenseRejectNotes" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	   		      <s:url var="getExpensesForApprovalEmployee" action="getExpensesForApprovalEmployee">
	   		        <s:param name="empExpenses.hcmoExpensesId" value="empExpenses.hcmoExpensesId"/>
	   		      </s:url>
	   		      <sj:submit href="%{getExpensesForApprovalEmployee}" targets="expenseRejectedNotesDivId" indicator="IndicatorForBackButtonExpenseRejectNotes" cssClass="submitbutton117" key="button.label.ExpBackButton"></sj:submit>
	   		 </td>
   		 </tr>
    </table> 		  		 
</s:form>
</div>