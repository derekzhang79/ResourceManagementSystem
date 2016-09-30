<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_Employee_div">
	<div class="submenu_bg">
		<sj:a href="showEmployeeReports.action" targets="report_Employee_div" indicator="indicatorGenerateEmployeeReportFormId_div" cssClass="link"><s:text name="MTIGenerateEmployeeReport" /></sj:a>
	</div>
	<br/>
	<img id="indicatorGenerateEmployeeReportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsEmployeeAction" id="reports_Employeeform_id">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.reports.employeeForm" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
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
									<td class="inputerrormessage"></td>
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
								<td class="inputerrormessage"></td>
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
						<td class="inputerrormessage"></td>
					        </tr>
				       </s:if>
				       <s:elseif test="#session.EMPLOYEE_REPORTING_TO == 'REPORTING-EMPLOYEE'">
				            <tr>
					            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
					             <td class="employeeinputtd">
									<s:select 
										headerKey=""
										list="#request.subEmpReportingToList"
										listKey="employeeId"
										listValue="empFirstName"
									    name="report.empIdObjList.employeeId"
									    cssClass="employeeselect"
									    multiple="true"
								    	size="3"
									    /><br/>
									    <s:text name="label.header.benefit.selectBoxHint"/>
								</td>
								<td></td>
								<td class="inputerrormessage"></td>
					        </tr>
				       </s:elseif>
				       <s:else>
					        <tr>
					            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
					             <td class="employeeinputtd">
									<s:select 
										headerKey=""
										list="#request.subEmpReportingToList"
										listKey="employeeId"
										listValue="empFirstName"
									    name="report.empIdObjList.employeeId"
									    cssClass="employeeselect"
									    multiple="true"
								    	size="3"
									    />
								</td>
								<td class="inputerrormessage"></td>
					        </tr>
				       </s:else>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeReportsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				</td>
				<td>
					<div class="button-comments">
		   		    <div class="button-left"></div>
		   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
		   		    <div class="button-right"></div>
		   		    </div>
				</td>
				<td>
					<s:url var="EmployeeReportsPreview" action="getEmployeeReportsPreview"/>				    
	   		    	<sj:submit formIds="reports_Employeeform_id" key="button.label.preview" href="%{#EmployeeReportsPreview}" cssClass="submitbutton117" targets="report_Employee_div" indicator="indicatorEmployeeReportsForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>