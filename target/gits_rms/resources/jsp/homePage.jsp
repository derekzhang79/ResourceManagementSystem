<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/menu.tld"  prefix="yui"%>  
<html>
<head>
	
    <title><s:text name="label.homePage"/></title>
</head>
<body>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<jsp:include page="common/menu.jsp" flush="true"/>
<jsp:include page="common/welcome.jsp" flush="true"/>
 	
<div class="informationMessageSingle"><li><span><s:text name="label.homePage"/></span></li></div>
			<img id="indicatorTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

			<s:url var="remoteEduList" action="getAllCountry">
			</s:url>

<br><br>		   
	<table width="300" align="center">
		<tr>
			<th>Independent CRUDS</th>
		</tr>
		<tr> 
			 <s:url var="getAllCountry" action="getAllCountry"/>
		     <td><s:a href="%{getAllCountry}"> View Country</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllNationality" action="getAllNationality"/>
		    <td><s:a href="%{getAllNationality}"> View Nationality</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllEthnicRace" action="getAllEthnicRace"/>	
	        <td><s:a href="%{getAllEthnicRace}"> View Ethnic Race</s:a></td>
	    </tr>
		<tr> 
			<s:url var="getAllProjects" action="getAllProjects"/>
		    <td><s:a href="%{getAllProjects}"> View Project</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllOrganizationType" action="getAllOrganizationType"/>
	        <td><s:a href="%{getAllOrganizationType}"> View OrganizationType</s:a></td>
	  	</tr>	     
		<tr> 		
			<s:url var="getAllCustomer" action="getAllCustomer"/>	
			<td><s:a href="%{getAllCustomer}"> View Customer</s:a></td>
	    </tr>
	    <tr> 
			 <s:url var="getAllHoliday" action="getAllHoliday"/>
		     <td><s:a href="%{getAllHoliday}"> View Holiday</s:a></td>
	    </tr>	
		<tr> 
			<s:url var="getAllSalaryGrade" action="getAllSalaryGrade"/>
		    <td><s:a href="%{getAllSalaryGrade}"> View SalaryGrade</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllJobTitle" action="getAllJobTitle"/>
		    <td><s:a href="%{getAllJobTitle}"> View Job Title</s:a></td>
		</tr>
	</table><br/>
	<table width="300" align="center">
		<tr>
			<th>Base CRUDS</th>
		</tr>
	    <tr> 
	    	<s:url var="getAllClient" action="getAllClient"/>
		    <td><s:a href="%{getAllClient}"> View Client</s:a></td>
		</tr>
	    <tr>
	    	<s:url var="getAllOrganization" action="getAllOrganization"/>        
	        <td><s:a href="%{getAllOrganization}"> View Organization</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllLocation" action="getAllLocation"/>
		    <td><s:a href="%{getAllLocation}"> View Location</s:a></td>	  
	    </tr>	     
	</table><br/>
	
	<table width="300" align="center">
		<tr>
			<th>Leave CRUDS</th>
		</tr>

		
			<s:if test="#session.LEAVE_APPROVER == 'BOTH'">
				<tr>
					 <s:url var="getAllLeaveType" action="getAllLeaveType"/>
				     <td><s:a href="%{getAllLeaveType}">Click Here to View LeaveType</s:a></td>
				</tr>
				<tr> 
			 		<s:url var="getAllEmployeeLeaveQuota" action="getAllEmployeeLeaveQuota"/>
		     		<td><s:a href="%{getAllEmployeeLeaveQuota}">Click Here to View EmployeeLeaveQuota</s:a></td>
	    		</tr>
				<tr>
					<s:url var="getAllSubEmpLeaveTab" action="getAllSubEmpLeaveTab" />
					<td><s:a href="%{getAllSubEmpLeaveTab}"> View Sub Employee Leave</s:a></td>
				</tr>
				
			</s:if>
			<s:if test="#session.LEAVE_APPROVER == 'NON-APPROVER'">
				<tr>
					<s:url var="getAllLeaveTab" action="getAllLeaveTab" />
					<td><s:a href="%{getAllLeaveTab}"> View Employee Leave</s:a></td>
				</tr>
				<tr>
					 <s:url var="getAllLeaveType" action="getAllLeaveType"/>
		   		  <td><s:a href="%{getAllLeaveType}"> View LeaveType</s:a></td>
				</tr>
				<tr> 
					 <s:url var="getAllEmployeeLeaveQuota" action="getAllEmployeeLeaveQuota"/>
		  			 <td><s:a href="%{getAllEmployeeLeaveQuota}"> View EmployeeLeaveQuota</s:a></td>
	    		</tr>
			</s:if>

		
		
	</table><br/>
	<table width="300" align="center">
		<tr>
			<th>Unnecessary CRUDS</th>
		</tr>
		<tr>
			 <s:url var="getAllRegion" action="getAllRegion"/>
		     <td><s:a href="%{getAllRegion}"> View Region</s:a></td>
		</tr>
	</table><br/>
	<table width="300" align="center">
		<tr>
			<th>Employee CRUDS</th>
		</tr>
		<tr>
			 <s:url var="getAllEmp" action="getAllEmp"/>
		     <td><s:a href="%{getAllEmp}"> View Employees</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllEmployeeStatus" action="getAllEmployeeStatus"/>
		   	<td><s:a href="%{getAllEmployeeStatus}"> View Employee Status</s:a></td>
		</tr>		
	    <tr> 
	    	<s:url var="getAllLicense" action="getAllLicense"/>
	        <td><s:a href="%{getAllLicense}"> View License</s:a></td>
	    </tr>
	    <tr>
	    	<s:url var="getAllEducation" action="getAllEducation"/>	   
	        <td><s:a href="%{getAllEducation}"> View Education</s:a></td>
		</tr>
	    <tr>
	    	<s:url var="getAllProjectActivity" action="getAllProjectActivity"/>	
	        <td><s:a href="%{getAllProjectActivity}"> View Project Activity</s:a></td>
		</tr>
		<tr>
			 <s:url var="getAllChildren" action="getAllChildren"/>
		     <td><s:a href="%{getAllChildren}"> View Children</s:a></td>
		</tr>
		<tr> 
			 <s:url var="getAllDirectDebit" action="getAllDirectDebit"/>
		     <td><s:a href="%{getAllDirectDebit}"> View DirectDebit</s:a></td>
		</tr>
		<tr> 
			<s:url var="getAllWorkExperience" action="getAllWorkExperience"/>
			<td><s:a href="%{getAllWorkExperience}"> View WorkExperience</s:a></td>
	    </tr>
	    <tr> 
			<s:url var="getAllEmployeeReportTo" action="getAllEmployeeReportTo"/>
		    <td><s:a href="%{getAllEmployeeReportTo}"> View EmployeeReportTo</s:a></td>
		</tr> 
		<tr> 
			<s:url var="getAllEmpLocationHistory" action="getAllEmpLocationHistory"/>
		    <td><s:a href="%{getAllEmpLocationHistory}"> View Emp. Location History</s:a></td>
		</tr>	
		<tr>
			 <s:url var="getAllRole" action="getAllRole"/>
		     <td><s:a href="%{getAllRole}"> View Role</s:a></td>
	    </tr>	
		<tr> 
			 <s:url var="getAllUser" action="getAllUser"/>
		     <td><s:a href="%{getAllUser}"> View User</s:a></td>
	    </tr>		    
	    <tr> 
			 <s:url var="getAllEmpPassport" action="getAllEmpPassport"/>
		     <td><s:a href="%{getAllEmpPassport}"> View Employee Passport</s:a></td>
	    </tr>	
	    <tr> 
			 <s:url var="getAllBenefit" action="getAllBenefit"/>
		     <td><s:a href="%{getAllBenefit}"> View Employee Benefits</s:a></td>
	    </tr>
		<tr> 
			 <s:url var="doLogout" action="doLogout"/>
		     <td><s:a href="%{doLogout}">Logout</s:a></td>
	    </tr>	
 	</table><br/>

	<table width="300" align="center">
	<tr>
		<th>Expenses CRUDS</th>
	</tr>
	<tr>
		<s:url var="getAllExpType" action="getAllExpType" />
		<td><s:a href="%{getAllExpType}"> View Employee Type</s:a></td>
	</tr>
	<tr>
		<s:url var="getAllEmpExpenses" action="getAllEmpExpenses" />
		<td><s:a href="%{getAllEmpExpenses}"> View Employee Expenses</s:a></td>
	</tr>
	<tr>
		<s:url var="getAllExpApprover" action="getAllExpApprover" />
		<td><s:a href="%{getAllExpApprover}"> View Expenses Approver</s:a></td>
	</tr>
	<tr>
		<s:url id="getAllExpAccountantApprover" action="getAllExpAccountantApprover" />
		<td><s:a href="%{getAllExpAccountantApprover}"> View Expenses Accountant Approver</s:a></td>
	</tr>
	</table><br/>
	
	<table width="300" align="center">
	<tr>
		<th>TimeSheet</th>
	</tr>
	<tr>
  		<s:url var="getAllTimeSheetApprover" action="getAllTimeSheetApprover" />
  		<td><s:a href="%{getAllTimeSheetApprover}">View TimeSheet Approver</s:a></td>
 	</tr>
 	<s:set name="EMPLOYEE_ID" value="#session.EMPLOYEE_ID"></s:set>	
	<s:if test="#session.TIMESHEET_APPROVER == 'BOTH'">
		<tr>
			<td><a href="resources/jsp/TimeSheetUpdate.jsp?consultant_id=${EMPLOYEE_ID}"> Enter Timesheet</a></td>
		</tr>
		<tr>
			<td><s:a href="resources/jsp/ListTimeSheet.jsp" > View Timesheet</s:a></td>
		</tr>
		<tr>
			<td><s:a href="resources/jsp/AppointConsultant.jsp" > Assign Project </s:a></td>
		</tr>
	</s:if>
	<s:if test="#session.TIMESHEET_APPROVER == 'NON-APPROVER'">
		<tr>
			<td><s:a href="resources/jsp/TimeSheetUpdate.jsp" > Enter Timesheet</s:a></td>
		</tr>
	</s:if>
	</table><br/>
</body>
</html>
