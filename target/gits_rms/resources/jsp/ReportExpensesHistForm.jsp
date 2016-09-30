<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_Expenses_div">
		<div class="submenu_bg">
			<sj:a href="showExpensesHistReports.action" targets="report_Expenses_div" indicator="indicatorGenerateExpenseReportHistFormId_div" cssClass="link"><s:text name="MTIGenerateExpenseReport" /></sj:a>
		</div>
		<br/>
		<img id="indicatorGenerateExpenseReportHistFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsExpensesHistory">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.expensesReport.form" />
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
					</tr>
				</s:if>
				<s:elseif test="#session.EXPENSES_APPROVER == 'BOTH'">
			        <tr>
			            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
			             <td class="employeeinputtd">
							<s:select 
		            			headerKey=""
								headerValue="-- Please Select --"
								list="#request.expensesSubEmpListReport"
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
		       </s:elseif>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorExpensesReportsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				</td>
				<td>
					<div class="button-comments">
		   		    <div class="button-left"></div>
		   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
		   		    <div class="button-right"></div>
		   		    </div>
				</td>
				<td>
					<s:url var="ExpensesStatusTrackerReportsPreview" action="getExpensesStatusTrackerReportsPreview"/>				    
	   		    	<sj:submit key="button.label.preview" href="%{#ExpensesStatusTrackerReportsPreview}" cssClass="submitbutton117" targets="report_Expenses_div" indicator="indicatorExpensesReportsForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>