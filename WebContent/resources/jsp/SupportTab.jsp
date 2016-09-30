<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<s:url var="helpTab" action="helpTab"></s:url>
	<s:url var="doSupportTab" action="doSupport"></s:url>
	<s:url var="aboutHCMOneTab" action="aboutHCMOneTab"></s:url>
	<s:url var="mainForumTab" action="forumTab"></s:url>
	<s:url var="upgradeTab" action="upgradeHCMOne"></s:url>
	
	<sj:tabbedpanel id="menuSupportTabbedpannel" animate="true">
		<sj:tab id="helpTab" key="MTHelp" href="%{helpTab}" />
		<s:if test="#session.MASTER_CLIENT_USERTYPE == 'paid_user'">
			<sj:tab id="doSupportTab" key="MSupport" href="%{doSupportTab}" />
		</s:if>
		<s:elseif test="#session.MASTER_CLIENT_USERTYPE == 'free_user'">
			<sj:tab id="forumTab" key="MForum" href="%{mainForumTab}" />
		</s:elseif>
		<s:if test="#session.MASTER_CLIENT_USERTYPE == 'free_user'">
			<sj:tab id="upgradeTab" key="MUpgrade" href="%{upgradeTab}" />
		</s:if>
		<sj:tab id="aboutTab" key="MAbout" href="%{aboutHCMOneTab}" />
	</sj:tabbedpanel>