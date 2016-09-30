<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<s:if test="#session.COUNTRY_ADD == true ||
				#session.COUNTRY_VIEW == true ||
				#session.NATIONALITY_ADD == true ||
				#session.NATIONALITY_VIEW == true ||
				#session.ETHNICRACE_ADD == true ||
				#session.ETHNICRACE_VIEW == true">
	
		<s:url var="getTabNationalityList" action="getAllNationality"/>
		<s:url var="getTabEthnicRaceList" action="getAllEthnicRace"/>
		<s:url var="getTabCountryList" action="getAllCountry"/>
		
		<sj:tabbedpanel id="remoteSubTabsEmpDynamicInfo" animate="true">
			<s:if test="#session.NATIONALITY_ADD == true || #session.NATIONALITY_VIEW == true">
				<sj:tab id="nationalityMainTab" key="MTNationality" href="%{getTabNationalityList}"/>
			</s:if>
			
			<s:if test="#session.ETHNICRACE_ADD == true || #session.ETHNICRACE_VIEW == true">
		 		<sj:tab id="ethnicRaceMainTab" key="MTEthnicRace" href="%{getTabEthnicRaceList}"/>
			</s:if>
			
			<s:if test="#session.COUNTRY_ADD == true || #session.COUNTRY_VIEW == true">
				<sj:tab id="countryMainTab" key="MTCountry" href="%{getTabCountryList}"/>
			</s:if>
		</sj:tabbedpanel>
	</s:if>