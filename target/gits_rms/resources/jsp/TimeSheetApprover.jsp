<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TimeSheetApproverVO"%>

<div id="submenu_timeSheetApprover_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.TIMESHEETAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateTimeSheetApprover.action" targets="submenu_timeSheetApprover_list_div_id" indicator="indicatorSubMenuTimeSheetApproverId_Form" cssClass="link"><s:text name="MTIAddTimeSheetApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW == true">
			<sj:a href="getAllTimeSheetApprover.action" targets="submenu_timeSheetApprover_list_div_id" indicator="indicatorSubMenuTimeSheetApproverId_Form" cssClass="link"><s:text name="MTIViewTimeSheetApprover"/></sj:a> |
			<sj:a href="timeAppSearchForm.action" targets="submenu_timeSheetApprover_list_div_id" indicator="indicatorSubMenuTimeSheetApproverId_Form" cssClass="link"><s:text name="MTISearchTimeSheetApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetApproverId_Form" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.timesheetapprover.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.timesheet.EmployeeName" var="HTimesheetEmployee"></s:text>
	  <s:text name="label.header.timesheet.timesheetApprovingEmployee" var="HTimeSheetApprover"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text> 
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_timeSheetApproverList_div_id">
		  <display:table class="tableborder" id="tsApproverListId" name="timeSheetApproverList" pagesize="${NO_OF_RECORDS}" requestURI="getAllTimeSheetApprover.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sTSApproverId = ((TimeSheetApproverVO)pageContext.getAttribute("tsApproverListId")).getHcmoApproverId().toString();
		        	request.setAttribute("TSApproverId", sTSApproverId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		  
		    <display:column property="hcmoEmployeeId.empFirstName" title="${HTimesheetEmployee}" sortable="true" headerClass="sortable"/>
		    <display:column property="hcmoApprovingEmpId.empFirstName" title="${HTimeSheetApprover}" sortable="true" headerClass="sortable"/>
		
		    <s:if test="#session.TIMESHEETAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewTSApprover" action="timesheetApproverView">
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.TSApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timeSheetApprover_list_div_id','%{listViewTSApprover}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		
		    <s:if test="#session.TIMESHEETAPPROVER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpTSApprover" action="setUpInsertOrUpdateTimeSheetApprover">
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.TSApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timeSheetApprover_list_div_id','%{listSetUpTSApprover}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.TIMESHEETAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteTSApprover" action="deleteTimeSheetApprover">
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.TSApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_timeSheetApprover_list_div_id','%{listDeleteTSApprover}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="TimesheetApprover.csv"/>
			 <display:setProperty name="export.excel.filename" value="TimesheetApprover.xls"/>
			 <display:setProperty name="export.xml.filename" value="TimesheetApprover.xml"/>
		  </display:table> 
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_timeSheetApproverList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     