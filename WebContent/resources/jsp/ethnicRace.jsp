<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EthnicRaceVO"%>

<div id="submenu_ethnicRace_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.ETHNICRACE_ADD == true">
		<sj:a href="setUpEthnicRace.action" targets="submenu_ethnicRace_list_div_id" indicator="indicatorSubMenuEthnicRaceId_div" cssClass="link"><s:text name="MTIAddEthnicRace" /></sj:a> |
	</s:if>
	<s:if test="#session.ETHNICRACE_VIEW == true">
		<sj:a href="getAllEthnicRace.action" targets="submenu_ethnicRace_list_div_id" indicator="indicatorSubMenuEthnicRaceId_div" cssClass="link"><s:text name="MTIViewEthnicRace"/></sj:a> |
		<sj:a href="ethnicRaceSearchForm.action" targets="submenu_ethnicRace_list_div_id" indicator="indicatorSubMenuEthnicRaceId_div" cssClass="link"><s:text name="MTISearchEthnicRace"/></sj:a>
	</s:if>
		
</div>
<br/>
<img id="indicatorSubMenuEthnicRaceId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.ethnicRace.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.ethnicRace.name" var="HEthnicRace"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="disply_tag_ethnicraceList_div_id">
	  	<display:table class="tableborder" id="ethnicRaceListId" name="ethnicRaceList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEthnicRace.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sEthnicRaceId = ((EthnicRaceVO)pageContext.getAttribute("ethnicRaceListId")).getEthnicRaceId().toString();
		        	request.setAttribute("EthnicRaceId", sEthnicRaceId);	
		    	}catch(NullPointerException ne){
		        }
		    	
		    %>
		    <display:column property="ethnicRaceDesc" title="${HEthnicRace}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.ETHNICRACE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEthnicRace" action="ethnicRaceView">
						<s:param name="ethRace.ethnicRaceId" value="#request.EthnicRaceId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_ethnicRace_list_div_id','%{listViewEthnicRace}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.ETHNICRACE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEthnicRace" action="setUpEthnicRace">
						<s:param name="ethRace.ethnicRaceId" value="#request.EthnicRaceId"></s:param>
					</s:url>
				
				<s:a href="#" onclick="doPostCall('submenu_ethnicRace_list_div_id','%{listSetUpEthnicRace}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.ETHNICRACE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEthnicRace" action="deleteEthnicRace">
						<s:param name="ethRace.ethnicRaceId" value="#request.EthnicRaceId"></s:param>
					</s:url>
				<s:a href="#" onclick="doPostCall('submenu_ethnicRace_list_div_id','%{listDeleteEthnicRace}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="EthnicRace.csv"/>
			 <display:setProperty name="export.excel.filename" value="EthnicRace.xls"/>
			 <display:setProperty name="export.xml.filename" value="EthnicRace.xml"/>
	  	</display:table>
	  </div>	   
	
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_ethnicRace_list_div_id" cssClass="submitbutton117"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"></s:url>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_ethnicRace_list_div_id" cssClass="button-midle"></sj:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:url var="skip" action="skip"></s:url>
						<sj:submit href="%{skip}" value="Skip" cssClass="submitbutton117" targets="submenu_ethnicRace_list_div_id"></sj:submit>
					</td>
				</tr>
		</table>
	</s:if> 
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#disply_tag_ethnicraceList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>