<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.RegionVO"%>

<div id="submenu_region_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.REGION_ADD == true">
		<sj:a href="setUpInsertOrUpdateRegion.action" targets="submenu_region_list_div_id" indicator="indicatorSubMenuRegionId_div" cssClass="link"><s:text name="MTIAddRegion" /></sj:a> |
	</s:if>
	<s:if test="#session.REGION_VIEW == true">
		<sj:a href="getAllRegion.action" targets="submenu_region_list_div_id" indicator="indicatorSubMenuRegionId_div" cssClass="link"><s:text name="MTIViewRegion"/></sj:a> |
		<sj:a href="regionSearchForm.action" targets="submenu_region_list_div_id" indicator="indicatorSubMenuRegionId_div" cssClass="link"><s:text name="MTISearchRegion"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRegionId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.region.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.region.name" var="HRegionName"></s:text>
	  <s:text name="label.header.region.country" var="HRegionCountry"></s:text>
	  <s:text name="label.header.common.zipCode" var="HRegionZip"></s:text>
	  <s:text name="label.header.common.city" var="HRegionCity"></s:text>
	  <s:text name="label.header.region.countyField" var="HRegionCountyField"></s:text>
	  <s:text name="label.header.region.regionCode" var="HRegionCode"></s:text>
	  <s:text name="label.header.region.areaCode" var="HRegionAreaCode"></s:text>
	  <s:text name="label.header.region.timeZone" var="HRegionTimeZone"></s:text>
	  <s:text name="label.header.region.latitude" var="HRegionLatitude"></s:text>
	  <s:text name="label.header.region.longitude" var="HRegionLongitude"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_regionList_div_id">
		  <display:table class="tableborder" id="regionListId" name="region" pagesize="${NO_OF_RECORDS}" requestURI="getAllRegion.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sRegionId = ((RegionVO)pageContext.getAttribute("regionListId")).getHcmoregionId().toString();
		        	request.setAttribute("RegionId", sRegionId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="name" title="${HRegionName}" sortable="true" headerClass="sortable"/>
		    <display:column property="country.countryName" title="${HRegionCountry}" sortable="true" headerClass="sortable"/>
		    <display:column property="zipCode" title="${HRegionZip}" sortable="true" headerClass="sortable"/>
		    <display:column property="city" title="${HRegionCity}" sortable="true" headerClass="sortable"/>
		    <display:column property="countyField" title="${HRegionCountyField}" sortable="true" headerClass="sortable"/>
		    <display:column property="regionCode" title="${HRegionCode}" sortable="true" headerClass="sortable"/>
		    <display:column property="areaCode" title="${HRegionAreaCode}" sortable="true" headerClass="sortable"/>
		    <display:column property="timeZone" title="${HRegionTimeZone}" sortable="true" headerClass="sortable"/>
		    <display:column property="latitude" title="${HRegionLatitude}" sortable="true" headerClass="sortable"/>
		    <display:column property="longitude" title="${HRegionLongitude}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.REGION_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewRegion" action="regionView">
						<s:param name="reg.hcmoregionId" value="#request.RegionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_region_list_div_id','%{listViewRegion}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.REGION_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpRegion" action="setUpInsertOrUpdateRegion">
						<s:param name="reg.hcmoregionId" value="#request.RegionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_region_list_div_id','%{listSetUpRegion}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.REGION_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteRegion" action="deleteRegion">
						<s:param name="reg.hcmoregionId" value="#request.RegionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_region_list_div_id','%{listDeleteRegion}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Region.csv"/>
			 <display:setProperty name="export.excel.filename" value="Region.xls"/>
			 <display:setProperty name="export.xml.filename" value="Region.xml"/>
		  </display:table>
	  </div>
	  
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_region_list_div_id" cssClass="submitbutton117"/>
						<img id="indicatorMailConfigurationRegionId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"/>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_region_list_div_id" cssClass="button-midle"/>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:url var="skip" action="skip"/>
						<sj:submit href="%{skip}" value="Skip" targets="submenu_region_list_div_id" cssClass="submitbutton117"/>
					</td>
				</tr>
		</table>
	</s:if>  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_regionList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     