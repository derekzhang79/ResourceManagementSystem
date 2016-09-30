<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<div id="submenu_ExpenseTypeSearchForm_div">
<div class="submenu_bg">
	<s:if test="#session.EXPENSETYPE_ADD == true">
		<sj:a href="setUpInsertOrUpdateExpType.action" targets="submenu_ExpenseTypeSearchForm_div" indicator="indicatorSubMenuExpenseTypeSearchId_div" cssClass="link"><s:text name="MTIAddExpenseType" /></sj:a> |
	</s:if>
	<s:if test="#session.EXPENSETYPE_VIEW == true">
		<sj:a href="getAllExpType.action" targets="submenu_ExpenseTypeSearchForm_div" indicator="indicatorSubMenuExpenseTypeSearchId_div" cssClass="link"><s:text name="MTIViewExpenseType"/></sj:a> |
		<sj:a href="expTypeSearchForm.action" targets="submenu_ExpenseTypeSearchForm_div" indicator="indicatorSubMenuExpenseTypeSearchId_div" cssClass="link"><s:text name="MTISearchExpenseType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuExpenseTypeSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="expTypeSearchResult">
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						 <s:text name="label.title.expenseType.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         <tr><td class="inputtext"><s:text name="label.header.expenseType.name"/></td>
       	         <td class="employeeinputtd"><s:textfield name="expType.name" cssClass="employeeinput"/></td>
       	         <td class="inputerrormessage"></td>
	         </tr>
	            </table></td></tr></table></td></tr></table></td></tr></table>
	    		<table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorExpenseTypeFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ExpenseTypeSearchForm_div" indicator="indicatorExpenseTypeFormSearchId_div"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 
   	</s:form>
</div>