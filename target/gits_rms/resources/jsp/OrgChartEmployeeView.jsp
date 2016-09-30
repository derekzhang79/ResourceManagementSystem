<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeesViewOrgchartId_div">
	<img id="indicatorSubMenuEmployeesOrgchartViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    
	    <s:set name="UniqueEmployeeId" value="employee.employeeId"></s:set>
	
		<s:form>
			<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.employee.view" />
			</td>
	       </tr>
				<%-- <tr>
			     	<td colspan="3" class="employeedisplaytd">
										
		    	 		<img alt="Employee Picture" src="./resources/images/rajesh.JPG" align="left" width="80px" height="100px"/>
		    	 		<br/>
		    	 		<br/>
		    	 		&nbsp;&nbsp;&nbsp;<s:property value="employee.empFirstName" /><br/>
		    	 		&nbsp;&nbsp;&nbsp;<s:property value="employee.empHmTelephone"/><br/>
		    	 		&nbsp;&nbsp;&nbsp;<s:property value="employee.empWorkEmail"/><br/>
		    	 	</td>
	     		</tr> --%>
	       	<tr>
	            <td class="forminner"><table class="tablealign">
	            <tr>
					<td colspan="3" align="left">
		    	 		<img alt="Employee Picture" src="<s:property value="employeePicturePath"/>" height="230px" width="200px" align="left"/>
		    	 	</td>
				</tr>
				<tr class="inputtext">
			     	<td class="formtitle1" colspan="3">
		    	 		<s:text name="label.common.message.personalInformation"/>
		    	 	</td>
		     	</tr>
				<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.employeeId" /></td>
						<td class="employeedisplaytd"><s:property value="employee.employeeId"/></td>
					</tr>
				<tr>
						<td class="inputtext"><s:text name="label.header.employee.firstName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.midName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empMidName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.lastName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empLastName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.nickName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empNickName" /></td>
					</tr>
				
					
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.dob" /></td>
						<td class="employeedisplaytd"><s:date name="employee.empBirthDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					<tr class="inputtext">
			     		<td class="formtitle1" colspan="3"><s:text name="label.common.message.generalInformation"/></td>
		     		</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.gender" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empGenderValue"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.maritalStatus" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empMaritalStatus"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.empSpace" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empSpace"/> <s:text name="label.title.empSpace.allotedSpace"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.ethnicRace.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.ethnicRaceIdObj.ethnicRaceDesc"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.countryName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.country.countryName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.department.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.departmentIdObj.deptName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.team.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.teamIdObj.teamName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.empType" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empType"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.vendor.vendorName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.vendorIdObj.vendorName"/></td>
				    </tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.empStatus" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empStatusIdObj.statusName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.SSN" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empSSNNumber"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.militaryStatus" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empMilitaryService"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.driLicenseNo" />
						</td><td class="employeedisplaytd"><s:property value="employee.empDriLicenNum"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.driLicenseExpDate" /></td>
						<td class="employeedisplaytd"><s:date name="employee.empDriLicenExpDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.otherId" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empOtherId" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.otherIdName" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empOtherName" /></td>
					</tr>
					
					<tr class="inputtext">
			     		<td class="formtitle1" colspan="3"><s:text name="label.common.message.contactInformation"/></td>
		     		</tr>
					 <tr>
						<td class="inputtext"><s:text name="label.header.nationality.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.nationalityIdObj.nationalityName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.zipCode" /></td>
							<td class="employeedisplaytd"><s:property value="employee.empZipCode"/></td>
					</tr>
				<tr>
						<td class="inputtext"><s:text name="label.header.common.city" /></td>
						<td class="employeedisplaytd">
						 <s:property value="employee.empCityName" />
						</td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empCounName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.street1" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empStreet1" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.street2" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empStreet2" /></td>
					</tr>
				
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.homeTelephone" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empHmTelephone"/></td>
					</tr>
					
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.mobile" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empMobile"/></td>
					</tr>
					<tr class="inputtext">
			     		<td class="formtitle1" colspan="3"><s:text name="label.common.message.workInformation"/></td>
		     		</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.role.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.roleObj.roleName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.jobTitle.name" /></td>
						<td class="employeedisplaytd"><s:property value="employee.jobTitleIdObj.jobTitleName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.workEmail" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empWorkEmail"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.workTelephone" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empWorkTelephone"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.employeeWage" /></td>
						<td class="employeedisplaytd"><s:property value="employee.employeeWage"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.employee.joinedDate" /></td>
						<td class="employeedisplaytd"><s:date name="employee.empJoineddate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.empStatus.name" />	</td>
						<td class="employeedisplaytd"><s:property value="employee.empStatus"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.terminatedDate" /></td>
						<td class="employeedisplaytd"><s:date name="employee.empTerminatedDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					 <tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.terminatedReason" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empTerminatedReason" /></td>
					</tr>
					<tr class="inputtext">
			     		<td class="formtitle1" colspan="3"><s:text name="label.common.message.others"/></td>
		     		</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.otherEmailId" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empOthEmail"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.employee.custom1" /></td>
						<td class="employeedisplaytd"><s:property value="employee.empCustom1" /></td>
					</tr>
					<tr>
						 <td class="inputtext"><s:text name="label.form.fields.employee.custom2" /></td>
						 <td class="employeedisplaytd"><s:property value="employee.empCustom2" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.created" /></td>
						<td class="employeedisplaytd"><s:date name="employee.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
						<td class="employeedisplaytd"><s:property value="employee.createdBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.updated" /></td>
						<td class="employeedisplaytd"><s:date name="employee.updated" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
					
					 <tr>
						<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
						<td class="employeedisplaytd"><s:property value="employee.updatedBy.empFirstName" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
						<td class="employeedisplaytd"><s:checkbox name="employee.isActive" label="icActive" disabled="true"></s:checkbox></td>
					</tr>
					
				</table></td></tr></table></td></tr></table></td></tr></table>
				</s:form>
</div>