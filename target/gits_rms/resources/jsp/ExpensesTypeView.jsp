<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_ExpenseTypeViewId_div">
<div class="submenu_bg">
	<s:if test="#session.EXPENSETYPE_ADD == true">
		<sj:a href="setUpInsertOrUpdateExpType.action" targets="submenu_ExpenseTypeViewId_div" indicator="indicatorSubMenuExpenseTypeViewId_div" cssClass="link"><s:text name="MTIAddExpenseType" /></sj:a> |
	</s:if>
	<s:if test="#session.EXPENSETYPE_VIEW == true">
		<sj:a href="getAllExpType.action" targets="submenu_ExpenseTypeViewId_div" indicator="indicatorSubMenuExpenseTypeViewId_div" cssClass="link"><s:text name="MTIViewExpenseType"/></sj:a> |
		<sj:a href="expTypeSearchForm.action" targets="submenu_ExpenseTypeViewId_div" indicator="indicatorSubMenuExpenseTypeViewId_div" cssClass="link"><s:text name="MTISearchExpenseType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuExpenseTypeViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form>
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
				   		<s:text name="label.title.expenseType.view"/>
					  </td>
	                </tr>
             <tr>
               <td class="forminner"><table class="tablealign">
             <tr>
	         	<td class="inputtext"><s:text name="label.header.expenseType.expensesTypeId"/></td>
	         	<td class="employeedisplaytd"><s:property value="expType.hcmoExpensesTypeId"/></td>
	         </tr>  
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.expenseType.name"/></td>
	         	<td class="employeedisplaytd"><s:property value="expType.name"/></td>
	         </tr>
         	 <tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="expType.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="expType.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="expType.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="expType.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="expType.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	    </table>
   		<br/>
	    </td></tr></table></td></tr></table></td></tr></table>
   	</s:form>
</div>