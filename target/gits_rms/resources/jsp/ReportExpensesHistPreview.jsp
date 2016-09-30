<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_expensesResult_list_div_id">
<div class="submenu_bg">
			<sj:a href="showExpensesHistReports.action" targets="report_expensesResult_list_div_id" indicator="indicatorGenerateExpenseReport" cssClass="link"><s:text name="MTIGenerateExpenseReport" /></sj:a>
		</div>
<br/>
<img id="indicatorGenerateExpenseReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expensesReport.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	<s:text name="label.header.empexpenses.expenseid" var="HExpensesId"></s:text>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.expenses.report.approvedBy" var="HApprovedBy"></s:text>
	  <s:text name="label.header.expenses.report.rejectedBy" var="HRejectedBy"></s:text>
	  <s:text name="label.header.empexpenses.rejectednotes" var="HRejectedNotes"></s:text>
	  <s:text name="label.header.expenses.report.reviewedBy" var="HReviewedBy"></s:text>
	  <s:text name="label.header.expenses.report.nextApprover" var="HNextApprover"></s:text>
	  <s:text name="label.header.empexpenses.accountantname" var="HAccountant"></s:text>
	  <s:text name="label.header.empexpenses.createdDate" var="HCreatedDate"></s:text>
	  <s:text name="label.header.empexpenses.totalAmount" var="HTotalAmount"></s:text>
	  <s:text name="label.header.empexpenses.reimbursementAmount" var="HReimburse"></s:text>
	  <s:text name="label.header.empexpenses.status" var="HApprovalStatus"></s:text>
	  
	  <div id="display_tag_reportExpensesResultsList_div_id">
		  <display:table class="tableborder" id="expensesListId" name="expensesList" pagesize="${NO_OF_RECORDS}" requestURI="getExpensesStatusTrackerReportsPreview.action" sort="list" defaultsort="1" defaultorder="ascending">
		  	<display:column property="hcmoExpensesId.hcmoExpensesId" title="${HExpensesId}" />
		  	<display:column property="hcmoExpensesId.hcmoEmployeeId.empFirstName" title="${HEmployeeName}" headerClass="sortable" sortable="true"/>
		    <display:column property="approverId.empFirstName" title="${HApprovedBy}" sortable="true"/>
		    <display:column property="rejectedId.empFirstName" title="${HRejectedBy}" sortable="true"/>
		    <display:column property="rejectedNotes" title="${HRejectedNotes}" maxLength="10" />
		    <display:column property="reviewedId.empFirstName" title="${HReviewedBy}" sortable="true"/>
		    <display:column property="nextLevelId.empFirstName" title="${HNextApprover}" sortable="true"/>
		    <display:column property="accountantId.empFirstName" title="${HAccountant}" sortable="true"/>
		    <display:column property="hcmoExpensesId.createdDate" title="${HCreatedDate}" format="{0,date,MM-dd-yyyy}"/>
		    <display:column property="hcmoExpensesId.curTypeValueForTotalAmountField" title="${HTotalAmount}" style="width:130px;" maxLength="30"/>
		    <display:column property="curTypeValueForReImbAmountField" title="${HReimburse}" format=" $ {0,number,0.00}"/>
	<!--	    <s:if test="#session.ReImbursementEmptyValue == '0.0'">-->
	<!--	     	<display:column property="hcmoExpensesId.curTypeValueForReImbAmountEmptyValue" title="${HReimburse}"/>-->
	<!--	    </s:if>-->
	<!--	    <s:elseif test="#session.ReImbursementStatusValue == 'Reimburse'">-->
	<!--	    	<display:column property="hcmoExpensesId.curTypeValueForReImbAmountField" title="${HReimburse}"/>-->
	<!--	    </s:elseif>-->
	<!--	    <s:else></s:else>-->
		    <display:column property="approvalStatus" title="${HApprovalStatus}" headerClass="sortable"/>
		  </display:table>
	  </div>		   
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportExpensesResultsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    
   