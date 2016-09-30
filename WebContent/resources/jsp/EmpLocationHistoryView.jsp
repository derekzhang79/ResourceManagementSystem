
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeLocationHistoryViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.LOCATIONHISTORY_ADD == true">
			<sj:a href="setUpEmpLocationHistory.action" targets="submenu_EmployeeLocationHistoryViewId_div" indicator="indicatorSubMenuEmployeeLocationHistoryViewId_div" cssClass="link"><s:text name="MTIAddEmployeeLocationHistory" /></sj:a> |
		</s:if>
		<s:if test="#session.LOCATIONHISTORY_VIEW == true">
			<sj:a href="getAllEmpLocationHistory.action" targets="submenu_EmployeeLocationHistoryViewId_div" indicator="indicatorSubMenuEmployeeLocationHistoryViewId_div" cssClass="link"><s:text name="MTIViewEmployeeLocationHistory"/></sj:a>|
			<sj:a href="empLocHistSearchForm.action" targets="submenu_EmployeeLocationHistoryViewId_div" indicator="indicatorSubMenuEmployeeLocationHistoryViewId_div" cssClass="link"><s:text name="MTISearchEmployeeLocationHistory"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeLocationHistoryViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
						<s:text name="label.title.empLocationHistory.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	            <tr>
					<td class="inputtext"><s:text name="label.header.empLocationHistory.hcmoEmpLocationId" /></td>
					<td class="employeedisplaytd"><s:property value="elhist.hcmoEmpLocHistoryId" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.employee.name" /></td>
					<td class="employeedisplaytd"><s:property value="elhist.empIdObj.empFullName" /></td>
				</tr>
				<s:if test="#session.LOCATIONHISTORY_LOCATIONNAME_VIEW == true">
					<tr>
						<td class="inputtext"><s:text name="label.header.location.name" /></td>
						<td class="employeedisplaytd"><s:property value="elhist.locationIdObj.locationName"/></td>
					</tr>
				</s:if>
				<s:if test="#session.LOCATIONHISTORY_COMPANYNAME_VIEW == true">
					<tr>
						<td class="inputtext"><s:text name="label.header.client.name" /></td>
						<td class="employeedisplaytd"><s:property value="elhist.clientIdObj.companyName"/></td>
					</tr>
				</s:if>
				<s:if test="#session.LOCATIONHISTORY_STARTDATE_VIEW == true">
					<tr>
						<td class="inputtext"><s:text name="label.common.startDate" /></td>
						<td class="employeedisplaytd"><s:date name="elhist.startDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
				</s:if>
				<s:if test="#session.LOCATIONHISTORY_ENDDATE_VIEW == true">
					<tr>
						<td class="inputtext"><s:text name="label.common.endDate" /></td>
						<td class="employeedisplaytd"><s:date name="elhist.endDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
				</s:if>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.created" /></td>
					<td class="employeedisplaytd"><s:date name="elhist.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
					<td class="employeedisplaytd"><s:property value="elhist.createdBy.empFullName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.updated" /></td>
					<td class="employeedisplaytd"><s:date name="elhist.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
				</tr>
			 	<tr>
					<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
					<td class="employeedisplaytd"><s:property value="elhist.updatedBy.empFullName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
					<td class="employeedisplaytd"><s:checkbox name="elhist.isActive" label="isActive" disabled="true"></s:checkbox></td>
				</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>