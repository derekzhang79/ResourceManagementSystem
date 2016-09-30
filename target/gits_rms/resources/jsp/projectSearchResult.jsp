<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.ProjectVO"%>

<div id="submenu_project_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.PROJECT_ADD == true">
		<sj:a href="setUpProject.action" targets="submenu_project_searchList_div_id" indicator="indicatorSubMenuProjectSearchResId_div" cssClass="link"><s:text name="MTIAddProject" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECT_VIEW == true">
		<sj:a href="getAllProjects.action" targets="submenu_project_searchList_div_id" indicator="indicatorSubMenuProjectSearchResId_div" cssClass="link"><s:text name="MTIViewProject"/></sj:a> |
		<sj:a href="projectsSearchForm.action" targets="submenu_project_searchList_div_id" indicator="indicatorSubMenuProjectSearchResId_div" cssClass="link"><s:text name="MTISearchProject"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.project.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.project.projectOwner" var="HProjectowner"></s:text>
	  <s:text name="label.header.customer.name" var="HCustomerName"></s:text>
	  <s:text name="label.header.project.projectName" var="HProjectName"></s:text>
	  <s:text name="label.header.common.description" var="HProjectDescription"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_projectSearchList_div_id">
	  <display:table class="tableborder" id="projectListId" name="proList" pagesize="${NO_OF_RECORDS}" requestURI="projectsSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	    <%
	    	try{
	    		String sProjectId = ((ProjectVO)pageContext.getAttribute("projectListId")).getProjectId().toString();
	        	request.setAttribute("ProjectId", sProjectId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HProjectowner}" sortable="true" headerClass="sortable"/>
	    <display:column property="customerId.customerName" title="${HCustomerName}" sortable="true" headerClass="sortable"/>
	    <display:column property="projectName" title="${HProjectName}" sortable="true" headerClass="sortable"/>
	    <display:column property="projectDesc" title="${HProjectDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
	    <s:if test="#session.PROJECT_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewProject" action="projectView">
					<s:param name="proj.projectId" value="#request.ProjectId"></s:param>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_project_searchList_div_id','%{listViewProject}','');"><s:text name="View"/></s:a>
			</display:column>
		</s:if>
	    <s:if test="#session.PROJECT_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpProject" action="setUpProject">
					<s:param name="proj.projectId" value="#request.ProjectId"></s:param>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_project_searchList_div_id','%{listSetUpProject}','');"><s:text name="Edit"/></s:a>
			</display:column>
		</s:if>
	    <s:if test="#session.PROJECT_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteProject" action="deleteProject">
					<s:param name="proj.projectId" value="#request.ProjectId"></s:param>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_project_searchList_div_id','%{listDeleteProject}','');"><s:text name="Delete"/></s:a>
			</display:column>
		</s:if>
		 <display:setProperty name="export.csv.filename" value="Project.csv"/>
		 <display:setProperty name="export.excel.filename" value="Project.xls"/>
		 <display:setProperty name="export.xml.filename" value="Project.xml"/>
	  </display:table>
	  </div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_projectSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    
  