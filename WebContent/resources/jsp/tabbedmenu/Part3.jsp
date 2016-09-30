<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<table class="maintable">
			<tr align="center"><td class="formtitle">
				<s:text name="MPart3"/>
			</td></tr>
		</table>
		
		<s:if test="#session.EMPLOYEE_ADD == true ||
					#session.EMPLOYEE_VIEW == true ||
					#session.USER_ADD == true ||
					#session.USER_VIEW == true ||
					#session.LICENSE_ADD == true ||
					#session.LICENSE_VIEW == true ||
					#session.EDUCATION_ADD == true ||
					#session.EDUCATION_VIEW == true ||
					#session.CHILDREN_ADD == true ||
					#session.CHILDREN_VIEW == true ||
					#session.WORKEXPERIENCE_ADD == true ||
					#session.WORKEXPERIENCE_VIEW == true ||
					#session.LOCATIONHISTORY_ADD == true ||
					#session.LOCATIONHISTORY_VIEW == true ||
					
					#session.DIRECTDEBIT_ADD == true ||
					#session.DIRECTDEBIT_VIEW == true ||
					#session.REPORTTO_ADD == true ||
					#session.REPORTTO_VIEW == true ||
					#session.EMPLOYEEPASSPORT_ADD == true ||
					#session.EMPLOYEEPASSPORT_VIEW == true ||
					#session.BENEFITS_ADD == true ||
					#session.BENEFITS_VIEW == true ||
					#session.LEAVEQUOTA_ADD == true ||
					#session.LEAVEQUOTA_VIEW == true ||
					
					#session.EXPENSESAPPROVER_ADD == true ||
					#session.EXPENSESAPPROVER_VIEW == true ||
					#session.EXPENSESACCOUNTANT_ADD == true ||
					#session.EXPENSESACCOUNTANT_VIEW == true ||
					#session.LEAVEAPPROVER_ADD == true ||
					#session.LEAVEAPPROVER_VIEW == true ||
					#session.TIMESHEETAPPROVER_ADD == true ||
					#session.TIMESHEETAPPROVER_VIEW == true">
		
			<s:url var="getMainInformationTab" action="MenuPart3InformationTab"/>
			<s:url var="getMainTransactionTab" action="MenuPart3TrancationTab"/>
			<s:url var="getMainApproverTab" action="MenuPart3ApproverTab"/>
			<s:url var="getMainTimesheetCategoryTab" action="MenuPart3TimesheetCategoryTab"/>
					
			<sj:tabbedpanel id="remoteMenuPart3" animate="true">
				<s:if test="#session.EMPLOYEE_ADD == true ||
							#session.EMPLOYEE_VIEW == true ||
							#session.USER_ADD == true ||
							#session.USER_VIEW == true ||
							#session.LICENSE_ADD == true ||
							#session.LICENSE_VIEW == true ||
							#session.EDUCATION_ADD == true ||
							#session.EDUCATION_VIEW == true ||
							#session.CHILDREN_ADD == true ||
							#session.CHILDREN_VIEW == true ||
							#session.WORKEXPERIENCE_ADD == true ||
							#session.WORKEXPERIENCE_VIEW == true ||
							#session.LOCATIONHISTORY_ADD == true ||
							#session.LOCATIONHISTORY_VIEW == true ||
							#session.EMPLOYEESCHEDULE_ADD == true ||
							#session.EMPLOYEESCHEDULE_VIEW == true">
					<sj:tab id="informationMainTab" key="MTInformation" href="%{getMainInformationTab}"/>
				</s:if>
				
				<s:if test="#session.DIRECTDEBIT_ADD == true ||
							#session.DIRECTDEBIT_VIEW == true ||
							#session.REPORTTO_ADD == true ||
							#session.REPORTTO_VIEW == true ||
							#session.EMPLOYEEPASSPORT_ADD == true ||
							#session.EMPLOYEEPASSPORT_VIEW == true ||
							#session.BENEFITS_ADD == true ||
							#session.BENEFITS_VIEW == true ||
							#session.LEAVEQUOTA_ADD == true ||
							#session.LEAVEQUOTA_VIEW == true">
					<sj:tab id="transactionMainTab" key="MTTransaction" href="%{getMainTransactionTab}"/>
				</s:if>
				
				<s:if test="#session.EXPENSESAPPROVER_ADD == true ||
							#session.EXPENSESAPPROVER_VIEW == true ||
							#session.EXPENSESACCOUNTANT_ADD == true ||
							#session.EXPENSESACCOUNTANT_VIEW == true ||
							#session.LEAVEAPPROVER_ADD == true ||
							#session.LEAVEAPPROVER_VIEW == true ||
							#session.TIMESHEETAPPROVER_ADD == true ||
							#session.TIMESHEETAPPROVER_VIEW == true">
					<sj:tab id="approverMainTab" key="MTApprovers" href="%{getMainApproverTab}"/>			
				</s:if>
				
				<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
					<sj:tab id="timesheetCategoryMainTab" key="MTTimeSheetCategory" href="%{getMainTimesheetCategoryTab}"/>	
				</s:if>
				
			</sj:tabbedpanel>
		</s:if>