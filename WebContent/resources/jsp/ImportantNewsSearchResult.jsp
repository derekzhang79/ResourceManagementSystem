<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.ImportantNewsVO"%>

<div id="submenu_importantNews_searchList_div_id">
<div class="submenu_bg">
		<sj:a href="setUpImportantNews.action" targets="submenu_importantNews_searchList_div_id" indicator="indicatorSubMenuImportantNewsSearchResId_div" cssClass="link"><s:text name="MTIAddImportantNews" /></sj:a> |
		<sj:a href="getAllImportantNews.action" targets="submenu_importantNews_searchList_div_id" indicator="indicatorSubMenuImportantNewsSearchResId_div" cssClass="link"><s:text name="MTIViewImportantNews"/></sj:a> |
		<sj:a href="importantNewsSearchForm.action" targets="submenu_importantNews_searchList_div_id" indicator="indicatorSubMenuImportantNewsSearchResId_div" cssClass="link"><s:text name="MTISearchImportantNews"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuImportantNewsSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.importantNews.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.message.subject" var="HSubject"></s:text>
	  <s:text name="label.header.message.message" var="HMessage"></s:text>
	  <s:text name="label.header.importantNews.showMessage" var="HShowMessage"></s:text>
	  <s:text name="label.header.importantNews.makeChangesToShowMessage" var="HMakeChangesToShowMessage"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_importantNewsSearchList_div_id">
		   <display:table class="tableborder" id="importantNewsId" name="impList" pagesize="${NO_OF_RECORDS}" requestURI="importantNewsSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <%
		    	try{
		    		String sImportantNewsId = ((ImportantNewsVO)pageContext.getAttribute("importantNewsId")).getImportantNewsId().toString();
		        	request.setAttribute("ImportantNeswId", sImportantNewsId);	
		        	Boolean sImpNewsShowMessageId = ((ImportantNewsVO)pageContext.getAttribute("importantNewsId")).isShowMessage();
		        	request.setAttribute("ImpNewsShowMessageId", sImpNewsShowMessageId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="subject" title="${HSubject}" sortable="true" headerClass="sortable"/>
		    <display:column property="message" title="${HMessage}" sortable="true" headerClass="sortable"/>
		    <display:column title="${HShowMessage}" style="text-align:center">
	                <s:checkbox id="check" name="#request.ImpNewsShowMessageId" disabled="true"/>
	       	</display:column>
	       	
	       	        <display:column title="${HMakeChangesToShowMessage}">
						<s:if test="#request.ImpNewsShowMessageId==true">
						<s:text name="Currently Showing"/>
						</s:if>
						<s:else>
						<s:url var="listMakeChangesToShowMessage" action="makeChangesToShowMessage">
							<s:param name="important.importantNewsId" value="#request.ImportantNeswId"></s:param>
						</s:url>
						<sj:a href="%{listMakeChangesToShowMessage}" title="Make Change To Show Message" targets="submenu_importantNews_searchList_div_id" indicator="indicatorSubMenuImportantNewsSearchResId_div"><s:text name="Show Message"/></sj:a>
						</s:else>
					</display:column>   
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="listViewImportantNews" action="importantNewsView">
							<s:param name="important.importantNewsId" value="#request.ImportantNeswId"></s:param>
						</s:url>
					<s:a href="#" onclick="doPostCall('submenu_importantNews_searchList_div_id','%{listViewImportantNews}','');"><s:text name="View"/></s:a>
					</display:column>
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="listSetUpImportantNews" action="setUpImportantNews">
							<s:param name="important.importantNewsId" value="#request.ImportantNeswId"></s:param>
						</s:url>
					<s:a href="#" onclick="doPostCall('submenu_importantNews_searchList_div_id','%{listSetUpImportantNews}','');"><s:text name="Edit"/></s:a>
					</display:column>
				
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:url var="listDeleteImportantNews" action="deleteImportantNews">
							<s:param name="important.importantNewsId" value="#request.ImportantNeswId"></s:param>
						</s:url>
					<s:a href="#" onclick="doPostCall('submenu_importantNews_searchList_div_id','%{listDeleteImportantNews}','');"><s:text name="Delete"/></s:a>
					</display:column>
		  </display:table>   
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_importantNewsSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   