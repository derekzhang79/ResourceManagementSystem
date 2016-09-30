<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.NationalityVO"%>

<div id="submenu_nationality_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.NATIONALITY_ADD == true">
			<sj:a href="setUpNationality.action" targets="submenu_nationality_list_div_id" indicator="indicatorSubMenuNationalityId_div" cssClass="link"><s:text name="MTIAddNationality" /></sj:a> |
		</s:if>
		<s:if test="#session.NATIONALITY_VIEW == true">
			<sj:a href="getAllNationality.action" targets="submenu_nationality_list_div_id" indicator="indicatorSubMenuNationalityId_div" cssClass="link"><s:text name="MTIViewNationality"/></sj:a> |
			<sj:a href="nationalitySearchForm.action" targets="submenu_nationality_list_div_id" indicator="indicatorSubMenuNationalityId_div" cssClass="link"><s:text name="MTISearchNationality"/></sj:a>
		</s:if>
	</div><br/>
	
	<img id="indicatorSubMenuNationalityId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.nationality.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.header.nationality.name" var="HNationalityName"></s:text>
	  	<s:text name="label.common.link.view" var="HView"></s:text>
	  	<s:text name="label.common.link.edit" var="HEdit"></s:text>
	  	<s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  	<div id="display_tag_nationalityList_div_id">
			<display:table class="tableborder" id="nationalityListId" name="natiList" pagesize="${NO_OF_RECORDS}" requestURI="getAllNationality.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
			    <%
			    	try{
			    		String sNationalityId = ((NationalityVO)pageContext.getAttribute("nationalityListId")).getNationalityId().toString();
			        	request.setAttribute("NationalityId", sNationalityId);	
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    
		    	<display:column property="nationalityName" title="${HNationalityName}" sortable="true" headerClass="sortable"/>
			    <s:if test="#session.NATIONALITY_VIEW==true">
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="listViewNationality" action="nationalityView">
							<s:param name="nati.nationalityId" value="#request.NationalityId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_nationality_list_div_id','%{listViewNationality}','');"><s:text name="View"/></s:a>
					</display:column>
				</s:if>
			    <s:if test="#session.NATIONALITY_UPDATE==true">
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="listSetUpNationality" action="setUpNationality">
							<s:param name="nati.nationalityId" value="#request.NationalityId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_nationality_list_div_id','%{listSetUpNationality}','');"><s:text name="Edit"/></s:a>
					</display:column>
				</s:if>
			    <s:if test="#session.NATIONALITY_DELETE==true">
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:url var="listDeleteNationality" action="deleteNationality">
							<s:param name="nati.nationalityId" value="#request.NationalityId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_nationality_list_div_id','%{listDeleteNationality}','');"><s:text name="Delete"/></s:a>
					</display:column>
				</s:if>
			 
				 <display:setProperty name="export.csv.filename" value="Nationality.csv"/>
				 <display:setProperty name="export.excel.filename" value="Nationality.xls"/>
				 <display:setProperty name="export.xml.filename" value="Nationality.xml"/>
				
			</display:table>
		</div>
	  	<s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
			<table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_nationality_list_div_id" cssClass="submitbutton117"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"></s:url>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_nationality_list_div_id" cssClass="button-midle"></sj:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:url var="skip" action="skip"></s:url>
						<sj:submit href="%{skip}" value="Skip" cssClass="submitbutton117" targets="submenu_nationality_list_div_id"></sj:submit>
					</td>
				</tr>
		</table>
	</s:if> 
</div>

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_nationalityList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  