
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeReportToFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.REPORTTO_ADD == true">
			<sj:a href="setUpEmployeeReportTo.action" targets="submenu_EmployeeReportToFormId_div" indicator="indicatorSubMenuEmployeeReportToFormId_div" cssClass="link"><s:text name="MTIAddEmployeeReportTo" /></sj:a> |
		</s:if>
		<s:if test="#session.REPORTTO_VIEW == true">
			<sj:a href="getAllEmployeeReportTo.action" targets="submenu_EmployeeReportToFormId_div" indicator="indicatorSubMenuEmployeeReportToFormId_div" cssClass="link"><s:text name="MTIViewEmployeeReportTo"/></sj:a>|
			<sj:a href="empReportToSearcForm.action" targets="submenu_EmployeeReportToFormId_div" indicator="indicatorSubMenuEmployeeReportToFormId_div" cssClass="link"><s:text name="MTISearchEmployeeReportTo"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeReportToFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateEmployeeReportTo">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="emp==null || emp.empReportToId == null">
							<s:text name="label.title.employeeReportTo.add" />
						</s:if> <s:else>
							<s:text name="label.title.employeeReportTo.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<s:if test="emp==null || emp.empReportToId == null">
						<tr>
         		    		<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
          		   			<td class="employeeinputtd">
          		   				<sj:autocompleter  
								    name="emp.empIdObj.employeeId"
									list="#request.APPL_EMPLOYEE_LIST"
									listKey="employeeId"
									listValue="empFullName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
					    		/>
							</td>
							<td class="inputerrormessage"><s:fielderror fieldName="emp.empIdObj.employeeId" /></td>
						</tr>
           		  	</s:if>
           		  	<s:else>
           		  		<tr>
            		  		<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
							<td class="employeedisplaytd"><s:textfield name="emp.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
							<s:hidden name="emp.empIdObj.employeeId" />
						</tr>
           		  	</s:else>
	      			 <tr>   
						<td class="inputtext"><s:text name="label.form.fields.employeeReportTo.Supervisor" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="emp.supEmpNumber.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
				    		/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="emp.supEmpNumber.employeeId" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employeeReportTo.ReportingMode" /></td>
						<td class="employeeinputtd">
							<s:select 
								name="emp.empRepReportingMode"
	            				headerKey="-1"
	            				headerValue="-- Please Select --"
								list="#{'0':'Direct','1':'Indirect'}" 
								cssClass="employeeselect"
							/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="emp.empRepReportingMode" /></td>
					</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeReportToFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeReportToFormId_div" indicator="indicatorEmployeeReportToFormId_div"/>
				</td>
				<td>
					<s:if test="emp==null || emp.empReportToId==null">
    	        		<s:url var="resetEmployeeReportToForm" action="resetEmployeeReportToForm"></s:url>
	    	            <sj:submit href="%{resetEmployeeReportToForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeeReportToFormId_div" indicator="indicatorEmployeeReportToFormId_div"/>
	    	        </s:if>
	    	        <s:else>
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	        </s:else>
	    	    </td>
			</tr>
		</table>
		<s:hidden name="emp.empReportToId"/>
	</s:form>
</div>