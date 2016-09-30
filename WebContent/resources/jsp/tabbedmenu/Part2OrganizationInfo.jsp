<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<s:if test="#session.JOBTITLE_ADD == true ||
				#session.JOBTITLE_VIEW == true ||
				#session.SALARYGRADE_ADD == true ||
				#session.SALARYGRADE_VIEW == true ||
				#session.ROLE_ADD == true ||
				#session.ROLE_VIEW == true ||
				#session.DEPARTMENT_ADD == true || 
				#session.DEPARTMENT_VIEW == true ||
				#session.TEAM_ADD == true ||
				#session.TEAM_VIEW == true ||
				#session.CURRENCY_ADD == true ||
				#session.CURRENCY_VIEW == true">

		<s:url var="getTabSalaryGradeList" action="getAllSalaryGrade"/>
		<s:url var="getTabJobTitleList" action="getAllJobTitle"/>
		<s:url var="getTabRoleList" action="getAllRole"/>
		<s:url var="getTabDepartmentList" action="getAllDepartment"/>
		<s:url var="getTabTeamList" action="getAllTeam"/>
		<s:url var="getTabCurrencyList" action="getAllCurrency"/>
		
		<sj:tabbedpanel id="remoteSubTabsOrganizationInfo" animate="true">
			
			<s:if test="#session.SALARYGRADE_ADD == true || #session.SALARYGRADE_VIEW == true">
				<sj:tab id="salaryGradeMainTab" key="MTSalaryGrade" href="%{getTabSalaryGradeList}"/>
			</s:if>
			
			<s:if test="#session.JOBTITLE_ADD == true || #session.JOBTITLE_VIEW == true">
				<sj:tab id="jobTitleMainTab" key="MTJobTitle" href="%{getTabJobTitleList}"/>
			</s:if>
			
		 	<s:if test="#session.ROLE_ADD == true || #session.ROLE_VIEW == true">
		 		<sj:tab id="roleMainTab" key="MTRole" href="%{getTabRoleList}"/>
			</s:if>
			
			<s:if test="#session.DEPARTMENT_ADD == true || #session.DEPARTMENT_VIEW == true">
				<sj:tab id="departmentMainTab" key="MTDepartment" href="%{getTabDepartmentList}"/>
			</s:if>
			
			<s:if test="#session.TEAM_ADD == true || #session.TEAM_VIEW == true">
				<sj:tab id="teamMainTab" key="MTTeam" href="%{getTabTeamList}"/>
			</s:if>
		
			<s:if test="#session.CURRENCY_ADD == true || #session.CURRENCY_VIEW == true">
				<sj:tab id="currencyMainTab" key="MTCurrency" href="%{getTabCurrencyList}"/>
			</s:if>
		</sj:tabbedpanel>
	</s:if>