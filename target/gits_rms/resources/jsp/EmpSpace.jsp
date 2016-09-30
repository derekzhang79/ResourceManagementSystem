<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpSpaceVO"%>

<div id="submenu_EmployeeSpaceId_div">
	<br/>
		<img id="indicatorSubMenuEmployeeSpaceId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<jsp:include page="common/messages.jsp" flush="true"/>
		<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpace.list"/></span></li></div>
		
		<s:if test="errorMessageValueForEmpFileShare != null">
			<ul class="actionMessageSingle">
				<li>
					<span><s:property value="errorMessageValueForEmpFileShare"/></span>
				</li>	
			</ul>
		</s:if>	
     
	    <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.title.empSpace.title" var="HTitle" />
        <s:text name="label.title.empSpace.description" var="HDescription" />
        <s:text name="label.title.empSpace.sharedTo" var="HSharedEmployeeNames" />
        <s:text name="label.title.empSpace.fileView" var="HEmpSpaceViewFile"/>
        <s:text name="label.title.empSpace.makeShared" var="HMakeShare"/>
        <s:text name="label.title.empSpace.makeUnshare" var="HMakeUnshare"/>
        <s:text name="label.common.link.delete" var="HDelete"/>
    
		<div id="display_tag_empSpaceList_div_id">
		<display:table class="tableborder" id="empSpaceListId" name="empSpaceList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpSpace.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
			<%
			  try{
				  String sEmpId=((EmpSpaceVO)pageContext.getAttribute("empSpaceListId")).getHcmoEmpSpaceId().toString();
				  request.setAttribute("sEmpSpaceId",sEmpId);
				 }catch(NullPointerException ne){
			  }
			%>
			
			<display:column property="sharedFileTitle" title="${HTitle}" sortable="true" headerClass="sortable"/>
			<display:column property="sharedFileDesc" title="${HDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
			<display:column property="sharedEmpFirstName" title="${HSharedEmployeeNames}" sortable="true" headerClass="sortable"/>
		
		    <display:column title="${HEmpSpaceViewFile}" class="viewedit" media="html">
		        <s:url var="empSpaceFileDownload" action="empSpaceFileDownload">
		              <s:param name="empSpace.hcmoEmpSpaceId" value="#request.sEmpSpaceId"/>
		        </s:url>
		        <s:a href="%{empSpaceFileDownload}">${empSpaceListId.spaceAttachFileName}</s:a>
		    </display:column>
		    
			<display:column  title="${HMakeShare}" class="viewedit" media="html">
				<s:url var="shareFile" action="shareFile">
					 <s:param name="empSpace.hcmoEmpSpaceId" value="#request.sEmpSpaceId"></s:param>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeSpace_list_div_id','%{shareFile}','');"><s:text name="label.title.empSpace.share"/></s:a>
			</display:column>
			
			<display:column  title="${HMakeUnshare}" class="viewedit" media="html">
				<s:url var="makeUnshare" action="makeUnshare" >
					<s:param name="empSpace.hcmoEmpSpaceId" value="#request.sEmpSpaceId"></s:param>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeSpace_list_div_id','%{makeUnshare}','');"><s:text name="label.title.empSpace.unShare"/></s:a>
			</display:column>
		
			<display:column  title="${HDelete}" class="viewedit" media="html">
				<s:url var="deleteEmpSpace" action="deleteEmpSpace">
					 <s:param name="empSpace.hcmoEmpSpaceId" value="#request.sEmpSpaceId"/>
				</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employeeSpace_list_div_id','%{deleteEmpSpace}','');"><s:text name="Delete"/></s:a>
			</display:column>
		    	
			<display:setProperty name="export.csv.filename" value="EmployeeSpace.csv"/>
			<display:setProperty name="export.excel.filename" value="EmployeeSpace.xls"/>
			<display:setProperty name="export.xml.filename" value="EmployeeSpace.xml"/>
			<s:hidden name="empSpace.hcmoEmpSpaceId"/>
		</display:table>
	</div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_empSpaceList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>      