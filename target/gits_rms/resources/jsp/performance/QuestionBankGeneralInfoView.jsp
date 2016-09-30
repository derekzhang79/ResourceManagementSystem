<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionBankGeneralInfoVO"%>

<div id="submenu_QuestionBank_view_listId_div">
<div class="submenu_bg">
	<sj:a href="setUpQuestBankGeneralInfoForm.action" targets="submenu_QuestionBank_view_listId_div" indicator="indicatorQuestionBankGenaralIndoViewId_div" cssClass="link"><s:text name="MTIAssignedEmployeeQuestions" /></sj:a> |
	<sj:a href="viewAssignedQuestionsList.action" targets="submenu_QuestionBank_view_listId_div" indicator="indicatorQuestionBankGenaralIndoViewId_div" cssClass="link"><s:text name="MTIViewAssignedKPIGroup"/></sj:a> 
</div>
<br/>
<img id="indicatorQuestionBankGenaralInfoViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.header.assigned.questionBanks"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.questionGroup.name" var="HGroupName"></s:text>
	  <s:text name="label.header.questionBankGeneralInfo.employeeId" var="HEmployeeName"></s:text>
	  <s:text name="label.header.employee.empType" var="HEmployeeType"></s:text>
	  <s:text name="label.header.questionBankGeneralInfo.peerEmployeeId" var="HPeerEmployeeName"></s:text>
	  
	  <div id="display_tag_questionBankGeneralList_div_id">
		  <display:table class="tableborder" id="quesBankGeneralInfoListId" name="quesBankGeneralInfoList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmployeeQuestionBankGroup.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		        	String sQuestionGeneralInfoGroupIdListId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId().toString();
		        	request.setAttribute("QuestionGeneralInfoGroupIdListId", sQuestionGeneralInfoGroupIdListId);  
		        	String sQuestionGeneralInfoId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoQuestionGeneralInfoId().toString();
		        	request.setAttribute("QuestionGeneralInfoGroupId", sQuestionGeneralInfoId);  
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoQuestionGroupNameIdentificationId.name" title="${HGroupName}" sortable="true" headerClass="sortable"/>
		    <display:column property="hcmoEmployeeId.empFirstName" title="${HEmployeeName}" sortable="true" headerClass="sortable"/>
		    <display:column property="employeeType" title="${HEmployeeType}" sortable="true" headerClass="sortable"/>
		    <display:column property="peerEmployeeId.empFirstName" title="${HPeerEmployeeName}" sortable="true" headerClass="sortable"/>
		  </display:table>    
	  </div>		   
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_questionBankGeneralList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>