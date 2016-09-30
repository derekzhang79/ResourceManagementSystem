<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.EmpSpaceVO"%>

<div id="submenu_employeeSpaceSearchResult_list_div_id">
	<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpaceSearch.list"/></span></li></div>
	
	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	<s:text name="label.title.empSpace.title" var="HEmpSpaceTitle"/>
	<s:text name="label.title.empSpace.description" var="HEmpSpaceDescription"/>
	<s:text name="label.title.empSpace.sharedTo" var="HEmpSpaceSharedTo"/>
	<s:text name="label.title.empSpace.fileView" var="HEmpSpaceFileView"/>
	
	 <div id="display_tag_empspaceSearchList_div_id">
		 <display:table class="tableborder" name="empSpaceList" id="empSpaceListId" pagesize="${NO_OF_RECORDS}" requestURI="empspaceSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		  <%
				try{
					String sempSpaceSharedId=((EmpSpaceVO)pageContext.getAttribute("empSpaceListId")).getHcmoEmpSpaceId().toString();
					request.setAttribute("hcmoEmpSpaceId",sempSpaceSharedId);
				}catch(NullPointerException ne){
	        	} 
			%>
		 
		 <display:column property="sharedFileTitle" title="${HEmpSpaceTitle}" sortable="true" headerClass="sortable"/> 
		 <display:column property="sharedFileDesc" title="${HEmpSpaceDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
		 <display:column property="sharedEmpFirstName" title="${HEmpSpaceSharedTo}" sortable="true" headerClass="sortable"/>  
		 <display:column title="${HEmpSpaceFileView}" class="viewedit" media="html">
				<s:url var="empSpaceFileDownload" action="empSpaceFileDownload">
	    		   <s:param name="empSpace.hcmoEmpSpaceId" value="#request.hcmoEmpSpaceId"/>
	 			</s:url>
				<s:a href="%{empSpaceFileDownload}" tooltip="View File">${empSpaceListId.spaceAttachFileName}</s:a>
	 	</display:column>
	 		
	 		<s:hidden name="empSpace.hcmoEmpSpaceId"/>
	 		
	 		<display:setProperty name="export.csv.filename" value="SharedFiles.csv"/>
			<display:setProperty name="export.excel.filename" value="SharedFiles.xls"/>
			<display:setProperty name="export.xml.filename" value="SharedFiles.xml"/>
	</display:table>
	 </div>
	 
	<table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpSpaceSearchResultResult" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="backEmpSpaceSearchTabButton" action="backEmpSpaceSearchTabButton"></s:url>
	   			 <sj:submit href="%{backEmpSpaceSearchTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_employeeSpaceSearchResult_list_div_id" indicator="indicatorEmpSpaceSearchResult"/>
   		    </td>
   		</tr>
   	  </table>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_empspaceSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   