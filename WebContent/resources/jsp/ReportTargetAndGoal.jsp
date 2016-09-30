<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.NationalityVO"%>

<div id="report_TargetAndGoalList_div">
	<div class="submenu_bg">
		<sj:a href="showTargetAndGoalReports.action" targets="report_TargetAndGoalList_div" indicator="indicatorGeneratetargetAndGoalListReport" cssClass="link"><s:text name="MTIGenerateTargetReport" /></sj:a>
	</div><br/>
	<img id="indicatorGeneratetargetAndGoalListReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.timeSheet.reportList"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  
	  <s:text name="label.header.project.projectName" var="HProjectName"></s:text>
	  <s:text name="label.header.projActivity.projectActi" var="HProjActivityName"></s:text>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.goal.name" var="HGoalName"></s:text>
	  <s:text name="label.header.target.achieved" var="HTargetAchived"></s:text>
	  <s:text name="label.header.target.notes" var="HTargetNotes"></s:text>
	  <s:text name="label.common.message.date" var="HCreatedDate"></s:text>
	  
	  <display:table class="tableborder" id="targetAndGoalListId" name="targetAndGoalList" pagesize="${NO_OF_RECORDS}" sort="list" defaultsort="1" defaultorder="ascending" export="false">
	  		<display:column property="proAssignObj.projectName.projectName" title="${HProjectName}" sortable="false" headerClass="sortable"/>
	  		<display:column property="proAssignObj.projectActivityId.activityName" title="${HProjActivityName}" sortable="false" headerClass="sortable"/>
	  		<display:column property="proAssignObj.employeeName.empFirstName" title="${HEmployeeName}" sortable="false" headerClass="sortable"/>
		    <display:column property="targetAchieved" title="${HTargetAchived}" sortable="false" headerClass="sortable"/>
		    <display:column property="targetNotes" title="${HTargetNotes}" sortable="false" headerClass="sortable"/>
		    <display:column property="created" title="${HCreatedDate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
	  </display:table>
</div>