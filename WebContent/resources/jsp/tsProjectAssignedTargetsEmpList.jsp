<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TimesheetAssignProjectTargetVO"%>

<div id="submenu_tsAssignedEmpTarget_list_div_id">
	
	<img id="indicatorTsAssignedEmpTargetListEmpId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.target.goal.list.emp"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HTsProjAssignedEmpName"></s:text>
	  <s:text name="label.header.project.projectName" var="HTsProjAssignedProjectName"></s:text>
	  <s:text name="label.header.projectActivity.name" var="HTsProjAssignedProjectActivityName"></s:text>
	  <s:text name="label.common.startDate" var="HStartDate"></s:text>
	  <s:text name="label.common.endDate" var="HEndDate"></s:text>
	  <s:text name="label.header.assignProject.allocatedHour" var="HAllocatedHours"></s:text>
	  <s:text name="label.header.targets.name" var="HTargetsName"></s:text>
	  <s:text name="label.header.targets.type" var="HTargetsType"></s:text>
	  <s:text name="label.header.targets.value" var="HTargetsValue"></s:text>
	  <s:text name="label.header.targets.mode" var="HTargetsMode"></s:text>
	  <s:text name="label.header.target.achieved" var="HTargetAchieved"></s:text>
	  <s:text name="label.header.target.notes" var="HTargetNotes"></s:text>
	  <s:text name="label.common.target.achieved" var="HTargetLink"></s:text>
	  		   
	 <div id="display_tag_tsAssignedEmpTargetList_div_id">
		 <display:table class="tableborder" id="assignTargetListId" name="assignTargetList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpAssignedTargetList.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		  		<%
			    	try{
			    		String sEmpTsId = ((TimesheetAssignProjectTargetVO)pageContext.getAttribute("assignTargetListId")).getHcmoTsAssignProjTargetId().toString();
			        	request.setAttribute("empTsId", sEmpTsId);	
			    	}catch(NullPointerException ne){
			        }    	
		    	%>
		    
			    <display:column property="employeeObj.empFirstName" title="${HTsProjAssignedEmpName}" sortable="true" headerClass="sortable"/>
			    <display:column property="proAssignObj.projectName.projectName" title="${HTsProjAssignedProjectName}" sortable="true" headerClass="sortable"/>
			    <display:column property="proAssignObj.projectActivityId.activityName" title="${HTsProjAssignedProjectActivityName}" sortable="true" headerClass="sortable"/>
			    <display:column property="proAssignObj.projectStartDate" title="${HStartDate}" sortable="true" headerClass="sortable"/>
			    <display:column property="proAssignObj.projectEndDate" title="${HEndDate}" sortable="true" headerClass="sortable"/>
			    <display:column property="proAssignObj.allocatedHours" title="${HAllocatedHours}" sortable="true" headerClass="sortable"/>
			    <display:column property="targetObj.targetName" title="${HTargetsName}" sortable="true" headerClass="sortable"/>
			    <display:column property="targetObj.targetTypeObj.targetTypeName" title="${HTargetsType}" sortable="true" headerClass="sortable"/>
			    <display:column property="targetObj.targetValue" title="${HTargetsValue}" sortable="true" headerClass="sortable"/>
			    <display:column property="targetObj.targetMode" title="${HTargetsMode}" sortable="true" headerClass="sortable"/>
				
				<display:column title="${HTargetLink}" class="viewedit" media="html">
					<s:url var="viewAssigedTarget" action="viewAssigedTarget">
						<s:param name="assignTargetObj.hcmoTsAssignProjTargetId" value="#request.empTsId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_tsAssignedEmpTarget_list_div_id','%{viewAssigedTarget}','');"><s:text name="Target Achieved"/></s:a>
				</display:column>
		  </display:table>
	 </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_tsAssignedEmpTargetList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       