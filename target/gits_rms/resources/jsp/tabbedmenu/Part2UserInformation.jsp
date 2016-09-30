<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:url var="getMainTabOrgInfo" action="MenuPart2OrgInfo"/>
			<s:url var="getMainTabEmpDynaicInfo" action="MenuPart2EmpDynmInfo"/>
			<sj:tabbedpanel id="remoteOrganizationInfo" animate="true">
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
				
				<sj:tab id="OrgInfoMainTab" key="MTOrganizationInfo" href="%{getMainTabOrgInfo}"/>
			</s:if>
			<s:if test="#session.COUNTRY_ADD == true ||
						#session.COUNTRY_VIEW == true ||
						#session.NATIONALITY_ADD == true ||
						#session.NATIONALITY_VIEW == true ||
						#session.ETHNICRACE_ADD == true ||
						#session.ETHNICRACE_VIEW == true">
				<sj:tab id="EmpDynamicInfoMainTab" key="MTEmployeeDynamicInfo" href="%{getMainTabEmpDynaicInfo}"/>
			</s:if>
			</sj:tabbedpanel>