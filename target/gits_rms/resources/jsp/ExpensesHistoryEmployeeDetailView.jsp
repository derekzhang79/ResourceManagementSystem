<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.ExpenseStatusTrackerVO"%>

<div id="submenu_ExpenseAppHistoryResult_Emp_div">
       <div class="informationMessageSingle"><li><span><s:text name="label.title.expensesApprovalhistory.search.result" /></span></li></div>
        <s:text name="label.header.empexpenses.approvername" var="HApproverName"/>
        <s:text name="label.header.empexpenses.rajectedname" var="HRejectedName"/>
        <s:text name="label.header.empexpenses.rejectednotes" var="HRejectedNotes"/>
        <s:text name="label.header.empexpenses.reviewedname" var="HReviewedName"/>
        <s:text name="label.header.empexpenses.nextlevelname" var="HNextLevelName"/>
        <s:text name="label.header.empexpenses.accountantname" var="HAccountantname"/>
        <s:text name="label.header.empexpenses.status" var="HStatus"/>
        
     <display:table class="tableborder" id="expenseStatusTrackerEmployeeList" name="expenseStatusTrackerEmployeeList"  requestURI="" sort="list" defaultsort="1" defaultorder="ascending" export="false">
        
            <display:column property="approverId.empFirstName" title="${HApproverName}" sortable="false" headerClass="sortable"/>
		    <display:column property="rejectedId.empFirstName" title="${HRejectedName}" sortable="false" headerClass="sortable"/>
		    <display:column property="rejectedNotes" title="${HRejectedNotes}" sortable="false" headerClass="sortable" maxLength="10"/>
		    <display:column property="reviewedId.empFirstName" title="${HReviewedName}" sortable="false" headerClass="sortable"/>
		    <display:column property="nextLevelId.empFirstName" title="${HNextLevelName}" sortable="false" headerClass="sortable"/>
		    <display:column property="accountantId.empFirstName" title="${HAccountantname}" sortable="false" headerClass="sortable"/>
		    <display:column property="approvalStatus" title="${HStatus}" sortable="false" headerClass="sortable"/>
            
     </display:table>
     <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpExpenseHistory" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="getExpEmployeeHistorySearchForm" action="getExpEmployeeHistorySearchForm"></s:url>
	   			 <sj:submit href="%{getExpEmployeeHistorySearchForm}"  key="button.label.ExpBackButton" cssClass="submitbutton117" targets="submenu_ExpenseAppHistoryResult_Emp_div" indicator="indicatorEmpExpenseHistory"/>
   		    </td>
   		</tr>
   	  </table>
</div>