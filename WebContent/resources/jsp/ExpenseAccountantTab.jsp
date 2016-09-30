<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmployeeExpensesVO"%>

<div id="expenseAccTabDivId">
<s:form>
<div class="textContent">
<jsp:include page="common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expenseaccountant.list"/></span></li></div>	
 <img id="indicatorExpenseAccountantTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.empexpenses.expenseid" var="HExpenseId"></s:text>
	  <s:text name="label.header.expenseforapproval.empName" var="HEmpName"></s:text>
	  <s:text name="label.header.expenseforapproval.totalAmount" var="HTotalAmount"></s:text>
	  
	   <display:table class="tableborder" id="expenseListId" name="expenseList" pagesize="${NO_OF_RECORDS}" requestURI="getAccountantTab.action" sort="list" defaultsort="1" defaultorder="ascending">
	        <%
	    	try{
	    		String sExpId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseListId")).getHcmoExpensesId().toString();
	        	request.setAttribute("ExpenseId", sExpId);
	        	}catch(NullPointerException ne){
	        }    	
	    %>
	   
	   		 <display:column title="${HExpenseId}" headerClass="sortable">
				<s:url var="getAccountExpensesDetails" action="getAccountExpensesDetails">
					<s:param name="empExpenses.hcmoExpensesId" value="#request.ExpenseId"></s:param>
				</s:url>
				<sj:a href="%{getAccountExpensesDetails}" targets="expenseAccTabDivId" indicator="indicatorExpenseAccountantTabs">${expenseListId.hcmoExpensesId}</sj:a>
			</display:column>
			
			<display:column title="${HEmpName}" headerClass="sortable">
				<s:url var="getAccountExpensesDetails" action="getAccountExpensesDetails">
					<s:param name="empExpenses.hcmoExpensesId" value="#request.ExpenseId"></s:param>
				</s:url>
				<sj:a href="%{getAccountExpensesDetails}" targets="expenseAccTabDivId" indicator="indicatorExpenseAccountantTabs">${expenseListId.hcmoEmployeeId.empFirstName}</sj:a>
			</display:column>
			
		  <display:column property="curTypeValueForTotalAmountField" title="${HTotalAmount}" headerClass="sortable" format=" $ {0,number,0.00}"/>
		  
		 
	   </display:table>
	   
<!--<table align=center class="borderAll">-->
<!--    <tr>-->
<!--    	<th><s:text name="label.header.empexpenses.expenseid"/></th>-->
<!--        <th><s:text name="label.header.expenseforapproval.empName"/></th>-->
<!--        <th><s:text name="label.header.expenseforapproval.totalAmount"/> <s:text name="currencyTypeValue"/></th>-->
<!--        -->
<!--    </tr>-->
<!--    <s:iterator value="expenseList" status="status">-->
<!--        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">-->
<!--        	<td class="nowrap">-->
<!--			<s:url var="getAccountExpensesDetails" action="getAccountExpensesDetails">-->
<!--               <s:param name="empExpenses.hcmoExpensesId" value="hcmoExpensesId"/>-->
<!--               -->
<!--           </s:url> -->
<!--                <sj:a href="%{getAccountExpensesDetails}" targets="tabAcnt"  indicator="indicatorTabs"><s:property value="hcmoExpensesId"/></sj:a>-->
<!--			</td>-->
<!--        	-->
<!--        	<td class="nowrap">-->
<!--			<s:url var="getAccountExpensesDetails" action="getAccountExpensesDetails">-->
<!--               <s:param name="empExpenses.hcmoExpensesId" value="hcmoExpensesId"/>-->
<!--               -->
<!--           </s:url> -->
<!--                <sj:a href="%{getAccountExpensesDetails}" targets="tabAcnt" indicator="indicatorTabs"><s:property value="hcmoEmployeeId.empFirstName"/></sj:a>-->
<!--			</td>-->
<!--			-->
<!--            <td class="nowrap"><s:text name="currencyTypeValue"/> <s:property value="totalAmount"/></td>-->
<!--        </tr> -->
<!--        -->
<!--	 </s:iterator>-->
<!--</table>-->
<br/>
	</div>
	</s:form>
</div>