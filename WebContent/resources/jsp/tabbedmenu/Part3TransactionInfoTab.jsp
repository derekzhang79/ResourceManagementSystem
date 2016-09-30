<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
		<s:url var="getTabDirectDebitList" action="getAllDirectDebit"/>
		<s:url var="getTabReportToList" action="getAllEmployeeReportTo"/>
		<s:url var="getTabEmpPassportList" action="getAllEmpPassport"/>
		<s:url var="getTabBenefitsList" action="getAllBenefit"/>
		<s:url var="getTabLeaveQuotaList" action="getAllEmployeeLeaveQuota"/>
		<s:url var="getTabPayStub" action="getAllPayStubs"/>
		
		<sj:tabbedpanel id="remoteSubTabsEmployeeTransactionInfo" animate="true">
								
			<s:if test="#session.DIRECTDEBIT_ADD == true || #session.DIRECTDEBIT_VIEW == true">
				<sj:tab id="directDebitMainTab" key="MTDirectDebit" href="%{getTabDirectDebitList}"/>
			</s:if>
			
			<s:if test="#session.REPORTTO_ADD == true || #session.REPORTTO_VIEW == true">
				<sj:tab id="reportToMainTab" key="MTReportTo" href="%{getTabReportToList}"/>
			</s:if>
		
			<s:if test="#session.EMPLOYEEPASSPORT_ADD == true || #session.EMPLOYEEPASSPORT_VIEW == true">
				<sj:tab id="empPassportMainTab" key="MTPassport" href="%{getTabEmpPassportList}"/>
			</s:if>
			
			<s:if test="#session.BENEFITS_ADD == true || #session.BENEFITS_VIEW == true">
				<sj:tab id="benefitstMainTab" key="MTBenefits" href="%{getTabBenefitsList}"/>
			</s:if>
			
			<s:if test="#session.LEAVEQUOTA_ADD == true || #session.LEAVEQUOTA_VIEW == true">
				<sj:tab id="leaveQuotaMainTab" key="MTLeaveQuota" href="%{getTabLeaveQuotaList}"/>
			</s:if>
			
			<s:if test="#session.PAYSTUB_ADD == true || #session.PAYSTUB_VIEW == true">
				<sj:tab id="PayStubMainTab" key="MTPayStub" href="%{getTabPayStub}"/>
			</s:if>
		</sj:tabbedpanel>