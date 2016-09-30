<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LicenseViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.LICENSE_ADD == true">
			<sj:a href="setUpLicense.action" targets="submenu_LicenseViewId_div" indicator="indicatorSubMenuLicenseViewId_div" cssClass="link"><s:text name="MTIAddLicense" /></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW == true">
			<sj:a href="getAllLicense.action" targets="submenu_LicenseViewId_div" indicator="indicatorSubMenuLicenseViewId_div" cssClass="link"><s:text name="MTIViewLicense"/></sj:a> |
			<sj:a href="licenseSearchForm.action" targets="submenu_LicenseViewId_div" indicator="indicatorSubMenuLicenseViewId_div" cssClass="link"><s:text name="MTISearchLicense"/></sj:a>			
		</s:if>
	</div>
	<br/>
		<img id="indicatorSubMenuLicenseViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
			<s:text name="label.title.license.view" />
		  </td>
       </tr>
       <tr>
            <td class="forminner"><table class="tablealign">
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.license.empLicenseId" /></td>
			<td class="employeedisplaytd"><s:property value="license.empLicenseId"/></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.header.common.empName" /></td>
			<td class="employeedisplaytd"><s:property value="license.empIdObj.empFullName" /></td>
		</tr>
		<s:if test="#session.LICENSE_LICENSENUMBER_VIEW == true">
			<tr>
				<td class="inputtext"><s:text name="label.header.license.licNumber" /></td>
				<td class="employeedisplaytd"><s:property value="license.licenseNumber" /></td>
			</tr>
		</s:if>
		<s:if test="#session.LICENSE_LICENSEDATE_VIEW == true">
			<tr>
				<td class="inputtext"><s:text name="label.header.license.licenseDate" /></td>
				<td class="employeedisplaytd"><s:date name="license.licenseDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
		</s:if>
		<s:if test="#session.LICENSE_LICENSERENEWALDATE_VIEW == true">
			<tr>
				<td class="inputtext"><s:text name="label.header.license.renewalDate" /></td>
				<td class="employeedisplaytd"><s:date name="license.licenseRenewalDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
		</s:if>
		<s:if test="#session.LICENSE_LICENSEDESC_VIEW == true">
			<tr>
				<td class="inputtext"><s:text name="label.header.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="license.licenseDesc"/></td>
			</tr>
		</s:if>
		<tr>
			<td class="inputtext"><s:text name="label.header.common.created" /></td>
			<td class="employeedisplaytd"><s:date name="license.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
			<td class="employeedisplaytd"><s:property value="license.createdBy.empFullName" /></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.header.common.updated" /></td>
			<td class="employeedisplaytd"><s:date name="license.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
		</tr>
	 	<tr>
			<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
			<td class="employeedisplaytd"><s:property value="license.updatedBy.empFullName" /></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
			<td class="employeedisplaytd"><s:checkbox name="license.isActive" label="isActive" disabled="true"></s:checkbox></td>
		</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
	<br />
</s:form>
</div>