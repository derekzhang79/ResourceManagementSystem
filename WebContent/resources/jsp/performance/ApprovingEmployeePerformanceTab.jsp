<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="App_Emp_Peer_Emp_Tab">
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<s:textfield name="questionGroupIdentifiId"></s:textfield>
	<s:textfield name="subEmployeeID"></s:textfield>
	<s:textfield name="sQuesGroupNameIdentiId"></s:textfield>
	<s:textfield name="questionGeneralInfoGroupIdListId"></s:textfield>
	
	<s:url var="getEmployeeQuestionBankList" action="getAppEmpSubEmpQuestions" escapeAmp="false">
		<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
		<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"></s:param>
		<s:param name="questionGeneralInfoGroupIdListId" value="questionGeneralInfoGroupIdListId"></s:param>
	</s:url>
	<s:url var="getEmployeeAnswerEnteredTab" action="getEmployeeAnswerEntered" escapeAmp="false">
		<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
		<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"></s:param>
	</s:url>
	<s:url var="getAllEmployeePeerEmployeeListTab" action="getAllEmployeePeerEmployeeList" escapeAmp="false">
		<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
		<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"></s:param>
	</s:url>
	
	<sj:tabbedpanel id="approverPerformanceTabbedpannel" animate="true">
		<sj:tab id="adminEmployeeQBankListMainTab" key="MTIQuestionsGroupList" href="%{getEmployeeQuestionBankList}"/>
		<sj:tab id="AnswerEmployeeMainTab" key="MTIApprovingEmployee" href="%{getEmployeeAnswerEnteredTab}"/>
		<sj:tab id="AnswerPeerEmployeeListTab" key="MTIPeerEmployee" href="%{getAllEmployeePeerEmployeeListTab}" />
	</sj:tabbedpanel>
</div>