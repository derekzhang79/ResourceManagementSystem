<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionVO"%>

   
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<div id="submenu_QuestionBank_div">
<div class="submenu_bg">
		<sj:a href="getAllQuestionType.action" targets="submenu_QuestionBank_div" indicator="indicatorAddQuestionBank" cssClass="link"><s:text name="MTIKPIGroup" /></sj:a> |
		<sj:a href="getAllQuestionGroupIdentificationName.action" targets="submenu_QuestionBank_div" indicator="indicatorAddQuestionBank" cssClass="link"><s:text name="MTIViewKPIGroup" /></sj:a> 
</div>
<img id="indicatorAddQuestionBank" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form id="questionBankList" action="insertQuestionBank">
	<table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         	<tr>
	            	<td><table class="employeeformiinertable">
	         		<tr>
	                	<td class="formtitle">
							 <s:text name="label.header.questionBank"/>
	              	    </td>
	         		</tr>
			     	 <tr>
			               <td class="forminner"><table class="tablealign"> 
			     	 
			         <tr><td class="inputtext"><s:text name="label.form.fields.questionBank.groupName"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="quesGroupId.name" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="quesGroupId.name" />
				 		</td>
	         		</tr>
	            	<s:hidden name="quesBank.hcmoQuestionBankId"/>
	            	<s:hidden name="quesGroupId.hcmoQuestionGroupNameIdentificationId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	
	<sj:tabbedpanel id="questioTypeTabbedpannel" selectedTab="1">
		<sj:tab id="QuestionOptionalTypeTab" key="MTIOptionalQuestion" target="questiontypetaboptionalList">
			<div id="questiontypetaboptionalList">
				<img id="indicatorOptionalQuestionsListTab_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
				
				<s:text name="label.header.category" var="HCategory"></s:text>
		  		<s:text name="label.header.subCategory" var="HSubCategory"></s:text>
				<s:text name="label.header.optionalQuestions" var="HOptionalQuestions"></s:text>
			    <s:text name="label.header.question.questionType" var="HQuestionType"></s:text>
			    <s:text name="label.header.question.option1" var="HOption1"></s:text>
			    <s:text name="label.header.question.option2" var="HOption2"></s:text>
			    <s:text name="label.header.question.option3" var="HOption3"></s:text>
				<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
				<display:table class="tableborder" id="questionListId" name="questionOptionalList" defaultsort="1" defaultorder="ascending" export="false">
				    <%
				    	try{
				    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
				        	request.setAttribute("QuestionId", sQuestionId);    		
				    	}catch(NullPointerException ne){
				        }    	
				    %>
				    <display:column property="hcmoCategoryId.categoryName" title="${HCategory}" sortable="false" headerClass="sortable"/>
			    	<display:column property="hcmoSubCategoryId.subCategoryName" title="${HSubCategory}" sortable="false" headerClass="sortable"/>
				    <display:column property="question" title="${HOptionalQuestions}" sortable="false" />
				    <display:column property="questionType" title="${HQuestionType}" sortable="false" />
				    <display:column property="option1" title="${HOption1}" sortable="false" />
				    <display:column property="option2" title="${HOption2}" sortable="false" />
				    <display:column property="option3" title="${HOption3}" sortable="false" />
				    
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:set name="question.hcmoQuestionId" value="%{#request.QuestionId}"></s:set>
						<s:checkbox name="cbList" id="cbList" fieldValue="%{#request.QuestionId}"></s:checkbox>
					</display:column>
				  </display:table>
			</div>
		</sj:tab>
		
		<sj:tab id="QuestionNumberingTypeTab" key="MTINumberingQuestion" target="questiontypetabnumberingList">
		<div id="questiontypetabnumberingList">
			<img id="indicatorOptionalQuestionsListTabId" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.numberingQuestions" var="HNumberingQuestions"></s:text>
		    <s:text name="label.header.question.minRate" var="HMininmumRate"></s:text>
		    <s:text name="label.header.question.maxRate" var="HMaximumRate"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
			<display:table class="tableborder" id="questionListId" name="questionNumberingList" defaultsort="1" defaultorder="ascending" export="false">
			    <%
			    	try{
			    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
			        	request.setAttribute("QuestionId", sQuestionId);    		
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    
			    <display:column property="hcmoCategoryId.categoryName" title="${HCategory}" sortable="false" headerClass="sortable"/>
			    <display:column property="hcmoSubCategoryId.subCategoryName" title="${HSubCategory}" sortable="false" headerClass="sortable"/>
			    <display:column property="question" title="${HNumberingQuestions}" sortable="false"/>
	   			<display:column property="minRate" title="${HMininmumRate}" sortable="false" />
	    		<display:column property="maxRate" title="${HMaximumRate}" sortable="false"/>
			    
				<display:column title="${HDelete}" class="viewedit" media="html">
				<s:set name="question.hcmoQuestionId" value="%{#request.QuestionId}"></s:set>
				<s:checkbox name="cbList" id="cbList" fieldValue="%{#request.QuestionId}"></s:checkbox>
				</display:column>
			  </display:table>
			 </div>
		</sj:tab>
		
		<sj:tab id="QuestionSummaryTypeTab" key="MTISummaryQuestion" target="questiontypetabsummaryList">
		<div id="questiontypetabsummaryList">
			<img id="indicatorOptionalQuestionsListTabId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
			<s:text name="label.header.summaryQuestions" var="HSummaryQuestions"></s:text>
			<div class="informationMessageSingle"><li><span><s:text name="label.title.question.list"/></span></li></div>
			
			<display:table class="tableborder" id="questionListId" name="questionSummaryList" defaultsort="1" defaultorder="ascending" export="false">
			    <%
			    	try{
			    		String sQuestionId = ((QuestionVO)pageContext.getAttribute("questionListId")).getHcmoQuestionId().toString();
			        	request.setAttribute("QuestionId", sQuestionId);    		
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    
			    <display:column property="hcmoCategoryId.categoryName" title="${HCategory}" sortable="false" headerClass="sortable"/>
			    <display:column property="hcmoSubCategoryId.subCategoryName" title="${HSubCategory}" sortable="false" headerClass="sortable"/>
			    <display:column property="question" title="${HSummaryQuestions}" sortable="false"/>
				<display:column title="${HDelete}" class="viewedit" media="html">
				<s:set name="question.hcmoQuestionId" value="%{#request.QuestionId}"></s:set>
				<s:checkbox name="cbList" id="cbList" fieldValue="%{#request.QuestionId}"></s:checkbox>
				</display:column>
			  </display:table>
			 </div>
		</sj:tab>
	</sj:tabbedpanel>
	<table align="center">
		<tr>
			<td>
				<img id="indicatorQuestionBankForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<sj:submit name="submit" formIds="questionBankList" cssClass="submitbutton117" targets="submenu_QuestionBank_div"></sj:submit>
				
			</td>
		</tr>
	 </table> 
  </s:form>
 </div> 
 
  <script type="text/javascript">
    	function test(){
        	var jCount=document.getElementById("cbList");
        	var JobSelected  = 0;
        	for(var j=0;j<jCount.length;j++){
    			if(jCount[j].checked==true){
    				JobSelected = JobSelected + 1;
    			}else if(jCount[j].checked=false){
    				JobSelected = JobSelected - 1;
    			}
    		}	
        	if(JobSelected<1){
    			alert("Please select atleast one Question to Group.");
    			return false;
    		}
        	};
    </script>