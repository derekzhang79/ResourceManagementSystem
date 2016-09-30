<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
  
<div id="submenu_QuestionBank_General_InfoChooseEmpId_div">
<div class="submenu_bg">
	<sj:a href="setUpAssignEmployeeQuestions.action" targets="submenu_QuestionBank_General_InfoChooseEmpId_div" indicator="indicatorSubMenuQuestionBankEmployee" cssClass="link"><s:text name="MTIAssignedEmployeeQuestions" /></sj:a>
	<sj:a href="viewAssignedQuestionsList.action" targets="submenu_QuestionBank_General_InfoChooseEmpId_div" indicator="indicatorSubMenuQuestionBankEmployee" cssClass="link"><s:text name="MTIViewAssignedKPIGroup"/></sj:a> 
</div>
<br/>
<img id="indicatorSubMenuQuestionBankEmployee" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	 <s:form action="questionBankGeneralInfoEmployeeList">
	     <table class="maintable" width="500px">
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
	               <tr><td class="inputtext"><s:text name="label.form.fields.employeeGroupName"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="name" cssClass="employeeinput"/></td>
	        	   </tr>    
		 		   <tr>
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.employeeName" /></td>
						<td class="employeeinputtd">
							 <s:select 
	            		 		name="employeeId"
	        					list="#request.APPL_KPI_ASSIGN_EMPLOYEE_LIST" 
	     						listKey="employeeId" 
	       					   	listValue="empFullName"
	       					   	cssClass="employeeselect"
	              			  />
						</td>
						
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.empGroupName" /></td>
						<td class="employeeinputtd">
						<sj:autocompleter  
							    name="groupName"
								list="#request.APPL_QUESTIONBANK_LIST"
								listKey="hcmoQuestionGroupNameIdentificationId"
								listValue="name"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="employee.departmentIdObj.hcmoDepartmentId"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.approvingEmployeeName" /></td>
						<td class="employeeinputtd">
							<s:optiontransferselect 
									cssClass="optionselectcss" 
									id="appEmployeeName" 
									name="appEmployeeName" 
									doubleCssClass="optionselectcss" 
									doubleList="selectedAppEmployeeList" 
					          		doubleName="selectedAppEmployeeList" 
					          		doubleId="selectedAppEmployeeList" 
					          		list="approvingEmployeeList" 
					          		addAllToLeftLabel="Remove All" 
					          		addAllToRightLabel="Add All"
					          		addToRightLabel="Add" 
					          		addToLeftLabel="Remove" 
					          		rightTitle="Approving Employee List" 
					          		leftTitle="Approving Employee List" 
					          		allowSelectAll="false" 
					          		allowUpDownOnLeft="false" 
					          		allowUpDownOnRight="false" 
					          		buttonCssClass="optionselectbuttoncss">
					          						
					          	</s:optiontransferselect>
						</td>
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.appEmpGroupName" /></td>
						<td class="employeeinputtd">
						<sj:autocompleter  
							    name="approvingEmpGroupName"
								list="#request.APPL_QUESTIONBANK_LIST"
								listKey="hcmoQuestionGroupNameIdentificationId"
								listValue="name"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
				 </tr>
				 
				 <tr>
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.peerEmployeeName" /></td>
						<td class="employeeinputtd">
							<s:optiontransferselect 
									cssClass="optionselectcss" 
									id="peerEmployeeName" 
									name="peerEmployeeName" 
									doubleCssClass="optionselectcss" 
									doubleList="peerEmployeeList" 
					          		doubleName="peerEmployeeList" 
					          		doubleId="peerEmployeeList" 
					          		list="assignedEmployeeList" 
					          		addAllToLeftLabel="Remove All" 
					          		addAllToRightLabel="Add All"
					          		addToRightLabel="Add" 
					          		addToLeftLabel="Remove" 
					          		rightTitle="Peer Employee List" 
					          		leftTitle="Peer Employee List" 
					          		allowSelectAll="false" 
					          		allowUpDownOnLeft="false" 
					          		allowUpDownOnRight="false" 
					          		buttonCssClass="optionselectbuttoncss">
					          						
					          	</s:optiontransferselect>
						</td>
						<td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.peerEmpGroupName" /></td>
						<td class="employeeinputtd">
						<sj:autocompleter  
							    name="peerEmpGroupName"
								list="#request.APPL_QUESTIONBANK_LIST"
								listKey="hcmoQuestionGroupNameIdentificationId"
								listValue="name"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
				 </tr>
					
	   			 </table></td>
	   		 </tr></table></td></tr></table></td></tr></table>
	    		 <br/>
		    <table align="center"> 
		    	     <tr>
		    		    <td>
							<img id="indicatorQBankGeneral_chooseempId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_QuestionBank_General_InfoChooseEmpId_div" indicator="indicatorQBankGeneral_chooseempId_div" onclick="empId();"/>
		    		    </td>
		    		 </tr>
		    </table> 	
	</s:form>
</div>		
<script type="text/javascript">
	function empId(){
 		var appEmpIdCount = document.getElementById('selectedAppEmployeeList');
	 	for ( var int = 0; int < appEmpIdCount.length; int++) {
	 		appEmpIdCount.options[int].selected = true;
	 	}

		var peerEmpIdCount = document.getElementById('peerEmployeeList');
	 	for ( var int = 0; int < peerEmpIdCount.length; int++) {
	 		peerEmpIdCount.options[int].selected = true;
	 	  }
	 }
</script>		