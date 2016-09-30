<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_TimeSheetSummationId_div">
        <div class="submenu_bg">
			<sj:a href="showTimeSheetReports.action" targets="report_TimeSheetSummationId_div" indicator="indicatorGenerateTimeSheetReportSummationId_div" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
			<sj:a href="showTimeEstimationReports.action" targets="report_TimeSheetSummationId_div" indicator="indicatorGenerateTimeSheetReportSummationId_div" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
			
			<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			| <sj:a href="showSummationTimeSheetReports.action" targets="report_TimeSheetSummationId_div" indicator="indicatorGenerateTimeSheetReportSummationId_div" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
			  <sj:a href="showTimeSheetProjectSummationReports.action" targets="report_TimeSheetSummationId_div" indicator="indicatorGenerateTimeSheetReportSummationId_div" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorGenerateTimeSheetReportSummationId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsTimeSheetSummationAction">
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
	                <!--Button image added by, R.Deepika-->
	                <!-- Extra text Removed by, R.Deepika -->
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.reports.fromDate" /></td>
				<td class="employeeinputtd">
	       	       <sj:datepicker name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
				</td>
				<td class="inputerrormessage"></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.reports.toDate" /></td>
				<td class="employeeinputtd">
	       	       <sj:datepicker name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	  
				</td>
			</tr>
			<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>
			<tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
	             <td class="employeeinputtd">
					<s:select 
						headerKey=""
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFirstName"
					    name="report.empIdObjList.employeeId"
					    cssClass="employeeselect"
					    multiple="true"
					    size="3"
				    />
				</td>
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
						indicator="indicatorTimesheetListReportSummationId_div"      
					    cssClass="employeeselect" 
					/>
					<img id="indicatorTimesheetListReportSummationId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	        </tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<div class="button-comments">
			   		    <div class="button-left"></div>
		   		    		<s:submit key="button.label.generateProjectReports" cssClass="button-midle"/>
		   		    	<div class="button-right"></div>
		   		    </div>
				</td>
				<td>
					<div class="button-comments">
			   		    <div class="button-left"></div>
			   		    	<s:submit action="reportsTimeSheetCategorySummationAction" key="button.label.generateCategoryReports" cssClass="button-midle"></s:submit>
		   		    	<div class="button-right"></div>
		   		    </div>
				</td>
				<!-- <td>
					<img id="indicatorTimesheetReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="TimesheetreportPreview" action="getTimeSheetReportsPreview"/>				    
	   		    	<sj:submit key="button.label.preview" href="%{#TimesheetreportPreview}" cssClass="submitbutton117" targets="report_TimeSheetSummationId_div" indicator="indicatorTimesheetReport"/>
				</td>  -->
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>