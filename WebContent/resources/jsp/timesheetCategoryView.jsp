<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimesheetCategoryViewId_div">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategory.action" targets="submenu_TimesheetCategoryViewId_div" indicator="indicatorSubMenuTimeSheetCategoryViewId_div" cssClass="link"><s:text name="MTAddTimeSheetCategory" /></sj:a> |
			<sj:a href="getAllTimeSheetCategory.action" targets="submenu_TimesheetCategoryViewId_div" indicator="indicatorSubMenuTimeSheetCategoryViewId_div" cssClass="link"><s:text name="MTViewTimeSheetCategory"/></sj:a> |
			<sj:a href="TimesheetCategorySearch.action" targets="submenu_TimesheetCategoryViewId_div" indicator="indicatorSubMenuTimeSheetCategoryViewId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategory"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetCategoryViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
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
								<s:text name="label.title.TimesheetCategory.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategory.timesheetcategoryId" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategory.hcmoTimesheetCategoryId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategoryList.name" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategory.name"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="timesheetCategory.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategory.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="timesheetCategory.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="timesheetCategory.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="timesheetCategory.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>