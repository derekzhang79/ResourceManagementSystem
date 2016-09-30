<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECT_ADD == true">
		<sj:a href="setUpProject.action" targets="submenu_ProjectSearchId_div" indicator="indicatorSubMenuProjectSearchFormId_div" cssClass="link"><s:text name="MTIAddProject" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECT_VIEW == true">
		<sj:a href="getAllProjects.action" targets="submenu_ProjectSearchId_div" indicator="indicatorSubMenuProjectSearchFormId_div" cssClass="link"><s:text name="MTIViewProject"/></sj:a> |
		<sj:a href="projectsSearchForm.action" targets="submenu_ProjectSearchId_div" indicator="indicatorSubMenuProjectSearchFormId_div" cssClass="link"><s:text name="MTISearchProject"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	    <s:form action="projectsSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
             	<td class="formtitle">
		 			<s:text name="label.title.project.search"/>
             	</td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
				<tr>
		            <td class="inputtext"><s:text name="label.header.project.projectOwner"/></td>
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
					<td class="inputerrormessage"></td>	
	        	</tr>
				<tr>
		            <td class="inputtext"><s:text name="label.header.customer.name"/></td>
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
		        </tr>
		        <!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
            	<tr>
            		<td class="inputtext"><s:text name="label.header.project.projectName"/></td>
         	        <td class="employeeinputtd"><s:textfield name="proj.projectName" cssClass="employeeinput"/></td>
	        	</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorProjectForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ProjectSearchId_div" indicator="indicatorProjectForm"/>				    
    		    </td>
    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>