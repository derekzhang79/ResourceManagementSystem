<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.LeaveReqsApprovalVO"%>
<div id="submenu_LeaveForApproval_SearchResult_Emp_div">
<div class="informationMessageSingle">
	<li>
		<span>
			<s:property value="lrapp.leaveMessageTitle"/>
		</span>
	</li>
</div>

<img id="indicatorSubMenuLeaveForApproval" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.leaveType.name" var="HLeaveType"></s:text>
	  <s:text name="label.header.common.dateApplied" var="HDateApplied"></s:text>
	  <s:text name="label.header.common.noOfDays" var="HNoOfDays"></s:text>
	  <s:text name="label.header.leaveapproveedby.name" var="HApprover"></s:text>
	  <s:text name="label.header.lrapp.dateApprovDisApprov" var="HDateAppDisApp"></s:text>
	  <s:text name="label.header.common.comments" var="HComments"></s:text>
	  <s:if test="employee!=null">
	  	<s:text name="label.header.common.cancel" var="HCancel"></s:text>
	  	<s:text name="label.common.link.edit" var="HEdit"></s:text>
	  </s:if>   
	  <display:table class="tableborder" id="leaveReqsApprovalListId" name="lrappList" requestURI="getAllLeaveReqsApproval.action" sort="list" defaultsort="1" defaultorder="ascending">
	    <%
	    	try{
	    		String sLeaveReqsApprovalId = ((LeaveReqsApprovalVO)pageContext.getAttribute("leaveReqsApprovalListId")).getHcmoLeaveReqsApprovalId().toString();
	        	request.setAttribute("leaveReqsApprovalListId", sLeaveReqsApprovalId);    		
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="empIdObj.empFullName" title="${HEmployeeName}" headerClass="sortable"/>
	    <display:column property="leaveTypeIdObj.leaveTypeName" title="${HLeaveType}" headerClass="sortable"/>
	    <display:column property="dateApplied" title="${HDateApplied}" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="leaveRequested" title="${HNoOfDays}" headerClass="sortable"/>
	    <display:column property="hcmoLeaveApproverId.empFullName" title="${HApprover}" headerClass="sortable"/>
	    <display:column property="dateApprDisappr" title="${HDateAppDisApp}" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	    <display:column property="comments" title="${HComments}" headerClass="sortable" maxLength="10"/>
	    <s:if test="employee=='Employee'">
		    <display:column title="${HCancel}" class="viewedit" media="html">
				 <s:url var="leaveRequestCancel" action="leaveRequestCancel">
			    		<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
		   		</s:url> 
		     <sj:submit href="%{leaveRequestCancel}" key="Cancel" cssClass="submitbutton117" indicator="indicatorLeaveForApprovalCancel" targets="submenu_LeaveForApproval_SearchResult_Emp_div"></sj:submit>
		     <img id="indicatorLeaveForApprovalCancel" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			</display:column>
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="setUpLeaveReqsApprovalEditForm" action="setUpLeaveReqsApprovalEditForm">
	               <s:param name="lrapp.hcmoLeaveReqsApprovalId" value="#request.leaveReqsApprovalListId"/>
	           </s:url> 
	            <sj:submit href="%{setUpLeaveReqsApprovalEditForm}" key="Edit" cssClass="submitbutton117" indicator="indicatorLeaveForApprovalEdit" targets="submenu_LeaveForApproval_SearchResult_Emp_div"></sj:submit>
	            <img id="indicatorLeaveForApprovalEdit" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			</display:column>
		</s:if>
	  </display:table> 
	  <table align="center">
	   <s:if test="employee=='Employee'"> 
	    	 <tr>
	   		    <td>
					<s:url var="backLeaveEmpForAppTabButton" action="backLeaveEmpForAppTabButton"></s:url>
		   			 <sj:submit href="%{backLeaveEmpForAppTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveForApproval_SearchResult_Emp_div" indicator="indicatorLeaveEmpForApp"/>
		   			 <img id="indicatorLeaveEmpForApp" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	   		    </td>
	   		</tr>
   		</s:if>
   	
		<s:if test="employee=='Approved'"> 
	    	 <tr>
	   		    <td>
					<s:url var="backLeaveEmpApproveTabButton" action="backLeaveEmpApproveTabButton"></s:url>
		   			 <sj:submit href="%{backLeaveEmpApproveTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveForApproval_SearchResult_Emp_div" indicator="indicatorLeaveEmpApp"/>
		   			 <img id="indicatorLeaveEmpApp" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	   		    </td>
	   		</tr>
   		</s:if><s:if test="employee=='DisApproved'"> 
	    	 <tr>
	   		    <td>
					<s:url var="backLeaveEmpDisApproveTabButton" action="backLeaveEmpDisApproveTabButton"></s:url>
		   			 <sj:submit href="%{backLeaveEmpDisApproveTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveForApproval_SearchResult_Emp_div" indicator="indicatorLeaveEmpDisApp"/>
		   			 <img id="indicatorLeaveEmpDisApp" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	   		    </td>
	   		</tr>
   		</s:if>
   	  </table>   
</div>   