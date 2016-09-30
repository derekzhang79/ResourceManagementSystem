<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="report_EmployeeBirthday_div">
<div class="submenu_bg">
		<sj:a href="showBirthdayReports.action" targets="report_EmployeeBirthday_div" indicator="indicatorGenerateBirthdayReport" cssClass="link"><s:text name="MTIGenerateBirthdayReport" /></sj:a>
</div>
<img id="indicatorGenerateBirthdayReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<img id="indicatorGenerateBirthdayReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsEmployeeBirthday" id="reports_birthDayForm_id">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.employee.birthday" />
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
								    /><br/><br/>
					    				<s:text name="label.header.benefit.selectBoxHint"/>
								</td>
								<td class="inputerrormessage"></td>
					        </tr>
				       </s:if>
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
					        </tr>
				       </s:else>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeBirthdayForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				</td>
	   		    <td>
	   		    <div class="button-comments">
	   		    <div class="button-left"></div>
	   		    	<s:submit key="button.label.generatereports" cssClass="button-midle"/>
	   		    <div class="button-right"></div>
	   		    </div>
	   		    </td>
				<td>
					<s:url var="BirthdayReportsPreview" action="getBirthdayReportsPreview"/>				    
	   		    	<sj:submit formIds="reports_birthDayForm_id" key="button.label.preview" href="%{#BirthdayReportsPreview}" cssClass="submitbutton117" targets="report_EmployeeBirthday_div" indicator="indicatorEmployeeBirthdayForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>