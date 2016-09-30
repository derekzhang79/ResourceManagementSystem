<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_projectResult_list_div_id">
<div class="submenu_bg">
		<sj:a href="showProjectReports.action" targets="report_projectResult_list_div_id" indicator="indicatorGenerateProjectReport" cssClass="link"><s:text name="MTIGenerateProjectReport" /></sj:a>
</div>
<br/>
<img id="indicatorGenerateProjectReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.project.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.project.projectOwner" var="HProjectowner"></s:text>
	  <s:text name="label.header.customer.name" var="HCustomerName"></s:text>
	  <s:text name="label.header.project.projectName" var="HProjectName"></s:text>
	  <s:text name="label.header.common.description" var="HProjectDescription"></s:text>
	  <div id="display_tag_reportProjectResultsList_div_id">
		  <display:table class="tableborder" id="projectListId" name="projectList" pagesize="${NO_OF_RECORDS}" requestURI="getProjectReportsPreview.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <display:column property="empIdObj.empFullName" title="${HProjectowner}" sortable="true"/>
		    <display:column property="customerId.customerName" title="${HCustomerName}" sortable="true"/>
		    <display:column property="projectName" title="${HProjectName}" sortable="true"/>
		    <display:column property="projectDesc" title="${HProjectDescription}" sortable="true"/>
		  </display:table>
	  </div>		   
	  
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportProjectResultsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    
  