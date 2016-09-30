<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssWorkExperience_Form_div">
	<div class="submenu_bg">
	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceForm" value="/setUpEmpWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueWorkExpEmployeeId" value="workexp.empIdObj.employeeId"></s:set>
	<s:url var="remoteWorkExperienceView" value="/getEmployeeAllWorkExperience.action">
		<s:param name="workexp.empIdObj.employeeId" value="#UniqueWorkExpEmployeeId"></s:param>
	</s:url>
		<s:if test="#session.WORKEXPERIENCE_ADD==true">
		    <sj:a href="%{remoteWorkExperienceForm}" indicator="indicatorWorkExpList" targets="submenu_EssWorkExperience_Form_div" cssClass="link"><s:text name="label.header.empExperience.add"/></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW==true">
			<sj:a href="%{remoteWorkExperienceView}" indicator="indicatorWorkExpList" targets="submenu_EssWorkExperience_Form_div" cssClass="link"><s:text name="label.header.empExperience.view"/></sj:a>
		</s:if>
	</div>		
<br />	
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

    <s:form action="insertOrUpdateWorkExperienceAjax">
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
                   <tr>
                  <td class="forminner"><table class="tablealign">
                 <tr><td class="inputtext"><s:text name="label.form.fields.empExperience.employerName"/></td>
         	        <td class="employeeinputtd"><s:textfield name="workexp.employeerName" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
                		<s:fielderror fieldName="workexp.employeerName" />
             		</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.empExperience.jobTitle"/></td>
         	        <td class="employeeinputtd"><s:textfield name="workexp.empJobTitle" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
                		<s:fielderror fieldName="workexp.empJobTitle" />
             		</td>
         </tr>
         <!--Button image added by, R.Deepika-->
         <!-- Date format text alligned by, R.Deepika -->
         <tr><td class="inputtext"><s:text name="label.form.fields.empExperience.fromDate"/></td>
         	 <td class="employeeinputtd">
       	        <sj:datepicker name="workexp.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	        
         	 </td>
         	 <td class="inputerrormessage">
                		<s:fielderror fieldName="workexp.fromDate" />
             </td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.empExperience.toDate"/></td>
         	 <td class="employeeinputtd">
       	        <sj:datepicker name="workexp.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	       
       	     </td> 
       	     <td class="inputerrormessage">
           		<s:fielderror fieldName="workexp.toDate" />
             </td>
         </tr>
         <tr>
	         <td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.empExperience.comments"/></td>
			<!-- rows and cols size has changed:venkat -->
			<td class="employeeinputtd"><s:textarea name="workexp.comments" cssClass="employeetextarea" rows="4" cols="26"/></td>
         </tr>
            
 	</table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td><img id="indicatorWorkExpForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssWorkExperience_Form_div" indicator="indicatorWorkExpForm"/>
    		    </td>
    	      <td><s:if test="workexp==null || workexp.empWorkExpId == null">
		    	        	<s:url var="resetWorkFormAjax" action="setUpEmpWorkExperience">
		    	        		<s:param name="workexp.empIdObj.employeeId" value="workexp.empIdObj.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetWorkFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssWorkExperience_Form_div" indicator="indicatorWorkExpForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
    		 </tr>
    </table> 		 
			<s:hidden name="workexp.empIdObj.employeeId"/>
            <s:hidden name="workexp.empWorkExpId"/>
</s:form>
</div>