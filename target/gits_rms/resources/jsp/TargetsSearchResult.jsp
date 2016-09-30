<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.TargetsVO"%>

<div id="submenu_targets_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.TARGETS_ADD == true">
		<sj:a href="setUpTargets.action" targets="submenu_targets_searchList_div_id" indicator="indicatorSubMenuTargetsSearchResId_div" cssClass="link"><s:text name="MTIAddTargets" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETS_VIEW == true">
		<sj:a href="getAllTargets.action" targets="submenu_targets_searchList_div_id" indicator="indicatorSubMenuTargetsSearchResId_div" cssClass="link"><s:text name="MTIViewTargets"/></sj:a> |
		<sj:a href="targetsSearchForm.action" targets="submenu_targets_searchList_div_id" indicator="indicatorSubMenuTargetsSearchResId_div" cssClass="link"><s:text name="MTISearchTargets"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTargetsSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.targets.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.targets.name" var="HTargetsName"></s:text>
	  <s:text name="label.header.targets.type" var="HTargetsType"></s:text>
	  <s:text name="label.header.targets.value" var="HTargetsValue"></s:text>
	  <s:text name="label.header.targets.mode" var="HTargetsMode"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_targetsSearchList_div_id">
		   <display:table class="tableborder" id="targetsListId" name="targetList" pagesize="${NO_OF_RECORDS}" requestURI="targetsSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sTargetsId = ((TargetsVO)pageContext.getAttribute("targetsListId")).getHcmoTargetId().toString();
		        	request.setAttribute("Targets", sTargetsId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="targetName" title="${HTargetsName}" sortable="true" headerClass="sortable"/>
		    <display:column property="targetTypeObj.targetTypeName" title="${HTargetsType}" sortable="true" headerClass="sortable"/>
		    <display:column property="targetValue" title="${HTargetsValue}" sortable="true" headerClass="sortable"/>
		    <display:column property="targetMode" title="${HTargetsMode}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.TARGETS_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewTargets" action="viewTargets">
						<s:param name="target.hcmoTargetId" value="#request.Targets"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targets_searchList_div_id','%{listViewTargets}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.TARGETS_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpTargets" action="setUpTargets">
						<s:param name="target.hcmoTargetId" value="#request.Targets"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targets_searchList_div_id','%{listSetUpTargets}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.TARGETS_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteTargets" action="deleteTargets">
						<s:param name="target.hcmoTargetId" value="#request.Targets"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_targets_searchList_div_id','%{listDeleteTargets}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Targets.csv"/>
			 <display:setProperty name="export.excel.filename" value="Targets.xls"/>
			 <display:setProperty name="export.xml.filename" value="Targets.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_targetsSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     
