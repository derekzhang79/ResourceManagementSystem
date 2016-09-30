<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_WorkExperienceViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.WORKEXPERIENCE_ADD == true">
			<sj:a href="setUpWorkExperience.action" targets="submenu_WorkExperienceViewId_div" indicator="indicatorSubMenuWorkExperienceViewId_div" cssClass="link"><s:text name="MTIAddWorkExperience" /></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW == true">
			<sj:a href="getAllWorkExperience.action" targets="submenu_WorkExperienceViewId_div" indicator="indicatorSubMenuWorkExperienceViewId_div" cssClass="link"><s:text name="MTIViewWorkExperience"/></sj:a> |
			<sj:a href="workExpSearchForm.action" targets="submenu_WorkExperienceViewId_div" indicator="indicatorSubMenuWorkExperienceViewId_div" cssClass="link"><s:text name="MTISearchWorkExperience"/></sj:a>
		</s:if>
	</div><br/>
	<img id="indicatorSubMenuWorkExperienceViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
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
				<s:text name="label.title.workExperience.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empExperience.empWorkExpId" /></td>
				<td class="employeedisplaytd"><s:property value="workexp.empWorkExpId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="workexp.empIdObj.empFullName" /></td>
			</tr>
			<s:if test="#session.WORKEXPERIENCE_EMPLOYEERNAME_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empExperience.employerName" /></td>
					<td class="employeedisplaytd"><s:property value="workexp.employeerName" /></td>
				</tr>
			</s:if>
			<s:if test="#session.WORKEXPERIENCE_EMPJOBTITLE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empExperience.jobTitle" /></td>
					<td class="employeedisplaytd"><s:property value="workexp.empJobTitle" /></td>
				</tr>
			</s:if>
			<s:if test="#session.WORKEXPERIENCE_FROMDATE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empExperience.fromDate" /></td>
					<td class="employeedisplaytd"><s:date name="workexp.fromDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.WORKEXPERIENCE_TODATE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empExperience.toDate" /></td>
					<td class="employeedisplaytd"><s:date name="workexp.toDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.WORKEXPERIENCE_COMMENTS_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.common.comments" /></td>
					<td class="employeedisplaytd"><s:property value="workexp.comments"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="workexp.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="workexp.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="workexp.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="workexp.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="workexp.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>