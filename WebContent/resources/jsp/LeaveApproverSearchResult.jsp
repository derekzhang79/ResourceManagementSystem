<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.LeaveApproverVO"%>

<div id="submenu_leaveApprover_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateLeaveApprover.action" targets="submenu_leaveApprover_searchList_div_id" indicator="indicatorSubMenuLeaveApproverSearchResId_div" cssClass="link"><s:text name="MTIAddLeaveApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_VIEW == true">
			<sj:a href="getAllLeaveApprover.action" targets="submenu_leaveApprover_searchList_div_id" indicator="indicatorSubMenuLeaveApproverSearchResId_div" cssClass="link"><s:text name="MTIViewLeaveApprover"/></sj:a> |
			<sj:a href="leaveAppSearchForm.action" targets="submenu_leaveApprover_searchList_div_id" indicator="indicatorSubMenuLeaveApproverSearchResId_div" cssClass="link"><s:text name="MTISearchLeaveApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
	<img id="indicatorSubMenuLeaveApproverSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.leaveapprover.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.leaveapprover.EmployeeName" var="HLeaveApproverEmpName"></s:text>
	  <s:text name="label.header.leaveapprover.leaveApprovingEmployee" var="HLeaveApproverName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_leaveApproverSearchList_div_id">
		  <display:table class="tableborder" id="leaveApproverListId" name="leaveApproverList" pagesize="${NO_OF_RECORDS}" requestURI="leaveAppSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sLeaveApproverId = ((LeaveApproverVO)pageContext.getAttribute("leaveApproverListId")).getHcmoLeaveApproverId().toString();
		        	request.setAttribute("LeaveApproverId", sLeaveApproverId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoEmployeeId.empFullName" title="${HLeaveApproverEmpName}" sortable="true" headerClass="sortable"/>
		    <display:column property="hcmoApprovingEmpId.empFullName" title="${HLeaveApproverName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.LEAVEAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewLeaveApprover" action="leaveApproverView">
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.LeaveApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveApprover_searchList_div_id','%{listViewLeaveApprover}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LEAVEAPPROVER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpLeaveApprover" action="setUpInsertOrUpdateLeaveApprover">
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.LeaveApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveApprover_searchList_div_id','%{listSetUpLeaveApprover}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LEAVEAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteLeaveApprover" action="deleteLeaveApprover">
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.LeaveApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveApprover_searchList_div_id','%{listDeleteLeaveApprover}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="LeaveApprover.csv"/>
			 <display:setProperty name="export.excel.filename" value="LeaveApprover.xls"/>
			 <display:setProperty name="export.xml.filename" value="LeaveApprover.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_leaveApproverSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>        