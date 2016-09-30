
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeReportToViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.REPORTTO_ADD == true">
			<sj:a href="setUpEmployeeReportTo.action" targets="submenu_EmployeeReportToViewId_div" indicator="indicatorSubMenuEmployeeReportToViewId_div" cssClass="link"><s:text name="MTIAddEmployeeReportTo" /></sj:a> |
		</s:if>
		<s:if test="#session.REPORTTO_VIEW == true">
			<sj:a href="getAllEmployeeReportTo.action" targets="submenu_EmployeeReportToViewId_div" indicator="indicatorSubMenuEmployeeReportToViewId_div" cssClass="link"><s:text name="MTIViewEmployeeReportTo"/></sj:a>|
			<sj:a href="empReportToSearcForm.action" targets="submenu_EmployeeReportToViewId_div" indicator="indicatorSubMenuEmployeeReportToViewId_div" cssClass="link"><s:text name="MTISearchEmployeeReportTo"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeReportToViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.employeeReportTo.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeReportTo.empReportToId" /></td>
				<td class="employeedisplaytd"><s:property value="emp.empReportToId"/></td>
			</tr>
			<s:if test="#session.REPORTTO_SUPEMPNAME_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeReportTo.Supervisor" /></td>
					<td class="employeedisplaytd"><s:property value="emp.supEmpNumber.empFullName"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="emp.empIdObj.empFullName" /></td>
			</tr>
			<s:if test="#session.REPORTTO_REPORTINGMODEVALUE_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeReportTo.ReportingMode" /></td>
					<td class="employeedisplaytd"><s:property value="emp.empRepReportingModeValue" /></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="emp.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="emp.createdBy.empFullName" /></td>
			</tr>
			
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="emp.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="emp.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="emp.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>