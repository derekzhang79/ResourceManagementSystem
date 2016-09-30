<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.GoalVO"%>

<div id="submenu_GoalId_div">
<div class="submenu_bg">
	<sj:a href="setUpGoalForm.action" targets="submenu_GoalId_div" indicator="indicatorSubMenuGoalId_div" cssClass="link"><s:text name="MTIAddGoal" /></sj:a> |
	<sj:a href="getAllGoal.action" targets="submenu_GoalId_div" indicator="indicatorSubMenuGoalId_div" cssClass="link"><s:text name="MTIViewGoal"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuGoalId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.goal.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.goal.name" var="HGoalName"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  <display:table class="tableborder" id="goalListId" name="goalNameList" pagesize="${NO_OF_RECORDS}" requestURI="getAllGoal.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
	    <%
	    	try{
	    		String sGoalId = ((GoalVO)pageContext.getAttribute("goalListId")).getHcmoGoalId().toString();
	        	request.setAttribute("Goal", sGoalId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="goalName" title="${HGoalName}" sortable="true" headerClass="sortable"/>
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpGoal" action="setUpGoalForm">
					<s:param name="goal.hcmoGoalId" value="#request.Goal"></s:param>
				</s:url>
				<sj:a href="%{listSetUpGoal}" targets="submenu_GoalId_div" indicator="indicatorSubMenuGoalId_div"><s:text name="Edit"/></sj:a>
			</display:column>
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteGoal" action="deleteGoal">
					<s:param name="goal.hcmoGoalId" value="#request.Goal"></s:param>
				</s:url>
				<sj:a href="%{listDeleteGoal}" targets="submenu_GoalId_div" indicator="indicatorSubMenuGoalId_div"><s:text name="Delete"/></sj:a>
			</display:column>
	  </display:table>
</div>