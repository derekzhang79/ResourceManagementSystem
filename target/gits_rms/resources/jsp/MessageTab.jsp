<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
	<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.title.messaging.messagingModule"/>
			</td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="Messaging" class="vidbox2" href="http://www.youtube.com/watch?v=_L3_T1xm5KE"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
	<s:url var="getAllMailConfigMessagingTab" action="getAllMailConfig"></s:url>
	<s:url var="getMessagingMailMenuTab" action="getMessageLeftPanel"></s:url>
	<s:url var="getMessagingSignatureTab" action="getAllSignature"></s:url>
	
	<sj:tabbedpanel id="menuMessagingModuleTabbedpannel" animate="true">
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			<sj:tab id="MessagingModuleGetAllMailConfigMainTab" key="MTMailConfiguration" href="%{getAllMailConfigMessagingTab}" />
		</s:if>
			<sj:tab id="MessagingModuleMailMenuMainTab" key="MTMessage" href="%{getMessagingMailMenuTab}" />
			<sj:tab id="MessagingModuleSignatureMainTab" key="MTSignature" href="%{getMessagingSignatureTab}" />
	</sj:tabbedpanel>
	
 <script>
	//Pop-up for Video in modules
	jQuery(document).ready(function() {
		try{
   			jQuery(".vidbox2").jqvideobox({
   			'width' : 600,
   			'height' : 400,
    		'getimage' : false,
   			'navigation' : true
   			});	
  		}catch(e){
  
  		 }
	}); 
 </script>
	