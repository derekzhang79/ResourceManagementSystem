<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center"><td class="formtitle">
			<s:text name="MPart4"/>
		</td></tr>
	</table>
	
	<s:url var="getTabListImportantNews" action="getAllImportantNews"></s:url>
	<sj:tabbedpanel id="menuBulletInNewsTabbedpannel">
		<sj:tab id="impotantMainTab" key="MTImportaneNews" href="%{getTabListImportantNews}" />
	</sj:tabbedpanel>