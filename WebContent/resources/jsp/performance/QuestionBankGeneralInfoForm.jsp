<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_QuestionBank_General_Info_div">
	<div class="submenu_bg">
		<sj:a href="setUpQuestBankGeneralInfoForm.action" targets="submenu_QuestionBank_General_Info_div" indicator="indicatorSubMenuQuestionGeneralIfoFormId_div" cssClass="link"><s:text name="MTIAssignedEmployeeQuestions" /></sj:a> |
		<sj:a href="viewAssignedQuestionsList.action" targets="submenu_QuestionBank_General_Info_div" indicator="indicatorSubMenuQuestionGeneralIfoFormId_div" cssClass="link"><s:text name="MTIViewAssignedKPIGroup"/></sj:a> 
	</div>
<br/>
	<img id="indicatorSubMenuQuestionGeneralIfoFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="getGeneralInfoGroup">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.questionBankGeneralInfo.add"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">    
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.department.name" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="departmentName"
								list="#request.APPL_DEPARTMENT_LIST"
								listKey="hcmoDepartmentId"
								listValue="deptName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="departmentName"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.team.name" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="teamName"
								list="#request.APPL_TEAM_LIST"
								listKey="hcmoTeamId"
								listValue="teamName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="teamName"/></td>
					</tr>
					<tr>        
			            <td class="inputtext"><s:text name="label.form.fields.jobTitle.name"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="jobTitleName"
								list="#request.APPL_JOBTITLE_LIST"
								listKey="jobTitleId"
								listValue="jobTitleName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="jobTitleName" /></td>
			        </tr>
				<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
				<tr>
	     	 		<td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
	     	     	<td class="employeeinputtd">
	     	     		<sj:autocompleter  
						    name="projectName"
							list="#request.APPL_PROJECT_LIST"
							listKey="projectId"
							listValue="projectName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="proActivity.proObj.projectId"/></td>
	     	 	</tr>
	     	 	<!--Button image added by, R.Deepika-->
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.performanceIndStartDate"/></td>
  	         	<td class="employeeinputtd"><sj:datepicker name="startDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
      	     	<td class="inputerrormessage"><s:fielderror fieldName="startDate"/></td>
	         </tr>
	          <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.performanceIndEndDate"/></td>
  	         	<td class="employeeinputtd"><sj:datepicker name="endDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
      	     	<td class="inputerrormessage"><s:fielderror fieldName="endDate"/></td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
			</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorQBankGeneral" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_QuestionBank_General_Info_div" indicator="indicatorQBankGeneral"/>
	    		    </td>
		    	         <td><s:if test="child==null || child.empChildrenId==null">
		    	        	<s:url var="resetChiForm" action="resetChiForm"></s:url>
		    	            <sj:submit href="%{resetChiForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_Children_div" indicator="indicatorChiForm"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
	    		 </tr>
	    </table> 	
	    <s:hidden name="child.empChildrenId"/>		  		 
	    </s:form>
</div>	