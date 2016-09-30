
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EducationViewId_div">
		<div class="submenu_bg">
			<s:if test="#session.EDUCATION_ADD == true">
				<sj:a href="setUpEducation.action" targets="submenu_EducationViewId_div" indicator="indicatorSubMenuEducationViewId_div" cssClass="link"><s:text name="MTIAddEducation" /></sj:a> |
			</s:if>
			<s:if test="#session.EDUCATION_VIEW == true">
				<sj:a href="getAllEducation.action" targets="submenu_EducationViewId_div" indicator="indicatorSubMenuEducationViewId_div" cssClass="link"><s:text name="MTIViewEducation"/></sj:a> |
				<sj:a href="educationSearchForm.action" targets="submenu_EducationViewId_div" indicator="indicatorSubMenuEducationViewId_div" cssClass="link"><s:text name="MTISearchEducation"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuEducationViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.education.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.education.empEducationId" /></td>
				<td class="employeedisplaytd"><s:property value="edu.empEducationId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="edu.empIdObj.empFullName" /></td>
			</tr>
			<s:if test="#session.EDUCATION_EDUMAJOR_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.education.major" /></td>
					<td class="employeedisplaytd"><s:property value="edu.eduMajor" /></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUYEAR_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.education.year" /></td>
					<td class="employeedisplaytd"><s:property value="edu.eduYear" /></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUGRADE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.education.grade" /></td>
					<td class="employeedisplaytd"><s:property value="edu.eduGrade" /></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUSTARTDATE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.common.startDate" /></td>
					<td class="employeedisplaytd"><s:date name="edu.eduStartDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUENDDATE_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.common.enddate" /></td>
					<td class="employeedisplaytd"><s:date name="edu.eduEndDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUUNIVERSITY_VIEW == true">
				<tr>
					<td class="inputtext"><s:text name="label.header.education.univ" /></td>
					<td class="employeedisplaytd"><s:property value="edu.eduUniversity"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EDUCATION_EDUDEGREE_VIEW == true">		
				<tr>
					<td class="inputtext"><s:text name="label.header.education.degree" /></td>
					<td class="employeedisplaytd"><s:property value="edu.eduDegree"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="edu.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="edu.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="edu.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="edu.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="edu.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
</s:form>
</div>