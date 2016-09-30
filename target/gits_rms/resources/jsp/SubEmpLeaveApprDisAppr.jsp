<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.LeaveReqsApprovalVO"%>

<div id="submenu_LeaveApprover_Result_div">
<div class="informationMessageSingle">
	<li>
		<span>
		<s:if test="approved!=null">
			<s:text name="label.title.Leave.leaveApproved"/>
		</s:if>
		<s:elseif test="disApproved!=null">
			<s:text name="label.title.Leave.leaveDisApproved"/>
		</s:elseif>
		</span>
	</li>
</div>
<br />
<img id="indicatorSubMenuCountrySubEmpLeaveApprId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.name" var="HLeaveType"></s:text>
	  <s:text name="label.header.common.dateApplied" var="HDateApplied"></s:text>
	  <s:text name="label.header.common.noOfDays" var="HNoOfDays"></s:text>
	   <s:text name="label.header.leaveapproveedby.name" var="HApprover"></s:text>
	   <s:text name="label.header.lrapp.dateApprovDisApprov" var="HDateAppDisApp"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  		   
	  <display:table class="tableborder" id="leaveReqsApprovalListId" name="lrappList" requestURI="getAllSubEmpApprovedList.action" sort="list" defaultsort="1" defaultorder="ascending">
	    <%
	    	try{
	    		String sLeaveReqsApprovalId = ((LeaveReqsApprovalVO)pageContext.getAttribute("leaveReqsApprovalListId")).getHcmoLeaveReqsApprovalId().toString();
	        	request.setAttribute("leaveReqsApprovalListId", sLeaveReqsApprovalId);    		
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" headerClass="sortable"/>
	    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveType}" headerClass="sortable"/>
	    <display:column property="dateApplied" title="${HDateApplied}" headerClass="sortable" format="{0,date,MM-dd-yyyy}" />
	    <display:column property="leaveRequested" title="${HNoOfDays}" headerClass="sortable"/>
	     <display:column property="hcmoLeaveApproverId.empFullName" title="${HApprover}" headerClass="sortable"/>
	    <display:column property="dateApprDisappr" title="${HDateAppDisApp}" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="comments" title="${HComments}" headerClass="sortable" maxLength="10"/>
	    
	  </display:table>    
	  <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorLeaveAppDisApp" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="backApproverApprovedTabButton" action="backApproverApprovedTabButton"></s:url>
	   			 <sj:submit href="%{backApproverApprovedTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveApprover_Result_div" indicator="indicatorLeaveAppDisApp"/>
   		    </td>
   		</tr>
   	  </table>
</div>