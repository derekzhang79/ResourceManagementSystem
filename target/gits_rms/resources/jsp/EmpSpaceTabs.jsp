<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVECONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle">
				<s:text name="label.title.empSpaceModule"/>
			</td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="EmpSpace" class="vidbox2" href="http://www.youtube.com/watch?v=DcAJnnb6Y6Y"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
	
	<s:url var="getSetUpFileUploadTab" action="setUpFileUpload"></s:url>
	<s:url var="getAllMyFilesEmpSpaceTab" action="getAllEmpSpace"></s:url>
	<s:url var="getAllSharedFilesEmpSpaceTab" action="getAllSharedEmpSpace"></s:url>
	<s:url var="getSearchFormEmpSpaceTab" action="empSpaceSearchForm"></s:url>
	<s:url var="getAllUploadsEmpSpaceTab" action="sharedAndUploadedEmpSpace"></s:url>

	<sj:tabbedpanel id="menuEmployeeSpaceTabbedpannel" animate="true">
		<sj:tab id="EmpSpaceSetUPFileUpLoadMainTab" key="MTIAddEmployeeSpace" href="%{getSetUpFileUploadTab}" />
		<sj:tab id="EmpSpaceGetAllMyFilesMainTab" key="MTIViewEmployeeSpace" href="%{getAllMyFilesEmpSpaceTab}" />
		<sj:tab id="EmpSpaceGetAllSharedFilesMainTab" key="MTIViewSharedEmployeeSpace" href="%{getAllSharedFilesEmpSpaceTab}" />
		<sj:tab id="EmpSpaceGetAllSearchMainTab" key="MTIViewSearchEmployeeSpace" href="%{getSearchFormEmpSpaceTab}" />

		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
			<sj:tab id="EmpSpaceGetAllUploadsMainTab" key="MTIViewSharedAndUploadEmployeeSpace"	href="%{getAllUploadsEmpSpaceTab}" />
		</s:if>
	</sj:tabbedpanel>
	
 <script>
	//Pop-up for Video in modules
	jQuery(document).ready(function() {
		try{
   			jQuery(".vidbox2").jqvideobox({
   			'width' : 600,
   			'height' : 400,
    		'getimage' : false,
   			'navigation' : true
   			});	
  		}catch(e){
  
  		 }
	}); 
 </script>
	