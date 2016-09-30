<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<div id="EmployeeEditFormDiv">	
	  	<div id="submenu_Employees_div">
			<jsp:include page="common/messages.jsp" flush="true"/>	 	
		  	<s:form id="employeeEditFormId" method="post" enctype="multipart/form-data">
		   		<table class="maintable">
	      			<tr>
	        			<td align="center" ><table class="formouter">
	      			<tr>
	         			<td><table class="employeeformiinertable">
	      			<tr>
	          			<td class="formtitle">
	 						<s:text name="label.title.employee.edit"/>
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
				         		<td class="inputtext"><s:text name="label.form.fields.employee.firstName"/></td>
				         		<td class="employeeinputtd"><s:textfield name="employee.empFirstName" cssClass="employeeinput"/></td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empFirstName" /></td>
				        	</tr>
				         	<tr>
				         	 	<td class="inputtext"><s:text name="label.form.fields.employee.midName"/></td>
				         	 	<td class="employeeinputtd"><s:textfield name="employee.empMidName" cssClass="employeeinput"/></td>
				         	 	<td class="inputerrormessage"><s:fielderror fieldName="employee.empMidName" /></td>
				        	</tr>
				         	<tr>
				         		<td class="inputtext"><s:text name="label.form.fields.employee.lastName"/></td>
				         		<td class="employeeinputtd"><s:textfield name="employee.empLastName" cssClass="employeeinput"/></td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empLastName" /></td>
				        	</tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.nickName"/></td>
								<td class="employeeinputtd"><s:textfield name="employee.empNickName" cssClass="employeeinput"/></td>
								<td class="inputerrormessage"><s:fielderror fieldName="employee.empNickName" /></td>
							</tr>
							<!--Button image added by, R.Deepika-->
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.birthDate"/></td>
				         		<td class="employeeinputtd"><sj:datepicker name="employee.empBirthDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empBirthDate" /></td>
				        	</tr>
				        	<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
							</tr>
				        	<tr class="inputtext">
					     		<td class="formtitle1" colspan="3">
				    	 			<s:text name="label.common.message.generalInformation"/>
				    	 		</td>
				     		</tr>
				         	<tr>
					       		<td class="inputtext"><s:text name="label.form.fields.employee.gender"/></td>
					         	<td class="employeeinputtd"><s:radio name="employee.empGender" label="Gender" list="#{'M':'Male','F':'Female'}" cssClass="employeeradiobut" /></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empGender" /></td>
					        </tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.maritalStatus"/></td>
				         		<td class="employeeinputtd">
			         				<s:url var="getMaritalStatusJSONList" action="getMaritalStatusJSONList"/>
									<sj:select
										headerKey=" "
										headerValue="-- Please Select --"
										href="%{getMaritalStatusJSONList}"
										list="maritalStatusList"
										name="employee.empMaritalStatus"
									    dataType="json"
										indicator="employeeMaritalStatusEditTabId_div"      
									    cssClass="employeeselect" 
									/>
									<img id="employeeMaritalStatusEditTabId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				         		</td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empMaritalStatus" /></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.empSpace.empFolderSize"/></td>
				         		<td class="employeeinputtd">
				         			<s:select 
										name="employee.empSpace"
			            				headerKey=""
			            				headerValue="-- Please Select --"
										list="#{'1048576':'1 MB','2097152':'2 MB','3145728':'3 MB','4194304':'4 MB',
												'5242880':'5 MB','6291456':'6 MB','7340032':'7 MB','8388608':'8 MB',
												'9437184':'9 MB','10485760':'10 MB'	}" 
										cssClass="employeeselect"
									/>
								</td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empSpace" /></td>
							</tr>
							<tr>
					            <td class="inputtext"><s:text name="label.form.fields.ethnicRace.name"/></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.ethnicRaceIdObj.ethnicRaceId" /></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.country.name" /></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.country.hcmocountryId"/></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.department.name" /></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.departmentIdObj.hcmoDepartmentId"/></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.team.name" /></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.teamIdObj.hcmoTeamId"/></td>
							</tr>
							<tr>
						         <td></td>
						         <td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					        </tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.empType.name"/></td>
								<td class="employeeinputtd">
									<s:select 
											name="employee.empType"
				            				headerKey=""
				            				headerValue="-- Please Select --"
											list="#{'Direct':'Direct','Permanent':'Permanent','Contract':'Contract'}" 
											cssClass="employeeselect"
										/>
								</td>
		         				<td class="inputerrormessage"><s:fielderror fieldName="employee.empType" /></td>
							</tr>
							<tr>
							    <td class="inputtext"><s:text name="label.header.vendor.vendorName"/></td>
					            <td class="employeeinputtd">
									<s:select 
										headerKey=""
										headerValue="-- Please Select --"
										list="#request.APPL_VENDOR_LIST"
										listKey="vendorId"
										listValue="vendorName"
									    name="employee.vendorIdObj.vendorId"
									   	cssClass="employeeselect"
									    />
								</td>
					            <td class="employeeinputtd"><s:text name="label.employee.vendorName.mandatory"/></td>
							</tr> 
							<tr>
					            <td class="inputtext"><s:text name="label.form.fields.empStatus.name"/></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.empStatusIdObj.empStatusId" /></td>
							</tr>
							<tr>
						         <td></td>
						         <td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					        </tr>
							<tr>
					            <td class="inputtext"><s:text name="label.form.fields.employee.SSN"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empSSNNumber" cssClass="employeeinput"/></td>
					         	<td class="employeeinputtd"><s:text name="label.employee.ssn.mandatory"/></td>
							</tr>
					        <tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.militaryStatus"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empMilitaryService" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empMilitaryService" /></td>
				        	</tr>
					        <tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.drivingLicenseNo"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empDriLicenNum" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empDriLicenNum" /></td>
				        	</tr>
				         	<tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.drivingLicenseExpDate"/></td>
					         	<td class="employeeinputtd"><sj:datepicker name="employee.empDriLicenExpDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empDriLicenExpDate" /></td>
					        </tr>
					        <tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
							</tr>
					        <tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.otherId"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empOtherId" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empOtherId" /></td>
				        	</tr>
				        	<tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.otherIdName"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empOtherName" cssClass="employeeinput"/></td>
				        	</tr>
				        	<tr class="inputtext">
					     		<td class="formtitle1" colspan="3">
				    	 			<s:text name="label.common.message.contactInformation"/>
				    	 		</td>
				     		</tr>
				     		<tr>           
					            <td class="inputtext"><s:text name="label.form.fields.nationality.name"/></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.nationalityIdObj.nationalityId" /></td> 
				         	</tr>
		         			<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
							</tr>
					        <tr> 
					         	<td class="inputtext"><s:text name="label.form.fields.employee.zipCode"/></td>
					        	<td class="employeeinputtd"><s:textfield id="employee.empZipCode" name="employee.empZipCode" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empZipCode" /></td>
				        	</tr>
					        <tr>
								<td class="inputtext"><s:text name="label.form.fields.common.city"/></td>
					         	<td class="employeeinputtd"><s:textfield id="employee.empCityName" name="employee.empCityName" onfocus="javascript:getRegionDetailsEmployee()" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empCityName" /></td>
				        	</tr>
				         	<tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.region"/></td>
					            <td class="employeeinputtd"><s:textfield id="employee.empCounName" name="employee.empCounName" cssClass="employeeinput"/></td>
					            <td class="inputerrormessage"><s:fielderror fieldName="employee.empCounName" /></td>
					        </tr> 	
					        <tr> 	
								<td class="inputtext"><s:text name="label.form.fields.employee.street1"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empStreet1" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empStreet1" /></td>
				        	</tr>
				         	<tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.street2"/></td>
					         	<td class="employeeinputtd"><s:textfield name="employee.empStreet2" cssClass="employeeinput"/></td>
					         	<td class="inputerrormessage"><s:fielderror fieldName="employee.empStreet2" /></td>
					        </tr>	
				         	<tr>
					         	<td class="inputtext"><s:text name="label.form.fields.employee.homeTelephone"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empHmTelephone" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empHmTelephone" /></td>
							</tr>
							<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.mobile"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empMobile" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empMobile" /></td>
				        	</tr>
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
					            <td class="inputtext"><s:text name="label.form.fields.role.name"/></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.roleObj.hcmoRoleId" /></td>
					        </tr>
					        <tr>        
					            <td class="inputtext"><s:text name="label.form.fields.jobTitle.name"/></td>
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
								<td class="inputerrormessage"><s:fielderror fieldName="employee.jobTitleIdObj.jobTitleId" /></td>
					        </tr>
					        <tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
							</tr>
							<tr>        
								<td class="inputtext"><s:text name="label.form.fields.employee.workEmail"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empWorkEmail" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empWorkEmail" /></td>
				        	</tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.workTelephone"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empWorkTelephone" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empWorkTelephone" /></td>
							</tr>
							<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
							</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.employeewage"/></td>
					        	<td class="employeeinputtd">
					        		<s:select 
					        			name="employee.employeeWage"
			            				headerKey=""
			            				headerValue="-- Please Select --"
										list="#{'Daily':'Daily','Monthly':'Monthly','Weekly':'Weekly'}" 
										cssClass="employeeselect"
									/>
					       	    </td>
					       	    <td class="inputerrormessage"><s:fielderror fieldName="employee.employeeWage"/></td>
					        </tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.joinedDate"/></td>
					        	<td class="employeeinputtd"><sj:datepicker name="employee.empJoineddate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empJoineddate" /></td>
					        </tr>
					        <tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
							</tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.terminatedDate"/></td>
					        	<td class="employeeinputtd"><sj:datepicker name="employee.empTerminatedDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empTerminatedDate" /></td>
							</tr> 
							<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
							</tr>      
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.terminatedReason"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empTerminatedReason" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage">
			                		<s:fielderror fieldName="employee.empTerminatedReason" />
			           		    </td>
				        	</tr>
				        	<tr class="inputtext">
					     		<td class="formtitle1" colspan="3">
				    	 			<s:text name="label.common.message.others"/>
				    	 		</td>
				     		</tr>
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.otherEmailId"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empOthEmail" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empOthEmail" /></td>
				        	</tr>
				         	<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.custom1"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empCustom1" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empCustom1" /></td>
							</tr>        
							<tr>
								<td class="inputtext"><s:text name="label.form.fields.employee.custom2"/></td>
					        	<td class="employeeinputtd"><s:textfield name="employee.empCustom2" cssClass="employeeinput"/></td>
					        	<td class="inputerrormessage"><s:fielderror fieldName="employee.empCustom2" /></td>
				        	</tr>
				        	<tr>
								<td class="inputtext"><s:text name="label.form.fields.common.picture"/></td>
					        	<td class="employeeinputtd"><s:file name="userImage"/></td>
				        	</tr>
		            <s:hidden name="employee.employeeId" id="UniqueEmployeeId"/>
		            <s:set name="UniqueEmployeeId" value="employee.employeeId"></s:set>
		    </table></td></tr></table></td></tr></table></td></tr></table>
		    		 <br/>
		    <table align="center"> 
	    	     <tr>
	    		    <td>
	    		    	<img id="indicatorEmployeeEditForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    	<s:submit key="button.label.submit" cssClass="submitbutton117" onclick="doPostCallWithFileUpload('EmployeeEditFormDiv','UpdateEmployees.action','employeeEditFormId');return false;"/></td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
	    		 </tr>
		    </table> 		
		   	</s:form>
	    </div></div>