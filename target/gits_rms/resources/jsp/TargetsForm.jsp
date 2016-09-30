<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<div id="submenu_TargetsFormId_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETS_ADD == true">
		<sj:a href="setUpTargets.action" targets="submenu_TargetsFormId_div" indicator="indicatorSubMenuTargetsFormId_div" cssClass="link"><s:text name="MTIAddTargets" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETS_VIEW == true">
		<sj:a href="getAllTargets.action" targets="submenu_TargetsFormId_div" indicator="indicatorSubMenuTargetsFormId_div" cssClass="link"><s:text name="MTIViewTargets"/></sj:a> |
		<sj:a href="targetsSearchForm.action" targets="submenu_TargetsFormId_div" indicator="indicatorSubMenuTargetsFormId_div" cssClass="link"><s:text name="MTISearchTargets"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTargetsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateTargets" id="targetAddFormId">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
              <td class="formtitle">
       		   <s:if test="target==null || target.hcmoTargetId == null">
				 <s:text name="label.title.targets.add"/>
			   </s:if>
			   <s:else>
				 <s:text name="label.title.targets.edit"/>
			   </s:else>
              </td>
	         </tr>
	     	 <tr>
	         	<td class="forminner"><table class="tablealign">
	         <tr><td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
	     	     <td class="employeeinputtd">
					<s:select
						id="target.projObj.projectId"
						name="target.projObj.projectId" 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
					    cssClass="employeeselect" 
					    onchange="getActivity(this);"
				    />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="target.projObj.projectId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.projActivity.projectActivityWithManadory"/></td>
	     	 	<td>
	     	 		<div id="resultActivity_targetformId_div">
	     	 			<s:select 
			     	 		id="activity"
			     	 		name="activity"
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_ACTIVITY_LIST"
							listKey="projectActivityId"
							listValue="activityName"
							cssClass="employeeselect" 
					    />
	     	 		</div>
	     	 	</td>
     	 	</tr>	
	        <tr>
	         	 <td class="inputtext"><s:text name="label.form.fields.targets.name"/></td>
        	     <td class="employeeinputtd"><s:textfield name="target.targetName" cssClass="employeeinput"/></td>
        	     <td class="inputerrormessage">
					<s:fielderror fieldName="target.targetName" />
		 		 </td>
	         </tr>
	         <tr><td class="inputtext"><s:text name="label.form.fields.targets.type"/></td>
	     	     <td class="employeeinputtd">
					<s:select
						id="target.targetTypeObj.hcmoTargetTypeId"
						name="target.targetTypeObj.hcmoTargetTypeId" 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_TARGETTYPE_LIST"
						listKey="hcmoTargetTypeId"
						listValue="targetTypeName"
					    cssClass="employeeselect" 
				    />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="target.targetTypeObj.hcmoTargetTypeId"/></td>
			</tr>
			<tr>
	         	 <td class="inputtext"><s:text name="label.header.targets.value"/></td>
        	     <td class="employeeinputtd"><s:textfield name="target.targetValue" cssClass="employeeinput"/></td>
        	     <td class="inputerrormessage">
					<s:fielderror fieldName="target.targetValue" />
		 		 </td>
	         </tr>
	         <tr>
	         	 <td class="inputtext"><s:text name="label.form.fields.targets.Mode"/></td>
        	     <td class="employeeinputtd">
        	     	<sj:autocompleter
						selectBox="true"
						selectBoxIcon="true"
						list="#{'Daily':'Daily','Weekly':'Weekly','Monthly':'Monthly'}"
						name="target.targetMode"
						id="target.targetMode"
        	     	/>
        	     </td>
        	     <td class="inputerrormessage">
					<s:fielderror fieldName="target.targetMode" />
		 		 </td>
	         </tr>
	         	<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
	            <s:hidden name="target.hcmoTargetId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorTargetFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TargetsFormId_div" indicator="indicatorTargetFormId_div"/>
	    		    </td>
	    		     <s:if test="target==null || target.hcmoTargetId==null">
		    		    <td>
		    		    	<s:url var="resetTarget" action="resetTarget"></s:url>
		    	            <sj:submit href="%{resetTarget}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_TargetsFormId_div" indicator="indicatorTargetFormId_div"/>
						</td>
					</s:if>
					<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 

 <script type="text/javascript">
	    function getActivity(projId){ 
	        var url = 'getProjectActivity.action';
	        jQuery.ajax({
	             type: "POST",
	             dataType: "html",
	             url: url,
	             data: "projectId="+projId.value,
	             success: function(data){       
	             jQuery("#resultActivity_targetformId_div").html(data);
	             }      
	           });
	     
	    }
    </script>