<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.QuestionBankGeneralInfoVO"%>

<div id="submenu_admin_approving_employee_questionBank_list_div_id">
<br/>
<img id="indicatorAppEmpQuestionBankList_adminId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.category.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.questionGroup.name" var="HGroupName"></s:text>
	  <s:text name="label.header.employeeGroupName" var="HEmployeeGroupName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_adminapprovingEmployeeQuestionBankList_div_id">
		   <display:table class="tableborder" id="quesBankGeneralInfoListId" name="quesBankGeneralInfoList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmployeeQuestionBankGroup.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    	    String sQuesBankGeneralInfoListId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoQuestionGroupNameIdentificationId().getHcmoQuestionGroupNameIdentificationId().toString();
		        	request.setAttribute("QuesBankGeneralInfoListId", sQuesBankGeneralInfoListId);  
		        	String sQuestionGeneralInfoGroupIdListId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoQuestionGeneralInfoGroup().getHcmoQuestionGeneralInfoGroupId().toString();
		        	request.setAttribute("QuestionGeneralInfoGroupIdListId", sQuestionGeneralInfoGroupIdListId);  
		        	String sQuestionGeneralInfoId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoQuestionGeneralInfoId().toString();
		        	request.setAttribute("QuestionGeneralInfoGroupId", sQuestionGeneralInfoId);  
		        	String sEmployeeId = ((QuestionBankGeneralInfoVO)pageContext.getAttribute("quesBankGeneralInfoListId")).getHcmoEmployeeId().getEmployeeId().toString();
		        	request.setAttribute("QuestionGeneralInfoEmployeeId", sEmployeeId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		   
		    <display:column property="hcmoQuestionGroupNameIdentificationId.name" title="${HGroupName}" sortable="true" headerClass="sortable"/>
		    <display:column property="hcmoQuestionGeneralInfoGroup.name" title="${HEmployeeGroupName}" sortable="true" headerClass="sortable"/>
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="getEmployeeAnswerEntered" action="getEmployeeAnswerEntered" escapeAmp="false">
					<s:param name="subEmployeeID" value="#request.QuestionGeneralInfoEmployeeId"></s:param>
					<s:param name="questionGroupIdentifiId" value="#request.QuesBankGeneralInfoListId"></s:param>
				</s:url>
						<s:a href="#" onclick="doPostCall('submenu_admin_approving_employee_questionBank_list_div_id','%{getEmployeeAnswerEntered}','');"><s:text name="Edit"/></s:a>
		  </display:column>
		  </display:table>  
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_adminapprovingEmployeeQuestionBankList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  