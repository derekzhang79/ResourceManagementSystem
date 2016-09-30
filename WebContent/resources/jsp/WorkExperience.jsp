<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.WorkExperienceVO"%>

<div id="submenu_workExperience_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.WORKEXPERIENCE_ADD == true">
			<sj:a href="setUpWorkExperience.action" targets="submenu_workExperience_list_div_id" indicator="indicatorSubMenuWorkExperienceId_div" cssClass="link"><s:text name="MTIAddWorkExperience" /></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW == true">
			<sj:a href="getAllWorkExperience.action" targets="submenu_workExperience_list_div_id" indicator="indicatorSubMenuWorkExperienceId_div" cssClass="link"><s:text name="MTIViewWorkExperience"/></sj:a> |
			<sj:a href="workExpSearchForm.action" targets="submenu_workExperience_list_div_id" indicator="indicatorSubMenuWorkExperienceId_div" cssClass="link"><s:text name="MTISearchWorkExperience"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuWorkExperienceId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.workExperience.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HWorkExpEmpName"></s:text>
	  <s:text name="label.header.empExperience.employerName" var="HWorkExpEmployerName"></s:text>
	  <s:text name="label.header.empExperience.jobTitle" var="HWorkExpJobTitle"></s:text>
	  <s:text name="label.header.empExperience.fromDate" var="HWorkExpFromDate"></s:text>
	  <s:text name="label.header.empExperience.toDate" var="HWorkExpToDate"></s:text>
	  <s:text name="label.header.common.comments" var="HWorkExpComment"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_workExperienceList_div_id">
		  <display:table class="tableborder" id="workExperienceListId" name="workexpList" pagesize="${NO_OF_RECORDS}" requestURI="getAllWorkExperience.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sWorkExperienceId = ((WorkExperienceVO)pageContext.getAttribute("workExperienceListId")).getEmpWorkExpId().toString();
		        	request.setAttribute("WorkExperienceId", sWorkExperienceId);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HWorkExpEmpName}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.WORKEXPERIENCE_EMPLOYEERNAME_VIEW == true">
		    	<display:column property="employeerName" title="${HWorkExpEmployerName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.WORKEXPERIENCE_EMPJOBTITLE_VIEW == true">
		    	<display:column property="empJobTitle" title="${HWorkExpJobTitle}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.WORKEXPERIENCE_FROMDATE_VIEW == true">
		    	<display:column property="fromDate" title="${HWorkExpFromDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.WORKEXPERIENCE_TODATE_VIEW == true">
		    	<display:column property="toDate" title="${HWorkExpToDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.WORKEXPERIENCE_COMMENTS_VIEW == true">
		    	<display:column property="comments" title="${HWorkExpComment}" sortable="true" headerClass="sortable" maxLength="10"/>
		    </s:if>
		    <s:if test="#session.WORKEXPERIENCE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewWorkExperience" action="workExperienceView">
						<s:param name="workexp.empWorkExpId" value="#request.WorkExperienceId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_workExperience_list_div_id','%{listViewWorkExperience}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.WORKEXPERIENCE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpWorkExperience" action="setUpWorkExperience">
						<s:param name="workexp.empWorkExpId" value="#request.WorkExperienceId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_workExperience_list_div_id','%{listSetUpWorkExperience}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.WORKEXPERIENCE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteWorkExperience" action="deleteWorkExperience">
						<s:param name="workexp.empWorkExpId" value="#request.WorkExperienceId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_workExperience_list_div_id','%{listDeleteWorkExperience}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="WorkExperience.csv"/>
			 <display:setProperty name="export.excel.filename" value="WorkExperience.xls"/>
			 <display:setProperty name="export.xml.filename" value="WorkExperience.xml"/>
		  </display:table>
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_workExperienceList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       