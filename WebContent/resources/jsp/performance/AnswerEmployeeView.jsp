<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Answer_View_div">
<div class="submenu_bg">
	<s:url var="getAllAnswer" action="getAllAnswer" escapeAmp="false">
		<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"></s:param>
		<s:param name="quesBankGeneralInfo.hcmoQuestionGeneralInfoId" value="hcmoQuestionGeneralInfoId"></s:param>
		<s:param name="questionGeneralInfoGroupIdListId" value="questionGeneralInfoGroupIdListId"></s:param>
		<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
	</s:url>
	<sj:a href="%{getAllAnswer}" targets="submenu_Answer_View_div" indicator="indicatorAnswerView">Answer List</sj:a>
	
</div>
<br/>
<img id="indicatorAnswerView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
		                <td class="formtitle">
							<s:text name="label.title.answer.view" />
	                    </td>
	                </tr>
     			<tr>
	                <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.answer.hcmoAnswerId" /></td>
				<td class="employeedisplaytd"><s:property value="answer.hcmoAnswerId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.employeeGroupName" /></td>
				<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionGeneralInfoGroup.name"/></td>
			</tr>
			 <s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='optional'">
				 <tr>
					<td class="inputtext"><s:text name="label.header.optionalQuestions" /></td>
					<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.question"/></td>
				</tr>
				<tr>
               	 	<td class="inputtext"><s:text name="label.header.question.option1" /></td>
               	 	<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.option1"/></td>
               	 </tr>
               	 <tr>
               	 	<td class="inputtext"><s:text name="label.header.question.option2" /></td>
               	 	<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.option2"/></td>
               	 </tr>
               	 <tr>
               	 	<td class="inputtext"><s:text name="label.header.question.option3" /></td>
               	 	<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.option3"/></td>
               	 </tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.question.option3" /></td>
					<td class="employeedisplaytd"><s:property value="answer.answer"/></td>
				</tr>
			</s:if>
			<s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='numbering'">
				<tr>
					<td class="inputtext"><s:text name="label.header.numberingQuestions" /></td>
					<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.question"/></td>
				</tr>
				<tr>
               	   <td class="inputtext"><s:text name="label.header.question.minRate" /></td>
               	   <td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.minRate"/></td>
               	</tr>
               	<tr>
               	   <td class="inputtext"><s:text name="label.header.question.maxRate" /></td>
               	   <td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.maxRate"/></td>
               	</tr>
               	<tr>
               	   <td class="inputtext"><s:text name="label.header.answer" /></td>
               	   <td class="employeeinputtd"><s:property value="answer.answer"/></td>
               	</tr>
			</s:if>
			<s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='summary'">false
				<tr>
					<td class="inputtext"><s:text name="label.header.summaryQuestions" /></td>
					<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.question"/></td>
				</tr>
	           	  <tr>
	           	 	<td class="inputtext"><s:text name="label.header.answer" /></td>
	           	 		<td class="employeeinputtd"><s:property value="answer.answer"/></td>
	           	 </tr>
           	</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.answer.appraiserEmp" /></td>
				<td class="employeedisplaytd"><s:property value="answer.hcmoAppraiserEmployeeId.empFirstName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.answer.appraisingEmp" /></td>
				<td class="employeedisplaytd"><s:property value="answer.hcmoAppraisingEmployeeId.empFirstName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="answer.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="answer.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="answer.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	    	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="answer.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="answer.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>