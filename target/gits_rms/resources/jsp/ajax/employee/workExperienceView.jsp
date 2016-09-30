<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_essWorkExperienceView_div_id">
   <div class="submenu_bg">
	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceForm" value="/setUpEmpWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceView" value="/getEmployeeAllWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
		<s:if test="#session.WORKEXPERIENCE_ADD==true">
		    <sj:a href="%{remoteWorkExperienceForm}" indicator="indicatorWorkExpList" targets="submenu_essWorkExperienceView_div_id" cssClass="link"><s:text name="label.header.empExperience.add"/></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW==true">
			<sj:a href="%{remoteWorkExperienceView}" indicator="indicatorWorkExpList" targets="submenu_essWorkExperienceView_div_id" cssClass="link"><s:text name="label.header.empExperience.view"/></sj:a>
		</s:if>
	</div>		
<br />	

<img id="indicatorWorkExpList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

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