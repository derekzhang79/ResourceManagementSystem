<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeStatusViewId_div">
<div class="submenu_bg">
	<s:if test="#session.EMPLOYEESTATUS_ADD == true">
	<sj:a href="setUpForInsertOrUpdateEmpStatus.action" targets="submenu_EmployeeStatusViewId_div" indicator="indicatorSubMenuEmployeeStatusViewId_div" cssClass="link"><s:text name="MTIAddEmployeeStatus" /></sj:a> |
</s:if>
<s:if test="#session.EMPLOYEESTATUS_VIEW == true">
	<sj:a href="getAllEmployeeStatus.action" targets="submenu_EmployeeStatusViewId_div" indicator="indicatorSubMenuEmployeeStatusViewId_div" cssClass="link"><s:text name="MTIViewEmployeeStatus"/></sj:a> |
	<sj:a href="employeeStatusSearchForm.action" targets="submenu_EmployeeStatusViewId_div" indicator="indicatorSubMenuEmployeeStatusViewId_div" cssClass="link"><s:text name="MTISearchEmployeeStatus"/></sj:a>
</s:if>
</div>
<br/>
<img id="indicatorSubMenuEmployeeStatusViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.empStatus.view" />
			  </td>
	       </tr>
           <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empStatus.empStatusId" /></td>
				<td class="employeedisplaytd"><s:property value="empStatus.empStatusId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empStatus.name" /></td>
				<td class="employeedisplaytd"><s:property value="empStatus.statusName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="empStatus.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="empStatus.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="empStatus.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="empStatus.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="empStatus.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>