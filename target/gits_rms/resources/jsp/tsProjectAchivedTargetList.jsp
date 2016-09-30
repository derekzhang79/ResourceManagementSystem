<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="submenu_AchivedTargetList_Div">
	<div class="submenu_bg">
		<sj:a href="setUpViewTargetGoal.action" targets="submenu_AchivedTargetList_Div" indicator="indicatorSubMenuAchivedTarget" cssClass="link"><s:text name="MTIViewTargets" /></sj:a>
	</div><br/>

	<img id="indicatorSubMenuAchivedTarget" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    
   	<div id="display_tag_emp_achivedTarget_List_div_id">
   		<s:hidden name="assignTargetObj.hcmoTsAssignProjTargetId"/>
   	
   		<s:text name="label.common.message.date" var="HDate"></s:text>
   		<s:text name="label.common.startDate" var="HStartDate"></s:text>
   		<s:text name="label.common.endDate" var="HEndDate"></s:text>
		<s:text name="label.header.target.achieved" var="HTargetAchieved"></s:text>
	  	<s:text name="label.header.target.notes" var="HTargetNotes"></s:text>
   	
		<display:table class="tableborder" id="achivedTargetListId" name="achivedTargetList" defaultsort="1" defaultorder="ascending" export="false">
			<s:if test="#session.ASSIGNEDTARGETMODE=='Daily'">
	    		<display:column property="date" title="${HDate}" sortable="false" headerClass="sortable"/>
	    	</s:if>
	    	<s:else>
	    		<display:column property="startDate" title="${HStartDate}" sortable="false" headerClass="sortable"/>
	    		<display:column property="endDate" title="${HEndDate}" sortable="false" headerClass="sortable"/>
	    	</s:else>
	    	<display:column property="targetAchieved" title="${HTargetAchieved}" sortable="false" headerClass="sortable"/>
	    	<display:column property="targetNotes" title="${HTargetNotes}" sortable="false" headerClass="sortable"/>
		</display:table>
	</div>
</div> 