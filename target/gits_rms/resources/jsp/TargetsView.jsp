<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TargetsViewId_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETS_ADD == true">
		<sj:a href="setUpTargets.action" targets="submenu_TargetsViewId_div" indicator="indicatorSubMenuTargetsViewId_div" cssClass="link"><s:text name="MTIAddTargets" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETS_VIEW == true">
		<sj:a href="getAllTargets.action" targets="submenu_TargetsViewId_div" indicator="indicatorSubMenuTargetsViewId_div" cssClass="link"><s:text name="MTIViewTargets"/></sj:a> |
		<sj:a href="targetsSearchForm.action" targets="submenu_TargetsViewId_div" indicator="indicatorSubMenuTargetsViewId_div" cssClass="link"><s:text name="MTISearchTargets"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTargetsViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
								<s:text name="label.title.targets.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.name" /></td>
				<td class="employeedisplaytd"><s:property value="target.targetName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.type" /></td>
				<td class="employeedisplaytd"><s:property value="target.targetTypeObj.targetTypeName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.mode" /></td>
				<td class="employeedisplaytd"><s:property value="target.targetMode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="target.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="target.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="target.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="target.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="target.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>