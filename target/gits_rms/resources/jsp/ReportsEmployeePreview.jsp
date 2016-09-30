<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_employeeResult_list_div_id">
	<div class="submenu_bg">
		<sj:a href="showEmployeeReports.action" targets="report_employeeResult_list_div_id" indicator="indicatorGenerateEmployeeReport" cssClass="link"><s:text name="MTIGenerateEmployeeReport" /></sj:a>
	</div>
	<br/>
	<img id="indicatorGenerateEmployeeReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.employee.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HFirstName"></s:text>
	  <s:text name="label.header.employee.lastName" var="HLastName"></s:text>
	  <s:text name="label.header.role.name" var="HRole"></s:text>
	  <s:text name="label.header.jobTitle.name" var="HJobTitle"></s:text>
	  
	  <div id="display_tag_reportsEmployeeResultsList_div_id">
		  <display:table class="tableborder" id="employeeListId" name="employeeList" pagesize="${NO_OF_RECORDS}" requestURI="getEmployeeReportsPreview.action" defaultsort="1" defaultorder="ascending">
		    <display:column property="empFirstName" title="${HFirstName}" sortable="true"/>
		    <display:column property="empLastName" title="${HLastName}" sortable="true"/>
		    <display:column property="roleObj.roleName" title="${HRole}" sortable="true"/>
		    <display:column property="jobTitleIdObj.jobTitleName" title="${HJobTitle}" sortable="true"/>
		  </display:table>
	  </div>		   
</div>   
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportsEmployeeResultsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     