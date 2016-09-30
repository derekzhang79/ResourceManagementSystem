<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.MailConfigurationVO"%>

<div id="submenu_mailConfig_list_div_id">
	<div class="submenu_bg">
		<%--<s:if test="#session.NATIONALITY_ADD == true">--%>
			<sj:a href="setUpMailConfig.action" targets="submenu_mailConfig_list_div_id" indicator="indicatorSubMenuMailConfig" cssClass="link"><s:text name="MTIAddMailConfiguration" /></sj:a> |
		<%--</s:if>
		<s:if test="#session.NATIONALITY_VIEW == true">--%>
			<sj:a href="getAllMailConfig.action" targets="submenu_mailConfig_list_div_id" indicator="indicatorSubMenuMailConfig" cssClass="link"><s:text name="MTIViewMailConfiguration"/></sj:a> 
		<%--</s:if>--%>
	</div>
	<br/>
	<img id="indicatorSubMenuMailConfig" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<jsp:include page="common/messages.jsp" flush="true"/>
	
		<div class="informationMessageSingle"><li><span><s:text name="label.title.mailConfig.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		  <s:text name="label.header.mailConfig.smtpHost" var="HSmtpHost"></s:text>
		  <s:text name="label.header.mailConfig.username" var="HUserName"></s:text>
		  
		  <s:text name="label.common.link.view" var="HView"></s:text>
		  <s:text name="label.common.link.edit" var="HEdit"></s:text>
		  		   
		  <div id="display_tag_mailConfigurationList_div_id">
		  <display:table class="tableborder" id="mailConfigListId" name="mailConfigList" pagesize="${NO_OF_RECORDS}" requestURI="getAllMailConfig.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <%
		    	try{
		    		String sMailConfigId = ((MailConfigurationVO)pageContext.getAttribute("mailConfigListId")).getHcmoMailConfigurationId().toString();
		        	request.setAttribute("MailConfigId", sMailConfigId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="smtpHost" title="${HSmtpHost}" sortable="true" headerClass="sortable"/>
		    <display:column property="username" title="${HUserName}" sortable="true" headerClass="sortable"/>
	
			<display:column title="${HEdit}" class="viewedit">
					<s:url var="setUpMailConfig" action="setUpMailConfig">
					<s:param name="mailConfig.hcmoMailConfigurationId" value="#request.MailConfigId"></s:param>
				</s:url>
				<s:a href="#" onclick="doPostCall('submenu_mailConfig_list_div_id','%{setUpMailConfig}','');"><s:text name="Edit"/></s:a>
			</display:column>	 
			<display:column title="${HView}" class="viewedit">
				<s:url var="mailConfigView" action="mailConfigView">
					<s:param name="mailConfig.hcmoMailConfigurationId" value="#request.MailConfigId"></s:param>
				</s:url>
				<s:a href="#" onclick="doPostCall('submenu_mailConfig_list_div_id','%{mailConfigView}','');"><s:text name="View"/></s:a>
			</display:column>    
		  </display:table> 
	  </div>
	  
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_mailConfig_list_div_id" indicator="indicatorMailConfigurationId_div" cssClass="submitbutton117"/>
						<img id="indicatorMailConfigurationId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					</td>
				</tr>
		</table>
	</s:if>   
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_mailConfigurationList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  