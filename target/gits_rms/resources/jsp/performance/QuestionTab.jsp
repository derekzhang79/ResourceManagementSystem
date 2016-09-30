<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
		</tr>
	</table>
	
	<s:url var="setUpOptionalQuestionsTab" action="setUpOptionalQuestions"></s:url>
	<s:url var="setUpNumberingQuestionsTab" action="setUpNumberingQuestions"></s:url>
	<s:url var="setUpSummaryQuestionsTab" action="setUpSummaryQuestions"></s:url>
	
	
	<s:url var="setUpQuestionsBankTab" action="getAllQuestionType"></s:url>
	<s:url var="setUpQuestionsBankGeneralInfoTab" action="setUpQuestBankGeneralInfoForm"></s:url>
	<s:url var="getAllEmployeeQuestionBankGroup" action="getAllEmployeeQuestionBankGroup"></s:url>
	<s:url var="getAllApproverSubEmployee" action="getAllApproverSubEmployee"></s:url>
	<s:url var="getAllPeersSubEmployeeTab" action="getAllPeersSubEmployee"></s:url>
	<s:url var="getAdminPerformanceTab" action="getAdminPerformanceTab"></s:url>
	
	<sj:tabbedpanel id="questionTabbedpannel" animate="true">
		<sj:tab id="QuestionOptionalMainTab" key="MTIOptionalQuestion" href="%{setUpOptionalQuestionsTab}" />
		<sj:tab id="QuestionNumberingMainTab" key="MTINumberingQuestion" href="%{setUpNumberingQuestionsTab}" />
		<sj:tab id="QuestionSummaryMainTab" key="MTISummaryQuestion" href="%{setUpSummaryQuestionsTab}" />
		<!--<sj:tab id="QuestionBankMainTab" key="MTIQuestionBank" href="%{setUpQuestionsBankTab}" />
		<sj:tab id="QuestionBankGeneralInfoMainTab" key="MTIQuestionBankGeneralInfo" href="%{setUpQuestionsBankGeneralInfoTab}" />
		<sj:tab id="MyReviewMainTab" key="MTIQuestionsGroupList" href="%{getAllEmployeeQuestionBankGroup}" />
		<s:if test="#session.APPROVING_EMPLOYEE==null"></s:if><s:else>dddd
			<sj:tab id="MySubEmpMainTab" key="MTIApprovingEmployee" href="%{getAllApproverSubEmployee}" />
		</s:else>
		<s:if test="#session.PEER_EMPLOYEE==null"></s:if><s:else>dddd
			<sj:tab id="MyPeersSubEmpMainTab" key="MTIPeerEmployee" href="%{getAllPeersSubEmployeeTab}" />
		</s:else>
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			<sj:tab id="adminPerformanceTab" key="MTIAdminReview" href="%{getAdminPerformanceTab}" />
		</s:if>  -->
		
	</sj:tabbedpanel>