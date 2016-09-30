<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_LeaveResult_div">
	<div class="submenu_bg">
		<sj:a href="showTimeSheetReports.action" targets="report_TimeSheetPreviewId_div" indicator="indicatorGenerateTimeSheetReportPreviewId_div" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
		<sj:a href="showTimeEstimationReports.action" targets="report_TimeSheetPreviewId_div" indicator="indicatorGenerateTimeSheetReportPreviewId_div" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
				
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
		| <sj:a href="showSummationTimeSheetReports.action" targets="submenu_GenerateTimeSheetReport" indicator="indicatorGenerateTimeSheetReportPreviewId_div" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
		  <sj:a href="showTimeSheetProjectSummationReports.action" targets="submenu_GenerateTimeSheetReport" indicator="indicatorGenerateTimeSheetReportPreviewId_div" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
		</s:if>
	</div>
<br/>
<img id="indicatorGenerateTimeSheetReportPreviewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.timeSheet.reportList"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.timeSheet.timeSheetCategory" var="HCategory"></s:text>
	  <s:text name="label.header.project.projectName" var="HProject"></s:text>
	  <s:text name="label.header.common.date" var="HEnteredDate"></s:text>
	  <s:text name="label.header.timeSheet.working.Hours" var="HWorkingHours"></s:text>
	  <s:text name="label.header.common.approved" var="HApproved"></s:text>
	  <s:text name="label.header.timeSheet.rejected" var="HRejected"></s:text>
	  <s:text name="label.header.timeSheet.rework" var="HRework"></s:text>
	  
	  <display:table class="tableborder" id="timesheetReport" name="timeSheetReportList" pagesize="${NO_OF_RECORDS}" requestURI="getTimeSheetReportsPreview.action" sort="list" defaultsort="1" defaultorder="ascending">
	    <display:column property="empName" title="${HEmployeeName}" sortable="true"/>
	    <display:column property="categoryName" title="${HCategory}" sortable="true"/>
	    <display:column property="projectName" title="${HProject}" sortable="true"/>
	    <display:column property="enteredDate" title="${HEnteredDate}" format="{0,date,MM-dd-yyyy}" sortable="true"/>
	    <display:column property="enteredTime" title="${HWorkingHours}" sortable="true"/>
	    <display:column property="approved" title="${HApproved}" sortable="true"/>
	    <display:column property="rejected" title="${HRejected}" sortable="true"/>
	    <display:column property="rework" title="${HRework}" sortable="true"/>
	  </display:table>
</div>    