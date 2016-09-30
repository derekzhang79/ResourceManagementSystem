<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_leaveResult_list_div_id">
<div class="submenu_bg">
			<sj:a href="showLeaveReports.action" targets="report_leaveResult_list_div_id" indicator="indicatorGenerateLeaveReport" cssClass="link"><s:text name="MTIGenerateLeaveReport" /></sj:a>
		</div>
<br/>
<img id="indicatorGenerateLeaveReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.lhist.reportList"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.leaveType" var="HLeaveTypeName"/></th>
	  <s:text name="label.header.leaveapproveedby.name" var="HApprover"></s:text>
	  <s:text name="label.header.common.dateApplied" var="HDateApplied"></s:text>
	  <s:text name="label.header.leaveHistory.leaveStatus" var="HLeaveStatus"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  <s:text name="label.header.lhist.approveNotes" var="HApprvoeNotes"></s:text>
	  <s:text name="label.header.lhist.disApproveNotes" var="HDisApproveNotes"></s:text>
	  
	  <div id="display_tag_reportLeaveResultsList_div_id">
		  <display:table class="tableborder" id="leaveListId" name="leaveList" pagesize="${NO_OF_RECORDS}" requestURI="getLeaveHistoryReportsPreview.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" sortable="true"/>
		    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveTypeName}" sortable="true"/>
		    <display:column property="hcmoLeaveApproverId.empFullName" title="${HApprover}" sortable="false" headerClass="sortable"/>
		    <display:column property="leaveDate" title="${HDateApplied}" format="{0,date,MM-dd-yyyy}" sortable="true"/>
		    <display:column property="leaveStatus" title="${HLeaveStatus}" sortable="true"/>
		    <display:column property="leaveComments" title="${HComments}" maxLength="10" sortable="true"/>
		     <display:column property="approveNotes" title="${HApprvoeNotes}" sortable="true" headerClass="sortable" maxLength="10"/>
	    	<display:column property="disApproveNotes" title="${HDisApproveNotes}" sortable="true" headerClass="sortable" maxLength="10"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportLeaveResultsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    
    