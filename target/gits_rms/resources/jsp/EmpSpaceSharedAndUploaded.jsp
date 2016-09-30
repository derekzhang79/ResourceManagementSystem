
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpSpaceVO"%>

<div id="submenu_employeeSpaceSharedAndUplod_list_div_id">
	<br/>
		<img id="indicatorSubMenuEmployeeSpaceShareanduploadId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpaceSharedAndUploaded.list"/></span></li></div>
	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	<s:text name="label.title.empSpace.title" var="HEmpSpaceTitle"/>
    <s:text name="label.title.empSpace.description" var="HEmpSpaceDescription"/>
    <s:text name="label.title.empSpace.uploadedBy" var="HEmpSpaceUploadedBy"/>
    <s:text name="label.title.empSpace.sharedTo" var="HEmpSpaceSharedTo"/>
    <s:text name="label.title.empSpace.fileView" var="HEmpSpaceFileView"/>
   
   <div id="display_tag_empSpaceSharedandUploadList_div_id">
	   <display:table class="tableborder" name="empSpaceListForSharedAndUploaded" id="empSpaceListForSharedAndUploadedId" pagesize="${NO_OF_RECORDS}" requestURI="sharedAndUploadedEmpSpace.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
	
			<%
				try{
					String sempSpaceSharedId=((EmpSpaceVO)pageContext.getAttribute("empSpaceListForSharedAndUploadedId")).getHcmoEmpSpaceId().toString();
					request.setAttribute("hcmoEmpSpaceId",sempSpaceSharedId);
				}catch(NullPointerException ne){
	        	} 
			%>
			
			<display:column property="sharedFileTitle" title="${HEmpSpaceTitle}" sortable="true" headerClass="sortable"/>
			<display:column property="sharedFileDesc" title="${HEmpSpaceDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
			<display:column property="empIdObj.empFirstName" title="${HEmpSpaceUploadedBy}" sortable="true" headerClass="sortable" style="width:80px;"/>
			<display:column property="sharedEmpFirstName" title="${HEmpSpaceSharedTo}" sortable="true" headerClass="sortable"/>
	            
	        <display:column title="${HEmpSpaceFileView}" class="viewedit" media="html">
				<s:url var="empSpaceFileDownload" action="empSpaceFileDownload">
	    		   <s:param name="empSpace.hcmoEmpSpaceId" value="#request.hcmoEmpSpaceId"/>
	 			</s:url>
				<s:a href="%{empSpaceFileDownload}">${empSpaceListForSharedAndUploadedId.spaceAttachFileName}</s:a>
	 		</display:column>
			
			<s:hidden name="empSpace.hcmoEmpSpaceId"/>
			<s:hidden name="empSpace.empIdObj.employeeId"/>
			
			 <display:setProperty name="export.csv.filename" value="SharedAndUploadedFiles.csv"/>
			 <display:setProperty name="export.excel.filename" value="SharedAndUploadedFiles.xls"/>
			 <display:setProperty name="export.xml.filename" value="SharedAndUploadedFiles.xml"/>
		</display:table>
   </div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_empSpaceSharedandUploadList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    