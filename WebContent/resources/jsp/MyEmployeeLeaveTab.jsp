<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
	<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.title.Leave.list"/>
			</td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="Leave" class="vidbox2" href="http://www.youtube.com/watch?v=X7833IbP4ro"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
	<s:url var="setUpLeaveReqsApprovalTab" action="setUpLeaveReqsApproval"></s:url>
	<s:url var="setUpLeaveReqsAssignedTab" action="setUpLeaveReqsAssigned"></s:url>
	<s:url var="getAllLeaveForApprovalTab" action="getAllLeaveForApprovalTab"></s:url>
	<s:url var="getAllLeaveApprovalTab" action="getAllLeaveApprovalTab"></s:url>
	<s:url var="getAllLeaveDisApprovalTab" action="getAllLeaveDisApprovalTab"></s:url>
	<s:url var="viewAssignedFormTab" action="viewAssignedForm"></s:url>
	<s:url var="viewCancelledFormTab" action="viewCancelForm"></s:url>
	<s:url var="setUpSubEmpLeaveHistoryTab" action="setUpSubEmpLeaveHistory"></s:url>
	
	<sj:tabbedpanel id="menuApproverLeaveTabbedpannel" animate="true">
		<sj:tab id="LeaveSetUpLeaveReqsApprovalMainTab" key="MTNewLeaveRequest" href="%{setUpLeaveReqsApprovalTab}" />
		<sj:tab id="LeaveSetUpLeaveReqsAssignedMainTab" key="MTLeaveAssigned" href="%{setUpLeaveReqsAssignedTab}" />
		<sj:tab id="LeaveForApprovalMainTab" key="MTForApproval" href="%{getAllLeaveForApprovalTab}" />
		<sj:tab id="LeaveApprovedMainTab" key="MTApproved" href="%{getAllLeaveApprovalTab}" />
		<sj:tab id="LeaveDisApprovedMainTab" key="MTDisApproved" href="%{getAllLeaveDisApprovalTab}" />
		<sj:tab id="LeaveViewAssignFormMainTab" key="MTViewAssignLeave" href="%{viewAssignedFormTab}" />
		<sj:tab id="LeaveViewCancelFormMainTab" key="MTViewCancelLeave" href="%{viewCancelledFormTab}" />
		<sj:tab id="LeaveSearchHistoryMainTab" key="MTSearchLeaveHistory" href="%{setUpSubEmpLeaveHistoryTab}" />
	</sj:tabbedpanel>
	
 <script>
	//Pop-up for Video in modules
	jQuery(document).ready(function() {
		try{
   			jQuery(".vidbox2").jqvideobox({
   			'width' : 600,
   			'height' : 400,
    		'getimage' : false,
   			'navigation' : true
   			});	
  		}catch(e){
  
  		 }
	}); 
 </script>
	