<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ExpenseTypeFormId_div">
<div class="submenu_bg">
	<s:if test="#session.EXPENSETYPE_ADD == true">
		<sj:a href="setUpInsertOrUpdateExpType.action" targets="submenu_ExpenseTypeFormId_div" indicator="indicatorSubMenuExpenseTypeFormId_div" cssClass="link"><s:text name="MTIAddExpenseType" /></sj:a> |
	</s:if>
	<s:if test="#session.EXPENSETYPE_VIEW == true">
		<sj:a href="getAllExpType.action" targets="submenu_ExpenseTypeFormId_div" indicator="indicatorSubMenuExpenseTypeFormId_div" cssClass="link"><s:text name="MTIViewExpenseType"/></sj:a> |
		<sj:a href="expTypeSearchForm.action" targets="submenu_ExpenseTypeFormId_div" indicator="indicatorSubMenuExpenseTypeFormId_div" cssClass="link"><s:text name="MTISearchExpenseType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuExpenseTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateExpType">
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="expType==null || expType.hcmoExpensesTypeId == null">
							 <s:text name="label.title.expenseType.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.expenseType.edit"/>
						   </s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         			<tr>
	         				<td class="inputtext"><s:text name="label.form.fields.expenseType.name"/></td>
	         	        	<td class="employeeinputtd"><s:textfield name="expType.name" cssClass="employeeinput"/></td>
	         	        	<td class="inputerrormessage"><s:fielderror fieldName="expType.name" /> </td>
	         			</tr>
	            <s:hidden name="expType.hcmoExpensesTypeId"/>
	            </table></td></tr></table></td></tr></table></td></tr></table>
	    		<table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorExpenseTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ExpenseTypeFormId_div" indicator="indicatorExpenseTypeFormId_div"/>
	    		    </td>
	    		   <s:if test="expType==null || expType.hcmoExpensesTypeId==null">
				        <td>
		    		    	<s:url var="resetExpType" action="resetExpType"></s:url>
		    	            <sj:submit href="%{resetExpType}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_ExpenseTypeFormId_div" indicator="indicatorExpenseTypeFormId_div"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	       </s:else>
	    		 </tr>
	    </table> 
   	</s:form>
</div>