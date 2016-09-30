<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionGroupNameIdentificationVO"%>

<div id="submenu_questionBank_List_View_div_id">
	<div class="submenu_bg">
		<sj:a href="getAllQuestionType.action" targets="submenu_questionBank_List_View_div_id" indicator="indicatorQuestionBankListView" cssClass="link"><s:text name="MTIKPIGroup" /></sj:a> |
		<sj:a href="getAllQuestionGroupIdentificationName.action" targets="submenu_questionBank_List_View_div_id" indicator="indicatorQuestionBankListView" cssClass="link"><s:text name="MTIViewKPIGroup" /></sj:a>
	</div><br/>
	<img id="indicatorQuestionBankListView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.category.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.questionGroup.name" var="HGroupName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	 <div id="display_QuestionBankListview_div_id">
		  <display:table class="tableborder" id="questGroupListId" name="questGroupList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmployeeQuestionBankGroup.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sQuesGroupNameIdentifitId = ((QuestionGroupNameIdentificationVO)pageContext.getAttribute("questGroupListId")).getHcmoQuestionGroupNameIdentificationId().toString();
		        	request.setAttribute("QuestionGroupIdentifiId", sQuesGroupNameIdentifitId); 
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		   
		    <display:column property="name" title="${HGroupName}" sortable="true" headerClass="sortable"/>
		    
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="getAllQuestionView" action="getAllQuestionGroupView" escapeAmp="false">
					<s:param name="quesGroupNameIdentificationId" value="#request.QuestionGroupIdentifiId"/>
				</s:url>
						<s:a href="#" onclick="doPostCall('submenu_questionBank_List_View_div_id','%{getAllQuestionView}','');"><s:text name="View"/></s:a>
			</display:column>
		  </display:table>
	 </div>
	 
	    
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_QuestionBankListview_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  