<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Vendor_div">
<div class="submenu_bg">
	<sj:a href="setUpForInsertOrUpdateVendor.action" targets="submenu_Vendor_div" indicator="indicatorSubMenuVendorViewId_div" cssClass="link"><s:text name="MTIAddVendor" /></sj:a> |
	<sj:a href="getAllVendor.action" targets="submenu_Vendor_div" indicator="indicatorSubMenuVendorViewId_div" cssClass="link"><s:text name="MTIViewVendor"/></sj:a> |
	<sj:a href="vendorSearchForm.action" targets="submenu_Vendor_div" indicator="indicatorSubMenuVendorViewId_div" cssClass="link"><s:text name="MTISearchVendor"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuVendorViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.vendor.view" />
			  </td>
	       </tr>
           <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.taxId" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.taxId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.vendorName" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.vendorName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.vendorShName" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.vendorShName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.contactPerson1" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.contactPerson1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.emailAddress1" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.emailAddress1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.contactPerson2" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.contactPerson2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.emailAddress2" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.emailAddress2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.userName" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.userName"/></td>
			</tr>
<!--			<tr>-->
<!--				<td class="inputtext"><s:text name="label.header.vendor.password" /></td>-->
<!--				<td class="employeedisplaytd"><s:property value="vendor.password"/></td>-->
<!--			</tr>-->
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.companyName" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.companyName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.address1" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.address1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.address2" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.address2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.country.name" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.country.countryName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.state" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.state"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.city" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.city"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.zipCode" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.zipCode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.phone" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.phone"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.extension" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.extension"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.fax" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.fax"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.website" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.webSite"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.custom1" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.custom1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.vendor.custom2" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.custom2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="vendor.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="vendor.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="vendor.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>