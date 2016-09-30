<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.LocationVO"%>


<div id="submenu_locationList_div_id">
<div class="submenu_bg">
	<s:if test="#session.LOCATION_ADD == true">
		<sj:a href="setUpLocation.action" targets="submenu_locationList_div_id" indicator="indicatorSubMenuLocationId_div" cssClass="link"><s:text name="MTIAddLocation" /></sj:a> |
	</s:if>
	<s:if test="#session.LOCATION_VIEW == true">
		<sj:a href="getAllLocation.action" targets="submenu_locationList_div_id" indicator="indicatorSubMenuLocationId_div" cssClass="link"><s:text name="MTIViewLocation"/></sj:a> |
		<sj:a href="locationSearchForm.action" targets="submenu_locationList_div_id" indicator="indicatorSubMenuLocationId_div" cssClass="link"><s:text name="MTISearchLocation"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLocationId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.location.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.location.name" var="HLocationName"></s:text>
	  <s:text name="label.header.country.name" var="HLocationCountryName"></s:text>
	  <s:text name="label.header.region.name" var="HLocationRegionName"></s:text>
	  <s:text name="label.header.common.address1" var="HLocationAddress1"></s:text>
	  <s:text name="label.header.common.address2" var="HLocationAddress2"></s:text>
	  <s:text name="label.header.common.city" var="HLocationCity"></s:text>
	  <s:text name="label.header.common.phone" var="HLocationPhone"></s:text>
	  <s:text name="label.header.common.fax" var="HLocationFax"></s:text>
	  <s:text name="label.header.common.zipCode" var="HLocationZipCode"></s:text>
	  <s:text name="label.header.common.comments" var="HLocationComment"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="displaytag_location_div_id">		   
		  <display:table class="tableborder" id="locationListId" name="location" pagesize="${NO_OF_RECORDS}" requestURI="getAllLocation.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sLocationId = ((LocationVO)pageContext.getAttribute("locationListId")).getHcmolocationId().toString();
		        	request.setAttribute("LocationId", sLocationId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="locationName" title="${HLocationName}" sortable="true" headerClass="sortable"/>
		    <display:column property="country.countryName" title="${HLocationCountryName}" sortable="true" headerClass="sortable"/>
		    <display:column property="region" title="${HLocationRegionName}" sortable="true" headerClass="sortable"/>
		    <display:column property="address1" title="${HLocationAddress1}" sortable="true" headerClass="sortable"/>
		    <display:column property="address2" title="${HLocationAddress2}" sortable="true" headerClass="sortable"/>
		    <display:column property="city" title="${HLocationCity}" sortable="true" headerClass="sortable"/>
		    <display:column property="phone" title="${HLocationPhone}" sortable="true" headerClass="sortable"/>
		    <display:column property="fax" title="${HLocationFax}" sortable="true" headerClass="sortable"/>
		    <display:column property="zipcode" title="${HLocationZipCode}" sortable="true" headerClass="sortable"/>
		    <display:column property="comments" title="${HLocationComment}" sortable="true" headerClass="sortable" maxLength="10"/>
		    
		    <s:if test="#session.LOCATION_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewLocation" action="locationView">
						<s:param name="loc.hcmolocationId" value="#request.LocationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_locationList_div_id','%{listViewLocation}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LOCATION_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpLocation" action="setUpLocation">
						<s:param name="loc.hcmolocationId" value="#request.LocationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_locationList_div_id','%{listSetUpLocation}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LOCATION_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteLocation" action="deleteLocation">
						<s:param name="loc.hcmolocationId" value="#request.LocationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_locationList_div_id','%{listDeleteLocation}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Location.csv"/>
			 <display:setProperty name="export.excel.filename" value="Location.xls"/>
			 <display:setProperty name="export.xml.filename" value="Location.xml"/>
		  </display:table>
	  </div>
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_locationList_div_id" cssClass="submitbutton117"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"/>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_locationList_div_id" cssClass="button-midle"/>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:url var="skip" action="skip"/>
						<sj:submit href="%{skip}" value="Skip" targets="submenu_locationList_div_id" cssClass="submitbutton117"/>
					</td>
				</tr>
		</table>
	</s:if>   
</div>

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#displaytag_location_div_id").displayTagAjax();
  		}catch(e){
   		}
  	});
</script>    