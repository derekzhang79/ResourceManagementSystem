<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Currency_div">
<div class="submenu_bg">
	<s:if test="#session.CURRENCY_ADD == true">
		<sj:a href="setUpCurrency.action" targets="submenu_Currency_div" indicator="indicatorSubMenuCurrencyViewId_div" cssClass="link"><s:text name="MTIAddCurrency"/></sj:a> |
	</s:if>
	<s:if test="#session.CURRENCY_VIEW == true">
		<sj:a href="getAllCurrency.action" targets="submenu_Currency_div" indicator="indicatorSubMenuCurrencyViewId_div" cssClass="link"><s:text name="MTIViewCurrency"/></sj:a> |
		<sj:a href="currencySearchForm.action" targets="submenu_Currency_div" indicator="indicatorSubMenuCurrencyViewId_div" cssClass="link"><s:text name="MTISearchCurrency"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCurrencyViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.currency.view" />
		                  </td>
	                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.currency.currencyId" /></td>
				<td class="employeedisplaytd"><s:property value="currency.hcmoCurrencyId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.currency.currencyType" /></td>
				<td class="employeedisplaytd"><s:property value="currency.currencyType"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="currency.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="currency.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="currency.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="currency.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="currency.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>