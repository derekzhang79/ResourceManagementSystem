<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_Leave_div">
        <div class="submenu_bg">
			<sj:a href="showLeaveReports.action" targets="report_Leave_div" indicator="indicatorGenerateLeaveReportHistFormId_div" cssClass="link"><s:text name="MTIGenerateLeaveReport" /></sj:a>
		</div>
		<br/>
		<img id="indicatorGenerateLeaveReportHistFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="reportsLeave" id="reports_form_id">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						 <s:text name="label.title.reports.leaveForm"/>
					  </td>
	                </tr>
	                <!--Button image added by, R.Deepika-->
	                <!--  Extra text removed by, R.Deepika -->
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         			<tr>
	         				<td class="inputtext"><s:text name="label.header.common.reports.fromDate"/></td>
				         	<td class="employeeinputtd">
				       	       <sj:datepicker name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				       	       
				         	</td>
				         	<td class="inputerrormessage"></td>
				        </tr>
				        <tr>
	         				<td class="inputtext"><s:text name="label.header.common.reports.toDate"/></td>
				         	
				         	<td class="employeeinputtd">
				       	       <sj:datepicker name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				         	</td>
					        </tr>
					        <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>
				        <s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
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
					            <td class="inputtext"><s:text name="label.header.project.name"/></td>
					             <td class="employeeinputtd">
									<s:select 
										headerKey=""
										list="#request.APPL_PROJECT_LIST"
										listKey="projectId"
										listValue="projectName"
									    name="report.projIdObjList.projectId"
									    cssClass="employeeselect"
						    			multiple="true"
					    				size="3"
								    />
								</td>
								<td></td>
					        </tr>
					        <tr>
					            <td class="inputtext"><s:text name="label.header.role.customername"/></td>
					             <td class="employeeinputtd">
									<s:select 
										headerKey=""
										list="#request.APPL_CUSTOMER_LIST"
										listKey="customerId"
										listValue="customerName"
									    name="report.custIdObjList.customerId"
									    cssClass="employeeselect"
						    			multiple="true"
					    				size="3"
								    /><br/><br/>
					    				<s:text name="label.header.benefit.selectBoxHint"/>
								</td>
								<td></td>
					        </tr>
					         <tr>
					           <td class="inputtext"><s:text name="label.lrapp.status"/></td>
					           <td class="employeeinputtd">
					           		<s:url var="getLeaveAssignStatusJSONList" action="getLeaveAssignStatusJSONList"/>
									<sj:select
									    headerKey=" "
										headerValue="-- Please Select --"
										href="%{getLeaveAssignStatusJSONList}"
										list="leaveStatusList"
										name="report.leaveStatus"
									    dataType="json"
										indicator="leaveStatusReportIndicator"      
									    cssClass="employeeselect" 
									/>
									<img id="leaveStatusReportIndicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					       	   </td>
					       </tr>
				       </s:if>
				       <s:elseif test="#session.LEAVE_APPROVER == 'BOTH'">
				        <tr>
				            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
				             <td class="employeeinputtd">
								<s:select 
			            			headerKey=""
									list="#request.leaveSubEmpListReport"
									listKey="employeeId"
									listValue="empFirstName"
								    name="report.empIdObjList.employeeId"
								    cssClass="employeeselect"
					    			multiple="true"
				    				size="3"
		               			 /><br/><br/>
					    				<s:text name="label.header.benefit.selectBoxHint"/>
							</td>
					        </tr>
					        <tr>
					           <td class="inputtext"><s:text name="label.lrapp.status"/></td>
					           <td class="employeeinputtd">
					           		<s:url var="getLeaveAssignStatusJSONList" action="getLeaveAssignStatusJSONList"/>
									<sj:select
									    headerKey=" "
										headerValue="-- Please Select --"
										href="%{getLeaveAssignStatusJSONList}"
										list="leaveStatusList"
										name="report.leaveStatus"
									    dataType="json"
										indicator="leaveStatusReportIndicator"      
									    cssClass="employeeselect" 
									/>
									<img id="leaveStatusReportIndicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					       	   </td>
					       </tr>
				       </s:elseif>
				</table></td></tr></table></td></tr></table></td></tr></table>	    
	   		 <br/>
		    <table align="center"> 
	    	     <tr>
	    	     	<td>
						<img id="indicatorReportLeaveHistForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					</td>
	    		    <td>
						<div class="button-comments">
			   		    <div class="button-left"></div>
			   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
			   		    <div class="button-right"></div>
			   		    </div>
					</td>
	    	        <td>
	    	        	<s:url var="LeaveHistoryReportsPreview" action="getLeaveHistoryReportsPreview"/>
			        	<sj:submit formId="reports_form_id" targets="report_Leave_div" href="%{#LeaveHistoryReportsPreview}" key="button.label.preview" cssClass="resetbutton117" indicator="indicatorReportLeaveHistForm"/>
	    	        </td>
	    	        <td>
						<s:reset key="button.label.reset" cssClass="resetbutton117"/>&nbsp;
	    	        </td>
	    		 </tr>
	    	</table>
    	</s:form>
	</div>   	