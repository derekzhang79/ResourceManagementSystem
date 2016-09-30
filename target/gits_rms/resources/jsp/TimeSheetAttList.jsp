<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java"  contentType="text/html;charset=windows-1252" import="java.util.*,java.sql.SQLException,java.text.SimpleDateFormat" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.gits.rms.vo.TimeSheetAttachmentVO"%>
<div id="targetid_deleteTimeSheetAttachments"><div id="mainmenuTsAttListId_div"/></div>
	<jsp:include page="common/messages.jsp" flush="true"/>
<div id="mainmenuTsAttListId_div">
<table width="100%">
<tr>
	<td>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpace.list"/></span></li></div>
	<div id="submenu_EmployeeBenefits_div">
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.form.fields.lhist.empName" var="HTimeSheetFileUploadEmpName"></s:text>
	  <s:text name="label.title.empSpace.fileView" var="HTimeSheetFileName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  	<div id="display_tag_tsfileupload_div_id">
	  <display:table class="tableborder" id="TimeSheetAttachListId" name="timeSheetAttachList" pagesize="${NO_OF_RECORDS}" requestURI="getAllTmesheetFileAttachment.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
	   <%
	   	  try{
	   		String sTimeSheetAttachId = ((TimeSheetAttachmentVO)pageContext.getAttribute("TimeSheetAttachListId")).getHcmoTsAttachmentId().toString();
	    	request.setAttribute("HcmoBenefitsId", sTimeSheetAttachId);   		  
	   	  }catch(NullPointerException ne){
	      }
	    	
	    %>
	    <display:column property="hcmoEmployeeId.empFirstName" title="${HTimeSheetFileUploadEmpName}" sortable="true" headerClass="sortable"/>
	    <display:column property="fileName" href="timesheetFileDownload.action" paramId="tsAttach.hcmoTsAttachmentId" paramProperty="hcmoTsAttachmentId" title="${HTimeSheetFileName}" sortable="true" headerClass="sortable"/>
	    
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="deleteBenefit" action="deleteTimeSheetAttachments">
					<s:param name="tsAttach.hcmoTsAttachmentId" value="#request.HcmoBenefitsId"></s:param>
				</s:url>
				<sj:a href="%{deleteBenefit}" targets="targetid_deleteTimeSheetAttachments" ><s:text name="Delete"/></sj:a>
			</display:column>
		 <display:setProperty name="export.csv.filename" value="TimeSheetAttachments.csv"/>
		 <display:setProperty name="export.excel.filename" value="TimeSheetAttachments.xls"/>
		 <display:setProperty name="export.xml.filename" value="TimeSheetAttachments.xml"/>
	  </display:table>
	  </div>
	</div>
	</td>
</tr>
	
	<br/>
	
	  </table>
</div>  

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_tsfileupload_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  