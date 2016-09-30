
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LeaveQuotaFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEQUOTA_ADD == true">
			<sj:a href="setUpEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaFormId_div" indicator="indicatorSubMenuLeaveQuotaFormId_div" cssClass="link"><s:text name="MTIAddLeaveQuota" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEQUOTA_VIEW == true">
			<sj:a href="getAllEmployeeLeaveQuota.action" targets="submenu_LeaveQuotaFormId_div" indicator="indicatorSubMenuLeaveQuotaFormId_div" cssClass="link"><s:text name="MTIViewLeaveQuota"/></sj:a> |
			<sj:a href="leaveQuotaSearchForm.action" targets="submenu_LeaveQuotaFormId_div" indicator="indicatorSubMenuLeaveQuotaFormId_div" cssClass="link"><s:text name="MTISearchLeaveQuota"/></sj:a>
		</s:if>
	</div>
	<br/><img id="indicatorSubMenuLeaveQuotaFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="#session.LEAVETYPE_ADD == true">
		<s:div cssClass="helpinformationmessage">
	    	<s:text name="label.header.employeeLeaveQuota.msg.leaveType"/>
		</s:div>
	</s:if>
	<s:else>
		<s:div cssClass="helpinformationmessage">	    	
		    <s:text name="label.header.employeeLeaveQuota.msg.newInfo"/>
		</s:div>	    	
	</s:else>
	
	<s:form action="insertOrUpdateEmployeeLeaveQuota">
		<table class="maintable">
	    <tr>
	        <td align="center" ><table class="formouter">
	        <tr>
	          <td><table class="employeeformiinertable">
	          <tr>
	             <td class="formtitle">
	            	 <s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
					 	<s:text name="label.title.empLeaveQuota.add" />
	          		 </s:if>
	 			     <s:else>
					 	<s:text name="label.title.empLeaveQuota.edit" />
					 </s:else>
	             </td>
	          </tr>
	  		 <tr>
	              <td class="forminner"><table class="tablealign">
	            
	        <s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
				<tr>
			  		<td class="inputtext"><s:text name="label.form.fields.common.empName" /></td>
				  	<td class="employeeinputtd">
				  		<sj:autocompleter  
						    name="empLeaveQuota.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="empLeaveQuota.empIdObj.employeeId" /></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="empLeaveQuota.empIdObj.employeeId" />
				</tr>
			</s:else>
			<s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.leaveType.name" /></td>
					<td class="employeeinputtd">
						<sj:autocompleter  
						    name="empLeaveQuota.leaveTypeIdObj.leaveTypeId"
							list="#request.APPL_LEAVE_LIST"
							listKey="leaveTypeId"
							listValue="leaveTypeName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="empLeaveQuota.leaveTypeIdObj.leaveTypeId" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td class="inputtext"><s:text name="label.header.leaveType.name"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.leaveTypeIdObj.leaveTypeName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="empLeaveQuota.leaveTypeIdObj.leaveTypeId" />
				</tr>
			</s:else>
			<tr>
				<s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
					<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.year" /></td>
					<td class="employeeinputtd"><s:textfield name="empLeaveQuota.year" cssClass="employeeinput"/></td>
					<td class="inputerrormessage"><s:fielderror fieldName="empLeaveQuota.year" /></td>
	            </s:if>
	            <s:else>
	           		<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.year"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.year" readonly="true" cssClass="employeeinput"/></td>
	            </s:else>
			</tr>
			<tr>
				<s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
					<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.preLeaveCarryForward" /></td>
					<td class="employeeinputtd">
						<s:textfield name="empLeaveQuota.prvYearCarryingForward" cssClass="employeeinput" readonly="true" id="empLeave.prvYearCarryingForward" />
					</td>
				</s:if>
				<s:else>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.preLeaveCarryForward"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.previousLeaveCarryDays" readonly="true" cssClass="employeeinput"/></td>
				</s:else>
			</tr>
			<tr>
				<s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
					<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.nextYearCarryForward" /></td>
					<td class="employeeinputtd">
						<s:textfield name="empLeaveQuota.leaveCarryingForward" cssClass="employeeinput" readonly="true" />
					</td>
				</s:if>
				<s:else>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.carryForward"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.empLeavePending" readonly="true" cssClass="employeeinput"/></td>
				</s:else>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.noOfDays" /></td>
				<td class="employeeinputtd">
					<s:textfield name="empLeaveQuota.noOfDays" cssClass="employeeinput"/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="empLeaveQuota.noOfDays" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.leaveHours" /></td>
				<td class="employeeinputtd">
					<s:select name="empLeaveQuota.hours"
		         				headerKey=""
								list="#{'0':'0','1':'1','2':'2','3':'3','4':'4',
										'5':'5','6':'6','7':'7'}"
								 cssClass="employeeselect"/>
				</td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.leaveMinutes" /></td>
				<td class="employeeinputtd">
					<s:select name="empLeaveQuota.minutes	"
		         				headerKey=""
								list="#{'0':'0','15':'15','30':'30','45':'45'}"
								 cssClass="employeeselect"/>
				</td>
			</tr>
			<tr>
				<s:if test="empLeaveQuota==null || empLeaveQuota.empLeaveQuotaId==null">
					<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.leaveTaken" /></td>
					<td class="employeeinputtd">
						<s:textfield name="empLeaveQuota.leaveTaken" cssClass="employeeinput" readonly="true"/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="leave.orgList.orgId" /></td>
				</s:if>
				<s:else>
					<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.leaveTaken"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="empLeaveQuota.empLeaveRequest" readonly="true" cssClass="employeeinput"/></td>
				</s:else>
			</tr>
			
			
		<br /></table></td></tr></table></td></tr></table></td></tr></table>
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeLeaveQuotaFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveQuotaFormId_div" indicator="indicatorEmployeeLeaveQuotaFormId_div"/>
				</td>
				 <td>
    		    	<s:url var="resetLeaveQuota" action="resetLeaveQuota"></s:url>
    	            <sj:submit href="%{resetLeaveQuota}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_LeaveQuotaFormId_div" indicator="indicatorEmployeeLeaveQuotaFormId_div"/>
				</td>
			</tr>
		</table>
		<s:hidden name="empLeaveQuota.empLeaveQuotaId" />
	 </s:form>
</div>