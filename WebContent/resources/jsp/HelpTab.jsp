<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:url var="helpMainTab" action="helpMainTab"></s:url>
		<s:url var="faqTab" action="faqTab"></s:url>
		<sj:tabbedpanel id="menuHelpTabbedpannel" animate="true">
			<sj:tab id="helpMainTab" key="MTVideos" href="%{helpMainTab}" />
			<sj:tab id="faqMainTab" key="MFaq" href="%{faqTab}" />
		</sj:tabbedpanel>