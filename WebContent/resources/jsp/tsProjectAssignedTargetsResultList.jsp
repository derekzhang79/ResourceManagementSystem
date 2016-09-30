<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TimesheetAssignProjectTargetVO"%>

	<div>
		<jsp:include page="common/messages.jsp" flush="true"/>
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<table align="center">
			<s:text name="label.header.employee.name" var="HEmployeeName"></s:text>
			<s:text name="label.header.project.projectName" var="HProjectName"></s:text>
			<s:text name="label.header.projActivity.projectActi" var="HProjectActivityName"></s:text>
			<s:text name="label.header.targets.name" var="HTargetsName"></s:text>
			<s:text name="label.header.targets.type" var="HTargetsType"></s:text>
			<s:text name="label.header.targets.value" var="HTargetsValue"></s:text>
			<s:text name="label.header.targets.mode" var="HTargetsMode"></s:text>
	  
		  <display:table class="tableborder" id="assignedTargetListId" name="assignTargetList">
		    <%
		    	try{
		    		String sAssignTargetsId = ((TimesheetAssignProjectTargetVO)pageContext.getAttribute("assignedTargetListId")).getHcmoTsAssignProjTargetId().toString();
		        	request.setAttribute("assignTargetsId", sAssignTargetsId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
			    <display:column property="employeeObj.empFirstName" title="${HEmployeeName}" sortable="false"/>
	    		<display:column property="proAssignObj.projectName.projectName" title="${HProjectName}" sortable="false"/>
	    		<display:column property="proAssignObj.projectActivityId.activityName" title="${HProjectActivityName}" sortable="false"/>
		    	<display:column property="targetObj.targetName" title="${HTargetsName}" sortable="false"/>
		    	<display:column property="targetObj.targetTypeObj.targetTypeName" title="${HTargetsType}" sortable="false"/>
		    	<display:column property="targetObj.targetValue" title="${HTargetsValue}" sortable="false"/>
		   	 	<display:column property="targetObj.targetMode" title="${HTargetsMode}" sortable="false"/>
		  </display:table>
		</table>
	</div>