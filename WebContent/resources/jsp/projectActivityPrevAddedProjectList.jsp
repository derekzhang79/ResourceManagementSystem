<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_projectActivity_list_div_id"><br/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.projectActivity.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.projActivity.projectOwner" var="HProjActivityProjOwner"></s:text>
	  <s:text name="label.header.project.projectName" var="HProjectActivityProjName"></s:text>
	  <s:text name="label.header.projActivity.projectActivity" var="HProjectActivity"></s:text>
	   <s:text name="label.form.fields.common.notes" var="HProjectActivityNotes"></s:text>
	  		   
	  <div id="display_tag_PprojectActivityList_div_id">
		   <display:table class="tableborder" id="projectActivityListId" name="projActivityList" pagesize="${NO_OF_RECORDS}" requestURI="getPrevAddedProjActivityByProj.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <display:column property="empIdObj.empFullName" title="${HProjActivityProjOwner}" sortable="true" headerClass="sortable"/>
		    <display:column property="proObj.projectName" title="${HProjectActivityProjName}" sortable="true" headerClass="sortable"/>
		    <display:column property="activityName" title="${HProjectActivity}" sortable="true" headerClass="sortable"/>
		    <display:column property="activityNotes" title="${HProjectActivityNotes}" sortable="true" headerClass="sortable" maxLength="10"/> 
		  </display:table>
	  </div>
	 
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_PprojectActivityList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  