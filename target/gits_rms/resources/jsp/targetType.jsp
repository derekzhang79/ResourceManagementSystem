
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.TargetTypeVO"%>

<div id="submenu_targetType_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.TARGETSTYPE_ADD == true">
		<sj:a href="setUpTargetType.action" targets="submenu_targetType_list_div_id" indicator="indicatorSubMenuTargetTypeId_div" cssClass="link"><s:text name="MTIAddTargetType" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETSTYPE_VIEW == true">
		<sj:a href="getAllTargetType.action" targets="submenu_targetType_list_div_id" indicator="indicatorSubMenuTargetTypeId_div" cssClass="link"><s:text name="MTIViewTargetType"/></sj:a> |
		<sj:a href="targetTypeSearchForm.action" targets="submenu_targetType_list_div_id" indicator="indicatorSubMenuTargetTypeId_div" cssClass="link"><s:text name="MTISearchTargetType"/></sj:a>
	</s:if>
</div>
<br/>
	<img id="indicatorSubMenuTargetTypeId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.targetType.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.targets.type" var="HTargetTypeName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  <div id="display_tag_targetTypeList_div_id">
		  <display:table class="tableborder" id="targetTypeListId" name="targetTypeList" pagesize="${NO_OF_RECORDS}" requestURI="getAllTargetType.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sTargetTypeId = ((TargetTypeVO)pageContext.getAttribute("targetTypeListId")).getHcmoTargetTypeId().toString();
		        	request.setAttribute("TargetTypeId", sTargetTypeId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="targetTypeName" title="${HTargetTypeName}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.TARGETSTYPE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="targetTypeView" action="targetTypeView">
						<s:param name="targetType.hcmoTargetTypeId" value="#request.TargetTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targetType_list_div_id','%{targetTypeView}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
			
		    <s:if test="#session.TARGETSTYPE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpTargetType" action="setUpTargetType">
						<s:param name="targetType.hcmoTargetTypeId" value="#request.TargetTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targetType_list_div_id','%{setUpTargetType}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
			
		    <s:if test="#session.TARGETSTYPE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteTargetType" action="deleteTargetType">
						<s:param name="targetType.hcmoTargetTypeId" value="#request.TargetTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targetType_list_div_id','%{deleteTargetType}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			
			 <display:setProperty name="export.csv.filename" value="TargetType.csv"/>
			 <display:setProperty name="export.excel.filename" value="TargetType.xls"/>
			 <display:setProperty name="export.xml.filename" value="TargetType.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_targetTypeList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    
