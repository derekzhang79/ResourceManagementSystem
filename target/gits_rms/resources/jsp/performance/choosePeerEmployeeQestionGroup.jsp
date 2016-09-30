<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 	<s:head/>
 		

<div id="submenu_QuestionBank_Peer_Employee_div">
<div class="submenu_bg">
		<sj:a href="setUpAssignEmployeeQuestions.action" targets="submenu_QuestionBank_Peer_Employee_div" indicator="indicatorSubMenuQuestionBankPeerEmployee" cssClass="link"><s:text name="MTIAssignedEmployeeQuestions" /></sj:a> 
</div>
<br/>
<img id="indicatorSubMenuQuestionBankPeerEmployee" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	 <s:form action="questionBankGeneralInfoPeerEmployeeList">
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
	               <tr>
			            <td class="inputtext"><s:text name="label.form.fields.questionBankGeneralInfo.employeeName"/></td>
			            <td class="employeeinputtd">
				            <s:select 
	            		 		name="employeeFullName"
	        					list="#request.emplList" 
	     						listKey="employeeId" 
	       					   	listValue="empFullName"
	       					   	cssClass="employeeselect"
	              			  />
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="employee.ethnicRaceIdObj.ethnicRaceId" /></td>
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
					          		leftTitle="Available Vendor Contact Email(s)" 
					          		allowSelectAll="false" 
					          		allowUpDownOnLeft="false" 
					          		allowUpDownOnRight="false" 
					          		buttonCssClass="optionselectbuttoncss">
					          						
					          	</s:optiontransferselect>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="employee.departmentIdObj.hcmoDepartmentId"/></td>
				 </tr>
	   			 </table></td>
	   		 </tr></table></td></tr></table></td></tr></table>
	    		 <br/>
		    <table align="center"> 
		    	     <tr>
		    		    <td>
							<img id="indicatorPeerEmp" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_QuestionBank_Peer_Employee_div" indicator="indicatorPeerEmp" onclick="empId();"/>
		    		    </td>
		    		 </tr>
		    </table> 	
	</s:form>
</div>				
 <script type="text/javascript">
 		function empId(){
 			var empIdCount = document.getElementById('peerEmployeeList');
 		 	for ( var int = 0; int < empIdCount.length; int++) {
 		 		empIdCount.options[int].selected = true;
 		 	  }
 	 		}
 	</script>
