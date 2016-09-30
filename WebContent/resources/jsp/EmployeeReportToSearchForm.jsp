
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeReportToSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.REPORTTO_ADD == true">
			<sj:a href="setUpEmployeeReportTo.action" targets="submenu_EmployeeReportToSearchId_div" indicator="indicatorSubMenuEmployeeReportToSearchId_div" cssClass="link"><s:text name="MTIAddEmployeeReportTo" /></sj:a> |
		</s:if>
		<s:if test="#session.REPORTTO_VIEW == true">
			<sj:a href="getAllEmployeeReportTo.action" targets="submenu_EmployeeReportToSearchId_div" indicator="indicatorSubMenuEmployeeReportToSearchId_div" cssClass="link"><s:text name="MTIViewEmployeeReportTo"/></sj:a> |
			<sj:a href="empReportToSearcForm.action" targets="submenu_EmployeeReportToSearchId_div" indicator="indicatorSubMenuEmployeeReportToSearchId_div" cssClass="link"><s:text name="MTISearchEmployeeReportTo"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuEmployeeReportToSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="empReportToSearcResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	             <tr>
	                <td class="formtitle">
						 <s:text name="label.title.employeeReportTo.search"/>
	                </td>
	       		</tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
				
				<tr>
         		   <td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
       			 </tr>
       			 	
      			 <tr>   
					<td class="inputtext">
						<s:text	name="label.header.employeeReportTo.Supervisor" />
					</td>
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
				</tr>
				
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.employeeReportTo.ReportingMode" /></td>
					<td class="employeeinputtd">
						<s:select 
							name="emp.empRepReportingMode"
            				headerKey=""
							list="#{'2':'-- Please Select --','0':'Direct','1':'Indirect'}" 
							cssClass="employeeselect"
						/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="emp.empRepReportingMode" /></td>
				</tr>
			</table></td></tr></table></td></tr></table></td></tr></table><br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmployeeReportToSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeReportToSearchId_div" indicator="indicatorEmployeeReportToSearchForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>