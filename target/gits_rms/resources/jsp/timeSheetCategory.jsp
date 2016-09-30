<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.NationalityVO"%>

<%@page import="com.gits.rms.persistence.TimesheetCategoryHibernateDao"%>
<%@page import="com.gits.rms.vo.TimesheetCategoryVO"%>

<div id="submenu_timesheetCategory_listId_div">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategory.action" targets="submenu_timesheetCategory_listId_div" indicator="indicatorSubMenuTimeSheetCategoryId_div" cssClass="link"><s:text name="MTAddTimeSheetCategory" /></sj:a> |
			<sj:a href="getAllTimeSheetCategory.action" targets="submenu_timesheetCategory_listId_div" indicator="indicatorSubMenuTimeSheetCategoryId_div" cssClass="link"><s:text name="MTViewTimeSheetCategory"/></sj:a> |
			<sj:a href="TimesheetCategorySearch.action" targets="submenu_timesheetCategory_listId_div" indicator="indicatorSubMenuTimeSheetCategoryId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategory"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetCategoryId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.TimesheetCategory.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.form.fields.TimesheetCategoryEmp.TimecategorynameList" var="HTimesheetCategoryName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_timeSheetCategoryList_div_id">
		  <display:table class="tableborder" id="timesheetCategoryListId" name="timesheetCategorylist" pagesize="${NO_OF_RECORDS}" requestURI="getAllTimeSheetCategory.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sTimesheetCategoryId = ((TimesheetCategoryVO)pageContext.getAttribute("timesheetCategoryListId")).getHcmoTimesheetCategoryId().toString();
		        	request.setAttribute("TimesheetCategoryId", sTimesheetCategoryId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="name" title="${HTimesheetCategoryName}" sortable="true" headerClass="sortable"/>
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewTimesheetCategory" action="timesheetCategoryView">
						<s:param name="timesheetCategory.hcmoTimesheetCategoryId" value="#request.TimesheetCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategory_listId_div','%{listViewTimesheetCategory}','');"><s:text name="View"/></s:a>
				</display:column>
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpTimesheetCategory" action="setUpInsertOrUpdateTimesheetCategory">
						<s:param name="timesheetCategory.hcmoTimesheetCategoryId" value="#request.TimesheetCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategory_listId_div','%{listSetUpTimesheetCategory}','');"><s:text name="Edit"/></s:a>
				</display:column>
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteTimesheetCategory" action="deleteTimesheetCategory">
						<s:param name="timesheetCategory.hcmoTimesheetCategoryId" value="#request.TimesheetCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timesheetCategory_listId_div','%{listDeleteTimesheetCategory}','');"><s:text name="Delete"/></s:a>
				</display:column>
			 <display:setProperty name="export.csv.filename" value="Timesheet Category.csv"/>
			 <display:setProperty name="export.excel.filename" value="Timesheet Category.xls"/>
			 <display:setProperty name="export.xml.filename" value="Timesheet Category.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_timeSheetCategoryList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>