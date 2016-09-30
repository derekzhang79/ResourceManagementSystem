<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SummaryQuestionFormId_div">
	<div class="submenu_bg">
		<sj:a href="setUpSummaryQuestions.action" targets="submenu_SummaryQuestionFormId_div" indicator="indicatorSummaryQuestionsFormId_div" cssClass="link"><s:text name="MTIAddQuestion" /></sj:a> |
		<sj:a href="getAllSummaryQuestions.action" targets="submenu_SummaryQuestionFormId_div" indicator="indicatorSummaryQuestionsFormId_div" cssClass="link"><s:text name="MTIViewQuestion"/></sj:a> |
	</div>
	<img id="indicatorSummaryQuestionsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateSummaryQuestion">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="question==null || question.hcmoQuestionId == null">
							<s:text name="label.title.question.add" />
						</s:if> 
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                  <tr>
						<td class="inputtext"><s:text name="label.form.fields.category.name"/></td>
				     	     <td class="employeeinputtd">
								<s:select 
									headerKey=""
									headerValue="-- Please Select --"
									list="#request.APPL_CATEGORY_LIST"
									listKey="hcmoCategoryId"
									listValue="categoryName"
								    name="question.hcmoCategoryId.hcmoCategoryId"
								    cssClass="employeeselect" 
								    onchange="getSubCategory(this);"
								    />
							</td>
							<td class="inputerrormessage"><s:fielderror fieldName="question.hcmoCategoryId.hcmoCategoryId" /></td>
					</tr>	
					<tr>
			        	<td class="inputtext"><s:text name="label.form.fields.subCategory.name"/></td>
	     	 			<td>
	     	 				<div id="resultSubCategorySummaryQuestionDiv">
			     	 		<s:select 
								headerKey=""
								headerValue="-- Please Select --"
								list="#request.APPL_SUB_CATEGORY_LIST"
								listKey="hcmoSubCategoryId"
								listValue="subCategoryName"
								name="question.hcmoSubCategoryId.hcmoSubCategoryId"
								cssClass="employeeselect" 
							    />
			     	 		</div>
			     	 	</td>
			     	 	<td class="inputerrormessage"><s:fielderror fieldName="question.hcmoSubCategoryId.hcmoSubCategoryId" /></td>
	     	 		</tr>
	                  <tr>
			        	<td class="inputtext"><s:text name="label.form.fields.question"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="question.question" cssClass="employeeinput"/></td>
	         	       <td class="inputerrormessage"><s:fielderror fieldName="question.question" /></td>
					</tr>
			<s:hidden name="question.questionType" value="summary"/>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSummaryQuestionForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SummaryQuestionFormId_div" indicator="indicatorSummaryQuestionForm"/>
				</td>
				<s:if test="question==null || question.hcmoQuestionId==null">
				   <td>
		    		    <s:url var="resetSummaryQuestion" action="resetSummaryQuestion"></s:url>
		    	        <sj:submit href="%{resetSummaryQuestion}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_SummaryQuestionFormId_div" indicator="indicatorSummaryQuestionsFormId_div"/>
			       </td>
			   </s:if>
			   <s:else>
				    <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			  </s:else>
			</tr>
		</table>
		<s:hidden name="question.hcmoQuestionId"/>
	</s:form>
</div>
<script type="text/javascript">
    function getSubCategory(hcmoCategoryId){ 
        var url = 'getAllSubCategoryName.action';
        jQuery.ajax({
             type: "POST",
             dataType: "html",
             url: url,
             data: "hcmoCategoryId="+hcmoCategoryId.value,
             success: function(data){       
             jQuery("#resultSubCategorySummaryQuestionDiv").html(data);
         }});
    }
</script>