<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center" />
	</table>
	<s:url var="getKpiQuestionTab" action="getKpiQuestionTab"></s:url>
	<s:url var="getKpiGroupTab" action="getAllQuestionType"></s:url>
	<s:url var="getAssignedKPITab" action="setUpQuestBankGeneralInfoForm"></s:url>
	<s:url var="getAllEmployeeQuestionBankGroup" action="getAllEmployeeQuestionBankGroup"></s:url>
	<s:url var="getAllApproverSubEmployee" action="getAllApproverSubEmployee"></s:url>
	<s:url var="getAllPeersSubEmployeeTab" action="getAllPeersSubEmployee"></s:url>
	<s:url var="getAdminPerformanceTab" action="getAdminPerformanceTab"></s:url>
	
	<sj:tabbedpanel id="kpiQuestionTabbedpannel" animate="true">
		<s:if test="#session.PERFORMANCEMENUKPIQUESTION_VIEW == true">
			<sj:tab id="kpiQuestionsTab" key="MTQuestion" href="%{getKpiQuestionTab}" />
		</s:if>
		<s:if test="#session.PERFORMANCEMENUKPIQUESTIONGROUP_VIEW == true">
			<sj:tab id="kpiGroupTab" key="MTIKPIGroup" href="%{getKpiGroupTab}" />
		</s:if>
		<s:if test="#session.PERFORMANCEMENUKPIASSIGNKPI_VIEW == true">
			<sj:tab id="kpiAssignedKPITab" key="MTIQuestionBankGeneralInfo" href="%{getAssignedKPITab}" />
		</s:if>
		<sj:tab id="MyReviewQuestionsMainTab" key="MTIQuestionsGroupList" href="%{getAllEmployeeQuestionBankGroup}" />
		<s:if test="#session.APPROVING_EMPLOYEE==null"></s:if><s:else>
			<sj:tab id="MySubEmpMainTab" key="MTIApprovingEmployee" href="%{getAllApproverSubEmployee}" />
		</s:else>
		<s:if test="#session.PEER_EMPLOYEE==null"></s:if><s:else>
			<sj:tab id="MyPeersSubEmpMainTab" key="MTIPeerEmployee" href="%{getAllPeersSubEmployeeTab}" />
		</s:else>
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			<sj:tab id="adminPerformanceTab" key="MTIAdminReview" href="%{getAdminPerformanceTab}" />
		</s:if>
	</sj:tabbedpanel>