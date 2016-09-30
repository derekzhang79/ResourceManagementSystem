
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EducationFormId_div">
		<div class="submenu_bg">
			<s:if test="#session.EDUCATION_ADD == true">
				<sj:a href="setUpEducation.action" targets="submenu_EducationFormId_div" indicator="indicatorSubMenuEducationFormId_div" cssClass="link"><s:text name="MTIAddEducation" /></sj:a> |
			</s:if>
			<s:if test="#session.EDUCATION_VIEW == true">
				<sj:a href="getAllEducation.action" targets="submenu_EducationFormId_div" indicator="indicatorSubMenuEducationFormId_div" cssClass="link"><s:text name="MTIViewEducation"/></sj:a>|
				<sj:a href="educationSearchForm.action" targets="submenu_EducationFormId_div" indicator="indicatorSubMenuEducationFormId_div" cssClass="link"><s:text name="MTISearchEducation"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuEducationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdateEducation">
    <table class="maintable">
       <tr>
        	<td align="center" ><table class="formouter">
       <tr>
            <td><table class="employeeformiinertable">
       <tr>
                <td class="formtitle">
	        		   <s:if test="edu==null || edu.empEducationId == null">
						 <s:text name="label.title.education.add"/>
					   </s:if>
					   <s:else>
						 <s:text name="label.title.education.edit"/>
				   		</s:else>
                </td>
        </tr>
     	<tr>
                <td class="forminner"><table class="tablealign">
		<s:if test="edu==null || edu.empEducationId == null">
			<tr>
	            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
	            <td class="employeeinputtd">
	            	<sj:autocompleter  
					    name="edu.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="edu.empIdObj.employeeId" /></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
				<td class="employeedisplaytd"><s:textfield name="edu.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
				<s:hidden name="edu.empIdObj.employeeId"></s:hidden>
			</tr>
		</s:else>			
        <tr>
			<td class="inputtext"><s:text name="label.form.fields.education.major"/></td>
			<td class="employeeinputtd"><s:textfield name="edu.eduMajor" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="edu.eduMajor" /></td>
        </tr>
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.education.year"/></td>
			<td class="employeeinputtd"><s:textfield name="edu.eduYear" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="edu.eduYear" /></td>
		</tr>
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.education.grade"/></td>
			<td class="employeeinputtd"><s:textfield name="edu.eduGrade" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="edu.eduGrade"/></td>
		</tr>
		<!--Button image added by, R.Deepika-->
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.common.startDate"/></td>
			<td class="employeeinputtd"><sj:datepicker name="edu.eduStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="edu.eduStartDate" /></td>
		</tr>
		<!-- Extra Format Text Removed by, R.Deepika -->
      	<tr>
      		<td class="inputtext"><s:text name="label.form.fields.common.enddate"/></td>
			<td class="employeeinputtd"><sj:datepicker name="edu.eduEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="edu.eduEndDate" /></td>
      	</tr>
      	<tr>
			<td></td>
			<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
		</tr>
        <tr>
	       	<td class="inputtext"><s:text name="label.form.fields.education.univ"/></td>
	   	    <td class="employeeinputtd"><s:textfield name="edu.eduUniversity" cssClass="employeeinput" /></td>
        	<td class="inputerrormessage"><s:fielderror fieldName="edu.eduUniversity" /></td>
         </tr>
         <tr>
	    	<td class="inputtext"><s:text name="label.form.fields.education.degree"/></td>
	        <td class="employeeinputtd"><s:textfield name="edu.eduDegree" cssClass="employeeinput" /></td>
	    	<td class="inputerrormessage"><s:fielderror fieldName="edu.eduDegree" /></td>
         </tr>
    	 <s:hidden name="edu.empEducationId"/>
    </table></td></tr></table></td></tr></table></td></tr></table><br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorEducationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EducationFormId_div" indicator="indicatorEducationFormId_div"/>
    		    </td>
    	        <td><s:if test="edu==null || edu.empEducationId==null">
		    	        	<s:url var="resetEducationForm" action="resetEducationForm"></s:url>
		    	            <sj:submit href="%{resetEducationForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EducationFormId_div" indicator="indicatorEducationFormId_div"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
    		 </tr>
    </table> 		  		 
    	</s:form>
</div>