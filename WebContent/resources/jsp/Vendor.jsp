<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.VendorVO"%>

<div id="submenu_vendor_list_div_id">
<div class="submenu_bg">
	<sj:a href="setUpForInsertOrUpdateVendor.action" targets="submenu_vendor_list_div_id" indicator="indicatorSubMenuVendorId_div" cssClass="link"><s:text name="MTIAddVendor" /></sj:a> |
	<sj:a href="getAllVendor.action" targets="submenu_vendor_list_div_id" indicator="indicatorSubMenuVendorId_div" cssClass="link"><s:text name="MTIViewVendor"/></sj:a> |
	<sj:a href="vendorSearchForm.action" targets="submenu_vendor_list_div_id" indicator="indicatorSubMenuVendorId_div" cssClass="link"><s:text name="MTISearchVendor"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuVendorId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.vendor.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.vendor.taxId" var="HTaxId"></s:text>
	  <s:text name="label.header.vendor.vendorName" var="HVendorName"></s:text>
	  <s:text name="label.header.vendor.companyName" var="HCompanyName"></s:text>
	  <s:text name="label.header.vendor.contactPerson1" var="HContactPerson1"></s:text>
	  <s:text name="label.header.vendor.emailAddress1" var="HEmailAddress1"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_vendorList_div_id">
		  <display:table class="tableborder" id="vendorListId" name="vendorList" pagesize="${NO_OF_RECORDS}" requestURI="getAllVendor.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sVendorId = ((VendorVO)pageContext.getAttribute("vendorListId")).getVendorId().toString();
		        	request.setAttribute("VendorId", sVendorId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="taxId" title="${HTaxId}" sortable="true" headerClass="sortable"/>
		    <display:column property="vendorName" title="${HVendorName}" sortable="true" headerClass="sortable"/>
		    <display:column property="companyName" title="${HCompanyName}" sortable="true" headerClass="sortable"/>
		    <display:column property="contactPerson1" title="${HContactPerson1}" sortable="true" headerClass="sortable"/>
		    <display:column property="emailAddress1" title="${HEmailAddress1}" sortable="true" headerClass="sortable"/>
		    
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewVendor" action="vendorView">
						<s:param name="vendor.vendorId" value="#request.VendorId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_vendor_list_div_id','%{listViewVendor}','');"><s:text name="View"/></s:a>
				</display:column>
				
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpVendor" action="setUpForInsertOrUpdateVendor">
						<s:param name="vendor.vendorId" value="#request.VendorId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_vendor_list_div_id','%{listSetUpVendor}','');"><s:text name="Edit"/></s:a>
				</display:column>
				
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteVendor" action="deleteVendor">
						<s:param name="vendor.vendorId" value="#request.VendorId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_vendor_list_div_id','%{listDeleteVendor}','');"><s:text name="Delete"/></s:a>
				</display:column>
				
			 <display:setProperty name="export.csv.filename" value="Vendor.csv"/>
			 <display:setProperty name="export.excel.filename" value="Vendor.xls"/>
			 <display:setProperty name="export.xml.filename" value="Vendor.xml"/>
		  </display:table>
	  </div>
	  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_vendorList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    