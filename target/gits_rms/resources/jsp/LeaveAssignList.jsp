<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.LeaveReqsApprovalVO"%>

<div id="submenu_LeaveAssigned_SearchResult_div">
<div class="informationMessageSingle"><li><span><s:text name="label.title.lrapp.assignLeave.list"/></span></li></div>

<img id="indicatorSubMenuCountryLeaveAssignListId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.name" var="HLeaveType"></s:text>
	  <s:text name="label.header.leaveAssigned.date" var="HDateApplied"></s:text>
	  <s:text name="label.header.common.noOfDays" var="HNoOfDays"></s:text>
	  <s:text name="label.header.leaveapproveedby.name" var="HApprover"></s:text>
	  <s:text name="label.header.lrapp.dateApprovDisApprov" var="HDateAppDisApp"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  		   
	  <display:table class="tableborder" id="leaveReqsApprovalListId" name="lrappList" pagesize="${NO_OF_RECORDS}" requestURI="getAllLeaveReqsApproval.action" sort="list" defaultsort="1" defaultorder="ascending">
	    <%
	    	try{
	    		String sLeaveReqsApprovalId = ((LeaveReqsApprovalVO)pageContext.getAttribute("leaveReqsApprovalListId")).getHcmoLeaveReqsApprovalId().toString();
	        	request.setAttribute("leaveReqsApprovalListId", sLeaveReqsApprovalId);    		
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" sortable="false" headerClass="sortable"/>
	    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveType}" sortable="false" headerClass="sortable"/>
	    <display:column property="dateApplied" title="${HDateApplied}" sortable="false" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="leaveRequested" title="${HNoOfDays}" sortable="false" headerClass="sortable"/>
	    <display:column property="hcmoLeaveApproverId.empFullName" title="${HApprover}" sortable="false" headerClass="sortable"/>
	    <display:column property="dateApprDisappr" title="${HDateAppDisApp}" sortable="false" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="comments" title="${HComments}" sortable="false" headerClass="sortable" maxLength="10"/>
	    
	  </display:table>
	  <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorLeaveAssign" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="backLeaveAssignTabButton" action="backLeaveAssignTabButton"></s:url>
	   			 <sj:submit href="%{backLeaveAssignTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveAssigned_SearchResult_div" indicator="indicatorLeaveAssign"/>
   		    </td>
   		</tr>
   	  </table>
	  </div>    