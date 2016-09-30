<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_AnswerEdit_Form_div">
<div class="submenu_bg">
	<s:url var="getAllAnswer" action="getAllAnswer" escapeAmp="false">
		<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"></s:param>
		<s:param name="quesBankGeneralInfo.hcmoQuestionGeneralInfoId" value="hcmoQuestionGeneralInfoId"></s:param>
		<s:param name="questionGeneralInfoGroupIdListId" value="questionGeneralInfoGroupIdListId"></s:param>
		<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
	</s:url>
	<sj:a href="%{getAllAnswer}" targets="submenu_AnswerEdit_Form_div" indicator="indicatorAnswerEdit">Answer List</sj:a>

	<s:if test="answer.status=='peeremployee'">
		<s:url var="getPeerEmployeeQuestionBank" action="getPeerEmployeeQuestionBank">
			<s:param name="employeeId" value="subEmployeeID"></s:param>
		</s:url>
		<sj:a href="%{getPeerEmployeeQuestionBank}" targets="submenu_AnswerEdit_Form_div" indicator="indicatorAnswerEdit">Answer List</sj:a>
	</s:if>

	<s:if test="answer.status=='approvingemployee'">
		<s:url var="getSubEmployeeQuestions" action="getSubEmployeeQuestions">
			<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
		</s:url>
		<sj:a href="%{getSubEmployeeQuestions}" targets="submenu_AnswerEdit_Form_div" indicator="indicatorAnswerEdit">Answer List</sj:a>
	</s:if>
</div>
<img id="indicatorAnswerEdit" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="updateAnswers">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
							 <s:text name="label.header.questionTab"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	               	 <tr>
	               	 	<td class="inputtext"><s:text name="label.header.questionTab" /></td>
	               	 	<td class="employeedisplaytd"><s:property value="answer.hcmoQuestionBankId.hcmoQuestionId.question"/></td>
	               	 </tr>
	               	 <s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='optional'">
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
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeeinput"/></td>
		               	 </tr>
	               	</s:if> 
	               	<s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='numbering'">
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
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeeinput"/></td>
		               	 </tr>
	               	</s:if>
	               	<s:if test="answer.hcmoQuestionBankId.hcmoQuestionId.questionType=='summary'">
		               	  <tr>
		               	 	<td class="inputtext"><s:text name="label.header.answer" /></td>
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeeinput"/></td>
		               	 </tr>
	               	</s:if>
	              <s:hidden name="answer.hcmoAnswerId"></s:hidden>
	              <s:hidden name="answerId"></s:hidden>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
	    		    	<img id="indicatorAnswerEditForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_AnswerEdit_Form_div" indicator="indicatorAnswerEditForm"/>
	    		    </td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 