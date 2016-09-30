<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_Project_div">
<div class="submenu_bg">
		<sj:a href="showProjectReports.action" targets="report_Project_div" indicator="indicatorGenerateProjectReportformId_div" cssClass="link"><s:text name="MTIGenerateProjectReport" /></sj:a>
</div>
<img id="indicatorGenerateProjectReportformId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsProjectAction" id="report_project_form_id">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.project.report.form" />
	                  </td>
	                </tr>
	                <tr>
	                <!--Button image added by, R.Deepika-->
	                <!-- Extra text Removed by, R.Deepika -->
	                  <td class="forminner"><table class="tablealign">
   			<tr>
				<td class="inputtext"><s:text name="label.header.common.reports.fromDate" /></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	      
				</td>
				<td class="inputerrormessage"></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.reports.toDate" /></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
				</td>
			</tr>
			<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
			<tr>
	            <td class="inputtext"><s:text name="label.header.customer.name"/></td>
	             <td class="employeeinputtd">
					<s:select 
						headerKey=""
						list="#request.APPL_CUSTOMER_LIST"
						listKey="customerId"
						listValue="customerName"
					    name="report.cust.customerId"
					    cssClass="employeeselect"
					   />
				</td>
	        </tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorReportProjectForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				</td>
				<td>
					<div class="button-comments">
		   		    <div class="button-left"></div>
		   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
		   		    <div class="button-right"></div>
		   		    </div>
				</td>
				<td>
					<s:url var="ProjectReportsPreview" action="getProjectReportsPreview"/>				    
	   		    	<sj:submit formIds="report_project_form_id"  key="button.label.preview" href="%{#ProjectReportsPreview}" cssClass="submitbutton117" targets="report_Project_div" indicator="indicatorReportProjectForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>
	<br/>