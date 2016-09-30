<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LeaveAssigned_App_div">
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<s:div cssClass="helpinformationmessage">
   	<s:text name="label.header.leaveReqs.msg.info"/>
</s:div>

<jsp:include page="common/messages.jsp" flush="true"/>	
<s:form action="insertOrUpdateLeaveReqsAssigned">
	<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
						<s:text name="label.leaveAssigned.add" />
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
                  
                  <tr>
		            	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		            	<td class="employeeinputtd">
		            	<sj:autocompleter  
							list="#request.APPL_LEAVESUBEMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
					  		name="lrapp.empIdObj.employeeId"
					   	 	selectBox="true"
					    	selectBoxIcon="true"
					    	cssClass="employeeselect"
				   		 />
					</td>
					<td class="inputerrormessage">
		                <s:fielderror fieldName="lrapp.empIdObj.employeeId" />
		            </td>
		        </tr>
				<tr>
	            	<td class="inputtext"><s:text name="label.form.fields.leaveType.name"/></td>
	            	<td class="employeeinputtd">
	            	<sj:autocompleter  
						list="#request.APPL_LEAVE_LIST"
						listKey="leaveTypeId"
						listValue="leaveTypeName"
					    name="lrapp.leaveTypeIdObj.leaveTypeId"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
				    />
				</td>
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.leaveTypeIdObj.leaveTypeId" />
	            </td>
	        </tr>
	        <tr>
				<td class="inputerrormessage"></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.dateApplied" /></td>
				<td class="employeeinputtd"><sj:datepicker name="lrapp.dateApplied" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/> 
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.dateApplied" />
	            </td>
			</tr>
			<tr>
		      <td></td>
		      <td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
		    </tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.noOfDays" /></td>
				<td class="employeeinputtd"><s:textfield name="lrapp.noOfDays"  cssClass="employeeinput" value="0"/></td>
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.noOfDays" />
	            </td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.leaveHours" /></td>
				<td class="employeeinputtd">
					<s:select name="lrapp.hours"
			         				headerKey=""
									list="#{'0':'0','1':'1','2':'2','3':'3','4':'4',
											'5':'5','6':'6','7':'7'}"
									 cssClass="employeeselect"/>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.employeeLeaveQuota.leaveMinutes" /></td>
				<td class="employeeinputtd">
					<s:select name="lrapp.mins	"
			         				headerKey=""
									list="#{'0':'0','15':'15','30':'30','45':'45'}"
									 cssClass="employeeselect"/>
			</tr>
			
			
			<tr>
				<td class="inputtext"><s:text name="label.header.common.comments" /></td>
				<!--text area size has been changed:venkat -->
				<td class="employeeinputtd"><s:textarea name="lrapp.comments" cssClass="employeetextarea" rows="4" cols="26" /></td>
			</tr>
			<s:hidden name="lrapp.hcmoLeaveReqsApprovalId" />
		
	</table></td></tr></table></td></tr></table></td></tr></table>
	<br />
	<table align="center">
		<tr>
			<td>
				<img id="indicatorLeaveReqsAssignedFormImg" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<sj:submit key="button.label.assign" targets="submenu_LeaveAssigned_App_div" indicator="indicatorLeaveReqsAssignedFormImg" cssClass="submitbutton117"></sj:submit>
   			</td>
   		    <td>
   		    	<s:url var="resetLeaveAssign" action="resetLeaveAssign"></s:url>
   	            <sj:submit href="%{resetLeaveAssign}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_LeaveAssigned_App_div" indicator="indicatorLeaveReqsAssignedFormImg"/>
			</td>
		</tr>
	</table>
</s:form>
</div>