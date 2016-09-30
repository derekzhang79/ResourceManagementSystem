<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="submenu_AssignedProject_TargetForm">
<div class="submenu_bg">
	<sj:a href="getAllEmpAssignedTargetList.action" targets="submenu_AssignedProject_TargetForm" indicator="indicatorSubMenuAssignedProject_TargetForm" cssClass="link"><s:text name="MTIViewMyTaregtGoal"/></sj:a>
</div><br/>
	<img id="indicatorSubMenuAssignedProject_TargetForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    
    <s:form action="UpdateTsAchievedTarget">
    	<table class="maintable">
	    	<tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
					 <s:text name="label.title.targetsAchived.add"/>
                </td>
	         </tr>
	     	 <tr>
	            <td class="forminner"><table class="tablealign"> 
	         	
	         	<tr>
					<td class="inputtext"><s:text name="label.header.common.empName" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.employeeObj.empFirstName"/></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.project.projectName" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.proAssignObj.projectName.projectName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.projectActivity.name" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.proAssignObj.projectActivityId.activityName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.targets.name" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.targetObj.targetName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.targets.type" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.targetObj.targetTypeObj.targetTypeName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.targets.value" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.targetObj.targetValue" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.targets.mode" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.targetObj.targetMode" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.common.startDate" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.proAssignObj.projectStartDate" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.common.endDate" /></td>
					<td class="employeedisplaytd"><s:property value="assignTargetObj.proAssignObj.projectEndDate" /></td>
				</tr>
				<s:if test="#session.ASSIGNEDTARGETMODE=='Daily'">
					<tr>
						<td class="inputtext"><s:text name="label.common.message.date.mandatory"/></td>
						<td class="employeeinputtd"><sj:datepicker name="achivedTargetObj.date" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="achivedTargetObj.date" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.startDate"/></td>
						<td class="employeeinputtd"><sj:datepicker name="achivedTargetObj.startDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="achivedTargetObj.startDate" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.enddate"/></td>
						<td class="employeeinputtd"><sj:datepicker name="achivedTargetObj.endDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="achivedTargetObj.endDate" /></td>
					</tr>
				</s:else>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.target.achieved"/></td>
	         	    <td class="employeeinputtd"><s:textarea name="achivedTargetObj.targetAchieved" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         	    <td class="inputerrormessage"><s:fielderror fieldName="achivedTargetObj.targetAchieved" /></td>
 				</tr>
	 			
 				<tr>
 					<td class="inputtext"><s:text name="label.form.fields.target.notes"/></td>
	         	    <td class="employeeinputtd"><s:textarea name="achivedTargetObj.targetNotes" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         	    <td class="inputerrormessage"><s:fielderror fieldName="achivedTargetObj.targetNotes" /></td>
 				</tr>
 				<s:hidden name="assignTargetObj.hcmoTsAssignProjTargetId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorTimesheetAssignedTargetForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_AssignedProject_TargetForm" indicator="indicatorTimesheetAssignedTargetForm"/>
	    		    </td>
    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
   	
   	<div id="display_tag_achivedTarget_List_div_id">
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