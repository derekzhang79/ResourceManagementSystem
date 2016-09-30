<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssLeaveApprover_Form_div">
 <div class="submenu_bg">
	<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteLeaveApprForm" value="/setUpEmpInsertOrUpdateLeaveApprover.action">
		<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteLeaveApprView" value="/getEmployeeAllLeaveApprover.action">
		<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
	</s:url>
	
		<s:if test="#session.LEAVEAPPROVER_ADD==true">
			<sj:a href="%{remoteLeaveApprForm}" indicator="indicatorLeaveApprList" targets="submenu_EssLeaveApprover_Form_div" cssClass="link"><s:text name="label.header.leaveapprover.add"/></sj:a> |
		</s:if>
			<s:if test="#session.LEAVEAPPROVER_VIEW==true">
			<sj:a href="%{remoteLeaveApprView}" indicator="indicatorLeaveApprList" targets="submenu_EssLeaveApprover_Form_div" cssClass="link"><s:text name="label.header.leaveapprover.view"/></sj:a>
		</s:if>
	</div>		

<br />
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form action="insertOrUpdateLeaveApproverAjax">
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
				<img id="indicatorLeaveApprForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssLeaveApprover_Form_div" indicator="indicatorLeaveApprForm"/>
			</td>
			<td><s:if test="leaveApprover==null || leaveApprover.hcmoLeaveApproverId == null">
		    	        	<s:url var="resetLeaveApproverFormAjax" action="setUpEmpInsertOrUpdateLeaveApprover">
		    	        		<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetLeaveApproverFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssLeaveApprover_Form_div" indicator="indicatorLeaveApprForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
		</tr>
	</table>
	<s:hidden name="leaveApprover.hcmoEmployeeId.employeeId"/> 
	<s:hidden name="leaveApprover.hcmoLeaveApproverId" />
</s:form>
</div>