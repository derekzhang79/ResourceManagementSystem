<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_MyReviewAdd_Form_div">
	<div class="submenu_bg">
		<s:if test="employeeType=='Employee'">
			<sj:a href="getAllEmployeeQuestionBankGroup.action" targets="submenu_MyReviewAdd_Form_div" indicator="indicatorEmployeeAnswer" cssClass="link"><s:text name="MTIQuestionBank" /></sj:a>
			<img id="indicatorEmployeeAnswer" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		</s:if>
	
		<s:if test="employeeType=='Peer Employee'">
			<s:url var="getPeerEmployeeQuestionBank" value="/getPeerEmployeeQuestionBank.action">
				<s:param name="employeeId" value="employeeId"></s:param>
			</s:url>
			<sj:a href="%{getPeerEmployeeQuestionBank}" indicator="indicatorEmployeeAnswer" targets="submenu_MyReviewAdd_Form_div" cssClass="link"><s:text name="MTIQuestionBank"/></sj:a>
		</s:if>
	
		<s:if test="employeeType=='Approving Employee'">
			<s:url var="getSubEmployeeQuestions" value="/getSubEmployeeQuestions.action">
				<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
			</s:url>
			<sj:a href="%{getSubEmployeeQuestions}" indicator="indicatorEmployeeAnswer" targets="submenu_MyReviewAdd_Form_div" cssClass="link"><s:text name="MTIQuestionBank"/></sj:a>
		</s:if>
	</div>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateAnswer">
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
	               	 	<td class="inputtext"><s:text name="label.header.questionName" /></td>
	               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.question"/></td>
	               	 </tr>
	               	 <s:if test="quesBank.hcmoQuestionId.questionType=='optional'">
		               	 <tr>
		               	 	<td class="inputtext"><s:text name="label.header.question.option1" /></td>
		               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.option1"/></td>
		               	 </tr>
		               	 <tr>
		               	 	<td class="inputtext"><s:text name="label.header.question.option2" /></td>
		               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.option2"/></td>
		               	 </tr>
		               	 <tr>
		               	 	<td class="inputtext"><s:text name="label.header.question.option3" /></td>
		               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.option3"/></td>
		               	 </tr>
		               	 <tr>
		               	 	<td class="inputtext"><s:text name="label.header.answer" /></td>
		               	 	<s:if test="answerSaved=='notAnswerSaved'">
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeeinput"/></td>
		               	 	</s:if>
		               	 	<s:else>
		               	 		<td class="employeeinputtd"><s:property value="answerEntered"/></td>
		               	 	</s:else>
		               	 </tr>
	               	</s:if> 
	               	<s:if test="quesBank.hcmoQuestionId.questionType=='numbering'">
	               		<tr>
		               	 	<td class="inputtext"><s:text name="label.header.question.minRate" /></td>
		               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.minRate"/></td>
		               	 </tr>
		               	 <tr>
		               	 	<td class="inputtext"><s:text name="label.header.question.maxRate" /></td>
		               	 	<td class="employeedisplaytd"><s:property value="quesBankId.hcmoQuestionId.maxRate"/></td>
		               	 </tr>
		               	  <tr>
		               	 	<td class="inputtext"><s:text name="label.header.answer" /></td>
		               	 	<s:if test="answerSaved=='notAnswerSaved'">
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeeinput"/></td>
		               	 	</s:if>
		               	 	<s:else>
		               	 		<td class="employeeinputtd"><s:property value="answerEntered"/></td>
		               	 	</s:else>
		               	 </tr>
	               	</s:if>
	               	<s:if test="quesBank.hcmoQuestionId.questionType=='summary'">
		               	  <tr>
		               	 	<td class="inputtext"><s:text name="label.header.answer" /></td>
		               	 	<s:if test="answerSaved=='notAnswerSaved'">
		               	 		<td class="employeeinputtd"><s:textfield name="answerEntered" cssClass="employeetextarea"  cssStyle="{width:200px;height:auto;}"/></td>
		               	 	</s:if>
		               	 	<s:else>
		               	 		<td class="employeeinputtd"><s:property value="answerEntered"/></td>
		               	 	</s:else>
		               	 </tr>
	               	</s:if>
	              <s:hidden name="quesBankId.hcmoQuestionBankId"></s:hidden>
	              <s:hidden name="questionGeneralInfoGroupIdListId"></s:hidden>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
	    		    	<img id="indicatorAnswerForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_MyReviewAdd_Form_div" indicator="indicatorAnswerForm"/>
	    		    </td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 