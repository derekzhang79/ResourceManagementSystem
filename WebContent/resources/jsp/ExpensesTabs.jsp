<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
	<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION");%></s:if>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
	<tr>
		<td></td>
	</tr>
		<tr>
			<td class="formtitle">
				<s:text name="MExpense"/>
			</td>
			<td class="employeedisplaytd"></td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="Expenses" class="vidbox2" href="http://www.youtube.com/watch?v=kSep2TmClIo"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
 	<s:url var="newExpenseRequestBoth" action="setUpInsertOrUpdateEmpExpenses"></s:url>
 	<s:url var="myReviewBoth" action="myExpensesReview"></s:url>
 	<s:url var="waitingForApprovalApprover" action="getAllExpensesForApproval"></s:url>
 	<s:url var="approvedExpenseApprover" action="forApprovedTab"></s:url>
 	<s:url var="rejectedExpenseApprover" action="forRejectedTab"></s:url>
 	<s:url var="reviewExpenseApprover" action="forReviewTab"></s:url>
 	<s:url var="accountsPayableExpenseAccountant" action="getAccountantTab"></s:url>
 	<s:url var="historyExpenseAppANDAcc" action="getExpEmployeeHistorySearchForm"></s:url>
 	<s:url var="historyExpenseMyOwnEmployee" action="getExpOwnHistorySearchForm"></s:url>
 
 	<sj:tabbedpanel id="expenseModuleMainTab" animate="true">
 		<sj:tab id="newExpenseRequestSubTab" key="MTNewExpensesRequest" href="%{newExpenseRequestBoth}"/>
 		
 		<s:if test="#session.EXPENSES_REVIEWER=='REVIEWER'">
  	    	<sj:tab id="myReviewSubTab" key="MTMyReview" href="%{myReviewBoth}"/>	
		</s:if>
		
		<s:if test="#session.EXPENSES_APPROVER=='BOTH'">
			<sj:tab id="expenseForApprovalTab" key="MTForApproval" href="%{waitingForApprovalApprover}"/>
			<sj:tab id="expenseApprovedTab" key="MTApproved" href="%{approvedExpenseApprover}"/>
			<sj:tab id="expenseRejectedTab" key="MTRejected" href="%{rejectedExpenseApprover}"/>
			<sj:tab id="expenseReviewedTab" key="MTReview" href="%{reviewExpenseApprover}"/>
		</s:if>
 		
 		<s:if test="#session.EXPENSES_ACCOUNTANT=='ACCOUNTANT'">
 			<sj:tab id="expenseAccountantPayableTab" key="MTForAccountsPayable" href="%{accountsPayableExpenseAccountant}"/>
        </s:if>
        
        <s:if test="#session.EXPENSES_APPROVER=='BOTH' || #session.EXPENSES_ACCOUNTANT=='ACCOUNTANT'">
        	<sj:tab id="expenseHistoryAppANDAccTab" key="MTExpensesHistory" href="%{historyExpenseAppANDAcc}"/>
	  	</s:if>
 		<s:else>
 			<sj:tab id="expenseHistoryMyOwnEmployeeTab" key="MTExpensesHistory" href="%{historyExpenseMyOwnEmployee}"/>
	  	</s:else>
	</sj:tabbedpanel>
	
 <script>
	//Pop-up for Video in modules
	jQuery(document).ready(function() {
		try{
   			jQuery(".vidbox2").jqvideobox({
   			'width' : 600,
   			'height' : 400,
    		'getimage' : false,
   			'navigation' : true
   			});	
  		}catch(e){
  
  		 }
	}); 
 </script>
	