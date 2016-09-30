<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.RoleVO"%>

<div id="submenu_role_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.ROLE_ADD == true">
		<sj:a href="setUpRole.action" targets="submenu_role_list_div_id" indicator="indicatorSubMenuRoleId_div" cssClass="link"><s:text name="MTIAddRole" /></sj:a> |
	</s:if>
	<s:if test="#session.ROLE_VIEW == true">
		<sj:a href="getAllRole.action" targets="submenu_role_list_div_id" indicator="indicatorSubMenuRoleId_div" cssClass="link"><s:text name="MTIViewRole"/></sj:a> |
		<sj:a href="roleSearchForm.action" targets="submenu_role_list_div_id" indicator="indicatorSubMenuRoleId_div" cssClass="link"><s:text name="MTISearchRole"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRoleId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.role.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.role.name" var="HRoleName"></s:text>
	  <s:text name="label.header.role.xmlPath" var="HRoleXmlPath"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_rloeList_div_id">
		  <display:table class="tableborder" id="roleListId" name="roleList" pagesize="${NO_OF_RECORDS}" requestURI="getAllRole.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sRoleId = ((RoleVO)pageContext.getAttribute("roleListId")).getHcmoRoleId().toString();
		        	request.setAttribute("RoleId", sRoleId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="roleName" title="${HRoleName}" sortable="true" headerClass="sortable"/>
		    <display:column property="xmlPath" title="${HRoleXmlPath}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.ROLE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewRole" action="roleView">
						<s:param name="role.hcmoRoleId" value="#request.RoleId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_role_list_div_id','%{listViewRole}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.ROLE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpRole" action="setUpRole">
						<s:param name="role.hcmoRoleId" value="#request.RoleId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_role_list_div_id','%{listSetUpRole}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.ROLE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteRole" action="deleteRole">
						<s:param name="role.hcmoRoleId" value="#request.RoleId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_role_list_div_id','%{listDeleteRole}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Role.csv"/>
			 <display:setProperty name="export.excel.filename" value="Role.xls"/>
			 <display:setProperty name="export.xml.filename" value="Role.xml"/>
		  </display:table>
	  </div>
	  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_rloeList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     