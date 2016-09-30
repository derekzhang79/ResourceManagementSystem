<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssTimeSheetApprover_Form_div">
	<div class="submenu_bg"
      <s:set name="UniqueTSApprEmployeeId" value="timeSheetApprover.hcmoEmployeeId.employeeId"></s:set>
	  <s:url var="remoteTimeSheetApprForm" value="/setUpEmpInsertOrUpdateTimeSheetApprover.action">
		<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#UniqueTSApprEmployeeId"></s:param>
	  </s:url>
	  <s:set name="UniqueTSApprEmployeeId" value="timeSheetApprover.hcmoEmployeeId.employeeId"></s:set>
	  <s:url var="remoteTimeSheetApprView" value="/getEmployeeAllTimeSheetApprover.action">
		<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#UniqueTSApprEmployeeId"></s:param>
	  </s:url>
>	  <s:if test="#session.TIMESHEETAPPROVER_ADD==true">
			<sj:a href="%{remoteTimeSheetApprForm}" indicator="indicatorTSApprList" targets="submenu_EssTimeSheetApprover_Form_div" cssClass="link"><s:text name="label.header.timesheet.add"/></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW==true">
			<sj:a href="%{remoteTimeSheetApprView}" indicator="indicatorTSApprList" targets="submenu_EssTimeSheetApprover_Form_div" cssClass="link"><s:text name="label.header.timesheet.view"/></sj:a>
		</s:if>
	</div>		
<br />		 
  
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form action="insertOrUpdateTimeSheetApproverAjax">
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
				<img id="indicatorTSApprForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssTimeSheetApprover_Form_div" indicator="indicatorTSApprForm"/>
   		    </td>
   		    <td><s:if test="timeSheetApprover==null || timeSheetApprover.hcmoApproverId == null">
		    	        	<s:url var="resetTimesheetApproverFormAjax" action="setUpEmpInsertOrUpdateTimeSheetApprover">
		    	        		<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="timeSheetApprover.hcmoEmployeeId.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetTimesheetApproverFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssTimeSheetApprover_Form_div" indicator="indicatorTSApprForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
		</tr>
	</table>
	<s:hidden name="timeSheetApprover.hcmoEmployeeId.employeeId"/>
	<s:hidden name="timeSheetApprover.hcmoApproverId" />
</s:form>
</div>