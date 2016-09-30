<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:url var="getSubTabProjDetails" action="getAllProjects"/>
	<s:url var="getSubTabProjectActivity" action="getAllProjectActivity"/>
	
	<sj:tabbedpanel id="remoteTabbedPannelProjectDetails" animate="true">
		<s:if test="#session.PROJECT_ADD == true || #session.PROJECT_VIEW == true">
			<sj:tab id="projDetailsSub" key="MTProject" href="%{getSubTabProjDetails}"/>
		</s:if>
		
		<s:if test="#session.PROJECTACTIVITY_ADD == true || #session.PROJECTACTIVITY_VIEW == true">
			<sj:tab id="ProjectActivitySubTab" key="MTProjectActivity" href="%{getSubTabProjectActivity}"/>
		</s:if>
	</sj:tabbedpanel>