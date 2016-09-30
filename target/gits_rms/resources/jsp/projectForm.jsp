<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectFormId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECT_ADD == true">
		<sj:a href="setUpProject.action" targets="submenu_ProjectFormId_div" indicator="indicatorSubMenuProjectFormId_div" cssClass="link"><s:text name="MTIAddProject" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECT_VIEW == true">
		<sj:a href="getAllProjects.action" targets="submenu_ProjectFormId_div" indicator="indicatorSubMenuProjectFormId_div" cssClass="link"><s:text name="MTIViewProject"/></sj:a> |
		<sj:a href="projectsSearchForm.action" targets="submenu_ProjectFormId_div" indicator="indicatorSubMenuProjectFormId_div" cssClass="link"><s:text name="MTISearchProject"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="proj==null || proj.projectId == null">
		<s:if test="#session.CUSTOMER_ADD == true">
			<s:div cssClass="helpinformationmessage">
		    	<s:text name="label.header.project.msg.coustomer"/>
			</s:div>
		</s:if>
	</s:if>	
	
	    <s:form action="insertOrUpdateProject">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		      <s:if test="proj==null || proj.projectId == null">
								 <s:text name="label.title.project.add"/>
							   </s:if>
							   <s:else>
								 <s:text name="label.title.project.edit"/>
							   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	<tr>
	            <td class="inputtext"><s:text name="label.form.fields.project.projectOwner"/></td>
	            <td class="employeeinputtd">
	            	<sj:autocompleter  
					    name="proj.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="proj.empIdObj.employeeId"/></td>
	        </tr>
	      <!-- Extra text removed by, R.Deepika  <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>-->
			<tr>
	            <td class="inputtext"><s:text name="label.form.fields.customer.name"/></td>
	             <td class="employeeinputtd">
	             	<sj:autocompleter  
					    name="proj.customerId.customerId"
						list="#request.APPL_CUSTOMER_LIST"
						listKey="customerId"
						listValue="customerName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
	            <td class="inputerrormessage"><s:fielderror fieldName="proj.customerId.customerId"/></td>
	        </tr>
	        <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
	        <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
       	        <td class="employeeinputtd"><s:textfield name="proj.projectName" cssClass="employeeinput"/></td>
       	        <td class="inputerrormessage"><s:fielderror fieldName="proj.projectName"/></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.project.estimatedHours"/></td>
	         	<td class="employeeinputtd"><s:textfield name="proj.estimatedHours" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="proj.estimatedHours"/></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.common.description"/></td>
				<!-- text area size has changed:venkat -->
	         	<td class="employeeinputtd"><s:textarea name="proj.projectDesc" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorProjectFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ProjectFormId_div" indicator="indicatorProjectFormId_div"/>
    		    </td>
    		    <s:if test="proj==null || proj.projectId==null">
			        <td>
	    		    	 <s:url var="resetProject" action="resetProject"></s:url>
	    	             <sj:submit href="%{resetProject}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_ProjectFormId_div" indicator="indicatorProjectFormId_div"/>
		            </td>
		       </s:if>
		       <s:else>
    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	       </s:else>
    		 </tr>
	    </table> 	
	    <s:hidden name="proj.projectId"/>	  		 
   	</s:form>
</div>