<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmployeeStatusVO"%>

<div id="submenu_employeeStatus_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.EMPLOYEESTATUS_ADD == true">
	<sj:a href="setUpForInsertOrUpdateEmpStatus.action" targets="submenu_employeeStatus_list_div_id" indicator="indicatorSubMenuEmployeeStatus" cssClass="link"><s:text name="MTIAddEmployeeStatus" /></sj:a> |
</s:if>
<s:if test="#session.EMPLOYEESTATUS_VIEW == true">
	<sj:a href="getAllEmployeeStatus.action" targets="submenu_employeeStatus_list_div_id" indicator="indicatorSubMenuEmployeeStatus" cssClass="link"><s:text name="MTIViewEmployeeStatus"/></sj:a> |
	<sj:a href="employeeStatusSearchForm.action" targets="submenu_employeeStatus_list_div_id" indicator="indicatorSubMenuEmployeeStatus" cssClass="link"><s:text name="MTISearchEmployeeStatus"/></sj:a>
</s:if>
</div>
<br/>
<img id="indicatorSubMenuEmployeeStatus" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empStatus.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.empStatus.name" var="HEmployeeStatus"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_employeeStatusList_div_id">
		  <display:table class="tableborder" id="employeeStatusListId" name="empStatusList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmployeeStatus.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sEmployeeStatusId = ((EmployeeStatusVO)pageContext.getAttribute("employeeStatusListId")).getEmpStatusId().toString();
		        	request.setAttribute("EmployeeStatusId", sEmployeeStatusId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="statusName" title="${HEmployeeStatus}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.EMPLOYEESTATUS_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEmployeeStatus" action="empStatusView">
						<s:param name="empStatus.empStatusId" value="#request.EmployeeStatusId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeStatus_list_div_id','%{listViewEmployeeStatus}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EMPLOYEESTATUS_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEmployeeStatus" action="setUpForInsertOrUpdateEmpStatus">
						<s:param name="empStatus.empStatusId" value="#request.EmployeeStatusId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeStatus_list_div_id','%{listSetUpEmployeeStatus}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EMPLOYEESTATUS_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEmployeeStatus" action="deleteEmployeeStatus">
						<s:param name="empStatus.empStatusId" value="#request.EmployeeStatusId"></s:param>
					</s:url>
				<s:a href="#" onclick="doPostCall('submenu_employeeStatus_list_div_id','%{listDeleteEmployeeStatus}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="EmployeeStatus.csv"/>
			 <display:setProperty name="export.excel.filename" value="EmployeeStatus.xls"/>
			 <display:setProperty name="export.xml.filename" value="EmployeeStatus.xml"/>
		  </display:table>
	  </div>
	  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_employeeStatusList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  