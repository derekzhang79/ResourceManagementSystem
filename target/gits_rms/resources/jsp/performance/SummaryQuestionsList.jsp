<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionVO"%>

<div id="submenu_summaryQuestions_list_div_id">
	<div class="submenu_bg">
		<sj:a href="setUpSummaryQuestions.action" targets="submenu_summaryQuestions_list_div_id" indicator="indicatorSummaryQuestionsList" cssClass="link"><s:text name="MTIAddQuestion" /></sj:a> |
		<sj:a href="getAllSummaryQuestions.action" targets="submenu_summaryQuestions_list_div_id" indicator="indicatorSummaryQuestionsList" cssClass="link"><s:text name="MTIViewQuestion"/></sj:a>
	</div><br/>
	<img id="indicatorSummaryQuestionsList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		
		<s:text name="label.header.category" var="HCategory"></s:text>
	    <s:text name="label.header.subCategory" var="HSubCategory"></s:text>
	  	<s:text name="label.header.summaryQuestions" var="HSummaryQuestions"></s:text>
	  	<s:text name="label.common.link.view" var="HView"></s:text>
	  		   
	  <div id="display_tag_summaryQuestionsList_div_id">
		  <display:table class="tableborder" id="questionListId" name="questionList" pagesize="${NO_OF_RECORDS}" requestURI="getAllSummaryQuestions.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
		        	request.setAttribute("QuestionId", sQuestionId);    		
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    
		    <display:column property="hcmoCategoryId.categoryName" title="${HCategory}" sortable="true" headerClass="sortable"/>
		    <display:column property="hcmoSubCategoryId.subCategoryName" title="${HSubCategory}" sortable="true" headerClass="sortable"/>
		    <display:column property="question" title="${HSummaryQuestions}" sortable="true" headerClass="sortable"/>
		    
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewSummaryQuestions" action="getQuestionView">
					<s:param name="question.hcmoQuestionId" value="#request.QuestionId"></s:param>
				</s:url>
						<s:a href="#" onclick="doPostCall('submenu_summaryQuestions_list_div_id','%{listViewSummaryQuestions}','');"><s:text name="View"/></s:a>
			</display:column>
			
			<display:setProperty name="export.csv.filename" value="SummaryQuestionsList.csv"/>
			<display:setProperty name="export.excel.filename" value="SummaryQuestionsList.xls"/>
			<display:setProperty name="export.xml.filename" value="SummaryQuestionsList.xml"/>
		  </display:table>    
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_summaryQuestionsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  