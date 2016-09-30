<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.AnswerVO"%>

<div id="submenu_employeeAnswer_list_div_id">

<br/>
<img id="indicatorSubMenuNationality_empanswerlistId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
		<div id="EmployeeoptionalList">
			<img id="indicatorOptionalQuestionsAnswerListId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.optionalQuestions" var="HOptionalQuestions"></s:text>
		    <s:text name="label.header.question.questionType" var="HQuestionType"></s:text>
		    <s:text name="label.header.question.option1" var="HOption1"></s:text>
		    <s:text name="label.header.question.option2" var="HOption2"></s:text>
		    <s:text name="label.header.question.option3" var="HOption3"></s:text>
		    <s:text name="label.header.answer" var="HAnswer"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.options"/></span></li></div>
			
			<div id="display_tag_employeeAnswerList_div_id">
				<display:table class="tableborder" id="questionListId" name="answerOptionalList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending">
				   
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.question" title="${HOptionalQuestions}" sortable="true" headerClass="sortable"/>
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.questionType" title="${HQuestionType}" sortable="true" headerClass="sortable"/>
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.option1" title="${HOption1}" sortable="true" headerClass="sortable"/>
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.option2" title="${HOption2}" sortable="true" headerClass="sortable"/>
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.option3" title="${HOption3}" sortable="true" headerClass="sortable"/>
				    <display:column property="answer" title="${HAnswer}" sortable="true" headerClass="sortable"/>
				  </display:table>
			</div>
			
			<div id="EmployeeNumberingList">
				<img id="indicatorOptionalQuestionsAnswerListId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
				<s:text name="label.header.numberingQuestions" var="HNumberingQuestions"></s:text>
			    <s:text name="label.header.question.minRate" var="HMininmumRate"></s:text>
			    <s:text name="label.header.question.maxRate" var="HMaximumRate"></s:text>
			    <s:text name="label.header.answer" var="HAnswer"></s:text>
				<div class="informationMessageSingle"><li><span><s:text name="label.title.question.numbering"/></span></li></div>
				
				<display:table class="tableborder" id="questionListId" name="answerNumberingList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending">
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.question" title="${HNumberingQuestions}" sortable="true" headerClass="sortable"/>
		   			<display:column property="hcmoQuestionBankId.hcmoQuestionId.minRate" title="${HMininmumRate}" sortable="true" headerClass="sortable"/>
		    		<display:column property="hcmoQuestionBankId.hcmoQuestionId.maxRate" title="${HMaximumRate}" sortable="true" headerClass="sortable"/>
				    <display:column property="answer" title="${HAnswer}" sortable="true" headerClass="sortable"/>
				  </display:table>
				 </div>
			
			<div id="EmployeeSummarryList">
				<img id="indicatorOptionalQuestionsAnswerListId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
				<s:text name="label.header.summaryQuestions" var="HSummaryQuestions"></s:text>
				 <s:text name="label.header.answer" var="HAnswer"></s:text>
				<div class="informationMessageSingle"><li><span><s:text name="label.title.question.summarry"/></span></li></div>
				
				<display:table class="tableborder" id="questionListId" name="answerSummarryList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending">
				    <display:column property="hcmoQuestionBankId.hcmoQuestionId.question" title="${HSummaryQuestions}" sortable="true" headerClass="sortable"/>
				    <display:column property="answer" title="${HAnswer}" sortable="true" headerClass="sortable"/>
				  </display:table>
			</div>
			
			 </div>
	
	 
	      
</div>

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_employeeAnswerList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>