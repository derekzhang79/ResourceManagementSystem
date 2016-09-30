<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_broadcastResult_list_div_id">
<div class="submenu_bg">
		<sj:a href="showBroadcastReports.action" targets="report_broadcastResult_list_div_id" indicator="indicatorGenerateBroadcastReport" cssClass="link"><s:text name="MTIGenerateBroadcastReport" /></sj:a>
	</div>
	<br/>
		<img id="indicatorGenerateBroadcastReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.reports.broadcastReportList"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.message.sender" var="HSender"></s:text>
	  <s:text name="label.header.message.subject" var="HSubject"></s:text>
	  <s:text name="label.header.message.type" var="HType"></s:text>
	  <s:text name="label.header.message.message" var="HMessage"></s:text>
	  	
	  	<div id="display_tag_reportBroadcastList_div_id">
		  	<display:table class="tableborder" id="msgBroadcastListId" name="msgBroadcastList" pagesize="${NO_OF_RECORDS}" requestURI="getBroadcastReportPreview.action" defaultsort="1" defaultorder="ascending">
		    <display:column property="sender.empFullName" title="${HSender}" sortable="true"/>
		    <display:column property="subject" title="${HSubject}" sortable="true" maxLength="10"/>
		    <display:column property="type" title="${HType}" sortable="true"/>
		    <display:column property="message" title="${HMessage}" sortable="true" maxLength="10"/>
		   </display:table>
	  	</div>	   
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_reportBroadcastList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     