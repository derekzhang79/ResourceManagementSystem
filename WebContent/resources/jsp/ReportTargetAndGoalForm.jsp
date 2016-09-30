<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_TargetAndGoalForm_div">
	<div class="submenu_bg">
		<sj:a href="showTargetAndGoalReports.action" targets="report_TargetAndGoalForm_div" indicator="indicatorGenerateTargetAndGoalReportForm" cssClass="link"><s:text name="MTIGenerateTargetReport" /></sj:a>
	</div>
	<img id="indicatorGenerateTargetAndGoalReportForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsTargetAndGoal" id="reportsTargetFormId">
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
	                <!--Button image added by, R.Deepika-->
	                <!-- Extra Date format text Removed by, R.Deepika -->
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                  
	            <tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.timesheet.MandatoryFromDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="targetFromDate" name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	      
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.reports.MandatoryToDate" /></td>
					<td class="employeeinputtd">
		       	       <sj:datepicker id="targeToDate" name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
					</td>
				</tr> 
				<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>     
			<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
				<tr>
		            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		             <td class="employeeinputtd">
						<s:select 
							id="targetReportEmpId"
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
			 </s:if>
			 <s:elseif test="#session.TIMESHEET_APPROVER == 'BOTH'">
			 	<tr>
		            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		             <td class="employeeinputtd">
						<s:select 
							id="targetReportEmpId"
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
					<img id="indicatorTargetAndGoal" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="getTargetAndGoalReportsPreview" action="getTargetAndGoalReportsPreview"/>				    
	   		    	<sj:submit formIds="reportsTargetAndGoalFormId" key="button.label.preview" href="%{#getTargetAndGoalReportsPreview}" cssClass="submitbutton117" targets="report_TargetAndGoalForm_div" indicator="indicatorTargetAndGoal"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>

<script type="text/javascript">
	$('#reportsTargetFormId').submit(function(){
		var fromDate = $('#targetFromDate').val();
		var toDate = $('#targeToDate').val();
		var targetReportEmpId = $('#targetReportEmpId').val();
		
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

		if((targetReportEmpId ==  null)||(targetReportEmpId ==  "")){
			alert("Employee Name is a required field");
			return false;
		}
		
		return true;

	});
</script>