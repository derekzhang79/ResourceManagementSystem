<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LeaveRequest_Both_div">
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<s:div cssClass="helpinformationmessage">
   	<s:text name="label.header.leaveReqs.msg.info"/>
</s:div>
<jsp:include page="common/messages.jsp" flush="true"/>	
<s:form action="insertOrUpdateLeaveReqsApproval">
	<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
					 <s:if test="lrapp==null || lrapp.hcmoLeaveReqsApprovalId==null">
					 	<s:text name="label.leaveReqsApproval.add" />
	          		 </s:if>
	 			     <s:else>
					 	<s:text name="label.leaveReqsApproval.edit" />
					 </s:else>
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
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
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.dateApplied" /></td>
				<td class="employeeinputtd"><sj:datepicker name="lrapp.dateApplied" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" minDate="-2"/> 
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.dateApplied" />
	            </td>
			</tr>
			<tr>
		      <td></td>
		      <td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
		    </tr>
			<tr>
			<s:if test="lrapp==null || lrapp.hcmoLeaveReqsApprovalId==null"> 	
				<td class="inputtext"><s:text name="label.form.fields.common.noOfDays" /></td>
				<td class="employeeinputtd"><s:textfield name="lrapp.noOfDays"  cssClass="employeeinput" value="0"/></td>
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.noOfDays" />
	            </td>
	         </s:if>
	         <s:else>
	         	<td class="inputtext"><s:text name="label.form.fields.common.noOfDays" /></td>
				<td class="employeeinputtd"><s:textfield name="lrapp.noOfDays"  cssClass="employeeinput"/></td>
				<td class="inputerrormessage">
	                <s:fielderror fieldName="lrapp.noOfDays" />
	            </td>
	         </s:else>
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
				<td class="employeeinputtd"><s:textarea name="lrapp.comments" cssClass="employeetextarea" rows="4" cols="26" /></td>
			</tr>

			<s:hidden name="lrapp.hcmoLeaveReqsApprovalId" />
		
	</table>
	<br />
	<table align="center">
		<tr>
			<td>
				<img id="indicatorLeaveReqsApprovalFormImg" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<sj:submit targets="submenu_LeaveRequest_Both_div" indicator="indicatorLeaveReqsApprovalFormImg" cssClass="submitbutton117" clearForm="false"></sj:submit>
   			</td>
   		    <td>
   		    	<s:url var="resetLeaveRequest" action="resetLeaveRequest"></s:url>
   	            <sj:submit href="%{resetLeaveRequest}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_LeaveRequest_Both_div" indicator="indicatorLeaveReqsApprovalFormImg"/>
			</td>
		</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
</s:form>
</div>