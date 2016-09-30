
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeLocationHistoryFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.LOCATIONHISTORY_ADD == true">
			<sj:a href="setUpEmpLocationHistory.action" targets="submenu_EmployeeLocationHistoryFormId_div" indicator="indicatorSubMenuEmployeeLocationHistoryFormId_div" cssClass="link"><s:text name="MTIAddEmployeeLocationHistory" /></sj:a> |
		</s:if>
		<s:if test="#session.LOCATIONHISTORY_VIEW == true">
			<sj:a href="getAllEmpLocationHistory.action" targets="submenu_EmployeeLocationHistoryFormId_div" indicator="indicatorSubMenuEmployeeLocationHistoryFormId_div" cssClass="link"><s:text name="MTIViewEmployeeLocationHistory"/></sj:a> |
			<sj:a href="empLocHistSearchForm.action" targets="submenu_EmployeeLocationHistoryFormId_div" indicator="indicatorSubMenuEmployeeLocationHistoryFormId_div" cssClass="link"><s:text name="MTISearchEmployeeLocationHistory"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeLocationHistoryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateEmpLocationHistory">
			<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="elhist==null || elhist.hcmoEmpLocHistoryId == null">
							<s:text name="label.title.empLocationHistory.add" />
						</s:if><s:else>
							<s:text name="label.title.empLocationHistory.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<s:if test="elhist==null || elhist.hcmoEmpLocHistoryId == null">	   
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.common.empName" /></td>
					 <td class="employeeinputtd">
					 	<sj:autocompleter  
						    name="elhist.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="elhist.empIdObj.employeeId"/></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="elhist.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="elhist.empIdObj.employeeId" />
				</tr>
			</s:else>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.location.name"/></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="elhist.locationIdObj.hcmolocationId"
						list="#request.APPL_LOCATION_LIST"
						listKey="hcmolocationId"
						listValue="locationName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="elhist.locationIdObj.hcmolocationId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.name" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="elhist.clientIdObj.hcmoclientId"
						list="#request.APPL_CLIENT_LIST"
						listKey="hcmoclientId"
						listValue="companyName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="elhist.clientIdObj.hcmoclientId"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.startDate" /></td>
				<td class="employeeinputtd"><sj:datepicker name="elhist.startDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="elhist.startDate"/></td>
			</tr>
			 <!-- Extra Format Text Removed by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.enddate" /></td>
				<td class="employeeinputtd"><sj:datepicker name="elhist.endDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="elhist.endDate"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
			</tr>
			<s:hidden name="elhist.hcmoEmpLocHistoryId" />
		 </table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLocationHistoryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeLocationHistoryFormId_div" indicator="indicatorLocationHistoryFormId_div"/>
				</td>
				<td>
					<s:if test="elhist==null || elhist.hcmoEmpLocHistoryId==null">
	    	        	<s:url var="resetLocationHistoryForm" action="resetLocationHistoryForm"></s:url>
	    	            <sj:submit href="%{resetLocationHistoryForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeeLocationHistoryFormId_div" indicator="indicatorLocationHistoryFormId_div"/>
	    	        </s:if>
	    	        <s:else>
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	        </s:else>
	    	    </td>
			</tr>
		</table>
	</s:form>
</div>