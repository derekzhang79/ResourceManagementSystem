<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.LicenseVO"%>

<div id="submenu_license_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.LICENSE_ADD == true">
			<sj:a href="setUpLicense.action" targets="submenu_license_list_div_id" indicator="indicatorSubMenuLicense" cssClass="link"><s:text name="MTIAddLicense" /></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW == true">
			<sj:a href="getAllLicense.action" targets="submenu_license_list_div_id" indicator="indicatorSubMenuLicense" cssClass="link"><s:text name="MTIViewLicense"/></sj:a> |
			<sj:a href="licenseSearchForm.action" targets="submenu_license_list_div_id" indicator="indicatorSubMenuLicense" cssClass="link"><s:text name="MTISearchLicense"/></sj:a>
		</s:if>
	</div>
	<br/>
		<img id="indicatorSubMenuLicense" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.license.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HLicenseEmpName"></s:text>
	  <s:text name="label.header.license.licNumber" var="HLicenseLicNumber"></s:text>
	  <s:text name="label.header.license.licenseDate" var="HLicenseLicenseDate"></s:text>
	  <s:text name="label.header.license.renewalDate" var="HLicenseRenewalDate"></s:text>
	  <s:text name="label.header.common.description" var="HLicenseDesc"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_licenseList_div_id">
		  <display:table class="tableborder" id="licenseListId" name="licenseList" pagesize="${NO_OF_RECORDS}" requestURI="getAllLicense.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sLicenseId = ((LicenseVO)pageContext.getAttribute("licenseListId")).getEmpLicenseId().toString();
		        	request.setAttribute("LicenseId", sLicenseId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HLicenseEmpName}" sortable="true" headerClass="sortable"/>
		   
		    <s:if test="#session.LICENSE_LICENSENUMBER_VIEW == true">
		    	<display:column property="licenseNumber" title="${HLicenseLicNumber}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LICENSE_LICENSEDATE_VIEW == true">
		    	<display:column property="licenseDate" title="${HLicenseLicenseDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LICENSE_LICENSERENEWALDATE_VIEW == true">
		    	<display:column property="licenseRenewalDate" title="${HLicenseRenewalDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LICENSE_LICENSEDESC_VIEW == true">
		    	<display:column property="licenseDesc" title="${HLicenseDesc}" sortable="true" headerClass="sortable" maxLength="10"/>
		    </s:if>
		    <s:if test="#session.LICENSE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewLicense" action="licenseView">
						<s:param name="license.empLicenseId" value="#request.LicenseId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_license_list_div_id','%{listViewLicense}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LICENSE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpLicense" action="setUpLicense">
						<s:param name="license.empLicenseId" value="#request.LicenseId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_license_list_div_id','%{listSetUpLicense}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LICENSE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteLicense" action="deletelicense">
						<s:param name="license.empLicenseId" value="#request.LicenseId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_license_list_div_id','%{listDeleteLicense}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="License.csv"/>
			 <display:setProperty name="export.excel.filename" value="License.xls"/>
			 <display:setProperty name="export.xml.filename" value="License.xml"/>
		  </display:table>
	  </div>
	  
 </div>
 <script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_licenseList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>         