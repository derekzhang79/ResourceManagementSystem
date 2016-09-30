<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.LeaveHistoryVO"%>

<div id="submenu_LeaveHistory_SearchResult_Emp_div">
<div class="informationMessageSingle"><li><span><s:text name="label.title.lhist.reportList"/></span></li></div>
<br />
<img id="indicatorSubMenuCountryLeaveHistSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.leaveType" var="HLeaveType"></s:text>
	  <s:text name="label.header.leaveapproveedby.name" var="HApprover"></s:text>
	  <s:text name="label.header.common.dateApplied" var="HDateApplied"></s:text>
	  <s:text name="label.header.common.noOfDays" var="HNoOfDays"></s:text>
	  <s:text name="label.header.leaveHistory.leaveStatus" var="HLeaveStatus"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  <s:text name="label.header.lhist.approveNotes" var="HApprvoeNotes"></s:text>
	  <s:text name="label.header.lhist.disApproveNotes" var="HDisApproveNotes"></s:text>
	  		   
	  <display:table class="tableborder" id="leaveHistListId" name="lHistList" requestURI="getAllLeaveHistorySearch.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	    <%
	    	try{
	    		String sLeaveHistoryId = ((LeaveHistoryVO)pageContext.getAttribute("leaveHistListId")).getHcmoLeaveHistoryId().toString();
	        	request.setAttribute("LeaveHistoryId", sLeaveHistoryId);    		
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" headerClass="sortable"/>
	    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveType}" headerClass="sortable"/>
	    <display:column property="hcmoLeaveApproverId.empFullName" title="${HApprover}" headerClass="sortable"/>
	    <display:column property="leaveDate" title="${HDateApplied}" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="leaveRequested" title="${HNoOfDays}" headerClass="sortable"/>
	    <display:column property="leaveStatus" title="${HLeaveStatus}" headerClass="sortable"/>
	    <display:column property="leaveComments" title="${HComments}" headerClass="sortable" maxLength="10"/>
	    <display:column property="approveNotes" title="${HApprvoeNotes}" headerClass="sortable" maxLength="10"/>
    	<display:column property="disApproveNotes" title="${HDisApproveNotes}" headerClass="sortable" maxLength="10"/>
    	
		 <display:setProperty name="export.csv.filename" value="LeaveHistoty.csv"/>
		 <display:setProperty name="export.excel.filename" value="LeaveHistoty.xls"/>
		 <display:setProperty name="export.xml.filename" value="LeaveHistoty.xml"/>
	  </display:table>    
	  <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpLeaveHistory" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="backEmpLeaveHistoryTabButton" action="backEmpLeaveHistoryTabButton"></s:url>
	   			 <sj:submit href="%{backEmpLeaveHistoryTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveHistory_SearchResult_Emp_div" indicator="indicatorEmpLeaveHistory"/>
   		    </td>
   		</tr>
   	  </table>
</div>