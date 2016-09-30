<%@ page contentType="text/html; charset=UTF-8" session="true" autoFlush="true" buffer="16kb"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.CountryVO"%>
<%@page import="org.apache.poi.hssf.*"%>

<div id="submenu_Country_searchlist_div_id">
<div class="submenu_bg">
	<s:if test="#session.COUNTRY_ADD == true">
		<sj:a href="setUpInsertOrUpdateCountry.action" targets="submenu_Country_searchlist_div_id" indicator="indicatorSubMenuCountrySearchResultId_div" cssClass="link"><s:text name="MTIAddCountry" /></sj:a> |
	</s:if>
	<s:if test="#session.COUNTRY_VIEW == true">
		<sj:a href="getAllCountry.action" targets="submenu_Country_searchlist_div_id" indicator="indicatorSubMenuCountrySearchResultId_div" cssClass="link"><s:text name="MTIViewCountry"/></sj:a> |
		<sj:a href="countrySearchForm.action" targets="submenu_Country_searchlist_div_id" indicator="indicatorSubMenuCountrySearchResultId_div" cssClass="link"><s:text name="MTISearchCountry"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCountrySearchResultId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.country.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.countryName" var="HCountryName"></s:text>
	  <s:text name="label.header.country.code" var="HCountryCode"></s:text>
	  <s:text name="label.form.fields.common.description" var="HDescription"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_countrysearchList_div_id">
		  <display:table class="tableborder" id="countryListId" name="country" pagesize="${NO_OF_RECORDS}" requestURI="countrySearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sCountryId = ((CountryVO)pageContext.getAttribute("countryListId")).getHcmocountryId().toString();
		        	request.setAttribute("CountryId", sCountryId);    		
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column  property="countryName" title="${HCountryName}" sortable="true" headerClass="sortable"/>
		    <display:column property="countryCode" title="${HCountryCode}" sortable="true" headerClass="sortable"/>
		    <display:column property="description" title="${HDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
		    <s:if test="#session.COUNTRY_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewCountry" action="countryView">
						<s:param name="cou.hcmocountryId" value="#request.CountryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_Country_searchlist_div_id','%{listViewCountry}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.COUNTRY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpCountry" action="setUpInsertOrUpdateCountry">
						<s:param name="cou.hcmocountryId" value="#request.CountryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_Country_searchlist_div_id','%{listSetUpCountry}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.COUNTRY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteCountry" action="deleteCountry">
						<s:param name="cou.hcmocountryId" value="#request.CountryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_Country_searchlist_div_id','%{listDeleteCountry}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Country.csv"/>
			 <display:setProperty name="export.excel.filename" value="Country.xls"/>
			 <display:setProperty name="export.xml.filename" value="Country.xml"/>
		  </display:table>    
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#submenu_Country_searchlist_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script> 