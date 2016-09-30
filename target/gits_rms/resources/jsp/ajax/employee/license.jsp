<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.LicenseVO"%>

<div id="submenu_EssLicense_List_div">
<img id="indicatorLicList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

   	<s:set name="UniqueLicEmployeeId" value="license.empIdObj.employeeId"></s:set>
	<s:url var="remoteLicForm" value="/setUpEmpLicense.action">
		<s:param name="license.empIdObj.employeeId" value="#UniqueLicEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueLicEmployeeId" value="license.empIdObj.employeeId"></s:set>
	<s:url var="remoteLicView" value="/getEmployeeAllLicense.action">
		<s:param name="license.empIdObj.employeeId" value="#UniqueLicEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
		<s:if test="#session.LICENSE_ADD==true">
			<sj:a href="%{remoteLicForm}" indicator="indicatorLicList" targets="submenu_EssLicense_List_div" cssClass="link"><s:text name="label.header.license.add"/></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW==true">
			<sj:a href="%{remoteLicView}" indicator="indicatorLicList" targets="submenu_EssLicense_List_div" cssClass="link"><s:text name="label.header.license.view"/></sj:a>
		</s:if>
	</div>		
<br />

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.license.list"/></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
        <s:text name="label.header.common.empName" var="HEmpname"/>
        <s:text name="label.header.license.licNumber" var="HlicNumber"/>
        <s:text name="label.header.license.licenseDate" var="HDate"/>
        <s:text name="label.header.license.renewalDate" var="HRenewalDate"/>
        <s:text name="label.header.common.description" var="HDescription"/>
        
         
        <s:if test="#session.LICENSE_VIEW==true">
        	<s:text name="label.header.common.view" var="HView"/>
        	</s:if>
        <s:if test="#session.LICENSE_UPDATE==true">
        	<s:text name="label.header.common.edit" var="HEdit"/>
        </s:if>
        <s:if test="#session.LICENSE_DELETE==true">
        	<s:text name="label.header.common.delete" var="HDelete"/>
        </s:if>

   <display:table class="tableborder" id="licenseListId" name="licenseList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try{
			  String sEmpId=((LicenseVO)pageContext.getAttribute("licenseListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sLicId=((LicenseVO)pageContext.getAttribute("licenseListId")).getEmpLicenseId().toString();
			  request.setAttribute("sLicenseId",sLicId);
		  }catch(NullPointerException ne){
		  }
		%>
		
            <display:column property="empIdObj.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
            <display:column property="licenseNumber" title="${HlicNumber}" sortable="false" headerClass="sortable"/>
            <display:column property="licenseDate" title="${HDate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
            <display:column property="licenseRenewalDate" title="${HRenewalDate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
            <display:column property="licenseDesc" title="${HDescription}" sortable="false" headerClass="sortable" maxLength="10"/>
            
            
          <s:if test="#session.LICENSE_VIEW==true">
	           <display:column title="${HView}" class="viewedit" media="html">
	               	 <s:url var="licenseView" action="viewEmpLicense" escapeAmp="false">
						 <s:param name="license.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		     <s:param name="license.empLicenseId" value="#request.sLicenseId"/>
		       		 </s:url> 
	                <sj:a href="%{licenseView}" targets="submenu_EssLicense_List_div" indicator="indicatorLicList"><s:text name="label.common.link.view"/></sj:a>
	           </display:column>
	       </s:if>
         
           <s:if test="#session.LICENSE_UPDATE==true">
	           <display:column title="${HEdit}" class="viewedit" media="html">
	               	 <s:url var="setUpEmpLicenseSingle" action="setUpEmpLicenseSingle" escapeAmp="false">
						 <s:param name="license.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		     <s:param name="license.empLicenseId" value="#request.sLicenseId"/>
		       		 </s:url> 
	                <sj:a href="%{setUpEmpLicenseSingle}" targets="submenu_EssLicense_List_div" indicator="indicatorLicList"><s:text name="label.common.link.edit"/></sj:a>
	           </display:column>
	       </s:if>
	       <s:if test="#session.LICENSE_DELETE==true">
	          <display:column title="${HDelete}" class="viewedit" media="html">
	                <s:url var="deleteEmplicense" action="deleteEmplicense" escapeAmp="false">
						 <s:param name="license.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		   	 <s:param name="license.empLicenseId" value="#request.sLicenseId"/>
		       		</s:url>
	                <sj:a href="%{deleteEmplicense}" targets="submenu_EssLicense_List_div" indicator="indicatorLicList"><s:text name="label.common.link.delete"/></sj:a>
	         </display:column>
	       </s:if>
	         
</display:table>
<br />
</div>