
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_MailConfigViewId_div">
<div class="submenu_bg">
	<%--<s:if test="#session.NATIONALITY_ADD == true">--%>
		<sj:a href="setUpMailConfig.action" targets="submenu_MailConfigViewId_div" indicator="indicatorSubMenuMailConfigViewId_div" cssClass="link"><s:text name="MTIAddMailConfiguration" /></sj:a> |
	<%--</s:if>
	<s:if test="#session.NATIONALITY_VIEW == true">--%>
		<sj:a href="getAllMailConfig.action" targets="submenu_MailConfigViewId_div" indicator="indicatorSubMenuMailConfigViewId_div" cssClass="link"><s:text name="MTIViewMailConfiguration"/></sj:a> 
	<%--</s:if>--%>
</div>
<br/>
<img id="indicatorSubMenuMailConfigViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.mailConfig.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                <tr>
						<td class="inputtext"><s:text name="label.header.mailConfig.mailConfigId" /></td>
						<td class="employeedisplaytd"><s:property value="mailConfig.hcmoMailConfigurationId"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.mailConfig.smtpHost" /></td>
						<td class="employeedisplaytd"><s:property value="mailConfig.smtpHost"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.mailConfig.username" /></td>
						<td class="employeedisplaytd"><s:property value="mailConfig.username" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.created" /></td>
						<td class="employeedisplaytd"><s:date name="mailConfig.created" format="%{getText('label.date.simpleDateFormat')}" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
						<td class="employeedisplaytd"><s:property value="mailConfig.createdBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.updated" /></td>
						<td class="employeedisplaytd"><s:date name="mailConfig.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
					</tr>
			 		<tr>
						<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
						<td class="employeedisplaytd"><s:property value="mailConfig.updatedBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
						<td class="employeedisplaytd"><s:checkbox name="mailConfig.isActive" label="icActive" disabled="true"></s:checkbox></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>