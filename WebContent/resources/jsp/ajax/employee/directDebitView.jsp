<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_essDirectDebitView_div_id">
    <div class="submenu_bg">
	   <s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	   <s:url var="remoteDirDebForm" value="/setUpEmpDirectDebit.action">
		 <s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	   </s:url>
	   <s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	   <s:url var="remoteDirDebView" value="/getEmployeeAllDirectDebit.action">
		 <s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	    </s:url>
		<s:if test="#session.DIRECTDEBIT_ADD==true">
		 <sj:a href="%{remoteDirDebForm}" indicator="indicatorDirDebList" targets="submenu_essDirectDebitView_div_id" cssClass="link"><s:text name="label.header.directDebit.add"/></sj:a> |
		</s:if>
		<s:if test="#session.DIRECTDEBIT_VIEW==true">
			<sj:a href="%{remoteDirDebView}" indicator="indicatorDirDebList" targets="submenu_essDirectDebitView_div_id" cssClass="link"><s:text name="label.header.directDebit.view"/></sj:a>
		</s:if>
	</div>		
<br />

<img id="indicatorSubMenuEducations" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	       <tr>
	         <td><table class="employeeformiinertable">
	        <tr>
	          <td class="formtitle">
				 <s:text name="label.title.directDebit.view" />
			 </td>
	       </tr>
	       <tr>
                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.customer.empDirectDebitId" /></td>
				<td class="employeedisplaytd"><s:property value="dirDebit.empDirectDebitId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="dirDebit.empIdObj.empFullName" /></td>
			</tr>
			<s:if test="#session.DIRECTDEBIT_ROUTINGNUM_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.routingNo" /></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.routingNum" /></td>
				</tr>
			</s:if>
			<s:if test="#session.DIRECTDEBIT_ACCOUNT_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.account" /></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.account" /></td>
				</tr>
			</s:if>
			<s:if test="#session.DIRECTDEBIT_AMOUNT_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.amount" /> <s:text name="currencyTypeValue"/></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.amount" /></td>
				</tr>
			</s:if>
			<s:if test="#session.DIRECTDEBIT_ACCOUNTTYPE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.accountType" /></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.accountType"/></td>
				</tr>
			</s:if>
			<s:if test="#session.DIRECTDEBIT_TRANSACTIONTYPE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.transactionType" /></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.transactionType"/></td>
				</tr>
			</s:if>
			<s:if test="#session.DIRECTDEBIT_PREACCOUNTVALUE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.directDebit.preAccount" /></td>
					<td class="employeedisplaytd"><s:property value="dirDebit.preAccountValue"/></td>
				</tr>
			</s:if>		
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="dirDebit.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="dirDebit.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="dirDebit.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="dirDebit.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="dirDebit.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>	
	