<!-- 
name:venkateswara
date:5-7-2012
description:
sub div checkboxs enabl has been changed and aligned border for subdive check boxes, 
added id for all sub div check box tags.-->



<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_RoleFormId_div">
<div class="submenu_bg">
	<s:if test="#session.ROLE_ADD == true">
		<sj:a href="setUpRole.action" targets="submenu_RoleFormId_div" indicator="indicatorSubMenuRoleFormId_div" cssClass="link"><s:text name="MTIAddRole" /></sj:a> |
	</s:if>
	<s:if test="#session.ROLE_VIEW == true">
		<sj:a href="getAllRole.action" targets="submenu_RoleFormId_div" indicator="indicatorSubMenuRoleFormId_div" cssClass="link"><s:text name="MTIViewRole"/></sj:a> |
		<sj:a href="roleSearchForm.action" targets="submenu_RoleFormId_div" indicator="indicatorSubMenuRoleFormId_div" cssClass="link"><s:text name="MTISearchRole"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRoleFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateRole">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="role==null || role.hcmoRoleId == null">
								<s:text name="label.title.role.add" />
							</s:if> <s:else>
								<s:text name="label.title.role.edit" />
							</s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext">
					<s:text	name="label.form.fields.role.name" />
				</td>
				<td class="employeeinputtd">
					<s:textfield name="role.roleName" cssClass="employeeinput" />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="role.roleName" /> </td>
			</tr>
			<tr>
				<td colspan="2">
					<table align="center" class="borderAll">
						<tr>
							<th class="inputtext"><s:text name="label.header.common.label"/></th>
							<th class="inputtext"><s:text name="label.header.common.enable"/></th>
							<th class="inputtext"><s:text name="label.common.selectAndDeSelect"/></th>
							<th><s:checkbox name="selectAllCheckBoxName" id="selectAllCheckBoxId" cssClass="employeecheckbox" onclick="selectAllMenuCheckBox()"></s:checkbox></th>
						</tr>
						<tr>
							<th class="inputtext"><s:text name="label.common.selectAndDeSelect"/></th>
							<th><s:checkbox name="selectAllModulesCheckBoxName" id="selectAllModulesCheckBoxId" cssClass="employeecheckbox" onclick="selectAllModulesMenuCheckBox()"></s:checkbox></th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.common.ess"></s:text></td>
							<td><s:checkbox name="role.ess.view" id="essMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.ess.label" value="ESS"></s:hidden></td>
							<td><s:hidden name="role.ess.urlRoot" value="employeeView.action"></s:hidden></td>
							<td><s:hidden name="role.ess.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.ess.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.ess.add" value="false"></s:hidden>
								<s:hidden name="role.ess.update" value="false"></s:hidden>
								<s:hidden name="role.ess.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.expense.name"/></td>
							<td><s:checkbox name="role.expensemodule.view" id="expenseMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.expensemodule.label" value="ExpenseModule"></s:hidden></td>
							<td><s:hidden name="role.expensemodule.urlRoot" value="getAllEmpExpenses.action"></s:hidden></td>
							<td><s:hidden name="role.expensemodule.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.expensemodule.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.expensemodule.add" value="false"></s:hidden>
								<s:hidden name="role.expensemodule.update" value="false"></s:hidden>
								<s:hidden name="role.expensemodule.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.leave.name"/></td>
							<td><s:checkbox name="role.leavemodule.view" id="leaveMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.leavemodule.label" value="LeaveModule"></s:hidden></td>
							<td><s:hidden name="role.leavemodule.urlRoot" value="getAllLeaveTab.action"></s:hidden></td>
							<td><s:hidden name="role.leavemodule.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.leavemodule.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.leavemodule.add" value="false"></s:hidden>
								<s:hidden name="role.leavemodule.update" value="false"></s:hidden>
								<s:hidden name="role.leavemodule.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.timeSheet.name"/></td>
							<td><s:checkbox name="role.timesheetmodule.view" id="timeSheetMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.timesheetmodule.label" value="TimeSheetModule"></s:hidden></td>
							<td><s:hidden name="role.timesheetmodule.urlRoot" value="showTimeSheetOptions.action"></s:hidden></td>
							<td><s:hidden name="role.timesheetmodule.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.timesheetmodule.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.timesheetmodule.add" value="false"></s:hidden>
								<s:hidden name="role.timesheetmodule.update" value="false"></s:hidden>
								<s:hidden name="role.timesheetmodule.delete" value="false"></s:hidden>
							</td>
						</tr>
					 <tr>
						<td class="inputtext"><s:text name="label.header.common.orgChart"></s:text></td>
						<td><s:checkbox name="role.orgChart.view" id="orgChartMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
						<td><s:hidden name="role.orgChart.label" value="OrgChart"></s:hidden></td>
						<td><s:hidden name="role.orgChart.urlRoot" value="ModalFrameListOrgChartBase.action"></s:hidden></td>
						<td><s:hidden name="role.orgChart.urlAdd" value=""></s:hidden></td>
						<td><s:hidden name="role.orgChart.urlView" value=""></s:hidden></td>
						<td>
							<s:hidden name="role.orgChart.add" value="false"></s:hidden>
							<s:hidden name="role.orgChart.update" value="false"></s:hidden>
							<s:hidden name="role.orgChart.delete" value="false"></s:hidden>
						</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.common.reports"></s:text></td>
							<td><s:checkbox name="role.reports.view" id="reportsMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.reports.label" value="Reports"></s:hidden></td>
							<td><s:hidden name="role.reports.urlRoot" value="menuReports.action"></s:hidden></td>
							<td><s:hidden name="role.reports.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.reports.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.reports.add" value="false"></s:hidden>
								<s:hidden name="role.reports.update" value="false"></s:hidden>
								<s:hidden name="role.reports.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.common.messaging"></s:text></td>
							<td><s:checkbox name="role.messaging.view" id="messagingMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.messaging.label" value="Messaging"></s:hidden></td>
							<td><s:hidden name="role.messaging.urlRoot" value="menuMessaging.action"></s:hidden></td>
							<td><s:hidden name="role.messaging.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.messaging.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.messaging.add" value="false"></s:hidden>
								<s:hidden name="role.messaging.update" value="false"></s:hidden>
								<s:hidden name="role.messaging.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.common.employeeSpace"></s:text></td>
							<td><s:checkbox name="role.employeeSpace.view" id="employeeSpaceMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.employeeSpace.label" value="EmployeeSpace"></s:hidden></td>
							<td><s:hidden name="role.employeeSpace.urlRoot" value="SubMenuEmployeeSpace.action"></s:hidden></td>
							<td><s:hidden name="role.employeeSpace.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.employeeSpace.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.employeeSpace.add" value="false"></s:hidden>
								<s:hidden name="role.employeeSpace.update" value="false"></s:hidden>
								<s:hidden name="role.employeeSpace.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.targets"></s:text></td>
							<td><s:checkbox name="role.targetmodulemenu.view" id="employeetargetMenuAddedId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.targetmodulemenu.label" value="TargetModule"></s:hidden></td>
							<td><s:hidden name="role.targetmodulemenu.urlRoot" value="getAllEmpAssignedTargetList.action"></s:hidden></td>
							<td><s:hidden name="role.targetmodulemenu.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.targetmodulemenu.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.targetmodulemenu.add" value="false"></s:hidden>
								<s:hidden name="role.targetmodulemenu.update" value="false"></s:hidden>
								<s:hidden name="role.targetmodulemenu.delete" value="false"></s:hidden>
							</td>
						</tr>
					</table>	
					<br>		
					<table align="center" class="borderAll">
						<tr>
							<th class="inputtext"><s:text name="label.header.common.label"/></th>
							<th class="inputtext"><s:text name="label.common.link.add"/></th>
							<th class="inputtext"><s:text name="label.common.link.view"/></th>
							<th class="inputtext"><s:text name="label.common.link.update"/></th>
							<th class="inputtext"><s:text name="label.common.link.delete"/></th>
							<th class="inputtext"><s:text name="label.common.selectAndDeSelect"/></th>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.common.message.independentCruds"/></th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.country.name"/></td>
							<td><s:checkbox name="role.countrymenu.add" id="countryMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.countrymenu.view" id="countryMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.countrymenu.update" id="countryMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.countrymenu.delete" id="countryMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="countryMenuSelectAllName" id="countryMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllCoutryMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.countrymenu.label" value="Country"></s:hidden></td>
							<td><s:hidden name="role.countrymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.countrymenu.urlAdd" value="setUpInsertOrUpdateCountry.action"></s:hidden></td>
							<td><s:hidden name="role.countrymenu.urlView" value="getAllCountry.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.nationality.name"/></td>
							<td><s:checkbox name="role.nationalitymenu.add" id="nationalityMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.nationalitymenu.view" id="nationalityMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.nationalitymenu.update" id="nationalityMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.nationalitymenu.delete" id="nationalityMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="nationalityMenuSelectAllName" id="nationalityMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllNationalityMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.nationalitymenu.label" value="Nationality"></s:hidden></td>
							<td><s:hidden name="role.nationalitymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.nationalitymenu.urlAdd" value="setUpNationality.action"></s:hidden></td>
							<td><s:hidden name="role.nationalitymenu.urlView" value="getAllNationality.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.ethnicRace.name"/></td>
							<td><s:checkbox name="role.ethnicracemenu.add" id="ethnicRaceMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.ethnicracemenu.view" id="ethnicRaceMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.ethnicracemenu.update" id="ethnicRaceMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.ethnicracemenu.delete" id="ethnicRaceMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="ethnicRaceMenuSelectAllName" id="ethnicRaceMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEthnicRaceMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.ethnicracemenu.label" value="EthnicRace"></s:hidden></td>
							<td><s:hidden name="role.ethnicracemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.ethnicracemenu.urlAdd" value="setUpEthnicRace.action"></s:hidden></td>
							<td><s:hidden name="role.ethnicracemenu.urlView" value="getAllEthnicRace.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.project.name"/></td>
							<td><s:checkbox name="role.projectmenu.add" id="projectMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectmenu.view" id="projectMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectmenu.update" id="projectMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectmenu.delete" id="projectMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="projectMenuSelectAllName" id="projectMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllProjectMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.projectmenu.label" value="Project"></s:hidden></td>
							<td><s:hidden name="role.projectmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.projectmenu.urlAdd" value="setUpProject.action"></s:hidden></td>
							<td><s:hidden name="role.projectmenu.urlView" value="getAllProjects.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.role.customername"/></td>
							<td><s:checkbox name="role.customermenu.add" id="customerMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.customermenu.view" id="customerMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.customermenu.update" id="customerMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.customermenu.delete" id="customerMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="customerMenuSelectAllName" id="customerMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllCustomerMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.customermenu.label" value="Customer"></s:hidden></td>
							<td><s:hidden name="role.customermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.customermenu.urlAdd" value="setUpCustomer.action"></s:hidden></td>
							<td><s:hidden name="role.customermenu.urlView" value="getAllCustomer.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.holiday.name"/></td>
							<td><s:checkbox name="role.holidaymenu.add" id="holidayMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.holidaymenu.view" id="holidayMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.holidaymenu.update" id="holidayMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.holidaymenu.delete" id="holidayMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="holidayMenuSelectAllName" id="holidayMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllHolidayMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.holidaymenu.label" value="Holiday"></s:hidden></td>
							<td><s:hidden name="role.holidaymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.holidaymenu.urlAdd" value="setUpHoliday.action"></s:hidden></td>
							<td><s:hidden name="role.holidaymenu.urlView" value="getAllHoliday.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.salaryGrade.name"/></td>
							<td><s:checkbox name="role.salarygrademenu.add" id="salaryGradeMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.salarygrademenu.view" id="salaryGradeMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.salarygrademenu.update" id="salaryGradeMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.salarygrademenu.delete" id="salaryGradeMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="salaryGradeMenuSelectAllName" id="salaryGradeMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllSalaryGradeMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.salarygrademenu.label" value="SalaryGrade"></s:hidden></td>
							<td><s:hidden name="role.salarygrademenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.salarygrademenu.urlAdd" value="setUpSalaryGrade.action"></s:hidden></td>
							<td><s:hidden name="role.salarygrademenu.urlView" value="getAllSalaryGrade.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.jobTitle.name"/></td>
							<td><s:checkbox name="role.jobtitlemenu.add" id="jobTitleMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.jobtitlemenu.view" id="jobTitleMenuViewId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.jobtitlemenu.update" id="jobTitleMenuUpdateId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.jobtitlemenu.delete" id="jobTitleMenuDeleteId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="jobTitleMenuSelectAllName" id="jobTitleMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllJobTitleMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.jobtitlemenu.label" value="JobTitle"></s:hidden></td>
							<td><s:hidden name="role.jobtitlemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.jobtitlemenu.urlAdd" value="setUpJobTitle.action"></s:hidden></td>
							<td><s:hidden name="role.jobtitlemenu.urlView" value="getAllJobTitle.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.common.message.baseCruds"/>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.client.title"/></td>
							<td><s:checkbox name="role.clientmenu.add" id="clientMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.clientmenu.view" id="clientMenuViewId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.clientmenu.update" id="clientMenuUpdateId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.clientmenu.delete" id="clientMenuDeleteId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="clientMenuSelectAllName" id="clientMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllClientMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.clientmenu.label" value="Client"></s:hidden></td>
							<td><s:hidden name="role.clientmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.clientmenu.urlAdd" value="setUpClient.action"></s:hidden></td>
							<td><s:hidden name="role.clientmenu.urlView" value="getAllClient.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.location.name"/></td>
							<td><s:checkbox name="role.locationmenu.add" id="locationMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.locationmenu.view" id="locationMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.locationmenu.update" id="locationMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.locationmenu.delete" id="locationMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="locationMenuSelectAllName" id="locationMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLocationMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.locationmenu.label" value="Location"></s:hidden></td>
							<td><s:hidden name="role.locationmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.locationmenu.urlAdd" value="setUpLocation.action"></s:hidden></td>
							<td><s:hidden name="role.locationmenu.urlView" value="getAllLocation.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.expenseType.name"/></td>
							<td><s:checkbox name="role.expensetypemenu.add" id="expenseTypeMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensetypemenu.view" id="expenseTypeMenuViewId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensetypemenu.update" id="expenseTypeMenuUpdateId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensetypemenu.delete" id="expenseTypeMenuDeleteId"  cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="expenseTypeMenuSelectAllName" id="expenseTypeMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllExpenseTypeMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.expensetypemenu.label" value="ExpenseType"></s:hidden></td>
							<td><s:hidden name="role.expensetypemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.expensetypemenu.urlAdd" value="setUpInsertOrUpdateExpType.action"></s:hidden></td>
							<td><s:hidden name="role.expensetypemenu.urlView" value="getAllExpType.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.common.message.leaveCruds"/>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.leaveType.name"/></td>
							<td><s:checkbox name="role.leavetypemenu.add" id="leaveTypeMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leavetypemenu.view" id="leaveTypeMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leavetypemenu.update" id="leaveTypeMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leavetypemenu.delete" id="leaveTypeMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="leaveTypeMenuSelectAllName" id="leaveTypeMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLeaveTypeMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.leavetypemenu.label" value="LeaveType"></s:hidden></td>
							<td><s:hidden name="role.leavetypemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.leavetypemenu.urlAdd" value="setUpLeaveType.action"></s:hidden></td>
							<td><s:hidden name="role.leavetypemenu.urlView" value="getAllLeaveType.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.leave.leaveQuota"/></td>
							<td><s:checkbox name="role.empleavequotamenu.add" id="leaveQuotaMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empleavequotamenu.view" id="leaveQuotaMenuViewId" cssClass="employeecheckbox" onclick="showHideLeaveQuotaRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.empleavequotamenu.update" id="leaveQuotaMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empleavequotamenu.delete" id="leaveQuotaMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="leaveQuotaMenuSelectAllName" id="leaveQuotaMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLeaveQuotaMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.empleavequotamenu.label" value="LeaveQuota"></s:hidden></td>
							<td><s:hidden name="role.empleavequotamenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.empleavequotamenu.urlAdd" value="setUpEmployeeLeaveQuota.action"></s:hidden></td>
							<td><s:hidden name="role.empleavequotamenu.urlView" value="getAllEmployeeLeaveQuota.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="leaveQuotaMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.leaveType"/></td>
											<td><s:checkbox name="role.empleavequotamenu_leavetype.view" id="leaveQuotaMenuLeaveTypeId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.year"/></td>
											<td><s:checkbox name="role.empleavequotamenu_year.view" id="leaveQuotaMenuYearId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.preLeaveCarryForward"/></td>
											<td><s:checkbox name="role.empleavequotamenu_preleavecarryforward.view" id="leaveQuotaMenupreLeaveCarryId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.carryForward"/></td>
											<td><s:checkbox name="role.empleavequotamenu_empleavepending.view"  id="leaveQuotaMenuLeaveCarryForwardId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.empleavequotamenu_leavetype.label" value="LeaveQuota_leavetype"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_leavetype.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.empleavequotamenu_year.label" value="LeaveQuota_year"></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_year.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.label" value="LeaveQuota_preleavecarryforward"></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_preleavecarryforward.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.empleavequotamenu_empleavepending.label" value="LeaveQuota_empleavepending"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleavepending.urlView" value=""></s:hidden>											
											</td>
										</tr>
										<tr>	
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.daysAlloted"/></td>
											<td><s:checkbox name="role.empleavequotamenu_leaveallotteddays.view" id="leaveQuotaMenuDaysAllotedId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.employeeLeaveQuota.leaveTaken"/></td>
											<td><s:checkbox name="role.empleavequotamenu_empleaverequest.view" id="leaveQuotaMenuLeaveTakenId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.label" value="LeaveQuota_leaveallotteddays"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_leaveallotteddays.urlView" value=""></s:hidden>											
											</td>
											<td>
												<s:hidden name="role.empleavequotamenu_empleaverequest.label" value="LeaveQuota_empleaverequest"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.add" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.update" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.delete" value="false"></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empleavequotamenu_empleaverequest.urlView" value=""></s:hidden>										
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.leaveapprover.name"/></td>
							<td><s:checkbox name="role.leaveapprovermenu.add" id="leaveapproverMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leaveapprovermenu.view" id="leaveapproverMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leaveapprovermenu.update" id="leaveapproverMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.leaveapprovermenu.delete" id="leaveapproverMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="leaveapproverMenuSelectAllName" id="leaveapproverMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLeaveapproverMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.leaveapprovermenu.label" value="LeaveApprover"></s:hidden></td>
							<td><s:hidden name="role.leaveapprovermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.leaveapprovermenu.urlAdd" value="setUpInsertOrUpdateLeaveApprover.action"></s:hidden></td>
							<td><s:hidden name="role.leaveapprovermenu.urlView" value="getAllLeaveApprover.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.common.message.removeCruds"/>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.region.name"/></td>
							<td><s:checkbox name="role.regionmenu.add" id="regionMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.regionmenu.view" id="regionMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.regionmenu.update" id="regionMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.regionmenu.delete" id="regionMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="regionMenuSelectAllName" id="regionMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllRegionMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.regionmenu.label" value="Region"></s:hidden></td>
							<td><s:hidden name="role.regionmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.regionmenu.urlAdd" value="setUpInsertOrUpdateRegion.action"></s:hidden></td>
							<td><s:hidden name="role.regionmenu.urlView" value="getAllRegion.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.common.message.employyeCruds"/>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.employee.name"/></td>
							<td><s:checkbox name="role.employeesmenu.add" id="employeeMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeesmenu.view" id="employeeMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeesmenu.update" id="employeeMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeesmenu.delete" id="employeeMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="employeeMenuSelectAllName" id="employeeMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEmployeeMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.employeesmenu.label" value="Employee"></s:hidden></td>
							<td><s:hidden name="role.employeesmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.employeesmenu.urlAdd" value="setUpEmployees.action"></s:hidden></td>
							<td><s:hidden name="role.employeesmenu.urlView" value="getAllEmp.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.department.name"/></td>
							<td><s:checkbox name="role.departmentsmenu.add" id="departmentMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.departmentsmenu.view" id="departmentMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.departmentsmenu.update" id="departmentMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.departmentsmenu.delete" id="departmentMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="departmentMenuSelectAllName" id="departmentMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllDepartmentMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.departmentsmenu.label" value="Department"></s:hidden></td>
							<td><s:hidden name="role.departmentsmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.departmentsmenu.urlAdd" value="setUpDepartment.action"></s:hidden></td>
							<td><s:hidden name="role.departmentsmenu.urlView" value="getAllDepartment.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.team.name"/></td>
							<td><s:checkbox name="role.teamsmenu.add" id="teamMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.teamsmenu.view" id="teamMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.teamsmenu.update" id="teamMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.teamsmenu.delete" id="teamMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="teamMenuSelectAllName" id="teamMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllTeamMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.teamsmenu.label" value="Team"></s:hidden></td>
							<td><s:hidden name="role.teamsmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.teamsmenu.urlAdd" value="setUpTeam.action"></s:hidden></td>
							<td><s:hidden name="role.teamsmenu.urlView" value="getAllTeam.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.currency.name"/></td>
							<td><s:checkbox name="role.currencymenu.add" id="currencyMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.currencymenu.view" id="currencyMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.currencymenu.update" id="currencyMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.currencymenu.delete" id="currencyMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="currencyMenuSelectAllName" id="currencyMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllCurrencyMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.currencymenu.label" value="Currency"></s:hidden></td>
							<td><s:hidden name="role.currencymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.currencymenu.urlAdd" value="setUpCurrency.action"></s:hidden></td>
							<td><s:hidden name="role.currencymenu.urlView" value="getAllCurrency.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.taxAndDeduction.name"/></td>
							<td><s:checkbox name="role.deductionmenu.add" id="taxAndDeductionMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.deductionmenu.view" id="taxAndDeductionMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.deductionmenu.update" id="taxAndDeductionMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.deductionmenu.delete" id="taxAndDeductionMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="taxAndDeductionMenuSelectAllName" id="taxAndDeductionMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllTaxAndDeductionMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.deductionmenu.label" value="Deduction"></s:hidden></td>
							<td><s:hidden name="role.deductionmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.deductionmenu.urlAdd" value="setUpForInsertOrUpdateDeduction.action"></s:hidden></td>
							<td><s:hidden name="role.deductionmenu.urlView" value="getAllDeductions.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.paystub.name"/></td>
							<td><s:checkbox name="role.paystubmenu.add" id="paystubMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.paystubmenu.view" id="paystubMenuViewId" cssClass="employeecheckbox" onclick="showHidePayStubRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.paystubmenu.update" id="paystubMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.paystubmenu.delete" id="paystubMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="paystubMenuSelectAllName" id="paystubMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllPaystubMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.paystubmenu.label" value="Paystub"></s:hidden></td>
							<td><s:hidden name="role.paystubmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.paystubmenu.urlAdd" value="setUpForInsertOrUpdatePayStub.action"></s:hidden></td>
							<td><s:hidden name="role.paystubmenu.urlView" value="getAllPayStubs.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="payStubMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.paystub.grossSalary"/></td>
											<td><s:checkbox name="role.paystubmenu_grosssalary.view" id="payStubMenuGrossSalaryId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.paystub.decDate"/></td>
											<td><s:checkbox name="role.paystubmenu_decdate.view" id="payStubMenuDecDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.paystub.netSalary"/></td>
											<td><s:checkbox name="role.paystubmenu_netsalary.view" id="payStubMenuNetSalaryId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.deduction.name"/></td>
											<td><s:checkbox name="role.paystubmenu_deductionname.view" id="payStubMenuDeductionId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.paystubmenu_grosssalary.label" value="Paystub_grosssalary"></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_grosssalary.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_decdate.label" value="Paystub_decdate"></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_decdate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_netsalary.label" value="Paystub_netsalary"></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_netsalary.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_deductionname.label" value="Paystub_deductionname"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionname.urlView" value=""></s:hidden>
											</td>
										</tr>
										<tr>
											<td class="inputtext"><s:text name="label.header.deduction.type"/></td>
											<td><s:checkbox name="role.paystubmenu_deductiontype.view" id="payStubMenuDeductionTypeId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.deduction.mode"/></td>
											<td><s:checkbox name="role.paystubmenu_deductionmode.view" id="payStubMenuDeductionModeId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.deduction.amount"/></td>
											<td><s:checkbox name="role.paystubmenu_deductionamount.view" id="payStubMenudEductionAmountId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.deduction.effDate"/></td>
											<td><s:checkbox name="role.paystubmenu_deductioneffdate.view" id="payStubMenuEffectiveDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.paystubmenu_deductiontype.label" value="Paystub_deductiontype"></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductiontype.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_deductionmode.label" value="Paystub_deductionmode"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionmode.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_deductionamount.label" value="Paystub_deductionamount"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductionamount.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.paystubmenu_deductioneffdate.label" value="Paystub_deductioneffdate"></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.add" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.update" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.delete" value="false"></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.paystubmenu_deductioneffdate.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.empStatus.name"/></td>
							<td><s:checkbox name="role.employeestatusmenu.add" id="empStatusMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeestatusmenu.view"  id="empStatusMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeestatusmenu.update" id="empStatusMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeestatusmenu.delete" id="empStatusMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="empStatusMenuSelectAllName" id="empStatusMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEmpStatusMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.employeestatusmenu.label" value="EmployeeStatus"></s:hidden></td>
							<td><s:hidden name="role.employeestatusmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.employeestatusmenu.urlAdd" value="setUpForInsertOrUpdateEmpStatus.action"></s:hidden></td>
							<td><s:hidden name="role.employeestatusmenu.urlView" value="getAllEmployeeStatus.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.license.name"/></td>
							<td><s:checkbox name="role.licensemenu.add" id="licenseMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.licensemenu.view" id="licenseMenuViewId" cssClass="employeecheckbox" onclick="showHideLicenseRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.licensemenu.update" id="licenseMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.licensemenu.delete" id="licenseMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="licenseMenuSelectAllName" id="licenseMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLicenseMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.licensemenu.label" value="License"></s:hidden></td>
							<td><s:hidden name="role.licensemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.licensemenu.urlAdd" value="setUpLicense.action"></s:hidden></td>
							<td><s:hidden name="role.licensemenu.urlView" value="getAllLicense.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="licenseMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.license.licNumber"/></td>
											<td><s:checkbox name="role.licensemenu_licensenumber.view" id="licenseMenuNumberId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.license.licenseDate"/></td>
											<td><s:checkbox name="role.licensemenu_licensedate.view" id="licenseMenuDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.license.renewalDate"/></td>
											<td><s:checkbox name="role.licensemenu_licenserenewaldate.view" id="licenseMenuRenewalDateId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td class="inputtext"><s:text name="label.header.common.description"/></td>
											<td><s:checkbox name="role.licensemenu_licenseDesc.view" id="licenseMenuDescriptionId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.licensemenu_licensenumber.label" value="License_licensenumber"></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.add" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.update" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.delete" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.urlRoot" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.urlAdd" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licensenumber.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.licensemenu_licensedate.label" value="License_licensedate"></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.add" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.update" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.delete" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licensedate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.licensemenu_licenserenewaldate.label" value="License_licenserenewaldate"></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.add" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.update" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.delete" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licenserenewaldate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.licensemenu_licenseDesc.label" value="License_licenseDesc"></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.add" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.update" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.delete" value="false"></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.urlRoot" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.urlAdd" value=""></s:hidden>
												<s:hidden name="role.licensemenu_licenseDesc.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.education.name"/></td>
							<td><s:checkbox name="role.educationmenu.add" id="educationMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.educationmenu.view" id="educationMenuViewId" cssClass="employeecheckbox" onclick="showHideEducationRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.educationmenu.update" id="educationMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.educationmenu.delete" id="educationMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="educationMenuSelectAllName" id="educationMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEducationMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.educationmenu.label" value="Education"></s:hidden></td>
							<td><s:hidden name="role.educationmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.educationmenu.urlAdd" value="setUpEducation.action"></s:hidden></td>
							<td><s:hidden name="role.educationmenu.urlView" value="getAllEducation.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="educationMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.education.major"/></td>
											<td><s:checkbox name="role.educationmenu_edumajor.view" id="educationMenuMajorId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.education.year"/></td>
											<td><s:checkbox name="role.educationmenu_eduyear.view" id="educationMenuYearId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.form.fields.education.grade"/></td>
											<td><s:checkbox name="role.educationmenu_edugrade.view" id="educationMenuGradeId" cssClass="employeecheckbox"></s:checkbox></td>
											 
											<td class="inputtext"><s:text name="label.header.common.startDate"/></td>
											<td><s:checkbox name="role.educationmenu_edustartdate.view" id="educationMenuStartDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.educationmenu_edumajor.label" value="Education_edumajor"></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edumajor.urlView" value=""></s:hidden>
											</td>
											
											<td>
												<s:hidden name="role.educationmenu_eduyear.label" value="Education_eduyear"></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduyear.urlView" value=""></s:hidden>
											</td>
											
											<td>
												<s:hidden name="role.educationmenu_edugrade.label" value="Education_edugrade"></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edugrade.urlView" value=""></s:hidden>
											</td>
											
											<td>
												<s:hidden name="role.educationmenu_edustartdate.label" value="Education_edustartdate"></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edustartdate.urlView" value=""></s:hidden>
											</td>
										</tr>
										<tr>
											<td class="inputtext"><s:text name="label.header.common.enddate"/></td>
											<td><s:checkbox name="role.educationmenu_eduenddate.view" id="educationMenuEnddateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.education.univ"/></td>
											<td><s:checkbox name="role.educationmenu_eduuniversity.view" id="educationMenuUnivId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.education.degree"/></td>
											<td><s:checkbox name="role.educationmenu_edudegree.view" id="educationMenuDegreeId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td>
												<s:hidden name="role.educationmenu_eduenddate.label" value="Education_eduenddate"></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduenddate.urlView" value=""></s:hidden>
											</td>
											
											<td>
												<s:hidden name="role.educationmenu_eduuniversity.label" value="Education_eduuniversity"></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_eduuniversity.urlView" value=""></s:hidden>
											</td>
											
											<td>
												<s:hidden name="role.educationmenu_edudegree.label" value="Education_edudegree"></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.add" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.update" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.delete" value="false"></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.urlRoot" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.urlAdd" value=""></s:hidden>
												<s:hidden name="role.educationmenu_edudegree.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.projActivity.projectActi"/></td>
							<td><s:checkbox name="role.projectactivitymenu.add" id="projActivityMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectactivitymenu.view" id="projActivityMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectactivitymenu.update" id="projActivityMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.projectactivitymenu.delete" id="projActivityMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="projActivityMenuSelectAllName" id="projActivityMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllProjActivityMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.projectactivitymenu.label" value="ProjectActivity"></s:hidden></td>
							<td><s:hidden name="role.projectactivitymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.projectactivitymenu.urlAdd" value="setUpProjectActivity.action"></s:hidden></td>
							<td><s:hidden name="role.projectactivitymenu.urlView" value="getAllProjectActivity.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.children.name"/></td>
							<td><s:checkbox name="role.childrenmenu.add" id="childrenMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.childrenmenu.view" id="childrenMenuViewId" cssClass="employeecheckbox" onclick="showHideChildrenRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.childrenmenu.update" id="childrenMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.childrenmenu.delete" id="childrenMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="childrenMenuSelectAllName" id="childrenMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllChildrenMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.childrenmenu.label" value="Children"></s:hidden></td>
							<td><s:hidden name="role.childrenmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.childrenmenu.urlAdd" value="setUpChildren.action"></s:hidden></td>
							<td><s:hidden name="role.childrenmenu.urlView" value="getAllChildren.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="childrenMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.children.name"/></td>											
											<td><s:checkbox name="role.childrenmenu_childName.view" id="childrensMenuchildNameId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.common.dob"/></td>
											<td><s:checkbox name="role.childrenmenu_dob.view" id="childrensMenuDateofBirthId" cssClass="employeecheckbox"></s:checkbox></td>
				
											<td>
												<s:hidden name="role.childrenmenu_dob.label" value="Children_dob"></s:hidden>
												<s:hidden name="role.childrenmenu_dob.add" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_dob.update" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_dob.delete" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_dob.urlRoot" value=""></s:hidden>
												<s:hidden name="role.childrenmenu_dob.urlAdd" value=""></s:hidden>
												<s:hidden name="role.childrenmenu_dob.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.childrenmenu_childName.label" value="Children_childName"></s:hidden>
												<s:hidden name="role.childrenmenu_childName.add" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_childName.update" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_childName.delete" value="false"></s:hidden>
												<s:hidden name="role.childrenmenu_childName.urlRoot" value=""></s:hidden>
												<s:hidden name="role.childrenmenu_childName.urlAdd" value=""></s:hidden>
												<s:hidden name="role.childrenmenu_childName.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.directDebit.name"/></td>
							<td><s:checkbox name="role.directdebitmenu.add" id="directDebitMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.directdebitmenu.view" id="directDebitMenuViewId" cssClass="employeecheckbox" onclick="showHideDirectDebitRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.directdebitmenu.update" id="directDebitMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.directdebitmenu.delete" id="directDebitMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="directDebitMenuSelectAllName" id="directDebitMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllDirectDebitMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.directdebitmenu.label" value="DirectDebit"></s:hidden></td>
							<td><s:hidden name="role.directdebitmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.directdebitmenu.urlAdd" value="setUpDirectDebit.action"></s:hidden></td>
							<td><s:hidden name="role.directdebitmenu.urlView" value="getAllDirectDebit.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="directDebitMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.directDebit.routingNo"/></td>
											<td><s:checkbox name="role.directdebitmenu_routingnum.view" id="directDebitMenuRoutingNoId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.directDebit.account"/></td>
											<td><s:checkbox name="role.directdebitmenu_account.view" id="directDebitMenuAccountId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.directDebit.amount"/></td>
											<td><s:checkbox name="role.directdebitmenu_amount.view" id="directDebitMenuAmountId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.directDebit.accountType"/></td>
											<td><s:checkbox name="role.directdebitmenu_accounttype.view" id="directDebitMenuAccontTypeId" cssClass="employeecheckbox"></s:checkbox></td>
				
											<td>
												<s:hidden name="role.directdebitmenu_routingnum.label" value="DirectDebit_routingnum"></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_routingnum.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.directdebitmenu_account.label" value="DirectDebit_account"></s:hidden>
												<s:hidden name="role.directdebitmenu_account.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_account.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_account.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_account.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_account.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_account.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.directdebitmenu_amount.label" value="DirectDebit_amount"></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_amount.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.directdebitmenu_accounttype.label" value="DirectDebit_accounttype"></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_accounttype.urlView" value=""></s:hidden>
											</td>
										</tr>
										<tr>
											<td class="inputtext"><s:text name="label.header.directDebit.transactionType"/></td>
											<td><s:checkbox name="role.directdebitmenu_transactiontype.view" id="directDebitMenuTransactionTypetId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.directDebit.preAccount"/></td>
											<td><s:checkbox name="role.directdebitmenu_preAccountvalue.view" id="directDebitMenuPrefAccountId" cssClass="employeecheckbox"></s:checkbox></td>
				
											<td>
												<s:hidden name="role.directdebitmenu_transactiontype.label" value="DirectDebit_transactiontype"></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_transactiontype.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.directdebitmenu_preAccountvalue.label" value="DirectDebit_preAccountvalue"></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.add" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.update" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.delete" value="false"></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.urlRoot" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.urlAdd" value=""></s:hidden>
												<s:hidden name="role.directdebitmenu_preAccountvalue.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.empExperience.name"/></td>
							<td><s:checkbox name="role.workexperiencemenu.add" id="empExperienceMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.workexperiencemenu.view" id="empExperienceMenuViewId" cssClass="employeecheckbox" onclick="showHideWorkExperienceRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.workexperiencemenu.update" id="empExperienceMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.workexperiencemenu.delete" id="empExperienceMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="empExperienceMenuSelectAllName" id="empExperienceMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEmpExperienceMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.workexperiencemenu.label" value="WorkExperience"></s:hidden></td>
							<td><s:hidden name="role.workexperiencemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.workexperiencemenu.urlAdd" value="setUpWorkExperience.action"></s:hidden></td>
							<td><s:hidden name="role.workexperiencemenu.urlView" value="getAllWorkExperience.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="workExperienceMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.empExperience.employerName"/></td>
											<td><s:checkbox name="role.workexperiencemenu_employeername.view" id="workExperienceMenuEmployerNameId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empExperience.jobTitle"/></td>
											<td><s:checkbox name="role.workexperiencemenu_empjobtitle.view" id="workExperienceMenuJobTitleId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empExperience.fromDate"/></td>
											<td><s:checkbox name="role.workexperiencemenu_fromdate.view" id="workExperienceMenuFromDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empExperience.toDate"/></td>
											<td><s:checkbox name="role.workexperiencemenu_todate.view" id="workExperienceMenuToDateId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.workexperiencemenu_employeername.label" value="WorkExperience_employeername"></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.add" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.update" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.delete" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.urlRoot" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.urlAdd" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_employeername.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.workexperiencemenu_empjobtitle.label" value="WorkExperience_empjobtitle"></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.add" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.update" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.delete" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.urlRoot" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.urlAdd" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_empjobtitle.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.workexperiencemenu_fromdate.label" value="WorkExperience_fromdate"></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.add" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.update" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.delete" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_fromdate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.workexperiencemenu_todate.label" value="WorkExperience_todate"></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.add" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.update" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.delete" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_todate.urlView" value=""></s:hidden>
											</td>
										</tr>
										<tr>
											<td class="inputtext"><s:text name="label.header.common.comments"/></td>
											<td><s:checkbox name="role.workexperiencemenu_comments.view" id="workExperienceMenuCommentsId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.workexperiencemenu_comments.label" value="WorkExperience_comments"></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.add" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.update" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.delete" value="false"></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.urlRoot" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.urlAdd" value=""></s:hidden>
												<s:hidden name="role.workexperiencemenu_comments.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.common.message.reportTo"/></td>
							<td><s:checkbox name="role.empreporttomenu.add" id="reportToMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empreporttomenu.view" id="reportToMenuViewId" cssClass="employeecheckbox" onclick="showHideReportToRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.empreporttomenu.update" id="reportToMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empreporttomenu.delete" id="reportToMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="reportToMenuSelectAllName" id="reportToMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllReportToMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.empreporttomenu.label" value="ReportTo"></s:hidden></td>
							<td><s:hidden name="role.empreporttomenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.empreporttomenu.urlAdd" value="setUpEmployeeReportTo.action"></s:hidden></td>
							<td><s:hidden name="role.empreporttomenu.urlView" value="getAllEmployeeReportTo.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="employeeReportToMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.employeeReportTo.Supervisor"/></td>
											<td><s:checkbox name="role.empreporttomenu_supempname.view" id="empReporttoMenuSupervisorId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.employeeReportTo.ReportingMode"/></td>
											<td><s:checkbox name="role.empreporttomenu_reportingmodevalue.view" id="empReporttoMenuReportingModeId" cssClass="employeecheckbox"></s:checkbox></td>
				
											<td>
												<s:hidden name="role.empreporttomenu_supempname.label" value="ReportTo_supempname"></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.add" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.update" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.delete" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empreporttomenu_supempname.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.label" value="ReportTo_reportingmodevalue"></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.add" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.update" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.delete" value="false"></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.urlRoot" value=""></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.urlAdd" value=""></s:hidden>
												<s:hidden name="role.empreporttomenu_reportingmodevalue.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.locationHistory.name"/></td>
							<td><s:checkbox name="role.emplocationhistorymenu.add" id="locationHistoryMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.emplocationhistorymenu.view" id="locationHistoryMenuViewId" cssClass="employeecheckbox" onclick="showHideLocationHistoryRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.emplocationhistorymenu.update" id="locationHistoryMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.emplocationhistorymenu.delete" id="locationHistoryMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="locationHistoryMenuSelectAllName" id="locationHistoryMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllLocationHistoryMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.emplocationhistorymenu.label" value="LocationHistory"></s:hidden></td>
							<td><s:hidden name="role.emplocationhistorymenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.emplocationhistorymenu.urlAdd" value="setUpEmpLocationHistory.action"></s:hidden></td>
							<td><s:hidden name="role.emplocationhistorymenu.urlView" value="getAllEmpLocationHistory.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="locationHistoryMenuSubTableDivId" style="display: none;" class="roleformsubdivborder"> 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.location.name"/></td>
											<td><s:checkbox name="role.emplocationhistorymenu_locationname.view" id="empLocationHistoryMenuNameId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.client.name"/></td>
											<td><s:checkbox name="role.emplocationhistorymenu_companyname.view" id="empLocationHistoryMenuClientNameId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.common.startDate"/></td>
											<td><s:checkbox name="role.emplocationhistorymenu_startdate.view" id="empLocationHistoryMenuStartDateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.common.endDate"/></td>
											<td><s:checkbox name="role.emplocationhistorymenu_enddate.view" id="empLocationHistoryMenuEndDateId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.emplocationhistorymenu_locationname.label" value="LocationHistory_locationname"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_locationname.add" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_locationname.update" value="false"></s:hidden>	
												<s:hidden name="role.emplocationhistorymenu_locationname.delete" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_locationname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_locationname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_locationname.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.emplocationhistorymenu_companyname.label" value="LocationHistory_companyname"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_companyname.add" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_companyname.update" value="false"></s:hidden>	
												<s:hidden name="role.emplocationhistorymenu_companyname.delete" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_companyname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_companyname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_companyname.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.emplocationhistorymenu_startdate.label" value="LocationHistory_startdate"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_startdate.add" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_startdate.update" value="false"></s:hidden>	
												<s:hidden name="role.emplocationhistorymenu_startdate.delete" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_startdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_startdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_startdate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.emplocationhistorymenu_enddate.label" value="LocationHistory_enddate"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_enddate.add" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_enddate.update" value="false"></s:hidden>	
												<s:hidden name="role.emplocationhistorymenu_enddate.delete" value="false"></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_enddate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_enddate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.emplocationhistorymenu_enddate.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.employeeSchedule.name"/></td>
							<td><s:checkbox name="role.empschedulermenu.add" id="employeeScheduleMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empschedulermenu.view" id="employeeScheduleMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empschedulermenu.update" id="employeeScheduleMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.empschedulermenu.delete" id="employeeScheduleMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="employeeScheduleMenuSelectAllName" id="employeeScheduleMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllScheduleMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.empschedulermenu.label" value="EmployeeSchedule"></s:hidden></td>
							<td><s:hidden name="role.empschedulermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.empschedulermenu.urlAdd" value="setUpEmpSchedule.action"></s:hidden></td>
							<td><s:hidden name="role.empschedulermenu.urlView" value="getAllEmpSchedule.action"></s:hidden></td>
						</tr>
						
						<tr>
							<td class="inputtext"><s:text name="label.header.role.name"/></td>
							<td><s:checkbox name="role.rolemenu.add" id="roleMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.rolemenu.view" id="roleMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.rolemenu.update" id="roleMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.rolemenu.delete" id="roleMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="roleMenuSelectAllName" id="roleMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllRoleMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.rolemenu.label" value="Role"></s:hidden></td>
							<td><s:hidden name="role.rolemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.rolemenu.urlAdd" value="setUpRole.action"></s:hidden></td>
							<td><s:hidden name="role.rolemenu.urlView" value="getAllRole.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.user.name"/></td>
							<td><s:checkbox name="role.usermenu.add" id="userMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.usermenu.view" id="userMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.usermenu.update" id="userMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.usermenu.delete" id="userMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="userMenuSelectAllName" id="userMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllUserMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.usermenu.label" value="User"></s:hidden></td>
							<td><s:hidden name="role.usermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.usermenu.urlAdd" value="setUpUser.action"></s:hidden></td>
							<td><s:hidden name="role.usermenu.urlView" value="getAllUser.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.empPassport.name"/></td>
							<td><s:checkbox name="role.employeepassportmenu.add" id="empPassportMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeepassportmenu.view" id="empPassportMenuViewId" cssClass="employeecheckbox" onclick="showHidePassportRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.employeepassportmenu.update" id="empPassportMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.employeepassportmenu.delete" id="empPassportMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="empPassportMenuSelectAllName" id="empPassportMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllEmpPassportMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.employeepassportmenu.label" value="EmployeePassport"></s:hidden></td>
							<td><s:hidden name="role.employeepassportmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.employeepassportmenu.urlAdd" value="setUpEmpPassport.action"></s:hidden></td>
							<td><s:hidden name="role.employeepassportmenu.urlView" value="getAllEmpPassport.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="passportMenuSubTableDivId" style="display: none"; class="roleformsubdivborder" > 
									<table>
										<tr>
											<td class="inputtext"><s:text name="label.header.empPassport.epPassportNo"/></td>
											<td><s:checkbox name="role.employeepassportmenu_passportno.view" id="employeeMenuPassportNoId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.PassportIssueDate"/></td>
											<td><s:checkbox name="role.employeepassportmenu_passportissuedate.view" id="employeePassportMenuPassportIssueId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.PassportExpireDate"/></td>
											<td><s:checkbox name="role.employeepassportmenu_passportexpiredate.view" id="employeePassportMenuPassportExpiredateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.epPassportTypeFlg"/></td>
											<td><s:checkbox name="role.employeepassportmenu_passporttypeflg.view" id="employeePassportMenuPassportTypeFlagId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.employeepassportmenu_passportno.label" value="EmployeePassport_passportno"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportno.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportno.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_passportno.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportno.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportno.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportno.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_passportissuedate.label" value="EmployeePassport_passportissuedate"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportissuedate.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportissuedate.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_passportissuedate.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportissuedate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportissuedate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportissuedate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.label" value="EmployeePassport_passportexpiredate"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_passportexpiredate.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passportexpiredate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.label" value="EmployeePassport_passporttypeflg"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_passporttypeflg.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_passporttypeflg.urlView" value=""></s:hidden>
											</td>
										</tr>
										<tr>
											<td class="inputtext"><s:text name="label.header.empPassport.epL9Status"/></td>
											<td><s:checkbox name="role.employeepassportmenu_l9Status.view" id="employeePassportMenu_19StatusId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.epL9ReviewDate"/></td>
											<td><s:checkbox name="role.employeepassportmenu_l9reviewdate.view" id="employeePassportMenu_19ReviewdateId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.countryName"/></td>
											<td><s:checkbox name="role.employeepassportmenu_countryname.view" id="employeePassportMenuCitizenshipId" cssClass="employeecheckbox"></s:checkbox></td>
											
											<td class="inputtext"><s:text name="label.header.empPassport.epComments"/></td>
											<td><s:checkbox name="role.employeepassportmenu_comments.view" id="employeepassportMenuCommentsId" cssClass="employeecheckbox"></s:checkbox></td>
						
											<td>
												<s:hidden name="role.employeepassportmenu_l9Status.label" value="EmployeePassport_l9Status"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9Status.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9Status.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_l9Status.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9Status.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9Status.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9Status.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.label" value="EmployeePassport_l9reviewdate"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_l9reviewdate.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_l9reviewdate.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_countryname.label" value="EmployeePassport_countryname"></s:hidden>
												<s:hidden name="role.employeepassportmenu_countryname.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_countryname.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_countryname.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_countryname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_countryname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_countryname.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.employeepassportmenu_comments.label" value="EmployeePassport_comments"></s:hidden>
												<s:hidden name="role.employeepassportmenu_comments.add" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_comments.update" value="false"></s:hidden>	
												<s:hidden name="role.employeepassportmenu_comments.delete" value="false"></s:hidden>
												<s:hidden name="role.employeepassportmenu_comments.urlRoot" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_comments.urlAdd" value=""></s:hidden>
												<s:hidden name="role.employeepassportmenu_comments.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.benefit.name"/></td>
							<td><s:checkbox name="role.benefitsmenu.add" id="benefitMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.benefitsmenu.view" id="benefitMenuViewId" cssClass="employeecheckbox" onclick="showHideBenefitsRoleDiv()"></s:checkbox></td>
							<td><s:checkbox name="role.benefitsmenu.update" id="benefitMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.benefitsmenu.delete" id="benefitMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="benefitMenuSelectAllName" id="benefitMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllBenefitMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.benefitsmenu.label" value="Benefits"></s:hidden></td>
							<td><s:hidden name="role.benefitsmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.benefitsmenu.urlAdd" value="setUpBenefit.action"></s:hidden></td>
							<td><s:hidden name="role.benefitsmenu.urlView" value="getAllBenefit.action"></s:hidden></td>
						</tr>
						<tr>
							<td colspan="6">
								<div id="benefitsMenuSubTableDivId" style="display: none"; class="roleformsubdivborder"> 
                                   <table>
                                     <tr>
									   <td class="inputtext"><s:text name="label.header.benefit.year"/></td>
										<td><s:checkbox name="role.benefitsmenu_year.view" id="benefitsMenuYearId" cssClass="employeecheckbox"></s:checkbox></td>
										<td class="inputtext"><s:text name="label.header.benefit.benefitsName"/></td>
											<td><s:checkbox name="role.benefitsmenu_benefitsname.view" id="benefitsMenuNameId" cssClass="employeecheckbox"></s:checkbox></td>
											<td class="inputtext"><s:text name="label.header.benefit.benefitsAttachFile"/></td>
											<td><s:checkbox name="role.benefitsmenu_attachfile.view" id="benefitsMenuAttachFileId" cssClass="employeecheckbox"></s:checkbox></td>
				                            <td>
												<s:hidden name="role.benefitsmenu_year.label" value="Benefits_year"></s:hidden>
												<s:hidden name="role.benefitsmenu_year.add" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_year.update" value="false"></s:hidden>	
												<s:hidden name="role.benefitsmenu_year.delete" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_year.urlRoot" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_year.urlAdd" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_year.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.benefitsmenu_benefitsname.label" value="Benefits_benefitsname"></s:hidden>
												<s:hidden name="role.benefitsmenu_benefitsname.add" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_benefitsname.update" value="false"></s:hidden>	
												<s:hidden name="role.benefitsmenu_benefitsname.delete" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_benefitsname.urlRoot" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_benefitsname.urlAdd" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_benefitsname.urlView" value=""></s:hidden>
											</td>
											<td>
												<s:hidden name="role.benefitsmenu_attachfile.label" value="Benefits_attachfile"></s:hidden>
												<s:hidden name="role.benefitsmenu_attachfile.add" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_attachfile.update" value="false"></s:hidden>	
												<s:hidden name="role.benefitsmenu_attachfile.delete" value="false"></s:hidden>
												<s:hidden name="role.benefitsmenu_attachfile.urlRoot" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_attachfile.urlAdd" value=""></s:hidden>
												<s:hidden name="role.benefitsmenu_attachfile.urlView" value=""></s:hidden>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.timesheet.name"/></td>
							<td><s:checkbox name="role.timesheetapprovermenu.add" id="timesheetApproverMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.timesheetapprovermenu.view" id="timesheetApproverMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.timesheetapprovermenu.update" id="timesheetApproverMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.timesheetapprovermenu.delete" id="timesheetApproverMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="timesheetApproverMenuSelectAllName" id="timesheetApproverMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllTimesheetApproverMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.timesheetapprovermenu.label" value="TimeSheetApprover"></s:hidden></td>
							<td><s:hidden name="role.timesheetapprovermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.timesheetapprovermenu.urlAdd" value="setUpInsertOrUpdateTimeSheetApprover.action"></s:hidden></td>
							<td><s:hidden name="role.timesheetapprovermenu.urlView" value="getAllTimeSheetApprover.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.target.goal"/></td>
							<td><s:checkbox name="role.targetsmenu.add" id="targetandGoalMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetsmenu.view" id="targetandGoalMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetsmenu.update" id="targetandGoalMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetsmenu.delete" id="targetandGoalMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="targetandGoalMenuSelectAllName" id="targetandGoalMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllTargetandGoalMenuCheckbox()"></s:checkbox></td>
							<td><s:hidden name="role.targetsmenu.label" value="Targets"></s:hidden></td>
							<td><s:hidden name="role.targetsmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.targetsmenu.urlAdd" value="setUpTargets.action"></s:hidden></td>
							<td><s:hidden name="role.targetsmenu.urlView" value="getAllTargets.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.targets.type"/></td>
							<td><s:checkbox name="role.targetstypemenu.add" id="targetTypeMenuAddedId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetstypemenu.view" id="targetTypeMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetstypemenu.update" id="targetTypeMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.targetstypemenu.delete" id="targetTypeMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="targetTypeMenuSelectAllName" id="targetTypeMenuSelectAllTypeId" cssClass="employeecheckbox" onclick="selectAllTargetTypeMenuCheckbox()"></s:checkbox></td>
							<td><s:hidden name="role.targetstypemenu.label" value="TargetsType"></s:hidden></td>
							<td><s:hidden name="role.targetstypemenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.targetstypemenu.urlAdd" value="setUpTargetType.action"></s:hidden></td>
							<td><s:hidden name="role.targetstypemenu.urlView" value="getAllTargetType.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="5" class="formtitle1">
								<s:text name="label.common.message.expenseCruds"></s:text>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.expapprover.name"/></td>
							<td><s:checkbox name="role.expensesapprovermenu.add" id="expapproverMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesapprovermenu.view" id="expapproverMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesapprovermenu.update" id="expapproverMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesapprovermenu.delete" id="expapproverMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="expapproverMenuSelectAllName" id="expapproverMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllExpapproverMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.expensesapprovermenu.label" value="ExpensesApprover"></s:hidden></td>
							<td><s:hidden name="role.expensesapprovermenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.expensesapprovermenu.urlAdd" value="setUpInsertOrUpdateExpApprover.action"></s:hidden></td>
							<td><s:hidden name="role.expensesapprovermenu.urlView" value="getAllExpApprover.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.common.message.expenseAccountant"/></td>
							<td><s:checkbox name="role.expensesaccountantmenu.add" id="expenseAccountantMenuAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesaccountantmenu.view" id="expenseAccountantMenuViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesaccountantmenu.update" id="expenseAccountantMenuUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.expensesaccountantmenu.delete" id="expenseAccountantMenuDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="expenseAccountantMenuSelectAllName" id="expenseAccountantMenuSelectAllId" cssClass="employeecheckbox" onclick="selectAllExpenseAccountantMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.expensesaccountantmenu.label" value="ExpensesAccountant"></s:hidden></td>
							<td><s:hidden name="role.expensesaccountantmenu.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.expensesaccountantmenu.urlAdd" value="setUpInsertOrUpdateExpAccountantApprover.action"></s:hidden></td>
							<td><s:hidden name="role.expensesaccountantmenu.urlView" value="getAllExpAccountantApprover.action"></s:hidden></td>
						</tr>
						<tr>
							<th colspan="6" class="formtitle1">
								<s:text name="label.header.performace.module"></s:text>
							</th>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.category"/></td>
							<td><s:checkbox name="role.performancemenucategory.add" id="performancemenucategoryAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenucategory.view" id="performancemenucategoryViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenucategory.update" id="performancemenucategoryUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenucategory.delete" id="performancemenucategoryDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="performancecategoryMenuSelectAllName" id="performancecategoryMenuSelectallId" cssClass="employeecheckbox" onclick="selectAllPerformanceMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.performancemenucategory.label" value="PerformanceCategory"></s:hidden></td>
							<td><s:hidden name="role.performancemenucategory.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenucategory.urlAdd" value="setUpCategory.action"></s:hidden></td>
							<td><s:hidden name="role.performancemenucategory.urlView" value="getAllCategory.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.header.subCategory"/></td>
							<td><s:checkbox name="role.performancemenusubcategory.add" id="performancemenusubcategoryAddId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenusubcategory.view" id="performancemenusubcategoryViewId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenusubcategory.update" id="performancemenusubcategoryUpdateId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="role.performancemenusubcategory.delete" id="performancemenusubcategoryDeleteId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="performanceSubcategoryMenuSelectAllName" id="performanceSubcategoryMenuSelectallId" cssClass="employeecheckbox" onclick="selectAllPerformanceMenuSubCategoryCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.performancemenusubcategory.label" value="PerformanceSubCategory"></s:hidden></td>
							<td><s:hidden name="role.performancemenucategory.urlRoot" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenusubcategory.urlAdd" value="setUpSubCategory.action"></s:hidden></td>
							<td><s:hidden name="role.performancemenusubcategory.urlView" value="getAllSubCategory.action"></s:hidden></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="MTQuestion"></s:text></td>
							<td><s:checkbox name="role.performancemenukpiquestion.view" id="performanceMenukpiQuestionId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:checkbox name="kpiMenuSelectAllName" id="kpiMenuSelectallNameId" cssClass="employeecheckbox" onclick="selectAllkpiMenuCheckBox()"></s:checkbox></td>
							<td><s:hidden name="role.performancemenukpiquestion.label" value="PerformancemenuKpiQuestion"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestion.urlRoot" value="getKpiQuestionTab.action"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestion.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestion.urlView" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestion.add" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiquestion.update" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiquestion.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="MTIKPIGroup"></s:text></td>
							<td><s:checkbox name="role.performancemenukpiquestiongroup.view" id="performanceMenukpiGroupId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.performancemenukpiquestiongroup.label" value="PerformancemenuKpiQuestionGroup"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestiongroup.urlRoot" value="getAllQuestionType.action"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestiongroup.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiquestiongroup.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.performancemenukpiquestiongroup.add" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiquestiongroup.update" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiquestiongroup.delete" value="false"></s:hidden>
							</td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="MTIQuestionBankGeneralInfo"></s:text></td>
							<td><s:checkbox name="role.performancemenukpiassignkpi.view" id="performanceMenuKpiAssignedkpiId" cssClass="employeecheckbox"></s:checkbox></td>
							<td><s:hidden name="role.performancemenukpiassignkpi.label" value="PerformancemenuKpiAssignkpi"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiassignkpi.urlRoot" value="setUpQuestBankGeneralInfoForm.action"></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiassignkpi.urlAdd" value=""></s:hidden></td>
							<td><s:hidden name="role.performancemenukpiassignkpi.urlView" value=""></s:hidden></td>
							<td>
								<s:hidden name="role.performancemenukpiassignkpi.add" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiassignkpi.update" value="false"></s:hidden>
								<s:hidden name="role.performancemenukpiassignkpi.delete" value="false"></s:hidden>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorRoleFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_RoleFormId_div" indicator="indicatorRoleFormId_div"/>
				</td>
				<s:if test="role==null || role.hcmoRoleId==null">
				       <td>
		    		    	<s:url var="resetRole" action="resetRole"></s:url>
		    	            <sj:submit href="%{resetRole}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_RoleFormId_div" indicator="indicatorRoleFormId_div"/>
			          </td>
			     </s:if>
			     <s:else>
				      <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
				</s:else>
			</tr>
			
		</table>
		<s:hidden name="role.hcmoRoleId"/>
		<div style="display: none;">
			<table>
				<tr>
					<td class="inputtext"><s:text name="label.common.message.home"/></td>
					<td><s:hidden name="role.homemenu.view" value="true"></s:hidden></td>
					<td><s:hidden name="role.homemenu.label" value="Home"></s:hidden></td>
					<td><s:hidden name="role.homemenu.urlRoot" value="employeeView.action"></s:hidden></td>
					<td><s:hidden name="role.homemenu.urlAdd" value=""></s:hidden></td>
					<td><s:hidden name="role.homemenu.urlView" value=""></s:hidden></td>
					<td>
						<s:hidden name="role.homemenu.add" value="false"></s:hidden>
						<s:hidden name="role.homemenu.update" value="false"></s:hidden>
						<s:hidden name="role.homemenu.delete" value="false"></s:hidden>
					</td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.common.message.logout"/></td>
					<td><s:hidden name="role.logoutmenu.view" value="true"></s:hidden></td>
					<td><s:hidden name="role.logoutmenu.label" value="Logout"></s:hidden></td>
					<td><s:hidden name="role.logoutmenu.urlRoot" value="doLogout.action"></s:hidden></td>
					<td><s:hidden name="role.logoutmenu.urlAdd" value=""></s:hidden></td>
					<td><s:hidden name="role.logoutmenu.urlView" value=""></s:hidden></td>
					<td>
						<s:hidden name="role.logoutmenu.add" value="false"></s:hidden>
						<s:hidden name="role.logoutmenu.update" value="false"></s:hidden>
						<s:hidden name="role.logoutmenu.delete" value="false"></s:hidden>
					</td>
				</tr>
			</table>
		</div>	
	</s:form>
</div>