<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.MessageVO"%>

<div id="submenu_messageSentItem_list_div_id">
<br/>
<img id="indicatorSubMenuMessageSentItemId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.message.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.message.subject" var="HMessageSubject"></s:text>
	  <s:text name="label.header.message.message" var="HMessageMessage"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_messageSentItemList_div_id">
		  <display:table class="tableborder" id="messageListId" name="messageList" pagesize="${NO_OF_RECORDS}" requestURI="getAllMessage.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <%
			    try{
		    		String sMessageId = ((MessageVO)pageContext.getAttribute("messageListId")).getHcmoMessageId().toString();
		        	request.setAttribute("MessageId", sMessageId);
		        	int isReadedAlert = ((MessageVO)pageContext.getAttribute("messageListId")).getIsRead();
		        	request.setAttribute("MessagingisReadedAleart", isReadedAlert);	
		    	}catch(NullPointerException ne){
			        }    	
		    %>
		    <display:column property="subject" title="${HMessageSubject}" sortable="true" headerClass="sortable"/>
		    <display:column property="message" title="${HMessageMessage}" sortable="true" headerClass="sortable"/>
		    
		    <display:column title="${HView}" class="viewedit">
					<s:if test="#request.MessagingisReadedAleart==0">
					<s:url var="listViewMessage" action="messageView">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_messageSentItem_list_div_id','%{listViewMessage}','');"><s:text name="View"/></s:a>
				</s:if>
				<s:elseif test="#request.MessagingisReadedAleart==1">
					<s:url var="markAsUnread" action="markAsUnread">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_messageSentItem_list_div_id','%{markAsUnread}','');"><s:text name="Mark as UnRead"/></s:a>
				
				</s:elseif>
			</display:column>
		    
		    <%-- <display:column title="${HDelete}" class="viewedit">
					<s:url var="listDeleteMessage" action="deleteMessage">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_messageSentItem_list_div_id','%{listDeleteMessage}','');"><s:text name="Delete"/></s:a>
		    </display:column>
		   <s:if test="#session.HOLIDAY_VIEW==true">
				<display:column title="${HView}" class="viewedit">
					<s:url var="listViewMessage" action="messageView">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<sj:a href="%{listViewMessage}" targets="submenu_messageSentItem_list_div_id" indicator="indicatorSubMenuMessageSentItemId_div"><s:text name="View"/></sj:a>
				</display:column>
			</s:if>
		    <s:if test="#session.HOLIDAY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit">
					<s:url var="listSetUpMessage" action="setUpMessage">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<sj:a href="%{listSetUpMessage}" targets="submenu_messageSentItem_list_div_id" indicator="indicatorSubMenuMessageSentItemId_div"><s:text name="Edit"/></sj:a>
				</display:column>
			</s:if>
		    <s:if test="#session.HOLIDAY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit">
					<s:url var="listDeleteMessage" action="deleteMessage">
						<s:param name="message.hcmoMessageId" value="#request.MessageId"></s:param>
					</s:url>
					<sj:a href="%{listDeleteMessage}" targets="submenu_messageSentItem_list_div_id" indicator="indicatorSubMenuMessageSentItemId_div"><s:text name="Delete"/></sj:a>
				</display:column>
			</s:if>--%>
		  </display:table>
	  </div>
	  
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_messageSentItemList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    