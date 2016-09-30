<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.OrganizationTypeVO"%>

<div id="submenu_OrganizationTypeSearchResId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATIONTYPE_ADD == true">
		<sj:a href="setUpOrganizationType.action" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div" cssClass="link"><s:text name="MTIAddOrganizationType" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATIONTYPE_VIEW == true">
		<sj:a href="getAllOrganizationType.action" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div" cssClass="link"><s:text name="MTIViewOrganizationType"/></sj:a> |
		<sj:a href="organizationTypeSearchForm.action" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div" cssClass="link"><s:text name="MTISearchOrganizationType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationTypeSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.orgType.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.orgType.name" var="HOrganizationType"></s:text>
	  <s:text name="label.form.fields.common.description" var="HOrganizationDescription"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <display:table class="tableborder" id="orgTypeListId" name="orgtypeList" pagesize="${NO_OF_RECORDS}" requestURI="organizationTypeSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	    <%
	    	try{
	    		String sOrgTypeId = ((OrganizationTypeVO)pageContext.getAttribute("orgTypeListId")).getHcmoorgTypeId().toString();
	        	request.setAttribute("OrganizationTypeId", sOrgTypeId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="orgTypeName" title="${HOrganizationType}" sortable="true" headerClass="sortable"/>
	    <display:column property="description" title="${HOrganizationDescription}" sortable="true" headerClass="sortable"/>
	    <s:if test="#session.ORGANIZATIONTYPE_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewOrgType" action="organizationTypeView">
					<s:param name="orgtype.hcmoorgTypeId" value="#request.OrganizationTypeId"></s:param>
				</s:url>
				<sj:a href="%{listViewOrgType}" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div"><s:text name="View"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.ORGANIZATIONTYPE_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpOrgType" action="setUpOrganizationType">
					<s:param name="orgtype.hcmoorgTypeId" value="#request.OrganizationTypeId"></s:param>
				</s:url>
				<sj:a href="%{listSetUpOrgType}" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div"><s:text name="Edit"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.ORGANIZATIONTYPE_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteOrgType" action="deleteOrganizationType">
					<s:param name="orgtype.hcmoorgTypeId" value="#request.OrganizationTypeId"></s:param>
				</s:url>
				<sj:a href="%{listDeleteOrgType}" targets="submenu_OrganizationTypeSearchResId_div" indicator="indicatorSubMenuOrganizationTypeSearchResId_div"><s:text name="Delete"/></sj:a>
			</display:column>
		</s:if>
		<display:setProperty name="export.csv.filename" value="OrganizationType.csv"/>
		<display:setProperty name="export.excel.filename" value="OrganizationType.xls"/>
		<display:setProperty name="export.xml.filename" value="OrganizationType.xml"/>
	  </display:table>
</div>    