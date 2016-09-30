<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TeamViewId_div">
<div class="submenu_bg">
	<s:if test="#session.TEAM_ADD == true">
		<sj:a href="setUpTeam.action" targets="submenu_TeamViewId_div" indicator="indicatorSubMenuTeamViewId_div" cssClass="link"><s:text name="MTIAddTeam" /></sj:a> |
	</s:if>
	<s:if test="#session.TEAM_VIEW == true">
		<sj:a href="getAllTeam.action" targets="submenu_TeamViewId_div" indicator="indicatorSubMenuTeamViewId_div" cssClass="link"><s:text name="MTIViewTeam"/></sj:a> |
		<sj:a href="teamSearchForm.action" targets="submenu_TeamViewId_div" indicator="indicatorSubMenuTeamViewId_div" cssClass="link"><s:text name="MTISearchTeam"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTeamViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.team.view" />
		                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.team.teamId" /></td>
				<td class="employeedisplaytd"><s:property value="team.hcmoTeamId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.team.name" /></td>
				<td class="employeedisplaytd"><s:property value="team.teamName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="team.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="team.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="team.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="team.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="team.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>