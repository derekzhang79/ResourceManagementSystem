<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Country_div">
<div class="submenu_bg">
	<s:if test="#session.COUNTRY_ADD == true">
		<sj:a href="setUpInsertOrUpdateCountry.action" targets="submenu_Country_div" indicator="indicatorSubMenuCountryViewId_div" cssClass="link"><s:text name="MTIAddCountry" /></sj:a> |
	</s:if>
	<s:if test="#session.COUNTRY_VIEW == true">
		<sj:a href="getAllCountry.action" targets="submenu_Country_div" indicator="indicatorSubMenuCountryViewId_div" cssClass="link"><s:text name="MTIViewCountry"/></sj:a> |
		<sj:a href="countrySearchForm.action" targets="submenu_Country_div" indicator="indicatorSubMenuCountryViewId_div" cssClass="link"><s:text name="MTISearchCountry"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCountryViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
							<s:text name="label.title.country.view" />
	                    </td>
	                </tr>
     			<tr>
	                <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.country.hcmocountryId" /></td>
				<td class="employeedisplaytd"><s:property value="cou.hcmocountryId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.country.name" /></td>
				<td class="employeedisplaytd"><s:property value="cou.countryName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.country.code" /></td>
				<td class="employeedisplaytd"><s:property value="cou.countryCode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="cou.description"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="cou.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="cou.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="cou.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	    	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="cou.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="cou.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>