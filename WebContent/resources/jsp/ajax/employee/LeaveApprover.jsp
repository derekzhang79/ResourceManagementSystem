<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.LeaveApproverVO"%>

<div id="submenu_EssLeaveApprover_List_div">
	<div class="submenu_bg">
		<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
		<s:url var="remoteLeaveApprForm" value="/setUpEmpInsertOrUpdateLeaveApprover.action">
			<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
		</s:url>
		<s:set name="UniqueLeaveApprEmployeeId" value="leaveApprover.hcmoEmployeeId.employeeId"></s:set>
		<s:url var="remoteLeaveApprView" value="/getEmployeeAllLeaveApprover.action">
			<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#UniqueLeaveApprEmployeeId"></s:param>
		</s:url>
		<s:if test="#session.LEAVEAPPROVER_ADD==true">
			<sj:a href="%{remoteLeaveApprForm}" indicator="indicatorLeaveApprList" targets="submenu_EssLeaveApprover_List_div" cssClass="link"><s:text name="label.header.leaveapprover.add"/></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_VIEW==true">
			<sj:a href="%{remoteLeaveApprView}" indicator="indicatorLeaveApprList" targets="submenu_EssLeaveApprover_List_div" cssClass="link"><s:text name="label.header.leaveapprover.view"/></sj:a>
		</s:if>
	</div>		

<br />		 
<img id="indicatorLeaveApprList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.leaveapprover.list" /></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.header.leaveapprover.EmployeeName" var="HEmpname"/>
		<s:text name="label.header.leaveapprover.leaveApprovingEmployee" var="HAppEmpname"/>
		
		<s:if test="#session.LEAVEAPPROVER_VIEW==true">
		    <s:text name="label.header.common.view" var="HView"/>
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_UPDATE==true">			
			<s:text name="label.header.common.edit" var="HEdit"/>
		</s:if>
		<s:if test="#session.LEAVEAPPROVER_DELETE==true">
			<s:text name="label.header.common.delete" var="HDelete"/>
		</s:if>
		
		<display:table class="tableborder" id="leaveApproverListId" name="leaveApproverList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((LeaveApproverVO)pageContext.getAttribute("leaveApproverListId")).getHcmoEmployeeId().getEmployeeId().toString();
			  request.setAttribute("shcmoEmployeeId",sEmpId);
			  String sLeaAppId=((LeaveApproverVO)pageContext.getAttribute("leaveApproverListId")).getHcmoLeaveApproverId().toString();
			  request.setAttribute("sLeaveApproverId",sLeaAppId);
		  }
           catch(NullPointerException ne){
		  }
		%>
		
        <display:column property="hcmoEmployeeId.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
	    <display:column property="hcmoApprovingEmpId.empFullName" title="${HAppEmpname}" sortable="false" headerClass="sortable"/>     

	
		    <s:if test="#session.LEAVEAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="leaveApproverView" action="viewEmpLeaveApprover" escapeAmp="false">
						<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.sLeaveApproverId" />
					</s:url> 
					<sj:a href="%{leaveApproverView}" targets="submenu_EssLeaveApprover_List_div" indicator="indicatorLeaveApprList"><s:text name="label.header.common.view" /></sj:a>
				</display:column>
			</s:if>

			<s:if test="#session.LEAVEAPPROVER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpEmpInsertOrUpdateLeaveApproverSingle" action="setUpEmpInsertOrUpdateLeaveApproverSingle" escapeAmp="false">
						<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.sLeaveApproverId" />
					</s:url> 
					<sj:a href="%{setUpEmpInsertOrUpdateLeaveApproverSingle}" targets="submenu_EssLeaveApprover_List_div" indicator="indicatorLeaveApprList"><s:text name="label.common.link.edit"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.LEAVEAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteEmpLeaveApprover" action="deleteEmpLeaveApprover" escapeAmp="false">
						<s:param name="leaveApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="leaveApprover.hcmoLeaveApproverId" value="#request.sLeaveApproverId" />
					</s:url> 
					<sj:a href="%{deleteEmpLeaveApprover}" targets="submenu_EssLeaveApprover_List_div" indicator="indicatorLeaveApprList"><s:text name="label.common.link.delete"/></sj:a>
				</display:column>
			</s:if>
	</display:table>
<br />
</div>