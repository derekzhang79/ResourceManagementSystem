<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Answer_EmployeePeerEmployeeListId_div">
			<br/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="getPeerEmployeeQuestionBank">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.questionBankGeneralInfo.peerEmployee"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">    
		 		<tr>
		        	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		            <td class="employeeinputtd">
		             <s:select 
	            		 		name="employeeId"
	        					list="#request.peerEmployeeList" 
	     						listKey="employeeId" 
	       					   	listValue="empFullName"
	       					   	cssClass="employeeselect"
	              			  />
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="child.empIdObj.employeeId"/></td>
				</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorAnswerEmployeePeerEmployee" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_Answer_EmployeePeerEmployeeListId_div" indicator="indicatorAnswerEmployeePeerEmployee"/>
	    		    </td>
	    	        <td><s:if test="child==null || child.empChildrenId==null">
		    	        	<s:url var="resetChiForm" action="resetChiForm"></s:url>
		    	            <sj:submit href="%{resetChiForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_Answer_EmployeePeerEmployeeListId_div" indicator="indicatorAnswerEmployeePeerEmployee"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
	    		 </tr>
	    </table> 	
	    <s:hidden name="child.empChildrenId"/>		  		 
	    </s:form>
</div>		