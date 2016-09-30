<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.WorkExperienceVO"%>

<div id="submenu_EssWorkExperience_List_div">
<img id="indicatorWorkExpList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceForm" value="/setUpEmpWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceView" value="/getEmployeeAllWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
		<s:if test="#session.WORKEXPERIENCE_ADD==true">
			<sj:a href="%{remoteWorkExperienceForm}" indicator="indicatorWorkExpList" targets="submenu_EssWorkExperience_List_div" cssClass="link"><s:text name="label.header.empExperience.add"/></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW==true">
			<sj:a href="%{remoteWorkExperienceView}" indicator="indicatorWorkExpList" targets="submenu_EssWorkExperience_List_div" cssClass="link"><s:text name="label.header.empExperience.view"/></sj:a>
		</s:if>
	</div>		
<br />	
	  
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.workExperience.list"/></span></li></div>

       <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
       <s:text name="label.header.common.empName" var="HName"/>
       <s:text name="label.header.empExperience.employerName" var="HEmpname"/>      
       <s:text name="label.header.empExperience.jobTitle" var="HJobtitle"/>  
       <s:text name="label.header.empExperience.fromDate" var="HFromdate"/>
       <s:text name="label.header.empExperience.toDate" var="HTodate"/>
       <s:text name="label.header.common.comments" var="HComments"/>
       
       <s:if test="#session.WORKEXPERIENCE_VIEW==true">
        	<s:text name="label.header.common.view" var="HView"/>
       </s:if>
       <s:if test="#session.WORKEXPERIENCE_UPDATE==true">
        	<s:text name="label.header.common.edit" var="HEdit"/>
       </s:if>
       <s:if test="#session.WORKEXPERIENCE_DELETE==true">
        	<s:text name="label.header.common.delete" var="HDelete"/>
       </s:if>
        
       <display:table class="tableborder" id="workexpListId" name="workexpList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((WorkExperienceVO)pageContext.getAttribute("workexpListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sWrkexpId=((WorkExperienceVO)pageContext.getAttribute("workexpListId")).getEmpWorkExpId().toString();
			  request.setAttribute("sWorkExperienceId",sWrkexpId);
		  }
           catch(NullPointerException ne){
		  }
		%>
		
	       <display:column property="empIdObj.empFullName" title="${HName}" sortable="false" headerClass="sortable"/> 
	       <display:column property="employeerName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
	       <display:column property="empJobTitle" title="${HJobtitle}" sortable="false" headerClass="sortable"/>
	       <display:column property="fromDate" title="${HFromdate}" sortable="false" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	       <display:column property="toDate" title="${HTodate}" sortable="false" headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
	       <display:column property="comments" title="${HComments}" sortable="false" headerClass="sortable" maxLength="10"/>
              
              
            <s:if test="#session.WORKEXPERIENCE_VIEW==true">
	            <display:column title="${HView}" class="viewedit" media="html">
	               	<s:url var="workExperienceView" action="viewEmpWorkExperience" escapeAmp="false">
						<s:param name="workexp.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		   	<s:param name="workexp.empWorkExpId" value="#request.sWorkExperienceId"/>
		       		</s:url> 
	                <sj:a href="%{workExperienceView}" targets="submenu_EssWorkExperience_List_div" indicator="indicatorWorkExpList"><s:text name="label.common.link.view"/></sj:a>
	           </display:column>
	       </s:if>
            <s:if test="#session.WORKEXPERIENCE_UPDATE==true">
	            <display:column title="${HEdit}" class="viewedit" media="html">
	               	<s:url var="setUpEmpWorkExperienceSingle" action="setUpEmpWorkExperienceSingle" escapeAmp="false">
						<s:param name="workexp.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		   	<s:param name="workexp.empWorkExpId" value="#request.sWorkExperienceId"/>
		       		</s:url> 
	                <sj:a href="%{setUpEmpWorkExperienceSingle}" targets="submenu_EssWorkExperience_List_div" indicator="indicatorWorkExpList"><s:text name="label.common.link.edit"/></sj:a>
	           </display:column>
	       </s:if>
	       <s:if test="#session.WORKEXPERIENCE_DELETE==true">
	          <display:column title="${HDelete}" class="viewedit" media="html">
	                <s:url var="deleteEmpWorkExperience" action="deleteEmpWorkExperience" escapeAmp="false">
						<s:param name="workexp.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		   <s:param name="workexp.empWorkExpId" value="#request.sWorkExperienceId"/>
		       		</s:url>
	                <sj:a href="%{deleteEmpWorkExperience}" targets="submenu_EssWorkExperience_List_div" indicator="indicatorWorkExpList"><s:text name="label.common.link.delete"/></sj:a>
	          </display:column>
	      </s:if>
	      
   </display:table>
<br />
</div>