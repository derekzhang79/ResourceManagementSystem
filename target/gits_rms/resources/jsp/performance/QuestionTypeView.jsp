<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionVO"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<div id="submenu_questionBank_view_div_id">
<div class="submenu_bg">
		<sj:a href="getAllQuestionType.action" targets="submenu_questionBank_view_div_id" indicator="indicatorQuestionBankView" cssClass="link"><s:text name="MTIKPIGroup" /></sj:a> |
		<sj:a href="getAllQuestionGroupIdentificationName.action" targets="submenu_questionBank_view_div_id" indicator="indicatorQuestionBankView" cssClass="link"><s:text name="MTIViewKPIGroup" /></sj:a> 
</div>
<img id="indicatorQuestionBankView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form id="questionBankList">
	
	<sj:tabbedpanel id="questioTypeTabbedpannel" selectedTab="1">
		<sj:tab id="QuestionOptionalTypeTab" key="MTIOptionalQuestion" target="optionalList">
		<div id="optionalList">
			<img id="indicatorOptionalQuestionsTypeViewListId" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.optionalQuestions" var="HOptionalQuestions"></s:text>
		    <s:text name="label.header.question.questionType" var="HQuestionType"></s:text>
		    <s:text name="label.header.question.option1" var="HOption1"></s:text>
		    <s:text name="label.header.question.option2" var="HOption2"></s:text>
		    <s:text name="label.header.question.option3" var="HOption3"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
				<div id="display_tag_questionTypeViewList_divone_id">
					<display:table class="tableborder" id="questionListId" name="questionOptionalList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending" export="false">
				    <%
				    	try{
				    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
				        	request.setAttribute("QuestionId", sQuestionId);    		
				    	}catch(NullPointerException ne){
				        }    	
				    %>
				    <display:column property="question" title="${HOptionalQuestions}" sortable="false" />
				    <display:column property="questionType" title="${HQuestionType}" sortable="false" />
				    <display:column property="option1" title="${HOption1}" sortable="false" />
				    <display:column property="option2" title="${HOption2}" sortable="false" />
				    <display:column property="option3" title="${HOption3}" sortable="false" />
				    
				  </display:table>
				</div>
			
			</div>
		</sj:tab>
		
		<sj:tab id="QuestionNumberingTypeTab" key="MTINumberingQuestion" target="numberingList">
		<div id="numberingList">
			<img id="indicatorOptionalQuestionsTypeViewList_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.numberingQuestions" var="HNumberingQuestions"></s:text>
		    <s:text name="label.header.question.minRate" var="HMininmumRate"></s:text>
		    <s:text name="label.header.question.maxRate" var="HMaximumRate"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
			<div id="display_tag_questionTypeViewList_div_id">
			<display:table class="tableborder" id="questionListId" name="questionNumberingList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending" export="false">
			    <%
			    	try{
			    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
			        	request.setAttribute("QuestionId", sQuestionId);    		
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    <display:column property="question" title="${HNumberingQuestions}" sortable="false"/>
	   			<display:column property="minRate" title="${HMininmumRate}" sortable="false" />
	    		<display:column property="maxRate" title="${HMaximumRate}" sortable="false"/>
			    
			  </display:table>
			 </div>
		 </div>
		</sj:tab>
		
		<sj:tab id="QuestionSummaryTypeTab" key="MTISummaryQuestion" target="summaryList">
		<div id="summaryList">
			<img id="indicatorOptionalQuestionsTypeViewListId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.summaryQuestions" var="HSummaryQuestions"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
			<div id="display_tag_questionTypeViewList_divtwo_id">
			
			<display:table class="tableborder" id="questionListId" name="questionSummaryList" requestURI="getQuestionBamkList.action" defaultsort="1" defaultorder="ascending" export="false">
			    <%
			    	try{
			    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
			        	request.setAttribute("QuestionId", sQuestionId);    		
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    <display:column property="question" title="${HSummaryQuestions}" sortable="false"/>
			  </display:table>
			</div>
		 </div>
		</sj:tab>
	</sj:tabbedpanel>
	
	<s:hidden name="quesBank.hcmoQuestionBankId"/>
	<s:hidden name="quesGroupId.hcmoQuestionGroupNameIdentificationId"/>
  </s:form>
 </div> 
 <script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_questionTypeViewList_div_id").displayTagAjax();
   		    jQuery("#display_tag_questionTypeViewList_divone_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  