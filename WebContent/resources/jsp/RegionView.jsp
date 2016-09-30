
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_RegionViewId_div">
<div class="submenu_bg">
	<s:if test="#session.REGION_ADD == true">
		<sj:a href="setUpInsertOrUpdateRegion.action" targets="submenu_RegionViewId_div" indicator="indicatorSubMenuRegionViewId_div" cssClass="link"><s:text name="MTIAddRegion" /></sj:a> |
	</s:if>
	<s:if test="#session.REGION_VIEW == true">
		<sj:a href="getAllRegion.action" targets="submenu_RegionViewId_div" indicator="indicatorSubMenuRegionViewId_div" cssClass="link"><s:text name="MTIViewRegion"/></sj:a> |
		<sj:a href="regionSearchForm.action" targets="submenu_RegionViewId_div" indicator="indicatorSubMenuRegionViewId_div" cssClass="link"><s:text name="MTISearchRegion"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRegionViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.region.view" />
			 </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.region.hcmoregionId" /></td>
				<td class="employeedisplaytd"><s:property value="reg.hcmoregionId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.name" /></td>
				<td class="employeedisplaytd"><s:property value="reg.name" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.country" /></td>
				<td class="employeedisplaytd"><s:property value="reg.country.countryName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.zipCode" /></td>
				<td class="employeedisplaytd"><s:property value="reg.zipCode" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.city" /></td>
				<td class="employeedisplaytd"><s:property value="reg.city" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.countyField" /></td>
				<td class="employeedisplaytd"><s:property value="reg.countyField"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.regionCode" /></td>
				<td class="employeedisplaytd"><s:property value="reg.regionCode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.areaCode" /></td>
				<td class="employeedisplaytd"><s:property value="reg.areaCode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.timeZone" /></td>
				<td class="employeedisplaytd"><s:property value="reg.timeZone"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.latitude" /></td>
				<td class="employeedisplaytd"><s:property value="reg.latitude"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.region.longitude" /></td>
				<td class="employeedisplaytd"><s:property value="reg.longitude"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="reg.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="reg.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="reg.updated" format="%{getText('label.date.simpleDateFormatWithTime')}"/></td>
			</tr>
			 <tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="reg.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="reg.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>