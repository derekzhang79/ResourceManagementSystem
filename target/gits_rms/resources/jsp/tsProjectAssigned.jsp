<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.ProjectAssignEmpVO"%>

<div id="submenu_tsProjectAssigned_list_div_id">
		<div class="submenu_bg">
				<sj:a href="setUpTsProjectAssigned.action" targets="submenu_tsProjectAssigned_list_div_id" indicator="indicatorSubMenuTsProjectAssignedId_div" cssClass="link"><s:text name="MTIAddAssignProject" /></sj:a> |
				<sj:a href="getAllTsProjectAssigned.action" targets="submenu_tsProjectAssigned_list_div_id" indicator="indicatorSubMenuTsProjectAssignedId_div" cssClass="link"><s:text name="MTIViewAssignProject"/></sj:a>|
				<sj:a href="tsProjectAssignSearchForm.action" targets="submenu_tsProjectAssigned_list_div_id" indicator="indicatorSubMenuTsProjectAssignedId_div" cssClass="link"><s:text name="MTISearchAssignProject"/></sj:a>
		</div>
		<br/>
		<img id="indicatorSubMenuTsProjectAssignedId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.assignProject.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HTsProjAssignedEmpName"></s:text>
	  <s:text name="label.header.project.projectName" var="HTsProjAssignedProjectName"></s:text>
	  <s:text name="label.header.projectActivity.name" var="HTsProjAssignedProjectActivityName"></s:text>
	  <s:text name="label.header.common.startDate" var="HTsProjAssignedStartDate"></s:text>
	  <s:text name="label.header.common.enddate" var="HTsProjAssignedEndDate"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_tsProjectAssignedList_div_id">
		  <display:table class="tableborder" id="tsProjAssignedListId" name="tsProjAssignedList" pagesize="${NO_OF_RECORDS}" requestURI="getAllTsProjectAssigned.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sProjAssignEmpId = ((ProjectAssignEmpVO)pageContext.getAttribute("tsProjAssignedListId")).getProjectAssignEmpId().toString();
		        	request.setAttribute("TsProjAssignId", sProjAssignEmpId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="employeeName.empFullName" title="${HTsProjAssignedEmpName}" sortable="true" headerClass="sortable"/>
		    <display:column property="projectName.projectName" title="${HTsProjAssignedProjectName}" sortable="true" headerClass="sortable"/>
		    <display:column property="projectActivityId.activityName" title="${HTsProjAssignedProjectActivityName}" sortable="true" headerClass="sortable"/>
		    <display:column property="projectStartDate" title="${HTsProjAssignedStartDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    <display:column property="projectEndDate" title="${HTsProjAssignedEndDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewTsProjectAssigned" action="tsProjectAssignedView">
						<s:param name="tsProjAssigned.projectAssignEmpId" value="#request.TsProjAssignId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_tsProjectAssigned_list_div_id','%{listViewTsProjectAssigned}','');"><s:text name="View"/></s:a>
				</display:column>
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listsetUpTsProjectAssigned" action="setUpTsProjectAssigned">
						<s:param name="tsProjAssigned.projectAssignEmpId" value="#request.TsProjAssignId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_tsProjectAssigned_list_div_id','%{listsetUpTsProjectAssigned}','');"><s:text name="Edit"/></s:a>
				</display:column>
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteTsProjectAssigned" action="deleteTsProjectAssigned">
						<s:param name="tsProjAssigned.projectAssignEmpId" value="#request.TsProjAssignId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_tsProjectAssigned_list_div_id','%{listDeleteTsProjectAssigned}','');"><s:text name="Delete"/></s:a>
				</display:column>
			 <display:setProperty name="export.csv.filename" value="TsAssignedProject.csv"/>
			 <display:setProperty name="export.excel.filename" value="TsAssignedProject.xls"/>
			 <display:setProperty name="export.xml.filename" value="TsAssignedProject.xml"/>
		  </display:table>
	  </div>
</div> 
  <script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_tsProjectAssignedList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     