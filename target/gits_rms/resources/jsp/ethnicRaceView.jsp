<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EthnicRaceViewId_div">
<div class="submenu_bg">
	<s:if test="#session.ETHNICRACE_ADD == true">
		<sj:a href="setUpEthnicRace.action" targets="submenu_EthnicRaceViewId_div" indicator="indicatorSubMenuEthnicRaceViewId_div" cssClass="link"><s:text name="MTIAddEthnicRace" /></sj:a> |
	</s:if>
	<s:if test="#session.ETHNICRACE_VIEW == true">
		<sj:a href="getAllEthnicRace.action" targets="submenu_EthnicRaceViewId_div" indicator="indicatorSubMenuEthnicRaceViewId_div" cssClass="link"><s:text name="MTIViewEthnicRace"/></sj:a> |
		<sj:a href="ethnicRaceSearchForm.action" targets="submenu_EthnicRaceViewId_div" indicator="indicatorSubMenuEthnicRaceViewId_div" cssClass="link"><s:text name="MTISearchEthnicRace"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuEthnicRaceViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
				<s:text name="label.title.ethnicRace.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.ethnicRace.ethnicRaceId" /></td>
				<td class="employeedisplaytd"><s:property value="ethRace.ethnicRaceId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.ethnicRace.name" /></td>
				<td class="employeedisplaytd"><s:property value="ethRace.ethnicRaceDesc"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="ethRace.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="ethRace.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="ethRace.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			 <tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="ethRace.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="ethRace.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>