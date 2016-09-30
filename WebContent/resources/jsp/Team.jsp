<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TeamVO"%>

<div id="submenu_team_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.TEAM_ADD == true">
		<sj:a href="setUpTeam.action" targets="submenu_team_list_div_id" indicator="indicatorSubMenuTeamId_div" cssClass="link"><s:text name="MTIAddTeam" /></sj:a> |
	</s:if>
	<s:if test="#session.TEAM_VIEW == true">
		<sj:a href="getAllTeam.action" targets="submenu_team_list_div_id" indicator="indicatorSubMenuTeamId_div" cssClass="link"><s:text name="MTIViewTeam"/></sj:a> |
		<sj:a href="teamSearchForm.action" targets="submenu_team_list_div_id" indicator="indicatorSubMenuTeamId_div" cssClass="link"><s:text name="MTISearchTeam"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTeamId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.team.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.team.name" var="HTeamName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_teamList_div_id">
		  <display:table class="tableborder" id="teamListId" name="teamList" pagesize="${NO_OF_RECORDS}" requestURI="getAllTeam.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sTeamId = ((TeamVO)pageContext.getAttribute("teamListId")).getHcmoTeamId().toString();
		        	request.setAttribute("TeamId", sTeamId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="teamName" title="${HTeamName}" sortable="true" headerClass="sortable"/>
		    	<s:if test="#session.TEAM_VIEW==true">
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="teamView" action="teamView">
							<s:param name="team.hcmoTeamId" value="#request.TeamId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_team_list_div_id','%{teamView}','');"><s:text name="View"/></s:a>
					</display:column>
				</s:if>
				<s:if test="#session.TEAM_UPDATE==true">
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="setUpTeam" action="setUpTeam">
							<s:param name="team.hcmoTeamId" value="#request.TeamId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_team_list_div_id','%{setUpTeam}','');"><s:text name="Edit"/></s:a>
					</display:column>
				</s:if>
				<s:if test="#session.TEAM_DELETE==true">
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:url var="deleteTeam" action="deleteTeam">
							<s:param name="team.hcmoTeamId" value="#request.TeamId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_team_list_div_id','%{deleteTeam}','');"><s:text name="Delete"/></s:a>
					</display:column>
				</s:if>
				<display:setProperty name="export.csv.filename" value="Team.csv"/>
			 	<display:setProperty name="export.excel.filename" value="Team.xls"/>
			 	<display:setProperty name="export.xml.filename" value="Team.xml"/>
		  </display:table>  
	  </div>
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_team_list_div_id" cssClass="submitbutton117"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"></s:url>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_team_list_div_id" cssClass="button-midle"></sj:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
		    	        <s:url var="skip" action="skip"></s:url>
						<sj:submit href="%{skip}" value="Skip" cssClass="submitbutton117" targets="submenu_team_list_div_id"></sj:submit>
					</td>
				</tr>
		</table>
	</s:if>   
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_teamList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  