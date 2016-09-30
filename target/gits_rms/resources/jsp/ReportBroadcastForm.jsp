<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_Broadcast_div">
<div class="submenu_bg">
		<sj:a href="showBroadcastReports.action" targets="report_Broadcast_div" indicator="indicatorGenerateBroadcastReportFormId_div" cssClass="link"><s:text name="MTIGenerateBroadcastReport" /></sj:a>
	</div>
	<img id="indicatorGenerateBroadcastReportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsBroadcastMessageAction">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.reports.broadcastForm" />
	                  </td>
	                </tr>
	                <tr>
	                <!--Button image added by, R.Deepika-->
	                <!-- Extra text Removed by R.Deepika -->
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
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorBroadcastForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				</td>
				<td>
					<div class="button-comments">
		   		    <div class="button-left"></div>
		   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
		   		    <div class="button-right"></div>
		   		    </div>
				</td>
				<td>
					<s:url var="BroadcastReportPreview" action="getBroadcastReportPreview"/>				    
	   		    	<sj:submit key="button.label.preview" href="%{#BroadcastReportPreview}" cssClass="submitbutton117" targets="report_Broadcast_div" indicator="indicatorBroadcastForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>
	<br/>
	<div id="report_BroadcastResult_div"></div>