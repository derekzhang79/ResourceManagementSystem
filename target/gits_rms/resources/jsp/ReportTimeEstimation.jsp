<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.NationalityVO"%>

<div id="report_TimeSheetEstimation_div">
       <div class="submenu_bg">
		<sj:a href="showTimeSheetReports.action" targets="report_TimeSheetEstimation_div" indicator="indicatorGenerateTimeEstimationReportId_div" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
		<sj:a href="showTimeEstimationReports.action" targets="report_TimeSheetEstimation_div" indicator="indicatorGenerateTimeEstimationReportId_div" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
		
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
		| <sj:a href="showSummationTimeSheetReports.action" targets="report_TimeSheetEstimation_div" indicator="indicatorGenerateTimeEstimationReportId_div" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
      		  <sj:a href="showTimeSheetProjectSummationReports.action" targets="report_TimeSheetEstimation_div" indicator="indicatorGenerateTimeEstimationReportId_div" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
		</s:if>
	</div><br/>
	<img id="indicatorGenerateTimeEstimationReportId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.timeSheet.reportList"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  
	  <s:text name="label.header.vendor.companyName" var="HCompanyName"></s:text>
	  <s:text name="label.header.project.projectName" var="HProjectName"></s:text>
	  <s:text name="label.header.projActivity.projectActi" var="HProjActivityName"></s:text>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.timeSheet.actualhours" var="HActualHours"></s:text>
	  <s:text name="label.header.timeSheet.estimatedHours" var="HEstimatedHours"></s:text>
	  <s:text name="label.header.common.reports.overTimeHours" var="HOverTimeHours"></s:text>
	  <s:text name="label.header.common.reports.timeRemaining" var="HTimeRemaining"></s:text>
	  <s:text name="label.header.common.reports.status" var="HStatus"></s:text>
	  
	  <display:table class="tableborder" id="enteredTimeDisplayListId" name="enteredTimeDisplayList" pagesize="${NO_OF_RECORDS}" sort="list" defaultsort="1" defaultorder="ascending" export="false">
	  		<display:column property="companyName" title="${HCompanyName}" sortable="false" headerClass="sortable"/>
	  		<display:column property="projectName" title="${HProjectName}" sortable="false" headerClass="sortable"/>
		    <display:column property="activityName" title="${HProjActivityName}" sortable="false" headerClass="sortable"/>
		    <display:column property="employeeName" title="${HEmployeeName}" sortable="false" headerClass="sortable"/>
		    <display:column property="totalEnteredTime" title="${HActualHours}" sortable="false" headerClass="sortable"/>
		    <display:column property="estimatedHours" title="${HEstimatedHours}" sortable="false" headerClass="sortable"/>
		    <display:column property="overTimeHours" title="${HOverTimeHours}" sortable="false" headerClass="sortable"/>
		    <display:column property="timeRemaining" title="${HTimeRemaining}" sortable="false" headerClass="sortable"/>
		    <display:column property="status" title="${HStatus}" sortable="false" headerClass="sortable"/>
	  </display:table>
</div>