<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 <s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center"><td class="formtitle">
			<s:text name="MPart1"/>
		</td></tr>
	</table>
	
	<s:if test="#session.CLIENT_ADD == true ||
				#session.CLIENT_VIEW == true ||
				#session.LOCATION_ADD == true ||
				#session.LOCATION_VIEW == true ||
				#session.REGION_ADD == true ||
				#session.REGION_VIEW == true">
		
		<s:url var="getTabClientList" action="getAllClient"/>
		<s:url var="getTabLocationList" action="getAllLocation"/>
		<s:url var="getTabRegionList" action="getAllRegion"/>
		
		<sj:tabbedpanel id="remoteMenuPart1" animate="true">
			<s:if test="#session.CLIENT_ADD == true || #session.CLIENT_VIEW == true">
	        	<sj:tab id="clientMainTab" key="MTClient" href="%{getTabClientList}"/>
	        </s:if>
	        <s:if test="#session.LOCATION_ADD == true || #session.LOCATION_VIEW == true">
	      		<sj:tab id="locationMainTab" key="MTLocation" href="%{getTabLocationList}"/>
	      	</s:if>
	      	<s:if test="#session.REGION_ADD == true || #session.REGION_VIEW == true">
	        	<sj:tab id="regionMainTab" key="MTRegion" href="%{getTabRegionList}"/>
	        </s:if>
	    </sj:tabbedpanel>
	</s:if>