<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_TimeSheetPreojectSummation_div">
        <div class="submenu_bg">
			<sj:a href="showTimeSheetReports.action" targets="report_TimeSheetPreojectSummation_div" indicator="indicatorGenerateTimeSheetReportSummationFormId_div" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
			<sj:a href="showTimeEstimationReports.action" targets="report_TimeSheetPreojectSummation_div" indicator="indicatorGenerateTimeSheetReportSummationFormId_div" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
			
			<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			| <sj:a href="showSummationTimeSheetReports.action" targets="report_TimeSheetPreojectSummation_div" indicator="indicatorGenerateTimeSheetReportSummationFormId_div" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
       		  <sj:a href="showTimeSheetProjectSummationReports.action" targets="report_TimeSheetPreojectSummation_div" indicator="indicatorGenerateTimeSheetReportSummationFormId_div" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorGenerateTimeSheetReportSummationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsTimeSheetProjectSummationAction">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.reports.TimeSheetForm" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
	            <td class="inputtext"><s:text name="label.header.project.name"/></td>
	             <td class="employeeinputtd">
					<s:select 
						headerKey=""
						list="#request.APPL_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
					    name="report.projIdObjList.projectId"
					    cssClass="employeeselect"
				    />
				</td>
				<td class="inputerrormessage"></td>
	        </tr>
	        <tr>
	            <td class="inputtext"><s:text name="label.lrapp.status"/></td>
	             <td class="employeeinputtd">
	             	<s:url var="getTimesheetStatusJSONList" action="getTimesheetStatusJSONList"/>
					<sj:select
					    headerKey=" "
						headerValue="-- Please Select --"
						href="%{getTimesheetStatusJSONList}"
						list="timesheetStatusList"
						name="report.timeSheetType"
					    dataType="json"
						indicator="indicatorTimesheetList"      
					    cssClass="employeeselect" 
					/>
					<img id="indicatorTimesheetList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					
	       	    </td>
	        </tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<div class="button-comments">
		   		    <div class="button-left"></div>
	   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
	   		    	<div class="button-right"></div>
		   		    </div>
				</td>
				<!-- <td>
					<img id="indicatorTimesheetReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="TimesheetreportPreview" action="getTimeSheetReportsPreview"/>				    
	   		    	<sj:submit key="button.label.preview" href="%{#TimesheetreportPreview}" cssClass="submitbutton117" targets="report_TimeSheetPreojectSummation_div" indicator="indicatorTimesheetReport"/>
				</td> -->
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>  
			</tr>
		</table>
	</s:form>
</div>