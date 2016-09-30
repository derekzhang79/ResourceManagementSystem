<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<s:url var="getSubTabTargetType" action="getAllTargetType"/>
	<s:url var="getSubTabTargets" action="getAllTargets" />
	<s:url var="getTabEmpTarget" action="getAllEmpAssignedTargetList"/>
	<s:url var="getTabEmpTargetGoal" action="setUpViewTargetGoal"/>
	
	<sj:tabbedpanel id="remoteTabbedPannelTargetAndGoals" animate="true">
		
		<s:if test="#session.TARGETSTYPE_ADD == true || #session.TARGETSTYPE_VIEW == true">
			<sj:tab id="getTargetType" key="MTTargetType" href="%{getSubTabTargetType}"/>
		</s:if>
		
		<s:if test="#session.TARGETS_ADD == true || #session.TARGETS_VIEW == true">
			<sj:tab id="getTarget" key="MTTargets" href="%{getSubTabTargets}"/>
		</s:if>
		
		<sj:tab id="getMyTarget" key="MTMyTaregt" href="%{getTabEmpTarget}"/>
		<sj:tab id="getMyTargetGoal" key="MTMyTaregtView" href="%{getTabEmpTargetGoal}"/>
	</sj:tabbedpanel>