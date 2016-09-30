<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java"  contentType="text/html;charset=windows-1252" import="java.util.*,com.sun.rowset.CachedRowSetImpl,java.sql.SQLException,java.text.SimpleDateFormat" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.gits.rms.vo.TimeSheetAttachmentVO"%>
<jsp:include page="common/messages.jsp" flush="true"/>
	
<div id="mainmenuTimesheetId_div">
	<table>
<tr>
	<td>
	<div id="submenu_EmployeeBenefitsTimesheetattachmentsId_div">
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.form.fields.lhist.empName" var="HTimeSheetFileUploadEmpName"></s:text>
	  <s:text name="label.title.empSpace.fileName" var="HTimeSheetFileName"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  	<div id="display_tag_TimesheetfileuploadId_div">
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
				<sj:a href="%{deleteBenefit}" targets="submenu_EmployeeBenefitsTimesheetattachmentsId_div" ><s:text name="Delete"/></sj:a>
			</display:column>
		 <display:setProperty name="export.csv.filename" value="EmployeeBenefits.csv"/>
		 <display:setProperty name="export.excel.filename" value="EmployeeBenefits.xls"/>
		 <display:setProperty name="export.xml.filename" value="EmployeeBenefits.xml"/>
	  </display:table>
	  </div>
	</div>
	</td>
</tr>
	
	<br/>
	
	  </table>
</div>  
<div id="timesheeetidTsAttachmentListId_div">

<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		<td align="center" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<!--headerpart-->
						
					</tr>
					<tr>
						<td align="center" valign="top">
							<!--centerpart-->


</td></tr>		
		<tr>
												
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>
</table>

<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_TimesheetfileuploadId_div").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  