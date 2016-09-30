<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_JobTitleViewId_div">
<div class="submenu_bg">
	<s:if test="#session.JOBTITLE_ADD == true">
		<sj:a href="setUpJobTitle.action" targets="submenu_JobTitleViewId_div" indicator="indicatorSubMenuJobTitleViewId_div" cssClass="link"><s:text name="MTIAddJobTitle" /></sj:a> |
	</s:if>
	<s:if test="#session.JOBTITLE_VIEW == true">
		<sj:a href="getAllJobTitle.action" targets="submenu_JobTitleViewId_div" indicator="indicatorSubMenuJobTitleViewId_div" cssClass="link"><s:text name="MTIViewJobTitle"/></sj:a> |
		<sj:a href="jobTitleSearchForm.action" targets="submenu_JobTitleViewId_div" indicator="indicatorSubMenuJobTitleViewId_div" cssClass="link"><s:text name="MTISearchJobTitle"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuJobTitleViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.jobTitle.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.jobTitle.jobTitleId" /></td>
					<td class="employeedisplaytd"><s:property value="jobTitle.jobTitleId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.salaryGrade.name" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.salaryGradeIdObj.salaryName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.jobTitle.name" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.jobTitleName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.description" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.jobTitleDesc"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.comments" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.jobTitleComments"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="jobTitle.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="jobTitle.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="jobTitle.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="jobTitle.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>