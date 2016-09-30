<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_HolidayViewId_div">
<div class="submenu_bg">
	<s:if test="#session.HOLIDAY_ADD == true">
		<sj:a href="setUpHoliday.action" targets="submenu_HolidayViewId_div" indicator="indicatorSubMenuHolidayViewId_div" cssClass="link"><s:text name="MTIAddHoliday" /></sj:a> |
	</s:if>
	<s:if test="#session.HOLIDAY_VIEW == true">
		<sj:a href="getAllHoliday.action" targets="submenu_HolidayViewId_div" indicator="indicatorSubMenuHolidayViewId_div" cssClass="link"><s:text name="MTIViewHoliday"/></sj:a> |
		<sj:a href="holidaySearchForm.action" targets="submenu_HolidayViewId_div" indicator="indicatorSubMenuHolidayViewId_div" cssClass="link"><s:text name="MTISearchHoliday"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuHolidayViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
						<s:text name="label.title.holiday.view" />
					  </td>
	                </tr>
           		<tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.holiday.holidayId" /></td>
				<td class="employeedisplaytd"><s:property value="holiday.holidayId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.holiday.description" /></td>
				<td class="employeedisplaytd"><s:property value="holiday.holidayDescription" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.holiday.date" /></td>
				<td class="employeedisplaytd"><s:date name="holiday.holidayDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.holiday.repeats" /></td>
				<td class="employeedisplaytd"><s:checkbox name="holiday.recurring" disabled="true"></s:checkbox></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.holiday.length" /></td>
				<td class="employeedisplaytd"><s:property value="holiday.lengthValue" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="holiday.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="holiday.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="holiday.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="holiday.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="holiday.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>