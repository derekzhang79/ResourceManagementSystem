
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_DepartmentViewId_div">
<div class="submenu_bg">
	<s:if test="#session.DEPARTMENT_ADD == true">
		<sj:a href="setUpDepartment.action" targets="submenu_DepartmentViewId_div" indicator="indicatorSubMenuDepartmentViewId_div" cssClass="link"><s:text name="MTIAddDepartment" /></sj:a> |
	</s:if>
	<s:if test="#session.DEPARTMENT_VIEW == true">
		<sj:a href="getAllDepartment.action" targets="submenu_DepartmentViewId_div" indicator="indicatorSubMenuDepartmentViewId_div" cssClass="link"><s:text name="MTIViewDepartment"/></sj:a> |
		<sj:a href="deptSearchForm.action" targets="submenu_DepartmentViewId_div" indicator="indicatorSubMenuDepartmentViewId_div" cssClass="link"><s:text name="MTISearchDepartment"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuDepartmentViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.department.view" />
		                  </td>
	                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.department.departmentId" /></td>
				<td class="employeedisplaytd"><s:property value="dept.hcmoDepartmentId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.department.name" /></td>
				<td class="employeedisplaytd"><s:property value="dept.deptName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="dept.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="dept.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="dept.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="dept.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="dept.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>