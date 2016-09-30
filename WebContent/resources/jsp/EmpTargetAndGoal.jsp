<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpTargetAndGoalVO"%>

<div id="submenu_Emp_TargetandGoalId_div">
<br/>
<img id="indicatorSubMenuEmpTargetandGoalId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.target.goal.list.emp"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HTsProjAssignedEmpName"></s:text>
	  <s:text name="label.header.project.projectName" var="HTsProjAssignedProjectName"></s:text>
	  <s:text name="label.header.projectActivity.name" var="HTsProjAssignedProjectActivityName"></s:text>
	  <s:text name="label.header.targets.name" var="HTargetsName"></s:text>
	  <s:text name="label.header.target.achieved" var="HTargetAchieved"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  		   
	  <display:table class="tableborder" id="empTargetGoalListId" name="empTargetGoalList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpTargetAndGoal.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
	    <%
	    	try{
	    		String sEmpTGId = ((EmpTargetAndGoalVO)pageContext.getAttribute("empTargetGoalListId")).getHcmoEmpTgId().toString();
	        	request.setAttribute("EmpTGId", sEmpTGId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="employeeName.empFullName" title="${HTsProjAssignedEmpName}" sortable="true" headerClass="sortable"/>
	    <display:column property="projectName.projectName" title="${HTsProjAssignedProjectName}" sortable="true" headerClass="sortable"/>
	    <display:column property="projectActivityName.activityName" title="${HTsProjAssignedProjectActivityName}" sortable="true" headerClass="sortable"/>
	    <display:column property="empTargetName" title="${HTargetsName}" sortable="true" headerClass="sortable"/>
	    <display:column property="empTargetAchieved" title="${HTargetAchieved}" sortable="true" headerClass="sortable"/>
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewEmpTargetGoal" action="viewEmpTargetAndGoal">
					<s:param name="empTAGObj.hcmoEmpTgId" value="#request.EmpTGId"></s:param>
				</s:url>
				<sj:a href="%{listViewEmpTargetGoal}" targets="submenu_Emp_TargetandGoalId_div" indicator="indicatorSubMenuEmpTargetandGoalId_div"><s:text name="View"/></sj:a>
			</display:column>
		 <display:setProperty name="export.csv.filename" value="TargetandGoal.csv"/>
		 <display:setProperty name="export.excel.filename" value="TargetandGoal.xls"/>
		 <display:setProperty name="export.xml.filename" value="TargetandGoal.xml"/>
	  </display:table>
	  <table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorViewTargetnadGoalBackId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="setUpViewTargetGoal" action="setUpViewTargetGoal"></s:url>
	   			 <sj:submit href="%{setUpViewTargetGoal}"  key="Back" cssClass="submitbutton117" targets="submenu_Emp_TargetandGoalId_div" indicator="indicatorViewTargetnadGoalBackId_div"/>
   		    </td>
   		</tr>
   	  </table>
  </div>    