<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Customer_div">
<div class="submenu_bg">
	<s:if test="#session.CUSTOMER_ADD == true">
		<sj:a href="setUpCustomer.action" targets="submenu_Customer_div" indicator="indicatorSubMenuCustomerViewId_div" cssClass="link"><s:text name="MTIAddCustomer" /></sj:a> |
	</s:if>
	<s:if test="#session.CUSTOMER_VIEW == true">
		<sj:a href="getAllCustomer.action" targets="submenu_Customer_div" indicator="indicatorSubMenuCustomerViewId_div" cssClass="link"><s:text name="MTIViewCustomer"/></sj:a> |
		<sj:a href="customerSearchForm.action" targets="submenu_Customer_div" indicator="indicatorSubMenuCustomerViewId_div" cssClass="link"><s:text name="MTISearchCustomer"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCustomerViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.customer.view" />
			 </td>
	      </tr>
	      <tr>
	           <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.customer.customerId" /></td>
				<td class="employeedisplaytd"><s:property value="cust.customerId"/></td>
			</tr>
	 	  	<tr>
				<td class="inputtext"><s:text name="label.header.customer.name" /></td>
				<td class="employeedisplaytd"><s:property value="cust.customerName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.customer.contactName" /></td>
				<td class="employeedisplaytd"><s:property value="cust.contactName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.email" /></td>
				<td class="employeedisplaytd"><s:property value="cust.email" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.address1" /></td>
				<td class="employeedisplaytd"><s:property value="cust.addr1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.customer.address2" /></td>
				<td class="employeedisplaytd"><s:property value="cust.addr2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.phone" /></td>
				<td class="employeedisplaytd"><s:property value="cust.phoneNumber"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.fax" /></td>
				<td class="employeedisplaytd"><s:property value="cust.fax"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="cust.desc"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="cust.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="cust.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="cust.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="cust.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="cust.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table>
		<br />
	</td>
	</tr></table></td></tr></table></td></tr></table>
</s:form>
</div>