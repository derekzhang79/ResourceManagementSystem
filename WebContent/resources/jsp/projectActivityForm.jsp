<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectActivityFormId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECTACTIVITY_ADD == true">
		<sj:a href="setUpProjectActivity.action" targets="submenu_ProjectActivityFormId_div" indicator="indicatorSubMenuProjectActivityFormId_div" cssClass="link"><s:text name="MTIAddProjectActivity" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECTACTIVITY_VIEW == true">
		<sj:a href="getAllProjectActivity.action" targets="submenu_ProjectActivityFormId_div" indicator="indicatorSubMenuProjectActivityFormId_div" cssClass="link"><s:text name="MTIViewProjectActivity"/></sj:a> |
		<sj:a href="projectActivitySearcForm.action" targets="submenu_ProjectActivityFormId_div" indicator="indicatorSubMenuProjectActivityFormId_div" cssClass="link"><s:text name="MTISearchProjectActivity"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectActivityFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="proActivity==null || proActivity.projectActivityId == null">
		<s:if test="#session.PROJECT_ADD == true">
			<s:div cssClass="helpinformationmessage">
		    	<s:text name="label.header.projActivity.msg.project"/>
			</s:div>
		</s:if>
    </s:if> 
    		
	
	     <s:form action="insertOrUpdateProjectActivity" id="projectActivityFormId">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
	       		      <s:if test="proActivity==null || proActivity.projectActivityId == null">
						 <s:text name="label.title.projectActivity.add"/>
					   </s:if>
					   <s:else>
						 <s:text name="label.title.projectActivity.edit"/>
					   </s:else>
	                </td>
	         </tr>
	     	 <tr>
             	<td class="forminner"><table class="tablealign"> 
	     	 
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.projActivity.projectOwner"/></td>
		     		<td class="employeeinputtd">
		     			<sj:autocompleter  
					   	 	name="proActivity.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="proActivity.empIdObj.employeeId"/></td>
	     	 	</tr>
     	 		<!-- extra text removed by, R.Deepika <tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>-->
	     	 	<tr>
	     	 		<td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
	     	     	<td class="employeeinputtd">
	     	     		<sj:autocompleter  
	     	     			id="proActivity.proObj.projectId"
						    name="proActivity.proObj.projectId"
							list="#request.APPL_PROJECT_LIST"
							listKey="projectId"
							listValue="projectName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td>
						<sj:dialog 
					    	id="projActivityGetPrevAddedProjListDialog" 
					    	autoOpen="false" 
					    	modal="true" 
					    	title="List Of Project Activity"
					    	width="700"
					    	height="400"
					    />
					    
					    <s:url var="getPrevAddedProjActivityByProj" action="getPrevAddedProjActivityByProj">
					    	<s:param name="proActivity.proObj.projectId" value="proActivity.proObj.projectId"></s:param>
					    </s:url>
						<sj:submit formIds="projectActivityFormId" openDialog="projActivityGetPrevAddedProjListDialog" href="%{getPrevAddedProjActivityByProj}" button="true" value="Check"></sj:submit>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="proActivity.proObj.projectId"/></td>
	     	 	</tr>
	     	 	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
	     	 	<tr>
            		<td class="inputtext"><s:text name="label.header.projectActivity.estimatedHours"/></td>
	         		<td class="employeeinputtd"><s:textfield name="proActivity.proObj.estimatedHours" id="proActivity.proObj.estimatedHours" readonly="true" onfocus="javascript:getProjectEstHours()" cssClass="employeeinput"/></td>
				</tr>
				<s:if test="proActivity == null || proActivity.projectActivityId == null">
					<tr>
	            		<td class="inputtext"><s:text name="label.header.projectActivity.remainingHours"/></td>
		         		<td class="employeeinputtd"><s:textfield name="proActivity.proObj.remainingHours" id="proActivity.proObj.remainingHours" readonly="true" cssClass="employeeinput"/></td>
					</tr>
	     	 	</s:if>
	         	<tr>
	         		<td class="inputtext"><s:text name="label.form.fields.projActivity.projectActivity"/></td>
		         	<td class="employeeinputtd"><s:textfield name="proActivity.activityName" cssClass="employeeinput"/></td>
		         	<td class="inputerrormessage"><s:fielderror fieldName="proActivity.activityName"/></td>
	         	</tr>
	         	<tr>
		         	<td class="inputtext"><s:text name="label.header.projectActivity.estHours"/></td>
		         	<td class="employeeinputtd"><s:textfield name="proActivity.estimatedHours" cssClass="employeeinput"/></td>
		         	<td class="inputerrormessage"><s:fielderror fieldName="proActivity.estimatedHours"/></td>
	         	</tr>
		        <tr>
		         	<td class="inputtext"><s:text name="label.form.fields.common.notes"/></td>
					<!-- text area size has changed:venkat -->
         	        <td class="employeeinputtd"><s:textarea name="proActivity.activityNotes" cssClass="employeetextarea" rows="4" cols="26"/></td>
		         </tr>
	            <s:hidden name="proActivity.projectActivityId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorProjectActivityFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ProjectActivityFormId_div" indicator="indicatorProjectActivityFormId_div"/>
    		    </td>
    		    <s:if test="proActivity==null || proActivity.projectActivityId==null">
			         <td>
	    		    	<s:url var="resetProjectActivity" action="resetProjectActivity"></s:url>
	    	            <sj:submit href="%{resetProjectActivity}" key="button.label.reset" cssClass="submitbutton117" targets="submenu_ProjectActivityFormId_div" indicator="indicatorProjectActivityFormId_div"/>
		            </td>
		       </s:if>
		       <s:else>
    	           <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	       </s:else>
    		 </tr>
	    </table> 		  		 
	</s:form>
</div>    	