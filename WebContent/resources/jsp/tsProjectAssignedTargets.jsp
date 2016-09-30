<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TargetsVO"%>
<%@page import="com.gits.rms.vo.TimesheetAssignProjectTargetVO"%>

<div id="submenu_TargetAssignProj_div">
<div>
	<div class="submenu_bg">
		<sj:a href="setUpTsProjectAssigned.action" targets="submenu_TargetAssignProj_div" indicator="indicatorSubMenuTargetsAssignProject" cssClass="link"><s:text name="MTIAddAssignProject" /></sj:a> |
		<sj:a href="getAllTsProjectAssigned.action" targets="submenu_TargetAssignProj_div" indicator="indicatorSubMenuTargetsAssignProject" cssClass="link"><s:text name="MTIViewAssignProject"/></sj:a>|
		<sj:a href="tsProjectAssignSearchForm.action" targets="submenu_TargetAssignProj_div" indicator="indicatorSubMenuTargetsAssignProject" cssClass="link"><s:text name="MTISearchAssignProject"/></sj:a>
	</div><br/>
	<img id="indicatorSubMenuTargetsAssignProject" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.targets.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>

	<s:text name="label.header.employee.name" var="HEmployeeName"></s:text>
	<s:text name="label.header.project.projectName" var="HProjectName"></s:text>
	<s:text name="label.header.projActivity.projectActi" var="HProjectActivityName"></s:text>
	<s:text name="label.header.targets.name" var="HTargetsName"></s:text>
	<s:text name="label.header.targets.type" var="HTargetsType"></s:text>
	<s:text name="label.header.targets.value" var="HTargetsValue"></s:text>
	<s:text name="label.header.targets.mode" var="HTargetsMode"></s:text>
	
	<s:form action="insertAssignProjectTarget" id="timesheetAssignProjTargetId">
		<table align="center">  
		  <display:table class="tableborder" id="targetsListId" name="targetList" pagesize="${NO_OF_RECORDS}" defaultsort="1" defaultorder="ascending" export="false">
		    <%
		    	try{
		    		String sTargetsId = ((TargetsVO)pageContext.getAttribute("targetsListId")).getHcmoTargetId().toString();
		        	request.setAttribute("TargetsId", sTargetsId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="targetName" title="${HTargetsName}" sortable="false"/>
		    <display:column property="targetTypeObj.targetTypeName" title="${HTargetsType}" sortable="false"/>
		    <display:column property="targetValue" title="${HTargetsValue}" sortable="false"/>
		    <display:column property="targetMode" title="${HTargetsMode}" sortable="false"/>
		    
		    <display:column title="${HTargetsMode}" sortable="false">
		    	<s:checkbox name="assignProjTargetList" id="assignProjTargetList" fieldValue="%{#request.TargetsId}"></s:checkbox>
		    </display:column>
		    
		  </display:table>
		  
		  <s:hidden name="projectAssignEmpId"/>
		  <s:hidden name="projectId"/>
		  <s:hidden name="activityId"/>
		</table> 
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSubMenuTargetsAssignProjectSubmit" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<sj:submit name="submit" indicator="indicatorSubMenuTargetsAssignProjectSubmit" formIds="timesheetAssignProjTargetId" cssClass="submitbutton117" targets="result_TargetAssignProj_div"></sj:submit>
					
				</td>
				<td>
					<s:url var="getAllTsProjectAssigned" action="getAllTsProjectAssigned"></s:url>
    	            <sj:submit href="%{getAllTsProjectAssigned}"  name="Close" value="Close" indicator="indicatorSubMenuTargetsAssignProjectSubmit" formIds="timesheetAssignProjTargetId" cssClass="submitbutton117" targets="submenu_TargetAssignProj_div"></sj:submit>
					
				</td>
			</tr>
	 	</table> 
	</s:form>
</div>
<div id="result_TargetAssignProj_div">
	<table align="center">  
	  <display:table class="tableborder" id="assignTargetListId" name="assignTargetList">
	    <%
	    	try{
	    		String sAssignTargetsId = ((TimesheetAssignProjectTargetVO)pageContext.getAttribute("assignTargetListId")).getHcmoTsAssignProjTargetId().toString();
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
</div>