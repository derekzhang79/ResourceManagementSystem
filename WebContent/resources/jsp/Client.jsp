<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.ClientVO"%>


<div id="submenu_client_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.CLIENT_ADD == true">
		<s:if test="#session.CHECK_CLIENT == 'AVAILABLE'"></s:if>
		<s:elseif test="#session.CHECK_CLIENT == 'NOT_AVAILABLE'">
			<sj:a href="setUpClient.action" targets="submenu_client_searchList_div_id" indicator="indicatorSubMenuClient" cssClass="link"><s:text name="MTIAddClient" /></sj:a> |
		</s:elseif>
	</s:if>
	<s:if test="#session.CLIENT_VIEW == true">
		<sj:a href="getAllClient.action" targets="submenu_client_searchList_div_id" indicator="indicatorSubMenuClient" cssClass="link"><s:text name="MTIViewClient"/></sj:a>
	</s:if>
</div><br/>
	<img id="indicatorSubMenuClient" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.client.list"/></span></li></div>		
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.client.companyName" var="HClientComName"></s:text>
	  <s:text name="label.header.client.noOfEmp" var="HClientNoOfEmp"></s:text>
	  <s:text name="label.header.client.taxId" var="HClientTaxId"></s:text>
	  <s:text name="label.header.common.phone" var="HClientPhone"></s:text>
	  <s:text name="label.header.common.fax" var="HClientFax"></s:text>
	  <s:text name="label.header.country.name" var="HClientCountryName"></s:text>
	  <s:text name="label.header.common.city" var="HClientcity"></s:text>
	  <s:text name="label.header.common.zipCode" var="HClientZipCode"></s:text>
	  <s:text name="label.header.common.address1" var="HClientAddress1"></s:text>
	  <s:text name="label.header.common.address2" var="HClientAddress2"></s:text>
	  <s:text name="label.header.client.state" var="HClientState"></s:text>
	  <s:text name="label.header.common.comments" var="HClientComment"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  
	  <div id="display_tag_clientList_div_id">		   
		  <display:table class="tableborder" id="clientListId" name="client" pagesize="${NO_OF_RECORDS}" requestURI="getAllClient.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sClientId = ((ClientVO)pageContext.getAttribute("clientListId")).getHcmoclientId().toString();
		        	request.setAttribute("ClientId", sClientId);    		
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="companyName" title="${HClientComName}" sortable="true" headerClass="sortable"/>
		    <display:column property="noOfEmp" title="${HClientNoOfEmp}" sortable="true" headerClass="sortable"/>
		    <display:column property="taxId" title="${HClientTaxId}" sortable="true" headerClass="sortable"/>
		    <display:column property="phone" title="${HClientPhone}" sortable="true" headerClass="sortable"/>
		    <display:column property="fax" title="${HClientFax}" sortable="true" headerClass="sortable"/>
		    <display:column property="country.countryName" title="${HClientCountryName}" sortable="true" headerClass="sortable"/>
		    <display:column property="city" title="${HClientcity}" sortable="true" headerClass="sortable"/>
		    <display:column property="zipcode" title="${HClientZipCode}" sortable="true" headerClass="sortable"/>
		    <display:column property="address1" title="${HClientAddress1}" sortable="true" headerClass="sortable"/>
		    <display:column property="address2" title="${HClientAddress2}" sortable="true" headerClass="sortable"/>
		    <display:column property="state" title="${HClientState}" sortable="true" headerClass="sortable"/>
		    <display:column property="comments" title="${HClientComment}" sortable="true" headerClass="sortable" maxLength="10"/>
		    <s:if test="#session.CLIENT_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewClient" action="clientView">
						<s:param name="cli.hcmoclientId" value="#request.ClientId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_client_searchList_div_id','%{listViewClient}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.CLIENT_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpClient" action="setUpClient">
						<s:param name="cli.hcmoclientId" value="#request.ClientId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_client_searchList_div_id','%{listSetUpClient}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
			<display:setProperty name="export.csv.filename" value="Client.csv"/>
			<display:setProperty name="export.excel.filename" value="Client.xls"/>
			<display:setProperty name="export.xml.filename" value="Client.xml"/>
		  </display:table>
	  </div>
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_client_searchList_div_id" cssClass="submitbutton117"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
	   		    			<s:url var="skippedPermanently" action="skippedPermanently"/>
							<sj:submit href="%{skippedPermanently}" value="Skipped Permanently" targets="submenu_client_searchList_div_id" cssClass="button-midle"/>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:url var="skip" action="skip"/>
						<sj:submit href="%{skip}" value="Skip" targets="submenu_client_searchList_div_id" cssClass="submitbutton117"/>
					</td>
				</tr>
		</table>
	</s:if>   
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_clientList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     