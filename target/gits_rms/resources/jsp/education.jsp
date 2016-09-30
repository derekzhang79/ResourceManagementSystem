<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EducationVO"%>

<div id="submenu_education_list_div_id">
		<div class="submenu_bg">
			<s:if test="#session.EDUCATION_ADD == true">
				<sj:a href="setUpEducation.action" targets="submenu_education_list_div_id" indicator="indicatorSubMenuEducation" cssClass="link"><s:text name="MTIAddEducation" /></sj:a> |
			</s:if>
			<s:if test="#session.EDUCATION_VIEW == true">
				<sj:a href="getAllEducation.action" targets="submenu_education_list_div_id" indicator="indicatorSubMenuEducation" cssClass="link"><s:text name="MTIViewEducation"/></sj:a> |
				<sj:a href="educationSearchForm.action" targets="submenu_education_list_div_id" indicator="indicatorSubMenuEducation" cssClass="link"><s:text name="MTISearchEducation"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuEducation" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.education.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HEducationEmpName"></s:text>
	  <s:text name="label.header.education.major" var="HEducationMajor"></s:text>
	  <s:text name="label.header.education.year" var="HEducationYear"></s:text>
	  <s:text name="label.header.education.grade" var="HEducationGrade"></s:text>
	  <s:text name="label.header.common.startDate" var="HEducationStartDate"></s:text>
	  <s:text name="label.header.common.enddate" var="HEducationEndDate"></s:text>
	  <s:text name="label.header.education.univ" var="HEducationUniv"></s:text>
	  <s:text name="label.header.education.degree" var="HEducationDegree"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_educationList_div_id">
		  <display:table class="tableborder" id="educationListId" name="eduList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEducation.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sEducationId = ((EducationVO)pageContext.getAttribute("educationListId")).getEmpEducationId().toString();
		        	request.setAttribute("EducationId", sEducationId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HEducationEmpName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.EDUCATION_EDUMAJOR_VIEW == true">
		    	<display:column property="eduMajor" title="${HEducationMajor}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUYEAR_VIEW == true">
		    	<display:column property="eduYear" title="${HEducationYear}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUGRADE_VIEW == true">
		    	<display:column property="eduGrade" title="${HEducationGrade}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUSTARTDATE_VIEW == true">
		    	<display:column property="eduStartDate" title="${HEducationStartDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUENDDATE_VIEW == true">
		    	<display:column property="eduEndDate" title="${HEducationEndDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUUNIVERSITY_VIEW == true">
		    	<display:column property="eduUniversity" title="${HEducationUniv}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_EDUDEGREE_VIEW == true">
		    	<display:column property="eduDegree" title="${HEducationDegree}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EDUCATION_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEducation" action="educationView">
						<s:param name="edu.empEducationId" value="#request.EducationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_education_list_div_id','%{listViewEducation}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EDUCATION_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEducation" action="setUpEducation">
						<s:param name="edu.empEducationId" value="#request.EducationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_education_list_div_id','%{listSetUpEducation}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EDUCATION_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEducation" action="deleteEducation">
						<s:param name="edu.empEducationId" value="#request.EducationId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_education_list_div_id','%{listDeleteEducation}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Education.csv"/>
			 <display:setProperty name="export.excel.filename" value="Education.xls"/>
			 <display:setProperty name="export.xml.filename" value="Education.xml"/>
		  </display:table>
	  </div>
	</div>
  <script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_educationList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script> 