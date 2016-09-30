<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_birthdayResult_list_div_id">
<div class="submenu_bg">
		<sj:a href="showBirthdayReports.action" targets="report_birthdayResult_list_div_id" indicator="indicatorGenerateBirthdayReportFormId_div" cssClass="link"><s:text name="MTIGenerateBirthdayReport" /></sj:a>
</div>
	<br/>
<img id="indicatorGenerateBirthdayReportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.employee.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HFirstName"></s:text>
	  <s:text name="label.header.common.dob" var="HBirthday"></s:text>
	  		   
	  <div id="display_tag_reportBirthdayResultsList_div_id">
		  <display:table class="tableborder" id="employeeListId" name="employeeList" pagesize="${NO_OF_RECORDS}" requestURI="getBirthdayReportsPreview.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <display:column property="empFullName" title="${HFirstName}" sortable="true"/>
		    <display:column property="empBirthDate" title="${HBirthday}" sortable="true" format="{0,date,MM-dd-yyyy}"/>
		  </display:table>
	  </div>
	  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportBirthdayResultsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>      