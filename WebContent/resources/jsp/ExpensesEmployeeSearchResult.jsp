<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.EmployeeExpensesVO"%>

<div id="submenu_ExpenseHistory_SearchResult_Emp_div">
<img id="indicatorExpenseEmpResultTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expenseshistory.search.result" /></span></li></div>
<br />


	  <s:text name="label.header.empexpenses.expenseid" var="HExpenseId"></s:text>
	  <s:text name="label.header.empexpenses.EmployeeName" var="HEmpName"></s:text>
	  <s:text name="label.header.empexpenses.createdDate" var="HCreateDate"></s:text>
	  <s:text name="label.header.empexpenses.totalAmount" var="HTotalAmt"></s:text>
	  <s:text name="label.header.empexpenses.reimbursementAmount" var="HReimburseAmt"></s:text>
	  <s:text name="label.header.empexpenses.approvalStatus" var="HAppStatus"></s:text>
	  <s:text name="label.header.empexpenses.history" var="HHistory"></s:text>
	  
	  <display:table class="tableborder" id="expenseEmployeeListId" name="expenseEmployeeList" requestURI="getExpEmployeeHistorySearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	       
	       <%
	    	try{
	    		String shcmoExpensesId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseEmployeeListId")).getHcmoExpensesId().toString();
	        	request.setAttribute("HcmoExpensesId", shcmoExpensesId);
	        	}catch(NullPointerException ne){
	        }    	
	    %>
	      <display:column property="hcmoExpensesId" title="${HExpenseId}" headerClass="sortable"/>
	      <display:column property="expenseEmpName" title="${HEmpName}" headerClass="sortable"/>
	      <display:column property="createdDate" title="${HCreateDate}" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	      <display:column property="curTypeValueForTotalAmountField" title="${HTotalAmt}" headerClass="sortable" format=" $ {0,number,0.00}"/>
	      <display:column property="curTypeValueForReImbAmountField" title="${HReimburseAmt}" headerClass="sortable" format=" $ {0,number,0.00}"/>
	      <display:column property="status" title="${HAppStatus}" headerClass="sortable"/>
	      
	      <display:column title="${HHistory}" class="viewedit" media="html">
				<s:url var="getExpEmployeeHistoryDetails" action="getExpEmployeeHistoryDetails">
					<s:param name="empExpense.hcmoExpensesId" value="#request.HcmoExpensesId"/>
				</s:url>
				<sj:submit href="%{getExpEmployeeHistoryDetails}" targets="submenu_ExpenseHistory_SearchResult_Emp_div" indicator="indicatorExpenseEmpResultTabs" key="label.common.message.approvalHistory"></sj:submit>
		 </display:column>
	      
	     <display:setProperty name="export.csv.filename" value="ExpenseHistory.csv"/>
		 <display:setProperty name="export.excel.filename" value="ExpenseHistory.xls"/>
		 <display:setProperty name="export.xml.filename" value="ExpenseHistory.xml"/>
      </display:table>
      <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpExpenseEmpSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="getExpEmployeeHistorySearchForm" action="getExpEmployeeHistorySearchForm"></s:url>
	   			 <sj:submit href="%{getExpEmployeeHistorySearchForm}"  key="button.label.ExpBackButton" cssClass="submitbutton117" targets="submenu_ExpenseHistory_SearchResult_Emp_div" indicator="indicatorEmpExpenseEmpSearchId_div"/>
   		    </td>
   		</tr>
   	  </table>
</div>