<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_TimeSheet_Estimation_div">
	<div class="submenu_bg">
		<sj:a href="showTimeSheetReports.action" targets="report_TimeSheet_Estimation_div" indicator="indicatorGenerateTimeEstimationReport" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
		<sj:a href="showTimeEstimationReports.action" targets="report_TimeSheet_Estimation_div" indicator="indicatorGenerateTimeEstimationReport" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
		
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
		| <sj:a href="showSummationTimeSheetReports.action" targets="report_TimeSheet_Estimation_div" indicator="indicatorGenerateTimeEstimationReport" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
		  <sj:a href="showTimeSheetProjectSummationReports.action" targets="report_TimeSheet_Estimation_div" indicator="indicatorGenerateTimeEstimationReport" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
		</s:if>
	</div>
	<img id="indicatorGenerateTimeEstimationReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsTimesheetEstimation" id="reportsTimesheetEstimationFormId">
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
	                <!-- Extra Date format text Removed by, R.Deepika -->
	                  <td class="forminner"><table class="tablealign">
			<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.timesheet.MandatoryFromDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="fromDate" name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	       
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.MandatoryToDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="toDate" name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
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
							headerValue="-- Please Select --"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFirstName"
						    name="report.empObj.employeeId"
						    cssClass="employeeselect"
					    />
					</td>
		        </tr>
		        <tr>
		            <td class="inputtext"><s:text name="label.header.role.customername"/></td>
		             <td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_CUSTOMER_LIST"
							listKey="customerId"
							listValue="customerName"
						    name="report.cust.customerId"
						    cssClass="employeeselect"
					    />
					</td>
		        </tr>
		        <tr>
		            <td class="inputtext"><s:text name="label.header.project.name"/></td>
		             <td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_PROJECT_LIST"
							listKey="projectId"
							listValue="projectName"
						    name="report.projObj.projectId"
						    cssClass="employeeselect"
					    />
					</td>
		        </tr>
		        <tr>
		            <td class="inputtext"><s:text name="label.header.projActivity.projectActi"/></td>
		             <td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_PROJECTACTIVITY_LIST"
							listKey="projectActivityId"
							listValue="activityName"
						    name="report.projActivityObj.projectActivityId"
						    cssClass="employeeselect"
					    />
					</td>
		        </tr>
			 </s:if>
			 <s:elseif test="#session.TIMESHEET_APPROVER == 'BOTH'">
			 	<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.timesheet.MandatoryFromDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="fromDate" name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	      
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.MandatoryToDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="toDate" name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	       
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
	            			headerValue="-- Please Select --"
							list="#request.timeSheetSubEmpListReport"
							listKey="employeeId"
							listValue="empFirstName"
						    name="report.empObj.employeeId"
						    cssClass="employeeselect"
               			 />
					</td>
				</tr>
	         </s:elseif>
	         <s:else>
	         	<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.timesheet.MandatoryFromDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="fromDate" name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	       
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.MandatoryToDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="toDate" name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	      
					</td>
				</tr>
				<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>
	         </s:else>
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
				<td>
					<img id="indicatorTimeEstimation" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="getTimeEstimationReportsPreview" action="getTimeEstimationReportsPreview"/>				    
	   		    	<sj:submit formIds="reportsTimesheetEstimationFormId" key="button.label.preview" href="%{#getTimeEstimationReportsPreview}" cssClass="submitbutton117" targets="report_TimeSheet_Estimation_div" indicator="indicatorTimeEstimation"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>

<script type="text/javascript">
	$('#reportsTimesheetEstimationFormId').submit(function(){
		var fromDate = $('#fromDate').val();
		var toDate = $('#toDate').val();
		if((fromDate ==  null)||(fromDate ==  "")){
			alert("From Date is a required field");
			return false;
		}

		if((toDate ==  null)||(toDate ==  "")){
			alert("To Date is a required field");
			return false;
		}

		if(toDate < fromDate){
			alert("The To Date must be after the From date");
			return false;
		}
		
		return true;

	});
</script>