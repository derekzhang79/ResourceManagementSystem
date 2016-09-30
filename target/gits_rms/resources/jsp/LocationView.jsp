<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LocationViewId_div">
<div class="submenu_bg">
	<s:if test="#session.LOCATION_ADD == true">
		<sj:a href="setUpLocation.action" targets="submenu_LocationViewId_div" indicator="indicatorSubMenuLocationViewId_div" cssClass="link"><s:text name="MTIAddLocation" /></sj:a> |
	</s:if>
	<s:if test="#session.LOCATION_VIEW == true">
		<sj:a href="getAllLocation.action" targets="submenu_LocationViewId_div" indicator="indicatorSubMenuLocationViewId_div" cssClass="link"><s:text name="MTIViewLocation"/></sj:a> |
		<sj:a href="locationSearchForm.action" targets="submenu_LocationViewId_div" indicator="indicatorSubMenuLocationViewId_div" cssClass="link"><s:text name="MTISearchLocation"/></sj:a>
	</s:if>

</div>
<br/>
<img id="indicatorSubMenuLocationViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.location.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.location.hcmolocationId" /></td>
				<td class="employeedisplaytd"><s:property value="loc.hcmolocationId"/></td>
			</tr>
			<tr>	
				<td class="inputtext"><s:text name="label.header.location.name" /></td>
				<td class="employeedisplaytd"><s:property value="loc.locationName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.country.name" /></td>
				<td class="employeedisplaytd"><s:property value="loc.country.countryName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.city" /></td>
				<td class="employeedisplaytd"><s:property value="loc.city"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.name" /></td>
				<td class="employeedisplaytd"><s:property value="loc.region" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.address1" /></td>
				<td class="employeedisplaytd"><s:property value="loc.address1" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.address2" /></td>
				<td class="employeedisplaytd"><s:property value="loc.address2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.zipCode" /></td>
				<td class="employeedisplaytd"><s:property value="loc.zipcode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.phone" /></td>
				<td class="employeedisplaytd"><s:property value="loc.phone"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.fax" /></td>
				<td class="employeedisplaytd"><s:property value="loc.fax"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.comments" /></td>
				<td class="employeedisplaytd"><s:property value="loc.comments"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="loc.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="loc.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="loc.updated" format="%{getText('label.date.simpleDateFormatWithTime')}"/></td>
			</tr>
			 <tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="loc.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="loc.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>