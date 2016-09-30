<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.LeaveReqsApprovalVO"%>

<div id="submenu_LeaveForApproval_SearchResult_App_div">
<div class="informationMessageSingle"><li><span><s:text name="label.title.Leave.leaveForApproval"/></span></li></div>
<jsp:include page="common/messages.jsp" flush="true"/>
<br />
<img id="indicatorLeaveApproverSubEmpForApproId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.name" var="HLeaveType"></s:text>
	  <s:text name="label.header.common.dateApplied" var="HDateApplied"></s:text>
	  <s:text name="label.header.common.noOfDays" var="HNoOfDays"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  <s:if test="leaveAppDis==null || leaveSelfApp!=null">
	    <s:text name="label.header.common.approved" var="HApproved"></s:text>
	    <s:text name="label.header.common.disApproved" var="HDisApproved"></s:text>
	  </s:if>
	  <s:if test="leaveAppDis=='Approver'">
	 	<s:text name="label.header.common.cancel" var="HCancel"></s:text>
	 	<s:text name="label.common.link.edit" var="HEdit"></s:text>
	  </s:if>
	  <display:table class="tableborder" id="leaveReqsApprovalListId" name="lrappList" requestURI="getAllSubEmpLeaveReqsApproval.action" sort="list" defaultsort="1" defaultorder="ascending">
	    <%
	    	try{
	    		String sLeaveReqsApprovalId = ((LeaveReqsApprovalVO)pageContext.getAttribute("leaveReqsApprovalListId")).getHcmoLeaveReqsApprovalId().toString();
	        	request.setAttribute("leaveReqsApprovalListId", sLeaveReqsApprovalId);    		
	        	String sEmployeeId = ((LeaveReqsApprovalVO)pageContext.getAttribute("leaveReqsApprovalEmployeeId")).getEmpIdObj().getEmployeeId().toString();
	        	request.setAttribute("leaveReqsApprovalEmployeeId", sEmployeeId);
	        	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" headerClass="sortable"/>
	    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveType}" headerClass="sortable"/>
	    <display:column property="dateApplied" title="${HDateApplied}" headerClass="sortable" format="{0,date,MM-dd-yyyy}" />
	    <display:column property="leaveRequested" title="${HNoOfDays}" headerClass="sortable"/>
	    <display:column property="comments" title="${HComments}" headerClass="sortable" maxLength="10"/>
	    
	    <s:hidden name="lrapp.empIdObj.employeeId"></s:hidden>
		<s:if test="leaveAppDis==null || leaveSelfApp!=null">
			 <display:column title="${HApproved}" class="viewedit" media="html">
				 <s:url var="leaveRequestapproved" action="leaveApprovedNotes" escapeAmp="false">
			    		<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
			    		<s:param name="notesEmployeeId" value="lrapp.empIdObj.employeeId"/>
		   		</s:url> 
		     <sj:submit href="%{leaveRequestapproved}" key="Approve" cssClass="submitbutton117" indicator="indicatorLeaveApproverSubEmpForApproId_div" targets="submenu_LeaveForApproval_SearchResult_App_div"></sj:submit>
			</display:column>
			
			<display:column title="${HDisApproved}" class="viewedit" media="html">
				<s:url var="leaveRequestdisapproved" action="leaveDisApprovedNotes" escapeAmp="false">
	               <s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
  	    		   <s:param name="notesEmployeeId" value="lrapp.empIdObj.employeeId"/>
	           </s:url> 
	            <sj:submit href="%{leaveRequestdisapproved}" key="DisApprove" cssClass="submitbutton117" indicator="indicatorLeaveApproverSubEmpForApproId_div" targets="submenu_LeaveForApproval_SearchResult_App_div"></sj:submit>
			</display:column>
		 </s:if>
		 <s:if test="leaveAppDis=='Approver'">
		 	 <display:column title="${HCancel}" class="viewedit" media="html">
			 
				 <s:url var="leaveRequestCancel" action="leaveRequestCancel">
			    		<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
		   		</s:url> 
		     <sj:submit href="%{leaveRequestCancel}" key="Cancel" cssClass="submitbutton117" indicator="indicatorLeaveApproverSubEmpForApproId_div" targets="submenu_LeaveForApproval_SearchResult_App_div"></sj:submit>
			</display:column>
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="setUpLeaveReqsApprovalEditForm" action="setUpLeaveReqsApprovalEditForm">
	               <s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
	           </s:url> 
	            <sj:submit href="%{setUpLeaveReqsApprovalEditForm}" key="Edit" cssClass="submitbutton117" indicator="indicatorLeaveApproverSubEmpForApproId_div" targets="submenu_LeaveForApproval_SearchResult_App_div"></sj:submit>
			</display:column>
		 </s:if>
	    
	  </display:table>  
      <table align="center"> 
   	     <tr>
   		    <td>
    		    <img id="indicatorLeave" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="backButton" action="backLeaveForApprovalButton"></s:url>
	   			 <sj:submit href="%{backButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveForApproval_SearchResult_App_div" indicator="indicatorLeave"/>
   		    </td>
   		   </tr>
   	  </table>
</div>