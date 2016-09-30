<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmployeeLeaveQuotaVO"%>

<div id="submenu_leaveQuota_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.LEAVEQUOTA_ADD == true">
			<sj:a href="setUpEmployeeLeaveQuota.action" targets="submenu_leaveQuota_list_div_id" indicator="indicatorSubMenuLeaveQuota" cssClass="link"><s:text name="MTIAddLeaveQuota" /></sj:a> |
		</s:if>
		<s:if test="#session.LEAVEQUOTA_VIEW == true">
			<sj:a href="getAllEmployeeLeaveQuota.action" targets="submenu_leaveQuota_list_div_id" indicator="indicatorSubMenuLeaveQuota" cssClass="link"><s:text name="MTIViewLeaveQuota"/></sj:a> |
			<sj:a href="leaveQuotaSearchForm.action" targets="submenu_leaveQuota_list_div_id" indicator="indicatorSubMenuLeaveQuota" cssClass="link"><s:text name="MTISearchLeaveQuota"/></sj:a>
		</s:if>
	</div>
	<br/><img id="indicatorSubMenuLeaveQuota" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.employeeLeaveQuota.list"/></span></li></div>
	<s:div cssClass="helpinformationmessage">
	    <s:text name="label.header.employeeLeaveQuota.msg.info"/>
	</s:div>	    	
	<s:div cssClass="helpinformationmessage">	    	
	    <s:text name="label.header.employeeLeaveQuota.msg.information"/>
	</s:div>
	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>		   
	        <s:text name="label.header.common.empName" var="HELQEmployeeName"/> 
	        <s:text name="label.header.leaveType.name" var="HELQLeaveName"/> 
	        <s:text name="label.header.employeeLeaveQuota.year" var="HELQYear"/>
	        <s:text name="label.header.employeeLeaveQuota.daysAlloted" var="HELQNoOfDays"/>
	        <s:text name="label.header.employeeLeaveQuota.preLeaveCarryForward" var="HELQPrvYearCarryForward"/>
	    	<s:text name="label.header.employeeLeaveQuota.leaveTaken" var="HELQleaveTaken"/>
	    	<s:text name="label.header.employeeLeaveQuota.carryForward" var="HELQleaveCarryForward"/>
	    	<s:text name="label.common.link.view" var="HView"></s:text>
	    	<s:text name="label.common.link.edit" var="HEdit"></s:text>
	   	
	   	
	   	<div id="display_tag_leaveQuotaList_div_id">
		   	<display:table class="tableborder" id="employeeLeaveQuotaListId" name="empLeaveList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmployeeLeaveQuota.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sEmployeeLeaveQuotaId = ((EmployeeLeaveQuotaVO)pageContext.getAttribute("employeeLeaveQuotaListId")).getEmpLeaveQuotaId().toString();
		        	request.setAttribute("EmployeeLeaveQuotaId", sEmployeeLeaveQuotaId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		   		<display:column property="empIdObj.empFullName" title="${HELQEmployeeName}" sortable="true" headerClass="sortable"/>
		   		<s:if test="#session.LEAVEQUOTA_LEAVETYPE_VIEW==true">
		   			<display:column property="leaveTypeIdObj.leaveTypeName" title="${HELQLeaveName}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		<s:if test="#session.LEAVEQUOTA_YEAR_VIEW==true">
		   			<display:column property="year" title="${HELQYear}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		<s:if test="#session.LEAVEQUOTA_LEAVEALLOTTEDDAYS_VIEW==true">
		   			<display:column property="leaveAllottedDays" title="${HELQNoOfDays}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		<s:if test="#session.LEAVEQUOTA_PRELEAVECARRYFORWARD==true">
		   			<display:column property="previousLeaveCarryDays" title="${HELQPrvYearCarryForward}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		<s:if test="#session.LEAVEQUOTA_EMPLEAVEREQUEST==true">
		   			<display:column property="empLeaveRequest" title="${HELQleaveTaken}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		<s:if test="#session.LEAVEQUOTA_EMPLEAVEPENDING==true">
		   			<display:column property="empLeavePending" title="${HELQleaveCarryForward}" sortable="true" headerClass="sortable"/>
		   		</s:if>
		   		
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEmployeeLeaveQuota" action="employeeLeaveQuotaView">
						<s:param name="empLeaveQuota.empLeaveQuotaId" value="#request.EmployeeLeaveQuotaId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveQuota_list_div_id','%{listViewEmployeeLeaveQuota}','');"><s:text name="View"/></s:a>
				</display:column>
				<s:if test="#session.LEAVEQUOTA_UPDATE == true">
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="listSetUpEmployeeLeaveQuota" action="setUpEmployeeLeaveQuota">
							<s:param name="empLeaveQuota.empLeaveQuotaId" value="#request.EmployeeLeaveQuotaId"></s:param>
						</s:url>
					<s:a href="#" onclick="doPostCall('submenu_leaveQuota_list_div_id','%{listSetUpEmployeeLeaveQuota}','');"><s:text name="Edit"/></s:a>
					</display:column>
				</s:if>
					
				<display:setProperty name="export.csv.filename" value="LeaveQuota.csv"/>
			 	<display:setProperty name="export.excel.filename" value="LeaveQuota.xls"/>
			 	<display:setProperty name="export.xml.filename" value="LeaveQuota.xml"/>
			</display:table>
	   	</div>
	   	
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_leaveQuotaList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  