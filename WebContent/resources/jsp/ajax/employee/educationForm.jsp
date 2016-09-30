<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssEducation_Form_div">
<div class="submenu_bg">
	<s:set name="UniqueEduEmployeeId" value="edu.empIdObj.employeeId"></s:set>
	<s:url var="remoteEduForm" value="/setUpEmpEducation.action">
		<s:param name="edu.empIdObj.employeeId" value="#UniqueEduEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueEduEmployeeId" value="edu.empIdObj.employeeId"></s:set>
		<s:url var="remoteEduView" value="/getEmployeeAllEducation.action">
	    <s:param name="edu.empIdObj.employeeId" value="#UniqueEduEmployeeId"></s:param>
	</s:url>
    <s:if test="#session.EDUCATION_ADD==true">
		<sj:a href="%{remoteEduForm}" indicator="indicatorEduList" targets="submenu_EssEducation_Form_div" cssClass="link"><s:text name="label.header.education.add"/></sj:a> |
    </s:if>
		<s:if test="#session.EDUCATION_VIEW==true">
	    <sj:a href="%{remoteEduView}" indicator="indicatorEduList" targets="submenu_EssEducation_Form_div" cssClass="link"><s:text name="label.header.education.view"/></sj:a>
	</s:if>
	</div>
	<br/>		
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdateEducationAjax">
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
     	         <tr><td class="inputtext"><s:text name="label.form.fields.education.major"/></td>
	         	 	<td class="employeeinputtd"><s:textfield name="edu.eduMajor" cssClass="employeeinput"/></td>
	         	 	<td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduMajor" />
					</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.education.year"/></td>
	         	 	<td class="employeeinputtd"><s:textfield name="edu.eduYear" cssClass="employeeinput"/></td>
	         	 	<td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduYear" />
					</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.education.grade"/></td>
         		 	<td class="employeeinputtd"><s:textfield name="edu.eduGrade" cssClass="employeeinput"/></td>
         		 	<td class="inputerrormessage"><s:fielderror fieldName="edu.eduGrade"/></td>
         </tr>
         <!--Button image added by, R.Deepika-->
         <!-- Date format text Alligned and Removed an extra text by R.Deepika -->
         <tr><td class="inputtext"><s:text name="label.form.fields.common.startDate"/></td>
		         	<td class="employeeinputtd">
		       	       <sj:datepicker name="edu.eduStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	      
		         	</td>
		         	<td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduStartDate" />
					</td>
					</tr>
					
         
         <tr><td class="inputtext"><s:text name="label.form.fields.common.enddate"/></td>
		    	    <td class="employeeinputtd">
		       	        <sj:datepicker name="edu.eduEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	      
		         	</td>
		         	<td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduEndDate" />
					</td>
         </tr>
         <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
         <tr><td class="inputtext"><s:text name="label.form.fields.education.univ"/></td>
         	        <td class="employeeinputtd"><s:textfield name="edu.eduUniversity" cssClass="employeeinput" /></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduUniversity" />
			 		</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.education.degree"/></td>
         	        <td class="employeeinputtd"><s:textfield name="edu.eduDegree" cssClass="employeeinput" /></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="edu.eduDegree" />
			 		</td>
         </tr>
             <s:hidden name="edu.empIdObj.employeeId"></s:hidden>
             <s:hidden name="edu.empEducationId"></s:hidden>
    </table>
    		 </td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center">  
    	     <tr>
    		    <td>
					<img id="indicatorEduForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssEducation_Form_div" indicator="indicatorEduForm"/>
    		    </td>
    		    <td><s:if test="edu==null || edu.empEducationId==null">
		    	        	<s:url var="resetEducationFormAjax" action="setUpEmpEducation">
								<s:param name="edu.empIdObj.employeeId" value="edu.empIdObj.employeeId"></s:param>
		    	        	</s:url>
		    	            <sj:submit href="%{resetEducationFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssEducation_Form_div" indicator="indicatorEduForm"/>
		    	 </s:if>
		    	 <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	 </s:else>
		    	</td>
    		 </tr>
    </table> 		  		 
    	</s:form>
</div>