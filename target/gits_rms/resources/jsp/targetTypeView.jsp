<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TargetType_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETSTYPE_ADD == true">
		<sj:a href="setUpTargetType.action" targets="submenu_TargetType_div" indicator="indicatorSubMenuTargetType" cssClass="link"><s:text name="MTIAddTargetType" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETSTYPE_VIEW == true">
		<sj:a href="getAllTargetType.action" targets="submenu_TargetType_div" indicator="indicatorSubMenuTargetType" cssClass="link"><s:text name="MTIViewTargetType"/></sj:a> |
		<sj:a href="targetTypeSearchForm.action" targets="submenu_TargetType_div" indicator="indicatorSubMenuTargetType" cssClass="link"><s:text name="MTISearchTargetType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuNationalityTargetTypeViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.targetType.view" />
		                  </td>
	                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.id" /></td>
				<td class="employeedisplaytd"><s:property value="targetType.hcmoTargetTypeId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.name" /></td>
				<td class="employeedisplaytd"><s:property value="targetType.targetTypeName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="targetType.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="targetType.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="targetType.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="targetType.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="targetType.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>