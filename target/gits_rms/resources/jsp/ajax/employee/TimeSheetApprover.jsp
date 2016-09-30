<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.TimeSheetApproverVO"%>

<div id="submenu_EssTimeSheetApprover_List_div"> 
<img id="indicatorTSApprList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:set name="UniqueTSApprEmployeeId" value="timeSheetApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteTimeSheetApprForm" value="/setUpEmpInsertOrUpdateTimeSheetApprover.action">
		<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#UniqueTSApprEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueTSApprEmployeeId" value="timeSheetApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteTimeSheetApprView" value="/getEmployeeAllTimeSheetApprover.action">
		<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#UniqueTSApprEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
		<s:if test="#session.TIMESHEETAPPROVER_ADD==true">
			<sj:a href="%{remoteTimeSheetApprForm}" indicator="indicatorTSApprList" targets="submenu_EssTimeSheetApprover_List_div" cssClass="link"><s:text name="label.header.timesheet.add"/></sj:a> |
		</s:if>
		<s:if test="#session.TIMESHEETAPPROVER_VIEW==true">
			<sj:a href="%{remoteTimeSheetApprView}" indicator="indicatorTSApprList" targets="submenu_EssTimeSheetApprover_List_div" cssClass="link"><s:text name="label.header.timesheet.view"/></sj:a>
		</s:if>
	</div>		
<br />		 
  
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.timesheetapprover.list" /></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.header.timesheet.EmployeeName" var="HEmpname"/>
		<s:text name="label.header.timesheet.timesheetApprovingEmployee" var="HAppEmpname"/>
			
		<s:if test="#session.TIMESHEETAPPROVER_VIEW==true">
		    <s:text name="label.header.common.view" var="HView"/>
		</s:if>	
		<s:if test="#session.TIMESHEETAPPROVER_UPDATE==true">
			<s:text name="label.header.common.edit" var="HEdit"/>
		</s:if>
		 <s:if test="#session.TIMESHEETAPPROVER_DELETE==true">
			<s:text name="label.header.common.delete" var="HDelete"/>
		</s:if>
		
	<display:table class="tableborder" id="timeSheetApproverListId" name="timeSheetApproverList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((TimeSheetApproverVO)pageContext.getAttribute("timeSheetApproverListId")).getHcmoEmployeeId().getEmployeeId().toString();
			  request.setAttribute("shcmoEmployeeId",sEmpId);
			  String sTimeAppId=((TimeSheetApproverVO)pageContext.getAttribute("timeSheetApproverListId")).getHcmoApproverId().toString();
			  request.setAttribute("sTimeSheetApproverId",sTimeAppId);
		  }
           catch(NullPointerException ne){
		  }
		%>
		
        <display:column property="hcmoEmployeeId.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
        <display:column property="hcmoApprovingEmpId.empFullName" title="${HAppEmpname}" sortable="false" headerClass="sortable"/>


				
			<s:if test="#session.TIMESHEETAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="timesheetApproverView" action="viewEmpTimeSheetApprover" escapeAmp="false">
						<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.sTimeSheetApproverId" />
					</s:url> 
					<sj:a href="%{timesheetApproverView}" targets="submenu_EssTimeSheetApprover_List_div" indicator="indicatorTSApprList"><s:text name="label.common.link.view"/></sj:a>
				</display:column>
			</s:if>
			
			<s:if test="#session.TIMESHEETAPPROVER_UPDATE==true">
			    <display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpEmpInsertOrUpdateTimeSheetApproverSingle" action="setUpEmpInsertOrUpdateTimeSheetApproverSingle" escapeAmp="false">
						<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.sTimeSheetApproverId" />
					</s:url> 
					<sj:a href="%{setUpEmpInsertOrUpdateTimeSheetApproverSingle}" targets="submenu_EssTimeSheetApprover_List_div" indicator="indicatorTSApprList"><s:text name="label.common.link.edit"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.TIMESHEETAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteEmpTimeSheetApprover" action="deleteEmpTimeSheetApprover" escapeAmp="false">
						<s:param name="timeSheetApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="timeSheetApprover.hcmoApproverId" value="#request.sTimeSheetApproverId" />
					</s:url> 
					<sj:a href="%{deleteEmpTimeSheetApprover}" targets="submenu_EssTimeSheetApprover_List_div" indicator="indicatorTSApprList"><s:text name="label.common.link.delete"/></sj:a>
				</display:column>
			</s:if>
		</display:table>
<br />
</div>