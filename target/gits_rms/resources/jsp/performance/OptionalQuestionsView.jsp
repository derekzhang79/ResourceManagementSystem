<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OptionalQuestionsView_div">
	<div class="submenu_bg">
		<sj:a href="setUpOptionalQuestions.action" targets="submenu_OptionalQuestionsView_div" indicator="indicatorOptionalQuestionsView" cssClass="link"><s:text name="MTIAddQuestion" /></sj:a> |
		<sj:a href="getAllOptionalQuestions.action" targets="submenu_OptionalQuestionsView_div" indicator="indicatorOptionalQuestionsView" cssClass="link"><s:text name="MTIViewQuestion"/></sj:a>
	</div>
<br/>
	<img id="indicatorOptionalQuestionsView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
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
							<s:text name="label.title.question.view" />
	                    </td>
	                </tr>
     			<tr>
	                <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.questions.hcmoQuestionId" /></td>
				<td class="employeedisplaytd"><s:property value="question.hcmoQuestionId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.questionTab" /></td>
				<td class="employeedisplaytd"><s:property value="question.question"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.question.questionType" /></td>
				<td class="employeedisplaytd"><s:property value="question.questionType"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.question.option1" /></td>
				<td class="employeedisplaytd"><s:property value="question.option1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.question.option2" /></td>
				<td class="employeedisplaytd"><s:property value="question.option2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.question.option3" /></td>
				<td class="employeedisplaytd"><s:property value="question.option3"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="question.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="question.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="question.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	    	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="question.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="question.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>