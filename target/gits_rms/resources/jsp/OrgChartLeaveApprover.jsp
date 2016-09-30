<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.LeaveApproverVO"%>

<div id="submenu_orgChartleaveApprover_list_div_id">
		<br/>
		<img id="indicatorSubMenuLeaveApproverOrghartId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.leaveapprover.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.leaveapprover.leaveApprovingEmployee" var="HLeaveApproverName"></s:text>
	  		   
	 <div id="display_tag_orgChartLeaveApproverList_div_id">
		 <display:table class="tableborder" id="leaveApproverListId" name="leaveApproverList" pagesize="${NO_OF_RECORDS}" requestURI="orgChartEmployeeLeaveApproverNewTab.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sLeaveApproverId = ((LeaveApproverVO)pageContext.getAttribute("leaveApproverListId")).getHcmoLeaveApproverId().toString();
		        	request.setAttribute("LeaveApproverId", sLeaveApproverId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoApprovingEmpId.empFullName" title="${HLeaveApproverName}" sortable="true" headerClass="sortable"/>
			 <display:setProperty name="export.csv.filename" value="LeaveApprover.csv"/>
			 <display:setProperty name="export.excel.filename" value="LeaveApprover.xls"/>
			 <display:setProperty name="export.xml.filename" value="LeaveApprover.xml"/>
		  </display:table>
	 </div>
	  
</div>  
 <script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_orgChartLeaveApproverList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       