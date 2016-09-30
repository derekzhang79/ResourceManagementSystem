<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TimeSheetApproverVO"%>

<div id="submenu_OrgcharttimeSheetApprover_list_div_id">
		<br/>
		<img id="indicatorSubMenuOrgchartTimeSheetApproverId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.timesheetapprover.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.timesheet.timesheetApprovingEmployee" var="HTimeSheetApprover"></s:text>
	  		   
	  <div id="display_tag_orgChartTimeSheetList_div_id">
		  <display:table class="tableborder" id="tsApproverListId" name="timeSheetApproverList" pagesize="${NO_OF_RECORDS}" requestURI="orgChartEmployeeTimesheetApproverNewTab.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sTSApproverId = ((TimeSheetApproverVO)pageContext.getAttribute("tsApproverListId")).getHcmoApproverId().toString();
		        	request.setAttribute("TSApproverId", sTSApproverId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		  
		    <display:column property="hcmoApprovingEmpId.empFullName" title="${HTimeSheetApprover}" sortable="true" headerClass="sortable"/>
		
			 <display:setProperty name="export.csv.filename" value="TimeSheetApprover.csv"/>
			 <display:setProperty name="export.excel.filename" value="TimeSheetApprover.xls"/>
			 <display:setProperty name="export.xml.filename" value="TimeSheetApprover.xml"/>
		  </display:table>    
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_orgChartTimeSheetList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    