<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.OrganizationVO"%>

<div id="submenu_Organization_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATION_ADD == true">
		<sj:a href="setUpOrganization.action" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div" cssClass="link"><s:text name="MTIAddOrganization" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATION_VIEW == true">
		<sj:a href="getAllOrganization.action" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div" cssClass="link"><s:text name="MTIViewOrganization"/></sj:a> |
		<sj:a href="organizationSearchForm.action" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div" cssClass="link"><s:text name="MTISearchOrganization"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.organization.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.org.name" var="HOrganizationName"></s:text>
	  <s:text name="label.header.org.parentOrgId" var="HOrganizationParentId"></s:text>
	  <s:text name="label.header.location.name" var="HOrganizationLocation"></s:text>
	  <s:text name="label.header.orgType.name" var="HOrganizationType"></s:text>
	  <s:text name="label.header.common.description" var="HOrganizationDescrition"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <display:table class="tableborder" id="orgListId" name="orgList" pagesize="${NO_OF_RECORDS}" requestURI="getAllOrganization.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	    <%
	    	try{
	    		String sOrgId = ((OrganizationVO)pageContext.getAttribute("orgListId")).getOrgId().toString();
	        	request.setAttribute("OrgId", sOrgId);
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="orgName" title="${HOrganizationName}" sortable="true" headerClass="sortable"/>
	    <display:column property="parentOrgValue" title="${HOrganizationParentId}" sortable="true" headerClass="sortable"/>
	    <display:column property="location.locationName" title="${HOrganizationLocation}" sortable="true" headerClass="sortable"/>
	    <display:column property="orgtype.orgTypeName" title="${HOrganizationType}" sortable="true" headerClass="sortable"/>
	    <display:column property="description" title="${HOrganizationDescrition}" sortable="true" headerClass="sortable" maxLength="10"/>
	    <s:if test="#session.ORGANIZATION_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewOrganization" action="organizationView">
					<s:param name="org.orgId" value="#request.OrgId"></s:param>
				</s:url>
				<sj:a href="%{listViewOrganization}" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div"><s:text name="View"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.ORGANIZATION_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpOrganization" action="setUpOrganization">
					<s:param name="org.orgId" value="#request.OrgId"></s:param>
				</s:url>
				<sj:a href="%{listSetUpOrganization}" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div"><s:text name="Edit"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.ORGANIZATION_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteOrganization" action="deleteOrganization">
					<s:param name="org.orgId" value="#request.OrgId"></s:param>
				</s:url>
				<sj:a href="%{listDeleteOrganization}" targets="submenu_Organization_div" indicator="indicatorSubMenuOrganizationId_div"><s:text name="Delete"/></sj:a>
			</display:column>
		</s:if>
		<display:setProperty name="export.csv.filename" value="Organization.csv"/>
		<display:setProperty name="export.excel.filename" value="Organization.xls"/>
		<display:setProperty name="export.xml.filename" value="Organization.xml"/>
	  </display:table>
</div>    