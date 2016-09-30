<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmployeeReportToVO"%>

<div id="submenu_employeeReportTo_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.REPORTTO_ADD == true">
			<sj:a href="setUpEmployeeReportTo.action" targets="submenu_employeeReportTo_searchList_div_id" indicator="indicatorSubMenuEmployeeReportToSearchResId_div" cssClass="link"><s:text name="MTIAddEmployeeReportTo" /></sj:a> |
		</s:if>
		<s:if test="#session.REPORTTO_VIEW == true">
			<sj:a href="getAllEmployeeReportTo.action" targets="submenu_employeeReportTo_searchList_div_id" indicator="indicatorSubMenuEmployeeReportToSearchResId_div" cssClass="link"><s:text name="MTIViewEmployeeReportTo"/></sj:a> |
			<sj:a href="empReportToSearcForm.action" targets="submenu_employeeReportTo_searchList_div_id" indicator="indicatorSubMenuEmployeeReportToSearchResId_div" cssClass="link"><s:text name="MTISearchEmployeeReportTo"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuEmployeeReportToSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		
	<div class="informationMessageSingle"><li><span><s:text name="label.title.employeeReportTo.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HReportToEmpName"></s:text>
	  <s:text name="label.header.employeeReportTo.Supervisor" var="HReportToSupervision"></s:text>
	  <s:text name="label.header.employeeReportTo.ReportingMode" var="HReportToReportingToo"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_employeeReportToSearchList_div_id">
		   <display:table class="tableborder" id="employeeReportToListId" name="empList" pagesize="${NO_OF_RECORDS}" requestURI="empReportToSearcResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sEmployeeReportToId = ((EmployeeReportToVO)pageContext.getAttribute("employeeReportToListId")).getEmpReportToId().toString();
		        	request.setAttribute("EmployeeReportToId", sEmployeeReportToId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HReportToEmpName}" sortable="true" headerClass="sortable"/>
		    <display:column property="supEmpNumber.empFullName" title="${HReportToSupervision}" sortable="true" headerClass="sortable"/>
		    <display:column property="empRepReportingModeValue" title="${HReportToReportingToo}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.REPORTTO_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEmployeeReportTo" action="employeeReportToView">
						<s:param name="emp.empReportToId" value="#request.EmployeeReportToId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeReportTo_searchList_div_id','%{listViewEmployeeReportTo}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.REPORTTO_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEmployeeReportTo" action="setUpEmployeeReportTo">
						<s:param name="emp.empReportToId" value="#request.EmployeeReportToId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeReportTo_searchList_div_id','%{listSetUpEmployeeReportTo}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.REPORTTO_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEmployeeReportTo" action="deleteEmployeeReportTo">
						<s:param name="emp.empReportToId" value="#request.EmployeeReportToId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeReportTo_searchList_div_id','%{listDeleteEmployeeReportTo}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="ReportTo.csv"/>
			 <display:setProperty name="export.excel.filename" value="ReportTo.xls"/>
			 <display:setProperty name="export.xml.filename" value="ReportTo.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_employeeReportToSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       