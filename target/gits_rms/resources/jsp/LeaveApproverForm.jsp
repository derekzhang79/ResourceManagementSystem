<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LeaveApproverFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateLeaveApprover.action" targets="submenu_LeaveApproverFormId_div" indicator="indicatorSubMenuLeaveApproverFormId_div" cssClass="link"><s:text name="MTIAddLeaveApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_VIEW == true">
			<sj:a href="getAllLeaveApprover.action" targets="submenu_LeaveApproverFormId_div" indicator="indicatorSubMenuLeaveApproverFormId_div" cssClass="link"><s:text name="MTIViewLeaveApprover"/></sj:a> |
			<sj:a href="leaveAppSearchForm.action" targets="submenu_LeaveApproverFormId_div" indicator="indicatorSubMenuLeaveApproverFormId_div" cssClass="link"><s:text name="MTISearchLeaveApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuLeaveApproverFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateLeaveApprover">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">                  
	                   	<s:if test="leaveApprover==null || leaveApprover.hcmoLeaveApproverId == null">
					  		<s:text name="label.title.leaveapprover.add" />
					  	</s:if> <s:else>
					  		<s:text name="label.title.leaveapprover.edit" />
					  	</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
						<s:if test="leaveApprover==null || leaveApprover.hcmoLeaveApproverId == null">
							<td class="inputtext"><s:text name="label.form.fields.leaveapprover.employeeName" /></td>
							<td class="employeeinputtd">
								<sj:autocompleter  
								    name="leaveApprover.hcmoEmployeeId.employeeId"
									list="#request.APPL_EMPLOYEE_LIST"
									listKey="employeeId"
									listValue="empFullName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
						    	/>
							</td>
							<td class="inputerrormessage"><s:fielderror fieldName="leaveApprover.hcmoEmployeeId.employeeId" /></td>
	             		</s:if>
	             		<s:else>
	             			<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
							<td class="employeedisplaytd"><s:textfield name="leaveApprover.hcmoEmployeeId.empFirstName" readonly="true" cssClass="employeeinput"/></td>
							<s:hidden name="leaveApprover.hcmoEmployeeId.employeeId" />
	             		</s:else>
				    </tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.leaveapprover.approvingEmployee" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="leaveApprover.hcmoApprovingEmpId.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="leaveApprover.hcmoApprovingEmpId.employeeId" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
			</table>
			</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLeaveApproverFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveApproverFormId_div" indicator="indicatorLeaveApproverFormId_div"/>
				</td>
				<s:if test="leaveApprover==null || leaveApprover.hcmoLeaveApproverId==null">
	    		    <td>
	    		    	<s:url var="resetLeaveApprover" action="resetLeaveApprover"></s:url>
	    	            <sj:submit href="%{resetLeaveApprover}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_LeaveApproverFormId_div" indicator="indicatorLeaveApproverFormId_div"/>
					</td>
				</s:if>
				<s:else>
    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	        </s:else>
			</tr>
		</table>
		<s:hidden name="leaveApprover.hcmoLeaveApproverId" />
	</s:form>
</div>	