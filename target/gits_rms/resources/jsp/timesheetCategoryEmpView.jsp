<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimesheetCategoryEmpViewId_div">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategoryEmp.action" targets="submenu_TimesheetCategoryEmpViewId_div" indicator="indicatorSubMenuTimesheetCategoryEmpViewId_div" cssClass="link"><s:text name="MTAddTimeSheetCategoryEmp" /></sj:a> |
			<sj:a href="getAllTimeSheetCategoryEmp.action" targets="submenu_TimesheetCategoryEmpViewId_div" indicator="indicatorSubMenuTimesheetCategoryEmpViewId_div" cssClass="link"><s:text name="MTViewTimeSheetCategoryEmp"/></sj:a> |
			<sj:a href="TimesheetCategorySearchEmp.action" targets="submenu_TimesheetCategoryEmpViewId_div" indicator="indicatorSubMenuTimesheetCategoryEmpViewId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategoryEmp"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimesheetCategoryEmpViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
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
								<s:text name="label.title.TimesheetCategoryEmp.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategory.timesheetcategoryId" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategoryEmp.hcmoTimesheetCategoryEmpId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategoryEmp.TimecategorynameList" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategoryEmp.timesheetCategoryName.name"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategoryEmp.Empname" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategoryEmp.employeeName.empFirstName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="timesheetCategoryEmp.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategoryEmp.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="timesheetCategoryEmp.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategoryEmp.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="timesheetCategoryEmp.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>