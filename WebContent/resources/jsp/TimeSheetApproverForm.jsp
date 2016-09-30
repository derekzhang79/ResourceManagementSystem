<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimeSheetApproverFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.TIMESHEETAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateTimeSheetApprover.action" targets="submenu_TimeSheetApproverFormId_div" indicator="indicatorSubMenuTimeSheetApproverFormId_div" cssClass="link"><s:text name="MTIAddTimeSheetApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW == true">
			<sj:a href="getAllTimeSheetApprover.action" targets="submenu_TimeSheetApproverFormId_div" indicator="indicatorSubMenuTimeSheetApproverFormId_div" cssClass="link"><s:text name="MTIViewTimeSheetApprover"/></sj:a> |
			<sj:a href="timeAppSearchForm.action" targets="submenu_TimeSheetApproverFormId_div" indicator="indicatorSubMenuTimeSheetApproverFormId_div" cssClass="link"><s:text name="MTISearchTimeSheetApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuTimeSheetApproverFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateTimeSheetApprover">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="timeSheetApprover==null || timeSheetApprover.hcmoApproverId == null">
								<s:text name="label.title.timesheetapprover.add" />
							</s:if> <s:else>
								<s:text name="label.title.timesheetapprover.edit" />
							</s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<s:if test="timeSheetApprover==null || timeSheetApprover.hcmoApproverId == null">
					<td class="inputtext"><s:text name="label.form.fields.timesheet.employeeName" /></td>
					<td class="employeeinputtd">
						<sj:autocompleter  
						    name="timeSheetApprover.hcmoEmployeeId.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="timeSheetApprover.hcmoEmployeeId.employeeId" /> </td>
				</s:if>
				<s:else>
					<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="timeSheetApprover.hcmoEmployeeId.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="timeSheetApprover.hcmoEmployeeId.employeeId" />
				</s:else>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.timesheet.approvingEmployeeName" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="timeSheetApprover.hcmoApprovingEmpId.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="timeSheetApprover.hcmoApprovingEmpId.employeeId" /> </td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center"> 
			<tr>
				<td>
					<img id="indicatorTimesheetApprFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TimeSheetApproverFormId_div" indicator="indicatorTimesheetApprFormId_div"/>
				</td>
				<s:if test="timeSheetApprover==null || timeSheetApprover.hcmoApproverId==null">
	    		    <td>
	    		    	<s:url var="resetTimeSheetApprover" action="resetTimeSheetApprover"></s:url>
	    	            <sj:submit href="%{resetTimeSheetApprover}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_TimeSheetApproverFormId_div" indicator="indicatorTimesheetApprFormId_div"/>
					</td>
				</s:if>
				<s:else>
    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	        </s:else>
			</tr>
		</table>
		<s:hidden name="timeSheetApprover.hcmoApproverId" />
	</s:form>
</div>	