<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Emp_TargetGoalFormId_div">
<div class="submenu_bg">
	<sj:a href="setUpEmpAddTargetGoal.action" targets="submenu_Emp_TargetGoalFormId_div" indicator="indicatorSubMenuEmpTargetGoalFormId_div" cssClass="link"><s:text name="MTIAddMyTaregtGoal" /></sj:a> |
	<sj:a href="getAllEmpTarget.action" targets="submenu_Emp_TargetGoalFormId_div" indicator="indicatorSubMenuEmpTargetGoalFormId_div" cssClass="link"><s:text name="MTIViewMyTaregtGoal"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuEmpTargetGoalFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<jsp:include page="common/messages.jsp" flush="true"/>
<s:if test="empTargetGoalCount == 0">
</s:if>
<s:else>
<s:form action="insertOrUpdateEmpTargetGoal">
    <table class="maintable">
       <tr>
        	<td align="center" ><table class="formouter">
       <tr>
            <td><table class="employeeformiinertable">
       <tr>
                <td class="formtitle">
	        		   <s:if test="empTAGObj==null || empTAGObj.hcmoEmpTgId == null">
						 <s:text name="label.title.targets.goal.add.emp"/>
					   </s:if>
					   <s:else>
						 <s:text name="label.title.targets.goal.edit.emp"/>
				   		</s:else>
                </td>
        </tr>
     	<tr>
                <td class="forminner"><table class="tablealign">
                <tr>
                	<td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
		     	     <td class="employeeinputtd">
		     	     	<s:select 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_EMP_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
					    name="empTAGObj.projectName.projectId"
					    id="EmpProjectName"
					    cssClass="employeeselect" 
					    onchange="getEmpProjectActivity(this);"
					    />
					 </td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.projectName.projectId"/>
				</td>
				</tr>
				<tr>
				<td class="inputtext"><s:text name="label.header.projActivity.projectActivityWithManadory"/></td>
	     	 	<td>
	     	 		<div id="resultEmpProjActivity">
	     	 			<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_EMP_PROJECT_ACTIVITY_LIST"
							listKey="projectActivityId"
							listValue="activityName"
							name="empTAGObj.projectActivityName.projectActivityId"
							cssClass="employeeselect" 
							onchange="getEmployeeTargetAndGoal(this);"
						    />
	     	 		</div>
	     	 	</td>
	     	 	<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.projectActivityName.projectActivityId"/>
				</td>
	     	   </tr>
	     	   <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.targets.name" /></td>
		     	<td class="employeeinputtd">
		     		<s:textfield name="empTAGObj.empTargetName" id="EmpTargetName" readonly="true" cssClass="employeeinput"/>
				</td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.empTargetName" />
		 		</td>
		       </tr>
		       <!-- <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.targets.type" /></td>
		     	<td class="employeeinputtd">
		     		<s:textfield name="empTAGObj.empTargetType" id="EmpTargetType" readonly="true" cssClass="employeeinput"/>
				</td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.empTargetType" />
		 		 </td>
		       </tr>
		       <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.targets.Mode" /></td>
		     	<td class="employeeinputtd">
		     		<s:textfield name="empTAGObj.empTargetMode" id="EmpTargetMode" readonly="true" cssClass="employeeinput" />
				</td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.empTargetMode" />
		 		</td>
		       </tr>
		       <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.goal.name" /></td>
		     	<td class="employeeinputtd">
		     		<s:textarea name="empTAGObj.empGoalName" id="EmpGoalName" rows="4" cols="26" readonly="true" cssClass="employeetextarea"/>
				</td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.empGoalName" />
		 		</td>
		       </tr> -->
		       <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.target.achieved" /></td>
		     	<td class="employeeinputtd">
		     		<s:textarea name="empTAGObj.empTargetAchieved" id="EmpTargetAchieved" rows="4" cols="26" cssClass="employeetextarea"/>
				</td>
				<td class="inputerrormessage">
					<s:fielderror fieldName="empTAGObj.empTargetAchieved" />
		 		</td>
		       </tr>
		       <tr>
		     	<td class="inputtext"><s:text name="label.form.fields.target.notes" /></td>
		     	<td class="employeeinputtd">
		     		<s:textarea name="empTAGObj.empTargetNotes" id="EmpTargetNotes" rows="4" cols="26" cssClass="employeetextarea"/>
				</td>
		       </tr>
				</table>
				</td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
  	     <tr>
  		    <td>
			<img id="indicatorEmpTGForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
  		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_Emp_TargetGoalFormId_div" indicator="indicatorEmpTGForm" />	
  		    </td>
  	        <td>
  	        <s:if test="empTAGObj==null || empTAGObj.hcmoEmpTgId == null">
   	        	<s:url var="resetEmpTargetGoalForm" action="resetEmpTargetGoalForm"></s:url>
   	            <sj:submit href="%{resetEmpTargetGoalForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_Emp_TargetGoalFormId_div" indicator="indicatorEmpTGForm"/>
	    	</s:if>
	    	<s:else>
	    	    <s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	</s:else>
	    	</td>
  		 </tr>
    </table> 		  		 
</s:form>
</s:else>
</div>
 <script type="text/javascript">
    function getEmpProjectActivity(projId)
    { 
        var url = 'getEmpProjectActivity.action';
        jQuery.ajax({
             type: "POST",
             dataType: "html",
             url: url,
             data: "projectId="+projId.value,
             success: function(data){       
             jQuery("#resultEmpProjActivity").html(data);
             }      
           });
     
    }
    function getEmployeeTargetAndGoal(projActId) {
    	var url = 'getEmployeeTargetAndGoal.action';
        jQuery.ajax({
             type: "POST",
             dataType: "html",
             url: url,
             data: "projectId="+jQuery('#EmpProjectName').val()+"&projectActivityId="+projActId.value,
             success: function(data){
	             data = data.trim();
	             var groupTG = new Array();
	             groupTG = data.split("$$$");
	             var i=0;
	             while (i<groupTG.length){
	               	if (i == 0) {
	               		jQuery("#EmpTargetName").val(groupTG[i]);
					}
	               	if (i == 1) {
	               		jQuery("#EmpTargetType").val(groupTG[i]);
					}
	               	if (i == 2) {
	               		jQuery("#EmpTargetMode").val(groupTG[i]);
					}
	               	if (i == 3) {
	               		jQuery("#EmpGoalName").val(groupTG[i]);
					}
	               i++;
	             }
             }      
           });
	}
</script>