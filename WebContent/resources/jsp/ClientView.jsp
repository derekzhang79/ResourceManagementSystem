
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_Client_div">
<div class="submenu_bg">
	<s:if test="#session.CLIENT_ADD == true">
		<s:if test="#session.CHECK_CLIENT == 'AVAILABLE'"></s:if>
		<s:elseif test="#session.CHECK_CLIENT == 'NOT_AVAILABLE'">
			<sj:a href="setUpClient.action" targets="submenu_Client_div" indicator="indicatorSubMenuClientViewId_div" cssClass="link"><s:text name="MTIAddClient" /></sj:a> |
		</s:elseif>
	</s:if>
	<s:if test="#session.CLIENT_VIEW == true">
		<sj:a href="getAllClient.action" targets="submenu_Client_div" indicator="indicatorSubMenuClientViewId_div" cssClass="link"><s:text name="MTIViewClient"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuClientViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.client.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.client.hcmoclientId" /></td>
						<td class="employeedisplaytd"><s:property value="cli.hcmoclientId"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.client.companyName" /></td>
						<td class="employeedisplaytd"><s:property value="cli.companyName" />
						</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.client.noOfEmp" /></td>
						<td class="employeedisplaytd"><s:property value="cli.noOfEmp" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.city" /></td>
						<td class="employeedisplaytd"><s:property value="cli.city" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.country.name" /></td>
						<td class="employeedisplaytd"><s:property value="cli.country.countryName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.client.state" /></td>
						<td class="employeedisplaytd"><s:property value="cli.state"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.address1" /></td>
						<td class="employeedisplaytd"><s:property value="cli.address1"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.address2" /></td>
						<td class="employeedisplaytd"><s:property value="cli.address2"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.phone" /></td>
						<td class="employeedisplaytd"> <s:property value="cli.phone"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.fax" /></td>
						<td class="employeedisplaytd"><s:property value="cli.fax"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.client.taxId" /></td>
						<td class="employeedisplaytd"><s:property value="cli.taxId"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.comments" /></td>
						<td class="employeedisplaytd"><s:property value="cli.comments"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.created" /></td>
						<td class="employeedisplaytd"><s:date name="cli.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					<tr><td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
						<td class="employeedisplaytd"><s:property value="cli.createdBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.updated" /></td>
						<td class="employeedisplaytd"><s:date name="cli.updated" format="%{getText('label.date.simpleDateFormatWithTime')}"/></td>
					</tr>
			 		<tr>
						<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
						<td class="employeedisplaytd"><s:property value="cli.updatedBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
						<td class="employeedisplaytd"><s:checkbox name="cli.isActive" label="isActive" disabled="true"></s:checkbox></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>