
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_Emp_TargetandGoalViewId_div">
<br/>
<img id="indicatorSubMenuEmpTargetandGoalViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.targets.goal.view.emp" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.employeeName.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.project.projectName" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.projectName.projectName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.projectActivity.name" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.projectActivityName.activityName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.name" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empTargetName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.type" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empTargetType" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.targets.mode" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empTargetMode" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.goal.name" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empGoalName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.target.achieved" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empTargetAchieved" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.target.notes" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.empTargetNotes" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="empTAGObj.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="empTAGObj.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="empTAGObj.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="empTAGObj.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorViewTargetBack" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="setUpViewTargetGoal" action="setUpViewTargetGoal"></s:url>
	   			 <sj:submit href="%{setUpViewTargetGoal}"  key="Back" cssClass="submitbutton117" targets="submenu_Emp_TargetandGoalViewId_div" indicator="indicatorViewTargetBack"/>
   		    </td>
   		</tr>
   	  </table>
</s:form>
</div>