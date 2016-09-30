<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ExpensesAccountantApproverVO"%>
<%@page import="com.gits.rms.vo.TimesheetCategoryEmpVO"%>

<div id="submenu_timesheetCategoryEmp_searchList_div_id">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategoryEmp.action" targets="submenu_timesheetCategoryEmp_searchList_div_id" indicator="indicatorSubMenuTimesheetCategoryEmpSearchResId_div" cssClass="link"><s:text name="MTAddTimeSheetCategoryEmp" /></sj:a> |
			<sj:a href="getAllTimeSheetCategoryEmp.action" targets="submenu_timesheetCategoryEmp_searchList_div_id" indicator="indicatorSubMenuTimesheetCategoryEmpSearchResId_div" cssClass="link"><s:text name="MTViewTimeSheetCategoryEmp"/></sj:a> |
			<sj:a href="TimesheetCategorySearchEmp.action" targets="submenu_timesheetCategoryEmp_searchList_div_id" indicator="indicatorSubMenuTimesheetCategoryEmpSearchResId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategoryEmp"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimesheetCategoryEmpSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.TimesheetCategoryEmp.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.form.fields.TimesheetCategoryEmp.TimecategorynameList" var="HTimesheetCategoryEmp"></s:text>
	  <s:text name="label.form.fields.TimesheetCategoryEmp.Empname" var="HEmployeeName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_timeSheetCategorySearchList_div_id">
		   <display:table class="tableborder" id="timesheetCategoryEmplistId" name="timesheetCategoryEmplist" pagesize="${NO_OF_RECORDS}" requestURI="timesheetCategoryEmpSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sTimesheetCategoryEmpId = ((TimesheetCategoryEmpVO)pageContext.getAttribute("timesheetCategoryEmplistId")).getHcmoTimesheetCategoryEmpId().toString();
		        	request.setAttribute("TimesheetCategoryEmpId", sTimesheetCategoryEmpId);	
		    	}catch(NullPointerException ne){
		        }
		    %>
		    <display:column property="timesheetCategoryName.name" title="${HTimesheetCategoryEmp}" sortable="true" headerClass="sortable"/>
		    <display:column property="employeeName.empFirstName" title="${HEmployeeName}" sortable="true" headerClass="sortable"/>
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewTimesheetCategoryEmp" action="timesheetCategoryEmpView">
						<s:param name="timesheetCategoryEmp.hcmoTimesheetCategoryEmpId" value="#request.TimesheetCategoryEmpId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategoryEmp_searchList_div_id','%{listViewTimesheetCategoryEmp}','');"><s:text name="View"/></s:a>
				</display:column>
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpTimesheetCategoryEmp" action="setUpInsertOrUpdateTimesheetCategoryEmp">
						<s:param name="timesheetCategoryEmp.hcmoTimesheetCategoryEmpId" value="#request.TimesheetCategoryEmpId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategoryEmp_searchList_div_id','%{listSetUpTimesheetCategoryEmp}','');"><s:text name="Edit"/></s:a>
				</display:column>
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteTimesheetCategoryEmp" action="deleteTimesheetCategoryEmp">
						<s:param name="timesheetCategoryEmp.hcmoTimesheetCategoryEmpId" value="#request.TimesheetCategoryEmpId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategoryEmp_searchList_div_id','%{listDeleteTimesheetCategoryEmp}','');"><s:text name="Delete"/></s:a>
				</display:column>
			 <display:setProperty name="export.csv.filename" value="Timesheet Category Emp.csv"/>
			 <display:setProperty name="export.excel.filename" value="Timesheet Category Emp.xls"/>
			 <display:setProperty name="export.xml.filename" value="Timesheet Category Emp.xml"/>
		  </display:table>
	  </div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_timeSheetCategorySearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     