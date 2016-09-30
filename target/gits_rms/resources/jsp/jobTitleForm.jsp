<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_JobTitleFormId_div">
<div class="submenu_bg">
	<s:if test="#session.JOBTITLE_ADD == true">
		<sj:a href="setUpJobTitle.action" targets="submenu_JobTitleFormId_div" indicator="indicatorSubMenuJobTitleFormId_div" cssClass="link"><s:text name="MTIAddJobTitle" /></sj:a> |
	</s:if>
	<s:if test="#session.JOBTITLE_VIEW == true">
		<sj:a href="getAllJobTitle.action" targets="submenu_JobTitleFormId_div" indicator="indicatorSubMenuJobTitleFormId_div" cssClass="link"><s:text name="MTIViewJobTitle"/></sj:a> |
		<sj:a href="jobTitleSearchForm.action" targets="submenu_JobTitleFormId_div" indicator="indicatorSubMenuJobTitleFormId_div" cssClass="link"><s:text name="MTISearchJobTitle"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuJobTitleFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="jobTitle==null || jobTitle.jobTitleId == null">
	   <s:if test="#session.SALARYGRADE_ADD == true">
		<s:div cssClass="helpinformationmessage">
	    	<s:text name="label.header.jobTitle.msg.salarygrade"/>
		</s:div>
	   </s:if>
	</s:if>
	
	    <s:form action="insertOrUpdateJobTitle">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
	        		<s:if test="jobTitle==null || jobTitle.jobTitleId == null">
						<s:text name="label.title.jobTitle.add"/>
				    </s:if><s:else>
						 <s:text name="label.title.jobTitle.edit"/>
				   </s:else>
                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
				<tr>
		            <td class="inputtext"><s:text name="label.form.fields.salaryGrade.name"/></td>
		            <td class="employeeinputtd">
		            	<sj:autocompleter  
						    name="jobTitle.salaryGradeIdObj.hcmoSalaryGradeId"
							list="#request.APPL_SALARYGRADE_LIST"
							listKey="hcmoSalaryGradeId"
							listValue="salaryName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
		            <td class="inputerrormessage"><s:fielderror fieldName="jobTitle.salaryGradeIdObj.hcmoSalaryGradeId"/></td>
	        	</tr>
	        	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
				<tr>
            		<td class="inputtext"><s:text name="label.form.fields.jobTitle.name"/></td>
         			<td class="employeeinputtd"><s:textfield name="jobTitle.jobTitleName" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage"><s:fielderror fieldName="jobTitle.jobTitleName"/></td>
	         	</tr>
		         <tr>
		         	<td class="inputtext"><s:text name="label.form.fields.common.comments"/></td>
	       	        <!-- textarea size has been changed : venkat -->
	       	        <td class="employeeinputtd"><s:textarea name="jobTitle.jobTitleComments" rows="4" cols="26" cssClass="employeetextarea"/></td>
		         </tr>
		         <tr>
		         	<td class="inputtext"><s:text name="label.form.fields.common.description"/></td>
		         	<!-- textarea size has been changed : venkat -->
		         	<td class="employeeinputtd"><s:textarea name="jobTitle.jobTitleDesc" rows="4" cols="26" cssClass="employeetextarea"/></td>
		         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center">
	    	     <tr>
	    		    <td>
						<img id="indicatorJobTitleForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_JobTitleFormId_div" indicator="indicatorJobTitleForm"/>
	    		   	</td>
	    		   	<s:if test="jobTitle==null || jobTitle.jobTitleId==null">
				        <td>
		    		    	<s:url var="resetJobTitle" action="resetJobTitle"></s:url>
		    	            <sj:submit href="%{resetJobTitle}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_JobTitleFormId_div" indicator="indicatorJobTitleForm"/>
			           </td>
			       </s:if>
			      <s:else>
	    	          <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	      </s:else>
	    		 </tr>
	    </table>
	    <s:hidden name="jobTitle.jobTitleId"/> 		  		 
   	</s:form>
</div>