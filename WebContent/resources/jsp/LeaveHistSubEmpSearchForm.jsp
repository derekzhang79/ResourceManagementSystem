<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<div id="submenu_LeaveHistory_AppSearchForm_div">
<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="getSubEmpLeaveHistorySearch">
		<table class="maintable">
	      <tr>
	        <td align="center"><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.lhist.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
				<tr>
				
	            <td class="inputtext"><s:text name="label.form.fields.lhist.empName"/></td>
	            	<td class="employeeinputtd">
	            	<sj:autocompleter  
							list="#request.empsList"
							listKey="employeeId"
							listValue="empFullName"
						    name="lhist.empIdObj.employeeId"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
					    />
	            	</td>
	            	<td class="inputerrormessage"></td>
	        </tr>
	        <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			  	<td></td>
			</tr>
			<!--Button image added by, R.Deepika-->
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.lhist.startDate" /></td>
			<td class="employeeinputtd"><sj:datepicker name="lhist.leaveStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			</td>
		</tr>
		<!-- Extra text removed by, R.Deepika -->
		
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.lhist.enddate" /></td>
			<td class="employeeinputtd"><sj:datepicker name="lhist.leaveEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		 	</td>
		</tr>
		<tr>
	      <td></td>
	      <td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
	    </tr>
		<tr>
	           <td class="inputtext"><s:text name="label.form.lhist.leaveType.name"/></td>
	           <td class="employeeinputtd">
	           <sj:autocompleter  
					list="#request.APPL_LEAVE_LIST"
					listKey="leaveTypeId"
					listValue="leaveTypeName"
				    name="lhist.leaveTypeIdObj.leaveTypeId"
				    selectBox="true"
				    selectBoxIcon="true"
				    cssClass="employeeselect"
			    />
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
							name="lhist.leaveStatus"
						    dataType="json"
							indicator="leaveStatusIndicator"      
						    cssClass="employeeselect" 
						/>
						<img id="leaveStatusIndicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	   </td>
	    </tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<table align="center">
			<tr>
			   	<td class="nowrap">
				   	<img id="indicatorSubEmpLeaveHistorySearchImgForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				    <sj:submit cssClass="submitbutton117" indicator="indicatorSubEmpLeaveHistorySearchImgForm" targets="submenu_LeaveHistory_AppSearchForm_div"></sj:submit>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	        </tr>  		
		</table>
	<br/>
	</s:form>
</div>