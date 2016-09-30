<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.JobTitleVO"%>

<s:form action="skippedPermanently" id="jobTitleSearchSkip">

<div id="submenu_jobTitle_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.JOBTITLE_ADD == true">
		<sj:a href="setUpJobTitle.action" targets="submenu_jobTitle_searchList_div_id" indicator="indicatorSubMenuJobTitleSearchResId_div" cssClass="link"><s:text name="MTIAddJobTitle" /></sj:a> |
	</s:if>
	<s:if test="#session.JOBTITLE_VIEW == true">
		<sj:a href="getAllJobTitle.action" targets="submenu_jobTitle_searchList_div_id" indicator="indicatorSubMenuJobTitleSearchResId_div" cssClass="link"><s:text name="MTIViewJobTitle"/></sj:a> |
		<sj:a href="jobTitleSearchForm.action" targets="submenu_jobTitle_searchList_div_id" indicator="indicatorSubMenuJobTitleSearchResId_div" cssClass="link"><s:text name="MTISearchJobTitle"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuJobTitleSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.jobTitle.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.salaryGrade.name" var="HSalaryName"></s:text>
	  <s:text name="label.header.jobTitle.name" var="HJobTitle"></s:text>
	  <s:text name="label.header.common.description" var="HJobTitleDescription"></s:text>
	  <s:text name="label.header.common.comments" var="HJobTitleComments"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_jobTitlesearchList_div_id">		   
	  <display:table class="tableborder" id="jobTitleId" name="jobTitleList" pagesize="${NO_OF_RECORDS}" requestURI="jobTitleSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true"> 
	    <%
	    	try{
	    		String sJobTitleId = ((JobTitleVO)pageContext.getAttribute("jobTitleId")).getJobTitleId().toString();
	        	request.setAttribute("JobTitleId", sJobTitleId);
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="salaryGradeIdObj.salaryName" title="${HSalaryName}" sortable="true" headerClass="sortable"/>
	    <display:column property="jobTitleName" title="${HJobTitle}" sortable="true" headerClass="sortable"/>
	    <display:column property="jobTitleDesc" title="${HJobTitleDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
	    <display:column property="jobTitleComments" title="${HJobTitleComments}" sortable="true" headerClass="sortable" maxLength="10"/>
	    <s:if test="#session.JOBTITLE_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewJobTitle" action="jobTitleView">
					<s:param name="jobTitle.jobTitleId" value="#request.JobTitleId"></s:param>
				</s:url>
				<s:a href="#" onclick="doPostCall('submenu_jobTitle_searchList_div_id','%{listViewJobTitle}','');"><s:text name="View"/></s:a>
			</display:column>
		</s:if>
	    <s:if test="#session.JOBTITLE_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpJobTitle" action="setUpJobTitle">
					<s:param name="jobTitle.jobTitleId" value="#request.JobTitleId"></s:param>
				</s:url>
				<s:a href="#" onclick="doPostCall('submenu_jobTitle_searchList_div_id','%{listSetUpJobTitle}','');"><s:text name="Edit"/></s:a>
			</display:column>
		</s:if>
	    <s:if test="#session.JOBTITLE_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteJobTitle" action="deleteJobTitle">
					<s:param name="jobTitle.jobTitleId" value="#request.JobTitleId"></s:param>
				</s:url>
				<s:a href="#" onclick="doPostCall('submenu_jobTitle_searchList_div_id','%{listSetUpJobTitle}','');"><s:text name="Delete"/></s:a>
			</display:column>
		</s:if>
 		 <display:setProperty name="export.csv.filename" value="JobTitle.csv"/>
		 <display:setProperty name="export.excel.filename" value="JobTitle.xls"/>
		 <display:setProperty name="export.xml.filename" value="JobTitle.xml"/>
	  </display:table>
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_jobTitle_searchList_div_id" indicator="indicatorMailConfigurationJobSerchResId_div" cssClass="submitbutton117"/>
						<img id="indicatorMailConfigurationJobSerchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
							<s:submit value="Skipped Permanently" cssClass="button-midle"></s:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:submit value="Skip" action="skip" cssClass="submitbutton117"></s:submit>
					</td>
				</tr>
		</table>
	</s:if> 
</div>    
</s:form>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_jobTitlesearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  