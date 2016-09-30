
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<div id="submenu_Employees_EditFormId_div">
	
		<div class="submenu_bg">
			<s:if test="#session.EMPLOYEE_ADD == true">
				<sj:a href="setUpEmployees.action" targets="submenu_Employees_EditFormId_div" indicator="indicatorSubMenuEmployeesEditFormId_div" cssClass="link"><s:text name="MTIAddEmployee" /></sj:a> |
			</s:if>
			<s:if test="#session.EMPLOYEE_VIEW == true">
				<sj:a href="getAllEmp.action" targets="submenu_Employees_EditFormId_div" indicator="indicatorSubMenuEmployeesEditFormId_div" cssClass="link"><s:text name="MTIViewEmployee"/></sj:a> |
				<sj:a href="employeeSearchForm.action" targets="submenu_Employees_EditFormId_div" indicator="indicatorSubMenuEmployeesEditFormId_div" cssClass="link"><s:text name="MTISearchEmployee"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuEmployeesEditFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<s:set name="UniqueEmployeeId" value="employee.employeeId"></s:set>
		
		<s:url var="remoteEmpInformationTab" action="getEmployeeInformationTab">
			<s:param name="employee.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remoteEduList" action="getEmployeeAllEducation">
			<s:param name="edu.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remoteChildList" action="getEmployeeAllChildren">
			<s:param name="child.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remoteLicenseList" action="getEmployeeAllLicense">
			<s:param name="license.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>		
		<s:url var="remoteDirectDebitList" action="getEmployeeAllDirectDebit">
			<s:param name="dirDebit.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remoteWorkExpList" action="getEmployeeAllWorkExperience">
			<s:param name="workexp.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remotePayStubList" action="getEmployeeAllPayStub">
			<s:param name="payStub.employee.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="employeeApproverListMainTab" action="getEmployeeApproverTab">
			<s:param name="essEmployeeID" value="#UniqueEmployeeId"></s:param>
		</s:url>
		<s:url var="remoteUserList" action="getEmployeeAllUser">
			<s:param name="user.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
		</s:url>
		
		<sj:tabbedpanel id="remoteEmployeeEditMainTabbedPannel" animate="true">
			<sj:tab id="employeeInformationTab" key="MTEmployeeInformation" href="%{remoteEmpInformationTab}"/>
			<sj:tab id="employeeEducationEditTab" key="Education" href="%{remoteEduList}"/>
			<sj:tab id="employeeChildrenEditTab" key="Dependents" href="%{remoteChildList}"/>
			<sj:tab id="employeeLicenseEditTab" key="License" href="%{remoteLicenseList}"/>
			<sj:tab id="employeeDirectDebitEditTab" key="Direct Debit" href="%{remoteDirectDebitList}"/>
			<sj:tab id="employeeWorkExpEditTab" key="Work Experience" href="%{remoteWorkExpList}"/>
			<sj:tab id="employeePayStubEditTab" key="PayStub" href="%{remotePayStubList}"/>
			<sj:tab id="employeeApproverMainTab" key="Approvers" href="%{employeeApproverListMainTab}"/>
			<sj:tab id="employeeUserLoginTab" key="MTUser" href="%{remoteUserList}"/>
		</sj:tabbedpanel>
	</div>