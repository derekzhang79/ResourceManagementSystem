<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="MTimesheet"/>
			</td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="TimeSheet" class="vidbox2" href="http://www.youtube.com/watch?v=J_STh19uD4c"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
	<s:url var="employeeEnterTimeSheet" action="TimeSheetUpdate"></s:url>
	<s:url var="approverViewAndApproveTimeSheet" action="projectAssignedViewForm"></s:url>
	
	<sj:tabbedpanel id="timesheetModuleMainTab" animate="true">
		<s:if test="#session.TIMESHEET_APPROVER == 'NON-APPROVER'">
			<sj:tab id="enterTimesheetEmployeeSubTab" key="MTIEnterTimesheet" href="%{employeeEnterTimeSheet}"/>
		</s:if>
		<s:if test="#session.TIMESHEET_APPROVER == 'BOTH'">
			<sj:tab id="enterTimesheetApproverSubTab" key="MTIEnterTimesheet" href="%{employeeEnterTimeSheet}"/>
			<sj:tab id="approveTimesheetApproverSubTab" key="MTIViewTimesheet" href="%{approverViewAndApproveTimeSheet}"/>
		</s:if>
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
 