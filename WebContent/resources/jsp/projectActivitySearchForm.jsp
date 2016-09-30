<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ProjectActivitySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.PROJECTACTIVITY_ADD == true">
		<sj:a href="setUpProjectActivity.action" targets="submenu_ProjectActivitySearchId_div" indicator="indicatorSubMenuProjectActivitySearchId_div" cssClass="link"><s:text name="MTIAddProjectActivity" /></sj:a> |
	</s:if>
	<s:if test="#session.PROJECTACTIVITY_VIEW == true">
		<sj:a href="getAllProjectActivity.action" targets="submenu_ProjectActivitySearchId_div" indicator="indicatorSubMenuProjectActivitySearchId_div" cssClass="link"><s:text name="MTIViewProjectActivity"/></sj:a> |
		<sj:a href="projectActivitySearcForm.action" targets="submenu_ProjectActivitySearchId_div" indicator="indicatorSubMenuProjectActivitySearchId_div" cssClass="link"><s:text name="MTISearchProjectActivity"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuProjectActivitySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	     <s:form action="projectActivitySearcResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.projectActivity.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	     	 <tr><td class="inputtext"><s:text name="label.header.projActivity.projectOwner"/></td>
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
				<td class="inputerrormessage"></td>
	     	 </tr>
	     	 <tr>
	     	 	<td class="inputtext"><s:text name="label.header.project.projectName"/></td>
     	     	<td class="employeeinputtd">
					<sj:autocompleter  
					    name="proActivity.proObj.projectId"
						list="#request.APPL_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
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
         		<td class="inputtext"><s:text name="label.header.projActivity.projectActivity"/></td>
	         	<td class="employeeinputtd"><s:textfield name="proActivity.activityName" cssClass="employeeinput"/></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorProjectActivityFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ProjectActivitySearchId_div" indicator="indicatorProjectActivityFormSearchId_div"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
	</s:form>
	</div> 