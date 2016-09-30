<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		 <div id="EmployeeFormId_Div">
		 
		<div id="submenu_EmployeesSearchId_div">
		<div class="submenu_bg">
			<s:if test="#session.EMPLOYEE_ADD == true">
				<sj:a href="setUpEmployees.action" targets="submenu_EmployeesSearchId_div" indicator="indicatorSubMenuEmployeesSearchId_div" cssClass="link"><s:text name="MTIAddEmployee" /></sj:a> |
			</s:if>
			<s:if test="#session.EMPLOYEE_VIEW == true">
				<sj:a href="getAllEmp.action" targets="submenu_EmployeesSearchId_div" indicator="indicatorSubMenuEmployeesSearchId_div" cssClass="link"><s:text name="MTIViewEmployee"/></sj:a> |
				<sj:a href="employeeSearchForm.action" targets="submenu_EmployeesSearchId_div" indicator="indicatorSubMenuEmployeesSearchId_div" cssClass="link"><s:text name="MTISearchEmployee"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuEmployeesSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		 		 	
		  <s:form action="employeeSearchResult">
		     	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	      <tr>
	         <td><table class="employeeformiinertable">
	      <tr>
	          <td class="formtitle">
				 <s:text name="label.title.employee.search"/>
	          </td>
	       </tr>
	       <tr>
                <td class="forminner"><table class="tablealign">
	     		<tr class="inputtext">
			     	<td class="formtitle1" colspan="3">
		    	 		<s:text name="label.common.message.personalInformation"/>
		    	 	</td>
	     		</tr>
		         	<tr>
		         		<td class="inputtext"><s:text name="label.header.employee.firstName"/></td>
		         		<td class="employeeinputtd"><s:textfield name="employee.empFirstName" cssClass="employeeinput"/></td>
		        	</tr>
		         	<tr>
		         		<td class="inputtext"><s:text name="label.header.employee.lastName"/></td>
		         		<td class="employeeinputtd"><s:textfield name="employee.empLastName" cssClass="employeeinput"/></td>
		        	</tr>
		        	<!--Button image added by, R.Deepika-->
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.birthDate"/></td>
		         		<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empBirthDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	       
		    	   	       <!--  Extra Date Format Text Removed by, R.Deepika-->
		         		</td>
		         		<td class="employeeinputtd">
							<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
							<sj:select
								href="%{getSearchProcessJSONList}"
								list="searchProcessList"
								name="employee.empBirthDateValue"
							    dataType="json"
								indicator="employeeSearchFormBirthDate"      
							    cssClass="employeeselect" 
							/>
							<img id="employeeSearchFormBirthDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    		</td>
		        	</tr>
		        	<tr>
						<td></td>
						<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empBirthDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	        <br/>
			       	        <s:text name="label.date.format"/></td>
			       	        <td><s:text name="label.common.search.dateInformation"/>
						</td>
					</tr>
		        	<tr class="inputtext">
			     		<td class="formtitle1" colspan="3">
		    	 			<s:text name="label.common.message.generalInformation"/>
		    	 		</td>
		     		</tr>
		         	<tr>
			       		<td class="inputtext"><s:text name="label.header.employee.gender"/></td>
			         	<td class="employeeinputtd">
			         		<s:radio name="employee.empGender" label="Gender" list="#{'M':'Male','F':'Female'}" cssClass="employeeradiobut" />
			         	</td>
			        </tr>
		         	<tr>
						<td class="inputtext"><s:text name="label.header.employee.maritalStatus"/></td>
		         		<td class="employeeinputtd">
							<s:url var="getMaritalStatusJSONList" action="getMaritalStatusJSONList"/>
							<sj:select
								headerKey=""
								headerValue="-- Please Select --"
								emptyOption="true"
								href="%{getMaritalStatusJSONList}"
								list="maritalStatusList"
								name="employee.empMaritalStatus"
							    dataType="json"
								indicator="employeeMaritalStatus"      
							    cssClass="employeeselect" 
							/>
							<img id="employeeSearchFormMaritalStatus" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		         		</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.fileUpload"/></td>
						<td class="employeeinputtd"><s:select name="employee.empSpace"
	            				headerKey=""
	            				headerValue="-- Please Select --"
								list="#{'1048576':'1 MB','2097152':'2 MB','3145728':'3 MB','4194304':'4 MB',
										'5242880':'5 MB','6291456':'6 MB','7340032':'7 MB','8388608':'8 MB',
										'9437184':'9 MB','10485760':'10 MB'	}" 
								cssClass="employeeselect"/>
						</td>
					</tr>
			     	<tr>
			            <td class="inputtext"><s:text name="label.header.ethnicRace.name"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="employee.ethnicRaceIdObj.ethnicRaceId"
								list="#request.APPL_ETHNICRACE_LIST"
								listKey="ethnicRaceId"
								listValue="ethnicRaceDesc"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.country.name" /></td>
							<td class="employeeinputtd">
								<sj:autocompleter  
								    name="employee.country.hcmocountryId"
									list="#request.APPL_COUNTRY_LIST"
									listKey="hcmocountryId"
									listValue="countryName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
						    	/>
							</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.department.name" /></td>
							<td class="employeeinputtd">
								<sj:autocompleter  
								    name="employee.departmentIdObj.hcmoDepartmentId"
									list="#request.APPL_DEPARTMENT_LIST"
									listKey="hcmoDepartmentId"
									listValue="deptName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
						    	/>
							</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.team.name" /></td>
							<td class="employeeinputtd">
								<sj:autocompleter  
								    name="employee.teamIdObj.hcmoTeamId"
									list="#request.APPL_TEAM_LIST"
									listKey="hcmoTeamId"
									listValue="teamName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
						    	/>
							</td>
					</tr>
					<tr>
			            <td class="inputtext"><s:text name="label.header.employee.empStatus"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="employee.empStatusIdObj.empStatusId"
								list="#request.APPL_EMPSTATUS_LIST"
								listKey="empStatusId"
								listValue="statusName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
					</tr><!-- autocomplete text added R.Deepika -->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>    
		         	<tr>
						<td class="inputtext"><s:text name="label.header.employee.driLicenseNo"/></td>
			         	<td class="employeeinputtd"><s:textfield name="employee.empDriLicenNum" cssClass="employeeinput"/></td>
		        	</tr>
		         	<tr>
			         	<td class="inputtext"><s:text name="label.header.employee.driLicenseExpDate"/></td>
			         	<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empDriLicenExpDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	        
		       	          <!--  Extra Date Format Text Removed by, R.Deepika-->
			         	</td>
			         	<td class="employeeinputtd">
							<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
							<sj:select
								href="%{getSearchProcessJSONList}"
								list="searchProcessList"
								name="employee.empDriLicenExpDateValue"
							    dataType="json"
								indicator="employeeSearchFormLicExpDate"      
							    cssClass="employeeselect" 
							/>
							<img id="employeeSearchFormLicExpDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			       	    </td>
			        </tr>
			        <tr>
						<td></td>
						<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empDriLicenExpDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	        <br/>
			       	        <s:text name="label.date.format"/></td>
			       	        <td><s:text name="label.common.search.dateInformation"/>
						</td>
					</tr>
		        	<tr class="inputtext">
			     		<td class="formtitle1" colspan="3">
		    	 			<s:text name="label.common.message.contactInformation"/>
		    	 		</td>
		     		</tr>
					<tr>           
			            <td class="inputtext"><s:text name="label.header.nationality.name"/></td>
			             <td class="employeeinputtd">
			             	<sj:autocompleter  
							    name="employee.nationalityIdObj.nationalityId"
								list="#request.APPL_NATIONALITY_LIST"
								listKey="nationalityId"
								listValue="nationalityName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>    
			         </tr><!-- autocomplete text by, R.Deepika -->
			         <tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
			        <tr> 
			         	<td class="inputtext"><s:text name="label.header.common.zipCode"/></td>
			        	<td class="employeeinputtd"><s:textfield id="employee.empZipCode" name="employee.empZipCode" cssClass="employeeinput"/></td>
		        	</tr>
			        <tr>
						<td class="inputtext"><s:text name="label.header.common.city"/></td>
			         	<td class="employeeinputtd"><s:textfield id="employee.empCityName" name="employee.empCityName" onfocus="javascript:getRegionDetailsEmployee()" cssClass="employeeinput"/></td>
		        	</tr>
		         	<tr>
			         	<td class="inputtext"><s:text name="label.header.region.name"/></td>
			            <td class="employeeinputtd"><s:textfield id="employee.empCounName" name="employee.empCounName" cssClass="employeeinput"/></td>
			        </tr> 	
			        <tr> 	
						<td class="inputtext"><s:text name="label.header.employee.street1"/></td>
			         	<td class="employeeinputtd"><s:textfield name="employee.empStreet1" cssClass="employeeinput"/></td>
		        	</tr>
		         	<tr>
			         	<td class="inputtext"><s:text name="label.header.employee.homeTelephone"/></td>
			        	<td class="employeeinputtd"><s:textfield name="employee.empHmTelephone" cssClass="employeeinput"/></td>
					</tr>
					<!-- phone number added by, R.Deepika -->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
					<tr>
			         	<td class="inputtext"><s:text name="label.header.employee.mobile"/></td>
			        	<td class="employeeinputtd"><s:textfield name="employee.empMobile" cssClass="employeeinput"/></td>
					</tr>
					<!-- Phone number added by, R.Deepika -->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
		        	<tr class="inputtext">
			     		<td class="formtitle1" colspan="3">
		    	 			<s:text name="label.common.message.workInformation"/>
		    	 		</td>
		     		</tr>
					<tr>            
			            <td class="inputtext"><s:text name="label.header.role.name"/></td>
			            <td class="employeeinputtd"> 
			            	<sj:autocompleter  
							    name="employee.roleObj.hcmoRoleId"
								list="#request.APPL_ROLE_LIST"
								listKey="hcmoRoleId"
								listValue="roleName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
			         </tr>
			  		<tr>        
			            <td class="inputtext"><s:text name="label.header.jobTitle.name"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="employee.jobTitleIdObj.jobTitleId"
								list="#request.APPL_JOBTITLE_LIST"
								listKey="jobTitleId"
								listValue="jobTitleName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
		         	</tr>
		         	<!-- text added by, R.Deepika -->
		         	<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>        
						<td class="inputtext"><s:text name="label.header.employee.workEmail"/></td>
			        	<td class="employeeinputtd"><s:textfield name="employee.empWorkEmail" cssClass="employeeinput"/></td>
		        	</tr>
		         	<tr>
						<td class="inputtext"><s:text name="label.header.employee.joinedDate"/></td>
			        	<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empJoineddate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	        
			       	         <!--  Extra Date Format Text Removed by, R.Deepika-->
			        	</td>
			        	<td class="employeeinputtd">
							<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
							<sj:select
								href="%{getSearchProcessJSONList}"
								list="searchProcessList"
								name="employee.empJoinedDateValue"
							    dataType="json"
								indicator="employeeSearchFormEmpJoinDate"      
							    cssClass="employeeselect" 
							/>
							<img id="employeeSearchFormEmpJoinDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			       	    </td>
			        </tr>
			        <tr>
						<td></td>
						<td class="employeeinputtd">
			       	        <sj:datepicker name="employee.empJoinedDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
			       	        <br/>
			       	        <s:text name="label.date.format"/></td>
			       	        <td><s:text name="label.common.search.dateInformation"/>
						</td>
					</tr>
		    </table></td></tr></table></td></tr></table></td></tr></table>
		    		 <br/>
		    <table align="center"> 
	    	     <tr>
	    	     <td>
	    	     	<img id="indicatorEmployeeForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeesSearchId_div" indicator="indicatorEmployeeForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
		    </table>
	   	</s:form>
</div></div>  