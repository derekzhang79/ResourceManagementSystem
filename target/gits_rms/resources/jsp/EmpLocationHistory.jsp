<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmpLocationHistoryVO"%>

<div id="submenu_employeeLocationHistory_list_div_div">
	<div class="submenu_bg">
		<s:if test="#session.LOCATIONHISTORY_ADD == true">
			<sj:a href="setUpEmpLocationHistory.action" targets="submenu_employeeLocationHistory_list_div_div" indicator="indicatorSubMenuEmployeeLocationHistory" cssClass="link"><s:text name="MTIAddEmployeeLocationHistory" /></sj:a> |
		</s:if>
		<s:if test="#session.LOCATIONHISTORY_VIEW == true">
			<sj:a href="getAllEmpLocationHistory.action" targets="submenu_employeeLocationHistory_list_div_div" indicator="indicatorSubMenuEmployeeLocationHistory" cssClass="link"><s:text name="MTIViewEmployeeLocationHistory"/></sj:a> |
			<sj:a href="empLocHistSearchForm.action" targets="submenu_employeeLocationHistory_list_div_div" indicator="indicatorSubMenuEmployeeLocationHistory" cssClass="link"><s:text name="MTISearchEmployeeLocationHistory"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeLocationHistory" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<jsp:include page="common/messages.jsp" flush="true"/>
	  <div class="informationMessageSingle"><li><span><s:text name="label.title.empLocationHistory.view" /></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.employee.name" var="HLocEmployeeName"></s:text>
	  <s:text name="label.header.location.name" var="HLocLocationName"></s:text>
	  <s:text name="label.header.client.name" var="HLocClientName"></s:text>
	  <s:text name="label.common.startDate" var="HLocStart"></s:text>
	  <s:text name="label.common.endDate" var="HLocEnd"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_employeeWorkExperienceList_div_id">
		  <display:table class="tableborder" id="employeeLocationHistoryId" name="elHistList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpLocationHistory.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sEmpLocationHistoryId = ((EmpLocationHistoryVO)pageContext.getAttribute("employeeLocationHistoryId")).getHcmoEmpLocHistoryId().toString();
		        	request.setAttribute("LocationHistoryId", sEmpLocationHistoryId);	
		    	}catch(NullPointerException ne){
		        }
		    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HLocEmployeeName}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.LOCATIONHISTORY_LOCATIONNAME_VIEW == true">
		    	<display:column property="locationIdObj.locationName" title="${HLocLocationName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LOCATIONHISTORY_COMPANYNAME_VIEW == true">
		    	<display:column property="clientIdObj.companyName" title="${HLocClientName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LOCATIONHISTORY_STARTDATE_VIEW == true">
		    	<display:column property="startDate" title="${HLocStart}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.LOCATIONHISTORY_ENDDATE_VIEW == true">
		    	<display:column property="endDate" title="${HLocEnd}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		     <s:if test="#session.LOCATIONHISTORY_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEmpLocationHistory" action="empLocationHistoryView">
						<s:param name="elhist.hcmoEmpLocHistoryId" value="#request.LocationHistoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeLocationHistory_list_div_div','%{listViewEmpLocationHistory}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LOCATIONHISTORY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEmpLocationHistory" action="setUpEmpLocationHistory">
						<s:param name="elhist.hcmoEmpLocHistoryId" value="#request.LocationHistoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeLocationHistory_list_div_div','%{listSetUpEmpLocationHistory}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.LOCATIONHISTORY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEmpLocationHistory" action="deleteEmpLocationHistory">
						<s:param name="elhist.hcmoEmpLocHistoryId" value="#request.LocationHistoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeLocationHistory_list_div_div','%{listDeleteEmpLocationHistory}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="EmployeeLocationHistory.csv"/>
			 <display:setProperty name="export.excel.filename" value="EmployeeLocationHistory.xls"/>
			 <display:setProperty name="export.xml.filename" value="EmployeeLocationHistory.xml"/>
		  </display:table>
	  </div>
 </div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_employeeWorkExperienceList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       