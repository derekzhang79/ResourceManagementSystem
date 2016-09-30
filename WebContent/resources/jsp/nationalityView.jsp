<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_NationalityViewId_div">
<div class="submenu_bg">
	<s:if test="#session.NATIONALITY_ADD == true">
		<sj:a href="setUpNationality.action" targets="submenu_NationalityViewId_div" indicator="indicatorSubMenuNationalityViewId_div" cssClass="link"><s:text name="MTIAddNationality" /></sj:a> |
	</s:if>
	<s:if test="#session.NATIONALITY_VIEW == true">
		<sj:a href="getAllNationality.action" targets="submenu_NationalityViewId_div" indicator="indicatorSubMenuNationalityViewId_div" cssClass="link"><s:text name="MTIViewNationality"/></sj:a> |
		<sj:a href="nationalitySearchForm.action" targets="submenu_NationalityViewId_div" indicator="indicatorSubMenuNationalityViewId_div" cssClass="link"><s:text name="MTISearchNationality"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuNationalityViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
								<s:text name="label.title.nationality.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.nationality.nationalityId" /></td>
				<td class="employeedisplaytd"><s:property value="nati.nationalityId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.nationality.name" /></td>
				<td class="employeedisplaytd"><s:property value="nati.nationalityName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="nati.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="nati.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="nati.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="nati.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="nati.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>