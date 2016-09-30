<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.AnswerVO"%>

<div id="submenu_answer_employee_list_div_id">
<div class="submenu_bg">
	<s:if test="status=='employee'">
		<sj:a href="getAllEmployeeQuestionBankGroup.action" targets="submenu_answer_employee_list_div_id" indicator="indicatorAnswerEmployee" cssClass="link"><s:text name="MTIQuestionBank" /></sj:a> |
		<img id="indicatorEmployeeAnswerId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	</s:if>
	
	<s:if test="status=='approvingemployee'">
		<s:url var="getSubEmployeeQuestions" action="getSubEmployeeQuestions">
			<s:param name="subEmployeeID" value="subEmployeeID"></s:param>
		</s:url>
		<sj:a href="%{getSubEmployeeQuestions}" targets="submenu_answer_employee_list_div_id" indicator="indicatorAnswerEmployee"><s:text name="MTIQuestionBank" /></sj:a>
	</s:if>
	
	<s:if test="status=='peeremployee'">
		<s:url var="getPeerEmployeeQuestionBank" action="getPeerEmployeeQuestionBank">
			<s:param name="employeeId" value="subEmployeeID"></s:param>
		</s:url>
		<sj:a href="%{getPeerEmployeeQuestionBank}" targets="submenu_answer_employee_list_div_id" indicator="indicatorAnswerEmployee"><s:text name="MTIQuestionBank" /></sj:a>
	</s:if>
	
</div>
<br/>
<img id="indicatorAnswerEmployee" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.answer.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.questionTab" var="HQuestion"></s:text>
	  <s:text name="label.header.answer" var="HAnswer"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_answersEmployeeList_div_id">
		  <display:table class="tableborder" id="answerListId" name="answerList" pagesize="${NO_OF_RECORDS}" requestURI="getAllNationality.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		Integer sAnswerId = ((AnswerVO)pageContext.getAttribute("answerListId")).getHcmoAnswerId();
		        	request.setAttribute("AnsId", sAnswerId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoQuestionBankId.hcmoQuestionId.question" title="${HQuestion}" sortable="true" headerClass="sortable"/>
		    <display:column property="answer" title="${HAnswer}" sortable="true" headerClass="sortable"/>
		    <s:set name="answerId" value="#request.AnsId"/>
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="getAnswers" action="getAnswers" escapeAmp="false">
						<s:param name="answerId" value="answerId"></s:param>
						<s:param name="answer.hcmoAnswerId" value="answerId"></s:param>
						<s:param name="hcmoQuestionGeneralInfoId" value="quesBankGeneralInfo.hcmoQuestionGeneralInfoId"/>
						<s:param name="questionGeneralInfoGroupIdListId" value="questionGeneralInfoGroupIdListId"/>
						<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"/>
						<s:param name="subEmployeeID" value="subEmployeeID"/>
					</s:url>
						<s:a href="#" onclick="doPostCall('display_tag_answersEmployeeList_div_id','%{getAnswers}','');"><s:text name="Edit"/></s:a>
				</display:column>
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="getAnswerView" action="getAnswerView" escapeAmp="false">
						<s:param name="answer.hcmoAnswerId" value="answerId"></s:param>
						<s:param name="hcmoQuestionGeneralInfoId" value="quesBankGeneralInfo.hcmoQuestionGeneralInfoId"/>
						<s:param name="questionGeneralInfoGroupIdListId" value="questionGeneralInfoGroupIdListId"/>
						<s:param name="questionGroupIdentifiId" value="questionGroupIdentifiId"/>
						<s:param name="subEmployeeID" value="subEmployeeID"/>
					</s:url>
						<s:a href="#" onclick="doPostCall('display_tag_answersEmployeeList_div_id','%{getAnswerView}','');"><s:text name="View"/></s:a>
				</display:column> 	
				
		  </display:table>
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_answersEmployeeList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  