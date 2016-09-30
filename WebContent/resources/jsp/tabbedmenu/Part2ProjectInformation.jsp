<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	

<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.title.project"/>
			</td>
			<td class="employeedisplaytd">
		 	<table align="right"><tr><td class="video"><a title="Projects" class="vidbox2" href="http://www.youtube.com/watch?v=-jeuYrBLNT0"><b>videos</b></a></td></tr></table>
				<!--<table align="right"><tr> <td class="video1"> <a title="Projects" class="vidbox2" href="http://www.youtube.com/watch?v=-jeuYrBLNT0">
					<b style="float: right"><font color="white">videos</font></b></a></td></tr></table>-->
			</td>
		</tr>
	</table>
	<s:url var="getSubTabCustomerInfo" action="getAllCustomer"/>
	<s:url var="getSubTabProjectDatails" action="MenuPart2SubProjDetails"/>
	<s:url var="assignTimesheetProject" action="getAllTsProjectAssigned"></s:url>
	<s:url var="getTabEmpSchedulerList" action="empScheduleForm"/>
	<s:url var="getTabTargetAndGoalsInfo" action="MenuPart2TargetAndGoalsInfo" />
	
	<sj:tabbedpanel id="remoteTabbedPannelProjectInformation" animate="true">
	
		<s:if test="#session.TIMESHEETAPPROVER_ADD == true || #session.TIMESHEETAPPROVER_VIEW == true">
				<sj:tab id="EmpSchedulerMainTab" key="MTEmployeeScheduler" href="%{getTabEmpSchedulerList}"/>
		</s:if>
		
		<s:if test="#session.CUSTOMER_ADD == true ||
					#session.CUSTOMER_VIEW == true">
			<sj:tab id="CustomerMainTab" key="MTCustomer" href="%{getSubTabCustomerInfo}"/>
		</s:if>
		
		<s:if test="#session.PROJECT_ADD == true ||
					#session.PROJECT_VIEW == true ||
					#session.PROJECTACTIVITY_ADD == true ||
					#session.PROJECTACTIVITY_VIEW == true">
			<sj:tab id="ProjectDetailsMainTab" key="MTProjectDeatils" href="%{getSubTabProjectDatails}"/>
		</s:if>
		<s:if test="#session.TIMESHEET_APPROVER == 'BOTH'">
			<sj:tab id="assignProjectTab" key="MTIAssignProject" href="%{assignTimesheetProject}"/>
		</s:if>
		
		<sj:tab id="targetAndGoalsTab" key="MTTargets" href="%{getTabTargetAndGoalsInfo}"/>
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