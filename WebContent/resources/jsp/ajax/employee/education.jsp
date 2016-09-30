<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EducationVO"%>
<%@page import="com.gits.rms.vo.EmployeesVO"%>

<div id="submenu_EssEducation_List_div">
<div class="submenu_bg">
	<s:set name="UniqueEduEmployeeId" value="edu.empIdObj.employeeId"></s:set>
	<s:url var="remoteEduForm" value="/setUpEmpEducation.action">
		<s:param name="edu.empIdObj.employeeId" value="#UniqueEduEmployeeId"></s:param>
	</s:url>
		<s:set name="UniqueEduEmployeeId" value="edu.empIdObj.employeeId"></s:set>
		<s:url var="remoteEduView" value="/getEmployeeAllEducation.action">
	    <s:param name="edu.empIdObj.employeeId" value="#UniqueEduEmployeeId"></s:param>
	</s:url>
	    <s:if test="#session.EDUCATION_ADD==true">
	    <sj:a href="%{remoteEduForm}" indicator="indicatorEduList" targets="submenu_EssEducation_List_div" cssClass="link"><s:text name="label.header.education.add"/></sj:a> |
	</s:if>
	    <s:if test="#session.EDUCATION_VIEW==true">
	    <sj:a href="%{remoteEduView}" indicator="indicatorEduList" targets="submenu_EssEducation_List_div" cssClass="link"><s:text name="label.header.education.view"/></sj:a>
	</s:if>
</div>		
<br />
<img id="indicatorEduList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.education.list"/></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
        <s:text name="label.header.common.empName" var="HEmployeeName"/>
        <s:text name="label.header.education.major" var="HMajor"/>
        <s:text name="label.header.education.year" var="HYear"/>
        <s:text name="label.header.education.grade" var="HGrade"/>
        <s:text name="label.header.common.startDate" var="HStartdate"/>
        <s:text name="label.header.common.enddate" var="HEnddate"/>
        <s:text name="label.header.education.univ" var="HUniversity"/>
        <s:text name="label.header.education.degree" var="HDegree"/>
        
        
        <s:if test="#session.EDUCATION_VIEW==true">
        	<th><s:text name="label.header.common.view" var="HView"/></th>
        </s:if>
  
        
        <s:if test="#session.EDUCATION_UPDATE==true">
        	<th><s:text name="label.header.common.edit" var="HEdit"/></th>
        </s:if>
        <s:if test="#session.EDUCATION_DELETE==true">
        	<th><s:text name="label.header.common.delete" var="HDelete"/></th>
        </s:if>
        
     <display:table class="tableborder" id="eduListId" name="eduList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
            
            <%
		  try
            {
			  String sEmpId=((EducationVO)pageContext.getAttribute("eduListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sEduId=((EducationVO)pageContext.getAttribute("eduListId")).getEmpEducationId().toString();
			  request.setAttribute("sEducationId",sEduId);
		  }
            catch(NullPointerException ne){
		  }
		%>
            <display:column property="empIdObj.empFullName" title="${HEmployeeName}" sortable="false" headerClass="sortable"/>
            <display:column property="eduMajor" title="${HMajor}" sortable="false" headerClass="sortable"/>
            <display:column property="eduYear" title="${HYear}" sortable="false" headerClass="sortable"/>
            <display:column property="eduGrade" title="${HGrade}" sortable="false" headerClass="sortable"/>
            <display:column property="eduStartDate" title="${HStartdate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
            <display:column property="eduEndDate" title="${HEnddate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
            <display:column property="eduUniversity" title="${HUniversity}" sortable="false" headerClass="sortable"/>
            <display:column property="eduDegree" title="${HDegree}" sortable="false" headerClass="sortable"/>
            
            <s:if test="#session.EDUCATION_VIEW=true">
            <display:column title="${HView}" class="viewedit" media="html">
            <s:url var="educationView" action="viewEmpEducation" escapeAmp="false">
            <s:param name="edu.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
            	<s:param name="edu.empEducationId" value="#request.sEducationId"/>
            	</s:url> 
            <sj:a href="%{educationView}" targets="submenu_EssEducation_List_div" indicator="indicatorEduList"><s:text name="label.common.link.view"/></sj:a>
            </display:column>
            
            </s:if>
            <s:if test="#session.EDUCATION_UPDATE==true">
		         <display:column title="${HEdit}" class="viewedit" media="html">
		               	<s:url var="setUpEmpEducationSingle" action="setUpEmpEducationSingle" escapeAmp="false">
							<s:param name="edu.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   	<s:param name="edu.empEducationId" value="#request.sEducationId"/>
			       		</s:url> 
		                <sj:a href="%{setUpEmpEducationSingle}" targets="submenu_EssEducation_List_div" indicator="indicatorEduList"><s:text name="label.common.link.edit"/></sj:a>
		         </display:column>
		    </s:if>
		    <s:if test="#session.EDUCATION_DELETE==true"> 
	         	<display:column title="${HDelete}" class="viewedit" media="html">
	                <s:url var="deleteEmpEducation" action="deleteEmpEducation" escapeAmp="false">
					   <s:param name="edu.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		   <s:param name="edu.empEducationId" value="#request.sEducationId"/>
		       		</s:url>
	                <sj:a href="%{deleteEmpEducation}" targets="submenu_EssEducation_List_div" indicator="indicatorEduList"><s:text name="label.common.link.delete"/></sj:a>
	            </display:column>
	        </s:if>
	        
      	</display:table>
</div>	