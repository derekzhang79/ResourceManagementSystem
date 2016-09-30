<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeesViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEE_ADD == true">
			<sj:a href="setUpEmployees.action" targets="submenu_EmployeesViewId_div" indicator="indicatorSubMenuEmployeesViewId_div" cssClass="link"><s:text name="MTIAddEmployee" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEE_VIEW == true">
			<sj:a href="getAllEmp.action" targets="submenu_EmployeesViewId_div" indicator="indicatorSubMenuEmployeesViewId_div" cssClass="link"><s:text name="MTIViewEmployee"/></sj:a> |
			<sj:a href="employeeSearchForm.action" targets="submenu_EmployeesViewId_div" indicator="indicatorSubMenuEmployeesViewId_div" cssClass="link"><s:text name="MTISearchEmployee"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeesViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<jsp:include page="common/messages.jsp" flush="true"/>
		
	    	<s:set name="UniqueEmployeeId" value="employee.employeeId"></s:set>
	    	
	    	<s:url var="essEmpInformationTab" action="getESSEmployeeInformationTab">
				<s:param name="employee.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essEducationEmpTab" action="getEmployeeAllEducation">
				<s:param name="edu.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essChildrenEmpTab" action="getEmployeeAllChildren">
				<s:param name="child.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essLicenseEmpTab" action="getEmployeeAllLicense">
				<s:param name="license.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essDirectDebitEmpTab" action="getEmployeeAllDirectDebit">
				<s:param name="dirDebit.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essWorkExperienceEmpTab" action="getEmployeeAllWorkExperience">
				<s:param name="workexp.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essPayStubEmpTab" action="getEmployeeAllPayStub">
				<s:param name="payStub.employee.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essApproverEmpTab" action="getEmployeeApproverTab">
				<s:param name="essEmployeeID" value="#UniqueEmployeeId"></s:param>
			</s:url>
			<s:url var="essUserLoginEmpTab" action="getEmployeeAllUser">
				<s:param name="user.empIdObj.employeeId" value="#UniqueEmployeeId"></s:param>
			</s:url>
		
			<sj:tabbedpanel id="menuESSMainTabbedpannel" animate="true">
				<sj:tab id="essEmployeeInformationTab" key="MTEmployeeInformation" href="%{essEmpInformationTab}"/>
				<sj:tab id="essEducationTab" key="MTEducation" href="%{essEducationEmpTab}" />
				<sj:tab id="essChildrenTab" key="MTChildren" href="%{essChildrenEmpTab}" />
				<sj:tab id="essLicenseTab" key="MTLicense" href="%{essLicenseEmpTab}" />				
				<sj:tab id="essDirectDebitTab" key="MTDirectDebit" href="%{essDirectDebitEmpTab}" />
				<sj:tab id="essWorkExperienceTab" key="MTWorkExperience" href="%{essWorkExperienceEmpTab}" />
				<sj:tab id="essPayStubTab" key="MTPayStub" href="%{essPayStubEmpTab}" />
				<sj:tab id="essApproverTab" key="MTApprovers" href="%{essApproverEmpTab}" />
				<sj:tab id="essUserLoginTab" key="MTUser" href="%{essUserLoginEmpTab}" />
				
				<%--  <sj:div key="MTGraph">
	            	<img src="getPayrollGraph.action" alt="Payroll Graph"/>
	            	<img src="getTimerollGraph.action" alt="Timeroll Graph"/>
	            </sj:div> --%>
			</sj:tabbedpanel>
	</div>