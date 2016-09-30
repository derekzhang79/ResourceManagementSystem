<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
		<s:url var="getTabTimesheetCategoryList" action="getAllTimeSheetCategory"/>
		<s:url var="getTabTimesheetCategoryAssignList" action="getAllTimeSheetCategoryEmp"/>
		
		<sj:tabbedpanel id="remoteSubTabsTimesheetCategoryInfo" animate="true">
			<sj:tab id="timesheetCategoryMainTab" key="MTTimeSheetCategory" href="%{getTabTimesheetCategoryList}"/>
			<sj:tab id="timesheetCategoryAssignMainTab" key="MTTimeSheetCategoryEmp" href="%{getTabTimesheetCategoryAssignList}"/>
		</sj:tabbedpanel>