<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.LeaveTypeVO"%>

<div id="submenu_leaveType_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.LEAVETYPE_ADD == true">
		<sj:a href="setUpLeaveType.action" targets="submenu_leaveType_searchList_div_id" indicator="indicatorSubMenuLeaveTypeSearcnResId_div" cssClass="link"><s:text name="MTIAddLeaveType" /></sj:a> |
	</s:if>
	<s:if test="#session.LEAVETYPE_VIEW == true">
		<sj:a href="getAllLeaveType.action" targets="submenu_leaveType_searchList_div_id" indicator="indicatorSubMenuLeaveTypeSearcnResId_div" cssClass="link"><s:text name="MTIViewLeaveType"/></sj:a> |
		<sj:a href="leaveTypeSearchForm.action" targets="submenu_leaveType_searchList_div_id" indicator="indicatorSubMenuLeaveTypeSearcnResId_div" cssClass="link"><s:text name="MTISearchLeaveType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLeaveTypeSearcnResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.leaveType.list"/></span></li></div>		
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.leaveType.leaveType" var="HLeaveTypeName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_laveTypeSearchList_div_id">
		  <display:table class="tableborder" id="leaveTypeListId" name="leaveList" pagesize="${NO_OF_RECORDS}" requestURI="leaveTypeSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sLeaveTypeId = ((LeaveTypeVO)pageContext.getAttribute("leaveTypeListId")).getLeaveTypeId().toString();
		        	request.setAttribute("LeaveTypeId", sLeaveTypeId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="leaveTypeName" title="${HLeaveTypeName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.LEAVETYPE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewLeaveType" action="leaveTypeView">
						<s:param name="leave.leaveTypeId" value="#request.LeaveTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveType_searchList_div_id','%{listViewLeaveType}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LEAVETYPE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpLeaveType" action="setUpLeaveType">
						<s:param name="leave.leaveTypeId" value="#request.LeaveTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveType_searchList_div_id','%{listSetUpLeaveType}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LEAVETYPE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteLeaveType" action="deleteLeaveType">
						<s:param name="leave.leaveTypeId" value="#request.LeaveTypeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveType_searchList_div_id','%{listDeleteLeaveType}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="LeaveType.csv"/>
			 <display:setProperty name="export.excel.filename" value="LeaveType.xls"/>
			 <display:setProperty name="export.xml.filename" value="LeaveType.xml"/>
		  </display:table>
	  </div>
	  
</div>    
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_laveTypeSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    