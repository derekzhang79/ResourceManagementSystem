<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.EmployeeExpensesVO"%>

<div id="tabExp">
<div class="textContent">
<div class="informationMessageSingle"><li><span><s:text name="label.title.expenseforapproval.list"/></span></li></div>	
<img id="indicatorExpForApprovalTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  		<s:text name="label.header.expenseforapproval.empName" var="HEmpName"></s:text>
	  		<s:text name="label.header.expenseforapproval.requestDate" var="HReqDate"></s:text>
	  		<s:text name="label.header.expenseforapproval.totalAmount" var="HtotalAmt"></s:text>
              
              <display:table class="tableborder" id="expenseListId" name="expenseList" requestURI="getAllExpensesForApproval.action" pagesize="${NO_OF_RECORDS}" sort="list" defaultsort="1" defaultorder="ascending">
              	<%
		    	try{
		    		String shcmoExpId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseListId")).getHcmoExpensesId().toString();
		        	request.setAttribute("HcmoExpId", shcmoExpId);
		        	
		        	String shcmoExpAttacId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseListId")).getExpAttachId().toString();
		        	request.setAttribute("HcmoExpAttachId", shcmoExpAttacId);
		        	
		        	
		        	}catch(NullPointerException ne){
		        }    	
	    	%>
              
             <display:column title="${HEmpName}" headerClass="sortable">
               <s:url var="getExpensesForApprovalEmployee" action="getExpensesForApprovalEmployee">
               		<s:param name="empExpenses.hcmoExpensesId" value="#request.HcmoExpId"/>
        	   </s:url> 
               <sj:a href="%{getExpensesForApprovalEmployee}" targets="tabExp" indicator="indicatorExpForApprovalTabs">
                ${expenseListId.hcmoEmployeeId.empFirstName}</sj:a>
             </display:column>
             
             <display:column property="createdDate" title="${HReqDate}"  headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
             <display:column property="curTypeValueForTotalAmountField" title="${HtotalAmt}" headerClass="sortable" format=" $ {0,number,0.00}"/>
             
                         
		</display:table>
	 
<br/>
</div></div>