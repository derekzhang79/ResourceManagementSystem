<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
		<s:url var="getTabEmployeeList" action="getAllEmp"/>
		<s:url var="getTabUserLoginList" action="getAllUser"/>
		<s:url var="getTabLicenseList" action="getAllLicense"/>
		<s:url var="getTabEducationList" action="getAllEducation"/>
		<s:url var="getTabChildrenList" action="getAllChildren"/>
		<s:url var="getTabWorkExperienceList" action="getAllWorkExperience"/>
		<s:url var="getTabLocationList" action="getAllEmpLocationHistory"/>
		
		<sj:tabbedpanel id="remoteSubTabsEmployeeComleteInfo" animate="true">
			
			<s:if test="#session.EMPLOYEE_ADD == true || #session.EMPLOYEE_VIEW == true">
				<sj:tab id="employeeMainTab" key="MTEmployees" href="%{getTabEmployeeList}"/>
			</s:if>
			
			<s:if test="#session.USER_ADD == true || #session.USER_VIEW == true">
				<sj:tab id="UserLoginMainTab" key="MTUser" href="%{getTabUserLoginList}"/>
			</s:if>
			
			<s:if test="#session.LICENSE_ADD == true || #session.LICENSE_VIEW == true">
				<sj:tab id="LicenseMainTab" key="MTLicense" href="%{getTabLicenseList}"/>
			</s:if>
			
			<s:if test="#session.EDUCATION_ADD == true || #session.EDUCATION_VIEW == true">
				<sj:tab id="EducationMainTab" key="MTEducation" href="%{getTabEducationList}"/>
			</s:if>
			
			<s:if test="#session.CHILDREN_ADD == true || #session.CHILDREN_VIEW == true">
				<sj:tab id="ChildrenMainTab" key="MTChildren" href="%{getTabChildrenList}"/>
			</s:if>
			
			<s:if test="#session.WORKEXPERIENCE_ADD == true || #session.WORKEXPERIENCE_VIEW == true">
				<sj:tab id="WorkExperienceMainTab" key="MTWorkExperience" href="%{getTabWorkExperienceList}"/>
			</s:if>
			
			<s:if test="#session.LOCATIONHISTORY_ADD == true || #session.LOCATIONHISTORY_VIEW == true">
				<sj:tab id="LocationMainTab" key="MTEmployeeLocationHistory" href="%{getTabLocationList}"/>
			</s:if>
			
		</sj:tabbedpanel>