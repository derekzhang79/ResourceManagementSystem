<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_WorkExperienceFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.WORKEXPERIENCE_ADD == true">
			<sj:a href="setUpWorkExperience.action" targets="submenu_WorkExperienceFormId_div" indicator="indicatorSubMenuWorkExperienceFormId_div" cssClass="link"><s:text name="MTIAddWorkExperience" /></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW == true">
			<sj:a href="getAllWorkExperience.action" targets="submenu_WorkExperienceFormId_div" indicator="indicatorSubMenuWorkExperienceFormId_div" cssClass="link"><s:text name="MTIViewWorkExperience"/></sj:a> |
			<sj:a href="workExpSearchForm.action" targets="submenu_WorkExperienceFormId_div" indicator="indicatorSubMenuWorkExperienceFormId_div" cssClass="link"><s:text name="MTISearchWorkExperience"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuWorkExperienceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateWorkExperience">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	                  	 <s:if test="workexp==null || workexp.empWorkExpId == null">
						 	<s:text name="label.title.workExperience.add"/>
	   					 </s:if>
	     				 <s:else>
		 				 	<s:text name="label.title.workExperience.edit"/>
	   					 </s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
		    <s:if test="workexp==null || workexp.empWorkExpId == null">
		    	<tr>
  			 		<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
        			<td class="employeeinputtd">
        				<sj:autocompleter  
						    name="workexp.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="workexp.empIdObj.employeeId" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
         	</s:if>
       		<s:else>
       			<tr>
	        		<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="workexp.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="workexp.empIdObj.employeeId" />
				</tr>
       		</s:else>
    		
	        <tr>
	        	<td class="inputtext"><s:text name="label.form.fields.empExperience.employerName"/></td>
	         	<td class="employeeinputtd"><s:textfield name="workexp.employeerName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="workexp.employeerName" /></td>
	        </tr>
	        <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empExperience.jobTitle"/></td>
	         	<td class="employeeinputtd"><s:textfield name="workexp.empJobTitle" cssClass="employeeinput"/></td>
       	        <td class="inputerrormessage"><s:fielderror fieldName="workexp.empJobTitle" /></td>
	        </tr>
	        <!--Button image added by, R.Deepika-->
	        <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empExperience.fromDate"/></td>
	         	<td class="employeeinputtd"><sj:datepicker name="workexp.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	       	    <td class="inputerrormessage"><s:fielderror fieldName="workexp.fromDate" /></td>
	        </tr>
	        <!-- Extra Format Text Removed by, R.Deepika -->
	        <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empExperience.toDate"/></td>
	         	<td class="employeeinputtd"><sj:datepicker name="workexp.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	       	    <td class="inputerrormessage"><s:fielderror fieldName="workexp.toDate" /></td>
	        </tr>
	        <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
			</tr>
	        <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empExperience.comments"/></td>
				<!-- text area size has changed:venkat -->
	         	<td class="employeeinputtd"><s:textarea name="workexp.comments" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="workexp.comments" /></td>
			</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorWorkExperienceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_WorkExperienceFormId_div" indicator="indicatorWorkExperienceFormId_div"/>
    		    </td>
    	        <td><s:if test="workexp==null || workexp.empWorkExpId==null">
	    	        	<s:url var="resetWorkExperienceForm" action="resetWorkExperienceForm"></s:url>
	    	            <sj:submit href="%{resetWorkExperienceForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_WorkExperienceFormId_div" indicator="indicatorWorkExperienceFormId_div"/>
	    	        </s:if>
	    	        <s:else>
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	        </s:else></td>
    		 </tr>
	    </table> 	
	    <s:hidden name="workexp.empWorkExpId"/>	  		 
    </s:form>
</div>	    	