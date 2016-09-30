<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.ProjectActivityVO"%>

<div id="submenu_projectActivity_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.PROJECTACTIVITY_ADD == true">
		<sj:a href="setUpProjectActivity.action" targets="submenu_projectActivity_searchList_div_id" indicator="indicatorSubMenuProjectActivitySearchResId_div" cssClass="link"><s:text name="MTIAddProjectActivity" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECTACTIVITY_VIEW == true">
		<sj:a href="getAllProjectActivity.action" targets="submenu_projectActivity_searchList_div_id" indicator="indicatorSubMenuProjectActivitySearchResId_div" cssClass="link"><s:text name="MTIViewProjectActivity"/></sj:a> |
		<sj:a href="projectActivitySearcForm.action" targets="submenu_projectActivity_searchList_div_id" indicator="indicatorSubMenuProjectActivitySearchResId_div" cssClass="link"><s:text name="MTISearchProjectActivity"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectActivitySearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.projectActivity.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.projActivity.projectOwner" var="HProjActivityProjOwner"></s:text>
	  <s:text name="label.header.project.projectName" var="HProjectActivityProjName"></s:text>
	  <s:text name="label.header.projActivity.projectActivity" var="HProjectActivity"></s:text>
	   <s:text name="label.form.fields.common.notes" var="HProjectActivityNotes"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  	
	  <div id="display_tag_projectActivitySearchList_div_id">
		  <display:table class="tableborder" id="projectActivityListId" name="projActivityList" pagesize="${NO_OF_RECORDS}" requestURI="projectActivitySearcResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sProjectActivityId = ((ProjectActivityVO)pageContext.getAttribute("projectActivityListId")).getProjectActivityId().toString();
		        	request.setAttribute("ProjectActivityId", sProjectActivityId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HProjActivityProjOwner}" sortable="true" headerClass="sortable"/>
		    <display:column property="proObj.projectName" title="${HProjectActivityProjName}" sortable="true" headerClass="sortable"/>
		    <display:column property="activityName" title="${HProjectActivity}" sortable="true" headerClass="sortable"/>
		    <display:column property="activityNotes" title="${HProjectActivityNotes}" sortable="true" headerClass="sortable" maxLength="10"/>
		    <s:if test="#session.PROJECTACTIVITY_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewProjectActivity" action="projectActivityView">
						<s:param name="proActivity.projectActivityId" value="#request.ProjectActivityId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_projectActivity_searchList_div_id','%{listViewProjectActivity}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.PROJECTACTIVITY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpProjectActivity" action="setUpProjectActivity">
						<s:param name="proActivity.projectActivityId" value="#request.ProjectActivityId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_projectActivity_searchList_div_id','%{listSetUpProjectActivity}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.PROJECTACTIVITY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteProjectActivity" action="deleteProjectActivity">
						<s:param name="proActivity.projectActivityId" value="#request.ProjectActivityId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_projectActivity_searchList_div_id','%{listDeleteProjectActivity}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="ProjectActivity.csv"/>
			 <display:setProperty name="export.excel.filename" value="ProjectActivity.xls"/>
			 <display:setProperty name="export.xml.filename" value="ProjectActivity.xml"/>
		  </display:table>
	  </div>		   
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_projectActivitySearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   